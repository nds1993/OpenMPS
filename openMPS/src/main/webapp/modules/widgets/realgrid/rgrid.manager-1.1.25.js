/**
 * Project : MobileOven
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
([
	"Logger",
	"WidgetManagerBase-1.0.0",
	"DefinitionsParams"
]
,
function( Logger, WidgetManagerBase, DefinitionsParams )
{
	var RealGridManager = WidgetManagerBase.extend(
	{
		template: "#realgrid_manager_html",

		regions:
		{
			"jsoneditor" : ".jsoneditor_region"
		},
		
		initialize: function(options)
		{
			RealGridManager.__super__.initialize.apply( this, arguments );
		}
		,
		_makeItemParams: function()
		{
			var seedParams = 
			{
				gridId: "realgrid",
				gridParams:
				{
					"displayOptions": {
						"fitStyle": "even"
					},
					"editOptions": {
						enterToNextRow: true,
						enterToTab: true,
						editable: true
					},
				    "panel": {
				        "visible": false
				    },
				    "header": {
				        "minHeight": 50
				    },
				    "footer": {
				        "visible": false
				    },
				    "indicator": {
				        "visible": true
				    },
				    "stateBar": {
				        "visible": true
				    },
				    "checkBar": {
				    	"visible": true
				    },
					columns: [
				        {
				            name: "col1",
				            fieldName: "field1",
				            header : {
				                text: "직업"
				            },
				            width : 60,
				            "mergeRule": {
				                criteria: "value"
				            },
				            "styles": {
				            	"textAlignment": "near"
				            },
				            "button": "action",
				            "alwaysShowButton": true
				        },
				        {
				            name: "col2",
				            fieldName: "field2",
				            header : {
				                text: "성별"
				            },
				            width: 50,
				            "mergeRule": {
				                criteria: "value"
				            },
				            "styles": {
				            	"textAlignment": "center"
				            }
				        },
				        {
				            name: "col3",
				            fieldName: "field3",
				            header : {
				                text: "이름"
				            },
				            width: 80,
				            "footer": {
				                "styles": {
				                    "textAlignment": "far",
				                    "font": "굴림,12"
				                },
				                "text": "합계 : ",
				                "groupText": "합계 =>"
				            },
				            "styles": {
				            	"textAlignment": "far"
				            },
				            "button": "action",
				            "alwaysShowButton": false,
				            "editable": false
				        },
				        {
				        	"type": "group",
				            "name": "교과목",
				            "orientation": "horizontal",
				            "width": 660,
				            "columns": [
				            	{
						            name: "col11",
						            fieldName: "field11",
						            header : {
						                text: "시험날짜"
						            },
						            width: 100,
						            "styles": {
						            	"textAlignment": "center",
						            	"datetimeFormat":"yyyy-MM-dd"
						            },
						            "editor": {
						            	"type":"date",
						            	"mask": {
						            		"editMask":"9999-99-99", //표시되는 형식
						                    "placeHolder":"yyyy-MM-dd", //편집기에 표시될 형식
						                    "includedFormat":true //편집기에 표시된 내용이 그대로 셀값으로 전달
						            	},
						            	"datetimeFormat": "yyyy-MM-dd"
						            },
						            "displayRegExp": "([0-9]{4})([0-9]{2})([0-9]{2})",
						            "displayReplace": "$1-$2-$3"
						        },
				            	{
						            name: "col4",
						            fieldName: "field4",
						            header : {
						                text: "국어"
						            },
						            width: 80,
						            "footer": {
						                "styles": {
						                    "textAlignment": "far",
						                    "numberFormat": "#,##0",
						                    "suffix": " $",
						                    "font": "Arial,12"
						                },
						                "text": "소계 : ",
						                "expression": "sum",
						                "groupText": "합계",
						                "groupExpression": "sum"
						            },
						            "validations": []
						        },
						        {
						            name: "col5",
						            fieldName: "field5",
						            header : {
						                "text": "수학",
										"subText": "|",
										"subTextGap": 4,
										"subTextLocation": "left",
										"subStyles": {
											"foreground": "#ffff0000",
											"fontSize": 10
										}
						            },
						            width: 80,
						            "validations": [
						            	{
						            		"criteria": "value >= 80",
						            		"message": "80 점 이상자만 등록 가능합니다."
						            	}
						            ]
						        },
						        {
						            name: "col6",
						            fieldName: "field6",
						            header : {
						                text: "민법",
										"subText": "|",
										"subTextGap": 4,
										"subTextLocation": "left",
										"subStyles": {
											"foreground": "#ffff0000",
											"fontSize": 10
										}
						            },
						            width: 80
						        },
						        {
						            name: "col7",
						            fieldName: "field7",
						            header : {
						                text: "한국사",
										"subText": "|",
										"subTextGap": 4,
										"subTextLocation": "left",
										"subStyles": {
											"foreground": "#ffff0000",
											"fontSize": 10
										}
						            },
						            width: 80,
						            "mergeRule": {
						                criteria: "value"
						            },
						            "validations": [
						            	{
						            		"criteria": "value is not empty",
						            		"message": "한국사는 필수 과목입니다."
						            	}
						            	,
						            	{
						            		"criteria": "value >= 90",
						            		"message": "90 점 이상자만 등록 가능합니다."
						            	}
						            ]
						        },
						        {
						            name: "col8",
						            fieldName: "field8",
						            header : {
						                text: "영어"
						            },
						            width: 80
						        },
						        {
						            name: "col9",
						            fieldName: "field9",
						            header : {
						                text: "과학"
						            },
						            width: 80
						        },
						        {
						            name: "col10",
						            fieldName: "field10",
						            header : {
						                text: "사회"
						            },
						            width: 80
						        }   	
				            ]
				        }
					],
					fields: [
				        {
				            fieldName: "field1",
				            "dataType": "text"
				        },
				        {
				            fieldName: "field2",
				            "dataType": "text"
				        },
				        {
				            fieldName: "field3",
				            "dataType": "text"
				        },
				        {
				            fieldName: "field4",
							"dataType": "number"
				        },
				        {
				            fieldName: "field5",
							"dataType": "number"
				        },
				        {
				            fieldName: "field6",
							"dataType": "number"
				        },
				        {
				            fieldName: "field7",
							"dataType": "number"
				        },
				        {
				            fieldName: "field8",
							"dataType": "number"
				        },
				        {
				            fieldName: "field9",
							"dataType": "number"
				        },
				        {
				            fieldName: "field10",
							"dataType": "number"
				        },
				        {
				            fieldName: "field11",
				            "dataType": "text"
				        }
					],
					data: [
				        ["배우", "여자", "전도연", "0", "70", "3", "70", "60", "100", "80"],
				        ["가수", "여자", "이선희", "1", "33", "44", "70", "60", "100", "80"],
				        ["배우", "여자", "하지원", "2", "40", "55", "70", "60", "80", "80"],
				        ["가수", "여자", "박정현", "3", "90", "66", "70", "60", "100", "80", "20171013"],
				        ["배우", "여자", "전지현", "4", "60", "77", "70", "60", "100", "80"],
				        ["가수", "남자", "이승환", "7", "70", "88", "100", "100", "70", "100", "20171013"],
				        ["가수", "남자", "신해철", "8", "80", "99", "50", "100", "100", "100"],
				        ["가수", "남자", "타블로", "9", "90", "80", "100", "100", "20", "90"],
				        ["가수", "남자", "토이", "10", "100", "70", "30", "100", "10", "90", "20171013"],
				        ["개발자", "남자", "홍성표", "7", "100", "60", "90", "100", "100", "90"],
				        ["개발자", "남자", "김정배", "8", "90", "50", "80", "100", "100", "100"],
				        ["개발자", "남자", "강철호", "9", "80", "10", "40", "100", "100", "100"],
				        ["개발자", "여자", "배보아", "10", "70", "20", "50", "100", "100", "100"],
				        ["개발자", "남자", "이수락", "8", "60", "30", "100", "100", "100", "100", "20171013"],
				        ["개발자", "남자", "유태균", "9", "50", "40", "100", "100", "100", "100"],
				        ["배우", "남자", "홍성표", "7", "100", "60", "90", "100", "100", "90"],
				        ["가수", "남자", "김정배", "8", "90", "50", "80", "100", "100", "100"],
				        ["배우", "남자", "강철호", "9", "80", "10", "40", "100", "100", "100"],
				        ["가수", "여자", "배보아", "10", "70", "20", "50", "100", "100", "100", "20171013"],
				        ["배우", "여자", "이수락", "8", "60", "30", "100", "100", "100", "100"],
				        ["가수", "여자", "유태균", "9", "50", "40", "100", "100", "100", "100"]
					],
					fixedOptions:
					{
						rowCount: 3,
						colCount: 3
					},
					groupBy: ["field2"],
					rowGroup: {
						mergeMode: true,
						mergeExpander: false,
						expandedAdornments: "footer",
						collapsedAdornments: "header"
					},
					contextMenu: [ { label: "엑셀 내보내기" } ],
					validations: [
						{
		            		"criteria": "value is not empty",
		            		"message": "필수 컬럼입니다."
		            	}
					],
					rowValidations: [
						{
							"criteria": "values['field6'] > 50",
		            		"message": "\"민법\" 은 최소 반타작은 해야합니다!"
						}
					]
				}
			};
			
			return seedParams;
		}
		,
		_getItemSchema: function()
		{
			var schema = 
			{
				"title": "RealGrid-1.1.25"
			};
			
			return schema;
		}
		,

		onBeforeShow: function()
		{
 			this._createJsonEditor(this.jsoneditor, this._makeItemParams(), this._getItemSchema());
		}
	});
	
	return RealGridManager;
});