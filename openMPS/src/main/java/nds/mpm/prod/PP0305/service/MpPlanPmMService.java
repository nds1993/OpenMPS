package nds.mpm.prod.PP0305.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0305.vo.MpPlanPmMDefaultVO;
import nds.mpm.prod.PP0305.vo.MpPlanPmMVO;

/**
 * @Class Name : MpPlanPmMService.java
 * @Description : MpPlanPmM Business class
 * @Modification Information
 *
 * @author 생산의뢰 접수 (PM주문)
 * @since 생산의뢰 접수 (PM주문)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpPlanPmMService {
	
	/**
	 * mp_plan_pm_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanPmMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPlanPmM(List<EgovMap> vos, String status) throws Exception ;
    
    /**
	 * mp_plan_pm_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanPmMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPlanPmM(MpPlanPmMVO vo) throws Exception;
    
    public ResultEx updateMpPlanPmMConfirm(List<EgovMap> vos) throws Exception ;

    /**
	 * mp_plan_pm_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanPmMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpPlanPmM(MpPlanPmMVO vo) throws Exception;
    
    /**
	 * mp_plan_pm_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanPmMVO
	 * @return 조회한 mp_plan_pm_m
	 * @exception Exception
	 */
    MpPlanPmMVO selectMpPlanPmM(EgovMap vo) throws Exception;
    
    /**
	 * mp_plan_pm_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_pm_m 목록
	 * @exception Exception
	 */
    List selectMpPlanPmMList(MpPlanPmMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_plan_pm_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_pm_m 총 갯수
	 * @exception
	 */
    int selectMpPlanPmMListTotCnt(MpPlanPmMDefaultVO searchVO);
    public List selectMpAcceptOrdrPmMList(MpPlanPmMDefaultVO searchVO) throws Exception;
    /**
	 * 접수분 조회 MpAcceptOrdrPmM 총 갯수
	 * @exception
	 */
    public int selectMpAcceptOrdrPmMListTotCnt(MpPlanPmMDefaultVO searchVO);

    public List selectMpPlanPmMListPlanTime(MpPlanPmMDefaultVO searchVO) throws Exception ;
    
    public List selectPP0306MpPlanPmMList(MpPlanPmMDefaultVO searchVO) throws Exception ;

}
