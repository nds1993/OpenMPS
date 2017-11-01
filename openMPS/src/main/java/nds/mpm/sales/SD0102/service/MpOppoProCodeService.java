package nds.mpm.sales.SD0102.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0102.vo.MpOppoProCodeDefaultVO;
import nds.mpm.sales.SD0102.vo.MpOppoProCodeVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOppoProCodeService.java
 * @Description : MpOppoProCode Business class
 * @Modification Information
 *
 * @author dd
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpOppoProCodeService {
	
	/**
	 * mp_oppo_pro_code을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpOppoProCodeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpOppoProCode(List<EgovMap> vo) throws Exception;
    
    /**
	 * mp_oppo_pro_code을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpOppoProCodeVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpOppoProCode(MpOppoProCodeVO vo) throws Exception;
    
    /**
	 * mp_oppo_pro_code을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpOppoProCodeVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpOppoProCode(MpOppoProCodeVO vo) throws Exception;
    
    /**
	 * mp_oppo_pro_code을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpOppoProCodeVO
	 * @return 조회한 mp_oppo_pro_code
	 * @exception Exception
	 */
    MpOppoProCodeVO selectMpOppoProCode(EgovMap vo) throws Exception;
    
    /**
	 * mp_oppo_pro_code 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_oppo_pro_code 목록
	 * @exception Exception
	 */
    List selectMpOppoProCodeList(MpOppoProCodeDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_oppo_pro_code 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_oppo_pro_code 총 갯수
	 * @exception
	 */
    int selectMpOppoProCodeListTotCnt(MpOppoProCodeDefaultVO searchVO);
    
}
