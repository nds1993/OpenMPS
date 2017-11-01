package nds.mpm.buy.PO0301.service;

import java.util.List;

import nds.mpm.buy.PO0301.vo.MpPigxxdDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigxxdVO;
import nds.mpm.common.vo.ResultEx;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPigxxdService.java
 * @Description : MpPigxxd Business class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PO0301MpPigxxdService {
	
	/**
	 * mp_pigxxd을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigxxdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPigxxd(List<EgovMap> vo) throws Exception ;
    
    /**
	 * mp_pigxxd을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigxxdVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPigxxd(MpPigxxdVO vo) throws Exception;
    
    /**
	 * mp_pigxxd을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigxxdVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpPigxxd(MpPigxxdVO vo) throws Exception;
    
    /**
	 * mp_pigxxd을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigxxdVO
	 * @return 조회한 mp_pigxxd
	 * @exception Exception
	 */
    MpPigxxdVO selectMpPigxxd(MpPigxxdVO vo) throws Exception;
    
    /**
	 * mp_pigxxd 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigxxd 목록
	 * @exception Exception
	 */
    List selectMpPigxxdList(MpPigxxdDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_pigxxd 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigxxd 총 갯수
	 * @exception
	 */
    int selectMpPigxxdListTotCnt(MpPigxxdDefaultVO searchVO);
    
}
