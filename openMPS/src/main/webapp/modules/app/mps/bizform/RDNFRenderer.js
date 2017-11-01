/**
 * Project : OpenMPS MIS
 * 
 * 조회폼 1 개,
 * 주종의 관계를 갖는 그리드 2 개로 구성된 업무 화면을 구현한다.
 * 
 * 조회폼 리소스의 이름은 "headerBox" 로 식별되며, this._box.query 에 인스턴스가 담겨있다.
 * 조회 결과를 담는 그리드 2개는 "resultBox" 에 담겨 있으며, "leftGrid", "rightGrid" 로 각각의 인스턴스에 접근할 수 있다.
 * _getResultGrid() 에서 그리드에 접근하는 절차가 구현된다.
 * 
 */

define([
	"Logger",
	"NDSProps",
	"APIClient",
	"WorkAreaRenderer",
	"WorkAreaHeader",
	"SubContainer",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, APIClient, WorkAreaRenderer, ContentHeader, SubContainer, JQGrid)
{
	var MainMethod =
	{
		initialize: function(options) 
		{
			Renderer.__super__.initialize.call(this, options);
		}
		,
		_getHeaderGridName: function()
		{
			return "leftGrid";
		}
		,
		_initResultGrid: function()
		{
			var p = this._initLeftGrid();
			this._initRightGrid();
			this._initRightSubHeader();
			
			return p;
		}
		,
		_getResultGrid: function(gridId)
		{
			var container = null;
			
			switch( gridId )
			{
			default:
				gridId = "leftGrid";
			//
			case "leftGrid":
			case "rightGrid":
				container = this._box.result.getItemElement( gridId );
				break;
			}

			return container ? container.getContent ? container.getContent() : container : null;
		}
		,
		onSelectItem: function(params, gridId) 
		{
			this._selRowId[gridId] = params.id;
			Logger.info("Select Row Id : "+this._selRowId[gridId]);
			this.onReadItemList( params.data );
		}
		,
		/**
		 * 계류중인 타스트 개수를 구한다.
		 * @param {string} gridId - 대상 그리드 식별자. 지정하지 않는 경우 모든 그리드의 타스크 개수를 구한다.
		 * @return {number}
		 */
		_getTaskCount: function(gridId)
		{
			if( gridId )
			{
				return Renderer.__super__._getTaskCount.call( this, gridId );
			}
			else
			{
				var leftPool = this._getTaskPool("leftGrid");
				var rightPool = this._getTaskPool("rightGrid");
				return (leftPool.getTotCount() + rightPool.getTotCount());
			}
		}
	};
	
	// 헤더 버튼 파트
	var HeaderMethod =
	{
		onCancel: function()
		{
			Logger.info("Renderer.onCancel()");
			
			//
			this._getTaskPool("leftGrid").reset();
			this._endTransaction(true, "leftGrid");
			
			//
			this._getTaskPool("rightGrid").reset();
			this._endTransaction(true, "rightGrid");
		}
		,
		onSave: function()
		{
			Logger.info("Renderer.onSave()");

			var self = this;
			var nDone = 0;
			//
			this.showLoading();
			this._saveLeftGrid()
			.always(function()
			{
				++nDone;
				if( nDone == 2 )
				{
					self.hideLoading();	
				}
			});
			this._saveRightGrid()
			.always(function()
			{
				++nDone;
				if( nDone == 2 )
				{
					self.hideLoading();	
				}
			});
			//
			this._box.header.setButtonMode(ContentHeader.MODE.READY);
		}
	};
	
	// 좌측 그리드 파트 처리
	var LeftGridMethod = 
	{
		_initLeftGrid: function()
		{
			var self = this;
			var gridId = "leftGrid";
			return this._initGrid
			(
				gridId
			)
			.then(function(grid)
			{
				grid.on
				(
					JQGrid.EVENT.SELECT
					,
					function(params)
					{
						self.onSelectItem( params, gridId );
					}
				);
				grid.on
				(
					JQGrid.EVENT.EDITROW
					,
					function(row)
					{
						row.data.useYn = ( row.data.useYn == "Yes" || row.data.useYn == "true" ) ? "Y" : "N";

						self._beginTransaction(gridId);
						self._getTaskPool(gridId).add(new WorkAreaRenderer.UpdateTask(row.id, row.data));
					}
				);
				grid.on
				(
					JQGrid.EVENT.EDITCELL
					,
					function(result)
					{
						Logger.debug("LeftGrid EDITCELL : "+result.id);
						self.triggerMethod(Renderer.EVENT.LEFTGRID_EDITCELL, result);
					}
				);
				
				return grid;
			});
		}
		,
		onQueryMasterGrid: function() 
		{
			var query = this._getQueryData();
			if (!query) {
				return;
			}
			
			var self = this;
			this._queryGridData( "leftGrid", query, function(res)
			{
				self._setResult(res);
				self._backupGrid( "leftGrid" );
			});

			// 기존 상세정보 제거
			var grid = this._getResultGrid("rightGrid");
			if( grid )
			{
				grid.clear();
			}
		}
		,
		onCreate: function() 
		{
			Logger.info("Renderer.onCreate()");
			this._createEmptyRow( "leftGrid", true );
		}
		,
		onDelete: function() 
		{
			this._deleteGridData( "leftGrid" );
		}
		,
		_saveLeftGrid: function()
		{
			Logger.info("Renderer._saveLeftGrid()");
			
			return this._saveGridTask("leftGrid");
		}
		,
		onLeftgridEditCell: function(result)
		{
			Renderer.__super__.onGridEditCell.call( this, result, "leftGrid" );
		}
	};
	
	// 우측 그리드 파트
	var RightGridMethod =
	{
		_initRightGrid: function()
		{
			var self = this;
			var gridId = "rightGrid";
			return this._initGrid
			(
				gridId
			)
			.then(function(grid)
			{
				grid.on
				(
					JQGrid.EVENT.EDITROW
					,
					function(row)
					{
						self._beginTransaction(gridId);
						self._getTaskPool(gridId).add(new WorkAreaRenderer.UpdateTask(row.id, row.data));
					}
				);
				grid.on
				(
					JQGrid.EVENT.EDITCELL
					,
					function(result)
					{
						Logger.debug("RightGrid EDITCELL : "+result.id);
						self.triggerMethod(Renderer.EVENT.RIGHTGRID_EDITCELL, result);
					}
				);
				
				return grid;
			});
		}
		,
		/**
		 * 그룹 코드의 상세 목록을 조회한다.
		 * @param {object} params - 선택된 그룹 정보
		 * 					{
		 * 					}
		 */
		onReadItemList: function(params, gridId)
		{
			var self = this;
			if( this._box.header.getButtonMode() != ContentHeader.MODE.READY )
			{
				if( this._getTaskCount( gridId ) > 0 )
				{
					UCMS.confirm("변경사항이 무시됩니다.<br>진행할까요?")
					.done(function()
					{
						self.onCancel();
						self.triggerMethod(Renderer.EVENT.RIGHTGRID_QUERY, params);
					});
					return;
				}
				else
				{
					this._box.header.setButtonMode(ContentHeader.MODE.READY);
				}
			}
			
			this.triggerMethod(Renderer.EVENT.RIGHTGRID_QUERY, params);
		}
		,
		/**
		 * 상세 그리드 데이타를 조회한다.
		 */
		onQueryDetailGrid: function(params)
		{
			var self = this;
			this._queryGridData( "rightGrid", params, function(resData)
			{
				Logger.debug(resData);
				
				var grid = self._getResultGrid( "rightGrid" );
				if( grid )
				{
					if( resData.extraData.result.length > 0  )
					{
						// 데이타가 자라난다.
						grid.addRow( resData.extraData.result, "last", true );
						self._backupGrid( "rightGrid" );
					}
					else
					{
						grid.clear();
						self._backupGrid( "rightGrid" );
					}
				}
			});
		}
		,
		onCreateItem: function(params)
		{
			if(! this._selRowId[this._getHeaderGridName()] )
			{
				UCMS.alert("마스터 아이템을 선택해 주세요.");
				return;
			}
			Logger.debug("onCreateItem : "+params);
			this._createEmptyRow( "rightGrid" );
		}
		,
		onDeleteItem: function(params)
		{
			if(! this._selRowId[this._getHeaderGridName()] )
			{
				UCMS.alert("마스터 아이템을 선택해 주세요.");
				return;
			}
			Logger.debug("onDeleteItem : "+params);
			this._deleteGridData( "rightGrid" );
		}
		,
		onEditDetail: function(bEnd, bRestore)
		{
			if( bEnd == true )
			{
				this._endTransaction(bRestore, "rightGrid");
			}
			else
			{
				this._beginTransaction("rightGrid");
			}
		}
		,
		_saveRightGrid: function(params)
		{
			Logger.info("Renderer._saveRightGrid()");
			
			return this._saveGridTask("rightGrid", params);
		}
		,
		_initRightSubHeader: function()
		{
			var self = this;
			UCMS.retry(function()
			{
				var container = self._box.result.getItemElement("rightGrid");
				if(! container )
				{
					return false;
				}
				if(typeof container.getContent !== 'function' )
				{
					// SubContainer 가 아닌경우 통과
					return;
				}
				container.on(SubContainer.EVENT, function(event)
				{
					var cmd = event.cmd;
					var params = event.params;
					
					switch(cmd)
					{
					case ContentHeader.EVENT.CREATE:
						self.onCreateItem(params);
						break;
					case ContentHeader.EVENT.DELETE:
						self.onDeleteItem(params);
						break;
					}
				});
			}
			, 1000);
		}
		,
		onRightgridEditCell: function(result)
		{
			Renderer.__super__.onGridEditCell.call( this, result, "rightGrid" );
		}
	};
	
	var Renderer = WorkAreaRenderer.extend
	(
		_.extend({}, MainMethod, HeaderMethod, LeftGridMethod, RightGridMethod)
		,
		{
			EVENT:
			{
				LEFTGRID_EDITCELL: "leftgrid:edit:cell",
				RIGHTGRID_EDITCELL: "rightgrid:edit:cell",
				RIGHTGRID_QUERY: "query:detail:grid"
			}
		}
	);
	 
	return Renderer;
});
