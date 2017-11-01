package nds.tmm.common.TMCOOS50.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0101.vo.TmPlatxmDefaultVO;
import nds.tmm.common.TMCOOS50.vo.TMCOOS50DefaultVO;
import nds.tmm.common.TMCOOS50.vo.TMCOOS50VO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOOS50Service.java
 * @Description : TMCOOS50 Business class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.06
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOOS50Service {
	
	/**
	 * tm_orguxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOOS50VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertTMCOOS50(List<EgovMap> vos) throws Exception;
    
    /**
	 * tm_orguxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOOS50VO
	 * @return void형
	 * @exception Exception
	 */
    void updateTMCOOS50(EgovMap vo) throws Exception;
    
    /**
	 * tm_orguxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOOS50VO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTMCOOS50(EgovMap vo) throws Exception;
    
    /**
	 * tm_orguxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOOS50VO
	 * @return 조회한 tm_orguxm
	 * @exception Exception
	 */
    TMCOOS50VO selectTMCOOS50(EgovMap vo) throws Exception;
    
    /**
	 * tm_orguxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_orguxm 목록
	 * @exception Exception
	 */
    List selectTMCOOS50List(TMCOOS50DefaultVO searchVO) throws Exception;
    
    /**
	 * tm_orguxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_orguxm 총 갯수
	 * @exception
	 */
    int selectTMCOOS50ListTotCnt(TMCOOS50DefaultVO searchVO);
    
    public int checkDupTMCOOS50_S(TMCOOS50DefaultVO searchVO) ;
}
