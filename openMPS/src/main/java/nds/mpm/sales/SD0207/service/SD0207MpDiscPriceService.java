package nds.mpm.sales.SD0207.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0205.vo.MpDiscPriceDefaultVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceTitleVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDiscPriceService.java
 * @Description : MpDiscPrice Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SD0207MpDiscPriceService {
	
	/**
	 * mp_disc_price을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDiscPriceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpDiscPrice(MpDiscPriceVO vo) throws Exception;
    
    /**
	 * mp_disc_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDiscPriceVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpDiscPrice(MpDiscPriceVO vo) throws Exception;
    
    /**
	 * mp_disc_price을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDiscPriceVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpDiscPrice(MpDiscPriceVO vo) throws Exception;
    
    /**
	 * mp_disc_price을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDiscPriceVO
	 * @return 조회한 mp_disc_price
	 * @exception Exception
	 */
    MpDiscPriceVO selectMpDiscPrice(MpDiscPriceVO vo) throws Exception;
    
    /**
	 * mp_disc_price 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price 목록
	 * @exception Exception
	 */
    List selectMpDiscPriceTitleList(MpDiscPriceTitleVO searchVO) throws Exception;
    
    /**
	 * mp_disc_price 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price 총 갯수
	 * @exception
	 */
    int selectMpDiscPriceListTotCnt(MpDiscPriceDefaultVO searchVO);
    
    public ResultEx updateMpDiscPriceConfirm(List<EgovMap> vo) throws Exception;
    
    public List selectMpDiscPriceDetailList(MpDiscPriceTitleVO searchVO) throws Exception;

}
