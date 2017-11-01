package nds.mpm.buy.PO0301.service;

import java.util.List;
import nds.mpm.buy.PO0301.vo.MpPigxxhDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigxxhVO;

/**
 * @Class Name : MpPigxxhService.java
 * @Description : MpPigxxh Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PO0301MpPigxxhService {
	
	/**
	 * mp_pigxxh을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigxxhVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	MpPigxxhVO insertMpPigxxh(MpPigxxhVO vo) throws Exception;
    
    /**
	 * mp_pigxxh을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigxxhVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPigxxh(MpPigxxhVO vo) throws Exception;
    
    /**
	 * mp_pigxxh을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigxxhVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpPigxxh(MpPigxxhVO vo) throws Exception;
    
    /**
	 * mp_pigxxh을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigxxhVO
	 * @return 조회한 mp_pigxxh
	 * @exception Exception
	 */
    MpPigxxhVO selectMpPigxxh(MpPigxxhVO vo) throws Exception;
    
    /**
	 * mp_pigxxh 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigxxh 목록
	 * @exception Exception
	 */
    List selectMpPigxxhList(MpPigxxhDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_pigxxh 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigxxh 총 갯수
	 * @exception
	 */
    int selectMpPigxxhListTotCnt(MpPigxxhDefaultVO searchVO);
    
}
