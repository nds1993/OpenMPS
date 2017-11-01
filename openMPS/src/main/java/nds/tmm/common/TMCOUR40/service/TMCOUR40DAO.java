package nds.tmm.common.TMCOUR40.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOUR40.vo.TMCOUR40DefaultVO;
import nds.tmm.common.TMCOUR40.vo.TMCOUR40VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : TMCOUR40DAO.java
 * @Description : TMCOUR40 DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOUR40DAO")
public class TMCOUR40DAO extends TMMPPBaseDAO {

	/**
	 * tm_grupxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR40VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOUR40(TMCOUR40VO vo) throws Exception {
        return (String)insert("TMCOUR40DAO.insertTMCOUR40_S", vo);
    }

    /**
	 * tm_grupxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR40VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOUR40(TMCOUR40VO vo) throws Exception {
        update("TMCOUR40DAO.updateTMCOUR40_S", vo);
    }

    /**
	 * tm_grupxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR40VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOUR40(TMCOUR40VO vo) throws Exception {
        delete("TMCOUR40DAO.deleteTMCOUR40_S", vo);
    }

    /**
	 * tm_grupxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR40VO
	 * @return 조회한 tm_grupxm
	 * @exception Exception
	 */
    public TMCOUR40VO selectTMCOUR40(TMCOUR40VO vo) throws Exception {
        return (TMCOUR40VO) select("TMCOUR40DAO.selectTMCOUR40_S", vo);
    }

    /**
	 * tm_grupxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_grupxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOUR40List(TMCOUR40DefaultVO searchVO) throws Exception {
        return list("TMCOUR40DAO.selectTMCOUR40List_D", searchVO);
    }

    /**
	 * tm_grupxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_grupxm 총 갯수
	 * @exception
	 */
    public int selectTMCOUR40ListTotCnt(TMCOUR40DefaultVO searchVO) {
        return (Integer)select("TMCOUR40DAO.selectTMCOUR40ListTotCnt_S", searchVO);
    }
    
    public int executeBatchInsert(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOUR40DAO.insertTMCOUR40_S");
    }
    public int executeBatchUpdate(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOUR40DAO.updateTMCOUR40_S");
    }
    public int executeBatchDelete(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOUR40DAO.deleteTMCOUR40_S");
    }
}
