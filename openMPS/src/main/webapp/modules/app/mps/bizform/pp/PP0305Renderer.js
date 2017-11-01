/**
 * Project : OpenMPS MIS
 * 생산 > PM 생산계획 입력
 * 
 */

define([
	"Logger",
	"NDSProps",
	"WorkAreaHeader",
	"WorkAreaRenderer",
	"SubContainer",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, WorkAreaHeader, WorkAreaRenderer, SubContainer, JQGrid)
{
	var Renderer = WorkAreaRenderer.extend(
	{
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "PP0305";
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

				self._box.query.ui.status.find("input[type=radio]").change(
					function(event){
						self._getResultGrid().clear();
					}
				);
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
					{"query":"조회"},
					{"create":"추가"},
					{"delete":"삭제"},
					{"save":"저장"},
				//	{"excel":"엑셀"},
					{"cancel":"취소"}
				];
	
				Renderer.__super__._initSubHeader.call( self, headerParams );
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
				var params = event.params;
				
				switch(cmd)
				{
				case WorkAreaHeader.EVENT.CONFIRM:
					self.onConfirm(params);
					break;
				}
			});
			UCMS.retry(function()
			{
				if( container.getHeader().getWidget$Element().find("button.confirm").length == 0 )
				{
					return false;
				}
				container.getHeader().setButtonState(["confirm"], false );
			});
			
			//
			return WorkAreaRenderer.prototype._initResultGrid.call( this )
			.then(function(grid)
			{
				grid.on
				(
					JQGrid.EVENT.GRIDCOMPLETE
					,
					function(params)
					{
						var ordrBoxSum = grid.getCol("ordrBox", false,"sum");
						var workQtySum = grid.getCol("workQty", false,"sum");
						grid.footerData("set", { proName: "합계", ordrBox: ordrBoxSum, workQty: workQtySum });
					}
				);
				grid.on
				(JQGrid.EVENT.CLICKBUTTON
					,
					function(btn)
					{
					self.downloadExcel("PP0305_grid","생산계획입력_PM",false);
					}
				);

			});
		}
		,
		onQueryMasterGrid: function()
		{
			var self = this;
			var query = this._getQueryData();
			if(! query.status )
			{
				UCMS.alert("검색조건을 지정해 주세요.");
				return;
			}
			this._queryGridData( this._getHeaderGridName(), query, function(res)
			{
				self._box.header.setButtonState(["save"], query.status != "complete" );
				self._box.header.setButtonState(["delete"], query.status != "accept" );
				self._box.result.getHeader().setButtonState(["confirm"], false );
				
				//
				if( res.extraData.result.length == 0 )
				{
					self._getResultGrid().clear();
					return;
				}
				else
				{
					self._box.result.getHeader()
					.setButtonState(["confirm"], query.status == "plan" );
				}
				res.extraData.result = _.map(res.extraData.result, function(item)
				{
					item.planQty = item.workQty;
					return item;
				});
				
				//
				self._setResult(res);
				self._backupGrid();
			});
		}
		,
		/**
		 * 계획 확정
		 */
		onConfirm: function()
		{
			var self = this;
			var grid = this._getResultGrid();
			var list = grid.getMultiData();
			if( list.length == 0 )
			{
				UCMS.alert("선택된 항목이 없습니다.");
				return;
			}
			else
			{
				UCMS.confirm("계획을 확정하시겠습니까?", { confirm: "확인", cancel: "취소" })
				.done(function()
				{
					var client = self._getClient();
					if( !client )
					{
						return UCMS.retReject();
					}

					// 확정 API 직접 호출
					var apiPath = client.getAPIPath("create", "confirm", self._getQueryData());
					client.transaction(apiPath, list)
					.done(function()
					{
						var rowIds = grid.getSelRowId( true );
						UCMS.alert(rowIds.length + "건 계획이 확정처리 되었습니다.")
						.then(function(){
							
			 				for(var i in rowIds)
			 				{
			 					grid.removeRow(rowIds[i], false);
			 				}
			 				self.$el.find(".resultBox_region .grid_box thead th input[type=checkbox]").prop("checked", false);
							
						});
						
					})
					.fail(function(e)
					{
						Logger.error(e);
						UCMS.alert("실패하였습니다.");
					});
	
				});
			}

		}
		,
		/**
		 * 신규 항목 입력
		 */
		onCreate: function()
		{
			var self = this;
			var queryData = this._getQueryData();
			if( queryData.status == "complete" )
			{
				UCMS.alert("제품추가 기능은 접수분과 계획진행분일 때 가능합니다.");
				return;
			}
			this.popupBox("PP0305_pop", 
			{
				callback: function(result)
				{
					if( result )
					{
						if( result.proCode )
						{
							Logger.info(result);
							var planItem = {
								custName: result.custCode.keyword,
								ordrCust: result.custCode.result,
								proLargeName: result.proCode.raw.largeName,
								largeCode: result.proCode.raw.largeCode,
								proCode: result.proCode.result,
								proName: result.proCode.keyword,
								planQty: null,
								workQty: result.workQty,
								delvDate: result.delvDate,
								proUnit: result.proCode.raw.proUkind,
								workSeq: 0,
								memo: result.planMemo
							};
							var rowId = self._createEmptyRow("grid");
							
							self._updateSelectRowData( "grid", rowId, planItem );
							
						}
						else
						{
							UCMS.alert("필수 정보가 입력되지 않았습니다.");
						}
					}
				}
			});
		}
		,
		onDelete: function() 
		{
			this._deleteGridData( null, true );
		}
		,
		_deleteGridData: function(gridId, bMultiple)
		{
			
			var query = this._getQueryData();
			
			gridId = gridId || this._getHeaderGridName();
			if( this.isCreateMode(gridId) )
			{
				UCMS.alert("신규 항목의 등록 완료후 다시 시도해 주세요.");
				return;
			}
			var grid = this._getResultGrid(gridId);
			if( grid )
			{
				var ids = grid.getSelRowId(bMultiple);
				if( bMultiple == true && ids.length == 0 )
				{
					ids = [null];
				}
				if( ids[0] == null )
				{
					UCMS.alert("선택된 행이 없습니다.");
					return;
				}

				var self = this;
				var msg = ids.length+"건의 데이타를 삭제하시겠습니까?";
				var confirmMsg = "삭제";
				
				if(query.status == "complete")
				{
					msg = ids.length+"건의 데이타를 계획확정 취소하시겠습니까?";
					confirmMsg = "취소";
				}
				
				UCMS.confirm( msg, {confirm:confirmMsg, cancel:"취소"})
				.then(function()
				{
					var deleteTasks = [];
					for(var i in ids)
					{
						var rowData = grid.getRowData( ids[i] );
						rowData.dsType = "D";
						deleteTasks.push( rowData );
						grid.removeRow( ids[i], true );
					}

					var client = self._getClient();
					var apiPath = client.getAPIPath("create", null, self._getQueryData());

					self.showLoading();
					return client.transaction( apiPath, deleteTasks )
					.then(function(res)
					{
						if( res.resultCode == 0 )
						{
							if(query.status == "complete")
							{
								UCMS.alert(ids.length+"건의 데이타가 계획확정 취소되었습니다.");
							}
							else
							{
								UCMS.alert("삭제되었습니다.");
							}
						}
						else
						{
							UCMS.reportError(res);
						}
					})
					.fail(function(err)
					{
						UCMS.reportError(err);
					})
					.always(function()
					{
						self.hideLoading();
						if( bMultiple == true )
						{
							grid.getWidget$Element().find("thead th input[type=checkbox]").prop("checked", false);
						}
					});
				});
			}
			else
			{
				UCMS.alert("대상 그리드가 존재하지 않습니다.");
			}
		}
		,
		onSave: function()
		{
			Logger.info("WorkAreaRenderer.onSave()");
			
			// 선택된 행은 저장 대상
			var resultGrid = this._getResultGrid(this._getHeaderGridName());
			var ids = resultGrid.getSelRowId(true);
			for(var i in ids)
			{
				this._updateSelectRowData(
						this._getHeaderGridName(), 
						ids[i], 
						resultGrid.getRowData( ids[i] ));
			}
/**
			var list = resultGrid.getMultiData();
			if( list.length == 0 )
			{
				UCMS.alert("저장할 작업이 없습니다.");
				return;
			}*/
			//
			var self = this;
			this._saveGridTask()
			.then(function(res)
			{
				self.onQuery();
			});
		}
	});

	return Renderer;
});
