package nds.mpm.buy.PO0202.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.buy.PO0202.vo.MpPigexmDefaultVO;
import nds.mpm.buy.PO0202.vo.MpPigexmVO;
import nds.mpm.common.vo.ResultEx;

/**
 * @Class Name : MpPigexmService.java
 * @Description : MpPigexm Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpPigexmService {
	
	/**
	 * mp_pigexm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigexmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPigexm(List<EgovMap> vos) throws Exception ;
    
    /**
	 * mp_pigexm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigexmVO
	 * @return void형
	 * @exception Exception
	 */
    int updateMpPigexm(EgovMap vo) throws Exception;
    
    /**
	 * mp_pigexm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigexmVO
	 * @return void형 
	 * @exception Exception
	 */
    int deleteMpPigexm(EgovMap vo) throws Exception;
    
    /**
	 * mp_pigexm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigexmVO
	 * @return 조회한 mp_pigexm
	 * @exception Exception
	 */
    MpPigexmVO selectMpPigexm(EgovMap vo) throws Exception;
    
    /**
	 * mp_pigexm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigexm 목록
	 * @exception Exception
	 */
    List selectMpPigexmList(MpPigexmDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_pigexm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigexm 총 갯수
	 * @exception
	 */
    int selectMpPigexmListTotCnt(MpPigexmDefaultVO searchVO);
    
    public List<?> selectMpPigexmCodeList(MpPigexmVO searchVO) throws Exception;
    
    public List selectMpPigexmRecentList(MpPigexmVO vo) throws Exception;
    
    public List<?> selectMpPigexmFacCodeList(MpPigexmVO searchVO) throws Exception ;
    
    public ResultEx selectMpPigexmJiyukAvg(MpPigexmVO searchVO) ;

    public ResultEx updateAdjustFnPO0202Call(MpPigexmVO searchVO) throws Exception;

    public EgovMap selectJiyukPeriod(MpPigexmDefaultVO searchVO) ;

}
