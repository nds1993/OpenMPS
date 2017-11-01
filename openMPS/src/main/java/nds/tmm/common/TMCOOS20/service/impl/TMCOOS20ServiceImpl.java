package nds.tmm.common.TMCOOS20.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.tmm.common.TMCOOS20.service.TMCOOS20DAO;
import nds.tmm.common.TMCOOS20.service.TMCOOS20Service;
import nds.tmm.common.TMCOOS20.vo.TMCOOS20DefaultVO;
import nds.tmm.common.TMCOOS20.vo.TMCOOS20VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : TMCOOS20ServiceImpl.java
 * @Description : TMCOOS20 Business Implement class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOOS20Service")
public class TMCOOS20ServiceImpl extends EgovAbstractServiceImpl implements
        TMCOOS20Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOOS20ServiceImpl.class);

    @Resource(name="TMCOOS20DAO")
    private TMCOOS20DAO TMCOOS20DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOOS20IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_platxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOOS20VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOOS20(TMCOOS20VO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	TMCOOS20DAO.insertTMCOOS20(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * tm_platxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOOS20VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOOS20(TMCOOS20VO vo) throws Exception {
        TMCOOS20DAO.updateTMCOOS20(vo);
    }

    /**
	 * tm_platxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOOS20VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOOS20(TMCOOS20VO vo) throws Exception {
        TMCOOS20DAO.deleteTMCOOS20(vo);
    }

    /**
	 * tm_platxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOOS20VO
	 * @return 조회한 tm_platxm
	 * @exception Exception
	 */
    public TMCOOS20VO selectTMCOOS20(TMCOOS20VO vo) throws Exception {
        TMCOOS20VO resultVO = TMCOOS20DAO.selectTMCOOS20(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * tm_platxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOOS20List(TMCOOS20DefaultVO searchVO) throws Exception {
        return TMCOOS20DAO.selectTMCOOS20List(searchVO);
    }

    /**
	 * tm_platxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 총 갯수
	 * @exception
	 */
    public int selectTMCOOS20ListTotCnt(TMCOOS20DefaultVO searchVO) {
		return TMCOOS20DAO.selectTMCOOS20ListTotCnt(searchVO);
	}
    
}
