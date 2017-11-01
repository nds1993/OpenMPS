package nds.mpm.prod.PP0604.service;

import java.util.List;
import nds.mpm.prod.PP0604.vo.MpBarinProMDefaultVO;
import nds.mpm.prod.PP0604.vo.MpBarinProMVO;

/**
 * @Class Name : MpBarinProMService.java
 * @Description : MpBarinProM Business class
 * @Modification Information
 *
 * @author 속라벨 발행현황 조회
 * @since 속라벨 발행현황 조회
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpBarinProMService {
	
	/**
	 * mp_barin_pro_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBarinProMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpBarinProM(MpBarinProMVO vo) throws Exception;
    
    /**
	 * mp_barin_pro_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBarinProMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpBarinProM(MpBarinProMVO vo) throws Exception;
    
    /**
	 * mp_barin_pro_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBarinProMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpBarinProM(MpBarinProMVO vo) throws Exception;
    
    /**
	 * mp_barin_pro_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBarinProMVO
	 * @return 조회한 mp_barin_pro_m
	 * @exception Exception
	 */
    MpBarinProMVO selectMpBarinProM(MpBarinProMVO vo) throws Exception;
    
    /**
	 * mp_barin_pro_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_barin_pro_m 목록
	 * @exception Exception
	 */
    List selectMpBarinProMList(MpBarinProMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_barin_pro_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_barin_pro_m 총 갯수
	 * @exception
	 */
    int selectMpBarinProMListTotCnt(MpBarinProMDefaultVO searchVO);
    
}
