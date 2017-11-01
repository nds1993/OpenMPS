package nds.tmm.common.TMCOBD20.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.common.vo.ResultEx;
import nds.tmm.common.TMCOBD20.vo.TMCOBD20VO;
import nds.tmm.common.TMCOBD20.vo.TMCOBD20VO;

/**
 * @Class Name : TMCOBD20Service.java
 * @Description : TMCOBD20 Business class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOBD20Service {
	
	/**
	 * tm_bdctxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD20VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertTMCOBD20(TMCOBD20VO vo) throws Exception;

    /**
	 * tm_bdctxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOBD20VO
	 * @return void형
	 * @exception Exception
	 */
    void updateTMCOBD20(TMCOBD20VO vo) throws Exception;
    
    /**
     * tm_bdctxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    void updateReplySortOrderTMCOBD20(TMCOBD20VO vo) throws Exception;
    
    /**
	 * tm_bdctxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOBD20VO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTMCOBD20(TMCOBD20VO vo) throws Exception;
   
    /**
	 * tm_bdctxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOBD20VO
	 * @return 조회한 tm_bdctxm
	 * @exception Exception
	 */
    TMCOBD20VO selectTMCOBD20(EgovMap vo) throws Exception;
    
    /**
	 * tm_bdctxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_bdctxm 목록
	 * @exception Exception
	 */
    List selectTMCOBD20List(TMCOBD20VO searchVO) throws Exception;
    
    /**
	 * tm_bdctxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_bdctxm 총 갯수
	 * @exception
	 */
    int selectTMCOBD20ListTotCnt(TMCOBD20VO searchVO);
    
    /**
     * tm_bdctxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    void updateReadCntTMCOBD20(TMCOBD20VO vo) throws Exception;
    
    /**
	 * tm_bdredh을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD20VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertBdredhTMCOBD20(TMCOBD20VO vo) throws Exception;
	
	/**
	 * tm_bdredh을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOBD20VO
	 * @return 조회한 tm_bdredh
	 * @exception Exception
	 */
    TMCOBD20VO selectBdredhTMCOBD20(TMCOBD20VO vo) throws Exception;
    
    
    /**
	 * tm_bdcmxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD20VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertComment(TMCOBD20VO vo) throws Exception;    
    
	 /**
	 * tm_bdcmxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_bdctxm 목록
	 * @exception Exception
	 */
    List selectComment(TMCOBD20VO searchVO) throws Exception;
    
    /**
     * tm_bdcmxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    void updateComment(TMCOBD20VO vo) throws Exception;    
    
    /**
     * tm_bdcmxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    void deleteComment(TMCOBD20VO vo) throws Exception;    
    
    /**
     * tm_atflxm 목록을 조회한다.
     * @param searchMap - 조회할 정보가 담긴 Map
     * @return tm_atflxm 목록
     * @exception Exception
     */
    String selectNextFileId(TMCOBD20VO vo) throws Exception;
    
    /**
     * tm_atflxm을 등록한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void insertFileMaster(TMCOBD20VO vo) throws Exception;
    
    /**
     * tm_atflxd을 등록한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void insertFileDetail(TMCOBD20VO vo) throws Exception;
    
    /**
     * tm_atflxd을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void deleteFile(TMCOBD20VO vo) throws Exception;
    
    /**
     * tm_atflxd 목록을 조회한다.
     * @param searchMap - 조회할 정보가 담긴 Map
     * @return tm_atflxd 목록
     * @exception Exception
     */
    public List<?> selectFile(TMCOBD20VO searchVO) throws Exception;    
    
}
