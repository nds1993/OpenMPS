package nds.mpm.prod.PP0901.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0901.vo.MpReqOutMDefaultVO;
import nds.mpm.prod.PP0901.vo.MpReqOutMVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpReqOutMService.java
 * @Description : MpReqOutM Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpReqOutMService {
	
	/**
	 * mp_req_out_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpReqOutMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpReqOutM(EgovMap vo) throws Exception;
    
    /**
	 * mp_req_out_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpReqOutMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpReqOutM(MpReqOutMVO vo) throws Exception;
    
    /**
	 * mp_req_out_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpReqOutMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpReqOutM(MpReqOutMVO vo) throws Exception;
    
    /**
	 * mp_req_out_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpReqOutMVO
	 * @return 조회한 mp_req_out_m
	 * @exception Exception
	 */
    MpReqOutMVO selectMpReqOutM(MpReqOutMVO vo) throws Exception;
    
    /**
	 * mp_req_out_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_out_m 목록
	 * @exception Exception
	 */
    List selectMpReqOutMList(MpReqOutMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_req_out_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_out_m 총 갯수
	 * @exception
	 */
    int selectMpReqOutMListTotCnt(MpReqOutMDefaultVO searchVO);
    
    public ResultEx insertBatchJobSample() throws Exception ;
    
    public ResultEx insertBatchJobMpReqOutM(MpReqOutMVO vo) throws Exception ;
    
    public List selectBomCurDayWorkList(MpReqOutMDefaultVO searchVO) throws Exception ;
    

}
