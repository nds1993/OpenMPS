package nds.mpm.sales.SD0204.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0204.service.MpCustPriClassService;
import nds.mpm.sales.SD0204.vo.MpCustPriClassDefaultVO;
import nds.mpm.sales.SD0204.vo.MpCustPriClassVO;
import nds.mpm.sales.SD0204.service.MpCustPriClassDAO;

/**
 * @Class Name : MpCustPriClassServiceImpl.java
 * @Description : MpCustPriClass Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpCustPriClassService")
public class MpCustPriClassServiceImpl extends EgovAbstractServiceImpl implements
        MpCustPriClassService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpCustPriClassServiceImpl.class);

    @Resource(name="mpCustPriClassDAO")
    private MpCustPriClassDAO mpCustPriClassDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpCustPriClassIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_cust_pri_class을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustPriClassVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpCustPriClass(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String newKey = null;
        int nCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if(StringUtils.isEmpty((String)reqVo.get("dsType")))
    		{
    			result.setResultCode(Consts.ResultCode.RC_INVALID_PARAMS.getCode());
    			return result;
    		}
    		if(StringUtils.isEmpty((String)reqVo.get("custCode")))
    		{
    			result.setResultCode(Consts.ResultCode.RC_INVALID_PARAMS.getCode());
    			return result;
    		}
    		if(StringUtils.isEmpty((String)reqVo.get("proCode")))
    		{
    			result.setResultCode(Consts.ResultCode.RC_INVALID_PARAMS.getCode());
    			return result;
    		}
    		if(StringUtils.isEmpty((String)reqVo.get("priceClass")))
    		{
    			result.setResultCode(Consts.ResultCode.RC_INVALID_PARAMS.getCode());
    			return result;
    		}
    		
    		if("C".equals((String)reqVo.get("dsType"))
    			|| "U".equals((String)reqVo.get("dsType")))
    		{
    			newKey = mpCustPriClassDAO.insertMpCustPriClass(reqVo);
    			if(newKey == null)
    			{
    				throw new Exception("insert fail!!");
    			}
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			nCnt = mpCustPriClassDAO.deleteMpCustPriClass(reqVo);
    			if(nCnt == 0)
    			{
    				throw new Exception("delete fail!!");
    			}
    		}
    	}
    	LOGGER.debug("nCnt :: " + nCnt);
    	
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	result.setExtraData(pageSet);  	
        return result;
    }

    /**
	 * mp_cust_pri_class을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustPriClassVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpCustPriClass(MpCustPriClassVO vo) throws Exception {
        mpCustPriClassDAO.updateMpCustPriClass(vo);
    }

    /**
	 * mp_cust_pri_class을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustPriClassVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpCustPriClass(MpCustPriClassVO vo) throws Exception {
        //mpCustPriClassDAO.deleteMpCustPriClass(vo);
    }

    /**
	 * mp_cust_pri_class을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustPriClassVO
	 * @return 조회한 mp_cust_pri_class
	 * @exception Exception
	 */
    public MpCustPriClassVO selectMpCustPriClass(MpCustPriClassVO vo) throws Exception {
        MpCustPriClassVO resultVO = mpCustPriClassDAO.selectMpCustPriClass(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    public List<?> selectMpSalesmanCustList(MpCustPriClassDefaultVO searchVO) throws Exception {
        return mpCustPriClassDAO.selectMpSalesmanCustList(searchVO);
    }

    /**
	 * mp_cust_pri_class 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_pri_class 총 갯수
	 * @exception
	 */
    public int selectMpSalesmanCustListTotCnt(MpCustPriClassDefaultVO searchVO) {
        return mpCustPriClassDAO.selectMpSalesmanCustListTotCnt(searchVO);
    }
    
    public List<?> selectMpCustPriClassList(MpCustPriClassDefaultVO searchVO) throws Exception {
    	return mpCustPriClassDAO.selectMpCustPriClassList(searchVO);
    }
}
