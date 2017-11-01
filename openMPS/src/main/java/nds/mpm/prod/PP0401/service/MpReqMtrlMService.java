package nds.mpm.prod.PP0401.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0401.vo.MpReqMtrlMDefaultVO;
import nds.mpm.prod.PP0401.vo.MpReqMtrlMVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpReqMtrlMService.java
 * @Description : MpReqMtrlM Business class
 * @Modification Information
 *
 * @author 자재 소요량 산출
 * @since 자재 소요량 산출
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpReqMtrlMService {
	
	/**
	 * mp_req_mtrl_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpReqMtrlMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpReqMtrlM( MpReqMtrlMVO searchVO, List<EgovMap> vos, boolean isDelete) throws Exception ;
    
    /**
	 * mp_req_mtrl_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpReqMtrlMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpReqMtrlM(MpReqMtrlMVO vo) throws Exception;
    
    /**
	 * mp_req_mtrl_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpReqMtrlMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpReqMtrlM(MpReqMtrlMVO vo) throws Exception;
    
    /**
	 * mp_req_mtrl_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpReqMtrlMVO
	 * @return 조회한 mp_req_mtrl_m
	 * @exception Exception
	 */
    MpReqMtrlMVO selectMpReqMtrlM(MpReqMtrlMVO vo) throws Exception;
    
    /**
	 * mp_req_mtrl_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_mtrl_m 목록
	 * @exception Exception
	 */
    List selectMpReqMtrlMList(MpReqMtrlMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_req_mtrl_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_mtrl_m 총 갯수
	 * @exception
	 */
    int selectMpReqMtrlMListTotCnt(MpReqMtrlMDefaultVO searchVO);
    
    public int checkMpReqMtrlMCnt(MpReqMtrlMDefaultVO searchVO) ;
    
    public int deleteCalcWorkDateMpReqMtrlM(MpReqMtrlMDefaultVO searchVO) ;
    
    public List<EgovMap> selectCalculateMpReqMtrlMList(MpReqMtrlMDefaultVO searchVO) throws Exception ;
    
}
