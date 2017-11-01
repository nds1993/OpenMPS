package nds.mpm.prod.PP0301.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0301.vo.MpPlanSetupMDefaultVO;
import nds.mpm.prod.PP0301.vo.MpPlanSetupMVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPlanSetupMService.java
 * @Description : MpPlanSetupM Business class
 * @Modification Information
 *
 * @author M
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpPlanSetupMService {
	
	/**
	 * mp_plan_setup_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanSetupMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpPlanSetupM(List<EgovMap> vo) throws Exception;
    
    /**
	 * mp_plan_setup_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanSetupMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPlanSetupM(MpPlanSetupMVO vo) throws Exception;
    
    /**
	 * mp_plan_setup_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanSetupMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpPlanSetupM(MpPlanSetupMVO vo) throws Exception;
    
    /**
	 * mp_plan_setup_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanSetupMVO
	 * @return 조회한 mp_plan_setup_m
	 * @exception Exception
	 */
    MpPlanSetupMVO selectMpPlanSetupM(MpPlanSetupMVO vo) throws Exception;
    
    /**
	 * mp_plan_setup_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_setup_m 목록
	 * @exception Exception
	 */
    List selectMpPlanSetupMList(MpPlanSetupMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_plan_setup_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_setup_m 총 갯수
	 * @exception
	 */
    int selectMpPlanSetupMListTotCnt(MpPlanSetupMDefaultVO searchVO);
    
}
