/**
 * Project : OpenMPS MIS
 * 
 * 클레임 처리요청 반려사유 입력 팝업을 구현한다.
 */

define([
	"Logger",
	"NDSProps",
	"SubContainer",
	"FormBox",
	"RendererBase",
	"manifest!CodeSearch-1.0.0",
	"WorkAreaHeader"
], function(Logger, NDSProps, SubContainer, FormBox, RendererBase, CodeSearch, WorkAreaHeader)
{
	var Renderer = SubContainer.extend(
	{
		onShowComplete: function()
		{
			var self = this;
			
			UCMS.retry(function()
			{
				if( self.$el.find("input.servId").length == 0 || self.$el.find("button.btnConfirm").length == 0 || self.$el.find("button.btnCancel").length == 0)
				{
					return false;
				}
				
				self._initPopup();
				
				return true;
			});
			
		}
		,
		_initPopup: function()
		{
			var self = this;
			
			self.$el.find("input.servId").val(self.options.params.servId);
			
			self.$el.find("button.btnCancel").click(function()
			{
				self.$el.parents().find("button.btn_floating_close").click();
			});
			
			self.$el.find("button.btnConfirm").click(function()
			{
				// 반려
				if( self.$el.find("textarea.procCont").val() == "") {
					UCMS.alert("반려사유를 입력하세요!");
					return;
				}
				
				var apiPath = "/rest/mpm/" + NDSProps.get("corpCode") + "/SD0502/rejectSD0502";
				
				self.submitSD0502(apiPath, "");
			});
			
			return;
		}
		,
		submitSD0502: function(apiPath, mode) 
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
					UCMS.alert("반려되었습니다.");
					self.options.callback(true);
					self.$el.parents().find("button.btn_floating_close").click();
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	UCMS.alert("저장중 오류가 발생하였습니다.");
                	Logger.error("fetchMyApp() - Error : "+textStatus);
                }
            });
		}
		,
		getParamData: function() {
			
			var self = this;
			var formData = new FormData();
			
			formData.append("servId", self.$el.find("input.servId").val());
			formData.append("procCont", self.$el.find("textarea.procCont").val());
			
			return formData;
		}
	});

	return Renderer;
});
