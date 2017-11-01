/**
 * Project : MobileOven
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
(
[
  "BaroPanelBase",
  "BaroProps",
  "Logger",
  "osapi",
  "excanvas",
  "jcrop",
  "imagecanvas-0.8.1"
]
,
function( BaroPanelBase, BaangProps, Logger, osapi)
{
	var EVENT = 
	{
		CONFIRM : "form:confirm",
		CANCEL : "form:cancel"
	};
	var _icon_degree = 0;
	
	/**
	 * Panel
	 * region.show( new 위젯명 )
	 * 
	 * 부모위젯에 아래 함수 구현하여 리턴값을 받아 처리한다.
	 * 
	 * self._parent.setSelectImage({ 
					
					//이미지 처리구분 icon, cover, image( default )
					type: self._type, 
					//처리된 image base64 string
					data : iconData,
					mimeType : "image/png",
					//임시 경로.
					temp_res_path : self._temp_icon_res_path
					
				});
	 */
	var	ImageEditorPanel = BaroPanelBase.extend(
	{
		_visionModule : null,
		
		_temp_icon_res_path : null, 
		_icon_mimeType : "image/png", 
		_icon_up_flag : false,
		_icon_degree : 0,
		
		_main : null,
		_existModel : null,
		_curImageCanvas : null,
		_curSelectImage : 'iconImage',
		
		_stat_crop : "off",
		
		_iconRotating : false,
		
		_options : null,
		
		_parent : null,
		
		_type : null,
		
		_image : null,
		
		_cropInit : {
			
			width : 282,
			height : 282
		},
		
		template: "#imageeditor",
		tagName : "div",
		regions: 
		{
    	},
	    events:
		{	
	    	"click .btn_go_native_image" : "getNativeImage",
	    	"click .btn_go_native_camera" : "getNativeCamera",
	    	"click .btn_go_crop_canvas" : "iconCrop",
	    	"click .btn_go_rotate_canvas" : "iconRotate",
	    	"change .btn_web_get_image" : "onGetWebImage",
	    	"click .btn_go_iconUpClose"	: "iconUpClose",
	    	"click .btn_go_Cancel"	: "onCancel"
		}
		,
		ui:
	    {
			headTitle : ".headTitle",
			subTitle : ".subTitle",
			infoMsg : ".infoMsg",
			web_image_form : "#web_image_form",
			btn_go_native_image : ".btn_go_native_image",
			btn_go_native_camera : ".btn_go_native_camera",
			editor_preview_box : "#editor_preview_box",
			editor_canvasPreview : "#iconCanvasPreview",
			editor_imagePreview : "#iconPreview",
			icon_control_popup : "#icon_control_popup",
	    	img_control_popup : "#img_control_popup",
	    	editor_iconCanvas : "#editor_iconCanvas",
	    	btn_go_Cancel : ".btn_go_Cancel"
	    }
		,
		initialize: function(options)
		{
			this._options = options;
			
			this._parent = options.frame;
			
			this._type = options.type;
			
			if(this._type == undefined) this._type = "image";
			
			this._image = options.image;
			
			this._visionModule = osapi.getModule("Vision");
        }
		,
        onShow: function()
        {
        	
        	var self = this;
        	
        	$("body").css("overflow","hidden");
        	
        	Logger.debug(self._options);
        	Logger.debug("self._type" + self._type );
        	
        	if(UCMS.SPA.isAppOS()==true){
				
				self.ui.web_image_form.hide();
				
				self.ui.btn_go_native_image.show();
				self.ui.btn_go_native_camera.show();
			}
			else{
				
				self.ui.btn_go_native_image.hide();
				self.ui.btn_go_native_camera.hide();
				
				self.ui.web_image_form.show();
			}
        	
        	if(self._type == "cover"){
        		
        		self.ui.headTitle.html("커버이미지 등록하기");
        		self.ui.subTitle.html("커버 이미지 미리보기 ");
        		self.ui.infoMsg.html(" 커버이미지는 가로 1024px 이상, 높이 400px 이상의 이미지를 업로드하여 주세요.");
        		
        		self._cropInit.width = 428;
        		self._cropInit.height = 160;
        		
        		$("#editor_iconCanvas").attr("width", self._cropInit.width);
        		$("#editor_iconCanvas").attr("height", self._cropInit.height);
        	}
        	else if(self._type == "icon"){
        		
        		self._cropInit.width = 250;
        		self._cropInit.height = 250;
        		
        		$("#editor_iconCanvas").attr("width", self._cropInit.width);
        		$("#editor_iconCanvas").attr("height", self._cropInit.height);
        		
        	}
        	else{
        		
        		self._cropInit.width = 300;
        		self._cropInit.height = 300; 	
        		
        		self.ui.headTitle.html("이미지 등록하기");
        		self.ui.subTitle.html("이미지 미리보기 ");
        		self.ui.infoMsg.html(" 최소 가로크기가 500px 이상이어야 이미지가 깨끗하게 보입니다.");
        	}

    		
    		
    		if(self._image){
    			
    			self._image = self._image.replace(BaangProps.getHosts().file , "");
    			
    			self.loadIconImage(self._image);
    		}
        	
        	Logger.debug( '#editor_preview_box.thumbnail' + $("#editor_preview_box.thumbnail").css("width"));
        
        }
		,
		
		iconCrop : function(){
			
			var self = this;
			
			if(self._icon_up_flag == false){
				
				UCMS.alert("선택한 신규 아이콘이미지가 없습니다.");  
				return;
			}
			
			/**
			 * 이미지 크롭기능.  /plugin/imagecanvas.js 에 구현된 함수.
			 * 옵션
			 * 
			 * setSelect : 초기화시 자동지정되게하는 크롭영역 포커스
			 * 
			 *    1. jcrop에 전달될 옵션. width: 80, height : 80, setSelect:[ 0, 0, 80, 80 ]
			 *    2. jcrop의 기능 활성, 비활성, doChange : true, doSelect : true, 
			 *     
			 *   */
			UCMS.log("self._stat_crop :: " + self._stat_crop);
			
			if( self._stat_crop == "off" ){
				
				self.cropOn();
			}
			else{
				
				self.cropOff();
				
			}
		}
		
		,
		
		cropOn : function(){
			
			var self = this;
			
			$(self.ui.editor_preview_box[0]).find(".jcrop-holder").remove();
			
			$("#iconCanvasPreview").hide();
			$("#iconPreview").show();
			
			if(jcrop_api != undefined)
				jcrop_api.destroy();
			
			var imgdata = getCanvasCapture("iconCanvasPreview");
			
			$(self.ui.editor_preview_box[0]).find("img").remove();
			$('<img src="' + imgdata+'" id="iconPreview" />').appendTo($(self.ui.editor_preview_box[0]));
			
			var jcropOption = {
					
					doChange : true, 
					doSelect : true, 
					width: self._cropInit.width, 
					height : self._cropInit.height
			};
			
			
			if(self._type == "icon"){
				
				jcropOption.setSelect = [ 0, 0, self._cropInit.width, self._cropInit.height ];
			}
			
			UCMS.log("self._cropInit.width :: " + self._cropInit.width);
			UCMS.log("self._cropInit.height :: " + self._cropInit.height);
			
			setCrop('iconPreview', "editor_iconCanvas", jcropOption);
				
			self._stat_crop = "on";
			
		}
		
		,
		
		cropOff : function(){
			
			var self = this;
			
			if(jcrop_api != undefined){
			
				jcrop_api.destroy();
				jcrop_api = null;
				
			}	
			
			$("#iconCanvasPreview").show();
			$("#iconPreview").hide();
			
			self._stat_crop = "off";
		}
		
		,
		
		iconRotate : function(){
			
			var self = this;
			
			self.cropOff();
			
			self._iconRotating = true;
			
			if(self._coverRotating == true){
				
				_cur_degree = 0;
				
				self._coverRotating = false;
			}
			
			if(self._icon_up_flag == false){
				
				UCMS.alert("선택한 신규 아이콘이미지가 없습니다.");
				return;
			}
			
			UCMS.showLoading();
			
			rotateProc("iconPreview", "iconCanvasPreview", 90, function(){
				
				var iconData = getCanvasCapture("iconCanvasPreview");
				
				setTimeout(function(){
					
					loadImageDataToCanvas( iconData , "editor_iconCanvas", true);
					
					UCMS.hideLoading();
					
					self._icon_up_flag = true;
						
				}, 500);
				
				
			}
			);

		}
		
		,
		
		clearIconPreview :function(parentDivId){
			
			if(parentDivId == undefined) return;
			
			var parentDiv = $("#" + parentDivId);
			
			if(parentDiv){
				
				$("#" + parentDivId + " img").remove();
				
			}
			
			$('<img src="' + BaangProps.getHosts().resource  + '/themes/base/img/blank.gif" height="150" id="iconPreview" />').appendTo($("#" + parentDivId));

			
		}
		
		,
		getNativeImage : function(){
			
			var self = this;
			
			UCMS.showLoading();
			
			var promise = this._visionModule.getPicture("gallery", {itype : self._type });
			
			if(promise){
				
				promise.then(
						function(result){
							
							Logger.debug("_visionModule.getPicture result :: " + JSON.stringify(result));
							
							self.TI_returnImageHandler(result.extraData);
						}
				);
				
			}else{
				
				UCMS.hideLoading();
			}
			
		}
		
		,
		getNativeCamera : function(){
			
			var self = this;
			
			UCMS.showLoading();
			
			var promise = this._visionModule.getPicture("camera", {itype : self._type });
			
			if(promise){
				
				promise.then(
						function(result){
							
							Logger.debug("_visionModule.getPicture result :: " + JSON.stringify(result));
							
							self.TI_returnImageHandler(result.extraData);
						}
				);
				
			}else{
				
				UCMS.hideLoading();
			}
			
		}
		
		,
		
		onGetWebImage : function(evt){
			
			Logger.log('onGetWebImage === =');
			Logger.log(evt);
			
			var self = this;
			
			if(  self.validFileCheck( evt.currentTarget, "GIF,PNG,JPG,JPEG" , 0 )  ){}
			else return;
			
			
			UCMS.showLoading();
			
			self.WEB_imageTempSave(
					function(data){
						
						//UCMSPlatform.log('return icon image data === =' + data);
						
						var result = JSON.parse(data);
						
						if(result.resultCode != 0){
							
							UCMS.alert("일시적인 오류로 파일을 로딩할 수 없습니다.");
							return;
						}

						self._temp_icon_res_path = result.extraData.path;
						
						Logger.debug("self._icon_mimeType=" + self._icon_mimeType);
						
						/*
						self.WEB_getBase64FromFile(result.extraData.path, function(base64Data){
							
							self.TI_returnImageHandler({
								itype : "icon",
								tempPath : result.extraData.path,
								data : base64Data
							});
							
						});*/
						self.TI_returnImageHandler({
							itype : "icon",
							tempPath : result.extraData.path,
							data : result.extraData.base64
						});
						
						
						UCMS.hideLoading();
							
					}
					,function(err){
						
						UCMS.hideLoading();
					}
					);
			
			setTimeout(function(){
				UCMS.hideLoading();
			}, 5000);
		}
		
		,
		WEB_imageTempSave : function(retCallback, errCallback){
			
			var self = this;	
			
			var session = BaangProps.getSessionParams();
			
			var user_id = BaangProps.getUser().id==null?'guest':BaangProps.getUser().id;

        	var sp_n = session.SP_N;
        	var sp_k = session.SP_K;
        	
			var apiPath = BaangProps.getHosts().api + "/bbang/temp/file/create/" + user_id + "?sp-name=" + sp_n + "&sp-key=" + sp_k;
			
			var formId = "web_image_form";
			
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
		        	
		        	if(retCallback)
		        		retCallback(data);
		        	
		        },
		        error: function (xhr, ajaxOptions, thrownError) {
		        	
		        	UCMSPlatform.log(thrownError);
		        	
		        	if(errCallback)
		        		errCallback();
		        	
		        }
		    });
			
			formObj.submit();
		}
		,
		WEB_getBase64FromFile : function(resultPath, cbFunction){
			
			var		caller	 = new UCMSPlatform.API();
    		var		pmodel	 = new Backbone.Model();
    		
    		var session = BaangProps.getSessionParams();
			
			var user_id = BaangProps.getUser().id;
        	
        	var sp_n = session.SP_N;
        	var sp_k = session.SP_K;
    		
    		var		apiUrl	= BaangProps.getHosts().api + "/bbang/temp/img/base64/" + user_id + "?sp-name=" + sp_n + "&sp-key=" + sp_k;
    		
    		caller.setAjaxParams(
			{
				contentType: 'application/json; charset=utf-8',
				dataType: 'json',
				data : {
					
					image_path : resultPath
				}
			});
    		
    		caller.query(apiUrl, pmodel, function(rmodel, resp, options)	//서버에서 데이터를 가져옴
			{	
    			
				UCMSPlatform.log(rmodel.attributes);
				
				if(rmodel.attributes && rmodel.attributes.extraData){
					
					var base64Data = rmodel.attributes.extraData;
					
					if(cbFunction)
						cbFunction(base64Data);
			       
				}
			
			}); 
			
		}
		
		,
		TI_returnImageHandler : function(imageex){
			
			var self = this;
			
			Logger.debug('returnImageHandler === =');
			
			if(imageex == undefined || imageex.data == null) { 
				UCMS.hideLoading();
				
				return;
			}
						
			if(imageex.itype == "none") { 
				UCMS.hideLoading();
				
				return;
			}
				
			var imgdata =  null;
			
			
			if(imageex.mimeType == undefined || imageex.mimeType == null){
				imageex.mimeType = "image/png";
			} 
			
			/** ex) image/png 
			 * */
			
			imgdata = null;
			
			if(imageex.data.indexOf("data:image") > -1){
				
				imgdata = imageex.data;
			}
			else{
				
				imgdata = "data:" + imageex.mimeType + ";base64," + imageex.data;
			}
			
			$(self.ui.editor_preview_box[0]).find("img").remove();
			$('<img src="' + imgdata+'" id="iconPreview" />').appendTo($(self.ui.editor_preview_box[0]));
			
			$(self.ui.editor_preview_box[0]).find("canvas").remove();
			$('<canvas id="iconCanvasPreview" ></canvas>').appendTo($(self.ui.editor_preview_box[0]));
			
			if(self._type == "image"){
			
				//self._cropInit.width = $("#iconPreview").css("width");
				//self._cropInit.height = $("#iconPreview").css("height");
				
				try{
					/*
					self._cropInit.width = self._cropInit.width.replace("px", "");
					self._cropInit.height = self._cropInit.height.replace("px", "");
						
					self._cropInit.width = Number(self._cropInit.width);
					self._cropInit.height = Number(self._cropInit.height);
					*/
					Logger.debug('TI_returnImageHandler_cropInit width' + self._cropInit.width);
					Logger.debug('TI_returnImageHandler_cropInit height' + self._cropInit.height);
					
					$("#editor_iconCanvas").attr("width", self._cropInit.width );
					$("#editor_iconCanvas").attr("height", self._cropInit.height );
					
				}catch(e){}
			}
			
			setTimeout(function(){
				
				setImageSize("iconPreview");
				
				$("#iconPreview").css("display", "none");
				$("#iconCanvasPreview").css("display", "");
				
				Logger.debug('iconPreview width' + $("#iconPreview").css("width"));
				Logger.debug('iconPreview height' + $("#iconPreview").css("height"));
				
				$("#iconCanvasPreview").attr("width", $("#iconPreview").css("width") );
				$("#iconCanvasPreview").attr("height", $("#iconPreview").css("height") );
				
				rotateProc("iconPreview", "iconCanvasPreview", 0);

				//target canvas의 크기를 기준으로 이미지를 복사.
				updateCanvas(0, 0, $("#editor_iconCanvas").attr("width"), $("#editor_iconCanvas").attr("height"), "editor_iconCanvas", "iconPreview");

				UCMS.hideLoading();
				self._icon_up_flag = true;
				
			}, 500);
    		
				
			Logger.debug('returnImageHandler end');
			
			UCMS.hideLoading();
			
		}
		
		,
		iconUpClose : function(){
			
			var self = this;
			
			var iconData = null;
			
			
			if(self._stat_crop == "on"){
				
				iconData = getCanvasCapture("editor_iconCanvas");
				
			}else{
				
				iconData = getCanvasCapture("iconCanvasPreview");
				
			}
			
			if( this._parent != undefined && this._parent != null )
			{
				self._parent.setSelectImage(
				{
					type: self._type, 
					data : iconData,
					mimeType : "image/png",
					temp_res_path : self._temp_icon_res_path
				});
			}
			
			this.trigger(EVENT.CONFIRM,
			{
				type: self._type, 
				data : iconData,
				mimeType : "image/png",
				temp_res_path : this._temp_icon_res_path
			});
			
			self.onClose();
		}
		,
		onClose : function(){
			
			var self = this;
			
			UCMSPlatform.log("imageEditor onClose");
			
			self.cropOff();
			
			self._stat_crop = "off";
			
			//이미지 회전 전역변수 초기화.
			///plugin/imagecanvas.js 에 정의.
			
			_cur_degree = 0;
			self._iconRotating = false;
			self._coverRotating = false;
			
			$("body").css("overflow","auto");
		}
		
		,
		onCancel :function(){
			
			this.onClose();
			
			if( this._parent != undefined )
			{
				this._parent.setSelectImage(null);
			}
			
			this.trigger(EVENT.CANCEL);
		}
		,
		
		loadIconImage : function(imagePath){
			
			var		self = this;
			self.TI_returnImageHandler({
				
				itype : self._type,
				data : imagePath
			});
    		
    		setTimeout(function(){
    			
    			UCMS.hideLoading();
    		}, 5000);
		}
		,
		
		validFileCheck : function(filethis, allowedTypes, maxFileSize){
			
			Logger.log("WEB_fileCheck");
			
			var ret = false;
			
			var self = this;
			
			var isFileTypeAllowed = function(fileName) {
				
	            var fileExtensions = allowedTypes.toLowerCase().split(",");
	            var ext = fileName.split('.').pop().toLowerCase();
	            if (allowedTypes != "*" && $.inArray(ext, fileExtensions) < 0) {
	                return false;
	            }
	            return true;
	        };

           
	        var fileExtensions = allowedTypes.toLowerCase().split(",");
			
            var filenameStr = $(filethis).val();
            
            if (!isFileTypeAllowed(filenameStr)) {
            	
            	Logger.log('can not support ext');
            	
            	UCMS.alert("지원하지 않는 파일형식입니다.");
            	
                return;
            }
            
            try{
            	
	            var fspec = filethis.files[0];
	            
	            if(fspec && maxFileSize > 0){
	            	
	            	Logger.log("fspec size=" + fspec.size);
		            if(fspec != undefined){
		            	
		            	if( fspec.size > maxFileSize ){
		            		
		            		UCMS.alert("이미지 크기는 " + maxFileSize + "이하 로 제한되어 있습니다.");
		            		
		            		return;
		            	}
		            	
		            }
	            
	            }
            
            }catch(e){
            	
            	Logger.log('can not support filethis.files[0]');
            }
            
            ret = true;
			
            return ret;
		}
	}
	
	);
	
	return ImageEditorPanel;
});