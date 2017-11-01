/**
 * Project : NDS MPS
 * 
 * FAQ 관리 화면을 구현한다.
 * ID : TMCOBD30
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
		 * 그리드 데이타를 조회한다.
		 * @param {string} featureId - 조회 기능 식별자
		 */
		onQuery: function(featureId, options)
		{
			var self = this;
			
			var fetching = function()
			{
				self.showLoading();
				return self.fetchingGrid(featureId, options)
				.always(function(e)
				{
					self.clearForm();
					self.initParamData();
					self.hideLoading();					
					self.setFile();
				});
			};
			
			self.setReadyMode();
			
			return fetching();
		}
		,
		onCreate: function()
		{
			
			if( this.getActiveForm() )
			{
				this.clearForm();
				this.getActiveForm().getItem().disabled(false);
			}
		}
		,
		onSave: function()
		{
			this.saveTMCOBD30();
		}
		,
		onDelete: function()
		{
			this.deleteTMCOBD30();
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
			var formItem = this.attachFormItem("formBox");
			formItem.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				self.onChangeFormData( gridItem, formItem );
			});
			formItem.getItem().disabled(true );
			
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
			
			var formItem = this.getActiveForm("formBox");
			
			if( formItem )
			{
				formItem.setData( selected.data, true );
				formItem.getItem().disabled(false);
			}
			
			// 파일 세팅
			this.setFile();
			
		}
	}
	
	var WorkAreaMethod = 
	{
		saveTMCOBD30: function() 
		{
			
			var self = this;
			
			if( self.$el.find("input.faqTitle").val() == "") {
				UCMS.alert("제목을 입력하세요!");
				return;
			}
			
			if( self.$el.find("textarea.question").val() == "") {
				UCMS.alert("질문을 입력하세요!");
				return;
			}
			
			if( self.$el.find("textarea.answer").val() == "") {
				UCMS.alert("답변을 입력하세요!");
				return;
			}
		
			var apiPath = "";
			var apiMode = "";
			
			if(self.$el.find("input.faqId").val()){				
				// 수정
				apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD30/updateTMCOBD30";
			} else {
				//신규 저장
				apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD30/saveTMCOBD30";				
			}
			
			self.submitTMCOBD30(apiPath, apiMode);
		}
		,
		deleteTMCOBD30: function() 
		{
			
			var self = this;
			
			if(self.$el.find("input.faqId").val() == ""){				
				UCMS.alert("삭제할 FAQ를 선택하세요.");
				return
			}
			
			UCMS.confirm("삭제 하시겠습니까?", { confirm: "확인", cancel: "취소" })
			.done(function()
			{
				var apiPath = "";
				
				apiPath = "/rest/tmm/" + NDSProps.get("corpCode") + "/TMCOBD30/deleteTMCOBD30";
				
				self.submitTMCOBD30(apiPath, "");
			});
		
		}
		,
		submitTMCOBD30: function(apiPath, mode) 
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
			
			self.$el.find("input.faqId").val("");
			self.$el.find("input.faqTitle").val("");
			self.$el.find("input.sortOrder").val("");
			self.$el.find("textarea.question").val("");
			self.$el.find("textarea.answer").val("");
			self.$el.find("input.rqfnDate").val("");
			self.$el.find("input.fileId").val("");
			self.$el.find("input[name=file]").val("");
			
		}
		,getParamData: function() {
			
			var self = this;
			var formData = new FormData();
			
			formData.append("faqId", self.$el.find("input.faqId").val());
			formData.append("faqTitle", self.$el.find("input.faqTitle").val());
			formData.append("sortOrder", self.$el.find("input.sortOrder").val());
			formData.append("question", self.$el.find("textarea.question").val());
			formData.append("answer", self.$el.find("textarea.answer").val());
			formData.append("rqfnDate", self.$el.find("input.rqfnDate").val());
			formData.append("fileId", self.$el.find("input.fileId").val());
			
			if(self.$el.find("input[name=file]")[0].files[0] != undefined) {					
				formData.append("file", self.$el.find("input[name=file]")[0].files[0]);
			}
			
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
