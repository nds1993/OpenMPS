/**
 * Project : OpenMPS MIS
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
(
[
	"Logger", "NDSProps", "BaroPanelBase", "BaroBox", "manifest!FavHeader-1.0.0#widget", "manifest!Menu-1.0.0#widget", "APIClient", "RendererBase",
	"SPA.Platform.Helper", "mps.helper"
]
,
function( Logger, NDSProps, BaroPanelBase, BaroBox, FavHeader, Menu, APIClient, RendererBase)
{
	/**
	 * 다양한 콘텐츠를 Tab 으로 관리한다.
	 */
	var ContentManager = BaroBox.extend(
	{
		className: 'workarea_box',

		initialize: function(options)
		{
			options =
			{
				"activation":
				{
					"defaultWidget": "home",
					"method": "show-hide",
					"navigator":
					{
						"options": {
							"className" : "menu_tab",
							"pos": "bottom",
							"control": {
								"close": true,
								"exceptClose": ["home"],
								"scroll": true,
								"tabClose" : true
							}
						}
					}
				}
			};

			ContentManager.__super__.initialize.apply( this, arguments );
        }
		,
		onRender: function()
		{
			//this.$el.attr('style', 'height:100%;');
		}
		,
		/**
         * 메뉴 목록에서 콘텐츠 페이지의 레이블 목록을 생성한다.
         */
        setContentsLabel: function(menuParams)
		{
	        var getItemLabel = function(itemId, item)
	        {
	        	var labels = {};
	        	if( item.order && item.order.length >= 0 )
	        	{
	        		// submenu
	        		for(var i in item.order)
	        		{
	        			_.extend(labels, getItemLabel( item.order[i], menuParams[ item.order[i] ]));
	        		}
	        	}
	        	else if( item.cmd )
	        	{
	        		labels[ itemId ] = item.label;
	        	}

	        	return labels;
	        };
	        this._tabLabels = getItemLabel( menuParams.defaultMenu, menuParams[ menuParams.defaultMenu ] );
	        this._tabLabels["home"] = "MAIN";

	        var self = this;
	        UCMS.retry(function()
	        {
	        	if( self._isTabMode() == false )
		        {
	        		return false;
		        }

	        	self._tab.setTabLabels( self._tabLabels );
	        })
	        .fail(function()
	        {
	        	Logger.error("setContentsLabel() - Invalid tab object!");
	        });
		}
		,
		/**
		 * 콘텐츠 높이를 설정한다.
		 * resize 이벤트가 발생될 때 윈도우 높이에 콘텐츠 높이를 맞추기 위해 호출한다.
		 */
		setBoxHeight: function(boxHeight, nRetryCnt)
		{
			Logger.debug("setBoxHeight() - boxHeight : "+boxHeight);
			nRetryCnt || (nRetryCnt = 0);

			var self = this;
			var retry = function()
			{
				if( nRetryCnt > 4)
				{
					return;
				}

				setTimeout(function()
				{
					++nRetryCnt;
					self.setBoxHeight( boxHeight, nRetryCnt );
				}
				, 500);
			}

			if( this._tab == null )
			{
				retry();
				return;
			}
			var activeContentId = this._tab.getActiveTabId();
			if( activeContentId == null )
			{
				retry();
				return;
			}
			var box = this._getWidgetInstance(activeContentId);
			if( box == null )
			{
				retry();
				return;
			}
			if( box instanceof BaroBox == false || box._getWidgetInstance("headerBox") == null )
			{
				retry();
				return;
			}
			if( box._getWidgetInstance("headerBox") == null )
			{
				retry();
				return;
			}
			var header$el = box._getWidgetInstance("headerBox").getWidget$Element();
			if( box._getWidgetInstance("queryBox") == null )
			{
				retry();
				return;
			}
			var query$el = box._getWidgetInstance("queryBox").getWidget$Element();

			var form$el = null;
			if( box._getWidgetInstance("formBox") != null )
			{
				form$el = box._getWidgetInstance("formBox").getWidget$Element();
			}
			if( box._getWidgetInstance("resultBox") == null )
			{
				retry();
				return;
			}
			var result$el = box._getWidgetInstance("resultBox").getWidget$Element();
			var height =
			{
				header: query$el.offset().top - header$el.offset().top,
				query: (form$el!=null ? (form$el.offset().top > result$el.offset().top ? result$el.offset().top : form$el.offset().top) : result$el.offset().top) - query$el.offset().top,
				form: (form$el!=null ? (form$el.offset().top > result$el.offset().top ? form$el.outerHeight(true) : result$el.offset().top - form$el.offset().top) : 0),
				tab: this._tab.getWidget$Element().height(),
				gridHeader: result$el.find(".ui-jqgrid-hdiv").height() + result$el.find(".ui-jqgrid-pager").height()
			};
			Logger.debug("setBoxHeight() - height : "+JSON.stringify(height));
			var $gridListArea = result$el.find(".ui-jqgrid-bdiv");
			var gridListHeight = boxHeight - height.header - height.query - height.form - height.gridHeader - height.tab - 23;
			if( gridListHeight > 0 )
			{
				Logger.debug("setBoxHeight() - grid list height : "+gridListHeight);
				$gridListArea.height( gridListHeight );
			}
		}
	});

	/**
	 * 메인 페이지를 구현한다.
	 */
	var MainBox = BaroPanelBase.extend(
	{
		template: "#mainframe_html",
		className: 'mainframe_box',

		ui:
		{
			menu_slide_box : ".menu_slide_box",
			btn_go_menuslide : ".btn_go_menuslide"
//			btn_go_max : ".btn_go_max",
//			btn_go_min : ".btn_go_min"
		},
		events :
		{
			"click @ui.btn_go_menuslide": "onMenuSlide",
			"click @ui.btn_go_max": "onGoMax",
			"click @ui.btn_go_min": "onGoMin"
		},
		regions:
		{
			'header': '.headerarea_region',
			'menu': '.menuarea_region',
			'contents': '.workarea_region'
		},
		onMenuSlide: function(){
			UCMS.onMenuSlide()
		},
		initialize: function()
		{
			MainBox.__super__.initialize.call( this );
			this._area = {
				header: null,
				menu: null,
				manager: null
			};

			// 캘린더 언어세트 설정
			//moment.locale('ko');
			$.fn.datetimepicker.defaults.locale = "ko";
			
        },
        onRender: function()
        {
        	//this.$el.attr('style', 'height: 100%; overflow: hidden; position: relative;');
        	//this.$el.attr('style', 'height: 100%;');

        	this._initHeader();
        	this._initManager();
        	this._initMenu();
        },
        _initHeader: function()
        {
        	this._area.header = new FavHeader();
        	this.header.show( this._area.header );
        	this.$el.find(".header_menu_box span.user").html(NDSProps.get("user").name);
        },
        _initMenu: function()
        {
        	var self = this;
        	var setMenuParams = function()
        	{
        		return {
						  "old_main_menu": {
						    "label": "(구)메인메뉴",
						    "order": [
								"portal",
						      "MCOM",
						      "MLOG",
							  "MMAN",
							  "MSAL",
							  "TMCO",
							  "TM",
							  "TEST"
						    ]
						  },
						  "portal":{
							"label":"고객포탈",
							"order":[
								"SD1001"
							]
						  },
						  "SD1001":{
							  "label":"주문입력",
							   "cmd": "#box/SD1001"
						  },
						  "MCOM": {
						    "label": "관리",
						    "order": [
						      "MCOMAA10",
						      "MCOMAA20",
						      "MCOMAA30",
						      "MCOMAA40"
						    ]
						  },
						  "MCOMAA10": {
						    "label": "고객",
						    "order": [
						      "MCOMAA10_01",
						      "MCOMAA10_02",
						      "MCOMAA10_03"
						    ]
						  },
						  "MCOMAA10_01": {
						    "label": "여신관리",
						    "cmd": "#box/MCOMAA10_01"
						  },
						  "MCOMAA10_02": {
						    "label": "잔액조회서",
						    "cmd": "#box/MCOMAA10_02"
						  },
						  "MCOMAA10_03": {
						    "label": "한도이력관리",
						    "cmd": "#box/MCOMAA10_03"
						  },
						  "MCOMAA20": {
						    "label": "관리",
						    "order": [
						      "MCOMAA20_03",
						      "MCOMAA20_04",
						      "MCOMAA20_05",
						      "MCOMAA20_06",
						      "MCOMAA20_07",
						      "MCOMAA20_08",
						      "MCOMAA20_09",
						      "MCOMAA20_10"
						    ]
						  },
						  "MCOMAA20_03": {
						    "label": "거래처담당자변경",
						    "cmd": "#box/MCOMAA20_03"
						  },
						  "MCOMAA20_04": {
						    "label": "거래처등록",
						    "cmd": "#box/MCOMAA20_04"
						  },
						  "MCOMAA20_05": {
						    "label": "기타코드등록",
						    "cmd": "#box/MCOMAA20_05"
						  },
						  "MCOMAA20_06": {
						    "label": "목표관리",
						    "cmd": "#box/MCOMAA20_06"
						  },
						  "MCOMAA20_07": {
						    "label": "부서등록",
						    "cmd": "#box/MCOMAA20_07"
						  },
						  "MCOMAA20_08": {
						    "label": "사용자권한관리",
						    "cmd": "#box/MCOMAA20_08"
						  },
						  "MCOMAA20_09": {
						    "label": "사용자등록",
						    "cmd": "#box/MCOMAA20_09"
						  },
						  "MCOMAA20_10": {
						    "label": "세트등록",
						    "cmd": "#box/MCOMAA20_10"
						  },
						  "MCOMAA30": {
						    "label": "단가",
						    "order": [
						      "MCOMAA30_01",
						      "MCOMAA30_02",
						      "MCOMAA30_03"
						    ]
						  },
						  "MCOMAA30_01": {
						    "label": "단가조정현황",
						    "cmd": "#box/MCOMAA30_01"
						  },
						  "MCOMAA30_02": {
						    "label": "판매가격등록",
						    "cmd": "#box/MCOMAA30_02"
						  },
						  "MCOMAA30_03": {
						    "label": "가격승인",
						    "cmd": "#box/"
						  },
						  "MCOMAA40": {
						    "label": "분석",
						    "order": [
						      "MCOMAA40_01",
						      "MCOMAA40_02",
						      "MCOMAA40_03",
						      "MCOMAA40_04",
						      "MCOMAA40_05"
						    ]
						  },
						  "MCOMAA40_01": {
						    "label": "개인별목표대비실적분석",
						    "cmd": "#box/MCOMAA40_01"
						  },
						  "MCOMAA40_02": {
						    "label": "거래처별매출현황",
						    "cmd": "#box/MCOMAA40_02"
						  },
						  "MCOMAA40_03": {
						    "label": "거래처별목표대비실적분석",
						    "cmd": "#box/MCOMAA40_03"
						  },
						  "MCOMAA40_04": {
						    "label": "제품별매출실적",
						    "cmd": "#box/MCOMAA40_04"
						  },
						  "MCOMAA40_05": {
						    "label": "팀별목표대비실적분석",
						    "cmd": "#box/MCOMAA40_05"
						  },
						  "MLOG": {
						    "label": "물류",
						    "order": [
						      "MLOGAA10",
						      "MLOGAA20",
						      "MLOGAA30",
						      "MLOGAA40"
						    ]
						  },
						  "MLOGAA10": {
						    "label": "현황",
						    "order": [
						      "MLOGAA10_01",
						      "MLOGAA10_02",
						      "MLOGAA10_03",
						      "MLOGAA10_04"
						    ]
						  },
						  "MLOGAA10_01": {
						    "label": "매입장",
						    "cmd": "#box/MLOGAA10_01"
						  },
						  "MLOGAA10_02": {
						    "label": "매출장",
						    "cmd": "#box/MLOGAA10_02"
						  },
						  "MLOGAA10_03": {
						    "label": "일자별매출현황",
						    "cmd": "#box/MLOGAA10_03"
						  },
						  "MLOGAA10_04": {
						    "label": "주문현황",
						    "cmd": "#box/MLOGAA10_04"
						  },
						  "MLOGAA20": {
						    "label": "관리",
						    "order": [
						      "MLOGAA20_01",
						      "MLOGAA20_02",
						      "MLOGAA20_03",
						      "MLOGAA20_04"
						    ]
						  },
						  "MLOGAA20_01": {
						    "label": "거래내역마감",
						    "cmd": "#box/MLOGAA20_01"
						  },
						  "MLOGAA20_02": {
						    "label": "거래처조회",
						    "cmd": "#box/MLOGAA20_02"
						  },
						  "MLOGAA20_03": {
						    "label": "매입실적",
						    "cmd": "#box/MLOGAA20_03"
						  },
						  "MLOGAA20_04": {
						    "label": "생산계획서",
						    "cmd": "#box/MLOGAA20_04"
						  },
						  "MLOGAA30": {
						    "label": "분석",
						    "order": [
						      "MLOGAA30_01"
						    ]
						  },
						  "MLOGAA30_01": {
						    "label": "생산일자별재고현황(월단위)",
						    "cmd": "#box/MLOGAA30_01"
						  },
						  "MLOGAA40": {
						    "label": "주문",
						    "order": [
						      "MLOGAA40_01"
						    ]
						  },
							"MLOGAA40_01": {
						    "label": "이월입력,삭제,현황",
						    "cmd": "#box/MLOGAA40_01"
						  },

						  "MMAN": {
						    "label": "생산",
						    "order": [
						      "MMANAA10",
						      "MMANAA20",
						      "MMANAA30"
						    ]
						  },
						  "MMANAA10": {
						    "label": "관리",
						    "order": [
						      "MMANAA10_01",
						      "MMANAA10_02",
						      "MMANAA10_03",
						      "MMANAA10_04",
						      "MMANAA10_05",
						      "MMANAA10_06",
						      "MMANAA10_07",
						      "MMANAA10_08",
						      "MMANAA10_09",
						      "MMANAA10_10",
						      "MMANAA10_11"					    ]
						  },
						  "MMANAA10_01": {
						    "label": "LPC코드관리",
						    "cmd": "#box/MMANAA10_01"
						  },
						  "MMANAA10_02": {
						    "label": "PM오더업로드",
						    "cmd": "#box/MMANAA10_02"
						  },
						  "MMANAA10_03":  {
						    "label": "PM입고관리",
						    "cmd": "#box/MMANAA10_03"
						  },
						  "MMANAA10_04": {
						    "label": "공박스중량",
						    "cmd": "#box/MMANAA10_04"
						  },
						  "MMANAA10_05": {
						    "label": "생산(삭제이력)",
						    "cmd": "#box/MMANAA10_05"
						  },
						  "MMANAA10_06": {
						    "label": "생산수율표",
						    "cmd": "#box/MMANAA10_06"
						  },
						  "MMANAA10_07": {
						    "label": "속라벨상세내역",
						    "cmd": "#box/MMANAA10_07"
						  },
						  "MMANAA10_08": {
						    "label": "제품등록",
						    "cmd": "#box/MMANAA10_08"
						  },
						  "MMANAA10_09": {
						    "label": "출하정산집계표",
						    "cmd": "#box/MMANAA10_09"
						  },
						  "MMANAA10_10": {
						    "label": "클레임입력",
						    "cmd": "#box/MMANAA10_10"
						  },
						  "MMANAA10_11": {
						    "label": "품목등록",
						    "cmd": "#box/MMANAA10_11"
						  },
						  "MMANAA20": {
						    "label": "마스터",
						    "order": [
						      "MMANAA20_01",
						      "MMANAA20_02",
						      "MMANAA20_03",
						      "MMANAA20_04",
						      "MMANAA20_05",
						      "MMANAA20_06",
						      "MMANAA20_07",
						      "MMANAA20_08",
						      "MMANAA20_09",
						      "MMANAA20_10"
						    ]
						  },
						  "MMANAA20_01": {
						    "label": "BOM관리",
						    "cmd": "#box/MMANAA20_01"
						  },
						  "MMANAA20_02": {
						    "label": "등판자료",
						    "cmd": "#box/MMANAA20_02"
						  },
						  "MMANAA20_03": {
						    "label": "생돈구매세부입력",
						    "cmd": "#box/MMANAA20_03"
						  },
						  "MMANAA20_04": {
						    "label": "생돈정산eKAPEpia연동",
						    "cmd": "#box/MMANAA20_04"
						  },
						  "MMANAA20_05": {
						    "label": "생돈정산",
						    "cmd": "#box/MMANAA20_05"
						  },
						  "MMANAA20_06": {
						    "label": "수율기초입력",
						    "cmd": "#box/MMANAA20_06"
						  },
						  "MMANAA20_07": {
						    "label": "이력시스템연계(매입)",
						    "cmd": "#box/MMANAA20_07"
						  },
						  "MMANAA20_08": {
						    "label": "이력시스템연계(매출)",
						    "cmd": "#box/MMANAA20_08"
						  },
						  "MMANAA20_09": {
						    "label": "이력시스템연계(포장)",
						    "cmd": "#box/MMANAA20_09"
						  },
						  "MMANAA20_10": {
						    "label": "지육시세업로드",
						    "cmd": "#box/MMANAA20_10"
						  },
						  "MMANAA30": {
						    "label": "분석",
						    "order": [
						      "MMANAA30_01",
						      "MMANAA30_02",
						      "MMANAA30_03"
						    ]
						  },
						  "MMANAA30_01": {
						    "label": "부자재소요량산출 및 발주",
						    "cmd": "#box/MMANAA30_01"
						  },
						  "MMANAA30_02": {
						    "label": "생산입고현황",
						    "cmd": "#box/MMANAA30_02"
						  },
						  "MMANAA30_03": {
						    "label": "업체별/농장별집계",
						    "cmd": "#box/MMANAA30_03"
						  },
						  "MSAL": {
						    "label": "영업",
						    "order": [
						      "MSALAA10",
						      "MSALAA20",
						      "MSALAA30",
						      "MSALAA40"
						    ]
						  },
						  "MSALAA10": {
						    "label": "관리",
						    "order": [
						      "MSALAA10_01",
						      "MSALAA10_02"
						    ]
						  },
						  "MSALAA10_01": {
						    "label": "마감(월,출고,생돈)",
						    "cmd": "#box/MSALAA10_01"
						  },
						  "MSALAA10_02": {
						    "label": "주간공급견적서",
						    "cmd": "#box/MSALAA10_02"
						  },
						  "MSALAA20": {
						    "label": "마스터",
						    "order": [
						      "MSALAA20_01",
						      "MSALAA20_02",
						      "MSALAA20_03"
						    ]
						  },
						  "MSALAA20_01": {
						    "label": "담보이력관리",
						    "cmd": "#box/MSALAA20_01"
						  },
						  "MSALAA20_02": {
						    "label": "입금입력",
						    "cmd": "#box/MSALAA20_02"
						  },
						  "MSALAA20_03": {
						    "label": "주문입력",
						    "cmd": "#box/MSALAA20_03"
						  },
						  "MSALAA30": {
						    "label": "분석",
						    "order": [
						      "MSALAA30_01",
						      "MSALAA30_02",
						      "MSALAA30_03",
						      "MSALAA30_04",
						      "MSALAA30_05",
						      "MSALAA30_06",
						      "MSALAA30_07"
						    ]
						  },
						  "MSALAA30_01": {
						    "label": "가격관리",
						    "cmd": "#box/MSALAA30_01"
						  },
						  "MSALAA30_02": {
						    "label": "거래처별제품별판매분석",
						    "cmd": "#box/MSALAA30_02"
						  },
						  "MSALAA30_03": {
						    "label": "거래처원장",
						    "cmd": "#box/MSALAA30_03"
						  },
						  "MSALAA30_04": {
						    "label": "수불부(급식제외)",
						    "cmd": "#box/MSALAA30_04"
						  },
						  "MSALAA30_05": {
						    "label": "월별매출및미수현황",
						    "cmd": "#box/MSALAA30_05"
						  },
						  "MSALAA30_06": {
						    "label": "입금현황",
						    "cmd": "#box/MSALAA30_06"
						  },
						  "MSALAA30_07": {
						    "label": "한도초과거래선",
						    "cmd": "#box/MSALAA30_07"
						  },
						  "MSALAA40": {
						    "label": "포털",
						    "order": [
						      "MSALAA40_01",
						      "MSALAA40_02"
						    ]
						  },
						  "MSALAA40_01": {
						    "label": "고객포털 | 주문",
						    "cmd": "#box/MSALAA40_01"
						  },
						  "MSALAA40_02": {
						    "label": "고객포털 | 주문조회",
						    "cmd": "#box/MSALAA40_02"
						  },
						  "TMCO": {
						    "label": "공통관리",
						    "order": [
				              "TMCOOS10",
						      "TMCOOS20",
						      "TMCOOS40",
						      "TMCOOS50",
						      "TMCOOS60",
						      "TMCOOS70",
						      "TMCOUR10",
						      "TMCOCD10_10",
						      "TMCOCD10_11"
						    ]
						  },
						  "TMCOOS10": {
							  "label": "회사관리",
							  "cmd": "#box/TMCOOS10"
						  },
						  "TMCOOS20": {
							  "label": "공장관리",
							  "cmd": "#box/TMCOOS20"
						  },
						  "TMCOOS40": {
							  "label": "창고관리",
							  "cmd": "#box/TMCOOS40"
						  },
						  "TMCOOS50": {
							  "label": "본부관리",
							  "cmd": "#box/TMCOOS50"
						  },
						  "TMCOOS60": {
							  "label": "팀관리",
							  "cmd": "#box/TMCOOS60"
						  },
						  "TMCOOS70": {
							  "label": "부서관리",
							  "cmd": "#box/TMCOOS70"
						  },
						  "TMCOUR10": {
							  "label": "사용자정보관리",
							  "cmd": "#box/TMCOUR10"
						  },
						  "TMCOCD10_10": {
							  "label": "공통코드관리",
							  "cmd": "#box/TMCOCD10_10"
						  },
						  "TMCOCD10_11": {
							  "label": "프로그램(메뉴)관리",
							  "cmd": "#box/TMCOCD10_11"
						  },
						  /* 170718 TOBE Start
						   *
						   */
						  "TM": {
						    "label": "공통",
						    "order": [
				              "TMCOCD",
						      "TMCOUR",
						      "TMCOMT"
						    ]
						  },
						  "TMCOCD": {
							  "label": "코드",
							    "order": [
							              "TMCOCD10",
							              "TMCOCD20",
									      "TMCOCD30"
									    ]
						  },
						  "TMCOCD10": {
							  "label": "공통코드관리",
							  "cmd": "#box/TMCOCD10"
						  },
						  "TMCOCD20": {
							  "label": "라벨관리",
							  "cmd": "#box/TMCOCD20"
						  },
						  "TMCOCD30": {
							  "label": "메시지관리",
							  "cmd": "#box/TMCOCD30"
						  },
						  "TMCOUR": {
							  "label": "사용",
							    "order": [
							              "TMCOUR40",
							              "TMCOUR50",
									      "TMCOUR60"
									    ]
						  },
						  "TMCOUR40": {
							  "label": "권한그룹관리",
							  "cmd": "#box/TMCOUR40"
						  },
						  "TMCOUR50": {
							  "label": "그룹별메뉴관리",
							  "cmd": "#box/TMCOUR50"
						  },
						  "TMCOUR60": {
							  "label": "그룹별사용자관리",
							  "cmd": "#box/TMCOUR60"
						  },
						  "TMCOMT": {
							  "label": "관리",
							    "order": [
										  "TMCOMT10",
							              "TMCOMT40",
							              "TMCOMT50",
									      "TMCOMT60"
									    ]
						  },
						  "TMCOMT10": {
							  "label": "프로그램관리",
							  "cmd": "#box/TMCOMT10"
						  },

						  "TMCOMT40": {
							  "label": "API로그관리",
							  "cmd": "#box/TMCOMT40"
						  },
						  "TMCOMT50": {
							  "label": "SMS/FAX로그관리",
							  "cmd": "#box/TMCOMT50"
						  },
						  "TMCOMT60": {
							  "label": "FAX보내기",
							  "cmd": "#box/TMCOMT60"
						  }
						  ,
						  //
						  // Test ------------------------- Page
						  //
						  "TEST": {
						    "label": "테스트 샘플",
						    "order": [
						              "codesearch",
						              "JQGrid4-Paging"
						    ]
						  },
						  "JQGrid4-Paging": {
							  "label": "그리드 페이징 테스트",
							  "cmd": "#box/JQGrid4-Paging"
						  },
						  "codesearch" : {
							  "label": "코드서치 샘플",
							  "cmd": "#popup/codesearch"
						  }

					};
        	};

        	this._area.menu = new Menu();
        	this.menu.show( this._area.menu );

        	//
        	this._area.menu.showLoading();
        	this._fetchMenu()
        	//this._fetchMenuEmul()
        	.then(function(params)
        	{
        		params = self._makeMenuParams( params );
        		//
        		// XXX 구메뉴 추가
        		//
        		//params.root.order.push("old_main_menu");
        		//_.extend( params, setMenuParams() );
        		//
        		// ====================

        		// setMenuItems() 내에서는 메뉴 아이템의 정보가 종속관계를 유지하도록 정보를 갱신한다.
        		// 메뉴 정보가 변경되기 이전의 구조를 필요로 하는 처리를 위해서 복사본을 사용한다.
        		self._area.menu.setMenuItems( UCMS.copyJSON(params) );
        		self._area.manager.setContentsLabel( params );
        		NDSProps.set( "menuItems", params );
        		NDSProps.set( "corpCode", "1001" );

        		//
        		self._area.menu.hideLoading();
        	})
        	.fail(function(jqXHR, textStatus, errorThrown)
			{
        		self._area.menu.hideLoading();
				UCMS.alert("프로그램 목록을 가져오지 못했습니다. 다시 접속합니다.<br>사유 : "+textStatus)
				.then(function()
				{
					window.close();
				});
			});
        },
        /**
         * 반환된 프로그램 목록을 기반으로 메뉴 파라메터를 생성한다.
         */
        _makeMenuParams: function(resultEx)
        {
        	if( !resultEx.extraData || resultEx.extraData.totalRecordCount == 0 )
        	{
        		return resultEx;
        	}

        	var menuParams =
        	{
        		"layout": {
				    "size": "xs",
				    "align": "text-left",
				    "type": "treeview"
        		},
        		"defaultMenu": "root",
        		"root": {
        			"label": "Root",
        			"order": []
        		}
        	};
        	var items = resultEx.extraData.result;
        	var makeItems = function(items)
        	{
        		for(var i in items)
        		{
        			var item = items[i];

        			if( item.programYn == "Y" )
        			{
        				menuParams[ item.menuCode ] =
        				{
	        				"label": item.menuName,
	        				"cmd": "#box/"+item.menuCode
	        			};
        			}
        			else
        			{
        				// 폴더 노드
        				menuParams[ item.menuCode ] =
        				{
	        				"label": item.menuName,
	        				"order": []
	        			};
        			}
        		}
        	};
        	var makeRelations = function(items)
        	{
        		for(var i in items)
        		{
        			var item = items[i];

        			if( item.upCode == "0" )
        			{
        				// 루트 노드
        				//menuParams["defaultMenu"] = item.menuCode;
        				menuParams["root"].order.push( item.menuCode );
        			}
        			else
        			{
        				var parentNode = menuParams[item.upCode];
        				if( parentNode )
        				{
	        				// 부모 노드에 자식 노드 등록
	        				parentNode.order.push( item.menuCode );
        				}
        				else
        				{
        					Logger.warn("makeRelations() - Invalid Menu list.");
        				}
        			}
        		}
        	};

        	makeItems( items );
        	makeRelations( items );

        	return menuParams;
        }
        ,
        _initManager: function()
        {
        	this._area.manager = new ContentManager();
        	this.contents.show( this._area.manager );
        },
        /**
         * 서브 패널들이 발생시키는 이벤트를 처리한다.
         * @see BaroPanelBase.sendEvent() - 이벤트가 처리되지 못한 경우 본 핸들러로 수신됨
         * @see BaroPanelBase.broadcastEvent() - 발생시킨 모든 이벤트가 본 핸들러로 수신됨
         */
        onEventHandling: function(event)
        {
        	Logger.debug("MainBox.onEventHandling() - ");
        	Logger.debug(event);

        	var params = event.params;
        	switch(event.cmd)
        	{
        	case RendererBase.EVENT.CLOSERENDERER:
        		this._area.manager.destroyWidget(params.id, params.force);
        		break;
        	}
        },
        /**
         * 지저안 모듈을 콘텐츠 영역에 출력한다.
         */
        showContent: function(contentId, modulePath, params)
        {
        	Logger.debug("MainBox.showContent() - contentId : "+contentId);
        	Logger.debug(modulePath);
        	Logger.debug(params);
        	if( !NDSProps.get("user") || !NDSProps.get("user").id )
        	{
        		UCMS.alert("사용자 정보를 확인할 수 없습니다.<br>다시 로그인해 주세요.")
        		.then(function()
        		{
        			window.close();
        		});
        		return;
        	}

        	if( params )
        	{
	        	// XXX openMPS에서만 사용되는 필드 추가
	        	// 패널 리소스 파라메터에 해당 패널의 유형을 식별할 수 있는 id 필드를 추가한다.
	        	// 콘텐츠별로 별도 처리할 사항이 있는 경우 WorkAreaRenderer 에서 본 식별자가 사용된다.
	        	params["contentId"] = contentId;
        	}

        	var mainBox = this;
        	return this._area.manager.addWidget
        	(
        		contentId,
        		{'module': modulePath, 'options': params},
        		true
        	)
        	.then(function(result)
        	{
        		Logger.debug("MainBox.showContent() - Complete : "+result.widgetId);

        		if( result.instance )
        		{
	        		result.instance.on( BaroBox.WIDGET_EVENT
					, function(event)
					{
	        			// 서브 모듈들이 발생시키는 이벤트를 처리한다.
	        			mainBox.onEventHandling(event);
					});
	        		result.instance.on( BaroBox.EVENT.SHOW
					, function(event)
					{
	        			mainBox._adjustContentsHeight();
					});
        		}
        		mainBox._adjustContentsHeight();
        	});
        },
        /**
         * 메인 페이지를 초기 상태로 설정한다.
         */
        setHome: function()
        {
        	Logger.debug("setHome()");

        	return this.showContent('home', 'manifest!MainHome-1.0.0#widget');
        },
        onBeforeShow: function()
        {
        	var self = this;
			$(window).resize(function ()
			{
				self._adjustContentsHeight();
			})
        	this.setHome();
        }
        ,
		onShow: function()
		{
			/*
			var self = this;
			setTimeout(function()
			{
				self._initScroller();
			}
			, 500);
			*/
		}
        ,
        /**
         * 콘텐츠 높이를 윈도우의 크기를 고려하여 설정한다.
         */
		_adjustContentsHeight: function()
		{
			var contentsHeight = window.innerHeight - this.contents.$el.offset().top;
			//this._area.manager.setBoxHeight( contentsHeight );
		}
		,
        _initScroller: function()
        {
        	//if( $.mCustomScrollbar )
			//{
        		this.menu.$el.mCustomScrollbar(
				{
					theme:"minimal-dark",
					axis:"y",
					scrollbarPosition:"outside"
				});
        		this.contents.$el.mCustomScrollbar(
				{
					theme:"minimal-dark",
					axis:"yx"
				});
        	//}
        }
		,
		/**
		 * 프로그램 메뉴 목록을 조회한다.
		 */
		_fetchMenu: function()
		{
			var systemCode = NDSProps.get('systemCode');
			var hosts = NDSProps.get('hosts') || { api: '' };
			var systemCode = NDSProps.get('systemCode');
			var corpCode = NDSProps.get('corpCode') || '1001';
			var token = NDSProps.get('token');
			var client = new APIClient(
			{
				token: token,
				corpCode: corpCode,
				host: hosts.api,
				systemCode: systemCode,
				contentId: "TMCOUR30",
				params: {
					timeout: 60000	// 1 min
				}
			});

			return client.read({systemCode:systemCode});
		}
        ,
        /**
         * 사용자의 메뉴 목록을 얻는다.
         * @return {object}
         */
        _fetchMenuEmul: function()
        {
        	var d = $.Deferred();
        	var resultEx = {
			  "extraData": {
			    "startOrd": 0,
			    "pageIndex": 0,
			    "pageSize": 0,
			    "totalRecordCount": 6,
			    "result": [
			      {
			        "menuCode": "M14",
			        "menuName": "M14",
			        "upCode": "0",
			        "upName": null,
			        "sortOrder": 1,
			        "useYn": "Y",
			        "deleYn": "N",
			        "mdUser": null,
			        "mdDate": null,
			        "crUser": "tester",
			        "crDate": 1500343334350
			      },
			      {
			        "menuCode": "M1200",
			        "menuName": "M1200",
			        "upCode": "M12",
			        "upName": "M12",
			        "sortOrder": 1,
			        "programYn": "Y",
			        "useYn": "Y",
			        "deleYn": "N",
			        "mdUser": "test",
			        "mdDate": null,
			        "crUser": "test",
			        "crDate": 1500217200000
			      },
			      {
			        "menuCode": "M1300",
			        "menuName": "M1300",
			        "upCode": "M11",
			        "upName": "M11",
			        "sortOrder": 1,
			        "useYn": "Y",
			        "deleYn": "N",
			        "programYn": "Y",
			        "mdUser": null,
			        "mdDate": 1500367766080,
			        "crUser": "test",
			        "crDate": 1500303600000
			      },
			      {
			        "menuCode": "M12",
			        "menuName": "M12",
			        "upCode": "0",
			        "upName": null,
			        "sortOrder": 2,
			        "useYn": "Y",
			        "deleYn": "N",
			        "mdUser": "test",
			        "mdDate": null,
			        "crUser": "test",
			        "crDate": 1500217200000
			      },
			      {
			        "menuCode": "M1100",
			        "menuName": "M1100",
			        "upCode": "M11",
			        "upName": "M11",
			        "sortOrder": 2,
			        "programYn": "Y",
			        "useYn": "Y",
			        "deleYn": "N",
			        "mdUser": null,
			        "mdDate": 1500367796971,
			        "crUser": "test",
			        "crDate": 1500217200000
			      },
			      {
			        "menuCode": "M11",
			        "menuName": "M11",
			        "upCode": "0",
			        "upName": null,
			        "sortOrder": 3,
			        "useYn": "Y",
			        "deleYn": "N",
			        "mdUser": "test",
			        "mdDate": null,
			        "crUser": "test",
			        "crDate": 1500217200000
			      }
			    ]
			  },
			  "resultCode": 0,
			  "msg": "Completed a requested task!"
			};

        	d.resolve(resultEx);
        	return d.promise();
        }
	});

	return MainBox;
});
