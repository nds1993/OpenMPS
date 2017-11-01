/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2016 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

define(
[ "text!BaroAppSkeleton", "BaroPanelBase", "BaroBox", "Logger" ], 
function( html, BaroPanelBase, BaroBox, Logger )
{
	UCMS.SPA.AppMain.initResource( html );
	
	var instanceMethod = 
	{
		template: "#baroapp_skeleton",
		className: "baroapp_holder",
		regions:
		{
			headerRegion: 		".header_region",
			menubarRegion: 		".menubar_region",
			footerRegion: 		".footer_region",
			contentsRegion: 	".contents_region"
		},
		model: new Backbone.Model()
		,
		
		/**
		 * 앱을 초기화한다.
		 * 
		 * @param skeleton		{ Object } 앱 파라메터 v1.0
		 * @param map			{ Object } 추가 앱 라우팅 맵. 필요한 경우 지정.
		 * @param handler		{ Object } 추가 앱 라이팅 핸들러. 필요한 경우 지정.
		 */
		initialize: function(options)
		{
			BaroPanelBase.prototype.initialize.apply( this, arguments );
			
			var skeleton		= options.skeleton;
			var skeleton_0_8	= options.skeleton_0_8;
			var map				= options.map;
			var handler			= options.handler;
			
			if( typeof skeleton != "undefined" )
			{
				//
				// 바로앱 파리메터 v1.0 규격의 경우
				//
				
				if( typeof skeleton.baroapp == "undefined" )
				{
					Logger.error("BaroAppHolder.initialize() - Not existed a baroapp info!");
					return;
				}
				/*
				if( typeof skeleton.headerRegion == "undefined" )
				{
					Logger.error("BaroAppHolder.initialize() - Not existed a region for Header!");
					return;
				}
				if( typeof skeleton.footerRegion == "undefined" )
				{
					Logger.error("BaroAppHolder.initialize() - Not existed a region for Footer!");
					return;
				}
				if( typeof skeleton.menubarRegion == "undefined" )
				{
					Logger.error("BaroAppHolder.initialize() - Not existed a region for Menubar!");
					return;
				}
				if( typeof skeleton.contentsRegion == "undefined" )
				{
					Logger.error("BaroAppHolder.initialize() - Not existed a region for Contents!");
					return;
				}
				*/
				
				this._makeAppFrame( skeleton );
				this._initBoxSkeleton( skeleton );
			}
			else if( typeof skeleton_0_8 != "undefined" )
			{
				//
				// 바로앱 파라메터 v0.8 의 규격인 경우
				//
				
				this._initBoxSkeleton( skeleton_0_8 );
			}
			
			//
			this._initRouter( map, handler );
			
			Logger.debug("BaroAppHolder.initialize() - complate to initialize a baroapp holder.");
		},
		
		/**
		 * 바로앱을 위한 템플릿 리소스를 생성한다.
		 */
		_makeAppFrame: function(skeleton)
		{
			var holder = this;
			var regionList = [ "headerRegion", "menubarRegion", "footerRegion" ];
			
			for(var idx in regionList)
			{
				var regionId = regionList[idx];
				var widgetParams = skeleton[regionId];
				
				if( widgetParams == undefined )
				{
					Logger.warn("BaroAppHolder._makeAppFrame() - Not used a region : "+regionId);
					continue;
				}
				
				if( typeof widgetParams.module != "string" )
				{
					Logger.into("BaroAppHolder._makeAppFrame() - invalid widget["+regionId+"] params : "+JSON.stringify(widgetParams));
					continue;
				}
				
				if( widgetParams.options == undefined )
				{
					Logger.into("BaroAppHolder._makeAppFrame() - invalid widget["+regionId+"] params : "+JSON.stringify(widgetParams));
					continue;
				}
				
				(function(regionId, widgetParams)
				{
					Logger.debug("_makeAppFrame() - module : "+widgetParams.module);
					
					UCMS.loadModuleByPath
					(
						[ widgetParams.module ], 
						function(Widget)
						{
							var region = holder[regionId];
							
							if( region == undefined )
							{
								Logger.debug("_makeAppFrame() - Not existed region : "+regionId);
								return;
							}
							
							// 해당 위젯을 생성해서 지정된 region 에 출력한다.
							region.show( new Widget( widgetParams.options ) );
						}
						,
						function(err)
						{
							Logger.error(err);
						}
					);
				}
				)(regionId, widgetParams);
			}
		}
		,
		/**
		 * 콘텍츠 박스 파라메터를 초기화한다.
		 */
		_initBoxSkeleton: function(skeleton)
		{
			var metaInfo = skeleton["contentsRegion"].options || skeleton["contentsRegion"];
			if( metaInfo == undefined )
			{
				Logger.error("_initBoxSkeleton() - invalid contents region!");
				return;
			}
			
			for(var idx in metaInfo.items)
			{
				var boxId = metaInfo.items[ idx ];
				
				if( skeleton[boxId]["order"] instanceof Array )
				{
					var templateId = this._makeBoxSkeleton( boxId, skeleton[boxId] );
	
					// 생성된 ContentsBox 용 템플릿 정보를 설정
					skeleton[boxId]["template"] = templateId;
				}
				
				this.model.set( boxId, skeleton[boxId] );
			}

			this.model.set("defaultBox", metaInfo["defaultBox"]);
			Logger.debug("Default Box : "+this.model.get("defaultBox"));
		}
		,
		/**
		 * 콘텐츠 박스의 구조를 템플릿 리소스로 생성한다.
		 * 콘텐츠 박스 내의 region 은 "박스이름"+"_region"의 형식으로 식별된다. 
		 * 
		 * @param boxId			{ String } 콘텐츠 박스 식별자
		 * @param boxParams		{ Object } 콘텐츠 박스를 구성하는 파라메터 객체
		 * @return { String } 생성된 템플릿 리소스 식별자 이름
		 */
		_makeBoxSkeleton: function(boxId, boxParams)
		{
			var templateId = boxId;
			var script = $("<script>").attr("id", templateId).attr("type", "text/template");
			var parentTag = $("<dummy_parent>").append( script );
			var regionList = boxParams["order"];
			
			for(var idx in regionList)
			{
				// TODO region 식별자 지정시 postfix 방식으로 위젯 식별자에 "_region" 을 붙인다.
				script.append( $("<div>").attr("class", regionList[idx]+"_region") );
			}
			
			UCMS.SPA.AppMain.initResource( parentTag.html() );
			UCMS.debug("_makeBoxSkeleton() - templateId : "+templateId+", template : "+parentTag.html());
			
			return "#"+templateId;
		}
		,
		_initRouter: function()
		{
			Logger.error("_initRouter() - Need to override _initRouter().");
		}
		,
		/**
		 * 기본 콘텐츠 박스 식별자를 얻는다.
		 */
		_getDefaultBox: function()
		{
			return this._getBoxOptions("defaultBox");
		}
		,
		/**
		 * 콘텐츠 박스의 설정 옵션을 얻는다.
		 */
		_getBoxOptions: function(boxId)
		{
			return this.model.get( boxId );
		}
		,
		getDefaultBox: function()
		{
			var boxId = this.model.get("defaultBox");
			Logger.debug("getDefaultBox() : "+boxId);
			
			return boxId;
		}
		,
		/**
		 * 지정된 박스를 활성화한다.
		 * 
		 * @param boxId		{ String } contentsBox 식별자
		 */
		showContentsBox: function(boxId)
		{
			var holder = this;
			var params = this._getBoxOptions( boxId );
			
			if( params == undefined || params.module == undefined || params.options == undefined )
			{
				Logger.error("showContentsBox() - invalid box parameters : "+boxId);
				return;
			}
			
			Logger.info("showContentsBox() - module : "+params.module+", box : "+boxId+", options : "+JSON.stringify(params.options));
			
			UCMS.loadModuleByPath
			(
				[ params.module ], 
				function(widgetBox)
				{
					holder.contentsRegion.show( new widgetBox( params.options ) );
					setTimeout(function()
					{
						$("body").scrollTop( 0 );
					});
				}
				,
				function(err)
				{
					Logger.error(err);
				}
			);
		}
		,
		showDefaultBox: function()
		{
			this.showContentsBox( this._getDefaultBox() );
		}
		,
		showContainer: function(containerId)
		{
			var holder = this;

			Logger.info("showContainer() - containerId : "+containerId);
			
			UCMS.loadModuleByPath
			(
				[ "gcon" ], 
				function(GConContainer)
				{
					var options = 
					{
						"className": "gcon_box",
						"title": "게시판",
				        "write": "admin",
				        "caption": "콘텐츠 목록입니다.",
				        "view": null,
				        "type": "bbs_simple",
				        "container_id": containerId
					};
					
					holder.contentsRegion.show( new GConContainer( options ) );
				}
				,
				function(err)
				{
					Logger.error(err);
				}
			);
		}
		,
		showContentViewer: function(contentId)
		{
			var holder = this;

			Logger.info("showContentViewer() - contentId : "+contentId);
			
			UCMS.loadModuleByPath
			(
				[ "gcon_viewer" ], 
				function(GConViewer)
				{
					var options = 
					{
						"cid": contentId
					};
					
					holder.contentsRegion.show( new GConViewer( options ) );
				}
				,
				function(err)
				{
					Logger.error(err);
				}
			);
		}
		,
		showContent: function(box)
		{
			this.contentsRegion.show( box );
		}
	};
	
	var BaroAppHolder = BaroPanelBase.extend( instanceMethod );

	return BaroAppHolder;
});
