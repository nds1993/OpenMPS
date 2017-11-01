/**
 * Project : OpenMPS MIS
 * 생산 > 주문접수 PM
 *
 */

define([
	"Logger",
	"NDSProps",
	"WorkAreaRenderer",
	"SubContainer",
	"WorkAreaHeader",
	"manifest!jqGrid4-1.0.0#widget",
	"FormBox",
	"RendererBase"

], function(Logger, NDSProps, WorkAreaRenderer, SubContainer, WorkAreaHeader, JQGrid, FormBox, RendererBase)
{
	var Renderer = WorkAreaRenderer.extend(
	{
		constructor: function(options)
		{
			/**
			var gridOptions = options.resultBox.options.content.options;
			// 페이징 처리 추가
			_.extend
			(
				gridOptions.gridParams,
				RendererBase.Method.makeAutoScrollingOptions2_local.call( this, 30 )
			);
			**/

			Renderer.__super__.constructor.call(this, options);
		}
		,
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "PP0304";
		}
		,
		_getHeaderGridName: function()
		{
			return "grid";
		}
		,
		_initForm: function()
		{
			var self = this;
			UCMS.retry(function()
			{
				if( !self._box.query || self.$el.find(".query_box").length == 0 )
				{
					return false;
				}

				self._box.query.on(FormBox.EVENT.CHANGE, function(item)
				{
					self._getResultGrid().clear();
				});
			});
		}
		,
		_initResultGrid: function()
		{
			var self = this;
			var container = this._box.result;
			container.on(SubContainer.EVENT, function(event)
			{
				var cmd = event.cmd;
				var selDatas = self._getResultGrid().getMultiData();
				if( selDatas.length == 0 )
				{
					UCMS.alert("선택된 자료가 존재하지 않습니다.");
					return;
				}

				_.map(selDatas, function(item)
				{
					item.dsType = "U";
				});

				switch(cmd)
				{
				case WorkAreaHeader.EVENT.REQUEST:
					self.onRequestAccept(selDatas);
					break;

				case WorkAreaHeader.EVENT.CANCEL:
					self.onRequestCancel(selDatas);
					break;
				}
			});

			return this._initGrid("grid")
			.then(function(grid)
			{
				grid.on
				(
					JQGrid.EVENT.GRIDCOMPLETE
					,
					function(params)
					{
						var sum = grid.getCol("ordrBox", false,"sum");
						grid.footerData("set", { proName: "합계", ordrBox: sum });
					}
				);
				grid.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
				{
					self.downloadExcel("PP0304_grid_1","주문접수_PM",false);
				});

			});
		}
		,
		_initSubHeader: function()
		{
			Logger.debug("Renderer.initSubHeader()");

			var self = this;
			return this._queryHeaderInfo().then(function(headerParams)
			{
				//
				headerParams.options.feature =
				[
					{"query":"조회"}
				];

				Renderer.__super__._initSubHeader.call( self, headerParams );
			});
		}
		,
		_getResultGrid: function()
		{
			return this._box.result.getContent();
		}
		,
		onQueryMasterGrid: function()
		{
			var self = this;
			var query = this._getQueryData();
			if( new Date(query.strtDate) > new Date(query.lastDate) )
			{
				UCMS.alert("조회 기간이 잘못 지정되었습니다.");
				return;
			}

			if(!query || !query.ordr_status )
			{
				UCMS.alert("검색조건을 지정해 주세요.");
				return;
			}
			this._queryGridData( this._getHeaderGridName(), query, function(res)
			{
				self._setResult(res);
				self._backupGrid();

				//
				self._setButtonState(["request"], query.ordr_status != "accept");
				self._setButtonState(["cancel"], query.ordr_status == "accept");
			});
		}
		,
		/**
		 * 주문 접수
		 */
		onRequestAccept: function(selDatas)
		{
			var self = this;
			UCMS.confirm("주문을 접수 하시겠습까?", { confirm: "확인", cancel: "취소" })
			.done(function()
			{
				Logger.debug("onRequestAccept()");
				self._request("accept", selDatas)
				.then(function(bSuccess)
				{
					if( bSuccess )
					{
						UCMS.alert(selDatas.length+"건의 자료가 주문 접수되었습니다.");
						//
						self.onQueryMasterGrid();
					}
				});
			});
		}
		,
		_request: function(action, datas)
		{
			var client = this._getClient();
			if( !client )
			{
				return UCMS.retReject();
			}

			var self = this;
			var params = this._getQueryData();
			params.ordr_status = action;
			var apiPath = client.getAPIPath("create", action, params);

			return client.transaction( apiPath, datas )
			.done(function(result)
			{
				if( result.resultCode != 0 )
				{
					UCMS.warn(result);
					UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");
				}
				else
				{
					// 적용된 항목을 목록에서 제거
					var grid = self._getResultGrid();
					var selIds = grid.getSelRowId( true );
					for(var i in selIds)
					{
						grid.removeRow(selIds[i]);
					}
					self.$el.find(".ui-jqgrid-htable input[type=checkbox]").prop("checked", false);
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
		/**
		 * 주문 취소
		 */
		onRequestCancel: function(selDatas)
		{
			var self = this;
			UCMS.confirm("주문을 취소 하시겠습까?", { confirm: "확인", cancel: "취소" })
			.done(function()
			{
				Logger.debug("onRequestCancel()");
				Logger.debug(selDatas);
				
				for(var i=0; i<selDatas.length; i++)
				{
					var item = selDatas[i]
					if(item.status == '1')
					{
						UCMS.alert("생산계획에 반영된 주문이 존재합니다. 취소할 수 없습니다.");
						return;
					}
					if(item.status == '3')
					{
						UCMS.alert("생산계획이 확정된 주문이 존재합니다. 취소할 수 없습니다.");
						return;
					}
				}
				
				self._requestCancel("naccept", selDatas);
			});
		}
		,
		_requestCancel: function(action, datas)
		{
			var client = this._getClient();
			if( !client )
			{
				return UCMS.retReject();
			}

			var self = this;
			var params = this._getQueryData();
			params.ordr_status = action;
			var apiPath = client.getAPIPath("create", action, params);

			return client.transaction( apiPath, datas )
			.done(function(result)
			{
				if( result.resultCode < 0 )
				{
					UCMS.warn(result);
					UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");
				}
				else if( result.resultCode > 0 )
				{
					UCMS.alert(result.extraData +"건의 자료가 주문 취소되고</br>" + result.resultCode + "건의 데이터가 계획에 반영되어 취소되지 않았습니다.<br> 항목을 확인해 주세요.")
					.then(function(){
						self.onQueryMasterGrid();
					});
				}
				else if( result.resultCode == 0 )
				{
					UCMS.alert(datas.length +"건의 자료가 주문 취소되었습니다.")
					.then(function(){
						self.onQueryMasterGrid();
					});
				}
			})
			.fail(function(e)
			{
				UCMS.error(e);
				UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");
			});
		}
		,
		_setButtonState: function(ids, bShow)
		{
			var container = this._box.result;
			container.getHeader().setButtonState(ids, true, bShow );
		}
	});

	return Renderer;
});
