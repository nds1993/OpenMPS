/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman 
 */

define(
[
	"Logger",
	"BaroPanelBase",
	"PlugInManager"
],
function( Logger, BaroPanelBase, PlugInManager )
{
	/**
	 * 외부 JsonEditor 를 사용하는 JsonEditor 위젯.
	 * @see https://github.com/jdorn/json-editor/blob/master/README.md
	 */
	var	JsonEditor = BaroPanelBase.extend(
	{
		template: "#jsoneditor_html",
		className : "jsoneditor_box",

		/**
		 * JsonEditor 를 초기화한다.
		 * @param options - {
		 * 						schema: {object}
		 * 						theme: {string} 기본값 barebones 
		 * 						iconlib: {string} 기본값 fontawesome4
		 * 						disable_edit_json: {boolean} 기본값 true
		 * 						object_layout: {string} normal/grid. 기본값 normal
		 * 						startval: {object} Viewer 에 출력되는 초기화 데이타.
		 * 					}
		 */
		initialize: function(options)
		{
			JsonEditor.__super__.initialize.apply( this, arguments );
			
			Logger.debug("JsonEditor.initialize()");
			Logger.debug("Initialize JSON Value : ");
			Logger.debug(this.getParam('startval'));
			
			//
			this._editor = null;
		}
		,
		onBeforeShow: function()
		{
			this._attachEditor();
		}
		,
		onClose: function()
		{
			if( this._editor )
			{
				this._editor.destroy();
				this._editor = null;
			}
		}
		,
		/**
		 * JsonEditor 모듈을 로드한다.
		 * @returns	{ $.promise }
		 */
		_loadModule: function()
		{
			var d = $.Deferred();
			
			PlugInManager.loadLib("JSONEditor");
			UCMS.loadModuleByPath([ "json-editor" ]
			, function()
			{
				d.resolve(window.JSONEditor);
			}
			, function(e)
			{
				Logger.error(e);
				d.reject(e);
			});
			
			return d.promise();
		}
		,
		/**
		 * 위젯의 캔바스에 에디터를 출력한다.
		 */
		_attachEditor: function()
		{
			if( this.$el.length == 0 )
			{
				UCMS.error("_attachEditor() - Invalid canvas.");
				return;
			}
			
			var self = this;			
			this._loadModule()
			.then(function(Editor)
			{
				self._editor = new Editor
				(
					self.$el[0], 
					_.extend(
					{
						schema: {},
					    theme: 'barebones',
					    iconlib: "fontawesome4",
					    disable_edit_json: false
					}
					, self._params)
				);
			});
		}
		,
		/**
		 * JSON 에디터 내부 객체를 얻는다.
		 */
		getInstance: function()
		{
			return this._editor;
		}
		,
		/**
		 * JSON 에디터에 입력된 값을 얻는다.
		 */
		getValue: function()
		{
			if( this._editor == null )
			{
				return null;
			}
			
			var data = this._editor.getValue();
			Logger.debug("JsonEditor.getValue()");
			Logger.debug(data);
			//return _.extend({}, data);
			return $.extend( true, {}, data );
		}
	}
	,
	{
		PATH: "/plugin/json-editor/jsoneditor.min.js"
	});
	
	return JsonEditor;
});
