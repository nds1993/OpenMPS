/**
 * Project : NDS MPS
 * 
 * 게시물 관리 화면을 구현한다.
 * ID : TMCOBD20
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
				self.saveTMCOBD20();
			});
			
			self.$el.find("button.btn_cancel").click(function()
			{
				self.onCancel();
			});
			
			self.$el.find("input[name=periodYn]").click(function()
			{
				
				if(self.$el.find("input[name=periodYn]").is(":checked")) {				
					self.$el.find("input.beginDate").attr("disabled", false);
					self.$el.find("input.endDate").attr("disabled", false);
				} else {
					self.$el.find("input.beginDate").val("");
					self.$el.find("input.endDate").val("");
					self.$el.find("input.beginDate").attr("disabled", true);
					self.$el.find("input.endDate").attr("disabled", true);
				}
				
			});
			
			self.$el.find("button.btn_delete").click(function()
			{
				
				UCMS.confirm("삭제 하시겠습니까?", { confirm: "확인", cancel: "취소" })
				.done(function()
				{
					self.deleteTMCOBD20();
				});
				
			});
			
			self.$el.find("button.btn_save_reply").click(function()
			{
				var paramUpcontId = "";
				paramBbsCode = "";
				paramTitle = self.$el.find("input.title").val();
				paramUpcontId = self.$el.find("input.contId").val();
				paramContGrp = self.$el.find("input.contGrp").val();
				paramContLv = self.$el.find("input.contLv").val();
				paramSortOrder = self.$el.find("input.sortOrder").val();
				paramBbsCode = self.$el.find("div.bbsCode select").val();
				self.onCreate();
				
				self.$el.find("input.title").val("RE: "+paramTitle);
				self.$el.find("input.upcontId").val(paramUpcontId);
				self.$el.find("input.contGrp").val(paramContGrp);
				self.$el.find("input.contLv").val(paramContLv);
				self.$el.find("input.sortOrder").val(paramSortOrder);
				self.$el.find("input.contReyn").val("Y");
				self.$el.find("div.bbsCode select").val(paramBbsCode).prop("selected", true);
				self.$el.find("div.bbsCode select").change();
			});
			
			self.$el.find("label.label_comt_curr_user").html(NDSProps.get("user").name);
			
			self.$el.find("button.btn_comt_save").click(function()
			{
				self.saveComment();				
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
			/*
			var formItem2 = this.getActiveForm("formBox2");
			
			if( formItem2 )
			{
				formItem2.setData( selected.data, true );
				formItem2.getItem().disabled(false);
			}
			*/
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
					self.initParamData();
					self.hideLoading();
					
					self.setFile();
					self.setComment();
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
			self.$el.find("button.btn_cancel").hide();
			self.$el.find("button.btn_delete").hide();
			self.$el.find("button.btn_save_reply").hide();
			self.$el.find("div.proc_result_box").hide();
		}
		,
		createShow: function() 
		{
			var self = this;
			self.allButtonHide();
			self.initParamData();
			self.$el.find("button.btn_save").show();
			self.$el.find("button.btn_cancel").show();
		}
		,
		contentShow: function() 
		{
			var self = this;
			self.allButtonHide();
			
			self.$el.find("button.btn_save").show();
			self.$el.find("button.btn_cancel").show();
			self.$el.find("button.btn_delete").show();
			self.$el.find("button.btn_save_reply").show();
			
			if(self.$el.find("input[name=periodYn]").is(":checked")) {				
				self.$el.find("input.beginDate").attr("disabled", false);
				self.$el.find("input.endDate").attr("disabled", false);
			} else {
				self.$el.find("input.beginDate").val("");
				self.$el.find("input.endDate").val("");
				self.$el.find("input.beginDate").attr("disabled", true);
				self.$el.find("input.endDate").attr("disabled", true);
			}
			
			// 파일 세팅
			self.setFile();
			
			// 코멘트 세팅
			self.setComment();
		}
		,
		saveTMCOBD20: function() 
		{
			
			var self = this;
			
			if( self.$el.find("input.title").val() == "") {
				UCMS.alert("제목을 입력하세요!");
				return;
			}
			
			if( self.$el.find("div.bbsCode select").val() == "") {
				UCMS.alert("요청유형을 선택하세요!");
				return;
			}
			
			if( self.$el.find("textarea.content").val() == "") {
				UCMS.alert("내용을 입력하세요!");
				return;
			}
		
			var apiPath = "";
			var apiMode = "";
			
			if(self.$el.find("input.contId").val()){				
				// 수정
				apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD20/updateTMCOBD20";
			} else {
				//신규 저장
				apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD20/saveTMCOBD20";				
			}
			
			self.submitTMCOBD20(apiPath, apiMode);
		}
		,
		deleteTMCOBD20: function() 
		{
			
			var self = this;
		
			var apiPath = "";
			
			apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD20/deleteTMCOBD20";
			
			self.submitTMCOBD20(apiPath, "");
		}
		,
		submitTMCOBD20: function(apiPath, mode) 
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
			
			self.$el.find("input.contId").val("");
			self.$el.find("input.contGrp").val("");
			self.$el.find("input.contLv").val("");
			self.$el.find("input.sortOrder").val("");
			self.$el.find("input.upcontId").val("");
			self.$el.find("input.contReyn").val("");
			self.$el.find("input.fileId").val("");
			self.$el.find("input.title").val("");
			self.$el.find("textarea.content").val("");
			
			self.$el.find("input[name=notiYn]").prop("checked",false);
			self.$el.find("input[name=secretYn]").prop("checked",false);
			self.$el.find("input[name=periodYn]").prop("checked",false);

			self.$el.find("input.beginDate").val("");
			self.$el.find("input.endDate").val("");
			
			self.$el.find("input.beginDate").attr("disabled", true);
			self.$el.find("input.endDate").attr("disabled", true);
			
			self.$el.find("div.bbsCode select option:eq(0)").prop("selected", true);
			self.$el.find("div.bbsCode select").change();
			
			self.$el.find(".formBox3_region .div_comt_save_area input.comment").val("");
			
			self.$el.find("input[name=file]").val("");
			
		}
		,getParamData: function() {
			
			var self = this;
			var formData = new FormData();
			
			formData.append("contId", self.$el.find("input.contId").val());
			formData.append("contGrp", self.$el.find("input.contGrp").val());
			formData.append("contLv", self.$el.find("input.contLv").val());
			formData.append("sortOrder", self.$el.find("input.sortOrder").val());
			formData.append("upcontId", self.$el.find("input.upcontId").val());
			formData.append("contReyn", self.$el.find("input.contReyn").val());
			formData.append("fileId", self.$el.find("input.fileId").val());
			formData.append("title", self.$el.find("input.title").val());
			formData.append("content", self.$el.find("textarea.content").val());
			
			if(self.$el.find("input[name=notiYn]").is(":checked")) {				
				formData.append("notiYn", self.$el.find("input[name=notiYn]:checked").val());
			} else {
				formData.append("notiYn", "N");
			}
			
			if(self.$el.find("input[name=secretYn]").is(":checked")) {				
				formData.append("secretYn", self.$el.find("input[name=secretYn]:checked").val());
			} else {
				formData.append("secretYn", "N");
			}
			
			if(self.$el.find("input[name=periodYn]").is(":checked")) {				
				formData.append("periodYn", self.$el.find("input[name=periodYn]:checked").val());
			} else {
				formData.append("periodYn", "N");
			}
			
			formData.append("beginDate", self.$el.find("input.beginDate").val());
			formData.append("endDate", self.$el.find("input.endDate").val());
			
			formData.append("bbsCode", self.$el.find("div.bbsCode select").val());
			
			if(self.$el.find("input[name=file]")[0].files[0] != undefined) {					
				formData.append("file", self.$el.find("input[name=file]")[0].files[0]);
			}
			
			return formData;
		}
		,
		saveComment: function() 
		{
			
			var self = this;
			
			if( self.$el.find(".formBox_region input.contId").val() == "" ) {
				UCMS.alert("게시물을 선택하세요!");
				return;
			}
		
			if( self.$el.find(".formBox3_region .div_comt_save_area input.comment").val() == "" ) {
				UCMS.alert("댓글을 입력하세요!");
				return;
			}
			
			var apiPath = "";
			var apiMode = "comment";
			apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD40/saveComment";
			
			self.submitComment(apiPath, apiMode);
			
		}
		,
		getParamDataComment: function() {
			
			var self = this;
			var formData = new FormData();
			
			formData.append("contId", self.$el.find(".formBox_region input.contId").val());
			formData.append("bbsCode", self._contentId);
			formData.append("comment", self.$el.find(".formBox3_region .div_comt_save_area input.comment").val());
			
			return formData;
		}
		,
		submitComment: function(apiPath, mode) 
		{
			
			var self = this;
		
			$.ajax(
			{
				url:  apiPath,
				data: self.getParamDataComment(), 
				processData: false,
				contentType: false, 
				type: 'POST',
				success: function (data)
                {
					self.setComment();
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	UCMS.alert("저장중 오류가 발생하였습니다.");
                	Logger.error("fetchMyApp() - Error : "+textStatus);
                	self.hideLoading();
                }
            });
		}
		,
		setComment: function() 
		{
			
			var self = this;
			var formData = new FormData();
			
			var paramContId = self.$el.find(".formBox_region input.contId").val();
			var paramCrUser = self.$el.find(".formBox_region input.crUser").val();
			formData.append("contId", paramContId);
			
			var apiPath = "";
			apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD40/selectComment";
			
			// 코멘트 영역 초기화
			self.$el.find(".formBox3_region .div_comt_area").html("");

			// 덧글 초기화
			self.$el.find(".formBox3_region .div_comt_save_area input.comment").val("");
			
			if(paramContId != "") {
				
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
						var commentRow;

						for(i=0; i < result.length; i++) {
							commentRow = "";
							commentRow += "<div class=\"row\">";
							commentRow += "	<div class=\"col-xs-8\">";
							commentRow += "		<div class=\"form-group\">";
							commentRow += "			<label class=\"label_comt_user\">" + result[i].crName + "</label>";
							commentRow += "			<input type=\"text\" class=\"form-control comtCode\" value=\"" + result[i].comtCode + "\" style=\"display: none;\">";
							commentRow += "			<input type=\"text\" class=\"form-control comment\" value=\"" + result[i].comment + "\" placeholder=\"\" disabled=\"disabled\">";
							commentRow += "		</div>";
							commentRow += "	</div>";
							commentRow += "	<div class=\"col-xs-4\">";
							commentRow += "		<label class=\"label_comt_date\">" + result[i].crDate + "</label>";
							
							commentRow += "		<button class=\"btn btn-primary  btn_comt_update_save\" style=\"display:none;\">";
							commentRow += "			<span class=\"btn_label \">저장</span>";
							commentRow += "		</button>";
							commentRow += "		<button class=\"btn btn-primary  btn_comt_update\">";
							commentRow += "			<span class=\"btn_label \">수정</span>";
							commentRow += "		</button>";
							commentRow += "		<button class=\"btn btn-primary  btn_comt_delete\">";
							commentRow += "			<span class=\"btn_label \">삭제</span>";
							commentRow += "		</button>";
							
							commentRow += "	</div>";
							commentRow += "</div>";
							
							self.$el.find(".formBox3_region .div_comt_area").append(commentRow);
						}
						
						// 삭제 기능 수행
						self.$el.find("button.btn_comt_delete").click(function()
						{
							self.updateComment($(this).parent().parent().find("input.comtCode").val(), "", "delete");
						});
						
						// 수정 기능 수행
						self.$el.find("button.btn_comt_update").click(function()
						{
							$(this).parent().find("button.btn_comt_update_save").show();
							$(this).parent().parent().find("input.comment").prop("disabled", false);
							$(this).parent().find("button.btn_comt_update").hide();
							$(this).parent().find("button.btn_comt_delete").hide();
						});
						
						// 저장 기능 수행
						self.$el.find("button.btn_comt_update_save").click(function()
						{
							self.updateComment($(this).parent().parent().find("input.comtCode").val(), $(this).parent().parent().find("input.comment").val(), "update");
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
		updateComment: function(comtCode, comment, mode) 
		{
			var self = this;
			var formData = new FormData();
			var apiPath = "";
			
			if (mode == "delete") {
				apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD40/deleteComment";
			} else {
				apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD40/updateComment";
			}
			
			formData.append("comtCode", comtCode);
			formData.append("comment", comment);
			
			$.ajax(
			{
				url:  apiPath,
				data: formData, 
				processData: false,
				contentType: false, 
				type: 'POST',
				success: function (data)
                {
					self.setComment();
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	UCMS.alert("저장중 오류가 발생하였습니다.");
                	Logger.error("fetchMyApp() - Error : "+textStatus);
                	self.hideLoading();
                }
            });
			
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
							commentRow += "<button class=\"btn btn-primary  btn_file_delete\" style=\"margin-right: 20px;\" fileNo=\"" + result[i].fileNo + "\"><span class=\"btn_label\">파일삭제</span></button>";
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
		,
		deleteFile: function(fileNo) 
		{
			var self = this;
			var formData = new FormData();
			var apiPath = "";
			
			apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD20/deleteFile";
			
			formData.append("fileNo", fileNo);
			
			$.ajax(
			{
				url:  apiPath,
				data: formData, 
				processData: false,
				contentType: false, 
				type: 'POST',
				success: function (data)
                {
					self.setFile();
                },
                error: function(XHR, textStatus, errorThrown) 
                {
                	UCMS.alert("저장중 오류가 발생하였습니다.");
                	Logger.error("fetchMyApp() - Error : "+textStatus);
                	self.hideLoading();
                }
            });
			
		}
	};
	
	var Renderer = WorkAreaRenderer2.extend
	(
		_.extend(OverridingMethod, WorkAreaMethod)
	);
	 
	return Renderer;
});
