/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2016 FREECORE, Inc. All rights reserved.
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
	var AuthClient = ClientBase.extend(
	{
		initialize: function(options)
		{
			AuthClient.__super__.initialize.apply( this, arguments );
			Logger.debug("AuthClient.initialize()");
		}
		,
		/**
		 * 수신한 결과에서 세션 정보를 저장한다.
		 * 
		 * @param recv	{ ResultEx } 세션 생성 결과
		 * @return	성공시 { SP_N: ##, SP_K: ## }
		 * 			실패시 undefined
		 */
		_saveSession : function(recv)
		{
			if( recv && recv.resultCode == 0 )
            {
            	var sessionParams = 
            	{ 
            		"SP_N": recv.extraData.name, 
            		"SP_K": recv.extraData.key
            	};

            	Logger.debug("SessionParam_Name = " + sessionParams.SP_N);
            	Logger.debug("SessionParam_Key = " + sessionParams.SP_K);
            	
            	this.set({ "session": sessionParams });
            	
            	return sessionParams;
            }
		}
		,
		/**
		 * 손님 권한의 세션을 생성한다.
		 * 
		 * @return { jqXHR } 게스트 세션 생성에 성공하는 경우 세션 파라메터를 반환한다. 실패한 경우 undefined 를 반환한다.
		 */
		makeGuestSession : function()
		{
			var self = this;
			var	host = this.get("host");
			var actors = this.get("actors");
			var baroapp = this.get("baroapp");
			
			/*
			return $.ajax(
			{
                type: 'GET',
                url:  host+'/'+baroapp+'/guest',
                dataType: "json",
                cache: false,
                crossDomain: true,
                data:
                {
                	svc_id : actors.svc_id,
                	con_id : actors.con_id
                }
            })
            */
            return this._get(host+'/'+baroapp+'/guest', 
            {
            	svc_id : actors.svc_id,
            	con_id : actors.con_id
            })
            .then(function(data, textStatus, jqXHR)
            {
            	self.set(
            	{
            		user: 
            		{
            			id: 'guest', 
            			name: null, 
            			md5pwd: null 
            		}
            		, session: null
            	});
            	return self._saveSession( data );
            });
		}
		,
		/**
		 * 사용자 세션을 생성한다.
		 * 
		 * @param user_id		{ String } 사용자 아이디.
		 * @param user_pwd		{ String } 사용자 비밀번호. md5 엔코딩 형식으로 지정한다.
		 * @return { jqXHR } 사용자 세션 생성에 성공하는 경우 세션 파라메터를 반환한다. 실패한 경우 undefined 를 반환한다.
		 */
		makeUserSession : function(user_id, user_pwd)
		{
			var self = this;
			var	host = this.get("host");
			var actors = this.get("actors");
			var baroapp = this.get("baroapp");
			var guestSession = this.get("session");
			
			if( guestSession == null )
			{
				Logger.warn("makeUserSession() - Invalid parameter[session]");
				return AuthClient.promiseResult( AResult.Helper.INVALID_PARAMETERS("Invalid parameter[session]").toJSON() );
			}
			/*
			return $.ajax(
			{
                type: 'POST',
                url:  host+'/'+baroapp+'/signin?svc_id='+actors.svc_id+"&con_id="+actors.con_id,
                contentType: 'application/json; charset=utf-8',
                dataType: "json",
                cache: false,
                crossDomain: true,
                headers:
                {
                	"sp-name" : guestSession.SP_N,
                	"sp-key" : guestSession.SP_K
                }
            	,
                data: JSON.stringify(
            	{
                	id : user_id,
                	pwd : user_pwd
                })
            })
            */
			return this._post
			(
				host+'/'+baroapp+'/signin?svc_id='+actors.svc_id+"&con_id="+actors.con_id
				,
				{
	            	id : user_id,
	            	pwd : user_pwd
	            }
	        )
            .then(function(data, textStatus, jqXHR)
            {
            	return self._saveSession( data );
            })
            .then(function(userSession)
            {
            	if( userSession == undefined )
            	{
            		Logger.debug("makeUserSession() - invalid user");
            		return;
            	}
            	
            	self.set(
            	{
            		user: 
            		{
            			id: user_id, 
            			name: null, 
            			md5pwd: user_pwd 
            		}
            	});
            	
            	return userSession;
            });
		}
		,
		/**
		 * 사용자 프로파일을 가져온다.
		 * 
		 * @return { jqXHR } 사용자 정보 조회에 성공한 경우 사용자 정보를 반환한다. 실패한 경우 undefined 를 반환한다.
		 */
		fetchUserProfile : function()
        {
			var self = this;
			var	host = this.get("host");
			var	user = this.get("user");
			var userSession = this.get("session");

			if( userSession == null )
			{
				Logger.warn("fetchUserProfile() - Invalid parameter[session]");
				return AuthClient.promiseResult( AResult.Helper.INVALID_PARAMETERS("Invalid parameter[session]").toJSON() );
			}
			
        	Logger.debug("fetchUserProfile.session_params : "+JSON.stringify(userSession));
        	
        	/*
            return $.ajax(
            {
                type: 'GET',
        		url : host+"/profile",
                dataType: "json",
                cache: false,
                crossDomain: true, 
                headers:
                {
                	"sp-name" : userSession.SP_N,
                	"sp-key" : userSession.SP_K
                }
            	,
            	success: function (data)
            	{
            		
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                }
            })
            */
        	return this._get( host+"/profile" )
            .then(function(data, textStatus, jqXHR)
            {
            	Logger.debug("fetchUserProfile() - result : "+JSON.stringify(data));
        		
                if(data && data.resultCode == 0)
                {
                	self.set(
                	{
                		user: 
                		{ 
                			id: data.extraData.user.screenId, 
                			name: data.extraData.user.name, 
                			md5pwd: user.md5pwd 
                		}
                	});
                	
                	return self.get("user");
             	}
            });
        }		
		,
		/**
		 * 세션을 파기한다.
		 * 
		 * @return { jqXHR }
		 */
		signOut : function()
        {
			var self = this;
			var	host = this.get("host");
			var actors = this.get("actors");
			var baroapp = this.get("baroapp");
			var userSession = this.get("session");
			
			if( userSession == null )
			{
				Logger.warn("signOut() - Invalid parameter[session]");
				return AuthClient.promiseResult( AResult.Helper.INVALID_PARAMETERS("Invalid parameter[session]").toJSON() );
			}

			// 전역 핸들러가 동작되지 않도록 결과를 자체적으로 success 함수를 핸들링한다.
		  	return $.ajax(
		  	{
                type: 'GET',
                url:  host + "/"+baroapp+"/signout",
				headers: 
				{ 
					"sp-name": userSession.SP_N, 
					"sp-key": userSession.SP_K 
				},                
        		dataType: "json",
                cache: false,
                crossDomain: true,
                complete: function(jqXHR, textStatus)
        		{
                	// 전역 핸들러가 동작되지 않도록 결과를 자체적으로 핸들링한다.
        			Logger.info("signOut() - status : "+textStatus);
        			
        			if( textStatus != "success" )
        			{
        				if( jqXHR.status == 401 )
        				{
        					Logger.error("signOut() - Error 401");
        				}
        				else
        				{
        					UCMS.getApplication().onAjaxError(jqXHR, textStatus);
        				}
        			}
        		}
            })
            .always(function()
            {
            	self.set({ user: null, session: null });
            	
            	Logger.info("signOut() - Completed to sign out from Mobile Oven.");
            });
        }
		,
		/**
		 * 사용자 운영환경에 맞는 세션을 생성한다.
		 * 
		 * @return { jqXHR } 생성 성공시 true 반환
		 */
		initSession: function()
		{
			var self = this;
			var user = this.get("user") || { md5pwd: null };
			
			Logger.info('initSession() - start! user : '+JSON.stringify( user ));

			if( typeof user.md5pwd != "string" || user.md5pwd.trim().length == 0 )
			{
				Logger.debug("Trying to create a guest session for user!");
				
				return this.makeGuestSession()
				.then(function(guestSession)
				{
					// 생성 성공시 true, 실패시 false
					return ( guestSession != undefined );
				});
			}
			else
			{
				Logger.debug("Trying to create a guest session for user!");
				
				return this.makeGuestSession()
				.then(function(guestSession)
				{
					if( guestSession != undefined )
					{
						Logger.debug("Trying to create a user session...");
						
						return self.makeUserSession( user.id, user.md5pwd )
						.then(function(userSession)
						{
							// 사용자 프로필 조회
							return self.fetchUserProfile()
							.then(function(user)
							{
								// 생성 성공시 true, 실패시 false
								return ( user != undefined );
							});
						})
					}
				});
			}
		}
	});
	
	return AuthClient;
});