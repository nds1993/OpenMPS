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
					{"create":"신규"},
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
			//this.attachFormItem("formBox2");
			
			//
			var gridItem = this.attachGridItem("resultBox");
			gridItem.getItem().on(JQGrid.EVENT.SELECT, function(item)
			{
				self.onSelectRow( item );
			});
			
			this.onQuery();
			
			self.$el.find("button.btn_save").click(function()
			{
				self.saveTMCOSM10();
			});
			
			self.$el.find("button.btn_delete").click(function()
			{
				
				UCMS.confirm("삭제 하시겠습니까?", { confirm: "확인", cancel: "취소" })
				.done(function()
				{
					self.deleteTMCOSM10();
				});
				
			});
			
			self.$el.find("button.btn_approval").click(function()
			{
		
				UCMS.confirm("상신 하시겠습니까?", { confirm: "확인", cancel: "취소" })
				.done(function()
				{
					self.approvalTMCOSM10();
				});
		
			});
			
			self.$el.find("button.btn_re_approval").click(function()
			{
				
				UCMS.confirm("재상신 하시겠습니까?", { confirm: "확인", cancel: "취소" })
				.done(function()
				{
					self.approvalTMCOSM10();
				});
				
			});
		}
		,
		onCreate: function()
		{
			if( this.getActiveForm() )
			{
				this.getActiveForm().getItem().disabled(false);
			}
			this.createShow();
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
			self.$el.find("button.btn_save").hide();
			self.$el.find("button.btn_delete").hide();
			self.$el.find("button.btn_approval").hide();
			self.$el.find("button.btn_re_approval").hide();
			self.$el.find("div.proc_result_box").hide();
		}
		,
		createShow: function() 
		{
			var self = this;
			self.allButtonHide();
			self.initParamData();
			self.$el.find("button.btn_save").show();
			self.$el.find("button.btn_approval").show();
		}
		,
		contentShow: function() 
		{
			var self = this;
			self.allButtonHide();
			
			var procStatus = self.$el.find("input.procStatus").val();
			
			if (procStatus == "SAVE") {				
				self.$el.find("button.btn_save").show();
				self.$el.find("button.btn_delete").show();
				self.$el.find("button.btn_approval").show();
			} else if (procStatus == "RETURN") {
				self.$el.find("button.btn_delete").show();
				self.$el.find("button.btn_re_approval").show();
			} else {
				var formItem = this.attachFormItem("formBox");
				formItem.getItem().disabled(true );
			}
			
			if (procStatus == "PROCESS" || procStatus == "FINISH" || procStatus == "RETURN") {
				self.$el.find("div.proc_result_box").show();
			} else {
				self.$el.find("div.proc_result_box").hide();
			}
		}
		,
		saveTMCOSM10: function() 
		{
			
			var self = this;
			
			if( self.$el.find("input.servTitle").val() == "") {
				UCMS.alert("제목을 입력하세요!");
				return;
			}
			
			if( self.$el.find("div.rqstType select").val() == "") {
				UCMS.alert("요청유형을 선택하세요!");
				return;
			}
			
			if( self.$el.find("textarea.servCont").val() == "") {
				UCMS.alert("내용을 입력하세요!");
				return;
			}
		
			var apiPath = "";
			var apiMode = "";
			
			if(self.$el.find("input.servId").val()){				
				// 수정
				apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOSM10/updateTMCOSM10";
			} else {
				//신규 저장
				apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOSM10/saveTMCOSM10";				
			}
			
			self.submitTMCOSM10(apiPath, apiMode);
		}
		,
		deleteTMCOSM10: function() 
		{
			
			var self = this;
		
			var apiPath = "";
			
			apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOSM10/deleteTMCOSM10";
			
			self.submitTMCOSM10(apiPath, "");
		}
		,
		approvalTMCOSM10: function() 
		{
			
			var self = this;
			
			var apiPath = "";
			
			if(self.$el.find("input.procStatus").val() == "RETURN") {
				// 재상신
				apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOSM10/reApprovalTMCOSM10";
			} else {
				
				if(self.$el.find("input.servId").val()){
					// 상신
					apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOSM10/approvalTMCOSM10";
				} else {					
					// 저장 + 상신
					apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOSM10/saveApprovalTMCOSM10";
				}
			}
			
			
			self.submitTMCOSM10(apiPath, "approval");
		}
		,
		submitTMCOSM10: function(apiPath, mode) 
		{
			
			var self = this;
		
			self.showLoading();

			$.ajax(
			{
				url:  apiPath,
				data: self.getParamData(), 
				processData: false,
				contentType: false, 
				type: 'POST',
				success: function (data)
                {
					if(mode == "approval") {
						UCMS.alert("상신되었습니다.");
					}
					self.onQuery();
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	UCMS.alert("저장중 오류가 발생하였습니다.");
                	Logger.error("fetchMyApp() - Error : "+textStatus);
                	self.hideLoading();
                }
            });
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
