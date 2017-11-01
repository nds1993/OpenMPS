/**
 * Project : OpenMPS MIS
 *
 * 생산 > 제품코드 등록
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
			var gridOptions = options.resultBox.options.gridParams;
			_.extend
			(
				gridOptions,
				this.makeAutoScrollingOptions2_local(50)
			);
			Renderer.__super__.constructor.call(this, options);
		}
		,
		initialize: function(options)
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "PP0103";
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
					{"query":"조회"},
					{"create":"신규"},
					{"save":"저장"},
				//	{"download":"엑셀"},
					{"delete":"삭제"},
					{"cancel":"취소"}
				];

				RendererBase.Method.initSubHeader.call( self, headerParams );
			});
		}
		,
		getItemList: function()
		{
			return [
			        "queryBox",
			        "formBox",
			        "resultBox"
			        ];
		}
		,
		onInitRendererItemEvents: function()
		{
			var self = this;

			UCMS.retry(function()
			{
				if( self.$el.find(".animalKind select option").length == 0
					||
					self.$el.find(".proKind select option").length == 0
					||
					self.$el.find(".prdtType select option").length == 0
					)
				{
					return false;
				}

				self.attachFormItem("queryBox");

				//
				var formItem = self.attachFormItem("formBox");
				formItem.getItem().on(FormBox.EVENT.CHANGE, function(item)
				{
					self.onChangeFormData( gridItem, formItem );
				});
				formItem.getItem().disabled(true );

				//
				var gridItem = self.attachGridItem("resultBox");
				gridItem.getItem().on(JQGrid.EVENT.SELECT, function(item)
				{
					self.onSelectRow( item );
				});
				gridItem.getItem()
				.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
				{
					self.downloadExcel("PP0103_grid_1","제품코드등록",false);
				});
			});
		}
		,
		getQueryData: function()
		{
			var data = this.getRendererItem("queryBox").getItemData();
			if( data["proCode"] && data["proCode"].result )
			{
				data["proCode"] = data["proCode"].result;
			}
			//
			data = _.pick(data, "animalKind", "proKind", "prdtType", "proCode");

			if( data.animalKind == 0 )
			{
				delete data.animalKind;
			}
			if( data.proKind == 0 )
			{
				delete data.proKind;
			}
			if( data.prdtType == 0 )
			{
				delete data.prdtType;
			}

			return data;
		}
		,
		onSelectRow: function(selected)
		{
			var formItem = this.getActiveForm();
			if( formItem )
			{
				var formData = this.transformGrid2Form(selected.data);
				formItem.setData( formData, true );
				formItem.getItem().disabled(false);
				this.initFormItemState( formData.animalKind || Renderer.CONSTS.PIG );
				this.initFormItemProKindState( formData.tabArea.tab_baseInfo.proKind || Renderer.CONSTS.PRODUCT );
			}
		}
		,
		transformGrid2Form: function(gridData)
		{
			var formData =
			{
				"animalKind": gridData.animalKind,
				"proCode": gridData.proCode,
				"proName": gridData.proName,
				"proName1": gridData.proName1,
				"frozenType": gridData.frozenType,
				"setYn": gridData.setYn,
				"shortCode": gridData.shortCode,
				"largeCode": {
					"keyword": gridData.largeCodeName,
					"result": gridData.largeCode
				},
				"saleGroup1": {
					"keyword": gridData.saleGroup1Name,
					"result": gridData.saleGroup1
				},
				"saleGroup2": {
					"keyword": gridData.saleGroup2Name,
					"result": gridData.saleGroup2
				},
				"tabArea": {
					"tab_baseInfo": {
						"prdtType": gridData.prdtType,
						"proKind": gridData.proKind,
						"meatType": gridData.meatType,
						"code88": {
							"keyword": gridData.code88,
							"result": gridData.code88Id
						},
						"brandName": {
							"keyword": gridData.brandName,
							"result": gridData.brandCode
						},
						"stdCode": {
							"keyword": gridData.stdName,
							"result": gridData.stdCode
						},
						"shelfLife": gridData.shelfLife,
						"rTemperature": gridData.rTemperature,
						"proUkind": gridData.proUkind,
						"proIpsu": gridData.proIpsu,
						"proWeig": gridData.proWeig,
						"priceCalc": gridData.priceCalc,
						"vatCode": gridData.vatCode,
						"weigType": gridData.weigType,
						"memo": gridData.memo
					},
					"tab_planInfo": {
						"ipsuCnt": gridData.ipsuCnt,
						"workType1": gridData.workType1,
						"workType2": gridData.workType2,
						"workLine": gridData.workLine,
						"printSheet": gridData.printSheet
					},
					"tab_proInfo": {
						"pmType": gridData.pmType,
						"cmRptType": gridData.cmRptType,
						"safetyQty": {
							"safetyStock10": gridData.safetyStock10,
							"safetyStock13": gridData.safetyStock13,
							"safetyStock14": gridData.safetyStock14,
							"safetyStock21": gridData.safetyStock21,
							"safetyStock22": gridData.safetyStock22
							//"safetyStock31": gridData.safetyStock31,
							//"safetyStock99": gridData.safetyStock99
						}
					},
					"tab_custInfo": {
						"emartBar1": gridData.emartBar1,
						"emartBar2": gridData.emartBar2,
						"emartBar31": gridData.emartBar31,
						"emartBar32": gridData.emartBar32,
						"lotteCode": gridData.lotteCode
					}
				}
			};

			return formData;
		}
		,
		onChangeFormData: function(gridItem, formItem)
		{
			var formData = formItem.getData();
			// 필수값이 입력되지 않은 경우에 대한 처리 보강
			var animalKind = formData
							? formData.animalKind
							: formItem.getItem().getWidget$Element().find(".animalKind select option:selected").val();
			this.initFormItemState( animalKind );
			
			var proKind = formData
			? formData.tabArea.tab_baseInfo.proKind
			: formItem.getItem().getWidget$Element().find(".proKind select option:selected").val();
			
			this.initFormItemProKindState( proKind );
			
			if(! formData )
			{
				// 필수 정보가 입력되지 않은 경우
				return;
			}
			
			var rowId = gridItem.getItem().getSelRowId();
			var gridData = gridItem.getItem().getRowData(rowId);
			Logger.debug("changed row : "+rowId);
			Logger.debug(formData);

			// 폼에는 그리드의 모든 필드 정보가 노출되지 않을 수도 있다.
			// TODO 그리드의 필드에 변경된 폼의 정보를 부가하는 방식을 사용해야, 트랜잭션 데이타가 누락되는 필드없이 생성된다.
			gridItem.setRow(rowId, _.extend(gridData, this.transformForm2Grid(formData)));
			
			this.beginTransaction();
		}
		,
		transformForm2Grid: function(formData)
		{
			var gridData =
			{
				"animalKind": formData.animalKind,
				"proCode": formData.proCode,
				"proName": formData.proName,
				"proName1": formData.proName1,
				"setYn": formData.setYn,
				"shortCode": formData.shortCode,
				"largeCodeName": formData.largeCode.keyword,
				"largeCode": formData.largeCode.result,
				"frozenType": formData.frozenType,
				"saleGroup1Name": formData.saleGroup1.keyword,
				"saleGroup1": formData.saleGroup1.result,
				"saleGroup2Name": formData.saleGroup2.keyword,
				"saleGroup2": formData.saleGroup2.result
			};

			gridData.animalKindName = this.findComboLabel("animalKind", formData.animalKind);
			gridData.animalKindName || ( gridData.animalKind = null );
			gridData.frozenTypeName = this.findComboLabel("frozenType", formData.frozenType);
			gridData.frozenTypeName || ( gridData.frozenType = null );

			_.extend(gridData
					, formData.tabArea.tab_baseInfo
					, formData.tabArea.tab_custInfo
					);

			//
			// tab_baseInfo
			//
			gridData.prdtType = formData.tabArea.tab_baseInfo.prdtType;
			gridData.prdtTypeName = this.findComboLabel("prdtType", gridData.prdtType);
			gridData.prdtTypeName || ( gridData.prdtType = null );
			gridData.proKind = formData.tabArea.tab_baseInfo.proKind;
			gridData.proKindName = this.findComboLabel("proKind", gridData.proKind);
			gridData.proKindName || ( gridData.proKind = null );
			gridData.meatType = formData.tabArea.tab_baseInfo.meatType;
			gridData.meatTypeName = this.findComboLabel("meatType", gridData.meatType);
			gridData.meatTypeName || ( gridData.meatType = null );
			gridData.shelfLife = formData.tabArea.tab_baseInfo.shelfLife;
			gridData.shelfLifeName = this.findComboLabel("shelfLife", gridData.shelfLife);
			gridData.shelfLifeName || ( gridData.shelfLife = null );
			gridData.rTemperature = formData.tabArea.tab_baseInfo.rTemperature;
			gridData.proUkind = formData.tabArea.tab_baseInfo.proUkind;
			gridData.proUkind = this.findComboLabel("proUkind", gridData.proUkind);
			gridData.proUkind || ( gridData.proUkind = null );
			gridData.priceCalc = formData.tabArea.tab_baseInfo.priceCalc;
			gridData.priceCalcName = this.findComboLabel("priceCalc", gridData.priceCalc);
			gridData.priceCalcName || ( gridData.priceCalc = null );
			gridData.vatCode = formData.tabArea.tab_baseInfo.vatCode;
			gridData.vatCodeName = this.findComboLabel("vatCode", gridData.vatCode);
			gridData.vatCodeName || ( gridData.vatCode = null );
			
			if(gridData.proKind == 1)
			{
				gridData.weigType = formData.tabArea.tab_baseInfo.weigType;
				gridData.weigTypeName = this.findComboLabel("weigType", gridData.weigType);
				gridData.weigTypeName || ( gridData.weigType = null );
			}
			else
			{
				gridData.weigType = "";
				gridData.weigTypeName = "";
			}
			
			
			// code88
			gridData.code88Id = formData.tabArea.tab_baseInfo.code88.result;
			gridData.code88 = formData.tabArea.tab_baseInfo.code88.keyword;
			if( formData.tabArea.tab_baseInfo.code88.raw )
			{
				gridData.code88Etc01 = formData.tabArea.tab_baseInfo.code88.raw.etc01;
			}
			// brandName
			gridData.brandName = formData.tabArea.tab_baseInfo.brandName.keyword;
			gridData.brandCode = formData.tabArea.tab_baseInfo.brandName.result;
			// stdCode
			gridData.stdName = formData.tabArea.tab_baseInfo.stdCode.keyword;
			gridData.stdCode = formData.tabArea.tab_baseInfo.stdCode.result;

			//
			// tab_planInfo
			//
			gridData.ipsuCnt = formData.tabArea.tab_planInfo.ipsuCnt;
			gridData.workType1 = formData.tabArea.tab_planInfo.workType1;
			gridData.workType1Name = this.findComboLabel("workType1", gridData.workType1);
			gridData.workType1Name || ( gridData.workType1 = null );
			gridData.workType2 = formData.tabArea.tab_planInfo.workType2;
			gridData.workType2Name = this.findComboLabel("workType2", gridData.workType2);
			gridData.workType2Name || ( gridData.workType2 = null );
			gridData.workLine = formData.tabArea.tab_planInfo.workLine;
			gridData.workLineName = this.findComboLabel("workLine", gridData.workLine);
			gridData.workLineName || ( gridData.workLine = null );
			gridData.printSheet = formData.tabArea.tab_planInfo.printSheet;
			gridData.printSheetName = this.findComboLabel("printSheet", gridData.printSheet);
			gridData.printSheetName || ( gridData.printSheet = null );

			//
			// tab_proInfo
			//
			gridData.pmType = formData.tabArea.tab_proInfo.pmType;
			gridData.cmRptType = formData.tabArea.tab_proInfo.cmRptType;
			gridData.cmRptTypeName = this.findComboLabel("cmRptType", gridData.cmRptType);
			gridData.cmRptTypeName || ( gridData.cmRptType = null );
			gridData.safetyStock10 = formData.tabArea.tab_proInfo.safetyQty.safetyStock10;
			gridData.safetyStock13 = formData.tabArea.tab_proInfo.safetyQty.safetyStock13;
			gridData.safetyStock14 = formData.tabArea.tab_proInfo.safetyQty.safetyStock14;
			gridData.safetyStock21 = formData.tabArea.tab_proInfo.safetyQty.safetyStock21;
			gridData.safetyStock22 = formData.tabArea.tab_proInfo.safetyQty.safetyStock22;
			//gridData.safetyStock31 = formData.tabArea.tab_proInfo.safetyQty.safetyStock31;
			//gridData.safetyStock99 = formData.tabArea.tab_proInfo.safetyQty.safetyStock99;

			return gridData;
		}
		,
		findComboLabel: function(name, code)
		{
			if( code == "0" )
			{
				return null;
			}
			return this.$el.find("."+name+" select option[value="+code+"]:first").html();
		}
		,
		initFormItemState: function(animalKind, areaBox)
		{
			var disabled = (animalKind != Renderer.CONSTS.PIG && animalKind != Renderer.CONSTS.COW);
			var fieldList = ["prdtType", "meatType", "code88", "brandName", "stdCode", "largeCode", "saleGroup1", "saleGroup2", "frozenType", "setYn", "shortCode", "rTemperature"];

			// 기본정보
			areaBox = this.getItemInstance("formBox");
			for( var i in fieldList )
			{
				if( areaBox.getParam( fieldList[i] ) )
				{
					areaBox._getWidgetInstance( fieldList[i] ).disabled( disabled );
				}
				else
				{
					var params = areaBox.getParam( "custom_form" ).items[fieldList[i]];
					if( params )
					{
						params.disable = disabled;
						if( disabled )
						{
							params.value = "";
						}
						areaBox.setItemParams( fieldList[i], params );
					}
				}
			}
			
			// 탭 내부 정보
			areaBox = this.getItemInstance("formBox.tabArea");
			var baseInfo = areaBox._getWidgetInstance("tab_baseInfo");
			for( var i in fieldList )
			{
				if( baseInfo.getParam( fieldList[i] ) )
				{
					baseInfo._getWidgetInstance( fieldList[i] ).disabled( disabled );
				}
				else
				{
					var params = baseInfo.getParam( "custom_form" ).items[fieldList[i]];
					if( params )
					{
						params.disable = disabled;
						if( disabled )
						{
							params.value = "";
						}
						baseInfo.setItemParams( fieldList[i], params );
					}
				}
			}
		}
		,
		initFormItemProKindState: function(proKind, areaBox)
		{
			var disabled = (proKind == Renderer.CONSTS.SALEPROD );
			var fieldList = ["weigType"];

			// 탭 내부 정보
			areaBox = this.getItemInstance("formBox.tabArea");
			var baseInfo = areaBox._getWidgetInstance("tab_baseInfo");
			for( var i in fieldList )
			{
				if( baseInfo.getParam( fieldList[i] ) )
				{
					baseInfo._getWidgetInstance( fieldList[i] ).disabled( disabled );
				}
				else
				{
					var params = baseInfo.getParam( "custom_form" ).items[fieldList[i]];
					if( params )
					{
						params.disable = disabled;
						if( disabled )
						{
							params.value = "";
						}
						baseInfo.setItemParams( fieldList[i], params );
					}
				}
			}
		}
		,
		onSave: function()
		{
			var self = this;
			this.saveTransaction()
			.then(function(res)
			{
				if( !res || !res.extraData || !res.extraData.result )
				{
					return;
				}
				// 변경된 정보 출력을 위해 재조회
				self.onQuery(null, {noti:false});
			});
		}
		,
		/**
		 * Task commit 시 데이타의 유효성을 검증한다.
		 * 기본적으로 true 를 반환하며, 검증이 필요한 랜더러에서 상속 받아서 제공되는 Task 정보의 유효성 검증 결과를 boolean 값으로 반환처리한다.
		 * @param {string} gridId - commit 이 발생된 그리드의 식별자
		 * @param {object} taskItem - 트랜잭션 항목 객체
		 * @return {boolean|string} false 를 리턴하면, 해당 commit 동작은 reject 되며, 문제가 된 task 항목에 대한 정보 { id, task } 가 반환된다.
		 */
		isValidRowChecker: function(gridId, taskItem)
		{
			if( gridId == "resultBox" )
			{
				if( !taskItem.proName )
				{
					return "\"제품명\"을 입력하여 주십시요.";
				}
				if( taskItem.emartBar1 && typeof taskItem.emartBar1 == "string" && taskItem.emartBar1.length != 6 )
				{
					return "\"TC매입코드\"는 6자리로 입력해야 합니다.";
				}
				if( taskItem.emartBar2 && typeof taskItem.emartBar2 == "string" && taskItem.emartBar2.length != 6 )
				{
					return "\"PC매입코드\"는 6자리로 입력해야 합니다.";
				}
				if( taskItem.emartBar31 && typeof taskItem.emartBar31 == "string" && taskItem.emartBar31.length != 6 )
				{
					return "\"PC출하1\"는 6자리로 입력해야 합니다.";
				}
				if( taskItem.emartBar32 && typeof taskItem.emartBar32 == "string" && taskItem.emartBar32.length != 6 )
				{
					return "\"PC출하2\"는 6자리로 입력해야 합니다.";
				}
				if( taskItem.lotteCode && typeof taskItem.lotteCode == "string" && taskItem.lotteCode.length != 10 )
				{
					return "\"롯데코드(LS)\"는 10자리로 입력해야 합니다.";
				}				
				if( taskItem.animalKind != Renderer.CONSTS.ETCS )
				{
					if( !taskItem.frozenType )
					{
						return "\"냉장/냉동구분\"을 선택해 주십시요.";
					}
					if( !taskItem.largeCode )
					{
						return "\"생산품목구분\"을 선택해 주십시요.";
					}
					if( !taskItem.prdtType )
					{
						return "\"가공구분\"을 선택해 주십시요.";
					}
					if( !taskItem.proKind )
					{
						return "\"재/상품구분\"을 선택해 주십시요.";
					}
					if( !taskItem.meatType )
					{
						return "\"정육구분\"을 선택해 주십시요.";
					}
					if( !taskItem.proUkind )
					{
						return "\"수량단위\"를 선택해 주십시요.";
					}
					if( !taskItem.priceCalc )
					{
						return "\"단가적용\"을 선택해 주십시요.";
					}
					if( !taskItem.vatCode )
					{
						return "\"부가세코드\"를 선택해 주십시요.";
					}
				}
				else
				{
					if( !taskItem.proKind )
					{
						return "\"재/상품구분\"을 선택해 주십시요.";
					}
					if( !taskItem.proUkind )
					{
						return "\"수량단위\"를 선택해 주십시요.";
					}
				}
				
				if( taskItem.proKind == Renderer.CONSTS.PRODUCT )
				{
					if( !taskItem.weigType )
					{
						return "\"중량표기형태\"를 선택해 주십시요.";
					}
				}
				
				return true;
			}
			return true;
		}
		,
		initExpireDate: function(tabArea, frozenType)
		{
			var baseInfo = tabArea._getWidgetInstance("tab_baseInfo");
			var params = baseInfo.getParam( "custom_form" ).items["shelfLife"];

			switch(frozenType)
			{
			case "0": // 냉장
				params.items = [
				                { label: "10일", value: "1" },
				                { label: "20일", value: "2" },
				                { label: "30일", value: "3" },
				                { label: "45일", value: "4" }
				];
				break;

			case "1": // 냉동
				break;

			case "2": // 냉동전환
				break;
			}
			baseInfo.setItemParams( "shelfLife", params );
		}
		,
		onActiveTab: function(params)
		{
			if( params.active == "tab_planInfo" 
				&& 
				this.$el.find(".tabarea_box .prdtType select option:checked").val() != 1 )
			{
				UCMS.alert("가공구분이 \"부분육(CM)\"만 선택 가능합니다.");
				this.getItemInstance("formBox.tabArea").activeTab( "tab_baseInfo" );
			}
			
			else if( params.active == "tab_custInfo" 
				&& 
				this.$el.find(".formBox_region .animalKind select option:checked").val() == Renderer.CONSTS.ETCS )
			{
				UCMS.alert("축종구분이 \"돼지\", \"소\"만 선택 가능합니다.");
				this.getItemInstance("formBox.tabArea").activeTab( "tab_baseInfo" );
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
			this._newRowId = this.getActiveGrid().createRow({"proIpsu":1}, "last");
			if( this.getActiveForm() )
			{
				this.getActiveForm().getItem().disabled(false);
			}
			this.beginTransaction();
			this.getItemInstance("formBox.tabArea").activeTab( "tab_baseInfo" );
		}
		,
		onQuery: function()
		{
			Renderer.__super__.onQuery.apply( this, arguments )
			this.getItemInstance("formBox.tabArea").activeTab( "tab_baseInfo" );
		}
		,
		onSearchCode: function(options)
		{
			if( options.params.groupCode == "MP020" )
			{
				// 브랜드 코드
				// 가공구분 코드값을 파라메터로 추가함
				options.params.etc02 = this.$el.find(".tabarea_box .prdtType select option:checked").val();
			}
			else if( options.params.etc02 )
			{
				delete options.params.etc02;
			}
			
			Renderer.__super__.onSearchCode.call( this, options );
		}
		,
		clearForm: function(formId)
		{
			var form = this.getActiveForm(formId);
			if( form )
			{
				form.clear();
				this.getItemInstance("formBox.tabArea.tab_baseInfo").clear();
				this.getItemInstance("formBox.tabArea.tab_proInfo").clear();
				this.getItemInstance("formBox.tabArea.tab_custInfo").clear();
				this.getItemInstance("formBox.tabArea.tab_planInfo").clear();
				form.getItem().disabled(true);
			}
		}
	}
	,
	{
		EVENT:
		{
		}
		,
		CONSTS:
		{
			PIG: "1",
			COW: "5",
			ETCS: "9",
			
			PRODUCT: "1",	//제품
			SALEPROD: "2"	//상품
		}
	});

	return Renderer;
});
