package nds.mpm.buy.PO0101.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.buy.PO0101.vo.MpPigixmDefaultVO;
import nds.mpm.buy.PO0101.vo.MpPigixmVO;
import nds.mpm.common.vo.ResultEx;

/**
 * @Class Name : MpPigixmService.java
 * @Description : MpPigixm Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpPigixmService {
	
	/**
	 * mp_pigixm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigixmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpSkinPigixm(List<EgovMap> vos) throws Exception;
    public ResultEx insertMpNonSkinPigixm(List<EgovMap> vos) throws Exception;
    /**
	 * mp_pigixm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigixmVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPigixm(MpPigixmVO vo) throws Exception;
    
    /**
	 * mp_pigixm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigixmVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpPigixm(MpPigixmVO vo) throws Exception;
    
    /**
	 * mp_pigixm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigixmVO
	 * @return 조회한 mp_pigixm
	 * @exception Exception
	 */
    MpPigixmVO selectMpPigixm(MpPigixmVO vo) throws Exception;
    
    /**
	 * mp_pigixm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigixm 목록
	 * @exception Exception
	 */
    public List<EgovMap> selectMpPigixmSkinList(MpPigixmDefaultVO searchVO) throws Exception;
    
    /**
   	 * mp_pigixm 목록을 조회한다.
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return mp_pigixm 목록
   	 * @exception Exception
   	 */
    public List<EgovMap> selectMpPigixmNonSkinList(MpPigixmDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_pigixm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigixm 총 갯수
	 * @exception
	 */
    int selectMpPigixmListTotCnt(MpPigixmDefaultVO searchVO);
    
    public List<?> selectIfPiggxmList(MpPigixmDefaultVO searchVO) throws Exception;
    public List<?> selectIfSkinPiggxmList(MpPigixmDefaultVO searchVO) throws Exception;
    public List<?> selectIfNonSkinPiggxmList(MpPigixmDefaultVO searchVO) throws Exception ;
   

    /**
	 * if_piggxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigixm 총 갯수
	 * @exception
	 */
    public int selectIfPiggxmListTotCnt(MpPigixmDefaultVO searchVO);
}
