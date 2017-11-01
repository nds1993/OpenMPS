package nds.tmm.common.TMCOUR60.service;

import java.util.List;

import nds.tmm.common.TMCOUR60.vo.TMCOUR60DefaultVO;
import nds.tmm.common.TMCOUR60.vo.TMCOUR60VO;

/**
 * @Class Name : TMCOUR60Service.java
 * @Description : TMCOUR60 Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOUR60Service {
	
	/**
	 * tm_grusrx을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR60VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    int insertTMCOUR60(List<TMCOUR60VO> vo) throws Exception;
    
    /**
	 * tm_grusrx을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR60VO
	 * @return void형
	 * @exception Exception
	 */
    int updateTMCOUR60(List<Object> vo) throws Exception;
    
    /**
	 * tm_grusrx을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR60VO
	 * @return void형 
	 * @exception Exception
	 */
    int deleteTMCOUR60(List<Object> vo) throws Exception;
    
    /**
	 * tm_grusrx을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR60VO
	 * @return 조회한 tm_grusrx
	 * @exception Exception
	 */
    TMCOUR60VO selectTMCOUR60(TMCOUR60VO vo) throws Exception;
    
    /**
	 * tm_grusrx 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_grusrx 목록
	 * @exception Exception
	 */
    List selectTMCOUR60List(TMCOUR60DefaultVO searchVO) throws Exception;
    
    /**
	 * tm_grusrx 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_grusrx 총 갯수
	 * @exception
	 */
    int selectTMCOUR60ListTotCnt(TMCOUR60DefaultVO searchVO);
    
    public List<?> selectTMCOUR60UserList_D(TMCOUR60DefaultVO searchVO) throws Exception;
    
}
