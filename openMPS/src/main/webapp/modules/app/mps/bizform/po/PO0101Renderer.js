/**
 * Project : OpenMPS MIS
 *
 * 구매 > 지육시세 등록
 *
 * 데이타를 담는 FormBox 의 식별자는 "formBox" 을 사용한다.
 * 데이타를 담는 JQGrid 의 식별자는 "resultBox" 을 사용한다.
 */

define([
	"Logger",
	"NDSProps",
	"RendererBase",
	"BaroBox",
	"FormBox",
	"WorkAreaHeader",
	"manifest!jqGrid4-1.0.0#widget",
	"moment"
], function(Logger, NDSProps, RendererBase, BaroBox, FormBox, WorkAreaHeader, JQGrid, moment)
{
	var OverridingMethod =
	{
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			RendererBase.Method.initMethod.apply( this, arguments );

			//
			this._contentId = "PO0101";
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
					{"print":"인쇄"},
					{"download":"엑셀"},
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
			        "customBtnBox",
			        "customBtnBox.btnLoad",
			        "customBtnBox.exUpload",
			        "tabAreaBox",
			        "tabAreaBox.tab_skin",
			        "tabAreaBox.tab_skin.formBox",
			        "tabAreaBox.tab_skin.resultBox",
			        "tabAreaBox.tab_nonskin",
			        "tabAreaBox.tab_nonskin.formBox",
			        "tabAreaBox.tab_nonskin.resultBox"
			        ];
		}
		,
		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
		getHeaderGridName: function()
		{
			return this._activeTabId+".resultBox";
		}
		,
		/**
		 * 4. 랜더러 아이템 관계성 초기화
		 */
		onInitRendererItemEvents: function()
		{
			var self = this;

			//
			this.attachHeaderItem("headerBox");
			this.attachFormItem("queryBox");
			//
			// 박피 skin
			//
			var skinGrid = this.attachGridItem("tabAreaBox.tab_skin.resultBox");
			skinGrid.getItem().on(JQGrid.EVENT.BEFORESELECT, function(item)
			{
				var data = skinGrid.getItem().getRowData(item.id);
				skinForm.setData( data );
				skinForm.getItem().disabled(false);
			});
			skinGrid.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PO0101_grid_2","지육시세등록_박피",false);
			});
			var skinForm = this.attachFormItem("tabAreaBox.tab_skin.formBox");
			skinForm.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				self.onChangeFormData( skinGrid, skinForm );
			});
			skinForm.getItem().disabled(true);
			//
			// 탕박 nonskin
			//
			var nonskinGrid = this.attachGridItem("tabAreaBox.tab_nonskin.resultBox");
			nonskinGrid.getItem().on(JQGrid.EVENT.BEFORESELECT, function(item)
			{
				var data = nonskinGrid.getItem().getRowData(item.id);
				nonskinForm.setData( data );
				nonskinForm.getItem().disabled(false);
			});
			nonskinGrid.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PO0101_grid_1","지육시세등록_탕박",false);
			});;
			var nonskinForm = this.attachFormItem("tabAreaBox.tab_nonskin.formBox");
			nonskinForm.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				self.onChangeFormData( nonskinGrid, nonskinForm );
			});
			nonskinForm.getItem().disabled(true);

			//
			this.initCustomBox();
		}
	};

	//
	// 5. 이벤트 처리
	// 초기화 완료된 아이템은 getRendererItem() 로 직접 아이템 내부 객체에 접근 가능하다.
	//
	var WorkAreaMethod =
	{
		getQueryData: function()
		{
			return RendererBase.Method.getQueryData.call( this,
			{
				"skinType" : this._activeTabId.replace("tabAreaBox.tab_","")
			});
		}
		,
		getActiveGrid: function(tabId)
		{
			tabId || (tabId = this._activeTabId);
			return this.attachGridItem(tabId+".resultBox");
		}
		,
		getActiveForm: function(tabId)
		{
			tabId || (tabId = this._activeTabId);
			return this.attachFormItem(tabId+".formBox");
		}
		,
		onActiveTab: function(params)
		{
			Logger.info("Active Tab ID : "+params.active);

			if( this.isTransactionMode() == true )
			{
				var self = this;
				var prevTabId = this._activeTabId;

				UCMS.confirm("진행 중인 작업을 저장할까요?", { confirm: "예", cancel: "아니요" })
				.done(function()
				{
					self.onSave(prevTabId);
				})
				.fail(function()
				{
					self.onCancel(prevTabId);
				});
			}

			this._activeTabId = "tabAreaBox."+params.active;
		}
		,
		onQuery: function(featureId, options)
		{
			var self = this;
			var fetching = function()
			{
				var params = self.getQueryData();
				var gridItem = self.getActiveGrid();
				var strtDay = moment(params.strtDate);
				var lastDay = moment(params.lastDate);

			    if( strtDay > lastDay )
			    {
			        UCMS.alert("조회 기간이 잘못 지정되었습니다.");
			        return;
			    }
			    if( featureId == "quotation" && strtDay.add(7, "day") < lastDay )
			    {
			    	UCMS.alert("1 주일 내로 지정해 주세요.");
			        return;
			    }

				options || (options = {});

				self.clearForm();
				self.showLoading();
				gridItem.fetch( params, featureId )
				.then(function(res)
				{
					if( res.resultCode == 0 )
					{
						if( options.noti != true && res.extraData.result.length == 0 )
						{
							UCMS.alert("조회된 데이타가 없습니다.");
						}
						else if( featureId == "quotation" )
						{
							// I/F 조회된 내용을 신규 모드로 추가
							gridItem.clear();
							gridItem.createRow( res.extraData.result );
							self.beginTransaction( false );
						}
						else
						{
							// 조회된 내용 적용
							gridItem.setData(res.extraData.result);
						}
					}
					else
					{
						UCMS.reportError(res,"데이타 조회를 실패하였습니다.");	
					}
				})
				.fail(function(e)
				{
					Logger.error(e);
					UCMS.reportError(e,"데이타 조회를 실패하였습니다.");
				})
				.always(function()
				{
					self.hideLoading();
				});

			};
			Logger.info("Renderer.onQuery");

			this.clearForm();
			if( this.isTransactionMode() == true )
			{
				UCMS.confirm("진행 중인 작업을 저장할까요?", { confirm: "예", cancel: "아니요" })
				.done(function()
				{
					self.onSave(self._activeTabId);
					fetching();
				})
				.fail(function()
				{
					self.onCancel(self._activeTabId);
					fetching();
				});
			}
			else
			{
				fetching();
			}
		}
		,
		/**
		 * 지정한 탭의 트랜잭션을 취소한다.
		 */
		onCancel: function(tabId)
		{
			//tabId || (tabId = this._activeTabId);
			//this.endTransaction(true, tabId+".resultBox");
			//this.getActiveForm(tabId).setData({});
			RendererBase.Method.onCancel.call( this );
		}
		,
		initCustomBox: function()
		{
			var self = this;
			var box = this.attachFormItem("customBtnBox").getItem();

			//
			// ButtonMulti-1.0.0
			//
			var item = box.getItemElement("btnLoad").getButton("quotation");
			if( item )
			{
				item.click(function()
				{
					self.onQuery("quotation");
				});
			}
			else
			{
				Logger.warn("initCustomBox() - Invalid item : btnLoad");
			}

			//
			// FormBox
			//
			item = box.getItemElement("exUpload").getItemElement("exFileUpload");
			if( item )
			{
				item.change(function(item)
				{
					self.clearForm();
					RendererBase.Method.onChangeUploadFile.call( self, this, item );
				});
			}
			else
			{
				Logger.warn("initCustomBox() - Invalid item : exFileUpload");
			}

			//
			// Data 형식 제약
			//
			this.$el.find("input.strtDate")
			.change(function()
			{
				if( moment($(this).val(), ["YYYY-MM-DD"]) == false )
				{
					UCMS.alert("바른 날짜 형식으로 입력해 주세요.");
				}
			});
		}
		,
		onSave: function()
		{
			var self = this;
			this.saveTransaction()
			.done(function()
			{
				self.onQuery();
			});
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
