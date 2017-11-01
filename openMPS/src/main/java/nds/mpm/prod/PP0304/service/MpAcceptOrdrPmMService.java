package nds.mpm.prod.PP0304.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0304.vo.MpAcceptOrdrPmMDefaultVO;
import nds.mpm.prod.PP0304.vo.MpAcceptOrdrPmMVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpAcceptOrdrPmMService.java
 * @Description : MpAcceptOrdrPmM Business class
 * @Modification Information
 *
 * @author 생산계획입력(PM)
 * @since 생산계획입력(PM)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpAcceptOrdrPmMService {
	
	/**
	 * mp_accept_ordr_pm_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpAcceptOrdrPmMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpAcceptOrdrPmM(List<EgovMap> vo) throws Exception;
    public ResultEx updateMpCancel(List<EgovMap> vo) throws Exception ;
    
    /**
	 * mp_accept_ordr_pm_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpAcceptOrdrPmMVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpAcceptOrdrPmM(EgovMap vo) throws Exception ;
    
    /**
	 * mp_accept_ordr_pm_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpAcceptOrdrPmMVO
	 * @return 조회한 mp_accept_ordr_pm_m
	 * @exception Exception
	 */
    MpAcceptOrdrPmMVO selectMpAcceptOrdrPmM(EgovMap vo) throws Exception;
    
    /**
     * 미접수분 조회 mp_order_d
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @exception Exception
	 */
    public List selectMpOrderDList(MpAcceptOrdrPmMDefaultVO searchVO) throws Exception ;

    /**
	 * 미접수분 조회 mp_order_d
	 * @exception
	 */
    public int selectMpOrderDListTotCnt(MpAcceptOrdrPmMDefaultVO searchVO) ;
    /**
	 * mp_accept_ordr_pm_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_accept_ordr_pm_m 목록
	 * @exception Exception
	 */
    List selectMpAcceptOrdrPmMList(MpAcceptOrdrPmMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_accept_ordr_pm_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_accept_ordr_pm_m 총 갯수
	 * @exception
	 */
    int selectMpAcceptOrdrPmMListTotCnt(MpAcceptOrdrPmMDefaultVO searchVO);
    
}
