package nds.mpm.sales.SD0501.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0501.vo.SD0501DefaultVO;
import nds.mpm.sales.SD0501.vo.SD0501VO;

/**
 * @Class Name : SD0501Service.java
 * @Description : SD0501 Business class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SD0501Service {
	
	/**
	 * mp_rece_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SD0501VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertSD0501(List<EgovMap> vos) throws Exception ;
    
    /**
	 * mp_rece_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SD0501VO
	 * @return void형
	 * @exception Exception
	 */
    void updateSD0501(SD0501VO vo) throws Exception;
    
    /**
	 * mp_rece_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SD0501VO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteSD0501(SD0501VO vo) throws Exception;
    
    /**
	 * mp_rece_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SD0501VO
	 * @return 조회한 mp_rece_info
	 * @exception Exception
	 */
    SD0501VO selectSD0501(SD0501VO vo) throws Exception;
    
    /**
	 * mp_rece_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_rece_info 목록
	 * @exception Exception
	 */
    List selectSD0501List(SD0501DefaultVO searchVO) throws Exception;
    
    /**
	 * mp_rece_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_rece_info 총 갯수
	 * @exception
	 */
    int selectSD0501ListTotCnt(SD0501DefaultVO searchVO);
    
}
