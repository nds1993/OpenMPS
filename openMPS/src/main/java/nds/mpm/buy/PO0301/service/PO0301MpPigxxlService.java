package nds.mpm.buy.PO0301.service;

import java.util.List;

import nds.mpm.buy.PO0301.vo.MpPigxxlDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigxxlVO;
import nds.mpm.common.vo.ResultEx;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPigxxlService.java
 * @Description : MpPigxxl Business class
 * @Modification Information
 *
 * @author ㅌ
 * @since ㅌ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PO0301MpPigxxlService {
	
	/**
	 * mp_pigxxl을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigxxlVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPigxxl(List<EgovMap> vos) throws Exception ;
    
    /**
	 * mp_pigxxl을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigxxlVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPigxxl(EgovMap vo) throws Exception;
    
    /**
	 * mp_pigxxl을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigxxlVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpPigxxl(EgovMap vo) throws Exception;
    
    /**
	 * mp_pigxxl을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigxxlVO
	 * @return 조회한 mp_pigxxl
	 * @exception Exception
	 */
    MpPigxxlVO selectMpPigxxl(MpPigxxlVO vo) throws Exception;
    
    /**
	 * mp_pigxxl 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigxxl 목록
	 * @exception Exception
	 */
    List selectMpPigxxlList(MpPigxxlDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_pigxxl 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigxxl 총 갯수
	 * @exception
	 */
    int selectMpPigxxlListTotCnt(MpPigxxlDefaultVO searchVO);
    
}
