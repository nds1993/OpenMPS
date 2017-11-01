/**
 * Project : Trunk Platform
 *
 * Copyright (c) 2015 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

define(["osapi"]
, function(osapi)
{
	/**
	 * Googla Analytics 연동을 위한 Wrapper 객체 GATracker
	 * 
	 */
	var GATracker = function()
	{
		this._tracker = null;
		this._trackingId = null;
		// Tracker name should only consist of alphanumeric characters.
		this._trackerName = "TPlatformTracker";
	};
	
	/**
	 * Google Analytic SDK 를 로딩한다.
	 * 로딩이 완료되면 window.ga 에 저장된다.
	 * 
	 * @return { $.Promise } 성공시 function(ga){}, 실패시 function(err){}
	 */
	GATracker.init = function()
	{
		var d = $.Deferred();
		
		if( GATracker._init == true )
		{
			d.resolve();
		}
		else
		{
			var gaPath = (location.protocol === "https:" ? "https:" : "http:")+"//www.google-analytics.com/analytics.js";
			
			UCMS.debug("Universal Analytics SDK Path : "+gaPath);
			
			require
			(
				[ gaPath ],
				function(ga)
				{
					GATracker._init = true;
					UCMS.info("GATracker.init() - Completed to load the google analytics!");
					
					d.resolve( window.ga );
				},
				function(err)
				{
					UCMS.error("GATracker.init() - Failed to load the google analytics!");
					d.reject( err );
				}
			);
		}
		
		return d.promise();
	};
	
	var GAT = GATracker.prototype;
	
	/**
	 * 프로토콜에 호환되는 Google Universal Analytics Tracker 를 생성한다.
	 * 
	 * @return { $.Promise }
	 */
	GAT.create = function(trackingId, trackerName, cookieDomain)
	{
		var d = $.Deferred();
		var tracker = null;
		
		if( location.protocol === "file:" && osapi != undefined )
		{
			var self = this;
			var cs = osapi.getModule("CoreService");
			
			cs.getDeviceUUID().then(
					function(result)
					{
						if( result.resultCode < 0 )
						{
							d.reject(result.msg);
							return;
						}
						
						var uuid = result.extraData;
						
						UCMS.info("GAT.create() - device UIID : "+uuid);
						tracker = self.createLocalTraker( trackingId, trackerName, uuid );
						d.resolve( tracker );
					}
					,
					function(err)
					{
						UCMS.error("GAT.create() - Failed to get a device uuid.");
						d.reject(err);
					});
			
			// TODO 테스트를 위한 코드
			//tracker = self.createLocalTraker( trackingId, trackerName, "2758449e-9632-4a67-af9d-3e291c896347" );
			
			d.resolve( tracker );
		}
		else
		{
			tracker = GAT.createRemoteTracker.apply( this, arguments );
			
			d.resolve( tracker );
		}
		
		return d.promise();
	};
	
	/**
	 * 웹서버 기반으로 서비스되는 앱을 위한 GA Tracker 인스턴스를 생성한다.
	 * 
	 * @param trackingId	Google Analytics Property 식별자
	 * @param trackerName	트래커 인스턴스 이름. 지정하지 않으면 'TPlatformTracker' 자동 지정.
	 * 						알파벳만 지정 가능하다.
	 * @param cookieDomain	쿠키 설정 도메인. 지정하지 않은 경우 'auto'로 자동 지정
	 * @return { Google Universal Analytics Tracker }
	 */
	GAT.createRemoteTracker = function(trackingId, trackerName, cookieDomain)
	{
		if( this._tracker != null )
		{
			return this._tracker;
		}

		this._trackingId = trackingId;
		this._trackerName = trackerName || this._trackerName;
		this._tracker = ga.create(trackingId, cookieDomain || 'auto', this._trackerName);
		
		// Remarketing, Demographics and Interest Reporting 설정
		window.ga(this._trackerName+'.require', 'displayfeatures');
		
		UCMS.info("GAT.createRemoteTracker() - Created tracker ID : "+this._trackingId+" Name : "+this._trackerName);
		
		return this._tracker;
	};
	
	/**
	 * 로컬 화일 시스템을 기반으로 서비스되는 앱을 위한 GA Tracker 인스턴스를 생성한다.
	 * 
	 * @param trackingId		{ String } Google Analytics Property 식별자
	 * @param trackerName		{ String } 트래커 인스턴스 이름. 지정하지 않으면 'TPlatformTracker' 자동 지정
	 * 							알파벳만 지정 가능하다.
	 * @param clientId			{ String } 클라이언트 식별자. UUID 형식으로 지정. 단말기를 구분하는 역할을 한다.
	 * 							createRemoteTracker()에서는 2년동안 유효하도록 관리된다.
	 * @return { Google Universal Analytics Tracker }
	 */
	GAT.createLocalTraker = function(trackingId, trackerName, clientId)
	{
		if( this._tracker != null )
		{
			return this._tracker;
		}

		this._trackingId = trackingId;
		this._trackerName = trackerName || this._trackerName;
		this._tracker = ga.create(trackingId, 
				{
					'name': this._trackerName,
					'cookieDomain': 'none',
					'storage': 'none',
					'clientId': clientId
				});
		this._tracker.set('checkProtocolTask', function(){ /* nothing */ });
		
		// Remarketing, Demographics and Interest Reporting 설정
		//window.ga(this._trackerName+'.require', 'displayfeatures', {cookieName: null});
		window.ga(this._trackerName+'.require', 'displayfeatures');
		
		UCMS.info("GAT.createLocalTraker() - Created tracker ID : "+this._trackingId+" Name : "+this._trackerName);
		
		return this._tracker;
	};
	
	/**
	 * Googla Analytics Tracker 인스턴스를 제거한다.
	 */
	GAT.destroy = function()
	{
		if( this._tracker == null )
		{
			UCMS.error("GAT.destroy() - Invalid tracker instacne!");
			return;
		}
		
		ga.remove( this._trackerName );
		UCMS.info("GAT.destroy() - Removed tracker Id : "+this._trackingId);
		
		this._tracker = null;
		this._trackerName = null;
		this._trackingId = null;
	};
	
	/**
	 * Googla Analytics Tracker 인스턴스를 구한다.
	 */
	GAT.getTracker = function()
	{
		return this._tracker;
	};
	
	/**
	 * 아날리틱스가 적용된 서비스의 사용자 아이디 정보를 셋한다.
	 */
	GAT.setUserId = function(userId)
	{
		if( this._tracker == null )
		{
			UCMS.error("GAT.setUserId() - Invalid tracker instacne!");
			return;
		}
		
		UCMS.info("GAT.setUserId() - "+userId);
		
		this._tracker.set('userId', userId);
	};
	
	/**
	 * 앱 활동 추적을 위한 정보를 설정한다.
	 * 
	 * @param appName	{ String } 앱 이름을 설정한다. 필수 항목. 다음과 같이 지정 'My App' 최대 100 Bytes
	 * @param appVer	{ String } 앱 버전. 선택사항
	 * @param appId		{ String } 앱 패키지 식별자. 선택사항.
	 * 					예를 들면 com.company.app 과 같은 형식으로 지정. 최대 150 Bytes
	 */
	GAT.setAppInfo = function(appName, appVer, appId)
	{
		if( this._tracker == null )
		{
			UCMS.error("GAT.setAppInfo() - Invalid tracker instacne!");
			return;
		}
		
		this._tracker.set(
		{
			'appName': appName,
			'appVersion': appVer,
			'appId': appId
		});
	};
	
	/**
	 * 바로앱 정보를 제공한다.
	 */
	GAT.setBaroAppInfo = function(appName, appVer)
	{
		this.setAppInfo("baroapp-"+appName, appVer);
	};
	
	/**
	 * 파트너앱 정보를 제공한다.
	 */
	GAT.setPartnerAppInfo = function(appName, appVer)
	{
		this.setAppInfo("partnerapp-"+appName, appVer);
	};
	
	/**
	 * 프로토콜에 따른 트래킹 신호를 발생시킨다.
	 */
	GAT.trackingView = function(viewName)
	{
		if( location.protocol === "file:" )
		{
			// App
			this.trackingScreenView(viewName);
		}
		else
		{
			// Web
			this.trackingPageView('/'+viewName);
		}
	};
	
	/**
	 * 웹 페이지의 전환을 기록한다. 
	 * 
	 * @param viewName		{ String } '/' 로 시작하는 페이지 경로. 필수 항목. 보통 location.pathname 을 지정. 최대 2048 Bytes
	 * @param title			{ String } 페이지 이름을 지정. 지정하지 않으면 기본값으로 document.title 가 사용된다. 최대 1500 Bytes
	 * @param location		{ String } 페이지의 전체 경로를 지정. 최대 2048 Bytes
	 */
	GAT.trackingPageView = function(viewName, title, location)
	{
		if( this._tracker == null )
		{
			UCMS.error("GAT.trackingPageView() - Invalid tracker instacne!");
			return;
		}

		this._tracker.send(
				{
					hitType: 'pageview',
					page: viewName,
					title: title,
					location: location
				});
	};
	
	/**
	 * 앱 내의 화면 전환을 기록한다.
	 */
	GAT.trackingScreenView = function(viewName)
	{
		if( this._tracker == null )
		{
			UCMS.error("GAT.trackingScreenView() - Invalid tracker instacne!");
			return;
		}
		
		this._tracker.send(
				{
					hitType: 'screenview', 
					screenName: viewName 
				});
	};
	
	/**
	 * 이벤트 히트를 기록한다.
	 * 
	 * @param category	{ String } 이벤트 구분자. 카테고리. 필수항목. 최대 150 Bytes
	 * @param action	{ String } 해당 이벤트의 세부 동작. 필수항목. 최대 500 Bytes
	 * @param label		{ String } 설명. 선택항목. 최대 500 Bytes
	 * @param value		{ Number } 이벤트 가치.양수로 지정한다.
	 */
	GAT.trackingEvent = function(category, action, label, value)
	{
		if( this._tracker == null )
		{
			UCMS.error("GAT.trackingEvent() - Invalid tracker instacne!");
			return;
		}

		this._tracker.send( 
				{ 
					hitType: 'event',
					eventCategory: category,
					eventAction: action,
					eventLabel: label,
					eventValue: value
				});
	};
	
	/**
	 * SNS 와의 연동 동작을 기록한다.
	 */
	GAT.trackingSocial = function(sns, action, target)
	{
		if( this._tracker == null )
		{
			UCMS.error("GAT.trackingSocial() - Invalid tracker instacne!");
			return;
		}

		this._tracker.send(
				{
					hitType: 'social',
					socialNetwork: sns,
					socialAction: action,
					socialTarget: target
				});
	};
  
	return GATracker;
});