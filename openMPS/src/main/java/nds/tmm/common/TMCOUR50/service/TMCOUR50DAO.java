package nds.tmm.common.TMCOUR50.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOUR50.vo.TMCOUR50DefaultVO;
import nds.tmm.common.TMCOUR50.vo.TMCOUR50VO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : TMCOUR50DAO.java
 * @Description : TMCOUR50 DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOUR50DAO")
public class TMCOUR50DAO extends TMMPPBaseDAO {

	/**
	 * tm_authxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR50VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOUR50(TMCOUR50VO vo) throws Exception {
        return (String)insert("TMCOUR50DAO.insertTMCOUR50_S", vo);
    }

    /**
	 * tm_authxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR50VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOUR50(TMCOUR50VO vo) throws Exception {
        update("TMCOUR50DAO.updateTMCOUR50_S", vo);
    }

    /**
	 * tm_authxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR50VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOUR50(Object vo) throws Exception {
        delete("TMCOUR50DAO.deleteTMCOUR50_S", vo);
    }

    /**
	 * tm_authxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR50VO
	 * @return 조회한 tm_authxm
	 * @exception Exception
	 */
    public TMCOUR50VO selectTMCOUR50(Object vo) throws Exception {
        return (TMCOUR50VO) select("TMCOUR50DAO.selectTMCOUR50_S", vo);
    }

    /**
	 * tm_authxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_authxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOUR50List(TMCOUR50DefaultVO searchVO) throws Exception {
        return list("TMCOUR50DAO.selectTMCOUR50List_D", searchVO);
    }

    /**
	 * tm_authxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_authxm 총 갯수
	 * @exception
	 */
    public int selectTMCOUR50ListTotCnt(TMCOUR50DefaultVO searchVO) {
        return (Integer)select("TMCOUR50DAO.selectTMCOUR50ListTotCnt_S", searchVO);
    }

    public int executeBatchInsert(List<Object> vos) throws Exception{
    	return executeBatch(vos, "TMCOUR50DAO.insertTMCOUR50_S");
    }
}
