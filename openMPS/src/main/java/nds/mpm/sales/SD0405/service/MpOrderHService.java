package nds.mpm.sales.SD0405.service;

import java.util.List;
import java.util.Map;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0405.vo.MpOrderDVO;
import nds.mpm.sales.SD0405.vo.MpOrderHDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderHService.java
 * @Description : MpOrderH Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpOrderHService {
	
	/**
	 * mp_order_h을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpOrderHVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	
    /**
	 * mp_order_h을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpOrderHVO
	 * @return 조회한 mp_order_h
	 * @exception Exception
	 */
    
    /**
	 * mp_order_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 목록
	 * @exception Exception
	 */
    List selectMpOrderHList(MpOrderHDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    int selectMpOrderHListTotCnt(MpOrderHDefaultVO searchVO);
    
    public List<?> selectSD0406List(MpOrderHDefaultVO searchVO) throws Exception;

    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    public int selectSD0406ListTotCnt(MpOrderHDefaultVO searchVO);
    
    public List<?> selectSD0406CustList(MpOrderHDefaultVO searchVO) throws Exception ;
    
    public List<?> selectSD0406OrdrProList(MpOrderHDefaultVO searchVO) throws Exception;
    
}
