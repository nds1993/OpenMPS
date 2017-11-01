package nds.mpm.prod.PP0104.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0103.vo.MpItemMasterMVO;
import nds.mpm.prod.PP0104.service.MpSetItemMDAO;
import nds.mpm.prod.PP0104.service.MpSetItemMService;
import nds.mpm.prod.PP0104.vo.MpSetItemMDefaultVO;
import nds.mpm.prod.PP0104.vo.MpSetItemMVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpSetItemMServiceImpl.java
 * @Description : MpSetItemM Business Implement class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpSetItemMService")
public class MpSetItemMServiceImpl extends TMMPPBaseService implements
        MpSetItemMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpSetItemMServiceImpl.class);

    @Resource(name="mpSetItemMDAO")
    private MpSetItemMDAO mpSetItemMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpSetItemMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_set_item_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpSetItemMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpSetItemM(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	for(EgovMap reqVo : vos)
    	{
    		if(StringUtils.isEmpty((String)reqVo.get("proCode")) ) continue;
    		
    		if("D".equals((String)reqVo.get("dsType")))
    		{
    			mpSetItemMDAO.deleteMpSetItemM(reqVo);
    		}
    		else
    		{
	    		if(selectMpSetItemM(reqVo) != null)
	    		{
	    			mpSetItemMDAO.updateMpSetItemM(reqVo);
	    		}
	    		else
	    		{
	    			mpSetItemMDAO.insertMpSetItemM(reqVo);
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
	 * mp_set_item_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSetItemMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpSetItemM(MpSetItemMVO vo) throws Exception {
        //mpSetItemMDAO.updateMpSetItemM(vo);
    }

    /**
	 * mp_set_item_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpSetItemMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpSetItemM(MpSetItemMVO vo) throws Exception {
        //mpSetItemMDAO.deleteMpSetItemM(vo);
    }

    /**
	 * mp_set_item_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpSetItemMVO
	 * @return 조회한 mp_set_item_m
	 * @exception Exception
	 */
    public MpSetItemMVO selectMpSetItemM(EgovMap vo) throws Exception {
        MpSetItemMVO resultVO = mpSetItemMDAO.selectMpSetItemM(vo);
        return resultVO;
    }

    /**
	 * mp_set_item_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_set_item_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpSetItemMList(MpSetItemMDefaultVO searchVO) throws Exception {
        return mpSetItemMDAO.selectMpSetItemMList(searchVO);
    }

    /**
	 * mp_set_item_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_set_item_m 총 갯수
	 * @exception
	 */
    public int selectMpSetItemMListTotCnt(MpSetItemMDefaultVO searchVO) {
		return mpSetItemMDAO.selectMpSetItemMListTotCnt(searchVO);
	}
    
    public List<?> selectMpItemMasterMList(MpItemMasterMVO searchVO) throws Exception {
    	return mpSetItemMDAO.selectMpItemMasterMList(searchVO);
    }
    
}
