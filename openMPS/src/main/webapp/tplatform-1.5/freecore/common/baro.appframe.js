/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2016 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

define(
[ "BaroAppHolder", "BaroBox", "Logger", "BaroProps" ], 
function( BaroAppHolder, BaroBox, Logger, BaroProps )
{
	var instanceMethod = 
	{
		_router : null
		,
		_routeMap : null
		,
		_routeHandler : null
		,
		/**
		 * 앱을 초기화한다.
		 * 
		 * @param skeleton		{ Object } 앱 파라메터 v1.0
		 * @param map			{ Object } 추가 앱 라우팅 맵. 필요한 경우 지정.
		 * @param handler		{ Object } 추가 앱 라이팅 핸들러. 필요한 경우 지정.
		 */
		initialize: function(options)
		{
			BaroAppHolder.prototype.initialize.apply( this, arguments );
			
			Logger.debug("BaroAppFrame.initialize() - complate to initialize a baroapp Frame.");
		}
		,
		_initRouter: function(map, handler)
		{
			var self = this;
			
			//
			this._initRouteMap( map );
			this._initRouteHandler( handler );
			
			//
			this._router = new (Backbone.Marionette.AppRouter.extend(
			{
				routes:
				{
					"" : "defaultRoute"
				}
				,
				defaultRoute: function(actions)
				{
					var pageId = BaroProps.getStartPage();
					if( pageId == null )
					{
						// 메인 페이지로 이동
						//pageId = "#!reset";
						pageId = "#home";
					}
					
					Logger.info("Baroapp Router - Start Page : "+pageId);
					self.route( pageId );
				}
				,
				appRoutes: self.getRouteMap()
				,
				controller: self.getRouteHandler()
				,
				execute: function(callback, args)
				{
					Logger.debug("BaroAppFrame Router.execute() - args: "+JSON.stringify(args));
					
					var panelTag = Backbone.history.getFragment();
					
					if( callback )
					{
						callback.apply(this, args);
					}

					Logger.debug("BaroAppFrame Router.execute() - Current Page : "+panelTag);
				}
				,
				onRoute: function( name, path, route )
				{
					Logger.debug("BaroAppFrame Router.onRoute() : "+name+", path: "+path+", route: "+route);

					var panelTag = Backbone.history.getFragment();
					
					if( panelTag )
					{
						Logger.debug("BaroAppFrame Tracking Tag : "+panelTag);
						UCMS.getApplication().getAppTracker().trackingView( panelTag );
						
						if( panelTag.charAt(0) != '!' )
						{
							self.pushPage( panelTag );
						}
					}
				}				
			}));
		}
		,
		/**
		 * 라우딩 맵을 초기화한다. 추가적인 라우팅 정보가 필요한 경우 파라메터로 전달한다.
		 * 
		 * @param additionalMap		{ Object } 추가되는 라우팅 맵을 지정한다.
		 */
		_initRouteMap: function(additionalMap)
		{
			this._routeMap = _.extend(
			{
				//
				// 기본 페이지
				//
				"home" 					: "goHome",
				"alram"					: "goAlram",
				"config"				: "goConfig",
				"about"					: "goAbout",
				"mobileoven"			: "goMobileOven",
				
				//
				// 페이지 출력
				//
				"box/:box_id"			: "viewBox",
				"box/:box_id/:params"	: "viewBox",
				"gconList/:container_id": "viewGConList",
				"gconViewer/:content_id": "viewGConContent",
				
				//
				// 특수기능
				//
				"!openMenu"				: "doOpenMenu",
				"!closeMenu"			: "doCloseMenu",
				"!history/:number"		: "doHistory",
				"!call/:number"			: "calling",
				"!call/:number/:method"	: "calling",
				"!sms/:number"			: "sendSMS",
				"!mail/:addr"			: "sendMail",
				"!web/:url"				: "openWeb"
			}
			, additionalMap );
		}
		,
		/**
		 * 라우팅 핸들러를 초기화한다. 추가적인 라우팅 맵이 지정되는 경우 해당하는 핸들러를 파라메터로 전달한다.
		 * 
		 * @param additionalHandler		{ Object } 핸들러 객체
		 */
		_initRouteHandler: function( additionalHandler )
		{
			var self = this;
			
			this._routeHandler = _.extend(
			{
				//
				// 기본 페이지 :: 푸터
				//
				goHome: function()
				{
					self._routeHandler.viewBox( self.getDefaultBox() );
				}
				,
				goAlram: function()
				{
					self._routeHandler.viewBox("alramBox");
				}
				,
				goConfig: function()
				{
					self._routeHandler.viewBox("configBox");
				}
				,
				goAbout: function()
				{
					self._routeHandler.viewBox("aboutBox");
				}
				,
				goMobileOven: function()
				{
					self._routeHandler.viewBox("mobileovenBox");
				}
				,
				
				//
				// 기본 페이지 :: 메뉴항목
				//
				
				viewBox: function(boxId, params)
				{
					Logger.debug("viewBox() - "+boxId+", params : "+params);
					self.showContentsBox(boxId);
				}
				,
				/**
				 * 지정된 게시판의 콘텐츠 목록을 활성화한다.
				 */
				viewGConList: function(containerId)
				{
					Logger.info("viewGConList() - containerId : "+containerId);
					
					self.showContainer( containerId );
				}
				,
				/**
				 * 시작 페이지로 콘텐츠 뷰어가 지정되는 경우 활성화된다.
				 * 
				 * @param contentId		{ String } 콘텐츠 식별자
				 */
				viewGConContent: function(contentId)
				{
					Logger.info("viewGConContent() - contentId : "+contentId);
					
					self.showContentViewer( contentId );
				}
				,
				
				//
				// 특수기능
				//

				doOpenMenu: function()
				{
					$(".menubar_btn").animate({"left":"-250px" }, 500, function() { });
					$(".header_box").animate({"left":"-250px" }, 500, function() { });
					$(".menubar_bg").css("right","0px");
					$(".menubar_bg").animate({"opacity":"0.5" }, 500, function() { });
					$("body")
						.css("overflow","hidden")
						.css("width","100%")
						.css("position","absolute")
						.animate({ "left": "-250px", }, 500, function(){});
					$(".menubar_list_box").animate({ "right": "0px", }, 200, function(){
	        			$(".menubar_info").css("position", "absolute");
	        			// 노트2 웹뷰 때문에 추가함. fixed 에서 반드시 위치값이 필요함. 
					});
				}
				,
				doCloseMenu: function()
				{
 					Logger.log("doCloseMenu() - closing a menu");
					
        			$(".header_box").animate({"left":"0" }, 200, function() { });
        			$(".menubar_btn").animate({"left":"0" }, 200, function() { });
        			$(".menubar_bg").css("opacity","0").css("right","-100%"); 
        			$(".menubar_box").animate({ "left": "0px", }, 200, function(){});
        			$(".menubar_list_box").animate({ "right": "-250px", }, 500, function(){
            			$(".menubar_info").css("position", "fixed");
        			});
					
        			Logger.log("doCloseMenu() - closing a body");
        			
        			$("body")
    				.animate({ "left": "0px", }, 200, function()
    				{
    					$("body")
    						.css("position","static")
    						.css("overflow","auto")
    					Logger.log("doCloseMenu() - Complate to close a menu of baroapp!!");
    				});
        			
        			/*
        			 *	ongil pc web 에서 메뉴클릭시 컨텐츠 영역이 좌측으로 밀려 들어옴.
        			
        			$("body")
        				.css("overflow","auto")
        				.css("position","absolute")
        				.animate({ "left": "0px", }, 200, function()
        				{
        					Logger.log("doCloseMenu() - Complate to close a menu of baroapp!!");
        				});
        			*/
				}
				,
				doHistory: function(number)
				{
					
				}
				,
				
				/**
				 * 스마트폰에서 전화걸기
				 * 
				 * @param number		{ Number } 전화번혼. 숫자로 지정.
				 * 						{ String } hp, tel 이 지정된 경우 운영자 정보의 연락처 번호가 자동 지정됨
				 * @param method		{ String } 전화거는 방법 지정. 지정하지 않은 경우 음성전화로 지정됨.
				 * 						audio : 음성전화로 걸기. 기본값
				 * 						av : 화성전화로 걸기.
				 */
				calling : function(number, method)
				{
					Logger.info("calling() - number : "+number);
					
					//
					if( UCMS.SPA.isDesktop() )
					{
						UCMS.alert("전화걸기 기능이 지원되지 않습니다.<br>"+number+"로 전화주세요.");
						return;
					}
					else if( method == 'hp' || method == 'tel' )
					{
						var contact = BaroProps.getAppInfo().owner.contact;
						
						if( typeof contact[ number ] == 'string' )
						{
							location.href = "tel:"+contact[ number ];
						}
					}
					else if( method == "av" )
					{
						location.href = "tel-av:"+number;
					}
					else
					{
						location.href = "tel:"+number;
					}
				}
				,
				sendSMS : function(number)
				{
					Logger.info("sendSMS() - number : "+number);
					
					//
					if( UCMS.SPA.isDesktop() )
					{
						UCMS.alert("문자 메시지 보내기 기능이 지원되지 않습니다.<br>"+number+"로 메시지를 보내주세요.");
						return;
					}
					else if( number == 'owner' )
					{
						var contact = BaroProps.getAppInfo().owner.contact;
						
						if( typeof contact[ 'hp' ] == 'string' )
						{
							location.href = "sms:"+contact[ 'hp' ];
						}
					}
					else
					{
						location.href = "sms:"+number;
					}
				}
				,
				sendMail : function(addr)
				{
					Logger.info("sendMail() - addr : "+addr);
					
					//
					if( UCMS.SPA.isDesktop() )
					{
						location.href = "mailto:"+addr;
					}
					else if( number == 'owner' )
					{
						var contact = BaroProps.getAppInfo().owner.contact;
						
						if( typeof contact[ 'email' ] == 'string' )
						{
							location.href = "mailto:"+contact[ 'email' ];
						}
					}
					else
					{
						location.href = "mailto:"+addr;
					}
				}
				,
				openWeb : function(url)
				{
					Logger.info("openWeb() - url : "+url);
					
					//
					if( number == 'owner' || number == 'facebook' || number == 'twitter' || number == 'kakaostory' || number == 'instagram' )
					{
						var contact = BaroProps.getAppInfo().owner.contact;
						
						number != 'owner' || ( number = 'web' );
						if( typeof contact[ number ] == 'string' )
						{
							url = contact[ number ];
						}
					}
					
					if( url.indexOf('http://') == -1 || url.indexOf('https://') == -1 )
					{
						url = "http://"+url;
					}
					
					//
					if( UCMS.SPA.isDesktop() )
					{
						window.open(url, "_blank");
					}
					else
					{
						window.open(url, "_blank");
					}
				}	
			}
			, additionalHandler );
		}
		,
		getRouteMap: function()
		{
			return this._routeMap;
		}
		,
		getRouteHandler: function()
		{
			return this._routeHandler;
		}
		,
		onRender: function()
		{
			Logger.log("BaroAppFrame.onRender()");
			
			UCMS.adjustViewHeight($("body"));
		}
		,
		/**
		 * 라우팅 이벤트를 발생시킨다.
		 * 처리된 이벤트 정보는 히스토리에 쌓이게 된다.
		 * 히스토리에 남기지 않고 이동하기 위해서는 window.location 객체를 사용한다.
		 * 
		 * @param pageTag	{ String } 라우팅 이벤트 식별자
		 */
		route: function(pageTag)
		{
			this._router.navigate( pageTag, { trigger: true } );
		}
	};
	
	var historyMethod = 
	{
		_historySize: 5,
		_routeHistory: []
		,
		
		/**
		 * 저장된 히스토리 스택을 초기화한다.
		 */
		clearHistory: function()
		{
			Logger.debug("BaroAppFrame() - Clearning history stack, length : "+this._routeHistory.length);
			this._routeHistory = [];
		}
		,
		/**
		 * 현재 화면에 출력중인 페이지 식별자를 얻는다.
		 */
		curPage: function()
		{
			var curPage = this._routeHistory[ this._routeHistory.length - 1 ];
			
			Logger.log("curPage() - "+curPage);
			
			return curPage;
		}
		,
		pushPage: function(pageTag)
		{
			if( this._routeHistory.push(pageTag) > this._historySize )
			{
				this._routeHistory.shift();
			}
			
			Logger.log("pushPage() - "+pageTag+", length:"+this._routeHistory.length);
		}
		,
		/**
		 * 히스토리내에 최상위 페이지를 제거한다.
		 * 
		 * @return { String } 제거된 페이지 식별자
		 */
		popPage: function()
		{
			var curPage = this._routeHistory.pop();
			
			Logger.log("popPage() - "+curPage);
			
			return curPage;
		}
		,
		/**
		 * 이전 페이지로 이동한다.
		 * 
		 * @return { String } 이동된 페이지 식별자. 이동할 페이지가 없는 경우 null 반환.
		 */
		backPage: function()
		{
			var curPage = this._routeHistory.pop();
			Logger.log("backPage() - curPage : "+curPage);
			
			if( this.historySize() == 0 )
			{
				Logger.log("backPage() - the history is empty!");
				return null;
			}

			var prevPage = "#"+this._routeHistory.pop();
			Logger.log("backPage() - backPage : "+prevPage+", length : "+this._routeHistory.length);
			
			window.location.href = prevPage;
			
			return prevPage;
		}
		,
		/**
		 * 라우터 히스토리의 크기를 반환한다.
		 * 히스토리 크기는 최대 _historySize 의 값 만큼 유지된다.
		 */
		historySize: function()
		{
			Logger.log("historySize() - "+this._routeHistory.length);
			return this._routeHistory.length;
		}
	};
	
	var BaroAppFrame = BaroAppHolder.extend(_.extend( instanceMethod, historyMethod ));

	return BaroAppFrame;
});