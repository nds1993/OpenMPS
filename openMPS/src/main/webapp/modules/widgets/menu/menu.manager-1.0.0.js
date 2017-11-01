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
	var MenuManager = WidgetManagerBase.extend(
	{
		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		constructor: function(options)
		{
			options || ( options = {} );
			options.model = new Backbone.Model({
				title: '메뉴 Manager',
				message: '메뉴 파라메터를 작성해 주세요.'
			});
			MenuManager.__super__.constructor.apply( this, arguments );
		},
		initialize: function(options)
		{
			MenuManager.__super__.initialize.apply( this, arguments );
			this._widget = options;
		}
		,
		_makeItemParams: function()
		{
			var seedParams =
			{
					 "defaultMenu": "main_menu",
					  "main_menu": {
					    "label": "메인메뉴",
					    "order": [
					      "project",
					      "apibulder",
					      "bulder",
					      "gallery"
					    ]
					  },
					  "divider": {
					    "cmd": "divider"
					  },
					  "project": {
					    "label": "프로젝트",
					    "cmd" : "#box/widgetrunner",
			    		"icon" : {pos : "",view: true, type: 'fa',	value: 'fa-paw' },

					  }
					  ,
					  "bulder": {
					    "label": "빌더",
					    "cmd" : "#box/boxbuilder",
			    		"icon" : {pos : "",view: true, type: 'fa',	value: 'fa-paw' },
					  },
					  "apibulder": {
						    "label": "Api 빌더",
						    "cmd" : "#box/apirunner",
				    		"icon" : {pos : "",view: true, type: 'fa',	value: 'fa-paw' },
						  },
					  "gallery": {
					    "label": "갤러리",
					    "cmd" : "#box/gallery",
			    		"icon" : {pos : "",view: true, type: 'fa',	value: 'fa-paw' },
					  },
					"layout" : { 
						// 메뉴의 텍스트 사이즈를 정의합니다. /menu_lg 큰 / menu_md 중간 / menu_sd 작은  /menu_xs 아주작은   -
						// menu_box class 에 class 명으로 추가됩니다. 
						// 값이 없으면  menu_box class 에 값을 추가하지 않습니다. 
						size : "menu_lg", 
						
						// text 정렬을 정희합니다. text-left : 메뉴 왼쪽 정렬 / text-right : 메뉴 오른쪽  정렬 /text-center : 가운데 정렬  
						// menu_box class 에 class 명으로 추가됩니다. 
						// 값이 없으면  menu_box class 에 값을 추가하지 않습니다. 
						align : "text-left", 
						
						// barview : 바 형태의 메뉴 / treeview : 트리구조  메뉴  / horizontal : 가로 메뉴 
						// menu_box class 에 class 명으로 추가됩니다. 
						// 값이 없으면  menu_box class 에 값을 추가하지 않습니다. 
						type :  "treeview",
						
						// true 메뉴검색을 사용합니다.false 는 사용하지 않습니다. 
						search : true 
					}
				};

			return seedParams;
		}
		,
		_getItemSchema: function()
		{
			var schema =
			{
				"title": "Menu Item",
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

	return MenuManager;
});
