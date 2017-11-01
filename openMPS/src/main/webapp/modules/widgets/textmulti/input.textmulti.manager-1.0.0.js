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
	var InputTextMultiManager = WidgetManagerBase.extend(
	{
		template: "#input_textmulti_manager_html",
		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		initialize: function(options)
		{
			InputTextMultiManager.__super__.initialize.apply( this, arguments );
		}
		,
		_makeItemParams: function()
		{
			var seedParams = 
			{
				label		: '선택된 텍스트', 	 	// 해당 위젯의 이
				required 	: true,	 	 		// 필수 요소

				value		:  {	 	// input 배열로 추가
					items : [
					   {label:'항목1',value:"",placeholder:"항목1",size:"input-lg",type:"text",		state:""},
					   {label:'항목2',value:"",placeholder:"항목2",size:"input-xs",type:"password",	state:""},
					   {label:'항목3',value:"",placeholder:"항목3",size:"input-sm",type:"text",		state:""},
					 ]
				}
				,
//				placeholder : '내용 입력 ', 		// 설명
				prop : {
					icon : {
						view: true, 	// 
						type: 'fa',		// fa : 폰트어썸 / img : 이미지 
						value: 'fa-paw' // fa : class명 /  img : img link
					},
					required_view : true,	// 화면에 필수 요소를 노출합니다.  required 는 기능을 의미하고 required_view 는 화면노출에 사용된다.
					desc : '짧은 설명', 	// 부가 설명
//					input_size : 'input-lg', // 폼 사이즈 표시 input-lg: 크게 / input-sm: 작게 / 없음은 보통! 
					label_size : 'label-lg', // 레이블 사이즈 표시 label-lg: 크게 / label-sm: 작게 / 없음은 보통! 
					label_align : 'text-left', // 레이블 텍스트 정렬 text-left | text-center | text-right
					form_layout : 'form-horizontal', 	
									// 디자인 선택 요소 
								 	// 없으면 기본!
								 	// form-inline : 인라인 요소로 표시 
								 	// form-horizontal : 수평폼 표시
								 	// form-only : 레이블 영역 없이 오직 control_box 만 노출
								 	// form-label : 레이블 영역은 존재 레이블은 없음
					multi_layout : { 
						align : null,
						layout : "inline",
					},
				}
			};
			
			return seedParams;
		}
		,
		_getItemSchema: function()
		{
			var schema = 
			{
				"title": "Input Text Item",
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
	
	return InputTextMultiManager;
});