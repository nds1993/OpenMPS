package nds.mpm.sales.SD0202.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0202.vo.MpStndPriceDefaultVO;
import nds.mpm.sales.SD0202.vo.MpStndPriceVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpStndPriceService.java
 * @Description : MpStndPrice Business class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpStndPriceService {
	
	/**
	 * mp_stnd_price을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpStndPriceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx  insertMpStndPrice(List<EgovMap> vo) throws Exception;
    
    /**
	 * mp_stnd_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpStndPriceVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpStndPrice(MpStndPriceVO vo) throws Exception;
    
    /**
	 * mp_stnd_price을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpStndPriceVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpStndPrice(MpStndPriceVO vo) throws Exception;
    
    /**
	 * mp_stnd_price을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpStndPriceVO
	 * @return 조회한 mp_stnd_price
	 * @exception Exception
	 */
    MpStndPriceVO selectMpStndPrice(EgovMap vo) throws Exception;
    
    /**
	 * mp_stnd_price 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_stnd_price 목록
	 * @exception Exception
	 */
    List selectMpStndPriceList(MpStndPriceDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_stnd_price 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_stnd_price 총 갯수
	 * @exception
	 */
    int selectMpStndPriceListTotCnt(MpStndPriceDefaultVO searchVO);
    public List<?> selectMpStndPricePeriodList(MpStndPriceDefaultVO searchVO) throws Exception;

}
