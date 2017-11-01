package nds.mpm.prod.PP0201.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0201.service.MpYieldInfoMDAO;
import nds.mpm.prod.PP0201.service.MpYieldInfoMService;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMDefaultVO;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpYieldInfoMServiceImpl.java
 * @Description : MpYieldInfoM Business Implement class
 * @Modification Information
 *
 * @author M
 * @since M
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpYieldInfoMService")
public class MpYieldInfoMServiceImpl extends TMMPPBaseService implements
        MpYieldInfoMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpYieldInfoMServiceImpl.class);

    @Resource(name="mpYieldInfoMDAO")
    private MpYieldInfoMDAO mpYieldInfoMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpYieldInfoMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_yield_info_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpYieldInfoMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpYieldInfoM(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String newKey= null;
    	int nCnt = 0;
    	
    	EgovMap firstMap = vos.get(0);
    	
    	if(mpYieldInfoMDAO.checkProdClosing(firstMap) > 0)
    	{
    		result.setMsg("생산일자가 마감되었습니다.");
    		result.setResultCode(Consts.ResultCode.RC_DENINED.getCode());
    		return result;
    	}
    	
    	for(EgovMap reqVo : vos)
    	{
    		String seq = (String)reqVo.get("seq");
    		
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			if(mpYieldInfoMDAO.checkDupMpYieldInfoM_S(reqVo) > 0)
    			{
    				result.setExtraData("이미등록된 생산일자의 공장그룹입니다.");
    				result.setResultCode(Consts.ResultCode.RC_DUPLICATE.getCode());
    				return result;
    			}
    			
    			if(StringUtils.isNotEmpty(seq))
        		{	
    	    		if(selectMpYieldInfoM(reqVo) != null)
    	    		{
    	    			return new ResultEx(Consts.ResultCode.RC_DUPLICATE);
    	    		}
        		}
    		}
    	}
    	
    	
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			newKey = mpYieldInfoMDAO.insertMpYieldInfoM(reqVo);
    			if(newKey == null)
    			{
    				throw new Exception("insert fail!!");
    			}
    			reqVo.put("seq", newKey);
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			nCnt = mpYieldInfoMDAO.updateMpYieldInfoM(reqVo);
    			if(nCnt == 0)
    			{
    				throw new Exception("delete fail!!");
    			}
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			nCnt = mpYieldInfoMDAO.deleteMpYieldInfoM(reqVo);
    			if(nCnt == 0)
    			{
    				throw new Exception("delete fail!!");
    			}
    		}
    	}

    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	result.setExtraData(pageSet);
        return result;
    }

    /**
	 * mp_yield_info_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpYieldInfoMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpYieldInfoM(MpYieldInfoMVO vo) throws Exception {
       // mpYieldInfoMDAO.updateMpYieldInfoM(vo);
    }

    /**
	 * mp_yield_info_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpYieldInfoMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpYieldInfoM(MpYieldInfoMVO vo) throws Exception {
        //mpYieldInfoMDAO.deleteMpYieldInfoM(vo);
    }

    /**
	 * mp_yield_info_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpYieldInfoMVO
	 * @return 조회한 mp_yield_info_m
	 * @exception Exception
	 */
    public MpYieldInfoMVO selectMpYieldInfoM(EgovMap vo) throws Exception {
        MpYieldInfoMVO resultVO = mpYieldInfoMDAO.selectMpYieldInfoM(vo);
        return resultVO;
    }

    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpYieldInfoMList(MpYieldInfoMDefaultVO searchVO) throws Exception {
        return mpYieldInfoMDAO.selectMpYieldInfoMList(searchVO);
    }

    /**
	 * mp_yield_info_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 총 갯수
	 * @exception
	 */
    public int selectMpYieldInfoMListTotCnt(MpYieldInfoMDefaultVO searchVO) {
		return mpYieldInfoMDAO.selectMpYieldInfoMListTotCnt(searchVO);
	}
    
}
