package nds.mpm.common.user.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.common.user.vo.ScreUserVO;
import nds.mpm.common.user.vo.ScreUserDefaultVO;

/**
 * @Class Name : ScreUserDAO.java
 * @Description : ScreUser DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("screUserDAO")
public class ScreUserDAO extends EgovAbstractDAO {

	/**
	 * scre_user을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ScreUserVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertScreUser(ScreUserVO vo) throws Exception {
        return (String)insert("screUserDAO.insertScreUser_S", vo);
    }

    /**
	 * scre_user을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ScreUserVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateScreUser(ScreUserVO vo) throws Exception {
        update("screUserDAO.updateScreUser_S", vo);
    }

    /**
	 * scre_user을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ScreUserVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteScreUser(ScreUserVO vo) throws Exception {
        delete("screUserDAO.deleteScreUser_S", vo);
    }

    /**
	 * scre_user을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ScreUserVO
	 * @return 조회한 scre_user
	 * @exception Exception
	 */
    public ScreUserVO selectScreUser(ScreUserVO vo) throws Exception {
        return (ScreUserVO) select("screUserDAO.selectScreUser_S", vo);
    }

    /**
	 * scre_user 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return scre_user 목록
	 * @exception Exception
	 */
    public List<?> selectScreUserList(ScreUserDefaultVO searchVO) throws Exception {
        return list("screUserDAO.selectScreUserList_D", searchVO);
    }

    /**
	 * scre_user 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return scre_user 총 갯수
	 * @exception
	 */
    public int selectScreUserListTotCnt(ScreUserDefaultVO searchVO) {
        return (Integer)select("screUserDAO.selectScreUserListTotCnt_S", searchVO);
    }

}
