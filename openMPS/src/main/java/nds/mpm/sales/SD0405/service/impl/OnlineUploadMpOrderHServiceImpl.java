package nds.mpm.sales.SD0405.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0101.service.MpCustInfoDAO;
import nds.mpm.sales.SD0101.vo.MpCustInfoVO;
import nds.mpm.sales.SD0102.service.MpOppoProCodeDAO;
import nds.mpm.sales.SD0102.vo.MpOppoProCodeVO;
import nds.mpm.sales.SD0405.service.MpOrderDDAO;
import nds.mpm.sales.SD0405.service.MpOrderHDAO;
import nds.mpm.sales.SD0405.service.OnlineUploadMpOrderHService;
import nds.mpm.sales.SD0405.vo.MpOrderDVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  EmartMpOrderHServiceImpl
 *
 * @author MPM TEAM
 * @since 2017.08.17
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 유통주문입력 ( SD0405 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.17	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Service("onlineUploadMpOrderHService")
public class OnlineUploadMpOrderHServiceImpl extends TMMPPBaseService implements
OnlineUploadMpOrderHService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(OnlineUploadMpOrderHServiceImpl.class);

    @Resource(name="mpOrderHDAO")
    private MpOrderHDAO mpOrderHDAO;
    
    @Resource(name="mpOrderDDAO")
    private MpOrderDDAO mpOrderDDAO;
    
    @Resource(name="mpCustInfoDAO")
    private MpCustInfoDAO mpCustInfoDAO;
    
    @Resource(name="mpOppoProCodeDAO")
    private MpOppoProCodeDAO mpOppoProCodeDAO;
    
	/**
	 * 엑셀 주문생성 서비스
	 * * 항목별 주문생성 규칙은 주문 Upload 설명_V1.5.xlsx 참고
	 * 
	 * 1. 이마트 수금집계 거래처 주문 특이사항.
	 * 
	 * 	- 출고일 한번생성된 주문번호유지.
	 * 	- 이마트 주문은 업로드하는 엑셀마다 출고일자기준 해당날짜에 생성된 주문건을 삭제하지않고 
	 * 		수량 및 금액을 가감한다.
	 * 
	 * 2. 이마트외 거래처 주문 특이사항.
	 * 
	 * - 출고일 생성된 주문번호삭제.
	 * 	- 출고일자기준 해당날짜에 생성된 주문건을 삭제
	 * 
	 * @exception Exception
	 */
    public ResultEx insertMpOrderHExcel(List<EgovMap> exportList, MpOrderDVO mpOrderDVO) throws Exception {
    	
    	PageSet pageSet = new PageSet();
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	LOGGER.debug("save target list size =" + exportList.size());
    	
    	/**
    	 * 거래처별 주문규칙에 따라 주문데이터 생성.
    	 * 오류 데이터 발생시 거래처리스트 또는 제품리스트 리턴.
    	 * checkResultList == null 이면 오류 데이터 없음.
    	 * */
    	List checkResultList = setOrderDataSet(exportList, mpOrderDVO);
    	
    	if(checkResultList != null)
    	{
    		pageSet.setTotalRecordCount(0);
        	pageSet.setResult(checkResultList);
        	pageSet.setFailCount(checkResultList.size());
        	result.setExtraData(pageSet);
        	
        	return result;
    	}
    	
    	boolean isAllNewOrdrCreate = true;
    	
    	String prevShopNo = null;
    	String curShopNo = null;
    	
    	String curOrdrNo = null;
    	EgovMap checkOrdr = null;
    	String ordrCust = null;
    	
    	String retChkOrdr=null;
    	
    	LOGGER.debug("order insert or update start ::::: ");
    	
    	for(int i=0; i < exportList.size(); i++)
    	{
    		EgovMap vo = exportList.get(i);
    		Map nextCustMap = (i+1) == exportList.size() ? null : exportList.get(i+1);
    		
    		vo.put("status", "I"); //신규
    		
    		curShopNo = (String)vo.get("ordrCust");
    		
    		//주문번호생성. header create
    		if(i == 0 || ( prevShopNo != null && curShopNo.compareTo(prevShopNo) != 0 ))
    		{
    			prevShopNo = curShopNo;
    			
    			if(isAllNewOrdrCreate == false){
        			//기존주문번호 유지.
        			checkOrdr = mpOrderHDAO.selectUploadCustMpOrderNo(vo);
        			if(checkOrdr != null)
            		{
        				curOrdrNo = (String)checkOrdr.get("ordrNo");
        				
        				vo.put("ordrNo", checkOrdr.get("ordrNo"));
        				vo.put("ordrSeq", checkOrdr.get("ordrSeq"));
            		}
        			//신규주문번호생성.
        			else
        			{
        				curOrdrNo = mpOrderHDAO.selectMpOrderNo(vo);
        				vo.put("ordrNo", curOrdrNo);
        			}
        		}
    			//신규주문번호생성.
    			else
    			{
    				curOrdrNo = mpOrderHDAO.selectMpOrderNo(vo);
    				vo.put("ordrNo", curOrdrNo);
    			}
    			
    			LOGGER.debug("::::::::: curOrdrNo :: " + curOrdrNo);
    			
    		}
    		
    		String nextOrdrCust = nextCustMap != null ? (String)nextCustMap.get("ordrCust") : null;
    		
    		//주문상세저장
    		retChkOrdr = mpOrderDDAO.insertMpOrderD(vo);
    		if(retChkOrdr == null)
    			throw new Exception("주문상세저장이 실패하였습니다.");
    		
			//주문헤더 저장. header create
	    	if( nextCustMap == null || ( nextOrdrCust != null && ordrCust.compareTo(nextOrdrCust) != 0 ) )	
    		{
	    		vo.put("ordrWeight", vo.get("sumOrdrWeight"));
    			vo.put("ordrAmt", vo.get("sumOrdrAmt"));
	    		
    			if(isAllNewOrdrCreate == false && checkOrdr != null){
	    			if(mpOrderHDAO.updateUploadMpOrderHCustAmt(vo)==0)
	    			{
	    				throw new Exception("주문수정이 실패하였습니다.");
	    			}
    			}
	    		else{
	    			retChkOrdr = mpOrderHDAO.insertMpOrderH(vo);
	    			if(retChkOrdr == null)
	        			throw new Exception("주문저장이 실패하였습니다.");
	    		}
	    			
    		}
	    	
    	}

    	LOGGER.debug("order insert or update end ::::: ");
    	
    	pageSet.setTotalRecordCount(exportList.size());
    	pageSet.setResult(exportList);
    	pageSet.setFailCount(0);
    	result.setExtraData(pageSet);
    	
        return result;
    }
    
    /**
     * 매핑에 실패한 거래처 또는 제품리스트 리턴.
     * 매핑 성공하면 주문생성 서비스 진행.
     * 
     * 1.판매장shop no 검색
     * 2.거래처상대제품->openMPS제품 매핑.
     * 3.제품마스터 제품정보 검색
     * 4.수량,금액셋팅.
     * 
     * */
    public List setOrderDataSet(List<EgovMap> exportList, MpOrderDVO mpOrderDVO) throws Exception{
    	
    	List checkProdList = new ArrayList();
    	List checkCustList = new ArrayList();
    	
    	//계산필드
    	int sumOrdrBox = 0;
    	int sumOrdrWeight = 0;
    	int sumOrdrAmt = 0;
    	
    	long intOrdrBox = 0;
    	int intOrdrWeight = 0;
    	int intOrdrAmt = 0;
    	
    	int insCustCnt = 0;
    	
    	MpCustInfoVO findCust = null;
    	MpOppoProCodeVO findProd = null;
    	
    	String newOrderNo = null;
    	String prevShopNo = null;
    	String curShopNo = null;
    	
    	String curOrdrNo = null;
    	String ordrCust = null;
    	String delvCust = null;
    	
    	String priceCust = null;
    	int intUnitPrice = 0;
    	double dbChangeRate = 0;
    	
    	EgovMap checkOrdr = null;
    	
    	for(int i=0; i < exportList.size(); i++)
    	{
    		EgovMap vo = exportList.get(i);
    		
    		Map nextCustMap = (i+1) == exportList.size() ? null : exportList.get(i+1);
    		
    		curShopNo = (String)vo.get("ordrCust");
    		delvCust = (String)vo.get("delvCust");
    		String nextOrdrCust = nextCustMap != null ? (String)nextCustMap.get("ordrCust") : null;
    		String proCode = (String)vo.get("proCode");
    		String unitPrice = (String)vo.get("unitPrice");
    		String ordrBox = (String)vo.get("ordrBox");
    		String ordrAmt = (String)vo.get("ordrAmt");
    		String delvDate = null;
    		//센터입하일
    		String delvDate1 = (String)vo.get("delvDate1");
    		//점입점일
    		String delvDate2 = (String)vo.get("delvDate2");
    		
    		System.out.println("prevShopNo :: " + prevShopNo);
    		System.out.println("curShopNo :: " + curShopNo);
    		
    		//System.out.println("next ordrCust :: " + nextOrdrCust);
    		System.out.println("proCode :: " + proCode);
			
			LOGGER.debug("i ===============start " + i +"[" + curShopNo );
			
    		//이마트 출고일자 적용. delvDate1 센터입하일자, delvDate2 점입점일자
    		if(StringUtils.isEmpty(delvDate1))
    		{
    			delvDate = delvDate1;
    		}
    		else
    		{
    			delvDate = delvDate2;
    		}
    		
    		/**
    		 * TEST delv_date
    		 * */
    		delvDate = "20171001";
    		
    		//엑셀에서 추출시 필수컬럼이 추출되지 않은 상태.
    		if(StringUtils.isEmpty(curShopNo) || StringUtils.isEmpty(proCode))
    		{
    			continue;
    		}
    		
    		//주문번호생성. header create
    		if(i == 0 || ( prevShopNo != null && curShopNo.compareTo(prevShopNo) != 0 ))
    		{
    			prevShopNo = curShopNo;
    			
    			MpCustInfoVO custVo = new MpCustInfoVO();
    			custVo.setCorpCode(mpOrderDVO.getCorpCode());
    			custVo.setShopNo(curShopNo);
    			custVo.setReceCombCust(mpOrderDVO.getCustCode());
    			
    			LOGGER.debug("::::::::: find ordr cust for shop no :: " + curShopNo);
    			/**
    			 * mp_cust_info openmps주문 거래처 검색.
    			 * */
    			findCust = mpCustInfoDAO.selectMpCustInfo(custVo);
    			if(findCust == null)
    			{
    				/**
        			 * 파일상 shop_no 검색실패시 오류 메시지.
        			 * */
    				checkCustList.add(curShopNo);
    			}
    			
    			ordrCust = findCust.getCustCode();
    			
    			/**
    			 * mp_cust_info openmps주문 거래처 검색.
    			 * */
    			vo.put("delvDate", StringUtils.remove(delvDate,"-"));
        		vo.put("ordrCust", ordrCust);
        		
    			LOGGER.debug("::::::::: find delv cust for shop no :: " + delvCust);
    			/**
    			 * mp_cust_info openmps배송처 검색.
    			 * */
    			if(StringUtils.isNotEmpty(delvCust))
    			{
    				custVo.setShopNo(delvCust);
        			MpCustInfoVO delvCustMap = mpCustInfoDAO.selectMpCustInfo(custVo);
        			
        			if(delvCustMap != null)
        			{
        				delvCust = delvCustMap.getCustCode();
            			vo.put("delvCust", delvCust);
        			}
        			else
        			{
        				/**
            			 * 배송처가 없을경우 mp_cust_info 배송처
            			 * */
        				vo.put("delvCust", findCust.getDelvCust());
        			}
    			}
    			else
    			{
    				/**
        			 * 파일상 배송처가 없을경우 mp_cust_info 배송처
        			 * */
    				vo.put("delvCust", findCust.getDelvCust());
    			}
    			
    			delvCust = (String)vo.get("delvCust");
    			
    			if("2".equals(findCust.getPriceComb()))
    			{
    				priceCust = findCust.getPriceCombCust();
    			}
    			else
    			{
    				priceCust = ordrCust;
    			}
    		}
    		else
    		{
    			vo.put("ordrNo", curOrdrNo);
    			vo.put("delvDate", StringUtils.remove(delvDate,"-"));
        		vo.put("ordrCust", ordrCust);
        		vo.put("delvCust", delvCust);
    		}
    		
			/**
			 * 거래처상대제품 검색 SD0102
			 * */
			EgovMap sProd = new EgovMap();
			sProd.put("corpCode",mpOrderDVO.getCorpCode());
			sProd.put("custCode", findCust.getCustCode());
			sProd.put("custProCode",proCode);
			
			findProd = mpOppoProCodeDAO.selectMpOppoProCode(sProd);
			/**
			 * custProCode로 제품검색에 실패한 row가 존재할경우
			 * 전체 저장거부?.
			 * */
			if(findProd == null)
			{
				LOGGER.error("custProCode No = " + proCode + ", not found!!");
				checkProdList.add(proCode);
			}
			
			if(findProd != null)
			{
				vo.put("proUkind", findProd.getProUkind());
				
				/**
				 * mp_order_d 제품저장 
				 * 제품 총금액, 총수량 계산이 필요하므로 detail 부터 저장.
				 * 
				 * */
				intOrdrWeight = Integer.parseInt(StringUtils.defaultIfEmpty(findProd.getProWeig(), "0"));
				intOrdrBox = Integer.parseInt(StringUtils.defaultIfEmpty(ordrBox, "0"));
				intOrdrAmt = Integer.parseInt(StringUtils.defaultIfEmpty(ordrAmt, "0"));
				dbChangeRate = Double.parseDouble(StringUtils.defaultIfEmpty(findProd.getChangeRate(),"0"));
				
				vo.put("custName", findCust.getCustName()); 
				vo.put("proCode", findProd.getProCode());  //제품코드
				vo.put("proName", findProd.getProName());  //제품코드
				
				/**
				 * 수량계산 mp_cust_pro_code.change_rate 환산계수적용.(거래처별 수량단위가 다르기 때문)
				 */
				intOrdrBox = Math.round(intOrdrBox * dbChangeRate);
				
				vo.put("ordrBox", intOrdrBox);
				
				//tm_codexd SD002 1: 과세 10% 
				if("1".equals(findProd.getVatCode()))
				{
					vo.put("ordrVat", (intOrdrAmt * 0.1) );
				}
				else
				{
					vo.put("ordrVat", 0); //
				}
			}
			
			vo.put("priceCust", findCust.getCustCode());
			intUnitPrice = mpOrderHDAO.selectMpOrderPrice(vo);
    		/**
    		 * 전달받은 단가가 없으면 DB조회.
    		 * */
    		if(intUnitPrice == 0)
    		{
				//단가적용 오류, 화면상에서 제품에 대한 단가를 구하지못했거나 단가값을 전달하지 못함. -104
    			//result.setResultCode(-104);
    			//result.setMsg("해당제품의 단가가 설정되어있는지 확인바랍니다.");
        		//return result;
    		}
    		
    		vo.put("unitPrice", intUnitPrice);
			
			//중량계산
			vo.put("ordrWeight", (intOrdrWeight * intOrdrBox));
			vo.put("ordrAmt", intOrdrAmt);
			
			
			setOrderHeaderValues(vo, findCust);
			setOrderDetailValues(vo, findCust, findProd);
			insCustCnt++;
			
	    	sumOrdrWeight += (intOrdrWeight * intOrdrBox);
	    	sumOrdrAmt += intOrdrAmt;
			
			//주문헤더 저장. header create
	    	if( nextCustMap == null || ( nextOrdrCust != null && ordrCust.compareTo(nextOrdrCust) != 0 ) )	
    		{
	    		vo.put("sumOrdrWeight", sumOrdrWeight);
    			vo.put("sumOrdrAmt", sumOrdrAmt);
	    		
    	    	sumOrdrWeight = 0;
    	    	sumOrdrAmt = 0;
    		}
	    	
	    	LOGGER.debug("i ===============end " + i);
    		
    	}
    	
    	if(checkCustList.size() > 0)
    	{
    		LOGGER.debug(" :: " + checkCustList.toString());
    		return checkCustList;
    	}
    	
    	if(checkProdList.size() > 0)
    	{
    		LOGGER.debug(" :: " + checkProdList.toString());
    		return checkProdList;
    	}
    
    	return null;
    }
    
  //mp_order_h
    public void setOrderHeaderValues(Map vo, MpCustInfoVO findCust){
    	
		vo.put("corpCode", findCust.getCorpCode()); //회사코드
		vo.put("ordrType", "10"); //"10"(판매주문)으로 Setting
		/** 파일(ORDR_CUST) + 거래처마스터 기준, 
		 * 1.파일의 ORDR_CUST = SHOP-NO, 
		 * 2.입력거래처 = SALE_COMB_CUST 조건에 맞는 거래처 CUST_CODE
		*/
		vo.put("ordrCust", findCust.getCustCode());  //거래처
		if(StringUtils.isNotEmpty(findCust.getSaleCombCust()))
		{
			vo.put("saleCust", findCust.getSaleCombCust()); //판매집계 거래처 mp_cust_info.SALE_COMB_CUST
		}
		else
		{
			vo.put("saleCust", "00");
		}
		
		vo.put("aggreGubn", findCust.getSaleComb()); //취합구분 mp_cust_info.SALE_COMB
		vo.put("delvType", findCust.getDelvType()); //출고창고 mp_cust_info.MAIN_DC
		if(StringUtils.isNotEmpty(findCust.getDelvCust()))
		{
			vo.put("delvCust", findCust.getDelvCust());
		}
		else
			vo.put("delvCust", "00");
			
		if(StringUtils.isNoneEmpty(findCust.getMainDc()))
			vo.put("delvDc", findCust.getMainDc()); //출고창고 mp_cust_info.MAIN_DC
		else
			vo.put("delvDc", "0");
		vo.put("approYn", "Y");
		vo.put("amtDisplay", "Y");
		vo.put("limitYn", "Y");
		vo.put("occurSrc", "3"); //온라인
		vo.put("delvBox", 0);
		vo.put("status", vo.get("status")); //
		vo.put("if_status", "0"); //WMS 접수 구분 입력 또는 변경 시 "0"(대상)
		vo.put("crUser", "upload");
    }
    
    //mp_order_d
    /**
     * corp_code character varying(4) NOT NULL, -- 회사코드 - 1001:openMPS
	  delv_date character varying(8) NOT NULL, -- 출고일자
	  ordr_no character varying(5) NOT NULL, -- 주문번호 - 일자의 일련번호 (Format : 00000)
	  ordr_seq character varying(3) NOT NULL, -- 주문번호 항순
	  pro_code character varying(10) NOT NULL, -- 제품코드
	  sale_group2 character varying(10) NOT NULL, -- 품목
	  price_src character varying(8) NOT NULL, -- 단가구분 - SD021
	  unit_price numeric(11,0) NOT NULL DEFAULT 0, -- 단가
	  ordr_box numeric(11,0) NOT NULL DEFAULT 0, -- 주문수량
	  ordr_weight numeric(11,0) NOT NULL DEFAULT 0, -- 주문중량
	  ordr_amt numeric(11,0) NOT NULL DEFAULT 0, -- 주문공급가
	  ordr_vat numeric(11,0) NOT NULL DEFAULT 0, -- 주문부가세
	  delv_box numeric(11,0) NOT NULL DEFAULT 0, -- 출고수량
	  delv_weight numeric(11,0) NOT NULL DEFAULT 0, -- 출고중량
	  delv_amt numeric(11,0) NOT NULL DEFAULT 0, -- 출고공급가
	  delv_vat numeric(11,0) NOT NULL DEFAULT 0, -- 출고부가세
	  weight_comp character varying(1) NOT NULL DEFAULT 'NULL'::character varying, -- 중량준수 - "Y" 중량준수
	  cr_date timestamp without time zone NOT NULL, -- 입력시간
     * 
     * */
    public void setOrderDetailValues(Map vo, MpCustInfoVO findCust, MpOppoProCodeVO findProd){
    		
		vo.put("corpCode", findCust.getCorpCode()); //회사코드
		if(findProd!= null)
		{
			vo.put("saleGroup2", (findProd.getSaleGroup2() != null ? findProd.getSaleGroup2() : "0")); //품목
			vo.put("priceSrc", (findCust.getPriceType() != null ? findCust.getPriceType() : "0")); // 단가구분 mp_cust_info 가준
		}
		else
		{
			vo.put("saleGroup2", "00"); //품목
			vo.put("priceSrc", "0"); // 단가구분 mp_cust_info 가준
		}
		vo.put("delvBox", 0); //
		vo.put("delvAmt", 0);
		vo.put("delvWeight", 0);
		vo.put("delvVat", 0); //
		vo.put("weightComp", "0"); //
		vo.put("pmAcceptYn", "Y"); // PM접수여부
		vo.put("status", "01"); //출고예정
		vo.put("crUser", "upload");
    }
    
    private void callTransaction(String targetGB, EgovMap vo) throws Exception {
    	
    	if("D".equals(targetGB))
    	{
    		mpOrderDDAO.insertMpOrderD(vo);
    	}
    	else if("H".equals(targetGB))
    	{
    		mpOrderHDAO.insertMpOrderH(vo);
    	}
    	
    }
    
    /**
	 * mp_order_h을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpOrderHVO
	 * @return void형
	 * @exception Exception
	 */
    public ResultEx deleteMpOrderH(List<EgovMap> vos) throws Exception {
        
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	int uCnt = 0;
    	for(EgovMap vo : vos)
    	{
    		uCnt = mpOrderDDAO.deleteMpOrderD(vo);
    		if(uCnt == 0)
    		{
    			throw new Exception("delete detail fail!!");
    		}
    	}
    	uCnt = 0;
    	int resultCnt = 0;
    	for(EgovMap vo : vos)
    	{
    		uCnt = mpOrderHDAO.deleteMpOrderH(vos.get(0));
    		if(uCnt == 0)
    		{
    			throw new Exception("delete detail fail!!");
    		}
    		resultCnt++;
    	}
    	
    	result.setExtraData(resultCnt);
    	
    	return result;
    }
}
