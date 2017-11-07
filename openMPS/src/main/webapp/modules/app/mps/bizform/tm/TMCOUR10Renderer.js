/**
 * Project : NDS MPS
 * 
 * 사용자 관리 화면을 구현한다.
 * ID : TMCOUR10
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
		 * 5. 랜더러 아이템 관계성 초기화
		 */
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachFormItem("queryBox");
			
			//
			var formItem = this.attachFormItem("formBox");
			formItem.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				// 신규 사용자 비밀번호 초기화
				if (self.$el.find("input.birth").val() != "") {
					var birth = self.$el.find("input.birth").val();
					
					birth = birth.replace(/-/gi, "");
					
					self.$el.find("input.userPass").val(Sha256.hash(birth));
				}
				
				// 주소 정보 설정
				if (self.$el.find(".address_region input.zip_no").val() != "") {
					
					self.$el.find("input.zipCode").val(self.$el.find(".address_region input.zip_no").val());
					self.$el.find("input.addrCode1").val(self.$el.find(".address_region input.address").val());
					self.$el.find("input.addrCode2").val(self.$el.find(".address_region input.address_detail").val());
					
				}
				
				self.onChangeFormData( gridItem, formItem );
			});
			formItem.getItem().disabled(true );
			
			//
			var gridItem = this.attachGridItem("resultBox");
			gridItem.getItem().on(JQGrid.EVENT.SELECT, function(item)
			{
				self.onSelectRow( item );
			});
			
			setTimeout(function()
			{
				self.$el.find(".top_button_region").prepend ("<button class=\"btn btn-primary pass-reset\" style=\"float: left; margin-left: 0px;\"><span class=\"btn_label \">비밀번호 초기화</span></button><button class=\"btn btn-primary pass-change\" style=\"float: left;\"><span class=\"btn_label \">비밀번호 변경</span></button>");
				
				self.$el.find("button.pass-change").click(function()
				{
					
					var userCode = self.$el.find("input.userCode").val();
					
					if (userCode == "") {
						UCMS.alert("사용자를 선택하세요!");
						return;
					}
					
					self.popupBox("TMCOUR10_pop",
					{
						"params": {
							// 사용자 ID
							changeUserCode: userCode
						},
						"callback": function(result)
						{
							Logger.debug(result);
						}
					});
				});
				
				self.$el.find("button.pass-reset").click(function()
				{
					
					var userCode = self.$el.find("input.userCode").val();
					var userPass = self.$el.find("input.birth").val();

					if (userCode == "") {
						UCMS.alert("사용자를 선택하세요!");
						return;
					}
					
					if (userPass == "") {
						UCMS.alert("생년월일을 선택하세요!");
						return;
					}
					
					userPass = userPass.replace(/-/gi, "");
					
					UCMS.confirm("비밀번호를 초기화 하시겠습니까?", { confirm: "확인", cancel: "취소" })
					.done(function()
					{
						
						var apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/tmcour10/updateUserPass?";
					
						userPass = Sha256.hash(userPass);
						
						apiPath += "userCode=" + userCode;
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
								UCMS.alert("비밀번호가 초기화 되었습니다.");				                	
			                },
			                error: function(XHR, textStatus, errorThrown) 
			                {
			                	UCMS.alert("사용자 비밀번호가 초기화중 오류가 발생하였습니다.");
			                	Logger.error("fetchMyApp() - Error : "+textStatus);
			                }
			            });
						
					});
					
				});
				
				//사용자 ID 중복 조회 기능 추가
				self.$el.find("button.btn_user_check").click(function()
				{
					var checkUserCode = self.$el.find("input.userCode").val();
					
					if (checkUserCode == "") {
						UCMS.alert("사용자 ID를 입력하세요!");
						return;
					}
					
					var apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/tmcour10?";
					
					apiPath += "checkUserCode=" + checkUserCode;
					
					$.ajax(
					{
		                type: 'GET',
		                url:  apiPath,
		                cache: false,
		                crossDomain: true,
		                contentType: 'application/json; charset=utf-8',
		                dataType: "json"
						,
						success: function (data)
		                {
							if (data.extraData.result.length > 0) {
		                		UCMS.alert("이미 등록된 사용자 ID 입니다.");
		                	} else {
		                		UCMS.alert("사용 가능한 사용자 ID 입니다.");
		                	}
		                	
		                },
		                error: function(XHR, textStatus, errorThrown) 
		                {
		                	Logger.error("fetchMyApp() - Error : "+textStatus);
		                }
		            });				
					
				});
				
				//
				self.onQuery();
			}
			, 1500);
			
		}
	
	}
	
	var WorkAreaMethod = 
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
							
							self.$el.find("input.teamCode").val("");
							self.$el.find("input.teamName").val("");
							self.$el.find(".teamCodeSearch_region input.val_keyword").val("");
							self.$el.find(".teamCodeSearch_region input.val_result").val("");
							
							self.$el.find("input.deptCode").val("");
							self.$el.find("input.deptName").val("");
							self.$el.find(".deptCodeSearch_region input.val_keyword").val("");
							self.$el.find(".deptCodeSearch_region input.val_result").val("");
							
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
							
							self.$el.find("input.teamCode").val("");
							self.$el.find("input.teamName").val("");
							self.$el.find(".teamCodeSearch_region input.val_keyword").val("");
							self.$el.find(".teamCodeSearch_region input.val_result").val("");
							
							self.$el.find("input.deptCode").val("");
							self.$el.find("input.deptName").val("");
							self.$el.find(".deptCodeSearch_region input.val_keyword").val("");
							self.$el.find(".deptCodeSearch_region input.val_result").val("");
							
							self.$el.find("input.headName").change();
						}
					}
				});
			} else if (options.codeType == "TMCOOS60") {
				
				var paramCorpCode = self.$el.find("input.corpCode").val();
				var paramHeadCode = self.$el.find("input.headCode").val();
				
				if (paramCorpCode == "") {
					UCMS.alert("회사를 선택하세요!");
					return;
				}
				
				if (paramHeadCode == "") {
					UCMS.alert("본부를 선택하세요!");
					return;
				}
				
				// 본부코드
				this.popupBox("codesearch",
				{
					"codeType": "TMCOOS60",
					"keyword": options.keyword,
					"params": {
						"corpCode" : paramCorpCode,
						"headCode" : paramHeadCode
					},
					"value": {
						"keyword": null,
						"result": null
					},
					"callback": function(result)
					{
						if( result )
						{
							self.$el.find("input.teamCode").val(result.code);
							self.$el.find("input.teamName").val(result.label);
							self.$el.find(".teamCodeSearch_region input.val_keyword").val(result.label);
							self.$el.find(".teamCodeSearch_region input.val_result").val(result.code);
							
							self.$el.find("input.deptCode").val("");
							self.$el.find("input.deptName").val("");
							self.$el.find(".deptCodeSearch_region input.val_keyword").val("");
							self.$el.find(".deptCodeSearch_region input.val_result").val("");
							
							self.$el.find("input.teamName").change();
						}
					}
				});
			} else if (options.codeType == "TMCOOS70") {
				
				var paramCorpCode = self.$el.find("input.corpCode").val();
				var paramHeadCode = self.$el.find("input.headCode").val();
				var paramTeamCode = self.$el.find("input.teamCode").val();
				
				if (paramCorpCode == "") {
					UCMS.alert("회사를 선택하세요!");
					return;
				}
				
				if (paramHeadCode == "") {
					UCMS.alert("본부를 선택하세요!");
					return;
				}
				
				if (paramTeamCode == "") {
					UCMS.alert("팀을 선택하세요!");
					return;
				}
				
				// 본부코드
				this.popupBox("codesearch",
				{
					"codeType": "TMCOOS70",
					"keyword": options.keyword,
					"params": {
						"corpCode" : paramCorpCode,
						"headCode" : paramHeadCode,
						"teamCode" : paramTeamCode
					},
					"value": {
						"keyword": null,
						"result": null
					},
					"callback": function(result)
					{
						if( result )
						{
							self.$el.find("input.deptCode").val(result.code);
							self.$el.find("input.deptName").val(result.label);
							self.$el.find(".deptCodeSearch_region input.val_keyword").val(result.label);
							self.$el.find(".deptCodeSearch_region input.val_result").val(result.code);
							
							self.$el.find("input.deptName").change();
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
			
			self.$el.find(".teamCodeSearch_region input.val_keyword").val(selected.data.teamName);
			self.$el.find(".teamCodeSearch_region input.val_result").val(selected.data.teamCode);
			
			self.$el.find(".deptCodeSearch_region input.val_keyword").val(selected.data.deptName);
			self.$el.find(".deptCodeSearch_region input.val_result").val(selected.data.deptCode);
			
			self.$el.find(".address_region input.zip_no").val(selected.data.zipCode);
			self.$el.find(".address_region input.address").val(selected.data.addrCode1);
			self.$el.find(".address_region input.address_detail").val(selected.data.addrCode2);
			
			formItem.getItem().disabled(false);
		}
	};
	
	var Renderer = WorkAreaRenderer2.extend
	(
		_.extend(OverridingMethod, WorkAreaMethod)
	);
	 
	return Renderer;
});
