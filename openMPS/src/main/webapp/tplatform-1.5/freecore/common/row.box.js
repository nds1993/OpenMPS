/**
 * Project : MobileOven
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
([
	"BaroBox",
	"BaroProps",
	"BaroPanelBase",
	"Logger"
]
,
function( BaroBox, BaroProps, BaroPanelBase, Logger )
{
	/**
	 * 기등록된 폼과 사용자 정의된 입력 폼을 구현한다.
	 * 본 폼 객체는 입력 완료(confirm) 및 취소(cancel) 동작에 대한 이벤트를 발생시킨다.
	 */
	var RowBox = BaroBox.extend(
	{
		template: _.noop
		,
		constructor: function(options)
		{
			if( options && !options.className )
			{
				// className 를 멤버변수로 선언하여 사용하면,
				// FormBox 에도 클래스가 적용되는 문제가 발생한다.
				options.className = 'row';
			}
			RowBox.__super__.constructor.apply( this, arguments );
		}
		,
		/**
		 * Row 박스를 초기화한다.
		 * 
		 * @param {object} options - BaroBox 초기화 파라메터에 아래의 정보가 추가될 수 있다.
		 * 							{
		 * 								col_option: {string}
		 * 								col_size: {number||object} 기본값 1. 1 ~ 12 사이에서 지정 가능.
		 * 											BaroBox 파라메터로 초기화하는 경우 {object} 지정.
		 * 											추가되는 위젯 별로 사이즈를 지정한다. "widgetId : col_size" 형식으로 지정한다.  
		 * 							}
		 */
		initialize: function(options)
		{
			RowBox.__super__.initialize.call( this, _.extend({'col_size':1},options) );
			this._colOption = this._detectColOption();
		}
		,
		render: function()
		{
			// before:render, render 이벤트가 발생된다.
			RowBox.__super__.render.call(this);
		}
		,
		/**
		 * 화면 폭의 크기를 고려해서 컬럼에 적용될 옵션 항목을 설정한다.
		 */
		_detectColOption: function()
		{
			//if( this.model.has("col_option") )
			if( this.getParam("col_option") )
			{
				this._colOption = 'col-'+this.getParam("col_option")+'-';
				return this._colOption;
			}
			
			var width = UCMS.getWindowWidth();
			
			if( width < 768 )
			{
				this._colOption = 'col-xs-';
			}
			else if( width < 992 )
			{
				// >= 768
				this._colOption = 'col-sm-';
			}
			else if( width < 1200 )
			{
				// >= 992
				this._colOption = 'col-md-';	
			}
			else
			{
				// >= 1200
				this._colOption = 'col-ls-';	
			}
			
			Logger.debug("_detectColOption() - "+this._colOption);
			
			return this._colOption;
		}
		,
		/**
		 * 위젯의 컬럼 사이즈를 얻는다.
		 * 개별로 지정하지 않은 경우 세팅된 초기값이 제공된다.
		 * 
		 * @returns {number} 제공할 수 없는 조건인 경우 -1 반환
		 */
		_getColSize: function(widgetId)
		{
			var sizeInfo = this.getParam('col_size');
			if( typeof sizeInfo == 'number' )
			{
				return sizeInfo;
			}
			else if( widgetId )
			{
				// 지정되지 않는 컬럼이 존재하는 경우 최대치로 설정
				return sizeInfo[ widgetId ] || 12;
			}
			else
			{
				Logger.error("_getColSize() - Can't use a column function.");
				return -1;
			}
		}
		,
		/**
		 * 파라메터로 생성되는 경우 사용될 Box 골격을 생성한다.
		 * 위젯이 배치될 위치를 식별하기 위해서 "위젯이름_region"의 식별자가 추가된다.
		 */
		_makeBoxSkeleton: function(regionList)
		{
			if(! this._colOption )
			{
				// 자식 클래스가 활성화시 RowBox 옵션을 사용하지 않은 경우
				return RowBox.__super__._makeBoxSkeleton.call(this, regionList);
			}
			
			var $parentTag = $("<div class='contents_box'></div>");
			for(var idx in regionList)
			{
				var col_class = this._colOption+this._getColSize(regionList[idx]);
				// TODO region 식별자 지정시 postfix 방식으로 위젯 식별자에 "_region" 을 붙인다.
				$parentTag.append( '<div class="'+col_class+' '+regionList[idx]+'_region"></div>' );
			}

			return $("<dummy_parent>").append($parentTag).html();
		}
		,
		/**
		 * 컬럼 항목을 담을 공간을 생성한다.
		 * 위젯이 배치될 위치를 식별하기 위해서 "위젯이름_region"의 식별자가 추가된다.
		 * 
		 * @param {string} rowItemId - 추가되는 항목의 식별자
		 * @param {number} itemSize - 추가되는 항목의 컬럼 사이즈. 지정하지 않으면 컬럼 class 없이 추가된다.
		 * 
		 * @returns {$} 추가된 택스의 jquery 선택자
		 */
		_createItemHolder: function(rowItemId, itemSize)
		{
			var col_class = null;
			if( this._colOption && itemSize )
			{
				col_class = this._colOption+itemSize+' ';
			}

			col_class += rowItemId+'_region';
			return this.$el.append('<div class="'+col_class+'"></div>');
		}
		,
		/**
		 * 컬럼 항목을 추가한다.
		 * 영역이 추가되고 항목이 출력된다. 추가된 영역의 이름은 위젯 식별자이다.
		 * 
		 * @param {string} rowItemId - 아이템 식별자. 추가되는 모든 아이템은 구별될 수 있는 식별자를 갖는다.
		 * @param {BaroPanelBase} rowItemObj - 아이템 인스턴스. 제공된 아이템은 grid 체계가 적용된 row region 에 출력된다.
		 * @param {number} itemSize - 아이템이 담길 holder 사이즈. 1 ~ 12 사이에서 지정 가능. 지정하지 않으면 RowBox 초기화시 제공된 값이 적용됨.
		 * @return {boolean}
		 */
		addItem: function(rowItemId, rowItemObj, itemSize)
		{
			if( this[rowItemId] )
			{
				// 이미 존재하는 구분자
				Logger.error("addItem() - Already has a item : "+rowItemId);
				return false;
			}
			if( rowItemObj instanceof BaroPanelBase == false )
			{
				Logger.error("addItem() - Invalid item parameter.");
				return false;
			}
			
			// 영역 생성
			itemSize || (itemSize = this._getColSize());
			this._createItemHolder(rowItemId, itemSize);
			
			// 영역을 선택하고 아이템을 추가한다.
			var region = {};
			region[rowItemId] = "."+rowItemId+"_region";
			this.addRegions( region );
			
			//
			this._setWidgetInstance( rowItemId, rowItemObj );
			this[rowItemId].show( rowItemObj );
			
			return true;
		}
	});
	
	return RowBox;
});