/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

        define
        (
        [
         	"text!./cover_list-0.8.1.html", "BaroProps"
        ],
        function( html, BaangProps )
        {
        	var RowData = Backbone.Model.extend({});
        	var TableData = Backbone.Collection.extend(
        	{
        		model: RowData
        	});
        	
        	var	liRow = Backbone.Marionette.ItemView.extend(
        	{
        	    template: "#cover_list_row_html",
        	    tagName: "li",
        	    className : "cover_list_item"
        	});
        	
        	/**
        	 * Collection 을 데이터로 사용하는 위젯
        	
      			  "icon_font":"fa-rocket",
				 				        	 "title":"시작이 쉽다",
				 				        	 "caption":"모바일오븐을 설치하고, 테마를 선택한 후 만들고자 하는 앱의 메뉴를 추가 하시면 앱이 바로 됩니다."
        	 *
        	 */
        	var		menubarListWidget = UCMSPlatform.SPA.Widget.extend(
        	{
				_thisImg : 1,
				_maxImg : null,

        		template: "#cover_list_box_html",
        	    tagName: "div",
        	    className : "cover_list_box",
        	    itemView: liRow,
        	    events:
        	    {
        	    	"click .btn_go_cover_left": "onGoLeft",
        	    	"click .btn_go_cover_right": "onGoRight",
          	    },
				ui:
				{
					cover_list : ".cover_list_region"
				},
        		initialize: function(options)
        		{
        			UCMSPlatform.log( "cover_list");
        			
        			//UCMSPlatform.log(options);
        			
        			var hosts = BaangProps.getHosts();
        			
        			var menuArr = [];
        			
        			var _img_host = hosts.file;
        			
        			$.each(options, function(i, itm){
        				
        				var bg_path = null;
        				
        				if(itm.bg_path != null){
        				
	        				if( itm.bg_path.indexOf("/TMP") == 0 )
	        				{
	        					bg_path = BaangProps.getHosts().api + itm.bg_path;
	        				}
	        				else if( itm.bg_path.indexOf("http://") == 0 )
	        				{
	        					bg_path = itm.bg_path;
	        				}
	        				else
	        				{
	        					bg_path = BaangProps.getHosts().file + itm.bg_path;
	        				}
        				
        				}
        				
        				menuArr.push({
        					bg_path : bg_path,
        					caption : itm.caption,
							link : itm.link
        				});
        				
        			});
        			
					this._maxImg =   menuArr.length;
        			this.collection = new TableData(menuArr);
        			
                },
        	    onShow: function()
        		{
        	    	UCMSPlatform.log("showing cover_list widget!" );
					/*이미지가 하나일 경우 좌우 버튼 삭제*/

        	    	//				if(this._maxImg <= 1){
        	    	//				}
				//	$(".cover_arrow").css("display","none")
        		},
                
        		onGoLeft : function(event)
                {
					this._thisImg = this._thisImg - 1;
					if ( this._thisImg < 1) { this._thisImg = this._maxImg};
					
        	    	var allView = $(event.currentTarget.parentNode).find('li');
					$(allView).hide();
					var thisView = $(event.currentTarget.parentNode.children[2].children[this._thisImg-1])
					$(thisView).show();
                 },
                onGoRight :function(event)
                {
					this._thisImg = this._thisImg + 1;
					if ( this._thisImg > this._maxImg) { this._thisImg = 1};
        	    	var allView = $(event.currentTarget.parentNode).find('li');
					$(allView).hide();
					var thisView = $(event.currentTarget.parentNode.children[2].children[this._thisImg-1])
					$(thisView).show();
                },
                appendHtml: function(collectionView, itemView)
        	    {
        	        collectionView.$("ul").append(itemView.el);
        	    }
        	});
        	
        	UCMSPlatform.SPA.AppMain.initResource( html );

        	return menubarListWidget;
        });