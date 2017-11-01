package nds.mpm.prod.base.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.prod.base.vo.MpIbarxmVO;
import nds.mpm.prod.base.vo.MpIbarxmDefaultVO;

/**
 * @Class Name : MpIbarxmDAO.java
 * @Description : MpIbarxm DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpIbarxmDAO")
public class MpIbarxmDAO extends EgovAbstractDAO {

	/**
	 * MP_IBARXM을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpIbarxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpIbarxm(MpIbarxmVO vo) throws Exception {
        return (String)insert("mpIbarxmDAO.insertMpIbarxm_S", vo);
    }

    /**
	 * MP_IBARXM을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpIbarxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpIbarxm(MpIbarxmVO vo) throws Exception {
        update("mpIbarxmDAO.updateMpIbarxm_S", vo);
    }

    /**
	 * MP_IBARXM을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpIbarxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpIbarxm(MpIbarxmVO vo) throws Exception {
        delete("mpIbarxmDAO.deleteMpIbarxm_S", vo);
    }

    /**
	 * MP_IBARXM을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpIbarxmVO
	 * @return 조회한 MP_IBARXM
	 * @exception Exception
	 */
    public MpIbarxmVO selectMpIbarxm(MpIbarxmVO vo) throws Exception {
        return (MpIbarxmVO) select("mpIbarxmDAO.selectMpIbarxm_S", vo);
    }

    /**
	 * MP_IBARXM 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return MP_IBARXM 목록
	 * @exception Exception
	 */
    public List<?> selectMpIbarxmList(MpIbarxmDefaultVO searchVO) throws Exception {
        return list("mpIbarxmDAO.selectMpIbarxmList_D", searchVO);
    }

    /**
	 * MP_IBARXM 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return MP_IBARXM 총 갯수
	 * @exception
	 */
    public int selectMpIbarxmListTotCnt(MpIbarxmDefaultVO searchVO) {
        return (Integer)select("mpIbarxmDAO.selectMpIbarxmListTotCnt_S", searchVO);
    }

}
