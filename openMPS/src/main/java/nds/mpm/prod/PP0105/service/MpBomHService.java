package nds.mpm.prod.PP0105.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0105.vo.MpBomHDefaultVO;
import nds.mpm.prod.PP0105.vo.MpBomHVO;
import nds.mpm.prod.PP0105.vo.MultiMpBomVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpBomHService.java
 * @Description : MpBomH Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpBomHService {
	
	/**
	 * mp_bom_h을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBomHVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpBomH(MultiMpBomVO reqVo) throws Exception ;
    
    /**
	 * mp_bom_h을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBomHVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpBomH(MpBomHVO vo) throws Exception;
    
    /**
	 * mp_bom_h을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBomHVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpBomH(MpBomHVO vo) throws Exception;
    
    /**
	 * mp_bom_h을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBomHVO
	 * @return 조회한 mp_bom_h
	 * @exception Exception
	 */
    EgovMap selectMpBomH(EgovMap vo) throws Exception;
    
    /**
	 * mp_bom_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bom_h 목록
	 * @exception Exception
	 */
    List selectMpBomHList(MpBomHDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_bom_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bom_h 총 갯수
	 * @exception
	 */
    int selectMpBomHListTotCnt(MpBomHDefaultVO searchVO);
    
    public List selectPP0106MpBomDList(MpBomHDefaultVO searchVO) throws Exception ;
    public int selectPP0106MpBomHListTotCnt(MpBomHDefaultVO searchVO) ;
    
    public int checkDupMpBomHCnt(MpBomHDefaultVO searchVO) ;

}
