package nds.tmm.common.TMCOOS70.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.tmm.common.TMCOOS70.vo.TMCOOS70DefaultVO;
import nds.tmm.common.TMCOOS70.vo.TMCOOS70VO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOOS70Service.java
 * @Description : TMCOOS70 Business class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOOS70Service {
	
	/**
	 * tm_deptxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOOS70VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertTMCOOS70(List<EgovMap> vos) throws Exception;
    
    /**
	 * tm_deptxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOOS70VO
	 * @return void형
	 * @exception Exception
	 */
    void updateTMCOOS70(EgovMap vo) throws Exception;
    
    /**
	 * tm_deptxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOOS70VO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTMCOOS70(EgovMap vo) throws Exception;
    
    /**
	 * tm_deptxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOOS70VO
	 * @return 조회한 tm_deptxm
	 * @exception Exception
	 */
    TMCOOS70VO selectTMCOOS70(EgovMap vo) throws Exception;
    
    /**
	 * tm_deptxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_deptxm 목록
	 * @exception Exception
	 */
    List selectTMCOOS70List(TMCOOS70DefaultVO searchVO) throws Exception;
    
    /**
	 * tm_deptxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_deptxm 총 갯수
	 * @exception
	 */
    int selectTMCOOS70ListTotCnt(TMCOOS70DefaultVO searchVO);
    
}
