package nds.mpm.sales.SD0401.service.impl;

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
import nds.mpm.sales.SD0401.service.SD0401DAO;
import nds.mpm.sales.SD0401.service.SD0401Service;
import nds.mpm.sales.SD0401.vo.MultiSD0401VO;
import nds.mpm.sales.SD0401.vo.SD0401DefaultVO;
import nds.mpm.sales.SD0401.vo.SD0401VO;
import nds.mpm.sales.SD0402.vo.SD0402DefaultVO;
import nds.mpm.sales.SD0405.service.MpOrderDDAO;
import nds.mpm.sales.SD0405.service.MpOrderHDAO;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : SD0401ServiceImpl.java
 * @Description : SD0401 Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD0401Service")
public class SD0401ServiceImpl extends TMMPPBaseService implements
        SD0401Service {
	
	@Resource(name="mpOrderHDAO")
    private MpOrderHDAO mpOrderHDAO;
    
    @Resource(name="mpOrderDDAO")
    private MpOrderDDAO mpOrderDDAO;
    
    @Resource(name="mpCustInfoDAO")
    private MpCustInfoDAO mpCustInfoDAO;
    
    @Resource(name="mpItemMasterMDAO")
    private MpItemMasterMDAO mpItemMasterMDAO;
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0401ServiceImpl.class);

    @Resource(name="SD0401DAO")
    private SD0401DAO SD0401DAO;
    
    public ResultEx insertMpOrderH(SD0401VO searchVO, MultiSD0401VO reqMVO) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpOrderHVO head = reqMVO.getHead();
    	//신규주문
    	if("C".equals(head.getDsType()))
    	{
    		result = createMpOrderH(reqMVO);
    	}
    	else if("U".equals(head.getDsType()))
    	{
    		result = updateMpOrderH(reqMVO);
    	}
    	else if("D".equals(head.getDsType()))
			deleteMpOrderH(reqMVO);
    	
    	return result;
    }	
    
    /**
     * 신규주문생성
     * 
     * */
    public ResultEx createMpOrderH(MultiSD0401VO reqMVO) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	MpOrderHVO head = reqMVO.getHead();
    	List<EgovMap> details = reqMVO.getDetail();
    	
    	//계산필드
    	int sumOrdrBox = 0;
    	int sumOrdrWeight = 0;
    	int sumOrdrAmt = 0;
    	
    	StringBuffer failRowNum = new StringBuffer();
    	
    	MpCustInfoVO findCust = null;
    	MpItemMasterMVO findProd = null;
    	
    	String newOrderNo = null;
    	String prevOrdrCust = null;
    	
    	/***
		 * 
		 * 1. 주문번호생성. header create
		 * 2. 거래처추가 정보조회 
		 */
		newOrderNo = mpOrderHDAO.selectMpOrderNo(details.get(0));
    	
    	for(EgovMap vo : details)
    	{

			vo.put("ordrNo", newOrderNo);
    		
    		int intUnitPrice = 0;
        	int intOrdrBox = 0;
        	double intOrdrWeight = 0;
        	int intOrdrAmt = 0;
        	double intOrdrVat = 0;
        	
    		String corpCode = (String)ObjectUtils.defaultIfNull(vo.get("corpCode"),"");
    		String ordrCust = (String)ObjectUtils.defaultIfNull(vo.get("ordrCust"),"");
    		String proCode = (String)ObjectUtils.defaultIfNull(vo.get("proCode"),"");
    		String ordrType = (String)ObjectUtils.defaultIfNull(vo.get("ordrType"),"");
    		String unitPrice = (String)ObjectUtils.defaultIfNull(vo.get("unitPrice"),"");
    		String ordrBox = (String)ObjectUtils.defaultIfNull(vo.get("ordrBox"),"");
    		String delvDate = (String)ObjectUtils.defaultIfNull(vo.get("delvDate"),"");
    		String amtDisplay = (String)ObjectUtils.defaultIfNull(vo.get("amtDisplay"),"");
    		String limitYn = (String)ObjectUtils.defaultIfNull(vo.get("limitYn"),"");
    		String proWeight = (String)ObjectUtils.defaultIfNull(vo.get("ordrWeight"),"");
    		String delvDc = (String)ObjectUtils.defaultIfNull(vo.get("delvDc"),"");
    		String ordrVat = (String)ObjectUtils.defaultIfNull(vo.get("ordrVat"),"");

    		/**
    		 * 필수체크 
    		 * */
    		if(StringUtils.isEmpty(ordrCust))
    		{
    			//화면상에서 ordrCust값을 전달하지 못함. -
    			result.setResultCode(-2);
    			result.setMsg("empty ordrCust!!");
        		return result;
    		}
    		if(StringUtils.isEmpty(proCode))
    		{
    			//화면상에서 proCode값을 전달하지 못함. -
    			result.setResultCode(-2);
    			result.setMsg("empty proCode!!");
        		return result;
    		}
    		if(StringUtils.isEmpty(delvDate))
    		{
    			//화면상에서 delvDate값을 전달하지 못함. -
    			result.setResultCode(-2);
    			result.setMsg("empty delvDate!!");
        		return result;
    		}
    		if(StringUtils.isEmpty(ordrType))
    		{
    			//화면상에서 orderType값을 전달하지 못함. -
    			result.setResultCode(-2);
    			result.setMsg("empty orderType!!");
        		return result;
    		}
    		if(StringUtils.isEmpty(ordrBox))
    		{
    			//화면상에서 orderType값을 전달하지 못함. -103
    			result.setResultCode(-2);
    			result.setMsg("empty ordrBox!!");
        		return result;
    		}
    		
    		/***
    		 * 값정리 
    		 * 
    		 * pro_code 에 적용된단가 
    		 * 화면에서 제품선택시 조회되어 셋팅된 단가.
    		 */
    		unitPrice = StringUtils.defaultIfEmpty(unitPrice, "0");
    		ordrBox = StringUtils.defaultIfEmpty(ordrBox, "0");
    		delvDate = StringUtils.remove(delvDate,"-");
    		delvDate = StringUtils.remove(delvDate,"/");
    		amtDisplay = StringUtils.defaultIfEmpty(amtDisplay,"Y");
    		limitYn = StringUtils.defaultIfEmpty(limitYn,"N");
    		ordrVat = StringUtils.defaultIfEmpty(ordrVat, "0");

    		intUnitPrice = Integer.parseInt(unitPrice);
    		/**
    		 * 전달받은 단가가 없으면 DB조회.
    		 * */
    		if(intUnitPrice == 0)
    		{
    			/**
    			 * 할인단가 > 판매단가 > 표준단가 순으로 조회.
    			 * */
    			intUnitPrice = mpOrderHDAO.selectMpOrderPrice(vo);
    			
    			if(intUnitPrice == 0)
    			{
    				//단가적용 오류, 화면상에서 제품에 대한 단가를 구하지못했거나 단가값을 전달하지 못함. -104
        			result.setResultCode(-104);
        			result.setMsg("empty unitPrice!!");
            		return result;
    			}
    			
    		}
    		
    		System.out.println("ordrCust :: " + ordrCust);
    		System.out.println("proCode :: " + proCode);
    		
    		/***
    		 * 거래처 정보조회 
    		 */
    		MpCustInfoVO custVo = new MpCustInfoVO();
			custVo.setCorpCode(corpCode);
			custVo.setCustCode(ordrCust);
    		findCust = mpCustInfoDAO.selectMpCustInfo(custVo);
			
    		/***
    		 * 3. 제품추가 정보조회 
    		 */
    		EgovMap sProd = new EgovMap();
			sProd.put("corpCode",corpCode);
			sProd.put("proCode",proCode);
			
			findProd = mpItemMasterMDAO.selectMpItemMasterM(sProd);
			
			/*
			if(StringUtils.isEmpty(findProd.getProWeig()))
    		{
    			//getProWeig invalid. -104
    			result.setResultCode(-104);
    			result.setMsg("empty product weight!!");
        		return result;
    		}
    		*/
			if(StringUtils.isEmpty(ordrBox))
    		{
    			//ordrBox invalid. -105
    			result.setResultCode(-105);
    			result.setMsg("empty product box qty!!");
        		return result;
    		}
			/**
			 * mp_order_d 제품저장 
			 * 제품 총금액, 총수량 계산이 필요하므로 detail 부터 저장.
			 * 
			 * */
			intOrdrBox = Integer.parseInt(ordrBox);
			intOrdrAmt = intUnitPrice * intOrdrBox;
			intOrdrVat = Integer.parseInt(ordrVat);
			
			if(proWeight == null)
			{
				if(findProd.getProWeig() != null)
					intOrdrWeight = Double.parseDouble(findProd.getProWeig());
			}
			else
			{
				intOrdrWeight = Double.parseDouble(proWeight);
			}
			
			//화면에서 전달한 부가세 체크 
			if(intOrdrVat == 0)
			{
				// mp_item_master_m.vatCode
				//tm_codexd SD002 1: 과세 10% 
				if("1".equals(findProd.getVatCode()))
				{
					intOrdrVat = intOrdrAmt * 0.1;
				}
				else
				{
					intOrdrVat = 0; //
				}
			}
			
			vo.put("custName", findCust.getCustName()); 
			vo.put("delvDate", delvDate);  //
			vo.put("proCode", findProd.getProCode());  //제품코드
			vo.put("proName", findProd.getProName());  //제품코드
			vo.put("unitPrice", intUnitPrice);
			vo.put("ordrBox", intOrdrBox);
			vo.put("amtDisplay", amtDisplay);
			vo.put("limitYn", limitYn);
			vo.put("delvDc", StringUtils.defaultIfEmpty(delvDc,findCust.getMainDc()));
			vo.put("ordrVat", intOrdrVat);
			vo.put("delvCust", StringUtils.defaultIfEmpty(delvDc,findCust.getDelvCust()));
			
			//중량계산
			vo.put("ordrWeight", (intOrdrWeight * intOrdrBox));
			vo.put("ordrAmt", intOrdrAmt);
			
			setOrderHeaderValues(vo, findCust);
			setOrderDetailValues(vo, findCust, findProd);
			//saveDetailList.add(vo);
			mpOrderDDAO.insertMpOrderD(vo);
			
			sumOrdrBox += intOrdrBox;
	    	sumOrdrWeight += (intOrdrWeight * intOrdrBox);
	    	sumOrdrAmt += intOrdrAmt;
    	}
    	
    	EgovMap headMap = details.get(0);
    	headMap.put("ordrBox", sumOrdrBox);
    	headMap.put("ordrAmt", sumOrdrAmt);
		
		mpOrderHDAO.insertMpOrderH(headMap);
		
		sumOrdrBox = 0;
    	sumOrdrWeight = 0;
    	sumOrdrAmt = 0;
    	
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(details.size());
    	pageSet.setResult(reqMVO);
    	result.setExtraData(pageSet);
        return result;
    }
    
    public void setOrderHeaderValues(Map vo, MpCustInfoVO findCust){
    	
		vo.put("corpCode", findCust.getCorpCode()); //회사코드
		vo.put("delvDate", vo.get("delvDate")); //출고일자
		vo.put("ordrType", vo.get("ordrType")); //주문유형
		vo.put("ordrCust", vo.get("ordrCust"));  //거래처
		vo.put("delvCust", vo.get("delvCust"));  //배송처 
		vo.put("saleCust", findCust.getSaleCombCust()); //판매처 mp_cust_info.SALE_COMB_CUST
		vo.put("aggreGubn", findCust.getSaleComb()); //취합구분 mp_cust_info.SALE_COMB
		vo.put("delvType", "2"); //배송구분물류납
		vo.put("delvDc", vo.get("delvDc")); //출고창고 mp_cust_info.MAIN_DC
		vo.put("approYn", "N"); //결재상태고정.
		vo.put("amtDisplay", vo.get("amtDisplay")); //금액표시고정
		vo.put("limitYn", vo.get("limitYn")); //경과일수 제외고정 
		vo.put("occurSrc", 1); //PC
		vo.put("delvBox", 0);
		vo.put("delvAmt", 0);
		vo.put("status", "01"); //출고예정
		vo.put("crUser", vo.get("crUser"));
    }
    
    public void setOrderDetailValues(Map vo, MpCustInfoVO findCust, MpItemMasterMVO findProd){
		
		vo.put("corpCode", findCust.getCorpCode()); //회사코드
		vo.put("saleGroup2", StringUtils.defaultIfEmpty(findProd.getSaleGroup2(),"0")); //품목
		vo.put("proUkind", findProd.getProUkind()); // 제품마스터 
		vo.put("priceSrc", StringUtils.defaultIfEmpty(findCust.getPriceType(),"0")); // 단가구분 mp_cust_info 가준
		vo.put("delvBox", 0); //
		vo.put("delvAmt", 0);
		vo.put("delvWeight", 0);
		vo.put("delvVat", 0); //
		vo.put("weightComp", "0"); //
		vo.put("pmAcceptYn", "N"); // PM접수여부
		vo.put("status", "01"); //출고예정
		vo.put("crUser", vo.get("crUser"));
    }
    
    public ResultEx updateMpOrderH(MultiSD0401VO reqMVO) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	List<EgovMap> details = reqMVO.getDetail();
    	
    	StringBuffer failRowNum = new StringBuffer();
    	
    	MpCustInfoVO findCust = null;
    	MpItemMasterMVO findProd = null;
    	
    	for(EgovMap vo : details)
    	{
    		int intUnitPrice = 0;
    		int intOrdrBox = 0;
        	double intOrdrWeight = 0;
        	int intOrdrAmt = 0;
        	
    		String corpCode = (String)ObjectUtils.defaultIfNull(vo.get("corpCode"),"");
    		String ordrCust = (String)ObjectUtils.defaultIfNull(vo.get("ordrCust"),"");
    		String proCode = (String)ObjectUtils.defaultIfNull(vo.get("proCode"),"");
    		String orderType = (String)ObjectUtils.defaultIfNull(vo.get("orderType"),"");
    		String unitPrice = (String)ObjectUtils.defaultIfNull(vo.get("unitPrice"),"");
    		String ordrBox = (String)ObjectUtils.defaultIfNull(vo.get("ordrBox"),"");
    		String delvDate = (String)ObjectUtils.defaultIfNull(vo.get("delvDate"),"");
    		String amtDisplay = (String)ObjectUtils.defaultIfNull(vo.get("amtDisplay"),"");
    		String limitYn = (String)ObjectUtils.defaultIfNull(vo.get("limitYn"),"");
    		String proWeight = (String)ObjectUtils.defaultIfNull(vo.get("ordrWeight"),"");
    		String delvDc = (String)ObjectUtils.defaultIfNull(vo.get("delvDc"),"");
    		/***
    		 * pro_code 에 적용된단가 
    		 * 화면에서 제품선택시 조회되어 셋팅된 단가.
    		 */
    		if(StringUtils.isEmpty(ordrCust))
    		{
    			//화면상에서 ordrCust값을 전달하지 못함. -
    			result.setResultCode(-2);
    			result.setMsg("empty ordrCust!!");
        		return result;
    		}
    		if(StringUtils.isEmpty(proCode))
    		{
    			//화면상에서 proCode값을 전달하지 못함. -
    			result.setResultCode(-2);
    			result.setMsg("empty proCode!!");
        		return result;
    		}
    		if(StringUtils.isEmpty(delvDate))
    		{
    			//화면상에서 delvDate값을 전달하지 못함. -
    			result.setResultCode(-2);
    			result.setMsg("empty delvDate!!");
        		return result;
    		}
    		if(StringUtils.isEmpty(delvDc))
    		{
    			//화면상에서 delvDate값을 전달하지 못함. -
    			result.setResultCode(-2);
    			result.setMsg("empty delvDc!!");
        		return result;
    		}
    		
    		System.out.println("corpCode :: " + corpCode);
    		System.out.println("ordrCust :: " + ordrCust);
    		System.out.println("proCode :: " + proCode);
    		
    		/***
    		 * 거래처 정보조회 
    		 */
    		MpCustInfoVO custVo = new MpCustInfoVO();
			custVo.setCorpCode(corpCode);
			custVo.setCustCode(ordrCust);
    		findCust = mpCustInfoDAO.selectMpCustInfo(custVo);
    		
    		/***
    		 * 3. 제품추가 정보조회 
    		 */
    		EgovMap sProd = new EgovMap();
			sProd.put("corpCode",corpCode);
			sProd.put("proCode",proCode);
			findProd = mpItemMasterMDAO.selectMpItemMasterM(sProd);

			unitPrice = StringUtils.defaultIfEmpty(unitPrice, "0");
    		ordrBox = StringUtils.defaultIfEmpty(ordrBox, "0");
    		delvDate = StringUtils.remove(delvDate,"-");
    		delvDate = StringUtils.remove(delvDate,"/");
    		amtDisplay = StringUtils.defaultIfEmpty(amtDisplay,"Y");
    		limitYn = StringUtils.defaultIfEmpty(limitYn,"N");
    		
    		intUnitPrice = Integer.parseInt(unitPrice);
    		/**
    		 * 전달받은 단가가 없으면 DB조회.
    		 * */
    		if(intUnitPrice == 0)
    		{
    			/**
    			 * 할인단가 > 판매단가 > 표준단가 순으로 조회.
    			 * */
    			intUnitPrice = mpOrderHDAO.selectMpOrderPrice(vo);
    			
    			if(intUnitPrice == 0)
    			{
    				//단가적용 오류, 화면상에서 제품에 대한 단가를 구하지못했거나 단가값을 전달하지 못함. -104
        			result.setResultCode(-104);
        			result.setMsg("empty unitPrice!!");
            		return result;
    			}
    			
    		}
			
			if(StringUtils.isEmpty(ordrBox))
    		{
    			//ordrBox invalid. -105
    			result.setResultCode(-105);
    			result.setMsg("empty product box qty!!");
        		return result;
    		}
			/**
			 * mp_order_d 제품저장 
			 * 제품 총금액, 총수량 계산이 필요하므로 detail 부터 저장.
			 * 
			 * */
			if(proWeight == null)
			{
				if(findProd.getProWeig() != null)
					intOrdrWeight = Double.parseDouble(findProd.getProWeig());
			}
			else
			{
				intOrdrWeight = Double.parseDouble(proWeight);
			}
			
			intOrdrBox = Integer.parseInt(ordrBox);
			intOrdrAmt = intUnitPrice * intOrdrBox;
			
			vo.put("delvDate", delvDate);
			vo.put("unitPrice", unitPrice);
			vo.put("ordrBox", intOrdrBox);
			vo.put("amtDisplay", amtDisplay);
			vo.put("limitYn", limitYn);
			
			vo.put("delvDc", StringUtils.defaultIfEmpty(delvDc,findCust.getMainDc()));
			vo.put("delvCust", StringUtils.defaultIfEmpty(delvDc,findCust.getDelvCust()));
			
			//중량계산
			vo.put("ordrWeight", (intOrdrWeight * intOrdrBox));
			vo.put("ordrAmt", intOrdrAmt);
			
			//tm_codexd SD002 1: 과세 10% 
			if("1".equals(findProd.getVatCode()))
			{
				vo.put("ordrVat", (intOrdrAmt * 0.1) );
			}
			else
			{
				vo.put("ordrVat", 0); //
			}
			
			setOrderHeaderValues(vo, findCust);
			setOrderDetailValues(vo, findCust, findProd);
			//saveDetailList.add(vo);
			if("D".equals((String)vo.get("dsType")))
				mpOrderDDAO.deleteMpOrderD(vo);
			else if("U".equals((String)vo.get("dsType")))
				mpOrderDDAO.insertMpOrderD(vo);	//insert or update
    	}
    	
    	mpOrderHDAO.updateMpOrderHAmt(details.get(0));
    	
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(details.size());
    	pageSet.setResult(reqMVO);
    	result.setExtraData(pageSet);
        return result;
    }
    
    /**
	 * mp_order_h을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpOrderHVO
	 * @return void형
	 * @exception Exception
	 */
    public ResultEx deleteMpOrderH(MultiSD0401VO reqMVO) throws Exception {
        
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpOrderHVO head = reqMVO.getHead();
    	List<EgovMap> details = reqMVO.getDetail();
    	
    	int uCnt = 0;
    	
    	for(EgovMap vo : details)
    	{
			uCnt = mpOrderDDAO.deleteMpOrderD(vo);
    		if(uCnt == 0)
    		{
    			throw new Exception("delete detail fail!!");
    		}
    	}
    	
    	EgovMap headMap = details.get(0);
    	uCnt = mpOrderHDAO.deleteMpOrderH(headMap);
		if(uCnt == 0)
		{
			throw new Exception("delete detail fail!!");
		}
    	
    	result.setExtraData(details.size());
    	
    	return result;
    }

    /**
	 * mp_order_h을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SD0401VO
	 * @return 조회한 mp_order_h
	 * @exception Exception
	 */
    public SD0401VO selectSD0401(EgovMap vo) throws Exception {
        SD0401VO resultVO = SD0401DAO.selectSD0401(vo);
        return resultVO;
    }

    /**
	 * mp_order_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 목록
	 * @exception Exception
	 */
    public List<?> selectSD0401List(SD0401DefaultVO searchVO) throws Exception {
        return SD0401DAO.selectSD0401List(searchVO);
    }

    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    public int selectSD0401ListTotCnt(SD0401DefaultVO searchVO) {
		return SD0401DAO.selectSD0401ListTotCnt(searchVO);
	}
    
    /**
	 * mp_order_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 목록
	 * @exception Exception
	 */
    public List<?> selectSD0402List(SD0402DefaultVO searchVO) throws Exception {
        return SD0401DAO.selectSD0402List(searchVO);
    }

    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    public int selectSD0402ListTotCnt(SD0402DefaultVO searchVO) {
		return SD0401DAO.selectSD0402ListTotCnt(searchVO);
	}
    
    public EgovMap selectSD0401ProJaegoInfo(SD0401VO vo) throws Exception {
    	return SD0401DAO.selectSD0401ProJaegoInfo(vo);
    }
    
    public EgovMap selectSD0401CustLimit(SD0401VO vo) throws Exception {
    	return SD0401DAO.selectSD0401CustLimit(vo);
    }
    
    public String selectSD0401DelvCust(SD0401DefaultVO searchVO) {
    	return SD0401DAO.selectSD0401DelvCust(searchVO);
    }
    
    public List<?> selectTab1MpOrderDList(SD0401VO searchVO) throws Exception {
    	return SD0401DAO.selectTab1MpOrderDList(searchVO);
    }

    public List<?> selectTab2MpOrderDList(SD0401VO searchVO) throws Exception {
    	return SD0401DAO.selectTab2MpOrderDList(searchVO);
    }

}
