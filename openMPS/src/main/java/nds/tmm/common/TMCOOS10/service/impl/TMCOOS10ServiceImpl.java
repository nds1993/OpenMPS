package nds.tmm.common.TMCOOS10.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOOS10.service.TMCOOS10DAO;
import nds.tmm.common.TMCOOS10.service.TMCOOS10Service;
import nds.tmm.common.TMCOOS10.vo.TMCOOS10DefaultVO;
import nds.tmm.common.TMCOOS10.vo.TMCOOS10VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOOS10ServiceImpl.java
 * @Description : TMCOOS10 Business Implement class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOOS10Service")
public class TMCOOS10ServiceImpl extends TMMPPBaseService implements
        TMCOOS10Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOOS10ServiceImpl.class);

    @Resource(name="TMCOOS10DAO")
    private TMCOOS10DAO TMCOOS10DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOOS10IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_corpxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOOS10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertTMCOOS10(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")) && selectTMCOOS10(reqVo) != null)
    		{
    			return new ResultEx( Consts.ResultCode.RC_DUPLICATE );
    		}
    		
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			TMCOOS10DAO.insertTMCOOS10(reqVo);
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			TMCOOS10DAO.updateTMCOOS10(reqVo);
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			TMCOOS10DAO.deleteTMCOOS10(reqVo);
    		}
    		
    	}

    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	
        return result;
    	
    }

    /**
	 * tm_corpxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 EgovMap
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOOS10(EgovMap vo) throws Exception {
        TMCOOS10DAO.updateTMCOOS10(vo);
    }

    /**
	 * tm_corpxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 EgovMap
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOOS10(EgovMap vo) throws Exception {
        TMCOOS10DAO.deleteTMCOOS10(vo);
    }

    /**
	 * tm_corpxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 EgovMap
	 * @return 조회한 tm_corpxm
	 * @exception Exception
	 */
    public TMCOOS10VO selectTMCOOS10(EgovMap vo) throws Exception {
        TMCOOS10VO resultVO = TMCOOS10DAO.selectTMCOOS10(vo);
        return resultVO;
    }

    /**
	 * tm_corpxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_corpxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOOS10List(TMCOOS10DefaultVO searchVO) throws Exception {
        return TMCOOS10DAO.selectTMCOOS10List(searchVO);
    }

    /**
	 * tm_corpxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_corpxm 총 갯수
	 * @exception
	 */
    public int selectTMCOOS10ListTotCnt(TMCOOS10DefaultVO searchVO) {
		return TMCOOS10DAO.selectTMCOOS10ListTotCnt(searchVO);
	}
    
}
