/**
 * Project : OpenMPS MIS
 *
 * 생산 > 생산계획 입력 CM
 * PP0302
 *
 */

define([
	"Logger",
	"NDSProps",
	"RendererBase",
	"BaroBox",
	"FormBox",
	"manifest!jqGrid4-1.0.0",
	"WorkAreaHeader",
	"SubContainer"
], function(Logger, NDSProps, RendererBase, BaroBox, FormBox, JQGrid, WorkAreaHeader, SubContainer)
{
	JQGrid = JQGrid.widget;

	var OverridingMethod =
	{
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			RendererBase.Method.initMethod.apply( this, arguments );

			//
			this._contentId = "PP0302";
			this._client = this.createAPIClient();
			this._statusCode = {};
		}
		,
		initSubHeader: function()
		{
			Logger.debug("Renderer.initSubHeader()");

			var self = this;
			return this.queryHeaderInfo().then(function(headerParams)
			{
				//
				headerParams.options.feature =
				[
					{"setup":"생산계획 Setup"},
					{"query":"조회"},
					{"create":"신규"},
					{"save":"저장"},
					//{"excel":"엑셀"},
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
			        "tabAreaBox",
			        "tabAreaBox.tab_plant_11",
			        "tabAreaBox.tab_plant_11.formBox",
			        "tabAreaBox.tab_plant_11.resultBox",
			        "tabAreaBox.tab_plant_11.resultBox.header",
			        "tabAreaBox.tab_plant_11.resultBox.content", //
			        "tabAreaBox.tab_plant_12",
			        "tabAreaBox.tab_plant_12.formBox",
			        "tabAreaBox.tab_plant_12.resultBox",
			        "tabAreaBox.tab_plant_12.resultBox.header",
			        "tabAreaBox.tab_plant_12.resultBox.content", //
			        "tabAreaBox.tab_plant_13",
			        "tabAreaBox.tab_plant_13.formBox",
			        "tabAreaBox.tab_plant_13.resultBox",
			        "tabAreaBox.tab_plant_13.resultBox.header",
			        "tabAreaBox.tab_plant_13.resultBox.content", //
			        "tabAreaBox.tab_plant_21",
			        "tabAreaBox.tab_plant_21.formBox",
			        "tabAreaBox.tab_plant_21.resultBox",
			        "tabAreaBox.tab_plant_21.resultBox.header",
			        "tabAreaBox.tab_plant_21.resultBox.content", //
			        "tabAreaBox.tab_plant_22",
			        "tabAreaBox.tab_plant_22.formBox",
			        "tabAreaBox.tab_plant_22.resultBox",
			        "tabAreaBox.tab_plant_22.resultBox.header",
			        "tabAreaBox.tab_plant_22.resultBox.content", //
			        "tabAreaBox.tab_plant_31",
			        "tabAreaBox.tab_plant_31.formBox",
			        "tabAreaBox.tab_plant_31.resultBox",
			        "tabAreaBox.tab_plant_31.resultBox.header",
			        "tabAreaBox.tab_plant_31.resultBox.content", //
					"tabAreaBox.tab_plant_99",
			        "tabAreaBox.tab_plant_99.formBox",
			        "tabAreaBox.tab_plant_99.resultBox",
			        "tabAreaBox.tab_plant_99.resultBox.header",
			        "tabAreaBox.tab_plant_99.resultBox.content", //
			        "tabAreaBox.tab_total",
					"tabAreaBox.tab_total.total_grid" //
			        ];
		}
		,
		getHeaderGridName: function()
		{
			if(! this._activeTabId )
			{
				return "tab_total";
			}
			return this.makeGridId(this._activeTabId);
		}
		,
		onEventHandler: function(event)
		{
			if( RendererBase.Method.onEventHandler.apply( this, arguments ) == false )
			{
				if( event.cmd == "content:setup" )
				{
					UCMS.reloadPage("#box/PP0301");
				}
				else
				{
					return false;
				}
			}
			return true;
		}
		,
		/**
		 * 랜더러 아이템들의 이벤트를 초기화한다.
		 * show:complete 상태에서 발생된다.
		 */
		onInitRendererItemEvents: function()
		{
			var self = this, header = null;

			UCMS.retry(function()
			{
				if(self.$el.find(".tabarea_box button.create").length < 5 || self.$el.find(".tabarea_box button.confirm").length < 5)
				{
					return false;
				}

				//
				self.attachHeaderItem("headerBox");

				//
				var p11FormItem = self.attachFormItem("tabAreaBox.tab_plant_11.formBox");
				p11FormItem.getItem().on(FormBox.EVENT.CHANGE, function()
				{
					var data = p11FormItem.getData();
					data.doosu5 = Number(data.doosu1) + Number(data.doosu2) + Number(data.doosu3) + Number(data.doosu4);
					p11FormItem.setData(data);
					
					self.beginTransaction();
				});
				p11FormItem.getItem().disabled(true);
				header = self.attachHeaderItem("tabAreaBox.tab_plant_11.resultBox.header", function(evt)
				{
					self.onButtonHandler(evt);
				}).getItem();
				var p11GridItem = self.attachGridItem("tabAreaBox.tab_plant_11.resultBox.content");
				p11GridItem.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
				{
					self.updateGridData( p11GridItem, item );
				});
				p11GridItem.getItem()
				.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
				{
					self.downloadExcel("PP0302_grid_1","생산계획_입력_CM_안성1공장",false);
				});

				//
				var p12FormItem = self.attachFormItem("tabAreaBox.tab_plant_12.formBox");
				p12FormItem.getItem().on(FormBox.EVENT.CHANGE, function()
				{
					var data = p12FormItem.getData();
					data.doosu5 = Number(data.doosu1) + Number(data.doosu2) + Number(data.doosu3) + Number(data.doosu4);
					p12FormItem.setData(data);
					
					self.beginTransaction();
				});
				p12FormItem.getItem().disabled(true);
				header = self.attachHeaderItem("tabAreaBox.tab_plant_12.resultBox.header", function(evt)
				{
					self.onButtonHandler(evt);
				}).getItem();
				var p12GridItem = self.attachGridItem("tabAreaBox.tab_plant_12.resultBox.content");
				p12GridItem.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
				{
					self.updateGridData( p12GridItem, item );
				});

				p12GridItem.getItem()
				.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
				{
					self.downloadExcel("PP0302_grid_2","생산계획_입력_CM_안성2공장",false);
				});
				//
				var p13FormItem = self.attachFormItem("tabAreaBox.tab_plant_13.formBox");
				p13FormItem.getItem().on(FormBox.EVENT.CHANGE, function()
				{
					var data = p13FormItem.getData();
					data.doosu5 = Number(data.doosu1) + Number(data.doosu2) + Number(data.doosu3) + Number(data.doosu4);
					p13FormItem.setData(data);
					
					self.beginTransaction();
				});
				p13FormItem.getItem().disabled(true);
				header = self.attachHeaderItem("tabAreaBox.tab_plant_13.resultBox.header", function(evt)
				{
					self.onButtonHandler(evt);
				}).getItem();
				var p13GridItem = self.attachGridItem("tabAreaBox.tab_plant_13.resultBox.content");
				p13GridItem.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
				{
					self.updateGridData( p13GridItem, item );
				});

				p13GridItem.getItem()
				.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
				{
					self.downloadExcel("PP0302_grid_13","생산계획_입력_CM_안성3공장",false);
				});
				//
				var p21FormItem = self.attachFormItem("tabAreaBox.tab_plant_21.formBox");
				p21FormItem.getItem().on(FormBox.EVENT.CHANGE, function()
				{
					var data = p21FormItem.getData();
					data.doosu5 = Number(data.doosu1) + Number(data.doosu2) + Number(data.doosu3) + Number(data.doosu4);
					p21FormItem.setData(data);
					
					self.beginTransaction();
				});
				p21FormItem.getItem().disabled(true);
				header = self.attachHeaderItem("tabAreaBox.tab_plant_21.resultBox.header", function(evt)
				{
					self.onButtonHandler(evt);
				}).getItem();
				var p21GridItem = self.attachGridItem("tabAreaBox.tab_plant_21.resultBox.content");
				p21GridItem.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
				{
					self.updateGridData( p21GridItem, item );
				});
				p21GridItem.getItem()
				.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
				{
					self.downloadExcel("PP0302_grid_3","생산계획_입력_CM_호남1공장",false);
				});

				//
				var p22FormItem = self.attachFormItem("tabAreaBox.tab_plant_22.formBox");
				p22FormItem.getItem().on(FormBox.EVENT.CHANGE, function()
				{
					var data = p22FormItem.getData();
					data.doosu5 = Number(data.doosu1) + Number(data.doosu2) + Number(data.doosu3) + Number(data.doosu4);
					p22FormItem.setData(data);
					
					self.beginTransaction();
				});
				p22FormItem.getItem().disabled(true);
				header = self.attachHeaderItem("tabAreaBox.tab_plant_22.resultBox.header", function(evt)
				{
					self.onButtonHandler(evt);
				}).getItem();
				var p22GridItem = self.attachGridItem("tabAreaBox.tab_plant_22.resultBox.content");
				p22GridItem.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
				{
					self.updateGridData( p22GridItem, item );
				});
				p22GridItem.getItem()
				.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
				{
					self.downloadExcel("PP0302_grid_3","생산계획_입력_CM_호남2공장",false);
				});

				//
				var p31FormItem = self.attachFormItem("tabAreaBox.tab_plant_31.formBox");
				p31FormItem.getItem().on(FormBox.EVENT.CHANGE, function()
				{
					var data = p31FormItem.getData();
					data.doosu5 = Number(data.doosu1) + Number(data.doosu2) + Number(data.doosu3) + Number(data.doosu4);
					p31FormItem.setData(data);
					
					self.beginTransaction();
				});
				header = self.attachHeaderItem("tabAreaBox.tab_plant_31.resultBox.header", function(evt)
				{
					self.onButtonHandler(evt);
				}).getItem();

				p31FormItem.getItem().disabled(true);
				var p31GirdItem = self.attachGridItem("tabAreaBox.tab_plant_31.resultBox.content")
				p31GirdItem.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
				{
					self.updateGridData( p31GirdItem, item );
				});
				p31GirdItem.getItem()
				.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
				{
					self.downloadExcel("PP0302_grid_4","생산계획_입력_CM_김제공장",false);
				});

				var p99FormItem = self.attachFormItem("tabAreaBox.tab_plant_99.formBox");
				header = self.attachHeaderItem("tabAreaBox.tab_plant_99.resultBox.header", function(evt)
				{
					self.onButtonHandler(evt);
				}).getItem();
				p99FormItem.getItem().on(FormBox.EVENT.CHANGE, function()
				{
					var data = p99FormItem.getData();
					data.doosu5 = Number(data.doosu1) + Number(data.doosu2) + Number(data.doosu3) + Number(data.doosu4);
					p99FormItem.setData(data);
					
					self.beginTransaction();
				});
				p99FormItem.getItem().disabled(true);
				
				var p99GirdItem = self.attachGridItem("tabAreaBox.tab_plant_99.resultBox.content")
				p99GirdItem.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
				{
					self.updateGridData( p99GirdItem, item );
				});
				p99GirdItem.getItem()
				.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
				{
					self.downloadExcel("PP0302_grid_99","생산계획_입력_CM_기타공장",false);
				});


				var totGirdItem = self.attachGridItem("tabAreaBox.tab_total.total_grid")
				totGirdItem.getItem()
				.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
				{
					self.downloadExcel("PP0302_grid_5","생산계획_입력_CM_합계",false);
				});

				//
				self.$el.find(".tabarea_box button.confirm").prop("disabled", true);
			});
		}
	};

	var WorkAreaMethod =
	{
		/**
		 * BaroBox 에서 발생되는 탭 관련 이벤트의 처리자
		 * RendererBase.onEventHandler() 에서 호출된다.
		 */
		onActiveTab: function(params)
		{
			var self = this;
			if( this._activeTabId != params.active &&
				this.isTransactionMode() == true )
			{
				var self = this;
				UCMS.confirm("진행 중인 작업을 저장하시겠습니까?", {confirm:"저장", cancel:"취소"})
				.done(function()
					{
						Logger.debug("params.active " + params.active);
						
						self.getItemInstance("tabAreaBox").activeTab( self._activeTabId );
						self.onSave( true );
					}
				)
				.fail(function()
				{
					self.onCancel();
				});
				return;
			}

			//
			this._activeTabId = params.active;
		}
		,
		onButtonHandler: function(event)
		{
			var self = this;

			switch( event.cmd )
			{
			case "content:create":
				self.onAddProduct( event.params.id );
				break;
			case "content:confirm":
				self.onChangeStatus();
				break;

			default:
				return false;
			}

			return true;
		}
		,
		onQuery: function()
		{
			if( this.isTransactionMode() == true )
			{
				UCMS.alert("진행 중인 작업이 있습니다.");
				return;
			}

			Logger.info("Renderer.onQuery");

			var params = this.getQueryData();
			this.createTransactionData();
			if( this._activeTabId == "tab_total" )
			{
				this.fetchCompTimeList();
				this.fetchTotalSum(this._activeTabId, params);
				this.fetchTotalList(this._activeTabId, params);
			}
			else
			{
				Logger.debug("Tab ID : "+this._activeTabId);
				this.fetchTabHeader(this._activeTabId, params);
				this.fetchTabDetail(this._activeTabId, params);

				//
				this.getActiveForm().getItem().disabled(false);
			}
		}
		,
		onCreate: function()
		{
			if( this.isTransactionMode() == true )
			{
				UCMS.alert("진행 중인 작업이 있습니다.");
				return;
			}
			if( this._activeTabId == "tab_total" )
			{
				UCMS.alert("\"합계\" 탭에서는 지원하지 않는 기능입니다.");
				return;
			}

			Logger.info("Renderer.onCreate() - cur TabId : "+this._activeTabId);
			var self = this;
			this.fetchNewList(this._activeTabId)
			.then(function()
			{
				self.getActiveForm().getItem().disabled(false);
			});
		}
		,
		onSave: function(silent)
		{
			if( this.isTransactionMode() == false )
			{
				UCMS.alert("저장할 작업이 없습니다.");
				return;
			}

			var formItem = this.getActiveForm();
			var headData = formItem.getData();

			if( headData.doosu5 == 0 )
			{
				UCMS.alert("작업두수를 입력해 주세요.");
				return;
			}

			var self = this;
			var save = function()
			{
				Logger.info("Renderer.onSave() - cur TabId : "+self._activeTabId);

				var gridItem = self.getActiveGrid();
				var params = self.getQueryData();
				delete params.proCode;
				params.plantCode = self.getPlantId();

				var taskList = self.updateTransactionData( headData, gridItem.getTaskList() );
				if(!taskList)
				{
					return;
				}
				//
				gridItem.commit( params, "new", taskList )
				.then(function(res)
				{
					if( res.resultCode != 0 )
					{
						UCMS.reportError(res)
					}
					else
					{
						UCMS.alert("생산계획이 저장되었습니다.");
						var header = self.attachHeaderItem("headerBox");
						header.setMode( WorkAreaHeader.MODE.READY );
						self.setStatusButtonMode("plan");

						self.onQuery();
					}
				});
			};
			if( silent == true )
			{
				save();
			}
			else
			{
				UCMS.confirm("저장할까요?", {confirm:"저장",cancel:"취소"}).then(save);
			}
		}
		,
		onCancel: function()
		{
			Logger.info("Renderer.onCancel() - cur TabId : "+this._activeTabId);
			RendererBase.Method.onCancel.call( this );
			UCMS.alert("입력된 데이타가 취소되었습니다.");
		}
		,
		onDelete: function()
		{
			var self = this;
			var formItem = this.getActiveForm();
			if( !formItem )
			{
				UCMS.alert("대상 공장을 선택하세요.");
				return;
			}
			if( formItem.getData().status.length == 0 )
			{
				UCMS.alert("선택된 생산계획이 없습니다.");
				return;
			}
			var remove = function()
			{
				var params = self.getQueryData();
				var apiPath = self._client.getAPIPath('create', "remove", params);

				self._client.transaction(apiPath)
				.then(function(res)
				{
					if( res.resultCode == 0 )
					{
						formItem.clear();
						self.getActiveGrid().clear();

						self.setStatusButtonMode();
						UCMS.alert("생산계획이 삭제되었습니다.");
					}
					else
					{
						UCMS.reportError(res);
					}
				})
				.fail(function(err)
				{
					UCMS.reportError(err);
				});
			};
			UCMS.confirm("전체공장 데이터가 삭제됩니다.</br>생산계획을 삭제하시겠습니까?", {confirm:"삭제", cancel:"취소"})
			.then(remove);
		}
		,
		onAddProduct: function(btnId)
		{
			Logger.debug("onAddProduct : "+btnId);
			var self = this;
			var params = this.getQueryData();
			if( params.proCode.result.length == 0 )
			{
				UCMS.alert("추가할 제품을 선택해 주세요.").then(
						function(){
							
							var queryBox = self.attachFormItem("queryBox");
							queryBox.getItem().getWidget$Element().find(".val_result").focus();
						}
				);
				return;
			}
			var gridId = this.makeGridId(btnId.substr(11,12));
			var grid = this.attachGridItem(gridId);
			if(! grid )
			{
				UCMS.alert("대상 그리드를 찾을 수 없습니다. : "+gridId);
				return;
			}
			if( grid.getItem().getRowData("proCode", params.proCode.result).length > 0 )
			{
				UCMS.alert("이미 존재하는 제품입니다.");
				return;
			}

			var self = this;
			this.fetchProdDetail()
			.then(function(prodInfo)
			{
				if( prodInfo == null )
				{
					UCMS.alert("\""+params.proCode.keyword+"\"은 생산계획 수립을 위한 세부 정보가 존재하지 않는 제품입니다.");
					return;
				}
				if( prodInfo.ipsuDoo == 0 )
				{
					UCMS.alert("\""+params.proCode.keyword+"\"은 \"입수두수\"가 존재하지 않는 제품입니다.");
					return;
				}

				self.beginTransaction(gridId);
				grid.createRow
				(
					_.extend(
					{
						proCode: params.proCode.result,
						proName: params.proCode.keyword,
						proLname: params.proCode.raw.largeName,
						proLcode: params.proCode.raw.largeCode,
					}
					,
					prodInfo)
				);
			})
			.fail(function(err)
			{
				UCMS.reportError(err, "생산계획 수립을 위한 세부 정보중 오류가 발생했습니다.<br>잠시 후 다시 시도해 주세요.");
			});
		}
		,
		onChangeStatus: function()
		{
			this.setCompleteStatus( this._statusCode[ this._activeTabId ] );
		}
		,
		fetchTabHeader: function(tabId, params)
		{
			var self = this;
			params.plantCode = this.getPlantId(tabId);
			if( params.proCode )
			{
				delete params.proCode;
			}

			var head = this.getActiveForm();

			this.showLoading();

			this._client.read(params, "header")
			.then(function(res)
			{
				self.hideLoading();

				if( res.resultCode == 0 && res.extraData )
				{
					self._statusCode[tabId] = res.extraData.status;
					self.setStatusButtonMode(res.extraData.status == "3" ? "complete" : "plan");

					//
					switch(res.extraData.status)
					{
					case "0":
						res.extraData.status = "접수";
						break;

					case "1":
						res.extraData.status = "계획";
						break;

					case "3":
						res.extraData.status = "확정";
						break;
					}
					head.setData(res.extraData);
					//
					self.updateTransactionData( head.getData() );
				}
				else
				{
					self.setStatusButtonMode();
					head.clear();
				}
			})
			.fail(function(err)
			{
				self.hideLoading();

				self.setStatusButtonMode();
				head.clear();
				UCMS.reportError(err);
			});
		}
		,
		fetchTabDetail: function(tabId, params)
		{
			var self = this;
			var grid = this.attachGridItem(this.makeGridId(tabId));

			params.plantCode = this.getPlantId(tabId);
			grid.fetch(params, "search")
			.then(function(res)
			{
				if( res.resultCode == 0 && res.extraData )
				{
					if( res.extraData.result.length > 0 )
					{
						grid.setData(res.extraData.result);
						self.$el.find("."+tabId+" button.create").prop("disabled", false);
					}
					else
					{
						grid.clear();
						UCMS.alert("조회된 데이타가 없습니다.");
					}
				}
				else
				{
					self.setStatusButtonMode();
					grid.clear();
					UCMS.reportError(res);
				}
			})
			.fail(function(err)
			{
				self.setStatusButtonMode();
				grid.clear();
				UCMS.reportError(err);
			});
		}
		,
		fetchTotalSum: function(tabId, params)
		{
			var self = this;
			this._client.read(params, "total")
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					var grid = self.attachGridItem("tabAreaBox."+tabId+".total_grid");
					var $el = grid.getItem().$el;

					_.map($el.find("th .total"), function(item)
					{
						var $item = $(item);
						switch($item.parents("th").attr("id"))
						{
						case "PP0302_grid_6_list_p11":
							$item.text(res.extraData.p11||0);
							break;

						case "PP0302_grid_6_list_p12":
							$item.text(res.extraData.p12||0);
							break;
							
						case "PP0302_grid_6_list_p13":
							$item.text(res.extraData.p13||0);
							break;	

						case "PP0302_grid_6_list_p21":
							$item.text(res.extraData.p21||0);
							break;

						case "PP0302_grid_6_list_p22":
							$item.text(res.extraData.p22||0);
							break;

						case "PP0302_grid_6_list_psum":
							$item.text(res.extraData.dooSum||0);
							break;
						}
					});
				}
				else
				{
					Logger.warn(res);
				}
			});
		}
		/**
		 * 확정시간 그리드 
		 * 
		 * */
		,
		fetchCompTimeList: function()
		{
			var self = this;
			var params = this.getQueryData();
			
			this._client.read(params, "complist")
			.then(function(res)
			{
				if( res.resultCode == 0 && res.extraData )
				{
					var compData = res.extraData;
					
					if(compData.planDateTime11 != null)
					{
						$(".planDateTime11").val(compData.planDateTime11);
					}
					if(compData.planDateTime12 != null)
					{
						$(".planDateTime12").val(compData.planDateTime12);
					}
					if(compData.planDateTime13 != null)
					{
						$(".planDateTime13").val(compData.planDateTime13);
					}
					if(compData.planDateTime21 != null)
					{
						$(".planDateTime21").val(compData.planDateTime21);
					}
					if(compData.planDateTime22 != null)
					{
						$(".planDateTime22").val(compData.planDateTime22);
					}
					if(compData.planDateTime31 != null)
					{
						$(".planDateTime31").val(compData.planDateTime31);
					}
					if(compData.planDateTime99 != null)
					{
						$(".planDateTime99").val(compData.planDateTime99);
					}
				}
				else
				{
					self.clearCompTime();
				}
			})
			.fail(function(err)
			{
				self.clearCompTime();
			});
		}
		,
		clearCompTime: function()
		{
			$(".planDateTime11").val('');
			$(".planDateTime12").val('');
			$(".planDateTime13").val('');
			$(".planDateTime21").val('');
			$(".planDateTime22").val('');
			$(".planDateTime31").val('');
			$(".planDateTime99").val('');
		}
		,
		fetchTotalList: function(tabId, params)
		{
			var grid = this.attachGridItem("tabAreaBox."+tabId+".total_grid");
			grid.fetch(params, "totlist")
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					if( res.extraData.result.length > 0 )
					{
						grid.setData(res.extraData.result);
					}
					else
					{
						grid.clear();
						UCMS.alert("조회된 데이타가 없습니다.");
					}
				}
				else
				{
					Logger.warn(res);
				}
			});
		}
		,
		/**
		 * @param {string} mode - 확정(complete) / 계획(plan) 지정
		 * 						이외의 값이 지정되면 버튼이 모두 disabled 된다.
		 */
		setStatusButtonMode: function(mode)
		{
			var tabArea = this.getItemInstance("tabAreaBox."+this._activeTabId);
			if( tabArea )
			{
				tabArea.$el.find("button.confirm")
					.prop("disabled", mode==undefined)
					.text(mode!="plan"?"확정취소":"계획확정");

				this._statusCode[ this._activeTabId ] = (mode=="plan"? "1" : mode=="complete" ? "3" : "0");
			}
			else
			{
				Logger.warn("setStatusButtonMode() - Not complete to initialize...");
			}
		}
		,
		/**
		 * 입력된 계획의 상태를 변경한다.
		 * @param {string} curStatus - 현재 상태, 0 접수, 1 계획, 3 확정
		 */
		setCompleteStatus: function(curStatus)
		{
			var self = this;
			// 요청하는 상태값을 갖는 파라메터 생성
			var params = this.getQueryData(
			{
				"status" : curStatus=="3"?"plan":"complete"
			});
			var req = function()
			{
				var apiPath = self._client.getAPIPath('create', "status", params);
				self._client.transaction(apiPath)
				.then(function(res)
				{
					if( res.resultCode == 0 )
					{
						var head = self.getActiveForm();
						var headData = head.getData();
						head.setData(_.extend(headData,{"status": params.status=="plan"?"계획":"확정" }));
						self.setStatusButtonMode(params.status);
						UCMS.alert("생산계획 상태가 변경 되었습니다.");
					}
					else
					{
						UCMS.reportError(res);
					}
				})
				.fail(function(err)
				{
					UCMS.reportError(err);
				});
			};
			UCMS.confirm("생산계획 상태를 변경하시겠습니까?", {confirm:"변경", cancel:"취소"}).then(req);
		}
		,
		/**
		 *  로딩바를 출력한다.
		 * @param parent_selector jquery selector 를 사용한다.
		 *
		 * @return { Boolean } 출력 성공시 true, 이미 존재하는 경우 false.
		 */
		showLoadingThis:function(parent_selector)
		{
			if($ && $(".loading_box").length == 0)
			{
				$("body").append("<div class='loading_box'><div class='loading_img'></div><div style='display: block;position: fixed;top: 50%;width: 290px;background: #fff;font-size: 20px;text-align: center;left: 50%;margin-left: -145px;margin-top: 40px;z-index: 2;'>생산계획자료 형성중 입니다</div></div>");
				return true;
			}
			else
			{
				return false;
			}
		},
		hideLoadingThis:function()
		{
		 $("body > .loading_box").remove();
		},
		fetchNewList: function(tabId)
		{
			var params = this.getQueryData();
			var gridId = this.makeGridId(tabId);
			var grid = this.attachGridItem(gridId);
			if(! grid )
			{
				UCMS.alert("대상 그리드를 얻지 못했습니다.");
				return UCMS.retReject();
			}

			var self = this;

			this.showLoadingThis();
			return grid.fetch(params, "new")
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					if( res.extraData.result.length > 0 )
					{
						/*
						grid.createRow(res.extraData.result);
						self.createTransactionData().dsType = "C";

						//
						self.beginTransaction( gridId );
						*/
						//self.$el.find("."+tabId+" button.create").prop("disabled", false);

						self.fetchTabHeader(self._activeTabId, params);
						self.fetchTabDetail(self._activeTabId, params);

					}
					else
					{
						UCMS.alert("할당된 생산량이 없습니다.");
					}
				}
				else if( res.resultCode == -4 )
				{
					UCMS.alert("이미 생산계획이 형성되어 있습니다.");
				}
				else
				{
					UCMS.reportError(res);
				}
			})
			.always(function()
			{
				self.hideLoadingThis();
			});
		}
		,
		getActiveForm: function()
		{
			if( this._activeTabId == "tab_total" )
			{
				return null;
			}
			return this.attachFormItem(this.makeFormId(this._activeTabId));
		}
		,
		getActiveGrid: function()
		{
			var gridId = this.makeGridId(this._activeTabId);
			return this.attachGridItem(gridId);
		}
		,
		getQueryData: function(params)
		{
			var queryData = this.attachFormItem("queryBox").getData();
			if( this._activeTabId != "tab_total" )
			{
				queryData.plantCode = this.getPlantId();
			}
			return _.extend(queryData, params);
		}
		,
		getPlantId: function(tabId)
		{
			tabId || (tabId=this._activeTabId);
			return tabId.substr(10, 2);
		}
		,
		makeFormId: function(tabId)
		{
			return "tabAreaBox."+tabId+".formBox"
		}
		,
		makeGridId: function(tabId)
		{
			return "tabAreaBox."+tabId+".resultBox.content"
		}
		,
		updateGridData: function(gridItem, item)
		{
			var data = gridItem.getItem().getRowData(item.id);
			if( item.cell.name == "sanQty" )
			{
				// 생산량 Box 변경됨
				// 정수로 변환하여 저장
				
				if(isNaN(data.unitKg))
					data.unitKg = 0;
				if(isNaN(data.jaegoWeig))
					data.jaegoWeig = 0;
				if(isNaN(data.orderWeig))
					data.orderWeig = 0;
				
				data.sanQty = Math.round(item.cell.value);
				data.sanDosu = (data.sanQty * data.ipsuDoo).toFixed(2);
				data.sanWeig = data.sanQty * Number(data.unitKg);
				data.bjaegoWeig= (Number(data.jaegoWeig) - Number(data.orderWeig) + data.sanWeig);
				
				//UCMS.alert(data.sanDosu);
			}
			gridItem.setRow(item.id, data);

			//
			this.beginTransaction();
		}
		,
		/**
		 * 선택된 제품의 생산 세부 정보를 조회한다.
		 */
		fetchProdDetail: function()
		{
			var self = this;
			var params = this.getQueryData();
			if( params.proCode.result.length == 0 )
			{
				return UCMS.retReject();
			}

			params.proCode = params.proCode.result;
			return this._client.read(params, "newprod")
			.then(function(res)
			{
				if( res.resultCode != 0 )
				{
					return null;
				}
				return res.extraData;
			});
		}
		,
		createTransactionData: function(head, detail)
		{
			this._transData =
			{
				"head" : head || null,
				"detail" : detail || null,
				"dsType" : "U"
			};
			return this._transData;
		}
		,
		updateTransactionData: function(head, detail)
		{
			this._transData || this.createTransactionData();
			this._transData.dsType = "U";
			if( head )
			{
				this._transData.head = head;
			}
			if( detail && detail.length > 0)
			{
				for(var i in detail)
				{
					var task = detail[i];
					if( task != null && task.memo != null && UCMS.getByteLength(task.memo) > 50 )
					{
						UCMS.alert("\"비고\"의 입력값은 50 bytes 를 넘어설 수 없습니다.");
						return null;
					}
				}

				this._transData.detail = detail;
				this._transData.dsType = detail[0].dsType;
			}
			return this._transData;
		},

		/**
		 * Task commit 시 데이타의 유효성을 검증한다.
		 * 기본적으로 true 를 반환하며, 검증이 필요한 랜더러에서 상속 받아서 제공되는 Task 정보의 유효성 검증 결과를 boolean 값으로 반환처리한다.
		 * @param {string} gridId - commit 이 발생된 그리드의 식별자
		 * @param {object} taskItem - 트랜잭션 항목 객체
		 * @return {boolean} false 를 리턴하면, 해당 commit 동작은 reject 되며, 문제가 된 task 항목에 대한 정보 { id, task } 가 반환된다.
		 "tabAreaBox.tab_plant_11.resultBox.content", //
		 "tabAreaBox.tab_plant_12.resultBox.content", //
		 "tabAreaBox.tab_plant_21.resultBox.content", //
		 "tabAreaBox.tab_plant_22.resultBox.content", //
		 "tabAreaBox.tab_plant_31.resultBox.content", //

		 */
		isValidRowChecker: function(gridId, taskItem)
		{
			if( gridId == "tabAreaBox.tab_plant_11.resultBox.content" )
			{
				return ( taskItem.sanQty && taskItem.memo ) ? true : false;
			}
			else if ( gridId == "tabAreaBox.tab_plant_12.resultBox.content" )
			{
				return ( taskItem.sanQty && taskItem.memo ) ? true : false;
			}
			else if ( gridId == "tabAreaBox.tab_plant_21.resultBox.content" )
			{
				return ( taskItem.sanQty && taskItem.memo ) ? true : false;
			}
			else if ( gridId == "tabAreaBox.tab_plant_22.resultBox.content" )
			{
				return ( taskItem.sanQty && taskItem.memo ) ? true : false;
			}
			else if ( gridId == "tabAreaBox.tab_plant_31.resultBox.content" )
			{
				return ( taskItem.sanQty && taskItem.memo ) ? true : false;
			}
			return true;
		}


	};

	var Renderer = BaroBox.extend
	(
		_.extend({}
			, RendererBase.Method
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
