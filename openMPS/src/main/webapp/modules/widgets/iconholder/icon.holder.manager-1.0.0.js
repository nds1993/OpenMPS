/**
 * Project : MobileOven
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
([
	"Logger",
	
	"BaroFloating",
	"WidgetManagerBase-1.0.0"
]
,
function( Logger, BaroFloating, WidgetManagerBase )
{
	/**
	 * IconHolder 의 생성 파라메터를 생성하는 Manager 모듈
	 */
	var IconHolderManager = WidgetManagerBase.extend(
	{
		template: "#icon_holder_manager_html",

		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		initialize: function(options)
		{
			IconHolderManager.__super__.initialize.apply( this, arguments );
		}
		,
		onBeforeShow: function()
		{
			var seedParams = 
			{
			//	type		: 'iconbox',
				label		: '선택된 아이콘',
				required	: false,
				value		: {
					type: 'fa', 
					icon: 'fa-home'
				},
				prop		: {
					icon : {
						pos: 'prefix',
						view: true,
						type: 'fa',
						value: 'fa-paw' 
					},
					required_view : true,	// 화면에 필수 요소를 노출합니다.  required 는 기능을 의미하고 required_view 는 화면노출에 사용된다.
					desc : '짧은 설명'
				}
			};
			this._createJsonEditor(this.jsoneditor, seedParams);
		}
	});
	
	return IconHolderManager;
});