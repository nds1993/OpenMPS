/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
(
[
	"BaroPanelBase", "FormItemPanel", "Logger", "SPA.Platform.Helper"
]
,
function( BaroPanelBase, FormItemPanel, Logger)
{
	/**
	 * 모든폼에서 고루쓰이는 코드찾기를 구현한다.
	 */
	var CodeSearchBox = FormItemPanel.extend(
	{
		template: "#code_search_html",
		ui:
		{
			val_keyword : "input.val_keyword",
			val_result : "input.val_result",
			btn_go_codesearch : "button.btn_go_codesearch"
		}
		,
		events : 
		{
			"click @ui.btn_go_codesearch": "onCodeSearch",
			//"change @ui.val_keyword": "onChange",
			//"keydown @ui.val_keyword": "onKeyDown"
			"change @ui.val_result": "onChange",
			"keydown @ui.val_result": "onKeyDown"
		},
		
		/**
		 * 코드찾기 박스를 초기화한다.
		 * 
		 * @params options		{
		 * 							label: "",
		 * 							required: "",
		 * 							value: {
		 * 								keyword: ##,
		 * 								result: ##
		 * 							},
		 * 							codeType: ##, 공통코드/거래처코드/제품코드
		 * 							params:{
		 * 							 	// 공통 코드 파라메터
		 * 								baseCode: ##
		 * 							 	// 거래처 코드 파라메터
		 * 								// 제품 코드 파라메터												
		 * 							},
		 * 							// 리소스 필드는 위젯 로딩시 manifest 설정을 참고하여 자동으로 추가된다.
		 * 							resource: {
		 * 								template: ##,
		 * 								nls: {
		 * 								} 
		 * 							},
		 * 							tabindex: {number} FormBox 에 의해 자동 할당된다.
		 * 						}
		 */
		initialize: function(options)
		{
			CodeSearchBox.__super__.initialize.apply(this, arguments);
			if(!options.value)
			{
				this.setParam('value', {codeType:'TMCOCD10', keyword:'', result:''})
			}
        }
		,
		onRender: function()
		{
			this._initTabIndex( this.ui.val_keyword );
			this._initTabIndex( this.ui.btn_go_codesearch );
			this._initTabIndex( this.ui.val_result );
		}
		,
		onBeforeShow: function()
		{
			var data = this.getParam('value');
			if( data.keyword && data.keyword.length > 0 )
			{
				this.ui.val_keyword.val( data.keyword );
			}
			if( data.result && data.result.length > 0 )
			{
				this.ui.val_result.val( data.result )
			}
		}	
		,
		/**
		 * TODO
		 * 코드를 검색하는 팝업 화면이 필요합니다. 
		 * 우선 버튼 클릭 만 처리합니다. 
		 * 해당되는 키워드와 코드 종류를 넘겨 플로팅창에서 결과를 보여주어야 합니다. 
		 */
		onCodeSearch: function() 
		{
			var self = this;
			var args = {
				codeType: this.getParam('codeType'),
				params: this.getParam('params'),
				//keyword: this.ui.val_keyword.val(),
				keyword: this.ui.val_result.val(),
				callback: function(result)
				{
					result || (result={});
					self.setItemData(
					{
						'keyword': result.label,
						'result': result.code,
						'raw': result
					});
				}
			};
			this.broadcastEvent( CodeSearchBox.EVENT.SEARCH, args);
		}
		,
		/**
		 * 폼 위젯 생성하는 폼 데이타.
		 * 폼 위젯에 입력된 값을 데이타로 반환한다.
		 * @return {object}
		 * 			{
		 * 				keyword: ##,
		 * 				result: ##,
		 * 				raw: ## 존재하는 경우만 설정됨. 검색 결과의 추가 정보.
		 * 			}
		 */
		getItemData: function()
		{
			var raw = this.getParam("raw");
			var input = 
			{
				"keyword": this.ui.val_keyword.val(), 
				"result": this.ui.val_result.val()
			};
			if( raw )
			{
				input.raw = raw;
			}
			return input;
		}
		,
		/**
		 * 선택된 코드 정보를 셋한다.
		 * @param {object} data -	코드값
		 * 							{
		 * 								keyword : ##,
		 * 								result : ##,
		 * 								raw: ## 결과값과 관련된 기타 등등의 데이타를 저장할 경우 추가됨
		 * 							}
		 */
		setItemData: function(data, silent)
		{
			UCMS.debug("CodeSearchBox.setItemData() -");
			UCMS.debug(data);
			
			var prevData = this.getItemData();
			
			data || (data={keyword:"", result:""});
			// TODO 왜 기존 값과 머지했는지 모르겠음. 신규 값을 사용하도록 변경
			//this.setParam( 'value', _.extend( this.getParam('value'), data ) );
			this.setParam( 'value', data );
			
			//
			this.ui.val_keyword.val( data.keyword );
			this.ui.val_result.val( data.result );
			this.setParam("raw", data.raw || null);
			
			if( silent != true
				&&
				(prevData.result != data.result 
				||
				prevData.keyword != data.keyword))
			{
				// 값이 변경된 경우에만 변경 이벤트를 발생시킴
				this.trigger(CodeSearchBox.EVENT.CHANGE, data);
			}
		}
		,
		onKeyDown: function(evt)
		{
			if( evt.which == 13 || evt.which == 9 ) 
			{
				// 이벤트를 막으면 탭 키에 의한 포커스 이동이 멈줘버린다.
				//event.preventDefault();
				this.onCodeSearch();
			}
		}
		,
		onChange: function(evt)
		{
			if( $(evt.target).val().length == 0 )
			{
				this.setItemData(
				{
					'keyword': "",
					'result': "",
					'raw': null
				}
				,
				true);
			}
		}
	}
	,
	{
		EVENT:
		{
			SEARCH: "codesearch",	// 코드 찾기 요청, { baseCode: ##, keyword: ##, callback: function(baseCode, keyword, result){} }
			CHANGE: "form:change"
		}
	});
	
	return CodeSearchBox;
});