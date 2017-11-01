/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
(
[
	"WidgetManagerBase-1.0.0", "BaroProps", "Logger"
]
,
function( WidgetManagerBase, BaroProps, Logger)
{
	var	FileSelectorManager = WidgetManagerBase.extend(
	{
		template: "#fileselector_manager_panel"
		,
		regions:
		{
		}			
		,
		ui:
	    {
	    }
		,
		events:
		{
		}
		,
		initialize: function(options)
		{
			FileSelectorManager.__super__.initialize.call(this, options);
        }
		,
        onRender: function()
        {
        }
		,
		onShow: function()
		{
		}
		,
		getItemData: function()
		{
			Logger.info("FileSelectorManager::getItemData() - Need to implement a this method.");
		}
	});
	
	return FileSelectorManager;
});