/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved. 
 */

// 모듈명을 "i18n!./nls/label.js" 을 지정하면 i18n 모듈에서 리소스를 로딩하지 못한다.
// 개발할때는 아래와 같이 구현하고,
// 패키징시 본 리소스의 이름을 붙여준다.
// 리소스 이름은 모듈에서 종속성 걸어줄 때 사용하는 경로와 같은 것으로 지정한다.
// 템플릿 리소스도 동일한 정책을 적용한다.
define(
{
	"root" : 
	{
		"i18n_postno" : "우편번호",
		"i18n_address" : "주소",
		"i18n_detail" : "상세주소",
		"i18n_desc" : " - 상세 주소 정보를 입력하여 주세요. ",
		"i18n_confirm" : "확인"
    }
});