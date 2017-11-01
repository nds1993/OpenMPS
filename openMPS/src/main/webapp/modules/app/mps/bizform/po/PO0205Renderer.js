/**
 * Project : OpenMPS MIS
 *
 * 구매 > 더느림지급액조회
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
 			this._contentId = "PO0205";
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
 			        "resultBox",
 			        "resultBox.content"
 			        ];
 		}
 		,
 		getHeaderGridName: function()
 		{
			return "resultBox.content";
 		}
 		,
 		onInitRendererItemEvents: function()
 		{
 			var self = this;
			this.attachHeaderItem("resultBox.header", function(evt)
			{
				if( evt.cmd == "content:confirm" )
				{
					self.onConfirm();
				}
			});
			var gridItem = this.attachGridItem("resultBox.content");
			gridItem.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PO0205_grid_1","더느림지급액조액",false);
			});;

			gridItem.getItem().on(JQGrid.EVENT.LOADCOMPLETE, function(data)
			{
				Logger.debug("JQGrid.EVENT.LOADCOMPLETE");
				var useRatesum 							= gridItem.getItem().getCol( "useRate", 		false, "avg" );
				var sex1Dosusum 						= gridItem.getItem().getCol( "sex1Dosu", 		false, "sum" );
				var sex1Pricesum 						= gridItem.getItem().getCol( "sex1Price", 		false, "sum" );
				var sex1PigPric8sum 					= gridItem.getItem().getCol( "sex1PigPric8", 	false, "sum" );
				var sex3Dosusum 						= gridItem.getItem().getCol( "sex3Dosu", 		false, "sum" );
				var sex3Pricesum 						= gridItem.getItem().getCol( "sex3Price", 		false, "sum" );
				var sex3PigPric8sum 					= gridItem.getItem().getCol( "sex3PigPric8", 	false, "sum" );
				var applyDosusum 						= gridItem.getItem().getCol( "applyDosu", 		false, "sum" );
				var applyAmtsum 						= gridItem.getItem().getCol( "applyAmt", 		false, "sum" );
				var dochCntsum 							= gridItem.getItem().getCol( "dochCnt", 		false, "sum" );
				var totRatesum 							= gridItem.getItem().getCol( "totRate", 		false, "avg" );

				gridItem.getItem().footerData('set',
				{
					haccp			: 	"합계",
					useRate 		:	useRatesum 			,
					sex1Dosu 		:	sex1Dosusum 		,
					sex1Price 		:	sex1Pricesum 		,
					sex1PigPric8 	:	sex1PigPric8sum 	,
					sex3Dosu 		:	sex3Dosusum 		,
					sex3Price 		:	sex3Pricesum 		,
					sex3PigPric8 	:	sex3PigPric8sum 	,
					applyDosu 		:	applyDosusum 		,
					applyAmt 		:	applyAmtsum 		,
					dochCnt 		:	dochCntsum 			,
					totRate 		:	totRatesum

				 }
				);
			});

 		}
 		,
		getQueryData: function(params)
		{
			var data = RendererBase.Method.getQueryData.call( this, params );
			if( data.buyType == '0' )
 			{
 				delete data.buyType;
 			}
 			if( data.custCode.length == 0 )
 			{
 				delete data.custCode;
 			}
			Logger.debug(data);
			data["custCode"] = data["custCode"].result;
			return data;
		}
 		,
 		onQuery: function()
 		{
 			var params = this.getQueryData();

 			Renderer.__super__.onQuery.call(this);
 		}
 		,
 		onConfirm: function()
 		{
 			UCMS.alert("API 구현 후 연동");
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
