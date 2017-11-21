package nds.mpm.sales.SD0101.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0101.vo.MpCustInfoDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustInfoMultiVO;
import nds.mpm.sales.SD0101.vo.MpCustInfoVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustInfoService.java
 * @Description : MpCustInfo Business class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpCustInfoService {
	
	/**
	 *	신규생성
	 * mp_cust_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpCustInfo(MpCustInfoMultiVO vo,MPUserSession sess) throws Exception;
    
    /**
	 * mp_cust_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustInfoVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpCustInfo(EgovMap vo) throws Exception;
    
    public void updateMpCustInfoFac(MpCustInfoVO vo) throws Exception;
    
    /**
	 * mp_cust_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpCustInfo(MpCustInfoVO vo) throws Exception;
    
    /**
	 * mp_cust_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustInfoVO
	 * @return 조회한 mp_cust_info
	 * @exception Exception
	 */
    MpCustInfoVO selectMpCustInfo(MpCustInfoVO vo) throws Exception;
    /**
     * mp_cust_info을 조회한다.
     * @param vo - 조회할 정보가 담긴 MpCustInfoVO
     * @return 조회한 mp_cust_info
     * @exception Exception
     */
    MpCustInfoVO selectDupMpCustInfo(MpCustInfoVO vo) throws Exception;
    
    /**
	 * mp_cust_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_info 목록
	 * @exception Exception
	 */
    List selectMpCustInfoList(MpCustInfoDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_cust_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_info 총 갯수
	 * @exception
	 */
    int selectMpCustInfoListTotCnt(MpCustInfoDefaultVO searchVO);
    
    public List selectMpCustInfoCodeList(MpCustInfoDefaultVO searchVO) throws Exception;
    
    public EgovMap selectMpCustAccountInfo(MpCustInfoVO vo) throws Exception ;

    public List selectOnlineUploadMpCustInfoCodeList(MpCustInfoDefaultVO searchVO) throws Exception ;

}
