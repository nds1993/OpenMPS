package nds.mpm.sales.SD0402.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0402.vo.SD0402DefaultVO;
import nds.mpm.sales.SD0402.vo.SD0402VO;
import nds.mpm.sales.SD0402.vo.SD0402DefaultVO;
import nds.mpm.sales.SD0402.vo.SD0402VO;
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
public interface SD0402Service {
	
	/**
	 * mp_delv_approval을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SD0402VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertSD0402(List<EgovMap> vos) throws Exception;
    
    /**
	 * mp_delv_approval을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SD0402VO
	 * @return void형
	 * @exception Exception
	 */
    void updateSD0402(SD0402VO vo) throws Exception;
    
    /**
	 * mp_delv_approval을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SD0402VO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteSD0402(SD0402VO vo) throws Exception;
    
    /**
	 * mp_delv_approval을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SD0402VO
	 * @return 조회한 mp_delv_approval
	 * @exception Exception
	 */
    SD0402VO selectSD0402(SD0402VO vo) throws Exception;
    
    /**
	 * mp_delv_approval 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_delv_approval 목록
	 * @exception Exception
	 */
    List selectSD0402List(SD0402DefaultVO searchVO) throws Exception;
    
    /**
	 * mp_delv_approval 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_delv_approval 총 갯수
	 * @exception
	 */
    int selectSD0402ListTotCnt(SD0402DefaultVO searchVO);
    
    public List<?> selectSD0403List(SD0402DefaultVO searchVO) throws Exception;

    /**
	 * mp_delv_approval 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_delv_approval 총 갯수
	 * @exception
	 */
    public int selectSD0403ListTotCnt(SD0402DefaultVO searchVO) ;
    
    public int updateDelvApproConfirm(List<EgovMap> vos) throws Exception;
    
    public EgovMap selectUserInfo(EgovMap vo) throws Exception ;

}
