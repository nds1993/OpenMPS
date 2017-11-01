/**
 * Project : OpenMPS MIS
 * 
 * 표준 랜더러 v2.0
 * 유형 NF
 * 
 */

define([
	"Logger",
	"NDSProps",
	"RendererBase",
	"WorkAreaRenderer2",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, RendererBase, WorkAreaRenderer2, JQGrid)
{
	var Renderer = WorkAreaRenderer2.extend(
	{
		initialize: function(options)
		{
			Renderer.__super__.initialize.apply( this, arguments );
			RendererBase.Method.initMethod.apply( this, arguments );
			
			this._contentId = options.contentId;
		}
		,
		getItemList: function()
		{
			return [
			        "queryBox",
			        "resultBox"
			        ];
		}
		,
		getFormName: function()
		{
			return null;
		}
		,
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachFormItem("queryBox");
			this.attachGridItem("resultBox");
			
			//
			this.onQuery();
		}
	});

	return Renderer;
});
