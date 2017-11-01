package nds.tmm.common.TMCOOS50.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOOS50.vo.TMCOOS50DefaultVO;
import nds.tmm.common.TMCOOS50.vo.TMCOOS50VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOOS50DAO.java
 * @Description : TMCOOS50 DAO Class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.06
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOOS50DAO")
public class TMCOOS50DAO extends TMMPPBaseDAO {

	/**
	 * tm_orguxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOOS50VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOOS50(EgovMap vo) throws Exception {
        return (String)insert("TMCOOS50DAO.insertTMCOOS50", vo);
    }

    /**
	 * tm_orguxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOOS50VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOOS50(EgovMap vo) throws Exception {
        update("TMCOOS50DAO.updateTMCOOS50", vo);
    }

    /**
	 * tm_orguxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOOS50VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOOS50(EgovMap vo) throws Exception {
        delete("TMCOOS50DAO.deleteTMCOOS50", vo);
    }

    /**
	 * tm_orguxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOOS50VO
	 * @return 조회한 tm_orguxm
	 * @exception Exception
	 */
    public TMCOOS50VO selectTMCOOS50(EgovMap vo) throws Exception {
        return (TMCOOS50VO) select("TMCOOS50DAO.selectTMCOOS50_S", vo);
    }

    /**
	 * tm_orguxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_orguxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOOS50List(TMCOOS50DefaultVO searchVO) throws Exception {
        return list("TMCOOS50DAO.selectTMCOOS50List_D", searchVO);
    }

    /**
	 * tm_orguxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_orguxm 총 갯수
	 * @exception
	 */
    public int selectTMCOOS50ListTotCnt(TMCOOS50DefaultVO searchVO) {
        return (Integer)select("TMCOOS50DAO.selectTMCOOS50ListTotCnt_S", searchVO);
    }
    
    public int checkDupTMCOOS50_S(TMCOOS50DefaultVO searchVO) {
        return (Integer)select("TMCOOS50DAO.checkDupTMCOOS50_S", searchVO);
    }

}
