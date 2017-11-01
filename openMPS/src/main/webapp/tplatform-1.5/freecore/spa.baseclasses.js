/**
 * Project : UCMS( Unified Contents Messaging Solution )
 *
 * Copyright (c) 2016 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

/**
 * SPA 구현 클래스를 정의한다.
 * UCMSPlatform 에 의해 활성화 되며, UCMSPlatform.SPA 로 접근한다.
 */
define(
[
	"spa_debug"
],
function(Debug)
{
	"use strict";
	
	/////////////////////////////////////////////////////////////////////////
	//
	// ModuleCache
	//
	
	/**
	 * 모듈 활성자.
	 */
	function ModuleCache()
	{
		function getPagePath()
		{
			var		url = window.location.pathname;
			
			if( url.charAt( url.length - 1 ) != '/' )
			{
				url = url.substr( 0, url.lastIndexOf("/")+1 );
			}

			return url;
		};
		
		this._moduleHolder = {};
		this._moduleRoot = getPagePath();
		
		UCMS.debug("Module Root Path : "+this._moduleRoot);
	}
	
	/**
	 * 지정된 패널 모듈을 로드하여 내부 저장소에 등록한다.
	 * 
	 * @param moduleName	조회시 사용되는 모듈 이름. UCMSPlatform 을 기준으로 하는 상대 경로를 지정한다.
	 * @param modulePath	모듈의 경로.
	 * @param cbLoaded		모듈 로딩이 완료되면 호출되는 콜백 함수. 당장 사용되는 경우 지정.
	 * 						function onLoaded( moduleName, moduleClass ) {}
	 * 						this 는 ModuleCache 로 셋된다.
	 * @param cbError		모듈 로딩이 실패된 경우 호출되는 콜백 함수.
	 */
	ModuleCache.prototype.loadModule = function(moduleName, modulePath, cbLoaded, cbError)
	{
		var		self = this;
		var		moduleIt = new Array( this._moduleRoot + modulePath );
		
		require
		(
			moduleIt
			,
			function( moduleClass )
			{
				self._moduleHolder[ moduleName ] = moduleClass;
				
				if( cbLoaded )
				{
					cbLoaded.call( self, moduleName, moduleClass );
				}
			},
			function(err)
			{
				console.log(err);
				
				if( cbError )
				{
					cbError.call( self, err );
				}
			}
		);
	};
	
	/**
	 * 패널 모듈 객체를 생성한다.
	 * 
	 * @param moduleName	생성을 원하는 패널 이름.
	 * @param options		패널 생성시 전달되는 파라메터. 필요시 지정한다.
	 * @returns
	 * 			생성 성공시 생성된 패널 객체.
	 * 			지정된 패널이 존재하지 않는경우 null.
	 */
	ModuleCache.prototype.createInstance = function(moduleName, options)
	{
		if( !this._moduleHolder[ moduleName ] )
		{
			return null;
		}
		
		return new this._moduleHolder[ moduleName ]( options ); 
	};
	
	/////////////////////////////////////////////////////////////////////////
	//
	// AppProps
	//
	
	/**
	 * 어플리케이션의 속성값을 관리한다.
	 * 
	 * 1. AppProps.fetch() 로 저장소의 값을 읽을 수 있다.
	 * 2. AppProps.save() 로 설정된 속성값에 영속성을 제공한다.
	 * 3. AppProps.destroy() 로 영속성을 제거한다.
	 */
	var AppProps = Backbone.Model.extend(
	{
		_name: "freecore.spa.properties.holder",
		_options:
		{ 
			// day
			expires: 1,
			path: '/'
		},
		
		/**
		 * 모든 상태 값을 제거한다.
		 * 
		 * @param bPropagate		{ boolean } true 면 "change" 이벤트가 발생된다.
		 */
		clear: function( bPropagate )
		{
			var option = {};
			
			if( typeof bPropagate == "boolean" && bPropagate == true )
			{
				option[ "silent" ] = true;
			}
			
			this.clear( option );
		},
		
		/**
		 * 쿠키를 저장소로 하는 영속성을 제공한다.
		 * 
		 * @param method
		 * @param model
		 * @param options
		 * @returns
		 */
		sync: function(method, model, options) 
		{
			if( method === 'read' )
			{
				// Model.fetch() 의 경우 호출됨.
				this._readProps();
			}
			else if( method === 'create' || method === 'update' )
			{
				// Model.save() 의 경우 호출됨.
				this._writeProps();
			}
			else if( method === 'delete' )
			{
				this._clearStorage();
			}
	    },
		
		/**
		 * 생성일로 부터 몇 일간 유효한지에 대한 정보를 지정한다.
		 * 
		 * @param day	유효일.
		 */
		setExpires: function(day)
		{
			this._options.expires = day;
		},
		
		/**
		 * 어플리케이션 상태 값을 설정한다.
		 */
		setProp: function(name,value)
		{
			var entry = {};
			
			entry[name] = value;
			
			this.set( entry );
		},
		
		/**
		 * 어플리케이션 상태 값을 얻는다.
		 */
		getProp: function(name)
		{
			return this.get( name );
		},
		
		/**
		 * 저장소를 청소한다.
		 */
		_clearStorage: function()
		{
			$.removeCookie( this._name );
		},
		
		/**
		 * AppProps 값을 쿠키에 적용한다.
		 */
		_writeProps: function()
		{
			var		rowData = $.toJSON( this.attributes );
			
			$.cookie( this._name, rowData, this._options  );
		},
		
		/**
		 * 쿠키에서 AppProps 값을 읽어온다. 
		 */
		_readProps: function()
		{
			var		rowData = $.cookie( this._name );
			
			if( !rowData )
			{
				return;
			}
			
			var		jsonData = $.evalJSON( rowData );
			
			for(var name in jsonData)
			{
				this.setProp( name, jsonData[name] );
			}
		}
	});
	
	///////////////////////////////////////////////////////////////////////////
	//
	// UCMSPA
	//
	
	/**
	 * UCMS SPA(Single Page Application) 객체 생성
	 */
	var UCMSPA = {};
	
	/**
	 * 어플리케이션의 시작점을 제공한다.
	 */
	UCMSPA.AppMain = Backbone.Marionette.Application.extend(
	{
		_params: null,
		_route: null,
		
		/**
		 * 어플리케이션 객체를 생성한다.
		 */
		constructor: function()
		{
			Marionette.Application.prototype.constructor.apply(this, arguments);
			
			var self = this;
			
			// URL 파라메터를 취한다.
			this._params = UCMSPlatform.getUrlParams();
			
			// 저장된 앱 전역 프로퍼티 값을 활성화한다.
			UCMSPA.AppMain.getAppProps().fetch();

			// 어플리케이션 골격을 초기화하도록 하는 초기화 메소드 호출
			// v2.3.0 부터 deprecated
			this.addInitializer(function( options )
			{
				// TODO 하위 버전과 호환성을 위해서 아래의 동작을 onBeforeStart() 로 옮김
				//self._initRoute( options );
				//self._initUI( options );
				
				// TODO Marionette-2.4.4.js 을 적용하기 전까지는 수동으로 onBeforeStart() 를 호출한다.
				// TODO Marionette-2.4.4.js 가 적용되면, start() 호출시 onBeforeStart(), onStart() 의 순서로 이벤트가 발생된다.
				//UCMSPA.AppMain.prototype.onBeforeStart.call( self, options );
				
				self.onBeforeStart( options );
				
				UCMSPlatform.log("\n==================\nInitialize the Application Module!\n==================\n");
			});

			UCMSPlatform.log("Constructed the Application!");
		},
		
		initialize: function(options)
		{
			UCMSPlatform.log("Initialized the Application!");
		},
		
		/**
		 * 어플리케이션의 this._route 를 설정한다.
		 * 생성자에서 호출되도록 설정된다.
		 */
		_initRoute: function( options )
		{
			UCMSPlatform.error("Need to override this method for your Application!");
		},
		
		/**
		 * 어플리케이션의 Region 을 설정한다.
		 * 생성자에서 호출되도록 설정된다.
		 */
		_initUI: function( options )
		{
			UCMSPlatform.error("Need to override this method for your Application!");
		},
		
		/**
		 * onStart() 전 필요한 초기화 동작을 수행한다.
		 */
		onBeforeStart: function(options)
		{
			UCMSPlatform.log("UCMSPA.AppMain.onBeforeStart()");

			// init
			this._initRoute( options );
			this._initUI( options );
		},
		
		onStart: function(options)
		{
			UCMSPlatform.log("UCMSPA.AppMain.onStart()");
			
			Backbone.history.start();
		},
		
		/**
		 * SPA 의 Backbone.Router 인스턴스를 얻는다.
		 */
		getAppRouter: function()
		{
			return this._route;
		}
	},
	{
		_logger: Debug,
		//_subModule: new ModuleCache(),
		
		/**
		 * 어플리케이션의 공통 상태를 보관하는 모델.
		 */
		_appProps: new AppProps(),
		
		initResource: function(html, selector)
		{
			return UCMS.initResource( html, selector );
		},

		initStyle: function(stylePath)
		{
			return UCMS.initStyle( stylePath );
		},

		initScript: function(scriptPath)
		{ 
			return UCMS.initScript( scriptPath );
		},
		
		/**
		 * 어플리케이션 상태 객체를 얻느다.
		 */
		getAppProps: function()
		{
			return this._appProps;
		}
	});
	
	/**
	 * 위젯 유형에 관계 없이 공통으로 사용 가능한 메소드를 정의한다.
	 */
	var CommonMethod = 
	{
		/**
		 * 본 위젯의 최상위 원소에 대한 jquery 객체를 얻는다.
		 * @returns {$}  
		 */
		getWidget$Element: function()
		{
			return this.$el;
		}
		,
		/**
		 * 위젯 영역 내에 로딩바를 추가한다.
		 */
		showLoading: function()
		{
			return UCMS.showLoading(this.$el);
		}
		,
		/**
		 * 위젯 영역 내에 추가된 로딩바를 제거한다.
		 */
		hideLoading: function()
		{
			UCMS.hideLoading(this.$el);
		}
	};
	
	// 이벤트 핸들러 정의.
	var EventHandler = {};
	
	//
	// 공통 이벤트 핸들러 정의
	//
	EventHandler.common =
	{
		onBeforeRender: function()
		{
		},
		
		onRender: function()
		{
		},
		
		onBeforeShow: function()
		{
		},
		
		onShow: function()
		{
		},
		
		onDomRefresh: function()
		{
		},
		
		onBeforeClose: function()
		{
		},
		
		onClose: function()
		{
		}
	};

	//
	// Item 에 대한 이벤트 핸들러 정의
	//
	EventHandler.item =
	{
		onBeforeItemAdded: function()
		{
		},
		
		onAfterItemAdded: function()
		{
		},
		
		onItemBeforeRender: function()
		{
		},
		
		onItemRendered: function()
		{
		},
		
		onItemBeforeClose: function()
		{
		},
		
		onItemClosed: function()
		{
		}
	};
	
	/**
	 * Panel의 베이스 클래스.
	 * 복잡한 구조의 패널 구조를 표현할 수 있도록 Marionette.Layout 을 상속받는다.
	 */
	UCMSPA.Panel = Backbone.Marionette.Layout.extend(
	{
		/**
		 * Panel 초기화를 진행한다.
		 */
		initialize: function()
		{
			UCMSPlatform.log("Initializing a UCMSPA.Panel!");
		}
	});
	
	// 공통 메소드 추가
	_.extend( UCMSPA.Panel.prototype, CommonMethod );
	
	// Event 핸들러 추가
	_.extend( UCMSPA.Panel.prototype, EventHandler.common );
	_.extend( UCMSPA.Panel.prototype, EventHandler.item );
	
	/**
	 *  Component.
	 *  Panel 과 Widget 에 의해 사용된다.
	 */
	UCMSPA.Component = Backbone.Marionette.ItemView.extend(
	{
		initialize: function()
		{
			UCMSPlatform.log("Initializing a UCMSPA.Component!");
		}
	});
	
	_.extend( UCMSPA.Component.prototype, CommonMethod );
	_.extend( UCMSPA.Component.prototype, EventHandler.common );
	_.extend( UCMSPA.Component.prototype, EventHandler.item );
	
	//
	// Collection 을 위한 이벤트 핸들러 정의.
	//
	EventHandler.collection =
	{
		onCollectionBeforeRender: function()
		{
		},
		
		onCollectionRendered: function()
		{
		},
		
		onCollectionBeforeClose: function()
		{
		},
		
		onCollectionClosed: function()
		{
			UCMSPlatform.log("onCollectionClosed()");
		}
	};
	
	//
	// Composite 를 위한 이벤트 핸들러 정의.
	//
	EventHandler.composite =
	{
		onCompositeModelRender: function()
		{
		},
		
		onCompositeRendered: function()
		{
		},
		
		onCompositeCollectionBeforeRender: function()
		{
		},
		
		onCompositeCollectionRendered: function()
		{
		}
	};
	
	/**
	 * 위젯의 베이스 클래스.
	 */
	UCMSPA.Widget = Backbone.Marionette.CompositeView.extend(
	{
		initialize: function()
		{
			UCMSPlatform.log("Initializing a UCMSPA.Widget!");
		}
	});
	
	_.extend( UCMSPA.Widget.prototype, CommonMethod );
	_.extend( UCMSPA.Widget.prototype, EventHandler.common );
	_.extend( UCMSPA.Widget.prototype, EventHandler.item );
	_.extend( UCMSPA.Widget.prototype, EventHandler.collection );
	_.extend( UCMSPA.Widget.prototype, EventHandler.composite );
	
	/**
	 * 도움 함수를 정의한다.
	 */
	var helperFunc =
	{
		isArray: function(it)
		{
			return Object.prototype.toString.call( it ) === '[object Array]';
		},
		
		isFunction: function(it)
		{
			return Object.prototype.toString.call( it ) === '[object Function]';
		},
		
		isTablet: function()
		{
			return this.isIPad();
		},
		
		isAndroid: function()
		{
			return ( navigator.userAgent.toLowerCase().indexOf("android") > -1 );
		},
		
		isIPod: function()
		{
			return ( navigator.platform.indexOf("iPod") > -1 );
		},
		
		isIPhone: function()
		{
			return ( navigator.platform.indexOf("iPhone") > -1 );
		},
		
		isIPad: function()
		{
			return ( navigator.platform.toLowerCase().indexOf("ipad") > -1 );
		},
		
		isDesktop: function()
		{
			return ( this.isTablet() == false && this.isAndroid() == false && this.isIPhone() == false && this.isIPod() == false && this.isIPad() == false );
		},
		
		/**
		 * appOS API 를 사용 가능한 모드인가?
		 * 
		 * @return { Boolean } true : appOS 기반으로 구동 중일때, false : 일반 웹 브라우저 기반으로 구동 중일때.
		 */
		isAppOS: function()
		{
			var bHybrid;
			
			try
			{
				Ti.App.fireEvent("check:hybrid:mode");
				
				//
				bHybrid = true;
			}
			catch(e)
			{
				bHybrid = false;
			}
			
			return bHybrid;
		}
	};
	
	_.extend( UCMSPA, helperFunc );
	
	return UCMSPA;
});