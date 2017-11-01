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
	 * RealGrid-1.1.25 플러그인을 위젯으로 구현한다.
	 * 
	 */
	var	RealGrid = BaroPanelBase.extend(
	{
		constructor: function(options)
		{
			//
			// 그리드 기본값 지정
			//
			RealGrid.__super__.constructor.call(this, options);
		}
		,
		/**
		 * RealGrid 를 초기화한다.
		 * @param options - {
		 * 						gridId: {string},
		 * 						width: {string}, // 그리드 폭을 지정. 기본값은 100%
		 * 						height: {string}, // 그리드 높이를 지정. 기본값은 500px
		 * 						footer: {boolean|object}, // 기본값 true
		 * 						// object 로 지정하는 경우 상황에 따른 상세 메시지 지정 가능.
		 *						// html 로 지정하며, 건수가 설정되는 부분은 아래 예시와 같은 태그를 사용한다.
		 *						// 객체 모드로 푸터가 설정되면, downloadMsg 을 지정하지 않으면, 다운로드 버튼은 비활성화된다.
		 * 						{
		 * 							"emptyMsg": {string}, // 조회된 데이타가 없습니다.
		 * 							"totalMsg": {string}, // <span class='grid_tot'>0</span>건이 조회되었습니다.
		 * 							"downloadMsg": {string} // 다운로드 버튼 레이블을 지정한다.
		 * 						}
		 * 						gridParams: 
		 * 						{
		 * @see http://help.realgrid.com/api/types/DisplayOptions/
		 * 							displayOptions: {
		 * 								fitStyle: {string} none|even|evenFill|fill
		 * 							},
		 * 
		 * @see http://help.realgrid.com/api/types/EditOptions/
		 * 							editOptions: {
		 * 								insertable: {boolean},
		 * 								appendable: {boolean},
		 * 								updatable: {boolean},
		 * 								editable: {boolean},
		 * 								validateOnEdited: {boolean},
		 * 								useTabKey: {boolean},
		 * 								enterToTab: {boolean},
		 * 								enterToNextRow: {boolean},
		 * 								enterToEdit: {boolean}
		 * 							}, 		
		 * 
		 * @see http://help.realgrid.com/api/types/Panel/
		 * 							panel: {
		 * 								visible: {boolean}
		 * 							},
		 * 
		 * @see http://help.realgrid.com/api/types/Indicator/
		 * 							indicator: {
		 * 								visible: {boolean}
		 * 							},
		 * 
		 * @see http://help.realgrid.com/api/types/StateBar/
		 * 							stateBar: {
		 * 								visible: {boolean}
		 * 							},
		 * 
		 * @see http://help.realgrid.com/api/types/Header/
		 * 							header: {
		 * 								minHeight: {number}
		 * 							},
		 * 
		 * @see http://help.realgrid.com/api/types/Footer/
		 * 							footer: {
		 * 								visible: {boolean}
		 * 							},
		 * 
		 * 컬럼 속성 설정과 관련된 전반적인 설명 참조
		 * @see http://help.realgrid.com/api/types/DataColumn/
		 * 컬럼 병함 관련 설명 참조
		 * @see http://demo.realgrid.com/CellComponent/CellMerging/
		 * 컬럼 소계 관련 설명 참조
		 * @see http://help.realgrid.com/api/types/SummaryMode/
		 * 스타일 관련 설명 참조
		 * @see http://demo.realgrid.com/GridStyle/StyleProperties/
		 * 컬럼 입력 데이타 검증 관련 설명 참조
		 * @see http://help.realgrid.com/api/features/Edit%20Validation/
		 * @see http://help.realgrid.com/api/types/EditValidation/
		 * Validation.criteria 문법
		 * @see http://help.realgrid.com/api/features/Expression/
		 * 							columns: [],
		 * 							fields: [],
		 * 							data: [],
		 * 
		 * @see http://help.realgrid.com/api/GridBase/setSelectOptions/
		 * 							selectOptions: {
		 * 								// 기본값은 singleRow
		 * 								style: "block"|"rows"|"columns"|"singleRow"|"singleColumn"|"none"
		 * 							},
		 * 
		 * 필요한 옵션만 지정. 세부 옵션에 대한 설명은 아래 링크 참조
		 * @see http://help.realgrid.com/tutorial/b8-1/
		 * 							checkBar: {
		 * 								width: "",
		 * 								showAll: {boolean},
		 * 								showGroup: {boolean},
		 * 								visibleOnly: {boolean},
		 * 								checkableOnly: {boolean},
		 * 								visible: {boolean}, 기본값 true
		 * 								exclusive: {boolean},
		 * 								checkableExpression: {boolean} 
		 * 							},
		 * 
		 * 필요한 옵션만 지정. 세부 옵션에 대한 설명은 아래 링크 참조
		 * @see http://help.realgrid.com/api/types/FixedOptions/
		 * 							fixedOptions: {
		 * 								colCount: {number},
		 * 								rightColCount: {number},
		 * 								rowCount: {number}
		 * 							},
		 * 
		 * @see http://demo.realgrid.com/RowGroup/RowGrouping/
		 * 							groupBy: [],
		 * 
		 * @see http://demo.realgrid.com/RowGroup/MergedRowGrouping/
		 * @see http://help.realgrid.com/api/types/RowGroupOptions/
		 * 							rowGroup: {
		 * 								expandedAdornments: "FOOTER"|"HEADER"|"BOTH"|"SUMMARY"|"NONE",
		 * 								collapsedAdornments: "FOOTER"|"HEADER"|"BOTH",
		 * 								mergeExpander: {boolean},
		 * 								mergeMode: {boolean}
		 * 							},
		 * 
		 * @see http://demo.realgrid.com/GridComponent/ContextMenu/
		 * @see http://help.realgrid.com/api/types/MenuItem/
		 * 							contextMenu: [
		 * 								{ label: "Print" }
		 * 							],
		 * 
		 * 모든 컬럼에 적용되는 유효성 검사 조건을 설정한다. 이미 유효성 검사 조건을 갖고 있는 컬럼에는 본 검사식이 추가된다.
		 * @see http://help.realgrid.com/api/types/EditValidation/
		 * @see http://demo.realgrid.com/Validation/ColumnValidation/
		 * 							validations: [],
		 * 
		 * 행 단위로 유효성을 검증하는 조건을 설정한다.
		 * criteria 설정시 values['fieldName'] 로 검사할 컬럼의 값을 참조할 수 있다.
		 * @see http://demo.realgrid.com/Validation/RowValidation/
		 * 							rowValidations: []
		 * 						}
		 * 					}
		 */
		initialize: function(options)
		{
			RealGrid.__super__.initialize.apply( this, arguments );

			Logger.debug("RealGrid.initialize()");
			Logger.debug("Initialize JSON Value : ");
			Logger.debug(options);
			
			var self = this;
			var listId = options.gridId+'_list';
			var width = options.width || "100%";
			var height = options.height || "500px";
			 
			if( options.footer == false )
			{
				this._msg = null;
			}
			else
			{
				options.footer != undefined && options.footer != true || (options.footer={});
				this._msg = {
					"empty": options.footer.emptyMsg || "조회된 데이타가 없습니다.",
					"total": options.footer.totalMsg || "<span class='grid_tot'>0</span> 건이 조회되었습니다.",
					"download": options.footer.downloadMsg || "엑셀 다운로드"
				};
			}
			this.template = function()
			{
				var body = '<div id="'+listId+'" class="real_grid_box" style="width: '+width+'; height: '+height+';"></div>';
				if( self._msg != null )
				{
					body += '<div id="'+listId+'_footer" class="grid_footer"><div class="grid_tot_box">'+self._msg.empty+'</div>';
					if( typeof self._msg.download == "string" )
					{
						body += '<button class="btn btn-default btn_go_down">'+self._msg.download+'</button>';
					}
					body += '</div>';
				}
				return body;
			};
			this._listId = listId;
			this._pagerId = listId+'_pager';
			this.ui =
			{
				'list': "#"+this._listId,
				'pager': "#"+this._pagerId
			};
			
			this._gridView = null;
			this._dataProvider = null;
		}
		,
		onBeforeShow: function()
		{
			var self = this;
			PlugInManager.loadLib("RealGrid-1.1.25-js")
			.then(function()
			{
				return UCMS.loadModuleByPath([ RealGrid.PATH.API ]);
			})
			.then(function()
			{
				return UCMS.loadModuleByPath([ RealGrid.PATH.ZIP ]);
			})
			.then(function(JSZip)
			{
				if(! window.JSZip )
				{
					// require.js 모드에서는 전역 객체가 생성되지 않기 때문에 명시적으로 생성.
					window["JSZip"] = JSZip;
				}
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
		}
		,
		/**
		 * 그리드 객체를 생성한다.
		 * @return {$.promise}
		 */
		_createGrid: function()
		{
			RealGridJS.setRootContext( RealGrid.PATH.ROOT );
		    
			var self = this;
			var gridSelector = "#"+this._listId;
			return UCMS.retry(function()
			{
				if( self.$el.find(gridSelector).length == 0 )
				{
					return false;
				}
				
				self._dataProvider = new RealGridJS.LocalDataProvider();
				self._gridView = new RealGridJS.GridView( self._listId );
				self._gridView.setDataSource( self._dataProvider );
			})
			.then(function()
			{
				Logger.debug("_createGrid() - Complete a RealGrid instance at ID : "+self._listId);
				self.onCreateGrid();
			});
		}
		,
		/**
		 * 그리드 객체 생성 후 절차를 수행한다.
		 */
		onCreateGrid: function()
		{
			var gridParams = this.getParam('gridParams') || {}, self = this;
			
			if( gridParams.displayOptions )
			{
				this._gridView.setDisplayOptions( gridParams.displayOptions );
			}
			if( gridParams.editOptions )
			{
				this._gridView.setEditOptions( gridParams.editOptions );
			}
			if( gridParams.panel )
			{
				this._gridView.setPanel( gridParams.panel );
			}
			if( gridParams.indicator )
			{
				this._gridView.setIndicator( gridParams.indicator );
			}
			if( gridParams.stateBar )
			{
				this._gridView.setStateBar( gridParams.stateBar );
			}
			if( gridParams.header )
			{
				this._gridView.setHeader( gridParams.header );
			}
			if( gridParams.footer )
			{
				this._gridView.setFooter( gridParams.footer );
			}
			if( gridParams.fields )
			{
				this._dataProvider.setFields( gridParams.fields );
			}
			if( gridParams.columns )
			{
				this._gridView.setColumns( gridParams.columns );
			}
			if( gridParams.data )
			{
				this._dataProvider.setRows( gridParams.data );
				//this._dataProvider.insertRows(0, gridParams.data, 0, -1);
			}
			if(! gridParams.selectOptions )
			{
				gridParams.selectOptions = { style: "singleRow" };
				
			}
			this._gridView.setSelectOptions( gridParams.selectOptions );
			if( gridParams.checkBar )
			{
				this._gridView.setCheckBar( gridParams.checkBar );
			}
			if( gridParams.fixedOptions )
			{
				this._gridView.setFixedOptions( gridParams.fixedOptions );
			}
			if( gridParams.groupBy )
			{
				this._gridView.groupBy( gridParams.groupBy );
			}
			if( gridParams.rowGroup )
			{
				this._gridView.setRowGroup( gridParams.rowGroup );
			}
			if( gridParams.contextMenu )
			{
				this._gridView.setContextMenu( gridParams.contextMenu );
				
				// XXX Testing export excel..
				/*
				var self = this;
				this._gridView.onContextMenuItemClicked = function (grid, label, index) 
				{
					self.exportGrid();
				};
				*/
			}
			if( gridParams.validations )
			{
				var names = this._gridView.getColumnNames();
				var setValidation = function(name, validations)
				{
					var column = self._gridView.columnByName( name );
					if( column.type == "group" )
					{
						/*
						for(var j in column.columns)
						{
							setValidation( column.columns[j].name, validations );
						}
						*/
					}
					else
					{
						column.validations || (column.validations=[]);
						_.map(validations, function(item)
						{
							column.validations.push(item);
						});
						self._gridView.setColumn(column);
					}
				};

				// 모든 컬럼에 검증 조건 세팅
				for(var i in names)
				{
					setValidation( names[i], gridParams.validations );
				}
			}
			if( gridParams.rowValidations )
			{
				this._gridView.setValidations( gridParams.rowValidations );
			}
			
			//
			this.initGridEvent();
		}
		,
		initGridEvent: function()
		{
			var self = this;
			var makeCellParams = function(dataRow, fieldName, fieldIndex)
			{
				var fieldValue = dataRow > -1 ? self._dataProvider.getFieldValues( fieldName, dataRow, dataRow ) : null;
				if( fieldValue != null && fieldValue.length > 0 )
				{
					fieldValue = fieldValue[0];
				}
				var params = 
				{
					id: dataRow, 
					cell: 
					{
						row: dataRow, 
						col: fieldIndex, 
						name: fieldName, 
						value: fieldValue
					}
				};
				Logger.debug( params );
				return params;
			};
			var makeRowParams = function(dataRow)
			{
				var rowData = dataRow > -1 ? self._dataProvider.getJsonRow( dataRow ) : null;
				var params =
				{
					id: dataRow, 
					data: rowData
				};
				Logger.debug( params );
				return params;
			};
			
			this._dataProvider.onRowCountChanged = function (provider, count) 
			{
				self.setTotalCount( count );
				self.trigger( RealGrid.EVENT.LOADCOMPLETE, {"totalCount": count} );
			};
			
			//
			// CELL
			//
			
			// callback 함수에서 명시적으로 false를 리턴하면 current가 변경되지 않는다.
			this._gridView.onCurrentChanging = function (grid, oldIndex, newIndex) 
			{
				Logger.debug( "BEFORESELECT - "+newIndex.dataRow );
				self.trigger( RealGrid.EVENT.BEFORESELECT, makeRowParams(newIndex.dataRow) );
			};
			this._gridView.onCurrentChanged = function (grid, newIndex) 
			{
				Logger.debug( "SELECTCELL - "+newIndex.dataRow );
				// newIndex {"itemIndex":12,"column":"col6","dataRow":0,"fieldIndex":5,"fieldName":"field6"}
				var params = makeCellParams(newIndex.dataRow, newIndex.fieldName, newIndex.fieldIndex);
				self.trigger( RealGrid.EVENT.SELECTCELL, params );
			};
			this._gridView.onDataCellClicked = function (grid, index) 
			{
				// 마우스 커서로 특정 셀을 선택한 경우 발생
			};
			this._gridView.onCellEdited = function (grid, itemIndex, dataRow, field)
			{
				Logger.debug( "EDITCELL - "+dataRow );
				// TODO 아래 주석처리된 메소드는 대문자로 변경된 필드명을 반환한다. 본래 이름은 getOrgFieldName() 을 사용한다.
				//var fieldName = self._dataProvider.getFieldName( field );
				var fieldName = self._dataProvider.getOrgFieldName( field );
				var params = makeCellParams(dataRow, fieldName, field);
				self.trigger( RealGrid.EVENT.EDITCELL, params );
			};
			this._gridView.onCellButtonClicked = function (grid, itemIndex, column) 
			{
				var current = self._gridView.getCurrent();
				Logger.debug( "CLICKBUTTON - "+current.dataRow );
				var params = makeCellParams(current.dataRow, column.fieldName, current.fieldIndex);
				self.trigger( RealGrid.EVENT.CLICKBUTTON, params );
			};
			
			//
			// ROW
			//
			
			this._gridView.onDataCellDblClicked = function (grid, index) 
			{
				Logger.debug( "DBLCLICK - "+index.dataRow );
				// index 구조
				// {"itemIndex":12,"column":"col5","dataRow":0,"fieldIndex":4,"fieldName":"field5"}
				self.trigger( RealGrid.EVENT.DBLCLICK, makeRowParams(index.dataRow) );
			};
			this._gridView.onCurrentRowChanged =  function (grid, oldRow, newRow) 
			{
				Logger.debug( "SELECT - "+newRow );
				self.trigger( RealGrid.EVENT.SELECT, makeRowParams(newRow) );
			};
			this._dataProvider.onRowUpdated = function (provider, rowId)
			{
				Logger.debug( "EDITROW - "+rowId );
				// dataRowId 로 데이타 얻기
				self.trigger( RealGrid.EVENT.EDITROW, makeRowParams(rowId) );
			};
			
			//
			// Footer
			//
			this.$el.find("button.btn_go_down").click(function()
			{
				// 엑셀 다운로드
				self.exportGrid();
			});
		}
		,
		/**
		 * Grid 파라메터를 설정한다.
		 * 
		 * @param {object} params - 신규로 설정되는 grid 파라메터.
		 * 							생성자 파라메터 중 gridParams 에 지정하는 설정을 지정 가능하다.
		 */
		setGridParam: function(params)
		{
			params || (params={});
			if( params.fields )
			{
				this._dataProvider.setFields(params.fields);
			}
			if( params.columns )
			{
				this._gridView.setColumns(params.columns);
			}
			if( params.data )
			{
				this._dataProvider.setRows(params.data);
				//this._dataProvider.insertRows(0, params.data, 0, -1);
			}
		}
		,
		getGridParam: function(name)
		{
			var gridParams = this.getParam('gridParams') || {};
			return gridParams[name];
		}
		,
		/**
		 * Grid 데이타를 추가한다.
		 * 
		 * @param {object|array} jsonData 추가되는 데이타. null 을 지정하는 경우 빈 행을 추가한다.
		 * @param {string} pos - "first", "last"
		 * @param {boolean} reset - 기존 데이타를 삭제하고 신규 데이타를 설정하는 경우 true 지정.
		 * 							추가하는 경우 생략하거나 false 를 지정한다.
		 * @returns {string} 새로 추가된 row id. 실패된 경우 null.
		 */
		addRow: function(jsonData, pos, reset)
		{
			if(! this._dataProvider || ! this._gridView)
			{
				Logger.error("addRow() - Not initialize.");
				return null;
			}
			
			pos || (pos = "first");
			if(! jsonData)
			{
				var curr = gridView.getCurrent();
		        this._gridView.beginInsertRow
		        (
		        	pos == "first" 
		        		? 0 
		        		: pos == "last" 
		        			? -1 
		        			: Math.max(0, curr.itemIndex)
		        );
		        this._gridView.showEditor();
		        this._gridView.setFocus();
		        
		        // 신규 행이 추가되고 편집 대기 상태로 전환된다.
		        // 편집을 완료하지 않으면, 추가된 행은 제거된다.
		        return;
			}
			
			if( jsonData instanceof Array == false )
			{
				// 데이타만 축출
				jsonData = _.values( jsonData );
			}
			
			if( pos == "first" )
			{
				this._dataProvider.insertRows(0, jsonData, 0, -1);
			}
			else if( pos == "last" )
			{
				this._dataProvider.addRows(jsonData);
			}
			
			// TODO 추가된 데이타의 식별자를 알 수가 없다.
		}
		,
		clear: function()
		{
			if( this._dataProvider )
			{
				this._dataProvider.clearRows();
			}
		}
		,
		/**
		 * 지정한 row 의 데이타를 변경한다.
		 * @param {number} rowId - DataProvider 식별자
		 * @param {array|object} data - 신규 데이타. 배열 또는 객체로 지정함
		 */
		setRowData: function(rowId, data)
		{
			if( this._dataProvider )
			{
				this._dataProvider.updateRow( rowId, data );
			}
		}
		,
		/**
		 * 지정한 row 데이타를 얻는다.
		 * @param {string} rowId - value 가 지정되지 않은 경우 데이타를 얻을 row 의 Item Id. 식별자를 지정하지 않으면 그리드 내의 모든 데이타가 반환된다.
		 * @param {string} value - 값이 지정된 경우 rowId 는 컬럼명으로 인식하고, 해당 컬럼이 지정한 값을 갖는 row 를 조회한다. 반환값은 배열로 반환된다.
		 * 							반환값의 형식은 조건에 해당하는 {id: rowId, data: rowData} 의 배열이 반환된다.
		 * @return {array||object}
		 */
		getRowData: function(rowId, value)
		{
			if(! this._dataProvider )
			{
				return [];	
			}
			if( rowId == undefined )
			{
				// 모든 행 데이타 얻기
				return this._dataProvider.getJsonRows( 0, -1 );
			}
			if( value == undefined )
			{
				// 지정한 행 데이타 얻기
				return this._dataProvider.getJsonRow( rowId );
			}
			
			var rows = [], rowDatas = this._dataProvider.getJsonRows( 0, -1 ), fieldName = rowId;
			for(var i in rowDatas)
			{
				if( rowDatas[i][fieldName] == value )
				{
					// TODO 행 식별자를 얻는 방법이 필요함
					rows.push({"id": i, "data": rowDatas[i]});
				}
			}
			return rows;
		}
		,
		/**
		 * 멀티 셀렉트 모드에서 선택된 데이타를 얻는다.
		 * @return {array} 선택된 데이타가 없는 경우 길이가 0 인 배열이 반환된다.
		 */
		getMultiData: function()
		{
			if(! this._gridView)
			{
				return [];
			}
			
			var rows = [], rowIds = this._gridView.getCheckedRows(false, false), rowId;
			for(var i in rowIds)
			{
				rowId = rowIds[i];
				rows.push( this._dataProvider.getRows( rowId, rowId ) )
			}
			
			return rows;
		}
		,
		/**
		 * 지정한 row 를 제거한다.
		 * @param {string} rowId - data rowId. 지정하지 않은 경우 현재 선택된 row 가 지정된다.
		 * @see http://help.realgrid.com/tutorial/b7-4/
		 */
		removeRow: function(rowId, moveNext)
		{
			if(! this._dataProvider || ! this._gridView)
			{
				return;
			}
			if(! rowId)
			{
				this._gridView.deleteSelection(true);
			}
			else
			{
				this._dataProvider.removeRows( rowId, false );
			}
		}
		,
		/**
		 * row 편집 모드를 시작한다.
		 * 
		 * @param {number} rowId - 편집을 진행할 row 의 Item Id
		 * @return {$.Promise} - 편집된 row 정보가 { id:##, data:## } 내용으로 반환된다.
		 * @see http://help.realgrid.com/tutorial/b7-3/
		 * @see http://help.realgrid.com/api/LocalDataProvider/onRowUpdated/
		 * @see http://help.realgrid.com/tutorial/c14/
		 */
		editRow: function(rowId, useKeys)
		{
			if(! this._gridView)
			{
				return;
			}
			if(! rowId)
			{
				var cur = this._gridView.getCurrent();
				rowId = cur ? cur.itemIndex : 0;
			}
			this._gridView.beginUpdateRow(Math.max(0, rowId));
			this._gridView.showEditor();
			this._gridView.setFocus();
			
			var d = $.Deferred();
			this._dataProvider.onRowUpdated = function (provider, rowId)
			{
				d.resolve({ "id": rowId, "data": provider.getRow( rowId ) });
	        };
	        /*
	        this._dataProvider.onCellEdited = function(grid, itemIndex, dataRow, field)
	        {
	        	d.resolve(row);
	        };
	        */
	        
	        return d.promise();
		}
		,
		/**
		 * 편집 중인 행이 있는지 확인한다.
		 * @return {boolean}
		 */
		isEditMode: function()
		{
			return false;
		}
		,
		/**
		 * 편집 중인 행의 Item Id 를 얻는다.
		 * @return {number}
		 */
		getEditingRowId: function()
		{
			if(! this._gridView)
			{
				return null;
			}
			var editItem = this._gridView.getEditingItem();
			return editItem ? editItem.itemIndex : null;
		}
		,
		/**
		 * 편집 중 행과 제공하는 데이타가 다른지 확인한다.
		 */
		isChangedRowData: function(newRowData)
		{
			return false;
		}
		,
		/**
		 * 편집 중인 row 를 보기 모드로 전환한다.
		 */
		restoreRow: function(rowId)
		{
		}
		,
		/**
		 * 현재 선택된 행의 Item Id 를 얻는다.
		 * 
		 * @param {boolean} bMultiple - true 가 지정된 경우 다중 선택된 항목의 목록을 얻는다.
		 * @return {number|array} 선택되지 않았거나 grid 객체가 존재하지 않는 경우 null 이 반환된다.
		 * 						다중 선택된 항목의 식별자를 얻는 경우 배열이 반환된다.
		 */
		getSelRowId: function(bMultiple)
		{
			if(! this._gridView)
			{
				return null;
			}
			if( bMultiple )
			{
				return this._gridView.getCheckedItems( false );
			}
			else
			{
				var focusCell = this._gridView.getCurrent();
				return focusCell.itemIndex;
			}
		}
		,
		/**
		 * 지정된 row 를 선택된 행으로 설정한다.
		 * @param {number} item Id
		 * @param {boolean} silent - true 를 지정한 경우, onSelectRow 이벤트가 발생되지 않는다.
		 * 							이 경우 행 선택 이후의 동작은 수동으로 처리해야 한다.
		 * @see http://help.realgrid.com/tutorial/a22/
		 */
		setSelectRow: function(rowId, silent)
		{
			if(! this._gridView)
			{
				return;
			}
			var selection = 
			{
		         style: "rows",
		         startRow: rowId,	// 시작 Item Id
		         endRow: rowId		// 종료 Item Id
		    };
			this._gridView.setSelection(selection);
		}
		,
		/**
		 * 현재 행의 인근에 배치한 row 식별자를 얻는다.
		 * @param {number} dist - 얻고자 하는 행이 현재 행을 기준으로 얼마나 떨어져 있는지를 나타낸다.
		 * 							0 인 경우 현재 선택된 행을 나타낸다.
		 * 							음수(-)는 현재 행을 기준으로 위(before)으로 몇 번째의 행인지를 나타낸다.
		 * 							양수(+)는 현재 행을 기준으로 아래(after)로 몇 번째의 행인지를 나타낸다.
		 * @return {string} 선택한 행의 Item id. 존재하지 않거나 찾을 수 없는 경우 null.
		 */
		getRowIdBySelRow: function(dist)
		{
			if(! this._gridView)
			{
				return;
			}
			var selection = this._gridView.getSelection();
			return selection ? selection.startRow : null;
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
		}
		,
		/**
		 * 멀티 선택 가능 모드인가?
		 * @return {boolean}
		 */
		isMultiSelectMode: function()
		{
			return false;
		}
		,
		/**
		 * 셀 편집 모드인가?
		 * @return {boolean}
		 */
		isCellMode: function()
		{
			return false;
		}
		,
		setCell: function(rowId, colname, value, cellclass, properties, forceup)
		{
		}
		,
		getCell: function(rowId, iCol)
		{
			return null;
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
			return true;
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
			return null;
		}
		,
		reload: function()
		{
		}
		,
		isFrozenMode: function()
		{
			return false;
		}
		,
		/**
		 * 그리드에 추가된 데이타의 row id 목록을 얻는다.
		 * @return {array} 
		 */
		getRowIds: function()
		{
			return [];
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
		}
		,
		/**
		 * 현재 그리드의 데이타와 레이아웃을 외부 파일로 내보낸다.
		 * @see http://demo.realgrid.com/Excels/ExcelExport/
		 * @see http://help.realgrid.com/api/types/GridExportOptions/ 
		 */
		exportGrid: function(params)
		{
			if(! this._gridView)
			{
				return;
			}
			
			params = params || {
			    type: "excel",	// excel | html
			    target: "local",
			    allItems: true,
			    indicator: "hidden",
			    header: "visible",
			    footer: "visible",
			    showProgress: true,
			    showConfirm: true
			};
			var ext = (params.type == "excel" ? "xlsx" : params.type );
			params.fileName = this._listId+"."+ext;
			
			this._gridView.exportGrid( params );
		}
		,
		/**
		 * 전체 데이타 수를 출력한다.
		 * 개수가 0 인경우 Empty 메시지가 출력됨
		 */
		setTotalCount: function(count)
		{
			if( count > 0 )
			{
				this.$el
					.find(".grid_tot_box").html( this._msg.total )
					.find("span.grid_tot").text( UCMS.numberWithCommas(count) );
			}
			else
			{
				this.$el.find(".grid_tot_box").html( this._msg.empty );
			}
		}
	}
	,
	{
		PATH: {
			ROOT: "/plugin/realgridjs_eval.1.1.25",
			API: "/plugin/realgridjs_eval.1.1.25/realgridjs-api.1.1.25.js",
			ZIP: "/plugin/realgridjs_eval.1.1.25/jszip.min.js"
		}
		,
		EVENT: {
			GRIDCOMPLETE: "realgrid:gridcomplete",		// 데이타가 그리드로 설정이 완료된 직후 발생된다.
			LOADCOMPLETE: "realgrid:loadcomplete",		// 모든 데이타 로딩 절차가 완료된 직후 발생된다. {"totalCount": count}
			BEFORESELECT: "realgrid:beforeselect",		// row 선택 확정 전 발생, {id:"row id", data:{object}}
			SELECT: "realgrid:select",					// row 선택시 발생, {id:"row id", data:{object}}
														// 셀편집/멀티선택 모드에서도 setSelectRow() 을 선택시에도 발생됨
			DBLCLICK: "realgrid:doubleclick",			// row 더블 클릭시 발생, {id:"row id", data:{object}}
			EDITROW: "realgrid:edit:row",				// row 편집 완료시 발생, {id:"row id", data:{object}}
			EDITCELL: "realgrid:edit:cell",				// 쎌 편집 완료시 발생, { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
			BEFOREEDITCELL: "realgrid:beforeedit:cell",	// 셀 편집 시작시 발생, { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
			
			// 편집 안되는 cell 선택시 발생, { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
			SELECTCELL: "realgrid:select:cell",
			CLICKBUTTON: "realgrid:button"				// 사용자 정의 버튼이 클릭 됐을때 발생, 클릭된 버튼의 파라메터가 argument 로 전달된다.
														// { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
		}
	});

	return RealGrid;
});
