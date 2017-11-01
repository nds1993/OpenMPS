package nds.tmm.common.TMCOOS70.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOOS70.service.TMCOOS70DAO;
import nds.tmm.common.TMCOOS70.service.TMCOOS70Service;
import nds.tmm.common.TMCOOS70.vo.TMCOOS70DefaultVO;
import nds.tmm.common.TMCOOS70.vo.TMCOOS70VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOOS70ServiceImpl.java
 * @Description : TMCOOS70 Business Implement class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOOS70Service")
public class TMCOOS70ServiceImpl extends TMMPPBaseService implements
        TMCOOS70Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOOS70ServiceImpl.class);

    @Resource(name="TMCOOS70DAO")
    private TMCOOS70DAO TMCOOS70DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOOS70IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_deptxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOOS70VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertTMCOOS70(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")) && selectTMCOOS70(reqVo) != null)
    		{
    			return new ResultEx( Consts.ResultCode.RC_DUPLICATE );
    		}
    		
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			TMCOOS70DAO.insertTMCOOS70(reqVo);
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			TMCOOS70DAO.updateTMCOOS70(reqVo);
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			// 히스토리 테이블에 삭제 로그 추가
    			TMCOOS70DAO.insertLogTMCOOS70(reqVo);
    			
    			TMCOOS70DAO.deleteTMCOOS70(reqVo);
    		}
    		
    	}

    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	
        return result;
    	
    }

    /**
	 * tm_deptxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 EgovMap
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOOS70(EgovMap vo) throws Exception {
        TMCOOS70DAO.updateTMCOOS70(vo);
    }

    /**
	 * tm_deptxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 EgovMap
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOOS70(EgovMap vo) throws Exception {
    	// 히스토리 테이블에 삭제 로그 추가
		TMCOOS70DAO.insertLogTMCOOS70(vo);
    	
        TMCOOS70DAO.deleteTMCOOS70(vo);
    }

    /**
	 * tm_deptxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 EgovMap
	 * @return 조회한 tm_deptxm
	 * @exception Exception
	 */
    public TMCOOS70VO selectTMCOOS70(EgovMap vo) throws Exception {
        TMCOOS70VO resultVO = TMCOOS70DAO.selectTMCOOS70(vo);
        return resultVO;
    }

    /**
	 * tm_deptxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_deptxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOOS70List(TMCOOS70DefaultVO searchVO) throws Exception {
        return TMCOOS70DAO.selectTMCOOS70List(searchVO);
    }

    /**
	 * tm_deptxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_deptxm 총 갯수
	 * @exception
	 */
    public int selectTMCOOS70ListTotCnt(TMCOOS70DefaultVO searchVO) {
		return TMCOOS70DAO.selectTMCOOS70ListTotCnt(searchVO);
	}
    
}
