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
	var	InputTextMultiBox = FormItemPanel.extend(
	{
		template: "#input_textmulti_html",
		ui:
		{
			label_box : '.label_box',
			control_box : '.control_box',
			label: 'label',
			title: 'span.title',
			textmulti_box: '.textmulti_box',
			desc: '.help-block'
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
			InputTextMultiBox.__super__.initialize.apply(this, arguments);

			if( options.model == undefined )
			{
				this.model = new Backbone.Model
	   	    	(
	   	    		_.extend({ title: "" }, {
	   	    			id: options.id,
						type: options.type,
						title: options.label,
						prop: options.prop || { icon: { view: false }, desc: '' },
						required : options.required,
					} )
	   	    	);
			}							
			this._seedValues = options.value || {};

		}
		,
		onBeforeShow: function()
		{
			// multi_layout 파라메터 가져오기 
			var multi_layout = (this.getParam('prop')||{multi_layout:{}}).multi_layout; 

			//레이블 텍스트 정렬 
			this.ui.label.addClass( this.model.get('prop').label_align || '');
			//레이블 사이즈 정렬 
			this.ui.label.addClass( this.model.get('prop').label_size || '');
			
			this.ui.textmulti_box.addClass( multi_layout.align || '');
			this.ui.textmulti_box.addClass( multi_layout.layout || '');

			this._initRequired( this.ui.label, this.ui.text );
			this._initLabelIcon( this.ui.title );
			this._initDesc( this.ui.desc );
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
			
			for(var i in this._seedValues.items)
			{
				var item = this._seedValues.items[i];
				this.ui.textmulti_box.append('<input id="'+item.value+'" type="'+item.type+'" class="form-control '+item.size+' '+item.value+'" value="'+item.value+'" placeholder= "'+item.placeholder+'" maxlength="'+item.maxlength+'" '+item.state+'/>');
			}
			/*
			if(this._seedValues.selected)
			{
				this.ui.select.find('option[value="'+this._seedValues.selected+'"]').attr('selected', true);
			}
			*/
		},
		
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
	
	return InputTextMultiBox;
});