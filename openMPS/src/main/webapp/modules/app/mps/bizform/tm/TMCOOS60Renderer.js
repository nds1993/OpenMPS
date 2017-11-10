/**
 * Project : NDS MPS
 * 
 * 팀 관리 화면을 구현한다.
 * ID : TMCOOS60
 * 
 */

define([
	"Logger",
	"NDSProps",
	"WorkAreaRenderer2"
], function(Logger, NDSProps, WorkAreaRenderer2)
{
	var BusinessMethod = 
	{
		onSearchCode: function(options) 
		{
			
			
			var self = this;
			
			if (options.codeType == "TMCOOS10") {
				// 회사코드
				this.popupBox("codesearch",
				{
					"codeType": "TMCOOS10",
					"keyword": options.keyword,
					"params": options.params,
					"value": {
						"keyword": null,
						"result": null
					},
					"callback": function(result)
					{
						if( result )
						{
							self.$el.find("input.corpCode").val(result.code);
							self.$el.find("input.corpName").val(result.label);
							self.$el.find(".corpCodeSearch_region input.val_keyword").val(result.label);
							self.$el.find(".corpCodeSearch_region input.val_result").val(result.code);
							
							self.$el.find("input.headCode").val("");
							self.$el.find("input.headName").val("");
							self.$el.find(".headCodeSearch_region input.val_keyword").val("");
							self.$el.find(".headCodeSearch_region input.val_result").val("");
							
							self.$el.find("input.corpName").change();
						}
					}
				});
			} else if (options.codeType == "TMCOOS50") {
				
				var paramCorpCode = self.$el.find("input.corpCode").val();
				
				if (paramCorpCode == "") {
					UCMS.alert("회사를 선택하세요!");
					return;
				}
				
				// 본부코드
				this.popupBox("codesearch",
				{
					"codeType": "TMCOOS50",
					"keyword": options.keyword,
					"params": {
						"corpCode" : paramCorpCode
					},
					"value": {
						"keyword": null,
						"result": null
					},
					"callback": function(result)
					{
						if( result )
						{
							self.$el.find("input.headCode").val(result.code);
							self.$el.find("input.headName").val(result.label);
							self.$el.find(".headCodeSearch_region input.val_keyword").val(result.label);
							self.$el.find(".headCodeSearch_region input.val_result").val(result.code);
							
							self.$el.find("input.headName").change();
						}
					}
				});
			}
		}		
		,
		onSelectRow: function(selected)
		{
			var self = this;
			var formItem = this.getActiveForm();
			formItem.setData( selected.data, true );

			self.$el.find(".corpCodeSearch_region input.val_keyword").val(selected.data.corpName);
			self.$el.find(".corpCodeSearch_region input.val_result").val(selected.data.corpCode);
			
			self.$el.find(".headCodeSearch_region input.val_keyword").val(selected.data.headName);
			self.$el.find(".headCodeSearch_region input.val_result").val(selected.data.headCode);
			
			formItem.getItem().disabled(false);
		}
		,
		onSave: function()
		{
			var self = this;
			
			if (self.$el.find("input.corpCode").val() == "" || self.$el.find("input.headCode").val() == "" || self.$el.find("input.teamCode").val() == "" || self.$el.find("input.teamName").val() == ""){
				UCMS.alert("필수입력값을 입력하세요!");
			} else {
				self._hisNo = undefined;
				self.saveTransaction()
				.then(function(data)
				{
					self.onCancel(true);
					self.onQuery();
				});
			}
		}
	};
	
	var Renderer = WorkAreaRenderer2.extend
	(
		_.extend(BusinessMethod)
	);
	 
	return Renderer;
});
