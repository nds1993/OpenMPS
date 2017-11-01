package nds.mpm.prod.PP0103.service;

import java.util.List;
import nds.mpm.prod.PP0103.vo.MpItemPlntgMDefaultVO;
import nds.mpm.prod.PP0103.vo.MpItemPlntgMVO;

/**
 * @Class Name : MpItemPlntgMService.java
 * @Description : MpItemPlntgM Business class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpItemPlntgMService {
	
	/**
	 * mp_item_plntg_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpItemPlntgMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpItemPlntgM(MpItemPlntgMVO vo) throws Exception;
    
    /**
	 * mp_item_plntg_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpItemPlntgMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpItemPlntgM(MpItemPlntgMVO vo) throws Exception;
    
    /**
	 * mp_item_plntg_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpItemPlntgMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpItemPlntgM(MpItemPlntgMVO vo) throws Exception;
    
    /**
	 * mp_item_plntg_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpItemPlntgMVO
	 * @return 조회한 mp_item_plntg_m
	 * @exception Exception
	 */
    MpItemPlntgMVO selectMpItemPlntgM(MpItemPlntgMVO vo) throws Exception;
    
    /**
	 * mp_item_plntg_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_item_plntg_m 목록
	 * @exception Exception
	 */
    List selectMpItemPlntgMList(MpItemPlntgMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_item_plntg_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_item_plntg_m 총 갯수
	 * @exception
	 */
    int selectMpItemPlntgMListTotCnt(MpItemPlntgMDefaultVO searchVO);
    
}
