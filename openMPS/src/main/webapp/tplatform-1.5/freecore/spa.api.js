/**
 * Project : UCMS( Unified Contents Messaging Solution )
 *
 * Copyright (c) 2014 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

define(function()
{
	/**
	 * API 호출을 위한 공통 기능을 제공한다.
	 * 
	 * @param	options		$.ajax() 옵션을 지정한다.
	 */
	function API( options )
	{
		this._status_code = 0;
		this._status_text = "";
		this._ajaxParams = 
		{
			timeout: 30000,
			crossDomain: false
		};
		
		this.setAjaxParams( options );
	};
	
	API.prototype._cbSuccess = function(cbSucceeded, model, resp, options)
	{
		this._status_code = 200;
		this._status_text = "";
		
		if( cbSucceeded )
		{
			cbSucceeded.call( this, model, resp, options );
		}
	};
	
	API.prototype._cbFail = function(cbFailed, model, resp, options)
	{
		this._status_code = resp.status;
		this._status_text = resp.statusText;
		
		UCMSPlatform.log("Status Code : "+this._status_code+", Text : "+this._status_text);
		
		if( cbFailed )
		{
			cbFailed.call( this, model, resp, options );
		}
	};
	
	API.prototype.setAjaxParams = function(params)
	{
		_.extend
		(
			this._ajaxParams,
			params
		);
	};
	
	API.prototype.getStatus = function()
	{
		return { code: this._status_code, text: this._status_text };
	};
	
	/**
	 * model 에 담겨진 정보로 자원을 생성한다.
	 * 
	 * @param url				필수 항목. 생성 API.
	 * @param model				필수 항목. 생성을 위한 자원 정보.
	 * @param cbSucceeded		선택 항목. 생성 성공시 callback 함수. function(model, resp, options) {} 의 형식을 갖는다.
	 * @param cbFailed			선택 항목. 생성 실패시 callback 함수. function(model, resp, options) {} 의 형식을 갖는다
	 * @returns
	 */
	API.prototype.create = function( url, model, cbSucceeded, cbFailed )
	{
		if( !model || !model.save )
		{
			return null;
		}
		
		if( model.isNew() == false )
		{
			return null;
		}
		
		var self = this;
		var options =
		{
			success: function( model, resp, options )
			{
				self._cbSuccess( cbSucceeded, model, resp, options );
			},
			
			error: function( model, resp, options )
			{
				self._cbFail( cbFailed, model, resp, options );
			}
		};
		
		model.urlRoot = url;
		return model.save(null, _.extend(options, this._ajaxParams));
	};
	
	/**
	 * 조회된 자원 정보를 model 에 담는다.
	 * 
	 * @param url				필수 항목. 조회 API.
	 * @param model				필수 항목. 조회된 결과를 담기 위한 모델.
	 * @param cbSucceeded		선택 항목. 조회 성공시 callback 함수. function(model, resp, options) {} 의 형식을 갖는다.
	 * @param cbFailed			선택 항목. 조회 실패시 callback 함수. function(model, resp, options) {} 의 형식을 갖는다
	 * @returns
	 */
	API.prototype.query = function( url, model, cbSucceeded, cbFailed )
	{
		if( !model || !model.fetch )
		{
			return null;
		}
		
		var self = this;
		var options =
		{
			success: function( model, resp, options )
			{
				self._cbSuccess( cbSucceeded, model, resp, options );
			},
			
			error: function( model, resp, options )
			{
				self._cbFail( cbFailed, model, resp, options );
			}
		};

		model.urlRoot = url;
		return model.fetch(_.extend(options, this._ajaxParams));
	};

	/**
	 * model.id 에 지정된 자원의 정보를 갱신한다.
	 * 
	 * @param url
	 * @param model
	 * @param cbSucceeded
	 * @param cbFailed
	 * @returns
	 */
	API.prototype.update = function( url, model, cbSucceeded, cbFailed )
	{
		if( !model || !model.save )
		{
			return null;
		}
		
		if( model.isNew() == true )
		{
			throw new Error("Need to set id of resource!");
		}
		
		var self = this;
		var options =
		{
			success: function( model, resp, options )
			{
				self._cbSuccess( cbSucceeded, model, resp, options );
			},
			
			error: function( model, resp, options )
			{
				self._cbFail( cbFailed, model, resp, options );
			}
		};
		
		model.urlRoot = url;
		return model.save(null, _.extend(options, this._ajaxParams));
	};
	
	/**
	 * model.id 에 지정된 자원을 제거한다.
	 * 
	 * @param url
	 * @param model
	 * @param cbSucceeded
	 * @param cbFailed
	 * @returns
	 */
	API.prototype.remove = function( url, model, cbSucceeded, cbFailed )
	{
		if( !model || !model.destroy )
		{
			return null;
		}
		
		if( model.isNew() == true )
		{
			throw new Error("Need to set id of resource!");
		}		
		
		var self = this;
		var options =
		{
			success: function( model, resp, options )
			{
				self._cbSuccess( cbSucceeded, model, resp, options );
			},
			
			error: function( model, resp, options )
			{
				self._cbFail( cbFailed, model, resp, options );
			}
		};
		
		model.urlRoot = url;
		return model.destroy(_.extend(options, this._ajaxParams));
	};
	
	
	return API;
});