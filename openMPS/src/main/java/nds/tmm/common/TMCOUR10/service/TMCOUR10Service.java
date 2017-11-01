package nds.tmm.common.TMCOUR10.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.tmm.common.TMCOUR10.vo.TMCOUR10DefaultVO;
import nds.tmm.common.TMCOUR10.vo.TMCOUR10VO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOUR10Service.java
 * @Description : TMCOUR10 Business class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOUR10Service {
	
	/**
	 * tm_userxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertTMCOUR10(List<EgovMap> vo) throws Exception;
    
    /**
	 * tm_userxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR10VO
	 * @return void형
	 * @exception Exception
	 */
    void updateTMCOUR10(EgovMap vo) throws Exception;
    
    /**
	 * tm_userxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR10VO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTMCOUR10(EgovMap vo) throws Exception;
    
    /**
	 * tm_userxm 비밀번호를 변경한다.
	 * @param vo - 변경할 정보가 담긴 TMCOUR10VO
	 * @return void형 
	 * @exception Exception
	 */
    void updateUserPass(TMCOUR10VO vo) throws Exception;
    
    /**
	 * tm_userxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR10VO
	 * @return 조회한 tm_userxm
	 * @exception Exception
	 */
    TMCOUR10VO selectTMCOUR10(EgovMap vo) throws Exception;
    
    /**
	 * tm_userxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_userxm 목록
	 * @exception Exception
	 */
    List selectTMCOUR10List(TMCOUR10DefaultVO searchVO) throws Exception;
    
    /**
	 * tm_userxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_userxm 총 갯수
	 * @exception
	 */
    int selectTMCOUR10ListTotCnt(TMCOUR10DefaultVO searchVO);
    
}
