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
			
			self.$el.find("button.btn_recipt").click(function()
			{
				
				var servId = self.$el.find("input.servId").val();
				
				if (servId == "") {
					UCMS.alert("요청서를 선택하세요!");
					return;
				}
		
				UCMS.confirm("접수 하시겠습니까?", { confirm: "확인", cancel: "취소" })
				.done(function()
				{
					self.reciptTMCOSM30();
				});
		
			});
			
			self.$el.find("button.btn_finish").click(function()
			{
				
				var servId = self.$el.find("input.servId").val();
				
				if (servId == "") {
					UCMS.alert("요청서를 선택하세요!");
					return;
				}
				
				if (self.$el.find("textarea.procCont").val() == "") {
					UCMS.alert("처리 내역을 입력하세요!");
					return;
				}
		
				UCMS.confirm("완료 하시겠습니까?", { confirm: "확인", cancel: "취소" })
				.done(function()
				{
					self.finishTMCOSM30();
				});
		
			});
			
			self.$el.find("button.btn_reject").click(function()
			{
		
				var servId = self.$el.find("input.servId").val();
				
				if (servId == "") {
					UCMS.alert("요청서를 선택하세요!");
					return;
				}
				
				self.popupBox("TMCOSM20_pop",
				{
					"params": {
						// 사용자 ID
						servId: servId
					},
					"callback": function(result)
					{
						Logger.debug(result);
						
						if (result) {							
							self.onQuery();
						}
					}
				});
		
			});
		}
		,onSelectRow: function(selected)
		{
			var self = this;
			self.initParamData();
			
			var formItem = this.getActiveForm();
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
					self.initParamData();
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
			self.$el.find("button.btn_recipt").hide();
			self.$el.find("button.btn_finish").hide();
			self.$el.find("button.btn_reject").hide();
		}
		,
		contentShow: function() 
		{
			
			var self = this;
			self.allButtonHide();
			
			var procStatus = self.$el.find("input.procStatus").val();
			
			if (procStatus != "PROCESS") {				
				self.$el.find("button.btn_recipt").show();
				self.$el.find("button.btn_reject").show();
			}
			
			if (procStatus == "PROCESS") {				
				self.$el.find("button.btn_finish").show();
			}
			
		}
		,
		reciptTMCOSM30: function() 
		{
			
			var self = this;
			
			var apiPath = "";
			
			apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOSM30/reciptTMCOSM30";
			
			self.submitTMCOSM30(apiPath, "recipt");
		}
		,
		finishTMCOSM30: function() 
		{
			
			var self = this;
			
			var apiPath = "";
			
			apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOSM30/finishTMCOSM30";
			
			self.submitTMCOSM30(apiPath, "finish");
		}
		,
		submitTMCOSM30: function(apiPath, mode) 
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
					if(mode == "recipt") {
						UCMS.alert("접수되었습니다.");
					} else if(mode == "finish") {
						UCMS.alert("완료되었습니다.");
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
			self.$el.find("textarea.procCont").val("");
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
			formData.append("procCont", self.$el.find("textarea.procCont").val());
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
