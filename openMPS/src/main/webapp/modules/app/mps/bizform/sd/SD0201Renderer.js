/**
 * Project : OpenMPS MIS
 *
 * SD 영업 > 단가관리 > 표준단가 목록
 * SD0201
 *
 */

define([
	"Logger",
	"NDSProps",
	"WorkAreaRenderer",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, WorkAreaRenderer, JQGrid4)
{
	var Renderer = WorkAreaRenderer.extend(
	{
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "SD0201";
		}
		,
		_initResultGrid: function()
		{
			var self = this;
			var gridId = this._getHeaderGridName();
			
			return this._initGrid
			(
				gridId
			)
			.then(function(grid)
			{
				grid.on
				(
					JQGrid4.EVENT.EDITCELL
					,
					function(result)
					{
						Logger.debug("EDITCELL : "+result.id);
						var rowData = grid.getRowData( result.id );
						Logger.debug(rowData);

						self.onSearchCode(
						{
							// 제품 검색
							codeType: "PP0103",
							params: {},
							keyword: rowData.proCode,
							callback: function(selected)
							{
								if(! selected)
								{
									Logger.debug("Canceled to edit a cell. rowId : "+result.id);
									return;
								}

								// rowData 에 검색 결과 적용
								rowData.proCode = selected.code;
								rowData.proName = selected.label;
								rowData.saleGroup2Name = selected.saleGroup2Name;

								self._beginTransaction(gridId);
								self._updateSelectRowData( gridId, result.id, rowData );
							} })
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
					{"create":"신규"},
					{"save":"저장"},
					{"delete":"삭제"},
					{"cancel":"취소"}
				];

				Renderer.__super__._initSubHeader.call( self, headerParams );
			});
		}
		,
		onCreate: function()
		{
			Logger.info("WorkAreaRenderer.onCreate()");
			this._createEmptyRow(null, true);
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
							UCMS.alert("삭제되었습니다.");
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
			
			var self = this;
			var errHandler = function(res){
				
				if(res.resultCode == -4)
				{
					UCMS.alert("이미 등록된 제품입니다.").then(function(){
						self.onCancel();
					});
				}
				else
				{
					UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");
				}
			}
			
			this._saveGridTask(null,null,errHandler)
			.then(function(res)
			{
				if(res.resultCode == 0)
				{
					self.onQuery();
				}
			})
			.fail(function(err)
			{
				UCMS.reportError(err);
			})
			.always(function()
			{
				self.hideLoading();
			});;
		}
	});

	return Renderer;
});
