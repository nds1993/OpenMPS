/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman 
 */

define(
[
	"Logger",
	"BaroPanelBase"
],
function( Logger, BaroPanelBase )
{
	var	FormItemPanel = BaroPanelBase.extend(
	{
		/**
		 * 폼 아이템을 초기화한다.
		 * 
		 * @param options	{
		 * 						itemId : {string} 폼 아이템 식별자 
		 * 						value : {object}
		 * 					}
		 * 					폼 아이템에 초기화되는 값이 전달된다.
		 * 					getItemData() 에 의해 반환된 값은 value 필드로 제공된다.
		 */
		initialize: function(options)
		{
			FormItemPanel.__super__.initialize.apply( this, arguments );
			this._initLayout();
			Logger.debug("FormItemPanel.initialize()");
		}
		,
		/**
		 * 해당 앱리소스의 form_layout 을 설정함.
		 * 콘텐츠가 랜더링 되기 전에 initialize() 에서 호출해야함.
		 * FormItemPanel.initialize() 에서 호출하고 있음.
		 */
		_initLayout: function()
		{
			var params = this.getParam('prop') || {};
			
			if( params.form_layout )
			{
				/*
				 * layout
				 * 		  form-inline : 인라인 요소로 표시 
				 * 		  form-horizontal : 수평폼 표시
				 * 		  form-only : 레이블 영역 없이 오직 control_box 만 노출
				 * 		  form-label : 레이블 영역은 존재 레이블은 없음
				 */
				this.className = 'form-group form_item_box '+params.form_layout;
			}
			else
			{
				this.className = 'form-group form_item_box';
			}
			this.$el.addClass( this.className );
		}
		,
		/**
		 * 아이템에서 생성된 값을 얻는다.
		 * 제공된 값은 본 모듈이 생성될 때 initialize() 의 value 파라메터로 전달된다.
		 * 
		 * @return 생성된 값이 없거나, 필수 값이 설정되지 않은 경우 null 이 반환된다.
		 */
		getItemData: function()
		{
			Logger.error("FormItemPanel::getItemData() - Need to implement a this method.");
		}
		,
		/**
		 * 폼 위젯 아이템의 데이타를 설정한다.
		 * @param {object} data - 설정 데이타
		 * @param {boolean} silent - true 이면 변경 이벤트가 발생되지 않는다.
		 */
		setItemData: function(data, silent)
		{
			this.setParam( 'value', data ); 
		}
		,
		/**
		 * 레이블 영역의 아이콘을 설정한다.
		 * @param {$} $$title - 레이블 정보가 출력될 태그의 선택자
		 */
		_initLabelIcon: function($title)
		{
			var params = (this.getParam('prop')||{icon:{}}).icon;
			if( params.view == false )
			{
				return;
			}
			if( params.pos == "suffix" ) {
				$title.append( this._makeIconTag(params)+' ' );
			} else {  // suffix 이외에는 앞에 위치함.
				$title.prepend( this._makeIconTag(params)+' ' );
			}
		}
		,
		/**
		 * 아이콘 정보를 생성한다.
		 */
		_makeIconTag: function(params)
		{
			if( params.type == 'fa' )
			{
				return '<i class="fa '+params.value+' fa-fw"></i>';
			} else if ( params.type == 'img' ) { // 이미지 일때 추가 함. escteam 2017.05.12
				return '<img src="'+params.value+'" class="title_icon" />';
			} else if ( params.type == 'text' ) { // text 일때 추가 함. escteam 2017.05.19
				return '<span class="title_icon">'+params.value+'</span>';
			}
		}
		,
		/**
		 * 필수 요소를 화면에 표시합니다. .
		 */
		_initRequired : function($label)
		{
			var params = (this.getParam('prop')||{desc:''});
			if( params.required_view == true )
			{
				$label.append( ' <span class="required">*</span>' );
			}
		}
		,
		/**
		 * 지정한 위치에 설명 정보를 설정한다.
		 * @param {$} $desc - 설명정보가 출력될 태그의 선택자
		 */
		_initDesc: function($desc)
		{
			var params = (this.getParam('prop')||{desc:''});

			// 값이 없으면 해당 TAG 삭제함. escteam 2017.05.12	
			if (params.desc == null || params.desc == "") {
				$desc.remove()
			} else {
				$desc.html( params.desc )
			}
		}
		,
		/**
		 * 폼 요소의 크기를 개별적으로 설정합니다. 
		 * @param {$} $desc - 폼 요소 태그의 선택자
		 */
		_initInputSize: function($desc)
		{
			var params = (this.getParam('prop')||{desc:''});
			$desc.addClass( params.form_size );
		}
		,
		/**
		 * 폼의 상태 요소의 설정합니다. 
		 * @param {$} $desc - 폼 요소 태그의 선택자
		 */
		_initInputState: function($desc)
		{
			var params = (this.getParam('prop')||{desc:''});
			if( params.state == 'readonly' )
			{
				$desc.attr("readonly","")
			} else if ( params.state == 'disabled' ) { 
				$desc.attr("disabled","")
			}
		}
		,
		/**
		 * 텍스트에어리어의 row 와 col 을 설정합니다.
		 * @param {$} $desc - 폼 요소 태그의 선택자
		 */
		_initTextareaSize:function($desc) {
			var params = (this.getParam('prop')||{desc:''});
			// rows 속성
			if (params.row  > 0) {
				$desc.attr( 'rows', params.row );
			} 
			// cols 속성 :  col은 특별한 경우 이외에는 사용하지 않습니다.
			if (params.col > 0) {
				$desc.attr( 'cols', params.col );
			} 
		}
		,
		/**
		 * 지정한 태그에 tabindex 를 설정한다.
		 */
		_initTabIndex: function($tag)
		{
			var tabindex = this.getParam('tabindex') || 0;
			if( tabindex == 0 )
			{
				return;
			}
			$tag.attr('tabindex', tabindex);
		}
	}
	,
	{
		EVENT: _.extend(
		{
			// 폼 아이템 데이타가 변경된 경우 본 이벤트를 발생시킨다.
			// 이벤트 발생시 { id: widgetId, value: {} } 같은 구조의 파라메터를 제공한다.
			CHANGE: "form:change"		// { id: widgetId, value: {} }
		}
		,
		BaroPanelBase.EVENT)
	});
	
	return FormItemPanel;
});
