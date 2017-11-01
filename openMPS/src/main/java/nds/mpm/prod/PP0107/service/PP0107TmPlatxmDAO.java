package nds.mpm.prod.PP0107.service;

import java.util.List;

import nds.mpm.prod.PP0101.vo.TmPlatxmDefaultVO;
import nds.mpm.prod.PP0101.vo.TmPlatxmVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TmPlatxmDAO.java
 * @Description : TmPlatxm DAO Class
 * @Modification Information
 *
 * @author 11
 * @since 11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PP0107TmPlatxmDAO")
public class PP0107TmPlatxmDAO extends EgovAbstractDAO {

	/**
	 * tm_platxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TmPlatxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTmPlatWarhxm(EgovMap vo) throws Exception {
        return (String)insert("PP0107TmPlatxmDAO.insertTmPlatxm", vo);
    }

    /**
	 * tm_platxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TmPlatxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTmPlatxm(EgovMap vo) throws Exception {
        update("PP0107TmPlatxmDAO.updateTmPlatxm", vo);
    }

    /**
	 * tm_platxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TmPlatxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTmPlatxm(EgovMap vo) throws Exception {
        delete("PP0107TmPlatxmDAO.deleteTmPlatxm", vo);
    }

    /**
	 * tm_platxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TmPlatxmVO
	 * @return 조회한 tm_platxm
	 * @exception Exception
	 */
    public TmPlatxmVO selectTmPlatxm(TmPlatxmVO vo) throws Exception {
        return (TmPlatxmVO) select("PP0107TmPlatxmDAO.selectTmPlatxm_S", vo);
    }

    /**
	 * tm_platxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_platxm 목록
	 * @exception Exception
	 */
    public List<?> selectTmPlatxmList(TmPlatxmDefaultVO searchVO) throws Exception {
        return list("PP0107TmPlatxmDAO.selectTmPlatxmList_D", searchVO);
    }

    /**
	 * tm_platxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_platxm 총 갯수
	 * @exception
	 */
    public int selectTmPlatxmListTotCnt(TmPlatxmDefaultVO searchVO) {
        return (Integer)select("PP0107TmPlatxmDAO.selectTmPlatxmListTotCnt_S", searchVO);
    }
    
    /**
	 * tm_platxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_platxm 목록
	 * @exception Exception
	 */
    public List<?> selectTmPlatxmLeftList(TmPlatxmDefaultVO searchVO) throws Exception {
        return list("PP0107TmPlatxmDAO.selectTmPlatxmLeftList_D", searchVO);
    }

    /**
	 * tm_platxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_platxm 총 갯수
	 * @exception
	 */
    public int selectTmPlatxmLeftListTotCnt(TmPlatxmDefaultVO searchVO) {
        return (Integer)select("PP0107TmPlatxmDAO.selectTmPlatxmLeftListTotCnt_S", searchVO);
    }
    
    /**
     * wh_code duplicate check
     * 
     * */
    public int checkTmPlatxmWhcode(EgovMap searchVO) {
        return (Integer)select("PP0107TmPlatxmDAO.checkTmPlatxmWhcode", searchVO);
    }
    

}
