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
	var ButtonMultiManager = WidgetManagerBase.extend(
	{
		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		constructor: function(options)
		{
			options.model = new Backbone.Model({
				title: '멀티 버튼 Manager',
				message: '멀티 버튼 파라메터를 작성해 주세요.'
			});
			ButtonMultiManager.__super__.constructor.apply( this, arguments );
		},
		initialize: function(options)
		{
			ButtonMultiManager.__super__.initialize.apply( this, arguments );
			this._widget = options;
		}
		,
		_makeItemParams: function()
		{
			var seedParams = 
			{
				btn_item: [
				   /*	
				    * label:'버튼1', // 버튼에 쓰여질 텍스트입니다. 
				    * icon:{view:true,type:"fa",value:"fa-paw"},  // 기존 아이콘과 동일합니다.
				    * style : 'btn-primary btn-lg text-left', // 버튼에 추가할 수 있는 클래스 입니다. 색상과 크기 및 각종 class 를 추가할 수 있습니다.  btn-default
				    * sr_only:true,  // 버튼에서 텍스트를 표시하지 않습니다.
				    * state : null, // 비활성화 표시 
				    * cmd:"" // 명령어를 입력합니다. 
				   */
				   {label:'버튼1',icon:{view:true,type:"fa",value:"fa-paw"}, style : 'btn-primary',sr_only:true, state : null, cmd:"" },
				   {label:'버튼1',icon:{view:true,type:"fa",value:"fa-paw"}, style : 'btn-default',sr_only:false, state : "disabled", cmd:"" },
				   {label:'버튼1',icon:{view:true,type:"fa",value:"fa-paw"}, style : 'btn-info',sr_only:false, state : null, cmd:"" }
				 ],
				btn_group_layout : "btn-group",//, "btn-inline" // btn-group : 버튼 그룹 으로 처리 //btn-inline : 개별 버튼으로 처리
				text_align : "text-left" // 텍스트 정렬 	
			};
			
			return seedParams;
		}
		,
		_getItemSchema: function()
		{
			var schema = 
			{
				"title": "Button Multi Item",
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
	
	return ButtonMultiManager;
});