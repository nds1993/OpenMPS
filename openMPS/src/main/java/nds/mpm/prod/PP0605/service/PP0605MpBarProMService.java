package nds.mpm.prod.PP0605.service;

import java.util.List;
import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;
import nds.mpm.prod.PP0504.vo.MpBarProMVO;

/**
 * @Class Name : MpBarProMService.java
 * @Description : MpBarProM Business class
 * @Modification Information
 *
 * @author 44444
 * @since 44444
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PP0605MpBarProMService {
	
	/**
	 * mp_bar_pro_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBarProMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpBarProM(MpBarProMVO vo) throws Exception;
    
    /**
	 * mp_bar_pro_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBarProMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpBarProM(MpBarProMVO vo) throws Exception;
    
    /**
	 * mp_bar_pro_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBarProMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpBarProM(MpBarProMVO vo) throws Exception;
    
    /**
	 * mp_bar_pro_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBarProMVO
	 * @return 조회한 mp_bar_pro_m
	 * @exception Exception
	 */
    MpBarProMVO selectMpBarProM(MpBarProMVO vo) throws Exception;
    
    /**
	 * mp_bar_pro_m 목록을 조회한다(헤드).
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    List selectMpBarProMListHead(MpBarProMVO searchVO) throws Exception;
    
    /**
	 * mp_bar_pro_m 목록을 조회한다(디테일).
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    List selectMpBarProMList(MpBarProMVO searchVO) throws Exception;
    
    /**
	 * mp_bar_pro_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bar_pro_m 총 갯수
	 * @exception
	 */
    int selectMpBarProMListTotCnt(MpBarProMDefaultVO searchVO);
    
}
