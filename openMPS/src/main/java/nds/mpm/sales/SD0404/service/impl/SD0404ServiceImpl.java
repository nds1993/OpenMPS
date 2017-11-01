package nds.mpm.sales.SD0404.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0103.service.MpItemMasterMDAO;
import nds.mpm.prod.PP0103.vo.MpItemMasterMVO;
import nds.mpm.sales.SD0101.service.MpCustInfoDAO;
import nds.mpm.sales.SD0101.vo.MpCustInfoVO;
import nds.mpm.sales.SD0102.vo.MpOppoProCodeVO;
import nds.mpm.sales.SD0404.service.SD0404DAO;
import nds.mpm.sales.SD0404.service.SD0404Service;
import nds.mpm.sales.SD0405.service.MpOrderDDAO;
import nds.mpm.sales.SD0405.service.MpOrderHDAO;
import nds.mpm.sales.SD0405.vo.MpOrderDVO;
import nds.mpm.sales.SD0405.vo.MpOrderHDefaultVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderHServiceImpl.java
 * @Description : MpOrderH Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

/**
 * 주문입력 방법이 추가될때 
 * MpOrderHServiceImpl 를 상속받아 커스텀 주문로직을 구현한다. 
 * 
 * @exception Exception
 */
@Service("SD0404Service")
public class SD0404ServiceImpl extends TMMPPBaseService implements
        SD0404Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0404ServiceImpl.class);

    @Resource(name="SD0404DAO")
    private SD0404DAO mpSD0404DAO;
    
    @Resource(name="mpOrderHDAO")
    private MpOrderHDAO mpOrderHDAO;
    
    @Resource(name="mpOrderDDAO")
    private MpOrderDDAO mpOrderDDAO;
    
    @Resource(name="mpCustInfoDAO")
    private MpCustInfoDAO mpCustInfoDAO;
    
    @Resource(name="mpItemMasterMDAO")
    private MpItemMasterMDAO mpItemMasterMDAO;
    
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
    			
    			curOrdrNo = mpOrderHDAO.selectMpOrderNo(vo);
				vo.put("ordrNo", curOrdrNo);
    			
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
	    		
    			retChkOrdr = mpOrderHDAO.insertMpOrderH(vo);
    			if(retChkOrdr == null)
        			throw new Exception("주문저장이 실패하였습니다.");
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
     * 3.제품마스터 제품정보 검색
     * 4.수량,금액셋팅.
     * 
     * */
    public List setOrderDataSet(List<EgovMap> exportList, MpOrderDVO mpOrderDVO) throws Exception{
    	
    	List checkProdList = new ArrayList();
    	
    	//계산필드
    	int sumOrdrBox = 0;
    	int sumOrdrWeight = 0;
    	int sumOrdrAmt = 0;
    	
    	long intOrdrBox = 0;
    	int intOrdrWeight = 0;
    	int intOrdrAmt = 0;
    	
    	int insCustCnt = 0;
    	
    	MpCustInfoVO findCust = null;
    	MpItemMasterMVO findProd = null;
    	
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
			sProd.put("proCode",proCode);
			
			findProd = mpItemMasterMDAO.selectMpItemMasterM(sProd);
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
				
				vo.put("custName", findCust.getCustName()); 
				vo.put("proCode", findProd.getProCode());  //제품코드
				vo.put("proName", findProd.getProName());  //제품코드
				
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
    	
    	if(checkProdList.size() > 0)
    	{
    		LOGGER.debug(" :: " + checkProdList.toString());
    		return checkProdList;
    	}
    
    	return null;
    }
    
    public List selectSD0404List(MpOrderHDefaultVO searchVO) throws Exception{
    	return mpSD0404DAO.selectSD0404List(searchVO);
    }
    
    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    public int selectSD0404ListTotCnt(MpOrderHDefaultVO searchVO){
    	return mpSD0404DAO.selectSD0404ListTotCnt(searchVO);
    }
}
