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
	/**
	 * Text 의 생성 파라메터를 생성하는 Manager 모듈
	 */
	var CodeSearchManager = WidgetManagerBase.extend(
	{
		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		constructor: function(options)
		{
			options.model = new Backbone.Model({
				title: '코드 검색 Manager',
				message: '코드 검색 Manager'
			});
			CodeSearchManager.__super__.constructor.apply( this, arguments );
		},
		initialize: function(options)
		{
			CodeSearchManager.__super__.initialize.apply( this, arguments );
			this._widget = options;
		}
		,
		_makeItemParams: function()
		{
			var seedParams = 
			{
				label:'코드검색' 
			};
			
			return seedParams;
		}
		,
		_getItemSchema: function()
		{
			var schema = 
			{
				"title": "CodeSearch Item",
				"$ref": "#/definitions/widgetOptions",
				"definitions": DefinitionsParams
			};
			
			return schema;
		}
		,

		onBeforeShow: function()
		{
			this._createJsonEditor(this.jsoneditor, this._makeItemParams(), this._getItemSchema());
			
		}
	});
	
	return CodeSearchManager;
});