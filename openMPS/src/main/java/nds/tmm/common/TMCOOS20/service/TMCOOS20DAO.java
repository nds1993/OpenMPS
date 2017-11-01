package nds.tmm.common.TMCOOS20.service;

import java.util.List;

import nds.tmm.common.TMCOOS20.vo.TMCOOS20DefaultVO;
import nds.tmm.common.TMCOOS20.vo.TMCOOS20VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : TMCOOS20DAO.java
 * @Description : TMCOOS20 DAO Class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOOS20DAO")
public class TMCOOS20DAO extends EgovAbstractDAO {

	/**
	 * tm_platxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOOS20VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOOS20(TMCOOS20VO vo) throws Exception {
        return (String)insert("TMCOOS20DAO.insertTMCOOS20_S", vo);
    }

    /**
	 * tm_platxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOOS20VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOOS20(TMCOOS20VO vo) throws Exception {
        update("TMCOOS20DAO.updateTMCOOS20_S", vo);
    }

    /**
	 * tm_platxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOOS20VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOOS20(TMCOOS20VO vo) throws Exception {
        delete("TMCOOS20DAO.deleteTMCOOS20_S", vo);
    }

    /**
	 * tm_platxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOOS20VO
	 * @return 조회한 tm_platxm
	 * @exception Exception
	 */
    public TMCOOS20VO selectTMCOOS20(TMCOOS20VO vo) throws Exception {
        return (TMCOOS20VO) select("TMCOOS20DAO.selectTMCOOS20_S", vo);
    }

    /**
	 * tm_platxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_platxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOOS20List(TMCOOS20DefaultVO searchVO) throws Exception {
        return list("TMCOOS20DAO.selectTMCOOS20List_D", searchVO);
    }

    /**
	 * tm_platxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_platxm 총 갯수
	 * @exception
	 */
    public int selectTMCOOS20ListTotCnt(TMCOOS20DefaultVO searchVO) {
        return (Integer)select("TMCOOS20DAO.selectTMCOOS20ListTotCnt_S", searchVO);
    }

}
