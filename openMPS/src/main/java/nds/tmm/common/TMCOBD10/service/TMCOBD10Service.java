package nds.tmm.common.TMCOBD10.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.common.vo.ResultEx;
import nds.tmm.common.TMCOBD10.vo.TMCOBD10VO;

/**
 * @Class Name : TMCOBD10Service.java
 * @Description : TMCOBD10 Business class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOBD10Service {
	
	/**
	 * tm_bdcaxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertTMCOBD10(List<EgovMap> vos) throws Exception;

    /**
	 * tm_bdcaxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOBD10VO
	 * @return void형
	 * @exception Exception
	 */
    void updateTMCOBD10(EgovMap vo) throws Exception;
    
    /**
	 * tm_bdcaxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOBD10VO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTMCOBD10(EgovMap vo) throws Exception;
   
    /**
	 * tm_bdcaxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOBD10VO
	 * @return 조회한 tm_bdcaxm
	 * @exception Exception
	 */
    TMCOBD10VO selectTMCOBD10(EgovMap vo) throws Exception;
    
    /**
	 * tm_bdcaxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_bdcaxm 목록
	 * @exception Exception
	 */
    List selectTMCOBD10List(TMCOBD10VO searchVO) throws Exception;
    
    /**
	 * tm_bdcaxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_bdcaxm 총 갯수
	 * @exception
	 */
    int selectTMCOBD10ListTotCnt(TMCOBD10VO searchVO);
    
}
