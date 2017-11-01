/**
 * Project : T-PlatformJS
 *
 * Copyright (c) 2017. FREECORE, Inc. All rights reserved.
 *
 * @author	dbongman
 */

"use strict";

///////////////////////////////////////////////////////////////////////////////
//
// 모듈 로딩 관련
//

/**
 * 지정한 모듈을 페이지로 로딩한다.
 *
 * @param	moduleConfig	Require.js 의 Config 객체.
 *
 * 							예)
 * 							{
 * 								baseUrl: "/rootPath/",
 * 								paths:
 * 								{
 * 									module_name1: "module_path/module_name1",
 * 									module_name2: "module_path/module_name2",
 * 									module_name3: "module_path/module_name3"
 *								}
 * 							}
 * @param	cbOk			로딩 성공시 처리 핸들러.
 * @param	cbError			로딩 실패시 처리 핸들러.
 */
UCMS.loadModuleByConfig = function(moduleConfig, cbOk, cbError)
{
	var		modulePaths = {};
	var		moduleNames = [];

	for(var i in moduleConfig["paths"])
	{
		var		name = "";

		modulePaths[i] = moduleConfig["paths"][i];

		if( modulePaths[i].indexOf(".html") > -1 )
		{
			// html 리소스인 경우 text.js 를 통해서 로딩.
			name += "text!";
		}

		name += i;

		moduleNames.push( name );
	}

	// 모듈 종속성 관리자 환경 설정
	require["config"](
	{
	    "baseUrl": moduleConfig["baseUrl"] || "/",
	    "paths": modulePaths
	});

	require
	(
		moduleNames
		,
		cbOk ||
		function()
		{
			UCMS.log("Complete to load");
		}
		,
		cbError ||
		function(err)
		{
			UCMS.error("Error to load: "+err);
		}
	);
};

/**
 * 지정된 모듈을 로딩하고, 모듈 사용을 간소화하는 Module Factory 를 반환한다.
 *
 * @param pathArray		로딩할 모듈의 경로. 하나의 모듈만 지정할 수 있다.
 * @param cbOk			모듈 팩토리 반환 핸들러. function(factory){}
 * @param cbError		팩토리 로딩중 오류 반환 핸들러. 필요없는 경우 생략할 수 있다. function(err){}
 * @returns
 * 		호출 성공시 true, 실패시 false.
 */
UCMS.loadModuleFactoryByPath = function(modulePath, cbOk, cbError)
{
	UCMS.log("Begin loadModuleFactoryByPath()");

	if( modulePath instanceof Array || typeof cbOk == "undefined" )
	{
		UCMS.warn("End loadModuleFactoryByPath() with error.");
		return false;
	}

	require
	(
		[ modulePath ]
		,
		function()
		{
			require(["spa_modfactory"]
			, function(mf)
			{
				UCMS.log("Complete to load");
				cbOk( mf );
			}
			, function(err)
			{
				cbOk();
			});
		},
		cbError ||
		function(err)
		{
			UCMS.error("Error to load: "+err);
		}
	);

	UCMS.log("End loadModuleFactoryByPath()");

	return true;
};

/**
 * 지정한 모듈을 페이지로 로딩한다.
 *
 * 예제)
 * 		UCMS.loadModuleByPath(["/lib/json2.js", "/lib/text.js", ]);
 *
 * @param	pathArray	로딩이 필요한 모듈의 배열.
 * @param	cbOk		로딩 성공시 처리 핸들러. function(){}
 * @param	cbError		로딩 실패시 처리 핸들러. function(err){}
 * @return	{ $.Promise }
 */
UCMS.loadModuleByPath = function(pathArray, cbOk, cbError)
{
	UCMS.log("Begin loadModuleByPath()");

	var d = $.Deferred();
	var returnError = function(err, cbError)
	{
		UCMS.error("Error to load: "+err);
		if( typeof cbError == "function" )
		{
			cbError.apply(undefined, arguments);
		}
		d.reject(err);
	}

	require
	(
		pathArray
		,
		function()
		{
			UCMS.log("Complete to load");

			var idx=0;
			for(var path in pathArray)
			{
				var module = pathArray[path];
				if(! module )
				{
					returnError('Invalid module path : '+pathArray[path], cbError);
					return d.promise();
				}
				var pos = module.search("[.][A-Za-z]");
				if( pos > 0 && typeof arguments[idx] == "object" )
				{
					var subModule = module.substr(pos+1);

					if( arguments[idx][ subModule ] )
					{
						// 서브 객체를 선택한다.
						arguments[idx] = arguments[idx][ subModule ];
					}
				}

				++idx;
			}

			if( typeof cbOk == "function" )
			{
				cbOk.apply(undefined, arguments);
			}
			d.resolve.apply(d, arguments);
		},
		function(err)
		{
			returnError(err, cbError);
		}
	);

	UCMS.log("End loadModuleByPath()");

	return d.promise();
};

/**
 * 제공된 Manifest 에 담겨진 모든 모듈을 로딩한다.
 * 로딩시 모듈의 template 가 존재한다면 자동으로 초기화된다.
 *
 * @param manifestPath {string} - 위젯 메니페스트 경로. 또는 require.js 에 제공한 모듈 식별자.
 * @returns {$.promise} -	로딩된 모듈들의 객체. 다음의 구조를 갖는다.
 * 							해당 리소스가 존재하지 않으면 해당 필드는 존재하지 않음.
 * 							{
 * 								resource:
 * 								{
 * 									template:{string},	위젯 템플릿
 * 									nls:{object}		require.js config 에 설정된 locale 이 반영된 i18n 문자열.
 * 														config.i18n.locale 을 참조하거나,
 * 														브라우저 navigator.language or navigator.userLanguage 에 의한 문자열이 로딩된다.
 * 								},
 * 								widget:{function}, 		위젯 모듈
 * 								manager:{function}, 	메니저 모듈. 위젯 파라메터를 생성한다.
 * 								manifest:{object}		지정한 메니페이스 설정
 * 							}
 */
UCMS.loadModuleByManifest = function(manifestPath)
{
	var d = $.Deferred();

	require
	(
		[ 'manifest!'+manifestPath ]
		,
		function(Module)
		{
			d.resolve(Module);
		}
		,
		function(e)
		{
			d.reject(e);
		}
	);

	return d.promise();
};

/**
 * Manifest 기반으로 운용되는 모듈을 제공된 파라메터로 생성한다.
 * i18n 리소스가 존재한다면 모듈 생성시 초기화 파라메터에 "resource" 필드가 함께 제공된다.
 * Manifest 설정이 없는 모듈은 loadModuleByPath() 를 사용하여 로드한다.
 *
 * @param {string} manifestPath - 생성을 원하는 모듈의 Manifest 경로 or 모듈 식별자
 * @param {object} params - 모듈 생성시 전달되는 모듈 파라메터 객체
 * @returns {$.promise} function(instance){} 모듈의 인스턴스를 수신할 수 있다.
 */
UCMS.createModuleByManifest = function(manifestPath, params)
{
	return UCMS.loadModuleByManifest(manifestPath)
	.then(function(Manifest)
	{
		return new Manifest.widget
		(
			_.extend({resource: Manifest.resource||{}}, params)
		);
	});
};

/**
 * 지정한 패널 리소스를 로딩한다.
 * 로딩된 리소스는 DOM 영역에 캐싱되어 재사용된다.
 *
 * @param {string} resPath - json 형식의 리소스 경로
 * @returns {jqXHR} 성공시 { module:{string}, options:{object} } 형식의 리소스를 반환한다.
 */
UCMS.loadBoxResource = function(resPath)
{
	//return UCMS.loadModuleByPath(["text!"+resPath])
	return $.ajax({
		url: resPath,
		dataType: "json"
	})
	.then(function(res)
	{
		if( typeof res == "string" )
		{
			return JSON.parse(res);
		}
		else
		{
			return res;
		}
	});
};

///////////////////////////////////////////////////////////////////////////////
//
// 레이어 팝업
//

/**
 *  로딩바를 출력한다.
 * @param parent_selector jquery selector 를 사용한다.
 *
 * @return { Boolean } 출력 성공시 true, 이미 존재하는 경우 false.
 */
UCMS.showLoading = function(parent_selector)
{
	if( parent_selector )
	{
		if( typeof parent_selector.length !== 'number' )
		{
			parent_selector = $(parent_selector);
		}
		//
		if( parent_selector.find(".loading_box").length == 0 )
		{
			parent_selector.append("<div class='loading_box'><div class='loading_img'></div></div>");
		}

		return true;
	}
	else if($ && $(".loading_box").length == 0)
	{
		$("body").append("<div class='loading_box'><div class='loading_img'></div></div>");

		return true;
	}
	else
	{
		return false;
	}
};

/**
 *  로딩바 제거
 */
UCMS.hideLoading = function(parent_selector)
{
	if( parent_selector )
	{
		if( typeof parent_selector.length !== 'number' )
		{
			parent_selector = $(parent_selector);
		}
		//
		parent_selector.find(".loading_box").remove();
	}
	else
	{
		$("body > .loading_box").remove();
	}
};

/**
 * 알림창을 출력한다.
 *
 * @param text		{ String } 알림 메시지
 * @param title		{ String } 메시지 타이틀. 필요치 않은 경우 지정하지 않는다.
 * @param errMgs    { String } 에러메시지. 필요치 않은 경우 지정하지 않는다.
 * @return { Promise } 재진입인 경우 즉시 reject() 된다.
 */
UCMS.alert = function(text, title, errMgs)
{
	var d = $.Deferred();

	if($("#alert_box").length == 0)
	{
    	var alert_str = '';

    	alert_str += '<div id="alert_box" class="modal_box">';
    	alert_str += '<div class="alert_box">';
    	alert_str += '	<div class="alert_text">';
    	alert_str += '		<i class="fa fa-info-circle color_2nd fa-3x"></i>';
    	alert_str += '		<div class="content"> ' +text;
		if(errMgs){
		alert_str += '		<textarea class="form-control" style="height:60px"> ' + errMgs + '</textarea>';
		}
		alert_str += '		</div>';
    	alert_str += '	</div>';
    	alert_str += '	<div class="alert_btn_box">';
    	alert_str += '		<button type="button" class="btn btn_go_confirm btn-lg btn-primary btn-block"><i class="fa fa-check color_2nd"></i> 확인</button>';
    	alert_str += '	</div>';
    	alert_str += '</div></div>';

    	$("body").append(alert_str);
    	$(".alert_btn_box button.btn_go_confirm").click(function()
    	{
    		$("#alert_box").remove();
    		d.resolve();
    	})
    	.focus();
	}
	else
	{
		// 활성화된 상태에서 재진입인 경우.
		d.reject();
	}

	return d.promise();
};

/**
 * 확인창을 출력한다.
 *
 * @param text			{ String } 확인 메시지
 * @param btnLabel		{ confirm: "", cancel: "" }
 * 						confirm	{ String } 확인 버튼의 레이블. 지정하지 않은 경우 "확인" 사용
 * 						cancel	{ String } 취소 버튼의 레이블. 지정하지 않은 경우 "취소" 사용
 * @return				{ Promise } 재진입인 경우 즉시 reject() 된다.
 * 						"확인"한 경우 resolve(), "취소"인 경우 reject() 된다.
 */
UCMS.confirm = function(text, btnLabel)
{
	var d = $.Deferred();

	btnLabel = btnLabel || {};
	var confirmLabel = btnLabel.confirm || "확인";
	var cancelLabel = btnLabel.cancel || "취소";

	if($("#confirm_box").length == 0)
	{
		var alert_str = '';
		alert_str += '<div id="confirm_box" class="modal_box">';
		alert_str += '<div class="alert_box">';
		alert_str += '	<div class="alert_text">';
		alert_str += '		<i class="fa fa-info-circle color_2nd fa-3x"></i>';
		alert_str += '		<div class="content"> ' +text+ '</div>';
		alert_str += '	</div>';
		alert_str += '	<div class="alert_btn_box">';
		alert_str += '		<button type="button" class="btn btn-lg btn_go_cofirm btn-primary w40p"><i class="fa fa-check color_2nd"></i> '+confirmLabel+'</button>';
		alert_str += '		<button type="button" class="btn btn-lg btn_go_cancel btn-primary w40p ml10"><i class="fa fa-close"></i> '+cancelLabel+'</button>';
		alert_str += '	</div>';
    	alert_str += '</div></div>';

		$("body").append(alert_str);

		$(".alert_btn_box button.btn_go_cofirm").click(function()
    	{
    		$("#confirm_box").remove();
    		d.resolve();
    	})
    	.focus();

		$(".alert_btn_box button.btn_go_cancel").click(function()
    	{
    		$("#confirm_box").remove();
    		d.reject();
    	});
	}
	else
	{
		// 활성화된 상태에서 재진입인 경우.
		d.reject();
	}

	return d.promise();
};

/**
 * 메시지를 잠시 동안 출력한다.
 *
 * @param text 			{string} 출력 메시지
 * @param secExpire		{number} 메시지가 출력되는 시간. 초 단위로 지정한다.
 * 						최대 10초까지 지정 가능하며, 생략하는 경우 hidePrompt() 가 호출할때 까지 메시지가 출력된다.
 * @param countdown		{boolean} true 인 경우 카운트다운을 출력한다.
 * @returns {$.promise} 메시지가 닫히면 성공을 반환한다. 중복호출된 경우 실패가 반환된다.
 */
UCMS.showPrompt = function(text, secExpire, countdown)
{
	var d = $.Deferred();

	if($("#prompt_box").length == 0)
	{
    	var alert_str = '';

    	alert_str += '<div id="prompt_box" class="modal_box">';
    	alert_str += '<div class="prompt_box">';
    	alert_str += '	<div class="prompt_text">';
    	alert_str += '		<i class="fa fa-refresh fa-spin fa-fw"></i>';
    	alert_str += '		<div class="content"> ' +text+ '</div><br>';
    	alert_str += '      <div class="text-center"></div>';
    	alert_str += '	</div>';
    	alert_str += '</div></div>';

    	$("body").append(alert_str);
    	var $captionArea = $("body #prompt_box .content");
    	var checker = function()
    	{
    		if( typeof secExpire == 'number' )
    		{
    			if( secExpire-- <= 1 )
    			{
    				$("#prompt_box").remove();
	    			d.resolve();
	    			return;
    			}

    			if( countdown == true )
    			{
    				$captionArea.html(text+"<br>remain : "+secExpire+" sec");
    			}

    			setTimeout(checker, 1000);
    		}
    		else
    		{
    			// hidePrompt() 가 호출될 때까지 출력함
    		}
    	};

    	setTimeout(checker, 1000);
	}
	else
	{
		// 활성화된 상태에서 재진입인 경우.
		d.reject();
	}

	return d.promise();
}

/**
 * 프롬프트를 닫는다.
 */
UCMS.hidePrompt = function()
{
	var $box = $("#prompt_box");

	if($box.length > 0)
	{
		$box.remove();
	}
}

///////////////////////////////////////////////////////////////////////////////
//
// 기타
//

/**
 * 앱의 URL 파라메터를 분석하여 JSON 형식의 값을 생성한다.
 *
 * @returns {object}
 */
UCMS.getUrlParams = function()
{
	var url = decodeURIComponent( window.location.href );
	var vars = {}, hash;
	var hashes = url.slice(url.indexOf('?') + 1).split('&');

	for(var i = 0; i < hashes.length; i++)
	{
		hash = hashes[i].split('=');
		vars[hash[0]] = hash[1];
	}

	return vars;
};

/**
 * Application 홈으로 리다이렉트한다.
 */
UCMS.redirectHome = function()
{
	window.location = UCMS.getPlatformPath();
};

/**
 * 지정한 페이지를 다시 로딩한다. 동일 페이지도 강제적으로 로딩한다.
 * 페이지 로딩이 완료되어도 브라우저의 URL 은 바뀌지 않는다.
 * 페이지 주소가 변경되기를 원하는 경우에는 window.location 를 사용한다.
 * window.location 은 동일 페이지인 경우 다시 로딩되지 않는 차이점이 있다.
 *
 * @param pageId	{ String } Router 에서 처리되는 페이지 식별자.
 */
UCMS.reloadPage = function(pageId)
{
	UCMS.debug("reloadPage() - pageId : "+pageId);
	Backbone.history.loadUrl( pageId );
};

/**
 * 현재 페이지를 리로딩한다. 강제적으로 로딩한다.
 */
UCMS.reloadCurPage = function()
{
	UCMS.reloadPage( Backbone.history.getFragment() );
};

/**
 * 제공된 html 리소스를 HEAD 태그 내에 추가한다.
 *
 * @param {string} html - 초기화 필요한 리소스 스트링.
 * 						<script type="text/html"></script> 또는 <style type="text/css"></stype> 으로 구성된다.
 * @param {string} selector - 초기화되는 리소스의 jquery selector
 * @return {boolean} 로딩 성공 true, 이미 로딩된 모듈인 경우 false.
 */
UCMS.initResource = function(html, selector)
{
	if( selector && $(selector).length > 0 )
	{
		// 이미 초기화된 리소스
		return false;
	}

	$("head").append( html );
	return true;
};

/**
 * CSS 리소스 링크를 동적으로 적용한다.
 * @param {string} stylePath - css 경로
 * @return {boolean} 로딩 성공 true, 이미 로딩된 css 인 경우 false.
 */
UCMS.initStyle = function(stylePath)
{
	if( $("link[href='"+stylePath+"']").length > 0 )
	{
		// 이미 초기화된 리소스
		return false;
	}

	var cssLink = $("<link rel='stylesheet' type='text/css' href='"+stylePath+"'>");
	$("head").append(cssLink);
	return true;
};

/**
 * Javascript 리소스 링크를 동적으로 적용한다.
 * @param {string} scriptPath - js 모듈 경로
 * @return {boolean} 로딩 성공 true, 이미 로딩된 모듈인 경우 false.
 */
UCMS.initScript = function(scriptPath)
{
	if( $("script[src='"+scriptPath+"']").length > 0 )
	{
		// 이미 초기화된 리소스
		return false;
	}

	var jsLink = $("<script type='text/javascript' src='"+scriptPath+"'></script>");
	$("head").append(jsLink);
	return true;
};

/**
 * AppProps 에 저장된 Hosts 정보를 템플릿 스트링에 적용한다.
 */
UCMS.applyHostPath = function(templateString)
{
	var	hosts = UCMS.SPA.AppMain.getAppProps().getProp("hosts");

	return _.template( templateString, hosts );
};

/**
 * View 의 높이를 window 객체의 높이에 맞춘다.
 * @param $view	view 를 담고 있는 jquery $ 객체
 */
UCMS.adjustViewHeight = function($view)
{
	var heightPX = window.innerHeight + "px";
	$view.css("height", heightPX);

	UCMS.debug("Adjust a view height : "+heightPX);
};

/**
 * HTML 노드를 선택한다.
 *
 * @param selector	{ String } 선택할 노드의 id 또는 className 또는 TagName 을 지정한다.
 * @return id 를 지정한 경우 하나의 element 노드, 그 외의 경우 선택된 element 노드의 배열이 반환됨
 */
UCMS.selectElement = function(selector)
{
	var ele;

	switch( selector.charAt(0) )
	{
	case '#':
		selector = selector.substr(1);
		ele = document.getElementById( selector );
		break;

	case '.':
		selector = selector.substr(1);
		ele = document.getElementsByClassName( selector );
		break;

	default:
		ele = document.getElementsByTagName( selector );
		break;
	}

	return ele;
};

/**
 * 모바일에서 가상 키보드 사용시 fixed 항목이 깨지는 문제를 해결한다.
 * Body 항목에 absolute 속성 적용
 *
 * @param targetTagName		{ String } Fixed 조건이 필요한 대상 태그 이름
 */
UCMS.initFixedHandler = function(targetTagName)
{
	var $body = $('body');

	$(document)
    .on('focus', targetTagName, function()
    {
        $body.addClass('fixfixed');
    })
    .on('blur', targetTagName, function()
    {
        $body.removeClass('fixfixed');
    });
};

/**
 * 윈도우 콘텐츠의 상단으로 스크롤한다.
 *
 * @param top	{ Number } 스크롤 시킬 y 좌료를 지정한다. 생략하는 경위 최상단으로 이동된다.
 * 				특정 노드가 화면의 최상단으로 옮겨지기 위해서는 본 파라메터에 해당 노드의 offset.top 값을 지정한다.
 * 				$(최상단에 노출된 노드의 selector).offset().top 값을 지정한다.
 * @param dur	{ Number } 스크롤 진행시간. 밀리초 단위로 지정하며, 생략하는 경우 500 로 서정됨
 */
UCMS.scrollTop = function(top, dur)
{
	UCMS.debug("Scroll to Top : "+window.scrollY);

	if( window.scrollY > 0 )
	{
		$("body").animate({scrollTop: top || 0}, dur || 500);
	}
};

UCMS.getWindowWidth = function()
{
	return( window.innerWidth
			|| document.documentElement.clientWidth
			|| document.body.clientWidth );
};

UCMS.getWindowHeight = function()
{
	return( window.innerHeight
			|| document.documentElement.clientHeight
			|| document.body.clientHeight );
};

/**
 * 재공되는 문자열에서 <br> 를 \n 로 변경한다.
 */
UCMS.removeTagBR = function(str)
{
	if(str == undefined ) return "";
    return str.replace(/<\/br>/gm,'\n');
};

/**
 * 지정한 요소가 화면에 보이도록 스크롤한다.
 * @param {$} $elem - 스크롤 대상이 되는 요소의 $ 객체
 * @param {number} dur - 진행시간. ms 단위로 지정.
 */
UCMS.elemScroll = function($elem, dur)
{
	var top = $elem.offset().top;
	$("body").animate({scrollTop : top}, dur || 400);
};

/**
 * IE 인지 확인한다. IE 11 확인 기능 포함
 */
UCMS.isIE = function()
{
	var agent = navigator.userAgent.toLowerCase();
	return ( (navigator.appName == 'Netscape' && agent.indexOf('trident') != -1) || (agent.indexOf("msie") != -1));	
};

/**
 * IE 버전을 얻는다.
 * @return {number} IE 가 아닌경우 -1 반환
 */
UCMS.getIEVer = function()
{
    var rv = -1; // Return value assumes failure.    
    if( navigator.appName == 'Microsoft Internet Explorer' )
    {
    	var ua = navigator.userAgent;
    	var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
    	if (re.exec(ua) != null)
    	{
    		rv = parseFloat(RegExp.$1);    
    	}
    }    
    return rv; 
};

/**
 * 100 단위 구분자 추가
 * @return {string}
 */
UCMS.numberWithCommas = function(num) 
{
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
};

/**
 * 정수인지 확인한다.
 * @return {boolean}
 */
UCMS.isInteger = function(num)
{
	return /^[0-9]+$/.test(num);
};

/**
 * 정수 또는 실수인지 확인한다.
 * @return {boolean}
 */
UCMS.isNumber = function(num)
{
	return num!="." ? /^[+-]?\d*(\.?\d*)$/.test(num) : false;
};

/**
 * 숫자값으로 변환한다.
 * 정수, 실수인 경우는 숫자값으로 반환한다.
 * @return {number} 숫자가 아닌경우 0 반환
 */
UCMS.parseNumber = function(num)
{
	return UCMS.isNumber(num)
			? num
			: 0;
}