/**
 * Project : OpenMPS MIS
 *
 * 생산 > 자재 소요량 조회
 *
 */


 define([
 	"Logger",
 	"NDSProps",
 	"BaroBox",
 	"FormBox",
 	"WorkAreaHeader",
 	"RendererBase",
 	"WorkAreaRenderer2",
 	"manifest!jqGrid4-1.0.0#widget"
 ], function(Logger, NDSProps, BaroBox, FormBox, WorkAreaHeader, RendererBase, WorkAreaRenderer2, JQGrid)
 {
 	var Renderer = WorkAreaRenderer2.extend(
 	{
 		initialize: function(options)
 		{
 			Renderer.__super__.initialize.apply( this, arguments );
 			this._contentId = "PP0402";
 		}
 		,
 		initSubHeader: function()
 		{
 			Logger.debug("Renderer.initSubHeader()");

 			var self = this;
 			return this.queryHeaderInfo().then(function(headerParams)
 			{
 				//
 				headerParams.options.feature =
 				[
					{"query":"조회"}
 				];

 				RendererBase.Method.initSubHeader.call( self, headerParams );
 			});
 		}
 		,
 		getItemList: function()
 		{
 			return [
 			          "queryBox",
 			          "tabAreaBox",
		              "tabAreaBox.tab_result_1",
		              "tabAreaBox.tab_result_2"
 			        ];
 		}
 		,
 		getHeaderGridName: function()
 		{
			return "tabAreaBox."+this._activeTabId;
 		}
 		,
 		onInitRendererItemEvents: function()
 		{
 			var self = this;

			this.attachHeaderItem("headerBox");
			this.attachFormItem("queryBox");
			this.attachGridItem("tabAreaBox.tab_result_1").getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0402_grid_1","자재소요량조회_제품별",false);
			});;
			this.attachGridItem("tabAreaBox.tab_result_2").getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0402_grid_2","자재소요량조회_자재별",false);
			});;

 		}
 		,
		getQueryData: function(params)
		{
			var data = RendererBase.Method.getQueryData.call( this, params );
			if( data.plantCode == 0 )
			{
				data = _.pick( data, "strtDate", "lastDate" );
			}
			return data;
		}
 		,
 		onActiveTab: function(params)
 		{
 			this._activeTabId = params.active;
 		}
 		,
 		onQuery: function(featureId, options)
 		{
			var queryData = this.getQueryData();
			if( new Date(queryData.strtDate) > new Date(queryData.lastDate) )
			{
				UCMS.alert("조회 기간이 잘못 지정되었습니다.");
				return;
			}
 			Renderer.__super__.onQuery.call( this, this._activeTabId );
 		}
 	}
 	,
 	{
 		EVENT:
 		{
 		}
 	});

 	return Renderer;
 });
