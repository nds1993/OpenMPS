/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2009-6-11 10:23:05
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* CHANGE REVISION
* ------------------------------
* AUTHOR   : NDS
* DATE     : 2009-6-11 10:23:05
* REVISION : 1.0   First release.
* -------------------------------
* CLASS DESCRIPTION
* -------------------------------
*
*/
package nds.mpm.api.openApi.service;


public class OpenapiVO {

    private static String strtDate;   // 시작일
    private String lastDate;   // 종료일
	
    /* 돼지도체 경락가격  */
    private String gradeType;  // 등급구분
    private String startYmd;   // 시작일
    private String endYmd;     // 종료일
    private String gradeCd;    // 등급코드
    private String gradeNm;    // 등급명
    private String skinYn;     // 박피/탕박구분코드
    private String skinNm;     // 박피/탕박구분
    private String judgeSexCd; // 성별코드
    private String judgeSexNm; // 성별

    private String c_0101Amt;  // 가격(농협서울)
	private String c_0101Cnt;  // 두수(농협서울)
    private String c_0320Amt;  // 가격(openMPS)
    private String c_0320Cnt;  // 두수(openMPS)
    private String c_0302Amt;  // 가격(협신식품)
    private String c_0302Cnt;  // 두수(협신식품)
    private String c_1301Amt;  // 가격(삼성식품)
    private String c_1301Cnt;  // 두수(삼성식품)
    private String c_0323Amt;  // 가격(농협부천)
    private String c_0323Cnt;  // 두수(농협부천)
    private String CCityAmt;   // 가격(수도권)
    private String CCityCnt;   // 두수(수도권)

    private String c_0513Amt;  // 가격(농협음성)
    private String c_0513Cnt;  // 두수(농협음성)
    private String c_1005Amt;  // 가격(김해축공)
    private String c_1005Cnt;  // 두수(김해축공)
    private String c_0202Amt;  // 가격(부경축공)
    private String c_0202Cnt;  // 두수(부경축공)
    private String c_0201Amt;  // 가격(동원산업)
    private String c_0201Cnt;  // 두수(동원산업)
    private String c_1201Amt;  // 가격(신흥산업)
    private String c_1201Cnt;  // 두수(신흥산업)
    private String c_0905Amt;  // 가격(농협고령)
    private String c_0905Cnt;  // 두수(농협고령)
    private String CEastAmt;   // 가격(영남권)
    private String CEastCnt;   // 두수(영남권)

    private String c_0714Amt;      // 가격(익산)
    private String c_0714Cnt;      // 두수(익산)
    private String c_0809Amt;      // 가격(농협나주)
    private String c_0809Cnt;      // 두수(농협나주)
    private String c_1401Amt;      // 가격(삼호축산)
    private String c_1401Cnt;      // 두수(삼호축산)
    private String CWestAmt;       // 가격(호남권)
    private String CWestCnt;       // 두수(호남권)

    private String c_1101Amt;      // 가격(제주축협)
    private String c_1101Cnt;      // 두수(제주축협)
    private String CTotAmt;        // 가격(전체)
    private String CTotCnt;        // 두수(전체)
    private String c_1101eTotAmt;  // 가격(제주제외전체)
    private String c_1101eTotCnt;  // 두수(제주제외전체)
    
    private String resultcode;     // 가격(제주제외전체)
    private String resultmsg;      // 두수(제주제외전체)

    /* 이력제 traceNoType : PIG/PIG_NO  */
    /* infoType : 1 */
    private String traceNoType;       // 구분
	private String infoType;          // infoType
    private String farmAddr;          // 농장소재지
    private String farmerNm;          // 농장경영자
    private String farmUniqueNo;      // 농장식별번호
    private String pigNo;             // 이력번호
    /* infoType : 3 */
    private String butcheryPlaceAddr; // 도축장 주소
    private String butcheryPlaceNm;   // 농장소재지
    private String butcheryYmd;       // 농장경영자
    private String inspectPassYn;     // 농장식별번호
    /* infoType : 4 */
    private String processPlaceAddr;  // 포장처리업소 주소
	private String processYmd;        // 포장처리일자

    /* 이력제 traceNoType : PIG/LOT_NO  */
    /* infoType : 8 */
    private String corpNo;			  // 사업자번호
    private String lotNo;  			  // 묶음번호
    private String processPlaceNm;    // 포장처리업소명
    /* infoType : 9 */

    /* 축산물 돼지 등급판정결과(확인서) 정보 */
    private String issueNo;           // 발급번호
	private String issueDate;         // 발급일자
    private String judgeKindCd;       // 농장소재지
    private String judgeKindNm;       // 축종명
    private String raterCode;         // 평가사코드
    private String raterNm;           // 평가사명
	private String abattCode;         // 도축장코드
    private String mgrAbattNm;        // 도축장관리지원
    private String abattAddr;         // 도축장주소
    private String abattTelNo;        // 도축장전화번호
    private String reqName;           // 요청자
	private String reqRegNo;          // 요청사업자번호
    private String reqComName;        // 요청사업장
    private String reqAddr;           // 요청자주소
    private String reqTelno;          // 요청자전화번호
    private String judgeDate;         // 등급판정일자
	private String abattNo2;          // 도축번호
    private String abattWeight;       // 도체중량
    private String totalWeight;       // 총중량
	private String totalIssueCnt;     // 확인서갯수
    
    /* 축산물 돼지 등급판정결과(확인서 상세) 정보 */
    private String meatType;          // 부위구분
	private String cutmeatCode;       // 부위코드
    private String cutmeatName;       // 부위명
    private String cutmeatWeight;     // 부위중량
    private String cutmeatRate;       // 비율
    
    /* 축산물 실시간 돼지도체 등급별경매현황정보 */
    private String auctDate;          // 경매일자
    private String abattNm;           // 도매시장명
    private String auct_1Amt;         // 1+등급 경매 가격
    private String auct_2Amt;         // 1등급 경매 가격
    private String auct_3Amt;         // 2등급 경매 가격
    private String auctEAmt;          // 등외등급 경매 가격
	private String auctEExceptAmt;    // 등외제외 평균 경매 가격
    private String totalAuctAmt;      // 평균 경매 가격
    private String totalAuctCnt;      // 경매 두수

    /* 축산물 실시간 돼지도체 등급별경매현황정보(상세) */
    private String abattDate;         // 도매시장명
    private String abattNo;           // 1+등급 경매 가격
    private String auctRank;          // 등외등급 경매 가격
	private String auctTime;          // 등외제외 평균 경매 가격
    private String etc;               // 평균 경매 가격
    private String skinYnNm;          // 1+등급 경매 가격
    private String leftWeight;        // 등외등급 경매 가격
	private String rightWeight;       // 등외제외 평균 경매 가격
    private String auctAmt;           // 평균 경매 가격
    private String weight;            // 경매 두수

    /* 돼지 권역별 경락가격현황  */
    private String baseYmd;           // 기준연월일
    private String beforeYmd;         // 기준연월일 이전 경매연월일
    private String localNm;           // 권역명
	private String localCode;         // 권역코드
    private String auctCnt;           // 경매 두수
    private String diffAuctAmt;       // 전일 경매 금액

    /* 로그테이블 insert  */
    private String offi_syst;           // 기준연월일
    private String intr_usid;         // 기준연월일 이전 경매연월일
    private String intr_name;           // 권역명
	private String intr_date;         // 권역코드

    /* 축산물등급판정확인서  발급번호정보  */
    private String animalNo;           // 이력번호
    
	/* 돼지 고기 경락가격  */
    public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getResultmsg() {
		return resultmsg;
	}
	public void setResultmsg(String resultmsg) {
		this.resultmsg = resultmsg;
	}
	public String getGradeType() {
		return gradeType;
	}
	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}
	public String getStartYmd() {
		return startYmd;
	}
	public void setStartYmd(String startYmd) {
		this.startYmd = startYmd;
	}
	public String getEndYmd() {
		return endYmd;
	}
	public void setEndYmd(String endYmd) {
		this.endYmd = endYmd;
	}
	public String getGradeCd() {
		return gradeCd;
	}
	public void setGradeCd(String gradeCd) {
		this.gradeCd = gradeCd;
	}
	public String getGradeNm() {
		return gradeNm;
	}
	public void setGradeNm(String gradeNm) {
		this.gradeNm = gradeNm;
	}
	public String getJudgeSexCd() {
		return judgeSexCd;
	}
	public void setJudgeSexCd(String judgeSexCd) {
		this.judgeSexCd = judgeSexCd;
	}
	public String getJudgeSexNm() {
		return judgeSexNm;
	}
	public void setJudgeSexNm(String judgeSexNm) {
		this.judgeSexNm = judgeSexNm;
	}
	public String getC_0101Amt() {
		return c_0101Amt;
	}
	public void setC_0101Amt(String c_0101Amt) {
		this.c_0101Amt = c_0101Amt;
	}
	public String getC_0101Cnt() {
		return c_0101Cnt;
	}
	public void setC_0101Cnt(String c_0101Cnt) {
		this.c_0101Cnt = c_0101Cnt;
	}
	public String getC_0320Amt() {
		return c_0320Amt;
	}
	public void setC_0320Amt(String c_0320Amt) {
		this.c_0320Amt = c_0320Amt;
	}
	public String getC_0320Cnt() {
		return c_0320Cnt;
	}
	public void setC_0320Cnt(String c_0320Cnt) {
		this.c_0320Cnt = c_0320Cnt;
	}
	public String getC_0302Amt() {
		return c_0302Amt;
	}
	public void setC_0302Amt(String c_0302Amt) {
		this.c_0302Amt = c_0302Amt;
	}
	public String getC_0302Cnt() {
		return c_0302Cnt;
	}
	public void setC_0302Cnt(String c_0302Cnt) {
		this.c_0302Cnt = c_0302Cnt;
	}
	public String getC_1301Amt() {
		return c_1301Amt;
	}
	public void setC_1301Amt(String c_1301Amt) {
		this.c_1301Amt = c_1301Amt;
	}
	public String getC_1301Cnt() {
		return c_1301Cnt;
	}
	public void setC_1301Cnt(String c_1301Cnt) {
		this.c_1301Cnt = c_1301Cnt;
	}
	public String getC_0323Amt() {
		return c_0323Amt;
	}
	public void setC_0323Amt(String c_0323Amt) {
		this.c_0323Amt = c_0323Amt;
	}
	public String getC_0323Cnt() {
		return c_0323Cnt;
	}
	public void setC_0323Cnt(String c_0323Cnt) {
		this.c_0323Cnt = c_0323Cnt;
	}
	public String getCCityAmt() {
		return CCityAmt;
	}
	public void setCCityAmt(String cCityAmt) {
		CCityAmt = cCityAmt;
	}
	public String getCCityCnt() {
		return CCityCnt;
	}
	public void setCCityCnt(String cCityCnt) {
		CCityCnt = cCityCnt;
	}
	public String getC_0513Amt() {
		return c_0513Amt;
	}
	public void setC_0513Amt(String c_0513Amt) {
		this.c_0513Amt = c_0513Amt;
	}
	public String getC_0513Cnt() {
		return c_0513Cnt;
	}
	public void setC_0513Cnt(String c_0513Cnt) {
		this.c_0513Cnt = c_0513Cnt;
	}
	public String getC_1005Amt() {
		return c_1005Amt;
	}
	public void setC_1005Amt(String c_1005Amt) {
		this.c_1005Amt = c_1005Amt;
	}
	public String getC_1005Cnt() {
		return c_1005Cnt;
	}
	public void setC_1005Cnt(String c_1005Cnt) {
		this.c_1005Cnt = c_1005Cnt;
	}
	public String getC_0202Amt() {
		return c_0202Amt;
	}
	public void setC_0202Amt(String c_0202Amt) {
		this.c_0202Amt = c_0202Amt;
	}
	public String getC_0202Cnt() {
		return c_0202Cnt;
	}
	public void setC_0202Cnt(String c_0202Cnt) {
		this.c_0202Cnt = c_0202Cnt;
	}
	public String getC_0201Amt() {
		return c_0201Amt;
	}
	public void setC_0201Amt(String c_0201Amt) {
		this.c_0201Amt = c_0201Amt;
	}
	public String getC_0201Cnt() {
		return c_0201Cnt;
	}
	public void setC_0201Cnt(String c_0201Cnt) {
		this.c_0201Cnt = c_0201Cnt;
	}
	public String getC_1201Amt() {
		return c_1201Amt;
	}
	public void setC_1201Amt(String c_1201Amt) {
		this.c_1201Amt = c_1201Amt;
	}
	public String getC_1201Cnt() {
		return c_1201Cnt;
	}
	public void setC_1201Cnt(String c_1201Cnt) {
		this.c_1201Cnt = c_1201Cnt;
	}
	public String getC_0905Amt() {
		return c_0905Amt;
	}
	public void setC_0905Amt(String c_0905Amt) {
		this.c_0905Amt = c_0905Amt;
	}
	public String getC_0905Cnt() {
		return c_0905Cnt;
	}
	public void setC_0905Cnt(String c_0905Cnt) {
		this.c_0905Cnt = c_0905Cnt;
	}
	public String getCEastAmt() {
		return CEastAmt;
	}
	public void setCEastAmt(String cEastAmt) {
		CEastAmt = cEastAmt;
	}
	public String getCEastCnt() {
		return CEastCnt;
	}
	public void setCEastCnt(String cEastCnt) {
		CEastCnt = cEastCnt;
	}
	public String getC_0714Amt() {
		return c_0714Amt;
	}
	public void setC_0714Amt(String c_0714Amt) {
		this.c_0714Amt = c_0714Amt;
	}
	public String getC_0714Cnt() {
		return c_0714Cnt;
	}
	public void setC_0714Cnt(String c_0714Cnt) {
		this.c_0714Cnt = c_0714Cnt;
	}
	public String getC_0809Amt() {
		return c_0809Amt;
	}
	public void setC_0809Amt(String c_0809Amt) {
		this.c_0809Amt = c_0809Amt;
	}
	public String getC_0809Cnt() {
		return c_0809Cnt;
	}
	public void setC_0809Cnt(String c_0809Cnt) {
		this.c_0809Cnt = c_0809Cnt;
	}
	public String getC_1401Amt() {
		return c_1401Amt;
	}
	public void setC_1401Amt(String c_1401Amt) {
		this.c_1401Amt = c_1401Amt;
	}
	public String getC_1401Cnt() {
		return c_1401Cnt;
	}
	public void setC_1401Cnt(String c_1401Cnt) {
		this.c_1401Cnt = c_1401Cnt;
	}
	public String getCWestAmt() {
		return CWestAmt;
	}
	public void setCWestAmt(String cWestAmt) {
		CWestAmt = cWestAmt;
	}
	public String getCWestCnt() {
		return CWestCnt;
	}
	public void setCWestCnt(String cWestCnt) {
		CWestCnt = cWestCnt;
	}
	public String getC_1101Amt() {
		return c_1101Amt;
	}
	public void setC_1101Amt(String c_1101Amt) {
		this.c_1101Amt = c_1101Amt;
	}
	public String getC_1101Cnt() {
		return c_1101Cnt;
	}
	public void setC_1101Cnt(String c_1101Cnt) {
		this.c_1101Cnt = c_1101Cnt;
	}
	public String getCTotAmt() {
		return CTotAmt;
	}
	public void setCTotAmt(String cTotAmt) {
		CTotAmt = cTotAmt;
	}
	public String getCTotCnt() {
		return CTotCnt;
	}
	public void setCTotCnt(String cTotCnt) {
		CTotCnt = cTotCnt;
	}
	public String getC_1101eTotAmt() {
		return c_1101eTotAmt;
	}
	public void setC_1101eTotAmt(String c_1101eTotAmt) {
		this.c_1101eTotAmt = c_1101eTotAmt;
	}
	public String getC_1101eTotCnt() {
		return c_1101eTotCnt;
	}
	public void setC_1101eTotCnt(String c_1101eTotCnt) {
		this.c_1101eTotCnt = c_1101eTotCnt;
	}

    public String getSkinYn() {
		return skinYn;
	}
	public void setSkinYn(String skinYn) {
		this.skinYn = skinYn;
	}
	public String getSkinNm() {
		return skinNm;
	}
	public void setSkinNm(String skinNm) {
		this.skinNm = skinNm;
	}

	/* 소고기 이력제 */
    public String getTraceNoType() {
		return traceNoType;
	}
	public void setTraceNoType(String traceNoType) {
		this.traceNoType = traceNoType;
	}
	
    public String getInfoType() {
		return infoType;
	}
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	public String getFarmAddr() {
		return farmAddr;
	}
	public void setFarmAddr(String farmAddr) {
		this.farmAddr = farmAddr;
	}
	public String getFarmerNm() {
		return farmerNm;
	}
	public void setFarmerNm(String farmerNm) {
		this.farmerNm = farmerNm;
	}
	public String getFarmUniqueNo() {
		return farmUniqueNo;
	}
	public void setFarmUniqueNo(String farmUniqueNo) {
		this.farmUniqueNo = farmUniqueNo;
	}
	public String getPigNo() {
		return pigNo;
	}
	public void setPigNo(String pigNo) {
		this.pigNo = pigNo;
	}
	public String getButcheryPlaceAddr() {
		return butcheryPlaceAddr;
	}
	public void setButcheryPlaceAddr(String butcheryPlaceAddr) {
		this.butcheryPlaceAddr = butcheryPlaceAddr;
	}
	public String getButcheryPlaceNm() {
		return butcheryPlaceNm;
	}
	public void setButcheryPlaceNm(String butcheryPlaceNm) {
		this.butcheryPlaceNm = butcheryPlaceNm;
	}
	public String getButcheryYmd() {
		return butcheryYmd;
	}
	public void setButcheryYmd(String butcheryYmd) {
		this.butcheryYmd = butcheryYmd;
	}
	public String getInspectPassYn() {
		return inspectPassYn;
	}
	public void setInspectPassYn(String inspectPassYn) {
		this.inspectPassYn = inspectPassYn;
	}
	public String getProcessPlaceAddr() {
		return processPlaceAddr;
	}
	public void setProcessPlaceAddr(String processPlaceAddr) {
		this.processPlaceAddr = processPlaceAddr;
	}
	public String getProcessYmd() {
		return processYmd;
	}
	public void setProcessYmd(String processYmd) {
		this.processYmd = processYmd;
	}
	public String getCorpNo() {
		return corpNo;
	}
	public void setCorpNo(String corpNo) {
		this.corpNo = corpNo;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getProcessPlaceNm() {
		return processPlaceNm;
	}
	public void setProcessPlaceNm(String processPlaceNm) {
		this.processPlaceNm = processPlaceNm;
	}

	/* 돼지 등급판정결과 (확인서) */
   public String getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getJudgeKindCd() {
		return judgeKindCd;
	}
	public void setJudgeKindCd(String judgeKindCd) {
		this.judgeKindCd = judgeKindCd;
	}
	public String getJudgeKindNm() {
		return judgeKindNm;
	}
	public void setJudgeKindNm(String judgeKindNm) {
		this.judgeKindNm = judgeKindNm;
	}
	public String getRaterCode() {
		return raterCode;
	}
	public void setRaterCode(String raterCode) {
		this.raterCode = raterCode;
	}
	public String getRaterNm() {
		return raterNm;
	}
	public void setRaterNm(String raterNm) {
		this.raterNm = raterNm;
	}
	public String getAbattCode() {
		return abattCode;
	}
	public void setAbattCode(String abattCode) {
		this.abattCode = abattCode;
	}
	public String getMgrAbattNm() {
		return mgrAbattNm;
	}
	public void setMgrAbattNm(String mgrAbattNm) {
		this.mgrAbattNm = mgrAbattNm;
	}
	public String getAbattAddr() {
		return abattAddr;
	}
	public void setAbattAddr(String abattAddr) {
		this.abattAddr = abattAddr;
	}
	public String getAbattTelNo() {
		return abattTelNo;
	}
	public void setAbattTelNo(String abattTelNo) {
		this.abattTelNo = abattTelNo;
	}
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
	public String getReqRegNo() {
		return reqRegNo;
	}
	public void setReqRegNo(String reqRegNo) {
		this.reqRegNo = reqRegNo;
	}
	public String getReqComName() {
		return reqComName;
	}
	public void setReqComName(String reqComName) {
		this.reqComName = reqComName;
	}
	public String getReqAddr() {
		return reqAddr;
	}
	public void setReqAddr(String reqAddr) {
		this.reqAddr = reqAddr;
	}
	public String getReqTelno() {
		return reqTelno;
	}
	public void setReqTelno(String reqTelno) {
		this.reqTelno = reqTelno;
	}
	public String getJudgeDate() {
		return judgeDate;
	}
	public void setJudgeDate(String judgeDate) {
		this.judgeDate = judgeDate;
	}
	public String getAbattNo2() {
		return abattNo2;
	}
	public void setAbattNo2(String abattNo2) {
		this.abattNo2 = abattNo2;
	}
	public String getAbattWeight() {
		return abattWeight;
	}
	public void setAbattWeight(String abattWeight) {
		this.abattWeight = abattWeight;
	}
	public String getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}
	public String getTotalIssueCnt() {
		return totalIssueCnt;
	}
	public void setTotalIssueCnt(String totalIssueCnt) {
		this.totalIssueCnt = totalIssueCnt;
	}
	
	/* 등급판정결과 상세 */
	public String getMeatType() {
		return meatType;
	}
	public void setMeatType(String meatType) {
		this.meatType = meatType;
	}
	public String getCutmeatCode() {
		return cutmeatCode;
	}
	public void setCutmeatCode(String cutmeatCode) {
		this.cutmeatCode = cutmeatCode;
	}
	public String getCutmeatName() {
		return cutmeatName;
	}
	public void setCutmeatName(String cutmeatName) {
		this.cutmeatName = cutmeatName;
	}
	public String getCutmeatWeight() {
		return cutmeatWeight;
	}
	public void setCutmeatWeight(String cutmeatWeight) {
		this.cutmeatWeight = cutmeatWeight;
	}
	public String getCutmeatRate() {
		return cutmeatRate;
	}
	public void setCutmeatRate(String cutmeatRate) {
		this.cutmeatRate = cutmeatRate;
	}
	
	/* 축산물 실시간 돼지도체 등급별경매현황정보 */
    public String getAuctDate() {
		return auctDate;
	}
	public void setAuctDate(String auctDate) {
		this.auctDate = auctDate;
	}
	public String getAbattNm() {
		return abattNm;
	}
	public void setAbattNm(String abattNm) {
		this.abattNm = abattNm;
	}
	public String getAuct_1Amt() {
		return auct_1Amt;
	}
	public void setAuct_1Amt(String auct_1Amt) {
		this.auct_1Amt = auct_1Amt;
	}
	public String getAuct_2Amt() {
		return auct_2Amt;
	}
	public void setAuct_2Amt(String auct_2Amt) {
		this.auct_2Amt = auct_2Amt;
	}
	public String getAuct_3Amt() {
		return auct_3Amt;
	}
	public void setAuct_3Amt(String auct_3Amt) {
		this.auct_3Amt = auct_3Amt;
	}
	public String getAuctEAmt() {
		return auctEAmt;
	}
	public void setAuctEAmt(String auctEAmt) {
		this.auctEAmt = auctEAmt;
	}
	public String getAuctEExceptAmt() {
		return auctEExceptAmt;
	}
	public void setAuctEExceptAmt(String auctEExceptAmt) {
		this.auctEExceptAmt = auctEExceptAmt;
	}
	public String getTotalAuctAmt() {
		return totalAuctAmt;
	}
	public void setTotalAuctAmt(String totalAuctAmt) {
		this.totalAuctAmt = totalAuctAmt;
	}
	public String getTotalAuctCnt() {
		return totalAuctCnt;
	}
	public void setTotalAuctCnt(String totalAuctCnt) {
		this.totalAuctCnt = totalAuctCnt;
	}
	
    /* 축산물 실시간 돼지도체 등급별경매현황정보(상세) */
    public String getAbattDate() {
		return abattDate;
	}
	public void setAbattDate(String abattDate) {
		this.abattDate = abattDate;
	}
	public String getAbattNo() {
		return abattNo;
	}
	public void setAbattNo(String abattNo) {
		this.abattNo = abattNo;
	}
	public String getAuctRank() {
		return auctRank;
	}
	public void setAuctRank(String auctRank) {
		this.auctRank = auctRank;
	}
	public String getAuctTime() {
		return auctTime;
	}
	public void setAuctTime(String auctTime) {
		this.auctTime = auctTime;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getSkinYnNm() {
		return skinYnNm;
	}
	public void setSkinYnNm(String skinYnNm) {
		this.skinYnNm = skinYnNm;
	}
	public String getLeftWeight() {
		return leftWeight;
	}
	public void setLeftWeight(String leftWeight) {
		this.leftWeight = leftWeight;
	}
	public String getRightWeight() {
		return rightWeight;
	}
	public void setRightWeight(String rightWeight) {
		this.rightWeight = rightWeight;
	}
	public String getAuctAmt() {
		return auctAmt;
	}
	public void setAuctAmt(String auctAmt) {
		this.auctAmt = auctAmt;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
    /* 돼지 권역별 경락가격현황  */
    public String getBaseYmd() {
		return baseYmd;
	}
	public void setBaseYmd(String baseYmd) {
		this.baseYmd = baseYmd;
	}
	public String getBeforeYmd() {
		return beforeYmd;
	}
	public void setBeforeYmd(String beforeYmd) {
		this.beforeYmd = beforeYmd;
	}
	public String getLocalNm() {
		return localNm;
	}
	public void setLocalNm(String localNm) {
		this.localNm = localNm;
	}
	public String getLocalCode() {
		return localCode;
	}
	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}
	public String getAuctCnt() {
		return auctCnt;
	}
	public void setAuctCnt(String auctCnt) {
		this.auctCnt = auctCnt;
	}
	public String getDiffAuctAmt() {
		return diffAuctAmt;
	}
	public void setDiffAuctAmt(String diffAuctAmt) {
		this.diffAuctAmt = diffAuctAmt;
	}

    /* 축산물등급판정확인서  발급번호정보  */
	public String getAnimalNo() {
		return animalNo;
	}
	public void setAnimalNo(String animalNo) {
		this.animalNo = animalNo;
	}
	
	/* 인터페이스 로그테이블 */
	public String getOffi_syst() {
		return offi_syst;
	}
	public void setOffi_syst(String offi_syst) {
		this.offi_syst = offi_syst;
	}
	public String getIntr_usid() {
		return intr_usid;
	}
	public void setIntr_usid(String intr_usid) {
		this.intr_usid = intr_usid;
	}
	public String getIntr_name() {
		return intr_name;
	}
	public void setIntr_name(String intr_name) {
		this.intr_name = intr_name;
	}
	public String getIntr_date() {
		return intr_date;
	}
	public void setIntr_date(String intr_date) {
		this.intr_date = intr_date;
	}
	public static String getStrtDate() {
		return strtDate;
	}
	public void setStrtDate(String strtDate) {
		this.strtDate = strtDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
}
