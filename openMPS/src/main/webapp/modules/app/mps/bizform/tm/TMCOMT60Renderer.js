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
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "TMCOMT60";
		}
		,
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
					{"create":"신규"},
					{"delete":"삭제"},
					{"cancel":"취소"},
					"close"
				];
	
				Renderer.__super__._initSubHeader.call( self, headerParams );
			});
		},
		onShowComplete: function() 
		{
			var self = this;
			
			this._initForm();
			this._initResultGrid()
			
			//FAX 보내기 버튼(save 이벤트)
			this.$el.find("button.btn_user_check").click(function()
			{
				UCMS.confirm("FAX를 보내시겠습니까?")
				.done(function()
				{
					self.triggerMethod("save");
				}
				).fail(doing);
				return false;
			});

			this.$el.find("button.btn_user_juso").click(function()
			{
				self.onJuso();
			});

		},
		onCreate: function()
		{
			var self = this;
			var defaultData = {
				sendName: "openMPS(주)",
				sendPhone: "0221069926"
			};
			
			var newRowId = this._createGridRow( null, defaultData );
			if( newRowId != null )
			{
				this._getTaskPool().add( new RendererBase.CreateTask(newRowId, {}) );
				Logger.info("newRowId : "+newRowId);
				
				var grid = this._getResultGrid(null);
				grid.setSelectRow( newRowId );
				//!silent || grid.editRow( newRowId );
			}
		},
		onJuso: function()
		{
			var self = this;

			this.popupBox("TMCOMT60_pop",
			{
				callback: function(selected)
				{
					// {id:#, data: {}}
					if( selected )
					{
						self._loadBOM = selected.data;
						self.beginTransaction();
						self.setBOMInfo(selected.data);
						self.createTransactionData(selected.data);
						self._dsType = 'C';
					}
				}
			});
		}
	});

	return Renderer;
});
