/**
 * Project : OpenMPS MIS
 *
 * 구매 > 출하정산집계표조회
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
], function(Logger, NDSProps, BaroBox, FormBox, WorkAreaHeader, RendererBase, WorkAreaRenderer2, JQGrid) {
	var Renderer = WorkAreaRenderer2.extend({
		initialize: function(options) {
			Renderer.__super__.initialize.apply(this, arguments);
			this._contentId = "PO0203";
		},
		/**
		 * 1. 헤더 기능 버튼 초기화
		 */
		initSubHeader: function() {
			Logger.debug("Renderer.initSubHeader()");

			var self = this;
			return this.queryHeaderInfo().then(function(headerParams) {
				//
				headerParams.options.feature = [{
						"query": "조회"
					},
					{
						"print": "인쇄"
					},
					{
						"delete": "삭제"
					},
					{
						"cancel": "취소"
					}
				];

				RendererBase.Method.initSubHeader.call(self, headerParams);
			});
		},
		/**
		 * 2. 접근이 필요한 아이템 목록 지정
		 */
		getItemList: function() {
			return [
				"queryBox",
				"resultBox_fac0",
				"resultBox_date0",
				"resultBox_fac1",
				"resultBox_date1"
			];
		},
		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
		getHeaderGridName: function() {
			return this._gubun4 == "1" ?
				(this._gubun2 == "1" ? "resultBox_date1" : "resultBox_date0") :
				(this._gubun2 == "1" ? "resultBox_fac1" : "resultBox_fac0");
		},
		/**
		 * 4. 랜더러 아이템 관계성 초기화
		 */
		onInitRendererItemEvents: function() {
			var self = this;

			this.attachHeaderItem("headerBox");
			this.attachFormItem("queryBox");
			var gridItemFac0 = this.attachGridItem("resultBox_fac0");
			gridItemFac0.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PO0203_grid_1","출하정산집계표조회",false);
			});
			var gridItemFac1 = this.attachGridItem("resultBox_fac1");
			gridItemFac1.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PO0203_grid_3","출하정산집계표조회",false);
			});
			//
			var gridItemDate0 = this.attachGridItem("resultBox_date0");
			gridItemFac1.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PO0203_grid_2","출하정산집계표조회",false);
			});

			var gridItemDate1 = this.attachGridItem("resultBox_date1");
			gridItemDate1.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PO0203_grid_4","출하정산집계표조회",false);
			});

			//
			this.getQueryData();
			this.switchResultGrid();
		},
		getQueryData: function(params) {
			var data = RendererBase.Method.getQueryData.call(this, params);
			if (data["custCode"]) {
				data["custCode"] = data["custCode"].result;
			}
			this._gubun2 = data.gubun_2;
			this._gubun4 = data.gubun_4;
			return data;
		},
		switchResultGrid: function() {
			// 농장합계
			this.getRendererItem("resultBox_fac0").showPanel(this._gubun2 == "0" && this._gubun4 == "0");
			this.getRendererItem("resultBox_fac1").showPanel(this._gubun2 == "1" && this._gubun4 == "0");
			// 일계
			this.getRendererItem("resultBox_date0").showPanel(this._gubun2 == "0" && this._gubun4 == "1");
			this.getRendererItem("resultBox_date1").showPanel(this._gubun2 == "1" && this._gubun4 == "1");
		},
		onQuery: function() {
		    var self = this;
		    var queryData = this.getQueryData();
		    if( new Date(queryData.strtDate) > new Date(queryData.lastDate) )
		    {
		        UCMS.alert("조회 기간이 잘못 지정되었습니다.");
		        return;
		    }
			Renderer.__super__.onQuery.apply(this, arguments);
			this.switchResultGrid();
		}
	}, {
		EVENT: {}
	});

	return Renderer;
});
