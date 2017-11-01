package nds.mpm.sales.SD0205.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0205.vo.MpDiscPriceTitleDefaultVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceTitleVO;

/**
 * @Class Name : MpDiscPriceTitleService.java
 * @Description : MpDiscPriceTitle Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpDiscPriceTitleService {
	
	/**
	 * mp_disc_price_title을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDiscPriceTitleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpDiscPriceTitle(List<EgovMap> vos) throws Exception ;
    
    /**
	 * mp_disc_price_title을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDiscPriceTitleVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpDiscPriceTitle(MpDiscPriceTitleVO vo) throws Exception;
    
    /**
	 * mp_disc_price_title을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDiscPriceTitleVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpDiscPriceTitle(MpDiscPriceTitleVO vo) throws Exception;
    
    /**
	 * mp_disc_price_title을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDiscPriceTitleVO
	 * @return 조회한 mp_disc_price_title
	 * @exception Exception
	 */
    EgovMap selectMpDiscPriceTitle(EgovMap vo) throws Exception;
    
    /**
	 * mp_disc_price_title 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price_title 목록
	 * @exception Exception
	 */
    List selectMpDiscPriceTitleList(MpDiscPriceTitleDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_disc_price_title 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price_title 총 갯수
	 * @exception
	 */
    int selectMpDiscPriceTitleListTotCnt(MpDiscPriceTitleDefaultVO searchVO);
    
}
