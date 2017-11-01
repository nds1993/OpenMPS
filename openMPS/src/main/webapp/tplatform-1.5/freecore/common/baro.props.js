/**
 * Project : UCMS( Unified Contents Messaging Solution )
 *
 * Copyright (c) 2013, 2014 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */
 
define
(
[]
,
function()
{
	/**
	 * 빵앱 운영에 필요한 속성값을 저장한다.
	 * 속성값의 변경 이벤트를 수신하기 위해서는 Backbone.Events.on 으로 이벤트를 등록한다.
	 * 
	 *  본 객체가 발생하는 이벤트는 다음과 같다.
	 *  
	 *  change:session_params		setSessionParams() 가 호출될 때 발생된다.
	 *  change:user					setUser() 가 호출될 때 발생된다.
	 *  change:baangapp_info		setAppInfo() 가 호출될 때 발생된다.
	 *  change:baang_member			setBaangMember() 가 호출될 때 발생된다.
	 *  change:conn_svc				setConnectedService() 가 호출될 때 발생된다.
	 *  change:alert				setAlertStates() 가 호출될 때 발생된다.
	 */
	var	BaroProps =
	{
		_startPage : null
		,
		init : function(options)
		{
			options = options || {};
			
			//
			BaroProps.setDebugMode( options.debug );
			BaroProps.setStartPage( options.startPage );
			BaroProps.setHosts( options.hosts );
			BaroProps.setActors( options.actors );
			BaroProps.setSessionParams( options.session_params );
			BaroProps.setSessionData();
			BaroProps.setAgentId( options.token );
			BaroProps.setUser(options.user);
			BaroProps.setAppInfo(options.app || options.baangapp || options.baroapp);
			BaroProps.setBaangMember();
			BaroProps.setApiKey(options.apiKeys);
		},
		
		clear : function()
		{
			BaroProps.init(
			{
				debug: BaroProps.getDebugMode(),
				actors: BaroProps.getActors()
			});
			BaroProps.setUser();
		},
		
		_trigger : function(evtName, params)
		{
			BaroProps.trigger("change:"+evtName, params);
		},
		
		//
		// 운영환경 설정
		//
		setDebugMode : function(bDebug)
		{
			UCMS.SPA.AppMain.getAppProps().setProp("debug", bDebug || false);
		},
		getDebugMode : function()
		{
			return UCMS.SPA.AppMain.getAppProps().getProp("debug");
		},
		setStartPage : function(pageId)
		{
			UCMS.SPA.AppMain.getAppProps().setProp("startPage", pageId || null);
		},
		getStartPage : function()
		{
			return UCMS.SPA.AppMain.getAppProps().getProp("startPage");
		},		
		
		//
		// 서비스 액터 정보
		//
		setActors : function(params)
		{
			var values = _.extend(
			{
				svc_id: null, 
				con_id: null 
			}, 
			params);
        	
        	UCMS.SPA.AppMain.getAppProps().setProp("actors", values);
		},
		getActors : function()
		{
			return UCMS.SPA.AppMain.getAppProps().getProp("actors");
		},
		
		//
		// 서비스 호스트 경로
		//
		setHosts : function(params)
		{
			var values = _.extend(
			{
				resource: null, 
				file: null, 
				app: null, 
				api: null, 
				oauth: null,
				unime: null,
				oven: null,
				store: null,
				launcher: null
			},
			params);
        	
        	UCMS.SPA.AppMain.getAppProps().setProp("hosts", values);
		},
		getHosts : function()
		{
			return UCMS.SPA.AppMain.getAppProps().getProp("hosts");
		},
		
		//
		// 세션 정보
		//
		setSessionParams : function(params)
		{
			var values = _.extend(
			{
				SP_N: null, 
				SP_K: null 
			}, 
			params);
			
			if(typeof values.SP_N == "string")
			{
				if( values.SP_N.trim().length == 0 )
				{
					values.SP_N = null;
				}
			}
			if(typeof values.SP_K == "string")
			{
				if( values.SP_K.trim().length == 0 )
				{
					values.SP_K = null;
				}
			}
        	
        	UCMS.SPA.AppMain.getAppProps().setProp("session_params", values);
        	
        	//
        	BaroProps._trigger("session_params", values);
		},
		
		/**
		 * 세션 데이타를 얻는다.
		 */
		getSessionParams : function()
		{
			return UCMS.SPA.AppMain.getAppProps().getProp("session_params");
		},
		setSessionData : function(data)
		{
        	UCMS.SPA.AppMain.getAppProps().setProp("session_data", data || { properties: {} });
        	
        	if( typeof data != "undefined" && data.client )
        	{
        		BaroProps.setUser(
    			{
    				no: data.client.no,
    				id: data.client.screenId,
    				name: data.client.name
    			});
        	}
        	else
        	{
        		// clear
        		BaroProps.setUser();
        	}
		},
		getSessionData : function()
		{
			return UCMS.SPA.AppMain.getAppProps().getProp("session_data");
		},
		
		/**
		 * 세션 데이타에 저장된 Access Token 을 얻는다.
		 */
		getAccessToken : function()
		{
			var data = BaroProps.getSessionData();
			
			return data.client.accessToken || null;
		},
		/**
		 * 세션 데이타에 저장된 UniMe Token 을 얻는다.
		 */
		getUniMeToken : function()
		{
			var data = BaroProps.getSessionData();
			
			return data.properties.unimeToken || null;
		},
		setUniMeToken : function(token)
		{
			var data = BaroProps.getSessionData();
			
			data.properties.unimeToken = token || null;
		},
		getAgentInfo : function()
		{
			var data = BaroProps.getSessionData();
			
			return data.properties.agent || null;
		},
		setAgentInfo : function(agent)
		{
			var params =
			{
				host_ip: null,
				port: 0,
				agent_id: null
			};
			
			var data = BaroProps.getSessionData();
			
			data.properties.agent = _.extend(params, agent);
		},
		/**
		 * 플랫폼 푸시 토큰을 저장한다.
		 */
		setAgentId : function(id)
		{
			var data = BaroProps.getSessionData();
			
			if(! data.properties.agent )
			{
				BaroProps.setAgentInfo();
			}
			
			data.properties.agent.agent_id = id || null;
		},
		getAgentId : function()
		{
			var data = BaroProps.getSessionData();
			
			if(! data.properties.agent )
			{
				BaroProps.setAgentInfo();
			}
			
			return data.properties.agent.agent_id;
		},
		
		//
		// 사용자 정보
		//
		setUser : function(params)
		{
			var values = _.extend(
			{
				no: 0,
				id: null, 
				name: null,
				md5pwd: null
			}, 
			params);
        	
        	UCMS.SPA.AppMain.getAppProps().setProp("user", values);
        	
        	//
        	BaroProps._trigger("user", values);
		},
		getUser : function()
		{
			return UCMS.SPA.AppMain.getAppProps().getProp("user");
		},
		
		//
		// 빵앱 정보
		//
		setAppInfo : function(params)
		{
			var values = _.extend(
			{
                id: null,
                no: 0,
                state: null,
                name: null,
                icon: null,
                desc: null,
                owner: {
                	id: null,
                	name: null,
                	contact: {
                		tel: null,
                		hp: null,
                		email: null,
                		web: null,
                		facebook: null,
                		twitter: null,
                		kakaostory: null,
                		instagram: null
                	}
                },
                staff: {
                	no: 0,
                    user: null,
                    baroNo: 0,
                    roleScopes: null,
                    roleGroup: 0,
                    roleAction: 0,
                    dept: null,
                    tel: null,
                    pos: null,
                    createDate: 0,
                    stateActive: 0
                },
                svc_id: null,
                con_id: null
			}, 
			params);
        	
        	UCMS.SPA.AppMain.getAppProps().setProp("baangapp_info", values);
        	
        	//
        	BaroProps._trigger("baangapp_info", values);
		},
		getAppInfo : function()
		{
			return UCMS.SPA.AppMain.getAppProps().getProp("baangapp_info");
		},
		
		// 빵앱 회원 상태
		setBaangMember : function(bMember)
		{
			var values = bMember || false;
			UCMS.SPA.AppMain.getAppProps().setProp("baang_member", values);
			
			//
			BaroProps._trigger("baang_member", values);
		},
		getBaangMember : function()
		{
			return UCMS.SPA.AppMain.getAppProps().getProp("baang_member") || false;
		},
		
		//
		// 연결된 서비스 정보
		//
		setConnectedService : function(connected)
		{
			UCMS.SPA.AppMain.getAppProps().setProp("conn_svc", connected);
			
			//
			BaroProps._trigger("conn_svc", connected);
		},
		getConnectedService : function()
		{
			return UCMS.SPA.AppMain.getAppProps().getProp("conn_svc") || {};
		},
		isConnected : function(svc_id)
		{
			var svc = BaroProps.getConnectedService();
			
			return ( typeof svc[svc_id] != "undefined" );
		},
		
		//
		// 알림 서비스 활성화 상태
		//
		setAlertState : function(method, state, trigger)
		{
			var baang_state = BaroProps.getAlertState();
			
			if( typeof method != "undefined" )
			{
				baang_state[method] = (state == 1);
			}
			
			UCMS.SPA.AppMain.getAppProps().setProp("alert", baang_state);
			
			if( trigger != false )
			{
				//
				// false 가 아닌 경우 상태 변경 이벤트를 발생시킨다.
				//
				BaroProps._trigger("alert", state);
			}
		},
		/**
		 * 푸시 상태를 조회한다.
		 * 최초 상태에서는 푸시만 활성화 상태로 반환된다.
		 */
		getAlertState : function()
		{
			return UCMS.SPA.AppMain.getAppProps().getProp("alert") || { noti: 1, email: 0, sms: 0 };
		},
		setAlertStates : function(states)
		{
			for(var method in states)
    		{
				BaroProps.setAlertState( method, states[method], false );
    		}
			
			BaroProps._trigger("alert", states);
		},
		
		getApiKey : function()
		{
			return UCMS.SPA.AppMain.getAppProps().getProp("apikeys") || {};
		},
		
		/**
		 * Open API 사용을 위해 필요한 API 키를 설정한다.
		 * API 키 이름은 해당 API 를 사용하는 위젯에서 지정한 이름으로 설정한다.
		 * 
		 * Tour API : tourapi
		 * Daum Map API : daummap
		 * Facebook : facebook
		 * Daum Kakao Story : kakostory
		 */
		setApiKey : function(apikeys)
		{
			UCMS.SPA.AppMain.getAppProps().setProp(
				"apikeys",
				_.extend( this.getApiKey(), apikeys ));
		},
		
		/**
		 * 읽지않은 푸시 메시지 개수를 관리한다.
		 */
		setUnreadPushMsg : function(cnt)
		{
			this.set("unread:push", cnt);
			BaroProps._trigger("unread:push", cnt);
		},
		getUnreadPushMsg : function()
		{
			return this.get("unread:push");
		},
		
		//
		// 속성값을 쿠키로 저장한다.
		//
		save : function()
		{
			UCMS.SPA.AppMain.getAppProps().save();
		}
	};
	
	/**
	 * 메모리에만 배치되는 저장소
	 */
	var MemAttr = 
	{
		_attributes : {},
		
		/**
		 * 지정한 이름에 해당하는 속성값을 얻는다.
		 * 
		 * @param {string} name - 데이타를 얻을 속성 이름
		 */
		get : function(name)
		{
			return this._attributes[name];
		},
		
		/**
		 * 속성을 저장한다.
		 * 
		 * @param {string|object} name - 속성 이름 또는 저장할 객체. 객체가 지정되는 경우 value 는 사용되지 않는다.
		 * @param {object|string|number|array} value - 속성 이름으로 저장될 데이타.
		 */
		set : function(name, value)
		{
			switch(typeof name)
			{
			case 'string':
				this._attributes[name] = value;
				break;
				
			case 'object':
				_.extend(this._attributes, name);
				break;
			}
		},
		
		/**
		 * 저장된 모든 속성값을 json 형식으로 얻는다.
		 */
		toJSON : function()
		{
			return _.extend({}, this._attributes);
		}
	};
	
	_.extend(BaroProps, Backbone.Events, MemAttr);
	
	return BaroProps;
});