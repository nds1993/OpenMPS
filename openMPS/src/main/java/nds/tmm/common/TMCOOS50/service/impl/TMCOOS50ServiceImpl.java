package nds.tmm.common.TMCOOS50.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0101.vo.TmPlatxmDefaultVO;
import nds.tmm.common.TMCOOS50.service.TMCOOS50DAO;
import nds.tmm.common.TMCOOS50.service.TMCOOS50Service;
import nds.tmm.common.TMCOOS50.vo.TMCOOS50DefaultVO;
import nds.tmm.common.TMCOOS50.vo.TMCOOS50VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOOS50ServiceImpl.java
 * @Description : TMCOOS50 Business Implement class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.06
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOOS50Service")
public class TMCOOS50ServiceImpl extends TMMPPBaseService  implements TMCOOS50Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOOS50ServiceImpl.class);

    @Resource(name="TMCOOS50DAO")
    private TMCOOS50DAO TMCOOS50DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOOS50IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_orguxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOOS50VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertTMCOOS50(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")) && selectTMCOOS50(reqVo) != null)
    		{
    			return new ResultEx( Consts.ResultCode.RC_DUPLICATE );
    		}
    		
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			TMCOOS50DAO.insertTMCOOS50(reqVo);
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			TMCOOS50DAO.updateTMCOOS50(reqVo);
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			TMCOOS50DAO.deleteTMCOOS50(reqVo);
    		}
    		
    	}

    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	
        return result;
    }

    /**
	 * tm_orguxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOOS50VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOOS50(EgovMap vo) throws Exception {
        TMCOOS50DAO.updateTMCOOS50(vo);
    }

    /**
	 * tm_orguxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOOS50VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOOS50(EgovMap vo) throws Exception {
        TMCOOS50DAO.deleteTMCOOS50(vo);
    }

    /**
	 * tm_orguxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOOS50VO
	 * @return 조회한 tm_orguxm
	 * @exception Exception
	 */
    public TMCOOS50VO selectTMCOOS50(EgovMap vo) throws Exception {
        TMCOOS50VO resultVO = TMCOOS50DAO.selectTMCOOS50(vo);
        return resultVO;
    }

    /**
	 * tm_orguxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_orguxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOOS50List(TMCOOS50DefaultVO searchVO) throws Exception {
        return TMCOOS50DAO.selectTMCOOS50List(searchVO);
    }

    /**
	 * tm_orguxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_orguxm 총 갯수
	 * @exception
	 */
    public int selectTMCOOS50ListTotCnt(TMCOOS50DefaultVO searchVO) {
		return TMCOOS50DAO.selectTMCOOS50ListTotCnt(searchVO);
	}
    
    public int checkDupTMCOOS50_S(TMCOOS50DefaultVO searchVO) {
    	return TMCOOS50DAO.checkDupTMCOOS50_S(searchVO);
    }
    
}
