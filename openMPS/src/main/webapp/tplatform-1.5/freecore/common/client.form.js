/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2014 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman 
 */

define(
[
	"ClientBase", "AResultClasses", "BaroProps", "Logger"
]
,
function(ClientBase, AResult, BaroProps, Logger)
{
	/**
	 * 파일을 전송할 수 있는 폼 객체를 구햔한다.
	 * 
	 * 하나의 파일이 전송되는 경우 file 필드에 담기고,
	 * 여러 개의 파일이 전송되는 경우 각 파일은 fileList 필드에 담긴다.
	 * 즉, 백엔드에서 파일을 읽을 때 참고하는 필드명은 file 또는 fileList 이다.
	 */
	var FormClient = ClientBase.extend(
	{
		initialize: function()
		{
			FormClient.__super__.initialize.apply( this, arguments );
			Logger.debug("FormClient.initialize()");
			
			this.clear();
		}
		,
		clear : function()
		{
			this._formData = null;
			this._formData = new FormData();
		}
		,
		/**
		 * 폼에 전송할 데이타를 설정한다.
		 * 
		 * @param {string|$} name	문자열인 경우 두번째 파라메터와 조합하여 name:value 의 쌍으로 값을 설정한다.
		 * 							$ 인 경우 파일 태그 "input[type=file]" 을 선택한 jquery 객체를 지정한다.
		 * 							하나의 파일이 선택된 경우 "file" 필드에 담기고, 복수개의 파일이 선택된 경우 각 파일은 fileList 필드에 담긴다.
		 * @param value		설정되는 파라메터의 값
		 */
		append : function(name, value)
		{
			if( typeof name.jquery == "string" )
			{
				if( name.length == 0 )
				{
					Logger.warn("append() - empty data");
					return;
				}
				
				//
				// input[type=file] 을 위한 setter
				//
				var $input = name;
				var files = [];
				
				for(var i =0; i<$input.length; ++i)
				{
					$.each( $input[i].files, function(i, file) 
					{
						files.push( file );
					});
				}

				if( files.length == 1 )
				{
					this._formData.append("file", files[0]);
				}
				else
				{
					var self = this;
					_.each(files, function(file, i, files)
					{
						self._formData.append("fileList", file);
					});
				}
			}
			else
			{
				//
				// 일반 파라메터를 위한 setter
				//
				this._formData.append(name, value);
			}
		}
		,
		/**
		 * append() 로 파라메터를 설정한 후 본 메소드를 호출한다.
		 * 
		 * @param {string} apiPath		API 경로
		 * @return {$.xhr}
		 */
		submit : function(apiPath)
		{
			return this._post( apiPath, this._formData );
		}
	});
	
	return FormClient;
});