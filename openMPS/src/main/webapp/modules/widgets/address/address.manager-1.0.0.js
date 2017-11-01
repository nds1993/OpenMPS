/**
 * Project : MobileOven
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
	 * Checkbox 의 생성 파라메터를 생성하는 Manager 모듈
	 */
	var AddressManager = WidgetManagerBase.extend(
	{
		template: "#address_manager_html",

		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		initialize: function(options)
		{
			AddressManager.__super__.initialize.apply( this, arguments );
			
			//
			this.model = new Backbone.Model();
			
			//
			if( options.nls )
			{
   	    		this.model.set( options.nls );
			}
		}
		,
		onBeforeShow: function()
		{
			var seedParams = 
			{
				label		: '선택된 아이콘',
				required	: false
			};
			this._createJsonEditor(this.jsoneditor, seedParams);
		}
	});
	
	return AddressManager;
});