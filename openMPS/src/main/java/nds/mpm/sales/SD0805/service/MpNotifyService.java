package nds.mpm.sales.SD0805.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0805.vo.MpNotifyDefaultVO;
import nds.mpm.sales.SD0805.vo.MpNotifyVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpNotifyService.java
 * @Description : MpNotify Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpNotifyService {
	
	/**
	 * mp_notify을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpNotifyVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpNotify(List<EgovMap> vo) throws Exception;
    
    /**
	 * mp_notify을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpNotifyVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpNotify(MpNotifyVO vo) throws Exception;
    
    /**
	 * mp_notify을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpNotifyVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpNotify(MpNotifyVO vo) throws Exception;
    
    /**
	 * mp_notify을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpNotifyVO
	 * @return 조회한 mp_notify
	 * @exception Exception
	 */
    MpNotifyVO selectMpNotify(EgovMap vo) throws Exception;
    
    /**
	 * mp_notify 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_notify 목록
	 * @exception Exception
	 */
    List selectMpNotifyList(MpNotifyDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_notify 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_notify 총 갯수
	 * @exception
	 */
    int selectMpNotifyListTotCnt(MpNotifyDefaultVO searchVO);
    
}
