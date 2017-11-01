/**
 * Project : MobileOven
 *
 * Copyright (c) 2012. FREECORE, Inc. All rights reserved.
 *
 * @author	dbongman
 */

define(
[
],
function()
{
	/**
	 * 필요한 시점에 플러그인 모듈을 로딩하는 기능을 제공한다.
	 */
	var PlugInManager =
	{
		/**
		 * 지정한 라이브러리를 로드한다.
		 * @param {string} moduleId - 모듈 식별자
		 * @return {$.promise} 이미 로딩되었거나, 로딩 성공시 resolve(), 지원하지 않는 플러그인이거나, 로딩 실패한 경우 reject()
		 */
		loadLib: function(moduleId)
		{
			var module = PlugInManager.MODULE[ moduleId ];
			if(module == undefined)
			{
				var d = $.Deferred();
				d.reject();
				return d.promise();
			}

			if( module["CSS"] )
			{
				UCMS.initStyle( module["CSS"] );
			}

			if( module["shim"] )
			{
				var cfg = {};
				cfg[moduleId] = module["shim"];
				// 앞서 설정된 값과 머지된다.
				require.config({"shim": cfg });
			}
			else if( module["packages"] )
			{
				var cfg = module["packages"];
				// 앞서 설정된 값과 머지된다.
				require.config({"packages": cfg });
			}

			return (module["JS"] instanceof Array
					? UCMS.loadModuleByPath( module["JS"] )
					: UCMS.loadModuleByPath([ module["JS"] ]));
		}
		,
		/**
		 * 로딩된 모듈인지 확인한다.
		 * @param {string} moduleId - 모듈 식별자
		 * @return {boolean}
		 */
		isLoaded: function(moduleId)
		{
			var module = PlugInManager.MODULE[ moduleId ];
			if(module == undefined)
			{
				return false;
			}

			return ($("script[src='"+module["JS"]+"']").length == 1);
		}
	};

	PlugInManager.MODULE =
	{
		"Moment":
		{
			//
			// Moment.js 2.18.1
			// http://momentjs.com/
			//

			// require.config({packages:{}}) 에 설정되는 내용을 선언한다.
			// @see http://requirejs.org/docs/api.html#packages
			// packages 로 추가할 때는 ResourcesDB 에 모듈 정보를 추가할 필요가 없다.
			"packages": [{
				"name": "moment",
				"location": "/plugin/moment",
				"main": "moment-with-locales.min"
			}],
	    	JS: "moment"
		}
		,
		"DatetimePickerV4":
		{
			//
			// Bootstrap 3 Datepicker v4
			// http://eonasdan.github.io/bootstrap-datetimepicker/
			//
			CSS:	'/plugin/bootstrap/css/bootstrap-datetimepicker.min.css',
			JS:		'DatetimePickerV4',
			"shim": {
	    		"deps": ["jquery", "moment"]
	    	}
		}
		,
		"jqgrid4-4.7.0":
		{
			// require.config({shim:{}}) 에 설정되는 내용을 선언한다.
			// @see http://requirejs.org/docs/api.html#config-shim
			"shim": {
	    		"deps": ["jquery", "jqgrid4-4.7.0-i18n"]
	    	},
	    	JS: "jqgrid4-4.7.0"
		}
		,
		"jqgrid-5.2.1":
		{
			// require.config({shim:{}}) 에 설정되는 내용을 선언한다.
			// @see http://requirejs.org/docs/api.html#config-shim
			"shim": {
	    		"deps": ["jquery", "jqgrid-5.2.1-i18n"]
	    	},
	    	JS: "jqgrid-5.2.1"
		}
		,
		"xml2json":
		{
			// x2js - XML to JSON and vice versa for JavaScript
			// https://github.com/abdmob/x2js
			// http://jsfiddle.net/abdmob/gkxucxrj/1/
			"shim": {
	    		"deps": []
	    	},
	    	JS: "xml2json"
		},
		"TableCSVExport":
		{
			// require.config({shim:{}}) 에 설정되는 내용을 선언한다.
			// @see http://requirejs.org/docs/api.html#config-shim
			// http://www.jqueryscript.net/table/Multi-functional-Table-To-CSV-Converter-With-jQuery-TableCSVExport.html
			"shim": {
	    		"deps": ["jquery"]
	    	},
	    	JS: "TableCSVExport"
		}
		,
		"jquery-mCustomScrollbar-3.1.5":
		{
			"shim": {
	    		"deps": ["jquery"]
	    	},
	    	JS: "jquery-mCustomScrollbar-3.1.5"
		}
		,
		"RealGrid-1.1.25-js":
		{
			"shim": {
	    		"deps": ["jquery", "RealGrid-1.1.25-lic"]
	    	},
	    	JS: "RealGrid-1.1.25-js"
		}
		,
		"JSONEditor":
		{
			//
			// JSON Editor v0.7.28 - JSON Schema -> HTML Editor
			// https://github.com/jdorn/json-editor
			//

			"packages": [{
				"name": "json-editor",
				"location": "/plugin/json-editor",
				"main": "jsoneditor.min"
			}],
	    	JS: "json-editor"
		}
	};

	return PlugInManager;
});
