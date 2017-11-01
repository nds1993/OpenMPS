package nds.mpm.sales.SD0805.service;

import java.util.List;

import nds.mpm.sales.SD0805.vo.MpNotifyDefaultVO;
import nds.mpm.sales.SD0805.vo.MpNotifyVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpNotifyDAO.java
 * @Description : MpNotify DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpNotifyDAO")
public class MpNotifyDAO extends EgovAbstractDAO {

	/**
	 * mp_notify을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpNotifyVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpNotify(EgovMap vo) throws Exception {
        return (String)insert("mpNotifyDAO.insertMpNotify_S", vo);
    }

    /**
	 * mp_notify을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpNotifyVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpNotify(EgovMap vo) throws Exception {
        update("mpNotifyDAO.updateMpNotify_S", vo);
    }

    /**
	 * mp_notify을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpNotifyVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpNotify(EgovMap vo) throws Exception {
        delete("mpNotifyDAO.deleteMpNotify_S", vo);
    }

    /**
	 * mp_notify을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpNotifyVO
	 * @return 조회한 mp_notify
	 * @exception Exception
	 */
    public MpNotifyVO selectMpNotify(EgovMap vo) throws Exception {
        return (MpNotifyVO) select("mpNotifyDAO.selectMpNotify_S", vo);
    }

    /**
	 * mp_notify 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_notify 목록
	 * @exception Exception
	 */
    public List<?> selectMpNotifyList(MpNotifyDefaultVO searchVO) throws Exception {
        return list("mpNotifyDAO.selectMpNotifyList_D", searchVO);
    }

    /**
	 * mp_notify 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_notify 총 갯수
	 * @exception
	 */
    public int selectMpNotifyListTotCnt(MpNotifyDefaultVO searchVO) {
        return (Integer)select("mpNotifyDAO.selectMpNotifyListTotCnt_S", searchVO);
    }

}
