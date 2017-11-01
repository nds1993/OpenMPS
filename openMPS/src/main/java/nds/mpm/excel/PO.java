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
public class PO {
    //박피
    public static final String[] PP01013={"기준일자","박피(암)-1+","박피(암)-1","박피(암)-2","박피(암)-등외","박피(수)-등외","박피(거)-1+","박피(거)-1","박피(거)-2","박피(거)-등외"};
    public static final String[] PP01013_TYPE={"C","N","N","N","N","N","N","N","N","N"};
    public static final String PP01013_NM="지육시세_박피";
    //탕박
    public static final String[] PP01011={"기준일자","탕박(암)-1+","탕박(암)-1","탕박(암)-2","탕박(암)-등외","탕박(수)-등외","탕박(거)-1+","탕박(거)-1","탕박(거)-2","탕박(거)-등외"};
    public static final String[] PP01011_TYPE={"C","N","N","N","N","N","N","N","N","N"};
    public static final String PP01011_NM="지육시세_탕박";
    
    //생돈구매세부입력
    public static final String[] PP02011={"도축일자","농장","농장명","돈피구분","두수","이력번호","대표농장","브랜드","생체중"};
    public static final String[] PP02011_TYPE={"C","C","C","C","C","C","C","C","C"};
    public static final String PP02011_NM="생돈구매세부입력";
    public static final String[] PP02012={"도축일자","판정일자","농장","농장명","대표농장","브랜드","가산액외","탕박시세조정금","품질유지금외","정산액","등급수수료","자조금","농장지급액"};
    public static final String[] PP02012_TYPE={"C","C","C","C","C","C","C","C","C","C","C","C","C"};
    public static final String PP02012_NM="생돈구매세부입력";
    
    //출하정산집계표
    public static final String[] PP02031={"도축일자","농장","농장명","암","수"
					    	,"거세","합계","등지방","지육중","평균지육중량"
					    	,"생체중","평균생체중","지육율","BP율"
					    	,"1등급이상비율","1등급이상두수","정산액","공제금액","이상육"
					    	,"계류사돈","도축사고","정산차액","지급액(공제후)","등판"
					    	,"지급액(회계)","자조금","농장지급액","적용시세","지급율"};
    public static final String[] PP02031_TYPE={"C","C","C","C"
					    	,"C","C","C","C","C"
					    	,"C","C","C","C"
					    	,"C","C","C","C","C"
					    	,"C","C","C","C","C"
					    	,"C","C","C","C","C"};
    public static final String[] PP02032={"도축일자","농장","농장명","도체번호","암","수"
    	,"거세","등지방","지육중"
    	,"생체중","등급","지육율","BP율"
    	,"1등급이상비율","1등급이상두수","정산액","공제금액","이상육"
    	,"계류사돈","도축사고","정산차액","지급액(공제후)","등판"
    	,"지급액(회계)","자조금","농장지급액","적용시세","지급율"};
	public static final String[] PP02032_TYPE={"C","C","C","C","C","C"
	    	,"C","C","C"
	    	,"C","C","C","C"
	    	,"C","C","C","C","C"
	    	,"C","C","C","C","C"
	    	,"C","C","C","C","C"};
    
    public static final String PP02031_NM="출하정산집계표_농장합계";
    public static final String PP02032_NM="출하정산집계표_개체별";
    //더느림지급액조회
    public static final String[] PP0205={"출하농장명","대표자","HACCP","비육후기사용비율","암돈두수","암돈단가","암돈금액","거세돈두수","거세돈단가","거세돈금액","규격돈두수","규격돈금액","출하두수","비율"};
    public static final String[] PP0205_TYPE={"C","C","C","C","C"
									    	,"C","C","C","C","C"
									    	,"C","C","C","C"};
    public static final String PP0205_NM="더느림지급액조회";
}
