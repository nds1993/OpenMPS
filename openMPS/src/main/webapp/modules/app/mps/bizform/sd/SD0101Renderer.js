/**
 * Project : OpenMPS MIS
 *
 * SD 영업 > 거래처관리 > 거래처 정보
 * SD0101
 *
 */

define([
	"Logger",
	"NDSProps",
	"BaroBox",
	"FormBox",
	"WorkAreaRenderer2",
	"RendererBase",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, BaroBox, FormBox, WorkAreaRenderer2, RendererBase, JQGrid4)
{
	var OverridingMethod =
	{
		constructor: function(options)
		{
			var gridOptions = options.tabAreaBox.options.tab_commonview.options;
			// 페이징 처리 추가
			_.extend
			(
				gridOptions.gridParams,
				this.makeAutoScrollingOptions2_local(30)
			);

			Renderer.__super__.constructor.call(this, options);
		}
		,
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );

			this._contentId = "SD0101";
		},
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
 				Renderer.__super__.initSubHeader.call( self, headerParams );
 			});
 		}
		,
		getItemList: function()
		{
			return [
					"headerBox",
					"queryBox",
			        "tabAreaBox",
			        "tabAreaBox.tab_commonview",
			        "tabAreaBox.tab_detailview",
			        "tabAreaBox.tab_detailview.header",
			        "tabAreaBox.tab_detailview.content"
			        ];
		}
		,
		getHeaderGridName: function()
		{
			return "tabAreaBox.tab_commonview";
		}
		,
		getFormName: function()
		{
			return "tabAreaBox.tab_detailview.content";
		}
		,
		onInitRendererItemEvents: function()
		{
			var self = this;
			var $detailView = this.$el.find(".sub_container_box");

			UCMS.retry(function()
			{
				if( $detailView.find(".grid_box").length != 5 )
				{
					return false;
				}

				self.attachHeaderItem("headerBox");
				self.attachFormItem("queryBox");
				self.initButtonEvent();
				self.initFormEvent();
				self.initGridEvent();

				//
				self.clearForm();
			});
		}
		,
		initButtonEvent: function()
		{
			var self = this;

			this.attachHeaderItem("tabAreaBox.tab_detailview.header", function(evt)
			{
				self.onBtnEventHandler( evt );
			});
			this.attachHeaderItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_organ.header", function(evt)
			{
				self.onBtnEventHandler( evt );
			});
			this.attachHeaderItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_security.header", function(evt)
			{
				self.onBtnEventHandler( evt );
			});
			this.attachHeaderItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_credit.header", function(evt)
			{
				self.onBtnEventHandler( evt );
			});
			this.attachHeaderItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_history.header", function(evt)
			{
				self.onBtnEventHandler( evt );
			});

			//
			var formItem = this.attachFormItem("tabAreaBox.tab_detailview.content");
			formItem.getItem().getWidget$Element().find("button.checkdup")
			.click(function()
			{
				// 거래처 중복 체크
				self.onBtnEventHandler({cmd:"checkDupCust"});
			});
		}
		,
		initFormEvent: function()
		{
			var self = this;
			var gridItem = this.attachGridItem("tabAreaBox.tab_commonview");
			var headForm = this.attachFormItem("tabAreaBox.tab_detailview.content.formBox");
			headForm.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				self.onChangeFormData( gridItem, headForm, "head" );
			});
			var salesForm = this.attachFormItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_salesinfo");
			salesForm.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				self.onChangeFormData( gridItem, salesForm, "sales" );
			});
			var farmForm = this.attachFormItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_farm");
			farmForm.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				self.onChangeFormData( gridItem, farmForm, "farm" );
			});
		}
		,
		initGridEvent: function()
		{
			var self = this;

			var gridItem = this.attachGridItem("tabAreaBox.tab_commonview")
			gridItem.getItem()
			.on(JQGrid4.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0101_grid_1","거래처정보_일반정보",false);
			});
			gridItem.getItem()
			.on(JQGrid4.EVENT.SELECT, function(item)
			{
				self.getItemInstance("tabAreaBox.tab_detailview.content.sub_tabarea_box").activeTab("tab_salesinfo");
				self.getItemInstance("tabAreaBox").activeTab("tab_detailview");
				self.onSelectRow(item);
			});

			//
			var editCell = function(gridItem, rowId)
			{
				var data = gridItem.getItem().getRowData(rowId);
				gridItem.setRow(rowId, data);
				//
				self.beginTransaction();
			};
			var organItem = this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_organ.content");

			organItem.getItem()
			.on(JQGrid4.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0101_grid_2","거래처정보_관할조직",false);
			});

			organItem.getItem().on(JQGrid4.EVENT.EDITCELL, function(item)
			{
				editCell( organItem, item.id );
			});
			organItem.getItem().on(JQGrid4.EVENT.BEFOREEDITCELL, function(item)
			{
				if( item.cell.name == "startDate" || item.cell.name == "lastDate" )
				{
					self.popupGridCalender( item.id, item.cell.name );
				}
			});
			var securityItem = this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_security.content");

			securityItem.getItem()
			.on(JQGrid4.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0101_grid_3","거래처정보_담보내역",false);
			});
			securityItem.getItem().on(JQGrid4.EVENT.EDITCELL, function(item)
			{
				editCell( securityItem, item.id );
			});
			securityItem.getItem().on(JQGrid4.EVENT.BEFOREEDITCELL, function(item)
			{
				if( item.cell.name == "damSdate" || item.cell.name == "damEdate" || item.cell.name == "damHdate" )
				{
					self.popupGridCalender( item.id, item.cell.name );
				}
			});
			var creditItem = this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_credit.content");
			creditItem.getItem()
			.on(JQGrid4.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0101_grid_4","거래처정보_한도내역",false);
			});

			creditItem.getItem().on(JQGrid4.EVENT.EDITCELL, function(item)
			{
				editCell( creditItem, item.id );
			});
			creditItem.getItem().on(JQGrid4.EVENT.BEFOREEDITCELL, function(item)
			{
				if( item.cell.name == "startDate" || item.cell.name == "lastDate" )
				{
					self.popupGridCalender( item.id, item.cell.name );
				}
			});
			var warehouseItem = this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_warehouse");

			warehouseItem.getItem()
			.on(JQGrid4.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0101_grid_6","거래처정보_물류정보",false);
			});

			warehouseItem.getItem().on(JQGrid4.EVENT.EDITCELL, function(item)
			{
				editCell( warehouseItem, item.id );
			});
			var historyItem = this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_history.content.listGrid");

			historyItem.getItem()
			.on(JQGrid4.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0101_grid_5","거래처정보_거래처이력",false);
			});
			historyItem.getItem().on(JQGrid4.EVENT.EDITCELL, function(item)
			{
				editCell( historyItem, item.id );
			});
		}
		,
		onBtnEventHandler: function(evt)
		{
			var self = this;

			switch( evt.cmd )
			{
			case "content:tab_organ_create": // 관할조직 행추가
				self.onCreateOrgan();
				break;

			case "content:tab_security_create": // 담보내역 행추가
				self.onCreateSecurity();
				break;

			case "content:tab_credit_create": // 한도내역 행추가
				self.onCreateCredit();
				break;

			case "content:tab_credit_confirm": // 한도내역 한도승인
				self.onConfirmCredit();
				break;

			case "content:tab_credit_cancel": // 한도내역 승인취소
				self.onCancelCredit();
				break;

			case "content:tab_history_create": // 거래처이력 행추가
				self.onCreateHistory();
				break;

			case "content:tab_history_delete": // 거래처이력 행삭제
				self.onDeleteHistory();
				break;

			case "checkDupCust": // 거래처 중복 체크
				self.onCheckDupCust();
				break;
			}
		}
		,
		onActiveTab: function(tab)
		{
			var gridId, self = this;

			switch( tab.active )
			{
			case "tab_organ":
				gridId = "tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_organ.content";
				break;

			case "tab_security":
				gridId = "tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_security.content";
				break;

			case "tab_credit":
				gridId = "tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_credit.content";
				break;

			case "tab_warehouse":
				gridId = "tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_warehouse";
				break;

			case "tab_history":
				gridId = "tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_history.content.listGrid";
				break;

			/*
			case "tab_commonview":
				if(! this._selCustCode )
				{
					// 신규 입력 상태인 경우 상세 정보를 취합해서 그리드에 적용
				}
				break;
			*/

			default:
				// 그리드가 아님.
				return;
			}

			if(! this._selCustCode )
			{
				UCMS.alert("\"영업정보\"와 \"농장정보\"를 입력해 주세요.");
				this.getItemInstance("tabAreaBox.tab_detailview.content.sub_tabarea_box").activeTab("tab_salesinfo");
				return;
			}

			this._loadedTab || (this._loadedTab={});
			if( this._loadedTab[tab.active] == true )
			{
				// 이미 데이타가 로딩된 상태
				// 재로딩 필요없음
				return;
			}

			this.showLoading();
			this.fetchingGrid( tab.active, {"params":{ "custCode": this._selCustCode }}, gridId )
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					self._loadedTab[tab.active] = true;
				}
			})
			.always(function()
			{
				self.hideLoading();
			});
		}
	};

	var WorkAreaMethod =
	{
		getQueryData: function()
		{
			var data = this.getRendererItem("queryBox").getItemData();
			if( data["custCode"] )
			{
				data["custCode"] = data["custCode"].result;
			}
			return data;
		}
		,
		onQuery: function()
		{
			this.getItemInstance("tabAreaBox").activeTab("tab_commonview");
			Renderer.__super__.onQuery.call( this );
		}
		,
		onSelectRow: function(selected)
		{
			this.clearForm(null, true);

			//
			var formItem = this.getActiveForm();
			if( formItem )
			{
				var formData = this.transformGrid2Form(selected.data);
				formItem.setData( formData, true );
				formItem.getItem().disabled(false);

				// 선택된 거래처 정보
				this._selCustCode = selected.data.custCode;
			}
		}
		,
		transformGrid2Form: function(gridData)
		{
			var fd =
			{
				"formBox":
				{
					"custCode": gridData.custCode,
					"custType": gridData.custType,
					"deptName": gridData.deptName,
					"salesmanName": gridData.salesmanName,
					"custName": gridData.custName,
					"owner": gridData.owner,
					"shortName": gridData.shortName,
					"delvAddr": gridData.delvAddr,
					"delvZip": gridData.delvZip,
					"postAddr": gridData.postAddr,
					"postZip": gridData.postZip,
					"bizNo": gridData.bizNo,
					"juminNo": gridData.juminNo,
					"uptae": gridData.uptae,
					"jong": gridData.jong,
					"phone": gridData.phone,
					"mobile": gridData.mobile,
					"fax": gridData.fax,
					"taxType": gridData.taxType,
					"email": gridData.email,
					"closeYn": gridData.closeYn
				}
				,
				sub_tabarea_box:
				{
					"tab_salesinfo":
					{
						"delvType": gridData.delvType,
						"delvCust": gridData.delvCust,
						"mainDc": gridData.mainDc,
						"distType": gridData.distType,
						"shopNo": gridData.shopNo,
						"shopName": gridData.shopName,
						"delvTime": gridData.delvTime,
						"limitF": gridData.limitF,
						"limitP": gridData.limitP,
						"billType": gridData.billType,
						"priceComb": gridData.priceComb,
						"priceCombCust": gridData.priceCombCust,
						"saleComb": gridData.saleComb,
						"saleCombCust": gridData.saleCombCust,
						"receComb": gridData.receComb,
						"receCombCust": gridData.receCombCust,
						"bankCode": gridData.bankCode,
						"bankName": gridData.bankName,
						"acctNo": gridData.acctNo,
						"acctName": gridData.acctName,
						"priceType": gridData.priceType,
						"priceDay": gridData.priceDay,
						"creditYn": gridData.creditYn,
						"taxbillYn": gridData.taxbillYn
					},
					"tab_farm":
					{
						"facCust": gridData.facCust,
						"brandCode": gridData.brandCode,
						"facKind": gridData.facKind,
						"facCode": gridData.facCode,
						"receComb": gridData.receComb,
						"receCombCust": gridData.receCombCust
					}
					/*
					"tab_organ": {
						// SubContainer
						// Grid
					},
					"tab_security": {
						// SubContainer
						// Grid
					},
					"tab_credit": {
						// SubContainer
						// Grid
					},
					"tab_warehouse": {
						// Grid
					},
					"tab_history": {
						// SubContainer
						// FormBox
							// listGrid
							// detailView
					}
					*/
				}
			};

			return fd;
		}
		,
		onChangeFormData: function(gridItem, formItem, formId)
		{
			var rowId = gridItem.getItem().getSelRowId();
			gridItem.getItem().setSelectRow( rowId, true );

			//
			var gridData = gridItem.getItem().getRowData(rowId);
			var formData = formItem.getData();
			if(! formData )
			{
				// 필수 정보가 입력되지 않은 경우
				return;
			}
			Logger.debug("changed row : "+rowId);
			Logger.debug(formData);

			if( formId == "head" )
			{
				formData = this.transformHeadForm2Grid(formData);
			}
			else if( formId == "sales" )
			{
				formData = this.transformSalesForm2Grid(formData);
			}
			else if( formId == "farm" )
			{
				formData = this.transformFarmForm2Grid(formData);
			}
			else
			{
				UCMS.warn("onChangeFormData() - Unknown Form ID : "+formId);
				return;
			}

			//
			this.beginTransaction();

			// 폼에는 그리드의 모든 필드 정보가 노출되지 않을 수도 있다.
			// TODO 그리드의 필드에 변경된 폼의 정보를 부가하는 방식을 사용해야, 트랜잭션 데이타가 누락되는 필드없이 생성된다.
			gridItem.setRow(rowId, _.extend(gridData, formData));
		}
		,
		transformHeadForm2Grid: function(formData)
		{
			var gd =
			{
				custCode : formData.custCode,
				custType : formData.custType,
				deptName : formData.deptName,
				salesmanName : formData.salesmanName,
				custName : formData.custName,
				owner : formData.owner,
				shortName : formData.shortName,
				delvAddr : formData.delvAddr,
				delvZip : formData.delvZip,
				postAddr : formData.postAddr,
				postZip : formData.postZip,
				bizNo : formData.bizNo,
				juminNo : formData.juminNo,
				uptae : formData.uptae,
				jong : formData.jong,
				phone : formData.phone,
				mobile : formData.mobile,
				fax : formData.fax,
				taxType : formData.taxType,
				email : formData.email,
				closeYn : formData.closeYn
			};

			return gd;
		}
		,
		transformSalesForm2Grid: function(formData)
		{
			var gd =
			{
				delvType : formData.delvType,
				delvCust : formData.delvCust,
				mainDc : formData.mainDc,
				distType : formData.distType,
				shopNo : formData.shopNo,
				shopName : formData.shopName,
				delvTime : formData.delvTime,
				limitF : formData.limitF,
				limitP : formData.limitP,
				billType : formData.billType,
				priceComb : formData.priceComb,
				priceCombCust : formData.priceCombCust,
				saleComb : formData.saleComb,
				saleCombCust : formData.saleCombCust,
				receComb : formData.receComb,
				receCombCust : formData.receCombCust,
				bankCode : formData.bankCode,
				bankName : formData.bankName,
				acctNo : formData.acctNo,
				acctName : formData.acctName,
				priceType : formData.priceType,
				priceDay : formData.priceDay,
				creditYn : formData.creditYn,
				taxbillYn : formData.taxbillYn
			};

			return gd;
		}
		,
		transformFarmForm2Grid: function(formData)
		{
			var gd =
			{
				facCust : formData.facCust,
				brandCode : formData.brandCode,
				facKind : formData.facKind,
				facCode : formData.facCode,
				receComb : formData.receComb,
				receCombCust : formData.receCombCust
			};

			return gd;
		}
		,
		onCreateOrgan: function()
		{
			this.createNewRow("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_organ.content");
		},
		onCreateSecurity: function()
		{
			this.createNewRow("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_security.content");
		},
		onCreateCredit: function()
		{
			this.createNewRow("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_credit.content");
		},
		onConfirmCredit: function()
		{
			UCMS.alert("onConfirmCredit");
		},
		onCancelCredit: function()
		{
			UCMS.alert("onCancelCredit");
		},
		onCreateHistory: function()
		{
			this.createNewRow("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_history.content");
		},
		onDeleteHistory: function()
		{
			this.removeRow("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_history.content");
		},
		onCheckDupCust: function()
		{
			var formItem = this.attachFormItem("tabAreaBox.tab_detailview.content.formBox");
			var custInfo = formItem.getData();
			if( !custInfo.custCode )
			{
				UCMS.alert("거래처 코드를 입력해 주세요!");
				return;
			}

			var client = this.getClient();
			client.read({"custCode": custInfo.custCode}, "checkdup")
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					UCMS.alert("등록 가능한 거래처 코드입니다.");
				}
				else
				{
					UCMS.alert("중복된 거래처 코드입니다.");
				}
			});
		}
		,
		/**
		 * 지정한 그리드에 신규 행을 추가한다.
		 * 추가된 내용은 트랜잭션으로 관리되고, "저장" 동작시 서버로 반영된다.
		 */
		createNewRow: function(gridId)
		{
			var gridItem = this.getActiveGrid( gridId );
			gridItem.createRow({}, "last");
			this.beginTransaction();
		}
		,
		/**
		 * 지정한 그리드에서 현재 선택된 행을 삭제한다.
		 * 삭제된 내용은 트랜잭션으로 관리되고, "저장" 동작시 서버로 반영된다.
		 */
		removeRow: function(gridId)
		{
			var gridItem = this.getActiveGrid( gridId );
			var rowId = gridItem.getItem().getSelRowId();
			gridItem.removeRow( rowId );
			this.beginTransaction();
		}
		,
		clearForm: function(formId, enable)
		{
			var form = this.getActiveForm(formId);
			if( form )
			{
				form.clear();
				form.getItem().disabled(enable!=true);
			}

			//
			this.attachFormItem("tabAreaBox.tab_detailview.content.formBox").clear();
			this.attachFormItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_salesinfo").clear();
			this.attachFormItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_farm").clear();

			//
			this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_organ.content").clear();
			this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_security.content").clear();
			this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_credit.content").clear();
			this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_warehouse").clear();
			this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_history.content.listGrid").clear();

			//
			this._loadedTab = {};
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
			this.beginTransaction();
			this._newRowId = this.getActiveGrid().createRow({}, "last");
			if( this.getActiveForm() )
			{
				this.getActiveForm().getItem().disabled(false);
			}
			this.clearForm(null, true);
		}
		,
		onCancel: function(silent)
		{
			Renderer.__super__.onCancel.call( this, silent );
			this._newRowId = null;
		}
		,
		/**
		 * 트랜잭션 처리를 위한 타스트 아이템을 생성한다.
		 */
		makeTaskItem: function(custData)
		{
			var item =
			{
				dsType: custData.dsType || 'U'
				,
				custinfo: {}
				,
				custhist: []
				,
				dambo: []
				,
				credit: []
				,
				custhistory: []
			};
			delete custData.dsType;

			//
			_.extend( item.custinfo, custData );

			//
			if( this._newRowId )
			{
				// 신규 거래처 생성 모드
				item.dsType = 'C';
			}

			return item;
		}
		,
		/**
		 * 트랜재션 부가 정보를 추가한다.
		 */
		appendTransactionData: function(taskItem)
		{
			// 관할조직
			taskItem.custhist = this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_organ.content").getTaskList();
			// 담보내역
			taskItem.dambo = this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_security.content").getTaskList();
			// 한도내역
			taskItem.credit = this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_credit.content").getTaskList();
			// 거래처이력
			taskItem.custhistory = this.attachGridItem("tabAreaBox.tab_detailview.content.sub_tabarea_box.tab_history.content.listGrid").getTaskList();

			return taskItem;
		}
		,
		onDelete: function()
		{
			var gridItem = this.getActiveGrid();
			if(! gridItem)
			{
				Logger.info("onDelete() - Find not found a grid item.");
				return;
			}
			var counter = gridItem.getTaskCounter();
			if( counter.create != 0 )
			{
				UCMS.alert("신규 항목의 등록 완료후 다시 시도해 주세요.");
				return;
			}
			var self = this;
			var grid = gridItem.getItem();
			var rowId = grid.getSelRowId();
			if( rowId == null )
			{
				UCMS.alert("선택된 행이 없습니다.");
				return;
			}
			var rowData = grid.getRowData( rowId );
			var remove = function()
			{
				self.showLoading();
				gridItem.removeRow(rowId);
				self.sendTrasction(null, self.getQueryData(), self.makeTaskItem( gridItem.getTaskList()[0] ))
				.then(function(res)
				{
					if( res.resultCode == 0 )
					{
						UCMS.alert("삭제되었습니다.");
						return res;
					}
					else
					{
						UCMS.reportError(res);
						return null;
					}
				})
				.then(function(res)
				{
					if( res == null )
					{
						// 삭제 실패된 경우..
						return;
					}

					self.getItemInstance("tabAreaBox").activeTab("tab_commonview");
					self.clearForm();
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
			UCMS.confirm("거래처 \""+rowData.custName+"\"을 삭제하시겠습니까?", {confirm:"삭제", cancel:"취소"})
			.then(remove);
		}
		,
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
					taskData = gridItem.getTaskList();
					taskData.length == 0 ? taskData = {} : taskData = taskData[0];
					return gridItem.commit( params, featureId,
							self.appendTransactionData(
									self.makeTaskItem( taskData ) ) )
					.then(function(res)
					{
						if( res.resultCode != 0 )
						{
							if( silent != true )
							{
								UCMS.reportError(res);
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
						UCMS.reportError(e);
					});
				};
				if( silent != true )
				{
					return UCMS.confirm("진행중인 작업을 저장할까요?", {confirm:"저장", cancel:"취소"})
					.done(apply);
				}
				else
				{
					return apply();
				}
			}
			else
			{
				return UCMS.retResolve();
			}
		}
	};

	var Renderer = WorkAreaRenderer2.extend
	(
		_.extend({}
			, OverridingMethod
			, WorkAreaMethod
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
