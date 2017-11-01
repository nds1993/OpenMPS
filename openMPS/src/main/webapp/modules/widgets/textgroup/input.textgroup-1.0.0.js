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
	 * 텍스트를 입력하는 폼 항목인 text 를 구현한다.
	 */
	var	InputTextBox = FormItemPanel.extend(
	{
		template: "#input_textgroup_html",
		ui:
		{
			label_box : '.label_box',
			control_box : '.control_box',
			label: 'label',
			title: 'span.title',
			text: 'input[type=text]',
			desc: '.help-block',
			input_group : '.input-group'
		}
		,
		/**
		 * 텍스트 입력 박스를 초기화한다.
		 * 
		 * @params options		{
		 *							type		{string}
		 *							id			{string}
		 *							label		{string}
		 *							style		{object}
		 *							selector	{string}
		 *							max_size	{number}
		 *							placeholder	{string}
		 *							value		{boolean}
		 * 						}
		 */
		initialize: function(options)
		{
			InputTextBox.__super__.initialize.apply(this, arguments);
			
			if( options.model == undefined )
			{
				this.model = new Backbone.Model
	   	    	(
	   	    		_.extend({ title: "" }, {
	   	    			id: options.id,
						type: options.type,
						title: options.label,
						prop: options.prop || { icon: { view: false }, desc: '' },
						value: options.value,
						required : options.required,
						maxlength: options.maxlength,
						placeholder: options.placeholder
					} )
	   	    	);
			}							
        }
		,
		onBeforeShow: function()
		{
			
			//placeholder 값 넣기
			this.ui.text.attr('placeholder', this.model.get('placeholder') || '');
			//레이블 텍스트 정렬 
			this.ui.label.addClass( this.model.get('prop').label_align || '');
			//레이블 사이즈 정렬 
			this.ui.label.addClass( this.model.get('prop').label_size || '');
			//인풋 사이즈 변경
			this.ui.text.addClass( this.model.get('prop').input_size || '');
			this.ui.text.attr( "type", this.model.get('prop').input_type || '');
			
			if( this.model.has('max_size') )
			{
				this.ui.text.attr('maxlength', this.model.get('maxlength'));
			}
			if( this.model.has('value') )
			{
				this.ui.text.val( UCMS.removeTagBR(this.model.get('value')) );
			}

			

			this._initRequired( this.ui.label, this.ui.text );
			this._initLabelIcon( this.ui.title );
			this._initDesc( this.ui.desc );
			this._initInputSize( this.ui.text );
			this._initInputState( this.ui.text );
			
			// addon 파라메터 가져오기 
			var prefix_addon = this.getParam('prop').prefix_addon;
			var suffix_addon = this.getParam('prop').suffix_addon; 
			
			if (prefix_addon.view) {
				this.ui.input_group.prepend( '<span class="input-group-addon">'+this._makeIconTag(prefix_addon)+'</span>' );
			}
			if (suffix_addon.view) {
				this.ui.input_group.append( '<span class="input-group-addon">'+this._makeIconTag(suffix_addon)+'</span>' );
			}
		}
		,
		/**
		 * 폼 위젯 생성하는 폼 데이타.
		 * 폼 위젯에 입력된 값을 데이타로 반환한다.
		 */
		getItemData: function()
		{
			var input = this.ui.text.val();
			Logger.info('getItemData() - '+this.model.get('id')+' : '+input);
			return input;
		}
	});
	
	return InputTextBox;
});