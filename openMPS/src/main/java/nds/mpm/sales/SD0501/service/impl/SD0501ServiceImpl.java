package nds.mpm.sales.SD0501.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0501.service.MpCustReceiptDAO;
import nds.mpm.sales.SD0501.service.MpLastUnpaymentDAO;
import nds.mpm.sales.SD0501.service.SD0501DAO;
import nds.mpm.sales.SD0501.service.SD0501Service;
import nds.mpm.sales.SD0501.vo.MpCustReceiptVO;
import nds.mpm.sales.SD0501.vo.MpLastUnpaymentVO;
import nds.mpm.sales.SD0501.vo.SD0501DefaultVO;
import nds.mpm.sales.SD0501.vo.SD0501VO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : SD0501ServiceImpl.java
 * @Description : SD0501 Business Implement class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD0501Service")
public class SD0501ServiceImpl extends EgovAbstractServiceImpl implements
        SD0501Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0501ServiceImpl.class);

    @Resource(name="SD0501DAO")
    private SD0501DAO SD0501DAO;
    
    @Resource(name="mpCustReceiptDAO")
    private MpCustReceiptDAO mpCustReceiptDAO;
    
    @Resource(name="mpLastUnpaymentDAO")
    private MpLastUnpaymentDAO mpLastUnpaymentDAO;

	/**
	 * mp_rece_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SD0501VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertSD0501(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	if(vos == null)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	if(vos.size() == 0)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	
    	/***
    	 * 해당월 수금일자 마감체크 
    	 * 
    	 * */
    	for(EgovMap reqVo : vos)
    	{
	    	if( SD0501DAO.checkReceClosing(reqVo) > 0)
	    	{
	    		result.setResultCode(Consts.ResultCode.RC_DENINED.getCode());
	    		result.setExtraData(reqVo.get("receDate"));
	    		return result;
	    	}
    	}

    	List<EgovMap> returnList = new ArrayList();
    	int iCnt = 0;
    	String newReceCode = null;
    	String chkCustRec = null;
    	String receDate = null;
    	String corpCode = null;
    	String custCode = null;
    	String strtDate = null;
    	String lastDate = null;
    	String yearmon = null;
    	
    	MpCustReceiptVO receip = new MpCustReceiptVO();
    	MpLastUnpaymentVO unpay = new MpLastUnpaymentVO();
    	
    	int uCnt = 0;
    	
    	for(EgovMap reqVo : vos)
    	{
    		iCnt = 0;
    		newReceCode = null;
    		chkCustRec = null;
    		
    		corpCode = (String)reqVo.get("corpCode");
    		receDate = (String)reqVo.get("receDate");
    		custCode = (String)reqVo.get("custCode");
    		/**
			 * 당월미수금갱신을 위해 해당월 입금액조회파라메터 생성.
			 * */
    		if(receDate != null && receDate.length() == 8)
    		{
    			yearmon  = receDate.substring(0,6);
    			strtDate = yearmon + "01";
    			lastDate = yearmon + "31";
    		}
    		else
    		{
    			throw new Exception("receDate invalid!!");
    		}
    		
    		String receAmt = StringUtils.defaultIfEmpty((String)reqVo.get("receAmt"),"0");
    		
    		receip.setCorpCode(corpCode);
    		receip.setReceDate(receDate);
    		receip.setCustCode(custCode);
    		receip.setReceType((String)reqVo.get("receType"));
    		receip.setReceAmt( Integer.parseInt(receAmt) );
    		receip.setHeadCust((String)reqVo.get("custCode"));
    		receip.setStrtDate(strtDate);
    		receip.setLastDate(lastDate);
    		
    		unpay.setCorpCode(corpCode);
    		unpay.setCustCode(custCode);
    		unpay.setUnpayYymm(yearmon);
    		
    		if("C".equals((String)reqVo.get("dsType"))){
    			reqVo.put("receCode", newReceCode);
    			
    			/**
    			 * 수금정보 추가
    			 * */
    			newReceCode = SD0501DAO.insertSD0501(reqVo);
    			/**
    			 * 거래처일자별 수금실적 갱신, 없으면 신규 생성
    			 * */
    			chkCustRec = mpCustReceiptDAO.insertUpdateMpCustReceipt(receip);
    			
    			int sumMonRece = mpCustReceiptDAO.selectMpCustReceiptMonthSum(receip);
    			/**
    			 * 거래처당월미수금 갱신
    			 * */
    			if(sumMonRece > 0)
    			{
    				unpay.setCurrentUnpay(sumMonRece);
    				mpLastUnpaymentDAO.updateMpLastCurrentUnpayment(unpay);
    				//LOGGER.debug("sumMonRece :: " + sumMonRece);
    			}
    			
    			LOGGER.debug("newReceCode :: " + newReceCode);
    			LOGGER.debug("chkCustRec :: " + chkCustRec);
    			
    			if(newReceCode == null || chkCustRec == null)
        		{
        			throw new Exception("insert fail!!");
        		}
    		}
    		else if("U".equals((String)reqVo.get("dsType"))){
    			
    			if(StringUtils.isEmpty((String)reqVo.get("receCode")))
    			{
    				result.setMsg("rece code empty!!");
    				result.setResultCode(Consts.ResultCode.RC_INVALID_PARAMS.getCode());
    				return result;
    			}
    			
    			iCnt = SD0501DAO.updateSD0501(reqVo);
    			
    			/**
    			 * 수금정보 수정.
    			 * */
    			uCnt = mpCustReceiptDAO.updateMpCustReceipt(receip);
    			/**
    			 * 거래처일자별 수금실적 갱신, 없으면 신규 생성
    			 * */
    			int sumMonRece = mpCustReceiptDAO.selectMpCustReceiptMonthSum(receip);
    			
    			/**
    			 * 거래처전월미수금 갱신
    			 * */
    			
    			if(mpLastUnpaymentDAO.selectMpLastUnpayment(unpay) != null)
    			{
    				if(sumMonRece > 0)
        			{
        				unpay.setCurrentUnpay(sumMonRece);
        				iCnt = mpLastUnpaymentDAO.updateMpLastCurrentUnpayment(unpay);
        			}
        			if(iCnt == 0)
            		{
            			throw new Exception("update fail!!");
            		}
    			}
    			
    		}
    		else if("D".equals((String)reqVo.get("dsType"))){
    			
    			if(StringUtils.isEmpty((String)reqVo.get("receCode")))
    			{
    				result.setMsg("rece code empty!!");
    				result.setResultCode(Consts.ResultCode.RC_INVALID_PARAMS.getCode());
    				return result;
    			}
    			/**
    			 * 수금정보 삭제
    			 * */
    			iCnt = SD0501DAO.deleteSD0501(reqVo);
    			
    			uCnt = mpCustReceiptDAO.deleteMpCustReceipt(receip);
    			
    			int sumMonRece = mpCustReceiptDAO.selectMpCustReceiptMonthSum(receip);
    			
    			/**
    			 * 거래처전월미수금 갱신
    			 * */
    			if(mpLastUnpaymentDAO.selectMpLastUnpayment(unpay) != null)
    			{
    			
	    			if(sumMonRece > 0)
	    			{
	    				unpay.setCurrentUnpay(sumMonRece);
	    				iCnt = mpLastUnpaymentDAO.updateMpLastCurrentUnpayment(unpay);
	    			}
	    			if(iCnt == 0)
	        		{
	        			throw new Exception("delete fail!!");
	        		}
    			
    			}
    		}
    		
    		/**
    		 * corp_code character varying(4) NOT NULL, -- 회사코드 - 1001:openMPS
			  rece_date character varying(8) NOT NULL, -- 수금일자
			  cust_code character varying(10) NOT NULL, -- 거래처코드
			  rece_type character varying(8) NOT NULL, -- 수금유형 - SD014
			  rece_amt numeric(11,0) NOT NULL DEFAULT 0, -- 수금액
			  head_cust character varying(10), -- 유통본부
    		 * */
    		
    		LOGGER.debug("rece proc success !! ");
    		
    		returnList.add(reqVo);
    	}
    	
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(returnList.size());
    	pageSet.setResult(returnList);
		
    	result.setExtraData(pageSet);
    	
    	return result;
    }

    /**
	 * mp_rece_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SD0501VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateSD0501(SD0501VO vo) throws Exception {
        //SD0501DAO.updateSD0501(vo);
    }

    /**
	 * mp_rece_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SD0501VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteSD0501(SD0501VO vo) throws Exception {
        //SD0501DAO.deleteSD0501(vo);
    }

    /**
	 * mp_rece_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SD0501VO
	 * @return 조회한 mp_rece_info
	 * @exception Exception
	 */
    public SD0501VO selectSD0501(SD0501VO vo) throws Exception {
        SD0501VO resultVO = SD0501DAO.selectSD0501(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_rece_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_rece_info 목록
	 * @exception Exception
	 */
    public List<?> selectSD0501List(SD0501DefaultVO searchVO) throws Exception {
        return SD0501DAO.selectSD0501List(searchVO);
    }

    /**
	 * mp_rece_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_rece_info 총 갯수
	 * @exception
	 */
    public int selectSD0501ListTotCnt(SD0501DefaultVO searchVO) {
		return SD0501DAO.selectSD0501ListTotCnt(searchVO);
	}
    
}
