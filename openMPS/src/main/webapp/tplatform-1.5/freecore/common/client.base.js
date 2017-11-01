/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2016 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman 
 */


define(
[
	"BaroProps", "Logger"
]
,
function(BaroProps, Logger)
{
	var ClientBase = Backbone.Model.extend(
	{
		defaults: 
		{
			host: null,
			actors: null,
			session: null,
			user: null,
			params: {}
		}
		,
		/**
		 * Client 모듈 초기화
		 * @param {object} options	클라이언트 모듈이 사용하는 파라메터를 지정한다.
		 * 							params 필드는 API 호출시 ajax 파라메터로 사용된다.
		 */
		initialize: function(options)
		{
			ClientBase.__super__.initialize.apply( this, arguments );
			
			if( options == undefined )
			{
				this.syncUser();
			}

			Logger.debug("ClientBase.initialize()");
		}
		,
		/**
		 * 사용자 정보를 동기화한다.
		 */
		syncUser : function()
		{
			this.set(
			{
				host: BaroProps.getHosts().oauth,
				actors: BaroProps.getActors(),
				baroapp: BaroProps.getAppInfo().id,
				session: BaroProps.getSessionParams(),
				user: BaroProps.getUser()
			});			
		}
		,
		/**
		 * 1.4.3 에서 추가됨
		 * API 요청 기능 통합
		 */
		_get : function(apiPath, data)
		{
			return this._request('GET', apiPath, data);
		}
		,
		/**
		 * 1.4.3 에서 추가됨
		 * API 요청 기능 통합
		 */
		_post : function(apiPath, data)
		{
			return this._request('POST', apiPath, data);
		}
		,
		_put : function(apiPath, data)
		{
			return this._request('PUT', apiPath, data);
		}
		,
		_delete : function(apiPath, data)
		{
			return this._request('DELETE', apiPath, data);
		}
		,
		/**
		 * API 호출을 수행한다.
		 * 기본적으로 cache 옵션을 false 를 지정한다.
		 * 
		 * @param {string} method				호출 방식. GET, POST, DELETE, PUT 중 지정한다.
		 * @param {string} apiPath				호출하는 API 경로
		 * @param {json} 또는 {FormData} data		json 형식 또는 FormData 를 지정한다.
		 * 										파일을 포함하는 전송인 경우 FormData 를 사용한다.	
		 */
		_request : function(method, apiPath, data)
		{
		  	var params = _.extend(
		  	{
		  		type: method,
				url: apiPath,
				cache: false,
				crossDomain: true,
				// 요청하는 데이타 유형을 JSON 로 고정
				dataType: "json",
			    error: function( XHR, textStatus, errorThrown )
			    {
			    	Logger.error("error status: "+textStatus);
			    },
			    success: function(data, textStatus, jqXHR)
			    {
			    	Logger.debug(data);
			    }
			}
		  	, this.get('params'));
		  	var sessionParams = this.get("session");
		  	if( sessionParams != null )
		  	{
		  		params.headers = 
		  		{
					"sp-name": sessionParams.SP_N,
					"sp-key": sessionParams.SP_K
				};
		  		
		  		Logger.debug(params.headers);
		  	}
		  	if( data != undefined )
		  	{
		  		if( method == 'POST' || method == 'PUT' || method == 'DELETE' )
		  		{
		  			if( data instanceof FormData )
		  			{
		  				// 파일을 포함하는 폼 전송인 경우
		  				params.contentType = false;
		  				params.processData = false;
		  				params.data = data;
		  			}
		  			else
		  			{
				  		// 전송하는 데이타 타입을 JSON 로 지정
				  		params.contentType = 'application/json; charset=utf-8';
				  		params.processData = false;
				  		params.data = JSON.stringify(data);
		  			}
		  		}
		  		else
		  		{
		  			params.data = data;
		  		}
		  	}
			
			return $.ajax( params );
		}
	}
	,
	{
		/**
		 * 결과를 $.Promise 객체에 담아서 반환한다.
		 * 
		 * @param result	반환하는 결과값
		 * @param defer		사용중인 Deferred 객체. 존재하지 않는 경우 undefined.
		 * @retrn $.Promise
		 */
		promiseResult : function(result, defer)
		{
			if( typeof defer == "undefined" )
			{
				defer = this.createDeferred();
			}
			
			if( result.resultCode >= 0 )
			{
				defer.resolve(result);
			}
			else
			{
				defer.reject(result);	
			}

			return (typeof defer.promise == "function"
					// jQuery Promise 객체인 경우 defer.promise 은 함수이고,
					? defer.promise()
					// Titanium Promise 객체인 경우 defer.promise 은 객체이다
					: defer.promise);
		},
		
		/*
		 * 운영환경에 맞는 Deferred 객체를 생성한다.
		 */
		createDeferred : function()
		{
			if( typeof $ == "undefined" )
			{
				// for Titanium Mode
				return Q.defer();
			}
			else
			{
				// for WebView Mode
				return $.Deferred();
			}
		}
	});
	
	return ClientBase;
});