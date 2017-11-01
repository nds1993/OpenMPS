/**
 * Project : OpenMPS MIS
 * 
 * 표준 랜더러 v2.0
 * 
 */

define([
	"Logger",
	"NDSProps",
	"RendererBase",
	"BaroBox",
	"FormBox",
	"WorkAreaHeader",
	"WorkAreaRenderer",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, RendererBase, BaroBox, FormBox, WorkAreaHeader, WorkAreaRenderer, JQGrid)
{
	var Renderer = WorkAreaRenderer.extend(
	{
		_initSubHeader: function()
		{
			Logger.debug("Renderer.initSubHeader()");
	
			var self = this;
			return this._queryHeaderInfo().then(function(headerParams)
			{
				//
				headerParams.options.feature =
				[
					{"query":"조회"},
					{"cancel":"취소"},
					"close"
				];
	
				Renderer.__super__._initSubHeader.call( self, headerParams );
			});
		}
	});

	return Renderer;
});
