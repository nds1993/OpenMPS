/**
 * Project : NDS MPS
 * 
 * 서비스 요청 등록 화면을 구현한다.
 * ID : TMCOSM10
 * 
 */

define([
	"Logger",
	"NDSProps",
	"RendererBase",
	"BaroBox",
	"FormBox",
	"WorkAreaHeader",
	"manifest!jqGrid4-1.0.0#widget",
	"WorkAreaRenderer2"
], function(Logger, NDSProps, RendererBase, BaroBox, FormBox, WorkAreaHeader, JQGrid, WorkAreaRenderer2)
{
	var OverridingMethod =
	{
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
					"close"
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
			        "formBox",
			        "formBox2",
			        "resultBox"
			        ];
		}
		,
		/**
		 * 5. 랜더러 아이템 관계성 초기화
		 */
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachFormItem("queryBox");
			
			//
			var gridItem = this.attachGridItem("resultBox");
			gridItem.getItem().on(JQGrid.EVENT.SELECT, function(item)
			{
				self.onSelectRow( item );
			});
			
			this.onQuery();
			
		}
		,onSelectRow: function(selected)
		{
			var formItem2 = this.getActiveForm("formBox2");
			
			if( formItem2 )
			{
				formItem2.setData( selected.data, true );
				formItem2.getItem().disabled(false);
			}
			
			var formItem = this.getActiveForm("formBox");
			
			if( formItem )
			{
				formItem.setData( selected.data, true );
				formItem.getItem().disabled(false);
			}
			
			this.contentShow();
		}
		,
		/**
		 * 그리드 데이타를 조회한다.
		 * @param {string} featureId - 조회 기능 식별자
		 */
		onQuery: function(featureId, options)
		{
			var self = this;
			var fetching = function()
			{
				self.showLoading();
				self.fetchingGrid(featureId, options)
				.always(function(e)
				{
					self.allButtonHide();
					self.clearForm();
					self.hideLoading();
				});
			};
			
			Logger.info("Renderer.onQuery");
			
			fetching();
		}
	
	}
	
	var WorkAreaMethod = 
	{
		allButtonHide: function() 
		{
			var self = this;
		}
		,
		contentShow: function() 
		{
			var self = this;
			self.allButtonHide();
			
			var procStatus = self.$el.find("input.procStatus").val();
			
			if (procStatus == "PROCESS" || procStatus == "FINISH" || procStatus == "RETURN") {
				self.$el.find("div.proc_result_box").show();
			} else {
				self.$el.find("div.proc_result_box").hide();
			}
			
		}
		,initParamData: function() {
			
			var self = this;
			
			self.$el.find("input.servId").val("");
			self.$el.find("input.procStatus").val("");
			self.$el.find("input.servTitle").val("");
			self.$el.find("textarea.servCont").val("");
			self.$el.find("input.rqfnDate").val("");
			
			self.$el.find("div.rqstType select option:eq(0)").prop("selected", true);
			self.$el.find("div.rqstType select").change();
			
		}
		,getParamData: function() {
			
			var self = this;
			var formData = new FormData();
			
			formData.append("servId", self.$el.find("input.servId").val());
			formData.append("servTitle", self.$el.find("input.servTitle").val());
			formData.append("rqstType", self.$el.find("div.rqstType select").val());
			formData.append("servCont", self.$el.find("textarea.servCont").val());
			formData.append("rqfnDate", self.$el.find("input.rqfnDate").val());
			
			return formData;
		}
	};
	
	var Renderer = WorkAreaRenderer2.extend
	(
		_.extend(OverridingMethod, WorkAreaMethod)
	);
	 
	return Renderer;
});
