/**
 * Project : openMPS MIS
 *
 * SD 영업 > 단가관리 > 표준단가 주간
 * SD0203
 *
 */

define([
	"Logger",
	"NDSProps",
	"SubContainer",
	"APIClient",
	"RendererBase",
	"FormClient"
], function(Logger, NDSProps, SubContainer, APIClient, RendererBase, FormClient)
{
	var Renderer = SubContainer.extend(
	{
		_data : null,
		_fileInfo : null,
		
		initialize: function(options)
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "SD0203";
			this._data = options.data;
			
			this._deferred = options.deferred || null;
		}
		,
		getClient: function()
		{
			if(! this._client )
			{
				var hosts = NDSProps.get('hosts') || { api: '' };
				this._client = new APIClient(
				{
					host: hosts.api,
					systemCode: NDSProps.get('systemCode'),
					contentId: this._contentId
				});	
			}
			return this._client;
		}
		,
		initButton: function()
		{
			var self = this;
			setTimeout(function(){
				self.$el.find(".exFileUpload")
				.change(function(event)
				{
					Logger.debug(event);
					
					if( self._uploading == true )
					{
						// IE 에서 중복 호출되는 상황이 발생하고 있음
						// 2 번째 호출은 무시되도록 처리
						Logger.warn("Duplication Event : file change");
						return;
					}
					
					self.uploadFile($(this));
				});
				
				self.$el.find(".confirm")
				.click(function(event)
				{
					Logger.debug(event);
					
					self.onSend($(this));
				});
				
				self.$el.find(".custCode").val(self._data.custCode);
				self.$el.find(".custName").val(self._data.custName);
				self.$el.find(".faxNo").val(self._data.fax);
				
			},500);
			
		}
		,
		/**
		 * content 영역에 접근이 필요한 경우 오버라이딩한다.
		 */
		onShowComplete: function()
		{
			Logger.debug("onShowComplete ::");
			Logger.debug(this._data);
			
			var self = this;
			UCMS.retry(function()
			{
				//
				self.initButton();
			});
			
		}
		,
		onSend : function($button)
		{
			var self = this;
			var $file = self.$el.find(".fileUpload");
			var $custCode = self.$el.find(".custCode");
			var $faxNo = self.$el.find(".faxNo");
			
			if($file == null){
				UCMS.alert("파일을 선택해 주세요."); return;
			}
			if($file.val() == undefined && $file.val() == "")
			{
				UCMS.alert("파일을 선택해 주세요."); return;
			}
			if(self._fileInfo == null)
			{
				UCMS.alert("파일을 업로드 해주세요."); return;
			}
			if($faxNo.val() == undefined || $faxNo.val() == "")
			{
				UCMS.alert("수신할 팩스번호를 입력 주세요."); return;
			}
			
			var client = this.getClient();
			
			this.showLoading();
			
			var apiPath = client.getAPIPath('create', "fax.send", {});
			
			self._fileInfo.faxNo = $faxNo.val();
			self._fileInfo.custCode = $custCode.val();
			//
			return client.transaction( apiPath, self._fileInfo )
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					UCMS.alert("전송이 완료되었습니다.");
					$file.val("");
					self._fileInfo = null;
				}
				else
				{
					UCMS.reportError(res);
				}
			})
			.fail(function(err)
			{
				UCMS.reportError(err);
			})
			.always(function()
			{
				self.hideLoading();
				//$file.val("");
			});
			
		}
		,
		uploadFile: function($input, errHandler)
		{
			var self = this;
			
			var client = this.getClient();
			var uploader = new FormClient({params:{dataType: "text"}});

			Logger.info("uploadExcel() - file : "+$input.val());
			
			this.showLoading();
			uploader.append( $input );
			
			var apiPath = client.getAPIPath('create', "upload.file", {});

			return uploader.submit( apiPath )
			.then(function(res)
			{
				try
				{
					res = JSON.parse(res);
					if( res.resultCode == 0 )
					{
						if( res.extraData != null )
						{
							self._fileInfo = res.extraData;
							$(".exFileName").val(res.extraData.orginalName);
						}
						else
						{
							UCMS.alert("등록된 데이타가 없습니다.");
						}
					}
					else
					{
						if(errHandler)
						{
							errHandler(res);
						}
						else
						{
							UCMS.reportError(res, "파일 처리중 오류가 발생하였습니다.<br>업로드 파일을 확인하세요.");
						}
					}
					return res;
				}
				catch(e)
				{
					UCMS.reportError(e);
				}
			})
			.fail(function(e)
			{
				Logger.error(e);
				UCMS.reportError(e, "업로드를 실패하였습니다.");
			})
			.always(function()
			{
				self.hideLoading();
				uploader.clear();
			});
		}

	});

	return Renderer;
});
