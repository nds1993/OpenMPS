/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2014 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman 
 */

define(
[
	"BaroCommander", "Logger", "AResultClasses", "AuthClient", "BaroProps"
]
,
function(BaroCommander, Logger, AResult, AuthClient, BaroProps)
{
	/**
	 * appOS API 패키지의 공통 모듈을 구현한다.
	 */
	var apiModule = Backbone.Model.extend(
	{
		_cmd : null
		,
		_hybridMode : false
		,
		initialize: function()
		{
			apiModule.__super__.initialize.apply( this, arguments );
			
			this._cmd = BaroCommander.getCommander();
			this._checkHybridMode();
			
			Logger.debug("apiModule.initialize()");
		}
		,
		/**
		 * appOS 에서 발생되는 이벤트 처리자를 초기화한다.
		 * 각 api client 에서 처리가 필요한 이벤트를 초기화한다.
		 * @param module	{ apiModule } apiModule 을 상속받아 구현한 api client 모듈. 
		 * 					내부에 { 이벤트식별자:핸들러 } 의 형식으로 정의된 EVENT Static 속성을 이벤트 처리를 위해 등록한다. 
		 */
		activeEvent: function(module)
		{
			if( module == undefined || module.EVENT == undefined )
			{
				// 이벤트를 지원하지 않음
				return;
			}
			
			for(var eventId in module.EVENT)
			{
				var handler = module.EVENT[eventId];
				
				if( typeof handler == "function" )
				{
					this._cmd.addCmdHandler( eventId, handler );
				}
				else
				{
					Logger.warn("Invalid event handler. event : "+eventId);
				}
			}
		}
		,
		_checkHybridMode : function()
		{
			try
			{
				Ti.App.fireEvent("check:hybrid:mode");
				
				//
				this._hybridMode = true;
				
				Logger.info("Hybrid WebView Mode! Interact by titanium event method.");
			}
			catch(e)
			{
				this._hybridMode = false;
				
				//
				Logger.info("Native WebView Mode! Don't use titanium event method.");
			}
			
			return this._hybridMode;
		}
		,
		/**
		 * 명령어를 호추한다.
		 * 
		 * @param cmd		{ String } 호출 명령어
		 * @param params	{ Object } 명령어 파라메터 객체. 필요한 경우에만 지정.
		 * 
		 * @return { Promise }
		 */
		_call : function(cmd, params)
		{
			return this._cmd.PostCommand( cmd, params );
		}
		,
		_isMobile: function()
		{
			if( UCMS.SPA.isAndroid() == true || UCMS.SPA.isIPhone() == true || UCMS.SPA.isIPod() == true || UCMS.SPA.isIPad() == true )
			{
				return true;
			}
			
			Logger.info("apiModule._isMobile() - current platform is a DESKTOP!!");
			
			return false;
		}
		,
		_isAppOS: function()
		{
			return this._hybridMode;
		}
		,
		/**
		 * 처리된 결과를 Promise 메시지로 반환한다.
		 * @param result	{ AResult.ResultEx }
		 * @return { $.Promise }
		 */
		_delayedReturn: function(result)
		{
			var d = $.Deferred();
			
			if( result.resultCode >= 0 )
			{
				d.resolve( result );	
			}
			else
			{
				d.reject( result );
			}
			
			return d.promise();
		}
	});
	
	var CoreService = apiModule.extend(
	{
		initialize: function()
		{
			CoreService.__super__.initialize.apply( this, arguments );
			
			Logger.debug("CoreService.initialize()");
		}
		,
		reset : function()
		{
			return this._call( CoreService.CMD.RESET );
		}
		,
		shutdown : function()
		{
			return this._call( CoreService.CMD.EXIT );
		}
		,
		/**
		 * 외부 브라우저로 지정한 웹 페이지를 뷰잉한다.
		 */
		openURL : function(url, options)
		{
			if( this._isAppOS() )
			{
				return this._call( CoreService.CMD.OPENURL, { "url": url, "options": options } );
			}
			else
			{
				window.open(url, "_blank");
				return this._delayedReturn( AResult.Helper.SUCCESS().toJSON() );
			}
		}
		,
		/**
		 * 디바이스 식별자를 얻는다.
		 * 한번 생성된 식별자는 appOS 저장소에 보관되며, 저장소 클러어 전까지 변경되지 않는다.
		 */
		getDeviceUUID : function()
		{
			if( this._isAppOS() )
			{
				return this._call( CoreService.CMD.DEVICEUUID );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
		,
		/**
		 * 플랫폼 푸시 토큰을 얻는다.
		 * 값이 존재하지 않는 경우 null 을 반환한다.
		 */
		getPushToken : function()
		{
			if( this._isAppOS() )
			{
				return this._call( CoreService.CMD.PUSHTOKEN );
			}
			else
			{
				// TODO 반환값을 AResult 에 담아서 보내는 것이 맞으나, AResult 의 형식을 사용하지 않는 API 들이 많아서 표준화 시키지 못하고, 값만 반환한다.
				// TODO 오픈 이후에 os API 반환값의 표준화 작업을 진행해야한다.
				//return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
				var d = $.Deferred();

				d.resolve( null );
				
				return d.promise();
			}
		}
		,
		/**
		 * appOS 서비스를 시작한다.
		 * 
		 * @param serviceType		{ string }
		 * @param appId				{ string } 초기화하는 바로앱 식별자. 
		 * @param sessionParams		{ SP_N: #, SP_K: # } 바로앱이 보유하고 있는 세션 파라메터
		 */
		initService : function(serviceType, appId, sessionParams)
		{
			if( this._isAppOS() )
			{
				return this._call( CoreService.CMD.INITSERVICE, { "serviceType": serviceType, "app_id": appId, "session_params": sessionParams } );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
	}
	,
	{
		CMD : 
		{
			RESET: "cs:re-standby",
			EXIT: "cs:exit",
			OPENURL: "web:open",
			DEVICEUUID: "cs:deviceuuid",
			PUSHTOKEN: "cs:pushtoken",
			INITSERVICE: "cs:init:service"
		}
		,
		EVENT :
		{
			"android:back": function(data)
			{
				Logger.debug("AppOS API Event - android:back");
				
				var result = AResult.Helper.ERROR();
				
				Logger.debug("AppOS API Event - [android:back] result : "+JSON.stringify(result.toJSON()));
				data.defer.resolve( result.toJSON() );
			}
		}
	});
	
	var AuthMoven = apiModule.extend(
	{
		_auth: null
		,
		initialize: function()
		{
			AuthMoven.__super__.initialize.apply( this, arguments );
			this._auth = new AuthClient();
			Logger.debug("AuthMoven.initialize()");
		}
		,
		/**
		 * 모바일오븐 로그인을 진행한다.
		 * 요청된 앱을 위한 사용자 세션이 생성된다.
		 * 
		 * @param con_id	{ String } 로그인을 요청하는 앱 식별자.
		 */
		signIn : function(con_id, baro_id)
		{
			if( this._isAppOS() )
			{
				return this._call( AuthMoven.CMD.SIGNIN, { "con_id": con_id, "baro_id" : baro_id } );
			}
			else
			{
				Logger.info("AuthMoven.signIn() - redirect : #!login");
				
				var d = $.Deferred();
				d.resolve({ redirect : "#!login" });
				return d.promise();
			}
		}
		,
		/**
		 * 로그인 완료. 인증앱으로 부터 호출된다.
		 * 웹 모드인 경우 파라메터는 제공하지 않는다.
		 * 
		 * @param user				{ id: ##, name: ##, md5pwd: ##, chk_save_login: Y/N }
		 * @param sessionParams		{ SP_N: ##, SP_K: ## }
		 */
		endOfsignIn : function(user, sessionParams)
		{
			if( this._isAppOS() )
			{
				return this._call( AuthMoven.CMD.ENDOFSIGNIN, { "user": user, "session_params": sessionParams } );
			}
			else
			{
				Logger.info("AuthMoven.endOfsignIn() - redirect : #home");
				
				location.href = "#home";
			}
		}
		,
		/**
		 * 로그아웃을 진행한다.
		 * 
		 * @param sessionParams		{ SP_N: ##, SP_K: ## } 현재 세션의 파라메터. 웹 모드에서만 지정한다.
		 * @returns { $.Promise }	게스트 사용자 정보가 반환된다.
		 */
		signOut : function(appId, sessionParams)
		{
			var self = this;
			// 게스트 세션을 생성하고, 생성된 게스트 정보(user, session_params)를 반환한다.
			var makeGuestSession = function()
			{
				return self._auth.makeGuestSession()
				.then(function(guestSession)
				{
	                if( guestSession != undefined )
	                {
	                	Logger.log("Guest SP_N = " + guestSession.SP_N);
	                	Logger.log("Guest SP_K = " + guestSession.SP_K);
	                	BaroProps.setSessionParams( guestSession );
	                	BaroProps.setUser({ id: "guest", name: null });
	                	
	                	return AResult.Helper.SUCCESS({
        					user : BaroProps.getUser()
        					,
        					session_params : sessionParams
        				}).toJSON();
	             	}
	                else
	                {
	                	Logger.error("AuthMoven.signOut() - Error!");
	                	return AResult.Helper.SUCCESS({
        					user : null
	    					,
	    					session_params : null
	    				}).toJSON();
	                }
				});
			}
			var webSignOut = function()
			{
				return self._auth.signOut()
				.then(function()
				{
					Logger.info("AuthMoven.signOut() - Making a guest session...");
					return makeGuestSession();
				});
			}
			
			this._auth.syncUser();
			if( this._isAppOS() == true )
			{
				return this._call( AuthMoven.CMD.SIGNOUT, { "appId": appId, "session_params": sessionParams } )
				.then(function(result)
				{
					if( result.resultCode < 0 )
					{
						// Retry...
						return webSignOut();
					}
					else
					{
						return makeGuestSession();
					}
				});
			}
			else
			{
				// web mode
				return webSignOut();
			}
		}
	}
	,
	{
		CMD : 
		{
			SIGNIN: "req:moven:signin",
			ENDOFSIGNIN: "res:moven:signin",
			SIGNOUT: "req:moven:signout"
		}
	});
	
	var BaroApp = apiModule.extend(
	{
		initialize: function()
		{
			BaroApp.__super__.initialize.apply( this, arguments );
			
			Logger.debug("BaroApp.initialize()");
		}
		,
		/**
		 * 바로앱에 회원으로 가입한다.
		 * 
		 * @param user_id	{ id: ##, name: ## } BaroProps.getUser() 에 의해 구해진다.
		 * @param app_info	{ id: ##, no: ##, state: ##, name: ##, icon: ##, desc: ##, owner: ## } BaroProps.getAppInfo() 에 의해 구해진다.
		 * @return { Promise }
		 */
		signUp : function(user_id, app_info)
		{
			return this._call( BaroApp.CMD.SIGNUP, { "user_id": user_id, "app_info": app_info } );
		}
		,
		/**
		 * 바로앱 회원가입 결과를 반환한다.
		 * 
		 * @param result	{ resultCode: ##, msg: ##, extraData: true/false } 가입 처리 결과값. 성공인지 실패인지 반환된다.
		 */
		endOfSignUp : function(result)
		{
			return this._call( BaroApp.CMD.ENDOFSIGNUP, result );
		}
		,
		/**
		 * 지정된 바로앱을 지정한 파라메터를 적용하여 실행한다.
		 * 
		 * @param appId		{ String }
		 * @param params	{ Object } 바로앱 실행 템플릿에 적용 가능한 속성들 지정.
		 */
		runApp : function(appId, params)
		{
			return this._call( BaroApp.CMD.RUNAPP, { "appId": appId, "params": params } );
		}
		,
		/**
		 * 지정된 계정 정보로 현재 실행 중인 바로앱을 다시 시작시킨다.
		 * 
		 * @param user				{ id: ##, name: ##, md5pwd: ##, chk_save_login: Y/N }
		 * @param sessionParams		{ SP_N: ##, SP_K: ## }
		 */
		restartApp : function(user, sessionParams)
		{
			return this._call( BaroApp.CMD.RESTARTAPP, { "user": user, "sessionParams": sessionParams } );
		}
		,
		exit : function(appId)
		{
			return this._call( BaroApp.CMD.EXIT, appId );
		}
	}
	,
	{
		CMD : 
		{
			SIGNUP: "req:baang:signup",
			ENDOFSIGNUP: "res:baang:signup",
			RUNAPP: "baang:launch",
			RESTARTAPP: "baang:re-launch",
			EXIT: "baang:exit"
		}
		,
		EVENT :
		{
			"baroapp:goPage": function(data)
			{
				Logger.debug("AppOS API Event - [baroapp:goPage] : "+JSON.stringify(data));
				
				var args = data.params.arguments;
				Logger.debug("AppOS API Event - [baroapp:goPage] : "+args.pageId);
				//window.location.href = args.pageId;
				location.href = args.pageId;
				
				data.defer.resolve( AResult.Helper.SCCESS().toJSON() );
			}
		}
	});
	
	var Social = apiModule.extend(
	{
		initialize: function()
		{
			Social.__super__.initialize.apply( this, arguments );
			
			Logger.debug("Social.initialize()");
		}
		,
		/**
		 * 지정하는 소셜 서비스에 연결한다.
		 * 
		 * @param social_id	{ String } facebook, twitter, kakaostory
		 */
		connect : function(params)
		{
			return this._call( Social.CMD.CONNECT, params );
		}
		,
		/**
		 * 소셜 서비스에 연결 결과를 반환한다.
		 * 
		 * @param result	{ resultCode: ##, msg: ##, extraData: true/false }
		 */
		endOfConnect : function(result)
		{
			return this._call( Social.CMD.ENDOFCONNECT, result );
		}
		,
		/**
		 * 지정하는 소셜 서비스에 연결한다.
		 * 
		 * @param social_id	{ String } facebook, twitter, kakaostory
		 */
		disconnect : function(social_id)
		{
			return this._call( Social.CMD.DISCONNECT, { "social": social_id }  );
		}
		,
		/**
		 * 2016.11.29
		 * 현재는 메시지만 가능.
		 * 단말기에서 지원하는 메시지 공유 서비스목록을 보여준다.
		 * 
		 * facebook의 경우 메시지형식에 링크만 가능 링크이외 텍스트는 제거됨.
		 * 
		 * @param status	{ String } 메시지
		 */
		share: function(params)
		{
			return this._call( Social.CMD.SOCIAL_SHARE_VIEW, params );
		}
	}
	,
	{
		CMD : 
		{
			CONNECT: "req:social:connect",
			ENDOFCONNECT: "res:social:connect",
			DISCONNECT: "social:disconnect",
			SOCIAL_SHARE_VIEW: "req:social:shareview"
		}
	});
	
	var Writter = apiModule.extend(
	{
		initialize: function()
		{
			Writter.__super__.initialize.apply( this, arguments );
			
			Logger.debug("Writter.initialize()");
		}
		,
		/**
		 * 텍스트 입력/편집하기
		 * 
		 * @param app_id	{ String } 바로앱 식별자
		 * @param type		{ String } 입력 콘텐츠 유형, text 타입인 경우 콘텐츠 영역의 regexp 를 지정해서 동일한 효과를 낼 수 있다
							multiline
							text
							password
							number
							email
							tel
							url
							서브 콘텐츠로 지정되는 경우 "image, location, file, score" 지정 가능
		 * @param content	{
								label : 입력 콘텐츠 레이블. 화면 출력용. 선택사항,
								id: 콘텐츠 식별자,
								regexp : 입력되는 콘텐츠를 검증하는 정규화 표현식. 선택사항,
								max : 콘텐츠 최대 길이 지정하지 않은 경우 길이 제한 없음,
								data: 콘텐츠. 지정하는 경우 편집 모드로 동작
								options : [
								{
								  label : 입력 콘텐츠 레이블. 화면 출력용. 선택사항,
								  id: 콘텐츠 식별자,
								  type : image, file, location, addr, score 중 지정,
								  max : 옵션 항목이 하나의 id 로 반복 지정 가능한 경우 지정. 지정하지 않은 경우 무조건 1 개만 입력 가능,"
								}, { 이미지가 여러개 필요한 경우 반복 지정 }
								]
		 * 					}
		 */
		inputText: function(app_id, type, content)
		{
			return this._call( Writter.CMD.INPUTTEXT, { "app_id": app_id, "type": type, "content": content } );
		}
		,
		/**
		 * 텍스트 입력의 결과 반환
		 * @param result	ResultEx(
							{
							  contentId1 : "등록되는 콘텐츠",
							  contentId2 : ["첨부된 이미지 상대 경로", "첨부된 이미지가 여러개인 경우 배열로 지정"],
							  contentId3 : "첨부된 파일 상대 경로",
							  contentId4 : { lat: 위도, lng: 경도 },
							  contentId5 : [우편번호] 주소,
							  contentId6 : 리뷰 점수,
							  ...
							})
		 */
		endOfText: function(result)
		{
			return this._call( Writter.CMD.ENDOFTEXT, result );
		}
		,
		/**
		 * 복합문서 입력/편집하기
		 * 
		 * @param app_id	{ String } 바로앱 식별자
		 * @param label		{ String } 편집기 제목, 앱 이름 또는 입력 주제
		 * @param id		{ String } 콘텐츠 식별자
		 * @param content	{ String } 콘텐츠. 지정하는 경우 편집 모드로 동작
		 */
		inputDoc: function(app_id, label, id, content)
		{
			return this._call( Writter.CMD.INPUTDOC, { "app_id": app_id, "label": label, "id": id, "content": content } );
		}
		,
		/**
		 * 복합문서 입력/편집하기의 결과 반환
		 * @param result	ResultEx(
							{
							  contentId : "등록되는 콘텐츠",
							  image : ["첨부된 이미지 상대 경로", "첨부된 이미지가 여러개인 경우 배열로 지정"],
							  ...
							})
		 */
		endOfDoc: function(result)
		{
			return this._call( Writter.CMD.ENDOFDOC, result );
		}
	}
	,
	{
		CMD : 
		{
			INPUTTEXT: "req:writter:inputtext",
			ENDOFTEXT: "res:writter:inputtext",
			INPUTDOC: "req:writter:inputdoc",
			ENDOFDOC: "res:writter:inputdoc"
		}
	});
	
	var Vision = apiModule.extend(
	{
		initialize: function()
		{
			Vision.__super__.initialize.apply( this, arguments );
			
			Logger.debug("Vision.initialize()");
		}
		,
		/**
		 * 1D/2D 바코드에서 코드값을 읽어온다.
		 * 
		 * @param type		{ String } 선택사항. 바코드 유형을 지정한다.
		 * @param params	{ msg: 화면에 출력된 메시지, cancel: 취소버튼 캡션 }
		 */
		scanCode: function(type, params)
		{
			if( this._isAppOS() )
			{
				return this._call( Vision.CMD.SCANCODE, { "type": type, "params": params } );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
		,
		/**
		 * 지정된 곳으로 부터 이미지를 취득한다.
		 * 
		 * @param from	{ String } 이미지 취득 장소
		 * 				gallery, camera 중 하나를 지정한다.
		 */
		getPicture: function(from, params)
		{
			if( this._isAppOS() )
			{
				return this._call( Vision.CMD.GETPICTURE, { "from": from, "params": params } );
			}
			else if( from === "gallery" )
			{
				var result = new AResult.ResultEx();
			
				// TODO 파일 선택창으로 부터 파일 입력 받은 이미지를 읽어들여 base64 로 엔코딩하여 반환한다.
				
				return this._delayedReturn( result.toJSON() );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
	}
	,
	{
		CMD : 
		{
			SCANCODE: "vision:scancode",
			GETPICTURE: "vision:getpicture"
		}
	});	
	
	var Messaging = apiModule.extend(
	{
		initialize: function()
		{
			Messaging.__super__.initialize.apply( this, arguments );
			
			Logger.debug("Messaging.initialize()");
		}
		,
		/**
		 * 푸시 통지를 활성화한다.
		 * 
		 * @return {$.promise} 활성화 성공시 플랫폼 토큰이 반환된다.
		 */
		activePushNotification: function()
		{
			if( this._isAppOS() )
			{
				return this._call( Messaging.CMD.ACTIVE_MESSAGING );
			}
			else
			{
				// TODO 크롬/사파리 푸시가 추가되면 해당 기능을 활성화한다.
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
		,
		/**
		 * 푸시 통지를 비활성화한다.
		 * 사용중인 푸시 토큰이 비활성화 된다.
		 */
		inactivePushNotification: function()
		{
			if( this._isAppOS() )
			{
				return this._call( Messaging.CMD.INACTIVE_MESSAGING );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
		,
		/**
		 * 푸시 설정을 조회한다.
		 */
		getPushConfig: function()
		{
			if( this._isAppOS() )
			{
				return this._call( Messaging.CMD.GET_CONFIG );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
	}
	,
	{
		CMD : 
		{
			ACTIVE_MESSAGING: "messaging:active",
			INACTIVE_MESSAGING: "messaging:inactive",
			GET_CONFIG: "messaging:config"
		}
	});
	
	var O2O = apiModule.extend(
	{
		initialize: function()
		{
			O2O.__super__.initialize.apply( this, arguments );
			
			Logger.debug("O2O.initialize()");
		}
	}
	,
	{
		CMD : 
		{
		}
	});
	
	var Commerce = apiModule.extend(
	{
		initialize: function()
		{
			Commerce.__super__.initialize.apply( this, arguments );
			
			Logger.debug("Commerce.initialize()");
		}
	}
	,
	{
		CMD : 
		{
		}
	});
	
	var Storage = apiModule.extend(
	{
		initialize: function()
		{
			Storage.__super__.initialize.apply( this, arguments );
			
			Logger.debug("Storage.initialize()");
		}
		,
		setAttr: function(name, value, merge)
		{
			if( this._isAppOS() )
			{
				return this._call( Storage.CMD.SETATTR, { "name": name, "value": value, "merge": merge } );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
		,
		/**
		 * 존재하지 않는 설정값으로 요청된 경우 ResultEx(null)  값이 반환된다.
		 */
		getAttr: function(name)
		{
			if( this._isAppOS() )
			{
				return this._call( Storage.CMD.GETATTR, { "name": name } );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
	}
	,
	{
		CMD : 
		{
			SETATTR: "storage:setattr",
			GETATTR: "storage:getattr"
		}
	});
	
	var GPS = apiModule.extend(
	{
		initialize: function()
		{
			GPS.__super__.initialize.apply( this, arguments );
			
			Logger.debug("GPS.initialize()");
		}
		,
		getPosition: function()
		{
			if( this._isAppOS() )
			{
				return this._call( GPS.CMD.GETPOSITION );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
		,
		beginLogger: function(pathName)
		{
			if( this._isAppOS() )
			{
				return this._call( GPS.CMD.BEGINLOGGER, { "pathName": pathName } );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
		,
		endLogger: function()
		{
			if( this._isAppOS() )
			{
				return this._call( GPS.CMD.ENDLOGGER );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
		,
		cleanLogger: function()
		{
			if( this._isAppOS() )
			{
				return this._call( GPS.CMD.CLEANLOGGER );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
		,
		getPath: function(name)
		{
			if( this._isAppOS() )
			{
				return this._call( GPS.CMD.GETPATH );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
	}
	,
	{
		CMD : 
		{
			GETPOSITION: "gps:location",
			BEGINLOGGER: "gps:logger:begin",
			ENDLOGGER: "gps:logger:end",
			CLEANLOGGER: "gps:logger:clean",
			GETPATH: "gps:getpath",
		}
	});
	
	var CookerApp = apiModule.extend(
	{
		initialize: function()
		{
			CookerApp.__super__.initialize.apply( this, arguments );
			
			Logger.debug("CookerApp.initialize()");
		}
		,
		/**
		 * 지정된 바로앱을 지정한 파라메터를 적용하여 실행한다.
		 * 
		 * @param appId		{ String }
		 * @param params	{ Object } 바로앱 실행 템플릿에 적용 가능한 속성들 지정.
		 */
		runApp : function()
		{
			return this._call( CookerApp.CMD.RUNAPP );
		}
		,
		/**
		 * 쿠커를 종료한다.
		 */
		exitApp : function()
		{
			return this._call( CookerApp.CMD.EXITAPP );
		}
	}
	,
	{
		CMD : 
		{
			RUNAPP: "cooker:launch",
			EXITAPP: "cooker:exit"
		}
	});
	
	var Beacon = apiModule.extend(
	{
		initialize: function()
		{
			Beacon.__super__.initialize.apply( this, arguments );
			
			Logger.debug("Beacon.initialize()");
		}
		,
		/**
		 * @param msg		비콘 신호로 송출할 메시지 기본정보를 잔달한다. 
		 * 					{
		 * 						type: ##,	Beacon.TYPE 의 상수값을 지정한다.
		 * 						uuid: ##,
		 * 						major: ##,
		 * 						minor: ##,
		 * 						mime: ##,
		 * 						message: ##
		 * 					}
		 * @param session	세션 파라메터. 지정하지 않는 경우 appOS 에 보관된 세션을 사용한다.
		 */
		addTransmit: function(appId, msg, session)
		{
			if(session == undefined)
			{
				return this._delayedReturn( AResult.Helper.INVALID_PARAMETERS("Invalid session parameters!").toJSON() );
			}
			else if( this._isAppOS() )
			{
				return this._call( Beacon.CMD.ADDTRANSMIT, _.extend({ app_id: appId, session: session }, msg) );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
		,
		removeTransmit: function(appId, service_uuid)
		{
			if( this._isAppOS() )
			{
				return this._call( Beacon.CMD.REMOVETRANSMIT, { app_id: appId, uuid: service_uuid } );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
		,
		/**
		 * 비콘 서비스 모니터를 추가한다.
		 * 
		 * @param {Object} svc_params	서비스 식별을 위한 파라메터
		 * 								{
		 * 									id:
		 * 									uuid:
		 * 									location:
		 * 								}
		 */
		addServiceMonitor: function(appId, svc_params, session)
		{
			if( this._isAppOS() )
			{
				return this._call( Beacon.CMD.ADDMONITOR, _.extend({ app_id: appId, session: session }, svc_params) );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
		,
		/**
		 * 지정된 서비스 모니터를 제거한다.
		 * Wildcast 모드인 경우에는 삭제후 다수의 모니터를 설정할 수 있는 상태로 리셋된다.
		 */
		removeServiceMonitor: function(appId, service_uuid)
		{
			if( this._isAppOS() )
			{
				return this._call( Beacon.CMD.REMOVEMONITOR, { app_id: appId, uuid: service_uuid } );
			}
			else
			{
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON() );
			}
		}
	}
	,
	{
		CMD : 
		{
			ADDTRANSMIT: 'beacon:transmit:add',
			REMOVETRANSMIT: 'beacon:transmit:remove',
			ADDMONITOR: 'beacon:scan:add',
			REMOVEMONITOR: 'beacon:scan:remove'
		}
		,
		EVENT :
		{
			'beacon:region:entered': function(id)
			{
				Logger.info("Entered a region : "+id);
			},
			'beacon:region:exited': function(id)
			{
				Logger.info("Exited a region : "+id);
			},
			'beacon:region:proximity': function(msg)
			{
				Logger.info("Beacon Message : "+JSON.stringify(msg));
			}
		}
		,
		SERVICE:
		{
			UUID_WILDCAST: null,
			UUID_CHECK_ACTIVITY: '743B955A-0E13-4BE7-82BA-7F1EED83D5C7',
			UUID_ADVERTISEMENT_MESSAGE: 'B73748EC-8301-4E1B-A724-6C1AB70C332D'
		}
		,
		TYPE :
		{
			// 유동인구 체크용 신호
			NONE: 0,
			
			// GCon 에 담긴 콘텐츠 구분자
			MESSAGE: 1,
			QUESTION: 2,
			PRODUCT_INQUIRY: 3,
			SCHEDULE: 4,
			COMMENT: 5,
			YOUTUBE: 6,
			REVIEW: 7,
			PRODUCT_REVIEW: 8,
			SIMPLEPRODUCT_REVIEW: 9,
			PRODUCT_REF: 0x10,
			PRODUCT: 0x11,
			DIRECT_MESSAGE: 0x12,
			BANNER: 0x13,
			NEWSLETTER: 0x14,
			EVENT: 0x15,
			ISSUE: 0x16,
			RESERVATION: 0x17,
			CUSTOM_FORM: 0x18,
			CUSTOM_DATA: 0x19,
			BAROAPP: 0x20
		}
	});
	
	/**
	 * 지정한 모듈명에 해당하는 appOS API 객체를 얻는다.
	 */
	var apiCreator = Backbone.Model.extend(
	{
		// nothing
	}
	,
	{
		_holder: {},
		
		ID_MODULE: {
			CORESERVICE: "CoreService",
			AUTHMOVEN: "AuthMoven",
			BAROAPP: "BaroApp",
			COOKERAPP: "CookerApp",
			SOCIAL: "Social",
			MESSAGING: "Messaging",
			WRITTER: "Writter",
			VISION: "Vision",
			O2O: "O2O",
			COMMERCE: "Commerce",
			STORAGE: "Storage",
			GPS: "GPS",
			BEACON: "BEACON"
		},
		
		/**
		 * 지정한 이름의 API 객체을 얻는다.
		 * 인스턴스가 존재하지 않는 경우 최초 한 번만 호출한다.
		 * 중복 호출하는 경우 각 API 의 이벤트 핸들러가 중복 등록된다.
		 * 
		 * @param name	{ String } 모듈 이름
		 * @return 지정한 이름의 모듈 인스턴스. 지원하지 않는 경우 null.
		 */
		_createModule: function(name)
		{
			var client = null;
			
			switch(name)
			{
			case "CoreService":
				client = new CoreService();
				client.activeEvent( CoreService );
				return client;
				
			case "AuthMoven":
				client = new AuthMoven();
				client.activeEvent( AuthMoven );
				return client;
				
			case "BaroApp":
				client = new BaroApp();
				client.activeEvent( BaroApp );
				return client;
				
			case "CookerApp":
				client = new CookerApp();
				client.activeEvent( CookerApp );
				return client;
				
			case "Social":
				client = new Social();
				client.activeEvent( Social );
				return client;
				
			case "Messaging":
				client = new Messaging();
				client.activeEvent( Messaging );
				return client;
				
			case "Writter":
				client = new Writter();
				client.activeEvent( Writter );
				return client;
				
			case "Vision":
				client = new Vision();
				client.activeEvent( Vision );
				return client;
				
			case "O2O":
				client = new O2O();
				client.activeEvent( O2O );
				return client;
				
			case "Commerce":
				client = new Commerce();
				client.activeEvent( Commerce );
				return client;
				
			case "Storage":
				client = new Storage();
				client.activeEvent( Storage );
				return client;
				
			case "GPS":
				client = new GPS();
				client.activeEvent( GPS );
				return client;
				
			case "BEACON":
				client = new Beacon();
				client.activeEvent( Beacon );
				return client;
				
			default:
				return null;
			}
		}
		,
		getModule: function(name)
		{
			var client = null;
			
			if( apiCreator._holder[name] == undefined )
			{
				client = apiCreator._createModule(name);
				apiCreator._holder[name] = client;
			}
			else
			{
				client = apiCreator._holder[name];
			}
			
			return client;
		}
		,
		/**
		 * API 의 이벤트를 초기화한다.
		 * API 객체가 생성될 때 자신의 이벤트를 등록하기 때문에 생성된 객체는 캐싱되어 재사용되도록 구성된다. 
		 */
		initEvent: function()
		{
			apiCreator.getModule( apiCreator.ID_MODULE.CORESERVICE );
			apiCreator.getModule( apiCreator.ID_MODULE.BAROAPP );
			apiCreator.getModule( apiCreator.ID_MODULE.BEACON );
		}
	});
	
	return apiCreator;
});