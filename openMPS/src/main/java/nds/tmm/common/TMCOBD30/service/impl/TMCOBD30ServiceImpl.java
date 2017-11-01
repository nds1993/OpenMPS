package nds.tmm.common.TMCOBD30.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOBD30.service.TMCOBD30DAO;
import nds.tmm.common.TMCOBD30.service.TMCOBD30Service;
import nds.tmm.common.TMCOBD30.vo.TMCOBD30VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : TMCOBD30ServiceImpl.java
 * @Description : TMCOBD30 Business Implement class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOBD30Service")
public class TMCOBD30ServiceImpl extends TMMPPBaseService implements
        TMCOBD30Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOBD30ServiceImpl.class);

    @Resource(name="TMCOBD30DAO")
    private TMCOBD30DAO TMCOBD30DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOBD30IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_faqxxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD30VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertTMCOBD30(TMCOBD30VO vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	TMCOBD30DAO.insertTMCOBD30(vo);
    	
        return result;
    	
    }

    /**
	 * tm_faqxxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOBD30VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOBD30(TMCOBD30VO vo) throws Exception {
        TMCOBD30DAO.updateTMCOBD30(vo);
    }
    
    /**
     * tm_faqxxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD30VO
     * @return void형
     * @exception Exception
     */
    public void updateReadCntTMCOBD30(TMCOBD30VO vo) throws Exception {
    	TMCOBD30DAO.updateReadCntTMCOBD30(vo);
    }

    /**
	 * tm_faqxxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOBD30VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOBD30(TMCOBD30VO vo) throws Exception {
        TMCOBD30DAO.deleteTMCOBD30(vo);
    }

    /**
	 * tm_faqxxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOBD30VO
	 * @return 조회한 tm_faqxxm
	 * @exception Exception
	 */
    public TMCOBD30VO selectTMCOBD30(TMCOBD30VO vo) throws Exception {
        TMCOBD30VO resultVO = TMCOBD30DAO.selectTMCOBD30(vo);
        return resultVO;
    }
    
    /**
	 * tm_bdredh을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD30VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertBdredhTMCOBD30(TMCOBD30VO vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	TMCOBD30DAO.insertBdredhTMCOBD30(vo);
    	
        return result;
    	
    }
    
    /**
     * tm_bdredh을 조회한다.
     * @param vo - 조회할 정보가 담긴 TMCOBD30VO
     * @return 조회한 tm_bdredh
     * @exception Exception
     */
    public TMCOBD30VO selectBdredhTMCOBD30(TMCOBD30VO vo) throws Exception {
    	TMCOBD30VO resultVO = TMCOBD30DAO.selectBdredhTMCOBD30(vo);
    	return resultVO;
    }

    /**
	 * tm_faqxxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_faqxxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOBD30List(TMCOBD30VO searchVO) throws Exception {
        return TMCOBD30DAO.selectTMCOBD30List(searchVO);
    }

    /**
	 * tm_faqxxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_faqxxm 총 갯수
	 * @exception
	 */
    public int selectTMCOBD30ListTotCnt(TMCOBD30VO searchVO) {
		return TMCOBD30DAO.selectTMCOBD30ListTotCnt(searchVO);
	}
    
}
