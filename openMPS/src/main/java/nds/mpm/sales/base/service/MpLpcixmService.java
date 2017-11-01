package nds.mpm.sales.base.service;

import java.util.List;
import nds.mpm.sales.base.vo.MpLpcixmDefaultVO;
import nds.mpm.sales.base.vo.MpLpcixmVO;

/**
 * @Class Name : MpLpcixmService.java
 * @Description : MpLpcixm Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.27
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpLpcixmService {
	
	/**
	 * MP_LPCIXM을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpLpcixmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpLpcixm(MpLpcixmVO vo) throws Exception;
    
    /**
	 * MP_LPCIXM을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpLpcixmVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpLpcixm(MpLpcixmVO vo) throws Exception;
    
    /**
	 * MP_LPCIXM을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpLpcixmVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpLpcixm(MpLpcixmVO vo) throws Exception;
    
    /**
	 * MP_LPCIXM을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpLpcixmVO
	 * @return 조회한 MP_LPCIXM
	 * @exception Exception
	 */
    MpLpcixmVO selectMpLpcixm(MpLpcixmVO vo) throws Exception;
    
    /**
	 * MP_LPCIXM 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_LPCIXM 목록
	 * @exception Exception
	 */
    List selectMpLpcixmList(MpLpcixmDefaultVO searchVO) throws Exception;
    
    /**
	 * MP_LPCIXM 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_LPCIXM 총 갯수
	 * @exception
	 */
    int selectMpLpcixmListTotCnt(MpLpcixmDefaultVO searchVO);
    
}
