/**
 * Project : openMPS MIS
 *
 * SD 영업 > 주문관리 > 출고승인요청
 * SD0402
 *
 */

define([
	"Logger",
	"NDSProps",
	"WorkAreaRenderer",
	"SubContainer",
	"manifest!jqGrid4-1.0.0#widget",
	"WorkAreaHeader",
	"moment"
], function(Logger, NDSProps, WorkAreaRenderer, SubContainer,JQGrid, WorkAreaHeader, moment)
{
	var Renderer = WorkAreaRenderer.extend(
	{
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "SD0402";
		}
		,
		/**
		 * 1. 헤더 기능 버튼 초기화
		 */
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
 					//{"delete":"삭제"},
 					//{"save":"저장"},
 					//{"cancel":"취소"},
 					{"manual":"도움말"}
 				];

 				Renderer.__super__._initSubHeader.call( self, headerParams );
 			});
 		}
		,

		_initResultGrid: function()
		{
			var self = this;
			return Renderer.__super__._initResultGrid.call(this)
			.done(function(grid)
			{
				self._initGridHeader();
				grid.on
				(JQGrid.EVENT.CLICKBUTTON
					,
					function(btn)
					{
					self.downloadExcel("SD0402_grid_1","출고승인요청",false);
					}
				);

			});
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
				case WorkAreaHeader.EVENT.REQUEST:
					self.onReqConfirm();
					break;
				case "content:reqcancel":
					self.onReqCancel();
					break;
				}
			});
		}
		,
		/**
		 * 할인단가 승인 요청
		 */
		onReqConfirm: function()
		{
			var self=this;
			var gridId = this._getHeaderGridName();
			var grid = this._beginTransaction( gridId );
			if( grid )
			{
				var rowIds = grid.getSelRowId( true );
				if( rowIds.length > 0 )
				{
					for(var i in rowIds)
					{
						var rowId = rowIds[i];
						var rowData = grid.getRowData( rowId );
						this._getTaskPool(gridId).add( new WorkAreaRenderer.UpdateTask( rowId, UCMS.copyJSON(rowData) ) );

						grid.setRowData( rowId, rowData );
						
					}
					
					self._saveGridTask("승인 요청하였습니다.",function(res)
						{
							self.onQuery();
						});
				}
				else
				{
					if( this._getTaskCount(gridId) == 0 )
					{
						this._endTransaction( gridId,  );
					}
					UCMS.alert("승인 요청할 항목을 선택해 주세요.");
				}
			}
			else
			{
				UCMS.alert("대상 그리드가 존재하지 않습니다.");
			}
		}
		,
		_saveGridTask: function(msg, succHandler, errHandler)
		{
			var client = this._getClient();
			var gridId = this._getHeaderGridName() ;
			if( !client )
			{
				return UCMS.retReject();
			}
			var self = this;
			var save = function()
			{
				var apiPath = client.getAPIPath("create", gridId, self._getQueryData( true ));
				var taskList = self._getTaskPool(gridId).getList();

				return client.transaction( apiPath, taskList )
				.then(function(result)
				{
					if( result.resultCode == 0 )
					{
						self._getTaskPool(gridId).reset();
						self._endTransaction(false, gridId);
						self._backupGrid(gridId);
						UCMS.alert(msg).then(succHandler);
					}
					else
					{
						if(errHandler)
						{
							errHandler(result);
						}
						else
						{
							UCMS.error(result);
							UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");
						}
					}
					
					return result;
				})
				.fail(function(e)
				{
					UCMS.error(e);
					UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");

					// 오류의 원인이 제거되면, 다시 시도할 수 있도록 트랜젝션을 유지한다.
				})
				.always(function()
				{
					self._newRowId = null;
				});
			};

			save();
		}
		,
		/**
		 * 할인단가 승인요청 취소
		 */
		onReqCancel: function()
		{
			var self=this;
			var gridId = this._getHeaderGridName();
			var grid = this._beginTransaction( gridId );
			if( grid )
			{
				var rowIds = grid.getSelRowId( true );
				if( rowIds.length > 0 )
				{
					for(var i in rowIds)
					{
						var rowId = rowIds[i];
						var rowData = grid.getRowData( rowId );
						this._getTaskPool(gridId).add( new WorkAreaRenderer.DeleteTask( rowId, UCMS.copyJSON(rowData) ) );

						grid.setRowData( rowId, rowData );
						
					}
					
					self._saveGridTask("승인 요청을 취소하였습니다.",function(res)
							{
						self.onQuery();
					});
				}
				else
				{
					if( this._getTaskCount(gridId) == 0 )
					{
						this._endTransaction( gridId );
					}
					UCMS.alert("승인 요청할 항목을 선택해 주세요.");
				}
			}
			else
			{
				UCMS.alert("대상 그리드가 존재하지 않습니다.");
			}
		}
		,
		onQuery: function()
 		{
 			var self = this;
			var queryData = this._getQueryData();
			if( new Date(queryData.strtDate) > new Date(queryData.lastDate) )
			{
				UCMS.alert("조회 기간이 잘못 지정되었습니다.");
				return;
			}
 			Renderer.__super__.onQuery.call(this)
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
