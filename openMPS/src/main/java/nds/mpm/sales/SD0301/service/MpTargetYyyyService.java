package nds.mpm.sales.SD0301.service;

import java.util.List;
import nds.mpm.sales.SD0301.vo.MpTargetYyyyDefaultVO;
import nds.mpm.sales.SD0301.vo.MpTargetYyyyVO;

/**
 * @Class Name : MpTargetYyyyService.java
 * @Description : MpTargetYyyy Business class
 * @Modification Information
 *
 * @author 333333
 * @since 333333
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpTargetYyyyService {
	
	/**
	 * mp_target_yyyy을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpTargetYyyyVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpTargetYyyy(MpTargetYyyyVO vo) throws Exception;
    
    /**
	 * mp_target_yyyy을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpTargetYyyyVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpTargetYyyy(MpTargetYyyyVO vo) throws Exception;
    
    /**
	 * mp_target_yyyy을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpTargetYyyyVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpTargetYyyy(MpTargetYyyyVO vo) throws Exception;
    
    /**
	 * mp_target_yyyy을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpTargetYyyyVO
	 * @return 조회한 mp_target_yyyy
	 * @exception Exception
	 */
    MpTargetYyyyVO selectMpTargetYyyy(MpTargetYyyyVO vo) throws Exception;
    
    /**
	 * mp_target_yyyy 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_target_yyyy 목록
	 * @exception Exception
	 */
    List selectMpTargetYyyyList(MpTargetYyyyDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_target_yyyy 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_target_yyyy 총 갯수
	 * @exception
	 */
    int selectMpTargetYyyyListTotCnt(MpTargetYyyyDefaultVO searchVO);
    
}
