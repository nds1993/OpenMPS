package nds.mpm.prod.PP0302.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0302.vo.MpPlanCmDVO;
import nds.mpm.prod.PP0302.vo.MpPlanCmHDefaultVO;
import nds.mpm.prod.PP0302.vo.MpPlanCmHVO;
import nds.mpm.prod.PP0302.vo.MultiMpPlanCmHVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPlanCmHService.java
 * @Description : MpPlanCmH Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpPlanCmHService {
	
    public String selectMpPlanCmHCanNewCmPlan(MpPlanCmHDefaultVO searchVO);
    
    public ResultEx insertNewMpPlanCmH(MpPlanCmHVO searchVO) throws Exception ;
	/**
	 * mp_plan_cm_h을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanCmHVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPlanCmH(MultiMpPlanCmHVO multiMpPlanCmHVO) throws Exception ;
    
    /**
	 * mp_plan_cm_h을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanCmHVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPlanCmH(MpPlanCmHVO vo) throws Exception;
	public int updateMpPlanCmHStatus(MpPlanCmHVO vo) throws Exception;
    
    /**
	 * mp_plan_cm_h을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanCmHVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpPlanCmH(MpPlanCmHVO vo) throws Exception;
    public ResultEx deleteMpPlanWorkDate(EgovMap vo) throws Exception ;

    /**
	 * mp_plan_cm_h을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanCmHVO
	 * @return 조회한 mp_plan_cm_h
	 * @exception Exception
	 */
    MpPlanCmHVO selectMpPlanCmH(MpPlanCmHVO vo) throws Exception;
    
    /**
	 * mp_plan_cm_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_cm_h 목록
	 * @exception Exception
	 */
    List selectMpPlanCmHList(MpPlanCmHDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_plan_cm_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_cm_h 총 갯수
	 * @exception
	 */
    int selectMpPlanCmHListTotCnt(MpPlanCmHDefaultVO searchVO);
    
    public List selectNewMpPlanCmHList(MpPlanCmHDefaultVO searchVO) throws Exception;
    
    public EgovMap selectNewMpPlanCmHSum(MpPlanCmHDefaultVO searchVO) throws Exception;
    public EgovMap selectNewMpPlanCmHProCode(MpPlanCmHDefaultVO searchVO) throws Exception ;

    
    public List<EgovMap> selectNewMpPlanCmHSumList(MpPlanCmHDefaultVO searchVO) throws Exception;
    public EgovMap selectMpPlanCmHDoosuSum(MpPlanCmHDefaultVO searchVO) throws Exception;
    
    public EgovMap selectCompTimeMpPlanCmH(MpPlanCmHDefaultVO vo) throws Exception ;

}
