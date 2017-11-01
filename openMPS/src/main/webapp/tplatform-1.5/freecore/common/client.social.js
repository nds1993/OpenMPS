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
	var SocialClient = ClientBase.extend(
	{
		initialize: function()
		{
			SocialClient.__super__.initialize.apply( this, arguments );
			Logger.debug("SocialClient.initialize()");
		}
		,
		syncUser : function()
		{
			this.set(
			{
				host: BaroProps.getHosts().unime,
				actors: BaroProps.getActors(),
				session: BaroProps.getSessionParams(),
				user: BaroProps.getUser(),
				token: BaroProps.getUniMeToken()
			});
		}		
	}
	,
	{
		CON_ID: "baangapp",
		
		PATH_FACEBOOK: "/facebook/cooker/connect.html",
		PATH_TWITTER: "/twitter/cooker/connect.html",
		PATH_KAKAOSTORY: "/kakao/cooker/connect.html",
		
		getFacebookUrl: function()
		{
			var hosts = BaroProps.getHosts();
			var params = BaroProps.getSessionParams();
			var conUrl = hosts.oauth + SocialClient.PATH_FACEBOOK 
						+ "?con_id="+SocialClient.CON_ID
						+ "&domain=.moven.net&sp-name=" + params.SP_N + "&sp-key=" + params.SP_K;
			
			return conUrl;
		},
		
		getTwitterUrl: function()
		{
			var hosts = BaroProps.getHosts();
			var params = BaroProps.getSessionParams();
			var conUrl = hosts.oauth + SocialClient.PATH_TWITTER
						+ "?con_id="+SocialClient.CON_ID
						+ "&domain=.moven.net&sp-name=" + params.SP_N + "&sp-key=" + params.SP_K;
			
			return conUrl;
		},
		
		getKakaostoryUrl: function()
		{
			var hosts = BaroProps.getHosts();
			var params = BaroProps.getSessionParams();
			var conUrl = hosts.oauth + SocialClient.PATH_KAKAOSTORY
						+ "?con_id="+SocialClient.CON_ID
						+ "&domain=.moven.net&sp-name=" + params.SP_N + "&sp-key=" + params.SP_K;
			
			return conUrl;
		}
	}
	);
	
	return SocialClient;
});