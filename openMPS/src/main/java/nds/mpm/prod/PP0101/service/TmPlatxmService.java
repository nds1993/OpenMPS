package nds.mpm.prod.PP0101.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0101.vo.TmPlatxmDefaultVO;
import nds.mpm.prod.PP0101.vo.TmPlatxmVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TmPlatxmService.java
 * @Description : TmPlatxm Business class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TmPlatxmService {
	
	/**
	 * tm_platxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TmPlatxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertTmPlatxm(List<EgovMap> vo) throws Exception;
    
    /**
	 * tm_platxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TmPlatxmVO
	 * @return void형
	 * @exception Exception
	 */
    void updateTmPlatxm(EgovMap vo) throws Exception;
    
    /**
	 * tm_platxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TmPlatxmVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTmPlatxm(EgovMap vo) throws Exception;
    
    /**
	 * tm_platxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TmPlatxmVO
	 * @return 조회한 tm_platxm
	 * @exception Exception
	 */
    TmPlatxmVO selectTmPlatxm(EgovMap vo) throws Exception;
    
    /**
	 * tm_platxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 목록
	 * @exception Exception
	 */
    List selectTmPlatxmList(TmPlatxmDefaultVO searchVO) throws Exception;
    
    /**
	 * tm_platxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 총 갯수
	 * @exception
	 */
    int selectTmPlatxmListTotCnt(TmPlatxmDefaultVO searchVO);
    
    public int checkDupTmPlatxm_S(TmPlatxmDefaultVO searchVO) ;
    
    public List selectGrupTmPlatxmList(TmPlatxmDefaultVO searchVO) throws Exception ;


}
