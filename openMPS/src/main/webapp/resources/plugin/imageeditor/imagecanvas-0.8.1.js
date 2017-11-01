	
	// Create variables (in this scope) to hold the API and image size
	var jcrop_api, boundx, boundy;
	
	var jcrop_option = null;
	
	var xsize = 200 , ysize = 200;
	var displayWidth = null;
	var displayHeight = null;
	
	var _cur_sourceCanvasId = null;
	var _cur_targetId = null;
	
	var _cur_width = null, _cur_height = null;
	
	var _cur_degree = 0;
	
	var _cur_canvas = null;
	var _cur_img = null;
	
	function getCanvasCapture(canvasId){
		
		var c=document.getElementById(canvasId);
		
		if(c == null) return;
		
		var imgData=c.toDataURL('image/jpg');
		
		return imgData;
	}
	
	function updateCanvas(cx, cy, cw, ch, sourceCanvasId, cropViewImgId, targetW, targetH){
		
		var c=document.getElementById(sourceCanvasId);
		img = document.getElementById(cropViewImgId);
		
		var ctx=c.getContext("2d");
		
		if(targetW != undefined)
		{
			ctx.drawImage(img, cx, cy, cw, ch, 0 ,0, targetW, targetH);
		}
		else
		{
			ctx.drawImage(img, cx, cy, cw, ch);
		}
	}
	
	/*
	 * example url http://deepliquid.com/projects/Jcrop/demos.php
	 * 
	 * option
	 * aspectRatio: xsize / ysize, 가로세로 영역비율
	 * allowResize : true, 리사이즈 가능여부, true 일경우 선택 point view
	 * setSelect: [ 0, 0, 80, 80 ] : 초기에 표시할 영역, 영역선택 고정시 사용. 옵션을 사용하지 않으면 자유선택
	 * 
	 * */
	function setCrop(targetId, sourceCanvasId, options){
			
			jcrop_option = options;
			
			var img = document.getElementById(targetId);
			
			if(img.naturalWidth > 0)
			{
				xsize = img.naturalWidth; 
				ysize = img.naturalHeight;
			}
			if(img.width > 0)
			{
				displayWidth = img.width;
				displayHeight = img.height;
			}
			
			_cur_sourceCanvasId = sourceCanvasId;
			_cur_targetId = targetId;
			
			var jOptions = {
			  keySupport : false,
		      allowResize : true
		    };
			
			if(options.doChange == true){
				
				jOptions.onChange = updatePreview;
			}

			if(options.doSelect == true){
				
				jOptions.onSelect = updatePreview;
			}
			
			if(options.setSelect){
				
				jOptions.setSelect = options.setSelect;
				jOptions.aspectRatio = options.width / options.height;
			}
			else{
				
				jOptions.setSelect = [0, 0, 100, 100];
				jOptions.aspectRatio = 0;
				
			}
			
			$('#' + targetId).Jcrop(
			jOptions
			,function(){
		      // Use the API to get the real image size
		      var bounds = this.getBounds();
		      boundx = bounds[0];
		      boundy = bounds[1];
		      // Store the API in the jcrop_api variable
		      jcrop_api = this;
		      
		      if(options.doSelect == false){
		    	  
		    	  jcrop_api.disable();
		      }

		    });

			function updatePreview(c) {
				
				if (parseInt(c.w) > 0) {
					
					var rx = xsize / displayWidth ;
					var ry = ysize / displayHeight ;
					
					var width = Math.round(rx * c.w);
					var height = Math.round(ry * c.h);
					
					//$("#" + sourceCanvasId).attr("height" , c.h);
					
					var ml = Math.round(rx * c.x);
					var mt = Math.round(ry * c.y);
					
					UCMS.log(c);
					UCMS.log("ml" + ml);
					UCMS.log("mt" + mt);
					UCMS.log("width" + width);
					UCMS.log("height" + height);
					
					$("#" + _cur_sourceCanvasId).attr("width", width);
					$("#" + _cur_sourceCanvasId).attr("height", height);
					
					updateCanvas( ml, mt, width, height, _cur_sourceCanvasId, _cur_targetId, width, height );
					
				}
				
			}
			;
			
			
		};
		
	function setCanvasImage(parentDivId, previewImgId, canvasId, data, degree, fnCallback){
		
		var parentDiv = $("#" + parentDivId);
		
		if(parentDiv){
			
			$("#"+parentDivId + " canvas").remove();
			$('<canvas id="'+ canvasId +'" style="display:none" ></canvas>').appendTo(parentDiv);

		}
		
		rotateProc(previewImgId, canvasId, degree, fnCallback);
		
	};
	
	/*
	 * 
	 * fullCanvasSize == true: canvas 크기를 지정하여 이미지를 채운다.
	 * */
	function rotateProc(previewImgId, canvasId, degree, fnCallback, fullCanvasSize){
		
		var img = document.getElementById(previewImgId);
		var canvas = document.getElementById(canvasId);
		
		if(_cur_degree > 270) _cur_degree = 0;
		
		_cur_degree += degree;
		
		if(canvas == null) return;
		
		var cContext = canvas.getContext("2d");
		
		//var cw = img.width, ch = img.height, cx = 0, cy = 0;
		
		var cw = img.naturalWidth, ch = img.naturalHeight, cx = 0, cy = 0;
		
		UCMS.log("_cur_degree ::: " + _cur_degree);
		
        UCMS.log("rotateProc img width ::: " + img.naturalWidth);
        UCMS.log("rotateProc img height ::: " + img.naturalHeight);
        
        //   Calculate new canvas size and x/y coorditates for image
        switch(_cur_degree){
	        case 90:
	            cw = img.naturalHeight;
	            ch = img.naturalWidth;
	            cy = img.naturalHeight * (-1);
	            break;
	        case 180:
	            cx = img.naturalWidth * (-1);
	            cy = img.naturalHeight * (-1);
	            break;
	        case 270:
	            cw = img.naturalHeight;
	            ch = img.naturalWidth;
	            cx = img.naturalWidth * (-1);
	            break;
	   }
         //  Rotate image            
		canvas.setAttribute('width', cw);
		canvas.setAttribute('height', ch);
		
		cContext.clearRect(0, 0, canvas.width, canvas.height);
		
		cContext.rotate(_cur_degree * Math.PI / 180);
		
		if(fullCanvasSize == true)
			cContext.drawImage(img, cx, cy, canvas.width, canvas.height);
		else
			cContext.drawImage(img, cx, cy);
		
		
		if(fnCallback)
			fnCallback();
			
	};
	

	function rotateImageAndCrop(parentDivId, resourceCanvasId, degree, targetImgId, resultViewCanvas, p_jcrop_option, imgClassName, cbFunction){
		
		var _className = "";
		
		if(imgClassName){
			
			_className = ' class="' + imgClassName + '" ';
		}
		
		rotateProc(targetImgId, resourceCanvasId, degree, function(){
			
			$("#" + parentDivId + " .jcrop-holder").remove();
			$("#" + parentDivId + " img").remove();
		
			var iconData = getCanvasCapture(resourceCanvasId);

			$('<img src="' + iconData+'" ' + _className + ' id="' + targetImgId + '" />').appendTo($("#" + parentDivId));
			
			setCrop(targetImgId, resultViewCanvas, p_jcrop_option);
			
			if(cbFunction)
				cbFunction();
			
		} );
		
	}
	
	function rotateImage(parentDivId, resourceCanvasId, degree, targetImgId, className){
		
		var _className = "";
		
		if(className){
			
			_className = 'class="'+ className +'"';
		}
		
		rotateProc(targetImgId, resourceCanvasId, degree, function(){
			
			$("#" + parentDivId + " img").remove();
		
			var iconData = getCanvasCapture(resourceCanvasId);

			$('<img src="' + iconData+'" ' + _className + ' id="' + targetImgId + '" />').appendTo($("#" + parentDivId));
			
		} );

		
	}
	
	
	function setImageToCanvas(imageSrc, viewCanvas){
		
		var img = document.getElementById(imageSrc);
		
		var canvas = document.getElementById(viewCanvas);
		
		setCrop(imageSrc, viewCanvas, {doSelect : false});
		
		$("#" + imageSrc).siblings(".jcrop-holder").remove();
		$("#" + imageSrc).css("display","");
		$("#" + imageSrc).css("visibility","");
		
		$(".jcrop-holder").remove();
		
		var cContext = canvas.getContext("2d");
		
        var cw = img.width, ch = img.height, cx = 0, cy = 0;
        
         //  Rotate image            
		canvas.setAttribute('width', cw);
		canvas.setAttribute('height', ch);
		
		_cur_width = cw;
		_cur_height = ch;
		
		cContext.drawImage(img, cx, cy);
		
	}
	
	/*
	 * image load to canvas
	 * 
	 * imageSrc : base64 image string 
	 * viewCanvas : copy source image canvas
	 * fixCanvasSize : canvas width, height fix 
	 * */
	function loadImageDataToCanvas(imageSrc, viewCanvas, fixCanvasSize){
		
		var canvas = document.getElementById(viewCanvas);
		
		var cContext = canvas.getContext("2d");
		
		// load image from data url
        var imageObj = new Image();
        imageObj.onload = function() {
        	
        	cContext.clearRect(0, 0, canvas.width, canvas.height);
        	
        	if(fixCanvasSize == true)
        		cContext.drawImage(this, 0, 0, canvas.width, canvas.height);
        	else
        		cContext.drawImage(this, 0, 0);
        };

        imageObj.src = imageSrc;
		
	}
	
	function loadImageSrcToCanvas(imageSrc, imageId, viewCanvas, fullCanvasSize){
		
		var canvas = document.getElementById(viewCanvas);
		var img = document.getElementById(imageId);
		
		var cContext = canvas.getContext("2d");
		
	//  Rotate image            
		//canvas.setAttribute('width', img.width);
		//canvas.setAttribute('height', img.height);
		
		//UCMSPlatform.log('loadImageSrcToCanvas width === =' + img.width);
		//UCMSPlatform.log('loadImageSrcToCanvas height=== =' + img.height);
		
    	cContext.clearRect(0, 0, canvas.width, canvas.height);
    	
    	if(fullCanvasSize == true)
    		cContext.drawImage(img, 0, 0, canvas.width, canvas.height);
    	else
    		cContext.drawImage(img, 0, 0);
        
		
	}
	
	function setImageSize(imageSrc){
		
		var img = document.getElementById(imageSrc);
		
		setCrop(imageSrc, null, {doSelect : false, doChange: false});
		
		$("#" + imageSrc).siblings(".jcrop-holder").remove();
		$("#" + imageSrc).css("display","");
		$("#" + imageSrc).css("visibility","");
		
		$(".jcrop-holder").remove();
		
	}
	