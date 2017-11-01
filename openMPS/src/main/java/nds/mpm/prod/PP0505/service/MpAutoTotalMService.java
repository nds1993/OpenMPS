package nds.mpm.prod.PP0505.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0505.vo.MpAutoTotalMDefaultVO;
import nds.mpm.prod.PP0505.vo.MpAutoTotalMVO;

/**
 * @Class Name : MpAutoTotalMService.java
 * @Description : MpAutoTotalM Business class
 * @Modification Information
 *
 * @author PM라벨실적 입고 요청(이시다)
 * @since PM라벨실적 입고 요청(이시다)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpAutoTotalMService {
	
	/**
	 * mp_auto_total_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpAutoTotalMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpAutoTotalM(MpAutoTotalMVO vo) throws Exception;
    
    /**
	 * mp_auto_total_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpAutoTotalMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpAutoTotalM(MpAutoTotalMVO vo) throws Exception;
    
    /**
	 * mp_auto_total_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpAutoTotalMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpAutoTotalM(MpAutoTotalMVO vo) throws Exception;
    
    /**
	 * mp_auto_total_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpAutoTotalMVO
	 * @return 조회한 mp_auto_total_m
	 * @exception Exception
	 */
    MpAutoTotalMVO selectMpAutoTotalM(MpAutoTotalMVO vo) throws Exception;
    
    /**
	 * mp_auto_total_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_auto_total_m 목록
	 * @exception Exception
	 */
    List selectMpAutoTotalMList(MpAutoTotalMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_auto_total_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_auto_total_m 총 갯수
	 * @exception
	 */
    int selectMpAutoTotalMListTotCnt(MpAutoTotalMDefaultVO searchVO);
    public List<?> selectMpAutoTotalMDetail(MpAutoTotalMDefaultVO searchVO) throws Exception ;

    public ResultEx checkIpgoClose(MpAutoTotalMDefaultVO searchVO) throws Exception ;

}
