package nds.mpm.prod.PP0101.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.prod.PP0101.vo.TmPlatxmDefaultVO;
import nds.mpm.prod.PP0101.vo.TmPlatxmVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TmPlatxmDAO.java
 * @Description : TmPlatxm DAO Class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("tmPlatxmDAO")
public class TmPlatxmDAO extends TMMPPBaseDAO {

	/**
	 * tm_platxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TmPlatxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTmPlatxm(EgovMap vo) throws Exception {
        return (String)insert("tmPlatxmDAO.insertTmPlatxm", vo);
    }

    /**
	 * tm_platxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TmPlatxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTmPlatxm(EgovMap vo) throws Exception {
        update("tmPlatxmDAO.updateTmPlatxm", vo);
    }

    /**
	 * tm_platxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TmPlatxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTmPlatxm(EgovMap vo) throws Exception {
        delete("tmPlatxmDAO.deleteTmPlatxm", vo);
    }

    /**
	 * tm_platxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TmPlatxmVO
	 * @return 조회한 tm_platxm
	 * @exception Exception
	 */
    public TmPlatxmVO selectTmPlatxm(EgovMap vo) throws Exception {
        return (TmPlatxmVO) select("tmPlatxmDAO.selectTmPlatxm_S", vo);
    }

    /**
	 * tm_platxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_platxm 목록
	 * @exception Exception
	 */
    public List<?> selectTmPlatxmList(TmPlatxmDefaultVO searchVO) throws Exception {
        return list("tmPlatxmDAO.selectTmPlatxmList_D", searchVO);
    }

    /**
	 * tm_platxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_platxm 총 갯수
	 * @exception
	 */
    public int selectTmPlatxmListTotCnt(TmPlatxmDefaultVO searchVO) {
        return (Integer)select("tmPlatxmDAO.selectTmPlatxmListTotCnt_S", searchVO);
    }
    
    public int checkDupTmPlatxm_S(TmPlatxmDefaultVO searchVO) {
        return (Integer)select("tmPlatxmDAO.checkDupTmPlatxm_S", searchVO);
    }
    
    public List<?> selectGrupTmPlatxmList(TmPlatxmDefaultVO searchVO) throws Exception {
        return list("tmPlatxmDAO.selectGrupTmPlatxmList_D", searchVO);
    }
}
