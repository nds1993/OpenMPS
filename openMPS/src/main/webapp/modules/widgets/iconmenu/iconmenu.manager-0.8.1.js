/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
([
	"Logger",
	
	"BaroFloating",
	"WidgetManagerBase-1.0.0"
]
,
function( Logger, BaroFloating, WidgetManagerBase )
{
	/**
	 * Text 의 생성 파라메터를 생성하는 Manager 모듈
	 */
	var IconMenuManager = WidgetManagerBase.extend(
	{
		template: "#iconmenu_box_manager_html",

		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		initialize: function(options)
		{
			IconMenuManager.__super__.initialize.apply( this, arguments );
		}
		,
		onBeforeShow: function()
		{
			var seedParams = 
			{
				"items" : [ 
	                        {
	                            "box_id" : "introMenu",
	                            "onclick" : "switching",
	                            "caption" : "About Me",
	                            "icon_font" : "fa-coffee"
	                        }, 
	                        {
	                            "box_id" : "photoMenu",
	                            "onclick" : "switching",
	                            "caption" : "Portfolio",
	                            "icon_font" : "fa-camera-retro"
	                        }, 
	                        {
	                            "box_id" : "careerMenu",
	                            "onclick" : "switching",
	                            "caption" : "Career",
	                            "icon_font" : "fa-pencil-square"
	                        }, 
	                        {
	                            "box_id" : "contactMenu",
	                            "onclick" : "switching",
	                            "caption" : "Contact Me",
	                            "icon_font" : "fa-at"
	                        }
	             ]
			};
			this._createJsonEditor(this.jsoneditor, seedParams);
		}
	});
	
	return IconMenuManager;
});