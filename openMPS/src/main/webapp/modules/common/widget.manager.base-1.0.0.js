/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
([
	"Logger",
	"FormItemPanel",
	"manifest!JsonEditor-1.0.0"
]
,
function( Logger, FormItemPanel, JsonEditorModule )
{
	/**
	 * 위젯 메니저의 공통 코드를 구현한다.
	 */
	var WidgetManagerBase = FormItemPanel.extend(
	{
		template: function(data)
		{
			data = _.extend({title:'Manager', message:'파라메터를 설정해 주세요.'}, data);
			return '<div class="panel panel-default">'+
					  '<div class="panel-heading">'+data.title+'</div>'+
					  '<div class="panel-body jsoneditor_region"></div>'+
					  '<div class="panel-footer">'+data.message+'</div></div>';
		},
		initialize: function(options)
		{
			if( options.style )
			{
				options.style.layout || (options.style.layout = '');
				options.style.layout += ' panel-default';
			}
			else
			{
				options.style = {layout: 'panel-default'};
			}
			WidgetManagerBase.__super__.initialize.apply( this, arguments );
			
			this._editor = null;
		}
		,
		onClose: function()
		{
			this._editor = null;
		}
		,
		/**
		 * 위젯 파라메터 설정 정보로 에디터를 생성한다.
		 * 
		 * @param {Marionette.Region} formRegion - JsonEditor 가 배치될 region
		 * @param {object} seedParams - 위젯 파라메터
		 * @param {object} schema - JSON 데이타 구조 정보
		 */
		_createJsonEditor: function(formRegion, seedParams, schema)
		{
			schema||(schema={options:{collapsed: true, remove_empty_properties: true}});
			schema.options = _.extend({collapsed: true, remove_empty_properties: true}, schema.options);
			
			//
			this._editor = new JsonEditorModule.widget(_.extend({}, { startval: seedParams||{}, schema: schema }));
			formRegion.show( this._editor );
		}
		,
		/**
		 * 매니저에 설정된 파라메터를 얻는다.
		 */
		getItemData: function()
		{
			return this._editor.getValue();
		}
	});
	
	return WidgetManagerBase;
});