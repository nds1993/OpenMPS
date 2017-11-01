/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved. 
 */

define([
    	"BaroPanelBase", 
    	"Logger"
        ], 
function(  BaroPanelBase, Logger)
{
	/**
	 * BaroBox, RowBox, FormBox 에서 디자인 항목 지정을 위해 사용되는 위젯. 
	 */
	var DesignItem = BaroPanelBase.extend(
	{
		template: _.noop,
		
		/**
		 * 디자인 항목을 설정한다.
		 * 
		 * @param {object} options - 디자인 정보를 지정
		 * 							{
		 * 								tagName: '', 필요시 지정
		 * 								className: '', 필요시 지정
		 * 								html: '' 디자인 정보를 html 형식으로 지정
		 * 							}
		 */
   	    initialize: function(options)
		{
   	    	DesignItem.__super__.initialize.apply(this, arguments);
   	    	
   	    	this._htmlBody = options.html || '';
		}
		,
		onRender: function()
		{
			this.$el.html( this._htmlBody );
		}
	});

	return DesignItem;
	
});