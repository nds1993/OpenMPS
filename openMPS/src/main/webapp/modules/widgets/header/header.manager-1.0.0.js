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
	var HeaderManager = WidgetManagerBase.extend(
	{
		template: "#header_manager_html",
		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		initialize: function(options)
		{
			HeaderManager.__super__.initialize.apply( this, arguments );
		}
		,
		_makeItemParams: function()
		{
			var seedParams = 
			{
				label		: '앱이름', 	 	// 해당 위젯의 이
				icon : {
					pos : 'prefix',  //  pos:prefix/suffix,
					view: true, 	// 
					type: 'fa',		// fa : 폰트어썸 / img : 이미지  / text : 텍스트 입력 
					value: 'fa-paw' // fa : class명 /  img : img link
				},
				pos : "relative", // css poaition 과 같음. fixed : /absolute /relative / static /sticky 
				label_align : 'text-left', // 레이블 텍스트 정렬 text-left | text-center | text-right
				text_color : 'text-primary',
				sr_only : false,  // sr-only 
			};
			return seedParams;
		}
		,
		_getItemSchema: function()
		{
			var schema = 
			{
				"title": "Title Manager",
				"$ref": "#/definitions/widgetOptions",
				"definitions": DefinitionsParams
			};
			
			return schema;
		}
		,

		onBeforeShow: function()
		{
			
 			this._createJsonEditor(this.jsoneditor, this._makeItemParams(), this._getItemSchema());

		},

	});
	
	return HeaderManager;
});