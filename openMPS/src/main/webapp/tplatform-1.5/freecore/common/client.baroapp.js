/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2014 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman 
 */

define(
[
	"ClientBase", "AResultClasses", "BaroProps", "Logger"
]
,
function(ClientBase, AResult, BaroProps, Logger)
{
	var BaroAppClient = ClientBase.extend(
	{
		defaults: {
			host: null,
			actors: null,
			session: null,
			user: null,
			token: null,
			baroappId: null,
			unread: 0
		}
		,
		initialize: function()
		{
			BaroAppClient.__super__.initialize.apply( this, arguments );
			Logger.debug("BaroAppClient.initialize()");
		}
		,
		/**
		 * 사용자 정보를 동기화한다.
		 * 동기화된 정보들은 UniMe Agent API 호출시 파라메터로 사용된다.
		 */
		syncUser : function()
		{
			this.set(
			{
				host: BaroProps.getHosts().api,
				actors: BaroProps.getActors(),
				session: BaroProps.getSessionParams(),
				user: BaroProps.getUser(),
				token: BaroProps.getUniMeToken(),
				baroappId: BaroProps.getAppInfo().id || null
			});			
		}
		,
		/**
		 * 로그인된 사용자의 바로앱 목록을 구한다.
		 * 현재 API 는 페이징 기능을 지원하지 않는다.
		 * 
		 * @param size		{ Number } 페이지 사이즈. 지정하지 않으면 5개로 세팅된다.
		 * @param after		{ Number } 조회 시작 순서값.
		 */
		fetchMyApp: function(size, after)
		{
			var self = this;
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var apiPath = this.get("host")+"/bbang/list/"+user.id+"?size="+(size || 5);
			
			if( after )
			{
				apiPath += "&after="+after;
			}
			
			Logger.debug("API : "+apiPath);
			Logger.debug("fetchMyApp() - session_params : "+JSON.stringify(session));
			
			return $.ajax(
			{
                type: 'GET',
                url:  apiPath,
                cache: false,
                crossDomain: true,
                headers:
                {
                	"sp-name" : session.SP_N,
                	"sp-key" : session.SP_K
                },
                contentType: 'application/json; charset=utf-8',
                dataType: "json"
				,
				success: function (data)
                {
					Logger.debug("fetchMyApp() - Success!");
                	Logger.debug(data);
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("fetchMyApp() - Error : "+textStatus);
                }
            });
		}
		,
		getAppInfo: function()
		{
			
		}
		,
		createMyApp: function()
		{
			
		}
		,
		/**
		 * 지정한 바로앱의 회원을 조회한다.
		 */
		fetchAppUser: function(appNo, size, after)
		{
			var self = this;
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var apiPath = this.get("host")+"/member/list/"+appNo+"?paseSize="+(size || 5);
			
			if( after )
			{
				apiPath += "&after="+after;
			}
			
			Logger.debug("API : "+apiPath);
			Logger.debug("fetchAppUser() - session_params : "+JSON.stringify(session));
			
			return $.ajax(
			{
                type: 'GET',
                url:  apiPath,
                cache: false,
                crossDomain: true,
                headers:
                {
                	"sp-name" : session.SP_N,
                	"sp-key" : session.SP_K
                },
                contentType: 'application/json; charset=utf-8',
                dataType: "json"
				,
				success: function (data)
                {
					Logger.debug("fetchAppUser() - Success!");
                	Logger.debug(data);
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("fetchAppUser() - Error : "+textStatus);
                }
            });
		}
	});
	
	return BaroAppClient;
});