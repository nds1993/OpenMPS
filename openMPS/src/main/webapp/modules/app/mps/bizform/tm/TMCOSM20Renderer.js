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
			
			self.$el.find("button.btn_approval").click(function()
			{
				
				var servId = self.$el.find("input.servId").val();
				
				if (servId == "") {
					UCMS.alert("요청서를 선택하세요!");
					return;
				}
		
				UCMS.confirm("승인 하시겠습니까?", { confirm: "확인", cancel: "취소" })
				.done(function()
				{
					self.approvalTMCOSM20();
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
			
			// 파일 세팅
			this.setFile();
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
					self.setFile();
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
			self.$el.find("button.btn_reject").hide();
			self.$el.find("button.btn_approval").hide();
			self.$el.find("div.proc_result_box").hide();
		}
		,
		contentShow: function() 
		{
			var self = this;
			self.allButtonHide();
			
			var procStatus = self.$el.find("input.procStatus").val();
			
			if (procStatus == "APPROVAL") {				
				self.$el.find("button.btn_approval").show();
				self.$el.find("button.btn_reject").show();
			}
			
			if (procStatus == "PROCESS" || procStatus == "FINISH" || procStatus == "RETURN") {
				self.$el.find("div.proc_result_box").show();
			} else {
				self.$el.find("div.proc_result_box").hide();
			}
			
		}
		,
		approvalTMCOSM20: function() 
		{
			
			var self = this;
			
			var apiPath = "";
			
			apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOSM20/approvalTMCOSM20";
			
			self.submitTMCOSM20(apiPath, "approval");
		}
		,
		submitTMCOSM20: function(apiPath, mode) 
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
						UCMS.alert("승인되었습니다.");
					} else if(mode == "reject") {
						UCMS.alert("반려되었습니다.");
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
			
			self.$el.find("input.fileId").val("");
			
		}
		,getParamData: function() {
			
			var self = this;
			var formData = new FormData();
			
			formData.append("servId", self.$el.find("input.servId").val());
			formData.append("servTitle", self.$el.find("input.servTitle").val());
			formData.append("rqstType", self.$el.find("div.rqstType select").val());
			formData.append("servCont", self.$el.find("textarea.servCont").val());
			formData.append("rqfnDate", self.$el.find("input.rqfnDate").val());
			
			formData.append("fileId", self.$el.find("input.fileId").val());
			
			return formData;
		}
		,
		setFile: function() 
		{
			
			var self = this;
			var formData = new FormData();
			
			var paramFileId = self.$el.find(".formBox_region input.fileId").val();
			formData.append("fileId", paramFileId);
			
			var apiPath = "";
			apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD20/selectFileList";
			
			// 코멘트 영역 초기화
			self.$el.find(".div-file-list").html("");
			
			if(paramFileId != "") {
				
				$.ajax(
				{
					url:  apiPath,
					data: formData, 
					processData: false,
					contentType: false, 
					type: 'POST',
					success: function (data)
	                {
						//self.onQuery();
						var result = data.extraData.result;
						var commentRow = "";

						for(i=0; i < result.length; i++) {							
							commentRow += "<button class=\"btn btn-primary  btn_file_down\" fileNo=\"" + result[i].fileNo + "\" style=\"border:0px;\"><span class=\"btn_label\">" + result[i].originFlnm + "</span></button>";
						}
						
						self.$el.find(".div-file-list").html(commentRow);
						
						// 삭제 기능 수행
						self.$el.find("button.btn_file_delete").click(function()
						{
							self.deleteFile($(this).attr("fileNo"));
						});
						
						// 다운 기능 수행
						self.$el.find("button.btn_file_down").click(function()
						{
							self.downloadFile($(this).attr("fileNo"));
						});
	                },
	                error: function(XHR, textStatus, errorThrown) 
	                {
	                	UCMS.alert("저장중 오류가 발생하였습니다.");
	                	Logger.error("fetchMyApp() - Error : "+textStatus);
	                	self.hideLoading();
	                }
	            });
				
			}
		}
		,
		downloadFile: function(fileNo) 
		{
			var self = this;
			var formData = new FormData();
			var apiPath = "";
			
			apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD20/downloadFile";
			
			formData.append("fileNo", fileNo);
			
			window.open( apiPath + "?fileNo=" + fileNo , "_download_" );
		}
	};
	
	var Renderer = WorkAreaRenderer2.extend
	(
		_.extend(OverridingMethod, WorkAreaMethod)
	);
	 
	return Renderer;
});
