package nds.tmm.common.TMCOCD10.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10DefaultVO;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10VO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : TMCOCD10DAO.java
 * @Description : TMCOCD10 DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOCD10DAO")
public class TMCOCD10DAO extends TMMPPBaseDAO {

	/**
	 * tm_codexm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOCD10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOCD10(TMCOCD10VO vo) throws Exception {
        return (String)insert("TMCOCD10DAO.insertTMCOCD10_S", vo);
    }

    /**
	 * tm_codexm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOCD10VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOCD10(TMCOCD10VO vo) throws Exception {
        update("TMCOCD10DAO.updateTMCOCD10_S", vo);
    }

    /**
	 * tm_codexm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOCD10VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOCD10(TMCOCD10VO vo) throws Exception {
        delete("TMCOCD10DAO.deleteTMCOCD10_S", vo);
    }

    /**
	 * tm_codexm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOCD10VO
	 * @return 조회한 tm_codexm
	 * @exception Exception
	 */
    public TMCOCD10VO selectTMCOCD10(Object vo) throws Exception {
        return (TMCOCD10VO) select("TMCOCD10DAO.selectTMCOCD10_S", vo);
    }

    /**
	 * tm_codexm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_codexm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOCD10List(TMCOCD10DefaultVO searchVO) throws Exception {
        return list("TMCOCD10DAO.selectTMCOCD10List_D", searchVO);
    }

    /**
	 * tm_codexm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_codexm 총 갯수
	 * @exception
	 */
    public int selectTMCOCD10ListTotCnt(TMCOCD10DefaultVO searchVO) {
        return (Integer)select("TMCOCD10DAO.selectTMCOCD10ListTotCnt_S", searchVO);
    }
    
    public int executeBatchInsert(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOCD10DAO.insertTMCOCD10_S");
    }
    public int executeBatchUpdate(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOCD10DAO.updateTMCOCD10_S");
    }
    public int executeBatchDelete(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOCD10DAO.deleteTMCOCD10_S");
    }
}
