/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2013, 2014, 2015 FREECORE, Inc. All rights reserved.
 * 
 * @author dbongman
 */

define(
[],
function()
{
	var Classes = {};
	
	Classes.Result = Backbone.Model.extend(
	{
		defaults:
		{
			resultCode: 0,
			msg: "success"
		}
	});

	/**
	 * ACmd 형식의 명령 처리 결과를 담는다.
	 */
	Classes.ResultEx = Classes.Result.extend(
	{
		defaults:
		{
			extraData: null
		},
		
		initialize: function()
		{
			Classes.Result.prototype.initialize.apply( this, arguments );
	    },
		
		setData: function(data)
		{
			this.set("extraData", data);
		}
	});

	Classes.Helper =
	{
		SUCCESS : function(extraData)
		{
			return new Classes.ResultEx(
			{
				resultCode: 0,
				msg: "success",
				extraData: extraData || null
			});
		},
		
		ERROR : function(errCode, errMsg)
		{
			if( !errCode )
			{
				errCode = -1;
			}
			
			if( !errMsg )
			{
				errMsg = "Error";
			}
			
			return new Classes.ResultEx({ resultCode: errCode, msg: errMsg });
		},
		
		ERROREX : function(errData)
		{
			return new Classes.ResultEx({resultCode: -1, msg: "error", extraData: errData });
		},
		
		ERROREX2 : function(errData, errCode, errMsg)
		{
			return new Classes.ResultEx({resultCode: errCode, msg: errMsg, extraData: errData });
		},
		
		UNAUTHORIZED : function(errData)
		{
			var r = this.ERROREX(errData);
			r.set({resultCode:-100, msg: "Not allowed a certificate about target resource!"});
			return r;
		},
		
		FORBIDDEN : function(errData)
		{
			var r = this.ERROREX( errData );
			r.set({resultCode:-101, msg: "The denied your request from target service!"});
			return r;
		},
		
		NOTSUPPORTEDPLATFORM : function()
		{
			return this.ERROR(-102, "It does not supported In the current platform.");
		},
		
		EXCEPTION : function(errData)
		{
			var r = this.ERROREX(errData);
			r.set({resultCode:-200, msg: "Occurred an exception condition!"});
			return r;
		},
		
		NOTFOUND : function(errData)
		{
			var r = this.ERROREX(errData);
			r.set({resultCode:-404, msg: "There is no data!"});
			return r;
		},
		
		INVALID_PARAMETERS : function(errData)
		{
			var r = this.ERROREX(errData);
			r.set({resultCode:-500, msg: "Invalid parameters!"});
			return r;
		},
		
		DUPLICATE_REQUEST : function(errData)
		{
			var r = this.ERROREX(errData);
			r.set({resultCode:-501, msg: "duplicate a request!"});			
			return r;
		}
	};
	
	return Classes;
});