package nds.mpm.sales.SD0101.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0101.service.MpCreditLimitDAO;
import nds.mpm.sales.SD0101.service.MpCustHistDAO;
import nds.mpm.sales.SD0101.service.MpCustHistoryDAO;
import nds.mpm.sales.SD0101.service.MpCustInfoDAO;
import nds.mpm.sales.SD0101.service.MpCustInfoService;
import nds.mpm.sales.SD0101.service.MpDamboInfoDAO;
import nds.mpm.sales.SD0101.service.MpSalesmanCustDAO;
import nds.mpm.sales.SD0101.vo.MpCustInfoDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustInfoMultiVO;
import nds.mpm.sales.SD0101.vo.MpCustInfoVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustInfoServiceImpl.java
 * @Description : MpCustInfo Business Implement class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpCustInfoService")
public class MpCustInfoServiceImpl extends EgovAbstractServiceImpl implements
        MpCustInfoService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpCustInfoServiceImpl.class);

    @Resource(name="mpCustInfoDAO")
    private MpCustInfoDAO mpCustInfoDAO;
    
    @Resource(name="mpCreditLimitDAO")
    private MpCreditLimitDAO mpCreditLimitDAO;
    
    @Resource(name="mpCustHistDAO")
    private MpCustHistDAO mpCustHistDAO;
    
    @Resource(name="mpDamboInfoDAO")
    private MpDamboInfoDAO mpDamboInfoDAO;
    
    @Resource(name="mpSalesmanCustDAO")
    private MpSalesmanCustDAO mpSalesmanCustDAO;
    
    @Resource(name="mpCustHistoryDAO")
    private MpCustHistoryDAO mpCustHistoryDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpCustInfoIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_cust_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpCustInfo(MpCustInfoMultiVO vo, MPUserSession sess) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	EgovMap custinfo = null;
    	List<EgovMap> custhists = null;
    	List<EgovMap> credits = null;
    	List<EgovMap> dambos = null;
    	List<EgovMap> custhistorys = null;
    	
    	if(vo.getCustinfo() == null)
    	{
    		return new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS );
    	}
    	custinfo = vo.getCustinfo();
    	custhists = vo.getCusthist();
    	credits = vo.getCredit();
    	dambos = vo.getDambo();
    	custhistorys = vo.getCusthistory();
    	
    	if(sess!=null && sess.getUser() != null)
    	{
    		custinfo.put("crUser", sess.getUser().getId());
    	}
    	
    	String newCustCode = null;
    	int mnCnt = 0;
    	
    	String newKey = null;
    	int nCnt = 0;
		if(custhists != null)
		{
			for(EgovMap hists : custhists)
			{
				hists.put("corpCode", custinfo.get("corpCode"));
				hists.put("custCode", custinfo.get("custCode"));
				
				hists.put("salesmanCust", custinfo.get("custCode"));
				hists.put("startDate", StringUtils.remove((String)hists.get("startDate"),"-"));
				hists.put("lastDate", StringUtils.remove((String)hists.get("lastDate"),"-"));
				hists.put("crUser", sess.getUser().getId());
				hists.put("mdUser", sess.getUser().getId());
				
				if(sess!=null && sess.getUser() != null)
    	    	{
    				custinfo.put("crUser", sess.getUser().getId());
    	    	}
				
				if("C".equals((String)hists.get("dsType")))
				{
					int seqNo = mpCustHistDAO.insertMpCustHist(hists);
					hists.put("seqNo", seqNo);
					
					nCnt = mpSalesmanCustDAO.deleteMpSalesmanCust(hists);
					newKey = mpSalesmanCustDAO.insertMpSalesmanCust(hists);
				}
				else if("U".equals((String)hists.get("dsType")))
				{
					mpCustHistDAO.updateMpCustHist(hists);
					
					nCnt = mpSalesmanCustDAO.deleteMpSalesmanCust(hists);
					newKey = mpSalesmanCustDAO.insertMpSalesmanCust(hists);
					
				}else if("D".equals((String)hists.get("dsType")))
				{
					nCnt = mpCustHistDAO.deleteMpCustHist(hists);
					
					nCnt = mpSalesmanCustDAO.deleteMpSalesmanCust(hists);
				}
			}
				
		}
		/**	**/
    	if(dambos != null)
    	{
    		for(EgovMap dambo : dambos)
			{
    			dambo.put("corpCode", custinfo.get("corpCode"));
    			dambo.put("custCode", custinfo.get("custCode"));
    			
    			if(StringUtils.isEmpty(String.valueOf(dambo.get("damCkind"))))
    			{
    				throw new Exception("종류코드는 필수입니다.");
    			}
    			if(StringUtils.isEmpty(String.valueOf(dambo.get("damPrice"))))
    			{
    				throw new Exception("담보가액은 필수입니다.");
    			}
    			
    			if(sess!=null && sess.getUser() != null)
            	{
    				dambo.put("crUser", sess.getUser().getId());
    				dambo.put("mdUser", sess.getUser().getId());
            	}
    			
    			if("C".equals((String)dambo.get("dsType")))
				{
    				int damCode = mpDamboInfoDAO.insertMpDamboInfo(dambo);
    				dambo.put("damCode", damCode);
				}
    			else if("U".equals((String)dambo.get("dsType")))
				{
    				nCnt = mpDamboInfoDAO.updateMpDamboInfo(dambo);
				}
    			else if("D".equals((String)dambo.get("dsType")))
				{
    				nCnt = mpDamboInfoDAO.deleteMpDamboInfo(dambo);
				}
        		
			}
    		
    	}
		
		/**	**/
    	if(credits != null)
    	{
    		for(EgovMap cred : credits)
			{
    			cred.put("corpCode", custinfo.get("corpCode"));
    			cred.put("custCode", custinfo.get("custCode"));
    			cred.put("startDate", StringUtils.remove((String)cred.get("startDate"),"-"));
    			cred.put("lastDate", StringUtils.remove((String)cred.get("lastDate"),"-"));
    			
    			if(StringUtils.isEmpty(String.valueOf(cred.get("baseLimit"))))
    			{
    				throw new Exception("기본한도는 필수입니다.");
    			}
    			if(StringUtils.isEmpty(String.valueOf(cred.get("oneLimit"))))
    			{
    				throw new Exception("1회한도는 필수입니다.");
    			}
    			
    			if(sess!=null && sess.getUser() != null)
            	{
    				cred.put("crUser", sess.getUser().getId());
    				cred.put("mdUser", sess.getUser().getId());
            	}
    			
    			if("C".equals((String)cred.get("dsType")))
				{
    				newKey = mpCreditLimitDAO.insertMpCreditLimit(cred);
				}
    			else if("U".equals((String)cred.get("dsType")))
				{
    				mpCreditLimitDAO.updateMpCreditLimit(cred);
				}
    			else if("D".equals((String)cred.get("dsType")))
				{
    				mpCreditLimitDAO.deleteMpCreditLimit(cred);
				}
        		
			}
    		
    	}
    	
    	if(custhistorys != null)
    	{
    		for(EgovMap cred : custhistorys)
			{
    			cred.put("corpCode", custinfo.get("corpCode"));
    			cred.put("custCode", custinfo.get("custCode"));
    			
    			if(sess!=null && sess.getUser() != null)
            	{
    				cred.put("crUser", sess.getUser().getId());
    				cred.put("mdUser", sess.getUser().getId());
            	}
    			
    			if("C".equals((String)cred.get("dsType")))
				{
    				newKey = mpCustHistoryDAO.insertMpCustHistory(cred);
				}
    			else if("U".equals((String)cred.get("dsType")))
				{
    				mpCreditLimitDAO.updateMpCreditLimit(cred);
				}
    			else if("D".equals((String)cred.get("dsType")))
				{
    				mpCreditLimitDAO.deleteMpCreditLimit(cred);
				}
        		
			}
    		
    	}
    	
    	if("D".equals((String)custinfo.get("dsType")))
    	{
    		mnCnt = mpCustInfoDAO.deleteMpCustInfo(custinfo);
    		if(mnCnt == 0)
    		{
    			throw new Exception("삭제가 실패하였습니다.");
    		}
    	}
    	else if("C".equals((String)custinfo.get("dsType")) || "U".equals((String)custinfo.get("dsType")))
    	{
    		if(mpCustInfoDAO.checkCustCodeCnt(custinfo) > 0)
    		{
    			mnCnt = mpCustInfoDAO.updateMpCustInfo(custinfo);
        		if(mnCnt == 0)
        		{
        			throw new Exception("수정이 실패하였습니다.");
        		}
    		}
    		else
    		{
    			newCustCode = mpCustInfoDAO.insertMpCustInfo(custinfo);
        		if(newCustCode == null)
        		{
        			throw new Exception("등록이 실패하였습니다.");
        		}
    		}
    	}

    	LOGGER.debug("newCustCode = " + newCustCode);
    	
    	result.setExtraData(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return result;
    }

    /**
	 * mp_cust_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpCustInfo(EgovMap vo) throws Exception {
        mpCustInfoDAO.updateMpCustInfo(vo);
    }
    
    public void updateMpCustInfoFac(MpCustInfoVO vo) throws Exception {
    	mpCustInfoDAO.updateMpCustInfoFac(vo);
    }

    /**
	 * mp_cust_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpCustInfo(MpCustInfoVO vo) throws Exception {
        //mpCustInfoDAO.deleteMpCustInfo(vo);
    }

    /**
	 * mp_cust_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustInfoVO
	 * @return 조회한 mp_cust_info
	 * @exception Exception
	 */
    public MpCustInfoVO selectMpCustInfo(MpCustInfoVO vo) throws Exception {
        MpCustInfoVO resultVO = mpCustInfoDAO.selectMpCustInfo(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }
    
    /**
	 * mp_cust_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustInfoVO
	 * @return 조회한 mp_cust_info
	 * @exception Exception
	 */
    public MpCustInfoVO selectDupMpCustInfo(MpCustInfoVO vo) throws Exception {
        MpCustInfoVO resultVO = mpCustInfoDAO.selectMpCustInfo(vo);        
        return resultVO;
    }

    /**
	 * mp_cust_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_info 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustInfoList(MpCustInfoDefaultVO searchVO) throws Exception {
        return mpCustInfoDAO.selectMpCustInfoList(searchVO);
    }

    /**
	 * mp_cust_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_info 총 갯수
	 * @exception
	 */
    public int selectMpCustInfoListTotCnt(MpCustInfoDefaultVO searchVO) {
		return mpCustInfoDAO.selectMpCustInfoListTotCnt(searchVO);
	}
    
    public List<?> selectMpCustInfoCodeList(MpCustInfoDefaultVO searchVO) throws Exception{
    	 return mpCustInfoDAO.selectMpCustInfoCodeList(searchVO);
    }
    
    public EgovMap selectMpCustAccountInfo(MpCustInfoVO vo) throws Exception {
    	return mpCustInfoDAO.selectMpCustAccountInfo(vo);
    }
    
    public List<?> selectOnlineUploadMpCustInfoCodeList(MpCustInfoDefaultVO searchVO) throws Exception {
    	return mpCustInfoDAO.selectOnlineUploadMpCustInfoCodeList(searchVO);
    }

}
