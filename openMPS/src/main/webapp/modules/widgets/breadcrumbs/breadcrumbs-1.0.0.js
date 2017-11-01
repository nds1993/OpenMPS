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
	 * 경로를 보여주는 위젯을 구현한다.
	 */
	var	BreadcrumbBox =  BaroPanelBase.extend(
	{
		tagName : "ol",
		className : "breadcrumb breadcrumb_box",
		ui:
		{
		}
		,
		/**
		 * 경로위젯을 초기화한다.
		 * 
		 * @params options		
			{
				breadcrumbs_item: [
				    *{ 
				    * label:'버튼1', // 버튼에 쓰여질 텍스트입니다. 
				    * icon:{view:true,type:"fa",value:"fa-paw"},  // 기존 아이콘과 동일합니다. //값이 없을 때 대비 
				    * cmd:"" // 명령어를 입력합니다. 
				    * icon:{view:true,type:"fa",value:"fa-paw"}, 
				    * active : true , 없어도 됨 트루이면 해당 li 에 active 클래스 삽입  
				    * cmd:"#aa" : 값이 없어도 됨 . 있으면 해당 링크 이동 가능
				    * }
				    *  
	
				   {label:'영업' },
				   {label:'자료입력'},
				   {label:'주문입력',icon:{view:true,type:"fa",value:"fa-paw"}, active :true , cmd: ""}
				 ],
				breadcrumbs_layout : "", // 필요에 의해 채워 넣으세요. 클래스명을 넣으시면 됩니다. 
				text_align : "text-right" // 텍스트 정렬 	
			};
		 */
		initialize: function(options)
		{
			BreadcrumbBox.__super__.initialize.apply(this, arguments);

			if( options.model == undefined )
			{
				this.model = new Backbone.Model
	   	    	(
	   	    		_.extend(/*{ title: "" },*/ {
	   	    			item : options.breadcrumbs_item || [],
	   	    			layout : options.btn_group_layout,
	   	    			text_align: options.text_align
					} )
	   	    	);
			}							
			var self = this;
			this.template = function(data)
			{
				return self._initItems(data);
			};
		}
		,
		onBeforeShow: function()
		{
			this.$el.addClass(this.model.get('text_align'));
			//this._initItems();
		}
		,
		_initItems: function(data)
		{
			if(! data.item )
			{
				Logger.error("_initItems() - invalid seed value.");
				return;
			}
			
			for(var i in data.item)
			{
				var bitem = data.item[i];
				var bitem_active = "";
				var bitem_icon = "";
				var bitem_cmd = "";
				if (bitem.active==true) {
					   bitem_active = ' class="active"';
					}
				if (!(bitem.cmd == null || bitem.cmd == "")) {
						bitem_cmd = bitem.cmd;
					}
				if (bitem.icon && bitem.icon.view==true) { bitem_icon = this._makeIconTag(bitem.icon)} // 아이콘 할당 그룹은 항상 앞에만 아이콘 할당함.  
				this.$el.append('<li'+bitem_active+'>'+bitem_icon+'<span class="bitem_label '+bitem_cmd+'">'+bitem.label+'</span></li>');
			}

		},
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
	
	return BreadcrumbBox;
});