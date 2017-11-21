package nds.tmm.common.TMCOCD10.service;

import java.util.List;

import nds.tmm.common.TMCOCD10.vo.TMCOCD10DefaultVO;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10VO;

/**
 * @Class Name : TMCOCD10Service.java
 * @Description : TMCOCD10 Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOCD10Service {
	
	/**
	 * BASE_INFO을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOCD10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertTMCOCD10(TMCOCD10VO vo) throws Exception;
    
    int insertTMCOCD10(List<TMCOCD10VO> vos) throws Exception;
    
    /**
	 * BASE_INFO을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOCD10VO
	 * @return void형
	 * @exception Exception
	 */
    int updateTMCOCD10(List<Object> vos) throws Exception;
    
    /**
	 * BASE_INFO을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOCD10VO
	 * @return void형 
	 * @exception Exception
	 */
    int deleteTMCOCD10(List<Object> vos) throws Exception;
    
    /**
	 * BASE_INFO을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOCD10VO
	 * @return 조회한 BASE_INFO
	 * @exception Exception
	 */
    TMCOCD10VO selectTMCOCD10(Object vo) throws Exception;
    
    /**
	 * BASE_INFO 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return BASE_INFO 목록
	 * @exception Exception
	 */
    List selectTMCOCD10List(TMCOCD10DefaultVO searchVO) throws Exception;
    
    /**
	 * BASE_INFO 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return BASE_INFO 총 갯수
	 * @exception
	 */
    int selectTMCOCD10ListTotCnt(TMCOCD10DefaultVO searchVO);
    
}
