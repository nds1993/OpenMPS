package nds.tmm.common.TMCOUR10.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOUR10.vo.TMCOUR10DefaultVO;
import nds.tmm.common.TMCOUR10.vo.TMCOUR10VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOUR10DAO.java
 * @Description : TMCOUR10 DAO Class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOUR10DAO")
public class TMCOUR10DAO extends TMMPPBaseDAO {

	/**
	 * tm_userxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOUR10(EgovMap vo) throws Exception {
        return (String)insert("TMCOUR10DAO.insertTMCOUR10", vo);
    }

    /**
	 * tm_userxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR10VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOUR10(EgovMap vo) throws Exception {
        update("TMCOUR10DAO.updateTMCOUR10", vo);
    }

    /**
	 * tm_userxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR10VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOUR10(EgovMap vo) throws Exception {
        delete("TMCOUR10DAO.deleteTMCOUR10", vo);
    }
    
    /**
     * tm_userxm 비밀번호를 변경한다.
     * @param vo - 변경할 정보가 담긴 TMCOUR10VO
     * @return void형 
     * @exception Exception
     */
    public void updateUserPass(TMCOUR10VO vo) throws Exception {
    	delete("TMCOUR10DAO.updateUserPass", vo);
    }

    /**
	 * tm_userxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR10VO
	 * @return 조회한 tm_userxm
	 * @exception Exception
	 */
    public TMCOUR10VO selectTMCOUR10(EgovMap vo) throws Exception {
        return (TMCOUR10VO) select("TMCOUR10DAO.selectTMCOUR10_S", vo);
    }

    /**
	 * tm_userxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_userxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOUR10List(TMCOUR10DefaultVO searchVO) throws Exception {
        return list("TMCOUR10DAO.selectTMCOUR10List_D", searchVO);
    }

    /**
	 * tm_userxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_userxm 총 갯수
	 * @exception
	 */
    public int selectTMCOUR10ListTotCnt(TMCOUR10DefaultVO searchVO) {
        return (Integer)select("TMCOUR10DAO.selectTMCOUR10ListTotCnt_S", searchVO);
    }

}
