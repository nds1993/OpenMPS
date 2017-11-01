/**
 * Project : OpenMPS MIS
 *
 * 생산 > 생산실적 조회 및 조정 (개체)
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
 	"manifest!jqGrid4-1.0.0#widget"
 ], function(Logger, NDSProps, BaroBox, FormBox, WorkAreaHeader, RendererBase, WorkAreaRenderer2, JQGrid)
 {
 	var Renderer = WorkAreaRenderer2.extend(
 	{
		constructor: function(options)
		{
			var gridId = ["date_grid", "plant_grid", "nong_grid", "largecode_grid"], gridOptions;
			for(var i in gridId)
			{
				gridOptions = options.tabAreaBox.options.tab_plan.options[gridId[i]].options.gridParams;
				_.extend
				(
					gridOptions,
					this.makeAutoScrollingOptions2_local(50)
				);
			}
			_.extend
			(
				options.tabAreaBox.options.tab_barcode.options.barcode_grid.options.gridParams,
				this.makeAutoScrollingOptions2_local(50)
			);
			
			Renderer.__super__.constructor.call(this, options);
		}
		,
 		initialize: function(options)
 		{
 			Renderer.__super__.initialize.apply( this, arguments );
 			this._contentId = "PP0504";
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
			        {"restore":"복원"},
			        {"delete":"삭제"}
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
 			          "tabAreaBox",
 			          "tabAreaBox.tab_plan",
 			          "tabAreaBox.tab_plan.date_grid",
 			          "tabAreaBox.tab_plan.plant_grid",
 			          "tabAreaBox.tab_plan.nong_grid",
 			          "tabAreaBox.tab_plan.largecode_grid",
 			          "tabAreaBox.tab_barcode",
 			          "tabAreaBox.tab_barcode.barcode_query",
 			          "tabAreaBox.tab_barcode.barcode_query.btnGoSearch",
 			          "tabAreaBox.tab_barcode.barcode_grid"
 			        ];
 		}
 		,
 		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
 		getHeaderGridName: function()
 		{
 			if( this._activeTabId == "tabAreaBox.tab_plan" )
 			{
 				return this._activeTabId+"."+this._activeGridId+"_grid";
 			}
 			else
 			{
 				return this._activeTabId+".barcode_grid";
 			}
 		}
 		,
 		/**
		 * 4. 랜더러 아이템 관계성 초기화
		 */
 		onInitRendererItemEvents: function()
 		{
 			var self = this;

			this.attachHeaderItem("headerBox", function(evt)
			{
				self.onButtonHaldler(evt);
			});
			this.attachFormItem("queryBox");
			//일자별 조회
			var dateGrid = this.attachGridItem("tabAreaBox.tab_plan.date_grid");
			dateGrid.getItem().on(JQGrid.EVENT.GRIDCOMPLETE, function(data)
			{
				Logger.debug("JQGrid.EVENT.LOADCOMPLETE");
				var sum = dateGrid.getItem().getCol( "boxWeig", false, "sum" );
				dateGrid.getItem().footerData('set', { cartonNo: "합계", boxWeig: sum });
			});
			dateGrid.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0504_grid_1","생산실적조회및조정_일자별",false);
			});



			//공장별 조회
			var plantGrid = this.attachGridItem("tabAreaBox.tab_plan.plant_grid");
			plantGrid.getItem().on(JQGrid.EVENT.GRIDCOMPLETE, function(data)
			{
				var sum = plantGrid.getItem().getCol( "boxWeig", false, "sum" );
				plantGrid.getItem().footerData('set', { cartonNo: "합계", boxWeig: sum });
			});
			plantGrid.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0504_grid_2","생산실적조회및조정_공장별",false);
			});


			//농장별 조회
			var nongGrid = this.attachGridItem("tabAreaBox.tab_plan.nong_grid");
			nongGrid.getItem().on(JQGrid.EVENT.GRIDCOMPLETE, function(data)
			{
				var sum = nongGrid.getItem().getCol( "boxWeig", false, "sum" );
				nongGrid.getItem().footerData('set', { cartonNo: "합계", boxWeig: sum });
			});
			nongGrid.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0504_grid_3","생산실적조회및조정_농장별",false);
			});

			//품목별 조회
			var largecodeGrid = this.attachGridItem("tabAreaBox.tab_plan.largecode_grid");
			largecodeGrid.getItem().on(JQGrid.EVENT.GRIDCOMPLETE, function(data)
			{
				var sum = largecodeGrid.getItem().getCol( "boxWeig", false, "sum" );
				largecodeGrid.getItem().footerData('set', { cartonNo: "합계", boxWeig: sum });
			});
			largecodeGrid.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0504_grid_4","생산실적조회및조정_품목별",false);
			});

			//바코드탭 쿼리박스 추가부분
			this.attachFormItem("tabAreaBox.tab_barcode.barcode_query");
			//바코드탭 그리드
			var barcodeGrid = this.attachGridItem("tabAreaBox.tab_barcode.barcode_grid");
			barcodeGrid.getItem().on(JQGrid.EVENT.GRIDCOMPLETE, function(data)
			{
				var sum = barcodeGrid.getItem().getCol( "boxWeig", false, "sum" );
				barcodeGrid.getItem().footerData('set', { cartonNo: "합계", boxWeig: sum });
			});
			barcodeGrid.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0504_grid_5","생산실적조회및조정_바코드",false);
			});
			//
			this.setActiveGrid("date");
			this.initCustomButton();

 		}
 		,
 		onActiveTab: function(params)
		{
 			this._activeTabId = "tabAreaBox."+params.active;

 			if(this.getActiveGrid() != null)
 			{
 				//Logger.debug("this.getHeaderGridName :: " + this.getHeaderGridName());
 				
 				this.onQuery();
 				/**
 				var queryData = this.getQueryData();
 				var gridItem = this.getActiveGrid();
				var reqData = gridItem.getItem().getMultiData();
				
				if(reqData && reqData.length > 0)
				{
 					this.$el.find(".pageheader_box button.restore").prop("disabled", queryData.searchCondition2 == "delen");
 		 			this.$el.find(".pageheader_box button.delete").prop("disabled", queryData.searchCondition2 == "deley");
				}
				else
				{
					this.$el.find(".pageheader_box button.restore").prop("disabled", true);
	 	 			this.$el.find(".pageheader_box button.delete").prop("disabled", true);
				}
				*/
 			}
 			else
 			{
 				this.$el.find(".pageheader_box button.restore").prop("disabled", true);
 	 			this.$el.find(".pageheader_box button.delete").prop("disabled", true);
 			}
		}
 		,
		getQueryData: function(params)
		{
			var data = RendererBase.Method.getQueryData.call( this, params );
			if( data["proCode"] )
			{
				data["proCode"] = data["proCode"].result;
			}
			if( data.plantCode == 0 )
			{
				delete data.plantCode;
			}
			if( data.largeCode == 0 )
			{
				delete data.largeCode;
			}
			if( this._activeTabId == "tabAreaBox.tab_barcode" )
 			{
				var subQueryData = this.attachFormItem("tabAreaBox.tab_barcode.barcode_query")
									.getItem().getItemData();
				data["searchCondition"] = "barCode";
				data["barCode"] = subQueryData.barCode || null;
				data["cartonNo"] = subQueryData.cartonNo || null;
 			}
			return data;
		}
 		,
 		onQuery: function()
 		{
 			var self = this;
 			var queryData = this.getQueryData();
 			var featureId = this._activeTabId.indexOf('tab_plan') > 0 ? queryData.searchKey : "barcode";
			
 			if( new Date(queryData.strtDate) > new Date(queryData.lastDate) )
			{
				UCMS.alert("조회 기간이 잘못 지정되었습니다.");
				return;
			}
 			
 			var between = ( new Date(queryData.lastDate).getTime() - new Date(queryData.strtDate).getTime() ) / 1000/60/60/24; 
 			if(queryData.plantCode == undefined)
 			{
 				if( between >  2)
 				{
 					UCMS.alert("전체공장 선택시 조회기간은 최대 3일까지 가능합니다.");
 					return;
 				}
 			}
 			else
 			{
 				if( between >  6)
 				{
 					UCMS.alert("각 공장별 조회기간은 최대 7까지 가능합니다.");
 					return;
 				}
 			}
 			
 			//
 			this.showLoading();
 			this.setActiveGrid( queryData.searchCondition );
 			this.fetchingGrid( featureId, queryData )
 			.always(function()
			{
				self.hideLoading();

				// 버튼 모드
				self.$el.find(".pageheader_box button.restore").prop("disabled", queryData.searchCondition2 == "delen");
				self.$el.find(".pageheader_box button.delete").prop("disabled", queryData.searchCondition2 == "deley");
			});
 		}
 		,
 		/**
 		 * 활성화되는 그리드를 설정한다.
 		 */
 		setActiveGrid: function(activeId)
 		{
 			if( this._activeTabId != "tabAreaBox.tab_plan" )
 			{
 				return;
 			}

 			var gridList = ["date", "plant", "nong", "largecode"];
 			for(var i in gridList)
 			{
 				var gridId = gridList[i];
 				this.attachGridItem("tabAreaBox.tab_plan."+gridId+"_grid")
 				.getItem().showPanel( gridId == activeId );
 			}
 			this._activeGridId = activeId;
 		}
 		,
 		initCustomButton: function()
 		{
 			var self = this;
 			UCMS.retry(function()
 			{
 				if( self.getWidget$Element().find(".btnGoSearch_region button").length == 0 )
 				{
 					return false;
 				}

 				self.getWidget$Element().find(".btnGoSearch_region button")
 				.click(function()
	 			{
	 				self.onQuery();
	 			});

 				//
 				self.$el.find(".pageheader_box button.restore").prop("disabled", true);
 				self.$el.find(".pageheader_box button.delete").prop("disabled", true);

 				//
 				self.$el.find(".barcode_query_region input[type=text]")
 				.keyup(function(event)
 				{
 					if( event.which == 13 )
 					{
 						self.onQuery();
 					}
 				});
 			});
 		}
 		,
 		onButtonHaldler: function(evt)
 		{
 			var queryData = this.getQueryData();

 			if( evt.cmd == "content:restore" )
 			{
 				if( queryData.searchCondition2 == "delen" )
 				{
 					UCMS.alert("삭제되지 않은 데이타입니다.");
 				}
 				else
 				{
 					var gridItem = this.getActiveGrid();
 					var reqData = gridItem.getItem().getMultiData();
 					if(reqData.length == 0)
 		 			{
 		 				UCMS.alert("복원할 데이터가 존재하지 않습니다.");
 		 				return;
 		 			}
 					
 					var self = this;
 					UCMS.confirm("생산실적을 복원하시겠습니까?", { confirm: "확인", cancel: "취소" })
 					.then(function()
 					{
 						self.request("restore", "건의 데이타가 복원되었습니다.");
 					});
 				}
 			}
 			else if( evt.cmd == "content:delete" )
 			{
 				if( queryData.searchCondition2 == "deley" )
 				{
 					UCMS.alert("이미 삭제된 데이타입니다.");
 				}
 				else
 				{
 					
 					var gridItem = this.getActiveGrid();
 					var reqData = gridItem.getItem().getMultiData();
 					if(reqData.length == 0)
 		 			{
 		 				UCMS.alert("삭제할 데이터가 존재하지 않습니다.");
 		 				return;
 		 			}
 					
 					var self = this;
 					UCMS.confirm("생산실적을 삭제하시겠습니까?", { confirm: "확인", cancel: "취소" })
 					.then(function()
 					{
 						self.request("delete", "건의 데이타가 삭제되었습니다.");
 					});
 				}
 			}
 		}
 		,
 		/**
 		 * 요청자
 		 * @param {string} deleType - restore or delete
 		 */
 		request: function(deleType, msg)
 		{
 			var self = this;
 			var client = this.getClient();
 			var queryData = self.getQueryData();
 			var apiPath = client.getAPIPath("create", null, _.extend({"deleType": deleType}, queryData));
 			var gridItem = this.getActiveGrid();
 			var reqData = gridItem.getItem().getMultiData();

 			var removeResult = function()
 			{
 				var rowIds = gridItem.getItem().getSelRowId( true );
 				for(var i in rowIds)
 				{
 					gridItem.getItem().removeRow(rowIds[i], false);
 				}
 				self.$el.find(".tabAreaBox_region .grid_box thead th input[type=checkbox]").prop("checked", false);
 			}

 			var doing = function()
 			{
	 			self.showLoading();
	 			client.transaction(apiPath, reqData)
	 			.then(function(result)
	 			{
	 				self.hideLoading();
	 				
	 				if(result.resultCode == 0)
	 				{
	 					if( queryData.searchCondition2 == "delen" &&  deleType == "delete" )
		 				{
		 					// 삭제 요청 성공 결과 반영
		 					removeResult();
		 				}
		 				else if( queryData.searchCondition2 == "deley" &&  deleType == "restore" )
		 				{
		 					// 복원 요청 성공 결과 반영
		 					removeResult();
		 				}
		 				
		 				UCMS.alert(reqData.length+msg);
	 				}
	 				else if(result.resultCode == -101)
	 				{
	 					var addMsg = "삭제할 수 없습니다.";
	 					if( queryData.searchCondition2 == "deley" )
	 					{
	 						addMsg = "복원할 수 없습니다.";
	 					}
	 					
	 					UCMS.alert("생산일자가 마감되었습니다. " + addMsg);
	 				}
	 				else
	 				{
	 					UCMS.alert(result.msg);
	 				}

	 			})
	 			.fail(function(err)
	 			{
	 				UCMS.reportError(err);
	 			});
 			};

 			Logger.debug("reqData cnt :: " + reqData.length);
 			if(deleType == "restore" && reqData.length == 0)
 			{
 				UCMS.alert("복원할 데이터가 존재하지 않습니다.");
 				return;
 			}
 			else if( deleType == "delete" )
 			{
 				if(reqData.length == 0)
 	 			{
 	 				UCMS.alert("삭제할 데이터가 존재하지 않습니다.");
 	 				return;
 	 			}
	 			for(var i in reqData)
	 			{
	 				if( reqData[i].wmsYn == "Y" )
	 				{
	 					UCMS.confirm("이미 물류입고 처리된 실적이 존재합니다. 삭제하시겠습니까?", {confirm:"삭제", cancel:"취소"})
	 					.then(function()
	 					{
	 						doing();
	 					});
	 					return;
	 				}
	 			}

	 			doing();
 			}
 			else
 			{
 				doing();
 			}
 		}
 		,
 		onDelete: function()
		{
 			// 부모의 삭제 동작은 무시하고, onButtonHaldler() 의 삭제 동작을 적용한다.
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
