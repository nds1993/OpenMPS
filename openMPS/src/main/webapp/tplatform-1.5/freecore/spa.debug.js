/**
 * Project : UCMS( Unified Contents Messaging Solution )
 *
 * Copyright (c) 2013 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

define(function()
{
	var		disableConsole = 
	{
			log: function(){},
			info: function(){},
			debug: function(){},
			warn: function(){},
			error: function(){},
			assert: function(){}
	};
	
	var		debug =
	{ 
		_debugMode : false,
		
		setDebugMode: function(bMode)
		{
			this._debugMode = bMode || false;
			UCMS._logger = this.getConsole();
		},
		getConsole : function()
		{
			return ( this._debugMode ? this._getSysConsole() : disableConsole );
		},
		_getSysConsole : function()
		{
			return ( typeof console === "undefined" ? disableConsole : typeof console.debug === "undefined" ? disableConsole : console );
		}
	};
	
	return debug;
});