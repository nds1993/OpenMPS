package nds.tmm.common.TMCOOS70.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOOS70.vo.TMCOOS70DefaultVO;
import nds.tmm.common.TMCOOS70.vo.TMCOOS70VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOOS70DAO.java
 * @Description : TMCOOS70 DAO Class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOOS70DAO")
public class TMCOOS70DAO extends TMMPPBaseDAO {

	/**
	 * tm_deptxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOOS70VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOOS70(EgovMap vo) throws Exception {
        return (String)insert("TMCOOS70DAO.insertTMCOOS70", vo);
    }

    /**
	 * tm_deptxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOOS70VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOOS70(EgovMap vo) throws Exception {
        update("TMCOOS70DAO.updateTMCOOS70", vo);
    }

    /**
	 * tm_deptxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOOS70VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOOS70(EgovMap vo) throws Exception {
        delete("TMCOOS70DAO.deleteTMCOOS70", vo);
    }

    /**
	 * tm_deptxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOOS70VO
	 * @return 조회한 tm_deptxm
	 * @exception Exception
	 */
    public TMCOOS70VO selectTMCOOS70(EgovMap vo) throws Exception {
        return (TMCOOS70VO) select("TMCOOS70DAO.selectTMCOOS70_S", vo);
    }

    /**
	 * tm_deptxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_deptxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOOS70List(TMCOOS70DefaultVO searchVO) throws Exception {
        return list("TMCOOS70DAO.selectTMCOOS70List_D", searchVO);
    }

    /**
	 * tm_deptxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_deptxm 총 갯수
	 * @exception
	 */
    public int selectTMCOOS70ListTotCnt(TMCOOS70DefaultVO searchVO) {
        return (Integer)select("TMCOOS70DAO.selectTMCOOS70ListTotCnt_S", searchVO);
    }
    
    public String insertLogTMCOOS70(EgovMap vo) throws Exception {
        return (String)insert("TMCOOS70DAO.insertLogTMCOOS70", vo);
    }


}
