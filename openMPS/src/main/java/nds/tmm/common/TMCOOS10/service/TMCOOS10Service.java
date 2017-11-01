package nds.tmm.common.TMCOOS10.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.tmm.common.TMCOOS10.vo.TMCOOS10DefaultVO;
import nds.tmm.common.TMCOOS10.vo.TMCOOS10VO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOOS10Service.java
 * @Description : TMCOOS10 Business class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOOS10Service {
	
	/**
	 * tm_corpxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 EgovMap
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertTMCOOS10(List<EgovMap> vo) throws Exception;
    
    /**
	 * tm_corpxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 EgovMap
	 * @return void형
	 * @exception Exception
	 */
    void updateTMCOOS10(EgovMap vo) throws Exception;
    
    /**
	 * tm_corpxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 EgovMap
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTMCOOS10(EgovMap vo) throws Exception;
    
    /**
	 * tm_corpxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 EgovMap
	 * @return 조회한 tm_corpxm
	 * @exception Exception
	 */
    TMCOOS10VO selectTMCOOS10(EgovMap vo) throws Exception;
    
    /**
	 * tm_corpxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_corpxm 목록
	 * @exception Exception
	 */
    List selectTMCOOS10List(TMCOOS10DefaultVO searchVO) throws Exception;
    
    /**
	 * tm_corpxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_corpxm 총 갯수
	 * @exception
	 */
    int selectTMCOOS10ListTotCnt(TMCOOS10DefaultVO searchVO);
    
}
