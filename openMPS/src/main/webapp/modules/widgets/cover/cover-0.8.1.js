/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define([
        "BaroPanelBase",
        "BaroProps",
        "./cover_list-0.8.1.js",
        "Logger"

        ], function( BaroPanelBase, BaroProps, itemList, Logger )
{
	/**
	 * 앱 커버 영역을 구현한다.
	 */
	var CoverWidget = BaroPanelBase.extend(
	{
		template: "#cover_box_html",
		tagName: "div",
		className: "cover_box",
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
		
			Logger.debug("initialize CoverWidget");
			Logger.debug(options);
		
		},
		regions: 
		{
			listPlace: ".cover_list_region"
	    },
		ui:
		{
			coverListBox: ".cover_list_region",
			view_qrcode : ".only_desktop.view_qrcode"
		},
			
		onBeforeRender: function()
		{
			this.model.set(
			{ 
				"title": this.getParam("title"),
				"type" : this.getParam("type"),
		});
		},

	    onShow: function()
		{
			
			var self = this;
	    	UCMSPlatform.log("showing cover widget!" );
	
			var type = this.model.attributes.type ; // 위의 모델에서 타입만 꺼내서 사용가

			if(this.type != "undefined"){
				$(this.ui.coverListBox).addClass(type);
			}

	    	this.listPlace.show( new itemList( this.getParam("items") ) );
			
			//desktop 만 qrcode 표시
			if(UCMS.SPA.isDesktop() == true){
			
				var viewQrFunc = function(){
				
				var app_id = BaroProps.getAppInfo().id;
				
				Logger.debug("app_id = " + app_id);
				
					if(app_id){
						
						var first_c = app_id.substring(0, 1);
						//Logger.debug("first_c = " + first_c);
						//<img src="http://file.moven.net/home/g/g7tc/qrcode.jpg">
						
						var qrurl = BaroProps.getHosts().file + "/home/" + first_c + "/" + app_id + "/qrcode.jpg";
						
						Logger.debug("qrurl = " + qrurl);
						
						self.ui.view_qrcode.html('<img src="' + qrurl + '" onerror="this.src=\'http://resource.moven.net/themes/moven/img/logo_s.png\'" />');
					}
				
				};
				
				viewQrFunc();
			
			}
		
		}
	});

	return CoverWidget;
	
});