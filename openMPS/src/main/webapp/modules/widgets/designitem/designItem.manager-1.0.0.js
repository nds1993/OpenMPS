/**
 * Project : MobileOven
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
([
	"Logger",
	
	"BaroFloating",
	"WidgetManagerBase-1.0.0",
	"DefinitionsParams"
]
,
function( Logger, BaroFloating, WidgetManagerBase, DefinitionsParams )
{
	var DesignItemManager = WidgetManagerBase.extend(
	{
		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		constructor: function(options)
		{
			options.model = new Backbone.Model({
				title: 'DesignItem Widget Manager',
				message: '디자인 항목을 html 로 입력해 주세요.'
			});
			DesignItemManager.__super__.constructor.apply( this, arguments );
		}
		,
		initialize: function(options)
		{
			DesignItemManager.__super__.initialize.apply( this, arguments );
		}
		,
		_makeItemParams: function()
		{
			var seedParams = 
			{
				html: "<h1>tagName, className 을 추가할 수 있습니다.</h1><hr>"
			};
			return seedParams;
		}
		,
		_getItemSchema: function()
		{
			var schema = 
			{
				"title": "Design Item",
				"type": "object",
				"defaultProperties":[],
				"properties": {
					"tagName": {
						"type": "string",
						"format": "text"
					},
					"className": {
						"type": "string",
						"format": "text"
					},
					"html": {
						"type": "string",
						"format": "textarea"
					}
				}
			};
			
			return schema;
		}
		,

		onBeforeShow: function()
		{
			
 			this._createJsonEditor(this.jsoneditor, this._makeItemParams(), this._getItemSchema());

		},

	});
	
	return DesignItemManager;
});