package nds.mpm.sales.SD0801.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0801.vo.MpClosingDateDefaultVO;
import nds.mpm.sales.SD0801.vo.MpClosingDateVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpClosingDateService.java
 * @Description : MpClosingDate Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpClosingDateService {
	
	/**
	 * mp_closing_date을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpClosingDateVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    ResultEx insertMpClosingDate(List<EgovMap> vo) throws Exception;
    
    /**
	 * mp_closing_date을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpClosingDateVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpClosingDate(MpClosingDateVO vo) throws Exception;
    
    /**
	 * mp_closing_date을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpClosingDateVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpClosingDate(MpClosingDateVO vo) throws Exception;
    
    /**
	 * mp_closing_date을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpClosingDateVO
	 * @return 조회한 mp_closing_date
	 * @exception Exception
	 */
    MpClosingDateVO selectMpClosingDate(MpClosingDateVO vo) throws Exception;
    
    /**
	 * mp_closing_date 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_closing_date 목록
	 * @exception Exception
	 */
    List selectMpClosingDateList(MpClosingDateDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_closing_date 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_closing_date 총 갯수
	 * @exception
	 */
    int selectMpClosingDateListTotCnt(MpClosingDateDefaultVO searchVO);
    
}
