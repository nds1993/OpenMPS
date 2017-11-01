package nds.mpm.prod.PP0802.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0802.vo.MpPighisPackMDefaultVO;
import nds.mpm.prod.PP0802.vo.MpPighisPackMVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPighisPackMService.java
 * @Description : MpPighisPackM Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpPighisPackMService {
	
	/**
	 * mp_pighis_pack_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPighisPackMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPighisPackM(List<EgovMap> vos) throws Exception ;
    
    /**
	 * mp_pighis_pack_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPighisPackMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPighisPackM(MpPighisPackMVO vo) throws Exception;
    
    /**
	 * mp_pighis_pack_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPighisPackMVO
	 * @return void형 
	 * @exception Exception
	 */
    int deleteMpPighisPackM(MpPighisPackMVO vo) throws Exception;
    
    /**
	 * mp_pighis_pack_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPighisPackMVO
	 * @return 조회한 mp_pighis_pack_m
	 * @exception Exception
	 */
    MpPighisPackMVO selectMpPighisPackM(MpPighisPackMVO vo) throws Exception;
    
    /**
	 * mp_pighis_pack_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pighis_pack_m 목록
	 * @exception Exception
	 */
    List selectMpPighisPackMList(MpPighisPackMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_pighis_pack_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pighis_pack_m 총 갯수
	 * @exception
	 */
    int selectMpPighisPackMListTotCnt(MpPighisPackMDefaultVO searchVO);
    public List<EgovMap> selectMpPighisPackMSendFormatList(MpPighisPackMVO searchVO) throws Exception ;

    public int updateApiTimeMpPighisPackM(EgovMap vo) throws Exception ;
    
    public List selectMpBarProMList(MpPighisPackMDefaultVO searchVO) throws Exception ;
    public int selectMpBarProMListTotCnt(MpPighisPackMDefaultVO searchVO) ;
    
    public int checkDupPackDateMpPighisPackM(MpPighisPackMDefaultVO searchVO) ;


}
