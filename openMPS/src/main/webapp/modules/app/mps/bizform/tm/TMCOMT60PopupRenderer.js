/**
 * Project : OpenMPS MIS
 * 
 * 관리 > 주소록 불러오기 팝업
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
		 * 					}
		 */
		initialize: function(options)
		{
			Renderer.__super__.initialize.call(this, options);
			
			this._contentId = "TMCOMT60";
			this._deferred = options.deferred || null;
		}
		,
		onEventHandler: function(event)
		{
			switch( event.cmd )
			{
			case CodeSearch.EVENT.SEARCH:
				RendererBase.Method.onSearchCode.call( this, event.params );
				break;

			default:
				return false;
			}

			return true;
		}
		,
		getClient: function()
		{
			if(! this._client )
			{
				var hosts = NDSProps.get('hosts') || { api: '' };
				this._client = new APIClient(
				{
					host: hosts.api,
					systemCode: NDSProps.get('systemCode'),
					contentId: this._contentId
				});	
			}
			return this._client;
		}
		,
		onShowComplete: function()
		{
			var self = this;
			UCMS.retry(function()
			{
				if( !self.getContent() 
					||
					!self.getContent()._getWidgetInstance("queryBox")
					||
					!self.getContent()._getWidgetInstance("resultBox")
					||
					!self.getContent()._getWidgetInstance("resultBox")._getWidgetInstance("leftGrid")
					||
					!self.getContent()._getWidgetInstance("resultBox")._getWidgetInstance("leftGrid").getContent()
					||
					!self.getContent()._getWidgetInstance("resultBox")._getWidgetInstance("rightGrid")
					||
					!self.getContent()._getWidgetInstance("resultBox")._getWidgetInstance("rightGrid").getContent()
					||
					self.$el.find(".btn-openmps button.search").length == 0
					||
					self.$el.find(".btn-openmps button.confirm").length == 0
					)
				{
					return false;
				}
				
				//
				self.initGrid();
				self.initButton();
				self.initQueryBox();
			});
		}
		,
		initButton: function()
		{
			var self = this;
			this.$el.find(".btn-openmps button.search")
			.click(function()
			{
				self.fetchHeadList();
			});
			this.$el.find(".btn-openmps button.confirm")
			.click(function()
			{
				//var selectedRowId = self._gridHead.getSelRowId();
				if( self._selected )
				{
					//var rowData = self._gridHead.getRowData( selectedRowId );
					if( self._deferred )
					{
						Logger.debug("initButton()");
						Logger.debug(self._selected);
						self._deferred.resolve(self._selected);
					}
					else
					{
						Logger.info( self._selected );
					}
				}
				else
				{
					UCMS.alert("불러올 BOM을 선택하세요.");
				}
			});
		}
		,
		initQueryBox: function()
		{
			var self = this;
			this._queryBox = this.getContent()._getWidgetInstance("queryBox");
			this._queryBox.on("widget:event", function(event)
			{
				self.onEventHandler(event);
			});
		}
		,
		initGrid: function()
		{
			var self = this;
			this._gridHead = this.getContent()._getWidgetInstance("resultBox")._getWidgetInstance("leftGrid").getContent();
			this._gridHead.on(GRID.EVENT.SELECT, function(selected)
			{
				self._selected = selected;
				var queryData = self._queryBox.getItemData();
				self._selected.data.plantCode = queryData.plantCode;
				self.fetchDetailList(selected);
			});
			this._gridDetail = this.getContent()._getWidgetInstance("resultBox")._getWidgetInstance("rightGrid").getContent();
		}
		,
		fetchHeadList: function()
		{
			var self = this;
			var queryData = this._queryBox.getItemData();
			if(! queryData.proCode.result )
			{
				UCMS.alert("대상 제품을 선택하세요.");
				return;
			}
			
			this.showLoading();
			
			//
			this._gridHead.clear();
			this._gridDetail.clear();
			
			//
			this.getClient().read({
				plantCode: queryData.plantCode,
				bomCode: queryData.proCode.result
			}, "history")
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					self._gridHead.addRow( res.extraData.result, "first", true );
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
		,
		fetchDetailList: function(item)
		{
			var self = this;
			var queryData = this._queryBox.getItemData();
			
			this.showLoading();
			
			//
			this._gridDetail.clear();
			this.getClient().read({
				plantCode: queryData.plantCode,
				bomCode: queryData.proCode.result,
				bomVer: item.data.bomVer
			}, "detail")
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					self._gridDetail.addRow( res.extraData.result, "first", true );
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
