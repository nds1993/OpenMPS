package nds.tmm.common.TMCOBD10.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOBD10.vo.TMCOBD10VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOBD10DAO.java
 * @Description : TMCOBD10 DAO Class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOBD10DAO")
public class TMCOBD10DAO extends TMMPPBaseDAO {

	/**
	 * tm_bdcaxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOBD10(EgovMap vo) throws Exception {
        return (String)insert("TMCOBD10DAO.insertTMCOBD10", vo);
    }
    
    /**
	 * tm_bdcaxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOBD10VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOBD10(EgovMap vo) throws Exception {
        update("TMCOBD10DAO.updateTMCOBD10", vo);
    }
    
    /**
	 * tm_bdcaxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOBD10VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOBD10(EgovMap vo) throws Exception {
        delete("TMCOBD10DAO.deleteTMCOBD10", vo);
    }
    
    /**
	 * tm_bdcaxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOBD10VO
	 * @return 조회한 tm_bdcaxm
	 * @exception Exception
	 */
    public TMCOBD10VO selectTMCOBD10(EgovMap vo) throws Exception {
        return (TMCOBD10VO) select("TMCOBD10DAO.selectTMCOBD10_S", vo);
    }
    
    /**
	 * tm_bdcaxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_bdcaxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOBD10List(TMCOBD10VO searchVO) throws Exception {
        return list("TMCOBD10DAO.selectTMCOBD10List_D", searchVO);
    }

    /**
	 * tm_bdcaxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_bdcaxm 총 갯수
	 * @exception
	 */
    public int selectTMCOBD10ListTotCnt(TMCOBD10VO searchVO) {
        return (Integer)select("TMCOBD10DAO.selectTMCOBD10ListTotCnt_S", searchVO);
    }

}
