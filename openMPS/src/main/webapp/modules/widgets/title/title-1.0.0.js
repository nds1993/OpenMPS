/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2013 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 *-------------------------------------------------------------
 * Project : Mobile Oven 
 * 2014.12.15
 * @author escteam 
 */

define([
    	"BaroPanelBase", 
    	"Logger"

        ], 
function(  BaroPanelBase, Logger)
{
	/**
	 * 일반 위젯 템플릿.
	 * 폼 외의 위젯은 BaroPanelBase 를 상속 받는다.
	 */
	/**
	 * 앱 커버 영역을 구현한다.
	 */
	var TitleWidget = BaroPanelBase.extend(
	{
		template: "#title_box_html",
		tagName: "div",
		className: "title_box",

		/**
		 * 커버 위젯을 초기화한다.
		 * 
		 * @param options	파라메터 객체
		 * 					{
		 * 						module: "title",
		 * 						title : 타이틀,
		 * 						icon_font : 폰트어썸 아이콘 폰트, 
		 * 						slogan: 서브타이틀,
		 * 						
		 * 					}
		 */
   	    initialize: function(options)
		{
   	    	TitleWidget.__super__.initialize.apply(this, arguments);
   	    	//
			if( options.model == undefined )
			{
				this.model = new Backbone.Model
	   	    	(
	   	    		_.extend({ title: "", title_type: "h1",text_color: ""}, {
						title: options.label,
						desc : options.desc,
						title_layout :  options.title_layout,
						title_type : options.title_type,
						label_align : options.label_align,
						text_color : options.text_color
					} )
	   	    	);
			}	

		},

		onBeforeRender: function()
		{
		},

		ui : {
			title_tag : '.title_tag',
			title: 'span.title',
			desc: '.help-block'
		},
		
		events : {
		}
		,
		onBeforeShow : function()
		{
			this.title_layout = this.model.get('title_layout') + " " + this.model.get('label_align') 
			this.$el.addClass( this.title_layout ); // el : 해당 위젯 tag / $el : 해당 위젯 최상단 선택자  
			
			this.ui.desc.html( this.model.get('desc') || '');
			this._initLabelIcon( this.ui.title );
		}
		,
	    onShow: function()
		{
	    	
		},
		_initLabelIcon: function($title)
		{
			var params = this.getParam('icon');
			if( params.view == false )
			{
				return;
			}
			// 오지 앞에만 배치합니다.pos : 'prefix',  //  pos:prefix/suffix,
			if( params.pos == "prefix") {
				$title.prepend( this._makeIconTag(params)+' ' );
			} else if( params.pos == "suffix") {
				$title.append( ' '+this._makeIconTag(params) );
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
			}
		}
		,		
	});

	return TitleWidget;
	
});