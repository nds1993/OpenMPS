package nds.mpm.sales.base.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.sales.base.vo.MpLpcixmVO;
import nds.mpm.sales.base.vo.MpLpcixmDefaultVO;

/**
 * @Class Name : MpLpcixmDAO.java
 * @Description : MpLpcixm DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.27
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpLpcixmDAO")
public class MpLpcixmDAO extends EgovAbstractDAO {

	/**
	 * MP_LPCIXM을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpLpcixmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpLpcixm(MpLpcixmVO vo) throws Exception {
        return (String)insert("mpLpcixmDAO.insertMpLpcixm_S", vo);
    }

    /**
	 * MP_LPCIXM을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpLpcixmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpLpcixm(MpLpcixmVO vo) throws Exception {
        update("mpLpcixmDAO.updateMpLpcixm_S", vo);
    }

    /**
	 * MP_LPCIXM을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpLpcixmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpLpcixm(MpLpcixmVO vo) throws Exception {
        delete("mpLpcixmDAO.deleteMpLpcixm_S", vo);
    }

    /**
	 * MP_LPCIXM을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpLpcixmVO
	 * @return 조회한 MP_LPCIXM
	 * @exception Exception
	 */
    public MpLpcixmVO selectMpLpcixm(MpLpcixmVO vo) throws Exception {
        return (MpLpcixmVO) select("mpLpcixmDAO.selectMpLpcixm_S", vo);
    }

    /**
	 * MP_LPCIXM 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return MP_LPCIXM 목록
	 * @exception Exception
	 */
    public List<?> selectMpLpcixmList(MpLpcixmDefaultVO searchVO) throws Exception {
        return list("mpLpcixmDAO.selectMpLpcixmList_D", searchVO);
    }

    /**
	 * MP_LPCIXM 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return MP_LPCIXM 총 갯수
	 * @exception
	 */
    public int selectMpLpcixmListTotCnt(MpLpcixmDefaultVO searchVO) {
        return (Integer)select("mpLpcixmDAO.selectMpLpcixmListTotCnt_S", searchVO);
    }

}
