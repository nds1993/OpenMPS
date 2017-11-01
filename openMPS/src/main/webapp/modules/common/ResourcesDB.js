/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
(function()
{
	/*
	 * 모듈 식별자 네이밍 규칙
	 *
	 * 1. "모듈이름-버전" 과 같은 형식으로 지정한다.
	 * 2. 모듈 이름과 모듈 디렉토리 이름은 같은 이름으로 지정하며,
	 * 3. 디렉토리 이름은 모두 소문자로 지정하다.
	 * 4. 기본적으로 파스칼 표기법(PascalCase)과 카멜 표기법(camelCase)을 적용하는 것을 원칙으로 하며,
	 *    상세한 위젯 네이밍 규칙은 위젯 개발사의 정책을 따른다.
	 */

	var libRepo =
	{
		"jquery" : "/lib/jquery-1.9.1",
	};

	var pluginRepo =
	{
    	"excanvas" : "/plugin/excanvas",
    	"jcrop" : "/plugin/imageeditor/jquery.Jcrop",
    	"imagecanvas-0.8.1" : "/plugin/imageeditor/imagecanvas-0.8.1",

    	//
    	"DatetimePickerV4" : "/plugin/bootstrap/js/bootstrap-datetimepicker.min",

    	//
    	"jqgrid-5.2.1" : "/plugin/jqgrid/jquery.jqgrid.min",
    	"jqgrid-5.2.1-i18n" : "/plugin/jqgrid/i18n/min/grid.locale-en",

    	// 4.7.1 버전
    	"jqgrid4-4.7.0" :		"/plugin/jqgrid4/js/minified/jquery.jqGrid.min",
    	//"jqgrid4-4.7.0" :		"/plugin/jqgrid4/js/jquery.jqGrid",
    	"jqgrid4-4.7.0-i18n" : 	"/plugin/jqgrid4/js/minified/i18n/grid.locale-kr",

    	"xml2json" : "/plugin/xml2json",
		"TableCSVExport" : "/plugin/CSVExport/jquery.TableCSVExport",
		
		//
		"jquery-mCustomScrollbar-3.1.5" : "/plugin/mCSB/jquery.mCustomScrollbar",
		
		//
		"RealGrid-1.1.25-lic" :		"/plugin/realgridjs_eval.1.1.25/realgridjs-lic",
		"RealGrid-1.1.25-js" :		"/plugin/realgridjs_eval.1.1.25/realgridjs_eval.1.1.25.min"
	};

	var commonRepo =
	{
		"SPA.Platform.Helper" : "/common/spa.platform.helper",
		"WidgetManagerBase-1.0.0" : "/common/widget.manager.base-1.0.0",
		"DefinitionsParams" : "/common/DefParams",
		"AppRepositories" : "/common/AppRepositories"
	};

	var widgetRepo =
	{
		"Breadcrumbs-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/breadcrumbs" // 페이지 경로를 보여주는 위젯
		},
		"ButtonMulti-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/buttonmulti"
		},
		"Button-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/button"
		},
		"Header-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/header"
		},
		"Menu-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/menu"
		},
		"cover-0.8.1" : {
			"ver":0x00080001,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/cover"
		},
		"DesignItem-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/designitem"
		},
		"file-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/file"
		},
		"iconmenu-0.8.1" : {
			"ver":0x00080001,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/iconmenu"
		},
    	"IconPicker-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/iconpicker"
		},
		"imageeditor-0.8.1" : {
			"ver":0x00080001,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/imageeditor"
		},
		"JsonEditor-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/jsoneditor"
		},
		"jqGrid-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/jqgrid"
		},
		"jqGrid4-1.0.0" : {
			"ver":0x01000000,
			"desc":'jqgrid 4.7.0 버전',
			"thumbnail":null,
			"detail": "/widgets/jqgrid4"
		},
		"map-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/map"
		},
		"Title-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/title"
		},
		"RealGrid-1.1.25" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/realgrid"
		}
	};

	var formWidgetRepo =
	{
		"TextGroup-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/textgroup"
		},
		"TextMulti-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/textmulti"
		},

		"Radio-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/radio"
		},

		"address-1.3.0"	: {
			"ver":0x01030000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/address"
		},
		"Checkbox-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/checkbox"
		},
		"Combobox-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/combobox"
		},
		"IconHolder-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/iconholder"
		},
		"ImageHolder-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/imgholder"
		},
		"Text-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/text"
		},
		"Textarea-1.0.0" : {
			"ver":0x01000000,
			"desc":'모듈에 대한 설명을 입력해주세요.',
			"thumbnail":null,
			"detail": "/widgets/textarea"
		}
	};

	/**
	 * 앱 리소스 정보에서 Module Map 을 생성한다.
	 *
	 * @param {object}	앱 리소스 목록
	 */
	function createResourceMap(repoList)
	{
		var map = {};

		for(var rid in repoList)
		{
			if( repoList[rid].detail )
			{
				map[rid] = repoList[rid].detail;
			}
		}

		return map;
	}

	// 현재는 패널을 아래와 같이 파라메터를 직접 등록합니다.
  // 패널명을 프로그램 ID 로 등록함.
	var panelRepo =
	{
		"HomePanel" : {
			"ver":0x01000000,
			"desc":'BaroBox 용 파라메터 샘플',
			"thumbnail":null,
			"detail": '/repo/box/box_HomePanel.json'
		},
		"RowPanel" : {
			"ver":0x01000000,
			"desc":'RowBox 용 파라메터 샘플',
			"thumbnail":null,
			"detail": '/repo/box/box_RowPanel.json'
		},
		"PageHeaderPanel" :{
			"ver":0x01000000,
			"desc":'각 페이지별 헤더를 정의합니다.멀티버트/타이틀/경로가 포함됩니다.',
			"thumbnail":null,
			"detail": '/repo/box/box_PageHeaerPanel.json'
		},
		"IntroHome" : {
			"ver":0x01000000,
			"desc":'데모앱의 인트로 패널',
			"thumbnail":null,
			"detail": '/repo/box/box_IntroHome.json'
		},
		"KakaoOnepage" : {
			"ver":0x01000000,
			"desc":'카카오 콘텐츠 One Page',
			"thumbnail":null,
			"detail": '/repo/box/box_kakao1.json'
		},
		"KakaoTab" : {
			"ver":0x01000000,
			"desc":'카카오 콘텐츠 Tab 보기',
			"thumbnail":null,
			"detail": '/repo/box/box_kakao2.json'
		},
		"sampleLayout" : {
			"ver":0x01000000,
			"desc":'openMPS 화면에 사용되는 레이아웃 샘플입니다.',
			"thumbnail":null,
			"detail": '/repo/box/sampleLayout.json'
		}

	};

	var formRepo =
	{
		"TestFormPanel" : {
			"ver":0x01000000,
			"desc":'FormBox 용 파라메터 샘플',
			"thumbnail":null,
			"detail": '/repo/box/box_TestFormPanel.json'
		}
	};

	var recipeRepo =
	{
		"BaseLayout" : {
			"ver":0x01000000,
			"desc":'openMPS 페이지 기본 레이아웃이 적용된 앱 샘플',
			"thumbnail":null,
			"detail": '/repo/recipe/base_layout.json'
		},
		"RowPanelHome" : {
			"ver":0x01000000,
			"desc":'RowPanel 을 Home 으로 하는 바로앱 샘플',
			"thumbnail":null,
			"detail": '/repo/recipe/baroapp.json'
		},
		"HtmlTest" : {
			"ver":0x01000000,
			"desc":'html방식으로 정리된 앱레시피 샘플',
			"thumbnail":null,
			"detail": '/repo/recipe/html_sample.json'
		}
	};

	var skinRepo =
	{
		"Default" : {
			"ver":0x01000000,
			"desc":"NDS 스킨",
			"thumbnail":null,
			"detail": '/nds/nds-0.1.0.css'
		}
	};

	var Resource =
	{
		getRepo : function()
		{
			return {
				"plugin" : { label: "Plug-in", repo: pluginRepo },
				"common" : { label: "Common Modules", repo: commonRepo },
				"widget" : { label: "Widget Modules", repo: widgetRepo },
				"formWidget" : { label: "Form Widget Modules", repo: formWidgetRepo },
				"panel" : { label: "Panel Modules", repo: panelRepo },
				"formPanel" : { label: "Form Panel Modules", repo: formRepo },
				"skin" : { label: "Skins", repo: skinRepo },
				"appRecipe" : { label: "App Recipes", repo: recipeRepo	}
			};
		}
		,
		getGallery : function()
		{
			return {
				"widget" : { label: "Widget Modules", repo: widgetRepo },
				"formWidget" : { label: "Form Widget Modules", repo: formWidgetRepo },
				"panel" : { label: "Panel Modules", repo: panelRepo },
				"formPanel" : { label: "Form Panel Modules", repo: formRepo },
				"skin" : { label: "Skins", repo: skinRepo },
				"appRecipe" : { label: "App Recipes", repo: recipeRepo	}
			};
		}
		,
		getResourceMap : function()
		{
			return _.extend(
					{}
					, libRepo
					, pluginRepo
					, commonRepo
					, createResourceMap( widgetRepo )
					, createResourceMap( formWidgetRepo ));
		}
	};

	return Resource;
});
