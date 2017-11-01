/**
 * Project : UCMS( Unified Contents Messaging Solution )
 *
 * Copyright (c) 2014 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

define([], function()
{
	/**
	 * UCMS 모듈들을 하나의 모듈로 통합한 경우 개별 모듈을 사용하는데 필요한 모듈 팩토리를 구현한다.
	 */
	function ModuleFactory()
	{
		
	};
	
	/**
	 * 지정한 클래스의 인스턴스를 생성한다.
	 * 
	 * @param className		{String} 클래스 이름.
	 * @param params		{Object} 클래스 인스턴스 생성자로 전달되는 파라메터.
	 * @returns				{Promise} 처리 결과를 반환하는 When.promise 객체
	 */
	ModuleFactory.createInstance = function(className, params)
	{
		var d = $.Deferred();
		
		require([ className ]
		, function(_klass)
		{
			d.resolve( new _klass( params ) );
		}
		, function(err)
		{
			d.reject( err )
		});
		
		return d.promise();
	};
	
	/**
	 * 지정한 클래스를 획득한다.
	 * 
	 * @param className		{String} 클래스 이름.
	 * @returns				{Promise} 처리 결과를 반환하는 When.promise 객체
	 */
	ModuleFactory.getClass = function(className)
	{
		var d = $.Deferred();
		
		require([ className ]
		, function(_klass)
		{
			d.resolve( _klass );
		}
		, function(err)
		{
			d.reject( err )
		});
		
		return d.promise();
	};
	
	return ModuleFactory;
});