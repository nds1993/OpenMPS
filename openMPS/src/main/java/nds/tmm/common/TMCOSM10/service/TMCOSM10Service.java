package nds.tmm.common.TMCOSM10.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.tmm.common.TMCOSM10.vo.TMCOSM10VO;

/**
 * @Class Name : TMCOSM10Service.java
 * @Description : TMCOSM10 Business class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOSM10Service {
	
	/**
	 * tm_svrqxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOSM10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertTMCOSM10(TMCOSM10VO vo) throws Exception;
	
	/**
	 * tm_svpcxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOSM10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertTmSvpcmx(TMCOSM10VO vo) throws Exception;
    
    /**
	 * tm_svrqxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOSM10VO
	 * @return void형
	 * @exception Exception
	 */
    void updateTMCOSM10(TMCOSM10VO vo) throws Exception;
    
    /**
	 * tm_svrqxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOSM10VO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTMCOSM10(TMCOSM10VO vo) throws Exception;
    
    /**
     * tm_svrqxm을 변경한다.
     * @param vo - 변경할 정보가 담긴 TMCOSM10VO
     * @return void형 
     * @exception Exception
     */
    void updateProcStatusTMCOSM10(TMCOSM10VO vo) throws Exception;
    
    /**
	 * tm_svrqxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOSM10VO
	 * @return 조회한 tm_svrqxm
	 * @exception Exception
	 */
    TMCOSM10VO selectTMCOSM10(TMCOSM10VO vo) throws Exception;
    
    /**
	 * tm_svrqxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_svrqxm 목록
	 * @exception Exception
	 */
    List selectTMCOSM10List(TMCOSM10VO searchVO) throws Exception;
    
    /**
	 * tm_svrqxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_svrqxm 총 갯수
	 * @exception
	 */
    int selectTMCOSM10ListTotCnt(TMCOSM10VO searchVO);
    
}
