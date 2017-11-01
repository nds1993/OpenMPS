/**
 * Project : OpenMPS MIS
 *
 * 영업 > 매출현황_영업파트
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
 			this._contentId = "SD0605";
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
 			return this._selected == null
			? "resultBox.upGridBox"
			: this._activeTabId;
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
			var gridHead = this.attachGridItem("resultBox.upGridBox");
			this.attachGridItem("resultBox.tabAreaBox.tab_result_view");
			this.attachGridItem("resultBox.tabAreaBox.tab_result_detail");
			
			//gridHead.getItem().on(RGrid.EVENT.SELECT, function(item)
			gridHead.getItem().on(JQGrid.EVENT.SELECT, function(item)
			{
				self.onSelectRow( item );
			});

 		}
 		,
		getQueryData: function(params)
		{
			var data = RendererBase.Method.getQueryData.call( this, params );
			/*
			if( params.plantCode )
			{
				params.plantCode = params.plantCode.result;
			}
			*/
			//data["custCode"] = data["custCode"].result; //농장(새창으로 불러서 값을 부모창에 전달할때)
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
		onSelectRow: function(selected)
		{
			var queryData = this.getQueryData();
			this._selected = selected;
			var activeTabId = this._activeTabId;
			if( selected )
			{
				Renderer.__super__.onQuery.call( this, activeTabId , {"params":{"strtDate": queryData.strtDate, "endDate": queryData.endDate, "partCode": queryData.partCode, "distCode": selected.data.salesman}} );
			}
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
