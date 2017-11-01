package nds.mpm.prod.base.service;

import java.util.List;
import nds.mpm.prod.base.vo.MpPlanxhDefaultVO;
import nds.mpm.prod.base.vo.MpPlanxhVO;

/**
 * @Class Name : MpPlanxhService.java
 * @Description : MpPlanxh Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.28
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpPlanxhService {
	
	/**
	 * MP_PLANXH을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanxhVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpPlanxh(MpPlanxhVO vo) throws Exception;
    
    /**
	 * MP_PLANXH을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanxhVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPlanxh(MpPlanxhVO vo) throws Exception;
    
    /**
	 * MP_PLANXH을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanxhVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpPlanxh(MpPlanxhVO vo) throws Exception;
    
    /**
	 * MP_PLANXH을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanxhVO
	 * @return 조회한 MP_PLANXH
	 * @exception Exception
	 */
    MpPlanxhVO selectMpPlanxh(MpPlanxhVO vo) throws Exception;
    
    /**
	 * MP_PLANXH 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_PLANXH 목록
	 * @exception Exception
	 */
    List selectMpPlanxhList(MpPlanxhDefaultVO searchVO) throws Exception;
    
    /**
	 * MP_PLANXH 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_PLANXH 총 갯수
	 * @exception
	 */
    int selectMpPlanxhListTotCnt(MpPlanxhDefaultVO searchVO);
    
}
