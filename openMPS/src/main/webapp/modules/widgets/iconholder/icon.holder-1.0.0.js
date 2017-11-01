/**
 * Project : MobileOven
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
([
	"Logger",
	
	"BaroFloating",
	"FormItemPanel",
	"manifest!IconPicker-1.0.0#widget"
]
,
function( Logger, BaroFloating, FormItemPanel, IconPicker )
{
	/**
	 * 아이콘을 선택하고, 선택된 아이콘을 출력하는 폼 위젯
	 */
	var IconPlaceHolder = FormItemPanel.extend(
	{
		template: "#icon_holder_html",

		ui:
		{
			thumb_box: ".icon_thumb_box i",
			btn_select: ".btn_menu_icon",
			'desc': '.desc'
		},
		
		events:
		{
			"click @ui.btn_select" : "onIconPicker"
		},
		
		/**
		 * 범용 콘텐츠의 files 필드가 model 로 설정된다.
		 * 
		 * @params options		{
		 * 	
		 *							type		{string}
		 *							id			{string}
		 *							label		{string}
		 *							selector	{string}
		 *							required	{boolean}
		 *							value		{ type: null, icon: null }
		 *								
		 * 						}
		 * 						배열로 설정되는 경우 첫번째 이미지가 초기값으로 사용된다.
		 */
		initialize: function(options)
		{
			IconPlaceHolder.__super__.initialize.apply( this, arguments );
			
			if( options.model == undefined )
			{
				if( options.value == undefined )
				{
					options.value = { type: null, icon: null };
				}
				this.model = new Backbone.Model(
				{
					title: options.label,
					type: options.value.type,
					icon: options.value.icon,
					required: options.required,
					style: options.style || { icon: { view: false }, desc: '' },
				});
			}
		},
		
		_initTitle: function()
		{
			var title = this.model.get("title");
			var required = this.model.get("required");
			
			if( required == true )
			{
				title += "<small class='color_point'><i class='fa fa-fw fa-asterisk'></i></small>";
			}
			
			this.model.set("title", title);
		},
		
		/**
		 * 지정한 아이콘을 화면요소에 적용한다.
		 * 
		 * @param iconName		{string} 설정되는 아이콘 이름
		 */
		_setIcon: function(iconName)
		{
			Logger.info("_setIcon() - icon : "+iconName);
			
			var style = "fa "+iconName+" fa-fw font_icon";
			
			this.ui.thumb_box.attr( "class", style );
			this.ui.thumb_box.val( iconName );
		},
		
		/**
		 * 선택된 데이타를 얻는다.
		 * 설정된 값이 없는 경우 null 을 반환한다.
		 */
		getItemData: function()
		{
			var data = 
			{
				type: this.model.get("type"),
				icon: this.model.get("icon")
			};
			return ( data.icon == null ? null : data );
		},
		
		onBeforeShow: function()
		{
			this._initTitle();
			this.model.set("type", this.model.get("type") || "paw");
			this._initDesc( this.ui.desc );
		},
		
		onIconPicker: function(e)
		{
			var self = this;

			BaroFloating.open(IconPicker, { type: IconPicker.TYPE.FONT_AWESOME }, {})
			.then(function(result)
			{
				Logger.info("onIconPicker()");
				Logger.info(result);
				
				self.model.set( result );
				self._setIcon( result.icon );
			})
			.always(function()
			{
				BaroFloating.close();
			});
		}
	});
	
	return IconPlaceHolder;
});