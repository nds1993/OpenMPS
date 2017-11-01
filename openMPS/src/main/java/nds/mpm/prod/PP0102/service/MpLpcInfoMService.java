package nds.mpm.prod.PP0102.service;

import java.util.List;
import nds.mpm.prod.PP0102.vo.MpLpcInfoMDefaultVO;
import nds.mpm.prod.PP0102.vo.MpLpcInfoMVO;

/**
 * @Class Name : MpLpcInfoMService.java
 * @Description : MpLpcInfoM Business class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpLpcInfoMService {
	
	/**
	 * mp_lpc_info_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpLpcInfoMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    int insertMpLpcInfoM(List<MpLpcInfoMVO> vos) throws Exception;
    
    /**
	 * mp_lpc_info_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpLpcInfoMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpLpcInfoM(MpLpcInfoMVO vo) throws Exception;
    
    /**
	 * mp_lpc_info_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpLpcInfoMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpLpcInfoM(MpLpcInfoMVO vo) throws Exception;
    
    /**
	 * mp_lpc_info_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpLpcInfoMVO
	 * @return 조회한 mp_lpc_info_m
	 * @exception Exception
	 */
    MpLpcInfoMVO selectMpLpcInfoM(MpLpcInfoMVO vo) throws Exception;
    
    /**
	 * mp_lpc_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_lpc_info_m 목록
	 * @exception Exception
	 */
    List selectMpLpcInfoMList(MpLpcInfoMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_lpc_info_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_lpc_info_m 총 갯수
	 * @exception
	 */
    int selectMpLpcInfoMListTotCnt(MpLpcInfoMDefaultVO searchVO);
    
}
