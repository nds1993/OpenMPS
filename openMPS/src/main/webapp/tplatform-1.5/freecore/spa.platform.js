/**
 * Project : T-PlatformJS
 *
 * Copyright (c) 2017. FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

define("TPlatform",[],
function()
{
	"use strict";
		
	// 앱 리소스 빌더 적용 버전
	var spa_platform_ver = "1.5.0";
	var	dummyConsole = 
	{
		log: function(){},
		info: function(){},
		debug: function(){},
		warn: function(){},
		error: function(){},
		assert: function(){}
	};
	
	/**
	 * UCMS 플랫폼 객체.
	 * 
	 * @param moduleRootPath	{ String } 종속 모듈의 최상위 경로. null 인 경우 "/"가 지정된다. 
	 * @param debug 			{ Boolean } 디버그 모드 플레그. true / false
	 * @param cbMain			{ Function } 플랫폼 메인 콜백 함수. function() {}
	 * 							제공되지 않으면 내부의 기본 메인함수가 수행되며, 기본 어플리케이션이 자동으로 로딩되어 수행을 시작한다.
	 * 							사용자 정의 어플리케이션을 수행하는 방법은 2 가지가 있다.
	 * 
	 * 							1. 서버의 경로 /ucms/app.js 에 어플리케이션을 배치시키는 방법.
	 * 							2. 사용자 정의 메인 함수를 제공하여 어플리케이션을 구동시키는 방법.
	 */
	function UCMSPlatform(moduleRootPath, debug, cbMain)
	{
		if( UCMSPlatform._APP_ )
		{
			throw new Error("Already created the application as SPA of UCMS!!");
		}
		
		//
		UCMSPlatform._VER_ = spa_platform_ver;
		UCMSPlatform.isCompatible = function(ver)
		{
			// TODO 같음이 아니라 호환이 되는지 검사하는 코드로 바뀌어야 한다.
			return (spa_platform_ver == ver);
		}
		
		// startAplication() 에서 초기화된다.
		UCMSPlatform._APP_ = null;
		
		// SPA 모듈의 최상위 경로를 저장한다.
		// 본 경로를 기준으로 상대경로로 지정된 모듈을 검색한다. 
		UCMSPlatform._moduleRootPath = moduleRootPath;
		
		//
		try
		{
			UCMSPlatform._logger = ( typeof console === "undefined" ? dummyConsole : typeof console.debug === "undefined" ? dummyConsole : console );
		}
		catch(e)
		{
			UCMSPlatform._logger = dummyConsole;
		}
		
		if( typeof moduleRootPath != "string" )
		{
			// v1.2.7 이전 버전의 파라메터를 보정한다.
			cbMain = debug;
			debug = moduleRootPath;
			moduleRootPath = "/";
		}
		
		UCMSPlatform._isDebug = debug || false;
		
		UCMSPlatform.log("SPA Platform SDK Ver : v"+UCMSPlatform._VER_);
		UCMSPlatform.log("Module Root Path : "+moduleRootPath);
		
		// 플랫폼 초기화시 호출되는 기본 Main 함수.
		// 기본 앱을 로딩하고 시작시킨다.
		var	innerMain = function()
		{
			UCMSPlatform.startApplication
			(
				"/ucms/app.js", 
				{
					bodyTag: "body"
				}
				,
				null
				,
				function(err)
				{
					if( err )
					{
						alert("웹 페이지 로딩에 실패하였습니다. 인터넷 연결을 확인해 주세요.");
					}
				}
			);			
		};
		
		//
		UCMSPlatform.SPA = null;
		
		if( UCMSPlatform._isDebug == false )
		{
			// Release Mode
			UCMSPlatform._initDependency
			(
				moduleRootPath, 
				"minimal", 
				cbMain || innerMain
			);
		}
		else
		{
			// Debugging Mode
			UCMSPlatform._initDependency
			(
				moduleRootPath,
				null, 
				cbMain || innerMain
			);	
		}
	};
	
	//
	// Debugging Static Method
	//
	
	UCMSPlatform.log = function()
	{
		UCMSPlatform._logger.log( arguments[0] );
	};
	
	UCMSPlatform.info = function()
	{
		UCMSPlatform._logger.info( arguments[0] );
	};
	
	UCMSPlatform.debug = function()
	{
		UCMSPlatform._logger.debug( arguments[0] );
	};
	
	UCMSPlatform.warn = function()
	{
		UCMSPlatform._logger.warn( arguments[0] );
	};
	
	UCMSPlatform.error = function()
	{
		UCMSPlatform._logger.error( arguments[0] );
	};
	
	UCMSPlatform.assert = function()
	{
		UCMSPlatform._logger.assert( arguments[0] );
	};
	
	/**
	 * UCMS 플랫폼 동작을 위해 필요로 하는 라이브러리 로딩을 위한 환경설정을 진행한다.
	 * 
	 * @param moduleRootPath	{ String } 종속 모듈의 최상위 경로
	 * @param minimal			{ String } 운영 및 개발 환경을 선택하는 옵션.
	 * 							문자열 "minimal"을 제공하면, 최소화된 라이브러리 코드가 로드되며, 지정하지 않거나 그 외의 값을 지정하면 읽기 가능한 라이브러리 코드가 로드된다.
	 */
	UCMSPlatform._initRequireConfig = function(moduleRootPath, minimal)
	{
		var library =
		{
			root: moduleRootPath || "/",
				
			path: 
			{
				"text"				: "lib/text",
		    	"i18n"				: "lib/i18n",
		    	//
		    	"manifest"			: "lib/freecore/spa.manifest",
		    	"spa_debug"			: "lib/freecore/spa.debug",
		    	"spa_baseclasses"	: "lib/freecore/spa.baseclasses",
		    	"spa_facebook"		: "lib/freecore/spa.fb.plugin",
		    	"spa_analytics"		: "lib/freecore/spa.analytics",
		    	"spa_modfactory"	: "lib/freecore/spa.modfactory",
		    	"spa_api"			: "lib/freecore/spa.api",
		    	"spa_util"			: "lib/freecore/spa.util",
		    	//
		    	"Logger"			: "lib/freecore/spa.logger",
		    	"AResultClasses"	: "lib/freecore/spa.result",
				"AsynCmdClasses"	: "lib/freecore/spa.asyncmd",
				"BaroCommander"		: "lib/freecore/spa.commander",
				"osapi"				: "lib/freecore/spa.osapi",
				"MemProps" 			: "lib/freecore/spa.props",
				//
				// Social SDK
				//
				'Facebook'			: 'http://connect.facebook.net/en_US/sdk',
				'Kakao'				: 'http://developers.kakao.com/sdk/js/kakao.min',
				'Twitter'			: 'http://platform.twitter.com/widgets',
				//
				// v1.4.x 부터 사용되는 common 모듈
				//
				"BaroAppSkeleton"	: "lib/freecore/common/baro.skeleton.html",
				"BaroAppHolder"		: "lib/freecore/common/baro.appholder",
				"BaroBox"			: "lib/freecore/common/baro.box",
				"BaroBoxTab"		: "lib/freecore/common/baro.box.tab",
				"BaroAppFrame"		: "lib/freecore/common/baro.appframe",
				//
				"BaroAppBase"		: "lib/freecore/common/baro.appbase",
				"BaroPanelBase"		: "lib/freecore/common/baro.panelbase",
				"BaroProps"			: "lib/freecore/common/baro.props",
				"BaroFloating"		: "lib/freecore/common/baro.floating",
				//
				"PlugInManager"		: "lib/freecore/common/baro.plugin",
				"NLSManager"		: "lib/freecore/common/baro.nls",
				//
				"ClientBase"		: "lib/freecore/common/client.base",
				"UniMeClient"		: "lib/freecore/common/client.unime",
				"BaroappClient"		: "lib/freecore/common/client.baroapp",
				"SocialConnect"		: "lib/freecore/common/client.social",
				"AuthClient"		: "lib/freecore/common/client.auth",
				"TourDBClient"		: "lib/freecore/common/client.tourdb",
				"AppRecipeClient"	: "lib/freecore/common/client.recipe",
				"FormClient"		: "lib/freecore/common/client.form",
				//
				"RowBox"			: "lib/freecore/common/row.box",
				//
				"FormBox"			: "lib/freecore/common/form.box",
				"FormItemPanel" 	: "lib/freecore/common/form.itempanel",
				//
				"NaverMap"			: "lib/freecore/common/map.naver",
				"DaumMap"			: "lib/freecore/common/map.daum"
			},
			
			path_min:
			{
				"text"				: "http://resource.moven.net/lib/text",
		    	"i18n"				: "http://resource.moven.net/lib/i18n",
		    	//
		    	'Facebook'			: 'http://connect.facebook.net/en_US/sdk',
		    	'Kakao'				: 'http://developers.kakao.com/sdk/js/kakao.min',
		    	'Twitter'			: 'http://platform.twitter.com/widgets'
			}
		};
		var userLocale = navigator.language;
		
		require["config"](
		{
			"config":
		    {
			    "text":
			    {
			    	"useXhr": function (url, protocol, hostname, port)
			    	{
			    		// 개발환경에서는 CORS 환경에서 테스트가 진행된다.
			    		UCMSPlatform.debug("[text.js] allows CORS - url : "+url);
			    		return true;
			    	}
					,
					i18n: 
					{
			            locale: userLocale
			        }
			    }
		    },
		    "baseUrl": library.root,
		    "paths": minimal === "minimal" ? library.path_min : library.path,
		    "waitSeconds": 30,
		    "shim":
		    {
		    	'Facebook' :
		    	{
		    		exports: 'FB'
		    	}
		    	,
		    	'Kakao' :
		    	{
		    		exports: 'Kakao'
		    	}
		    	,
		    	'Twitter' :
		    	{
		    		exports: 'twttr'
		    	}
		    }
		});
	};
	
	/**
	 * 위젯 시스템 동작을 위해 공통적으로 필요로하는 라이브러리를 초기화한다. 
	 * 로딩된 UCMS SPA 는 window.UCMSPA 에 셋된다.
	 * 
	 * @param moduleRootPath	{ String } 종속 모듈의 최상위 경로
	 * @param minimal			{ String } 운영 및 개발 환경을 선택하는 옵션.
	 * 							문자열 "minimal"을 제공하면, 최소화된 라이브러리 코드가 로드되며,
	 * 							지정하지 않거나 그 외의 값을 지정하면 읽기 가능한 라이브러리 코드가 로드된다. 
	 * @param cbMain			{ Function } 라이브러리 로딩이 완료되면 호출되는 콜백 메인 함수. 필수 항목이다.
	 */
	UCMSPlatform._initDependency = function(moduleRootPath, minimal, cbMain)
	{
		this._initRequireConfig(moduleRootPath, minimal);
		
		require(
		[
		 	"spa_util", "spa_debug", "spa_baseclasses", "spa_api", "NLSManager"
		]
		, 
		function(spaUtil, spaDebugger, spaBaseClasses, spa_api, NLSManager)
		{
			spaDebugger.setDebugMode( UCMSPlatform._isDebug );
		
			UCMSPlatform._logger = spaDebugger.getConsole();
			UCMSPlatform.SPA = spaBaseClasses;
			UCMSPlatform.API = spa_api;
			
			(function (a) 
			{
				UCMSPlatform._logger.log("Param : "+a);
				UCMSPlatform._logger.log("Agent : "+navigator.userAgent);
				UCMSPlatform._logger.log("Vendor : "+navigator.vendor);
				UCMSPlatform._logger.log("Opera : "+window.opera);
			}
			)(navigator.userAgent || navigator.vendor || window.opera);
			
			if( UCMSPlatform.SPA.isFunction( cbMain ) )
			{
				cbMain.call();
			}
			else
			{
				throw new Error("Need to provide a valid callback function!");
			}
		});
	};
	
	/**
	 * 종속성된 모듈을 추가한다.
	 * 
	 * @param modules		{ JSON } 사용되는 모듈 경로
	 * @param baseUrl		{ String } 모듈의 Root 경로. 상대경로 or 절대경로를 지정한다.
	 * @param waitSeconds	{ Number } 로딩 타임아웃 초. 시스템 기본값 7 초
	 */
	UCMSPlatform._addDependency = function(modules, baseUrl, waitSeconds)
	{
		var reqParams =
		{
			"baseUrl": baseUrl || "/",
			"paths": modules,
			"waitSeconds": waitSeconds || 7
		};
	
		require["config"]( reqParams );
	};
	
	/**
	 * 1.2.2 부터는 Underbar 가 붙지 않은 함수를 사용한다.
	 */
	UCMSPlatform.addDependency = UCMSPlatform._addDependency; 
	
	/**
	 * UCMS 플랫폼 페이지 경로를 구한다.
	 * @returns		플랫폼 경로. 예) "/example/spa.example/" 
	 */
	UCMSPlatform.getPlatformPath = function()
	{
		UCMSPlatform.info( "Protocol : "+window.location.protocol );
		UCMSPlatform.info( "Host : "+window.location.host );
		UCMSPlatform.info( "Path : "+window.location.pathname  );
		UCMSPlatform.info( "href : "+window.location.href);
		
		var		url = window.location.pathname;
		
		if( url.charAt( url.length - 1 ) != '/' )
		{
			url = url.substr( 0, url.lastIndexOf("/")+1 );
		}
		
		UCMSPlatform.info( "Platform Path : "+url);
		
		return url;
	};
	
	UCMSPlatform.getRootPath = function()
	{
		UCMSPlatform.log("getRootPath() - UCMS Module Root Path : " + UCMSPlatform._moduleRootPath);
		return UCMSPlatform._moduleRootPath;
	};
	
	/**
	 * 어플리케이션을 실행시킨다.
	 * 
	 * @param modulePath	인덱스 페이지 경로를 기준으로 하는 모듈 상대 경로.
	 * @param options		어플리케이션 시작시 제공되는 파라메터.
	 * @param moduleName	로딩된 모듈이 패키지인 경우 AppMain 모듈의 이름을 지정한다. 필요없는 경우 undefined.
	 * @param cbResult		처리 결과를 반환한다. 오류가 발생한 경우 파라메터로 오류 객체가 제공된다.
	 * 						function(err){}
	 * @return	{ $.Promise }
	 */
	UCMSPlatform["startApplication"] = function( modulePath, options, moduleName, cbResult )
	{
		var		appPath = null;
		var		appParams = options;
		var		runnerFn = function(app, params)
		{
			if( typeof app["then"] === "function" )
			{
				//
				// 동적 앱 모듈
				//
				
				UCMSPlatform.debug("Dynamic App Module!");
				
				app.then(function(appMain)
				{
					UCMSPlatform.debug("Create a Application of UCMS.");
					var ucmsApp = new appMain( params );
					
					UCMSPlatform._APP_ = ucmsApp;
					
					UCMSPlatform.debug("Start a Application of UCMS.");
					ucmsApp.start( params );
					
					if( typeof cbResult != "undefined" )
					{
						UCMSPlatform.debug("complete to start a Application of UCMS.");
						cbResult();
					}
				});
			}
			else
			{
				//
				// 정적 앱 모듈
				//
				
				UCMSPlatform.debug("Static App Module!");
				
				if( typeof app["start"] == "function" )
				{
					UCMSPlatform._APP_ = app;
					
					UCMSPlatform.debug("Start a Application of UCMS.");
					app.start( params );
				}
				else
				{
					UCMSPlatform.debug("Create a Application of UCMS.");
					var ucmsApp = new app( params );
					
					UCMSPlatform._APP_ = ucmsApp;
					
					UCMSPlatform.debug("Start a Application of UCMS.");
					ucmsApp.start( params );
				}
				
				if( typeof cbResult != "undefined" )
				{
					cbResult();
				}
			}
		};
		var		errorFn = function(err)
		{
			UCMSPlatform.error(err);
			
			if( typeof cbResult != "undefined" )
			{
				cbResult(err);
			}
		};
		
		if( modulePath instanceof Array )
		{
			appPath = modulePath;
		}
		else if( modulePath.indexOf("http://") >= 0 )
		{
			appPath = [ modulePath ];
		}
		else if( modulePath.indexOf("/") == 0 )
		{
			appPath = [ modulePath ];
		}
		else
		{
			appPath = [ UCMSPlatform.getPlatformPath() + modulePath ];
		}
		
		UCMSPlatform.debug("startApplication() - appPath : "+ appPath[0]);
	
		return this.loadModuleByPath
		(
			appPath, 
			function( module_app )
			{
				if( typeof moduleName == "string" )
				{
					require([ moduleName ]
					, function(app)
					{
						runnerFn( app, appParams );
					}
					, errorFn);
				}
				else
				{
					runnerFn( module_app, appParams );
				}
			},
			errorFn
		);
	};
	
	/**
	 * UCMSPlatform.startApplication() 에서 초기화된 Application 인스턴스를 얻는다.
	 */
	UCMSPlatform.getApplication = function()
	{
		return UCMSPlatform._APP_;
	};
	
	window["UCMSPlatform"] = UCMSPlatform;
	window["UCMS"] = UCMSPlatform;
	
	return window["UCMS"];
});

