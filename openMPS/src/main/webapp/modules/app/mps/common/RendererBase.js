/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define(["Logger",
        "NDSProps",
        "BaroBox",
        "FormBox",
        "APIClient",
        "WorkAreaHeader",
        "SubContainer",
        "manifest!CodeSearch-1.0.0",
        "FormClient"
        ],
		function(Logger, NDSProps, BaroBox, FormBox, APIClient, WorkAreaHeader, SubContainer, CodeSearch, FormClient)
{
	CodeSearch = CodeSearch.widget;
	var bases = 
	{
		// jqGrid, realgrid 중 사용하는 그리드 모드를 하나 지정
		//GRIDMODE: "realgrid"
		GRIDMODE: "jqGrid"
	};

	/**
	 * 원본 데이타를 보관하는 기능
	 */
	function BackupModel()
	{
		this._buf = {};
	}
	BackupModel.prototype =
	{
		reset: function(mid)
		{
			mid ? ( !this._buf[mid] || delete this._buf[mid] )
				: ( this._buf = {} );
		}
		,
		backup: function(mid, data)
		{
			this._buf[ mid ] = data;
		}
		,
		get: function(mid)
		{
			if(mid)
			{
				if( this._buf[ mid ] )
				{
					return this._buf[ mid ] instanceof Array
					? this._buf[ mid ] : [this._buf[ mid ]];
				}
				else
				{
					return [];
				}
			}
			else
			{
				return _.values(this._buf);
			}
		}
		,
		has: function(mid)
		{
			return ( this.get(mid).length > 0 );
		}
	};
	function DummyBackupModel()
	{
		this.isDummy = true;
	}
	DummyBackupModel.prototype =
	{
		reset: function(mid){}
		,
		backup: function(mid, data){}
		,
		get: function(mid){ return []; }
		,
		has: function(mid){ return false; }
	};
	// TODO DummyBackupModel 적용
	//bases.BackupModel = BackupModel;
	bases.BackupModel = DummyBackupModel;

	/**
	 * Task 상세 정보를 담는다.
	 */
	function ReqTask(api)
	{
		this._api = api || null;
		this._action = null;
		this._data = null;
	};
	ReqTask.prototype =
	{
		setId: function(tid)
		{
			this._id = tid;
			Logger.debug("Task ID : "+tid);
		}
		,
		setAPI: function(api)
		{
			this._api = api || null;
		}
		,
		setAction: function(action)
		{
			this._action = action;
		}
		,
		setCreateTask: function(data)
		{
			this._action = 'C';
			this._data = data;
		}
		,
		setUpdateTask: function(data)
		{
			this._action = 'U';
			this._data = data;
		}
		,
		setDeleteTask: function(data)
		{
			this._action = 'D';
			this._data = data;
		}
		,
		getId: function()
		{
			return this._id;
		}
		,
		getAction: function()
		{
			return this._action;
		}
		,
		/**
		 * @return
		 * {
		 *  id: #, Task Id
		 * 	api: #, 동작 대상 API
		 * 	action: #, 동작을 나타낸다. "C, U, D" 중 하나
		 * 	data: # 대상 데이타
		 * }
		 */
		getTask: function()
		{
			return {
				id: this._id,
				api: this._api,
				action: this._action,
				data: this._data
			};
		}
	};
	bases.ReqTask = ReqTask;
	//
	function CreateTask(tid, data, api)
	{
		this.setId( tid );
		this.setAPI( api );
		this.setCreateTask( data );
	};
	CreateTask.prototype = new ReqTask();
	CreateTask.prototype.constructor = CreateTask;
	bases.CreateTask = CreateTask;
	//
	function UpdateTask(tid, data, api)
	{
		this.setId( tid );
		this.setAPI( api );
		this.setUpdateTask( data );
	};
	UpdateTask.prototype = new ReqTask();
	UpdateTask.prototype.constructor = UpdateTask;
	bases.UpdateTask = UpdateTask;
	//
	function DeleteTask(tid, data, api)
	{
		this.setId( tid );
		this.setAPI( api );
		this.setDeleteTask( data );
	};
	DeleteTask.prototype = new ReqTask();
	DeleteTask.prototype.constructor = DeleteTask;
	bases.DeleteTask = DeleteTask;
	/**
	 * 여러개의 Task 정보를 보관한다.
	 */
	function TaskPool()
	{
		var counter =
		{
			create: 0,
			update: 0,
			delete: 0
		};
		this._resetCounter = function()
		{
			counter.create = 0;
			counter.update = 0;
			counter.delete = 0;
		};
		this._counting = function(task)
		{
			switch( task.action )
			{
			case 'C':
				++counter.create;
				break;

			case 'U':
				++counter.update;
				break;

			case 'D':
				++counter.delete;
				break;
			}
		};
		this._discounting = function(task)
		{
			switch( task.action )
			{
			case 'C':
				--counter.create;
				break;

			case 'U':
				--counter.update;
				break;

			case 'D':
				--counter.delete;
				break;
			}
		};
		this.getCounter = function()
		{
			return counter;
		};
		this.reset();
	};
	TaskPool.prototype =
	{
		reset: function()
		{
			this._pool = {};
			this._resetCounter();
		}
		,
		/**
		 * 타스크 추가
		 * @param {ReqTask} task 작업 상세
		 */
		add: function(task)
		{
			if(!task || !task.getTask )
			{
				Logger.error("addTask() - Not compotible type.");
				return;
			}

			task = task.getTask();
			var tid = task.id;

			//
			if( this._pool[ tid ] )
			{
				var oldTask = this._pool[ tid ];

				switch(oldTask.action)
				{
				case 'C':
					if( task.action == 'U' )
					{
						// 신규 값으로 대체
						task.action = 'C';
						this._pool[ tid ] = task;
					}
					else if( task.action == 'D' )
					{
						// 생성된 것을 없었던 것으로 처리
						delete this._pool[ tid ];
						this._discounting( oldTask );
					}
					else
					{
						// XXX 중복 생성은 있을 수 없는 상황.
						// XXX UI 업무 로직에 문제가 있는 상황.
						Logger.error("addTask() - Dup a creation action.");
					}
					break;
				case 'U':
					if( task.action == 'U' )
					{
						// 신규 값으로 대체
						this._pool[ tid ] = task;
					}
					else if( task.action == 'D' )
					{
						// 삭제 동작으로 대체
						this._pool[ tid ] = task;
						this._discounting( oldTask );
						this._counting( task );
					}
					else
					{
						// XXX 갱신된 항목에 대해서 생성 동작은 발생시킬 수 없는 동작
						// XXX UI 업무 로직에 문제가 있는 상황.
						Logger.error("addTask() - Invalid request 'UPDATE'");
					}
					break;
				case 'D':
					// XXX 중복 삭제는 발생될 수 없는 상황
					// XXX UI 업무 로직에 문제가 있는 상황.
					Logger.error("addTask() - Invalid request 'DELETE'");
					break;
				}
			}
			else
			{
				// 신규
				this._pool[ tid ] = task;
				this._counting( task );
			}
		}
		,
		/**
		 * 보관 중인 Task 를 얻는다.
		 * @param {string} tid - Task 식별자. grid 의 경우 row Id 를 지정
		 * @return {ReqTask}
		 */
		get: function(tid)
		{
			return this._pool[ tid ] || null;
		}
		,
		/**
		 * @return {Array} 추가된 타스크 배열
		 */
		getList: function()
		{
			return _.map(
					_.values(this._pool),
					function(item)
					{
						item.data.dsType = item.action;
						return item.data;
					});
		}
		,
		getTotCount: function()
		{
			var cnt = this.getCounter();
			var list = this.getList();
			if( list.length != ( cnt.create + cnt.update + cnt.delete ) )
			{
				Logger.warn("getTotCount() - Not equal count.");
			}
			return this.getList().length;
		}
	}; // TaskPool
	
	//
	bases.TaskPool = TaskPool;

	/**
	 * 여러 종류의 Task 데이타를 관리한다.
	 */
	function TaskDataCollector()
	{
		this._buf = {};
		this._count = 0;
	}
	TaskDataCollector.prototype =
	{
		reset: function(tid)
		{
			tid ? ( !this._buf[tid] || (delete this._buf[tid], this._count--) )
				: ( this._buf = {}, this._count = 0 );
		}
		,
		/**
		 * @param {string} tid - task 식별자
		 * @param {object} data - task data
		 */
		set: function(tid, data)
		{
			this._buf[ tid ] || this._count++;
			this._buf[ tid ] = data;
		}
		,
		/**
		 * @param {string} tid - task 식별자
		 * @return {object} task 데이타
		 */
		get: function(tid)
		{
			if(tid)
			{
				if( this._buf[ tid ] )
				{
					return this._buf[ tid ];
				}
				else
				{
					return null;
				}
			}
			else
			{
				return this._buf;
			}
		}
		,
		/**
		 * @param {string} tid - task 식별자
		 * @return {boolean}
		 */
		has: function(tid)
		{
			return !! this._buf[ tid ];
		}
		,
		/**
		 * 보관중인 task 개수를 구한다.
		 * @return {number}
		 */
		getCount: function()
		{
			return this._count;
		}
	};
	bases.TaskDataCollector = TaskDataCollector;

	/**
	 * Item 공통 메소드의 프로투타입
	 */
	var WrapperBase = Backbone.Model.extend(
	{
		/**
		 * 내부에 보관중인 항목을 얻는다.
		 */
		getItem: function()
		{
			return this.get("item");
		}
		,
		clear: function()
		{
		}
		,
		setData: function(data)
		{
		}
		,
		getData: function()
		{
		}
	});

	var Header = WrapperBase.extend(
	{
		/**
		 * @param {object} options - 헤더 Wrapper 초기화 파라메터
		 * 			{
		 * 				id: ##,
		 * 				item: {WorkAreaHeader | SubContainer},
		 * 				handler: {function} 이벤트 처리 핸들러
		 * 			}
		 */
		initialize: function(options)
		{
			var self = this;

			if( options.item instanceof SubContainer )
			{
				options.item
				.on( SubContainer.EVENT, function(event)
				{
					event.params = {id: options.id, module: "SubContainer"};
					self.onEventHandler(event);
				});
			}
			else if( options.item instanceof WorkAreaHeader )
			{
				options.item
				.on( "widget:event", function(event)
				{
					event.params = {id: options.id, module: "WorkAreaHeader"};
					self.onEventHandler(event);
				});
			}

			if(! options.handler )
			{
				// 더미 핸들러 설정
				this.set("handler", function(){});
			}
		}
		,
		setMode: function(mode)
		{
			this.getItem().setButtonMode( mode );
		}
		,
		getMode: function()
		{
			return this.getItem().getButtonMode();
		}
		,
		/**
		 * WorkAreaHeader 과 SubContainer의 버튼 이벤트를 수신한다.
		 * @param {object} event - 이벤트 상세 정보
		 * 			{
		 * 				cmd: {string},
		 * 				params: {
		 * 					id: {string} 객체 식별자,
		 * 					module: {string} 객체의 모듈명}
		 * 			}
		 */
		onEventHandler: function(event)
		{
			this.get("handler")(event);
		}
	});
	bases.Header = Header;

	var Form = WrapperBase.extend(
	{
		initialize: function(options)
		{

		}
		,
		setData: function(data, silent)
		{
			this.getItem().setItemData(data||{}, silent);
		}
		,
		/**
		 * 폼 데이타를 취합한다.
		 * 
		 * @param {string} bNoti - 필수값 체크 결과 알림창 사용 유무. true 면 누락된 정보에 대한 안내창이 팝업된다.
		 * @return {object} 필수값이 입력되지 않은 경우 null
		 */
		getData: function(bNoti)
		{
			return this.getItem().getItemData(bNoti);
		}
		,
		clear: function()
		{
			this.getItem().clear();
		}
	});
	bases.Form = Form;

	/**
	 * 그리드 관련 처리를 담당한다.
	 */
	var Grid = WrapperBase.extend(
	{
		/**
		 * @param {object} options - 그리드 Wrapper 초기화 파라메터
		 * 			{
		 * 				id: ##,
		 * 				item: ##,
		 * 				client: ##
		 * 			}
		 */
		initialize: function(options)
		{
			// BackupModel 사용 안함
			this._model = new DummyBackupModel();
			this._pool = new TaskPool();
			this._rowCounter = 0;
		}
		,
		clear: function()
		{
			this.getItem().clear();
			this._rowCounter = 0;
		}
		,
		/**
		 * 그리드에 신규 데이타를 설정한다.
		 * @param {array} data
		 * @parma {string} method - 행 데이타 추가 방식 지정.
		 * 							loop
		 * 							one
		 * 							local
		 */
		setData: function(data, method)
		{
			var grid = this.getItem();
			grid.clear();

			if( bases.GRIDMODE == "jqGrid" )
			{
				switch( method )
				{
				default:
				case "loop":
					// rowspan 이 정상 동작하는 데이타 추가 방식
					if( data instanceof Array )
					{
						// case 1
						// 배열의 경우 아래와 같은 방식으로 추가해야 rowspan 이 정상 동작한다.
						// 그런데 행 추가시 추기되는 데이타가 많은 경우 시간이 제일 오래 걸림
						for(var i in data)
						{
							grid.addRow( data[i], "last", false );
						}
					}
					else
					{
						// case 2
						grid.addRow( data, "last", false );
					}
					break;
	
				case "one":
					// case 3
					grid.addRow( data, "first", true );
					// reload 를 수행하면, rowspan 이 깨진다.
					// grid.reload();
					break;
					
				case "local":
					// case 4
					// case 2, 3 번은 노출되는 행 순서가 데이타의 역순으로 노출된다.
					// 아래 방식으로 추가하면, 대랑의 행 데이타도 빠르게 추가되며,
					// 데이타 나열 순서로 그리드에 추가된다.
					// 스크롤 처리를 위해서는 본 방식을 사용한다.
					grid.setGridParam({data: data});
					break;
				}
			}
			else if( bases.GRIDMODE == "realgrid" )
			{
				grid.addRow( data, "first" );
			}
			else
			{
				Logger.error("fetchingGrid() - Invalid GRIDMODE : "+bases.GRIDMODE);
			}
			
			if( data && data instanceof Array )
			{
				this._rowCounter = data.length;
			}
			else
			{
				this._rowCounter = 1;
			}
			
			//
			this.beginTransaction(true);
		}
		,
		getData: function()
		{
			return this.getItem().getRowData();
		}
		,
		/**
		 * 그리드의 데이타를 조회한다.
		 * @param {object} params - 조회시 적용되는 파라메터
		 * @param {string} featureId - 조회 기능 식별자. 대상 그리드가 구현하는 기능에 따라 필요한 경우 지정한다.
		 * @return {$.promise}
		 */
		fetch: function(params, featureId)
		{
			featureId || (featureId=this.get("id"));
			return this.get("client").read( params, featureId );
		}
		,
		/**
		 * 신규 행을 추가한다.
		 * @param {object|array} defaultData - 기본 값. 지정하지 않으면 빈 object 가 추가됨
		 * 						배열로 여래 행을 추가할 수 있다.
		 * @param {string} pos - 신규 행이 추가되는 위치. 지정하지 않은 경우 "after" 가 적용됨
		 * 						"first", "last", "after", "before"
		 * @return {string} 추가된 행의 row Id
		 * 			{number} 추가된 row 개수
		 * 			{$.promise} realgrid 인경우 추가된 row id 를 반환하는 promise 객체
		 */
		createRow: function(defaultData, pos)
		{
			var self = this;
			var addRow = function(defaultData)
			{
				if( bases.GRIDMODE == "jqGrid" )
				{
					defaultData || (defaultData = {});
				}
				var newRowId = self.getItem().addRow( defaultData, pos || "after" );
				if( typeof newRowId == "string" )
				{
					//
					// JQGrid
					//
					self._pool.add( new CreateTask(newRowId, defaultData) );
					// TODO 신규 생성시 선택하지 않으면, 폼의 변경된 내용이 반영되지 않음
					self.getItem().setSelectRow( newRowId );
					self._rowCounter++; 
				}
				else if( newRowId && typeof newRowId.then == "function" )
				{
					//
					// RealGrid
					//
					newRowId.then(function(newRowId)
					{
						self._pool.add( new CreateTask(newRowId, defaultData) );
						self._rowCounter++;
					});
				}
				return newRowId;
			};
			if( defaultData instanceof Array )
			{
				for(var i in defaultData)
				{
					addRow(defaultData[i]);
				}

				return defaultData.length;
			}
			else
			{
				return addRow(defaultData);
			}
		}
		,
		/**
		 * 지정한 행을 변경한다.
		 */
		setRow: function(rowId, data)
		{
			this.getItem().setRowData( rowId, data );
			this._pool.add(new UpdateTask( rowId, data ));
		}
		,
		/**
		 * 지정한 행을 제거한다.
		 */
		removeRow: function(rowId)
		{
			rowId || (rowId = this.getItem().getSelRowId());
			if(! rowId)
			{
				Logger.warn("removeRow() - invalid rowId.");
				return;
			}
			var rowData = this.getItem().getRowData( rowId );
			this.getItem().removeRow( rowId, true );
			this._pool.add( new DeleteTask( rowId, rowData ) );
			this._rowCounter--;
		}
		,
		/**
		 * 지정한 셀 정보를 변경한다.
		 */
		setCell: function(rowId, colname, value, cellclass)
		{
			rowId || (rowId = this.getItem().getSelRowId());
			if(! rowId)
			{
				Logger.warn("setCell() - invalid rowId.");
				return;
			}
			var rowData = this.getItem().getRowData( rowId );
			this.getItem().setCell( rowId, colname, value, cellclass );
			this._pool.add( new UpdateTask( rowId, rowData ) );
		}
		,
		/**
		 * 트랜젝션 시작을 위해 백업 데이타를 갱신한다.
		 */
		beginTransaction: function(backup)
		{
			if( backup == true )
			{
				var data = this.getItem().getRowData();
				if( this._model )
				{
					this._model.backup( this.get("id"), data );
				}
			}

			return this.get("id");
		}
		,
		getTaskCount: function()
		{
			return this._pool.getTotCount();
		}
		,
		getTaskList: function()
		{
			return this._pool.getList();
		}
		,
		/**
		 * 존재하는 Task 를 얻는다.
		 * @return {ReqTask} 존재하지 않는 경우 null
		 */
		getTaskRow: function(tid)
		{
			return this._pool.get(tid);
		}
		,
		/**
		 * 트랜젝션을 적용한다.
		 * @param {object} params - 트랜젝션 파라메터
		 * @param {string} cid - 커밋 동작 결정시 참고되는 식별자
		 * @param {object} taskData - 커밋 데이타. 지정하지 않는 경우 트랜잭션 풀에 보관된 Task 배열이 자동 커밋된다.
		 * @param {function} isValidRowChecker - task 유효성을 검사하는 함수. function( gridId, task ){} 의 형식으로 제공하고, 검사 결과를 boolean 형식으로 반환한다.
		 * @return {$.promise} 커밋 조건에 맞지 않는 task 정보가 존재하는 경우에도 reject 된다. 이 경우 해당 {id, task} 가 반환된다.
		 */
		commit: function(params, cid, taskData, isValidRowChecker)
		{
			if( this.getTaskCount() > 0 || taskData )
			{
				var self = this;
				var id = this.get("id");
				var apiPath = this.get("client").getAPIPath("create", cid||id, params);
				var taskList = taskData || this._pool.getList();
				
				if( typeof isValidRowChecker == "function" )
				{
					for(var i in taskList)
					{
						var valid = isValidRowChecker( id, taskList[i] );
						if( typeof valid == 'string' )
						{
							// 커밋 조건에 해당하지 않는 task.
							return UCMS.retReject({resultCode:-2, msg:valid, extraData:{"id": id, "task": taskList[i]}});
						}
						else if( valid == false )
						{
							// 커밋 조건에 해당하지 않는 task.
							return UCMS.retReject({resultCode:-2, msg:"Invalid Parameter!", extraData:{"id": id, "task": taskList[i]}});
						}
					}
				}

				return this.get("client").transaction( apiPath, taskList )
				.then(function(res)
				{
					if( res.resultCode == 0 )
					{
						// 백업 데이타 갱신
						self.beginTransaction(true);
						self._pool.reset();
					}
					return res;
				});
			}
			else
			{
				return UCMS.retReject(null);
			}
		}
		,
		/**
		 * 그리드를 트랜젝션 이전 상태로 되돌린다.
		 * @return {boolean}
		 */
		rollback: function()
		{
			this._pool.reset();
			//
			if( this._model )
			{
				if( this._model.isDummy == undefined && this._model.has( this.get("id") ))
				{
					var id = this.get("id");
					this.getItem().addRow( this._model.get( id ), "first", true );
					return UCMS.retResolve(null);
				}
				else
				{
					// Dummy Backup Model
					this.getItem().clear();
					return UCMS.retResolve("refresh");
				}
			}
			else
			{
				this.getItem().clear();
				return UCMS.retReject();
			}
		}
		,
		/**
		 * 트랜젝션을 종료한다.
		 * @param {boolean|object} restore - true 면 rollback 되고, false 이면 commit 된다.
		 * 									객체가 지정된 경우에는 commit 시 파라메터로 사용된다.
		 * @return {$.promise}
		 */
		endTransaction: function(restore)
		{
			if(typeof restore == "boolean")
			{
				return (restore == true? this.rollback() : this.commit());
			}
			else if( restore )
			{
				// 파라메터가 제공된 경우
				return this.commit( restore );
			}
			else
			{
				return this.rollback();
			}
		}
		,
		getTaskCounter: function()
		{
			return this._pool.getCounter();
		}
		,
		getRowCounter: function()
		{
			return this._rowCounter;
		}
	}
	,
	{
		EVENT: {

		}
	});
	/**
	 * openMPS DB 에서 필요로 하는 데이타 값의 형식 변환을 진행하는 Grid Wrapper
	 */
	var OpenMPSGrid = Grid.extend(
	{
		createRow: function(defaultData, pos)
		{
			if( defaultData instanceof Array )
			{
				for(var i in defaultData)
				{
					Grid.prototype.createRow.call(this, this.adjustValue(defaultData[i]), pos);
				}

				return defaultData.length;
			}
			else
			{
				return Grid.prototype.createRow.call(this, this.adjustValue(defaultData), pos);
			}
		}
		,
		setRow: function(rowId, data)
		{
			Grid.prototype.setRow.call(this, rowId, this.adjustValue(data));
		}
		,
		adjustValue: function(rowData)
		{
			if(! rowData )
			{
				return null;
			}
			if( rowData.setYn != undefined )
			{
				// 제품 마스터 필드
				rowData.setYn = (new Boolean(rowData.setYn) == true ? "1" : "0");
			}
			if( typeof rowData.useYn != undefined )
			{
				rowData.useYn = (new Boolean(rowData.useYn) == true ? "Y" : "N");
			}
			if( rowData.programYn != undefined )
			{
				rowData.programYn = (new Boolean(rowData.programYn) == true ? "Y" : "N");
			}
			if( rowData.smsYn != undefined )
			{
				rowData.smsYn = (new Boolean(rowData.smsYn) == true ? "Y" : "N");
			}
			if( rowData.mailYn != undefined )
			{
				rowData.mailYn = (new Boolean(rowData.mailYn) == true ? "Y" : "N");
			}
			if( rowData.personYn != undefined )
			{
				rowData.personYn = (new Boolean(rowData.personYn) == true ? "Y" : "N");
			}
			if( rowData.salesYn != undefined )
			{
				rowData.salesYn = (new Boolean(rowData.salesYn) == true ? "Y" : "N");
			}
			if( rowData.prodYn != undefined )
			{
				rowData.prodYn = (new Boolean(rowData.prodYn) == true ? "Y" : "N");
			}
			if( rowData.logYn != undefined )
			{
				rowData.logYn = (new Boolean(rowData.logYn) == true ? "Y" : "N");
			}
			if( rowData.purYn != undefined )
			{
				rowData.purYn = (new Boolean(rowData.purYn) == true ? "Y" : "N");
			}
			
			if( rowData.replyYn != undefined )
			{
				rowData.replyYn = (new Boolean(rowData.replyYn) == true ? "Y" : "N");
			}
			if( rowData.commentYn != undefined )
			{
				rowData.commentYn = (new Boolean(rowData.commentYn) == true ? "Y" : "N");
			}
			if( rowData.attachYn != undefined )
			{
				rowData.attachYn = (new Boolean(rowData.attachYn) == true ? "Y" : "N");
			}
			
			if( rowData.custCode && typeof rowData.custCode != 'string' )
			{
				// CodeSearch 형의 데이타를 일반형식으로 변환
				rowData.custName = rowData.custCode.keyword;
				rowData.custCode = rowData.custCode.result;
			}
			return rowData;
		}
	});
	bases.Grid = OpenMPSGrid;

	//
	// 공통 메소드
	//

	var MainMethod =
	{
		initMethod: function(options)
		{
			this._items = {};

			//
			_.extend(FormBox.ItemSettings,
			{
				"host": (NDSProps.get('hosts')||{api:"http://localhost:8080"}).api,
				"corpCode": NDSProps.get('corpCode') || "1001",
				"systemCode": NDSProps.get('systemCode') || "MPS"
			});
		}
		,
		onRender: function()
		{
			this.showLoading();
		}
		,
		onBeforeShow: function()
		{
			this._initPromise = this.initSubHeader();
		}
		,
		onShow: function()
		{
			var renderer = this;
			this._initPromise.then(function()
			{
				renderer._showContentsBox();
			});
		}
		,
		onShowComplete: function()
		{
			var self = this;
			var itemList = this.getItemList();

			if( itemList.length > 0 )
			{
				UCMS.retry(function()
				{
					for(var i in itemList)
					{
						if(self.getItemInstance( itemList[i] ) == null )
						{
							return false;
						}
					}

					//
					// 모든 아이템의 인스턴스에 접근할 수 있는 상태
					//

					self.triggerMethod("init:renderer:item:events");
				}
				, 5000)
				.done(function()
				{
					Logger.info("onShowComplete() - done : "+self._contentId);
				})
				.fail(function()
				{
					Logger.warn("onShowComplete() - Failed to initialize. "+self._contentId);
				})
				.always(function()
				{
					self.hideLoading();
				});
			}
			else
			{
				this.triggerMethod("init:renderer:item:events");
				this.hideLoading();
			}
		}
		,
		/**
		 * 자식 모듈들이 발생시킨 이벤트를 처리한다.
		 */
		onEventHandler: function(event)
		{
			Logger.debug(event);

			switch( event.cmd )
			{
			//////
				// 헤더 이벤트
				//
				case WorkAreaHeader.EVENT.CREATE:
					this.triggerMethod("create");
					break;
				case WorkAreaHeader.EVENT.QUERY:
					this.triggerMethod("query");
					break;
				case WorkAreaHeader.EVENT.DELETE:
					this.triggerMethod("delete");
					break;
				case WorkAreaHeader.EVENT.SAVE:
					this.triggerMethod("save");
					break;
				case WorkAreaHeader.EVENT.CANCEL:
					this.triggerMethod("cancel");
					break;
				case WorkAreaHeader.EVENT.CLOSE:
					this.triggerMethod("close:renderer");
					break;
				case WorkAreaHeader.EVENT.DOWNLOAD:
					this.triggerMethod("download");
					break;
				case WorkAreaHeader.EVENT.SHARE:
					this.triggerMethod("share");
					break;
				case WorkAreaHeader.EVENT.PRINT:
					this.triggerMethod("print");
					break;
				case WorkAreaHeader.EVENT.FAVORITE:
					this.triggerMethod("favorite");
					break;
				case WorkAreaHeader.EVENT.MANUAL:
					this.triggerMethod("manual");
					break;

			//////
				// Box 이벤트
				//
				case BaroBox.EVENT.ACTIVETAB:
					this.triggerMethod("active:tab", event.params);
					break;
				case CodeSearch.EVENT.SEARCH:
					this.triggerMethod("search:code", event.params);
					break;

				default:
					return false;
			}

			return true;
		}
		,
		createAPIClient: function(cid, ajaxParams)
		{
			var hosts = NDSProps.get('hosts') || { api: '' };
			return new APIClient(
			{
				host: hosts.api,
				systemCode: NDSProps.get('systemCode'),
				corpCode: NDSProps.get('corpCode'),
				contentId: cid || this._contentId,
				params: ajaxParams
			});
		}
		,
		/**
		 * 팝업 박스를 구현한다.
		 * 팝업 내에서 처리된 결과는 파라메터로 제공되는 callback 함수를 통해 반환된다.
		 *
		 * @param {string} boxId - 팝업 박스 식별자
		 * @param {object} options - { callback: function(result){} }
		 * 							지정한 팝업 박스가 요하는 파라메터를 추가할 수 있다.
		 * 							callbakck 은 결과를 수신하기 위한 필수 요소이다.
		 */
		popupBox: function(boxId, options)
		{
			var boxRepo = NDSProps.get("BoxRepo");
			var appName = NDSProps.get("appName");

			UCMS.loadBoxResource( boxRepo[boxId].detail )
			.then(function(boxParams)
			{
				options || (options={});
				UCMS.loadModuleByPath(["BaroFloating", boxParams.module])
				.then(function(BaroFloating, Module)
				{
					if( BaroFloating.isFloating() == true )
					{
						Logger.debug("popupBox() - Already opened.");
						return;
					}
					var cbResult = function(result)
					{
						if( options.callback )
						{
							options.callback( result );
						}
						BaroFloating.close();
					};

					BaroFloating.open
					(
						Module,
						_.extend(options, boxParams.options),
						{
							title: appName,
							close: function()
							{
								cbResult(null);
							}
							,
							className: "modal_box "+boxId
						}
					)
					.done(function(result)
					{
						cbResult(result);
					});
				});
			});
		}
		,
		getQueryData: function(params)
		{
			var formItem = this.attachFormItem("queryBox");
			if(! formItem )
			{
				return null;
			}
			var queryBox = formItem.getItem();
			return _.extend(queryBox.getItemData(), params);
		}
		,
		closeRenderer: function(force)
		{
			this.broadcastEvent(bases.EVENT.CLOSERENDERER, {"id": this._contentId, "force": force});
		}
		,
		isPossibleClose: function()
		{
			if( this.isTransactionMode() == true )
			{
				var self = this;
				UCMS.confirm("작업중인 데이타가 존재합니다.<br>종료할까요?")
				.done(function()
				{
					self.closeRenderer(true);
				});
				return false;
			}

			return true;
		}
		,
		/**
		 * 현재 활성화된 폼 인스턴스를 얻는다.
		 */
		getActiveForm: function(formId)
		{
			formId || (formId=this.getFormName());
			return this.attachFormItem(formId);
		}
		,
		/**
		 * 현재 활성화된 그리드 인스턴스를 얻는다.
		 */
		getActiveGrid: function(gridId)
		{
			gridId || (gridId=this.getHeaderGridName());
			return this.attachGridItem(gridId);
		}
		,
 		getClient: function(cid, ajaxParams)
 		{
 			if(! this._client)
 			{
 				this._client = this.createAPIClient(cid, ajaxParams);
 			}
 			else if( cid && this._client.get("contentId") != cid )
 			{
 				// 다른 cid 를 사용하는 client 생성
 				return this.createAPIClient(cid, ajaxParams);
 			}
 			return this._client;
 		}
	};

	var HeaderMethod =
	{
		/**
		 * 사용자별로 제공되는 권한을 담은 파라메터 정보를 조회한다.
		 * 해당 정보는 WorkAreaHeader 에 제공된다.
		 */
		queryHeaderInfo: function()
		{
			var d = $.Deferred();
			var makeContentPath = function(contentId)
			{
				var path = [];
				var menuItems = NDSProps.get("menuItems") || {};
				var rootItem = menuItems[ menuItems.defaultMenu ] || {};
				var makePath = function(node)
				{
					if(! node.order )
					{
						path.push( node.label );
						return;
					}
					var list = node.order;
					for( var i in list )
					{
						var childId = list[i];
						if( contentId.indexOf( childId ) >= 0 )
						{
							path.push( node.label );
							makePath( menuItems[ childId ] );
						}
					}
				}

				var list = rootItem.order;
				for( var i in list )
				{
					makePath( menuItems[ list[i] ] );
				}

				return path;
			};
			var itemPath = makeContentPath( this._contentId );
			var headerParams = {
				"module": "WorkAreaHeader",
				"options": {
					"title": {
						label: itemPath[ itemPath.length-1 ],
						icon: "fa-bookmark"
					},
					"path": itemPath,

					// 사용자별로 콘텐츠에 대한 권한을 API 로 부터 취득한다.
					"feature": [
        						"query",
        						"create",
        						"save",
        						"delete",
        						"cancel",
        						"close"
        					],

        			"corpCode": true
				}
			};

			Logger.debug("Renderer.queryHeaderInfo() - Content Id : " + this._contentId);

			d.resolve(headerParams);

			return d.promise();
		}
		,
		/**
		 * 트랜젝션을 시작한다.
		 * @param {boolean|string} backup - 트랜젝션 시작시 현재 상태 백업 진행 플레그
		 * 									문자열이 지정되면 지정한 문자열은 gridId 로 사용되고, 현재 시점은 백업된다.
		 * @param {string} gridId - 트랜젝션 대상이 되는 그리드 식별자
		 * @return {boolean} 트랜잭션 시작 유무
		 */
		beginTransaction: function(backup, gridId)
		{
			var header = this.attachHeaderItem("headerBox");
			if( header.getMode() == WorkAreaHeader.MODE.TRANSATION )
			{
				// 이미 트랜잭션 상태.
				return false;
			}

			if(typeof backup == 'string')
			{
				gridId = backup;
				backup = true;
			}

			gridId || (gridId = this.getHeaderGridName());
			var grid = this.attachGridItem(gridId);
			if( grid )
			{
				grid.beginTransaction(backup);
				
				// 신규 트랜잭션이 존재하는 경우, NEWTRANSATION 모드로 설정.
				// 그렇지 않으면 TRANSATION 모드로 설정.
				var counter = grid.getTaskCounter();
				header.setMode
				(
					counter.create > 0 
					? WorkAreaHeader.MODE.NEWTRANSATION 
					: WorkAreaHeader.MODE.TRANSATION
				);
			}

			return true;
		}
		,
		/**
		 * 트랜잭션을 종료한다.
		 * @param {boolean} restore - 트랜잭션의 저장/취소를 결정한다.
		 * 								false 인 경우 트랜잭션은 commit 되고,
		 * 								true 인 경우 rollback 된다.
		 * @param {string} gridId - 트랜잭션 대상 그리드 식별자.
		 * @return {$.promise}
		 */
		endTransaction: function(restore, gridId)
		{
			if(typeof restore == 'string')
			{
				gridId = restore;
				restore = true;
			}

			gridId || (gridId = this.getHeaderGridName());
			var grid = this.attachGridItem(gridId);
			if( grid )
			{
				this.setReadyMode();
				return grid.endTransaction(restore);
			}
			else
			{
				return UCMS.retReject();
			}
		}
		,
		isNewTransactionMode: function()
		{
			var header = this.getRendererItem("headerBox");
			return header 
				? header.getButtonMode() == WorkAreaHeader.MODE.NEWTRANSATION
				: false;
		}
		,
		isTransactionMode: function()
		{
			var header = this.getRendererItem("headerBox");
			return header 
				? (header.getButtonMode() == WorkAreaHeader.MODE.TRANSATION || header.getButtonMode() == WorkAreaHeader.MODE.NEWTRANSATION)
				: false;
		}
		,
		setReadyMode: function()
		{
			var header = this.attachHeaderItem("headerBox");
			header.setMode(WorkAreaHeader.MODE.READY);
		}
		,
		setTransationMode: function()
		{
			var header = this.attachHeaderItem("headerBox");
			header.setMode(WorkAreaHeader.MODE.TRANSATION);
		}
		,
		setCreateMode: function()
		{
			this.setTransationMode();
		}
		,
		setUpdateMode: function()
		{
			this.setTransationMode();
		}
		,
		/**
		 * 그리드 데이타를 조회한다.
		 * @param {string} featureId - 조회 기능 식별자
		 * @return {$.xhr}
		 */
		onQuery: function(featureId, options)
		{
			var self = this;
			var fetching = function()
			{
				self.showLoading();
				return self[
						 	self._gridFetcherMode == true
						 	? "fetchingBtGridFetcher"
						 	: "fetchingGrid"
						 ]
						(featureId, options)
				.always(function(e)
				{
					self.clearForm();
					self.hideLoading();
				});
			};

			Logger.info("Renderer.onQuery");
			if( this.isTransactionMode() == true )
			{
				return UCMS.confirm("진행 중인 작업을 저장할까요?", { confirm: "예", cancel: "아니요" })
				.then(function()
				{
					return self.saveTransaction(null, true);
				})
				.fail(function()
				{
					self.onCancel(true);
				})
				.always(function()
				{
					fetching();
				});
			}
			else
			{
				return fetching();
			}
		}
		,
		onCreate: function()
		{
			var counter = this.getActiveGrid().getTaskCounter();
			if( counter.create != 0 )
			{
				UCMS.alert("신규 항목의 등록을 완료하세요.");
				return;
			}
			this._newRowId = this.getActiveGrid().createRow();
			if( this.getActiveForm() )
			{
				this.getActiveForm().getItem().disabled(false);
			}
			this.beginTransaction();
			
			return this._newRowId;
		}
		,
		/**
		 * @return {$.promise}
		 */
		onSave: function()
		{
			return this.saveTransaction();
		}
		,
		/**
		 * 트랜잭션을 저장한다.
		 * 트랜잭션 처리 종료 시점과 연계한 처리가 필요한 경우 반환하는 promise 객체를 사용한다.
		 *
		 * @param {string} featureId - 저장 기능(유형) 선택
		 * @param {boolean} silent - 저장 전 확인할 것인지 지정.
		 * 					생략하거나 true 를 지정하면 확인하지 않고 저장한다.
		 * @param {object} taskData - 트랜잭션 데이타. 지정하지 않는 경우 그리드의 Task 데이타가 사용된다.
		 * @return {$.promise}
		 */
		saveTransaction: function(featureId, silent, taskData)
		{
			if( typeof featureId == "boolean" )
			{
				silent = featureId;
				featureId = null;
			}
			else if( typeof featureId == "string" && typeof silent != "boolean" )
			{
				// 지정하지 않은경우 확인하지 않고 저장
				silent = true;
			}
			else if(!silent)
			{
				silent = false;
			}

			var gridItem = this.getActiveGrid();
			if( gridItem )
			{
				var self = this;
				var apply = function()
				{
					var params = self.getQueryParams() || self.getQueryData();
					
					self.showLoading();
					return gridItem.commit( params, featureId, taskData, self.isValidRowChecker )
					.then(function(res)
					{
						self._newRowId = null;
						self.hideLoading();
						
						//
						if( res.resultCode != 0 )
						{
							if( silent != true )
							{
								if( res.resultCode == -4 )
								{
									UCMS.alert("이미 저장된 데이타가 존재합니다.");
								}
								else
								{
									UCMS.reportError(res);
								}
							}
						}
						else
						{
							// TODO onSave 현재 상태로 롤백을 위해 그리드의 저장된 상태를 백업한다.
							gridItem.beginTransaction(true);
							self.setReadyMode();

							//
							if( silent != true )
							{
								return UCMS.alert("저장되었습니다.")
								.then(function()
								{
									return res;
								});
							}
						}
						return res;
					})
					.fail(function(e)
					{
						self.hideLoading();
						UCMS.reportError(e);
					});
				};
				if( silent == false && gridItem.getTaskCount() > 0 )
				{
					return UCMS.confirm("진행중인 작업을 저장할까요?", {confirm:"저장", cancel:"취소"})
					.then(apply);
				}
				else if( gridItem.getTaskCount() > 0 )
				{
					return apply();
				}
				else if( taskData )
				{
					return apply();
				}
				else
				{
					//return UCMS.retReject({errorCode:-3, msg:"This request is an unacceptable condition.", extraData: null});
					return UCMS.alert("저장할 데이타가 존재하지 않습니다.");
				}
			}
			else
			{
				return UCMS.retResolve();
			}
		}
		,
		/**
		 * 트랜잭션을 취소한다.
		 * @param {boolean} silent - 취소 알림을 사용할지 결정하는 플레그.
		 * 							true 인 경우 알림을 사용하지 않는다.
		 * @return {$.promise}
		 */
		onCancel: function(silent)
		{
			this.clearForm();
			
			var self = this;
			return this.endTransaction(true, this.getHeaderGridName())
			.then(function(cmd)
			{
				self._newRowId = null;
				if( cmd == null )
				{
					if( typeof silent != "boolean" || silent == false )
					{
						UCMS.alert("취소되었습니다.");
					}
				}
				else if( cmd == "refresh" )
				{
					//
					// Backup Model 을 사용하지 않는 모드. 기존 데이타로 되돌린다.
					//
					var gridItem = self.getActiveGrid();
					var selRowId = gridItem.getItem().getSelRowId();
					var promise = self.onQuery(null, {noti:false});
					if( promise )
					{
						promise.always(function()
						{
							gridItem.getItem().setSelectRow( selRowId );
						});
					}
					else
					{
						_.delay(function()
						{
							gridItem.getItem().setSelectRow( selRowId );
						}
						, 500);
					}
				}
			});
		}
		,
		onDelete: function()
		{
			var gridItem = this.getActiveGrid();
			if(! gridItem)
			{
				Logger.info("onDelete() - Find not found a grid item.");
				return UCMS.retReject();
			}
			var counter = gridItem.getTaskCounter();
			if( counter.create != 0 )
			{
				UCMS.alert("신규 항목의 등록 완료후 다시 시도해 주세요.");
				return UCMS.retReject();
			}

			var self = this;
			var grid = gridItem.getItem();
			var ids = grid.getSelRowId(true);
			if( ids.length == 0 )
			{
				ids = [grid.getSelRowId()];
			}

			if( ids[0] == null )
			{
				UCMS.alert("선택된 행이 없습니다.");
				return UCMS.retReject();
			}
			
			var remove = function()
			{
				self.clearForm();
				var deleteTasks = [];
				for(var i in ids)
				{
					var rowData = grid.getRowData( ids[i] );
					rowData.dsType = "D";
					deleteTasks.push( rowData );
					grid.removeRow( ids[i], true );
				}

				self.showLoading();
				return self.sendTrasction(null, self.getQueryData(), deleteTasks)
				.then(function(res)
				{
					if( res.resultCode == 0 )
					{
						UCMS.alert("삭제되었습니다.");
					}
					else
					{
						UCMS.reportError(res);
					}
					
					return res;
				})
				.fail(function(err)
				{
					UCMS.reportError(err);
				})
				.always(function()
				{
					self.hideLoading();
				});
			};
			return UCMS.confirm(ids.length+"건의 데이타를 삭제하시겠습니까?", {confirm:"삭제", cancel:"취소"})
			.then(remove);
		}
		,
		/**
		 * 랜더러를 종료한다.
		 * 트랜잭션이 존재하는 경우 종료 확인창이 출력된다.
		 */
		onCloseRenderer: function()
		{
			this.closeRenderer();
		}
		,
		onManual: function()
		{
			var hosts = NDSProps.get("hosts").manual;
			var path = "/openmps-mps-manual/";
			var partId = this._contentId.substr(0,2);
			var url = hosts + path + partId + "/" + this._contentId + ".pdf";
			var man = window.open(url, '_manual_');
			
			// TODO 테스트 완료 후 창 닫기 적용
			//man.close();
		}
		,
		onDownload: function()
		{
			this.downloadExcel();
		}
		,
		/**
		 * 인쇄
		 */
		onPrint: function()
		{
			var gridItem = this.getActiveGrid();
			// 메인 그리드에 설정된 데이타 개수
			var extraDataLength = gridItem.getRowCounter();
			/*
			 * 조회 - 인쇄 - 조회를 누를때 두번째 조회때 이벤트리스너 동작함..
			 * 20171013 : 보아씨 추후 수정예정 주석처리! 
				var self = this;
				var data = this.getQueryData();
				
				if(extraDataLength == null){
					UCMS.alert("조회를 먼저 해주세요.");
					
				}
				else if(extraDataLength != 0 ){
					
					var partId = this._contentId.substr(0,2);
					var jrfDir = 'report/'+partId+'/'+this._contentId+'_report.jrf';
					
					this.popupReportView( jrfDir , data );
				}else if(extraDataLength == 0){
					UCMS.alert("조회된 데이터가 없습니다.");
				}
			 */
				var data = this.getQueryData();
				var key = _.keys(data);
				var partId = this._contentId.substr(0,2);
				var conId = this._contentId;
				var tabId = this._activeTabId;
				var jrfDir, paramVal;
				
				if(partId == 'PO'){
					if(conId == 'PO0101'){
						// 탕박(tab_nonskin), 박피(tab_skin)
						var gubunTab = tabId.replace('tabAreaBox.tab_','');
						jrfDir = 'report/'+partId+'/'+this._contentId + '_'  + gubunTab + '_report.jrf';
					}
					if(conId == 'PO0203'){
						if(data.gubun_1 == 0 || data.gubun_1 < 0){
							jrfDir = 'report/'+partId+'/'+this._contentId + '_gubun1_report.jrf';
						}else{
							jrfDir = 'report/'+partId+'/'+this._contentId + '_gubun2_report.jrf';
						}
					}
					if(conId == 'PO0302'){
						if(tabId == 'tab_01'){
							UCMS.alert("해당 선택 영역은 인쇄지원을 하지 않습니다.");
						}else{
							jrfDir = 'report/'+partId+'/'+this._contentId+'_report.jrf';
						}
					}
				}else if(partId == 'PP'){
					 if(conId == 'PP0202'){
						 if(tabId == 'tabAreaBox.tab_result_1.grid'){
							 jrfDir = 'report/'+partId+'/'+this._contentId + '_'  + 'tab1_report.jrf';
						 }
						 if(tabId == 'tabAreaBox.tab_result_2.grid'){
							 jrfDir = 'report/'+partId+'/'+this._contentId + '_'  + 'tab2_report.jrf';
						 }
						 if(tabId == 'tabAreaBox.tab_result_3.grid'){
							 jrfDir = 'report/'+partId+'/'+this._contentId + '_'  + 'tab3_report.jrf';
						 }
					}
					 else if(conId == 'PP0203'){
						 // 화면설계 탭구분 않되어있음 구분잡은 후 분기처리 예정
						 // var gubunTab = tabId.replace('tabAreaBox.tab_',''); /
						 // jrfDir = 'report/'+partId+'/'+this._contentId + '_'  + gubunTab + '_report.jrf';
						 jrfDir = 'report/' + partId + '/PP0203_tab1_report.jrf';
					}
					else if(conId == 'PP0204'){
						jrfDir = 'report/'+partId+'/'+this._contentId+'_report.jrf';
					}
					else if(conId == 'PP0302'){
						if(tabId == 'tab_total'){
							UCMS.alert("해당 합계 영역은 인쇄 지원을 하지 않습니다..");
						}else{
							jrfDir = 'report/'+partId+'/'+this._contentId+'_report.jrf';
						}
					}
					else if(conId == 'PP0306'){
						if(key == "workDate,plantCode"){
							jrfDir = 'report/'+partId+'/'+this._contentId+'_report.jrf';	
						}else{
							jrfDir = 'report/'+partId+'/'+this._contentId+'_date_report.jrf';	
						}
					}
					else if(conId == 'PP0503'){
						jrfDir = 'report/'+partId+'/'+this._contentId+'_report.jrf';
					}
					else if(conId == 'PP0602'){
						var gubunTab = tabId.replace('tab_','');
						jrfDir = 'report/'+partId+'/'+this._contentId + '_'  + gubunTab + '_report.jrf';
					}else{
						jrfDir = 'report/'+partId+'/'+this._contentId+'_report.jrf';
					}
				}else{
					jrfDir = 'report/'+partId+'/'+this._contentId+'_report.jrf';
				}
				
				/************* SD 영업  /s/**************/
				//판매단가(견적서)
				if(conId == 'SD0203')
				{
					paramVal = '&scCorpCode=1001' + '&scStrtDate=' + data.strtDate.replace(/-/gi, "")+ '&scEndDate=' + data.lastDate.replace(/-/gi, "") + '&scSalesMan=' + data.salesman + '&scCustCode=' + data.custCode.result;
				}
				//잔액조회서 : 화면않나옴 추후확인
				if(conId == 'SD0506')
				{
					//paramVal = '&scCorpCode=1001' + '&scCustCode=' + data.custCode + '&scSaleDate=' + data.saleDate + '&scHeadCode=' + data.headCode + '&scTeamCode=' + data.teamCode + '&scSearchCondition=' + data.searchCondition;  
				}
				/************* SD 영업 /e/**************/
				
				/************* PP 생산  /s/**************/
				//생산수율표 조회/발행(일)
				if(conId == 'PP0202')
				{
					paramVal = '&scCorpCode=1001' + '&scStrtDate=' + data.strtDate.replace(/-/gi, "")+ '&scEndDate=' + data.lastDate.replace(/-/gi, "") + '&scGrupPlant=' + data.grupPlant;
				}
				//생산수율표 조회/발행(기간별)
				if(conId == 'PP0203')
				{
					paramVal = '&scCorpCode=1001' + '&scDangStrtDate=' + data.junStrtDate.replace(/-/gi, "") + '&scDangEndDate=' + data.junLastDateBe.replace(/-/gi, "") +
																		'&scJunStrtDate=' + data.dangStrtDateNo.replace(/-/gi, "") + '&scJunEndDate=' + data.dangLastDateNo.replace(/-/gi, "") + 
																		'&scGrupPlant=' + data.grupPlant;
				}
				//품목별 생산량 및 수율 조회
				if(conId == 'PP0204')
				{
					paramVal = '&scCorpCode=1001' + '&scStrtDate=' + data.strtDate.replace(/-/gi, "") + '&scGrupPlant=' + data.grupPlant;
				}
				//품목별 생산량 및 수율조회
				if(conId == 'PP0204')
				{
					paramVal = '&scCorpCode=1001' + '&scStrtDate=' + data.strtDate.replace(/-/gi, "") + '&scGrupPlant=' + data.grupPlant;
				}
				//생산계획입력(CM) : 합계 탭 영역은 인쇄지원 없음
				if(conId == 'PP0302')
				{
					paramVal = '&scCorpCode=1001' + '&scWorkDate=' + data.workDate.replace(/-/gi, "") + '&scPlantCode=' + data.plantCode +  '&scProCode=' + data.proCode.result;
				}
				//생산계획서조회/발행(CM) - 소계 제외 백업본 : PP0303_report_backup.jrf
				if(conId == 'PP0303')
				{
					paramVal = '&scCorpCode=1001' + '&scPlantCode=' + data.plantCode + '&scWorkDate=' + data.workDate.replace(/-/gi, "");
				}
				//생산계획서 조회/발행(PM) : 현재 font 9로 설정
				if(conId == 'PP0306')
				{
					if(key == "workDate,plantCode" ) //planTime: 0
					{
						paramVal = '&scCorpCode=1001' + '&scWorkDate=' + data.workDate.replace(/-/gi, "") + '&scPlantCode=' + data.plantCode;
					}
					else
					{
						paramVal = '&scCorpCode=1001' + '&scWorkDate=' + data.workDate.replace(/-/gi, "") + '&scPlantCode=' + data.plantCode + '&scPlanTime=' + data.planTime;
					}
				}
				//생산vs입고현황조회
				if(conId == 'PP0503')
				{
					paramVal = '&scCorpCode=1001' + '&scProDate=' + data.strtDate.replace(/-/gi, "") + '&scPlantCode=' + data.plantCode;
				}
				//공장별 생산현황 조회(집계)
				if(conId == 'PP0602')
				{
					paramVal = '&scCorpCode=1001' +  '&scStrtDate=' + data.strtDate.replace(/-/gi, "") + '&scEndDate=' + data.lastDate.replace(/-/gi, "")  + '&scGrupPlant=' + data.plantCode;
				}
				//공장별 생산현황 조회(개체별) : 현재 font 7로 설정
				if(conId == 'PP0603')
				{
					paramVal = '&scCorpCode=1001' +  '&scStrtDate=' + data.strtDate.replace(/-/gi, "") + '&scEndDate=' + data.lastDate.replace(/-/gi, "")  + '&scGrupPlant=' + data.plantCode;
				}
				//원료별 생산일보 조회/발행
				if(conId == 'PP0605')
				{
					paramVal = '&scCorpCode=1001' +  '&scWorkDate=' + data.strtDate.replace(/-/gi, "") + '&scGrupPlant=' + data.plantCode  + '&scSearchCondition=' + data.searchKeyword;
				}
				/************* PP 생산  /e/**************/
				
				/************* PO 구매  /s/**************/
				//1.지육시세등록
				if(conId == 'PO0101')
				{
					paramVal = '&scCorpCode=1001' + '&scStrtDate=' + data.strtDate.replace(/-/gi, "") + '&scEndDate=' + data.lastDate.replace(/-/gi, "");
				}
				//2.출하정산집계표조회
				if(conId == 'PO0203')
				{
					paramVal = '&scCorpCode=1001' + '&scStrtDate=' + data.strtDate.replace(/-/gi, "") + '&scEndDate=' + data.lastDate.replace(/-/gi, "") +
										 '&scFacKind=' + data.facKind + '&scBuyType=' + data.buyType + '&scBrandCode=' + data.brandCode + '&scRepCust=' + data.custCode.result + 
										 '&scSearchCondition=' + data.gubun_1 + '&scSearchCondition3=' + data.gubun_3 + '&scSearchCondition4=' + data.gubun_4 ;
				}
				//3.이상육 발생현황 조회
				if(conId == 'PO0302')
				{
					paramVal = '&scCorpCode=1001' + '&scStrtDate=' + data.strtDate.replace(/-/gi, "") + '&scEndDate=' + data.lastDate.replace(/-/gi, "") + 
										 '&scBrandCode=' + data.brandCode + '&scRepCust=' + data.custCode.result + '&scOthPart=' + data.othPart;
				}
				/************* PO 구매  /e/**************/
				
				this.popupReportView( jrfDir , paramVal );
		}
		,
		/**
		 * 현재 적용된 조회 파라메터를 얻는다.
		 * @return {object|null}
		 */
		getQueryParams: function()
		{
			return this._queryParams || null;
		}
	};

	/**
	 * 랜더러 항목으로써 필요한 동작의 구현을 지원하는 Wrapper 로 감싼 랜더러 Item 객체를 제공한다.
	 */
	var RendererItemMethod =
	{
		/**
		 * 아이템 인스턴스를 랜더러 객체에서 얻는다.
		 * 종속되는 BaroBox 가 있다면, 해당 Box 는 지시자 "." 을 지정하고, 하위 노드를 지정할 수 있다.
		 * @param {string} itemId - 아이템 식별자. 서브 BaroBox 의 내부 인스턴스를 얻는 경우 "."로 구분하여 지정한다.
		 * @return 지정한 아이템 인스턴스. 인스턴스가 존재하지 않는 경우 null 을 반환한다.
		 */
		getItemInstance: function(itemId)
		{
			if(typeof itemId != "string")
			{
				return null;
			}

			var item = this;
			var itemChain = itemId.split(".");
			for(var i in itemChain)
			{
				var nodeId = itemChain[i];
				item = item._getWidgetInstance(nodeId);
				if( item == null )
				{
					Logger.warn("getItemInstance() - Find not found a instance : "+nodeId+", itemId : "+itemId);
					break;
				}
			}

			return item;
		}
		,
		/**
		 * 활성화된 Wrapper 를 얻는다.
		 * attachHeader(), attachGrid(), attachForm() 에 의해 활성화 된 랜더러 항목의 내부 객체를 얻는다.
		 */
		getRendererItem: function(itemId)
		{
			return this._items[itemId] ? this._items[itemId].getItem() : null;
		}
		,
		/**
		 * 그리드에 대한 랜더러 아이템 Wrapper 를 생성한다.
		 * @param {string} itemId - 아이템 식별자
		 * @param {string} cid - content Id. 특정 콘텐츠를 출력하도록 지정하는 경우 사용.
		 * 						지정하지 않으면 페이지에 설정된 콘텐츠가 출력된다.
		 */
		attachGridItem: function(itemId, cid)
		{
			if(! this._items[itemId] )
			{
				var grid = this.getItemInstance( itemId );
				if(! grid )
				{
					Logger.warn("attachGridItem() - Unknown Grid Item : "+itemId);
					return null;
				}
				var options =
				{
					id : itemId,
					item: grid,
					client: this.getClient(cid)
				};
				this._items[itemId] = new bases.Grid(options);
			}
			return this._items[itemId];
		}
		,
		/**
		 * 콘테이너에 대한 랜더러 아이템 Wrapper 를 생성한다.
		 * @param {string} itemId - 아이템 식별자
		 * @param {function} cbHandler
		 */
		attachHeaderItem: function(itemId, cbHandler)
		{
			if(! this._items[itemId] )
			{
				var header = this.getItemInstance( itemId );
				if(! header )
				{
					Logger.warn("attachHeaderItem() - Unknown header Item : "+itemId);
					return null;
				}
				var options =
				{
					id : itemId,
					item: header,
					handler: cbHandler
				};
				this._items[itemId] = new bases.Header(options);
			}
			return this._items[itemId];
		}
		,
		/**
		 * 폼에 대한 랜더러 아이템 Wrapper 를 생성한다.
		 * @param {string} itemId - 아이템 식별자
		 */
		attachFormItem: function(itemId)
		{
			if(! this._items[itemId] )
			{
				var form = this.getItemInstance( itemId );
				if(! form )
				{
					Logger.warn("attachFormItem() - Unknown Form Item : "+itemId);
					return null;
				}
				var options =
				{
					id : itemId,
					item: form
				};
				this._items[itemId] = new bases.Form(options);
			}
			return this._items[itemId];
		}
	};

	var FormMethod =
	{
		onSearchCode: function(options, cbComplete)
		{
			UCMS.debug("onSearchCode()");
			UCMS.debug(options);

			var boxRepo = NDSProps.get("BoxRepo");
			var appName = NDSProps.get("appName");

			UCMS.loadBoxResource( boxRepo["codesearch"].detail )
			.then(function(boxParams)
			{
				UCMS.loadModuleByPath(["BaroFloating", boxParams.module])
				.then(function(BaroFloating, Module)
				{
					var cbResult = function(result)
					{
						if( options.callback )
						{
							options.callback( result );
						}
						if( typeof cbComplete == "function" )
						{
							cbComplete( result );
						}
						BaroFloating.close();
					};

					BaroFloating.open
					(
						Module,
						_.extend({ codeType: options.codeType, params: options.params, keyword: options.keyword }, boxParams.options),
						{
							title: appName,
							close: function()
							{
								cbResult({});
							}
							,
							className: "modal_box codesearch"
						}
					)
					.done(function(result)
					{
						cbResult(result);
					});
				});
			});
		}
		,
		/**
		 * 폼 아이템의 변경된 데이타를 그리드 아이템에 적용한다.
		 */
		onChangeFormData: function(gridItem, formItem)
		{
			this.beginTransaction();

			//
			var rowId = gridItem.getItem().getSelRowId();
			var gridData = gridItem.getItem().getRowData(rowId);
			var formData = formItem.getData();
			Logger.debug("changed row : "+rowId);
			// 폼에는 그리드의 모든 필드 정보가 노출되지 않을 수도 있다.
			// TODO 그리드의 필드에 변경된 폼의 정보를 부가하는 방식을 사용해야, 트랜잭션 데이타가 누락되는 필드없이 생성된다.
			gridItem.setRow(rowId, _.extend(gridData, formData));
		}
		,
		/**
		 * 돔 객체 input[type=file] 의 change 이벤트 핸들러를 구현한다.
		 * IE 에서 파일 변경 이벤트가 2 번 발생하는 상황이 발생했음.
		 * 파일이 2 번 올라가는 문제가 발생되지 않는 조처가 필요함
		 * 
		 * @return { $.xhr }
		 */
		onChangeUploadFile: function(inputTag, item)
		{
			var $excelFile = $(inputTag);
			if( this.isTransactionMode() == true )
			{
				var self = this;
				return UCMS.confirm("진행 중인 작업을 저장할까요?", { confirm: "예", cancel: "아니요" })
				.done(function()
				{
					self.onSave(null, true);
					return self.uploadExcel($excelFile);
				})
				.fail(function()
				{
					self.onCancel(true);
					return self.uploadExcel($excelFile);
				});
			}
			else
			{
				return this.uploadExcel($excelFile);
			}
		}
		,
		clearForm: function(formId)
		{
			var form = this.getActiveForm(formId);
			if( form )
			{
				form.clear();
				form.getItem().disabled(true);
			}
		}
	};

	var GridMethod =
	{
		/**
		 * 행이 선택된 경우 발생되는 이벤트
		 *
		 * @param {object} selected - 선택된 행 정보
		 * 							  {id:"row id", data:{object}}
		 */
		onSelectRow: function(selected)
		{
			var formItem = this.getActiveForm();
			if( formItem )
			{
				formItem.setData( selected.data, true );
				formItem.getItem().disabled(false);
			}
		}
		,
		/**
		 * 자동 페이징 옵션을 생성한다.
		 * 데이타 요청 방식이 변경된다. 스크롤될 때 페이징 요청이 자동으로 발생된다.
		 */
		makeAutoScrollingOptions: function(pageSize, apiPath)
		{
			var pageSize = pageSize || 100;
			var pagingOptions = 
			{
				"url": apiPath || "/",
				"datatype": "json",
				"jsonReader": 
				{
					// total page count
					"total": function(res) { return Math.ceil(res.extraData.totalRecordCount / pageSize); },
					// total recod count
					"records": function(res) { return res.extraData.totalRecordCount; },
					// current page index
					"page": "extraData.pageIndex", 
					"root": "extraData.result",
					"repeatitems": false
				},
				"rowNum": pageSize,
				"scroll": 1,
				"prmNames": {
					"page": "pageIndex",
					"rows": "pageSize",
					"sort": null,
					"order": null,
					"search": null
				},
				"viewrecords": true,
				"gridview": true,
				"loadComplete": function(data)
				{
					Logger.debug(data);
					if( data.resultCode != 0 )
					{
						return;
					}
					if( data.extraData.result.length == 0 )
					{
						UCMS.alert("조회된 데이타가 없습니다.");
					}
				}
			};
			
			// grid 의 내부 fetcher 사용 모드 활성화
			this._gridFetcherMode = true;

			return pagingOptions;
		}
		,
		/**
		 * 로컬 모드에서 페이징 처리를 지원한다.
		 * 그리드에 데이타를 추가하는 방식이 변경된다.
		 */
		makeAutoScrollingOptions2_local: function(pageSize, apiPath)
		{
			var pageSize = pageSize || 100;
			var pagingOptions = 
			{
				"datatype": "local",
				"jsonReader": 
				{
					"repeatitems": false
				},
				"rowNum": pageSize,
				"scroll": 1,
				"gridview": true,
				"viewrecords": true,
				"loadonce": true
			};
			
			this._localPagingMode = true;

			return pagingOptions;
		}
		,
		/**
		 * Task commit 시 데이타의 유효성을 검증한다.
		 * 기본적으로 true 를 반환하며, 검증이 필요한 랜더러에서 상속 받아서 제공되는 Task 정보의 유효성 검증 결과를 boolean 값으로 반환처리한다.
		 * @param {string} gridId - commit 이 발생된 그리드의 식별자
		 * @param {object} taskItem - 트랜잭션 항목 객체
		 * @return {boolean|string} false 또는 에러 메시지를 리턴하면, 해당 commit 동작은 reject 되며, 문제가 된 task 항목에 대한 정보 { id, task } 가 반환된다.
		 */
		isValidRowChecker: function(gridId, taskItem)
		{
			return true;
		}
		,
		/**
		 * 신규로 추가된 저장되지 않은 항목의 row Id 를 얻는다.
		 */
		getNewRowId: function()
		{
			return this._newRowId;
		}
		,
		/**
		 * 그리드 데이타를 조회한다.
		 * @param {string} featureId - 조회 기능 식별자
		 * @param {object} options - 조회 옵션.
		 * 							{
		 * 								noti: {boolean}, 조회 결과를 통지할지를 결정하는 플레그
		 * 								params: {object} 조회 파라메터
		 * 							}
		 * @param {string} gridId - 그리드 식별자.
		 * @return {$.xhr}
		 */
		fetchingGrid: function(featureId, options, gridId)
		{
			options || (options={});
			this._queryParams = options.params || this.getQueryData();
			var gridItem = this.getActiveGrid(gridId);
			var showNothingMsg = function()
			{
				if( options.noti != false )
				{
					UCMS.alert("조회된 데이타가 없습니다.");
				}
			};
			var self = this;

			gridItem.clear();
			return gridItem.fetch( this._queryParams, featureId )
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					if( res.extraData.result )
					{
						Logger.debug("Fetched grid data count : "+res.extraData.result.length);
						if( res.extraData.result.length == 0 )
						{
							showNothingMsg();
						}
						else
						{
							gridItem.setData(
								res.extraData.result, 
								self._localPagingMode == true ? "local" : "loop" );

							// TODO fetchingGrid 롤백을 위해서 새로 채워진 데이타를 백업한다.
							gridItem.beginTransaction(true);
						}
					}
					else
					{
						Logger.warn("fetchingGrid() - Returned a null.");
						showNothingMsg();
					}
				}
				else
				{
					UCMS.reportError(res,"데이타 조회를 실패하였습니다.");
				}
				
				return res;
			})
			.fail(function(e)
			{
				UCMS.reportError(e,"데이타 조회를 실패하였습니다.");
			});
		}
		,
		/**
		 * 그리드 자체 조회기능을 사용한 조회 요청 처리
		 */
		fetchingBtGridFetcher: function(featureId, options, gridId)
		{
			options || (options={});
			var params = options.params || this.getQueryData();
			var apiPath = this.getClient().getAPIPath("read", null, params)
				+ "?" + this.makeUrlParams(params);
			var gridItem = this.getActiveGrid(gridId);
			gridItem.clear();
			gridItem.getItem().setGridParam({"url": apiPath});
			
			return UCMS.retResolve();
		}
	}; // GridMethod

	/**
	 * 기타 기능 메소드 모음
	 */
	var FeatureMethod =
	{
		/**
		 * 엑셀파일을 업로드하고, 결과를 그리드에 추가한다.
		 * 추가된 데이타가 존재하면 트랜잭션 모드가 시작된다.
		 * @return {$.xhr}
		 */
		uploadExcel: function($input, errHandler)
		{
			var self = this;
			var client = this.getClient();
			var uploader = new FormClient({params:{dataType: "text"}});

			Logger.info("uploadExcel() - file : "+$input.val());

			this.showLoading();
			uploader.append( $input );
			
			var params = this.getQueryData();
			var apiPath = client.getAPIPath('create', "excel", params);

			//
			return uploader.submit( params?apiPath+"?"+UCMS.makeUrlParams(params):apiPath )
			.then(function(res)
			{
				try
				{
					res = JSON.parse(res);
					if( res.resultCode == 0 )
					{
						if( res.extraData.result.length > 0 )
						{
							var gridItem = self.getActiveGrid();
							gridItem.clear();
							gridItem.createRow( res.extraData.result );

							//
							self.beginTransaction( true );
						}
						else
						{
							UCMS.alert("등록된 데이타가 없습니다.");
						}
					}
					else
					{
						if(errHandler)
						{
							errHandler(res);
						}
						else
						{
							UCMS.reportError(res, "파일 처리중 오류가 발생하였습니다.<br>업로드 파일을 확인하세요.");
						}
					}
					return res;
				}
				catch(e)
				{
					UCMS.reportError(e);
				}
			})
			.fail(function(e)
			{
				Logger.error(e);
				UCMS.reportError(e, "엑셀 업로드를 실패하였습니다.");
			})
			.always(function()
			{
				$input.val("");
				self.hideLoading();
				uploader.clear();
			});
		}
		,
		/**
		 * 신고파일 다운로드
		 */
		makeReportFile: function()
		{
			var client = this.getClient();
			if( !client )
			{
				return UCMS.retReject();
			}
			var params = this.getQueryData();
			return client.create( params, "download" )
			.then
			(
				function(res)
				{
					if( res.resultCode == 0 )
					{
						UCMS.alert("신고파일이 생성되었습니다.");
					}
					else
					{
						UCMS.reportError(res);
					}
				}
				,
				function(err)
				{
					UCMS.reportError(err);
				}
			);
		}
		,
		/**
		 * 이력 신고
		 * apply(), call() 로 호출한다.
		 */
		postReport: function()
		{
			var grid = this.getActiveGrid().getItem();
			var rowData = grid.getRowData();
			if( rowData.length == 0 )
			{
				UCMS.alert("신고할 내역이 존재하지 않습니다.");
				return;
			}
			var client = this.getClient();
			if( !client )
			{
				return UCMS.retReject();
			}
			var params = this.getQueryData();
			return client.create( params, "openapi" )
			.then
			(
				function(res)
				{
					if( res.resultCode == 0 )
					{
						UCMS.alert(rowData.length+"건이 신고 되었습니다.");
					}
					else
					{
						UCMS.reportError(res);
					}
				}
				,
				function(err)
				{
					UCMS.reportError(err);
				}
			);
		}
		,
		/**
		 * 날짜 입력을 위한 캘린더를 팝업한다.
		 * @param {string} rowId - 날짜가 입력되는 행의 식별자
		 * @param {string} celName - 날짜가 입력되는 컬럼의 이름
		 * @param {object} options - Bootstrap DatetimePicker 의 활성화 옵션. 필요시 지정
		 * @return {$} 캘린더가 활성화되는 input 태그의 $ 객체
		 */
		popupGridCalender: function(rowId, celName, options)
		{
			/*
			var $cell = $("#"+rowId+" input[name="+celName+"]");
			$cell.datetimepicker(_.extend({"format": 'YYYY-MM-DD', "useCurrent":true}, options));
			$cell.data("DateTimePicker").defaultDate(new Date());
			$cell.data("DateTimePicker").show();
			return $cell;
			*/
			return UCMS.popupGridCalender(rowId, celName, options);
		}
		,
		/**
		 * 트랜잭션을 서버로 전달한다.
		 *
		 * @param {string} featureId
		 * @param {object} params
		 * @param {array} taskData
		 * @return {$.xhr}
		 */
		sendTrasction: function(featureId, params, taskData)
		{
			var client = this.getClient();
			var apiPath = client.getAPIPath("create", featureId, params);
			return client.transaction( apiPath, taskData );
		}
		,
		/**
		 * CSV를 다운로드 합니다.
		 * @see http://www.jqueryscript.net/table/Multi-functional-Table-To-CSV-Converter-With-jQuery-TableCSVExport.html
		 * @param {string} gridId - 해당 그리드 아이디
		 * @param {string} fileName - 저장할 파일 이름
		 * @param {boolean} hidden - 히트 테이블 보이기
		 */
		downloadExcel: function(gridId,fileName,hidden)
		{

			UCMS.confirm("엑셀 파일을<br>다운로드 하시겠습니까?")
			.done(function()
			{
				jQuery("#"+gridId+"_list").TableCSVExport({
				  separator: ',',
				  header: [],
				  columns: [],
				  extraHeader: "",
				  extraData: [],
				  insertBefore: fileName ,
				  delivery: 'download' /* popup, value, download */,
				  emptyValue: '',
				  showHiddenRows: hidden,
				  rowFilter: "",
				  filename: fileName + ".csv"
				});
				// UCMS.alert("엑셀이 다운로드되었습니다.");
			});


			/*
			var client = this.getClient();
			if( !client )
			{
				return UCMS.retReject();
			}
			var params = this.getQueryData();
			var path = client.getAPIPath("read", featureId, params)
						+ "/export?"
						+ this.makeUrlParams(params);
							*/

			/*
			var dnWnd = window.open( path, "_download_" );
			_.delay(function()
			{
				dnWnd.close();
			}
			, 3000);
			*/

			//UCMS.alert(featureId)


		}
		,
		/**
		 * @param {object} options - URL 로 전달되는 파라메터 정보가 담긴 객체
		 * @return {string} url 의 "?" 뒤에 붙는 파라메터 스트링
		 */
		makeUrlParams: function(options)
		{
			return UCMS.makeUrlParams(options);
		}
		,
		/**
		 * 유비레포트 뷰어를 팝업한다.
		 */
		popupReportView: function(jrfDir, params)
		{
			Logger.debug("PopupReportView");
			
			if(! jrfDir )
			{
				Logger.error("popupReportView() - Invalid parameter : jrfDir");
				return;
			}
			
			var data = this.getQueryData();
			var key = _.keys(data);
			var value = _.values(data);
			var tabId = this._activeTabId;
			var conId = this._contentId;
			
			Logger.debug(">>> jrfDir <<< : " + jrfDir);
			Logger.debug(">>> data key <<< : " + key);     
			Logger.debug(">>> data value <<< : " + value);
			Logger.debug(">>> tabId <<< : " + tabId);

			/**
			 * 
			 * 
			 * 개발설치후 변경 바람.
			 * Report Host 
			 * 
			 * 개발 http://openmps.cloud.nongshim.co.kr:8080/
			 * 
			 * 임시 테스트 주소 http://172.27.62.234:8080
			 * */
			var url = "http://" + window.location.hostname + ":8080" + '/common/commonAuthUbiPrint.do?jrfDir=' + jrfDir + params;
			var widthX = 1080;
			var heightY = 830;
			var left = (window.screen.width - widthX) / 2;
			var top = (window.screen.height - heightY) / 2;
			var strProp;
			strProp = 'height=' +heightY + ',width=' + widthX + ',menubar=no,toolbar=no, location=no, resizable=no, status=no, scrollbars=no, top=' + top + ',left=' + left
			window.open(url,'ConfPrint', strProp);
		}
	}; // FeatureMethod

	/**
	 * 랜더러 구현체에서 구현이 필요한 메소드 모음
	 */
	var OverridingMethod =
	{
		/**
		 * 헤더 기능 버튼을 초기화한다.
		 * 기본적으로 설정한 기능 버튼에 커스터마이징이 필요한 경우에만 Overriding 한다.
		 */
		initSubHeader: function(headerParams)
		{
			Logger.debug("Renderer.initSubHeader()");

			if( this.$el.find(".headerBox_region").length > 0 )
			{
				this.headerBox.close();
			}
			else
			{
				// 모든 레전의 제일 앞에 배치
				this.$el.prepend('<div class="headerBox_region"></div>');
				this.addRegions({
					"headerBox": ".headerBox_region"
				});
			}

			var self = this;
			var setHeaderParams = function(headerParams) {
				// 콘텐츠 헤더 정보를 제일 앞에 추가한다.
				var order = self.getParam('order') || [];
				order.unshift("headerBox");
				self.setParam("order", order)
				self.setParam("headerBox", headerParams);
			};
			if( headerParams )
			{
				setHeaderParams( headerParams );
			}
			else
			{
				return this.queryHeaderInfo().then(setHeaderParams);
			}
		}
		,
		/**
		 * 접근이 필요한 아이템 목록
		 * 지정한 아이템 인스턴스가 활성화되면 랜더러 동작이 시작된다.
		 * 해당 아이템은 getItemInstance() 로 접근하여 인스턴스에 접근 가능하다.
		 * @return {array} 아이템 목록.
		 * 					목록에 담기는 아이템의 순서는 종속 관계를 고려하여 절차적으로 접근할 수 있도록 지정한다.
		 */
		getItemList: function()
		{
			return [];
		}
		,
		/**
		 * 트랜젝션의 중심이 되는 마스터 그리스 식별자를 얻는다.
		 * 업무단에서 UI 맞는 이름을 반환한다.
		 */
		getHeaderGridName: function()
		{
			return "resultBox";
		}
		,
		getFormName: function()
		{
			return "formBox";
		}
		,
		/**
		 * 아이템 간의 관계 설정 또는 특정 아이템의 이벤트 처리를 구현한다.
		 */
		onInitRendererItemEvents: function()
		{

		}
	};

	bases.EVENT =
	{
		CLOSERENDERER: "renderer:close"			// {id: {string} renderer 식별자, force: {boolean} 강제 종료 유무}
	}

	bases.Method = _.extend
	(
		{}
		, MainMethod
		, HeaderMethod
		, RendererItemMethod
		, FormMethod
		, GridMethod
		, FeatureMethod
		, OverridingMethod
	);

	return bases;
});
