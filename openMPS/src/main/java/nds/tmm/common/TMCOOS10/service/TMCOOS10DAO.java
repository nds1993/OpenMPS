package nds.tmm.common.TMCOOS10.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOOS10.vo.TMCOOS10DefaultVO;
import nds.tmm.common.TMCOOS10.vo.TMCOOS10VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOOS10DAO.java
 * @Description : TMCOOS10 DAO Class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOOS10DAO")
public class TMCOOS10DAO extends TMMPPBaseDAO {

	/**
	 * tm_corpxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 EgovMap
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOOS10(EgovMap vo) throws Exception {
        return (String)insert("TMCOOS10DAO.insertTMCOOS10", vo);
    }

    /**
	 * tm_corpxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 EgovMap
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOOS10(EgovMap vo) throws Exception {
        update("TMCOOS10DAO.updateTMCOOS10", vo);
    }

    /**
	 * tm_corpxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 EgovMap
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOOS10(EgovMap vo) throws Exception {
        delete("TMCOOS10DAO.deleteTMCOOS10", vo);
    }

    /**
	 * tm_corpxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 EgovMap
	 * @return 조회한 tm_corpxm
	 * @exception Exception
	 */
    public TMCOOS10VO selectTMCOOS10(EgovMap vo) throws Exception {
        return (TMCOOS10VO) select("TMCOOS10DAO.selectTMCOOS10_S", vo);
    }

    /**
	 * tm_corpxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_corpxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOOS10List(TMCOOS10DefaultVO searchVO) throws Exception {
        return list("TMCOOS10DAO.selectTMCOOS10List_D", searchVO);
    }

    /**
	 * tm_corpxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_corpxm 총 갯수
	 * @exception
	 */
    public int selectTMCOOS10ListTotCnt(TMCOOS10DefaultVO searchVO) {
        return (Integer)select("TMCOOS10DAO.selectTMCOOS10ListTotCnt_S", searchVO);
    }

}
