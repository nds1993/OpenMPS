package nds.mpm.sales.SD0501.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0501.vo.SD0501VO;

/**
 * @Class Name : SD0501Service.java
 * @Description : SD0501 Business class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SD0501Service {
	
	/**
	 * sd_claimrqxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SD0501VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertSD0501(SD0501VO vo) throws Exception;
	
	/**
	 * sd_claimpcxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SD0501VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertSdClaimpcxm(SD0501VO vo) throws Exception;
    
    /**
	 * sd_claimrqxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SD0501VO
	 * @return void형
	 * @exception Exception
	 */
    void updateSD0501(SD0501VO vo) throws Exception;
    
    /**
	 * sd_claimrqxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SD0501VO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteSD0501(SD0501VO vo) throws Exception;
    
    /**
     * sd_claimrqxm을 변경한다.
     * @param vo - 변경할 정보가 담긴 SD0501VO
     * @return void형 
     * @exception Exception
     */
    void updateProcStatusSD0501(SD0501VO vo) throws Exception;
    
    /**
	 * sd_claimrqxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SD0501VO
	 * @return 조회한 sd_claimrqxm
	 * @exception Exception
	 */
    SD0501VO selectSD0501(SD0501VO vo) throws Exception;
    
    /**
	 * sd_claimrqxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return sd_claimrqxm 목록
	 * @exception Exception
	 */
    List selectSD0501List(SD0501VO searchVO) throws Exception;
    
    /**
	 * sd_claimrqxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return sd_claimrqxm 총 갯수
	 * @exception
	 */
    int selectSD0501ListTotCnt(SD0501VO searchVO);
    
}
