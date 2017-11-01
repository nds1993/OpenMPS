package nds.mpm.prod.PP0105.service;

import java.util.List;

import nds.mpm.prod.PP0105.vo.MpBomDDefaultVO;
import nds.mpm.prod.PP0105.vo.MpBomDVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpBomDService.java
 * @Description : MpBomD Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpBomDService {
	
	/**
	 * mp_bom_d을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBomDVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpBomD(EgovMap vo) throws Exception;
    
    /**
	 * mp_bom_d을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBomDVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpBomD(EgovMap vo) throws Exception;
    
    /**
	 * mp_bom_d을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBomDVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpBomD(EgovMap vo) throws Exception;
    
    /**
	 * mp_bom_d을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBomDVO
	 * @return 조회한 mp_bom_d
	 * @exception Exception
	 */
    MpBomDVO selectMpBomD(EgovMap vo) throws Exception;
    
    /**
	 * mp_bom_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bom_d 목록
	 * @exception Exception
	 */
    List selectMpBomDList(MpBomDDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_bom_d 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bom_d 총 갯수
	 * @exception
	 */
    int selectMpBomDListTotCnt(MpBomDDefaultVO searchVO);
    
}
