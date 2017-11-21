package nds.tmm.common.TMCOCD10.service;

import java.util.List;

import nds.core.operation.holiday.service.HolidayVO;
import nds.core.operation.surveytmpl.service.SurveyTemplateVO;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10DETAILDefaultVO;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10DETAILVO;

/**
 * @Class Name : TMCOCD10DETAILService.java
 * @Description : TMCOCD10DETAIL Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TMCOCD10DETAILService {
	
	/**
	 * tm_codexd을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOCD10DETAILVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    int insertTMCOCD10DETAIL(List<TMCOCD10DETAILVO> vo, String groupCode) throws Exception;
    
    /**
	 * tm_codexd을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOCD10DETAILVO
	 * @return void형
	 * @exception Exception
	 */
    int updateTMCOCD10DETAIL(List<Object> vo) throws Exception;
    
    /**
	 * tm_codexd을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOCD10DETAILVO
	 * @return void형 
	 * @exception Exception
	 */
    int deleteTMCOCD10DETAIL(List<Object> vo) throws Exception;
    
    /**
	 * tm_codexd을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOCD10DETAILVO
	 * @return 조회한 tm_codexd
	 * @exception Exception
	 */
    TMCOCD10DETAILVO selectTMCOCD10DETAIL(Object vo) throws Exception;
    
    /**
	 * tm_codexd 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_codexd 목록
	 * @exception Exception
	 */
    List selectTMCOCD10DETAILList(TMCOCD10DETAILDefaultVO searchVO) throws Exception;
    
    /**
	 * tm_codexd 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_codexd 총 갯수
	 * @exception
	 */
    int selectTMCOCD10DETAILListTotCnt(TMCOCD10DETAILDefaultVO searchVO);
    
    /**
     * WMS Function 실행한다.
     * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 
	 * @exception
     */
    void functionTMCOCD10DETAIL(TMCOCD10DETAILVO vo) throws Exception;
    
    public List<?> selectTMCOCD10DETAILSDDCList_D(TMCOCD10DETAILDefaultVO searchVO) throws Exception ;
    public List<?> selectTMCOCD10DETAILCodeList_D(TMCOCD10DETAILDefaultVO searchVO) throws Exception ;

}
