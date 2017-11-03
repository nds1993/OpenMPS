/**
 * Project : openMPS MIS
 *
 * 영업 > 할인단가 등록
 *
 */

define([
	"Logger",
	"NDSProps",
	"BaroBox",
	"FormBox",
	"WorkAreaHeader",
	"WorkAreaRenderer2",
	"RendererBase",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, BaroBox, FormBox, WorkAreaHeader, WorkAreaRenderer2, RendererBase, JQGrid)
{
	var OverridingMethod =
	{
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "SD0205";
		}
		,

		/**
		 * 1. 헤더 기능 버튼 초기화
		 */
		initSubHeader: function()
		{
			Logger.debug("Renderer.initSubHeader()");

			var self = this;
			return this.queryHeaderInfo().then(function(headerParams)
			{
				//
				headerParams.options.feature =
				[
					{"query":"조회"},
					{"create":"신규"},
					{"save":"저장"},
					{"delete":"삭제"},
					{"cancel":"취소"},
					{"manual":"도움말"}
				];

				Renderer.__super__.initSubHeader.call( self, headerParams );
			});
		}
		,
		/**
		 * 2. 접근이 필요한 아이템 목록 지정
		 */
		getItemList: function()
		{
			return [
			        "queryBox",
					"resultBox_1",
					"resultBox_1.header",
					"resultBox_1.content",
					"purposeBox",
					"resultBox_2"
			        ];
		}
		,
		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
		getHeaderGridName: function()
		{
			return "resultBox_1.content";
		}
		,
		/**
		 * 4. 랜더러 아이템 관계성 초기화
		 */
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachHeaderItem("headerBox");
			this.attachFormItem("queryBox");

			//
			this.attachHeaderItem("resultBox_1.header", function(event)
			{
				self.onButtonHandler(event);
			});
			var headGridItem = this.attachGridItem("resultBox_1.content");
			headGridItem.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0205_grid_1","할인단가_거래처",false);
			});

			headGridItem.getItem().on(JQGrid.EVENT.SELECTCELL, function(item)
			{
				Logger.debug("SELECTCELL row id : "+item.id);
				Logger.debug(item);
				
				var data = headGridItem.getItem().getRowData(item.id);
				
				if(item.cell.name == "approYnName")
				{
					if( data.approYn == "X" )
					{
						// 반려 의견 팝업
						UCMS.msgBox( "반려의견 : \r"+data.approMemo );
					}
				}
				else if(item.cell.name == "custCode"
					|| item.cell.name == "custName"
					|| item.cell.name == "partDate"
					|| item.cell.name == "headDate"
					|| item.cell.name == "ceoDate")
				{
					self._headRowId = item.id;
					self.onSelectRow( data );
				}
			
			});
			headGridItem.getItem().on(JQGrid.EVENT.BEFOREEDITCELL, function(item)
			{
				Logger.debug("BEFOREEDITCELL row id : "+item.id+" - Editing : "+headGridItem.getItem().isEditMode());
				Logger.debug(item);

				/**
				if( headGridItem.getTaskList().length > 0 )
				{
					UCMS.alert("작업중인 거래처를 완료하고 진행하세요.");
					return;
				}
				**/

				var data = headGridItem.getItem().getRowData(item.id);
				self._headRowId = item.id;

				if( item.cell.name == "strtDate" || item.cell.name == "lastDate" )
				{
					/**
					 * 이미저장되어 있는 건은 날짜수정 불가 삭제만 가능.
					 * */
					if(data.oldStrtDate && data.oldStrtDate.length > 0)
					{
						//realgrid 교체후 edit 잠금처리 추가해야함.
						return;
					}
					
					self.popupGridCalender( item.id, item.cell.name );
				}
				else if( data.lastStrtDate != null && date.oldStrtDate != null && item.cell.name == "custCode" )
				{
					// 영업사원 거래처 검색 팝업
					
					var keyw = data.custCode;
					if(keyw && keyw.length > 8)
					{
						keyw = null;
					}
					
					self.findCustInfo(keyw, function(res)
		            {
		            	if( res &&
			            		res.code.length > 0 &&
			            		res.label.length > 0 )
		            	{
		            		self._selCust =
		            		{
		            			code : res.code,
		            			label : res.label
		            		};
		            		$("#"+item.id+" input[name=custCode]").val(res.code);
		            	}
		            	else
		            	{
		            		self._selCust = null;
		            	}
					});
				}
				
			});
			headGridItem.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
			{
				Logger.debug("Head Grid EDITCELL row id : "+item.id);
				var headAll = headGridItem.getItem().getRowData();
				var rowData = headGridItem.getItem().getRowData(item.id);
				
				if( item.cell.name == "custCode" )
				{
					var setCustCode = function(rowId, custInfo)
					{
						var rowData = headGridItem.getItem().getRowData(rowId);

						//
	            		{
		            		rowData.custCode = custInfo.code;
		            		rowData.custName = custInfo.label;
		            		headGridItem.setRow(item.id, rowData);
							self.setUpdateMode();
	            		}
					};
	            	if( self._selCust )
	            	{
	            		setCustCode(item.id, self._selCust);
	            	}
	            	else
	            	{
	            		var rowData = headGridItem.getItem().getRowData(item.id);
	            		var keyw = rowData.custCode;
						if(keyw && keyw.length > 8)
						{
							keyw = null;
						}
	            		
	            		self.findCustInfo(keyw, function(res)
			            {
			            	if( res &&
				            	res.code.length > 0 &&
				            	res.label.length > 0 )
			            	{
			            		setCustCode(item.id,
			            		{
			            			code: res.code,
			            			label: res.label
			            		});
			            	}
			            	self._selCust = null;
    					});
	            	}
				}
				else if( item.cell.name == "strtDate" || item.cell.name == "lastDate" )
				{
					rowData[item.cell.name] = item.cell.value;
					
					if( !self.checkDupPeriod(rowData) ) return;
					
					headGridItem.setRow(item.id, rowData);
					self.setUpdateMode();
				}
			});
			var box = this.getItemInstance("purposeBox");
			UCMS.retry(function()
			{
				if( box.getWidget$Element().find("textarea.discTitle").length == 0 )
				{
					return false;
				}

				box.getWidget$Element().find("textarea.discTitle")
				.change(function(item)
				{
					if(! self._headRowId )
					{
						$(this).val("");
						UCMS.alert("먼저 거래처를 선택해 주세요.");
						return;
					}
					var data = headGridItem.getItem().getRowData( self._headRowId );
					data["discTitle"] = $(this).val();
					headGridItem.setRow( self._headRowId, data );
					self.setUpdateMode();
				});
			});

			//
			var detailGridItem = this.attachGridItem("resultBox_2");
			detailGridItem.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0205_grid_2","할인단가_거래처별_제품",false);
			});

			detailGridItem.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
			{
				Logger.debug("Detail Grid EDITCELL row id : "+item.id);

				var data = detailGridItem.getItem().getRowData(item.id);
				/**
				 * 승인체크
				 * */
				var headdata = headGridItem.getItem().getRowData(self._headRowId);
				if(headdata.approYn == 'Y')
				{
					UCMS.alert("승인된 가격은 수정할 수 없습니다.");
					if(self._detailData)
						detailGridItem.setData( self._detailData );
					self.setReadyMode();
					return;
				}
				else
				{
					detailGridItem.setRow( item.id, data );
					self.setUpdateMode();
				}
				
			});
			detailGridItem.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel();
			});
			//
			this.initTaskCollector();
		}
		,
		getProductGridName: function()
		{
			return "resultBox_2";
		}
	};

	//
	// 5. 이벤트 처리
	// 초기화 완료된 아이템은 getRendererItem() 로 직접 아이템 내부 객체에 접근 가능하다.
	//
	var WorkAreaMethod =
	{
		/**
		 * 헤드 그리드에서 선택된 거래처 정보를 조회 파라메터에 적용하는 기능 추가
		 */
		getQueryData: function(params)
		{
			var data = Renderer.__super__.getQueryData.call( this );
			if(this._custCode)
			{
				data.custCode = this._custCode;
			}
			return data;
		}
		,
		onCreate: function()
		{
			if( this.isTransactionMode() == true )
			{
				UCMS.alert("신규 항목의 등록을 완료하세요.");
				return;
			}
			this.beginTransaction();
			this.getActiveGrid().createRow();

			//
			this.setCreateMode();
			this.clearProductList();
		}
		,
		onDelete: function(gridId)
		{
			var gridItem = this.getActiveGrid(gridId);
			var detailgridItem = this.getActiveGrid("resultBox_2");
			var box = this.getItemInstance("purposeBox");
			
			if(! gridItem)
			{
				Logger.info("onDelete() - Find not found a grid item.");
				return UCMS.retReject();
			}
			var counter = gridItem.getTaskCounter();

			var self = this;
			var grid = gridItem.getItem();
			var ids = grid.getSelRowId(true);
			
			if( ids[0] == null )
			{
				UCMS.alert("선택된 행이 없습니다.");
				return UCMS.retReject();
			}
			
			for(var i in ids)
			{
				var rowData = grid.getRowData( ids[i] );
				if(rowData.approYn == "Y")
				{
					UCMS.alert("승인된 가격은 삭제할 수 없습니다.");
					return;
				}
			}
			
			
			var remove = function()
			{
				self.clearForm();
				var deleteTasks = [];
				for(var i in ids)
				{
					var rowData = grid.getRowData( ids[i] );
					if( rowData )
					{
						rowData.dsType = "D";
						deleteTasks.push( rowData );
					}
					else
					{
						Logger.warn("onDelete() - Invalid row Id : "+ids[i]);
					}
				}

				self.showLoading();
				return self.sendTrasction(null, "delete", deleteTasks)
				.then(function(res)
				{
					if( res.resultCode == 0 )
					{
						UCMS.alert("삭제되었습니다.");
						grid.removeRow( ids );
						
						detailgridItem.clear();
						box.getWidget$Element().find("textarea.discTitle").val('');
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
		onQuery: function()
		{
			this._custCode = null;
			this._headRowId = null;
			this.clearProductList();

			var self = this;
			var queryData = this.getQueryData();
			if( new Date(queryData.strtDate) > new Date(queryData.lastDate) )
			{
				UCMS.alert("조회 기간이 잘못 지정되었습니다.");
				return;
			}


			Renderer.__super__.onQuery.call(this);
		}
		,
		onCancel: function()
		{
			Renderer.__super__.onCancel.call(this);
			this.clearProductList();
			
			var box = this.getItemInstance("purposeBox");
			box.getWidget$Element().find("textarea.discTitle").val('');
		}
		,
		onSelectRow: function(params)
		{
			Logger.debug(params);
			this.setPurposeMsg( params.discTitle || "" );

			if(! params.custCode )
			{
				Logger.debug("Empty Row");
				return;
			}
			this.fetchProductList(params);
		}
		,
		onButtonHandler: function(event)
		{
			switch( event.cmd )
			{
			default:
				return false;

			case "content:confirm": // 승인요청
				this.onReqConfirm( event.params );
				break;
			
			case "content:reqcancel": // 승인요청취소
				this.onReqCancel( event.params );
				break;
			}

			return true;
		}
		,
		/**
		 * 할인단가 승인 요청
		 */
		onReqConfirm: function()
		{
			var gridItem = this.getActiveGrid();
			if(! gridItem)
			{
				Logger.info("onConfirmPlan() - Find not found a grid item.");
				return;
			}

			var reqData = [];
			var ids = gridItem.getItem().getSelRowId(true);
			if( !ids || ids.length == 0 )
			{
				UCMS.alert("먼저 대상 거래처를 조회해 주세요.");
				return;
			}
			
			for(var i in ids)
			{
				var data = gridItem.getItem().getRowData(ids[i]);
				if(data.approRequest && data.approRequest.length > 0)
				{
					UCMS.alert("이미 요청중인 건이 있습니다.</br>해당건을 선택해제하고 다시 진행해주세요.");
					return;
				}
				if(data.partAppro && data.partAppro.length > 0)
				{
					UCMS.alert("승인진행중인 건이 있습니다.</br>해당건을 선택해제하고 다시 진행해주세요.");
					return;
				}
			}

			var self = this;
			UCMS.confirm("승인요청을 진행할까요?")
			.then(function()
			{
				for(var i in ids)
				{
					var data = gridItem.getItem().getRowData(ids[i]);
					reqData.push(data);
				}

				//return self.getClient().create([data], "reqConfirm");
				
				return self.sendTrasction("reqConfirm", null, reqData);
			})
			.done(function()
			{
				UCMS.alert("요청을 완료하였습니다.");
				self.onQuery();
			})
			;
		}
		,
		/**
		 * 할인단가 승인요청 취소
		 */
		onReqCancel: function()
		{
			var gridItem = this.getActiveGrid();
			if(! gridItem)
			{
				Logger.info("onConfirmPlan() - Find not found a grid item.");
				return;
			}

			var reqData = [];
			var ids = gridItem.getItem().getSelRowId(true);
			if( !ids || ids.length == 0 )
			{
				UCMS.alert("먼저 대상 거래처를 조회해 주세요.");
				return;
			}
			
			for(var i in ids)
			{
				var data = gridItem.getItem().getRowData(ids[i]);
				if(data.approRequest == null || data.approRequest == '')
				{
					UCMS.alert("요청하지않은 건이 있습니다.</br>해당건을 선택해제하고 다시 진행해주세요.");
					return;
				}
				if(data.partAppro && data.partAppro.length > 0)
				{
					UCMS.alert("승인진행중인 건이 있습니다.</br>해당건을 선택해제하고 다시 진행해주세요.");
					return;
				}
			}

			var self = this;
			UCMS.confirm("승인요청취소를 진행할까요?")
			.then(function()
			{
				for(var i in ids)
				{
					var data = gridItem.getItem().getRowData(ids[i]);
					reqData.push(data);
				}

				//return self.getClient().create([data], "cancelReqConfirm");
				
				return self.sendTrasction("cancelReqConfirm", null, reqData);
			})
			.done(function()
			{
				UCMS.alert("승인요청이 취소되었습니다.");
				self.onQuery();
			})
			;
		}
		,
		setPurposeMsg: function(msg)
		{
			var box = this.getItemInstance("purposeBox");
			box.getWidget$Element().find("textarea.discTitle").val( msg );
		}
		,
		fetchProductList: function(params)
		{
			if(params.strtDate && params.lastDate){}
			else return;
			
			this._custCode = params.custCode;

			// custCode 가 설정된 상태로 조회 요청
			// 거래처 제품의 할인단가 목록이 조회됨
			var self = this;

			var options =
			{
				"params" : {
					"custCode" : params.custCode,
					"strtDate" : params.strtDate,
					"lastDate" : params.lastDate
				}
			};

			this.showLoading();
			this.fetchingGrid(null, options, this.getProductGridName())
			.then(function(res){
				
				if(res.resultCode == 0 && res.extraData.result.length > 0){
					
					self._detailData = res.extraData.result;
					self.setUpdateMode();
				}
				
			})
			.always(function(e)
			{
				self.hideLoading();
			});
		}
		,
		clearProductList: function()
		{
			var gridItem = this.getActiveGrid("resultBox_2");
			gridItem.rollback();
			this.setPurposeMsg("");
		}
		,
		findCustInfo: function(keyword, cbSelect)
		{
			this.popupBox("codesearch", // 영업사원 거래처 코드 조회
			{
				"codeType": "SD0103",
				"keyword": keyword,
	            "params": {
	            	"salesman": NDSProps.get("user").id
	            },
	            "callback": cbSelect
			});
		}
	};

	var transactionMethod =
	{
		initTaskCollector: function()
		{
			this._tc = new RendererBase.TaskDataCollector();

			//
			this._tc.set("head", {});
			this._tc.set("detail", []);

			//
			this._taskData = {
				dsType: null
			};
		}
		,
		setCreateMode: function()
		{
			this._taskData.dsType = "C";
			this.setTransationMode();
		}
		,
		setUpdateMode: function()
		{
			this._taskData.dsType = "U";
			this.setTransationMode();
		}
		,
		setReadyMode: function()
		{
			this._taskData.dsType = null;
			Renderer.__super__.setReadyMode.call(this);
		}
		,
		/**
		 * Task 를 취합한다.
		 * @return {number} 취합된 Task 개수를 반환한다.
		 */
		collect: function()
		{
			var count = 0;

			//
			var headItem = this.attachGridItem("resultBox_1.content");
			var headTask = headItem.getTaskList();
			var box = this.getItemInstance("purposeBox");
			var discTitle = box.getWidget$Element().find("textarea.discTitle").val();
			
			if( headTask.length > 0 )
			{
				/***
				 * 단건 기준으로만 저장함.
				 * 
				 * */
				headTask = headTask[0];
				this._tc.set("head", headTask);
				count++;
			}
			else
				headTask = {};
			
			headTask.discTitle = discTitle;
			
			this._tc.set("head", headTask);
			count = headTask.length;

			//
			var detailTask = this.attachGridItem("resultBox_2").getTaskList();
			count += detailTask.length;
			this._tc.set("detail", detailTask);

			//
			this._taskData = _.extend(this._taskData, this._tc.get());

			return count;
		}
		,
		saveTransaction: function(featureId, silent)
		{
			if( this.collect() == 0 )
			{
				// 저장 버튼이 활성화된 상태에서 취합된 트랜잭션이 없는 상황은 발생할 수 없다.
				Logger.warn("have no task!!");
				return;
			}
			
			var self = this;
			
			var gridItem =  this.attachGridItem("resultBox_2");
			var headItem =  this._taskData.head;
			var detailItem =  this._taskData.detail;
			
			if(!headItem) 
				headItem = {"dsType" : "U"};
			
			if(!headItem.dsType)
				headItem.dsType = "U";
			
			var apply = function()
			{

				UCMS.showPrompt("처리중입니다.");
				
				var params = self.getQueryData();
				return gridItem.commit( params, featureId, self._taskData )
				.then(function(res)
				{
					if( res.resultCode == -4 )
					{
						UCMS.alert("선택한 기간이 저장된 날짜와 중복됩니다.</br>저장할 기간을 조회하시기바랍니다.");
					}
					if( res.resultCode == 0 )
					{
						// TODO onSave 현재 상태로 롤백을 위해 그리드의 저장된 상태를 백업한다.
						gridItem.beginTransaction(true);
						self.setReadyMode();

						//
						UCMS.alert("저장되었습니다.");
						
					}
					else
					{
						UCMS.reportError(res);
					}
				})
				.always(function(){
					UCMS.hidePrompt();
				});
			};

			if( gridItem.getData().length == 0 )
			{
				UCMS.alert("할인 대상 제품이 존재하지 않습니다!");
				return;
			}
			
			if( !self.checkDupPeriod(headItem) ) return;
			
			return UCMS.confirm("진행중인 작업을 저장할까요?", {confirm:"저장", cancel:"취소"})
			.done(apply);
		}
		,
		checkDupPeriod : function(rowData)
		{
			var headGridItem = this.attachGridItem("resultBox_1.content").getItem();
			var headAll = headGridItem.getRowData();
			
			$.each(headAll, function(i, hditem){
				
				var chkCustCode = rowData.custCode;
				var chkStrtDate = rowData.strtDate;
				var chkLastDate = rowData.lastDate;
				
				if( chkCustCode && chkCustCode.length == 0 )
				{
					UCMS.alert("거래처는 필수입니다.");
					return;
				}
				if( chkStrtDate && chkStrtDate.length == 0 )
				{
					UCMS.alert("시작일은 필수입니다.");
					return;
				}
				if( chkLastDate && chkLastDate.length == 0 )
				{
					UCMS.alert("종료일은 필수입니다.");
					return;
				}
				
				chkStrtDate = chkStrtDate.replace("-","");
				chkStrtDate = chkStrtDate.replace("-","");
				chkLastDate = chkLastDate.replace("-","");
				chkLastDate = chkLastDate.replace("-","");
				
				/**
				 * db에서 조회된 건만 체크.
				 * */
				if(hditem.oldStrtDate && hditem.oldStrtDate.length > 0)
				{
					if( hditem.custCode == chkCustCode 
						&& hditem.oldStrtDate <= chkStrtDate 
						&& hditem.oldLastDate >= chkStrtDate )
					{
						UCMS.alert("기존 기간과 중복됩니다.");
						rowData.strtDate = "";
						rowData.lastDate = "";
						return;
					}
				}
				
			});
			
			return true;
		}
	};

	var Renderer = WorkAreaRenderer2.extend
	(
		_.extend({}
			, OverridingMethod
			, WorkAreaMethod
			, transactionMethod
		)
		,
		{
			EVENT:
			{
			}
		}
	);

	return Renderer;
});
