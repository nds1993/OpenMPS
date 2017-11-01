package nds.mpm.prod.PP0104.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0103.vo.MpItemMasterMVO;
import nds.mpm.prod.PP0104.vo.MpSetItemMDefaultVO;
import nds.mpm.prod.PP0104.vo.MpSetItemMVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpSetItemMService.java
 * @Description : MpSetItemM Business class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpSetItemMService {
	
	/**
	 * mp_set_item_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpSetItemMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpSetItemM(List<EgovMap> vos) throws Exception ;
    
    /**
	 * mp_set_item_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSetItemMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpSetItemM(MpSetItemMVO vo) throws Exception;
    
    /**
	 * mp_set_item_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpSetItemMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpSetItemM(MpSetItemMVO vo) throws Exception;
    
    /**
	 * mp_set_item_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpSetItemMVO
	 * @return 조회한 mp_set_item_m
	 * @exception Exception
	 */
    MpSetItemMVO selectMpSetItemM(EgovMap vo) throws Exception;
    
    /**
	 * mp_set_item_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_set_item_m 목록
	 * @exception Exception
	 */
    List selectMpSetItemMList(MpSetItemMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_set_item_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_set_item_m 총 갯수
	 * @exception
	 */
    int selectMpSetItemMListTotCnt(MpSetItemMDefaultVO searchVO);
    
    public List<?> selectMpItemMasterMList(MpItemMasterMVO searchVO) throws Exception ;

}
