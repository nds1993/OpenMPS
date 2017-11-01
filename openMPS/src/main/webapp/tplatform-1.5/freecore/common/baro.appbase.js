/**
 * Project : Trunk-Platform SPA Platform
 *
 * Copyright (c) 2016 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */
	
define
(
[ "BaroProps", "osapi", "AuthClient", "UniMeClient", "spa_analytics", "Logger", "spa_debug" ],
function(BaroProps, osapi, AuthClient, UniMeClient, GATracker, Logger, SPADebuger)
{
	var instanceMethod =
	{
		_auth: null,
		_unimeClient: null,
		_tracker: null,
		_appFrame: null,
		_bErrorHandling: true,
		
		constructor: function(options) 
		{
			options || (options={});
			SPADebuger.setDebugMode( options.debug );
			Logger.log("Mobile Oven Application constructor!!");
			
			BaroAppBase.__super__.constructor.apply(this, arguments);
		}
		,
		/**
		 * Marionette.Application.start() 가 호출 된 경우 발생됨.
		 * addInitializer() 로 제공되는 초기화 함수가 실행되기 전에 호출된다.
		 */
		onInitializeBefore: function(options)
		{
			Logger.debug("BaroAppBase.onInitializeBefore()");
		}
		,
		/**
		 * 기본적으로 제공되는 App Initializer 에서 호출된다.
		 * 앱 시작 전에 필요한 초기화를 진행한다.
		 */
		onBeforeStart: function(options)
		{
			Logger.debug("BaroAppBase.onBeforeStart()");
			BaroAppBase.__super__.onBeforeStart.call( this, options );
		}
		,
		/**
		 * Marionette.Application.start() 가 호출 된 경우 발생됨
		 * addInitializer() 로 제공되는 초기화 함수가 실행된 후에 호출된다.
		 */
		onInitializeAfter: function()
		{
			Logger.debug("BaroAppBase.onInitializeAfter()");
		}
		,
		onStart: function(options)
		{
			Logger.debug("BaroAppBase.onStart()");
			BaroAppBase.__super__.onStart.call( this, options );
		}
		,
		/**
		 * URL Parameter 로 제공되는 세션 파라메터를 취득한다.
		 */
		_pickSessionParameters: function()
		{
			var params = 
			{
				SP_N: this._params["sp-name"] || null,
				SP_K: this._params["sp-key"] || null
			};
			
			UCMS.debug("Session Parameters[SP_N] = "+params.SP_N);
			UCMS.debug("Session Parameters[SP_K] = "+params.SP_K);
			
			return params;
		}
		,
		/**
		 * 앱 인덱스 페이지에서 제공되는 파라메터를 분석하고, 앱 내에서 사용될 수 있도록 초기화한다.
		 */
		_initParameters: function(options)
		{
			UCMS.log('Parameters : ');
			UCMS.log('bodyTag : '+ JSON.stringify( options.bodyTag ));
			UCMS.log('hosts : '+ JSON.stringify( options.hosts ));
			if( options.actors )
			{
				UCMS.log('svc_id : '+ options.actors.svc_id);
				UCMS.log('con_id : '+ options.actors.con_id);
			}
			
			UCMS.debug('_initParameters() - start!');

			BaroProps.init( options );
			Logger.debug("_initParameters() - "+JSON.stringify( options ));
		}
		,
		/**
		 * 어플리케이션을 초기화한다.
		 * 
		 * @param options			{ Object } 앱 실행을 위해 필요한 파라메터 
		 * @param subModules		{ Object } 앱 실행시 사용되는 내부 모듈 정보. { 모듈 이름 : 모듈 경로 } 게 구성된 객체
		 * @param moduleRootPath	{ String } 내부 모듈이 배치된 경로
		 */
		initApplication: function(options, subModules, moduleRootPath)
		{
			UCMS.log('initApplication() - start!');
			
			if( typeof options.session_params == "undefined" )
			{
				// 호출자 측에서 제공되는 세션 파라메터가 없는 경우 URL 파라메터로 제공되는 파라메터 값을 취한다.
				// URL 파라메터 값이 없는 경우 null 값을 갖는다.
				options.session_params = this._pickSessionParameters();	
			}

			// 파라메터 초기화
			this._initParameters(options);

			var hosts = BaroProps.getHosts();
			
			//
			// UCMS Platform 을 확장하는 plugin 모듈 종속성 추가
			//
			UCMS.addDependency(
			{
		    	validator				: hosts.resource+"/plugin/validator",
		    	encrypt					: hosts.resource+"/plugin/encrypt",
		    	hashmap					: hosts.resource+"/plugin/hashmap",
		    	idangerous_swiper		: hosts.resource+"/plugin/idangerous.swiper-2.0.min"
			}
			, moduleRootPath);
			
			//
			// 바로앱의 비즈니스 로직을 구현한 모듈에 대한 종속성 추가
			// 바로앱 마다 다양한 모듈이 사용될 수 있다.
			//
			if( typeof subModules !== "undefined" )
			{
				var secRequireTimeout = 30;
				UCMS.addDependency( subModules, moduleRootPath, secRequireTimeout );
			}
		
			//
			if( typeof options.skeleton != "undefined" )
			{
				this._initTheme( options.skeleton.theme_path || options.skeleton.theme.path );
			}
		}
		,
		/**
		 * 구글 Universal Analytics 를 초기화한다.
		 * 
		 * @param trackingId		{ String } 아날리틱스 프로퍼티 식별자
		 * @param appName			{ String } 트래킹 활동자 앱 이름.
		 * @param appVer			{ String } 트래킹 앱 버전. 선택사항
		 * @param trackerName		{ String } 트래커 이름. 영문만 입력 가능. 선택사항.
		 * @return { $.Promise } 초기화 결과를 반환한다.
		 */
		_initAnalytics: function(trackingId, appName, appVer, trackerName)
		{
			var self = this;
			var user = BaroProps.getUser() || {};
			
			Logger.debug("_initAnalytics() - "+trackingId+", "+appName);
			
			if(typeof trackerName == "string" && "/^[A-za-z]/g".test(trackerName) == false)
			{
				Logger.error("_initAnalytics() - Tracker name should only consist of alphanumeric characters.");
			}
			
			return GATracker.init().then(function()
			{
				self._tracker = new GATracker();
				return self._tracker.create(trackingId, trackerName);
			})
			.then(function()
			{
				self._tracker.setAppInfo(appName, appVer);
				if(typeof user.id == "string")
				{
					self._tracker.setUserId( user.id );
				}
				
				Logger.debug("_initAnalytics() - Complete to initialize the Google Analytics.");
			});
		}
		,
		/**
		 * 백엔드 API 연동을 위한 세션을 초기화한다.
		 * initApplication() 수행 이후에 호출한다.
		 * 
		 * 바로앱 런처로 부터 취득한 세션이 존재하는 경우 추가적인 세션 생성과정 없이 종료된다.
		 * 
		 * @return { $.Promise } 세션 생성 프로세스의 종료 시점을 반환하는 Promise 객체
		 */
		initSession: function()
		{
			if(this._auth == null)
			{
				this._auth = new AuthClient();
			}
			else
			{
				this._auth.syncUser();
			}
			
			var sessionParams = this._auth.get("session");
			
			if( typeof sessionParams.SP_N == 'string' && typeof sessionParams.SP_K == 'string' )
			{
				var d = $.Deferred();
				Logger.info("initSession() - Already has a session.");
				d.resolve();
				return d.promise();
			}
			
			var self = this;
			
			return this._auth.initSession()
			.then(function(bSuccess)
			{
				if( bSuccess == true )
				{
					BaroProps.setUser( self._auth.get("user") );
					BaroProps.setSessionParams( self._auth.get("session") );
				}
			});
		}
		,
		/**
		 * UniMe API 호출을 위한 초기화를 진행한다.
		 * initSession() 처리 완료하고, alert 상태를 세팅한 후 호출한다.
		 * BaroProps.getAlertState() 를 최초 호출할 때는 푸시가 가능한 상태의 값을 반환한다.
		 * 플랫폼 토큰이 없는 경우에는 송출만 가능한 UniMe 모드로 초기화한다.
		 * 
		 * @return { $.Promise }
		 */
		initUniMeAgent: function()
		{
			var d = $.Deferred();
			var alertState = BaroProps.getAlertState();
			if( alertState.noti == 0 )
			{
				// noti 가 비활성화 상태인 경우 후속 처리를 진행하지 않는다.
				Logger.info("initUniMeAgent() - initialize pass.");
				d.resolve();
				return d.promise();
			}
			var platformId =
				(
					UCMSPlatform.SPA.isAndroid() ? 3 // GCM Mode
						: UCMSPlatform.SPA.isIPhone() || UCMSPlatform.SPA.isIPad() || UCMSPlatform.SPA.isIPod() ? 2	// APN Mode
							: 1 // UniMe Mode
				);			
			var platformToken = BaroProps.getAgentId();
			var self = this;
			
			// 세션이 생성된 후에 초기화 되어야 한다!
			if( this._unimeClient == null )
			{
				this._unimeClient = new UniMeClient();
			}
			else
			{
				// 현재의 사용자 정보를 동기화한다.
				this._unimeClient.syncUser();
			}

			Logger.debug("initUniMeAgent() - model : "+JSON.stringify(this._unimeClient.toJSON()));
			Logger.debug("initUniMeAgent() - platform Id : "+platformId+", token : "+platformToken);

			this._unimeClient.createToken(platformId, platformToken)
			.then(function(data, textStatus, jqXHR)
			{
				Logger.info("initUniMeAgent() - Complate to create a unime token : "+JSON.stringify(data));
				
				BaroProps.setUniMeToken( data.extraData );
			})
			.then(function()
			{
				return self._initBadge();
			})
			.then(function()
			{
				Logger.info("initUniMeAgent() - Complate to initialize a connection with the unime agent");
				d.resolve( BaroProps.getUniMeToken() );
			})
			.fail(function(jqXHR, textStatus, errorThrown)
			{
				Logger.error("initUniMeAgent() - Failed to create a unime token by error : "+textStatus);
				d.reject(jqXHR);
			});

			return d.promise();
		}
		,
		/**
		 * 신규 메시지 개수를 조회한다.
		 * 
		 * @return { $.Promise }
		 */
		_initBadge: function()
		{
			var self = this;

			this._unimeClient.syncUser();
			Logger.info("_initBadge() - Current User : "+this._unimeClient.get("user").id);
			// TODO 사용자가 바뀐경우 화면 전환시 항상 UniMe Token 이 다시 설정된다.
			// 그러므로 _unimeClient 에는 언제나 최신 사용자 정보가 설정한다.
			return this._unimeClient.getUnreadCount()
			.then(
					function()
					{
						var cnt = self._unimeClient.get("unread");
						Logger.info("initUniMeAgent() - Unread Message Count : "+cnt);
						BaroProps.setUnreadPushMsg(cnt);
					});
		}
		,
		/**
		 * 위젯에서 UniMe Client 가 필요할 때 호출한다.
		 * 위젯에서 UniMeClient 인스턴스를 생성해서 사용해도 되지만,
		 * 향 후 공유가 필요해지는 상황이 발생할 수도 있어서 앱 생성시 초기화된 UniMe Client 객체를 공유할 수 있는 방법을 마련함.
		 */
		getUniMeClient : function()
		{
			return this._unimeClient;
		}
		,
		/**
		 * 지정한 패널을 앱의 전면 프레임으로 셋한다.
		 */
		_showFrame: function(framePanel)
		{
			this.body.show( framePanel );
		}
		,
		/**
		 * 지정한 서브 모듈을 로딩하여 전면화 프레임으로 셋한다.
		 * 
		 * @param moduleName	{ String } 동적 로딩이 필요한 모듈명. 모듈 종속성 목록에서 제공하는 모듈의 이름을 지정한다.
		 * @param params		{ Object } 로딩된 모듈이 활성화시 제공되는 파라메터를 지정한다. 필요없는 경우 undefined.
		 */
		_moduleLoading : function(moduleName, params)
		{
			var self = this;
			
			require([ moduleName ], function(klass)
			{
				var panel = new klass(params || {});
				self._showFrame( panel );
			},
			function(err)
			{
				console.log(err);
			});
		}
		,
		/**
		 * 빵앱를 시작한다.
		 */
		startBaangapp : function()
		{
			var pageId = BaroProps.getStartPage();
			
			if( pageId != null )
			{
				Logger.info("startBaangapp() - Start Page : "+pageId);
				
				//
				Backbone.history.start({ silent: true });
				this._route.navigate(pageId, { trigger: true });
			}
			else
			{
				Logger.info("startBaangapp()");
				
				Backbone.history.start();		
			}
		}
		,
		/**
		 * 앱 CSS 를 초기화한다.
		 */
		_initTheme: function(theme_path)
		{
			UCMS.SPA.AppMain.initStyle( theme_path );
		},
		
		/**
		 * 앱 운영을 위한 기반을 초기화한다.
		 * UI 출력을 위한 골격과 이벤트 처리를 위한 구조가 생성된다.
		 * 초기화된 마스터 객체는 getAppFrame() 으로 얻을 수 있다.
		 * 
		 * @return { $.Promise }
		 */
		_initAppFrame: function(skeleton)
		{
			var self = this;
			
			return UCMS.loadModuleByPath
			(
				[ "BaroAppHolder" ], 
				function(BaroAppHolder)
				{
					self._appFrame = new BaroAppHolder(skeleton);
					self.body.show( self._appFrame );
				}
				,
				function(err)
				{
					Logger.error(err);
				}
			);
		},
		
		/**
		 * 앱 마스터 객체를 얻는다.
		 */
		getAppFrame: function()
		{
			return this._appFrame;
		},
		
		/**
		 * 앱 활동을 추적하는 Google Universal Analytics 객체를 얻는다.
		 */
		getAppTracker: function()
		{
			return this._tracker || { trackingView: function(){} };
		},
		
		_makeDiv: function(attrName, value)
		{
			return $("<div>").attr(attrName, value);
		}
		,
		/**
		 * 에레처리 모드 확인
		 * true 인 경우 onAjaxError() 가 사용된다.
		 * false 인 경우 별도의 에러처리 기능을 제공해야 한다.
		 */
		isErrorHandlingMode: function()
		{
			return this._bErrorHandling;
		}
		,
		/**
		 * 에러처리 플레그를 설정한다.
		 * @param {boolean} bHandling
		 */
		setErrorHandling: function(bHandling)
		{
			this._bErrorHandling = bHandling;
		}
		,
		/**
		 * AJAX 오류에 대한 사용자정의 처리를 구현한다.
		 * 별도의 에러 처리가 필요한 경우 자식 클래스에서 정의하고, 필히 true 를 반환한다.
		 * 
		 * @param jqXHR			jquery.ajax 객체
		 * @param textStatus	에러 상태 메시지.
		 * 						"notmodified", "nocontent", "error", "timeout", "abort", or "parsererror"
		 * @returns { Boolean } 에러 처리를 완료한 경우 true 반환.
		 * 						기본 핸들러가 처리되도록 하기 위해서는 false 반환.
		 */
		onAjaxError: function(jqXHR, textStatus)
		{
			var msg;
			
			switch( textStatus )
			{
			case "success":
				break;

			case "timeout":
				msg = "요청 대기시간이 초과되었습니다.<br>네트워크 상태가 불안전합니다.<br>잠시 후 다시 이용해 주세요.<br>감사합니다!";
				break;

			case "notmodified":
			case "nocontent":
			case "error":				
			case "abort":
			case "parseerror":
			default:
				msg = "[Status : "+textStatus+"] 서버와 통신 중 오류["+jqXHR.status+"]가 발생하였습니다.<br>잠시 후 다시 시도해주세요.<br>이용에 불편을 드려 죄송합니다!";
				break;
			}

			switch( jqXHR.status )
			{
			case 401:
				Logger.error("[digestError] Unauthorized access.");
				var user = BaroProps.getUser();
				
				if( user.id == null || user.id == "guest" )
				{
					//BaroAppBase.makeGuestSession();
					UCMS.alert("세션을 생성할 수 없습니다.<br>관리자에게 문의해 주세요.");
				}
				else
				{
					if( UCMS.SPA.isAppOS() == false )
					{
						UCMS.alert("세션을 생성해 주세요.")
						return;
					}
					
					UCMS.alert("세션이 만료되었습니다. 세션을 갱신합니다.")
					.then(function()
					{
						var cs = osapi.getModule("CoreService");
						
						UCMS.showLoading();
						
						cs.reset()
						.fail(function()
						{
							UCMS.alert("세션 갱신에 실패하였습니다.<br>서비스 이용에 불편을 드려 죄송합니다.<br>잠시 후 다시 이용해주세요.")
							.then(function()
							{
								cs.shutdown()
								.fail(function()
								{
									//
									// in iOS
									//
									UCMS.hideLoading();
									UCMS.alert("앱을 종료하고, 다시 실행 부탁드립니다.<br>감사합니다.");
								});
							});
						});
					});
				} // endif
				break;
				
			case 404:
				Logger.error("[digestError] File Not Found.");
				UCMS.alert("서버에서 요청한 리소스를 찾을 수 없습니다.<br>관리자에게 문의하세요!");
				break;
			
			default:
				Logger.error("[digestError] Error Code : "+jqXHR.status+", Message : "+jqXHR.responseText);
				UCMS.alert(msg);
			}
		}
		,
		/**
		 * appOS 동작중 SPAView.open() 처리 중에 발생된다.
		 * 바로앱이 화면에 보여지는 과정(fadeIn)이 완료되면 본 이벤트가 발생된다.
		 */
		onForegroundBaroapp: function()
		{
			Logger.debug("onForegroundBaroapp()");
		}
		,
		/**
		 * appOS 동작 중 SPAView.close() 의 fadeOut() 완료 후 발생된다.
		 */
		onBackgroundBaroapp: function()
		{
			Logger.debug("onBackgroundBaroapp()");
		}
		,
		/**
		 * appOS 동작 중 AppRenderer.stop() 동작 완료 후 발생된다.
		 * 즉, 바로앱이 close() 된 후 완전히 종료된 후 발생된다.
		 */
		onDestroyBaroapp: function()
		{
			Logger.debug("onDestroyBaroapp()");
		}
	};
	
	/**
	 * 빵앱에서 전역적으로 공유하는 함수
	 */
	var staticMethod =
	{
		/**
		 * 링크 클릭에 대한 이벤트 대행자를 설정한다.
		 * @param selector	클릭 이벤트 대행자를 설정할 선택자 
		 */
		hookingHyperLink : function(selector, tiEventName)
		{
			var openWebLink = function()
			{
				var theURL = this.getAttribute('href');
				
				Logger.debug("hookingHyperLink() - browsing web link : "+theURL);
				
				if( theURL == null )
				{
					return false;
				}

				try
				{
					if( theURL.indexOf("http") == 0 )
					{
						Ti.App.fireEvent(tiEventName, { url: theURL });
						return false;
					}
				}
				catch(e)
				{
					Logger.debug("hookingHyperLink() - Error : "+JSON.stringify(e));
					window.open(theURL, "_blank");
					
					return false;
				}
				
				return true;
			};
			
			$(selector).on("click", "a", openWebLink);
		}
		,
		/**
		 * 발생된 에러에 대한 적절한 후속 처리를 진행한다.
		 * BaroAppBase 에서 Ajax Error 핸들러를 제공하는 경우 본 에러처리 루틴은 사용되지 않는다.
		 * 
		 * @param jqXHR			{ jquery.jqXHR } 에러가 발생된 ajax XHR 객체
		 * @param textStatus	{ String } 다음 에러의 종류 중 하나의 값이 지정된다.
		 * 						timeout
		 * 						error
		 * 						abort
		 * 						parseerror
		 */
		digestError : function(jqXHR, textStatus)
		{
			if( typeof jqXHR != 'object' )
			{
				UCMS.alert("알 수 없는 오류가 발생했습니다.<br><dic class'desc'>"+ JSON.stringify(jqXHR) +"</dic>");
				return;
			}
			
			Logger.error("[digestError] Error status - "+jqXHR.status+", status text : "+jqXHR.statusText);
			//Logger.error("[digestError] Error Code : "+jqXHR.status+", Message : "+jqXHR.responseText);
			
			if( typeof jqXHR.status != 'number' )
			{
				UCMS.alert("알 수 없는 오류가 발생했습니다.<br><dic class'desc'>"+ JSON.stringify(jqXHR) +"</dic>");
				return;
			}
			
			var baroapp = UCMS.getApplication();
			if( baroapp.isErrorHandlingMode() == true )
			{
				baroapp.onAjaxError(jqXHR, textStatus);
			}
			else
			{
				// 바로앱 내에서 별도의 에러처리 모듈을 사용하는 경우
			}
		}
		,
		/**
		 * 바로앱 식별자를 얻는다.
		 */
		getBaroAppId : function()
		{
			var appInfo = BaroProps.getAppInfo();
			return appInfo.id || null;
		}
		,
		/**
		 * 사용자 프로파일을 가져온다.
		 * 
		 * @param cbResult	{ Function } 처리 결과를 반환하는 callback 함수
		 * @return { jqXHR }
		 */
		fetchUserProfile : function(cbResult)
        {
        	var	hosts = BaroProps.getHosts();
        	var sessionParams = BaroProps.getSessionParams();

        	Logger.debug("fetchUserProfile.session_params : "+JSON.stringify(sessionParams));
        	
            return $.ajax({
                type: 'GET',
        		url : hosts.oauth+"/profile",
                dataType: "json",
                cache: false,
                crossDomain: true, 
                headers:
                {
                	"sp-name" : sessionParams.SP_N,
                	"sp-key" : sessionParams.SP_K
                }
            	,
            	success: function (data)
            	{
            		Logger.debug("fetchUserProfile() - result : "+JSON.stringify(data));
            		
                    if(data && data.resultCode == 0)
                    {
                    	var user = BaroProps.getUser();
                    	BaroProps.setUser(
	                	{
	                		no: data.extraData.user.no,
	                		id: data.extraData.user.screenId,
	                		name: data.extraData.user.name,
	                		md5pwd: user.md5pwd
	                	});
                    	
                    	if( typeof cbResult != "undefined" )
                    	{
                    		cbResult(true);
                    	}
                 	}
                    else
                    {
                    	if( typeof cbResult != "undefined" )
                    	{
                    		cbResult(false);
                    	}
                 	}
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	if( typeof cbResult != "undefined" )
                	{
                		cbResult(false);
                	}
                }
            });
        }
		,
		/**
		 * 빵앱 회원인지 체크한다.
		 * 
		 * @return { $.Promise }
		 */
		checkBaangAppMember: function()
		{
			var d = $.Deferred();
        	var	hosts = BaroProps.getHosts();
        	var sessionParams = BaroProps.getSessionParams();
        	var user = BaroProps.getUser();
        	var appInfo = BaroProps.getAppInfo();
        	var path = hosts.oauth+"/member/"+user.id+"/"+appInfo.no;

        	Logger.info("checkBaangAppMember() - Path : "+path);

        	if( appInfo.no > 0 )
        	{
        		Logger.debug("sp-name=" + sessionParams.SP_N);
            	Logger.debug("sp-key=" + sessionParams.SP_K);
            	
                $.ajax(
                {
                    type: 'GET',
            		url : path,
                    dataType: "json",
                    cache: false,
                    crossDomain: true, 
                    headers:
                    {
                    	"sp-name" : sessionParams.SP_N,
                    	"sp-key" : sessionParams.SP_K
                    }
                	,
                	success: function (data)
                	{
                		Logger.debug("checkBaangAppMember() - "+JSON.stringify(data));
                		
                        if(data && data.resultCode == 0)
                        {
                        	BaroProps.setBaangMember( data.extraData );
                        	d.resolve( data.extraData );
                     	}
                        else
                        {
                        	BaroProps.setBaangMember( false );
                        	d.resolve( false );
                     	}
                    },
                    error: function(XHR, textStatus, errorThrown) 
                    {
                    	Logger.error("checkBaangAppMember() - Error : "+textStatus);
                    	
                    	d.resolve( false );
                    }
                });
        	}
        	else
        	{
        		setTimeout(function()
        		{
        			Logger.error("checkBaangAppMember() - Invalid a BaangApp Number!!");
        			
        			d.resolve( false );
        		}
        		, 1);
        	}
			
			return d.promise();
		}
		,
		/**
		 * 앱 정보를 조회한다.
		 * 
		 * @return { jqXHR }
		 */
		fetchAppInfo : function(app_id)
		{
			Logger.info("fetchAppInfo() - App ID : "+app_id);
			
        	var	hosts = BaroProps.getHosts();
        	var sessionParams = BaroProps.getSessionParams();
        	
        	Logger.debug("fetchAppInfo.session_params : "+JSON.stringify(sessionParams));
        	
            return $.ajax(
            {
                type: 'GET',
        		url : hosts.api+"/bbang/detail/"+app_id,
                dataType: "json",
                cache: false,
                crossDomain: true, 
                headers:
                {
                	"sp-name" : sessionParams.SP_N,
                	"sp-key" : sessionParams.SP_K
                }
            	,
            	success: function (data)
            	{
            		Logger.debug("fetchAppInfo() - result : "+JSON.stringify(data));
            		
                    if(data && data.resultCode == 0)
                    {
                    	var appInfo = BaroProps.getAppInfo();
                    	var result = data.extraData;
						
						appInfo.no = result.app_no;
						appInfo.owner = result.user;
						appInfo.pub_date = result.create_date;
						
						BaroProps.setAppInfo( appInfo );
                 	}
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("fetchAppInfo() - Error : "+textStatus);
                }
            });
		}
		,
		/**
		 * 사용자 계정과 연결된 소셜 서비스 정보를 얻는다.
		 * 
		 *  @return { jqXHR }
		 */
		fetchConnectedService : function()
		{
			var	hosts = BaroProps.getHosts();
			var actors = BaroProps.getActors();
			var sessionParams = BaroProps.getSessionParams();
			var api = hosts.oauth+'/'+actors.con_id+'/connected';
			
			Logger.info("API : "+api);
			Logger.debug("session_params : "+JSON.stringify(sessionParams));
			
			return $.ajax(
			{
                type: 'GET',
                url: api,
                dataType: "json",
                cache: false,
                crossDomain: true,
                headers:
                {
                	"sp-name" : sessionParams.SP_N,
                	"sp-key" : sessionParams.SP_K
                },
                success: function (data)
                {
                	Logger.debug("fetchConnectedService.Result : "+JSON.stringify(data));
	            	
	            	if( typeof data != "undefined" && data.resultCode == 0 )
	            	{
	            		BaroProps.setConnectedService( data.extraData );
	            	}
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("fetchConnectedService.Error : "+textStatus);
                }
            });
		}
		,
		/**
		 * 빵앱 유저의 알림 설정을 갱신한다.
		 * 
		 * @return { jqXHR }
		 */
		fetchAlertState : function()
		{
			var	hosts = BaroProps.getHosts();
			var actors = BaroProps.getActors();
			var user = BaroProps.getUser();
			var baang = BaroProps.getAppInfo();
			var sessionParams = BaroProps.getSessionParams();
			var api = hosts.unime+'/alert/'+actors.svc_id+'/'+actors.con_id+'/'+user.id+"/"+baang.no;
			
			Logger.info("API : "+api);
			Logger.debug("session_params : "+JSON.stringify(sessionParams));
			
			return $.ajax(
			{
                type: 'GET',
                url:  api,
                dataType: "json",
                cache: false,
                crossDomain: true,
                headers:
                {
                	"sp-name" : sessionParams.SP_N,
                	"sp-key" : sessionParams.SP_K
                },
                success: function (data)
                {
                	Logger.debug("fetchAlertState.Result : "+JSON.stringify(data));
	            	
	            	if( typeof data != "undefined" && data.resultCode == 0 )
	            	{
	            		BaroProps.setAlertStates( data.extraData );
	            	}
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("fetchAlertState.Error : "+textStatus);
                }
            });
		}
		,
		/**
		 * 빵앱 회원을 탈퇴한다.
		 * 
		 * @return { Promise } 탈퇴 결과가 반환된다. function(bResult){ }
		 */
		dropBaangMember: function()
		{
			var d = $.Deferred();
        	var	hosts = BaroProps.getHosts();
        	var sessionParams = BaroProps.getSessionParams();
        	var user = BaroProps.getUser();
        	var appInfo = BaroProps.getAppInfo();
        	var path = hosts.oauth+"/member/"+user.id+"/"+appInfo.no;

        	Logger.info("dropBaangMember() - Path : "+path);
        	Logger.debug("session_params : "+JSON.stringify(sessionParams));

        	if( appInfo.no > 0 )
        	{
                $.ajax(
                {
                    type: 'DELETE',
            		url : path,
                    dataType: "json",
                    cache: false,
                    crossDomain: true, 
                    headers:
                    {
                    	"sp-name" : sessionParams.SP_N,
                    	"sp-key" : sessionParams.SP_K
                    }
                	,
                	success: function (data)
                	{
                		Logger.debug("dropBaangMember() - "+JSON.stringify(data));
                		
                        if(data && data.resultCode == 0)
                        {
                        	BaroProps.setBaangMember( false );
                        	d.resolve( true );
                     	}
                        else
                        {
                        	d.resolve( false );
                     	}
                    },
                    error: function(XHR, textStatus, errorThrown) 
                    {
                    	Logger.error("dropBaangMember() - Error : "+textStatus);
                    	
                    	d.reject();
                    }
                });
        	}
        	else
        	{
        		setTimeout(function()
        		{
        			Logger.error("dropBaangMember() - Invalid a BaangApp Number!!");
        			
        			d.reject();
        		}
        		, 1);
        	}
			
			return d.promise();        			
		}
		,
		/**
		 * appOS 에 저장된 알림 플레그 설정을 로드한다.
		 * 로드된 설정값은 BaroProps 에 저장된다.
		 */
		loadLocalAlertState: function()
        {
        	var storage = osapi.getModule("Storage");
        	var setDefault = function()
        	{
    			BaroProps.setAlertState("noti", 1);
    			BaroProps.setAlertState("email", 0);
    			BaroProps.setAlertState("sms", 0);
    			
    			Logger.debug("loadLocalAlertState() - initialize default values."); 
        	};
        	
        	return storage.getAttr("alertState")
        	.then(function(result)
			{
        		Logger.debug("loadLocalAlertState() - result : "+JSON.stringify(result));
        		
        		var state = result.extraData;
        		if( state != null )
        		{
        			for(var method in state)
            		{
            			BaroProps.setAlertState(method, state[method]);	
            		}	
        		}
        		else
        		{
        			setDefault();
        		}
			})
			.fail(function()
			{
				setDefault();
			});
        }
		,
		/**
		 * 비콘 모니터 서비스를 초기화한다.
		 * 세션 초기화를 완료하고, 본 메소드를 호출한다.
		 *  
		 * @param {string} baro_id	바로앱 식별자
		 * @return {$.Promise}
		 */
		initBeaconMonitor: function(baro_id)
		{
			var cs = osapi.getModule(osapi.ID_MODULE.CORESERVICE);
			
			return cs.initService("beaconManager.monitor", baro_id, BaroProps.getSessionParams());
		}
		,
		/**
		 * 비콘 송출 서비스를 초기화한다.
		 * 세션 초기화를 완료하고, 본 메소드를 호출한다.
		 *  
		 * @param {string} baro_id	바로앱 식별자
		 * @return {$.Promise}
		 */
		initBeaconTransmitter: function(baro_id)
		{
			var cs = osapi.getModule(osapi.ID_MODULE.CORESERVICE);
			
			return cs.initService("beaconManager.transmitter", baro_id, BaroProps.getSessionParams());
		}
		,
		/**
		 * 앱을 다시 시작한다.
		 */
		restartApp: function()
		{
			if(UCMS.SPA.isAppOS() == true)
			{
				var cs = osapi.getModule(osapi.ID_MODULE.CORESERVICE);
				cs.reset();
			}
			else
			{
				//UCMS.reloadPage("#!reset");
				window.location.reload();
			}
		}
	};
	
	/**
	 * 빵앱의 Application 공통 코드를 구현한다.
	 * UCMS Platform v1.2.2 부터 적용 가능함.
	 */
	var BaroAppBase = UCMS.SPA.AppMain.extend( instanceMethod, staticMethod );
	
	//
	// AJAX 전역 핸들러
	//
	$.support.cors = true;
	$.ajaxSetup(
	{
		// 30 sec
		timeout : 40000,
		cache: false,
        crossDomain: true
        ,
        beforeSend: function(jqXHR, settings)
		{
			Logger.info("ajax.beforeSend - settings : "+JSON.stringify(settings));
		},
		complete: function(jqXHR, textStatus)
		{
			Logger.info("ajax.complete - status : "+textStatus);
			
			if( textStatus != "success" )
			{
				BaroAppBase.digestError( jqXHR, textStatus );
			}
		}
	});	

	return BaroAppBase;
});