package nds.mpm.prod.PP0103.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0103.vo.MpItemMasterMDefaultVO;
import nds.mpm.prod.PP0103.vo.MpItemMasterMVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpItemMasterMService.java
 * @Description : MpItemMasterM Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.08
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpItemMasterMService {
	
	/**
	 * product_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpItemMasterMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpItemMasterM(List<EgovMap> vos) throws Exception ;
    
    /**
	 * product_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpItemMasterMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpItemMasterM(MpItemMasterMVO vo) throws Exception;
    
    /**
	 * product_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpItemMasterMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpItemMasterM(MpItemMasterMVO vo) throws Exception;
    
    /**
	 * product_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpItemMasterMVO
	 * @return 조회한 product_info
	 * @exception Exception
	 */
    MpItemMasterMVO selectMpItemMasterM(EgovMap vo) throws Exception;
    
    /**
	 * product_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return product_info 목록
	 * @exception Exception
	 */
    List selectMpItemMasterMList(MpItemMasterMDefaultVO searchVO) throws Exception;
    
    /**
	 * product_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return product_info 총 갯수
	 * @exception
	 */
    int selectMpItemMasterMListTotCnt(MpItemMasterMDefaultVO searchVO);
    
    public List<?> selectMpItemMasterMCodeList(MpItemMasterMVO searchVO) throws Exception;
}
