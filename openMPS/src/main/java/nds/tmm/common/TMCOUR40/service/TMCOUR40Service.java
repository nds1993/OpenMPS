package nds.tmm.common.TMCOUR40.service;

import java.util.List;
import nds.tmm.common.TMCOUR40.vo.TMCOUR40DefaultVO;
import nds.tmm.common.TMCOUR40.vo.TMCOUR40VO;

/**
 * @Class Name : TMCOUR40Service.java
 * @Description : TMCOUR40 Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOUR40Service {
	
	/**
	 * tm_grupxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR40VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    int insertTMCOUR40(List<TMCOUR40VO> vos) throws Exception;
    
    /**
	 * tm_grupxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR40VO
	 * @return void형
	 * @exception Exception
	 */
    int updateTMCOUR40(List<Object> vos) throws Exception;
    
    /**
	 * tm_grupxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR40VO
	 * @return void형 
	 * @exception Exception
	 */
    int deleteTMCOUR40(List<Object> vos) throws Exception;
    
    /**
	 * tm_grupxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR40VO
	 * @return 조회한 tm_grupxm
	 * @exception Exception
	 */
    TMCOUR40VO selectTMCOUR40(TMCOUR40VO vo) throws Exception;
    
    /**
	 * tm_grupxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_grupxm 목록
	 * @exception Exception
	 */
    List selectTMCOUR40List(TMCOUR40DefaultVO searchVO) throws Exception;
    
    /**
	 * tm_grupxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_grupxm 총 갯수
	 * @exception
	 */
    int selectTMCOUR40ListTotCnt(TMCOUR40DefaultVO searchVO);
    
}
