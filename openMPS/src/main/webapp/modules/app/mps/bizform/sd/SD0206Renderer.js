/**
 * Project : openMPS MIS
 *
 * 영업 > 판매단가 승인
 *
 */

define([
	"Logger",
	"NDSProps",
	"WorkAreaHeader",
	"WorkAreaRenderer2",
	"SubContainer",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, WorkAreaHeader, WorkAreaRenderer2, SubContainer, JQGrid4)
{
	var Renderer = WorkAreaRenderer2.extend(
	{
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "SD0206";
		}
		,
		initSubHeader: function()
		{
			Logger.debug("Renderer.initSubHeader()");

			var self = this;
			return this.queryHeaderInfo().then(function(headerParams)
			{
				//
				headerParams.options.feature =
				[
					{"query":"조회"},
					{"save":"저장"},
					//{"delete":"삭제"},
					{"manual":"도움말"}
				];

				Renderer.__super__.initSubHeader.call( self, headerParams );
			});
		}
		,
		getItemList: function()
		{
			return [
			        "queryBox",
			        "resultBox",
			        "resultBox.content"
			        ];
		}
		,
		getHeaderGridName: function()
		{
			return "resultBox.content";
		}
		,
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachFormItem("queryBox");
			this.attachFormItem("resultBox");

			//
			this.attachHeaderItem("resultBox.header", function(evt)
			{
				if(evt.cmd == "content:confirm")
				{
					self._setApproState("confirm");
				}
				else if(evt.cmd == "content:reject")
				{
					self._setApproState("reject");
				}
			});
			//
			var gridItem = this.attachGridItem("resultBox.content");

			gridItem.getItem().on(JQGrid4.EVENT.EDITCELL, function(item)
			{
				self.onGridEditCell( gridItem, item.id );
			});
			
		}
		,
		_setButtonState: function(ids, bEnable)
		{
			var container = this._box.result;
			container.getHeader().setButtonState(ids, bEnable);
		}
		,
		onQueryMasterGrid: function()
		{
			var self = this;
			var query = this._getQueryData();
			
			if(query.status == 0)
				query.status = "ALL";
			
			if( new Date(query.strtDate) > new Date(query.lastDate) )
			{
				UCMS.alert("조회 기간이 잘못 지정되었습니다.");
				return;
			}

			this._queryGridData( this._getHeaderGridName(), query, function(res)
			{
				self._setResult(res);

				//
				self._setButtonState(["confirm"], query.status == "nappro");
				self._setButtonState(["reject"], query.status == "nappro");
			});
		}
		,
		getReqData : function()
		{
			var gridItem = this.getActiveGrid();
			var grid = gridItem.getItem();
			
			var ids = grid.getSelRowId(true);
			if( ids.length == 0 )
			{
				ids = [grid.getSelRowId()];
			}

			if( ids[0] == null )
			{
				UCMS.alert("선택된 행이 없습니다.");
				return false;
			}

			var reqTasks = [];
			for(var i in ids)
			{
				var rowData = grid.getRowData( ids[i] );
				reqTasks.push( rowData );
			}
			
			return reqTasks;
		}
		,
		/**
		 * 지정한 목록의 상태를 설정한다.
		 */
		_setApproState: function(mode)
		{
			var client = this.getClient();
			if( !client )
			{
				return UCMS.retReject();
			}
			
			var resultMsg = "";
			
			if(mode == "confirm")
				resultMsg = "승인처리 되었습니다.";
			else
				resultMsg = "승인취소 되었습니다.";

			var query = this.getQueryData();
			query.appro = mode;
			
			var self = this;
			
			var list = self.getReqData();
			
			if(!list) return;
			
			var gridItem = this.getActiveGrid();
			var apiPath = client.getAPIPath("create", mode, query);
			return client.transaction(apiPath, list)
			.then(function(result)
			{
				if( result.resultCode == 0 )
				{
					// 그리드 상태 변경
					UCMS.alert(result.extraData.result.length + "건이 " + resultMsg)
					.then(function(){
						gridItem.setData(result.extraData.result);
					});
					//self._applyToGrid(mode, result.extraData);
				}
				else
				{
					Logger.warn(result);
					UCMS.alert("요청을 처리하는 중 에러가 발생하였습니다.");
				}
			})
			.fail(function(e)
			{
				Logger.error(e);
				UCMS.alert("요청을 처리하는 중 에러가 발생하였습니다.");
			});
		}
		/**
		 * 가격변경
		 * */
		,
		onGridEditCell: function(gridItem, rowId)
		{
			var rowData = this.applyPrice(gridItem.getItem().getRowData( rowId ));
			gridItem.setRow( rowId, rowData );

			//
			this.beginTransaction();
		}
		,
		applyPrice : function(rowData)
		{
			
			Logger.debug("rowData.totalPrice :: " + rowData.totalPrice);
			Logger.debug("rowData.stndPrice :: " + rowData.stndPrice);
			
			rowData.increase = Number(rowData.increase);
			rowData.salePrice = !isNaN(rowData.totalPrice) &&  rowData.totalPrice != "null" ? Number(rowData.totalPrice) + rowData.increase : 0;
			rowData.stndPrice = !isNaN(rowData.stndPrice) &&  rowData.stndPrice != "null"  ? Number(rowData.stndPrice) : 0;
			rowData.lastStndPrice = Number(rowData.lastStndPrice);
			rowData.increasePrice =  ( rowData.stndPrice == 0 &&  rowData.lastStndPrice == 0 )
						? 0
						: ( rowData.stndPrice - rowData.lastStndPrice) + (rowData.increase) ;
			rowData.discPer = (rowData.salePrice == 0 || rowData.stndPrice == 0 || rowData.increasePrice == 0)
						? 0
						: (1 - (rowData.salePrice / rowData.stndPrice)*100.0).toFixed(2);
			//
			return rowData;
		}
		,
		onSave: function()
		{
			Logger.info("WorkAreaRenderer.onSave()");
			
			var self = this;
			var client = this.getClient();
			var errHandler = function(res){
				
				UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");
			}
			
			var query = this.getQueryData();
			query.appro = "upsave";
			
			var gridItem = this.getActiveGrid();
			var list = gridItem.getItem().getRowData();
			
			$.each(list, function(i,item){
				item.dsType="U";
			});

			var apiPath = client.getAPIPath("create", null, query);
			return client.transaction(apiPath, list)
			.then(function(result)
			{
				if( result.resultCode == 0 )
				{
					UCMS.alert("저장되었습니다.")
					.then(function(){
						self.setReadyMode();
						self.onQueryMasterGrid();
					});
					
				}
				else
				{
					Logger.warn(result);
					UCMS.alert("요청을 처리하는 중 에러가 발생하였습니다.");
				}
			})
			.fail(function(e)
			{
				Logger.error(e);
				UCMS.alert("요청을 처리하는 중 에러가 발생하였습니다.");
			});
		}
	});

	return Renderer;
});
