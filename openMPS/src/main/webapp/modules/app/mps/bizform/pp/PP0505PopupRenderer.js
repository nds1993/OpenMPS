/**
 * Project : OpenMPS MIS
 *
 * 생산 > PM라벨실적입고요청 상세보기 팝업
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
 			this._contentId = "PP0505";
 		}
 		,
 		initSubHeader: function()
 		{
 			// 헤더 사용하지 않음
 			return UCMS.retResolve();
 		}
 		,
 		getItemList: function()
 		{
 			return [
 			        "formBox",
 			        "resultBox"
 			        ];
 		}
 		,
 		getHeaderGridName: function()
 		{
			return "resultBox";
 		}
 		,
 		onInitRendererItemEvents: function()
 		{
			this.attachFormItem("formBox");
			this.attachGridItem("resultBox");
			this.onQuery();
 		}
 		,
 		onQuery: function()
 		{
 			var self = this;
 			Renderer.__super__.onQuery.call( this, "detail" )
 			.then(function()
 			{
 				self.getRendererItem("formBox").setItemData(
 				{
 	 				/*
 	 				proName: {
 	 					"keyword": this.getParam("product").proName,
 	 					"result": this.getParam("product").proCode
 	 				},
 	 				*/
 	 				proName: self.getParam("product").proName,
 	 				printCode: self.getParam("product").workQty,
 	 				inStatus: self.getParam("product").inQty
 	 			});
 			});
 		}
 		,
		getQueryData: function()
		{
			return {
				plantCode: this.getParam("plantCode"),
				strtDate: this.getParam("strtDate"),
				proCode: this.getParam("product").proCode
			};
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
