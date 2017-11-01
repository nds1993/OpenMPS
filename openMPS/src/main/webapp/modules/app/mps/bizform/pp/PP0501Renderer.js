/**
 * Project : OpenMPS MIS
 *
 * 생산 > 부자재 출고오류 현황
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
 			this._contentId = "PP0501";
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
					{"query":"조회"}
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
 			        "resultBox"
 			        ];
 		}
 		,
 		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
 		getHeaderGridName: function()
 		{
			return "resultBox";
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
			this.attachGridItem("resultBox").getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0501_grid_1","부자재춢고현황",false);
			});;
 		}
 		,
		getQueryData: function(params)
		{
			var data = RendererBase.Method.getQueryData.call( this, params );
			if( data.plantCode == "0" )
			{
				data = _.pick( data, "strtDate", "lastDate" );
			}
			return data;
		},
		onQuery: function(featureId, options)
		{
			var queryData = this.getQueryData();
			if( new Date(queryData.strtDate) > new Date(queryData.lastDate) )
			{
				UCMS.alert("조회일자에서 오류가 발견되었습니다.");
				return;
			}
			Renderer.__super__.onQuery.call( this, featureId, options )
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
