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
	var CoverManager = WidgetManagerBase.extend(
	{
		template: "#cover_box_manager_html",

		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		initialize: function(options)
		{
			CoverManager.__super__.initialize.apply( this, arguments );
		}
		,
		onBeforeShow: function()
		{
			var seedParams = 
			{
                "items" : 
                [ 
                    {
                        "link" : "#home",
                        "bg_path" : "http://resource.moven.net/themes/base/asset/bg/img_640_025.jpg",
                        "caption" : "나를 표현해 보자!"
                    }
                ]
			};
			this._createJsonEditor(this.jsoneditor, seedParams);
		}
	});
	
	return CoverManager;
});