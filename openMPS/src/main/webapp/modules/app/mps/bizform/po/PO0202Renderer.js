/**
 * Project : OpenMPS MIS
 *
 * 구매 > 생돈정산
 *
 */


 define([
 	"Logger",
 	"NDSProps",
 	"BaroBox",
 	"FormBox",
 	"WorkAreaHeader",
 	"RendererBase",
 	"WorkAreaRenderer2",
 	"manifest!jqGrid4-1.0.0#widget",
 	"moment"
 ], function(Logger, NDSProps, BaroBox, FormBox, WorkAreaHeader, RendererBase, WorkAreaRenderer2, JQGrid, moment)
 {
 	var Renderer = WorkAreaRenderer2.extend(
 	{
 		initialize: function(options)
 		{
 			Renderer.__super__.initialize.apply( this, arguments );
 			this._contentId = "PO0202";
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
					{"cancel":"취소"}
 				];
 				RendererBase.Method.initSubHeader.call( self, headerParams );
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
 			        "queryBox.btnGetDate",
 			        "resultBox",
 			        "resultBox.gridBox",
 			        "resultBox.btnPigexm",
 			        "formBox",
 			        "formBox.formBox1",
 			        "formBox.formBox1.content",
 			        "formBox.formBox1.content.btnGetAvg",
 			        "formBox.formBox2",
 			        "formBox.formBox2.content",
 			        "formBox.formBox3",
 			        "formBox.formBox3.content"
 			        ];
 		}
 		,
 		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
 		getHeaderGridName: function()
 		{
			return "resultBox.gridBox";
 		}
 		,
 		/**
		 * 4. 랜더러 아이템 관계성 초기화
		 */
 		onInitRendererItemEvents: function()
 		{
 			var self = this;

			this.attachHeaderItem("headerBox");
			
			//
			var queryBoxItem = this.attachFormItem("queryBox");
			queryBoxItem.getItem().getItemElement("facKind")
			.change(function(e)
			{
				self.fillMethodCombo( $(e.target).val() );
			});
			queryBoxItem.getItem().getItemElement("startDate")
			.change(function(e)
			{
				self.fillJiyukDate(
						$(e.target).val(),
						queryBoxItem.getItem().getItemElement("endDate").val() );
			});
			queryBoxItem.getItem().getItemElement("endDate")
			.change(function(e)
			{
				self.fillJiyukDate(
						queryBoxItem.getItem().getItemElement("startDate").val(),
						$(e.target).val() );
			});
			queryBoxItem.getItem().getItemElement("btnGetDate").getWidget$Element()
			.click(function()
			{
				self.fillRecentDate();
			});
			this.attachFormItem("resultBox").getItem().getItemElement("btnPigexm")
			.getWidget$Element().click(function()
			{
				self.pigAdjust();
			});
			
			//
			var gridItem = this.attachGridItem("resultBox.gridBox");
			// setSelectRow() 시 발생되는 처리 진행
			gridItem.getItem().on(JQGrid.EVENT.SELECT, function(params)
			{
				self.onSelectRow(params);
			});
			gridItem.getItem().on(JQGrid.EVENT.SELECTCELL, function(params)
			{
				params.data = gridItem.getItem().getRowData(params.id);
				self.onSelectRow(params);
			});

			gridItem.getItem().on(JQGrid.EVENT.BEFOREEDITCELL, function(item)
			{
				//
				// 셀의 데이타를 입력하는 모드인 셀 편집 모드에서만 동작된다.
				// 본 이벤트는 셀 편집이 시작되면 발생된다.
				//
				Logger.debug("BEFOREEDITCELL row id : "+item.id);
				Logger.debug(item);

				if( item.cell.name == "startDate" )
				{
					// 날짜를 입력할 필드인 경우 캘린더를 팝업한다.
					self.popupGridCalender( item.id, item.cell.name );
				}
				else if( item.cell.name == "endDate" )
				{
					var $cell = $("#"+item.id+" input[name="+item.cell.name+"]");
					var newData = moment(self.$el.find("input.doStartDate").val()).subtract(1, 'days');
					$cell.val( newData.format("YYYY-MM-DD") );
					self.popupGridCalender( item.id, item.cell.name );
				}
			});

			gridItem.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
			{
				var gridData = gridItem.getItem().getRowData(item.id);
				gridItem.setRow(item.id, gridData);

				//
				self.beginTransaction();
			});

			//
			var fi1 = this.attachFormItem("formBox.formBox1.content");
			fi1.getItem().getItemElement("btnGetAvg")
			.getWidget$Element().click(function()
			{
				self.fillAvgPrice();
			});
			fi1.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				self.onChangeFormData(gridItem, fi1);
			});
			var fi2 = this.attachFormItem("formBox.formBox2.content");
			fi2.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				self.onChangeFormData(gridItem, fi2);
			});
			var fi3 = this.attachFormItem("formBox.formBox3.content");
			fi3.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				self.onChangeFormData(gridItem, fi3);
			});
			
			//
			this.initCustomButtons();
 		}
 		,
 		activeAdjustBtn: function(bActive)
		{
			this.$el.find(".btnPigexm_region button").prop("disabled", !bActive);
		}
 		,
 		initCustomButtons: function()
 		{
 			var self = this;
 			var gridItem = this.attachGridItem("resultBox.gridBox");
 			var acceptStartDate = function()
 			{
 				if(! self._selectRow )
 				{
 					UCMS.alert("정산이력 목록에서 적용할 \"정산 방법\"을 선택해 주세요.");
 					return;
 				}
 				var startDate = $(this).val(), jsDate = new Date(startDate);
 				var ids = gridItem.getItem().getRowIds();
 				
 				self._startDate = null;
 				for(var i in ids)
 				{
 					var rowData = gridItem.getItem().getRowData(ids[i]);
 					if( // 현재 선택된 행이 아니고
 						ids[i] != self._selectRow.id
 						&&
 						// 시작일이 선택된 정보에서 변경된 경우
 						self._selectRow.data.startDate != startDate
 						&&
 						// 지정한 날짜에 정산된 이력이 존재하면
 						jsDate <= new Date( rowData.endDate ) )
 					{
 						UCMS.alert("지정한 시작일에 중복된 정산 내역이 존재합니다. 정산이력 목록에서 \"시작일자\"와 \"종료일자\"를 확인해 주세요.");
 						self.activeAdjustBtn( false );
 						return;
 					}
 				}
 				self._startDate = jsDate;
 				
 				//
 				// 진행중인 트랜잭션 데이타를 신규 모드로 전환
 				// 또는 신규 트랜잭션 시작
 				//
 				
 				var createNewTask = function()
 				{
 					self.onCreate(_.extend( {}, self._selectRow.data, 
					{
						"startDate": self.$el.find("input.doStartDate").val(),
						"endDate": self.$el.find("input.doEndDate").val()
					}));	
 				};
 				var taskRow = gridItem.getTaskRow( self._selectRow.id );
 				if( taskRow )
 				{
 					// 신규 트랜잭션으로 전환
 					Renderer.__super__.onCancel.call( self, true )
 					.then(function()
 					{
 						createNewTask();	
 					});
 				}
 				else
 				{
 					// 신규 트랜잭션 생성
 					createNewTask();
 				}
 			};
 			var acceptEndDate = function()
 			{
 				if(! self._startDate )
 				{
 					UCMS.alert("먼저 \"도축 시작일\"을 지정해 주세요.");
 					return;
 				}
 				var endDate = new Date($(this).val());
 				if( self._startDate <= endDate )
 				{
 					if( self._selectRow.data.endDate != $(this).val() )
 					{
 						// 종료일이 변경된 경우
	 					var ids = gridItem.getItem().getRowIds();
	 					for(var i in ids)
	 	 				{
	 	 					var rowData = gridItem.getItem().getRowData(ids[i]);
	 	 					if( // 지정한 종료일이 중첩되는 경우
	 	 						endDate <= new Date( rowData.endDate ) )
	 	 					{
	 	 						UCMS.alert("지정한 종료일에 중복된 정산 내역이 존재합니다. 정산이력 목록에서 \"시작일자\"와 \"종료일자\"를 확인해 주세요.");
	 	 						self.activeAdjustBtn( false );
	 	 						return;
	 	 					}
	 	 				}
 					}
 					self._endDate = endDate;
 					
 					//
 					// 편집 트랜잭션 시작
 					// 트랜잭션을 저장하고, 정산을 진행한다.
 					//
 					gridItem.setRow( self._selectRow.id, self._selectRow.data );
 					self.beginTransaction();
 				}
 				else
 				{
 					UCMS.alert("\"도축 시작일\" 이후 날짜를 지정해 주세요.");
 					self._endDate = null;
 					self.activeAdjustBtn( false );
 				}
 			};

 			this.$el.find("input.doStartDate")
 			.focusout(acceptStartDate).change(acceptStartDate);
 			this.$el.find("input.doEndDate")
 			.focusout(acceptEndDate).change(acceptEndDate);
 			this.activeAdjustBtn( false );
 		}
 		,
 		fillMethodCombo: function(facKind)
 		{
 			if( facKind == "0" )
 			{
 				this.$el.find(".facCode .select_label").empty();
 				this.$el.find(".facCode select").empty();
 				return;
 			}
 			var self = this;
 			var queryBox = this.attachFormItem("queryBox");
 			var client = this.getClient();
 			client.read({"facKind":facKind}, "methodCombo")
 			.then(function(res)
 			{
 				var newParams =
 				{
					"selector": ".facCode",
					"label": "정산방법",
					"required": true,
					"module": "combobox",
					"items": []
				};
 				if( res.resultCode != 0 )
 				{
 					newParams.items.push({"label": "loading fail!","value": 0});
 				}
 				else
 				{
 					newParams.items.push({"label": "전체", "value": "0"});
 					//
 					for(var i in res.extraData.result)
 					{
 						var item = res.extraData.result[i];
 						newParams.items.push({"label": item.code+":"+item.label,"value": item.code});
 					}
 				}

 				queryBox.getItem()
 				.setItemParams("facCode", newParams);
 			})
 			.fail(function(err)
 			{
 				Logger.error(err);
 				queryBox.clear();
 			});
 		}
 		,
 		fillJiyukDate: function(startDate, endDate)
 		{
 			var self = this;
 			var client = this.getClient();
 			client.read({"startDate":startDate, "endDate":endDate}, "jiyuk")
 			.then(function(res)
 			{
 				if( res.resultCode != 0 )
 				{
 					Logger.error(res);
 					return;
 				}
 				self.attachGridItem("formBox.formBox1").getItem().getContent().setItemData(
 				{
 					"fromDate": res.extraData.startDate,
 					"toDate": res.extraData.endDate
 				});
 			});
 		}
 		,
 		fillRecentDate: function()
 		{
 			var self = this;
 			var queryForm = this.attachFormItem("queryBox").getItem();
 			var facKind = queryForm.getItemElement("facKind").find("option:selected").val();
 			if( facKind == 0 )
 			{
 				UCMS.alert("\"정산구분\" 항목을 선택해 주세요.");
 				return;
 			}
 			var facCode = queryForm.getItemElement("facCode").find("option:selected").val();
 			var client = this.getClient();
 			client.read({"facKind":facKind,"facCode":facCode}, "recent")
 			.then(function(res)
 			{
 				if( res.resultCode != 0 )
 				{
 					UCMS.reportError(res);
 					return;
 				}

 				// 평균시세 기간 적용
 				var formItem = self.attachFormItem("formBox.formBox1.content");
 				var formData = formItem.getData();
 				formData.fromDate = res.extraData.result[0].fromDate;
 				formData.toDate = res.extraData.result[0].toDate;
 				formItem.setData( formData );

 				// 정산방법 적용
 				var gridItem = self.getActiveGrid();
 				gridItem.setData( res.extraData.result );
 				var ids = gridItem.getItem().getRowIds();
 				gridItem.getItem().setSelectRow( ids[0] );
 			});
 		}
 		,
 		fillAvgPrice: function()
 		{
 			if(! this._selectRow )
 			{
 				UCMS.alert("정산 이력 목록에서 \"정산방법\"을 선택해 주세요.");
 				return;
 			}
 			var queryForm = this.attachFormItem("queryBox").getItem();
 			var facKind = this._selectRow.data.facKind;
 			var formItem = this.attachGridItem("formBox.formBox1.content");

 			if(facKind == "0")
 			{
 				UCMS.alert("\"정산구분\"과 \"정산방법\"을 선택해 주세요.");
 				return;
 			}
 			var reqData =
 			{
 				"facKind": facKind,
				"startDate": formItem.getItem().getItemElement("fromDate").val(),
	 			"endDate": formItem.getItem().getItemElement("toDate").val()
 			};

 			var self = this;
 			var client = this.getClient();
 			client.read(reqData, "avgPrice")
 			.then(function(res)
 			{
 				if( res.resultCode != 0 )
 				{
 					UCMS.reportError(res);
 					return;
 				}
 				if( res.extraData )
 				{
 					formItem.getItem().setItemData( res.extraData );
 					var gridItem = self.attachGridItem("resultBox.gridBox");
 					self.onChangeFormData( gridItem, formItem );
 					
 					//
 					self.beginTransaction();
 				}
 				else
 				{
 					UCMS.alert("평균시세가 존재하지 않습니다.");
 				}
 			});
 		}
 		,
 		onQuery: function()
 		{
 			var fi1 = this.attachFormItem("formBox.formBox1.content");
 			fi1.clear();
 			var fi2 = this.attachFormItem("formBox.formBox2.content");
 			fi2.clear();
 			var fi3 = this.attachFormItem("formBox.formBox3.content");
 			fi3.clear();

            var self = this;
            var queryData = this.getQueryData();
            if( new Date(queryData.startDate) > new Date(queryData.endDate) )
            {
                UCMS.alert("조회 기간이 잘못 지정되었습니다.");
                return;
            }

 			this.activeAdjustBtn( false );
			this._selectRow = null;
 			
 			//
 			Renderer.__super__.onQuery.call(this);
 		}
 		,
 		onSelectRow: function(selected)
		{
 			var self = this;
 			var selectRow = function(selectRowData)
 			{
	 			var fi1 = self.attachFormItem("formBox.formBox1.content");
	 			fi1.setData( selectRowData.data, false );
	 			var fi2 = self.attachFormItem("formBox.formBox2.content");
	 			fi2.setData( selectRowData.data, false );
	 			var fi3 = self.attachFormItem("formBox.formBox3.content");
	 			fi3.setData( selectRowData.data, false );
	 			
	 			//
	 			self.attachGridItem("resultBox.gridBox").getItem().setSelectRow( selectRowData.id, true );
	 			
	 			//
	 			self._selectRow = selectRowData;
	 			
	 			//
	 			self.$el.find("input.doStartDate").val( selectRowData.data.startDate );
	 			self._startDate = new Date(selectRowData.data.startDate);
	 			self.$el.find("input.doEndDate").val( selectRowData.data.endDate );
	 			self._endDate = new Date(selectRowData.data.endDate);
	 			
	 			//
	 			self.activeAdjustBtn( true );
 			};
 			
 			if( this._selectRow && this.isTransactionMode() == true )
			{
 				var selectRowData = this._selectRow;
 				UCMS.confirm("진행 중인 작업을 저장할까요?", { confirm: "예", cancel: "아니요" })
 				.then(function()
				{
					return self.onSave(null, true);
				})
				.then(function()
				{
					// 새로운 선택 행을 사용함
					selectRowData = selected;
				})
				.fail(function()
				{
					// 이전 선택된 행으로 돌아감
					return self.onCancel();
				})
				.always(function()
				{
					selectRow(selectRowData);
				});
			}
 			else
 			{
 				selectRow(selected);
 			}
		}
 		,
 		onCreate: function(data)
 		{
 			var counter = this.getActiveGrid().getTaskCounter();
			if( counter.create != 0 )
			{
				UCMS.alert("신규 항목의 등록을 완료하세요.");
				return UCMS.retReject();
			}
			
			var gridItem = this.getActiveGrid();
			var rowId = gridItem.createRow(data ? data : this._selectRow ? this._selectRow.data : {}, true);
			if( rowId.then )
			{
				// RealGrid
				return rowId.then(function()
				{
					self.beginTransaction();
				});
			}
			else
			{
				// JQGrid
				this.beginTransaction();
				return UCMS.retResolve();
			}
 		}
 		,
 		onCancel: function(silent)
 		{
 			var self = this;
 			return Renderer.__super__.onCancel.call( this, silent )
 			.then(function()
 			{
 				self.activeAdjustBtn( false );
 	 			self._selectRow = null;
 			});
 		}
 		,
 		pigAdjust: function()
 		{
 			if(! this._selectRow )
 			{
 				UCMS.alert("정산 이력 목록에서 \"정산방법\"을 선택해 주세요.");
 				return;
 			}
 			if( this.isTransactionMode() == true )
 			{
 				UCMS.alert("편집중인 \"정산방법\"을 저장해 주세요.");
 				return;
 			}
 			
 			var self = this;
 			var queryItem = this.attachFormItem("queryBox").getItem();
 			var facKind = this._selectRow.data.facKind;
			var facCode = this._selectRow.data.facCode;

			if(! facCode )
			{
				UCMS.alert("먼저 \"정산방법\"을 지정해 주세요.");
				return;
			}
			
			/*
			this.$el.find("input.doStartDate").change();
 			this.$el.find("input.doEndDate").change();
 			*/
 			
 			//
			if(!this._startDate || !this._endDate)
			{
				UCMS.alert("\"도축일자\"를 지정해 주세요.");
				return;
			}
			if( this._startDate > this._endDate )
			{
				UCMS.alert("\"도축 일자\" 지정이 잘못되었습니다.");
				return;
			}

 			UCMS.confirm("생돈정산을 진행 하시겠습니까?", {"confirm": "정산", "cancel": "취소"})
 			.done(function()
 			{
 				var client = self.getClient();
 	 			var queryItem = self.attachFormItem("queryBox");
 	 			var params = {
 	 				"facKind": facKind,
 	 				"facCode": facCode,
 	 				"startDate": self.attachFormItem("resultBox").getItem().getItemElement("doStartDate").val(),
 	 				"endDate": self.attachFormItem("resultBox").getItem().getItemElement("doEndDate").val()
 	 			};

 	 			var urlPath = client.getAPIPath("create", "adjust", params);

 	 			self.showLoading();
 	 			client.transaction(urlPath)
 	 			.then(function(res)
 	 			{
 	 				if( res.resultCode == 0 )
 	 				{
 	 					UCMS.alert("정산이 완료되었습니다.");
 	 				}
 	 				else
 	 				{
 	 					UCMS.reportError(res, "정산을 완료하지 못했습니다.");
 	 				}
 	 			})
 	 			.fail(function(err)
	 			{
	 				UCMS.reportError(err);
	 			})
 	 			.always(function()
	 			{
	 				self.hideLoading();
	 			});
 			});
 		}
 		,
 		clearForm: function()
		{
 			this.attachFormItem("formBox.formBox1.content").clear();
 			this.attachFormItem("formBox.formBox2.content").clear();
 			this.attachFormItem("formBox.formBox2.content").clear();
		}
 		,
 		getQueryData: function(params)
 		{
 			var data = Renderer.__super__.getQueryData.call( this, params );
 			if( data )
 			{
				if( data.facKind == "0" )
				{
					delete data.facKind;
				}
				if( data.facCode == "0" )
				{
					delete data.facCode;
				}
 			}
 			else
 			{
 				data = {
 					"startDate": this.attachFormItem("queryBox").getItem().getItemElement("startDate").val(),
 					"endDate": this.attachFormItem("queryBox").getItem().getItemElement("endDate").val()
 				};
 			}
			return data;
 		}
 		,
		onSave: function(featureId, silent)
		{
			var self = this;

			return this.saveTransaction(featureId, silent)
			.done(function()
			{
				self.activeAdjustBtn( true );
			});
		}
 		,
 		isValidRowChecker: function(gridId, taskItem)
		{
 			if( new Date(taskItem.startDate) > new Date(taskItem.endDate) )
 			{
 				return "\"도축기간\"을 잘못 지정하셨습니다.";
 			}
 			
			return true;
		}
 		,
 		onChangeFormData: function(gridItem, formItem)
		{
 			if(! this._selectRow )
			{
				UCMS.alert("정산이력 목록에서 적용할 \"정산 방법\"을 선택해 주세요.");
				return;
			}
 			Renderer.__super__.onChangeFormData.apply( this, arguments );
		}
 	}
 	,
 	{
 		EVENT:
 		{
 		}
 	});

 	return Renderer;
 });
