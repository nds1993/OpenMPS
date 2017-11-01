package nds.tmm.common.TMCOUR50.service;

import java.util.List;

import nds.tmm.common.TMCOUR50.vo.TMCOUR50DefaultVO;
import nds.tmm.common.TMCOUR50.vo.TMCOUR50VO;

/**
 * @Class Name : TMCOUR50Service.java
 * @Description : TMCOUR50 Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOUR50Service {
	
	/**
	 * tm_authxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR50VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    int insertTMCOUR50(List<TMCOUR50VO> vo) throws Exception;
    
    /**
	 * tm_authxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR50VO
	 * @return void형
	 * @exception Exception
	 */
    void updateTMCOUR50(TMCOUR50VO vo) throws Exception;
    
    /**
	 * tm_authxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR50VO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTMCOUR50(TMCOUR50VO vo) throws Exception;
    
    /**
	 * tm_authxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR50VO
	 * @return 조회한 tm_authxm
	 * @exception Exception
	 */
    TMCOUR50VO selectTMCOUR50(TMCOUR50VO vo) throws Exception;
    
    /**
	 * tm_authxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_authxm 목록
	 * @exception Exception
	 */
    List selectTMCOUR50List(TMCOUR50DefaultVO searchVO) throws Exception;
    
    /**
	 * tm_authxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_authxm 총 갯수
	 * @exception
	 */
    int selectTMCOUR50ListTotCnt(TMCOUR50DefaultVO searchVO);
    
}
