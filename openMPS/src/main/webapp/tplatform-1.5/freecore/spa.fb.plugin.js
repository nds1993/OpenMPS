/**
 * Project : UCMS( Unified Contents Messaging Solution )
 *
 * Copyright (c) 2014 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

define([]
, function()
{
	var Helper =
	{
		_init: false,
		loadSDK: function(cbResult, msTimeout)
		{
			if( this._init )
			{
				UCMSPlatform.log("Already loaded the FACEBOOK SDK!!");
				return;
			}
			
			var self = this;
			var gaUrl = location.protocol + (navigator.languages.indexOf("ko")==-1 ? "//connect.facebook.net/en_US/all.js" : "//connect.facebook.net/ko_KR/sdk.js#version=v2.0");

			UCMSPlatform.log("FACEBOOK SDK : "+gaUrl);
			
			UCMSPlatform.loadModuleByPath
			(
				[ gaUrl ],
				function()
				{
					var timeout = msTimeout || 3000;
					var start_render = new Date().getTime();
					var checkDone = function()
					{
						if( typeof FB !== "undefined" )
						{
							self._init = true;							
							UCMSPlatform.log("Completed to load the facebook sdk!");
							
							cbResult.call( self );
						}
						else if( start_render + timeout >= new Date().getTime())
						{
							// FB 가 완료될 때 까지 대기..
							setTimeout(function()
							{
								checkDone.call( self );
							}
							,200);
						}
						else
						{
							// 지정된 시간 경과된 경우.
							cbResult.call( self, new Error("spa.fb.plugin - Failed to load the facebook sdk!!") );
						}
					};
					
					//
					checkDone.call( self );
				},
				function(err)
				{
					cbResult.call( self, err );
				}
			);
		},			
		activate: function(selector, msTimeout)
		{
			var self = this;
			var tag = null;
			
			if( selector )
			{
				//FB.XFBML.parse( $(selector).attr("id") );
				switch( selector.charAt(0) )
				{
				case '#':
					selector = selector.substr(1);
					tag = document.getElementById( selector );
					break;
					
				case '.':
					selector = selector.substr(1);
					tag = document.getElementByClassName( selector );
					break;
				}
			}
			
			var timeout = msTimeout || 5000;
			var start_render = new Date().getTime();
			var render_plugin = function()
			{
				if( typeof FB !== "undefined" )
				{
					if( tag )
					{
						FB.XFBML.parse( tag );
					}
					else
					{
						FB.XFBML.parse();	
					}
				}
				else if( start_render + timeout >= new Date().getTime())
				{
					// FB 가 완료될 때 까지 대기..
					setTimeout(function()
					{
						render_plugin.call( self );
					}
					,200);
				}
				else
				{
					// 3 초 경과된 경우.
					console.log("fb.plugin.activator - You need to initialize the sdk of facebook!");
				}
			};
			
			//
			render_plugin.call( self );
		},
		
		setShareButton: function(selector, href, width)
		{
			// <div id="share_enongchon" class="fb-share-button" data-href="https://www.enongchon.com" data-width="500px"></div>
			var btn = $(selector);
			
			btn
			.attr("class", "fb-share-button")
			.attr("data-href", href)
			.attr("data-width", width);
		},
		
		setLike: function(selector, href, width, options)
		{
			// <div class="fb-like" data-share="true" data-width="450" data-show-faces="true"></div>
			var btn = $(selector)
			.attr("class", "fb-like")
			.attr("data-href", href)
			.attr("data-share", "true")
			.attr("data-width", width);
			
			if( options )
			{
				if( options.show_faces )
				{
					btn.attr("data-show-faces", options.show_faces);
				}
				if( options.stream )
				{
					btn.attr("data-stream", options.stream);
				}
				if( options.header )
				{
					btn.attr("data-header", options.header);
				}
				if( options.show_border )
				{
					btn.attr("data-show-border", options.show_border);
				}
				if( options.height )
				{
					btn.attr("data-height", options.height);
				}
				if( options.force_wall )
				{
					btn.attr("data-force-wall", options.force_wall);
				}
			}
		},
		
		setActivityFeed: function(selector, action, options)
		{
			// <div class="fb-activity" data-site="developers.facebook.com" data-action="likes, recommends" data-colorscheme="light" data-header="true"></div>
			var btn = $(selector)
			.attr("class", "fb-activity")
			.attr("data-action", action);
			
			if( options )
			{
				if( options.app_id )
				{
					btn.attr("data-app-id", options.app_id);
				}
				if( options.site )
				{
					btn.attr("data-site", site);
				}
				if( options.width )
				{
					btn.attr("data-width", width);
				}
				if( options.colorscheme )
				{
					btn.attr("data-colorscheme", options.colorscheme);
				}
				if( options.header )
				{
					btn.attr("data-header", options.header);
				}
				if( options.show_border )
				{
					btn.attr("data-show-border", options.show_border);
				}
				if( options.linktarget )
				{
					btn.attr("data-linktarget", options.linktarget);
				}
				if( options.recommendations )
				{
					btn.attr("data-recommendations", options.recommendations);
				}
			}
		}
	};
  
	return Helper;
});