package nds.mpm.sales.SD0203.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0203.vo.MpSalePriceDefaultVO;
import nds.mpm.sales.SD0203.vo.MpSalePriceVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpSalePriceService.java
 * @Description : MpSalePrice Business class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpSalePriceService {
	
	/**
	 * mp_sale_price을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpSalePriceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpSalePrice(List<EgovMap> vo) throws Exception;
    
    /**
	 * mp_sale_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSalePriceVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpSalePrice(MpSalePriceVO vo) throws Exception;
    
    /**
	 * mp_sale_price을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpSalePriceVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpSalePrice(MpSalePriceVO vo) throws Exception;
    
    /**
	 * mp_sale_price을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpSalePriceVO
	 * @return 조회한 mp_sale_price
	 * @exception Exception
	 */
    EgovMap selectMpSalePrice(EgovMap vo) throws Exception;
    
    /**
	 * mp_sale_price 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_sale_price 목록
	 * @exception Exception
	 */
    List selectMpSalePriceList(MpSalePriceDefaultVO searchVO) throws Exception;
    
    public List selectNewMpSalePriceList(MpSalePriceDefaultVO searchVO) throws Exception;

    /**
	 * mp_sale_price 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_sale_price 총 갯수
	 * @exception
	 */
    int selectMpSalePriceListTotCnt(MpSalePriceDefaultVO searchVO);
    
    public ResultEx updateReqApproMpSalePrice(List<EgovMap> vos) throws Exception ;
    
    public ResultEx updateSD0206MpSalePrice(List<EgovMap> vos) throws Exception ;
    
    public List<?> selectSD0206MpSalePriceList(MpSalePriceDefaultVO searchVO) throws Exception;

    public int selectSD0206MpSalePriceListTotCnt(MpSalePriceDefaultVO searchVO);
    
    public int checkNewStrtDateCount(EgovMap searchVO) throws Exception;
    
    public ResultEx updateCancelApproMpSalePrice(List<EgovMap> vos) throws Exception ;

    public ResultEx updateSD0206ApproRejectMpSalePrice(List<EgovMap> vos) throws Exception ;

}
