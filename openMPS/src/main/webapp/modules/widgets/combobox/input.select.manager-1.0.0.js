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
	 * Combobox 의 생성 파라메터를 생성하는 Manager 모듈
	 */
	var InputSelectManager = WidgetManagerBase.extend(
	{
		template: "#input_select_manager_html",

		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		initialize: function(options)
		{
			InputSelectManager.__super__.initialize.apply( this, arguments );
		}
		,
		_makeItemParams: function()
		{
			var seedParams = 
			{
				label		: '선택된 값',
				required	: false,
				value		: {
					items: [
					        {label:'선택해주세요',value:0},
					        {label:'항목1',value:1},
					        {label:'항목2',value:2},
					        {label:'항목3',value:3}
					        ],
					selected: 3
				},
				prop : {
					icon : {
						view: true, 
						type: 'fa',	
						value: 'fa-mouse-pointer'
					},
					required_view : true,	// 화면에 필수 요소를 노출합니다.  required 는 기능을 의미하고 required_view 는 화면노출에 사용된다.
					desc : '짧은 설명', 
					input_size : 'input-lg',
					label_size : 'label-lg',
					label_align : 'text-left',
					form_layout : 'form-horizontal', 	
					state		: 'disabled'
				}
			};
			return seedParams;
		}
		,
		_getItemSchema: function()
		{
			var schema = 
			{
				"title": "Input Select Item",
				"$ref": "#/definitions/formItem",
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
	
	return InputSelectManager;
});