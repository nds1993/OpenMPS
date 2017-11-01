/**
 * Project : OpenMPS MIS
 * 
 * 프로그램 관리 화면을 구현한다.
 * ID : TMCOUR30
 * 
 */

define([
	"Logger",
	"NDSProps",
	"APIClient",
	"WorkAreaRenderer",
	"WorkAreaHeader",
	"SubContainer",
	"manifest!jqGrid4-1.0.0#widget",
	"manifest!Menu-1.0.0#widget"
], function(Logger, NDSProps, APIClient, WorkAreaRenderer, ContentHeader, SubContainer, JQGrid, MenuBoxForBaroBox)
{
	var MainMethod =
	{
		initialize: function(options) 
		{
			Renderer.__super__.initialize.call(this, options);
		}
		,
		_getBoxInstance: function(boxId)
		{
			var box = null;
			
			switch(boxId)
			{
			case 'headerBox':
				box = this._getWidgetInstance('headerBox');
				break;
			case 'menuTree':
				box = this._getWidgetInstance('programMngBox')
					._getWidgetInstance('menuTree');
				break;
			case 'formBox':
				box = this._getWidgetInstance('programMngBox')
					._getWidgetInstance('resultBody')
					._getWidgetInstance('formBox');
				break;
			case 'resultBox':
				box = this._getWidgetInstance('programMngBox')
					._getWidgetInstance('resultBody')
					._getWidgetInstance('resultBox');
				break;
			}
			
			return box;
		}
		,
		_getResultGrid: function(gridId)
		{
			return this._box.result;
		}
		,
		onShowComplete: function() 
		{
			Renderer.__super__.onShowComplete.call( this );
			
			var self = this;
			
			this._menu = this._getBoxInstance('menuTree');
			this._menu.on(MenuBoxForBaroBox.EVENT.SELECT_ITEM, function(model)
			{
				if( self._box.header.getButtonMode() == ContentHeader.MODE.READY )
				{
					self._fetchSubItems( model );
				}
				else
				{
					UCMS.alert("저장되지 않은 데이타가 존재합니다.");
				}
			});
			
			this.triggerMethod("query");
		}
	};
	
	// 헤더 버튼 파트
	var HeaderMethod =
	{

	};
	
	var TreeMenuMethod = 
	{
		onQuery: function() 
		{
			// 프로그램 목록을 조회하여 트리 메뉴에 출력
			var query = this._getQueryData() || {};
			if (!query) 
			{
				return;
			}
			else
			{
				query["systemCode"] = NDSProps.get('systemCode');
			}

			var self = this;
			this._queryGridData(null, query, function(res)
			{
				var params = self._makeMenuParams(res);
				params.layout = {
				    "size": "xs",
				    "align": "text-left",
				    "type": "treeview",
				    "eventMode": true
				};
				self._menu.setMenuItems( params );
				return res;
			});
		}
		,
		/**
         * 반환된 프로그램 목록을 기반으로 메뉴 파라메터를 생성한다.
         */
        _makeMenuParams: function(resultEx)
        {
        	if( !resultEx.extraData || resultEx.extraData.totalRecordCount == 0 )
        	{
        		return resultEx;
        	}
        	
        	var menuParams = 
        	{
        		"layout": {},
        		"defaultMenu": "root",
        		"root": {
        			"label": "Root",
        			"order": []
        		}
        	};
        	var items = resultEx.extraData.result;
        	var makeItems = function(items)
        	{
        		for(var i in items)
        		{
        			var item = items[i];
        			var subItem = {
						"upCode": item.upCode,
    					"upName": item.upName,
        				"label": item.menuName,
        				"menuCode": item.menuCode
        			};

        			if( item.programYn == "Y" )
        			{
        				subItem["cmd"] = "#box/"+item.menuCode;
        			}
        			else
        			{
        				// 폴더 노드
        				subItem["order"] = [];
        			}
        			menuParams[ item.menuCode ] = subItem;
        		}
        	};
        	var makeRelations = function(items)
        	{
        		for(var i in items)
        		{
        			var item = items[i];
        			
        			if( item.upCode == "0" )
        			{
        				// 루트 노드
        				//menuParams["defaultMenu"] = item.menuCode;
        				menuParams["root"].order.push( item.menuCode );
        			}
        			else
        			{
        				var parentNode = menuParams[item.upCode];
        				if( parentNode )
        				{
	        				// 부모 노드에 자식 노드 등록
	        				parentNode.order.push( item.menuCode );
        				}
        				else
        				{
        					Logger.warn("makeRelations() - Invalid Menu list.");
        				}
        			}
        		}
        	};
        	
        	makeItems( items );
        	makeRelations( items );
        	
        	return menuParams;
        }
	};
	
	var GridMethod =
	{
		_upItem: {
			code: null,
			name: null
		},
		
		_fetchSubItems: function(upItem)
		{
			var self = this;
			this._queryGridData( null, { upCode: upItem.menuCode }, function(res)
			{
				self._upItem.code = upItem.menuCode;
				self._upItem.name = upItem.label;
				self._setResult(res);
				self._backupGrid();
				return res;
			});
		}
		,
		onCreate: function() 
		{
			Logger.info("TMCOUR30Renderer.onCreate()");
			if( this._upItem.code == null )
			{
				UCMS.alert("부모 메뉴를 선택하세요.");
				return;
			}

			var gridId = this._getHeaderGridName();
			var newRowId = this._createGridRow(gridId, 
			{
				upCode: this._upItem.code,
				upName: this._upItem.name
			});
			if( newRowId != null )
			{
				this._getTaskPool(gridId).add( new WorkAreaRenderer.CreateTask(newRowId, {}) );
				Logger.info("newRowId : "+newRowId);
				
				var grid = this._getResultGrid(gridId);
				grid.setSelectRow( newRowId, true );
			}
		}
	};
	
	var FormMethod = 
	{

	};
	
	var Renderer = WorkAreaRenderer.extend
	(
		_.extend({}, MainMethod, HeaderMethod, TreeMenuMethod, GridMethod, FormMethod)
	);
	 
	return Renderer;
});
