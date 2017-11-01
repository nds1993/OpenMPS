/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 *
 * @author	dbongman
 */

define(
[
	"Logger",
	"BaroPanelBase",
	"jqgrid-5.2.1",
	"jqgrid-i18n"
],
function( Logger, BaroPanelBase )
{
	/**
	 * JQGrid 플러그인을 위젯으로 구현한다.
	 * @see https://github.com/free-jqgrid/jqGrid
	 * @see https://jsfiddle.net/OlegKi/yvbt6w54/
	 * @see http://www.trirand.com/jqgridwiki/doku.php?id=wiki:options
	 * TODO Group Column Headers 가능해야 함. 사용자 권한 관리에 추가해놓았음 [UserMngPanel]
	 *      참고 http://www.guriddo.net/demo/bootstrap/functionality/group_column_headers/index.html
	 * 			http://www.trirand.com/jqgridwiki/doku.php?id=wiki:groupingheadar
	 */
	var	JQGrid = BaroPanelBase.extend(
	{
		/**
		 * JQGrid 를 초기화한다.
		 * @param options - {
		 * 						gridId: {string},
		 * 						gridParams: {
		 * 							caption: {string},
		 * 							colNames: {array}
		 * 							colModel: {array}
		 * 							styleUI: {array}
		 * 							rowNum: {array}
		 * 							sortorder: {array}
		 * 						}
		 * 					}
		 */
		initialize: function(options)
		{
			JQGrid.__super__.initialize.apply( this, arguments );

			Logger.debug("JQGrid.initialize()");
			Logger.debug("Initialize JSON Value : ");
			Logger.debug(options);

			var listId = options.gridId+'_list';
			var pagerId = options.gridId+'_pager';
			this.template = function()
			{
				return '<table id="'+listId+'"></table><div id="'+pagerId+'"></div>';
			};
			this.ui =
			{
				'list': '#'+listId,
				'pager': '#'+pagerId
			};

			this._$grid = null;
			this.pagerId = "#"+pagerId;
			this._lastSelectRow = null;
			this._editRowId = null;
			this._editRowData = null;
			this._multiselect = ( options.gridParams.multiselect == true );
		}
		,
		onShow: function()
		{
			this._createGrid();
		}
		,
		/**
		 * 행이 선택된 경우의 후속 처리를 수행하는 공통 메소드
		 */
		_onSelectRow: function(rowId, status, evt)
		{
			if( this.isEditMode() == true )
			{
				// 편집중인 임시 셀 데이타를 확정한다.
				this.saveRow();
			}
			
			if( this._lastSelectRow && this._lastSelectRow != rowId )
			{
				// 기존 선택된 행을 보기 상태로 전환한다.
				this.restoreRow( this._lastSelectRow );
			}

			this._editRowData = this.getRowData(rowId);
    		this.trigger( JQGrid.EVENT.SELECT, { id: rowId, data: this._editRowData } );
        	this.editRow( rowId, true );
        	
        	this._lastSelectRow = rowId;
			Logger.debug(rowId+":"+status);
		}
		,
		_createGrid: function()
		{
			var self = this;
			var params = _.extend(
			{
				pager: this.pagerId,
				// This event fire when the user click on the row, but before select them.
				// This event should return boolean true or false. If the event return true the selection is done. If the event return false the row is not selected and any other action if defined does not occur.
				beforeSelectRow: function(rowId, e)
				{
					Logger.debug("beforeSelectRow : "+rowId+", Previous : "+self._lastSelectRow);
					if( self._lastSelectRow == rowId )
					{
						// 같은 행에서 컬럼을 이동한 경우
					}
					return true;
				},
		        onSelectRow: function(rowId, status, evt)
		        {
		        	if( self.isMultiSelectMode() )
		        	{
		        		return;
		        	}
		        	
		        	Logger.debug("onSelectRow : "+rowId);
		        	self._onSelectRow(rowId, status, evt);
		        },
		        ondblClickRow: function(rowId, iRow, iCol, evt)
		        {
		        	Logger.debug("ondblClickRow - "+rowId+" : "+iRow+", "+iCol);
		        	var rowData = self.getRowData(rowId);
		        	self.trigger( JQGrid.EVENT.DBLCLICK, { id: rowId, data: rowData } );
		        },
		        // Fires when we click on particular cell in the grid.
		        // row/cell 편집 모드에서도 모두 발생된다.
		        // 행이 처음 선택되는 시점에 선택된 셀 정보가 전달됨.
		        onCellSelect: function(rowId, iCol, cellcontent, evt)
		        {
		        	Logger.debug("onCellSelect - Current "+rowId+" : "+iCol+", Previous : "+self._lastSelectRow);
		        }
		        ,
		        
		        //
		        // Cell 편집 모드용 이벤트
		        // jqGrid 옵션의 celledit 가 true 인 경우 발생된다.
		        // url 없이 셀 편집 모드를 사용하기 위해서는
		        // "cellsubmit" 파라메터에 "clientArray" 을 지정한다.
		        //
		        
		        // applies only to a cell that is editable; this event fires after the edited cell is edited
		        // 셀 편집 모드 시작
		        afterEditCell: function(rowId, cellname, value, iRow, iCol)
		        {
		        	Logger.debug("afterEditCell - "+rowId+"  "+cellname+" : "+value);
		        	// 편집모드 시작
		        	self._editRowId = rowId;
		        }
		        ,
		        // Enables (disables) cell editing. 
		        // When this option is set to true, onSelectRow event can not be used, and hovering is disabled (when mouseover on the rows).
		        // 셀 편집 완료 : 내용 변경됨
		        afterSaveCell: function(rowId, cellname, value, iRow, iCol)
		        {
		        	Logger.debug("afterSaveCell - "+rowId+"  "+cellname+" : "+value);
		        	if( self.isEditMode() == true )
					{
						// 편집중인 임시 셀 데이타를 확정한다.
						self.saveRow(rowId, {row: iRow, col: iCol, name: cellname, value: value});
					}
		        	// 편집모드 종료
		        	self._editRowId = null;
		        }
		        ,
		        // applies only to cells that are not editable; fires after the cell is selected
		        // 편집 불가능한 셀 선택
		        onSelectCell: function(rowId, cellname, value, iRow, iCol)
		        {
		        	Logger.debug("onSelectCell - "+rowId+" : "+cellname);
		        }
		    }
			,
			this.getParam('gridParams'));
			
			//
			this._$grid = $(this.ui.list)
				.jqGrid( params );
			this._$grid.jqGrid("navGrid", self.pagerId, {});
			//.jqGrid("inlineNav", this.pagerId)
			//.jqGrid("filterToolbar");
		}
		,
		/**
		 * Grid 파라메터를 설정한다.
		 * 
		 * @param {object} params - 신규로 설정되는 grid 파라메터
		 */
		setGridParam: function(params)
		{
			if( this._$grid )
			{
				var rowId = this.getSelRowId();
				
				this._$grid.jqGrid('setGridParam', params)
				.trigger("reloadGrid");
				
				//
				if( rowId != null )
				{
					this._$grid.jqGrid('setSelection', rowId);
				}
			}
		}
		,
		/**
		 * Grid 데이타를 추가한다.
		 * 
		 * @param {object|array} jsonData 추가되는 데이타 
		 * @param {string} pos - "first", "last"
		 * @param {boolean} reset - 기존 데이타를 삭제하고 신규 데이타를 설정하는 경우 true 지정.
		 * 							추가하는 경우 생략하거나 false 를 지정한다.
		 * @returns {string} 새로 추가된 row id. 실패된 경우 null.
		 */
		addRow: function(jsonData, pos, reset)
		{
			if(! this._$grid )
			{
				return 0;
			}
			
			if( reset == true )
			{
				this.clear();
				pos = "first";
				
				//
				this._editRowId = null;
				this._editRowData = null;
			}
			
			var srcRowId;
			if( pos == "before" || pos == "after" )
			{
				srcRowId = this._$grid.jqGrid('getGridParam', 'selrow');
				if( srcRowId == null )
				{
					pos = "last";
				}
			}

			var newRowId = $.jgrid.randId();
			return this._$grid.jqGrid
			(
				'addRowData', 
				newRowId, 
				jsonData, 
				pos,
				srcRowId
			) ? newRowId : null;
		}
		,
		clear: function()
		{
			if( this._$grid )
			{
				this._$grid.jqGrid('clearGridData');
			}
		}
		,
		/**
		 * 지정한 row 의 데이타를 변경한다.
		 */
		setRowData: function(rowId, data)
		{
			if( this._$grid )
			{
				return this._$grid.jqGrid('setRowData', rowId, data);
			}
		}
		,
		/**
		 * 지정한 row 데이타를 얻는다.
		 * @param {string} rowId - value 가 지정되지 않은 경우 데이타를 얻을 row 의 식별자. 식별자를 지정하지 않으면 그리드 내의 모든 데이타가 반환된다.
		 * @param {string} value - 값이 지정된 경우 rowId 는 컬럼명으로 인식하고, 해당 컬럼이 지정한 값을 갖는 row 를 조회한다. 반환값은 배열로 반환된다.
		 * 							반환값의 형식은 조건에 해당하는 {id: rowId, data: rowData} 의 배열이 반환된다.
		 * @return {array||object}
		 */
		getRowData: function(rowId, value)
		{
			if( this._$grid )
			{
				if(! value )
				{
					// rowId 가 지정된 경우
					return this._$grid.jqGrid('getRowData', rowId);
				}
				else
				{
					// 조회 조건 컬럼이 지정된 경우
					var rids = this._$grid.jqGrid('getDataIDs');
					if( rids.length == 0 )
					{
						// 데이타가 존재하지 않는다.
						return [];
					}
					
					var rows = [], colName = rowId;
					for(var i in rids)
					{
						var rowData = this._$grid.jqGrid('getRowData', rids[i]);
						if( rowData[colName] == value )
						{
							// 조건에 해당하는 row
							rows.push({id: rids[i], data: rowData});
						}
					}
					
					return rows;
				}
			}
			
			return [];
		}
		,
		/**
		 * 지정한 row 를 제거한다.
		 * @param {string} rowId - 삭제될 row 식별자. 지정하지 않은 경우 현재 선택된 row 가 지정된다.
		 * @param {boolean} moveNext - true 인 경우 삭제 후 다음 row 로 이동한다.
		 */
		removeRow: function(rowId, moveNext)
		{
			if( this._$grid && rowId )
			{
				if( moveNext == true )
				{
					var selId = this.getSelRowId(), afterId = null;
					if( selId == rowId )
					{
						afterId = this.getRowIdBySelRow(1);
					}
					this._$grid.jqGrid('delRowData', rowId);
					if( afterId )
					{
						// 현재 행이 삭제된 경우 삭제된 행의 아래에 있는 행 선택
						this.setSelectRow( afterId );
					}
				}
				else
				{
					this._$grid.jqGrid('delRowData', rowId);
				}
			}
		}
		,
		/**
		 * row 편집 모드를 시작한다.
		 * 
		 * @param {string} rowId - 편집을 진행할 row 식별자
		 * @param {boolean} useKeys - true 이면 Enter, ESC 키를 사용하는 모드
		 * @return {$.Promise} - 편집된 row 정보가 { id:##, data:## } 내용으로 반환된다.
		 * @see http://www.trirand.com/jqgridwiki/doku.php?id=wiki:inline_editing
		 */
		editRow: function(rowId, useKeys)
		{
			if( this._$grid && rowId )
			{
				var self = this;
				var d = $.Deferred();

				this._$grid.jqGrid('editRow', rowId, 
				{
					// when set to true we can use [Enter] key to save the row and [Esc] to cancel editing.
					keys: useKeys,
					
					oneditfunc: function(rowId)
					{
						Logger.debug("editRow() - oneditfunc : "+rowId);
						// 편집 중인 행 식별자 저장
						self._editRowId = rowId;
					}
					,
					//
					// 하단의 이벤트는 keys true 모드에서 Enter 키로 입력 확정한 경우에 발생한다.
					//
					
					// if defined, this function is called immediately after the request is successful. 
					// This function is passed the data returned from the server. 
					// Depending on the data from server; this function should return true or false.
					successfunc: function(xhr)
					{
						Logger.debug("editRow() - successfunc : "+rowId);
						self.restoreRow(rowId);
						return true;
					}
					,
					// if defined, this function is called after the data is saved to the server. 
					// Parameters passed to this function are the rowid and the response from the server request. 
					// Also the event is called too when the url is set to 'clientArray'.
					aftersavefunc: function()
					{
						Logger.debug("editRow() - aftersavefunc : "+rowId);

						var rowData = { "id": rowId, data: self.getRowData(rowId) };
						
						// 편집 완료된 값 반환
						// TODO 본 처리는 saveRow 에서 수행하는 것으로 변경.
						// keys 플레그가 false 인 경우에는 aftersavefunc 이벤트가 발생되지 않기 때문이다.
						//self.trigger( JQGrid.EVENT.EDITROW, rowData );
						d.resolve( rowData );
					}
					,
					errorfunc: function(e)
					{
						Logger.debug("editRow() - errorfunc : "+rowId);
						d.reject(e);
					}
				});

				return d.promise();
			}
			else
			{
				return UCMS.retReject();
			}
		}
		,
		/**
		 * 편집 중인 행이 있는지 확인한다.
		 * @return {boolean}
		 */
		isEditMode: function()
		{
			return typeof this._editRowId == 'string';
		}
		,
		/**
		 * 편집 중 행과 제공하는 데이타가 다른지 확인한다.
		 */
		isChangedRowData: function(newRowData)
		{
			if( this._editRowData == null || this.isEditMode() == false || !newRowData )
			{
				return false;
			}
			for(var field in this._editRowData)
			{
				if( this._editRowData[field] != newRowData[field] )
				{
					return true;
				}
			}
			
			return false;
		}
		,
		/**
		 * 편집 중인 row 를 보기 모드로 전환한다.
		 */
		restoreRow: function(rowId)
		{
			if( this._$grid )
			{
				rowId || ( rowId = this._editRowId );
				this._$grid.jqGrid('restoreRow', rowId);
				Logger.debug("restoreRow() - rowId : "+rowId);
				
				if( rowId == this._editRowId )
				{
					// 편집중인 row 가 restore 된 경우
					this._editRowId = null;
					this._editRowData = null;
				}
			}
		}
		,
		/**
		 * 현재 선택된 row Id 를 얻는다.
		 * 
		 * @param {boolean} bMultiple - true 가 지정된 경우 다중 선택된 항목의 목록을 얻는다.
		 * @return {string|array} 선택되지 않았거나 jqgrid 객체가 존재하지 않는 경우 null 이 반환된다.
		 * 						다중 선택된 항목의 식별자를 얻는 경우 배열이 반환된다.
		 */
		getSelRowId: function(bMultiple)
		{
			var rowIds = this._$grid 
				? this._$grid.jqGrid('getGridParam', bMultiple==true?'selarrrow':'selrow') 
				: null;
			
			return rowIds==null
					? null
					: bMultiple==true
						? _.toArray(rowIds)
						: rowIds;
		}
		,
		/**
		 * 지정된 row 를 선택된 행으로 설정한다.
		 * @param {string} rowId
		 * @param {boolean} silent - true 를 지정한 경우, onSelectRow 이벤트가 발생되지 않는다.
		 * 							후속 동작은 수동으로 처리한다.
		 */
		setSelectRow: function(rowId, silent)
		{
			if( this._$grid )
			{
				this._$grid.jqGrid('setSelection', rowId, !silent);
				if( silent )
				{
					// silent 모드가 아닌 경우에는 onSelectRow 이벤트에 의해 복구 처리가 진행된다.
					if( this._lastSelectRow && this._lastSelectRow != rowId )
					{
						// 기존 선택된 행을 보기 상태로 전환한다.
						this.restoreRow( this._lastSelectRow );
					}
				}
			}
		}
		,
		/**
		 * 현재 행의 인근에 배치한 row 식별자를 얻는다.
		 * @param {number} dist - 얻고자 하는 행이 현재 행을 기준으로 얼마나 떨어져 있는지를 나타낸다.
		 * 							0 인 경우 현재 선택된 행을 나타낸다.
		 * 							음수(-)는 현재 행을 기준으로 위(before)으로 몇 번째의 행인지를 나타낸다.
		 * 							양수(+)는 현재 행을 기준으로 아래(after)로 몇 번째의 행인지를 나타낸다.
		 * @return {string} 지정한 행의 id. 존재하지 않거나 찾을 수 없는 경우 null.
		 */
		getRowIdBySelRow: function(dist)
		{
			if(! this._$grid )
			{
				return null;
			}
			var selId = this._$grid.jqGrid('getGridParam', 'selrow');
			if(! selId )
			{
				// 선택된 행이 없다.
				return null;
			}
			var rids = this._$grid.jqGrid('getDataIDs');
			if( rids.length == 0 )
			{
				// 행이 존재하지 않는다.
				return null;
			}
			
			var nSelRow = _.indexOf( rids, selId );
			if( dist == 0 )
			{
				// 현재 행의 row
				return rids[ nSelRow ];
			}
			else
			{
				var nRow = nSelRow + dist;
				if( nRow < 0 || nRow >= rids.length )
				{
					// 지정된 row 가 존재하지 않는다
					return null;
				}
				return rids[nRow];
			}
		}
		,
		/**
		 * 지정한 row 또는 편집중인 row 를 저장한다.
		 * saveRow 호출 후에 restoreRow 를 수행해야 변경된 내용이 셀에 적용된다.
		 * saveRow 을 수행하지 않은 상태에서는 restoreRow 가 호출되면 편집된 셀의 내용은 무시된다.
		 * @param {string} rowId - 저장할 row 식별자.
		 * 							지정된 행의 편집 UI 는 뷰 모드로 자동 전환된다.
		 * @param {object} cell - 편집된 셀 정보.
		 * 							셀 기반 편집 모드인 경우 지정됨.
		 * 							cell 값이 지정되면 EDITCELL 이벤트가 발생되고,
		 * 							본 파라메터가 존재하지 않으면 EDITROW 이벤트가 발생된다.
		 */
		saveRow: function(rowId, cell)
		{
			if( this._$grid )
			{
				rowId || ( rowId = this._editRowId );
				Logger.debug("saveRow() - rowId : "+rowId+", editRow : "+this._editRowId);
				this._$grid.jqGrid('saveRow', rowId);
				var newData = this._$grid.jqGrid('getRowData', rowId);
				Logger.debug(newData);
				
				if( this.isChangedRowData( newData ) == true )
				{
					// 편집 이벤트 발생
					if( cell )
					{
						this.trigger( JQGrid.EVENT.EDITCELL, { id: rowId, cell: cell });
					}
					else
					{
						this.trigger( JQGrid.EVENT.EDITROW, { id: rowId, data: newData });
					}
				}
			}
		}
		,
		isMultiSelectMode: function()
		{
			return this._multiselect;
		}
	}
	,
	{
		PATH: {
			CSS: "/plugin/jqgrid/css/ui.jqgrid.min.css",
			UI: "https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/redmond/jquery-ui.min.css"
		}
		,
		EVENT: {
			SELECT: "jqgrid:select",		// row 선택시 발생, {id:"row id", data:{object}}
			DBLCLICK: "jqgrid:doubleclick",	// row 더블 클릭시 발생, {id:"row id", data:{object}}
			EDITROW: "jqgrid:edit:row",		// row 편집 완료시 발생, {id:"row id", data:{object}}
			EDITCELL: "jqgrid:edit:cell"	// 쎌 편집 완료시 발생, { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
		}
	});

	UCMS.SPA.AppMain.initStyle(JQGrid.PATH.CSS);
	UCMS.SPA.AppMain.initStyle(JQGrid.PATH.UI);

	return JQGrid;
});
