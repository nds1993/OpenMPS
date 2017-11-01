/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
(
[
	"BaroPanelBase", "Logger",
]
,
function( BaroPanelBase,  Logger)
{
	var MenuItemData = Backbone.Model.extend(
	{
		defaults:
		{
			icon: {},
			label: '',
			order: null,
			cmd: null
		}
		,
	    parse: function(response, options)
	    {
	    	return response;
	    }
		,
		isDivider: function()
		{
			return (this.get('cmd')=='divider');
		}
		,
		/**
		 * 서브 메뉴를 갖고 있는가?
		 * @return {Boolean}
		 */
		hasMenu: function()
		{
			return (this.get('order') instanceof Array);
		}
		,
		/**
		 * 서브 메뉴 목록을 얻는다.
		 * @return {Array} 서브 메뉴 항목을 담은 배열
		 */
		getSubMenu: function()
		{
			if(this.get('order') instanceof Array)
			{
				return this.get('order');
			}
			else
			{
				return null;
			}
		}
	});
	var MenuListData = Backbone.Collection.extend(
	{
		model: MenuItemData,
		/**
		 * ResultEx(List) 형식으로 반환되는 결과값을 Collection 에 셋하는 방법을 지정한다.
		 */
		parse: function(response)
        {
        	return response.extraData.list;
        }
		,
		initialize: function(options)
		{
			MenuListData.__super__.initialize.apply(this, arguments);
			Logger.debug("MenuListData.initialize()");
			Logger.debug(options);
		}
	});
	var EmptyItem = UCMS.SPA.Component.extend(
	{
		template: "#menuItem_empty_html",
		tagName : "div",
	    className : "menu_item"
	});
	var MenuItem = UCMS.SPA.Component.extend(
	{
		template: "#menuItem_html",
		tagName : "div",
		className: 'menu_item',

		ui:
		{
			"anchor": ".anchor",
			"submenu": ".menu_group",
			"menu_item_label": ".menu_item_label"
		},
		events:
		{
			"click": "onCLick"
		},

		initialize: function(options)
		{
			this._showMenu = false;
			this._eventOnly = ( options.eventOnly == true );
			this._rootNode = options.rootNode || null;
		}
		,
		onBeforeRender: function()
		{
			if( this.model.isDivider() )
			{
				this.model.set(
				{
					iconTag: '',
					anchorTag: ''
				});
				this.className += ' divider';
			}
			else
			{
				this.model.set(
				{
					iconTag: this._makeIcon() || '',
					anchorTag: this._makeAnchor() || ''
				});
			}
		}
		,
		onCLick: function(e)
		{
			if( this.model.hasMenu() )
			{
				this._toggleMenu();
				this.trigger(MenuBoxForBaroBox.EVENT.SELECT_ITEM, this.model.toJSON());
			}
			else
			{
				this._showBox();
			}
			e.stopPropagation();
		}
		,
		_makeIcon: function()
		{
			var icon = this.model.get('icon');

			if( icon.type == 'fa' )
			{
				return '<i class="fa '+icon.value+' fa-fw"></i>';
			}
			else if ( icon.type == 'img' )
			{
				// 이미지 일때 추가 함.
				return '<img src="'+icon.value+'" class="title_icon" />';
			}
			else if ( icon.type == 'text' )
			{
				// text 일때 추가 함.
				return '<span class="title_icon">'+icon.value+'</span>';
			}
		}
		,
		_makeAnchor: function()
		{
			if( this.model.hasMenu() )
			{
				// 부모 노드
				return '<i class="fa fa-fw anchor fa-plus-square-o"></i>';
			}
			else
			{
				// 말단 노드
				return '<i class="fa fa-fw anchor fa-file-o"></i>';
			}
		}
		,
		_showBox: function()
		{
			var cmd = this.model.get('cmd');
			Logger.debug(cmd);

			if( this._eventOnly == false )
			{
				if( cmd.indexOf('#') > -1 )
				{
					//location.href = cmd;
					UCMS.reloadPage( cmd );
				}
			}
		}
		,
		_toggleMenu: function()
		{
			var openMenu = function(self)
			{
				self.ui.anchor.removeClass('fa-plus-square-o');
				self.ui.anchor.addClass('fa-minus-square-o');
				//$(".menu_item").removeClass('active').removeClass('fa-minus-square-o')
				self.ui.menu_item_label.addClass('active');

				//
				self._subMenu = new MenuBox(
				{
					collection: new MenuListData(self.model.get('order')),
					eventOnly: ( self._eventOnly == true ),
					rootNode: self._rootNode
				});
				self._subMenu.render();
				self._subMenu.triggerMethod("before:show");
				self.ui.submenu.append( self._subMenu.$el );
				self._subMenu.triggerMethod("show");
			}
			var closeMenu = function(self)
			{
				self.ui.anchor.removeClass('fa-minus-square-o');
				self.ui.anchor.addClass('fa-plus-square-o');
				self.ui.menu_item_label.removeClass('active');

				if(self._subMenu)
				{
					self._subMenu.close();
					self._subMenu = null;
				}
			}
			if( this._showMenu )
			{
				closeMenu(this);
			}
			else
			{
				openMenu(this);
			}
			this._showMenu = !this._showMenu;
		}
		,
		toggleActive: function()
		{
			this.$el.find(".menu_item_label").toggleClass("active");
		}
	});

	var	MenuBox =  UCMS.SPA.Widget.extend(
	{
		template: _.noop,
		className : "menu_box",
		ui:
		{
			box: '.btn_group_box'
		}
		,
	    collection: null,
	    itemView: MenuItem,
	    emptyView: EmptyItem
		,
		/**
		 * @params {object} options
		 * 					{
		 * 						collection: {},
		 * 						eventOnly: {boolean}, 이벤트 발생 모드
		 * 						rootNode: {boolean|MenuBox} 루투 노드의 인스턴스. 
		 * 									루투 노드는 true 를 지정한다. 
		 * 									메뉴 그룹은 루투 노드의 인스턴스가 지정된다.
		 * 					}
		 */
		initialize: function(options)
		{
			options || (options={});
			MenuBox.__super__.initialize.apply(this, arguments);

			if( options.collection == undefined )
			{
				// setMenuItems() 로 제공되는 메뉴 목록을 출력한다.
				this.collection = new MenuListData();
			}
			else if( options.collection instanceof Backbone.Collection )
			{
				// 제공되는 메뉴 목록을 출력한다.
				this.collection = options.collection;
			}
			
			//
			this.itemViewOptions = { eventOnly: (options.eventOnly==true) };
			if( options.rootNode == true )
			{
				// 루투 메뉴
				this.itemViewOptions.rootNode = this;
			}
			else if( options.rootNode )
			{
				// 메뉴 그룹
				this.itemViewOptions.rootNode = options.rootNode;
			}
		}
		,
	    isEmpty: function(collection)
	    {
	        return !collection || collection.length === 0;
	    }
		,
		buildItemView: function(item, ItemViewType, itemViewOptions)
        {
			var self = this;
        	var itemView = MenuBox.__super__.buildItemView.apply( this, arguments );
        	if( this.itemViewOptions.eventOnly == true )
        	{
	        	itemView.on(MenuBoxForBaroBox.EVENT.SELECT_ITEM, function(model)
	        	{
	        		self.itemViewOptions.rootNode.trigger(MenuBoxForBaroBox.EVENT.SELECT_ITEM, model);
	        		if( self._selItem != itemView )
	        		{
		        		itemView.toggleActive();
		        		if( self._selItem )
		        		{
		        			self._selItem.toggleActive();
		        		}
		        		self._selItem = itemView;
	        		}
	        	});
        	}
        	
        	return itemView;
        }
		,
		/**
		 * 메뉴의 모양을 설정한다.
		 */
		_initLayout: function(params)
		{
			var layout = "";
			
			params || (params = {});
			!params.size || this.$el.addClass(params.size);
			!params.align || this.$el.addClass(params.align);
			
			//
			this.itemViewOptions.eventOnly = ( params.eventMode == true );
		}
		,
		/**
		 * 새로운 메뉴 파라메터를 설정한다.
		 */
		setMenuItems: function(params, redraw)
		{
			if(! params.defaultMenu )
			{
				Logger.error("setMenuItems() - Invalid parameter : defaultMenu");
				return false;
			}
			if(! params[params.defaultMenu] )
			{
				Logger.error("setMenuItems() - Invalid parameters.");
				return false;
			}
			this._menuParams = params;

			var makeItem = function (menuId, params)
			{
				var item = params[menuId];

				if( item.order instanceof Array )
				{
					for(var i in item.order)
					{
						if( typeof item.order[i] == 'string' )
						{
							// TODO 순환 참조가 발생하지 않도록 유의한다.
							item.order[i] = makeItem( item.order[i], params );
						}
					}
				}
				else
				{
					item.order = null;
				}

				return item;
			}
			var topItem = makeItem(params.defaultMenu, params);
			
			this._initLayout(params["layout"]);
			this.collection.reset(topItem.order);
		}
	});
	
	/**
	 * BaroBox 에 MenuBox 를 추가하기 위한 Wrapping 하는 위젯.
	 */
	var MenuBoxForBaroBox = BaroPanelBase.extend(
	{
		template: function(data)
		{
			return "<div class=menu_region></div>";
		}
		,
		regions:
		{
			list: ".menu_region"
		}
		,
		/**
		 * 메뉴 위젯을 초기화한다.
		 * @param {object} options - 메뉴 초기화 파라메터. 위젯 생성 후 setMenuItems() 로 전달할 수도 있다.
		 * {
					  "layout": {
					    "size": "xs",
					    "align": "text-left",
					    "type": "treeview"
					  },
					  "defaultMenu": "main_menu",
					  "main_menu": {
					    "label": "메인메뉴",
					    "order": [
					      "MCOM",
					      "MLOG",
						  "MMAN",
						  "MSAL",
						  "TMCO",
						  "TM"
					    ]
					  },
					  "divider": {
					    "cmd": "divider"
					  },
					  "MCOM": {
					    "label": "관리",
					    //
					    // 폴더는 order 필드를 갖는다
					    //
					    "order": [
					      //
					      // 자식 메뉴들은 부모 메뉴 항목의 식별자에 자신의 식별자를 붙여서 사용한다.
					      //
					      "MCOMAA10",
					      "MCOMAA20",
					      "MCOMAA30",
					      "MCOMAA40"
					    ]
					  },
					  "MCOMAA10": {
					    "label": "고객",
					    "order": [
					      "MCOMAA10_01",
					      "MCOMAA10_02",
					      "MCOMAA10_03"
					    ]
					  },
					  "MCOMAA10_01": {
					    "label": "여신관리",
					    //
					    // 명령어 메뉴는 cmd 필드를 갖는다
					    //
					    "cmd": "#box/MCOMAA10_01"
					  }
					  , ...
			}
		 * 
		 */
		initialize: function(options)
		{
			MenuBoxForBaroBox.__super__.initialize.apply(this, arguments);
			this._menu = null;
		}
		,
		onRender: function()
		{
			var self = this;
			
			this._menu = new MenuBox({rootNode: true});
			this.list.show( this._menu );
			this._menu.on
			(
				MenuBoxForBaroBox.EVENT.SELECT_ITEM
				, function(model)
				{
					self.trigger(MenuBoxForBaroBox.EVENT.SELECT_ITEM, model);
				}
			);
			if( this._params )
			{
				if(! this._params.defaultMenu )
				{
					// 제공된 메뉴 정보가 없음
					return;
				}
				
				this.setMenuItems( this._params );
			}
		}
		,
		setMenuItems: function(params, redraw)
		{
			if( this._menu )
			{
				this._menu.setMenuItems(params, redraw);
			}
		}
	}
	,
	{
		EVENT:
		{
			SELECT_ITEM: "menu:select:item"
		}
	});

	return MenuBoxForBaroBox;
});
