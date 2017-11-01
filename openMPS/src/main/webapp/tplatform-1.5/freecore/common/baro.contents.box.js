/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2013 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

define(
[ "BaroPanelBase", "spa_modfactory" ], 
function( BaroPanelBase, mf )
{
	/**
	 * 빵앱 파라메터의 "contents" 을 구현하는 모듈로써, 다양한 위젯을 담는 Box 역할을 한다.
	 * 자식 위젯들을 보관하며, 상호 운영을 위한 관리자 역할을 수행한다.
	 */
	var	BaroContentsPanel = BaroPanelBase.extend(
	{
		className : "baangapp_panel",
		
		_childs: {},
		_activePanelId: null,
		
		initialize: function(options)
		{
			// TODO template_id 이 뭐지?? 쓰는 곳이 없는데???
			this.template = options.template_id;

			BaroPanelBase.prototype.initialize.apply( this, arguments );

			var regions = this._makeRegions( options );
			
			if( typeof regions != "undefined" )
			{
				this.addRegions( regions );
			}

			UCMS.log("Complete to initialize a BaroContentsPanel!");
		},
		
		onBeforeRender: function()
		{
			UCMS.debug("Panel::onBeforeRender()");
		},
		
		onBeforeShow: function()
		{
			UCMS.debug("Panel::onBeforeShow()");
		},
		
		onShow: function()
		{
			UCMS.debug("Panel::onShow()");
			
			this._activateWidgets( this._params );
		},
		
		onDomRefresh: function()
		{
			UCMS.debug("Panel::onDomRefresh()");
		},
		
		/**
		 * 컨텐츠 영역의 Region 정보를 구성한다.
		 * 
		 * @param params		{ Object } 컨텐츠 영역 파라메터
		 * @return				{ Object } Regions 의 맵
		 */
		_makeRegions: function(params)
		{
			var content_regions = {};
			
			for(var content_id in params.order)
			{
				content_id = params.order[ content_id ];
				
				switch( content_id )
				{
				case "contents":
					{
						// 서브 컨텐츠 영역
						content_regions["contents"] = "#"+params["contents"].id;
					}
					break;
					
				default:
					{
						// 컨텐츠 위젯
						content_regions[content_id] = "#"+content_id;
					}
				} // end switch
			}
			
			return content_regions;
		},

		/**
		 * switching 옵션에 따른 자식 widget 을 활성화한다.
		 */
		_activateWidgets: function(params)
		{
			var switchingOptions = this.getParam("switching") || { method: null };
			var wid = switchingOptions.default_id;

			if( typeof switchingOptions.method == "string" )
			{
				//
				// 최상위 contents 에서만 지정된다.
				//
				
				$("body").addClass(switchingOptions.method);
			}

			this._activePanelId = switchingOptions.default_id;
			
			switch( switchingOptions.method )
			{
			case "show-hide":
				$(".baro_content_box > div > div").addClass("hide")
				$("#"+wid).removeClass("hide")

				for(var widget_id in params)
				{
					this._activateWidgetWithId( widget_id, params[ widget_id ] );
				} // end for
				break;

			case "hori-swipe":

				var content = $(".baro_baro_content_box").html();
				//content = '<div class="swiper-container">' + content + '</div>'
				//alert(content)
				$(".baro_content_box").html(content)
				$(".baro_content_box > ").addClass("swiper-container")
				$(".baro_content_box > div > div").addClass("swiper-wrapper")
				$(".baro_content_box > div > div > div").addClass("swiper-slide")
				var i = 0;
				for(var widget_id in params)
				{
					i = i + 1;
					this._activateWidgetWithId( widget_id, params[ widget_id ] );
					$(".baro_content_box > div > div > div > div:nth-child("+i+")").addClass("slide_"+i);
				} // end for	
				
				var mySwiper = new Swiper('.swiper-container',{
				    pagination: '.pagination',
				    paginationClickable: true
				})

				break;
				
			case "vert-swipe":
				
			default:

				for(var widget_id in params)
				{
					this._activateWidgetWithId( widget_id, params[ widget_id ] );
				} // end for
				break;
			}

		},
		
		/**
		 * 스위칭 옵션에 따라 최초 실행 body에 클래스값 부여 
		 */
		_preprocessActivate: function(params)
		{
			UCMSPlatform.log("switchingOptions.method : " + params.method)
			
			switch( params.method )
			{
			case "show-hide":
				var wid = params.default_id;
				$(".baro_content_box > div > div").addClass("hide")
				$("#"+wid).removeClass("hide")
				break;
				
			case "hori-swipe":
				var wid = params.default_id;
				//slide_1
				$(".baro_content_box").addClass("swiper-container")
				$(".baro_content_box > div").addClass("swiper-wrapper")
				$(".baro_content_box > div > div").addClass("swiper-slide")
				//$(".baro_content_box > div > div:nth-child(1n+0)").addClass("hide")
				//$("#"+wid).removeClass("hide")
				
				break;

			case "vert-swipe":
				
			default:
			}
		},
		
		/**
		 * 지정된 식별자를 갖는 자식 위젯을 생성한다.
		 */
		_activateWidgetWithId: function(widget_id, widget_params)
		{
			var baangPanel = this;
 
			switch( widget_id )
			{
			case "contents":
				// 콘텐츠 패널 영역
				this._childs[widget_id] = new BaroContentsPanel(widget_params);
				baangPanel["contents"].show( this._childs[widget_id] );
				break;
				
			case "id": // 나의 id
			case "className": // 나의 스타일 클래스 이름
			case "tagName": // 나의 태그 이름
			case "box_id": // 나와 관련있는 box 의 id
			case "module":
			case "switching":
			case "order":
			case "template_id":
				// 위젯 정보가 아닌 값은 통과~
				break;
			
			default:
				{
					// 위젯 영역
					if( typeof widget_params.module == "undefined" )
					{
						// 인스턴스 생성을 위한 위젯 모듈 지정은 필수이다.
						return;
					}

					(function(self, wid)
					{
						// panel 위젯 세팅
						widget_params["parent_instance"] = self;
					
						mf.createInstance( widget_params.module, widget_params ).then(function(w)
						{
							// 자식 위젯 저장
							self._childs[wid] = w;
							
							// 해당 Region 에 위젯을 붙인다.
							if( typeof baangPanel[ wid ] != "undefined" )
							{
								baangPanel[ wid ].show( w );
							}
							else
							{
								UCMS.debug("Unknown Widget ID : "+wid);
							}
						});
						
						if( typeof widget_params.view_method  == "string")
						{
							// 페이지뷰 형태가 새로운 페이지일 경우 아래 클래스 추가 우선 hide로 감춤
							$("#"+wid).addClass(widget_params.view_method);
							$("#"+wid).addClass("hide")
						}
					})( this, widget_id );
				}
				break;
			}
		},
		
		/**
		 * 지정된 위젯 식별자의 위젯 인스턴스를 얻는다.
		 * 
		 * @param wid	{ String } 위젯 식별자(id)
		 * @returns { Object } 위젯 인스턴스
		 */
		getChild: function( wid )
		{
			var w = this._childs[wid];
			
			if( typeof w == "undefined" )
			{
				return null;
			}
			
			return this._childs[wid];
		},
		
		/**
		 * 컨텐츠를 전환한다.
		 * 지정된 switching 옵션을 기반으로 동작된다.
		 * 
		 * @param cid	{ String } 컨텐츠 식별자. getChild() 로 대상 컨텐츠를 구할 수 있다.
		 */
		onSwitching: function( cid )
		{
			var switchingOptions = this.getParam("switching") || { method: null };
			
			UCMSPlatform.log("Switching Method : "+ switchingOptions.method);
			
			switch( switchingOptions.method )
			{
			case "show-hide":
				this._switchContent( cid );
				break;
				
			case "hori-swipe":
				break;
				
			case "vert-swipe":
				break;
				
			default:
				this._scrollToId( cid );
				break;
			}
			this._activePanelId = cid;
		},
		
		_scrollToId: function(cid)
		{
			var scrollmove = $("#"+cid).position().top - 55;
			$("body").scrollTop(scrollmove);
		},
		
		_switchContent: function(cid)
		{
			if(this._activePanelId != cid)
			{	
				$("#"+this._activePanelId).addClass("hide");
				$("."+this._activePanelId).removeClass("active");

				$("#"+cid).css("display","none");
				$("#"+cid).removeClass("hide");
				$("#"+cid).fadeIn("slow");
			}
			
			//
			$("."+cid).addClass("active");
			$("body").scrollTop(0);
		}
	});

	return BaroContentsPanel;
	
});