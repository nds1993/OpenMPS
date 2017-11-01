package nds.mpm.prod.PP0803.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0803.vo.MpPighisSaleMDefaultVO;
import nds.mpm.prod.PP0803.vo.MpPighisSaleMVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPighisSaleMService.java
 * @Description : MpPighisSaleM Business class
 * @Modification Information
 *
 * @author 거래내역실적신고(이력제)
 * @since 거래내역실적신고(이력제)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpPighisSaleMService {
	
	/**
	 * mp_pighis_sale_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPighisSaleMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPighisSaleM(List<EgovMap> vos) throws Exception ;
    
    /**
	 * mp_pighis_sale_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPighisSaleMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPighisSaleM(MpPighisSaleMVO vo) throws Exception;
    
    /**
	 * mp_pighis_sale_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPighisSaleMVO
	 * @return void형 
	 * @exception Exception
	 */
    int deleteMpPighisSaleM(MpPighisSaleMVO vo) throws Exception;
    
    /**
	 * mp_pighis_sale_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPighisSaleMVO
	 * @return 조회한 mp_pighis_sale_m
	 * @exception Exception
	 */
    MpPighisSaleMVO selectMpPighisSaleM(MpPighisSaleMVO vo) throws Exception;
    
    /**
	 * mp_pighis_sale_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pighis_sale_m 목록
	 * @exception Exception
	 */
    List selectMpPighisSaleMList(MpPighisSaleMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_pighis_sale_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pighis_sale_m 총 갯수
	 * @exception
	 */
    int selectMpPighisSaleMListTotCnt(MpPighisSaleMDefaultVO searchVO);
    
    public List<EgovMap> selectMpPighisSaleMSendFormatList(MpPighisSaleMDefaultVO searchVO) throws Exception;
    
    public int updateApiTimeMpPighisSaleM(EgovMap vo) throws Exception ;
    
    public List selectMpPighisSaleMProcodeList(MpPighisSaleMDefaultVO searchVO) throws Exception ;

    public List selectOdOderhdList(MpPighisSaleMDefaultVO searchVO) throws Exception ;
    
    public int checkDupSaleDate(MpPighisSaleMDefaultVO searchVO) ;

    public int selectMpPighisSaleSum(MpPighisSaleMDefaultVO searchVO) ;

}
