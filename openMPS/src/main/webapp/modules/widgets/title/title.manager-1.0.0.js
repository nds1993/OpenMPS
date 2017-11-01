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
	var TitleManager = WidgetManagerBase.extend(
	{
		template: "#title_manager_html",
		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		initialize: function(options)
		{
			TitleManager.__super__.initialize.apply( this, arguments );
		}
		,
		_makeItemParams: function()
		{
			var seedParams = 
			{
				label		: '타이틀을 입력해 주세요', 	 	// 해당 위젯의 이
				icon : {
					pos : 'prefix',  //  pos:prefix/suffix,
					view: true, 	// 
					type: 'fa',		// fa : 폰트어썸 / img : 이미지 
					value: 'fa-paw' // fa : class명 /  img : img link
				},
				desc : '짧은 설명', 	// 부가 설명
				label_align : 'text-left', // 레이블 텍스트 정렬 text-left | text-center | text-right
				title_layout : "", //"" : 기본형 / inline / full / 
				title_type : "h1",
				text_color : 'text-primary'
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
	
	return TitleManager;
});