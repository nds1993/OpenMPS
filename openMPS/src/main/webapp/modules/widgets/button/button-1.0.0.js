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
	var ButtonWidget = BaroPanelBase.extend(
	{
		template: "#button_box_html",
		tagName: "div",
		className: "button_box",

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
   	    	ButtonWidget.__super__.initialize.apply(this, arguments);
   	    	//
   	    	options || (options={});
			if( options.model == undefined )
			{
				this.model = new Backbone.Model
	   	    	(
	   	    		_.extend({ label: "" }, {
						label: options.label,
						label_align : options.label_align,
						button_loyout: options.button_loyout || { button_loyout: { view: false }},
						icons: options.icons || [],
						cmd: options.cmd || null
					} )
	   	    	);
			}
		},

		onBeforeRender: function()
		{
		},

		ui : {
			label: 'span.btn_label',
			button: '.btn'
		},
		
		events : {
		}
		,
		onBeforeShow : function()
		{
			/*style : 'btn-default', // btn-default / btn-primary / btn-success / btn-info / btn-warning / btn-danger / btn-link
			size : 'btn-lg',  //"" , .btn-lg, "", .btn-sm, .btn-xs  
			align : 'text-left', //text-left // text-center / text-right
			state : 'disabled',
			*/ 
			var btn_style = this.model.get('button_loyout').style;
			btn_style += " "+ this.model.get('button_loyout').size; 
			btn_style += " "+ this.model.get('button_loyout').align; 
			btn_style += " "+ this.model.get('button_loyout').state 
			
			if (this.model.get('button_loyout').block == true){
				btn_style += " btn-block"
			}
			if (this.model.get('button_loyout').sr_only == true){
				this.ui.label.addClass("sr-only")
			}
			
			// alert(btn_style)				
			this.ui.button.addClass(btn_style)
			this._initIcons();
			
		}
		,
	    onShow: function()
		{
	    	
		},
		_initIcons: function()
		{
			var icons = this.model.get('icons');
			for(var i in icons)
			{
				var item = icons[i];

				if( item.pos == "prefix") {
					this.ui.button.prepend( this._makeIconTag(item)+' ' );
				} else if( item.pos == "suffix") {
					this.ui.button.append( ' '+this._makeIconTag(item) );
				}
			}
			var self = this;
			this.ui.button.click(function()
			{
				self.onButtonClick();
			});
		},
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
		onButtonClick: function()
		{
			var cmd = this.model.get("cmd");
			if( typeof cmd == 'function' )
			{
				cmd();
			}
			else if( typeof cmd == 'string' && cmd.charAt(0) == '#' )
			{
				//location.href = cmd;
				UCMS.reloadPage( cmd );
			}
			else if( cmd )
			{
				// # 로 시작하지 않는 명령어는 함수로 실행됨
				var func = new Function(cmd);
				func();
				delete func;
			}
		}
	});

	return ButtonWidget;
	
});