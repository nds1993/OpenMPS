/**
 * Project : NDS MPS
 * 
 * 게시판 화면을 구현한다.
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
			
			self.$el.find(".formBox2_region").show();
			self.$el.find(".formBox_region").hide();

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
			
			self.$el.find("button.btn_update").click(function()
			{
				self.$el.find(".formBox2_region").hide();
				self.$el.find(".formBox_region").show();
			});
			
			self.$el.find("button.btn_cancel").click(function()
			{
				self.onCancel();
			});
			
			self.$el.find(".formBox_region input[name=periodYn]").click(function()
			{
				
				if(self.$el.find(".formBox_region input[name=periodYn]").is(":checked")) {				
					self.$el.find(".formBox_region input.beginDate").attr("disabled", false);
					self.$el.find(".formBox_region input.endDate").attr("disabled", false);
				} else {
					self.$el.find(".formBox_region input.beginDate").val("");
					self.$el.find(".formBox_region input.endDate").val("");
					self.$el.find(".formBox_region input.beginDate").attr("disabled", true);
					self.$el.find(".formBox_region input.endDate").attr("disabled", true);
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
				paramTitle = self.$el.find(".formBox_region input.title").val();
				paramUpcontId = self.$el.find(".formBox_region input.contId").val();
				paramContGrp = self.$el.find(".formBox_region input.contGrp").val();
				paramContLv = self.$el.find(".formBox_region input.contLv").val();
				paramSortOrder = self.$el.find(".formBox_region input.sortOrder").val();
				self.onCreate();
				
				self.$el.find(".formBox_region input.title").val("RE: "+paramTitle);
				self.$el.find(".formBox_region input.upcontId").val(paramUpcontId);
				self.$el.find(".formBox_region input.contGrp").val(paramContGrp);
				self.$el.find(".formBox_region input.contLv").val(paramContLv);
				self.$el.find(".formBox_region input.sortOrder").val(paramSortOrder);
				self.$el.find(".formBox_region input.contReyn").val("Y");
				

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
			var formItem2 = this.getActiveForm("formBox2");
			
			if( formItem2 )
			{
				formItem2.setData( selected.data, true );
			}
			
			var formItem = this.getActiveForm("formBox");
			
			if( formItem )
			{
				formItem.setData( selected.data, true );
				formItem.getItem().disabled(false);
				this.saveReadCnt();
				
				if(selected.data.secretYn == 'Y' && selected.data.crUser != NDSProps.get("user").id) {
					// 비밀글 본인 확인 여부
					UCMS.alert("비밀글은 작성자만 조회 가능합니다.");
				}
			}
			
			this.contentShow(selected.data);
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
					
					var form2 = self.getActiveForm("formBox2");
					if( form2 )
					{
						form2.clear();
					}
					
					self.clearForm("formBox");
					self.initParamData();
					self.hideLoading();
					self.$el.find(".formBox2_region").show();
					self.$el.find(".formBox_region").hide();
					
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
			self.$el.find("button.btn_update").hide();
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
			self.$el.find("button.btn_update").show();
			self.$el.find("button.btn_cancel").show();
			self.$el.find(".formBox2_region").hide();
			self.$el.find(".formBox_region").show();
		}
		,
		contentShow: function(paramSelectData) 
		{
			var self = this;
			self.allButtonHide();
			
			if(paramSelectData.crUser == NDSProps.get("user").id) {				
				self.$el.find("button.btn_save").show();
				self.$el.find("button.btn_update").show();
				self.$el.find("button.btn_cancel").show();
				self.$el.find("button.btn_delete").show();
			}
			self.$el.find("button.btn_save_reply").show();
			
			if(self.$el.find(".formBox_region input[name=periodYn]").is(":checked")) {				
				self.$el.find(".formBox_region input.beginDate").attr("disabled", false);
				self.$el.find(".formBox_region input.endDate").attr("disabled", false);
			} else {
				self.$el.find(".formBox_region input.beginDate").val("");
				self.$el.find(".formBox_region input.endDate").val("");
				self.$el.find(".formBox_region input.beginDate").attr("disabled", true);
				self.$el.find(".formBox_region input.endDate").attr("disabled", true);
			}
			
			self.$el.find(".formBox2_region").show();
			self.$el.find(".formBox_region").hide();
			
			// 파일 세팅
			self.setFile();
			
			// 코멘트 세팅
			self.setComment();
		}
		,
		saveTMCOBD20: function() 
		{
			
			var self = this;
			
			if( self.$el.find(".formBox_region input.title").val() == "") {
				UCMS.alert("제목을 입력하세요!");
				return;
			}
			
			if( self.$el.find(".formBox_region textarea.content").val() == "") {
				UCMS.alert("내용을 입력하세요!");
				return;
			}
		
			var apiPath = "";
			var apiMode = "";
			
			if(self.$el.find(".formBox_region input.contId").val()){				
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
		saveReadCnt: function() 
		{
			
			var apiPath = "";
			
			apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD40/saveReadCnt";
			
			this.submitTMCOBD20(apiPath, "saveReadCnt");
			
		}
		,
		submitTMCOBD20: function(apiPath, mode) 
		{
			
			var self = this;
		
			if(mode != "saveReadCnt") {
				self.showLoading();
			}

			$.ajax(
			{
				url:  apiPath,
				data: self.getParamData(), 
				processData: false,
				contentType: false, 
				type: 'POST',
				success: function (data)
                {
					if(mode != "saveReadCnt") {
						self.onQuery();
					}
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
		initParamData: function() {
			
			var self = this;
			
			self.$el.find(".formBox_region input.contId").val("");
			self.$el.find(".formBox_region input.contGrp").val("");
			self.$el.find(".formBox_region input.contLv").val("");
			self.$el.find(".formBox_region input.sortOrder").val("");
			self.$el.find(".formBox_region input.upcontId").val("");
			self.$el.find(".formBox_region input.contReyn").val("");
			self.$el.find(".formBox_region input.fileId").val("");
			
			self.$el.find(".formBox_region input.title").val("");
			self.$el.find(".formBox_region textarea.content").val("");
			
			self.$el.find(".formBox_region input[name=notiYn]").prop("checked",false);
			self.$el.find(".formBox_region input[name=secretYn]").prop("checked",false);
			self.$el.find(".formBox_region input[name=periodYn]").prop("checked",false);

			self.$el.find(".formBox_region input.beginDate").val("");
			self.$el.find(".formBox_region input.endDate").val("");
			
			self.$el.find(".formBox_region input.beginDate").attr("disabled", true);
			self.$el.find(".formBox_region input.endDate").attr("disabled", true);			
			
			self.$el.find(".formBox3_region .div_comt_save_area input.comment").val("");
			
			self.$el.find(".formBox_region input[name=file]").val("");
			
		}
		,
		getParamData: function() {
			
			var self = this;
			var formData = new FormData();
			
			formData.append("contId", self.$el.find(".formBox_region input.contId").val());
			formData.append("contGrp", self.$el.find(".formBox_region input.contGrp").val());
			formData.append("contLv", self.$el.find(".formBox_region input.contLv").val());
			formData.append("sortOrder", self.$el.find(".formBox_region input.sortOrder").val());
			formData.append("upcontId", self.$el.find(".formBox_region input.upcontId").val());
			formData.append("contReyn", self.$el.find(".formBox_region input.contReyn").val());
			formData.append("fileId", self.$el.find(".formBox_region input.fileId").val());
			formData.append("title", self.$el.find(".formBox_region input.title").val());
			formData.append("content", self.$el.find(".formBox_region textarea.content").val());
			
			if(self.$el.find(".formBox_region input[name=notiYn]").is(":checked")) {				
				formData.append("notiYn", self.$el.find(".formBox_region input[name=notiYn]:checked").val());
			} else {
				formData.append("notiYn", "N");
			}
			
			if(self.$el.find(".formBox_region input[name=secretYn]").is(":checked")) {				
				formData.append("secretYn", self.$el.find(".formBox_region input[name=secretYn]:checked").val());
			} else {
				formData.append("secretYn", "N");
			}
			
			if(self.$el.find(".formBox_region input[name=periodYn]").is(":checked")) {				
				formData.append("periodYn", self.$el.find(".formBox_region input[name=periodYn]:checked").val());
			} else {
				formData.append("periodYn", "N");
			}
			
			formData.append("beginDate", self.$el.find(".formBox_region input.beginDate").val());
			formData.append("endDate", self.$el.find(".formBox_region input.endDate").val());
			
			formData.append("bbsCode", self._contentId);
			
			if(self.$el.find(".formBox_region input[name=file]")[0].files[0] != undefined) {			
				formData.append("file", self.$el.find(".formBox_region input[name=file]")[0].files[0]);
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
							
							if(result[i].crUser == NDSProps.get("user").id) {								
								commentRow += "		<button class=\"btn btn-primary  btn_comt_update_save\" style=\"display:none;\">";
								commentRow += "			<span class=\"btn_label \">저장</span>";
								commentRow += "		</button>";
								commentRow += "		<button class=\"btn btn-primary  btn_comt_update\">";
								commentRow += "			<span class=\"btn_label \">수정</span>";
								commentRow += "		</button>";
								commentRow += "		<button class=\"btn btn-primary  btn_comt_delete\">";
								commentRow += "			<span class=\"btn_label \">삭제</span>";
								commentRow += "		</button>";
							}
							
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
			self.$el.find(".div-file-list-2").html("");
			
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
						var commentRow2 = "";

						for(i=0; i < result.length; i++) {							
							commentRow += "<button class=\"btn btn-primary  btn_file_down\" fileNo=\"" + result[i].fileNo + "\" style=\"border:0px;\"><span class=\"btn_label\">" + result[i].originFlnm + "</span></button>";
							commentRow += "<button class=\"btn btn-primary  btn_file_delete\" style=\"margin-right: 20px;\" fileNo=\"" + result[i].fileNo + "\"><span class=\"btn_label\">파일삭제</span></button>";
							
							commentRow2 += "<button class=\"btn btn-primary  btn_file_down\" fileNo=\"" + result[i].fileNo + "\" style=\"border:0px;\"><span class=\"btn_label\">" + result[i].originFlnm + "</span></button>";
						}
						
						self.$el.find(".div-file-list").html(commentRow);
						self.$el.find(".div-file-list-2").html(commentRow2);
						
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
