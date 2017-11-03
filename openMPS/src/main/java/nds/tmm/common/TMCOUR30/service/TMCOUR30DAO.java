package nds.tmm.common.TMCOUR30.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOUR30.vo.TMCOUR30DefaultVO;
import nds.tmm.common.TMCOUR30.vo.TMCOUR30VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : TMCOUR30DAO.java
 * @Description : TMCOUR30 DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOUR30DAO")
public class TMCOUR30DAO extends TMMPPBaseDAO {

	/**
	 * tm_menuxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR30VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOUR30(TMCOUR30VO vo) throws Exception {
        return (String)insert("TMCOUR30DAO.insertTMCOUR30_S", vo);
    }

    /**
	 * tm_menuxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR30VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOUR30(TMCOUR30VO vo) throws Exception {
        update("TMCOUR30DAO.updateTMCOUR30_S", vo);
    }

    /**
	 * tm_menuxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR30VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOUR30(TMCOUR30VO vo) throws Exception {
        delete("TMCOUR30DAO.deleteTMCOUR30_S", vo);
    }

    /**
	 * tm_menuxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR30VO
	 * @return 조회한 tm_menuxm
	 * @exception Exception
	 */
    public TMCOUR30VO selectTMCOUR30(Object vo) throws Exception {
        return (TMCOUR30VO) select("TMCOUR30DAO.selectTMCOUR30_S", vo);
    }

    /**
	 * tm_menuxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_menuxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOUR30List(TMCOUR30DefaultVO searchVO) throws Exception {
        return list("TMCOUR30DAO.selectTMCOUR30List_D", searchVO);
    }

    /**
	 * tm_menuxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_menuxm 총 갯수
	 * @exception
	 */
    public int selectTMCOUR30ListTotCnt(TMCOUR30DefaultVO searchVO) {
        return (Integer)select("TMCOUR30DAO.selectTMCOUR30ListTotCnt_S", searchVO);
    }
    
    /**
	 * Tree( 계층구조 ) 뷰용 데이터 리턴. 
	 * 부모1
	 * 	자식1-1
	 * 부모2
	 * 	자식2-1
	 * 	자식2-2
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_menuxm 목록
	 * @exception Exception
	 * @exception
	 */
    public List<?> selectTMCOUR30TreeList(TMCOUR30DefaultVO searchVO) throws Exception {
        return list("TMCOUR30DAO.selectTMCOUR30TreeList", searchVO);
    }

    public int executeBatchInsert(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOUR30DAO.insertTMCOUR30_S");
    }
    public int executeBatchUpdate(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOUR30DAO.updateTMCOUR30_S");
    }
    public int executeBatchDelete(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOUR30DAO.deleteTMCOUR30_S");
    }
}
