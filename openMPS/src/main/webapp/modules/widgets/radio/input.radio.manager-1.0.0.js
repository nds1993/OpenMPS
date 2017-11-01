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
	var InputRadioManager = WidgetManagerBase.extend(
	{
		template: "#input_radio_manager_html",

		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		initialize: function(options)
		{
			InputRadioManager.__super__.initialize.apply( this, arguments );
		}
		,
		_makeItemParams: function()
		{
			var seedParams = 
			{
				name 		: 'item_id1', // checkbox에 name 으로 값을 넘기고 있습니다. 
				label		: '선택된 값',
				required	: false,
				value		: {
					items: [
					        //color : "<p class="text-muted">...</p>
/*<p class="text-primary">...</p>
<p class="text-success">...</p>
<p class="text-info">...</p>
<p class="text-warning">...</p>
<p class="text-danger">...</p>*/
					        //size : ""/sm, lg
					        //state : ""/disabled, readonly
					        {label:'항목1',value:1,color:"text-primary",size:"lg",state:""},
					        {label:'항목2',value:2,color:"",size:"lg",state:""},
					        {label:'항목3',value:3,color:"text-danger",size:"lg",state:""},
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
					label_size : 'label-lg',
					label_align : 'text-left',
					form_layout : 'form-horizontal', 	
					multi_layout : {
						align : null,
						layout : null 
					}
				}
			};
			return seedParams;
		}
		,
		_getItemSchema: function()
		{
			var schema = 
			{
				"title": "Radio Item",
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
	
	return InputRadioManager;
});