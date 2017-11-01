/**
 * Project : Trunk-Platform SPA Platform
 *
 * Copyright (c) 2016 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

define(
[
	"Logger", "BaroProps", "NLSManager"
]
,
function(Logger, BaroProps, NLSManager)
{
	/**
	 * 모든 위젯의 베이스 모듈로써, 위젯으로써 동작되는데 필요한 기능들을 구현한다.
	 */
	var BaroPanelBase = UCMS.SPA.Panel.extend(
	{
		/**
		 * 위젯 베이스 생성자. 제공되는 파라메터를 취득한다.
		 */
		initialize: function(options)
		{
			UCMS.SPA.Panel.prototype.initialize.apply( this, arguments );
			
			//
			this._params = options || null;
			this._initNLSresource();
		}
		,
		/**
		 * 특정 위젯에게 명령을 전달한다.
		 * 이벤트가 처리완료된 경우 부모로 전파되지 않는다.
		 * 
		 * @param {string} itemId - 이벤트를 수신하는 위젯 식별자
		 * @param {string} cmd - 명령어
		 * @param {object} params - 명령어 파라메터
		 */
		sendEvent: function(itemId, cmd, params)
		{
			this.trigger( BaroPanelBase.WIDGET_EVENT, { itemId: itemId, cmd: cmd, params: params });
		}
		,
		/**
		 * 모든 폼 아이템에게 명령을 전달한다.
		 * 처리 결과와 관계 없이 이벤트는 모두 부모로 전파된다.
		 * 
		 * @param {string} cmd - 명령어. 해당 명령어를 처리하는 모든 위젯이 실행된다.
		 * @param {object} params - 명령어 파라메터
		 */
		broadcastEvent: function(cmd, params)
		{
			this.trigger( BaroPanelBase.WIDGET_EVENT, { cmd: cmd, params: params });
		}
		,
		/**
		 * 아이템 이벤트를 처리한다.
		 * 다른 폼 아이템이 sendEvent(), broadcastEvent() 으로 요청한 이벤트를 처리한다.
		 * @param {object} event - sendEvent() 로 발생된 이벤트인 경우 { itemId: itemId, cmd: cmd, params: params }
		 * 							broadcastEvent() 로 발생된 이벤트인 경우 { cmd: cmd, params: params }
		 */
		onEventHandler: function(event)
		{
			//Logger.debug("["+this.getParam("id")+"] BaroPanelBase::onEventHandler("+event.cmd+") - Not supported a event.");
		}
		,
		/**
		 * 위젯 초기화시 제공된 파라메터 셋에서 지정한 이름의 필드 값을 얻는다.
		 * 
		 * @param name	{ String } 파라메터 이름.
		 * @returns		{ Object } 파라메터의 값. 파라메터가 없는 경우 null.
		 */
		getParam: function(name)
		{
			if( this._params == null )
			{
				return null;
			}
			
			var retVal = this._params[ name ];
			
			if( typeof retVal == "undefined" )
			{
				retVal = null;
			}
			
			return retVal;
		},
		
		/**
		 * 위젯 파라메터를 저장한다.
		 * 
		 * @param { String||Object } name - 파라메터 이름. 객체를 지정하는 경우 객체가 복사된다.
		 * @param { Object } widgetParams - 저장하는 위젯 파라메터
		 */
		setParam: function(name, widgetParams)
		{
			if( this._params == null )
			{
				this._params = {};
			}
			
			if(typeof name == 'object')
			{
				_.extend( this._params, name );
			}
			else
			{
				this._params[ name ] = widgetParams;
			}
		},
		
		/**
		 * 지정한 box_id 에 담겨있는 widget 을 얻는다.
		 * 
		 * @param	{ String } 위젯의 id
		 * @return	{ jQuery selector } 위젯 태그의 $el
		 */
		_getWidget: function(box_id)
		{
			return $("#"+box_id).children();
		},
		
		/**
		 * 모바일오븐 사용자인지 확인한다.
		 */
		_isMovenMember: function()
		{
			var user = this._getUserInfo() || { id: "guest" };
			
			return ( (typeof user.id == "string") && user.id != "guest" );
		},
		
		/**
		 * 빵앱 회원인가 확인한다.
		 */
		_isAppMember: function()
		{
			return BaroProps.getBaangMember();
		},
		
		/**
		 * 로그인된 사용자가 빵앱 소유자인지 확인한다. 
		 */
		_isAppOwner: function()
		{
			var user = this._getUserInfo() || { id: "guest" };
			var appOwner = this._getAppOwner() || { screenId: "unkonwn_owner" };
			
			return ( user.id === appOwner.screenId );
		},

		/**
		 * 로드인된 사용자가 바로앱 스태프인지 확인한다.
		 */
		_isAppStaff: function()
		{
			var appInfo = BaroProps.getAppInfo();
			return ( appInfo.staff != null && typeof appInfo.staff.roleScopes == 'string' );
		},
		
		/**
		 * 위젯의 고유 식별자를 얻는다.
		 * 오븐에서 생성하는 위젯 파라메의 식별자를 얻는다.
		 */
		_getContentId: function()
		{
			return this.getParam("id");
		},
		
		/**
		 * 부모 인스턴스를 얻는다.
		 * 존재하지 않는 경우 null 을 반환한다.
		 * 
		 * @return		부모 인스턴스 
		 */
		_getParentInstance: function()
		{
			return this.getParam("parent_instance");
		},
		
		/**
		 * 로컬 저장소의 사용자 정보를 취득한다.
		 * 
		 * @return { id: ##, name: ## }
		 */
		_getUserInfo : function()
        {
			return BaroProps.getUser();
        },
        
        /**
         * 빵앱 소유자 정보를 얻는다.
         * 
         * @return	{ no: ##, name: ##, screenId: ## }
         * 			존재하지 않는 경우 null
         */
        _getAppOwner : function()
        {
        	var appInfo = BaroProps.getAppInfo();
        	
			return typeof appInfo != "undefined" ? appInfo.owner||null : null;
        },

		_getSessionParams : function()
		{
			return BaroProps.getSessionParams();
		},
		
		/**
		 * 위젯의 NLS 리소스를 초기화한다.
		 */
		_initNLSresource: function()
		{
			var resource = this.getParam('resource') || null;			
			if( resource == null || !resource.nls )
			{
				return;
			}

			Logger.debug("BaroPanelBase._initNLSresource()");
			Logger.debug(resource.nls);
			
			if( NLSManager.isActive() )
			{
				/*
				 * 전역 nls 리소스를 사용할 때는 NLSManager 를 사용한다.
				 */
				NLSManager.getManager().addResource( resource.nls );
			}
			else
			{
				/*
				 * 위젯 nls 리소스만 사용될 때..
				 */
				if( this.model )
				{
					this.model.set(resource.nls);
				}
				else
				{
					this.model = new Backbone.Model(resource.nls);
				}
			}
		},
		
		/**
		 * 위젯의 화면 출력 스타일을 설정한다.
		 * @param {boolean} show - 위젯에 화면 출력 스타일의 적용 유무를 지정한다. false 인 경우 스타일 'hide' 를 추가한다.
		 */
		showPanel: function(show)
		{
			show == true ? 
			(
				this.$el.removeClass('hide'),
				this.trigger( BaroPanelBase.EVENT.SHOW )
			) 
			:
			(
				this.$el.addClass('hide'),
				this.trigger( BaroPanelBase.EVENT.HIDE )
			);
		}
		,
		/**
		 * BaroBox.destroyWidget() 에 의해 위젯을 닫을 때 해당 위젯의 종료 조건을 확인하기 위해 호출된다.
		 * 종료 조건을 갖는 위젯은 본 메소드를 구현하여 종료 조건을 구현한다.
		 * @return {boolean} false 를 반환하면 종료되지 않는다.
		 */
		isPossibleClose: function()
		{
			return true;
		}
		,
		/**
		 * 패널의 모든 요소를 사용할 수 없도록 Disabled 처리한다.
		 */
		disabled: function(bDisabled)
		{
			bDisabled == false 
			? this.$el.removeClass("disabled_box") 
			: this.$el.addClass("disabled_box");
		}
	}
	,
	{
		WIDGET_EVENT : "widget:event",		// { itemId: itemId, cmd: cmd, params: params }
		EVENT: 
		{
			// 패널이 showPanel() 에 의해 show/hide 될 때 발생된다.
			SHOW: "widget:show",
			HIDE: "widget:hide"
		}
	});
	
	return BaroPanelBase;
});