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
	var UniMeClient = ClientBase.extend(
	{
		initialize: function()
		{
			UniMeClient.__super__.initialize.apply( this, arguments );
			Logger.debug("UniMeClient.initialize()");
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
				host: BaroProps.getHosts().unime,
				actors: BaroProps.getActors(),
				session: BaroProps.getSessionParams(),
				user: BaroProps.getUser(),
				token: BaroProps.getUniMeToken(),
				baroappId: BaroProps.getAppInfo().id || null,
				baroappNo: BaroProps.getAppInfo().no || null,
				unread: 0
			});
		}
		,
		/**
		 * UniMe Token 을 생성한다.
		 * 
		 * @param platformId		{ Number } 플랫폼 식별자. 1 : UniMe, 2 : iOS, 3 : Android
		 * @param platformToken		{ String } 플랫폼에서 할당 받은 Push 토큰. iOS, Android 인 경우에만 지정한다.
		 * @return { jqXHR } function(ResultEx){}
		 */
		createToken : function(platformId, platformToken)
		{
			var self = this;
		  	var actors = this.get("actors");
		  	var user = this.get("user");
		  	var sessionParams = this.get("session");
			var apiPath = this.get("host") + "/token/"+actors.svc_id+"/"+actors.con_id+"/"+( user.id || "guest" );
			var baroId = this.get("baroappId");
			
			if(typeof baroId === "string")
			{
				apiPath += "/"+baroId;
			}
			
			if(typeof platformToken !== "string")
			{
				// 토큰이 없는 경우는 무조건 UniMe 모드로 동작됨.
				platformId = 1;
			}

			return $.ajax(
			{
				type: "POST",
				url: apiPath,
				headers: 
				{
					"sp-name":sessionParams.SP_N, 
					"sp-key":sessionParams.SP_K 
				},
                contentType: 'application/json; charset=utf-8',				
				processData: false,
				crossDomain: true,
				dataType: "json",
				data: JSON.stringify(
				{
					platform: platformId,
					token: platformToken || null
				}),
			    error: function( XHR, textStatus, errorThrown )
			    {
			    	Logger.error("error status: "+textStatus);
			    },
			    success: function(data, textStatus, jqXHR)
			    {
			    	Logger.debug(data);
			    	self.set("token", data.extraData);
			    	
			    	return data;
			    }
			});
		}
		,
		/**
		 * 활성화된 UniMe 토큰을 제거한다.
		 */
		removeToken : function()
		{
			var self = this;
		  	var actors = this.get("actors");
		  	var user = this.get("user");
		  	var sessionParams = this.get("session");
			var baroId = this.get("baroappId");
			var token = this.get("token");
			var apiPath = this.get("host") + "/token/"+actors.svc_id+"/"+actors.con_id+"/"+( user.id || "guest" )+"/"+baroId;

			return $.ajax(
			{
				type: "DELETE",
				url: apiPath,
				headers: 
				{
					"sp-name":sessionParams.SP_N, 
					"sp-key":sessionParams.SP_K 
				},
                contentType: 'application/json; charset=utf-8',				
				processData: false,
				crossDomain: true,
				dataType: "json",
				data: JSON.stringify(
				{
					token: token
				}),
			    error: function( XHR, textStatus, errorThrown )
			    {
			    	Logger.error("error status: "+textStatus);
			    },
			    success: function(data, textStatus, jqXHR)
			    {
			    	Logger.debug(data);
			    	self.set("token", null);
			    }
			});
		}
		,
		/**
		 * 소셜로 발행한다.
		 * 
		 * @param msg			{ String } 발행할 메시지. 본문에 http 또는 https 가 존재하는 경우 링크는 분리되어 발행된다. 
		 * @param bridges		{ Array } 발행 대상이 되는 소셜 서비스 배얄. facebook, twitter, kakaotalk 중 연결된 서비스를 지정한다.
		 * 
		 * @return { jqXHR }
		 * @see https://api.jquery.com/jQuery.ajax/#jqXHR
		 */
		socialPublishing: function(msg, bridges)
		{
			var user = this.get("user");
			return this._unimeTask( msg, bridges, [ user.id ], ["SNS_PUB"] );
		}
		,
		/**
		 * 모바일 플랫폼 푸시 메시지를 발송한다.
		 * 
		 * @param msg			{ String } 발행할 메시지. 본문에 http 또는 https 가 존재하는 경우 링크는 분리되어 발행된다.
		 * @param recipients	{ Array } 수신자 ID 배열. 모든 회원(익명회원 포함)을 대상으로 발송하는 경우 null 지정.
		 * @param methods		{ Array } 전송방식의 배열.
		 * @param recvBaro_id	{ string } 수신하는 바로앱 식별자. 본 파라메터가 UniMe Client 내부에 보관되는 baro_id 보다 우선적으로 사용된다.
		 * 
		 * @return { jqXHR }
		 * @see https://api.jquery.com/jQuery.ajax/#jqXHR
		 * 
		 * 2016.08.19
		 * jerry
		 * recvBaro_id : 메시지를 수신할 바로앱아이디는 전송하는곳에서 지정할 경우.
		 * 바로앱에서 쿠커로 메시지 발송시. recvBaro_id = 'cooker'
		 */
		mobilePushing: function(msg, recipients, methods, recvBaro_id)
		{
			if( methods == undefined )
			{
				methods = ["GCM", "APN"];
			}
			
			return this._unimeTask( msg, [], recipients, methods, recvBaro_id );
		}
		,
		_unimeTask: function(msg, bridges, recipients, methods, recvBaro_id)
		{
			var unime_token = BaroProps.getUniMeToken();
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var baroId = this.get("baroappId");
			var apiPath = this.get("host")+'/task/'+actors.svc_id+'/'+actors.con_id+'/'+user.id + "/" + baroId;
		
			Logger.debug("API : "+apiPath);
			Logger.debug("_unimeTask() - session_params : "+JSON.stringify(session));
			
			var postBody = JSON.stringify(
					{
						body: msg,
						select:
						{
							svc : actors.svc_id,
							app: actors.con_id,
							baang: recvBaro_id || baroId,
							bridged: bridges,
							recipient: recipients
						},
						method: methods,
						token : unime_token
					});
			
			Logger.debug("postBody : "+postBody);
			
			return $.ajax(
			{
                type: 'POST',
                url:  apiPath,
                cache: false,
                crossDomain: true,
                headers:
                {
                	"sp-name" : session.SP_N,
                	"sp-key" : session.SP_K
                },
                contentType: 'application/json; charset=utf-8',
                dataType: "json",
                data: postBody
				,
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("_unimeTask() - Error : "+textStatus);
                }
            });
		}
		,
		/**
		 * 지정된 알림 항목에 대해서 읽음상태를 피드백한다.
		 */
		feedbackViewed : function(cid)
		{
		  	var actors = this.get("actors");
		  	var user = this.get("user");
		  	var session = this.get("session");        	
    		var apiPath = this.get("host") + "/msg/"+ cid +"/view";

    		Logger.debug("feedbackViewed() - apiPath : "+apiPath);

        	return $.ajax({
                type: "POST",
                url: apiPath,
                cache: false,
                crossDomain: true,
                headers:
                {
                	"sp-name" : session.SP_N,
                	"sp-key" : session.SP_K
                },
                contentType: 'application/json; charset=utf-8',
                dataType: "json",
                success: function (data)
                {
                	Logger.debug("feedbackViewed() - cid : "+cid+", result: "+JSON.stringify(data));
                },
                error: function (error) 
                {
                	Logger.error("feedbackViewed() - "+JSON.stringify(error));
                }
            });
		},
		/**
		 * 읽지 않은 메시지 개수를 구한다.
		 * 
		 * @return { jqXHR }
		 */
		getUnreadCount : function()
		{
			var self = this;
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var baroId = this.get("baroappId");
			var apiPath = this.get("host")+'/msg/'+actors.svc_id+'/'+actors.con_id+'/'+( user.id || "guest");
			
			if(typeof baroId === "string")
			{
				apiPath += "/" + baroId + "/received/count";
			}
			else
			{
				apiPath += "/received/count";
			}
		
			Logger.debug("API : "+apiPath);
			Logger.debug("getUnreadCount() - session_params : "+JSON.stringify(session));
			
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
                	Logger.debug("getUnreadCount() result: "+JSON.stringify(data));
                	
                	self.set("unread", data.extraData.cnt || 0);
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("getUnreadCount() - Error : "+textStatus);
                }
            });
		},
		/**
		 * 수신 메시지를 조회한다.
		 */
		getReceivedMsg : function(size, after)
		{
			return this._fetchMessage("received", size, after);
		},
		/**
		 * 송신 메시지를 조회한다.
		 */
		getSendedMsg : function(size, after)
		{
			return this._fetchMessage("sended", size, after);
		},
		/**
		 * 메시지를 조회한다.
		 * 
		 * @param action	{ String } 송수신 동작 식별자. 송신 메시지 : sended, 수신 메시지 : received
		 * @param size		{ Number } 조회 메시지 최대 개수
		 * @param after		{ String } 조회 시작 항목의 식별자
		 * @return { jqXHR }
		 */
		_fetchMessage : function(action, size, after)
		{
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var baroId = this.get("baroappId");
			var apiPath = this.get("host")+'/msg/'+actors.svc_id+'/'+actors.con_id+'/'+user.id;
			
			if(typeof baroId === "string")
			{
				apiPath += "/" + baroId + "/" + action;
			}
			else
			{
				apiPath += "/" + action;
			}
			
			if(typeof after === "string")
			{
				apiPath += "after="+after;
			}
			
			if(typeof size === "number")
			{
				apiPath += "size="+size;
			}
		
			Logger.debug("API : "+apiPath);
			Logger.debug("_fetchMessage() - session_params : "+JSON.stringify(session));
			
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
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("_fetchMessage() - Error : "+textStatus);
                }
            });
		}
		,
		/**
		 * 2016.06.09
		 * jerry
		 * 비콘 메시지 생성.
		 * */
		createBeaconMessage: function(postdata)
		{
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var baroId = this.get("baroappId");
			var apiPath = this.get("host")+'/beacon/'+actors.svc_id+'/'+actors.con_id+'/' + baroId + "/msg";
		
			Logger.debug("API : "+apiPath);
			Logger.debug("createBeaconMessage() - session_params : "+JSON.stringify(session));
			Logger.debug(postdata);
			
			return $.ajax(
			{
                type: 'POST',
                url:  apiPath,
                cache: false,
                crossDomain: true,
                headers:
                {
                	"sp-name" : session.SP_N,
                	"sp-key" : session.SP_K
                },
                contentType: 'application/json; charset=utf-8',
                dataType: "json",
                data: JSON.stringify(postdata)
				,
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("_unimeTask() - Error : "+textStatus);
                }
            });
		}
		,
		removeBeaconMessage: function(msg_no)
		{
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var baroId = this.get("baroappId");
			var apiPath = this.get("host")+'/beacon/'+actors.svc_id+'/'+actors.con_id+'/' + baroId + "/msg/" + msg_no;
		
			Logger.debug("API : "+apiPath);
			
			return $.ajax(
			{
                type: 'DELETE',
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
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("_unimeTask() - Error : "+textStatus);
                }
            });
		}
		,
		getBeaconMessage: function(uuid)
		{
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var baroId = this.get("baroappId");
			var apiPath = this.get("host")+'/beacon/'+actors.svc_id+'/'+actors.con_id+'/' + baroId + "/msg";
			
			if(typeof uuid == 'string')
			{
				apiPath += '/' + uuid;
			}
		
			Logger.debug("API : "+apiPath);
			Logger.debug("getBeaconMessage() - session_params : "+JSON.stringify(session));
			
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
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("_unimeTask() - Error : "+textStatus);
                }
            });
		}
		,
		getBeaconMessageByType: function(ctype)
		{
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var baroId = this.get("baroappId");
			var apiPath = this.get("host")+'/beacon/'+actors.svc_id+'/'+actors.con_id+'/' + baroId + "/msg/type/" + ctype;
		
			Logger.debug("API : "+apiPath);
			Logger.debug("getBeaconMessage() - session_params : "+JSON.stringify(session));
			
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
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("_unimeTask() - Error : "+textStatus);
                }
            });
		}
		,
		removeBeaconMessageByType: function(ctype)
		{
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var baroId = this.get("baroappId");
			var apiPath = this.get("host")+'/beacon/'+actors.svc_id+'/'+actors.con_id+'/' + baroId + "/msg/type/" + ctype;
		
			Logger.debug("API : "+apiPath);
			
			return $.ajax(
			{
                type: 'DELETE',
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
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("_unimeTask() - Error : "+textStatus);
                }
            });
		}
		,
		/**
		 * 바로앱별 사용자 알림 플레그를 설정한다.
		 * 처리 결과가 본 객체 속성 "alert_state" 에 { method: alert_activie } 상태로 저장된다.
		 * 
		 * @param method			{ String } noti, email, sms 중 하나를 지정한다.
		 * @param alert_state		{ Boolean } 활성화 상태로 셋하는 경우 true, 그렇지 않은 경우 false
		 * @return { $.Promise }
		 */
		setAlertState: function(method, alert_state)
		{
			var self = this;
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var baroNo = this.get("baroappNo");
			var apiPath = this.get("host")+'/alert/'+actors.svc_id+'/'+actors.con_id+'/'+( user.id || "guest" )+"/"+baroNo+"?method="+method;
			
			Logger.info("API : "+apiPath);
			Logger.debug("session_params : "+JSON.stringify(session));
			
			return $.ajax(
			{
                type: alert_state?'POST':'DELETE',
                url:  apiPath,
                dataType: "json",
                cache: false,
                crossDomain: true,
                headers:
                {
                	"sp-name" : session.SP_N,
                	"sp-key" : session.SP_K
                },
                success: function (data)
                {
                	Logger.debug("setAlertState() - Result : "+JSON.stringify(data));

	            	if( typeof data != "undefined" && data.resultCode == 0 )
	            	{
	            		var alert = {};
	            		alert[method] = alert_state;
	            		self.set("alert_state", _.extend({}, self.get("alert_state"), alert));
	            	}
	            	
	            	return data;
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("setAlertState() - Error : "+textStatus);
                }
            });
		}
		,
		getAlertState: function()
		{
			var self = this;
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var baroNo = this.get("baroappNo");
			var apiPath = this.get("host")+'/alert/'+actors.svc_id+'/'+actors.con_id+'/'+( user.id || "guest" )+"/"+baroNo;
			
			Logger.info("API : "+apiPath);
			Logger.debug("session_params : "+JSON.stringify(session));
			
			return $.ajax(
			{
                type: 'GET',
                url:  apiPath,
                dataType: "json",
                cache: false,
                crossDomain: true,
                headers:
                {
                	"sp-name" : session.SP_N,
                	"sp-key" : session.SP_K
                },
                success: function (data)
                {
                	Logger.debug("getAlertState() - Result : "+JSON.stringify(data));

	            	if( typeof data != "undefined" && data.resultCode == 0 )
	            	{
	            		self.set("alert_state", data.extraData || { noti: null, email: null, sms: null } );
	            	}
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("getAlertState() - Error : "+textStatus);
                }
            });
		}
		,
		/**
		 * 2017.04
		 * jerry
		 * 연계서비스 저장
		 * */
		addUnimeService: function(type, postdata)
		{
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var baroId = this.get("baroappId");
			var apiPath = this.get("host")+'/service/'+actors.svc_id+'/'+actors.con_id+'/' + type;
		
			Logger.debug("API : "+apiPath);
			Logger.debug("addUnimeService() - session_params : "+JSON.stringify(session));
			Logger.debug(postdata);
			
			return $.ajax(
			{
                type: 'POST',
                url:  apiPath,
                cache: false,
                crossDomain: true,
                headers:
                {
                	"sp-name" : session.SP_N,
                	"sp-key" : session.SP_K
                },
                contentType: 'application/json; charset=utf-8',
                dataType: "json",
                data: JSON.stringify(postdata)
				,
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("_unimeTask() - Error : "+textStatus);
                }
            });
		}
		,
		/**
		 * 2017.04
		 * jerry
		 * 연계서비스 삭제
		 * */
		removeUnimeService: function(type, service_no)
		{
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var baroId = this.get("baroappId");
			var apiPath = this.get("host")+'/service/'+service_no;
		
			Logger.debug("API : "+apiPath);
			Logger.debug("addUnimeService() - session_params : "+JSON.stringify(session));
			Logger.debug(postdata);
			
			return $.ajax(
			{
                type: 'DELETE',
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
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("_unimeTask() - Error : "+textStatus);
                }
            });
		}
		,
		/**
		 * 2017.04
		 * jerry
		 * 연계서비스 조회
		 * */
		getUnimeService: function()
		{
			var actors = this.get("actors");
			var user = this.get("user");
			var session = this.get("session");
			var baroId = this.get("baroappId");
			var apiPath = this.get("host")+'/service/'+actors.svc_id+'/'+actors.con_id;
		
			Logger.debug("API : "+apiPath);
			Logger.debug("addUnimeService() - session_params : "+JSON.stringify(session));
			Logger.debug(postdata);
			
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
                error: function(XHR, textStatus, errorThrown) 
                {
                	Logger.error("_unimeTask() - Error : "+textStatus);
                }
            });
		}
	});
	
	return UniMeClient;
});