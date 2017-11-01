/**
 * Project : UCMS( Unified Contents Messaging Solution )
 *
 * Copyright (c) 2013, 2014, 2015 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */
 
define
(
[  "AsynCmdClasses", "AResultClasses", "Logger" ]
,
function(AsynClasses, AResult, Logger)
{	
	/**
	 * 빵앱을 위한 명령어 처리기를 구현한다.
	 * Titanium 모듈과 인터렉션을 위해 필요하다.
	 * 웹 모드를 위한 별도의 추가 작업이 필요하다.
	 */
	var	BaroCommander = AsynClasses.AsynCommand.extend(
	{
		initialize: function()
		{
			BaroCommander.__super__.initialize.call( this,
			{
				logger: Logger,
				deferFactory: $.Deferred,
				evtName:
				{
					req: BaroCommander.EVENT.REQ,
					res: BaroCommander.EVENT.RES+"_"+new Date().getMilliseconds(),
					offside: 
					{
						req: BaroCommander.EVENT.OFFSIDE_REQ,
						res: BaroCommander.EVENT.OFFSIDE_RES
					}
				}
			} );
			
			Logger.info("Complete to initialize a BaroCommander!");
		}
		,
		/**
		 * appOS 에서 요청되는 명령을 처리한다.
		 */
		_onCmdHandler : function(task)
		{
			var d = $.Deferred();
			var result;
			
			Logger.debug("BaroCommander._onCmdHandler() - task : "+JSON.stringify(task));

			// @see Backbone.Events.on()
			if( this._events[ task.cmd ] != undefined )
			{
				Logger.debug("BaroCommander._onCmdHandler() - valid cmd : "+task.cmd);
				
				// 요청된 명령의 핸들러가 존재하는 경우
				this.trigger( task.cmd, { defer: d, params: task } );
				//result = AResult.Helper.SUCCESS();
				// 핸들러 측에서 처리결과를 반환한다.
			}
			else
			{
				Logger.warn("BaroCommander._onCmdHandler() - Unknown command : "+task.cmd);
				
				// 핸들러가 없는 경우 에러 반환 처리
				result = AResult.Helper.FORBIDDEN("Unknown event : "+task.cmd);
				this._delayedReturn(result.toJSON(), d);
			}
			
			return d.promise();
		}
		,
		/**
		 * BaroCommander 의 명령 핸들러를 등록한다.
		 * appOS 측에서 요청하는 명령에 대한 처리를 수행한다.
		 * 
		 * @param cmd		{ String } Backbone.Events.on() 의 event 지정 규칙을 따른다.
		 * @param cbFunc	{ Function } 이벤트가 발생시 콜백되는 함수
		 */
		addCmdHandler : function(cmd, cbFunc)
		{
			if(typeof cmd != "string" )
			{
				Logger.info("addCmdHandler() - Invalid Command Type : "+typeof cmd);
				return;
			}
			if(typeof cbFunc != "function" )
			{
				Logger.info("addCmdHandler() - Invalid callback function Type : "+typeof cbFunc);
				return;
			}
			
			Logger.debug("addCmdHandler() - cmd : "+cmd);
			
			this.on(cmd, cbFunc);
		}
		,
		/**
		 * 
		 * @param cmd		{ String } Backbone.Events.on() 의 event 지정 규칙을 따른다.
		 * @param cbFunc	{ Function } 이벤트가 발생시 콜백되는 함수
		 */
		removeCmdHandler : function(cmd, cbFunc)
		{
			if(typeof cmd != "string" )
			{
				Logger.info("addCmdHandler() - Invalid Command Type : "+typeof cmd);
				return;
			}
			
			if( cbFunc )
			{
				this.off(cmd, cbFunc);
			}
			else
			{
				this.off(cmd);
			}
		}
	}
	,
	{
		/**
		 * Commander 는 Singleton 으로 관리된다.
		 * 그러므로, 유일한 1 개의 인스턴스만 생성된다.
		 */
		_SINGLETON_: null
		,
		ACTIVITY: AsynClasses.ACTIVITY
		,
		EVENT:
		{
			REQ: "baang:asyn:req",
			RES: "baang:asyn:res",
			OFFSIDE_REQ: "ti:asyn:req",
			OFFSIDE_RES: "ti:asyn:res"
		}
		,
		/**
		 * Commander 가 필요한 상황에 호출한다.
		 */
		getCommander: function()
		{
			if( BaroCommander._SINGLETON_ == null )
			{
				BaroCommander._createInstance();
				BaroCommander._checkAppHost();
			}
			
			return BaroCommander._SINGLETON_;
		}
		,
		_createInstance: function()
		{
			BaroCommander._SINGLETON_ = new BaroCommander();
			BaroCommander._SINGLETON_.create();
			
			Logger.info("BaroCommander._createInstance() - Succeeded!!!");
		}
		,
		/**
		 * AppHost 초기화가 먼저 진행되는 경우 BaroCommander 의 Foreground 이벤트 핸들러가 호출되지 않는 문제가 있다.
		 * 이런 경우를 보완하기 위해서 BaroCommander 가 생성될 때 AppHost 의 초기화 유무를 체크해서, activityState 에 전면화 상태를 설정한다. 
		 */
		_checkAppHost: function()
		{
			$(window).trigger("AppHost:Check");
			if( window.apphost == true )
			{
				BaroCommander._SINGLETON_._activityState = AsynClasses.ACTIVITY.FOREGROUND;
			}
			
			Logger.info("BaroCommander._checkAppHost() - window.apphost : "+window.apphost+", activityState : "+BaroCommander._SINGLETON_._activityState);
		}
		,
		/**
		 * 빵앱이 종료될 때 Commander 를 소멸시켜야한다. 
		 */
		destroy: function()
		{
			if( BaroCommander._SINGLETON_ != null )
			{
				BaroCommander._SINGLETON_.destroy();
			}
		}
	});
	
	return BaroCommander;
});