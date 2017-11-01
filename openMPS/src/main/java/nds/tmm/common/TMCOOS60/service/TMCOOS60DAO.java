package nds.tmm.common.TMCOOS60.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOOS60.vo.TMCOOS60DefaultVO;
import nds.tmm.common.TMCOOS60.vo.TMCOOS60VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOOS60DAO.java
 * @Description : TMCOOS60 DAO Class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.06
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOOS60DAO")
public class TMCOOS60DAO extends TMMPPBaseDAO {

	/**
	 * tm_teamxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOOS60VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOOS60(EgovMap vo) throws Exception {
        return (String)insert("TMCOOS60DAO.insertTMCOOS60", vo);
    }

    /**
	 * tm_teamxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOOS60VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOOS60(EgovMap vo) throws Exception {
        update("TMCOOS60DAO.updateTMCOOS60", vo);
    }

    /**
	 * tm_teamxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOOS60VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOOS60(EgovMap vo) throws Exception {
        delete("TMCOOS60DAO.deleteTMCOOS60", vo);
    }

    /**
	 * tm_teamxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOOS60VO
	 * @return 조회한 tm_teamxm
	 * @exception Exception
	 */
    public TMCOOS60VO selectTMCOOS60(EgovMap vo) throws Exception {
        return (TMCOOS60VO) select("TMCOOS60DAO.selectTMCOOS60_S", vo);
    }

    /**
	 * tm_teamxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_teamxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOOS60List(TMCOOS60DefaultVO searchVO) throws Exception {
        return list("TMCOOS60DAO.selectTMCOOS60List_D", searchVO);
    }

    /**
	 * tm_teamxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_teamxm 총 갯수
	 * @exception
	 */
    public int selectTMCOOS60ListTotCnt(TMCOOS60DefaultVO searchVO) {
        return (Integer)select("TMCOOS60DAO.selectTMCOOS60ListTotCnt_S", searchVO);
    }
    
    public String insertLogTMCOOS60(EgovMap vo) throws Exception {
        return (String)insert("TMCOOS60DAO.insertLogTMCOOS60", vo);
    }

}
