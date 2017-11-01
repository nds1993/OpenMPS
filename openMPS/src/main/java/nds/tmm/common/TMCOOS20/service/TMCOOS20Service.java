package nds.tmm.common.TMCOOS20.service;

import java.util.List;

import nds.tmm.common.TMCOOS20.vo.TMCOOS20DefaultVO;
import nds.tmm.common.TMCOOS20.vo.TMCOOS20VO;

/**
 * @Class Name : TMCOOS20Service.java
 * @Description : TMCOOS20 Business class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOOS20Service {
	
	/**
	 * tm_platxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOOS20VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertTMCOOS20(TMCOOS20VO vo) throws Exception;
    
    /**
	 * tm_platxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOOS20VO
	 * @return void형
	 * @exception Exception
	 */
    void updateTMCOOS20(TMCOOS20VO vo) throws Exception;
    
    /**
	 * tm_platxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOOS20VO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTMCOOS20(TMCOOS20VO vo) throws Exception;
    
    /**
	 * tm_platxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOOS20VO
	 * @return 조회한 tm_platxm
	 * @exception Exception
	 */
    TMCOOS20VO selectTMCOOS20(TMCOOS20VO vo) throws Exception;
    
    /**
	 * tm_platxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 목록
	 * @exception Exception
	 */
    List selectTMCOOS20List(TMCOOS20DefaultVO searchVO) throws Exception;
    
    /**
	 * tm_platxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 총 갯수
	 * @exception
	 */
    int selectTMCOOS20ListTotCnt(TMCOOS20DefaultVO searchVO);
    
}
