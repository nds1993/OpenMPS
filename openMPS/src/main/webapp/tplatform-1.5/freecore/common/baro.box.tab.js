/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

define(
[ "Logger" ], 
function( Logger )
{
	/**
	 * BaroBox 탭을 구현한다.
	 * 탭이 활성화 될 때 BoxTab.EVENT.ACTIVE 이벤트가 발생된다.
	 * 종료 버튼이 클릭 될 때 BoxTab.EVENT.CLOSE 이벤트가 발생된다.
	 * 사용자정의 BoxTab 은 BoxTab.EVENT.ACTIVE 을 필히 발생시켜야 한다.
	 * 
	 * TODO Tab 제어 영역의 구현 필요
	 */
	var BoxTab = UCMS.SPA.Panel.extend(
	{
		template: function()
		{
			return '<div class="nav_box"><ul class="nav nav-tabs"></ul></div><div class="btn_box hide"/>';
		}
		,
		ui:
		{
			'tabs': 'ul.nav-tabs',
			'btn_left': '.ui_left_btn',
			'btn_right': '.ui_right_btn',
			'nav': '.nav',
			'nav_box': '.nav_box',
			'btn_box':'.btn_box'
		}
		,
		events:
		{
			'click @ui.btn_left': 'onLeft',
			'click @ui.btn_right': 'onRight'
		}
		,
		// 활성화된 탭의 li 태그
		_$activeTabLi: null,
		// 탭의 레이블
		_mapLabel: null,
		// 탭의 위치
		_posClass: null,
		// 닫기 버튼 사용유무
		_btnClose: null,
		// 닫기 버튼이 필요없는 콘텐츠 배열
		_exceptClose: null,
		// 스크롤 사용 유무
		_btnScroll: null,
		
		initialize: function(options)
		{
			BoxTab.__super__.initialize.apply(this, arguments);
			this._$activeTabLi = null;
			this._mapLabel = options.label || null;
			this._posClass = BoxTab.getPosClass( options.pos );
			
			var control = options.control || {};
			this._btnClose = (control.close == true);
			this._btnScroll = (control.scroll == true);
			this._btnTabClose = (control.tabClose == true);
			this._exceptClose = control.exceptClose || [];
		}
		,
		onRender: function()
		{
			this.$el.addClass( this._posClass );
			if( this._btnScroll == true )
			{
				this.$el.addClass("nav_scroll");
				this.ui.btn_box.removeClass("hide");
				this.ui.btn_box.append('<div class=ui_left_btn><i class="fa fa-fw fa-chevron-left"></i></div>'
						+'<div class=ui_right_btn><i class="fa fa-fw fa-chevron-right"></i></div>');
			}
			if( this._btnTabClose == true )
			{
				this.ui.btn_box.removeClass("hide");
				this.ui.btn_box.append('<div class=ui_tab_close><i class="fa fa-fw fa-times"></i></div>');
			}
		}
		,
		onShow: function()
		{
			Logger.debug("BoxTab.onShow()");
			Logger.debug(this.ui);
			if( this._btnScroll == true )
			{
				var self = this;
				UCMS.retry(function()
				{
					self._$nav = $(".nav_scroll .nav_box");
					if( self._$nav.length == 0 )
					{
						return false;
					}
					
					self._$nav.mCustomScrollbar(
					{
						scrollButtons:{ enable: false },
						axis:"x",
						theme:"dark-thin",
						autoExpandScrollbar:true,
						scrollbarPosition:"inside",
						advanced: {
							autoExpandHorizontalScroll:true,
							autoScrollOnFocus : "li"
						}
					});
				});
			}
		}
		,
		/**
		 * 신규 탭을 추가합니다.
		 * @param {string} tabId - 추가되는 탭 식별자
		 * @param {object} cmd - 탭이 클릭시 수행하는 명령어.
		 * @returns {boolean} 추가 결과. 추가된 경우 true, 
		 * 					Tab 이 필요 없는 경우 false. 이 경우는 디자인 항목의 경우 tab 전환이 필요없다.
		 */
		addTab: function(tabId, cmd)
		{
			var $tab = this.getTab$(tabId);
			if( $tab.length > 0 )
			{
				Logger.warn("addTab() - Already has a tab id.");
				return false;
			}
			
			var self = this;
			var label = tabId;
			
			if( this._mapLabel )
			{
				//
				// 탭 레이블 정보가 설정된 경우에는 해당 레이블을 사용함.
				//
				label = this._mapLabel[tabId];
				
				if( typeof label !== 'string' )
				{
					Logger.debug("addTab() - Not allowed tab content. widget Id : "+tabId);
					return false;
				}
			}
			
			var $anchor = $("<a>").html(label)
				.addClass(tabId)
				.click(function()
				{
					self.activeTab( tabId );

					// 앵커에 cmd 설정
					// TODO cmd 의 형식이 규정되면 실행하는 코드 적용
				});
			if( this._btnClose == true && _.indexOf(this._exceptClose, tabId) == -1 )
			{
				var $btn = $("<button>").html('×')
					.click(function(e)
					{
						Logger.debug("Closing Content Id : "+tabId);
						self.trigger( BoxTab.EVENT.CLOSE, { 'id' : tabId } );
						e.stopPropagation();
					});
				$anchor.append( $btn );
			}
			this.ui.tabs.append( $("<li>").append($anchor) );
			if( this._$nav && this._$nav.mCustomScrollbar )
			{
				this._$nav.mCustomScrollbar("update");
			}
			return true;
		}
		,
		removeTab: function(tabId)
		{
			var $tab = this.getTab$(tabId);
			if( $tab.length > 0 )
			{
				$tab.remove();
			}
		}
		,
		/**
		 * 추가된 탭 개수를 구한다.
		 */
		getTabCount: function()
		{
			return this.ui.tabs.find('li').length;
		}
		,
		/**
		 * 지정한 탭의 jquery 객체를 얻는다.
		 * @returns {$} li 태그를 담고 있는 탭의 $ 객체
		 */
		getTab$: function(tabId)
		{
			// 현재 탭을 선택하는 방식에 따라서 getActiveTabId() 의 조건도 함께 변경한다.
			return this.ui.tabs.find("li a."+tabId).parent();
		}
		,
		activeTab: function(tabId)
		{
			var inactiveTab = null;
			if( this._$activeTabLi )
			{
				inactiveTab = this.getActiveTabId();
				this._$activeTabLi.removeClass('active');
				this._$activeTabLi = null;
			}
			
			var $tab = this.getTab$(tabId);
			if( $tab.length > 0 )
			{
				this._$activeTabLi = $tab.addClass('active');
				Logger.debug("activeTab() - tabId : "+tabId);
				
				if( tabId != inactiveTab )
				{
					this.trigger( BoxTab.EVENT.ACTIVE, { 'active' : tabId, 'inactive' : inactiveTab } );
				}
			}
			
			if( this._$nav && this._$nav.mCustomScrollbar )
			{
				this._$nav.mCustomScrollbar("scrollTo", $tab);
			}

		}
		,
		/**
		 * 현재 활성화된 탭의 li 노드 선택자를 얻는다.
		 */
		getActiveTabLi$: function()
		{
			return this._$activeTabLi || this.$('');
		}
		,
		getActiveTabId: function()
		{
			// getTab$() 의 반환 조건에 의해 tab ID 를 구하는 조건이 바뀌게 된다.
			return this._$activeTabLi ? this._$activeTabLi.children().attr('class') : null;
		}
		,
		/**
		 * 탭의 위치 
		 * 
		 * @return {string} left, top, right, bottom
		 */
		getPosition: function()
		{
			return this._posClass.replace('pos_','');
		}
		,
		onLeft: function()
		{
			//var tabWidth = this.getTabWidth();
			//var navWidth = Number(this.ui.nav.css("width").replace("px",""))
			if( this._$nav && this._$nav.mCustomScrollbar )
			{
				this._$nav.mCustomScrollbar("scrollTo", "+="+this.getTabWidth());
			}
		}
		,
		onRight: function()
		{
			if( this._$nav && this._$nav.mCustomScrollbar )
			{
				this._$nav.mCustomScrollbar("scrollTo","-="+this.getTabWidth());
			}
		}
		,
		// 탭 전체 닫기 
		onTabClose: function()
		{
			//UCSM.confirm("모든 메뉴탭을 닫으시겠습니까?<br>모든 화면을 닫으면 저장하지 않는 자료와 조회한 내용이 초기화됩니다.<br><br>그래도 진행하시겠습니까?>")
		}
		,
		getTabWidth: function(){
			return Number(this.$el.css("width").replace("px",""))
		}
		,
		/**
		 * 레이블 정보를 추가한다.
		 * @params {object} labels - "아이템식별자:레이블" 형식의 JSON
		 */
		setTabLabels: function(labels)
		{
			this._mapLabel = labels ? _.extend(this._mapLabel || {}, UCMS.copyJSON( labels )) : null;
		}
	}
	,
	{
		EVENT:
		{
			ACTIVE: "boxtab:active",		// 파라메터 active: 활성화되는 tab id, inactive: 비활성화되는 tab id
			CLOSE: "boxtab:close"			// 파라메터 id : 닫히는 tab id
		},
		POSITION:
		{
			LEFT: 'pos_left',
			RIGHT: 'pos_right',
			TOP: 'pos_top',
			BOTTOM: 'pos_bottom',
			STICKY: 'pos_sticky'
		},
		getPosClass: function(value)
		{
			return this.POSITION[(value||'top').toUpperCase()];
		}
	});

	return BoxTab;
});