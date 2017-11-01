package nds.mpm.prod.PP0302.service;

import java.util.List;

import nds.mpm.prod.PP0302.vo.MpPlanCmDDefaultVO;
import nds.mpm.prod.PP0302.vo.MpPlanCmDVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPlanCmDService.java
 * @Description : MpPlanCmD Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpPlanCmDService {
	
	/**
	 * mp_plan_cm_d을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanCmDVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpPlanCmD(EgovMap vo) throws Exception;
    
    /**
	 * mp_plan_cm_d을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanCmDVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPlanCmD(MpPlanCmDVO vo) throws Exception;
    
    /**
	 * mp_plan_cm_d을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanCmDVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpPlanCmD(MpPlanCmDVO vo) throws Exception;
    
    /**
	 * mp_plan_cm_d을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanCmDVO
	 * @return 조회한 mp_plan_cm_d
	 * @exception Exception
	 */
    MpPlanCmDVO selectMpPlanCmD(MpPlanCmDVO vo) throws Exception;
    
    /**
	 * mp_plan_cm_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_cm_d 목록
	 * @exception Exception
	 */
    List selectMpPlanCmDList(MpPlanCmDDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_plan_cm_d 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_cm_d 총 갯수
	 * @exception
	 */
    int selectMpPlanCmDListTotCnt(MpPlanCmDDefaultVO searchVO);
    
}
