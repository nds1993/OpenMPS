package nds.mpm.excel;


/**
 * @Class Name : UserInfoVO.java
 * @Description : UserInfo VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class PP {
	
	//PP0101 공장등록
    public static final String[] PP0101={"공장코드","공장명","공장그룹","공장구분","가공구분","우편번호","주소","사용유무","비고"};
    public static final String[] PP0101_TYPE={"C","C","C","C","C","C","C","C","C"};
    public static final String PP0101_NM="공장등록";
	
	//PP0103 제품등록조회
    public static final String[] PP0103={"제품코드","제품명","제품명2","축종코드","축종명","품목코드","품목명","영업분류구분1코드","영업분류구분1명","영업분류구분2코드","영업분류구분2명","냉동/냉장구분코드","냉동/냉장구분명","세트여부","가공구분명","제/상품구분코드","제/상품구분명","정육여부코드","정육여부명","88코드id","88코드","88코드명칭","브랜드코드","브랜드코드명","표준부위코드","표준부위명","유통기한코드","유통기한명","상온온도","수정단위","단위당입수","단위당중량(Kg)","단가적용코드","단가적용명","부가세코드","부가세명","PM분류코드","PM분류명","생산일보분류코드","생산일보분류","TC매입코드","PC매입코드","PC출하1","PC출하2","롯데코(LS)","입수두수","작업사양코드","작업사양명","포장방법코드","포장방법명","작업라인코드","작업라인명","출력시트코드","출력시트명","안성공장부자재안전재고량","호남1공장부자재안전재고량","호남2공장부자재안전재고량","김제공장부자재안전재고량","메모","삭제여부","등록일시","등록자","수정일시","수정자"};
    public static final String[] PP0103_TYPE={"C","C","C","C","C","C","C","C","C","C" 
    										 ,"C","C","C","C","C","C","C","C","C","C" 
    										 ,"C","C","C","C","C","C","C","C","C","C"
    										 ,"C","C","C","C","C","C","C","C","C","C"
    										 ,"C","C","C","C","C","C","C","C","C","C"
    										 ,"C","C","C","C","C","C","C","C","C","C"
    										 ,"C","C","C","C"
    										 };
    public static final String PP0103_NM="제품등록조회";
	
	
	//PP0106 BOM조회
    public static final String[] PP0106={"BOM코드","버전","공장","제품명","공박스중량","필름중량","기타중량","비고","부자재구분","부자재명","소요량","단위","비고","생성일시","변경일시","변경내역"};
    public static final String[] PP0106_TYPE={"C","C","C","C","C","C","C","C","C","C","C","C","C","C","C","C"};
    public static final String PP0106_NM="BOM조회";
	
    //PP0201 수율기초등록
    public static final String[] PP0201={"생산일자","그룹공장","가공두수","생체중량","지육중량","6분체","1공장","2공장","3공장","암퇘지","1등급","더느림","비고"};
    public static final String[] PP0201_TYPE={"C","C","C","C","C","C","C","C","C","C","C","C","C"};
    public static final String PP0201_NM="수율기초";
    //PP0302 생산계획입력(CM)
    public static final String[] PP03021={"품목","무박비율","제품명","생산두수","생산량(BOX)","비고","입수두수","현재고(kg)","익일후1주간주문량","익일주문량","최근일주일출고중","출고후예상재고","생산량(kg)","생산후예상재고"};
    public static final String[] PP03021_TYPE={"C","C","C","C","C","C","C","C","C","C","C","C","C","C"};
    public static final String PP03021_NM="생산계획(CM)";
    //PP0302 생산계획입력(CM)합계
    public static final String[] PP03022={"품목","구분","제품명","안성1생산량","안성2생산량","호남1생산량","호남2생산량","합계","생산중량(kg)","현재고(kg)","주문량(kg)","가용재고(kg)"};
    public static final String[] PP03022_TYPE={"C","C","C","C","C","C","C","C","C","C","C","C"};
    public static final String PP03022_NM="생산계획합계(CM)";
    //PP0303 생산계획서조회발행(CM)
    public static final String[] PP0303={"공장","품목","제품코드","제품명","작업지시량","생산두수","라인","냉장","냉동","작업사양","포장방법","비고"};
    public static final String[] PP0303_TYPE={"C","C","C","C","C","C","C","C","C","C","C","C"};
    public static final String PP0303_NM="생산계획서조회발행(CM)";
    //PP0304 주문접수(PM)
    public static final String[] PP0304={"주문번호","품목코드","거래처명","품목명","제품코드","제품명","주문수량","주문중량","출고일자","접수","주문특기사항"};
    public static final String[] PP0304_TYPE={"C","C","C","C","C","C","C","C","C","C","C"};
    public static final String PP0304_NM="주문접수(PM)";
    //PP0305 생산계획입력(PM)
    public static final String[] PP0305={"거래처","품목명","제품코드","제품명","계획대상량","작업지시량","출고일자","단위","순위","계획특기사항"};
    public static final String[] PP0305_TYPE={"C","C","C","C","C","C","C","C","C","C"};
    public static final String PP0305_NM="생산계획서(PM)";
    
    //PP0306 생산계획서조회(PM)
    public static final String[] PP0306={"공장","계획번호","계획확정시각","거래처","품목","제품코드","제품명","작업지시량","계획특기사항"};
    public static final String[] PP0306_TYPE={"C","C","C","C","C","C","C","C","C"};
    public static final String PP0306_NM="생산계획서확정본(PM)";
    
    //PP0401 자재소요량산출
    public static final String[] PP0401={"소요일자","소스","공장","자재코드","자재명","표준소요량","단위","현재고","안전재고","구매요청량"};
    public static final String[] PP0401_TYPE={"C","C","C","C","C","C","C","C","C","C"};
    public static final String PP0401_NM="자재소요량산출";
    
    //PP0402 자재소요량조회
    public static final String[] PP04021={"제품코드","제품명","자재코드","자재명","소요량","단위","구매요청량"};
    public static final String[] PP04021_TYPE={"C","C","C","C","C","C","C"};
    public static final String PP04021_NM="자재소요량조회_제품별";
    
    public static final String[] PP04022={"자재코드","자재명","소요량","단위","구매요청량"};
    public static final String[] PP04022_TYPE={"C","C","C","C","C"};
    public static final String PP04022_NM="자재소요량조회_자재별";
    
    //PP0501 부자재출고오류현황
    public static final String[] PP0501={"공장","생산일자","자재코드","자재명","출고수량","단위","처리결과","오류메시지","출고번호","출고순번"};
    public static final String[] PP0501_TYPE={"C","C","C","C","C","C","C","C","C","C"};
    public static final String PP0501_NM="부자재출고오류현황";
    
    //PP0504 부자재출고오류현황
    public static final String[] PP05041={"공장","생산일자","생산시간","농장명","품목","제품명","카톤No","중량(Kg)","도축장","제조원","보관","유효기간","바코드","삭제일자","삭제자"};
    public static final String[] PP0504_TYPE={"C","C","C","C","C","C","C","C","C","C","C","C","C","C","C"};
    public static final String PP05041_NM="생산실적조회및조정(개체)_일자별";
    
    public static final String[] PP05042={"생산일자","생산시간","공장","농장명","품목","제품명","카톤No","중량(Kg)","도축장","제조원","보관","유효기간","바코드","삭제일자","삭제자"};
    public static final String PP05042_NM="생산실적조회및조정(개체)_공장별";
    public static final String[] PP05043={"농장","생산일자","생산시간","공장","품목","제품명","카톤No","중량(Kg)","도축장","제조원","보관","유효기간","바코드","삭제일자","삭제자"};
    public static final String PP05043_NM="생산실적조회및조정(개체)_농장별";
    
    //PP0701 부자재사용량조회
    public static final String[] PP0701={"공장","출고일자","자재코드","자재명","출고량(사용량)","단위"};
    public static final String[] PP0701_TYPE={"C","C","C","C","C","C"};
    public static final String PP0701_NM="부자재사용량조회";
    
    //PP0801 매입실적신고
    public static final String[] PP0801={"작업장","월","일","도체번호","판정방법","도체형태","성별","도체중량(Kg)","등지방두께","최종등급","출하농가","이력번호","API전송시간","전송자"};
    public static final String[] PP0801_TYPE={"C","C","C","C","C","C","C","C","C","C","C","C","C","C"};
    public static final String PP0801_NM="매입실적신고";
    
    //PP0802 포장처리실적신고
    public static final String[] PP0802={"포장일자","이력(묶음)번호","표준부위코드","표준부위명","무게(Kg)","API전송시간","전송자"};
    public static final String[] PP0802_TYPE={"C","C","C","C","C","C","C"};
    public static final String PP0802_NM="포장처리실적신고";
    
    //PP0803 거래내역실적신고
    public static final String[] PP0803={"거래일자","이력(묶음)번호","표준부위코드","표준부위","무게(Kg)","API전송시간","전송자"};
    public static final String[] PP0803_TYPE={"C","C","C","C","C","C","C"};
    public static final String PP0803_NM="거래내역실적신고";
    
}
