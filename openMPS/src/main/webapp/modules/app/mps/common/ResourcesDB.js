/**
 * Project : OpenMPS MIS
 */

define
(
[ "/common/ResourcesDB.js" ],
function(GlobalRepo)
{
	/*
	 * 모듈 식별자 네이밍 규칙
	 *
	 * 1. "모듈이름-버전" 과 같은 형식으로 지정한다.
	 * 2. 모듈 이름과 모듈 디렉토리 이름은 같은 이름으로 지정하며,
	 * 3. 디렉토리 이름은 모두 소문자로 지정하다.
	 * 4. 기본적으로 파스칼 표기법(PascalCase)과 카멜 표기법(camelCase)을 적용하는 것을 원칙으로 하며,
	 *    상세한 위젯 네이밍 규칙은 위젯 개발사의 정책을 따른다.
	 */

	var widgetRepo =
	{
		"SignBox-1.0.0" : {
			"ver":0x01000000,
			"desc":'로그인 페이지 위젯',
			"thumbnail":null,
			"detail": "/app/mps/widgets/signbox"
		}
		,
		"MainBox-1.0.0" : {
			"ver":0x01000000,
			"desc":'메인 페이지 위젯.',
			"thumbnail":null,
			"detail": "/app/mps/widgets/mainbox"
		}
		,
		"MainBoxMobile-1.0.0" : {
			"ver":0x01000000,
			"desc":'메인 페이지 모바일 위젯.',
			"thumbnail":null,
			"detail": "/app/mps_mobile/widgets/mainboxmobile"
		}
		,
		"FavHeader-1.0.0" : {
			"ver":0x01000000,
			"desc":'즐겨찾기 기능을 갖는 헤더 위젯',
			"thumbnail":null,
			"detail": "/app/mps/widgets/favheader"
		}
		,
		"MobileHeader-1.0.0" : {
			"ver":0x01000000,
			"desc":'모바일 전용 헤더',
			"thumbnail":null,
			"detail": "/app/mps_mobile/widgets/mobileheader"
		}
		,
		"MainHome-1.0.0" : {
			"ver":0x01000000,
			"desc":'메인의 기본 페이지 위젯',
			"thumbnail":null,
			"detail": "/app/mps/widgets/mainhome"
		}
		,
		"MobileHome-1.0.0" : {
			"ver":0x01000000,
			"desc":'메인의 모바일 기본 페이지 위젯',
			"thumbnail":null,
			"detail": "/app/mps_mobile/widgets/mobilehome"
		}		,
		"BoxRenderer-1.0.0" : {
			"ver":0x01000000,
			"desc":'Box 리소스를 출력한다.',
			"thumbnail":null,
			"detail": "/app/mps/widgets/boxrenderer"
		}
	};

	var formWidgetRepo =
	{
		"CodeSearch-1.0.0" : {
			"ver":0x01000000,
			"desc":'코드 찾기 위젯.',
			"thumbnail":null,
			"detail": "/app/mps/widgets/codesearch"
		},
	};

	/**
	 * 앱 리소스 정보에서 Module Map 을 생성한다.
	 *
	 * @param {object}	앱 리소스 목록
	 */
	function createResourceMap(repoList)
	{
		var map = {};

		for(var rid in repoList)
		{
			if( repoList[rid].detail )
			{
				map[rid] = repoList[rid].detail;
			}
		}

		return map;
	}

	var panelRepo =
	{
		// TOBE 2017.07.18
		//코드서치 위젯용 파라메
		"codesearch" : {
			"ver":0x01000000,
			"desc":'codesearch : 코드서치용 파라메터',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/codesearch.json'
		},
		"JQGrid4-Paging" : {
			"ver":0x01000000,
			"desc":'JQGrid4 : 페이징 테스트',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/test/jqgrid4paging.json'
		},
		// 공툥
		// 공툥 / 코드
		"TMCOCD10" : {
			"ver":0x01000000,
			"desc":'TMTOCD10 : openMPS /공통/코드/공통코드관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOCD10.json'
		},
		"TMCOCD20" : {
			"ver":0x01000000,
			"desc":'TMTOCD20 : openMPS /공통/코드/라벨관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOCD20.json'
		},
		"TMCOCD30" : {
			"ver":0x01000000,
			"desc":'TMTOC30 : openMPS /공통/코드/메시지관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOCD30.json'
		},
		// 공툥
		// 공툥  / 관리
		"TMCOMT40" : {
			"ver":0x01000000,
			"desc":'TMCOMT40 : openMPS /공통/관리/API 로그관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOMT40.json'
		},
		// SMS/FAX 로그 관리
		"TMCOMT50" : {
			"ver":0x01000000,
			"desc":'TMCOMT50 : openMPS /공통/관리/SMS/FAX 로그관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOMT50.json'
		},
		// FAX 보내기
		"TMCOMT60" : {
			"ver":0x01000000,
			"desc":'TMCOMT60 : openMPS /공통/관리/FAX 보내기',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOMT60.json'
		},
		// FAX 주소록 팝업
		"TMCOMT60_pop" : {
			"ver":0x01000000,
			"desc":'TMCOMT60_popup : openMPS /공통/관리/주소록 팝업',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOMT60_pop.json'
		},

		// 공툥
		// 공툥 / 사용자
		"TMCOMT10" : {
			"ver":0x01000000,
			"desc":'TMCOMT10 : openMPS /공통/관리/프로그램(메뉴)관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOMT10.json'
		},
		"TMCOUR40" : {
			"ver":0x01000000,
			"desc":'TMCOUR40 : openMPS /공통/코드/권한관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOUR40.json'
		},
		"TMCOUR50" : {
			"ver":0x01000000,
			"desc":'TMCOUR50 : openMPS /공통/코드/그룹별 메뉴관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOUR50.json'
		},
		"TMCOUR60" : {
			"ver":0x01000000,
			"desc":'TMCOUR60 : openMPS /공통/코드/그룹별 사용자 관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOUR60.json'
		},
		//영업
		//기준정보
		"SD0101" : {
			"ver":0x01000000,
			"desc":'SD0101 : openMPS /영업/주문관리/거래처 정보',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0101.json'
		},
		"SD0102" : {
			"ver":0x01000000,
			"desc":'SD0102 : openMPS /영업/주문관리/거래처 제품',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0102.json'
		},
		"SD0103" : {
			"ver":0x01000000,
			"desc":'SD0103 : openMPS /영업/주문관리/사원별 제품목록',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0103.json'
		},
		//단가관리
		"SD0201" : {
			"ver":0x01000000,
			"desc":'SD0201 : openMPS /영업/단가관리/표준단가 목록',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0201.json'
		},
		"SD0202" : {
			"ver":0x01000000,
			"desc":'SD0202 : openMPS /영업/단가관리/표준단가 입력',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0202.json'
		},
		"SD0203" : {
			"ver":0x01000000,
			"desc":'SD0203 : openMPS /영업/단가관리/판매단가',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0203.json'
		},
		"SD0203_pop_1" : {
			"ver":0x01000000,
			"desc":'SD0203 : openMPS /영업/단가관리/판매단가 - 팩스전송',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0203_pop_1.json'
		},
		"SD0203_pop_2" : {
			"ver":0x01000000,
			"desc":'SD0203 : openMPS /영업/단가관리/판매단가 - 메일전송',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0203_pop_2.json'
		},
		"SD0204" : {
			"ver":0x01000000,
			"desc":'SD0204 : openMPS /영업/단가관리/거래처단가등급',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0204.json'
		},
		"SD0205" : {
			"ver":0x01000000,
			"desc":'SD0205 : openMPS /영업/단가관리/할인단가 입력',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0205.json'
		},
		"SD0206" : {
			"ver":0x01000000,
			"desc":'SD0206 : openMPS /영업/단가관리/판매단가 승인',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0206.json'
		},
		"SD0207" : {
			"ver":0x01000000,
			"desc":'SD0207 : openMPS /영업/단가관리/할인단가 승인',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0207.json'
		},
		//목표관리
		"SD0301" : {
			"ver":0x01000000,
			"desc":'SD0301 : openMPS /영업/목표관리/년간목표_영업사원',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0301.json'
		},
		"SD0302" : {
			"ver":0x01000000,
			"desc":'SD0302 : openMPS /영업/목표관리/년간목표_거래처',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0302.json'
		},
		"SD0303" : {
			"ver":0x01000000,
			"desc":'SD0303 : openMPS /영업/목표관리/월별 목표현황_영업파트',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0303.json'
		},
		"SD0304" : {
			"ver":0x01000000,
			"desc":'SD0304 : openMPS /영업/목표관리/월별 목표현황_영업사원',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0304.json'
		},
		"SD0305" : {
			"ver":0x01000000,
			"desc":'SD0305 : openMPS /영업/목표관리/월별 목표현황_거래처',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0305.json'
		},
		//주문입력
		"SD0401" : {
			"ver":0x01000000,
			"desc":'SD0401 : openMPS /영업/주문관리/주문입력',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0401.json'
		},
		"SD1001" : {
			"ver":0x01000000,
			"desc":'SD0401 : openMPS /영업/주문관리/주문입력-고객포털',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD1001.json'
		},
		"SD0402" : {
			"ver":0x01000000,
			"desc":'SD0402 : openMPS /영업/주문관리/출고승인요청',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0402.json'
		},
		"SD0403" : {
			"ver":0x01000000,
			"desc":'SD0403 : openMPS /영업/주문관리/출고승인',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0403.json'
		},
		"SD0404" : {
			"ver":0x01000000,
			"desc":'SD0404 : openMPS /영업/주문관리/PM주문입력',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0404.json'
		},
		"SD0405" : {
			"ver":0x01000000,
			"desc":'SD0405 : openMPS /영업/주문관리/온라인주문입력',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0405.json'
		},
		"SD0406" : {
			"ver":0x01000000,
			"desc":'SD0406 : openMPS /영업/주문관리/주문현황',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0406.json'
		},
		"SD0407" : {
			"ver":0x01000000,
			"desc":'SD0407 : openMPS /영업/주문관리/온라인주문매핑',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0407.json'
		},
		"SD0501" : {
			"ver":0x01000000,
			"desc":'SD0501 : openMPS /영업/수금관리/수금입력',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0501.json'
		},
		"SD0502" : {
			"ver":0x01000000,
			"desc":'SD0502 : openMPS /영업/수금관리/수금현황',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0502.json'
		},
		"SD0503" : {
			"ver":0x01000000,
			"desc":'SD0503 : openMPS /영업/수금관리/미수현황 영업파트',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0503.json'
		},

		"SD0504" : {
			"ver":0x01000000,
			"desc":'SD0504 : openMPS /영업/수금관리/미수현황 유통본부',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0504.json'
		},

		"SD0505" : {
			"ver":0x01000000,
			"desc":'SD0505 : openMPS /영업/수금관리/미수현황 거래처',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0505.json'
		},

		"SD0506" : {
			"ver":0x01000000,
			"desc":'SD0503 > SD0506 : openMPS /영업/수금관리/잔액조회서 ',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0506.json'
		},
		//실적관리
		"SD0601" : {
			"ver":0x01000000,
			"desc":'SD0601 : openMPS /영업/실적관리/거래 원장',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0601.json'
		},
		"SD0603" : {
			"ver":0x01000000,
			"desc":'SD0603 : openMPS /영업/실적관리/매출현황_유통경로',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0603.json'
		},
		"SD0604" : {
			"ver":0x01000000,
			"desc":'SD0603 : openMPS /영업/실적관리/매출현황_유통경로',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0604.json'
		},
		"SD0605" : {
			"ver":0x01000000,
			"desc":'SD0605 : openMPS /영업/실적관리/영업현황_영업파트',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0605.json'
		},
		/*
		"SD0606" : {
			"ver":0x01000000,
			"desc":'SD0606 : openMPS /영업/실적관리/판매현황_영업파트',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0606.json'
		},
		"SD0608" : {
			"ver":0x01000000,
			"desc":'SD0608 : openMPS /영업/실적관리/거래처별 매출현황',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/SD0608.json'
		},
*/
//실적관리
"SD0601" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0601.json',"desc":' : openMPS /영업/거래원장'},
"SD0602" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0602.json',"desc":' : openMPS /영업/매출장'},
"SD0603" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0603.json',"desc":' : openMPS /영업/매출현황_유통경로'},
"SD0604" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0604.json',"desc":' : openMPS /영업/매출현황_유통본부'},
"SD0605" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0605.json',"desc":' : openMPS /영업/매출현황_영업파트'},
"SD0606" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0606.json',"desc":' : openMPS /영업/판매추이_누계'},
"SD0607" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0607.json',"desc":' : openMPS /영업/판매추이_월별'},
"SD0608" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0608.json',"desc":' : openMPS /영업/반품현황'},
"SD0609" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0609.json',"desc":' : openMPS /영업/제품매출_영업파트'},
"SD0610" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0610.json',"desc":' : openMPS /영업/제품매출_거래처'},

//수금관리
"SD0611" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0611.json',"desc":' : openMPS /영업/매출현황_거래처'},
"SD0612" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0612.json',"desc":' : openMPS /영업/매출현황_신규거래처'},

//영업관리
"SD0801" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0801.json',"desc":' : openMPS /영업/마감일자'},
"SD0802" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0802.json',"desc":' : openMPS /영업/출고제한'},
"SD0803" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0803.json',"desc":' : openMPS /영업/단가수정'},
"SD0804" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0804.json',"desc":' : openMPS /영업/전표이월'},
"SD0805" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0805.json',"desc":' : openMPS /영업/공지사항'},
"SD0806" : {"ver":0x01000000,"thumbnail":null,"detail": '/app/mps/repo/box/SD0806.json',"desc":' : openMPS /영업/전표이월'},
		//생산
		//기준정보관리
		"PP0101" : {
			"ver":0x01000000,
			"desc":'PP0101 : openMPS /생산/기준정보관리/공장등록',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0101.json'
		},
		"PP0102" : {
			"ver":0x01000000,
			"desc":'PP0102 : openMPS /생산/기준정보관리/농장등록',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0102.json'
		},
		"PP0103" : {
			"ver":0x01000000,
			"desc":'PP0103 : openMPS /생산/기준정보관리/제품코드등록',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0103.json'
		},
		"PP0104" : {
			"ver":0x01000000,
			"desc":'PP0103 : openMPS /생산/기준정보관리/세트등록',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0104.json'
		},
		"PP0105" : {
			"ver":0x01000000,
			"desc":'PP0105 : openMPS /생산/기준정보관리/BOM등록',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0105.json'
		},
		"PP0105_pop_01" : {
			"ver":0x01000000,
			"desc":'PP0105_pop_01 : openMPS /생산/기준정보관리/BOM등록_팝업 불러오기',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0105_pop_01.json'
		},
		"PP0105_pop_02" : {
			"ver":0x01000000,
			"desc":'PP0105_pop_02 : openMPS /생산/기준정보관리/BOM 코드 조회 팝업 ',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0105_pop_02.json'
		},
		"PP0106" : {
			"ver":0x01000000,
			"desc":'PP0106 : openMPS /생산/기준정보관리/BOM조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0106.json'
		},
		"PP0107" : {
			"ver":0x01000000,
			"desc":'PP0107 : openMPS /생산/기준정보관리/공장창고매핑 등록',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0107.json'
		},
		//수율관리
		"PP0201" : {
			"ver":0x01000000,
			"desc":'PP0201 : openMPS /생산/수율관리/수율기초등록',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0201.json'
		},
		"PP0202" : {
			"ver":0x01000000,
			"desc":'PP0202 : openMPS /생산/수율관리/생산수율표조회(일)',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0202.json'
		},
		"PP0203" : {
			"ver":0x01000000,
			"desc":'PP0203 : openMPS /생산/수율관리/생산수율표조회(주간)',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0203.json'
		},
		"PP0204" : {
			"ver":0x01000000,
			"desc":'PP0204 : openMPS /생산/수율관리/품목별 생산량 및 수율 조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0204.json'
		},
		//생산계획관리
		"PP0301" : {
			"ver":0x01000000,
			"desc":'PP0301 : openMPS /생산/생산계획관리/생산계획 Setup',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0301.json'
		},
		"PP0302" : {
			"ver":0x01000000,
			"desc":'PP0302 : openMPS /생산/생산계획관리/생산계획입력(CM)',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0302.json'
		},

		"PP0303" : {
			"ver":0x01000000,
			"desc":'PP0303 : openMPS /생산/생산계획관리/생산계획서 조회 및 발행(CM)',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0303.json'
		},
		"PP0304" : {
			"ver":0x01000000,
			"desc":'PP0304 : openMPS /생산/생산계획관리/주문 접수(PM)',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0304.json'
		},
		"PP0305" : {
			"ver":0x01000000,
			"desc":'PP0305 : openMPS /생산/생산계획관리/생산계획입력(PM)',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0305.json'
		},
		"PP0305_pop" : {
			"ver":0x01000000,
			"desc":'PP0305 : openMPS /생산/생산계획관리/생산계획입력(PM)_팝업',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0305_pop.json'
		},
		"PP0306" : {
			"ver":0x01000000,
			"desc":'PP0306 : openMPS /생산/생산계획관리/생산계획서 조회 및 발행(PM)',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0306.json'
		},
		"PP0307" : {
			"ver":0x01000000,
			"desc":'PP0307 : openMPS /생산/생산계획관리/생산계획 대비 실적현황 조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0307.json'
		},
		"PP0307_popup" : {
			"ver":0x01000000,
			"desc":'PP0307_pop : openMPS /생산/생산계획관리/생산계획 대비 실적현황 조회 팝업',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0307_pop.json'
		},

		//자재소요량관리
		"PP0401" : {
			"ver":0x01000000,
			"desc":'PP0401 : openMPS /생산/자재소요량관리/자재 소요량 산출',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0401.json'
		},
		"PP0402" : {
			"ver":0x01000000,
			"desc":'PP0402 : openMPS /생산/자재소요량관리/자재 소요량 조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0402.json'
		},
		//생산실적관리
		"PP0501" : {
			"ver":0x01000000,
			"desc":'PP0501 : openMPS /생산/생산계획관리/부자재 출고오류 현황',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0501.json'
		},
		"PP0503" : {
			"ver":0x01000000,
			"desc":'PP0503 : openMPS /생산/생산계획관리/생산 vs 입고현황 조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0503.json'
		},
		"PP0503_popup" : {
			"ver":0x01000000,
			"desc":'PP0503_popup : openMPS /생산/생산계획관리/생산 vs 입고현황 조회 팝업',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0503_pop.json'
		},
		"PP0504" : {
			"ver":0x01000000,
			"desc":'PP0504 : openMPS /생산/생산계획관리/생산실적조회및조정(개체)',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0504.json'
		},
		"PP0505" : {
			"ver":0x01000000,
			"desc":'PP0505 : openMPS /생산/생산계획관리/PM라벨실적입고요청',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0505.json'
		},
		"PP0505_popup" : {
			"ver":0x01000000,
			"desc":'PP0505 : openMPS /생산/생산계획관리/PM라벨실적입고요청 상세보기 팝업',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0505_pop.json'
		},
		//생산현황관리
		"PP0601" : {
			"ver":0x01000000,
			"desc":'PP0601 : openMPS /생산/생산현황관리/공장별 농장별 생산현황 조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0601.json'
		},
		"PP0602" : {
			"ver":0x01000000,
			"desc":'PP0602 : openMPS /생산/생산현황관리/동장별 생산현황 조회(집계)',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0602.json'
		},
		"PP0603" : {
			"ver":0x01000000,
			"desc":'PP0603 : openMPS /생산/생산현황관리/동장별 생산현황 조회(개체별)',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0603.json'
		},
		"PP0604" : {
			"ver":0x01000000,
			"desc":'PP0604 : openMPS /생산/생산현황관리/속라벨 발행현황 조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0604.json'
		},
		"PP0605" : {
			"ver":0x01000000,
			"desc":'PP0605 : openMPS /생산/생산현황관리/작업일보 조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0605.json'
		},
		"PP0607" : {
			"ver":0x01000000,
			"desc":'PP0607 : openMPS /생산/생산현황관리/반품현황 조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0607.json'
		},
		//부자재 관리
		"PP0701" : {
			"ver":0x01000000,
			"desc":'PP0701 : openMPS /생산/부자재 관리/부자재 사용량 조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0701.json'
		},
		"PP0702" : {
			"ver":0x01000000,
			"desc":'PP0702 : openMPS /생산/부자재 관리/부자재 현재고 조회(On-Hand)',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0702.json'
		},
		//이력제관리
		"PP0801" : {
			"ver":0x01000000,
			"desc":'PP0801 : openMPS /생산/이력제관리/매입실적 신고',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0801.json'
		},
		"PP0802" : {
			"ver":0x01000000,
			"desc":'PP0802 : openMPS /생산/이력제관리/포장처리실적 신고',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0802.json'
		},
		"PP0803" : {
			"ver":0x01000000,
			"desc":'PP0803 : openMPS /생산/이력제관리/거래내역실적 신고',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0803.json'
		},
		"PP0803_pop" : {
			"ver":0x01000000,
			"desc":'PP0803_pop : openMPS /생산/이력제관리/거래내역실적 신고 팝업',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0803_pop.json'
		},
		//실적집계관리
		"PP0902" : {
			"ver":0x01000000,
			"desc":'PP0902 : openMPS /생산/실적집계관리/생산실적 집계',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PP0902.json'
		},
		//구매
		"PO0101" : {
			"ver":0x01000000,
			"desc":'PO0101 : openMPS /구매/자료등륵/지육시세등록',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PO0101.json'
		},
		//자료등록
		"PO0102" : {
			"ver":0x01000000,
			"desc":'PO0102 : openMPS /구매/생돈정산/등판자료등록',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PO0102.json'
		},
		//생돈정산
		"PO0201" : {
			"ver":0x01000000,
			"desc":'PO0201 : openMPS /구매/생돈정산/생돈구매세부입력',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PO0201.json'
		},
		"PO0202" : {
			"ver":0x01000000,
			"desc":'PO0201 : openMPS /구매/생돈정산/생돈정산',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PO0202.json'
		},
		"PO0203" : {
			"ver":0x01000000,
			"desc":'PO0203 : openMPS /구매/생돈정산/출하정산집계표조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PO0203.json'
		},
		"PO0204" : { //206 > 204 로 변경함
			"ver":0x01000000,
			"desc":'PO0204 : openMPS /구매/생돈정산/생돈지급률조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PO0204.json'
		},
		"PO0205" : { //207 > 205 로 변경함
			"ver":0x01000000,
			"desc":'PO0205 : openMPS /구매/생돈정산/더느림지급액조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PO0205.json'
		},
		"PO0301" : { //207 > 205 로 변경함
			"ver":0x01000000,
			"desc":'PO0301 : openMPS /구매/생돈정산/이상육 발생현황 관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PO0301.json'
		},
		"PO0302" : { //207 > 205 로 변경함
			"ver":0x01000000,
			"desc":'PO0301 : openMPS /구매/생돈정산/이상육 발생현황 관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/PO0302.json'
		},
		//---------------
		"MCOMAA20_04" : {
			"ver":0x01000000,
			"desc":'MCOMAA20_04 : openMPS /물류/관리/거래처등록 메뉴 (거래처조회와 동일)',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/custinfo.json'
		},
		"MLOGAA20_02" : {
			"ver":0x01000000,
			"desc":'MLOGAA20_02 : openMPS /물류/관리/거래처조회 메뉴 (거래처등록과 동일)',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/custinfo.json'
		},
		"MCOMAA20_03" : {
            "ver":0x01000000,
            "desc":'MCOMAA20_03 : openMPS /관리/고객/거래처담당자변경 메뉴',
            "thumbnail":null,
            "detail": '/app/mps/repo/box/MCOMAA20_03.json'
        },
		"MCOMAA10_03" : {
			"ver":0x01000000,
			"desc":'MCOMAA10_03 : openMPS /관리/고객/한도이력관리 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/MCOMAA10_03.json'
		},

		"MCOMAA10_02" : {
			"ver":0x01000000,
			"desc":'MCOMAA10_02 : openMPS /관리/고객/잔액조회서 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/MCOMAA10_02.json'
		},
		"MCOMAA10_01" : {
			"ver":0x01000000,
			"desc":'MCOMAA10_01 : openMPS /관리/고객/여신관리 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/MCOMAA10_01.json'
		},
		"MMANAA10_08" : {
			"ver":0x01000000,
			"desc":'MMANAA10_08 : openMPS /생산/관리/제품등록 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/MMANAA10_08.json'
		},
		"MMANAA10_11" : {
			"ver":0x01000000,
			"desc":'MMANAA10_11 : openMPS /생산/관리/품목등록 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/MMANAA10_11.json'
		},
		"MCOMAA20_10" : {
			"ver":0x01000000,
			"desc":'MCOMAA20_10 : openMPS /생산/관리/세트등록 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/MCOMAA20_10.json'
		},
		"MCOMAA20_08" : {
			"ver":0x01000000,
			"desc":'MCOMAA20_08 : openMPS /관리/관리/사용자권한관리 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/MCOMAA20_08.json'
		},
		"MSALAA20_01" : {
			"ver":0x01000000,
			"desc":'MSALAA20_01 : openMPS /영업/마스터/담보이력관리 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/MSALAA20_01.json'
		},
		"MCOMAA20_07" : {
			"ver":0x01000000,
			"desc":'MCOMAA20_07 : openMPS /관리/관리/부서등록 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/MCOMAA20_07.json'
		},
		"MCOMAA20_05" : {
			"ver":0x01000000,
			"desc":'MCOMAA20_05 : openMPS 기타코드등록 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/MCOMAA20_05.json'
		},
		"MCOMAA20_09" : {
			"ver":0x01000000,
			"desc":'MCOMAA20_09 : openMPS 사용자등록 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/MCOMAA20_09.json'
		},

		"TMCOOS10" : {
			"ver":0x01000000,
			"desc":'TMCOOS10 : openMPS /공통관리/회사관리 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOOS10.json'
		},
		"TMCOUR10" : {
			"ver":0x01000000,
			"desc":'TMCOUR10 : openMPS /공통관리/사용자정보관리 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOUR10.json'
		},
		"TMCOUR10_pop" : {
			"ver":0x01000000,
			"desc":'TMCOUR10 : openMPS /공통관리/사용자정보관리 메뉴_팝업',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOUR10_pop.json'
		},
		"TMCOOS20" : {
			"ver":0x01000000,
			"desc":'TMCOOS20 : openMPS /공통관리/공장관리 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOOS20.json'
		},
		"TMCOOS40" : {
			"ver":0x01000000,
			"desc":'TMCOOS40 : openMPS /공통관리/창고관리 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOOS40.json'
		},
		"TMCOOS50" : {
			"ver":0x01000000,
			"desc":'TMCOOS50 : openMPS /공통관리/본부관리 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOOS50.json'
		},
		"TMCOOS60" : {
			"ver":0x01000000,
			"desc":'TMCOOS60 : openMPS /공통관리/팀관리 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOOS60.json'
		},
		"TMCOOS70" : {
			"ver":0x01000000,
			"desc":'TMCOOS70 : openMPS /공통관리/부서관리 메뉴',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOOS70.json'
		},
		"TMCOSM10" : {
			"ver":0x01000000,
			"desc":'TMCOSM10 : openMPS /공통/게시판/서비스요청',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOSM10.json'
		},
		"TMCOSM20" : {
			"ver":0x01000000,
			"desc":'TMCOSM20 : openMPS /공통/게시판/서비스요청승인',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOSM20.json'
		},
		"TMCOSM20_pop" : {
			"ver":0x01000000,
			"desc":'TMCOUR10 : openMPS /공통/게시판/서비스요청반려',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOSM20_pop.json'
		},
		"TMCOSM30" : {
			"ver":0x01000000,
			"desc":'TMCOSM30 : openMPS /공통/게시판/서비스요청처리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOSM30.json'
		},
		"TMCOSM40" : {
			"ver":0x01000000,
			"desc":'TMCOSM40 : openMPS /공통/게시판/서비스요청조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOSM40.json'
		},
		"TMCOBD10" : {
			"ver":0x01000000,
			"desc":'TMCOBD10 : openMPS /공통/게시판/게시판관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOBD10.json'
		},
		"TMCOBD20" : {
			"ver":0x01000000,
			"desc":'TMCOBD20 : openMPS /공통/게시판/게시물관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOBD20.json'
		},
		"TMCOBD30" : {
			"ver":0x01000000,
			"desc":'TMCOBD30 : openMPS /공통/게시판/FAQ관리',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOBD30.json'
		},
		"TMCOBD0002" : {
			"ver":0x01000000,
			"desc":'TMCOBD40 : openMPS /공통/게시판/게시판',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOBD40.json'
		},
		"TMCOBD50" : {
			"ver":0x01000000,
			"desc":'TMCOBD50 : openMPS /공통/게시판/FAQ',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/TMCOBD50.json'
		},
		//* 모바일 화면
		"SD9002" : {
			"ver":0x01000000,
			"desc":'SD9002  모바일 전용 공지사항 ' ,
			"thumbnail":null,
			"detail": '/app/mps/repo/box/mobile/SD9002.json'
		}
		,
		"SD900201" : {
			"ver":0x01000000,
			"desc":'SD900201 모바일 전용 공지사항 상세 ' ,
			"thumbnail":null,
			"detail": '/app/mps/repo/box/mobile/SD900201.json'
		}
		,
		"SD9004" : {
			"ver":0x01000000,
			"desc":'SD9004 모바일 전용 주문입력 1 - 영업원용 ' ,
			"thumbnail":null,
			"detail": '/app/mps/repo/box/mobile/SD9004.json'
		}
		,
		"SD900401" : {
			"ver":0x01000000,
			"desc":'SD900401 모바일 전용 주문입력 1 - 영업원용 - 상세화면 ' ,
			"thumbnail":null,
			"detail": '/app/mps/repo/box/mobile/SD900401.json'
		}
		,
		"SD9005" : {
			"ver":0x01000000,
			"desc":'SD9005 모바일 전용 주문입력 2 - 대리점주용',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/mobile/SD9005.json'
		}
		,
		"SD900501" : {
			"ver":0x01000000,
			"desc":'SD900501 모바일 전용 주문입력 2 - 대리점주용 - 상세화면 ',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/mobile/SD900501.json'
		}
		,
		"SD9008" : {
			"ver":0x01000000,
			"desc":'SD9008 모바일 전용 주문조회',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/mobile/SD9008.json'
		}
		,
		"SD900801" : {
			"ver":0x01000000,
			"desc":'SD900801 모바일 전용 주문조회-상세화면',
			"thumbnail":null,
			"detail": '/app/mps/repo/box/mobile/SD900801.json'
		}
		,
		"SD9006" : {"ver":0x01000000,"desc":'SD9006 모바일 전용 출고승인요청',"thumbnail":null,"detail": '/app/mps/repo/box/mobile/SD9006.json'},
		"SD900601" : {"ver":0x01000000,"desc":'SD900601 모바일 전용 출고승인요청 - 상세보기',"thumbnail":null,"detail": '/app/mps/repo/box/mobile/SD900601.json'},
		"SD9007" : {"ver":0x01000000,"desc":'SD9007 모바일 전용 출고승인',"thumbnail":null,"detail": '/app/mps/repo/box/mobile/SD9007.json'},
		"SD900701" : {"ver":0x01000000,"desc":'SD900701 모바일 전용 출고승인 - 상세보기',"thumbnail":null,"detail": '/app/mps/repo/box/mobile/SD900701.json'},
		"SD900702" : {"ver":0x01000000,"desc":'SD900702 모바일 전용 출고이력',"thumbnail":null,"detail": '/app/mps/repo/box/mobile/SD900702.json'},
		"SD9009" : {"ver":0x01000000,"desc":'SD9009 모바일 전용 재고조회1',"thumbnail":null,"detail": '/app/mps/repo/box/mobile/SD9009.json'},
		"SD900901" : {"ver":0x01000000,"desc":'SD900901 모바일 전용 재고조회1 - 상세보기',"thumbnail":null,"detail": '/app/mps/repo/box/mobile/SD900901.json'},
		"SD9010" : {"ver":0x01000000,"desc":'SD9010 모바일 전용 재고조회2',"thumbnail":null,"detail": '/app/mps/repo/box/mobile/SD9010.json'},
		"SD901001" : {"ver":0x01000000,"desc":'SD901001 모바일 전용 재고조회2 - 상세보기',"thumbnail":null,"detail": '/app/mps/repo/box/mobile/SD901001.json'},
		"SD9003" : {"ver":0x01000000,"desc":'SD9003 모바일 전용 할인단가승인',"thumbnail":null,"detail": '/app/mps/repo/box/mobile/SD9003.json'},
		"SD900301" : {"ver":0x01000000,"desc":'SD900301 모바일 전용 할인단가승인 - 상세보기',"thumbnail":null,"detail": '/app/mps/repo/box/mobile/SD900301.json'},
		"SD9011" : {"ver":0x01000000,"desc":'SD9011 모바일 전용 단가조회',"thumbnail":null,"detail": '/app/mps/repo/box/mobile/SD9011.json'},
		"SD901101" : {"ver":0x01000000,"desc":'SD901101 모바일 전용 단가조회 - 상세보기',"thumbnail":null,"detail": '/app/mps/repo/box/mobile/SD901101.json'}
	};

	var formRepo =
	{
		"Q_TEXT" : {
			"ver":0x01000000,
			"desc":'조회용 폼 파라메터 텍스트형',
			"thumbnail":null,
			"detail": '/app/mps/repo/querybox/q_text.json'
		}
	};

	var commonRepo =
	{
		"NDSProps" : "/app/mps/common/NDSProps",
		"WorkAreaRenderer" : "/app/mps/common/WorkAreaRenderer",
		"WorkAreaHeader" : "/app/mps/common/WorkAreaHeader",
		"SubContainer" : "/app/mps/common/SubContainer",
		"CodeSearchRenderer": "/app/mps/common/CodeSearchRenderer",
		"RendererBase" : "/app/mps/common/RendererBase",
		"WorkAreaRenderer2" : "/app/mps/common/WorkAreaRenderer2",
		"NFRenderer" : "/app/mps/common/NFRenderer"
	};

	var bizRepo =
	{
		"RDNFRenderer" : "/app/mps/bizform/RDNFRenderer",
		"RDENFRenderer" : "/app/mps/bizform/RDENFRenderer",
		"APIClient" : "/app/mps/bizform/client.openmpsapi",
		"mps.helper" : "/app/mps/bizform/mps.helper",

		//
		// TM
		"TMCOCD10Renderer" : "/app/mps/bizform/tm/TMCOCD10Renderer",
		//메시지 관리
		"TMCOCD30Renderer" : "/app/mps/bizform/tm/TMCOCD30Renderer",
		// 회사 관리
		"TMCOOS10Renderer" : "/app/mps/bizform/tm/TMCOOS10Renderer",
		// 본부 관리
		"TMCOOS50Renderer" : "/app/mps/bizform/tm/TMCOOS50Renderer",
		// 팀 관리
		"TMCOOS60Renderer" : "/app/mps/bizform/tm/TMCOOS60Renderer",
		// 부서 관리
		"TMCOOS70Renderer" : "/app/mps/bizform/tm/TMCOOS70Renderer",
		// 사용자 관리
		"TMCOUR10Renderer" : "/app/mps/bizform/tm/TMCOUR10Renderer",
		"TMCOUR10PopupRenderer" : "/app/mps/bizform/tm/TMCOUR10PopupRenderer",

		"TMCOUR40Renderer" : "/app/mps/bizform/tm/TMCOUR40Renderer",
		// 서비스 요청
		"TMCOSM10Renderer" : "/app/mps/bizform/tm/TMCOSM10Renderer",
		// 서비스 요청 승인
		"TMCOSM20Renderer" : "/app/mps/bizform/tm/TMCOSM20Renderer",
		"TMCOSM20PopupRenderer" : "/app/mps/bizform/tm/TMCOSM20PopupRenderer",
		// 서비스 요청 처리
		"TMCOSM30Renderer" : "/app/mps/bizform/tm/TMCOSM30Renderer",
		// 서비스 요청 조회
		"TMCOSM40Renderer" : "/app/mps/bizform/tm/TMCOSM40Renderer",
		// 게시판 관리
		"TMCOBD10Renderer" : "/app/mps/bizform/tm/TMCOBD10Renderer",
		// 게시물 관리
		"TMCOBD20Renderer" : "/app/mps/bizform/tm/TMCOBD20Renderer",
		// FAQ 관리
		"TMCOBD30Renderer" : "/app/mps/bizform/tm/TMCOBD30Renderer",
		// 게시판
		"TMCOBD40Renderer" : "/app/mps/bizform/tm/TMCOBD40Renderer",
		// FAQ
		"TMCOBD50Renderer" : "/app/mps/bizform/tm/TMCOBD50Renderer",
		// 프로그램 관리
		"TMCOMT10Renderer" : "/app/mps/bizform/tm/TMCOMT10Renderer",
		//그룹별사용자관리
		"TMCOUR60Renderer" : "/app/mps/bizform/tm/TMCOUR60Renderer",
		// API 로그관리
		"TMCOMT40Renderer" : "/app/mps/bizform/tm/TMCOMT40Renderer",
		// SMS FAX 로그 관리
		"TMCOMT50Renderer" : "/app/mps/bizform/tm/TMCOMT50Renderer",
		// FAX 보내기
		"TMCOMT60Renderer" : "/app/mps/bizform/tm/TMCOMT60Renderer",
		"TMCOMT60PopupRenderer" : "/app/mps/bizform/tm/TMCOMT60PopupRenderer",
		//
		// SD
		//
		"SD0101Renderer" : "/app/mps/bizform/sd/SD0101Renderer",
		"SD0102Renderer" : "/app/mps/bizform/sd/SD0102Renderer",
		"SD0103Renderer" : "/app/mps/bizform/sd/SD0103Renderer",

		"SD0201Renderer" : "/app/mps/bizform/sd/SD0201Renderer",
		"SD0202Renderer" : "/app/mps/bizform/sd/SD0202Renderer",
		"SD0203Renderer" : "/app/mps/bizform/sd/SD0203Renderer",
		"SD0204Renderer" : "/app/mps/bizform/sd/SD0204Renderer",
		"SD0205Renderer" : "/app/mps/bizform/sd/SD0205Renderer",
		"SD0206Renderer" : "/app/mps/bizform/sd/SD0206Renderer",
		"SD0207Renderer" : "/app/mps/bizform/sd/SD0207Renderer",

		"SD0301Renderer" : "/app/mps/bizform/sd/SD0301Renderer",
		"SD0302Renderer" : "/app/mps/bizform/sd/SD0302Renderer",
		"SD0303Renderer" : "/app/mps/bizform/sd/SD0303Renderer",
		"SD0305Renderer" : "/app/mps/bizform/sd/SD0305Renderer",

		"SD0401Renderer" : "/app/mps/bizform/sd/SD0401Renderer",
		"SD0402Renderer" : "/app/mps/bizform/sd/SD0402Renderer",
		"SD0403Renderer" : "/app/mps/bizform/sd/SD0403Renderer",
		"SD0404Renderer" : "/app/mps/bizform/sd/SD0404Renderer",
		"SD0405Renderer" : "/app/mps/bizform/sd/SD0405Renderer",
		"SD0406Renderer" : "/app/mps/bizform/sd/SD0406Renderer",
		"SD0407Renderer" : "/app/mps/bizform/sd/SD0407Renderer",

		"SD0501Renderer" : "/app/mps/bizform/sd/SD0501Renderer",
		"SD0502Renderer" : "/app/mps/bizform/sd/SD0502Renderer",
		"SD0503Renderer" : "/app/mps/bizform/sd/SD0503Renderer",
		"SD0504Renderer" : "/app/mps/bizform/sd/SD0504Renderer",
		"SD0505Renderer" : "/app/mps/bizform/sd/SD0505Renderer",
		"SD0506Renderer" : "/app/mps/bizform/sd/SD0506Renderer",

		"SD0601Renderer" : "/app/mps/bizform/sd/SD0601Renderer",
		"SD0602Renderer" : "/app/mps/bizform/sd/SD0602Renderer",
		"SD0603Renderer" : "/app/mps/bizform/sd/SD0603Renderer",
		"SD0604Renderer" : "/app/mps/bizform/sd/SD0604Renderer",
		"SD0605Renderer" : "/app/mps/bizform/sd/SD0605Renderer",
		"SD0606Renderer" : "/app/mps/bizform/sd/SD0606Renderer",
		"SD0607Renderer" : "/app/mps/bizform/sd/SD0607Renderer",
		"SD0608Renderer" : "/app/mps/bizform/sd/SD0608Renderer",
		"SD0609Renderer" : "/app/mps/bizform/sd/SD0609Renderer",
		"SD0610Renderer" : "/app/mps/bizform/sd/SD0610Renderer",

		"SD0611Renderer" : "/app/mps/bizform/sd/SD0611Renderer",
		"SD0612Renderer" : "/app/mps/bizform/sd/SD0612Renderer",

		"SD0801Renderer" : "/app/mps/bizform/sd/SD0801Renderer",
		"SD0802Renderer" : "/app/mps/bizform/sd/SD0802Renderer",
		"SD0803Renderer" : "/app/mps/bizform/sd/SD0803Renderer",
		"SD0804Renderer" : "/app/mps/bizform/sd/SD0804Renderer",
		"SD0805Renderer" : "/app/mps/bizform/sd/SD0805Renderer",
		"SD0806Renderer" : "/app/mps/bizform/sd/SD0806Renderer",

		"SD1001Renderer" : "/app/mps/bizform/sd/SD1001Renderer",

		//
		// PP
		//
		"PP0101Renderer" : "/app/mps/bizform/pp/PP0101Renderer",
		"PP0103Renderer" : "/app/mps/bizform/pp/PP0103Renderer",
		"PP0104Renderer" : "/app/mps/bizform/pp/PP0104Renderer",
			"PP0105LoadPopupRenderer" : "/app/mps/bizform/pp/PP0105LoadPopupRenderer",
		"PP0105Renderer" : "/app/mps/bizform/pp/PP0105Renderer",
			"PP0105SearchPopupRenderer" : "/app/mps/bizform/pp/PP0105SearchPopupRenderer",
		"PP0106Renderer" : "/app/mps/bizform/pp/PP0106Renderer",
		"PP0107Renderer" : "/app/mps/bizform/pp/PP0107Renderer",

		"PP0201Renderer" : "/app/mps/bizform/pp/PP0201Renderer",
		"PP0202Renderer" : "/app/mps/bizform/pp/PP0202Renderer",
		"PP0203Renderer" : "/app/mps/bizform/pp/PP0203Renderer",
		"PP0204Renderer" : "/app/mps/bizform/pp/PP0204Renderer",

		"PP0301Renderer" : "/app/mps/bizform/pp/PP0301Renderer",
		"PP0302Renderer" : "/app/mps/bizform/pp/PP0302Renderer",
		"PP0303Renderer" : "/app/mps/bizform/pp/PP0303Renderer",
		"PP0304Renderer" : "/app/mps/bizform/pp/PP0304Renderer",
			"PP0305PopupRenderer" : "/app/mps/bizform/pp/PP0305PopupRenderer",
		"PP0305Renderer" : "/app/mps/bizform/pp/PP0305Renderer",
		"PP0306Renderer" : "/app/mps/bizform/pp/PP0306Renderer",
		"PP0307Renderer" : "/app/mps/bizform/pp/PP0307Renderer",
			"PP0307PopupRenderer" : "/app/mps/bizform/pp/PP0307PopupRenderer",

		"PP0401Renderer" : "/app/mps/bizform/pp/PP0401Renderer",
		"PP0402Renderer" : "/app/mps/bizform/pp/PP0402Renderer",

		"PP0501Renderer" : "/app/mps/bizform/pp/PP0501Renderer",
			"PP0503PopupRenderer" : "/app/mps/bizform/pp/PP0503PopupRenderer",
		"PP0503Renderer" : "/app/mps/bizform/pp/PP0503Renderer",
		"PP0504Renderer" : "/app/mps/bizform/pp/PP0504Renderer",
			"PP0505PopupRenderer" : "/app/mps/bizform/pp/PP0505PopupRenderer",
		"PP0505Renderer" : "/app/mps/bizform/pp/PP0505Renderer",

		"PP0601Renderer" : "/app/mps/bizform/pp/PP0601Renderer",
		"PP0602Renderer" : "/app/mps/bizform/pp/PP0602Renderer",
		"PP0603Renderer" : "/app/mps/bizform/pp/PP0603Renderer",
		"PP0604Renderer" : "/app/mps/bizform/pp/PP0604Renderer",
		"PP0605Renderer" : "/app/mps/bizform/pp/PP0605Renderer",

		"PP0701Renderer" : "/app/mps/bizform/pp/PP0701Renderer",
		"PP0702Renderer" : "/app/mps/bizform/pp/PP0702Renderer",

		"PP0801Renderer" : "/app/mps/bizform/pp/PP0801Renderer",
		"PP0802Renderer" : "/app/mps/bizform/pp/PP0802Renderer",
			"PP0803PopupRenderer" : "/app/mps/bizform/pp/PP0803PopupRenderer",
		"PP0803Renderer" : "/app/mps/bizform/pp/PP0803Renderer",

		"PP0902Renderer" : "/app/mps/bizform/pp/PP0902Renderer",
		//
		// PO
		//
		"PO0101Renderer" : "/app/mps/bizform/po/PO0101Renderer",
		"PO0102Renderer" : "/app/mps/bizform/po/PO0102Renderer",
		"PO0201Renderer" : "/app/mps/bizform/po/PO0201Renderer",
		"PO0202Renderer" : "/app/mps/bizform/po/PO0202Renderer",
		"PO0203Renderer" : "/app/mps/bizform/po/PO0203Renderer",
		"PO0204Renderer" : "/app/mps/bizform/po/PO0204Renderer",
		"PO0205Renderer" : "/app/mps/bizform/po/PO0205Renderer",
		"PO0301Renderer" : "/app/mps/bizform/po/PO0301Renderer",
		"PO0302Renderer" : "/app/mps/bizform/po/PO0302Renderer"
	};

	var testRepo = {
		"JQGrid4TestRenderer": "/app/mps/bizform/test/JQGrid4TestRenderer"
	};

	var g_repo = GlobalRepo.getRepo();

	var Resource =
	{
		getRepo : GlobalRepo.getRepo,
		getGallery : function()
		{
			return {
				"widget" : { label: "Widget Modules", repo: _.extend({}, widgetRepo, g_repo.widget.repo) },
				"formWidget" : { label: "Form Widget Modules", repo: _.extend({}, formWidgetRepo, g_repo.formWidget.repo) },
				"panel" : { label: "Panel Modules", repo: _.extend({}, panelRepo, g_repo.panel.repo) },
				"formPanel" : { label: "Form Panel Modules", repo: _.extend({}, formRepo, g_repo.formPanel.repo) }
			};
		}
		,
		getResourceMap : function()
		{
			return _.extend(
					{}
					, commonRepo
					, bizRepo
					, testRepo
					, createResourceMap( widgetRepo )
					, createResourceMap( formWidgetRepo )
					, GlobalRepo.getResourceMap() );
		}
	};

	return Resource;
});
