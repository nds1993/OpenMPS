package nds.mpm.prod.base.service;

import java.util.List;
import nds.mpm.prod.base.vo.MpIbarxmDefaultVO;
import nds.mpm.prod.base.vo.MpIbarxmVO;

/**
 * @Class Name : MpIbarxmService.java
 * @Description : MpIbarxm Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpIbarxmService {
	
	/**
	 * MP_IBARXM을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpIbarxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpIbarxm(MpIbarxmVO vo) throws Exception;
    
    /**
	 * MP_IBARXM을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpIbarxmVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpIbarxm(MpIbarxmVO vo) throws Exception;
    
    /**
	 * MP_IBARXM을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpIbarxmVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpIbarxm(MpIbarxmVO vo) throws Exception;
    
    /**
	 * MP_IBARXM을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpIbarxmVO
	 * @return 조회한 MP_IBARXM
	 * @exception Exception
	 */
    MpIbarxmVO selectMpIbarxm(MpIbarxmVO vo) throws Exception;
    
    /**
	 * MP_IBARXM 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_IBARXM 목록
	 * @exception Exception
	 */
    List selectMpIbarxmList(MpIbarxmDefaultVO searchVO) throws Exception;
    
    /**
	 * MP_IBARXM 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_IBARXM 총 갯수
	 * @exception
	 */
    int selectMpIbarxmListTotCnt(MpIbarxmDefaultVO searchVO);
    
}
