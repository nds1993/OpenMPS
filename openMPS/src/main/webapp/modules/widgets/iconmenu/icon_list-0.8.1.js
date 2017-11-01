/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

        define
        (
        [
         "text!./icon_list-0.8.1.html",
         "Logger"
        ],
        function( html, Logger )
        {
        	var RowData = Backbone.Model.extend({});
        	var TableData = Backbone.Collection.extend(
        	{
        		model: RowData
        	});
        	
        	var		liRow = Backbone.Marionette.ItemView.extend(
        	{
        		_params : null,
        		
        	    template: "#iconmenu_list_row_html",
        	    tagName: "li",
				className: "list-group-item",
				events:
        	    {
        	    	"click .btn_go_content": "goMenu"
	       	    }
        		,
        		initialize: function(options)
        		{
        	    	this._params = options.params;
        		}
        		,
				goMenu : function(event)
                {
        			Logger.debug("box_id :: " + this.model.get("box_id"));
        			
        			var targetId = this.model.get("box_id");

        			if(targetId.indexOf("!sms") > 0) 
					{
						location.href = targetId;
					}
					else if(targetId.indexOf("!call") > 0)
					{
        				location.href = targetId;
					}
					else
					{
						/*
						 * XXX 커버 계속 사용
	        			setTimeout(function()
	        			{
	        				$(".cover_box").hide();
	        			}
	        			, 100);
	        			*/
	        			
						location.href = "#box/"+targetId;	
					}
                }
        	});
        	
        	/**
        	 * Collection 을 데이터로 사용하는 위젯
        	 */
        	var		menubarListWidget = UCMSPlatform.SPA.Widget.extend(
        	{
        		template: "#iconmenu_list_box_html",
        	    tagName: "div",
        	    className : "iconmenu_list_box",
        	    itemView: liRow,
        	    itemViewOptions : function () { return { params : this._params }; },
        	    events:
        	    {
	       	    },
        	    
        	    _params: null,

        	    /**
        	     * 메뉴 버튼 항목을 초기화한다.
        	     * 
        	     * @param options	{ box_id:"", items:[] }
        	     */
				 
        		initialize: function(options)
        		{
        			UCMSPlatform.SPA.Widget.prototype.initialize.apply( this, arguments );
        			UCMSPlatform.log("iconmenu_list options : "+options);
        			
        			this._params = options;
                },
                onCollectionBeforeRender: function()
	            {
	            	var menuArr = [];
	    			
					UCMSPlatform.log("this._params.items.options : "+this._params);

					
	    			$.each(this._params.items, function(i, itm){
	    				
	    				menuArr.push({
	    					box_id : itm.box_id,
	    					caption : itm.caption,
		    				icon_font : itm.icon_font
    					});
	    				
	    			});
					
	        		UCMSPlatform.log("this._params. options : "+this._params);
	    			this.collection = new TableData(menuArr);
	            },                
        	    onShow: function()
        		{
        	    	UCMSPlatform.log("showing iconmenu_list widget!" );
			       	var self =this;

        		},
                appendHtml: function(collectionView, itemView)
        	    {
        	        collectionView.$("ul.icon_list").append(itemView.el);
        	    }
				
        	});
        	
        	UCMSPlatform.SPA.AppMain.initResource( html );

        	return menubarListWidget;
        });