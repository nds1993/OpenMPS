/**
 * Project : MobileOven
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
([
	"Logger",
	"BaroProps",
	"BaroFloating",
	"FormItemPanel",
	"manifest!imageeditor-0.8.1#widget"
]
,
function( Logger, BaroProps, BaroFloating, FormItemPanel, ImageUploader )
{
	/**
	 * 아이콘을 선택하고, 선택된 아이콘을 출력하는 위젯
	 */
	var ImagePlaceHolder = FormItemPanel.extend(
	{
		template: "#img_holder_html",

		ui:
		{
			title: "label",
			preview: "#gconImagePreview",
			btn_select: ".btn_go_imageUpPopup"
		},
		
		events:
		{
			"click @ui.btn_select" : "onSelect"
		},
		
		/**
		 * 범용 콘텐츠의 files 필드가 model 로 설정된다.
		 * 
		 * @params options		{
		 * 	
		 *							type		{string}
		 *							id			{string}
		 *							label		{string}
		 *							selector	{string}
		 *							required	{boolean}
		 *							max_size	{number}
		 *							value		{array}
		 *								
		 * 						}
		 * 						배열로 설정되는 경우 첫번째 이미지가 초기값으로 사용된다.
		 */
		initialize: function(options)
		{
			ImagePlaceHolder.__super__.initialize.apply( this, arguments );

			if( options.model == undefined )
			{
				if( options.value instanceof Array == false )
				{
					options.value = [];
				}
				
				this.model = new Backbone.Model(
				{
					title: options.label,
					mode: 'image',
					file: options.value[0] || { path: null },
					required: options.required
				});
				
				UCMS.debug("imageholder initialize >> ");
				UCMS.debug(this.model);
			}
		},
		
		_initTitle: function()
		{
			var title = this.model.get("title");
			var required = this.model.get("required");
			
			if( required == true )
			{
				title += "<small class='color_point'><i class='fa fa-fw fa-asterisk'></i></small>";
			}
			
			this.model.set("title", title);
		},
		
		/**
		 * 지정한 이미지를 화면요소에 적용한다.
		 * 
		 * @param data		출력되는 이미지 정보
		 * 					{
		 *  					type: icon, cover, image( default ),
		 *    					mimeType: 'image/png',
		 *   					data: 'data:image/png;base64,############',
		 *     					temp_res_path: '/TMP/####'
		 * 					}
		 */
		_setImage: function(img)
		{
			if( this.model.get("mode") !== img.type )
			{
				Logger.error("_setImage() - Not equal operation mode : "+img.type);
				return;
			}

			Logger.info("_setImage() - "+img.temp_res_path);
			var imgData = img.data.split(",");
			if( imgData.length > 2 )
			{
				for(var i=2; i<imgData.length; ++i)
				{
					imgData[1] += ","+imgData[i];
				}
			}
			else if( img.data.length == 1 )
			{
				imgData = ["data:"+img.mimeType+";base64", img.data];
			}
			
			this.ui.preview.attr( "src", imgData[0]+","+imgData[1] );
			this.model.set( "file",
			{
				mime_type: img.mimeType, 
				path: imgData[1]
			});
		},
		
		/**
		 * 선택된 아이템 데이타를 얻는다.
		 * 
		 * 
		 * @return	초기화 옵션의 value 필드에 지정되는 형식을 반환한다. 
		 * 			GCon DTO AttachmentFile 의 형식으로 지정.
		 * 			설정된 값이 없는 경우 null 을 반환한다.
		 * 			{
		 * 				mime_type: ##,
		 * 				name: ##,
		 * 				path: ##,
		 * 				host: ##,
		 * 				create_date: ##
		 * 			} 
		 */
		getItemData: function()
		{
			var file = this.model.get("file");
			return ( typeof file.path == 'string' ? file : null );
		},
		
		onBeforeRender: function()
		{
			var file = this.model.get("file");
			if(file && file.path)
			{
				if( file.path.indexOf("/home") == 0 )
				{
					file.path = BaroProps.getHosts().file + file.path;
				}
			}
			this.model.set({"file" : file});
			
			this._initTitle();
		},
		
		onSelect: function(e)
		{
			var self = this;
			var uploader = new ImageUploader( { type: "image", image: null } );
			uploader.on(ImagePlaceHolder.EVENT.CONFIRM, function(result)
			{
				self._setImage(result);
				BaroFloating.close();
				uploader.close();
				delete uploader;
			});
			uploader.on(ImagePlaceHolder.EVENT.CANCEL, function()
			{
				BaroFloating.close();
				uploader.close();
				delete uploader;
			});

			BaroFloating.open( uploader );
		}
	}
	,
	{
		EVENT:
		{
			CONFIRM: "form:confirm",
			CANCEL: "form:cancel"
		}
	});
	
	return ImagePlaceHolder;
});