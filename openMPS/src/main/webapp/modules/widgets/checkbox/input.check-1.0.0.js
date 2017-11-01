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
	var	InputCheckBox = FormItemPanel.extend(
	{
		template: "#input_check_html"
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
		 *
									type		{string}
									id			{string}
									label		{string}
									selector	{string}
									required	{boolean}
									value		: {
										items: [
										        //color : ""/"primary","info","success","warning","danger",
										        //size : ""/sm, lg
										        //state : ""/disabled, readonly
										        {label:'항목1',id:"checkbox1",value:1,color:"danger",size:"lg",state:"disabled"},
										        {label:'항목2',id:"checkbox2",value:2,color:null,size:"",state:""},
										        {label:'항목3',id:"checkbox3",value:3,color:null,size:"",state:""},
										        ],
										selected: 3
									},
									prop : {
										icon : {
											view: true, 
											type: 'fa',	
											value: 'fa-mouse-pointer'
										},
										desc : '짧은 설명', 
										label_size : 'label-lg',
										label_align : 'text-left',
										form_layout : 'form-horizontal', 	
										checkbox : {
											align : null,
											layout : null 
										}
									}
		 * 						}
		 * 						배열로 설정되는 경우 첫번째 이미지가 초기값으로 사용된다.
		 */
		initialize: function(options)
		{
			InputCheckBox.__super__.initialize.apply(this, arguments);
			
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

				var item = this._seedValues.items[i];
				if (item.size || ''){ var item_size = "label-"+ item.size};
				this.ui.checkbox.append('<div class="checkbox '+item_size+' '+item.color+'"><label class="'+item.state+'"><input type="checkbox" name="'+this.model.get('name')+'" value="'+item.value+'" '+item.state+'> <span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span> <span class="title">'+item.label+'</span></label></div>');
			}
			/*
			if(this._seedValues.selected)
			{
				this.ui.checkbox.find('option[value="'+this._seedValues.selected+'"]').attr('checked', true);
			}*/
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
			var self = this;
			setTimeout(function(){
				this._initItems();
			}, 500);
		}	
	});
	
	return InputCheckBox;
});