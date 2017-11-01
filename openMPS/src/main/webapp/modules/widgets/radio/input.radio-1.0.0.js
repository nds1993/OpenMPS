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
	var	InputRadioBox = FormItemPanel.extend(
	{
		template: "#input_radio_html"
		,
		ui: 
		{
				label_box : '.label_box',
				control_box : '.control_box',
				label: 'label',
				title: 'span.title',
				checkbox: '.checkbox_box',
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
			InputRadioBox.__super__.initialize.apply(this, arguments);
			
			//
			options || (options = { label: '항목선택', required: false });
			
			if( options.model == undefined )
			{
				this.model = new Backbone.Model
	   	    	(
	   	    		_.extend({ title: "" }, {
	   	    			name: options.name,
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
			// multi_layout 파라메터 가져오기 
			var multi_layout = (this.getParam('prop')||{multi_layout:{}}).multi_layout; 

			//레이블 텍스트 정렬 
			this.ui.label.addClass( this.model.get('prop').label_align || '');
			//레이블 사이즈 정렬 
			this.ui.label.addClass( this.model.get('prop').label_size || '');
			//콘트롤 파라메터 변경			
			this.ui.checkbox.addClass( multi_layout.align || '');
			this.ui.checkbox.addClass( multi_layout.layout || '');
			
			this._initRequired( this.ui.label );
			this._initLabelIcon( this.ui.title );
			this._initDesc( this.ui.desc );
			this._initInputSize( this.ui.checkbox );
			this._initInputState( this.ui.checkbox );
        	this._initItems();
        	
			
		}
		,
		_initItems: function()
		{
			//var self = this;

			if(! this._seedValues )
			{
				Logger.error("_initItems() - invalid seed value.");
				return;
			}
			for(var i in this._seedValues.items)
			{
				var item_size = ""; // 아이템 크기
				var item_color = "" ;	// 아이템 레이블 컬러

				var item = this._seedValues.items[i];
				if (item.size || ''){ var item_size = "label-"+ item.size};

				this.ui.checkbox.append('<div class="radio '+item_size+' '+item.color+'"><label class="'+item.state+'"><input type="radio" name="'+this.model.get('name')+'" value="'+item.value+'" '+item.state+'><span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span> <span class="title">'+item.label+'</span></label></div>');
			}
			if(this._seedValues.selected)
			{
				this.ui.checkbox.find('option[value="'+this._seedValues.selected+'"]').attr('checked', true);
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
		,
		onShow: function(){
		
		}	
	});
	
	return InputRadioBox;
});