package nds.tmm.common.TMCOUR60.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOUR60.vo.TMCOUR60DefaultVO;
import nds.tmm.common.TMCOUR60.vo.TMCOUR60VO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : TMCOUR60DAO.java
 * @Description : TMCOUR60 DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOUR60DAO")
public class TMCOUR60DAO extends TMMPPBaseDAO {

	/**
	 * tm_grusrx을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR60VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOUR60(TMCOUR60VO vo) throws Exception {
        return (String)insert("TMCOUR60DAO.insertTMCOUR60_S", vo);
    }

    /**
	 * tm_grusrx을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR60VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOUR60(TMCOUR60VO vo) throws Exception {
        update("TMCOUR60DAO.updateTMCOUR60_S", vo);
    }

    /**
	 * tm_grusrx을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR60VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOUR60(Object vo) throws Exception {
        delete("TMCOUR60DAO.deleteTMCOUR60_S", vo);
    }

    /**
	 * tm_grusrx을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR60VO
	 * @return 조회한 tm_grusrx
	 * @exception Exception
	 */
    public TMCOUR60VO selectTMCOUR60(Object vo) throws Exception {
        return (TMCOUR60VO) select("TMCOUR60DAO.selectTMCOUR60_S", vo);
    }

    /**
	 * tm_grusrx 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_grusrx 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOUR60List(TMCOUR60DefaultVO searchVO) throws Exception {
        return list("TMCOUR60DAO.selectTMCOUR60List_D", searchVO);
    }

    /**
	 * tm_grusrx 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_grusrx 총 갯수
	 * @exception
	 */
    public int selectTMCOUR60ListTotCnt(TMCOUR60DefaultVO searchVO) {
        return (Integer)select("TMCOUR60DAO.selectTMCOUR60ListTotCnt_S", searchVO);
    }
    
    /**
   	 * tm_grusrx 목록을 조회한다.
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return tm_grusrx 목록
   	 * @exception Exception
   	 */
    public List<?> selectTMCOUR60UserList_D(TMCOUR60DefaultVO searchVO) throws Exception {
       return list("TMCOUR60DAO.selectTMCOUR60UserList_D", searchVO);
    }

    public int executeBatchInsert(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOUR60DAO.insertTMCOUR60_S");
    }
    public int executeBatchUpdate(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOUR60DAO.updateTMCOUR60_S");
    }
    public int executeBatchDelete(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOUR60DAO.deleteTMCOUR60_S");
    }
}
