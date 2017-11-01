/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

UCMS.isURL = function(str)
{
	return /^(https?):\/\/([a-z0-9-]+\.)+[a-z0-9]{2,4}.*$/.test(str);
};

/**
 * 줄바꿈 처리된 문자열의 특수기호와 라인 앞뒤 공백을 제거하여, 한 줄의 문자열로 만든다. 
 */
UCMS.joinTextLines = function(str)
{
	if(str == undefined ) return "";
	str.replace(/\s+/, ""); //왼쪽공백
	str.replace(/\s+$/g, ""); //오른쪽공백
	str.replace(/\n/g, ""); //행바꿈
	str.replace(/\r/g, ""); //엔터
	
	return str;
};

/**
 * 에러를 담은 Promise 객체를 반환한다.
 */
UCMS.retReject = function(err)
{
	var d = $.Deferred();
	UCMS.error(err);
	d.reject(err);
	return d.promise();
};

UCMS.retResolve = function(data)
{
	var d = $.Deferred();
	d.resolve(data);
	return d.promise();
};

/**
 * JSON 객체를 복제한다.
 */
UCMS.copyJSON = function(json)
{
	var copyArray = function(list)
	{
		var copy = [];
		for( var i in list )
		{
			var item = list[i];
			if( item instanceof Array )
			{
				copy.push(copyArray( item ));
			}
			else if( typeof item == 'object' )
			{
				copy.push(UCMS.copyJSON( item ));
			}
			else
			{
				copy.push( item );
			}
		}
		return copy;
	};
	var copy = {};
	for( var attr in json ) 
	{
		if( json.hasOwnProperty(attr) ) 
		{
			if( json[attr] instanceof Array )
			{
				copy[attr] = copyArray( json[attr] );
			}
			else if( typeof json[attr] == 'object' )
			{
				copy[attr] = UCMS.copyJSON( json[attr] );
			}
			else
			{
				copy[attr] = json[attr];
			}
		}
	}
	
	return copy;
};

/**
 * 지정한 함수가 성공을 반환할 때까지 지정한 시간만큼 10ms 간격으로 재시도시한다.
 * @param {function} cbFunc - 수행 함수
 * @param {number} msTimeout - 최대 재시도 시간. ms 단위로 지정. 기본값은 2000ms
 * @param {number} msInterval - 재시도 간격. ms 단위로 지정. 기본값은 10ms
 * @return {$.promise}
 */
UCMS.retry = function(cbFunc, msTimeout, msInterval)
{
	var d = $.Deferred();
	if(typeof cbFunc != "function")
	{
		UCMS.error("UCMS.retry() - invalid parameter.");
		d.reject("invalid parameter.");
	}
	else
	{
		var msElap = 0;
		msTimeout || (msTimeout = 2000);
		msInterval || (msInterval = 10);
		var cbTry = function()
		{
			if( msElap < msTimeout )
			{
				if( cbFunc() == false )
				{
					msElap += msInterval;
					setTimeout(cbTry, msInterval);
				}
				else
				{
					d.resolve();
				}
			}
			else
			{
				UCMS.error("UCMS.retry() - timeout.");
				d.reject("timeout");
			}
		};
		cbTry();
	}
	return d.promise();
};

/**
 * 에러를 보고한다.
 * @param {object} e - 에러 정보를 담은 JSON 객체
 * @param {string} msg - 에러 메시지. 지정하지 않는 경우 표준 메시지가 사용된다.
 */
UCMS.reportError = function(e, msg)
{
	UCMS.error(e);
	if( e && e.resultCode == -2 )
	{
		UCMS.alert("필수항목 데이타가 바르지 않습니다.<br>검증결과 : "+e.msg);
	}
	else
	{
		msg || (msg="요청 처리 중 오류가 발생하였습니다.");
		return UCMS.alert(msg, "ERROR", JSON.stringify(e));
	}
};

/**
 * JSON 데이타를 URL 파라메터 스트링으로 변환한다.
 * @return {string}
 */
UCMS.makeUrlParams = function(options)
{
	return _.compact(_.map(options, function(value, key)
	{
		return value?key+"="+value:null;
	})).join().replace(/,/gi, "&");
};