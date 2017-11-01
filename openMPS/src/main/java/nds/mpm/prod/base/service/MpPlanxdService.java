package nds.mpm.prod.base.service;

import java.util.List;
import nds.mpm.prod.base.vo.MpPlanxdDefaultVO;
import nds.mpm.prod.base.vo.MpPlanxdVO;

/**
 * @Class Name : MpPlanxdService.java
 * @Description : MpPlanxd Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.28
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpPlanxdService {
	
	/**
	 * MP_PLANXD을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanxdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpPlanxd(MpPlanxdVO vo) throws Exception;
    
    /**
	 * MP_PLANXD을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanxdVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPlanxd(MpPlanxdVO vo) throws Exception;
    
    /**
	 * MP_PLANXD을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanxdVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpPlanxd(MpPlanxdVO vo) throws Exception;
    
    /**
	 * MP_PLANXD을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanxdVO
	 * @return 조회한 MP_PLANXD
	 * @exception Exception
	 */
    MpPlanxdVO selectMpPlanxd(MpPlanxdVO vo) throws Exception;
    
    /**
	 * MP_PLANXD 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_PLANXD 목록
	 * @exception Exception
	 */
    List selectMpPlanxdList(MpPlanxdDefaultVO searchVO) throws Exception;
    
    /**
	 * MP_PLANXD 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_PLANXD 총 갯수
	 * @exception
	 */
    int selectMpPlanxdListTotCnt(MpPlanxdDefaultVO searchVO);
    
}
