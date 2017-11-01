/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2013, 2014, 2015 FREECORE, Inc. All rights reserved.
 * 
 * @author dbongman
 */

define(
[
	"AResultClasses"
],
function(AResult)
{
	var Classes = {};

	/**
	 * 외부에서 제공하는 모듈을 보관한다.
	 * WebView 에서 동작되는 빵앱 위젯 모듈과 공유하는 클래스이므로, 환경에 종속성되는 모듈은 외부에서 제공받는다.
	 */
	var ModuleHolder =
	{
		logger: null,
		deferFactory: null
	};

	var _asynCmdInstanceMethod = 
	{
		defaults:
		{
			seq: 0,
			cmd: null,
			arguments: null,
			// 내부에서 처리되는 명령어 구분 플레그
			// true : 명령이 발생된 쪽에서 실행되는 명령어
			// false : off-side 에서 실행되는 명령어
			inside: null,
			result: null,
			
			// 명령 처리가 완료된 시점을 통보받는 Deferred
			defer: null,
			
			// 명령 결과를 반환할 이벤트 이름.
			response_event: null
		},
		
		/**
		 * 명령어 정보를 얻는다.
		 * 명령어를 전송할 때 사용된다.
		 * 
		 * @returns { seq: CommandEntry 가 발행한 명령 식별자, cmd: 클라이언트가 처리 요청한 명령어, arguments: 명령어 처리를 위한 파라메터 }
		 */
		getCommand: function()
		{
			var cmd = 
			{
				seq : this.get("seq"),
				cmd : this.get("cmd"),
				arguments : this.get("arguments"),
				inside : this.get("inside")
			};
			
			if( this.get("response_event") != null )
			{
				cmd.response_event = this.get("response_event");
			}
			
			return cmd;
		},
		
		/**
		 * 명령 처리 결과를 담은 명령어를 생성한다.
		 * 
		 * @param result	{ Result, ResultEx, Object } 명령 처리 결과값.
		 * @returns			{ Object } 생성된 명령어 인스턴스. 실패한 경우 null 을 반환한다.
		 */
		createResult: function(result)
		{
			this.set("result", result);
			
			var cmd =
			{
				"seq": this.get("seq"),
				"cmd": this.get("cmd"),
				"arguments": this.get("arguments"),
				"result": this.get("result")
			};
			
			return cmd;
		}
	};

	/**
	 * 명령어를 구성한다.
	 */
	Classes.ACmd = Backbone.Model.extend( _asynCmdInstanceMethod );

	var _commandEntryInstanceMethod =
	{
		_seq : 0,
		_count : 0,

		initialize : function()
		{
			
		},
		
		/**
		 * 신규 명령어를 엔트리에 등록한다.
		 * seq, defer 는 지정되지 않는 경우 내부 룰에 의해 생성한다.
		 * 본 메소드로 생성된 명령어는 반드시 releaseCmd() 로 해제 해야한다.
		 * 
		 * @param item		{ Object } JSON 형식의 엔트리 파라메터
		 * 					{ 
		 * 						seq: CommandEntry 가 발행한 명령 식별자, 
		 * 						cmd: 클라이언트가 처리 요청한 명령어, 
		 * 						arguments: 명령어 처리를 위한 파라메터
		 * 						defer: 결과 반환용 Promise 
		 * 					}
		 * @return			{ ACmd } 등록된 엔트리 인스턴스.
		 * 					이미 존재하는 경우 해당 엔트리의 인스턴스를 반환한다.
		 */
		addNewCmd : function(item)
		{
			if( typeof item.cmd != "string" )
			{
				return null;
			}

			var entryItem = new Classes.ACmd(item);
			
			if( typeof item.seq == "undefined" )
			{
				// 엔트리 식별자 생성
				entryItem.set("seq", this._nextSeq());
			}
			else if( typeof this.get(item.seq)  != "undefined" )
			{
				// 이미 해당 item.seq 가 존재하는 경우, 저장된 cmd 를 반환한다.
				return this.getEntry( item.seq );
			}

			if( entryItem.get("defer") == null )
			{
				// 결과 반환을 위한 defer 객체 생성
				entryItem.set("defer", ModuleHolder.deferFactory());
				
				ModuleHolder.logger.info("addNewCmd() - create to new deferred object.");
			}
			
			ModuleHolder.logger.info("addNewCmd() - seq : "+entryItem.get("seq"));
			
			// 신규 명령어 등록
			this.set( entryItem.get("seq"), entryItem );
			this._count = this._count + 1;
			
			ModuleHolder.logger.info("addNewCmd() - entry count : "+this._count);
			
			return entryItem;
		},
		
		/**
		 * 존재하는 명령어 entry 를 얻는다.
		 * 
		 * @param entrySeq	{ String } 명령어 엔트리 시퀀스 문자열
		 * @return 			{ ACmd } 명령어 엔트리 or null
		 */
		getEntry : function(entrySeq)
		{
			ModuleHolder.logger.info("getEntry() - entry : "+JSON.stringify( this.toJSON() ));
			
			if( typeof this.get( entrySeq )  == "undefined" )
			{
				return null;
			}
				
			return this.get( entrySeq );
		},

		/**
		 * 명령어를 엔트리에서 완료처리한다.
		 * 
		 * @param cmd		{ ACmd, Object } ACmd 명령어 또는 아래와 같은 형식의 JSON 객체.
		 * 					{
		 * 						seq: CommandEntry 가 발행한 명령 식별자, 
		 * 						cmd: 클라이언트가 처리 요청한 명령어, 
		 * 						arguments: 명령어 처리를 위한 파라메터
		 * 						result: { Object } ResultEx 의 JSON 객체. 
		 * 					}
		 * @return 			{ Boolean } 수신 처리 결과
		 */
		releaseCmd : function(cmd)
		{
			if( cmd == null )
			{
				ModuleHolder.logger.warn("releaseCmd(cmd) - parameter is null!");
				return false;
			}
			
			var cmdSeq = null;

			if( cmd instanceof Classes.ACmd )
			{
				cmdSeq = cmd.get("seq");
				ModuleHolder.logger.debug("releaseCmd(cmd) - cmd.get(seq) : "+ cmdSeq);
			}
			else
			{
				cmdSeq = cmd.seq;
				ModuleHolder.logger.debug("releaseCmd(cmd) - cmd.seq : "+ cmdSeq);
			}
			
			var cmdEntry = this.getEntry( cmdSeq );
			
			if( cmdEntry == null )
			{
				ModuleHolder.logger.warn("Has not exist a command seq "+cmdSeq);
				return false; 
			}
			
			var result = cmdEntry.get("result");

			if( result instanceof AResult.ResultEx )
			{
				ModuleHolder.logger.debug("releaseCmd() - result : "+JSON.stringify(result));
				
				if( result.get("resultCode") >= 0 )
				{
					cmdEntry.get("defer").resolve( result.toJSON() );
				}
				else
				{
					cmdEntry.get("defer").reject( result.toJSON() );	
				}
			}
			else if( result != undefined )
			{
				// JSON 형식의 결과값인 경우.
				
				ModuleHolder.logger.debug("releaseCmd() - result : "+JSON.stringify(result));
				
				// result 가 존재하니까 성공으로 간주!!
				cmdEntry.get("defer").resolve( result );
			}
			else
			{
				cmdEntry.get("defer").resolve( AResult.Helper.ERROR().toJSON() );
			}
			
			this._removeEntry( cmdSeq );
			
			ModuleHolder.logger.info("releaseCmd() - succeeded, remain command count : "+this.getSize());
			
			return true;
		},
		
		/**
		 * 지정된 명령어를 엔트리에서 삭제한다.
		 */
		_removeEntry : function(resultSeq)
		{
			var entryItem = this.get( resultSeq );
			
			if( typeof entryItem != "undefined" )
			{
				this.unset( resultSeq );
				this._count = this._count - 1;
			}
			
			//
			ModuleHolder.logger.debug("Remain entry size : "+this._count);
		},
		
		/**
		 * 명령어의 seq 를 받는다.
		 */
		_nextSeq : function()
		{
			// 무조건 증가한다.
			this._seq = this._seq + 1;
			return this._seq;
		},
		
		/**
		 * 실행중인 명령어 개수를 구한다.
		 */
		getSize : function()
		{
			return this._count;
		},
		
		/**
		 * 큐잉된 명령어 Entry 를 모두 제거한다.
		 */
		removeAll : function()
		{
			this.clear();
		}
	};

	/**
	 * 실행중인 명령어를 보관한다.
	 */
	Classes.CommandEntry = Backbone.Model.extend( _commandEntryInstanceMethod );

	/**
	 * AppHost 에서 통지되는 이벤트
	 */
	Classes.EVENT =
	{
		FOREGROUND: "Baangapp:Foreground",
		BACKGROUND: "Baangapp:Background",
		DESTROY: "Baangapp:Destroy"
	};

	Classes.ACTIVITY =
	{
		NONE: 0,
		FOREGROUND: 1,
		BACKGROUND: 2,
		DESTROY: 3
	};

	var _asynCommandInstanceMethod =
	{
		_baangappMode : false,
		_hybridMode : false,
		_eventName :
		{
			// 명령 처리 수신 이벤트 이름. 처리결과는 offside.res 로 반환한다.
			// 사용하지 않는 경우 생략 가능.
			// 생략한 경우 offside.res 도 지정할 필요없다.
			req: null,
			
			// 명령 처리 결과 수신자
			// 필수 지정항목.
			res: null,
			
			// 대상자
			offside:
			{
				req: null,
				res: null
			}
		},
		_entryBox : null,
		_runMode : false,
		// Classes.ACTIVITY 에 따른 동작 상태를 나타낸다.
		// 원칙은 NONE 로 설정하는 것이 맞으나, 앱 실행 절차상 본 객체가 초기화되는 시점이
		// AppHost 에 의해 앱이 실행되고 발생되는 이벤트를 받기 위한 시점보다 늦어지는 상황이라서 상태 이벤트를 받지 못하는 상황이된다.
		// 그래서 기본값을 FOREGROUND 로 설정한다.
		// { Classes.ACTIVITY }
		_activityState : Classes.ACTIVITY.NONE,
		
		/**
		 * @param options	{ 
		 * 						logger: 로거 모듈, 
		 * 						deferFactory: 비동기 핸들러 처리가 모듈, 
		 * 						evtName: { req: 명령 수신기 이벤트 이름, res: 명령 수신기 이벤트 이름, offside: { req: "", res: "" } }
		 * 					}
		 */
		initialize : function(options)
		{
			if( typeof options.logger == "undefined" )
			{
				alert("AsynCommand : Invalid initialize parameters[logger]!!");
				return;
			}
			if( typeof options.deferFactory != "function" )
			{
				alert("AsynCommand : Invalid initialize parameters[deferFactory]!!");
				return;
			}
			
			//
			ModuleHolder.logger = options.logger;
			ModuleHolder.deferFactory = options.deferFactory;
			this._eventName = options.evtName;
			this._checkWebViewMode();

			ModuleHolder.logger.info("Complete to initialize a AsynCommand!");
		},
		
		/**
		 * 바로앱의 엑티비티 상태를 얻는다.
		 * 
		 * @returns {number} Classes.ACTIVITY 의 상수값을 반환한다.
		 */
		getActivityState: function()
		{
			ModuleHolder.logger.info("getActivityState() - "+this._activityState);
			return this._activityState;
		},
		
		/**
		 * Hybrid 모드인지 체크한다.
		 * Hybrid 모드에서는 위젯과 Titanium 모듈이 Ti.App.fireEvent() 로 상호작용할 수 있습니다.
		 * 
		 * @return { Boolean }	체크 결과. Hybrid 모드인 경우 true.
		 */
		_checkHybridMode : function()
		{
			try
			{
				Ti.App.fireEvent("check:hybrid:mode");
				
				//
				this._hybridMode = true;
				
				ModuleHolder.logger.info("Hybrid WebView Mode! Interact by titanium event method.");
			}
			catch(e)
			{
				this._hybridMode = false;
				
				//
				ModuleHolder.logger.info("Native WebView Mode! Don't use titanium event method.");
			}
			
			return this._hybridMode;
		},
		
		/**
		 * 웹뷰 모드인지, Titanium 네이티브 모드인지 구동 환경을 체크한다.
		 */
		_checkWebViewMode : function()
		{
			if( typeof navigator == "undefined" )
			{
				this._baangappMode = false;
				
				ModuleHolder.logger.info("Titanium Mode!");
			}
			else
			{
				this._baangappMode = true;
				
				ModuleHolder.logger.info("Baangapp Mode!");
			}
			
			return this._baangappMode;
		},
		
		_isHybridMode : function()
		{
			return this._hybridMode;
		},
		
		/**
		 * 명령 수신자를 정의한다.
		 * 
		 * @return { Boolean } Listener 등록이 필요한 경우, 즉, Hybrid 모드인 경우 true, 그렇지 않은 경우 false.
		 * 			true 가 반환되는 경우 자식 클래스는 Request, Response 상황을 전달 받을 수 있는 Event Listener 를 정의해야한다.
		 */
		_initListener : function()
		{
			var self = this;

			if( this._checkHybridMode() == false )
			{
				ModuleHolder.logger.info("Don't need to initialize a event listener for to interact.");
				return false;
			}
			
			if( this._baangappMode == true )
			{
				//
				$(window).on( Classes.EVENT.FOREGROUND, function(evt, cmd)
				{
					ModuleHolder.logger.info("[WEBVIEW EVENT] "+Classes.EVENT.FOREGROUND);
					
					self._activityState = Classes.ACTIVITY.FOREGROUND;
				});
				ModuleHolder.logger.info("Registe a event for activity management : "+Classes.EVENT.FOREGROUND);
				
				//
				$(window).on( Classes.EVENT.BACKGROUND, function(evt, cmd)
				{
					ModuleHolder.logger.info("[WEBVIEW EVENT] "+Classes.EVENT.BACKGROUND);
					
					self._activityState = Classes.ACTIVITY.BACKGROUND;
				});
				ModuleHolder.logger.info("Registe a event for activity management : "+Classes.EVENT.BACKGROUND);
				
				//
				$(window).on( Classes.EVENT.DESTROY, function(evt, cmd)
				{
					ModuleHolder.logger.info("[WEBVIEW EVENT] "+Classes.EVENT.DESTROY);
					
					self._activityState = Classes.ACTIVITY.DESTROY;
					self.destroy();
				});
				ModuleHolder.logger.info("Registe a event for activity management : "+Classes.EVENT.DESTROY);
			}
			
			/**
			 * 명령어 처리 요청을 받는다.
			 * 
			 * @param cmd		{
			 * 						seq: CommandEntry 가 발행한 명령 식별자, 
			 * 						cmd: 클라이언트가 처리 요청한 명령어, 
			 * 						arguments: 명령어 처리를 위한 파라메터 
			 * 					}
			 */
			var requestListener = function(cmd)
			{
				ModuleHolder.logger.debug("requestListener() - cmd : "+JSON.stringify(cmd));
				if( typeof cmd.type != "string" )
				{
					ModuleHolder.logger.warn("Invalid command format!");
					return false;
				}
				
				// 명령을 처리한다.
				self._onNewTask(cmd);
				
				return true;
			};

			if( typeof this._eventName.req == "string" )
			{
				ModuleHolder.logger.debug("Request Event Name : "+this._eventName.req+", webview side : "+this._baangappMode);

				if( this._baangappMode )
				{
					$(window).on( this._eventName.req, function(evt, cmd)
					{
						return requestListener.call( self, cmd );
					});
				}
				else
				{
					Ti.App.addEventListener(this._eventName.req, requestListener);	
				}

				ModuleHolder.logger.info("Register a request event listener with event name "+this._eventName.req);
			}
			else
			{
				ModuleHolder.logger.info("Not use a request event listener.");
			}

			/**
			 * 명령 처리 결과를 수신한다.
			 * 
			 * @param cmd		{
			 * 						seq: CommandEntry 가 발행한 명령 식별자, 
			 * 						cmd: 클라이언트가 처리 요청한 명령어, 
			 * 						arguments: 명령어 처리를 위한 파라메터
			 * 						result: { ResultEx } 처리 결과를 담는다. 
			 * 					}
			 */
			var responseListener = function(cmd)
			{
				if( typeof cmd.type != "string" )
				{
					ModuleHolder.logger.warn("responseListener() - Invalid command format!");
					return false;
				}
				if( self._entryBox == null )
				{
					ModuleHolder.logger.warn("responseListener() - EntryBox is null!!! ");
					ModuleHolder.logger.warn("responseListener() - self : "+JSON.stringify( self ));
					return false;
				}
				
				var cmdEntry = self._entryBox.getEntry( cmd.seq );
				if( cmdEntry == null )
				{
					ModuleHolder.logger.warn("responseListener() - Not existed command entry. seq : "+cmd.seq);
					return false;
				}
				
				cmdEntry.set("result", cmd.result);
		
				self._entryBox.releaseCmd(cmd);
				
				return true;
			};
					
			if( typeof this._eventName.res == "string" )
			{
				ModuleHolder.logger.debug("Response Event Name : "+this._eventName.res+", webview side : "+this._baangappMode);

				if( this._baangappMode )
				{
					$(window).on( this._eventName.res, function(evt, cmd)
					{
						ModuleHolder.logger.debug("[BaroApp] responseListener() - arguments length : "+arguments.length);
						ModuleHolder.logger.debug("[BaroApp] responseListener() - cmd : "+typeof cmd);
						
						if( responseListener.call( self, cmd ) == false )
						{
							ModuleHolder.logger.error("[BaroApp] responseListener() - Failed to response a result!!");
						}
						else
						{
							ModuleHolder.logger.debug("[BaroApp] responseListener() - Complete!!!");
						}
					});
				}
				else
				{
					Ti.App.addEventListener(this._eventName.res, responseListener);	
				}
				
				ModuleHolder.logger.info("Register a response event listener with event name "+this._eventName.res);
			}
			else
			{
				ModuleHolder.logger.info("Not use a response event listener.");
			}
			
			//
			// remover
			//
			this._uninitListener = function()
			{
				if( self._hybridMode )
				{
					Ti.App.removeEventListener( self._eventName.req, requestListener );
					Ti.App.removeEventListener( self._eventName.res, responseListener );
				}
				else
				{
					$(window).off( self._eventName.req );
					$(window).off( self._eventName.res );
				}
				
				self._runMode = false;
				
				ModuleHolder.logger.info("AsynCommand._uninitListener() - Uninitialize listener!!!");
			};
			
			this._runMode = true;
			
			ModuleHolder.logger.info("AsynCommand._initListener() - Initialize listener!!! run mode : "+this._runMode+", baroapp mode : "+this._baangappMode);
			
			return true;
		},		
		
		/**
		 * 요청된 명령을 처리한다.
		 * 
		 * @param task		{
		 * 						seq: 요청자 CommandEntry 가 발행한 명령 식별자, 
		 * 						cmd: 클라이언트가 처리 요청한 명령어, 
		 * 						arguments: 명령어 처리를 위한 파라메터,
		 * 						response_event: 반환받을 이벤트 이름
		 * 					}
		 */
		_onNewTask : function(task)
		{
			var self = this;
			
			// 명령 처리
			this._onCmdHandler(task)
			.then(
					function(result)
					{
						// 호출측 수신용 이벤트로 성공 결과 반환
						task["result"] = result;
						self._responseResult( task );
					}
					,
					function(reason)
					{
						// 호출측 수신용 이벤트로 실패 결과 반환
						task["result"] = reason;
						self._responseResult( task );
					}
			);
		},
		
		/**
		 * 명령어 처리 핸들러를 정의한다.
		 * 자식 클래스에서 재정의가 필요하다.
		 * 
		 * @param task		{
		 * 						seq: 요청자 CommandEntry 가 발행한 명령 식별자, 
		 * 						cmd: 클라이언트가 처리 요청한 명령어, 
		 * 						arguments: 명령어 처리를 위한 파라메터 
		 * 					}
		 *  @return			{ Promise } 처리 결과 반환용 Promise
		 *  				function(Object){} 함수의 JSON 형식으로 결과를 반환한다.
		 *  				성공, 실패 모두 같은 형식으로 반환된다.
		 */
		_onCmdHandler : function(task)
		{
			ModuleHolder.logger.warn("===================================================");
			ModuleHolder.logger.warn("You must implement command handler at child class!!");
			ModuleHolder.logger.warn("===================================================");
		},
		
		/**
		 * Hybrid 모드인 경우, 상대편의 명령 요청 이벤트를 발생시킨다.
		 * 
		 * @param cmd	{
		 * 					seq: CommandEntry 가 발행한 명령 식별자, 
		 * 					cmd: 클라이언트가 처리 요청한 명령어, 
		 * 					arguments: 명령어 처리를 위한 파라메터,
		 * 					response_event: 반환받을 이벤트 이름
		 * 				}
		 * @return		{ Boolean } 호출 결과. Hybrid 모드인 경우 true.
		 */
		_requestEvent : function(cmd)
		{
			if( this._eventName.offside.req == null )
			{
				return false;
			}
			
			ModuleHolder.logger.debug("_requestLocalEvent() - Request Event Name at offside : "+this._eventName.offside.req);
			
			if( this._baangappMode )
			{
				// 바로앱 모드 - appOS 쪽으로 요청
				Ti.App.fireEvent(this._eventName.offside.req, cmd);	
			}
			else
			{
				// appOS 모드 - 바로앱 쪽으로 요청
				this._resquestEvent2BaangApp(this._eventName.offside.req, cmd);
			}
			
			return true;
		},
		
		/**
		 * 내부에서 처리되는 명령어를 실행 요청한다.
		 * 
		 * @param cmd	{
		 * 					seq: CommandEntry 가 발행한 명령 식별자, 
		 * 					cmd: 클라이언트가 처리 요청한 명령어, 
		 * 					arguments: 명령어 처리를 위한 파라메터,
		 * 					response_event: 반환받을 이벤트 이름
		 * 				}
		 * @return		{ Boolean } 호출 결과. Hybrid 모드인 경우 true.
		 */
		_requestLocalEvent : function(cmd)
		{
			if( this._eventName.req == null )
			{
				return false;
			}
			
			ModuleHolder.logger.debug("_requestLocalEvent() - Request Event Name at local : "+this._eventName.req);
			
			if( this._baangappMode )
			{
				// 바로앱 모드
				$(window).trigger( this._eventName.req, cmd );
			}
			else
			{
				// appOS 모드
				Ti.App.fireEvent(this._eventName.req, cmd);
			}
			
			return true;
		},
		
		/**
		 * Titanium 쪽에서 빵앱의 요청을 수신하는 메소드.
		 * TODO Titanium 쪽에서는 본 메소드를 필히 구현해야한다.
		 * 
		 * @param offsideEventName	수신측 이벤트 이름
		 * @param cmd				{ cmd } 요청 명령을 담은 JSON 객체.
		 */
		_resquestEvent2BaangApp : function(offsideEventName, cmd)
		{
			Logger.warn("You must to implement this method _responseResult2BaangApp at child class on Titanium Side.");
		},
		
		/**
		 * 명령 처리 결과를 반환한다.
		 * 
		 * @param cmd	{ cmd } 처리 결과를 담은 명령어 원형(JSON 객체).
		 * @return		{ Boolean } 호출 결과. Hybrid 모드인 경우 true.
		 */
		_responseResult : function(cmd)
		{
			ModuleHolder.logger.debug("_responseResult() - cmd : "+JSON.stringify( cmd ));
			
			if( cmd.inside == true )
			{
				// 내부에서 요청된 명령어
				
				if( this._baangappMode )
				{
					// 바로앱 명령 핸들러 => 명령 요청자
					$(window).trigger( cmd.response_event, cmd );
				}
				else
				{
					// appOS 명령 핸들러 => 명령 요청자
					Ti.App.fireEvent( cmd.response_event, cmd );
					// TODO 수신측 핸들러에서는 this 객체로 접근하지 못하는 상황이다. 왜??
				}
			}
			else
			{
				//
				// 맞은편에서 요청된 명령어
				//
				
				if( this._eventName.offside.res == null )
				{
					return false;
				}
				
				if( cmd.response_event == undefined )
				{
					cmd.response_event = this._eventName.offside.res;	
				}
				
				ModuleHolder.logger.debug("_responseResult() - Response Event Name at offside : "+cmd.response_event);
				
				if( this._baangappMode )
				{
					// 바로앱인 경우
					Ti.App.fireEvent(cmd.response_event, cmd);
				}
				else
				{
					// appOS 인 경우
					this._responseResult2BaangApp( cmd.response_event, cmd );
				}
			}
			
			return true;
		},
		
		/**
		 * Titanium 쪽에서 빵앱으로 요청 결과를 반환하는 메소드.
		 * TODO Titanium 쪽에서는 본 메소드를 필히 구현해야한다.
		 * 
		 * @param offsideEventName	수신측 이벤트 이름
		 * @param cmd				{ cmd } 처리 결과를 담은 명령어 원형(JSON 객체).
		 */
		_responseResult2BaangApp : function(offsideEventName, cmd)
		{
			Logger.warn("You must to implement this method _responseResult2BaangApp at child class on Titanium Side.");
		},

		/**
		 * 상대편쪽으로 명령 실행 요청 이벤트를 발생시킨다.
		 * 전면화 상태(ACTIVITY.FOREGROUND)에서만 명령어를 전송할 수 있다.
		 * 
		 * @param cmdName		{ String } 명령어
		 * @param cmdParams		{ Object } JSON 형식의 파라메터
		 * @param inside		{ Boolean } 내부용 명령인 경우 true 가 지정된다. off side 로 전달되는 명령인 경우 지정하지 않거나 false 를 지정한다.
		 * @return				{ Promise } 결과 반환용 Promise 객체. 내부 객체가 비활성화 상태인 경우 undefined.
		 */
		PostCommand : function(cmdName, cmdParams, inside)
		{
			if( this._entryBox == null )
			{
				ModuleHolder.logger.debug("PostCommand("+cmdName+") - Invalid command entry box.");
				return this._delayedReturn( AResult.Helper.FORBIDDEN("Invalid command entry box").toJSON() );
			}
			if( this._activityState != Classes.ACTIVITY.FOREGROUND )
			{
				ModuleHolder.logger.debug("PostCommand("+cmdName+") - Invalid active state : "+this._activityState);
				return this._delayedReturn( AResult.Helper.FORBIDDEN("Invalid active state : "+this._activityState).toJSON() );
			}
			if( this._runMode == false )
			{
				ModuleHolder.logger.debug("PostCommand("+cmdName+") - Invalid run mode, inside : "+inside);
				return this._delayedReturn( AResult.Helper.FORBIDDEN("Invalid run mode, inside : "+inside).toJSON() );
			}
			
			// 명령어가 발생된 쪽에서 실행되는 명령인 경우 true 가 지정된다.
			inside = inside || false;
			
			var acmd = this._entryBox.addNewCmd(
			{
				cmd: cmdName, 
				arguments: cmdParams,
				inside: inside
			});
				
			ModuleHolder.logger.debug("PostCommand() - inside flag : "+inside);
			
			if( acmd == null )
			{
				//
				// 파라메터가 잘못된 경우의 처리
				//
				return this._delayedReturn( AResult.Helper.INVALID_PARAMETERS().toJSON() );
			}
			else
			{
				// 명령어 처리 결과를 수신할 이벤트 이름
				acmd.set("response_event", this._eventName.res);
			}
			
			var reqSucess;

			if( inside == true )
			{
				// 내부에서 요청하는 명령어
				reqSucess = this._requestLocalEvent( acmd.getCommand() );
			}
			else
			{
				// 맞은편 요청하는 명령어
				reqSucess = this._requestEvent( acmd.getCommand() );
			}
			
			if( reqSucess == false )
			{
				// Native WebView 모드 : 이벤트 사용 불가
				// Hyper Link 사용
				// TODO Titanium Event 를 사용할 수 없는 경우의 명령 처리 방법이 필요함. location.href = url??
				
				// 
				return this._delayedReturn( AResult.Helper.NOTSUPPORTEDPLATFORM().toJSON(), acmd.get("defer"));
			}
			else
			{
				ModuleHolder.logger.debug("PostCommand() : "+acmd.get("cmd"));
			}
			
			var deferred = acmd.get("defer");
			
			ModuleHolder.logger.debug("PostCommand() - deferred : "+JSON.stringify(deferred));

			return typeof deferred.promise == "function" ? deferred.promise() : deferred.promise;
		},
		
		/**
		 * 내부용 명령어를 전달한다. 
		 */
		PostLocalCommand : function(cmdName, cmdParams)
		{
			return this.PostCommand( cmdName, cmdParams, true );
		},

		/**
		 * 에러 메시지 반환을 Promise 로 진행한다.
		 * 
		 * @param result	{ Object } ResultEx 형식의 JSON 객체
		 * @return			{ Primise } 에러 반환용 Primise 객체.
		 */
		_delayedReturn : function(result, defer)
		{
			if( typeof defer == "undefined" )
			{
				defer = ModuleHolder.deferFactory();
			}
			
			if( result.resultCode >= 0 )
			{
				defer.resolve(result);
			}
			else
			{
				defer.reject(result);	
			}

			return (typeof defer.promise == "function"
					// jQuery Promise 객체인 경우 defer.promise 은 함수이고,
					? defer.promise()
					// Titanium Promise 객체인 경우 defer.promise 은 객체이다
					: defer.promise);
		},
		
		create: function()
		{
			if( this._entryBox != null )
			{
				return false;
			}
			
			this._entryBox = new Classes.CommandEntry();
			this._initListener();
			
			return true;
		},

		/**
		 * 명령 핸들러를 제거한다.
		 */
		destroy: function()
		{
			this._uninitListener();

			//
			if( this._entryBox != null )
			{
				if( this._entryBox.getSize() > 0 )
				{
					ModuleHolder.logger.warn("\n\n=====\n[[Warning]] Remain Command Entry Size : "+this._entryBox.getSize());
				}
				
				//
				delete this._entryBox;
				this._entryBox = null;
			}
			
			ModuleHolder.logger.info("destroy() - destroyed a AsynCommand!!");
		}
	};

	/**
	 * 비동기 명령 처리기
	 */
	Classes.AsynCommand = Backbone.Model.extend( _asynCommandInstanceMethod );
	
	return Classes;
});