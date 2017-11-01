/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */
 
define
(function()
{
	var definitions =
	{
		"BaroBox": {
			"title": "BaroBox Options",
			"type": "object",
			"properties": {
				"className" : {
					"$ref": "#/definitions/className"
				},
				"height" : {
					"type": "string"
				},
				"order": {
					"$ref": "#/definitions/order"
				},
				"activation": {
					"$ref": "#/definitions/activation"
				}
			}
		},
		"RowBox": {
			"title": "RowBox Options",
			"type": "object",
			"properties": {
				"className" : {
					"$ref": "#/definitions/className"
				},
				"height" : {
					"type": "string"
				},
				"order": {
					"$ref": "#/definitions/order"
				},
				"activation": {
					"$ref": "#/definitions/activation"
				},
				"col_size" :{
					"type": "object"
				},
				"col_option" :{
					"$ref": "#/definitions/col_option"
				}
			}
		},
		"FormBox": {
			"title": "FormBox Options",
			"type": "object",
			"properties": {
				"className" : {
					"$ref": "#/definitions/className"
				},
				"height" : {
					"type": "string"
				},
				"order": {
					"$ref": "#/definitions/order"
				},
				"activation": {
					"$ref": "#/definitions/activation"
				},
				"col_size" :{
					"type": "object"
				},
				"col_option" :{
					"$ref": "#/definitions/col_option"
				},
				"custom_form": {
					"$ref": "#/definitions/custom_form"
				}
			}
		},
		"className": {
			"type": "string",
			"enum": [
				"contents_box",
				"form_panel_box",
				"form_item_box",
				"row"
			]
		},
		"formMode": {
			"type": "string",
			"enum": [
				"page",
				"form"
			]
		},

		"formItem": {
			"type": "object",
			"defaultProperties": [],
			"properties": {
				"label": {
					"type": "string"
				},
				"required": {
					"type": "boolean",
					"format": "checkbox"
				},
				"value" : {
					// 형 선언을 스키마에서는 하지 않고 각 위젯 메니저에서 진행합니다. 
				},
				"prop": {
					"$ref": "#/definitions/widgetOptions"
				},
				"flat": {
					"type": "boolean"
				},
			}
		},
		"custom_form": {
			"title": "Custom Form",
			"type": "object",
			"properties": 
			{
				"name": {
					"type": "string"
				},
				"mode": {
					"$ref": "#/definitions/formMode"
				},
				"template": {
					"type": "string",
					"format": "html",
					"options": {
					    "wysiwyg": true
					}
				},
				"items": {
					"type": "object"
				},
				"data": {
					"type": "object"
				}
			}
		},
		"widgetOptions": {
			"type": "object",
			"defaultProperties":[],
			"properties": {
				"type": {
					"type": "string"
				},
				"id": {
					"type": "string"
				},
				"label": {
					"type": "string"
				},
				"icon": {
					"$ref": "#/definitions/icon"
				},
				"desc": {
					"type": "string",
					"format": "textarea"
				},	
				"required_view" : {
					"type": "boolean",
					"format": "checkbox"
				},
				"input_size": {
					"type": "string",
					"enum": [
						"input-lg",
						"input-sm"
					]
				},
				"label_size": {
					"type": "string",
					"enum": [
						"label-lg",
						"label-sm"
					]
				},
				"label_align": {
					"type": "string",
					"enum": [
						"text-left",
						"text-center",
						"text-right"
					]
				},
				"form_layout": {
					"type": "string",
					"enum": [
						"form-inline",
						"form-horizontal",
						"form-only",
						"form-label"
					]
				},
				"input_type" : {
					"type": "string",
					"enum": [
						"text",
						"password"
					]				
				},
				"title_layout": {
					"type": "string",
					"enum": [
					    // 기본 : 위/아래로 배치      
						"title_inline", // 한줄로 배치 
						"title_full" // 좌우로 배치 
					]
				},
				"title_type": {
					"type": "string",
					"enum": [
						"h1",
						"h2",
						"h3",
						"h4"
					]
				},
				'text_color':{
					"type": "string",
					"enum": [
					         // 기본 : 색 선택 없음
						"text-muted",
						"text-primary",
						"text-success",
						"text-info",
						"text-warning",
						"text-danger"
					]
				},
				"state" : {
					"type": "string",
					"enum": [
								"disabled",
								"readonly"
							]
				},
				"row" : {
					"type":"integer",
					"format": "number",

				},
				"col" : {
					"type":"integer",
					"format": "number",
				},
				"resize" : {
					"type" : "string",
					"enum" : [
					          "both", // 허용 
					          "hresize", //horizontal
					          "vresize", //vertical
					          "noresize" 
					          ],
					"default": "noresize"
				},
				"multi_layout" :{
					"$ref": "#/definitions/multi_layout"
				},
				"prefix_addon" :{
					"$ref": "#/definitions/icon"
				},
				"suffix_addon" :{
					"$ref": "#/definitions/icon"
				},
				"maxCheckBox" : { // 체크 최대 선택 수 
					"type" : "integer",
					"format": "number"
				},
				"minCheckBox" : { // 체크 최소 선택 수 
					"type" : "integer",
					"format": "number"},
				"maxlength": {
					"type": "integer",
					"format": "number"
				},
				"minlength": {
					"type": "integer",
					"format": "number"
				},
				"placeholder": {
					"type": "string"
				},
				"button_loyout" : {
					"$ref": "#/definitions/button_loyout"
				},
				"pos" : {
					"$ref": "#/definitions/pos"
				}
			}
		},
		"icon": {
			"type": "object",
			 "properties": {
				 "pos" : { // 위
						"type": "string",
						"enum": [
									"prefix", // 폰트어썸 선택 
									"suffix"  // 이미지 선
								]
				 },
				"view": {
					"type": "boolean",
					"format": "checkbox"
				},
				"type": {
					"type": "string",
					"enum": [
								"fa", // 폰트어썸 선택
								"text", // 텍스트 입력
								"img" // 이미지 선택 
							]
				},
				"value": {
					"type": "string",
				}
			},
		},
		"order": {
			"title": "Widget Order",
			"type": "array",
			"items": {
				"type": "string"
			}
		},
		"activation": {
			"title": "Switching Method",
			"type": "object",
			"properties": {
				"defaultWidget": {
					"type": "string"
				},
				"method": {
					"$ref": "#/definitions/switchingMethod"
				},
				"navigator":{
					"type": "object",
					"properties": {
						"module" : {
							"type": "string"
						},
						"options": {
							"type": "object",
							"properties": {
								"label": {
									"type": "string"		
								},
								"pos": {
									"type": "string",
									"enum" : [
									          "left", 
									          "top",
									          "right",
									          "bottom" 
									          ],
									"default": "top"
								},
								"control": {
									"type": "object",
									"properties": {
										"close": {
											"type": "boolean"
										},
										"scroll": {
											"type": "boolean"
										},
										"exceptClose": {
											"type": "array",
											"items": {
												"type": "string"
											}
										},
									}
								}
							}
						}
					}
				}
			}
		},
		"switchingMethod": {
			"type": "string",
			"enum": [
				"one-page",
				"hori-swipe",
				"vert-swipe",
				"show-hide"
			]
		},
		"widgetParams": {
			"type": "object",
			"properties": {
				"module": {
					"type": "string"
				},
				"options": {
					"type": "object"
				}
			}
		},
		"divider" : { // 디바이더 
			"type" : "object",
			"properties": {
				"view": {
					"type": "boolean",
					"format": "checkbox"
				},
				"type": {
					"type": "string",
					"enum": [
					         	"blank",
								"line", // 폰트어썸 선택 
								"dot" // 이미지 선
							]
				},
				"divider_margin": {
					"type": "string",
					"enum": [
					         	// 기본값 
					         	"divider_margin_lg",
					         	"divider_margin_sm",
							]	
				},				
			}
		}
		,
		"columns": {
			"title": "Column Info",
			"type" : "object",
			"properties": {
				"col_size" :{
					"type": "object"
				},
				"col_option" :{
					"$ref": "#/definitions/col_option"
				}
			}
		}
		,
		"col_option": {
			"title": "Column Option",
			"type": "string",
			"enum": ["xs", "sm", "md", "ls"]
		}
		,
		"multi_layout" : { //multi_layout 파라메터 
			"type" : "object",
			"properties": {

				"align" :{
					"$ref": "#/definitions/text_align"
				},
				"layout" :{
					"type": "string",
					"enum": [
								"inline",
								"block"
							]
				}
			}
		}
		,
		"text_align": {
			"type": "string",
			"enum": [
				"text-left",
				"text-center",
				"text-right"
			]
		}
		,
		"color" :{
			"type": "string",
			"enum": [
			         // 기본
					"primary",
					"info",
					"success",
					"warning",
					"danger",
					]
		},
		"button_loyout" : {
			"type" : "object",
			"properties": {
				"style" : {
					"type": "string",
					"enum": [
					         	"btn-default",
								"btn-primary",
								"btn-success",
								"btn-info",
								"btn-warning",
								"btn-danger",
								"btn-link"
							]	
				},
				"size" : {
					"type": "string",
					"enum": [
					         	"btn-lg",
								"btn-sm",
								"btn-xs"
							]	
				},
				"align" :{
					"$ref": "#/definitions/text_align"
				},
				"state" :{
					"type": "string",
					"enum": [
								"disabled",
								"active"
							]
				},
				"sr_only" : {
					"type": "boolean",
					"format": "checkbox"				
				},
				"block" : {
					"type": "boolean",
					"format": "checkbox"
				}
			}
		},
		pos : {
			"type" : "string",
			"enum": [
				"relative", // css poaition 과 같음. fixed : /absolute /relative / static /sticky 
				"fixed",
				"absolute",
				"relative",
				"static",
				"sticky"
				]
		}

	};
	return _.extend({}, definitions);
});