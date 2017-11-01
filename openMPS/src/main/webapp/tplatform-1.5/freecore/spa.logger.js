/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2016 FREECORE, Inc. All rights reserved.
 * 
 * @author dbongman
 */

define([], function()
{
	/**
	 * 개발 단계에서 사용하는 로거
	 */
	var LoggerDev =
	{
		info : function(msg)
		{
			this._hybrid == true ? Ti.API.info( msg ) : UCMS.info( msg );
		},

		warn : function(msg)
		{
			this._hybrid == true ? Ti.API.warn( msg ) : UCMS.warn( msg );
		},

		error : function(msg)
		{
			this._hybrid == true ? Ti.API.error( msg ) : UCMS.error( msg );
		},

		debug : function(msg)
		{
			this._hybrid == true ? Ti.API.debug( msg ) : UCMS.debug( msg );
		},

		trace : function(msg)
		{
			this._hybrid == true ? Ti.API.info( msg ) : UCMS.log( msg );
		},
		
		log : function(msg)
		{
			this._hybrid == true ? Ti.API.info( msg ) : UCMS.log( msg );
		}
	};

	/**
	 * 서비스 중에 사용되는 로거
	 * 로그 정보를 보관하고, 백엔드로 전송하는 역할을 수행한다.
	 */
	var LoggerService =
	{
		info : function(msg)
		{
		},

		warn : function(msg)
		{
		},

		error : function(msg)
		{
		},

		debug : function(msg)
		{
		},

		trace : function(msg)
		{
		},
		
		log : function(msg)
		{
		}
	};
	
	var Creator =
	{
		_attr :
		{
			_debug : true
			,
			_hybrid : false
		}
		,
		setEnable : function(bEnable)
		{
			this._attr._debug = bEnable;
			
			function checkHybridMode()
			{
				var bHybrid;
				
				try
				{
					Ti.App.fireEvent("check:hybrid:mode");
					
					//
					bHybrid = true;
				}
				catch(e)
				{
					bHybrid = false;
				}
				
				return bHybrid;
			};
			
			/**
			 * Titanium WebView 내에서 동작되는치 체크한다.
			 */
			this._attr._hybrid = checkHybridMode();
		}
		,
		getLogger : function()
		{
			var Logger = this._attr._debug == true ? LoggerDev : LoggerService;
			
			return _.extend( this._attr, Logger );
		}
	};
	
	Creator.setEnable( UCMS._isDebug );

	return Creator.getLogger();
});