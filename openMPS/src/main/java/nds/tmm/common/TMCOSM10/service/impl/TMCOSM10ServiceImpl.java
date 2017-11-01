package nds.tmm.common.TMCOSM10.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOSM10.service.TMCOSM10DAO;
import nds.tmm.common.TMCOSM10.service.TMCOSM10Service;
import nds.tmm.common.TMCOSM10.vo.TMCOSM10VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : TMCOSM10ServiceImpl.java
 * @Description : TMCOSM10 Business Implement class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOSM10Service")
public class TMCOSM10ServiceImpl extends TMMPPBaseService implements
        TMCOSM10Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOSM10ServiceImpl.class);

    @Resource(name="TMCOSM10DAO")
    private TMCOSM10DAO TMCOSM10DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOSM10IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_svrqxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOSM10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertTMCOSM10(TMCOSM10VO vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	TMCOSM10DAO.insertTMCOSM10(vo);
    	
        return result;
    	
    }
    
    /**
     * tm_svpcxm을 등록한다.
     * @param vo - 등록할 정보가 담긴 TMCOSM10VO
     * @return 등록 결과
     * @exception Exception
     */
    public ResultEx insertTmSvpcmx(TMCOSM10VO vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	TMCOSM10DAO.insertTmSvpcmx(vo);
    	
    	return result;
    	
    }

    /**
	 * tm_svrqxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOSM10VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOSM10(TMCOSM10VO vo) throws Exception {
        TMCOSM10DAO.updateTMCOSM10(vo);
    }

    /**
	 * tm_svrqxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOSM10VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOSM10(TMCOSM10VO vo) throws Exception {
        TMCOSM10DAO.deleteTMCOSM10(vo);
    }
    
    /**
     * tm_svrqxm을 변경한다.
     * @param vo - 변경할 정보가 담긴 TMCOSM10VO
     * @return void형 
     * @exception Exception
     */
    public void updateProcStatusTMCOSM10(TMCOSM10VO vo) throws Exception {
    	TMCOSM10DAO.updateProcStatusTMCOSM10(vo);
    }

    /**
	 * tm_svrqxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOSM10VO
	 * @return 조회한 tm_svrqxm
	 * @exception Exception
	 */
    public TMCOSM10VO selectTMCOSM10(TMCOSM10VO vo) throws Exception {
        TMCOSM10VO resultVO = TMCOSM10DAO.selectTMCOSM10(vo);
        return resultVO;
    }

    /**
	 * tm_svrqxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_svrqxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOSM10List(TMCOSM10VO searchVO) throws Exception {
        return TMCOSM10DAO.selectTMCOSM10List(searchVO);
    }

    /**
	 * tm_svrqxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_svrqxm 총 갯수
	 * @exception
	 */
    public int selectTMCOSM10ListTotCnt(TMCOSM10VO searchVO) {
		return TMCOSM10DAO.selectTMCOSM10ListTotCnt(searchVO);
	}
    
}
