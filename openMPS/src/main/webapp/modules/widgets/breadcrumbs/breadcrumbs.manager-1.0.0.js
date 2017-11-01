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
				title: '경로 Manager',
				message: '경로 파라메터를 작성해 주세요.'
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
				breadcrumbs_item: [
				   /*	
				    *{ 
				    * label:'버튼1', // 버튼에 쓰여질 텍스트입니다. 
				    * icon:{view:true,type:"fa",value:"fa-paw"},  // 기존 아이콘과 동일합니다. //값이 없을 때 대비 
				    * cmd:"" // 명령어를 입력합니다. 
				    * icon:{view:true,type:"fa",value:"fa-paw"}, 
				    * active : true , 없어도 됨 트루이면 해당 li 에 active 클래스 삽입  
				    * cmd:"#aa" : 값이 없어도 됨 . 있으면 해당 링크 이동 가능
				    * }
				    *  
				   */
				   {label:'영업' },
				   {label:'자료입력',cmd : "#asdf"},
				   {label:'주문입력',icon:{view:true,type:"fa",value:"fa-paw fa-spin"}, active :true , cmd: null}
				 ],
				breadcrumbs_layout : "", // 필요에 의해 채워 넣으세요. 클래스명을 넣으시면 됩니다. 
				text_align : "text-right" // 텍스트 정렬 	
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