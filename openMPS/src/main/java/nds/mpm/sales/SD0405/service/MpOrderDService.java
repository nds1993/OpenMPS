package nds.mpm.sales.SD0405.service;

import java.util.List;

import nds.mpm.sales.SD0405.vo.MpOrderDDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderDVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderDService.java
 * @Description : MpOrderD Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpOrderDService {
	
	/**
	 * mp_order_d을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpOrderDVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpOrderD(EgovMap vo) throws Exception;
    
    /**
	 * mp_order_d을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpOrderDVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpOrderD(EgovMap vo) throws Exception;
    
    /**
	 * mp_order_d을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpOrderDVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpOrderD(EgovMap vo) throws Exception;
    
    /**
	 * mp_order_d을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpOrderDVO
	 * @return 조회한 mp_order_d
	 * @exception Exception
	 */
    EgovMap selectMpOrderD(MpOrderDVO vo) throws Exception;
    
    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    List selectMpOrderDList(MpOrderDDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_order_d 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 총 갯수
	 * @exception
	 */
    int selectMpOrderDListTotCnt(MpOrderDDefaultVO searchVO);
    
}
