package nds.tmm.common.TMCOCD10.service;

import java.util.List;

import nds.core.operation.surveytmpl.service.SurveyTemplateVO;
import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10DETAILDefaultVO;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10DETAILVO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : TMCOCD10DETAILDAO.java
 * @Description : TMCOCD10DETAIL DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOCD10DETAILDAO")
public class TMCOCD10DETAILDAO extends TMMPPBaseDAO {

	/**
	 * tm_codexd을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOCD10DETAILVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOCD10DETAIL(TMCOCD10DETAILVO vo) throws Exception {
        return (String)insert("TMCOCD10DETAILDAO.insertTMCOCD10DETAIL_S", vo);
    }

    /**
	 * tm_codexd을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOCD10DETAILVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOCD10DETAIL(TMCOCD10DETAILVO vo) throws Exception {
        update("TMCOCD10DETAILDAO.updateTMCOCD10DETAIL_S", vo);
    }

    /**
	 * tm_codexd을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOCD10DETAILVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOCD10DETAIL(TMCOCD10DETAILVO vo) throws Exception {
        delete("TMCOCD10DETAILDAO.deleteTMCOCD10DETAIL_S", vo);
    }

    /**
	 * tm_codexd을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOCD10DETAILVO
	 * @return 조회한 tm_codexd
	 * @exception Exception
	 */
    public TMCOCD10DETAILVO selectTMCOCD10DETAIL(Object vo) throws Exception {
        return (TMCOCD10DETAILVO) select("TMCOCD10DETAILDAO.selectTMCOCD10DETAIL_S", vo);
    }

    /**
	 * tm_codexd 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_codexd 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOCD10DETAILList(TMCOCD10DETAILDefaultVO searchVO) throws Exception {
        return list("TMCOCD10DETAILDAO.selectTMCOCD10DETAILList_D", searchVO);
    }

    /**
	 * tm_codexd 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_codexd 총 갯수
	 * @exception
	 */
    public int selectTMCOCD10DETAILListTotCnt(TMCOCD10DETAILDefaultVO searchVO) {
        return (Integer)select("TMCOCD10DETAILDAO.selectTMCOCD10DETAILListTotCnt_S", searchVO);
    }
    
    public void functionTMCOCD10DETAIL(TMCOCD10DETAILVO vo) throws Exception{
        selectByPk("TMCOCD10DETAILDAO.functionTMCOCD10DETAIL", vo);
    }
    
    public int executeBatchInsert(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOCD10DAO.insertTMCOCD10DETAIL_S");
    }
    public int executeBatchUpdate(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOCD10DAO.updateTMCOCD10DETAIL_S");
    }
    public int executeBatchDelete(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOCD10DAO.deleteTMCOCD10DETAIL_S");
    }
    public List<?> selectTMCOCD10DETAILSDDCList_D(TMCOCD10DETAILDefaultVO searchVO) throws Exception {
        return list("TMCOCD10DETAILDAO.selectTMCOCD10DETAILSDDCList_D", searchVO);
    }
    public List<?> selectTMCOCD10DETAILCodeList_D(TMCOCD10DETAILDefaultVO searchVO) throws Exception {
        return list("TMCOCD10DETAILDAO.selectTMCOCD10DETAILCodeList_D", searchVO);
    }
}
