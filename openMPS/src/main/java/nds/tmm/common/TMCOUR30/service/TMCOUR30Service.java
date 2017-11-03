package nds.tmm.common.TMCOUR30.service;

import java.util.List;

import nds.tmm.common.TMCOUR30.vo.TMCOUR30DefaultVO;
import nds.tmm.common.TMCOUR30.vo.TMCOUR30VO;

/**
 * @Class Name : TMCOUR30Service.java
 * @Description : TMCOUR30 Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOUR30Service {
	
	/**
	 * tm_menuxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR30VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    int insertTMCOUR30(List<TMCOUR30VO> vos) throws Exception;
    
    /**
	 * tm_menuxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR30VO
	 * @return void형
	 * @exception Exception
	 */
    int updateTMCOUR30(List<Object> vos) throws Exception;
    
    /**
	 * tm_menuxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR30VO
	 * @return void형 
	 * @exception Exception
	 */
    int deleteTMCOUR30(List<Object> vos) throws Exception;
    
    /**
	 * tm_menuxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR30VO
	 * @return 조회한 tm_menuxm
	 * @exception Exception
	 */
    TMCOUR30VO selectTMCOUR30(Object vo) throws Exception;
    
    /**
	 * tm_menuxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_menuxm 목록
	 * @exception Exception
	 */
    List selectTMCOUR30List(TMCOUR30DefaultVO searchVO) throws Exception;
    
    /**
	 * tm_menuxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_menuxm 총 갯수
	 * @exception
	 */
    int selectTMCOUR30ListTotCnt(TMCOUR30DefaultVO searchVO);
    
    public List<?> selectTMCOUR30TreeList(TMCOUR30DefaultVO searchVO) throws Exception;
}
