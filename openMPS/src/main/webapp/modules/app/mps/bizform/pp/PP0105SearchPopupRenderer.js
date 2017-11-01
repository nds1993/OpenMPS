/**
 * Project : OpenMPS MIS
 * 
 * 생산 > BOM 코드검색 팝업
 */

define([
	"Logger",
	"NDSProps",
	"SubContainer",
	"APIClient",
	"manifest!CodeSearch-1.0.0#widget",
	"RendererBase",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, SubContainer, APIClient, CodeSearch, RendererBase, GRID)
{
	var Renderer = SubContainer.extend(
	{
		/**
		 * @param {object} options
		 * 					{
		 * 						plantCode: ## 공장 코드
		 * 						keyword: ## 제품 코드. 그런데 호출창은 BOM 코드. 제품코드와 BOM 코드는 같은 값
		 * 					}
		 */
		initialize: function(options)
		{
			Renderer.__super__.initialize.call(this, options);
			
			this._contentId = "PP0105";
			this._deferred = options.deferred || null;
		}
		,
		onEventHandler: function(event)
		{
			var self = this;
			switch( event.cmd )
			{
			case CodeSearch.EVENT.SEARCH:
				RendererBase.Method.onSearchCode.call( this, event.params, function(result)
				{
					if( result && result.code.length > 0 && result.label.length > 0 )
					{
						self.fetchList();
					}
				});
				break;
	
			default:
				return false;
			}
	
			return true;
		}	
		,
		onShowComplete: function()
		{
			var self = this;
			UCMS.retry(function()
			{
				if( self.$el.find(".btn-openmps button.search").length == 0
					||
					!self.getContent() 
					||
					!self.getContent()._getWidgetInstance("queryBox")
					||
					!self.getContent()._getWidgetInstance("queryBox")._getWidgetInstance("proCode")
					||
					!self.getContent()._getWidgetInstance("resultBox")
					)
				{
					return false;
				}
				
				//
				self.initQueryBox();
				self.initGrid();
				self.initButton();
			})
			.fail(function()
			{
				Logger.warn("PP0105SearchPopupRenderer.onShowComplete() - timeout.");
			});
		}
		,
		initButton: function()
		{
			var self = this;
			this.$el.find(".btn-openmps button.search")
			.click(function()
			{
				self.fetchList();
			});
		}
		,
		initQueryBox: function()
		{
			var self = this;
			this._queryBox = this.getContent()._getWidgetInstance("queryBox");
			this._queryBox.setItemData(
			{
				proCode:{
					keyword: "",
					result: this.getParam("keyword")
				}
			});
			this._queryBox.on("widget:event", function(event)
			{
				self.onEventHandler(event);
			});
		}
		,
		initGrid: function()
		{
			var self = this;
			this._gridBox = this.getContent()._getWidgetInstance("resultBox");
			this._gridBox.on(GRID.EVENT.SELECT, function(selected)
			{
				if( self._deferred )
				{
					self._deferred.resolve(
					{
						plantCode: self.getParam("plantCode"),
						product: self._queryBox.getItemData().proCode,
						bom:selected.data 
					});
				}
				else
				{
					Logger.info( selected.data );
				}
			});
		}
		,
		fetchList: function()
		{
			var self = this;
			var hosts = NDSProps.get('hosts') || { api: '' };
			var client = new APIClient(
			{
				host: hosts.api,
				systemCode: NDSProps.get('systemCode'),
				contentId: this._contentId
			});
			var queryData = this._queryBox.getItemData();
			if(! queryData.proCode.result )
			{
				UCMS.alert("제품을 선택하세요.");
				return;
			}
			
			this.showLoading();
			client.read({
				plantCode: this.getParam("plantCode"),
				bomCode: queryData.proCode.result
			}, "search")
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					self._gridBox.addRow( res.extraData.result, "first", true );
					if( res.extraData.result.length == 0 )
					{
						UCMS.alert("등록된 BOM 정보가 없는 제품입니다.");
					}
				}
				else
				{
					UCMS.reportError(res);
				}
			})
			.fail(function(err)
			{
				UCMS.reportError(err);
			})
			.always(function()
			{
				self.hideLoading();
			});
		}
	}
	,
	{
	});

	return Renderer;
});
