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
	 * 문장을 입력하는 입력 항목인 Textarea 를 구현한다.
	 */
	var	InputTextareaBox = FormItemPanel.extend(
	{
		template: "#input_textarea_html"
		,
		ui:
		{
			label_box : '.label_box',
			control_box : '.control_box',
			label: 'label',
			title: 'span.title',
			textarea: 'textarea',
			desc: '.help-block'
		}
		,
		/**
		 * 장문의 텍스트 입력 박스를 초기화한다.
		 * 
		 * @params options		{
		 *							type		{string}
		 *							id			{string}
		 *							label		{string}
		 *							prop		{object}
		 *							selector	{string}
		 *							max_size	{number}
		 *							placeholder	{string}
		 *							value		{boolean}
		 * 						}
		 */
		initialize: function(options)
		{
			InputTextareaBox.__super__.initialize.apply(this, arguments);
			
			if( options.model == undefined )
			{
				this.model = new Backbone.Model
	   	    	(
	   	    		_.extend({ title: "" }, {
	   	    			id: options.id,
						title: options.label,
						prop: options.prop || { icon: { view: false }, desc: '' },
						value: options.value,
						max_size: options.max_size,
						placeholder: options.placeholder
					} )
	   	    	);
			}				
        }
		,
        onRender: function()
        {
        }
		,
		onBeforeShow: function()
		{
			//placeholder 값 넣기
			this.ui.textarea.attr('placeholder', this.model.get('placeholder') || '');
			//레이블 텍스트 정렬 
			this.ui.label.addClass( this.model.get('prop').label_align || '');
			//레이블 사이즈 정렬 
			this.ui.label.addClass( this.model.get('prop').label_size || '');
			//인풋 사이즈 변경
			this.ui.textarea.addClass( this.model.get('prop').input_size || '');
			this.ui.textarea.addClass( this.model.get('prop').resize || '');

			
			if( this.model.has('max_size') )
			{
				this.ui.textarea.attr('maxlength', this.model.get('max_size'));
			}
			if( this.model.has('value') )
			{
				this.ui.textarea.val( UCMS.removeTagBR(this.model.get('value')) );
			}	
			this._initRequired( this.ui.label, this.ui.title );
			this._initLabelIcon( this.ui.title );
			this._initDesc( this.ui.desc );
			this._initInputSize( this.ui.textarea );
			this._initInputState( this.ui.textarea );
			// 
			this._initTextareaSize( this.ui.textarea );
		}
		,
		/**
		 * 폼 위젯 생성하는 폼 데이타.
		 * 폼 위젯에 입력된 값을 데이타로 반환한다.
		 */
		getItemData: function()
		{
			var input = this.ui.textarea.val();
			Logger.info('getItemData() - '+this.model.get('id')+' : '+input);
			return input;
		}
	});
	
	return InputTextareaBox;
});