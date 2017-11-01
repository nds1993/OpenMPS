/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2016 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

define(
[ "BaroPanelBase", "Logger", "BaroBoxTab" ], 
function( BaroPanelBase, Logger, BaroBoxTab )
{
	var instanceMethod = 
	{
		template: _.noop
		,
		initialize: function(options)
		{
			BaroPanelBase.prototype.initialize.apply( this, arguments );
			
			Logger.debug("BaroBox.initialize() - Template Id : "+options.template);
			Logger.debug("BaroBox.initialize() - Class Name : "+options.className);

			this._initTemplate();
			this._initWidgets();
			
			Logger.debug("BaroBox.initialize() - contents widget list : "+this._getWidgetIdList().join());
			Logger.debug("BaroBox.initialize() - complate to initialize a contents box.");
		}
		,
		_initTemplate: function()
		{
			var self = this; 
			this.template = function(data)
			{
				// 내부 위젯의 구조를 담는 템플릿을 생성 및 반환한다.
				return self._makeBoxSkeleton( self._getWidgetIdList() );
			}
		}
		,
		render: function()
		{
			// 골격 코드를 랜더링한다.
			BaroBox.__super__.render.call(this);
			
			//
			this._initBoxHeight();
			this._createRegions();
		}
		,
		onBeforeShow: function()
		{
			this._showContentsBox();
		}
		,
		onShow: function()
		{
		}
		,
		/**
		 * 위젯으로 부터 발생된 이벤트를 처리한다.
		 * BaroBox 를 상속 받는 모듈의 경우 자식 모듈에서 onEventHandler(event) 를 구현하면, 처리되지 않은 이벤트 또는 브로드캐스팅 된 이벤트를 수신할 수 있다.
		 * 이 때 onEventHandler() 에서 true 를 반환하면, 이벤트가 처리된 것으로 간주하여 이벤트 전파가 정지된다.
		 *  
		 * @param {object} event - 이벤트 객체
		 */
		_onItemEventHandler: function(event)
		{
			var itemId = event.itemId;
			var cmd = event.cmd;
			var params = event.params;
			var handlingEvent = function(self, itemId)
			{
				var _INSTANCE_ = self._getWidgetInstance(itemId);
				if( _INSTANCE_ instanceof BaroPanelBase )
				{
					// 다른 아이템에 이벤트 전파
					_INSTANCE_.onEventHandler( event );
					return true;
				}
				else if( _INSTANCE_ != null )
				{
					Logger.warn("BaroBox._onItemEventHandler() - Unable to process event at Widget "+itemId);
					// 모든 위젯은 BaroPanelBase 을 상속 받아야 한다.
					return false;
				}
			};
			
			if( typeof itemId == "string" )
			{
				if( handlingEvent( this, itemId ) == false )
				{
					if( !this["onEventHandler"] || !this.onEventHandler( event ) )
					{
						// 이벤트가 처리되지 못한 경우 부모로 이벤트 전파
						this.trigger( BaroBox.WIDGET_EVENT, event);
					}
				}
			}
			else
			{
				var idList = this._getWidgetIdList();
				
				// Broadcast
				for( var i in idList )
				{
					handlingEvent( this, idList[i] );
				}
				
				//
				if( !this["onEventHandler"] || !this.onEventHandler( event ) )
				{
					// 이벤트가 처리되지 못한 경우 부모로 이벤트 전파
					this.trigger( BaroBox.WIDGET_EVENT, event);
				}
			}
		}
	};
	
	var widgetManagement = 
	{
		_widgets: null,
		_count: 0,
		
		_initWidgets: function()
		{
			this._widgets = new Backbone.Model();
			this._count = 0;
		},
		
		/*
		 * 위젯의 생성 순서에 맞는 식별자 목록을 얻는다.
		 * 
		 * @returns {Array}
		 */
		_getWidgetIdList: function()
		{
			if(this.getParam('order') == null)
			{
				this.setParam('order', []);
			}
			
			return this.getParam('order');
		}
		,
		/**
		 * 제공된 위젯 상세정보를 얻는다.
		 * @param {string} widgetId - 위젯 식별자. 'order' 항목에 담겨 있는 위젯 목록 중 하나를 지정한다.
		 * @returns	{object||null} 위젯 파라메터가 존재하지 않는 경우 null 반환
		 * 			{
		 * 				module: ##, 
		 * 				options: {} 
		 * 			}
		 */
		_getWidgetParams: function(widgetId)
		{
			return this.getParam( widgetId ) || null;
		}
		,
		_setWidgetParams: function(widgetId, params)
		{
			this.setParam( widgetId, params );
		}
		,
		/**
		 * 지정한 위젯의 파라메터 정보를 제거한다.
		 */
		_deleteWidgetParams: function(widgetId)
		{
			if( this._params[ widgetId ] )
			{
				delete this._params[ widgetId ];
				
				//
				var order = this.getParam('order');
				if( order[widgetId] )
				{
					delete order[widgetId];
					this.setParam('order', order);
				}
			}
		}
		,
		/**
		 * 위젯 인스턴스를 저장한다.
		 * 이 때 박스 내의 위젯과 소통할 수 있는 이벤트 핸들러가 연결된다.
		 * 
		 * @param {string} widgetId - 위젯 식별자
		 * @param {BaroPanelBase} widgetInstance - 위젯 인스턴스
		 * @returns {Boolean}
		 */
		_setWidgetInstance: function(widgetId, widgetInstance)
		{
			if( this._widgets.has(widgetId) == true )
			{
				return false;
			}
			
			var self = this;
			
			// 위젯 아이템 이벤트 핸들러 등록
			widgetInstance.on( BaroBox.WIDGET_EVENT, function(event)
			{
				self._onItemEventHandler( event );
			});
			this._widgets.set(widgetId, widgetInstance);
			
			// Activation 이 활성화 된 경우 탭을 추가함
			this._addTab( widgetId );
			this._count++;
			
			return true;
		}
		,
		_deleteWidgetInstance: function(widgetId)
		{
			if( this._widgets.has(widgetId) == false )
			{
				return false;
			}
			
			// 위젯 파라메터 정보 제거
			this._deleteWidgetParams(widgetId);
			if( this._tab )
			{
				if( this._tab.getActiveTabId() == widgetId )
				{
					// 이전 탭을 활성화한다.
					var tab$ = this._tab.getActiveTabLi$().prev();
					tab$.length > 0 || (tab$ = this._tab.getActiveTabLi$().next());
					
					if( tab$.length > 0 )
					{
						this._tab.activeTab( tab$.children().attr('class') );
					}
				}
				this._tab.removeTab(widgetId);
			}
			this._widgets.unset(widgetId);
			this._count--;
		}
		,
		/**
		 * 지정한 위젯의 인스턴스를 얻는다.
		 * @returns {object} 존재하지 않는 경우 null
		 */
		_getWidgetInstance: function(widgetId)
		{
			if( this._widgets.has(widgetId) == false )
			{
				return null;
			}
			return this._widgets.get(widgetId);
		}
		,
		/**
		 * 생성된 위젯 인스턴스 개수를 구한다.
		 * @return {number}
		 */
		getSubInstanceCount: function()
		{
			return this._count;
		}
	};
	
	var activationManagement =
	{
		_naviParams:
		{
			module: 'BaroBoxTab', 
			options: {}
		},
		_tab: null,
		_defaultTabId: null,
		
		/**
		 * 활성화 모드 초기화
		 */
		_initActivation: function()
		{
			if( this._tab )
			{
				Logger.warn("_initActivation() - Already to initialized.");
				return;
			}
			
			var activation = this.getParam('activation');
			if(! activation )
			{
				return;
			}
			
			if( activation.navigator )
			{
				//
				// Navigator 옵션 적용
				//
				this._naviParams = 
				{
					'module': activation.navigator.module||'BaroBoxTab', 
					'options': JSON.parse(JSON.stringify(activation.navigator.options||{}))
				};
			}
			
			//
			// 탭을 필요로 하는 전환 옵션 지정
			//
			var box = this;
			var initShowHideMethod = function(box, TabModule, options)
			{
				var position = options.pos || 'top';
				if( /(left|top)/.test(position) )
				{
					box.$el.prepend( $("<div>").attr("class", "navigator_region") );
				}
				else if( /(right|bottom)/.test(position) )
				{
					box.$el.append( $("<div>").attr("class", "navigator_region") );	
				}
				box.addRegions({ "box_navigator" : '.navigator_region' });

				// 네비게이터 생성
				var boxTab = new TabModule( options );
				boxTab.on( TabModule.EVENT.ACTIVE
				, function(params)
				{
					//
					// 콘텐츠 위젯 show/hide 처리
					//
					box._getWidgetInstance( params.active ).showPanel( true );
					if( params.active == params.inactive )
					{
						// 활성화된 탭이 비활성화되는 것을 방지
						return;
					}
					if( typeof params.inactive == 'string' )
					{
						var inactiveBox = box._getWidgetInstance( params.inactive );
						if( inactiveBox )
						{
							inactiveBox.showPanel( false );
						}
					}
					// 탭 이벤트 전파
					box.broadcastEvent(BaroBox.EVENT.ACTIVETAB, params);
				} );
				boxTab.on( TabModule.EVENT.CLOSE, function(params)
				{
					if( box.destroyWidget( params.id ) == true )
					{
						// 위젯 닫기 이벤트 전파
						box.broadcastEvent(BaroBox.EVENT.CLOSETAB, params);
					}
					else
					{
						// 위젯을 닫을 수 없는 상태.
					}
				});
				box["box_navigator"].show( boxTab );
				
				//
				box._tab = boxTab;
				box._defaultTabId = activation.defaultWidget;
			} // initShowHideMethod()
			
			switch( activation.method )
			{
			case 'show-hide':
				UCMS.loadModuleByPath([ this._naviParams.module ])
				.then(function(TabModule)
				{
					initShowHideMethod( box, TabModule, box._naviParams.options );
				});
				break;
				
			case 'hori-swipe':
				break;
				
			case 'vert-swipe':
				break;
				
			default:
			case 'one-page':
				break;
			}
		}
		,
		/**
		 * 콘텐츠 박스의 높이를 설정한다.
		 */
		_initBoxHeight: function()
		{
			var height = this.getParam('height') || '';
			// TODO Box 높이 지정시 이슈
			// className 을 지정하는 경우 contents_box 가 사용되지 않는다.
			// 이런 경우 height 옵션을 지정하지 못하는 문제가 발생한다.
			// 왜 첫 번째 contents_box 에 높이를 지정했는지는 이유가 기억나지 않는다.
			//this.$el.find('.contents_box:first').css('height', height);
			this.$el.css('height', height);
		}
		,
		/**
		 * 콘텐츠 박스의 구조를 템플릿 리소스로 생성한다.
		 * 콘텐츠 박스 내의 region 은 "박스이름"+"_region"의 형식으로 식별된다. 
		 * 
		 * @param regionList	{ Array } 콘텐츠 박스 식별자 배열
		 * @return { String } 생성된 템플릿 리소스 식별자 이름
		 */
		_makeBoxSkeleton: function(regionList)
		{
			var $parentTag = $("<div class='contents_box'></div>");
			for(var idx in regionList)
			{
				// TODO region 식별자 지정시 postfix 방식으로 위젯 식별자에 "_region" 을 붙인다.
				$parentTag.append( $("<div>").attr("class", regionList[idx]+"_region") );
			}

			return $("<dummy_parent>").append( $parentTag ).html();
		}
		,
		/**
		 * 제공된 contents box 구성을 위한 region 을 생성한다.
		 */
		_createRegions: function()
		{
			var box = this;
			var widgetList = this._getWidgetIdList();
			
			for(var idx in widgetList)
			{
				var regionName = widgetList[idx];
				var region = {};
				region[regionName] = "."+regionName+"_region";
				box.addRegions( region );
				
				Logger.debug("_createRegions() - Region for ContentsBox : "+widgetList[idx]);
			}
			this._initActivation();
		}
		,
		/**
		 * contents box 로 제공된 위젯들을 활성화한다.
		 * order 항목에 담긴 위젯 식별자 순서로 생성된다.
		 * 활성화된 위젯 인스턴스는 _getWidgetInstance() 로 얻을 수 있다.
		 */
		_showContentsBox: function()
		{
			var widgetList = this._getWidgetIdList();
			var contentsCount = widgetList.length;
			
			if( contentsCount == 0 )
			{
				Logger.debug("_showContentsBox() - empty box.");
				// 빈 box 상태로 즉시 완료.
				this._fireShowComplete();
				return;
			}
			
			for(var idx in widgetList)
			{
				(function(box, widgetId)
				{
					var boxRegion = box[widgetId];
					if( boxRegion == undefined )
					{
						Logger.error("_showContentsBox() - region : Not existed a region : "+widgetId);
					}
					else
					{
						var widgetParams = box._getWidgetParams(widgetId);
						if( widgetParams == undefined || widgetParams.module == undefined )
						{
							Logger.warn("_showContentsBox() - Unknown region : "+widgetId); 
							return;
						}
						
						Logger.info("_showContentsBox() - region : "+widgetId+", module : "+widgetParams.module);
						box._createWidget( widgetParams )
						.then(function(_INSTANCE_)
						{
							_INSTANCE_.showPanel( box._isTabMode() == false );
							// 인스턴스가 등록되면서 기본탭이 활성화된다.
							box._setWidgetInstance( widgetId, _INSTANCE_ );
							_INSTANCE_.getWidget$Element();
							boxRegion.show( _INSTANCE_ );
							
							//
							Logger.info("_showContentsBox() - Ready : "+widgetId+", Active Widget Count : "+box.getSubInstanceCount()+", max : "+contentsCount);
							if( box.getSubInstanceCount() == contentsCount )
							{
								Logger.debug("_showContentsBox() - Completed : "+widgetId);
								box._fireShowComplete();
							}
						});
					}
				}
				)( this, widgetList[idx] );
			}
		}
		,
		/**
		 * 제공된 파라메터를 사용해서 위젯을 생성한다. 
		 * @param {object} widgetParams - 위젯 파라메터. {module:##,options:##} 형식으로 지정
		 * @returns {$.promise} 생성된 위젯 인스턴스를 반환한다. 반환된 위젯은 region 영역에 출력하고, 인스턴스는 _setWidgetInstance() 로 보관한다.
		 */
		_createWidget: function(widgetParams)
		{
			var d = $.Deferred();
			var box = this;
			
			if( (widgetParams.module || widgetParams.type) == undefined )
			{
				Logger.error("_createWidget() - Invalid Parameters :");
				Logger.error(widgetParams);
				d.reject("_createWidget() - Invalid Parameters.");
				return d.promise();
			}
			
			UCMS.loadModuleByPath
			(
				[ widgetParams.module || widgetParams.type ], 
				function(Module)
				{
					if( typeof Module != 'function' && typeof Module['widget'] == 'function' )
					{
						// Manifest 기반 로딩 방식인 경우
						// 패널에서는 무조건 위젯을 로딩한다.
						Module = Module['widget'];
					}
					
					var _INSTANCE_ = null;
					
					if( widgetParams.module == "GPanel" )
					{
						_INSTANCE_ = new Module.GContainerPanel( widgetParams.options );
					}
					else
					{
						_INSTANCE_ = new Module( widgetParams.options );
					}
					d.resolve( _INSTANCE_ );
				}
				,
				function(err)
				{
					Logger.error(err);
					Logger.error("Find not found a module : "+(widgetParams.module||widgetParams.type));
					d.reject(err);
				}
			);
			
			return d.promise();
		}
		,
		/**
		 * 신규 위젯을 추가한다.
		 * 위젯 식별자가 중복되는 경우 추가되지 않는다.
		 * @param {string} widgetId
		 * @param {object} params - { module: ##, options: {} }
		 * @param {boolean} show - 화면에 출력 유무 지정. true 인 경우 보이는 상태로 위젯을 추가한다.
		 * @return {$.promise} 정상적으로 위젯이 추가된 경우 resolve. region 추가에 실패하는 경우 reject.
		 * 						resolve 시 {"widgetId": ##, "params": {}, "instance": {}} 의 결과값이 반환된다.
		 */
		addWidget: function(widgetId, params, show)
		{
			var d = $.Deferred();
			
			if(this[widgetId] )
			{
				Logger.warn("addWidget() - Already exist a widget : "+widgetId);
				if( this._tab )
				{
					this._tab.activeTab( widgetId );
				}
				d.resolve({"widgetId": widgetId, "params": params, "instance": this._getWidgetInstance( widgetId )});
				return d.promise();
			}
			
			var box = this;
			var widgetRegion = {};
			
			// region 템플릿 생성
			switch( this._tab ? this._tab.getPosition() : null )
			{
			default:
			case 'left' :
			case 'top' :
				this.$el.find('.contents_box:first').append( $("<div>").attr("class", widgetId+"_region") );
				break;

			case 'right' :
			case 'bottom' :
				this.$el.find('.contents_box:first').prepend( $("<div>").attr("class", widgetId+"_region") );
				break;
			}
			// region 추가
			widgetRegion[widgetId] = '.'+widgetId+'_region';
			box.addRegions( widgetRegion );
			// 파라메터 추가
			{
				var order = this.getParam('order')||[];
				order.push( widgetId );
				this.setParam('order', order)
				this.setParam( widgetId, params );
			}
			if(! box[widgetId] )
			{
				Logger.error("Failed to create a region for Widget ["+widgetId+"]");
				d.reject();
				return d.promise();
			}
			
			// 위젯 생성
			box._createWidget( params )
			.then(function(_INSTANCE_)
			{
				_INSTANCE_.showPanel( show );
				box._setWidgetInstance( widgetId, _INSTANCE_ );
				box[widgetId].show( _INSTANCE_ );
				
				if( box._tab && show == true )
				{
					box._tab.activeTab( widgetId );
				}
				
				d.resolve({"widgetId": widgetId, "params": params, "instance": _INSTANCE_});
			});
			
			return d.promise();
		}
		,
		/**
		 * @param {string} widgetId - 종료할 위젯 식별자
		 * @param {boolean} force - true 이면 강제로 닫는다.
		 * @return {boolean} 위젯 닫기 성공 플레그. 위젯을 종료할 수 없는 경우 false 가 반환된다. 
		 */
		destroyWidget: function(widgetId, force)
		{
			var _WIDGET_ = this._getWidgetInstance(widgetId);
			if(! this[widgetId] || !_WIDGET_)
			{
				Logger.warn("destroyWidget() - Find not found a widget : "+widgetId);
				return false;
			}
			if(force != true && _WIDGET_.isPossibleClose && _WIDGET_.isPossibleClose() == false )
			{
				Logger.warn("destroyWidget() - Not ready to close a widget : "+widgetId);
				return false;
			}
			
			// 위젯 제거
			this[widgetId].close();
			// 레지온 제거
			this.removeRegion(widgetId);
			// 골격제거
			this.$el.find('.'+widgetId+'_region').remove();
			// 위젯과 관련된 부가 정보 제거
			this._deleteWidgetInstance( widgetId );
			
			return true;
		}
		,
		/**
		 * Box 출력이 완료 이벤트를 발생한다.
		 * Method 와 Event 가 동시에 발생된다.
		 */
		_fireShowComplete: function()
		{
			// triggerMethod() 내에서 trigger() 도 동시에 호출된다.
			// 즉, 자식 모듈이 onShowComplete() 을 구현한다면, 호출해준다.
			this.triggerMethod(BaroBox.METHOD.SHOWCOMPLETE);
			
			// box 가 추가되면서 기본탭은 자동으로 active 된다.
			// 다시 활성화 할 필요 없음.
			//this._activeDefaultTab();
		}
	};
	
	var navigatorManagement =
	{
		_isTabMode: function()
		{
			return this._tab != null;
		}
		,
		/**
		 * 탭 콘텐츠인가? 아니면 디자인 콘텐츠인가? 확인함.
		 * 위젯이 화면 출력 완료 후에 정상적으로 동작됨.
		 * @returns {boolean} 탭을 갖는 콘텐츠인 경우 true 반환
		 */
		_isTabContent: function(widgetId)
		{
			if(! this._tab )
			{
				return false;
			}
			
			var $tab = this._tab.getTab$(widgetId);
			return $tab.length > 0;
		}
		,
		/**
		 * 탭 정보를 추가한다.
		 * @param {string} tabId - 추가되는 탭 레이블
		 * @param {object} cmd - 탭이 클릭시 수행하는 명령어. 명령어가 아직 정의되지 않았다.
		 * @returns {boolean} 추가 결과. 추가된 경우 true, 
		 * 					Tab 이 필요 없는 경우 false. 이 경우는 디자인 항목의 경우 tab 전환이 필요없다.
		 */
		_addTab: function(tabId, cmd)
		{
			if( this._tab )
			{
				var added = this._tab.addTab(tabId, cmd);
				if( added == true && this._defaultTabId == tabId)
				{
					this._tab.activeTab( this._defaultTabId );	
				}
				return added;
			}
			
			return false;
		}
		,
		/**
		 * @deprecated _addTab() 에서 기본탭은 활성화하도록 정책이 변경됨으로써 본 메소드는 호출할 필요없다.
		 * 기본 탭을 활성화한다.
		 * 위젯이 활성화되는 시간이 모듈 로딩 시간에 따라 시점이 동기화되지 않는다.
		 * 그래서 모든 위젯이 활성화 되었을 때 까지 기다렸다가 탭을 활성화해야 정상적으로 원하는 탭을 활성화 할 수 있다.
		 * show-hide 모드에서인 경우에만 탭이 활성화된다. 
		 */
		_activeDefaultTab: function()
		{
			if( this._tab && this._defaultTabId )
			{
				Logger.debug("_activeDefaultTab() - default tab id : "+this._defaultTabId+", tab count : "+this._tab.getTabCount());
				if( this._tab.getTabCount() > 0 )
				{
					this._tab.activeTab( this._defaultTabId );
				}
				else
				{
					Logger.debug("_activeDefaultTab() - wait..");
				}
			}
		}
		,
		/**
		 * 지정한 탭을 활성화한다.
		 */
		activeTab: function(tabId)
		{
			if( this._tab )
			{
				this._tab.activeTab( tabId );
			}
		}
		,
		getActiveTabId: function()
		{
			return this._tab ? this._tab.getActiveTabId() : null;
		}
	};
	
	/**
	 * 위젯 모듈을 담는 그릇 모듈.
	 * 자식 위젯에서 발생된 이벤트를 중계한다.
	 */
	var BaroBox = BaroPanelBase.extend
	(
		_.extend({}
		, instanceMethod
		, widgetManagement
		, activationManagement
		, navigatorManagement
		),
		{
			METHOD:
			{
				// 모든 위젯이 출력 완료되는 시점에 발생된다..
				SHOWCOMPLETE: "show:complete"
			}
			,
			EVENT:
			{
				// 탭의 상태 변화를 전파한다.
				ACTIVETAB: "boxtab:active",		// { active: {string} tabId, inactive: {string} tabId }
				CLOSETAB: "boxtab:close"		// { id: {string} tabId }
			}
		}
	);

	return BaroBox;
});