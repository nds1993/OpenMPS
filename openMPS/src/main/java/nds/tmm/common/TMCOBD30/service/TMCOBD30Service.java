package nds.tmm.common.TMCOBD30.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.tmm.common.TMCOBD30.vo.TMCOBD30VO;

/**
 * @Class Name : TMCOBD30Service.java
 * @Description : TMCOBD30 Business class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOBD30Service {
	
	/**
	 * tm_faqxxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD30VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertTMCOBD30(TMCOBD30VO vo) throws Exception;

    /**
	 * tm_faqxxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOBD30VO
	 * @return void형
	 * @exception Exception
	 */
    void updateTMCOBD30(TMCOBD30VO vo) throws Exception;
    
    /**
     * tm_faqxxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD30VO
     * @return void형
     * @exception Exception
     */
    void updateReadCntTMCOBD30(TMCOBD30VO vo) throws Exception;
    
    /**
	 * tm_faqxxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOBD30VO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTMCOBD30(TMCOBD30VO vo) throws Exception;
   
    /**
	 * tm_faqxxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOBD30VO
	 * @return 조회한 tm_faqxxm
	 * @exception Exception
	 */
    TMCOBD30VO selectTMCOBD30(TMCOBD30VO vo) throws Exception;
    
    /**
	 * tm_bdredh을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD30VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertBdredhTMCOBD30(TMCOBD30VO vo) throws Exception;
	
	/**
	 * tm_bdredh을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOBD30VO
	 * @return 조회한 tm_bdredh
	 * @exception Exception
	 */
    TMCOBD30VO selectBdredhTMCOBD30(TMCOBD30VO vo) throws Exception;
    
    /**
	 * tm_faqxxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_faqxxm 목록
	 * @exception Exception
	 */
    List selectTMCOBD30List(TMCOBD30VO searchVO) throws Exception;
    
    /**
	 * tm_faqxxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_faqxxm 총 갯수
	 * @exception
	 */
    int selectTMCOBD30ListTotCnt(TMCOBD30VO searchVO);
    
}
