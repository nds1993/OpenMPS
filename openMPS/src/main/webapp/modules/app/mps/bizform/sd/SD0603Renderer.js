/**
 * Project : OpenMPS MIS
 *
 * 영업 > 매출현황_유통경로
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
 			this._contentId = "SD0603";
 		}
 		,
 		/**
		 * 1. 헤더 기능 버튼 초기화
		 */
 		initSubHeader: function()
 		{
 			Logger.debug("Renderer.initSubHeader()");

 			var self = this;
 			return this.queryHeaderInfo().then(function(headerParams)
 			{
 				//
 				headerParams.options.feature =
 				[
 					{"query":"조회"},
					{"manual":"도움말"}
 				];

 				RendererBase.Method.initSubHeader.call( self, headerParams );
 			});
 		}
	   ,
 		/**
		 * 2. 접근이 필요한 아이템 목록 지정
		 */
 		getItemList: function()
 		{
 			return [
 			        "queryBox",
 			        "resultBox",
 			        "resultBox.upGridBox",
 			        "resultBox.tabAreaBox",
 			        "resultBox.tabAreaBox.tab_result_view",
 			        "resultBox.tabAreaBox.tab_result_detail"
 			        ];
 		}
 		,
 		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
 		getHeaderGridName: function()
 		{
			return this._activeTabId;

 		}
 		,
 		/**
		 * 4. 랜더러 아이템 관계성 초기화
		 */
 		onInitRendererItemEvents: function()
 		{
 			var self = this;

			this.attachHeaderItem("headerBox");
			this.attachFormItem("queryBox");
			var upGridBox = this.attachGridItem("resultBox.upGridBox");
			var downGridBox = this.attachGridItem("resultBox.tab_result_view");
			this.attachGridItem("resultBox.tab_result_detail");
			
			

 		}
 		,
		getQueryData: function(params)
		{
			var data = RendererBase.Method.getQueryData.call( this, params );
			/*
			 * partCode 테스트 데이터
			 */
			if(data.partCode == "undefined"){
				data.partCode = "5021";
			}
			return data;
		}
 		,
 		onActiveTab: function(params)
 		{
 			this._activeTabId = "resultBox.tabAreaBox."+params.active;
 		}
 		,
 		onQuery: function()
 		{
 			this.fetchHead();
 			var self = this;
			var queryData = this.getQueryData();
			Logger.debug(queryData);
 			Renderer.__super__.onQuery.apply(this, arguments);
 			
 		}
 		,
 		fetchHead: function(){

 			var self = this;
 			var upgridItem = this.attachGridItem("resultBox.upGridBox");
			
			var queryData = this.getQueryData();

			return this.getClient().read(
			{
				strtDate: queryData.strtDate,
				endDate: queryData.endDate,
				partCode: queryData.partCode

			})
			.then(function(res)
			{
				if( res.resultCode == 0 && res.extraData )
				{

					var headerInfo = res.extraData.result;
					upgridItem.setData( headerInfo );
					
				}
				else
				{
					res = null;
				}
				return res;
			})
			.fail(function(err)
			{
				UCMS.reportError(err);
			});

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
