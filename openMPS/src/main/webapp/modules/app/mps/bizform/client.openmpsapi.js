/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2016 FREECORE, Inc. All rights reserved.
 *
 * @author	dbongman
 */

define(
[
	"ClientBase", "AResultClasses", "NDSProps", "Logger"
]
,
function(ClientBase, AResult, NDSProps, Logger)
{
	var OpenMPSAPIClient = ClientBase.extend(
	{
		defaults:
		{
			// AccessToken
			token: null,
			// API 서버 주소
			host: null,
			// 시스템 코드
			systemCode: null,

			// 회사 코드. 기본값으로 설정
			corpCode: "1001",
			// 업무영역 식별자. 메뉴 ID 와 같은 값. 호출하는 콘텐츠 API 식별자
			contentId: null
		}
		,
		/**
		 * 클라이언트 모듈을 초기화한다.
		 * @param {object} options - 초기화 파라메터. API 동작을 위해 필요한 파라메터를 설정한다.
		 * 					{
		 * 						token: {string},
		 * 						host: {string},
		 * 						systemCode: {string},
		 * 						corpCode: {string},
		 * 						contentId: {string},
		 * 						params: {object}
		 * 					}
		 */
		initialize: function(options)
		{
			OpenMPSAPIClient.__super__.initialize.apply( this, arguments );

			Logger.debug("OpenMPSAPIClient.initialize() - contentId : "+this._contentId);
		}
		,
		/**
		 * 제공된 콘텐츠에 접근할 수 있는 API 를 구한다.
		 *
		 * TODO 트랜젝션 저장 동작은 해당 트랜젝션이 발생된 그리드의 식별자를 붙인다.
		 * TODO 그리드가 1 개인 경우 "save" 가 자동으로 붙는다. 그러므로, API 주소를 반환할 때 ContentId+GridId 의 패턴으로 주소를 반환한다.
		 *
		 * @param {string} method - read, create, update, delete 중 하나가 지정된다.
		 * @param {string||object} apiID - API 식별자. 생략되는 경우 초기화시 설정된 값이 사용된다.
		 * 							string 인 경우 mainId 는 contentId 가 설정되고, 제공된 apiId 는 subId 로 사용된다.
		 * 							object 인 경우 { mainId:{string}, subId:{string} } 로 지정한다.
		 * 							mainId 는 업무 화면을 선택하는 용도이며, subId 는 업무 화면 내의 리소스를 지정하는 용도이다.
		 * @param {object} params - API 구성시 필요한 파라메터 객체
		 */
		getAPIPath: function(method, apiID, params)
		{
			var path = this.get('host');
			if( typeof apiID == 'string' )
			{
				apiID = {
					"mainId": this.get('contentId'),
					"subId": ''+apiID
				};
			}
			else
			{
				apiID = {
					"mainId": this.get('contentId'),
					"subId": null
				};
			}
			params || ( params = {} );
			apiID.mainId || (apiID.mainId="");

			switch( apiID.mainId )
			{
			//////
				// 인증
				case "signbox":
					if( method == 'create' )
					{
						// 토큰 생성
						path += '/rest/ctoken';
					}
					else if( method == 'delete' )
					{
						// 토큰 해제
						path += '/rest/rtoken';
					}
					break;
			//////
				//
				case "TMCOOS10":
					// 회사관리
					path += '/rest/tmm/TMCOOS10';
					if( apiID.subId == 'code' )
					{
						path += "/code";
					}
					break;
				case "TMCOUR10":
					// 사용자정보관리
					path += '/rest/tmm/'+this.get("corpCode")+'/tmcour10';
					break;
				case "TMCOOS20":
					// 공장관리
					path += '/rest/mpm/TMCOOS20';
					break;
				case "TMCOOS40":
					// 창고관리
					path += '/rest/mpm/setitem';
					break;
				case "TMCOOS50":
					// 본부관리
					path += '/rest/tmm/TMCOOS50';
					if( apiID.subId == "dup" )
					{
						path += "/dup";
					}
					if( apiID.subId == 'code' )
					{
						path += "/code";
					}
					break;
				case "TMCOOS60":
					// 팀관리
					path += '/rest/tmm/TMCOOS60';
					if( apiID.subId == 'code' )
					{
						path += "/code";
					}
					break;
				case "TMCOOS70":
					// 부서관리
					path += '/rest/tmm/TMCOOS70';
					if( apiID.subId == 'code' )
					{
						path += "/code";
					}
					break;
				case "TMCOSM10":
					// 서비스 요청
					path += '/rest/tmm/'+this.get("corpCode")+'/TMCOSM10';
					break;
				case "TMCOSM20":
					// 서비스 요청 승인
					path += '/rest/tmm/'+this.get("corpCode")+'/TMCOSM20';
					break;
				case "TMCOSM30":
					// 서비스 요청 처리
					path += '/rest/tmm/'+this.get("corpCode")+'/TMCOSM30';
					break;
				case "TMCOSM40":
					// 서비스 요청 처리
					path += '/rest/tmm/'+this.get("corpCode")+'/TMCOSM40';
					break;
				case "TMCOBD10":
					// 게시판 관리
					path += '/rest/tmm/'+this.get("corpCode")+'/TMCOBD10';
					break;
				case "TMCOBD20":
					// 게시물 관리
					path += '/rest/tmm/'+this.get("corpCode")+'/TMCOBD20';
					break;
				case "TMCOBD30":
					// FAQ 관리
					path += '/rest/tmm/'+this.get("corpCode")+'/TMCOBD30';
					break;
				case "TMCOBD0002":
					// 게시판
					path += '/rest/tmm/'+this.get("corpCode")+'/TMCOBD40/TMCOBD0002';
					break;
				case "TMCOBD50":
					// FAQ
					path += '/rest/tmm/'+this.get("corpCode")+'/TMCOBD50';
					break;

			////// -----------
				// 공통코드관리
				//
				case "TMCOCD10":
				{
					var item = params;

					if( params instanceof Array )
					{
						item = params[0];
					}

					path += '/rest/tmm/'+this.get("corpCode")+'/tmcocd10';
					if( typeof item.groupCode == "string" )
					{
						// 코드 상세 리소스
						path += '/'+item.groupCode+'/tmcodexd';
						if( apiID.subId == 'code' )
						{
							path += "/code";
						}
					}
					else
					{
						// 코드 그룹 리소스
						path += '/tmcodexm';
					}
				}
				break;
				//
				// 라벨관리
				//
				case "TMCOCD20":
					path += '/rest';
					if( apiID.subId == 'rightGrid' )
					{
						// 메뉴별 라벨 조회
						path += '/tmm/'+this.get("corpCode")+'/tmcocd20';
						if( params.menuCode )
						{
							// 조회 대상이 되는 메뉴 코드
							path += '/'+params.menuCode;
						}
					}
					else if( apiID.subId == 'leftGrid' )
					{
						if( method == 'read' )
						{
							// 전체 메뉴 조회
							path += '/tmm/'+this.get("corpCode")+'/tmcour30/tmmenuxm';
							if( this.has("systemCode") )
							{
								// 검색 조건
								path += '?systemCode='+this.get("systemCode");
							}
						}
						else
						{
							// 해당 동작 없음
							path = null
						}
					}
					break;
				//
				// 메시지 관리
				//
				case "TMCOCD30":
					path += '/rest';
					path += '/tmm/'+this.get("corpCode")+'/tmcocd30';
					if( params.searchKeyword )
					{
						// 검색 조건 추가
						path += '?searchKeyword='+params.searchKeyword;
					}
					break;
				//
				// API 로그 관리
				//
				case "TMCOMT40":
					path += '/rest';
					path += '/tmm/'+this.get("corpCode")+'/tmcomt40';
					if( params.searchKeyword  && params.frstDate && params.lastDate )
					{
						// 검색 조건 추가
						path += '?searchKeyword='+params.searchKeyword +'/'+params.frstDate + '/'+params.lastDate;
					}
					break;
				// SMS/FAX 로그 관리
				//
				case "TMCOMT50":
					path += '/rest';
					path += '/tmm/'+this.get("corpCode")+'/tmcomt50';
					if( params.frstDate && params.lastDate && params.msgType )
					{
						// 검색 조건 추가
						path += '?frstDate='+params.frstDate + '/'+params.lastDate + '/'+params.msgType;
					}
					break;
				// FAX 보내기
				//
				case "TMCOMT60":
					path += '/rest';
					path += '/tmm/'+this.get("corpCode")+'/tmcomt60';
					if( params.frstDate && params.lastDate )
					{
						// 검색 조건 추가
						path += '?frstDate='+params.frstDate + '/'+params.lastDate;
					}
					break;
				// 프로그램 관리
				//
				case "TMCOUR30":
					path += '/rest';
					if( method == 'read' )
					{
						// 전체 메뉴 조회
						path += '/tmm/'+this.get("corpCode")+'/tmcour30/tmmenuxm';
						if( params.upCode )
						{
							// 서브 메뉴 조회
							path += '/' + params.upCode;
						}
						if( this.has("systemCode") )
						{
							// 검색 조건
							path += '?systemCode='+this.get("systemCode");
						}
					}
					else
					{
						path += '/tmm/'+this.get("corpCode")+'/tmcour30/tmmenuxm';
					}
					break;
				//
				// 권한그룹관리
				//
				case "TMCOUR40":
					path += '/rest';
					path += '/tmm/'+this.get("corpCode")+'/tmcour40/tmgrupxm';
					if( params.groupName )
					{
						path += '/' + params.groupName;
					}
					break;
				//
				// 그룹별 메뉴 관리
				//
				case "TMCOUR50":
					{
						var item = params;

						if( params instanceof Array )
						{
							item = params[0];
						}

						path += '/rest';
						if( apiID.subId == 'rightGrid' )
						{
							if( params.groupName )
							{
								// 그룹내 메뉴 목록 조회
								path += '/tmm/'+this.get("corpCode")+'/tmcour40/tmgrupxm/'+params.groupName;
							}
							else if( params.groupCode )
							{
								// 그룹내 메뉴에 대한 접근 권한 조회
								path += "/tmm/"+this.get("corpCode")+"/tmcour50/tmauthxm/"+params.groupCode;
							}
							else
							{
								Logger.error(apiID);
								Logger.error("Invalid parameters.");
							}
						}
						else if( apiID.subId == 'leftGrid' )
						{
							// 권한 그룹 목록 조회
							path += '/tmm/'+this.get("corpCode")+'/tmcour40/tmgrupxm';
							if( params.searchKeyword )
							{
								// 검색 조건 추가
								path += '?searchKeyword='+params.searchKeyword;
							}
						}
					}
					break;
				//
				// 그룹별 사용자 관리
				//
				case "TMCOUR60":
					break;

				default:
					path = null;
					break;
			}

			if( path == null )
			{
				if( apiID.mainId.indexOf('SD') == 0 )
				{
					path = this._getAPIPath4SD( method, apiID, params );
				}
				else if( apiID.mainId.indexOf('PP') == 0 )
				{
					path = this._getAPIPath4PP( method, apiID, params );
				}
				else if( apiID.mainId.indexOf('PO') == 0 )
				{
					path = this._getAPIPath4PO( method, apiID, params );
				}
				else
				{
					Logger.error("OpenMPSAPIClient.getAPIPath() - unknown id : "+this.get('contentId'));
				}
			}

			return path;
		}
		,
		/**
		 * 영업 파트 API 경로를 구한다.
		 */
		_getAPIPath4SD: function(method, apiID, params)
		{
			var hosts = NDSProps.get("hosts");
			var user = NDSProps.get("user");
			var path = "/rest/mpm/"+this.get("corpCode")+"/"+apiID.mainId.toLowerCase();

			switch( apiID.mainId )
			{
			case "SD0101": // 거래처 정보

				if( apiID.subId == 'code' )
				{
					path += "/mpcustinfo/ALL/code";
				}
				else if( apiID.subId == 'tab_organ' )
				{
					// 관할조직
					path += "/mpcusthist/"+params.custCode;
				}
				else if( apiID.subId == 'tab_security' )
				{
					// 담보 내역
					path += "/mpdamboinfo/"+params.custCode;
				}
				else if( apiID.subId == 'tab_credit' )
				{
					// 한도 내역
					path += "/mpcreditlimit/"+params.custCode;
				}
				else if( apiID.subId == 'tab_warehouse' )
				{
					// 물류 내역
					path += "/mpcustprolimit/"+params.custCode;
				}
				else if( apiID.subId == 'tab_history' )
				{
					// 거래처 이력
					path += "/mpcusthistory/"+params.custCode;
				}
				else if( apiID.subId == 'checkdup' )
				{
					path += "/mpcustinfo/dup/"+params.custCode;
				}
				else
				{
					if(method == "create")
					{
						path += "/mpcustinfo/" +params.custCode;
					}
					else
						path += "/mpcustinfo";
				}

				break;

			case "SD0102": // 거래처 제품
				path += "/mpcustprocode";
				break;

			case "SD0103": // 사원별 제품목록
				if( apiID.subId == "resultBox.leftGrid.content" )
				{
					// 사원의 거래처 목록
					// /mpsalesmancust
					path += "/mpsalesmancust";
					if(method == 'read')
					{
						// salesman
					}
					else if(method == 'create')
					{

					}
				}
				else if( apiID.subId == "resultBox.rightGrid.content" )
				{
					// 사원의 제품 목록
					// /mpsalesmanpro
					path += "/mpsalesmanpro";
					if(method == 'read')
					{
						// salesman
					}
					else if(method == 'create')
					{

					}
				}
				else if( apiID.subId == "resultBox.centerDownGrid" )
				{
					// 거래처별 제품 목록
					// /mpcustpro
					path += "/mpcustpro";
					if(method == 'read')
					{
						// salesman
						// samesmanCust
					}
					else if(method == 'create')
					{

					}
				}
				break;

			case "SD0201": // 표준단가 목록
				path += "/mpstndprod";
				break;

			case "SD0202": // 표준단가 입력
				path += "/mpstndprice";
				if( apiID.subId == "leftGrid" )
				{
					// /period/{strtDate}/{lastDate}
					path += "/period";
				}
				else
				{
					// rightGrid
					// /{strtDate}/{lastDate}
				}

				if( params.strtDate && params.lastDate )
				{
					path += "/"+params.strtDate +"/"+params.lastDate;
				}
				else
				{
					// 잘못된 조회 조건
				}
				break;

			case "SD0203": // 판매단가 주간
				// /{strtDate}/{lastDate}/{salesman}
				path += "/mpsaleprice/"+params.strtDate +"/"+params.lastDate;// +"/"+params.salesman;
				path += "/"+user.id;
				if(params.newGubun == "new")
				{
					path += "?searchCondition=new&searchCondition2="+params.newStrtDate;
				}
				break;

			case "SD0204": // 거래처단가등급
				path += "/mppriclass";
				if( apiID.subId == "detail" )
				{
					path += "/"+params.custCode;
				}
				if( apiID.subId == "users" )
				{
					path = "/rest/mpm/"+this.get("corpCode")+"/auth/user";
				}
				break;

			case "SD0205": // 할인단가 입력
				if( apiID.subId == "reqConfirm" )
				{
					// /reqappro/{strtDate}/{lastDate}/{salesman}
					path += "/mpdiscprice/reqappro/"+params.strtDate+"/"+params.lastDate+"/"+user.id;
				}
				else if( params.custCode )
				{
					path += "/mpdiscprice/"+params.strtDate +"/"+params.lastDate +"/"+params.custCode;
					//path += "/"+params.salesman;
					path += "/"+user.id;
				}
				else
				{
					path += "/mpdiscpricetitle/"+params.strtDate +"/"+params.lastDate;
				}
				break;

			case "SD0206": // 판매단가 승인
				// /mpsaleprice/{strtDate}/{lastDate}/{salesman}
				path += "/mpsaleprice/"+params.strtDate +"/"+params.lastDate+"/ALL";
				if( params.appro )
				{
					// 승인
					path += "/"+params.appro;
				}
				else if( params.status )
				{
					// 조회
					path += "/"+params.status;
				}
				break;
			case "SD0207": // 할인단가 승인
				// /mpdiscprice/{strtDate}/{lastDate}
				path += "/mpdiscprice/"+params.strtDate +"/"+params.lastDate;
				if( params.status == null && params.custCode )
				{
					// 제품 할인가격 목록
					path += "/" + params.custCode + "/detail";
				}
				else if( params.status )
				{
					// 승인/반려 요청
					path += "/appro/" + params.status;
				}
				break;

			case "SD0301": // 년간목표_영업사원
				//path += "/mptargetyyyy/"+params.target_yyyy +"/"+params.partCode +"/"+params.salesMan +"/"+params.manClass;
				path += "/mptargetyyyy/"+params.target_yyyy +"/"+params.partCode +"/"+params.salesMan;
				break;

			case "SD0302": // 년간목표_거래처
				//path += "/mptargetyyyy/"+params.target_yyyy +"/"+params.partCode +"/"+params.salesMan +"/"+params.manClass;
				path += "/mptargetyyyy/"+params.target_yyyy +"/"+params.partCode +"/"+ params.salesman;
				break;

			case "SD0303": // 월별 목표현황_영업파트
				path += "/mptargetyymm";
				if( apiID.subId == "gridBox1" )
				{
					path += "/head/" + params.targetYyyy;
				}
				else
				{
					path += "/detail/"+params.targetYyyy +"/"+ params.teamCode +"/"+ params.salesman;
				}
				break;

			case "SD0304": // 월별 목표현황_영업사원
				break;

			case "SD0305": // 월별 목표현황_거래처
				path += "/mptargetyymm"
				
				if( apiID.subId == "gridBox1" )
				{
					path += "/head/"+params.target_yyyy+"/"+params.partCode;
				}
				else{
					path += "/detail/"+params.target_yyyy+"/"+params.partCode +"/"+params.salesman;
				}
				break;

			case "SD0401": // 주문입력
				path += "/mporderh";
				if(apiID.subId == "tab_result")
				{
					// /{strtDate}/{lastDate}
					path += "/"+params.strtDate + "/"+params.lastDate;
				}
				else if(apiID.subId == "tab_result_detail")
				{
					// /items/{delvDate}/{ordrCust}/{ordrNo}
					path += "/items/"+params.delvDate + "/"+params.ordrCust + "/"+params.ordrNo;
				}
				else if(apiID.subId == "tab_form")
				{
					// 여신 조회
					// /credit/{ordrCust}/{strtDate}/{ordrAmt}
					path += "/credit/"+params.ordrCust + "/"+params.strtDate + "/"+params.ordrAmt;
				}
				else if(apiID.subId == "tab_result_edit")
				{
					// 재고 조회
					// /wms/{ordrCust}/{delvDate}/{delvDc}/{proCode}
					path += "/wms/"+params.ordrCust + "/"+params.delvDate + "/"+params.delvDc + "/"+params.proCode;
				}
				break;

			case "SD0402": // 출고승인요청
				// /mpdelvappro/{strtDate}/{lastDate}
				path += "/mpdelvappro/"+params.strtDate + "/"+params.lastDate;
				break;

			case "SD0403": // 출고승인
				// /mpdelvappro/{strtDate}/{lastDate}
				path += "/mpdelvappro/"+params.strtDate + "/"+params.lastDate;
				if(method == "create")
				{
					// 승인/반려
					path += "/approyn";
				}
				break;

			case "SD0404": // PM 주문입력 (이마트 주문 입력)
				if( apiID.subId == "upload" )
				{
					path += "/excel/upload";
				}
				else
				{
					path += "/mporderh/"+params.strtDate+"/"+params.ordrCust;
					if( method == "create" )
					{
						// /mporderh/{strtDate}/{ordrCust}/{delvDc}
						path += "/"+params.delvDc;
					}
				}
				break;

			case "SD0405": // 온라인 주문 입력
				{
					if( apiID.subId == "upGrid" )
					{
						// 주문 조회
						path += "/mporderh";	// /{custCode}/{proUkind}

						// 주문 등록
						if( params.custCode && params.proUkind )
						{
							path += "/excel/" + params.custCode;
							path += "/" + params.proUkind;
						}
					}
					else if( apiID.subId == "downGrid" )
					{
						// 엑셀 등록
						path += "/excel/upload";
					}
				}
				break;

			case "SD0406": // 주문현황
			///cust/{delvDate}/{salesman}
				path += "/mporderh/cust/"+params.delvDate+"/"+params.salesman;
				/*
				if( apiID.subId == "tab_result_view" )
				{
					// 거래처별 주문현황 조회
					// /cust/{delvDate}/{salesman}
					path += "/cust/"+params.delvDate+"/"+params.salesman;
				}
				else if( apiID.subId == "tab_result_detail" )
				{
					// 제품별 주문현황 조회
					// /prod/{delvDate}/{ordrCust}/{ordrNo}
					path += "/prod/"+params.delvDate+"/"+params.salesman;
				}
				*/
				break;

			case "SD0407": // 온라인 주문정보 매핑
				if( apiID.subId == "custlist" )
				{
					// 영업사원의 거래처 목록 조회
					path += "/mporderupload";
				}
				else if( apiID.subId == "mapping" )
				{
					// 주문정보 컬럼 정보
					// /mporderupload/{ordrCust}
					path += "/mporderupload/"+params.ordrCust;
				}
				break;

			case "SD0501": // 수금입력조회
				// /mpreceinfo//{strtDate}/{lastDate}
				path += "/mpreceinfo/"+params.strtDate +"/"+params.lastDate;
				break;

			case "SD0502": // 수금현황조회
				// /mpreceinfo/{strtDate}/{lastDate}
				path += "/mpreceinfo/"+params.strtDate + "/"+params.lastDate;
				break;
/*
			case "SD0503": // 잔액조회서
				path += "/mplastunpayment/"+params.saleDate;
				break;
*/

			case "SD0503": // 미수현황_영업파트
				path += "/mpcustrecord/"+params.strtDate +"/"+params.lastDate;
				if( apiID.subId == "detail" )
				{
					path += "/"+params.teamCode;
				}
				break;

			case "SD0504": // 미수현황_유통본부
				path += "/mpcustrecord/"+params.strtDate +"/"+params.lastDate+"/"+params.teamCode;
				break;

			case "SD0505": // 미수현황_거래처
				path += "/mporderh/"+params.strtDate +"/"+params.lastDate+"/"+params.teamCode;
				break;

			case "SD0601": // 거래원장
				path += "/mporderd/"+params.sellDate +"/"+params.custCode;
				break;

			case "SD0602": // 매출장
				path += "/mporderd/"+params.strtDate +"/"+params.lastDate;
				break;

			case "SD0603": // 매출현황_유통경로
				Logger.debug("SD0603>>>");
				Logger.debug(apiID.subId);
				
				path += "/mpcustrecord/";
				if( apiID.subId == "detail" )
				{
					path += "tab1/" +params.strtDate +"/"+params.endDate +"/"+ params.partCode+ "/"+params.distCode;
				}
				else{
					path += "head/"+params.strtDate +"/"+params.endDate +"/"+ params.partCode;
					
				}
				break;
				//랜더러 적용 후 조회 분기처리 확인
				
				/*8
				path += "/mpcustrecord";
				if( apiID.subId != null )
				{
					if( apiID.subId == "resultBox.tabAreaBox.tab_result_view" )
					{
						//path += "/tab1/"+params.strtDate +"/"+params.lastDate +"/"+ params.teamCode +"/"+ params.distCode;
						path += "/tab1/"+params.strtDate +"/"+params.endDate +"/"+ params.partCode +"/"+ params.distCode;
					}
					else
					{
						//path += "/tab2/"+params.strtDate +"/"+params.lastDate +"/"+ params.teamCode +"/"+ params.distCode;
						path += "/tab2/"+params.strtDate +"/"+params.endDate +"/"+ params.partCode +"/"+ params.distCode;
					}
				}
				else{
					//path += "/head/"+params.strtDate +"/"+params.lastDate +"/"+ params.teamCode;
					path += "/head/"+params.strtDate +"/"+params.endDate +"/"+ params.partCode;
					
				}*/
				
				break;

			case "SD0604": // 매출현황_유통본부
				/*path += "/mpcustrecord/"+params.strtDate +"/"+params.lastDate +"/"+ params.teamCode;
				if( apiID.subId == "detail" )
				{
					path += "/"+params.custCode;
				}*/
				//랜더러 적용 후 조회 분기처리 확인
				
				Logger.debug("SD0604>>>");
				Logger.debug(apiID.subId);
				
				path += "/mpcustrecord/";
				if( apiID.subId == "detail" )
				{
					path += "tab1/" +params.strtDate +"/"+params.endDate +"/"+ params.partCode+ "/"+params.distCode;
				}
				else{
					path += "head/"+params.strtDate +"/"+params.endDate +"/"+ params.partCode;
					
				}
				break;
				
				
			case "SD0605": // 매출현황_영업파트
				path += "/mpcustrecord/"+params.strtDate +"/"+params.lastDate +"/"+ params.teamCode;
				if( apiID.subId == "detail" )
				{
					path += "/"+params.salesMan;
				}
				break;

			case "SD0606": // 판매추이_누계
				path += "/mpcustrecord/"+params.strtYyyy +"/"+params.teamCode +"/"+ params.salesMan +"/"+ params.custCode;
				break;

			case "SD0607": // 판매추이_월별
				path += "/mpcustrecord/"+params.strtYyyy +"/"+params.teamCode +"/"+ params.salesMan +"/"+ params.custCode;
				break;

			case "SD0608": // 반품현황

				path += "/mporderd/head/"+params.strtDate+"/"+params.lastDate+"/"+params.teamCode;

				if( apiID.subId == "totNoGrid" )
				{
					path += "/head/"+params.strtDate+"/"+params.lastDate+"/"+params.teamCode;
				}
				else if ( apiID.subId == "totTeGrid" )
				{
					path += "/tab1/"+params.strtDate+"/"+params.lastDate+"/"+params.teamCode;;
				}
				else if ( apiID.subId == "totUsGrid" )
				{
					path += "/tab2/"+params.strtDate+"/"+params.lastDate+"/"+params.teamCode;;
				}
				else if ( apiID.subId == "totDiGrid" )
				{
					path += "/tab3/"+params.strtDate+"/"+params.lastDate+"/"+params.teamCode;;
				}
				break;

			case "SD0609": // 제품매출_영업파트
				path += "/mporderd/"+params.strtDate +"/"+params.lastDate +"/"+ params.teamCode +"/"+ params.proCode +"/"+ params.searchCondition;
				break;

			case "SD0610": // 제품매출_거래처
				path += "/mporderd/"+params.strtDate +"/"+params.lastDate +"/"+ params.teamCode;
				break;

			case "SD0611": // 매출현황_거래처
				path += "/mporderd/"+params.strtDate +"/"+params.lastDate +"/"+ params.teamCode;

			case "SD0612": // 매출현황_신규거래처
				path += "/mporderd/"+params.strtDate +"/"+params.lastDate +"/"+ params.teamCode;
				break;
			case "SD0801": //  마감일자
				path += "/mpclosingdate/" + params.applyYyyy;
			break;
			case "SD0802": //  출고제한 /{startDate}/{lastDate}
				path += "/mpdelvlimit/"+params.startDate +"/"+params.lastDate;
			break;
			case "SD0905": // 공지사항
				path += "/mpnotify/"+params.openGubn;
				break;

			case "SD0911": // 단가조회
				path += "/mpstndprice/";
				break;


			case "SD1001": // 주문입력-고객포털
					// /rest	/mpm/1001/sd1001/mporderh	/{ordrCust}/{strtDate}/{lastDate}
					path += "/mporderh";
					if(apiID.subId == "tab_result")
					{
						// /{strtDate}/{lastDate}
						path += "/"+params.strtDate + "/"+params.lastDate;
					}
					else if(apiID.subId == "tab_result_detail")
					{
						// /items/{delvDate}/{ordrCust}/{ordrNo}
						path += "/items/"+params.delvDate + "/"+params.ordrCust + "/"+params.ordrNo;
					}
					else if(apiID.subId == "tab_form")
					{
						// 여신 조회
						// /credit/{ordrCust}/{strtDate}/{ordrAmt}
						path += "/credit/"+params.ordrCust + "/"+params.strtDate + "/"+params.ordrAmt;
					}
					else if(apiID.subId == "tab_result_edit")
					{
						// 재고 조회
						// /wms/{ordrCust}/{delvDate}/{delvDc}/{proCode}
						path += "/wms/"+params.ordrCust + "/"+params.delvDate + "/"+params.delvDc + "/"+params.proCode;
					}
				break;


			// 영업 모바일  - 모바일은 상세 페이지가 존재합니다. ######01로 추가
			case "SD9004": // 모바일 주문입력(영업사원용
				break;

			case "SD900401": // 모바일 주문입력(영업사원용 - 상세화면
				break;

			case "SD9005": // 모바일 주문입력(대리점주용
				break;

			case "SD900501": // 모바일 주문입력(대리점주용 - 상세화면
				break;

			case "SD9008": // 모바일 주문조회
				path += "/mporderd/"+params.delvDate;
				break;

			case "SD900801": // 모바일 주문조회 - 상세화면
				break;

			case "SD9006": // 모바일 출고승인요청
				break;

			case "SD900601": // 모바일 출고승인요청 - 상세화면
				break;

			case "SD9007": // 모바일 출고승인
				break;

			case "SD900701": // 모바일 출고승인 - 상세화면
				break;

			case "SD9009": // 모바일 재고조회1
				path += "/sddc/"+params.whcode;
				if( apiID.subId == "detail" )
				{
					path += "/"+params.proCode;
				}
				break;

			case "SD900901": // 모바일 재고조회1 - 상세화면
				break;

			case "SD9010": // 모바일 재고조회2
				break;

			case "SD9003": // 모바일 할인단가승인
				break;

			case "SD900301": // 모바일 할인단가승인 - 상세화면
				break;

			case "SD9011": // 모바일 단가조회
				break;

			case "SD901101": // 모바일 단가조회 - 상세화면
				break;

			default:
				Logger.warn("_getAPIPath4SD() - Unknown ID : "+apiID.mainId+", subId : "+apiID.subId);
				path = null;
				break;
			}

			return path;
		}
		,
		/**
		 * 생산 파트 API 경로를 구한다.
		 */
		_getAPIPath4PP: function(method, apiID, params)
		{
			var hosts = NDSProps.get("hosts");
			var user = NDSProps.get("user");
			var path = "/rest/mpm/"+this.get("corpCode")+"/"+apiID.mainId.toLowerCase();

			switch( apiID.mainId )
			{
			case "PP0101": // 부자재 출고 오류 현황
				// /tmplatxm/{strtDate}/{lastDate}
				path += "/tmplatxm";
				if( apiID.subId == "dup" )
				{
					path += "/dup";
				}
				break;

			case "PP0103":
				path += "/mpitemmasterm";
				/*
				 * Get 파라메터로 붙여 호출
				if( params.setYn )
				{
					path += "/"+params.setYn;
				}
				else
				*/
				if( apiID.subId == 'code' )
				{
					path += "/ALL/code";
				}
				break;

			case "PP0104": // 세트등록
				path += "/mpsetitemm";
				if( apiID.subId == 'detail' )
				{
					// /mpsetitemm/{setCode}
					path += "/"+params.setCode;
				}
				else
				{
					path += "/mpitemmasterm/Y";
				}
				break;

			case "PP0105": // BOM 등록
				if( apiID.subId == "detail" )
				{
					// /mpbomd/{plantCode}/{bomCode}/{bomVer}
					path += "/mpbomd/"+params.plantCode+"/"+params.bomCode+"/"+params.bomVer;
				}
				else if( apiID.subId == "dup" )
				{
					// /mpbomd/{plantCode}/{bomCode}/{bomVer}
					path += "/mpbomh/dup/"+params.plantCode+"/"+params.proCode;
				}
				else
				{
					path += "/mpbomh";
					if( apiID.subId == "history" )
					{
						// /mpbomh/hist
						path += "/hist";
					}
					else if( apiID.subId == "form" )
					{
						// /mpbomh/form/{plantCode}/{bomCode}
						path += "/form/"+params.plantCode+"/"+params.bomCode;
					}
				}
				break;

			case "PP0106": //BOM조회
				path += "/mpbomh";
				if( params.proCode.result.length > 0 )
				{
					path += "/"+params.proCode.result;
				}
				break;

			case "PP0107": //공장창고 매핑
				if( apiID.subId == 'left' )
				{
					path += "/tmplatxm";
				}
				else if( apiID.subId == 'right' )
				{
					path += "/tmplatwarhxm/"+params.plantCode;
				}

				break;

			case "PP0201": // 수율기초 등록
				if( apiID.subId == "plant" )
				{
					path += "/tmplatxm?grupPlant="+params.grupPlant;
				}
				else
				{
					// /{strtDate}/{lastDate}
					path += "/mpyieldinfom/"+params.strtDate+"/"+params.lastDate;
				}
				break;

			case "PP0202": // 생산수율표조회(일)
				path += "/mpyieldinfom";
				if( apiID.subId == "tabAreaBox.tab_result_1.grid" ){
					path += "/cm/ldetail/" + params.grupPlant+"/"+params.strtDate;
				}
				else if( apiID.subId == "tabAreaBox.tab_result_2.grid" ){
					path += "/cm/sdetail/" + params.grupPlant+"/"+params.strtDate;
				}
				else if( apiID.subId == "tabAreaBox.tab_result_3" ){
					path += "/pm/" + params.grupPlant+"/"+params.strtDate+"/"+params.lastDate;
				}
				else{
					path += "/cm/top/" + params.grupPlant+"/"+params.strtDate;
				}
				break;

			case "PP0203": // 생산수율표조회(기간별)
				path += "/mpyieldinfom";

				if( apiID.subId == "tabAreaBox.tab_result_1.grid" ){
					path += "/ldetail/";
				}
				else if( apiID.subId == "tabAreaBox.tab_result_2.grid" ){
					path += "/sdetail/";
				}
				else{
					path += "/top/";
				}

				path += params.junStrtDate+"/"+params.junLastDateBe+"/"+params.dangStrtDateNo+"/"+params.dangLastDateNo+"/"+params.grupPlant;
				break;

			case "PP0204": // 품목별 생산량 및 수율 조회


				if( method == "read" )
				{
					if( apiID.subId != 'resultBox.grid' )
					{
						path += "/mpyieldinfom/head/";
					}
					else{
						path += "/mpyieldinfom/detail/";
					}

					path += params.strtDate+"/"+params.grupPlant;

				}

				break;

			case "PP0301": // 생산계획 setup
				path += "/mpplansetupm";
				break;

			case "PP0302": // 생산계획 입력 CM
				// header, search, total, totlist, new, status
				apiID.subId || (apiID.subId = "search");
				path += "/mpplancmh/"+apiID.subId;
				if(apiID.subId == "header"
					|| apiID.subId == "search"
					|| apiID.subId == "new"
					|| apiID.subId == "remove"
					|| apiID.subId == "status"
					|| apiID.subId == "newprod")
				{
					if( params.plantCode )
					{
						path += "/"+params.plantCode;
					}
				}
				if( params.workDate )
				{
					path += "/"+params.workDate;
				}
				if(apiID.subId == "status")
				{
					path += "/"+params.status;
				}
				else if(apiID.subId == "newprod")
				{
					path += "/"+params.proCode;
				}
				break;

			case "PP0303": // 생산계획서 조회/발행 CM
				if( method == "read" )
				{
					if( apiID.subId == 'upGrid' )
					{
						// /mpplancmd/total/{plantCode}/{workDate}
						path += "/mpplancmd/total";
					}
					else
					{
						// /mpplanplatd/{plantCode}/{workDate}
						path += "/mpplanplatd";
					}
					path += "/"+params.plantCode+"/"+params.workDate;
				}
				else
				{
					// 생산계획 저장
				}
				break;

			case "PP0304": // 주문접수 PM
				// /mpacceptordrpmm/{plantCode}/{delvDate}/{status}
				path += "/mpacceptordrpmm/"+params.plantCode+"/"+params.delvDate+"/"+params.ordr_status;
				break;

			case "PP0305": // 생산계획입력(PM)
				path += "/mpplanpmm";
				if( apiID.subId == 'confirm' )
				{
					// 계획 확정
					path += "/complete"
				}
				else
				{
					// 조회 / 신규 / 수정
				}
				// /{plantCode}/{workDate}/{status}
				path += "/"+params.plantCode+"/"+params.workDate+"/"+params.status;
				break;

			case "PP0306": // 생산계획서 조회/발핼(PM)
				// /mpplanpmm/{plantCode}/{workDate}
				path += "/mpplanpmm/"+params.plantCode+"/"+params.workDate;
				if( apiID.subId == 'time' )
				{
					// /mpplanpmm/{plantCode}/{workDate}/time
					path += "/time";
				}
				break;

			case "PP0307": // 생산계획대비 실적현황 조회
				path += "/mpplanplatd/";
				if( apiID.subId == 'detail' )
				{
					path += "pop/"+params.plantCode+"/"+params.proCode + "/"+ params.proDate;
				}
				else{
					path += params.plantCode+"/"+params.strtDate+"/"+params.lastDate;
				}

				break;

			case "PP0401": //자재소요량 산출
				path += "/mpreqmtrlm";
				if( apiID.subId == 'calc' )
				{
					path += "/calc";
				}
				path += "/"+params.workDate+"/"+params.plantCode;
				if( params.forceD )
				{
					path += "/"+params.forceD;
				}
				break;

			case "PP0402": //자재소요량 조회
				path += "/mpreqmtrlm";
				if( apiID.subId == 'tab_result_2' )
				{
					path += "/tab2/";
				}
				else
				{
					path += "/tab1/";
				}
				path += params.strtDate+"/"+params.lastDate;
				break;

			case "PP0501": // 부자재 출고 오류 현황
				// /mpreqoutm/{strtDate}/{lastDate}
				path += "/mpreqoutm/"+params.strtDate+"/"+params.lastDate;
				break;

			case "PP0503": //생산 vs 입고현황 조회
				path += "/mpbarprom/"+params.strtDate+"/"+params.plantCode;
				if( apiID.subId == 'detail' )
				{
					path += "/"+params.proCode;
				}
				break;

			case "PP0504": //생산실적 조회 및 조정(개체)
				path += "/mpbarprom/"+params.strtDate+"/"+params.lastDate;
				if( params.deleType )
				{
					path += "/"+params.deleType;
				}
				break;

			case "PP0505": //PM라벨실적 입고요청(이시다)
				if( apiID.subId == 'detail' )
				{
					path += "/mpautototalm/"+params.plantCode+"/"+params.strtDate;
					// /detail/{proCode}
					path += "/detail/"+params.proCode;
				}
				else if( apiID.subId == "ipgo" )
				{
					// /ipgo
					//path += "/ipgo";

					// TODO WMS 서버 주소를 글로벌 변수로 추가해야한다.
					path = hosts.wms+"/rcpt/rcpt/rcptExc.do?method=excRcptComptR11ForScale&";
					path += "plant_code="+params.plantCode+"&work_date="+params.strtDate.replace(/-/ig, '')+"&userid="+user.id;
				}
				else if( apiID.subId == "ipgocheck" )
				{
					path += "/mpautototalm/"+params.plantCode+"/"+params.strtDate +"/ipgo/check";
				}
				else
				{
					path += "/mpautototalm/"+params.plantCode+"/"+params.strtDate;
				}
				break;
			case "PP0601": //공장별 농장별 생산현황조회
				path += "/mpbarprom/"+params.strtDate+"/"+params.lastDate+"/"+params.plantCode;
				break;

			case "PP0602": //공장별 생산현황 조회(집계)
				path += "/mpbarprom";
				if( apiID.subId == 'tabAreaBox.tab_pro' )
				{
					path += "/tab2/";
				}
				else
				{
					path += "/tab1/";
				}
				path += params.strtDate+"/"+params.lastDate + "/"+params.plantCode;

				Logger.debug("PP0602>>");
				Logger.debug(path);
				break;

			case "PP0603": //공장별 생산현황 조회(개체별)
				path += "/mpbarprom/"+params.strtDate+"/"+params.lastDate+"/"+params.plantCode;
				break;

			case "PP0604": //속라벨 발행현황 조회
				path += "/mpbarinprom/"+params.strtDate;
				break;

			case "PP0605": //원료별 생산일보 조회 디테일
				//원료별 생산일보 조회 헤더
				if( method == "read" )
				{
					if( apiID.subId != 'resultBox.grid' )
					{
						path += "/mpbarprom/head/"+params.strtDate+"/"+params.searchKeyword + "/"+params.plantCode;
					}
					else{
						path += "/mpbarprom/detail/"+params.strtDate+"/"+params.searchKeyword + "/"+params.plantCode;
					}
				}

				break;

			case "PP0701": //부자재 사용량 조회
				path += "/ododerhd/"+params.strtDate+"/"+params.lastDate;
				break;

			case "PP0702": //부자재 현재고 조회 (On-Hand)
				path += "/tmplatxm/"+params.grupPlant+"/"+params.proCode;
				break;

			case "PP0802": // 포장처리실적 신고
				path += "/mppigpackm";
				if( apiID.subId == "save" )
				{
					// /{packDate}/{sendType}/save
					path += "/"+params.packDate+"/"+params.sendType+"/save";
				}
				else if( apiID.subId == "save2" )
				{
					// /{packDate}/{sendType}/save
					path += "/"+params.packDate+"/"+params.sendType+"/save?forceD=Y";
				}
				else if( apiID.subId == "download" )
				{
					// /{packDate}/{sendType}/download
					path += "/"+params.packDate+"/"+params.sendType+"/download";
				}
				else if( apiID.subId == "send" )
				{
					path += "/openapi/send/"+params.packDate;
				}
				else
				{
					// /{packDate}/{sendType}
					path += "/"+params.packDate+"/"+params.sendType;
				}
				break;

			case "PP0803": // 거래내역실적 신고
				path += "/mppighissalem";
				if( apiID.subId == "save" )
				{
					// /{packDate}/{sendType}/save
					path += "/"+params.saleDate+"/"+params.sendType+"/save";
				}
				else if( apiID.subId == "save2" )
				{
					// /{packDate}/{sendType}/save
					path += "/"+params.saleDate+"/"+params.sendType+"/save?forceD=Y";
				}
				else if( apiID.subId == "download" )
				{
					// 신고 파일 생성
					// //{saleDate}/{sendType}/download
					path += "/"+params.saleDate+"/"+params.sendType+"/download";
				}
				else if( apiID.subId == "send" )
				{
					// 실적 신고하기
					// /openapi/send/{saleDate}/{custCode}
					path += "/openapi/send/"+params.saleDate+"/"+params.sendType;
				}
				else if( apiID.subId == "sales" )
				{
					// 매출장 조회
					// /sales/{saleDate}/{custCode}
					path += "/sales/"+params.saleDate;
				}
				else if( apiID.subId == "salestot" )
				{
					path += "/salestot/"+params.saleDate;
				}
				else
				{
					// /{saleDate}/{sendType}
					path += "/"+params.saleDate+"/"+params.sendType;
				}
				break;

			case "PP0801": // 매입실적 신고
				path += "/mppighisbuym";
				if( apiID.subId == "excel" )
				{
					// 등급 판정 파일 불러오기
					path += "/"+params.buyDate+"/"+params.buyType+"/upload";
				}
				else if( apiID.subId == "save" )
				{
					// 신고 파일 생성
					// /{buyDate}/{buyType}/save
					path += "/"+params.buyDate+"/"+params.buyType+"/save?forceD="+params.forceD;
				}
				else if( apiID.subId == "download" )
				{
					// 신고 파일 생성
					// /{buyDate}/{buyType}/download
					path += "/"+params.buyDate+"/"+params.buyType+"/download";
				}
				else if( apiID.subId == "openapi" )
				{
					// 실적 신고하기
					// /openapi/send/{buyDate}/{buyType}
					path += "/openapi/send/"+params.buyDate+"/"+params.buyType;
				}
				else
				{
					// /{buyDate}/{buyType}
					path += "/"+params.buyDate+"/"+params.buyType;
				}
				break;

			case "PP0902": // 생산실적 집계
				path += "/mpbarprom/"+params.strtDate+"/"+params.lastDate+"?plantCode="+params.plantCode;
				break;

			default:
				Logger.warn("_getAPIPath4PP() - Unknown ID : "+apiID.mainId+", subId : "+apiID.subId);
				path = null;
				break;
			}

			return path;
		}
		,
		/**
		 * 구매 파트 API 경로를 구한다.
		 */
		_getAPIPath4PO: function(method, apiID, params)
		{
			var path = "/rest/mpm/"+this.get("corpCode")+"/"+apiID.mainId.toLowerCase();

			switch( apiID.mainId )
			{
			case "PO0101": // 지육시세등록
				if( apiID.subId == "quotation" )
				{
					// 등록된 지육시세 조회
					// /ifpiggxm/{strtDate}/{lastDate}/{skinType}
					path += "/ifpiggxm";
				}
				else if( apiID.subId == "excel" )
				{
					// Open API 로 부터 취득된 지육시세 조회
					// /ifpiggxm/{strtDate}/{lastDate}/{skinType}
					//path += "/ifpiggxm";
					path += "/mppigixm/upload/" + params.strtDate;
				}
				else
				{
					// /mppigixm/{strtDate}/{lastDate}/{skinType}
					path += "/mppigixm";
				}
				if( apiID.subId == "excel" )
				{
					path += "/"+params.skinType;
				}
				else
				{
					path += "/"+params.strtDate+"/"+params.lastDate+"/"+params.skinType;
				}

				break;
			case "PO0301": // 이상육 발생현황 관리
					// /mpm/{corpCode}/po0301/mppigoxm	/tab1/{dochDate}
					path += "/mppigoxm/tab1/"+params.dochDate;
					break;

			case "PO0302": // 이상육 발생현황 조회
				// /{custCode}/{strtDate}/{lastDate}
				path += "/mppigoxm";

				if( apiID.subId == 'tab_01' )
				{
					path += "/tab1/";
				}
				else
				{
					path += "/tab2/";
				}

				path += params.brandCode+"/"+params.strtDate+"/"+params.lastDate;

//				path += params.brandCode+"/"+params.othPart+"/"+params.custCode+"/"+params.strtDate+"/"+params.lastDate;
				//path += "/mppigoxm/tab1/{"+params.custCode+"/"+params.strtDate+"/"+params.lastDate;
				break;

			case "PO0102": // 등판자료등록
				if( apiID.subId == "excel" )
				{
					path += "/excel/upload";
				}
				else
				{
					// /mppigdxm/{dochDate}
					path += "/mppigdxm/"+params.dochDate;
					if( params.force == "true" )
					{
						path += "?force=true";
					}
				}
				break;

			case "PO0201": // 생돈구매세부입력
				path += "/mppigdxm/"+params.strtDate+"/"+params.lastDate;
				if( apiID.subId == "detail" )
				{
					path += "/"+params.hisNo;
				}
				break;

			case "PO0202": // 생돈정산
				path += "/mppigexm";
				if( apiID.subId == "methodCombo" )
				{
					path += "/code/"+params.facKind;
				}
				else if( apiID.subId == "jiyuk" )
				{
					path += "/jiyuk/"+params.startDate+"/"+params.endDate;
				}
				else if( apiID.subId == "avgPrice" )
				{
					path += "/jiyuk/"+params.facKind+"/"+params.startDate+"/"+params.endDate;
				}
				else if( apiID.subId == "adjust" )
				{
					path += "/adjust/"+params.facKind+"/"+params.facCode+"/"+params.startDate+"/"+params.endDate;
				}
				else if( apiID.subId == "recent" )
				{
					path += "/recent/"+params.facKind;
				}
				else
				{
					path += "/search/"+params.startDate+"/"+params.endDate;
				}

				break;

			case "PO0203": // 출하정산집계표조회
				// /mppigdxm/{facKind}/{buyType}/{brandCode}/{custCode}/{strtDate}/{lastDate}/{gubun_1}/{gubun_2}/{gubun_3}/{gubun_4}/{strtDate}/{lastDate}
				path += "/mppigdxm/"+params.strtDate+"/"+params.lastDate;
				//path += "/mppigdxm/"+params.facKind+"/"+params.buyType+"/"+params.brandCode+"/"+params.custCode+"/"+params.strtDate+"/"+params.lastDate+"/"+params.gubun_1+"/"+params.gubun_2+"/"+params.gubun_3+"/"+params.gubun_4;
				break;

			case "PO0204": // 생돈지급율조회
				// /mppigdxm//{custCode}/{strtDate}/{lastDate}

				path += "/mppigdxm/"+params.custCode+"/"+params.strtDate+"/"+params.lastDate;
				break;

			case "PO0205": // 더느림지급액조회
				// /mppigdxm/{buyDateM}
				path += "/mppigdxm/"  +params.buyDateM;
				break;

			default:
				Logger.warn("_getAPIPath4PO() - Unknown ID : "+apiID.mainId+", subId : "+apiID.subId);
				path = null;
				break;
			}

			return path;
		}
		,
		/**
		 * 지정한 조건으로 API 로 생성 요청을 보낸다.
		 *
		 * @param {object} data - 조회 요청 조건. url 파라메터로 사용된다.
		 * @param {string||object} apiID - API 식별자. 생략되는 경우 초기화시 설정된 contentId 값이 사용된다.
		 * 							object 인 경우 { mainId:{string}, subId:{string} } 로 지정된다.
		 * @return {$.xhr}
		 */
		create: function(data, apiID)
		{
			return this._post( this.getAPIPath('create', apiID, data), data );
		}
		,
		/**
		 * 지정한 조건으로 API 로 조회 요청을 보낸다.
		 *
		 * @param {object} data - 조회 요청 조건. url 파라메터로 사용된다.
		 * @param {string||object} apiID - API 식별자. 생략되는 경우 초기화시 설정된 contentId 값이 사용된다.
		 * 							object 인 경우 { mainId:{string}, subId:{string} } 로 지정된다.
		 * @return {$.xhr}
		 */
		read: function(data, apiID)
		{
			return this._get( this.getAPIPath('read', apiID, data), data );
		}
		,
		/**
		 * @param {object} data - 수정 조건.
		 * @param {string||object} apiID - API 식별자. 생략되는 경우 초기화시 설정된 contentId 값이 사용된다.
		 * 							object 인 경우 { mainId:{string}, subId:{string} } 로 지정된다.
		 * @return {$.xhr}
		 */
		update: function(data, apiID)
		{
			return this._put( this.getAPIPath('update', apiID, data), data );
		}
		,
		/**
		 * @param {object} data - 삭제 조건.
		 * @param {string||object} apiID - API 식별자. 생략되는 경우 초기화시 설정된 contentId 값이 사용된다.
		 * 							object 인 경우 { mainId:{string}, subId:{string} } 로 지정된다.
		 * @return {$.xhr}
		 */
		"delete": function(data, apiID)
		{
			return this._delete( this.getAPIPath('delete', apiID, data), data );
		}
		,
		/**
		 * 지정한 API 로 트랜잭션 처리를 요청한다.
		 */
		transaction: function(api, items)
		{
			return this._post(api, items);
		}
	});

	return OpenMPSAPIClient;
});
