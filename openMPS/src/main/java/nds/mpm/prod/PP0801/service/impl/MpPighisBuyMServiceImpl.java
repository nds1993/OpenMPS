package nds.mpm.prod.PP0801.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0801.service.MpPighisBuyMDAO;
import nds.mpm.prod.PP0801.service.MpPighisBuyMService;
import nds.mpm.prod.PP0801.vo.MpPighisBuyMVO;
import nds.mpm.sales.SD0101.service.MpCustInfoDAO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPighisBuyMServiceImpl.java
 * @Description : MpPighisBuyM Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPighisBuyMService")
public class MpPighisBuyMServiceImpl extends EgovAbstractServiceImpl implements
        MpPighisBuyMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPighisBuyMServiceImpl.class);

    @Resource(name="mpPighisBuyMDAO")
    private MpPighisBuyMDAO mpPighisBuyMDAO;
    
    @Resource(name="mpCustInfoDAO")
    private MpCustInfoDAO mpCustInfoDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPighisBuyMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_pighis_buy_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPighisBuyMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPighisBuyM(List<EgovMap> vos) throws Exception {
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
		 * 
		 * 도축장 이름으로 
		 * 매입처 정보조회 
		 */
    	EgovMap custInfo = mpPighisBuyMDAO.selectMpPighisBuyMBuyTypeInfo(vos.get(0));
    	
    	if( custInfo == null )
    	{
    		result.setResultCode(-2);
    		result.setMsg("not fount buy type cust info");
    		return result;
    	}

    	String newKey = null;
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		reqVo.put("compSsno", StringUtils.remove((String)custInfo.get("compSsno"),"-"));
    		reqVo.put("compName", custInfo.get("compName"));
    		reqVo.put("compOwner", custInfo.get("compOwner"));
    		reqVo.put("compTel", custInfo.get("compTel"));
    		reqVo.put("compAddr", custInfo.get("compAddr"));
    		
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			newKey = mpPighisBuyMDAO.insertMpPighisBuyM(reqVo);
    			if(newKey == null)
    			{
    				throw new Exception("insert fall!!");
    			}
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			iCnt = mpPighisBuyMDAO.updateMpPighisBuyM(reqVo);
    			if(iCnt == 0)
    			{
    				throw new Exception("update fall!!");
    			}
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			iCnt = mpPighisBuyMDAO.deleteMpPighisBuyM(reqVo);
    			if(iCnt == 0)
    			{
    				throw new Exception("delete fall!!");
    			}
    		}
    			
    	}
    	return result;
    }

    /**
	 * mp_pighis_buy_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPighisBuyMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPighisBuyM(MpPighisBuyMVO vo) throws Exception {
        //mpPighisBuyMDAO.updateMpPighisBuyM(vo);
    }

    /**
	 * mp_pighis_buy_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPighisBuyMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPighisBuyM(MpPighisBuyMVO vo) throws Exception {
        //mpPighisBuyMDAO.deleteMpPighisBuyM(vo);
    }

    /**
	 * mp_pighis_buy_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPighisBuyMVO
	 * @return 조회한 mp_pighis_buy_m
	 * @exception Exception
	 */
    public MpPighisBuyMVO selectMpPighisBuyM(EgovMap vo) throws Exception {
        MpPighisBuyMVO resultVO = mpPighisBuyMDAO.selectMpPighisBuyM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_pighis_buy_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pighis_buy_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpPighisBuyMList(MpPighisBuyMVO searchVO) throws Exception {
        return mpPighisBuyMDAO.selectMpPighisBuyMList(searchVO);
    }

    /**
	 * mp_pighis_buy_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pighis_buy_m 총 갯수
	 * @exception
	 */
    public int selectMpPighisBuyMListTotCnt(MpPighisBuyMVO searchVO) {
		return mpPighisBuyMDAO.selectMpPighisBuyMListTotCnt(searchVO);
	}
    
    public List<EgovMap> selectMpPighisBuyMSendFormatList(MpPighisBuyMVO searchVO) throws Exception {
    	return mpPighisBuyMDAO.selectMpPighisBuyMSendFormatList(searchVO);
    }

    public int updateApiTimeMpPighisBuyM(EgovMap vo) throws Exception {
    	return mpPighisBuyMDAO.updateApiTimeMpPighisBuyM(vo);
    }
    
    public int checkDupBuyDateMpPighisBuyM(MpPighisBuyMVO searchVO) {
    	return mpPighisBuyMDAO.checkDupBuyDateMpPighisBuyM(searchVO);
    }
    public int deleteDupMpPighisBuyM(MpPighisBuyMVO searchVO) {
    	return mpPighisBuyMDAO.deleteDupMpPighisBuyM(searchVO);
    }
    public String selectBuyTypeMpPighisBuyM_S(MpPighisBuyMVO searchVO) {
    	return mpPighisBuyMDAO.selectBuyTypeMpPighisBuyM_S(searchVO);
    }

}
