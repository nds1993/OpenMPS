/**
 * Project : openMPS MIS
 *
 * SD 영업 > 주문관리 > 출고승인
 * SD0403
 *
 */

define([
	"Logger",
	"NDSProps",
	"WorkAreaRenderer2",
	"SubContainer",
	"WorkAreaHeader",
	"manifest!jqGrid4-1.0.0#widget",

], function(Logger, NDSProps, WorkAreaRenderer2, SubContainer, WorkAreaHeader, JQGrid)
{
	var Renderer = WorkAreaRenderer2.extend(
	{
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "SD0403";
		}
		,
		/**
		 * 2. 접근이 필요한 아이템 목록 지정
		 */
		getItemList: function()
		{
			return [
			        "queryBox",
					"resultBox",
					"resultBox.leftGrid",
					"resultBox.rightGrid",
					"resultBox.leftGrid.content"
			        ];
		}
		,
		/**
		 * 1. 헤더 기능 버튼 초기화
		 */
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
 					//{"delete":"삭제"},
 					//{"save":"저장"},
 					//{"cancel":"취소"},
 					{"manual":"도움말"}
 				];

 				Renderer.__super__.initSubHeader.call( self, headerParams );
 			});
 		}
		,
		/**
		 * 4. 랜더러 아이템 관계성 초기화
		 */
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachHeaderItem("headerBox");
			this.attachFormItem("queryBox");

			this.attachHeaderItem("resultBox.leftGrid.header", function(event)
			{
				self.onButtonHandler(event);
			});
			//
			//
			var headGridItem = this.attachGridItem("resultBox.leftGrid.content");

			headGridItem.getItem().on(JQGrid.EVENT.SELECTCELL, function(item)
			{
				Logger.debug("SELECTCELL row id : "+item.id);
				Logger.debug(item);
			});

			//
			var detailGridItem = this.attachGridItem("resultBox.rightGrid");

		}
		,
		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
		getHeaderGridName: function()
		{
			return "resultBox.leftGrid.content";
		}
		,
		_initGridHeader: function()
		{
			var self = this;
			var container = this._box.result;
			if(! container )
			{
				Logger.error("_initGridHeader() - Invalid result item.");
				return;
			}
			container.on(SubContainer.EVENT, function(event)
			{
				var cmd = event.cmd;
				var params = event.params;

				switch(cmd)
				{
				case WorkAreaHeader.EVENT.CONFIRM:
					self.setApproState("Y");
					break;

				case WorkAreaHeader.EVENT.REJECT:
					self.setApproState("N");
					break;
				}
			});
		}
		,
		onButtonHandler: function(event)
		{
			switch( event.cmd )
			{
			default:
				return false;

			case "content:confirm": // 승인요청
				this.confirmApproState();
				break;
			
			case "content:reject": // 승인요청취소
				this.rejectApproState();
				break;
			}

			return true;
		}
		,
		confirmApproState: function()
		{
			var gridItem = this.attachGridItem("resultBox.leftGrid.content");

			var reqData = [];
			var ids = gridItem.getItem().getSelRowId(true);
			if( !ids || ids.length == 0 )
			{
				UCMS.alert("먼저 대상 거래처를 조회해 주세요.");
				return;
			}
			
			for(var i in ids)
			{
				var data = gridItem.getItem().getRowData(ids[i]);
				if(data.partAppro && data.partAppro.length > 0)
				{
					UCMS.alert("승인진행중인 건이 있습니다.</br>해당건을 선택해제하고 다시 진행해주세요.");
					return;
				}
			}

			var self = this;
			UCMS.confirm("승인요청을 진행할까요?")
			.then(function()
			{
				for(var i in ids)
				{
					var data = gridItem.getItem().getRowData(ids[i]);
					reqData.push(
					{
						custCode: this._custCode,
						proCode: data.proCode
					});
				}

				return self.getClient().create([data], "reqConfirm");
			})
			.done(function()
			{
				UCMS.alert("요청을 완료하였습니다.");
				self.onQuery();
			})
			;
		}
		,
		/**
		 * 승인상태를 반려한다.
		 * @param {string} approYn - "Y" 출고승인, "N" 반려
		 */
		rejectApproState: function()
		{
			var gridItem = this.attachGridItem("resultBox.leftGrid.content");

			var self = this;
			var grid = gridItem.getItem();
			var ids = grid.getSelRowId(true);
			if( ids.length == 0 )
			{
				ids = [grid.getSelRowId()];
			}

			if( ids[0] == null )
			{
				UCMS.alert("선택된 행이 없습니다.");
				return UCMS.retReject();
			}

			var deleteTasks = [];
			for(var i in ids)
			{
				var rowData = grid.getRowData( ids[i] );
				
				if(rowData.approYn == 'Y'
				)
				{
					UCMS.alert("승인된건은 취소불가능합니다.");
					return;
				}

				if(rowData.approRequest && rowData.approRequest.length > 0
				)
				{
					deleteTasks.push( rowData );
				}
				else
				{
					UCMS.alert("승인요청된 건만 취소가능합니다.");
					return;
				}
			}

			var client = this.getClient();

			var self = this;
			var params = this.getQueryData();
			var apiPath = client.getAPIPath("create", "cancelappro", params);

			UCMS.showPrompt("처리중 입니다.");

			client.transaction( apiPath, deleteTasks )
			.done(function(result)
			{
				UCMS.hidePrompt();

				if( result.resultCode != 0 )
				{
					UCMS.warn(result);
					UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");
				}
				else
				{
					UCMS.alert("승인요청 취소되었습니다.").then(function(){
						self.onQuery();
					});
				}

				return ( result.resultCode==0 );
			})
			.fail(function(e)
			{
				UCMS.error(e);
				UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");
			});
		}
		,
		onQuery: function()
 		{
 			var self = this;
			var queryData = this.getQueryData();
			if( new Date(queryData.strtDate) > new Date(queryData.lastDate) )
			{
				UCMS.alert("조회 기간이 잘못 지정되었습니다.");
				return;
			}
 			Renderer.__super__.onQuery.call(this)
 		}
		,
		fetchProductList: function(params)
		{
			if(params.strtDate && params.lastDate){}
			else return;
			
			var self = this;
			var queryParam = this.getQueryData();

			var options =
			{
				"params" : {
					"custCode" : params.custCode,
					"strtDate" : params.strtDate,
					"lastDate" : params.lastDate
				}
			};

			this.showLoading();
			this.fetchingGrid("detail", options, "resultBox.rightGrid")
			.always(function(e)
			{
				self.hideLoading();
			});
		}
		,
		onCreate: function()
		{
			UCMS.alert("지원하지 않는 기능입니다.");
		}
		,
		onDelete: function()
		{
			UCMS.alert("지원하지 않는 기능입니다.");
		}
	});

	return Renderer;
});
