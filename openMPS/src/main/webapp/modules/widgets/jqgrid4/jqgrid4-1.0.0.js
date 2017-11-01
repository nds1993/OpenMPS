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
	"PlugInManager"
],
function( Logger, BaroPanelBase, PlugInManager )
{
	/**
	 * JQGrid 4.7.0 플러그인을 위젯으로 구현한다.
	 * 
	 * @see http://www.trirand.com/jqgridwiki/doku.php?id=wiki:changetwo#jqgrid_4.7.0_changes_and_fixes
	 * @see http://www.trirand.com/jqgridwiki/doku.php?id=wiki:methods
	 * @see http://www.trirand.com/jqgridwiki/doku.php?id=wiki:options
	 * @see Working Examples http://www.trirand.com/blog/jqgrid/jqgrid.html
	 * @see http://www.trirand.com/jqgridwiki/doku.php?id=wiki:groupingheadar
	 * @see http://www.trirand.com/jqgridwiki/doku.php?id=wiki:grouping
	 * @see https://stackoverflow.com/questions/12200621/jqgrid-grouping-row-level-data
	 */
	var	JQGrid4 = BaroPanelBase.extend(
	{
		constructor: function(options)
		{
			//
			// 기본값 지정
			//
			
			// When enabled, selecting a row with setSelection scrolls the grid so that the selected row is visible. This is especially useful when we have a verticall scrolling grid and we use form editing with navigation buttons (next or previous row). On navigating to a hidden row, the grid scrolls so that the selected row becomes visible.	
			options.gridParams.scrollrows = true;
			
			JQGrid4.__super__.constructor.call(this, options);
		}
		,
		/**
		 * JQGrid4 를 초기화한다.
		 * @param options - {
		 * 						gridId: {string},
		 * 						gridParams: {
		 * 							caption: {string},
		 * 							colNames: {array}
		 * 							colModel: {array}
		 * 							styleUI: {array}
		 * 							rowNum: {array}
		 * 							sortorder: {array}
		 * 						},
		 * 						setGroupHeaders: {object},
		 * 
		 * 						// 본 설정이 추가되면, rowspan 처리를 위해서 gridComplete 도 함께 추가된다.
		 * 						// colNames 에 추가된 컬럼 기준으로 소팅된 데이타가 그리드 데이타로 설정되어야 정상적인 rowspan 이 동작된다.
		 * 						cellattr:  
		 * 						{
		 * 							colNames: {array}, cellattr 을 적용하는 컬럼명의 배열. colModel 에서 name 을 지정한다.
		 * 							method: {string} 호출되는 함수의 이름을 기입한다. 별도의 로직 구현이 필요없는 경우 지정하지 않으면, 내부 함수가 사용된다.
		 * 										지정되는 함수는 호출시 (rowId, val, rawObject, cm, rdata) 의 파라메터가 제공된다.
		 * 										rowId - the id of the row 
		 * 										val - the value which will be added in the cell 
		 * 										rawObject - the raw object of the data row - i.e if datatype is json - array, if datatype is xml xml node. 
		 * 										cm - all the properties of this column listed in the colModel 
		 * 										rdata - the data row which will be inserted in the row. This parameter is array of type name:value, where name is the name in colModel
		 * 						},
		 * 
		 * 						navGrid: {object}, navGrid 에 전달되는 옵션을 지정한다. buttons 를 사용하기 위해서는 필수 지정항목이다.
		 * 											옵션에 대한 자세한 내용은 http://www.trirand.com/jqgridwiki/doku.php?id=wiki:navigator 를 참고한다.
		 * 
		 * 						buttons: {array} navButtonAdd 에 전달되는 버튼 옵션의 배열.
		 * 										클릭 핸들러는 스트링으로 지정되며, new Function() 에 의해 구현체로 사용된다.
		 * 										함수의 구현을 제공하는 경우 파라메터명 options 에 클릭된 버튼의 옵션이 제공된다.
		 * 										버튼 옵션 중 onClickButton 를 제공하지 않는 경우 JQGrid4 위젯 내의 클릭 핸들러가 사용되며,
		 * 										이 때 그리드 이벤트 CLICKBUTTON 이 발생되고, 파라메터로 클릭된 버튼의 옵션이 제공된다.
		 * 										옵션에 대한 자세한 내용은 http://www.trirand.com/jqgridwiki/doku.php?id=wiki:custom_buttons 을 참고한다. 
		 * 					}
		 */
		initialize: function(options)
		{
			JQGrid4.__super__.initialize.apply( this, arguments );

			Logger.debug("JQGrid4.initialize()");
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
			this._lastSelectRow = null;
			this._editRowId = null;
			this._editRowData = null;
			this._multiselect = ( options.gridParams.multiselect == true );
			this._cellEdit = ( options.gridParams.cellEdit == true );
			this._frozenMode = GridInnerFunc.checkFrozenMode( options.gridParams );
			
			//
			this._setupCellAttr(options);
		}
		,
		onBeforeShow: function()
		{
			var self = this;
			PlugInManager.loadLib("jqgrid4-4.7.0")
			.then(function()
			{
				return PlugInManager.loadLib("TableCSVExport");
			})
			.then(function()
			{
				self._createGrid();
			});
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
    		this.trigger( JQGrid4.EVENT.SELECT, { id: rowId, data: this._editRowData } );
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
				pager: this.ui.pager.selector,
				
				// This fires after all the data is loaded into the grid and all other processes are complete. Also the event fires independent from the datatype parameter and after sorting paging and etc.
				// TODO _setupCellAttr() 에 의해 설정되는 gridComplete 이벤트와 충돌이 발생함. 둘 다 사용할 수 없는 상태. 대책이 필요.
				gridComplete: function()
				{
					self.trigger( JQGrid4.EVENT.GRIDCOMPLETE );
				},
				// This event is executed immediately after every server request. 
				// data Data from the response depending on datatype grid parameter
				loadComplete: function(data)
				{
					self.trigger( JQGrid4.EVENT.LOADCOMPLETE, data );
				},
				// This event fire when the user click on the row, but before select them.
				// This event should return boolean true or false. If the event return true the selection is done. If the event return false the row is not selected and any other action if defined does not occur.
				beforeSelectRow: function(rowId, e)
				{
					Logger.debug("beforeSelectRow : "+rowId+", Previous : "+self._lastSelectRow);
					if( self._lastSelectRow == rowId )
					{
						// 같은 행에서 컬럼을 이동한 경우
					}
					else
					{
						if( self.isCellMode() == true )
						{
							// 멀티 셀렉트 모드에서 셀 편집 옵션이 활성화된 상태에서는 체크 박스가 선택되지 않는 문제가 있다.
							// TODO 본 이벤트 발생시 행 선택 명령을 명시적으로 수행하면, 해당 행이 선택된다.
							// XXX 셀 편집 의도와 행 선택 의도를 구분할 수 없기 때문에 의도치 않게 행 선택 체크박스가 선택되는 문제를 가지고 있다.
							
							// TODO 클릭 핸들러에서 선택하는 동작을 수행한다. 행을 선택할 때 멀티선택이 동작되는 문제를 제거한다.
							//self.setSelectRow( rowId );
							//self.$el.find("#"+rowId+" input[type=checkbox]:first").prop("checked", true);
						}
						
						// 이벤트 전파
						self._editRowData = self.getRowData(rowId);
						self.trigger( JQGrid4.EVENT.BEFORESELECT, { id: rowId, data: self._editRowData } );
					}
					return true;
				},
			//////
				// 행 편집 모드용
		        onSelectRow: function(rowId, status, evt)
		        {
		        	Logger.debug("onSelectRow : "+rowId);
		        	if( self.isCellMode() == true || self.isMultiSelectMode() == true )
		        	{
		        		// 셀 편집시 행 선택 이벤트가 발생되는 문제가 있다.
		        		// TODO 본 상황은 헤드/디테일 구조에서 헤드 그리드의 정보를 수정하는 동작에서,
		        		// 디테일 그리드에 대한 조회 시도를 하게 되는 원인이 된다.
		        		self._editRowData = self.getRowData(rowId);
		        		self.trigger( JQGrid4.EVENT.SELECT, { id: rowId, data: self._editRowData } );
		        	}
		        	else
		        	{
		        		self._onSelectRow(rowId, status, evt);
		        	}
		        },
		        ondblClickRow: function(rowId, iRow, iCol, evt)
		        {
		        	Logger.debug("ondblClickRow - "+rowId+" : "+iRow+", "+iCol);
		        	var rowData = self.getRowData(rowId);
		        	self.trigger( JQGrid4.EVENT.DBLCLICK, { id: rowId, data: rowData } );
		        },
		        
		    ////// Fires when we click on particular cell in the grid.
		        // row/cell 편집 모드에서도 모두 발생된다.
		        // 행이 처음 선택되는 시점에 선택된 셀 정보가 전달됨.
		        onCellSelect: function(rowId, iCol, cellcontent, evt)
		        {
		        	Logger.debug("onCellSelect - Current "+rowId+" : "+iCol+", Previous : "+self._lastSelectRow);
		        }
		        ,
		        
		    //////
		        // Cell 편집 모드용 이벤트
		        // jqGrid 옵션의 celledit 가 true 인 경우 발생된다.
		        // url 없이 셀 편집 모드를 사용하기 위해서는
		        // "cellsubmit" 파라메터에 "clientArray" 을 지정한다.
		        // cellEdit : Enables (disables) cell editing. When this option is set to true, onSelectRow event can not be used, and hovering is disabled (when mouseover on the rows).
		        //
		        
		        // applies only to a cell that is editable; this event fires after the edited cell is edited
		        // 셀 편집 모드 시작
		        afterEditCell: function(rowId, cellname, value, iRow, iCol)
		        {
		        	Logger.debug("afterEditCell - "+rowId+"  "+cellname+" : "+value);
		        	// 편집모드 시작
		        	self._editRowId = rowId;
		        	self.trigger( JQGrid4.EVENT.BEFOREEDITCELL, 
		        	{
		        		id: rowId, cell: {row: iRow, col: iCol, name: cellname, value: value}
		        	});
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
		        	self.trigger( JQGrid4.EVENT.SELECTCELL, 
		        	{
		        		id: rowId, cell: {row: iRow, col: iCol, name: cellname, value: value}
		        	});
		        }
		    }
			,
			this.getParam('gridParams'));
			
			UCMS.retry(function()
			{
				self._$grid = jQuery(self.ui.list.selector);
				if( self._$grid.length == 0 )
				{
					Logger.warn("_createGrid() - "+self.getParam('gridId'));
					return false;
				}
				self._$grid.jqGrid( params );
				
				//self._$grid.jqGrid("navGrid", self.ui.pager.selector, {});
				//.jqGrid("inlineNav", this.ui.pager.selector)
				//.jqGrid("filterToolbar");
				
				self.triggerMethod("create:grid");
			}
			, 1000)
			.done(function()
			{
				Logger.info("_createGrid() - done : "+self.getParam('gridId'));
			})
			.fail(function(err)
			{
				Logger.warn("_createGrid() - "+self.getParam('gridId')+", error : "+err);
			});
		}
		,
		/**
		 * 그리드의 최종 생성 절차를 수행한다.
		 */
		onCreateGrid: function()
		{
			var self = this;
			
			var headerOptions = this.getParam('setGroupHeaders');
			if( headerOptions )
			{
				this._$grid.jqGrid("setGroupHeaders", headerOptions);
			}
			if( this.isFrozenMode() )
			{
				this._$grid.jqGrid("setFrozenColumns");
				Logger.info("onCreateGrid() - Turn on frozen mode!!");
			}
			var navOptions = this.getParam('navGrid');
			if( navOptions )
			{
				navOptions = _.extend(
				{
					edit: false, 
					add: false, 
					del: false, 
					search: false, 
					refresh: false, 
					view: false, 
					position: "right", 
					cloneToTop: false 
				}
				, navOptions );
				this._$grid.jqGrid("navGrid", this.ui.pager.selector, navOptions);
			}
			var buttons = this.getParam('buttons') || [];
			for( var i in buttons )
			{
				if( buttons[i].onClickButton )
				{
					(function(btnParams)
					{
						var callbackFunc = btnParams.onClickButton;
						
						// 클릭 핸들러에 버튼 설정 정보가 전달된다.
						btnParams.onClickButton = function()
						{
							var cbClick = new Function( "options", callbackFunc );
							cbClick( btnParams );
						};
					})( buttons[i] );
				}
				else
				{
					(function(btnParams)
					{
						buttons[i].onClickButton = function()
						{
							self.trigger( JQGrid4.EVENT.CLICKBUTTON, btnParams );
						}
					})( buttons[i] );
				}
				this.addButton( buttons[i] );
			}
		}
		,
		/**
		 * Grid 파라메터를 설정한다.
		 * 
		 * @param {object} params - 신규로 설정되는 grid 파라메터
		 * @param {boolean} overwrite
		 */
		setGridParam: function(params, overwrite)
		{
			if( this._$grid )
			{
				var rowId = this.getSelRowId();
				
				this._$grid.jqGrid('setGridParam', params, overwrite)
				.trigger("reloadGrid");
				
				if( params.data )
				{
					// 신규 데이타가 추가된 경우
					this.enableMultiSelectChecker();
				}
				
				//
				if( rowId != null )
				{
					this.setSelectRow( rowId );
				}
			}
		}
		,
		getGridParam: function(name)
		{
			return this._$grid ? this._$grid.jqGrid('getGridParam', name) : null;
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
				return null;
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

			// TODO rowId 지정 방식 변경. 
			// 멀티 선택 모드와 셀 편집 모드가 병행되는 경우 멀티 선택이 되지 않는 문제 해결을 위한 조치임.
			// rowId 를 숫자로만 사용하면, 편집 불가능한 컬럼 선택시 행이 선택된다.
			// FIXME 그런데 행 사이에 신규 행을 추가하면, rowid 의 순서가 석이면서 행 선택 동작이 어긋나게 되는 문제가 발생된다.
			// 우선 본 문제를 방지하기 위해서는 신규 행은 무조건 끝에 추가되도록 처리해야 한다.
			// TODO 호출측 코드는 그대로 두고, 본 신규 행 처리시 사용하는 아규먼트인 pos 의 설정을 'last'로 강제하여 처리한다.
			/*
			pos = "last";
			if(!this._newRowId)
			{
				this._newRowId = 0;
			}
			var newRowId = ++this._newRowId;
			return this._$grid.jqGrid
			(
				'addRowData', 
				newRowId, 
				jsonData, 
				pos,
				srcRowId
			) ? newRowId : null;
			*/

			// TODO 위 정책을 철회한다. row 가 삭제되는 경우에도 위의 행 식별자와 행 위치가 어긋나는 문제로 인해 그리드가 오동작한다.
			// 이전 정책을 적용한다.
			var newRowId = $.jgrid.randId();
			if( this._$grid.jqGrid
				(
					'addRowData', 
					newRowId, 
					jsonData, 
					pos,
					srcRowId
				) )
			{
				this.enableMultiSelectChecker(jsonData.length>0?null:newRowId);
			}
			else
			{
				newRowId = null;
			}
			
			return newRowId;
		}
		,
		clear: function()
		{
			if( this._$grid )
			{
				this._$grid.jqGrid('clearGridData');
				
				if( this.isMultiSelectMode() )
				{
					// 전체 선택 체크박스 클리어
					this.$el.find(".cbox:first").prop("checked", false);
				}
			}
			this._newRowId = 0;
			this._rowspan = {};
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
		 * 멀티 셀렉트 모드에서 선택된 데이타를 얻는다.
		 * @return {array} 선택된 데이타가 없는 경우 길이가 0 인 배열이 반환된다.
		 */
		getMultiData: function()
		{
			var rows = [];
			var rowIds = this.getSelRowId( true );
			if( rowIds.length == 0 )
			{
				rowIds = this.getSelRowId();
				rowIds = rowIds == null ? [] : [rowIds];
			}
			for(var i in rowIds)
			{
				var rowData = this.getRowData( rowIds[i] );
				rows.push( rowData );
			}
			
			return rows;
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
						this.setSelectRow( afterId, false );
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
						//self.trigger( JQGrid4.EVENT.EDITROW, rowData );
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
		 * 편집 중인 행의 식별자를 얻는다.
		 * @return {string}
		 */
		getEditingRowId: function()
		{
			return this._editRowId;
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
		 * 							이 경우 행 선택 이후의 동작은 수동으로 처리해야 한다.
		 */
		setSelectRow: function(rowId, silent)
		{
			if( this._$grid )
			{
				// TODO onSelectRow() 에서 이미 아래 조건에 대한 처리를 하고 있으므로, 이벤트 발생 억제를 강제할 필요는 없다.
				// 셀 편집모드, 멀티 선택 모드에서는 SELECT 이벤트와 연동이 필요한 상황이 필요하므로,
				// 본 처리는 무효화한다.
				//silent = (this.isCellMode() == true || this.isMultiSelectMode() == true) ? true : silent;
				
				//
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

				// 편집 이벤트 발생
				if( cell )
				{
					// 셀 편집됨
					this.trigger( JQGrid4.EVENT.EDITCELL, { id: rowId, cell: cell });
				}
				else if( this.isChangedRowData( newData ) == true )
				{
					// 행 편집됨
					this.trigger( JQGrid4.EVENT.EDITROW, { id: rowId, data: newData });
				}
			}
		}
		,
		/**
		 * 멀티 선택 가능 모드인가?
		 * @return {boolean}
		 */
		isMultiSelectMode: function()
		{
			return this._multiselect;
		}
		,
		/**
		 * 셀 편집 모드인가?
		 * @return {boolean}
		 */
		isCellMode: function()
		{
			return this._cellEdit;
		}
		,
		/**
		 * 컬럼의 cellattr 옵션을 설정한다.
		 */
		_setupCellAttr: function(options)
		{
			if(! options.cellattr )
			{
				return;
			}
			
			var self = this;
			var cellMethod = null;
			
			// cellattr 관련 메소드에서 rowspan 정보를 공유하는 변수
			this._rowspan = {};
			
			if(options.cellattr.method)
			{
				cellMethod = new Function( options.cellattr.method )
			}
			else
			{
				cellMethod = function(rowId, val, rawObject, cm, rdata)
				{
					return GridInnerFunc.cellattr.call( this, rowId, val, rawObject, cm, rdata, self._rowspan );
				};
			}

			var colNames = options.cellattr.colNames;
			var colModel = options.gridParams.colModel;
			for(var i in colModel )
			{
				if( _.indexOf(colNames, colModel[i].name) >= 0 )
				{
					var column = colModel[i];
					column.cellattr = cellMethod;
				}
			}
			
			/**
			 * rowspan 에 기록된 정보를 기반으로 병합 처리를 진행한다.
			 */
			options.gridParams.gridComplete = function()
			{
				GridInnerFunc.gridComplete.call( this, self._rowspan );
				
				// 이벤트 릴레이
				self.trigger( JQGrid4.EVENT.GRIDCOMPLETE );
			};
			
			// 아래 설정을 진행하지 않아도 위 동작은 cellattr 으로 설정된다.
			//this.setParam("gridParams", options.gridParams)
		}
		,
		setCell: function(rowId, colname, value, cellclass, properties, forceup)
		{
			if(! this._$grid )
			{
				return;
			}
			this._$grid.jqGrid('setCell', rowId, colname, value, cellclass, properties, forceup);
		}
		,
		getCell: function(rowId, iCol)
		{
			if(! this._$grid )
			{
				return;
			}
			return this._$grid.jqGrid('getCell', rowId, iCol);
		}
		,
		/**
		 * 두 개의 옵션(footerrow, userDataOnFooter)이 true 인 경우 지정한 데이타를 푸터에 출력한다.
		 * 
		 * @param {string} action - can be 'get' or 'set'. The default is get. 'get' returns an object of type name:value, where the name is a name from colModel. This will return data from the footer. The other two options have no effect in this case. 
		 * @param {obect} data - 'set' takes a data array (object) and places the values in the footer. The object should be in name:value pair, where the name is the name from colMode
		 * @param {string} format - default is true. This instruct the method to use the formatter (if set in colModel) when new values are set. A value of false will disable the using of formatter
		 */
		footerData: function(action, data, format)
		{
			if(! this._$grid )
			{
				return;
			}
			return this._$grid.jqGrid('footerData', action, data, format);
		}
		,
		/**
		 * This method returns an array with the values from the column.
		 * @param {string} colname - colname can be either a number that represents the index of the column or a name from colModel.
		 * @param {string} returntype - returntype determines the type of the returned array. When set to false (default) the array contain only the values. 
		 * 								When set to true the array contain a set of objects. The object is defined as {id:rowid, value:cellvalue} where the rowid is the id of the row and cellvalue is the value of the cell. For example, such output can be [{id:1,value:1},{id:2,value:2}…] 
		 * @param {string} mathoperation - The valid options for mathoperation are - 'sum, 'avg', 'count'. If this parameter is set and is valid, the returned value is a scalar representing the operation applied to the all values in the column. If the parameter is not valid the returned value is empty array.
		 * @return {array|value}
		 */
		getCol: function(colname, returntype, mathoperation)
		{
			if(! this._$grid )
			{
				return;
			}
			return this._$grid.jqGrid('getCol', colname, returntype, mathoperation);
		}
		,
		reload: function()
		{
			if( this._$grid )
			{
				this._$grid.trigger("reloadGrid");
			}
		}
		,
		isFrozenMode: function()
		{
			return this._frozenMode == true;
		}
		,
		/**
		 * 그리드에 추가된 데이타의 row id 목록을 얻는다.
		 * @return {array} 
		 */
		getRowIds: function()
		{
			if(! this._$grid)
			{
				return [];
			}
			return this._$grid.jqGrid('getDataIDs');
		}
		,
		/**
		 * 사용자 정의 버튼을 추가한다.
		 * @param {object} btnOptions
		 * 			파라메터 
					{
						caption:"엑셀",
						title: "엑셀다운로드",
						buttonicon:"ui-icon-disk",
						onClickButton: function()
						{
							UCMS.confirm("엑셀파일을 다운로드하시겠습니까?")
						},
						position: "last",
						cursor: "pointer"
					}
		 */
		addButton: function(btnOptions)
		{
			if(! this._$grid )
			{
				return;
			}
			this._$grid.jqGrid('navButtonAdd', this.ui.pager.selector, btnOptions);
		}
		,
		/**
		 * 멜티 선택을 위한 체크 박스에 check 이벤트 핸들러를 적용하고,
		 * 이벤트 발생시 행 선택이 되도록 처리한다.
		 * 셀 편집 & 멀티 셀렉트 모드에서 동작됨
		 * @param {string} rowId - 적용 대상이 되는 행 식별자 
		 */
		enableMultiSelectChecker: function(rowId)
		{
			if( this.isCellMode() == true && this.isMultiSelectMode() == true )
			{
				var self = this;
				// TODO 각 행의 멀티 셀렉트 체크박스를 선택하는 핸들러 등록
				// 체크박스가 클릭 됐을 때만 행을 선택한다.
				var $checkInput;
				if( rowId )
				{
					// 지정행만
					$checkInput = this.$el.find("tr#"+rowId).find("td:first input");
				}
				else
				{
					// 전체 행 대상
					$checkInput = this.$el.find("tr").find("td:first input");
				}
				Logger.info("CheckBox Count : "+$checkInput.length);
				$checkInput.click(function()
				{
					self.setSelectRow( $(this).parents("tr").attr("id") );
				});
			}
		}
	}
	,
	{
		PATH: {
			CSS: "/plugin/jqgrid4/css/ui.jqgrid.css",
			UI: "https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/redmond/jquery-ui.min.css"
		}
		,
		EVENT: {
			GRIDCOMPLETE: "jqgrid:gridcomplete",		// 데이타가 그리드로 설정이 완료된 직후 발생된다.
			LOADCOMPLETE: "jqgrid:loadcomplete",		// 모든 데이타 로딩 절차가 완료된 직후 발생된다.
			BEFORESELECT: "jqgrid:beforeselect",		// row 선택 확정 전 발생
			SELECT: "jqgrid:select",					// row 선택시 발생, {id:"row id", data:{object}}
														// 셀편집/멀티선택 모드에서도 setSelectRow() 을 선택시에도 발생됨
			DBLCLICK: "jqgrid:doubleclick",				// row 더블 클릭시 발생, {id:"row id", data:{object}}
			EDITROW: "jqgrid:edit:row",					// row 편집 완료시 발생, {id:"row id", data:{object}}
			EDITCELL: "jqgrid:edit:cell",				// 쎌 편집 완료시 발생, { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
			BEFOREEDITCELL: "jqgrid:beforeedit:cell",	// 셀 편집 시작시 발생, { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
			
			// 편집 안되는 cell 선택시 발생, { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
			SELECTCELL: "jqgrid:select:cell",
			CLICKBUTTON: "jqgrid:button"				// 사용자 정의 버튼이 클릭 됐을때 발생, 클릭된 버튼의 파라메터가 argument 로 전달된다.
		}
	});
	
	var GridInnerFunc = 
	{
		/**
		 * 컬럼의 속성을 설정한다.
		 * this 는 grid의 table dom 객체이다.
		 * 
		 * @param {string} rowId - row 식별자
		 * @param {string} val - 컬럼 데이타
		 * @param {object} rawObject - rowId 의 전체 컬럼 데이타
		 * @param {object} cm - 현재 컬럼의 colModel
		 * @param {object} rdata - name:value 형식의 컬럼 데이타
		 */
		cellattr : function(rowId, val, rawObject, cm, rdata, rowspan)
		{
			// 특수문자, 공백 제거
			var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\" ]/gi;
			val = val.replace(regExp,'');
			
			//
			var cellattr;
			var rowList = rowspan[cm.name] ? rowspan[cm.name][val] : null;
			if( rowList )
			{
				// 이전 row 의 컬럼과 값이 동일한 경우.
				// 이전 row 와 rowspan 대상
				cellattr = ' style="display: none"';
				rowspan[cm.name][val].cnt++;
			}
			else
			{
				// 신규 row 정보 생성
				if(! rowspan[cm.name]) rowspan[cm.name] = {};
				// 신규 row 정보 보관
				rowspan[cm.name][val] = {rowId: rowId, cnt: 1};
				cellattr = ' rowspan="1" rowspanid="'+rowId+'_'+cm.name+'_'+val+'"';
			}
			
			return cellattr;
		}
		,
		/**
		 * 그리드 생성 이벤트 6 번째 단계의 이벤트로써 생성이 완료된 직후 호출된다.
		 * 본 이벤트 다음으로 loadComplete 가 발생된다.
		 */
		gridComplete : function(rowspan)
		{
			var $grid = $(this);
			for(var colname in rowspan)
			{
				var values = rowspan[colname];
				for(var val in values)
				{
					var span = values[val];
					$grid.find("[rowspanid="+span.rowId+"_"+colname+"_"+val+"]").attr("rowspan", span.cnt);
				}
			}
		}
		,
		/**
		 * Frozen 모드인지 확인한다.
		 * @return {boolean} frozen 모드인 경우 true 를 반환한다.
		 */
		checkFrozenMode: function(jqgridOptions)
		{
			if(! jqgridOptions.colModel)
			{
				return false;
			}
			for(var i in jqgridOptions.colModel)
			{
				if( jqgridOptions.colModel[i].frozen == true )
				{
					return true;
				}
			}
			
			return false;
		}
	};

	UCMS.SPA.AppMain.initStyle(JQGrid4.PATH.CSS);
	UCMS.SPA.AppMain.initStyle(JQGrid4.PATH.UI);
	
	/**
	 * openMPS에서 필요한 공통 기능을 구현한다.
	 * openMPS 전용
	 */
	var OpenMPSJQGrid = JQGrid4.extend(
	{
		constructor: function(options)
		{
			if( UCMS.appendExcelBtn )
			{
				// 엑셀버튼 일괄 추가
				// openMPS에서 사용하는 모든 그리드에 엑셀 다운로드 버튼을 추가한다.
				// 핸들링이 필요한 경우 해당 페이지의 랜더링 코드에서 버튼 핸들러를 구현한다.
				UCMS.appendExcelBtn( options );
			}
			OpenMPSJQGrid.__super__.constructor.call(this, options);
		}
		,
		onCreateGrid: function()
		{
			var self = this;

			//
			OpenMPSJQGrid.__super__.onCreateGrid.call( this );
		}
	});

	return OpenMPSJQGrid;
});
