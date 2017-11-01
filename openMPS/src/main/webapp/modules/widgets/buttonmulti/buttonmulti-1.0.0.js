/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
(
[
	"BaroPanelBase", "Logger", 
]
,
function( BaroPanelBase,  Logger)
{
	/**
	 * 텍스트를 입력하는 폼 항목인 text 를 구현한다.
	 */
	var	ButtonMultiBox =  BaroPanelBase.extend(
	{
		template: "#buttonmulti_html",
		className : "buttonmulti_box",
		ui:
		{
			box: '.btn_group_box'
		}
		,
		/**
		 * 멀티버튼 박스를 초기화한다.
		 * 
		 * @params options		
		 * 	{
				btn_item: [
				    * label:'버튼1', // 버튼에 쓰여질 텍스트입니다. 
				    * icon:{view:true,type:"fa",value:"fa-paw"},  // 기존 아이콘과 동일합니다.
				    * style : 'btn-primary btn-lg text-left', // 버튼에 추가할 수 있는 클래스 입니다. 색상과 크기 및 각종 class 를 추가할 수 있습니다.  btn-default
				    * sr_only:true,  // 버튼에서 텍스트를 표시하지 않습니다.
				    * state : null, // 비활성화 표시 
				    * cmd:"" // 명령어를 입력합니다. 
				   {label:'버튼1',icon:{view:true,type:"fa",value:"fa-paw"}, style : 'btn-primary',sr_only:true, state : null, cmd:"" },
				   {label:'버튼1',icon:{view:true,type:"fa",value:"fa-paw"}, style : 'btn-default',sr_only:false, state : "disabled", cmd:"" },
				   {label:'버튼1',icon:{view:true,type:"fa",value:"fa-paw"}, style : 'btn-info',sr_only:false, state : null, cmd:"" }
				 ],
				btn_group_layout : "btn-group",//, "btn-inline" // btn-group : 버튼 그룹 으로 처리 //btn-inline : 개별 버튼으로 처리
				text_align : "text-left" // 텍스트 정렬 	
				} 
		 */
		initialize: function(options)
		{
			ButtonMultiBox.__super__.initialize.apply(this, arguments);

			if( options.model == undefined )
			{
				this.model = new Backbone.Model
	   	    	(
	   	    		_.extend(/*{ title: "" },*/ {
//	   	    			btn_item: options.btn_item || { icon: { view: false }, desc: '' },
	   	    			layout : options.btn_group_layout,
	   	    			text_align: options.text_align
					} )
	   	    	);
			}							
			this._seedValues = options.btn_item || [];

		}
		,
		onBeforeShow: function()
		{
			this.ui.box.addClass(this.model.get('layout'));
			this.$el.addClass(this.model.get('text_align'));
			this._initItems();
		}
		,
		_initItems: function()
		{
			if(! this._seedValues )
			{
				Logger.error("_initItems() - invalid seed value.");
				return;
			}
			
			var self = this;
			
			for(var i in this._seedValues)
			{
				(function(button)
				{
					var sr_only = "";
					var btn_icon = "";
					var btn_state = "";
					if (button.sr_only==true) { sr_only = "sr-only"} //텍스트 노출안함 
					if ( button.icon && button.icon.view==true) { btn_icon = self._makeIconTag(button.icon)} // 아이콘 할당 그룹은 항상 앞에만 아이콘 할당함.  
					if (button.state){btn_state = button.state;} // 상태값 할당 
					
					var $btn = $('<button class="btn '+button.style+' '+btn_state+'">'+ btn_icon +' <span class="btn_label '+sr_only+'">'+button.label+'</span></button>');
					$btn.click(function()
					{
						self.onButtonClick(button.cmd);
					});
					if( button.buttonId )
					{
						$btn.addClass(button.buttonId);
					}
					
					self.ui.box.append($btn);
				})(this._seedValues[i]);
			}

		},
		onButtonClick: function(cmd)
		{
			if( typeof cmd == 'function' )
			{
				cmd();
			}
			else if( typeof cmd == 'string' && cmd.charAt(0) == '#' )
			{
				location.href = cmd;
			}
		}
		,
		/**
		 * 지정한 버튼 객체를 얻는다.
		 * @return {$}
		 */
		getButton: function(buttonId)
		{
			var $btn = this.ui.box.find("button."+buttonId);
			
			return $btn;
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
			} else if ( params.type == 'img' ) { // 이미지 일때 추가 함. 
				return '<img src="'+params.value+'" class="title_icon" />';
			} else if ( params.type == 'text' ) { // text 일때 추가 함.
				return '<span class="title_icon">'+params.value+'</span>';
			}
		}

	});
	
	return ButtonMultiBox;
});