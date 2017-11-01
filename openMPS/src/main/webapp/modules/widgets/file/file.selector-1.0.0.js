/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
(
[
	"BaroPanelBase", "BaroProps", "Logger"
]
,
function( BaroPanelBase, BaroProps, Logger)
{
	var	FileSelectorPanel = BaroPanelBase.extend(
	{
		template: "#fileselector_panel"
		,
		regions:
		{
			sessionPanel: ".ui_session_body"
		}			
		,
		ui:
	    {
			btn_go_send_me : ".btn_go_send_me"
	    }
		,
		events:
		{
			"change .btn_web_get_image" : "onGetWebImage",
			"click @ui.btn_go_send_me": "onConfirm"
		}
		,
		model: new Backbone.Model()
		,
		initialize: function(options)
		{
			BaroPanelBase.prototype.initialize.call(this, options);
        }
		,
        onRender: function()
        {
        }
		,
		onShow: function()
		{
		}
		,
		onGetWebImage : function(){
			
			var self = this;	
			
			var session = BaroProps.getSessionParams();
			
			var user_id = BaroProps.getUser().id;

        	var sp_n = session.SP_N;
        	var sp_k = session.SP_K;
        	
			var apiPath = BaroProps.getHosts().api + "/file/web/temp/file/create/" + user_id + "?sp-name=" + sp_n + "&sp-key=" + sp_k;
			
			var formId = "web_file_form";
			
			var formObj = $("#" + formId);
			
			if(formObj.find("input[type=file]").val() == ""
				|| formObj.find("input[type=file]").val() == undefined) return;
			
			try{
				var file_size = formObj.find("input[type=file]")[0].files[0].size;
				if(file_size>5000000) {
					UCMS.alert("업로드파일은 5MB이하 크기만 사용가능합니다.");
					UCMS.hideLoading();
	        		return;
				} 
			}catch(e){}
			
			formObj.attr("action", apiPath);
			
			formObj.ajaxForm({
		        beforeSubmit: function(a,f,o) {
		            o.dataType = "html";
		        },
		        success: function(data) {
		        	
		        	UCMS.log(data);
		        	
		        },
		        error: function (xhr, ajaxOptions, thrownError) {
		        	
		        	UCMS.log(thrownError);
		        	
		        }
		    });
			
			formObj.submit();
		}
		
	});
	
	return FileSelectorPanel;
});