/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2017. FREECORE, Inc. All rights reserved.
 * 
 * @author dbongman
 */

define(
["Logger"],
function(Logger)
{
	/**
	 * NLS 리소스를 관리한다.
	 */
	var NLSManager = Backbone.Model.extend(
	{
		initialize: function()
		{
			// 전역 nls 리소스 저장소
			this._nls = null;
		}
		,
		/**
		 * 지정한 NLS API 로 부터 리소스를 로드한다.
		 * 로드된 리소스는 기존의 리소스와 통합된다.
		 * 중복된 식별자를 갖는 리소스는 새로운 것으로 대체된다.
		 * 
		 * @param {string} apiPath - NLS API 경로
		 * @param {object} params - NLS 리소스 요청 파라메터. 필요한 경우에만 지정
		 * @return {jqXHR}
		 */
		load: function(apiPath, params)
		{
			var self = this;
			this.url = apiPath;
			return this.fetch( params )
			.then(function()
			{
				self.addResource( self.toJSON() );
			});
		}
		,
		/**
		 * nls 리소스를 추가한다.
		 * 
		 * @param {object} nlsResources - 신규 nls 리소스.
		 * @return {object} 통합된 리소스
		 */
		addResource: function(nlsResources)
		{ 
			this._nls || ( this._nls = {} );
			return _.extend(this._nls, nlsResources);
		}
		,
		/**
		 * 로드된 nls 리소스를 얻는다.
		 * 뷰에서 사용된 데이타가 혼합되어 존재한다.
		 */
		getResource: function()
		{
			return this._nls;
		}
		,
		/**
		 * 지정한 templateString 에 로딩된 nls 리소스를 반영한다.
		 * @see http://underscorejs.org/#template
		 * @param {string} templateString - i18n 리소스 적용이 필요한 템플릿 스트링
		 * @param {object} data - 템플릿에서 사용되는 데이타 셋. 존재하는 경우 지정함.
		 * @return {string} nls 리소스가 적용된 스트링. 불가능한 경우 null 반환.
		 */
		template: function(templateString, data)
		{
			if( this._nls == null || _ == undefined )
			{
				Logger.warn("NLSManager.template() - Need to load nls resources.");
				return null;
			}
			
			//
			// 뷰에 출력시 메모리 절약을 위해 위젯으로 복사하지 않고,
			// 위젯의 data 를 전역 nls 리소스에 담아서 뷰를 랜더링한다.
			//
			return _.template(templateString, _.extend(this._nls, data));
		}
	}
	,
	{
		__INSTANCE__: null
		,
		getManager: function()
		{
			if( this.__INSTANCE__ == null )
			{
				this.__INSTANCE__ = new this();
			}
			
			return this.__INSTANCE__;
		}
		,
		/**
		 * 리소스가 활성화 되어 있는가?
		 * @return {Boolean}
		 */
		isActive: function()
		{
			return this.getManager().getResource() != null;
		}
		,
		/**
		 * NLS 리소스를 템플릿에 적용한다.
		 */
		template: function(templateString, data)
		{
			return this.getManager().template( templateString, data );
		}
	});
	
	// 템플릿 랜더링 절차에 NLS 리소스를 반영한다.
	Marionette.TemplateCache.prototype.compileTemplate = function(rawTemplate)
	{
		return function(data)
		{
			var mgr = NLSManager.getManager();
			if( mgr.getResource() == null )
			{
				// 매니저가 초기화되지 않은 경우
				return _.template( rawTemplate, data );
			}
			else
			{
				// 매니저를 사용해서 랜더링한다.
				return mgr.template( rawTemplate, data );
			}
		};
	};
	
	return NLSManager;
});