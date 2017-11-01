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
function( Logger, BaroFloating, WidgetManagerBase, JsonEditor, DefinitionsParams )
{
	/**
	 * Textarea 의 생성 파라메터를 생성하는 Manager 모듈
	 */
	var InputTextareaManager = WidgetManagerBase.extend(
	{
		template: "#input_textarea_manager_html",
		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		initialize: function(options)
		{
			InputTextareaManager.__super__.initialize.apply( this, arguments );
		}
		,
		_makeItemParams: function()
		{
			var seedParams = 
			{
				label		: '선택된 텍스트', 	 	// 해당 위젯의 이
				required	: true,	 	 		// 필수 요소 
				maxlength	: 30, 			 	// 최대 글자
				minlength	: 0, 			 	// 최소 글자
				value		: null, 		 	// 값 설정 
				placeholder : '내용 입력 ', 		// 설명
				prop		: {
					icon : {
						view: true, 	// 
						type: 'fa',		// fa : 폰트어썸 / img : 이미지 
						value: 'fa-paw' // fa : class명 /  img : img link
					},
					required_view : true,	// 화면에 필수 요소를 노출합니다.  required 는 기능을 의미하고 required_view 는 화면노출에 사용된다.
					desc : '짧은 설명', 	// 부가 설명
					input_size : 'input-lg', // 폼 사이즈 표시 input-lg: 크게 / input-sm: 작게 / 없음은 보통! 
					label_size : 'label-lg', // 레이블 사이즈 표시 label-lg: 크게 / label-sm: 작게 / 없음은 보통! 
					label_align : 'text-left', // 레이블 텍스트 정렬 text-left | text-center | text-right
					
					form_layout : 'form-horizontal', 	
									// 디자인 선택 요소 
								 	// 없으면 기본!
								 	// form-inline : 인라인 요소로 표시 
								 	// form-horizontal : 수평폼 표시
								 	// form-only : 레이블 영역 없이 오직 control_box 만 노출
								 	// form-label : 레이블 영역은 존재 레이블은 없음
					state		: null, 	// 현재 폼의 상태 -disabled : 비활성화 상태 /-readonly : 읽기전용 상태
					
					row : 5, // null 4, // 텍스트 줄수 (세로 크기)
					col : null, // 80, 택스트칼럼수 ( 가로 크기 )
					resize : "noresize" // null : both |hresize: horizontal |vresize: vertical | none | initial // 크기변경 하기 
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
	
	return InputTextareaManager;
});