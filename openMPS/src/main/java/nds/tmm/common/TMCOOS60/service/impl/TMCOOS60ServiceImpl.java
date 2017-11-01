package nds.tmm.common.TMCOOS60.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOOS60.service.TMCOOS60DAO;
import nds.tmm.common.TMCOOS60.service.TMCOOS60Service;
import nds.tmm.common.TMCOOS60.vo.TMCOOS60DefaultVO;
import nds.tmm.common.TMCOOS60.vo.TMCOOS60VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOOS60ServiceImpl.java
 * @Description : TMCOOS60 Business Implement class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.06
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOOS60Service")
public class TMCOOS60ServiceImpl extends TMMPPBaseService  implements
        TMCOOS60Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOOS60ServiceImpl.class);

    @Resource(name="TMCOOS60DAO")
    private TMCOOS60DAO TMCOOS60DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOOS60IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_teamxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOOS60VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertTMCOOS60(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")) && selectTMCOOS60(reqVo) != null)
    		{
    			return new ResultEx( Consts.ResultCode.RC_DUPLICATE );
    		}
    		
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			TMCOOS60DAO.insertTMCOOS60(reqVo);
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			TMCOOS60DAO.updateTMCOOS60(reqVo);
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			// 히스토리 테이블에 삭제 로그 추가
    			TMCOOS60DAO.insertLogTMCOOS60(reqVo);
    			
    			TMCOOS60DAO.deleteTMCOOS60(reqVo);
    		}
    		
    	}

    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	
        return result;

    }

    /**
	 * tm_teamxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOOS60VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOOS60(EgovMap vo) throws Exception {
        TMCOOS60DAO.updateTMCOOS60(vo);
    }

    /**
	 * tm_teamxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOOS60VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOOS60(EgovMap vo) throws Exception {
    	
    	// 히스토리 테이블에 삭제 로그 추가
		TMCOOS60DAO.insertLogTMCOOS60(vo);
		
        TMCOOS60DAO.deleteTMCOOS60(vo);
    }

    /**
	 * tm_teamxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOOS60VO
	 * @return 조회한 tm_teamxm
	 * @exception Exception
	 */
    public TMCOOS60VO selectTMCOOS60(EgovMap vo) throws Exception {
        TMCOOS60VO resultVO = TMCOOS60DAO.selectTMCOOS60(vo);
        
        return resultVO;
    }

    /**
	 * tm_teamxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_teamxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOOS60List(TMCOOS60DefaultVO searchVO) throws Exception {
        return TMCOOS60DAO.selectTMCOOS60List(searchVO);
    }

    /**
	 * tm_teamxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_teamxm 총 갯수
	 * @exception
	 */
    public int selectTMCOOS60ListTotCnt(TMCOOS60DefaultVO searchVO) {
		return TMCOOS60DAO.selectTMCOOS60ListTotCnt(searchVO);
	}
    
}
