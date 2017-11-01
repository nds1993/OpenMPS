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
	var ButtonManager = WidgetManagerBase.extend(
	{
		template: "#button_manager_html",
		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		initialize: function(options)
		{
			ButtonManager.__super__.initialize.apply( this, arguments );
		}
		,
		_makeItemParams: function()
		{
			var seedParams = 
			{
				label : "버튼 텍스트",
				icons: [
				        {pos:'prefix',view:true,type:"fa",value:"fa-paw"},
				        {pos:'suffix',view:true,type:"fa",value:"fa-paw"}
				        ],
				button_loyout : {
					style : 'btn-primary', // btn-default / btn-primary / btn-success / btn-info / btn-warning / btn-danger / btn-link
					size : 'btn-lg',  //"" , .btn-lg, "", .btn-sm, .btn-xs  
					align : 'text-left', //text-left // text-center / text-right
					state : null,    // 비활성화 상태 disabled, active 
					sr_only : false,  // sr-only 텍스트 없음 / "" 기본 / text-block : 아이콘과 텍스트가 다른줄
					block : false //
				},
				cmd: "#box/###"	// 명령어 또는 함수를 넘긴다.
			};
			return seedParams;
		}
		,
		_getItemSchema: function()
		{
			var schema = 
			{
				"title": "Button Manager",
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
	
	return ButtonManager;
});