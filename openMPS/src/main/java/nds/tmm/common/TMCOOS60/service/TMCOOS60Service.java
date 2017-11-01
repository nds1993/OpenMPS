package nds.tmm.common.TMCOOS60.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.tmm.common.TMCOOS60.vo.TMCOOS60DefaultVO;
import nds.tmm.common.TMCOOS60.vo.TMCOOS60VO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOOS60Service.java
 * @Description : TMCOOS60 Business class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.06
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOOS60Service {
	
	/**
	 * tm_teamxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOOS60VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertTMCOOS60(List<EgovMap> vos) throws Exception;
    
    /**
	 * tm_teamxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOOS60VO
	 * @return void형
	 * @exception Exception
	 */
    void updateTMCOOS60(EgovMap vo) throws Exception;
    
    /**
	 * tm_teamxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOOS60VO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTMCOOS60(EgovMap vo) throws Exception;
    
    /**
	 * tm_teamxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOOS60VO
	 * @return 조회한 tm_teamxm
	 * @exception Exception
	 */
    TMCOOS60VO selectTMCOOS60(EgovMap vo) throws Exception;
    
    /**
	 * tm_teamxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_teamxm 목록
	 * @exception Exception
	 */
    List selectTMCOOS60List(TMCOOS60DefaultVO searchVO) throws Exception;
    
    /**
	 * tm_teamxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_teamxm 총 갯수
	 * @exception
	 */
    int selectTMCOOS60ListTotCnt(TMCOOS60DefaultVO searchVO);
    
}
