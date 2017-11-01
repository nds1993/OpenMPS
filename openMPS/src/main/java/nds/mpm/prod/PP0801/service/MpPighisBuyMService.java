package nds.mpm.prod.PP0801.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0801.vo.MpPighisBuyMDefaultVO;
import nds.mpm.prod.PP0801.vo.MpPighisBuyMVO;

/**
 * @Class Name : MpPighisBuyMService.java
 * @Description : MpPighisBuyM Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpPighisBuyMService {
	
	/**
	 * mp_pighis_buy_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPighisBuyMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPighisBuyM(List<EgovMap> vos) throws Exception ;
    
    /**
	 * mp_pighis_buy_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPighisBuyMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPighisBuyM(MpPighisBuyMVO vo) throws Exception;
    
    /**
	 * mp_pighis_buy_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPighisBuyMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpPighisBuyM(MpPighisBuyMVO vo) throws Exception;
    
    /**
	 * mp_pighis_buy_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPighisBuyMVO
	 * @return 조회한 mp_pighis_buy_m
	 * @exception Exception
	 */
    MpPighisBuyMVO selectMpPighisBuyM(EgovMap vo) throws Exception;
    
    /**
	 * mp_pighis_buy_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pighis_buy_m 목록
	 * @exception Exception
	 */
    List selectMpPighisBuyMList(MpPighisBuyMVO searchVO) throws Exception;
    
    /**
	 * mp_pighis_buy_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pighis_buy_m 총 갯수
	 * @exception
	 */
    int selectMpPighisBuyMListTotCnt(MpPighisBuyMVO searchVO);
    public List<EgovMap> selectMpPighisBuyMSendFormatList(MpPighisBuyMVO searchVO) throws Exception ;


    public int updateApiTimeMpPighisBuyM(EgovMap vo) throws Exception ;
    
    public int checkDupBuyDateMpPighisBuyM(MpPighisBuyMVO searchVO) ;

    public int deleteDupMpPighisBuyM(MpPighisBuyMVO searchVO) ;
    
    public String selectBuyTypeMpPighisBuyM_S(MpPighisBuyMVO searchVO) ;


}
