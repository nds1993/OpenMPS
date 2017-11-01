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
	var InputCheckManager = WidgetManagerBase.extend(
	{
		template: "#input_check_manager_html",

		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		initialize: function(options)
		{
			InputCheckManager.__super__.initialize.apply( this, arguments );
		}
		,
		_makeItemParams: function()
		{
			var seedParams = 
			{
				name			: 'item_id1', // checkbox 항목에 name으로 넘기고 있습니다. 
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
					        {label:'항목1',value:1,color:"text-primary",size:"lg",state:"disabled"},
					        {label:'항목2',value:2,color:"text-info",size:"sm",state:""},
					        {label:'항목3',value:3,color:"text-warning",size:"",state:"lg"},
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
						layout : null,
					},
					maxCheckBox : 4, // 최대 선택 수
					minCheckBox : 1, // 최소 선택 수 

				}
			};
			return seedParams;
		}
		,
		_getItemSchema: function()
		{
			var schema = 
			{
				"title": "Checkbox Item",
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
	
	return InputCheckManager;
});