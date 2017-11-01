/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */
 
define([
        "BaroPanelBase",
        "./icon_list-0.8.1.js"

        ], function( BaroPanelBase, itemList )
{
	/**
	 * 앱 커버 영역을 구현한다.
	 */
	var IconMenuWidget = BaroPanelBase.extend(
	{
	    template: "#iconmenu_box_html",
	    tagName: "div",
	    /*TODO container-fix container-fluid container 옵션으로 선택가능해야 함 */
		className: "iconmenu_box container-fix",
		
		model: new Backbone.Model(),

		/**
		 * 커버 위젯을 초기화한다.
		 * 
		 * @param options	파라메터 객체
		 * 					{
		 * 						module: "cover",
		 * 						caption: 슬로건 문구,
		 * 						bg_path: 커버 위젯의 배경 이미지 경로. 가로 폭은 1024px 이상 권장.
		 * 					}
		 */
		initialize: function(options)
		{
			BaroPanelBase.prototype.initialize.apply( this, arguments );
		},
		regions: 
		{
			listPlace: ".iconmenu_list_region"
	    },
		 ui:
		 {
			listUi: ".iconmenu_list_region",
			go_call : ".go_call",
			go_sms : ".go_sms"
		 },
			
		onBeforeRender: function()
		{
			/*this.model.set(
			{ 
				"title": this.getParam("title"),
				"type" : this.getParam("type"),
			});
			*/
		},
	    onShow: function()
		{
	    	_self = this;

	    	//$(".cover_box")show();
			var self = this;
	    	UCMSPlatform.log("showing IconMenu widget!" );
	    	
//			$("body #cover_region").fadeIn("fast"); /*XXX 옥버전 현재 커버가 최상위 영역에 존재하므로, 홈 페이지 이동시 노출  */
	    	
			//var type = this.model.attributes.type ; // 위의 모델에서 타입만 꺼내서 사용가

			//if(this.type != "undefinde"){
			//	$(this.ui.coverListBox).addClass(type);
			//}
	    	
	    	var options = { "parent_instance": this, box_id : this.getParam("box_id"), items: this.getParam("items") };
	    	var menu_list = new itemList( options );
	    	this.listPlace.show( menu_list );
			//this.listPlace.show( new itemList( this.getParam("items") ) );
	    	if ( UCMS.SPA.isDesktop() == true) {
	    		$( "body .go_sms" ).closest( "li" ).css( "display", "none" );	    	
	    		$( "body .go_call" ).closest( "li" ).css( "display", "none" );	    	
	    	}
		}
	});

	return IconMenuWidget;
	
});