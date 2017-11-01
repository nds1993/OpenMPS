/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
(
[
	"BaroPanelBase", "FormItemPanel", "Logger"
]
,
function( BaroPanelBase, FormItemPanel, Logger)
{
	/**
	 * 콤보 박스를 구현한다.
	 */
	var	InputSelectBox = FormItemPanel.extend(
	{
		template: "#input_select_html"
		,
		ui: 
		{
				label_box : '.label_box',
				control_box : '.control_box',
				label: 'label',
				title: 'span.title',
				select: 'select',
				desc: '.help-block'
		}
		,
		/**
		 * 콤보 박스를 초기화한다.
		 * 
		 * @params options		{
		 *							type		{string}
		 *							id			{string}
		 *							label		{string}
		 *							style		{object}
		 *							selector	{string}
		 *							required	{boolean}
		 *							value		{
		 *											items:[
		 *												{label:항목레이블,value:항목값}, ...
		 *												]
		 *											,selected:선택된 값
		 *										}
		 * 						}
		 * 						배열로 설정되는 경우 첫번째 이미지가 초기값으로 사용된다.
		 */
		initialize: function(options)
		{
			InputSelectBox.__super__.initialize.apply(this, arguments);
			
			//
			options || (options = { label: '항목선택', required: false });
			
			if( options.model == undefined )
			{
				this.model = new Backbone.Model
	   	    	(
	   	    		_.extend({ title: "" }, {
	   	    			id: options.id,
						title: options.label,
						prop: options.prop || { icon: { view: false }, desc: '' },
						required: options.required
					} )
	   	    	);
			}					
			
			this._seedValues = options.value || {};
        }
		,
        onRender: function()
        {
        }
		,
		onBeforeShow: function()
		{
			//레이블 텍스트 정렬 
			this.ui.label.addClass( this.model.get('prop').label_align || '');
			//레이블 사이즈 정렬 
			this.ui.label.addClass( this.model.get('prop').label_size || '');
			//인풋 사이즈 변경
			this.ui.select.addClass( this.model.get('prop').input_size || '');
			
			this._initRequired( this.ui.label );
			this._initLabelIcon( this.ui.title );
			this._initDesc( this.ui.desc );
			this._initInputSize( this.ui.select );
			this._initInputState( this.ui.select );
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
				this.ui.select.append('<option value="'+item.value+'">'+item.label+'</option>');
			}
			if(this._seedValues.selected)
			{
				this.ui.select.find('option[value="'+this._seedValues.selected+'"]').attr('selected', true);
			}
		}
		,
		/**
		 * 폼 위젯 생성하는 폼 데이타.
		 * 폼 위젯에 입력된 값을 데이타로 반환한다.
		 */
		getItemData: function()
		{
			var selected = this.ui.select.val();
			Logger.info('getItemData() - '+this.model.get('id')+' : '+selected);
			return selected;
		}
	});
	
	return InputSelectBox;
});