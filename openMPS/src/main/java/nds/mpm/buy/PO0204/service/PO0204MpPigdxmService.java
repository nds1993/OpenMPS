package nds.mpm.buy.PO0204.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.buy.PO0102.vo.MpPigdxmDefaultVO;
import nds.mpm.buy.PO0102.vo.MpPigdxmVO;
import nds.mpm.common.vo.ResultEx;

/**
 * @Class Name : MpPigdxmService.java
 * @Description : MpPigdxm Business class
 * @Modification Information
 *
 * @author 생돈지급율조회
 * @since 생돈지급율조회
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PO0204MpPigdxmService {
	
	/**
	 * mp_pigdxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigdxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpPigdxm(MpPigdxmVO vo) throws Exception;
    
    /**
	 * mp_pigdxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigdxmVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPigdxm(MpPigdxmVO vo) throws Exception;
    
    /**
	 * mp_pigdxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigdxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public ResultEx deleteMpPigdxm(List<EgovMap> vos) throws Exception ;
    
    /**
	 * mp_pigdxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigdxmVO
	 * @return 조회한 mp_pigdxm
	 * @exception Exception
	 */
    MpPigdxmVO selectMpPigdxm(MpPigdxmVO vo) throws Exception;
    
    /**
	 * mp_pigdxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 목록
	 * @exception Exception
	 */
    List selectMpPigdxmList(MpPigdxmDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_pigdxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 총 갯수
	 * @exception
	 */
    int selectMpPigdxmListTotCnt(MpPigdxmDefaultVO searchVO);
    
}
