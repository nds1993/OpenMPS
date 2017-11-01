package nds.mpm.prod.PP0303.service;

import java.util.List;

import nds.mpm.prod.PP0303.vo.MpPlanPlatDDefaultVO;
import nds.mpm.prod.PP0303.vo.MpPlanPlatDVO;

/**
 * @Class Name : MpPlanPlatDService.java
 * @Description : MpPlanPlatD Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpPlanPlatDService {
	
	/**
	 * mp_plan_plat_d을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanPlatDVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpPlanPlatD(MpPlanPlatDVO vo) throws Exception;
    
    /**
	 * mp_plan_plat_d을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanPlatDVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPlanPlatD(MpPlanPlatDVO vo) throws Exception;
    
    /**
	 * mp_plan_plat_d을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanPlatDVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpPlanPlatD(MpPlanPlatDVO vo) throws Exception;
    
    /**
	 * mp_plan_plat_d을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanPlatDVO
	 * @return 조회한 mp_plan_plat_d
	 * @exception Exception
	 */
    MpPlanPlatDVO selectMpPlanPlatD(MpPlanPlatDVO vo) throws Exception;
    
    /**
	 * mp_plan_plat_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_plat_d 목록
	 * @exception Exception
	 */
    List selectMpPlanPlatDList(MpPlanPlatDDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_plan_plat_d 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_plat_d 총 갯수
	 * @exception
	 */
    int selectMpPlanPlatDListTotCnt(MpPlanPlatDDefaultVO searchVO);
    
}
