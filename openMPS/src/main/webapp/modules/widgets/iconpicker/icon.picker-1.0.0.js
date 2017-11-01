/**
 * Project : MobileOven
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
([
	"BaroProps",
	"Logger"
]
,
function( BaroProps, Logger )
{
	/**
	 * 폰트어썸 아이콘의 목록을 구현하고, 선택하는 위젯.
	 * BaroFloating 에 의해 플로팅시켜 활성화한다.
	 */
	var IconPicker = UCMS.SPA.Component.extend(
	{
		template: "#icon_picker_html",

		ui:
		{
			icon: ".icon_select_ul li .fa",
			btn_cancel: ".btn_go_iconpopup_cancel"
		},
		
		events:
		{
			"click @ui.icon" : "onIconSelect",
			"click @ui.btn_cancel" : "onCancel",
		},
		
		initialize: function(options)
		{
			IconPicker.__super__.initialize.apply( this, arguments );
			
			if( options && options.deferred )
			{
				// BaroFloating 로 생성된 경우
				this._deferred = options.deferred;
			}
		},
		
		onBeforeRender: function()
		{
		},
		
	    onShow: function()
		{
		},
		
		onCancel: function()
		{
			if( this._deferred )
			{
				this._deferred.reject();
			}
			else
			{
				this.trigger( IconPicker.EVENT.CANCEL );
			}
		},
		
		/**
		 * 선택된 아이콘 정보를 부모 위젯으로 반환한다.
		 * BaroFloating 으로 활성화 된 경우, BaroFloating 이 제공하는 Deferred 객체로 반환되며,
		 * 그 외의 경우에는 IconPicker.EVENT.SELECT 이벤트를 발생시킨다.
		 */
		onIconSelect: function(e)
		{
			var icon = 
			{
				type: IconPicker.TYPE.FONT_AWESOME,
				icon: IconPicker.TYPE.FONT_AWESOME+"-"+e.currentTarget.attributes["value"].nodeValue
			};
			
			Logger.info(e.currentTarget.className);
			Logger.info( icon );
			
			if( this._deferred )
			{
				this._deferred.resolve( icon );
			}
			else
			{
				this.trigger( IconPicker.EVENT.SELECT, icon );
			}
		}
	}
	,
	{
		TYPE: {
			FONT_AWESOME: "fa"
		}
		,
		EVENT: {
			/**
			 * 선택된 아이콘 정보는 다음과 값이 구조화된다.
			 * {
			 * 		type: 아이콘 종류,
			 * 		icon: 선택된 아이콘 식별자
			 * }
			 */
			SELECT: "select:icon",
			CANCEL: "cancel:icon"
		}
	});
	
	return IconPicker;
});