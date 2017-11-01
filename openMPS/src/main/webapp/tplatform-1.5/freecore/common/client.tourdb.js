/**
 * Project : MobileOven
 *
 * Copyright (c) 2016 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman 
 */

define(
[
	"ClientBase", "AResultClasses", "Logger"
]
,
function(ClientBase, AResult, Logger)
{
	/**
	 * 한국관광공사가 제공하는 TourAPI 의 Client 를 구현한다.
	 */
	var TourDBClient = ClientBase.extend(
	{
		/**
		 * TourAPI 사용을 위한 초기화를 진행한다.
		 * 
		 * @param options		{
		 * 							apiKey : "공공데이타포털에서 발급 받은 인증키",
		 * 							langType : 서비스 언어 타입. TourDBClient.LANG_TYPE 의 상수값을 지정한다,
		 * 							os : 모바일 OS 유형을 지정한다,
		 * 							appName : client 이름을 지정한다
		 * 						}
		 */
		initialize: function(options)
		{
			TourDBClient.__super__.initialize.apply( this, arguments );
			
			if( this.get("apiKey") == undefined )
			{
				Logger.error("TourDBClient.initialize() - You need to pass a parameter [service_key].");
				return;
			}
			if( this.get("langType") == undefined )
			{
				TourDBClient.initContentType( TourDBClient.LANG_TYPE.KOR );
				this.set("langType", TourDBClient.LANG_TYPE.KOR);
			}
			else
			{
				TourDBClient.initContentType( this.get("langType") );
			}
			if( this.get("os") == undefined )
			{
				this.set("os", "ETC");
			}
			if( this.get("appName") == undefined )
			{
				this.set("appName", "MobileOven");
			}
			
			Logger.debug("TourDBClient.initialize() - seed : "+JSON.stringify(this.toJSON()));
		}
		,
		setLanguage: function(langType)
		{
			this.set("langType", langType);
		}
		,
		/**
		 * 서비스에 종속적인 코드값을 조회한다.
		 * 
		 * @param options	코드 조회 조건을 지정한다.
		 * 					지역코드 조회인 경우에는 areaCode 에 null 을 지정하고, 시군 코드 조회의 경우에는 areaCode 을 지정한다.
		 * 					{
		 * 						areaCode: 지역 코드
		 * 					}
		 * 					대분류 코드 조회의 경우에는 cat1 에 null 을 지정하고, 중분류 코드 조회의 경우 cat1 를 지정하고, 소분류 코드 조회의 경우 cat1, cat2 를 지정한다.
		 * 					{
		 * 						cat1: 대분류 코드,
		 * 						cat2: 중분류 코드
		 * 					}
		 * @return { $.promise }
		 */
		getCodes: function(options)
		{
			var apiPath;
			
			if( typeof options.areaCode == 'object' || typeof options.areaCode == 'string' )
			{
				// 지역코드 조회 모드
				apiPath = TourDBClient.getAPIPath( this.get("langType"), TourDBClient.CMD_TYPE.AREA );
				
				if( options.areaCode == null )
				{
					delete options.areaCode;
				}
			}
			else if( typeof options.cat1 == 'object' || typeof options.cat1 == 'string' )
			{
				// 서비스 분류 코드 조회 모드
				apiPath = TourDBClient.getAPIPath( this.get("langType"), TourDBClient.CMD_TYPE.CATEGORY );
				
				if( options.cat1 == null )
				{
					delete options.cat1;
				}
			}
			else
			{
				Logger.warn("TourDBClient.getCodes() - Invalid parameter");
				return TourDBClient.promiseResult( AResult.Helper.INVALID_PARAMETERS("Invalid parameter").toJSON() );
			}
			
			return this._fetch( 
					this._appendPageInfo(
							this._appendParameters( apiPath, options )
							, "999") );
		}
		,
		/**
		 * 지정된 조건의 관광정보를 조회한다.
		 * 페이지 내의 콘텐츠 개수가 100 개를 넘어가는 경우 extraData.items.item 에 2차원 배열로 담겨 반환된다.
		 * 100 개 내인 경우에는 extraData.items.item 에 1차원 배열로 담겨서 반환된다.
		 * 1 개인 경우에는 extraData.items.item 에 바로 담겨서 반환된다.
		 * 
		 * 파라메터 지정 방식에 대해서는 TourAPI 매뉴얼을 참고한다.
		 * @see http://api.visitkorea.or.kr/openAPI/tourAPI/koreaGuide.do?menuCode=06
		 * 
		 * @param options	검색 조건을 지정한다.
		 * 					{
		 * 						contentTypeId: CONTENT_TYPE 의 값을 지정한다. 지정하지 않으면 모든 콘텐츠 유형이 조회된다,
		 * 						areaCode: 지역 코드. getCodes() 로 조회,
		 * 						sigunguCode: 시군 코드. getCodes() 로 조회,
		 * 						keyword: 특정 키워드와 관련된 정보 조회시 지정한다.
		 * 					}
		 * 					필요시 다음의 파라메터를 추가로 지정할 수 있다.
		 * 					{
		 * 						numOfRows: 기본값 10,
		 * 						pageNo: 기본값 1,
		 * 						arrange: 기본값 A, (A=제목순, B=조회순, C=수정일순, D=생성일순) 대표이미지 정렬 추가 (O=제목순, P=조회순, Q=수정일순, R=생성일순)
		 * 						listYN: 기본값 Y, 목록구분(Y=목록, N=개수)
		 * 					}
		 * @return { $.promise }
		 */
		getContents: function(options)
		{
			var apiPath = null;
			
			if(typeof options.keyword == 'string')
			{
				options.keyword = encodeURIComponent(options.keyword);
				apiPath = TourDBClient.getAPIPath( this.get("langType"), TourDBClient.CMD_TYPE.SEARCH );
			}
			else
			{
				apiPath = TourDBClient.getAPIPath( this.get("langType"), TourDBClient.CMD_TYPE.AREABASE );
			}
			
			//
			if(typeof options.contentTypeId == 'string')
			{
				options.contentTypeId = TourDBClient.CONTENT_TYPE[options.contentTypeId];
			}

			return this._fetch(this._appendParameters( apiPath, options ));
		}
		,
		/**
		 * 지정한 관광정보의 공통정보(제목, 연락처, 주소, 좌표, 개요정보 등)를 조회한다.
		 * 
		 * 파라메터 지정 방식에 대해서는 TourAPI 매뉴얼을 참고한다.
		 * @see http://api.visitkorea.or.kr/openAPI/tourAPI/koreaGuide.do?menuCode=09
		 * 
		 * @param conId		getContents() 에서 조회된 콘텐츠의 식별자
		 * @param conType	필요한 경우 CONTENT_TYPE 의 값을 지정한다.
		 * @param options	추가적인 상세 설정 파라메터. 필요한 경우에만 지정한다.
		 * 					{
		 * 						defaultYN: 기본정보조회 플레그 기본값은 N, 제목, 등록일, 수정일, 홈페이지, 전화번호, 전화번호명, 교과서속여행지여부
		 * 						firstImageYN: 대표이미지조회 플레그 기본값은 N, 원본, 썸네일대표이미지
		 * 						areacodeYN: 지역코드조회 플레그 기본값은 N,
		 * 						catcodeYN: 서비스분류코드조회 플레그 기본값은 N,
		 * 						addrinfoYN: 주소조회 플레그 기본값은 N,
		 * 						mapinfoYN: 좌표조회 플레그 기본값은 N,
		 * 						overviewYN: 개요조회 플레그 기본값은 N
		 * 					}
		 * @return { $.promise }
		 */
		getDetailCommon: function(conId, conType, options)
		{
			var apiPath = TourDBClient.getAPIPath( this.get("langType"), TourDBClient.CMD_TYPE.COMMON );
			
			if( options == undefined )
			{
				options = {
					defaultYN: 'Y',
					firstImageYN: 'Y',
					overviewYN: 'Y'
				};
			}
			
			return this._fetch( 
					this._appendParameters( 
							apiPath, 
							_.extend(
									{ contentId: conId, contentTypeId: conType }
									, options) ) );
		}
		,
		/**
		 * 지정한 관광정보의 소개정보(휴무일, 개장시간, 주차시설 등)를 조회한다.
		 * 
		 * @param conId		getContents() 에서 조회된 콘텐츠의 식별자
		 * @param conType	필요한 경우 CONTENT_TYPE 의 값을 지정한다.
		 * @return { $.promise }
		 */
		getDetailIntro: function(conId, conType)
		{
			var apiPath = TourDBClient.getAPIPath( this.get("langType"), TourDBClient.CMD_TYPE.INTRO );

			return this._fetch( 
					this._appendParameters( 
							apiPath, { contentId: conId, contentTypeId: conType }) );			
		}
		,
		/**
		 * 지정한 관광정보의 타입별 반복정보를 조회한다.
		 * “숙박”은 객실정보를 제공합니다.
		 * “여행코스”는 코스정보를 제공합니다.
		 * “숙박, 여행코스”를 제외한 나머지 타입은 다양한 정보를 반복적인 형태로 제공합니다.
		 * 
		 * @param conId		getContents() 에서 조회된 콘텐츠의 식별자
		 * @param conType	필요한 경우 CONTENT_TYPE 의 값을 지정한다.
		 * @return { $.promise }
		 */
		getDetailInfo: function(conId, conType)
		{
			var apiPath = TourDBClient.getAPIPath( this.get("langType"), TourDBClient.CMD_TYPE.INFO );
			
			return this._fetch( 
					this._appendParameters( 
							apiPath, { contentId: conId, contentTypeId: conType }) );
		}
		,
		/**
		 * 지정한 관광정보의 각 관광타입(관광지, 숙박 등)에 해당하는 이미지URL 목록을 조회한다.
		 * 
		 * @param conId		getContents() 에서 조회된 콘텐츠의 식별자
		 * @param conType	필요한 경우 CONTENT_TYPE 의 값을 지정한다.
		 * @param options	추가적인 상세 설정 파라메터. 필요한 경우에만 지정한다.
		 * 					{
		 * 						numOfRows : {number}
		 * 						pageNo : {number}
		 * 					}
		 * @return { $.promise }
		 */
		getDetailImage: function(conId, conType, options)
		{
			var apiPath = TourDBClient.getAPIPath( this.get("langType"), TourDBClient.CMD_TYPE.IMAGES );
			
			return this._fetch(
					this._appendParameters( apiPath, 
							_.extend({ contentId: conId, contentTypeId: conType }
							, options) ) );
		}
		,
		_appendKey: function(apiPath)
		{
			return apiPath+"?ServiceKey="+this.get("apiKey");
		}
		,
		/**
		 * API 에 파라메터를 추가한다.
		 */
		_appendParameters: function(apiPath, params)
		{
			var pairs = _.pairs(params);
			var urlParams = "";
			
			for(var o in pairs)
			{
				var item = pairs[o];
				urlParams += "&"+item[0]+"="+item[1];
			}
			
			return this._appendKey(apiPath)+urlParams;
		}
		,
		/**
		 * 요청 페이지 정보를 추가한다.
		 * _appendParameters() 다음으로 호출한다.
		 * 
		 * @param apiPath	{string}
		 * @param numOfRows	{string}
		 * @param pageNo	{string}
		 */
		_appendPageInfo: function(apiPath, numOfRows, pageNo)
		{
			if(numOfRows != undefined)
			{
				apiPath += "&numOfRows="+numOfRows;	
			}
			if(pageNo != undefined)
			{
				apiPath += "&pageNo="+pageNo;	
			}
			
			return apiPath;
		}
		,
		/**
		 * Tour API 에서 정보를 요청한다.
		 */
		_fetch: function(apiPath)
		{
			return this._get( apiPath+"&MobileOS="+this.get("os")+"&MobileApp="+this.get("appName")+"&_type=json" )
			.then(function(data)
			{
				var result = new TourAPIResponse( data.response );
		    	Logger.debug(result.toJSON());
		    	return result;
			});
		}
	}
	,
	{
		PATH_TOURAPI: "http://api.visitkorea.or.kr/openapi/service/rest"
		,
		// 지원되는 언어 서비스 식별자
		LANG_TYPE: {
			KOR: "KorService",
			ENG: "EngService",
			JPN: "JpnService",
			CHS: "ChsService",
			CHT: "ChtService",
			GER: "GerService",
			FRE: "FreService",
			SPN: "SpnService",
			RUS: "RusService"
		}
		,
		CMD_TYPE: {
			AREA: "areaCode",
			CATEGORY: "categoryCode",
			AREABASE: "areaBasedList",
			LOCATIONBASED: "locationBasedList",
			SEARCH: "searchKeyword",
			COMMON: "detailCommon",
			INTRO: "detailIntro",
			INFO: "detailInfo",
			IMAGES: "detailImage"
		}
		,
		getAPIPath: function(langType, cmd)
		{
			return TourDBClient.PATH_TOURAPI+"/"+langType+"/"+cmd;
		}
		,
		// TourDBClient.initContentType() 가 호출되면, 유효한 콘텐츠 식별자가 셋된다.
		CONTENT_TYPE: {
			// 관광지
			TourAttractions: 0,
			// 문화시설
			CulturalFacilities: 0,
			// 행사공연축제
			FestivalsEventsPerformances: 0,
			// 여행코스
			Transportation: 0,
			// 레포츠
			LeisureSports: 0,
			// 숙박
			Accommodation: 0,
			// 쇼핑
			Shopping: 0,
			// 음식점
			Dining: 0
		}
		,
		// 언어 서비스에 맞는 콘텐츠 식별자로 초기화한다.
		initContentType: function(langType)
		{
			if(langType === TourDBClient.LANG_TYPE.KOR)
			{
				TourDBClient.CONTENT_TYPE = 
				{
					// 관광지
					TourAttractions: 12,
					// 문화시설
					CulturalFacilities: 14,
					// 행사공연축제
					FestivalsEventsPerformances: 15,
					// 여행코스
					Transportation: 25,
					// 레포츠
					LeisureSports: 28,
					// 숙박
					Accommodation: 32,
					// 쇼핑
					Shopping: 38,
					// 음식점
					Dining: 39
				};
			}
			else
			{
				TourDBClient.CONTENT_TYPE = 
				{
					TourAttractions: 76,
					CulturalFacilities: 78,
					FestivalsEventsPerformances: 85,
					Transportation: 77,
					LeisureSports: 75,
					Accommodation: 80,
					Shopping: 79,
					Dining: 82
				};
			}
			
			Logger.debug("initContentType() - langType : "+langType);
			Logger.debug(TourDBClient.CONTENT_TYPE);
		}
		,
		/**
		 * TourAPI 결과값을 AResult 형식으로 변환한다.
		 * 
		 * @param data	TourAPI 의 반환값
		 * @return {AResult.ResultEx}
		 */
		parseResponse: function(data)
		{
			if( data.response.header.resultMsg == 'OK' )
			{
				return AResult.Helper.SUCCESS( data.response.body );
			}
			else
			{
				return AResult.Helper.ERROREX( data.response );
			}
		}
	});
	
	/**
	 * TourAPI 의 Response 값에 대한 접근을 지원한다.
	 */
	var TourAPIResponse = Backbone.Model.extend(
	{
		defaults:
		{
			header: {},
			body: {}
		}
		,
		initialize: function(options)
		{
			TourAPIResponse.__super__.initialize.apply( this, arguments );
			
			if( this.get('response') != undefined )
			{
				// 상위 값으로 초기호된 경우, response 구조의 내용으로 재설정한다.
				this.reset(this.get('response'));
			}
		}
		,
		isSuccess: function()
		{
			return this.get('header').resultMsg == 'OK';
		}
		,
		getResultCode: function()
		{
			return this.get('header').resultCode;
		}
		,
		getResultMsg: function()
		{
			return this.get('header').resultMsg;
		}
		,
		getPageInfo: function()
		{
			var body = this.get('body');
			
			return {
				numOfRows: body.numOfRows,
				pageNo: body.pageNo,
				totalCount: body.totalCount,
			};
		}
		,
		getContents: function()
		{
			return this.get('body');
		}
		,
		/**
		 * TourAPI Response 객체를 AResult.ResultEx 객체로 변환환다.
		 * 
		 * @return {AResult.ResultEx}
		 */
		getResultEx: function()
		{
			if( this.isSuccess() )
			{
				return AResult.Helper.SUCCESS( this.get('body') ).toJSON();
			}
			else
			{
				return AResult.Helper.ERROREX( this.toJSON() ).toJSON();
			}
		}
	});
	TourDBClient.Response = TourAPIResponse;
	
	return TourDBClient;
});