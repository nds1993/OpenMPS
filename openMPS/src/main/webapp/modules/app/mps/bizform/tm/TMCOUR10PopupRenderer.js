/**
 * Project : OpenMPS MIS
 * 
 * 비밀번호 변경히가 팝업을 구현한다.
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
				if( self.$el.find("input.changeUserCode").length == 0 || self.$el.find("button.btnChangeUserPass").length == 0 || self.$el.find("button.btnChangeUserPassCancel").length == 0)
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
			
			self.$el.find("input.changeUserCode").val(self.options.params.changeUserCode);
			
			self.$el.find("button.btnChangeUserPassCancel").click(function()
			{
				self.$el.parents().find("button.btn_floating_close").click();
			});
			
			self.$el.find("button.btnChangeUserPass").click(function()
			{
				// 비밀번호 변경
				var apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/tmcour10/updateUserPass?";
				
				var changeUserPass1 = self.$el.find("input.changeUserPass1").val();
				var changeUserPass2 = self.$el.find("input.changeUserPass2").val();
				
				if (changeUserPass1 != changeUserPass2) {
					UCMS.alert("입력하신 신규 비밀번호가 틀립니다.");
					return;
				}
				
				var userPass = Sha256.hash(changeUserPass1);
				
				apiPath += "userCode=" + self.options.params.changeUserCode;
				apiPath += "&userPass=" + userPass;
				
				$.ajax(
				{
	                type: 'POST',
	                url:  apiPath,
	                cache: false,
	                crossDomain: true,
	                contentType: 'application/json; charset=utf-8',
	                dataType: "json"
					,
					success: function (data)
	                {
						UCMS.alert("비밀번호가 변경 되었습니다.");				
						self.$el.parents().find("button.btn_floating_close").click();
	                },
	                error: function(XHR, textStatus, errorThrown) 
	                {
	                	UCMS.alert("사용자 비밀번호가 초기화중 오류가 발생하였습니다.");
	                	Logger.error("fetchMyApp() - Error : "+textStatus);
	                }
	            });
				
			});
			
			return;
		}
	});

	return Renderer;
});
