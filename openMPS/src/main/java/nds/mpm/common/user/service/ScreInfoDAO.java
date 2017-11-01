package nds.mpm.common.user.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.common.user.vo.ScreInfoVO;
import nds.mpm.common.user.vo.ScreInfoDefaultVO;

/**
 * @Class Name : ScreInfoDAO.java
 * @Description : ScreInfo DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.08
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("screInfoDAO")
public class ScreInfoDAO extends EgovAbstractDAO {

	/**
	 * scre_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ScreInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertScreInfo(ScreInfoVO vo) throws Exception {
        return (String)insert("screInfoDAO.insertScreInfo_S", vo);
    }

    /**
	 * scre_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ScreInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateScreInfo(ScreInfoVO vo) throws Exception {
        update("screInfoDAO.updateScreInfo_S", vo);
    }

    /**
	 * scre_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ScreInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteScreInfo(ScreInfoVO vo) throws Exception {
        delete("screInfoDAO.deleteScreInfo_S", vo);
    }

    /**
	 * scre_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ScreInfoVO
	 * @return 조회한 scre_info
	 * @exception Exception
	 */
    public ScreInfoVO selectScreInfo(ScreInfoVO vo) throws Exception {
        return (ScreInfoVO) select("screInfoDAO.selectScreInfo_S", vo);
    }

    /**
	 * scre_info 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return scre_info 목록
	 * @exception Exception
	 */
    public List<?> selectScreInfoList(ScreInfoDefaultVO searchVO) throws Exception {
        return list("screInfoDAO.selectScreInfoList_D", searchVO);
    }

    /**
	 * scre_info 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return scre_info 총 갯수
	 * @exception
	 */
    public int selectScreInfoListTotCnt(ScreInfoDefaultVO searchVO) {
        return (Integer)select("screInfoDAO.selectScreInfoListTotCnt_S", searchVO);
    }

}
