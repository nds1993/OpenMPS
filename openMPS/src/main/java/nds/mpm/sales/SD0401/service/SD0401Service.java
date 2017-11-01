package nds.mpm.sales.SD0401.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0401.vo.MultiSD0401VO;
import nds.mpm.sales.SD0401.vo.SD0401DefaultVO;
import nds.mpm.sales.SD0401.vo.SD0401VO;
import nds.mpm.sales.SD0402.vo.SD0402DefaultVO;
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
public interface SD0401Service {
	
	/**
	 * mp_order_h을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpOrderHVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpOrderH(SD0401VO searchVO, MultiSD0401VO reqMVO) throws Exception;

	/**
	 * mp_order_h을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpOrderHVO
	 * @return 조회한 mp_order_h
	 * @exception Exception
	 */
    SD0401VO selectSD0401(EgovMap vo) throws Exception;
    
    /**
	 * mp_order_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 목록
	 * @exception Exception
	 */
    List selectSD0401List(SD0401DefaultVO searchVO) throws Exception;
    
    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    int selectSD0401ListTotCnt(SD0401DefaultVO searchVO);
    
    List selectSD0402List(SD0402DefaultVO searchVO) throws Exception;
    
    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    int selectSD0402ListTotCnt(SD0402DefaultVO searchVO);
    
    public EgovMap selectSD0401ProJaegoInfo(SD0401VO vo) throws Exception;
    
    public EgovMap selectSD0401CustLimit(SD0401VO vo) throws Exception ;
    
    public String selectSD0401DelvCust(SD0401DefaultVO searchVO) ;

    public List selectTab1MpOrderDList(SD0401VO searchVO) throws Exception ;

    public List selectTab2MpOrderDList(SD0401VO searchVO) throws Exception ;
}
