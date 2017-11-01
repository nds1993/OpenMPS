package nds.mpm.buy.PO0301.service;

import java.util.List;

import nds.mpm.buy.PO0301.vo.MpPigoxmDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigoxmVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : PO0301MpPigoxmDAO.java
 * @Description : PO0301MpPigoxm DAO Class
 * @Modification Information
 *
 * @author PO0302
 * @since PO0302
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PO0301MpPigoxmDAO")
public class PO0301MpPigoxmDAO extends EgovAbstractDAO {

	/**
	 * mp_pigoxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 PO0301MpPigoxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertPO0301MpPigoxm(EgovMap vo) throws Exception {
        return (String)insert("PO0301MpPigoxmDAO.insertPO0301MpPigoxm", vo);
    }

    /**
	 * mp_pigoxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 PO0301MpPigoxmVO
	 * @return void형
	 * @exception Exception
	 */
    public int updatePO0301MpPigoxm(EgovMap vo) throws Exception {
       return update("PO0301MpPigoxmDAO.updatePO0301MpPigoxm", vo);
    }

    /**
	 * mp_pigoxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 PO0301MpPigoxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deletePO0301MpPigoxm(EgovMap vo) throws Exception {
        return delete("PO0301MpPigoxmDAO.deletePO0301MpPigoxm", vo);
    }

    /**
	 * mp_pigoxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 PO0301MpPigoxmVO
	 * @return 조회한 mp_pigoxm
	 * @exception Exception
	 */
    public MpPigoxmVO selectPO0301MpPigoxm(MpPigoxmVO vo) throws Exception {
        return (MpPigoxmVO) select("PO0301MpPigoxmDAO.selectPO0301MpPigoxm_S", vo);
    }

    /**
	 * mp_pigoxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigoxm 목록
	 * @exception Exception
	 */
    public List<?> selectPO0301MpPigoxmTab1List(MpPigoxmDefaultVO searchVO) throws Exception {
        return list("PO0301MpPigoxmDAO.selectPO0301MpPigoxmTab1List_D", searchVO);
    }

    /**
	 * mp_pigoxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigoxm 총 갯수
	 * @exception
	 */
    public int selectPO0301MpPigoxmTab1ListTotCnt(MpPigoxmDefaultVO searchVO) {
        return (Integer)select("PO0301MpPigoxmDAO.selectPO0301MpPigoxmTab1ListTotCnt_S", searchVO);
    }

    /**
     * TAB1 mp_pigdxm 이상육일괄생성.
     * */
    public List<?> selectPO0301Tab1MpPigdxmList_D(MpPigoxmVO searchVO) throws Exception {
        return list("PO0301MpPigoxmDAO.selectPO0301Tab1MpPigdxmList_D", searchVO);
    }
    
    public List<?> selectPO0301MpPigoxmTab2List(MpPigoxmDefaultVO searchVO) throws Exception {
        return list("PO0301MpPigoxmDAO.selectPO0301MpPigoxmTab2List_D", searchVO);
    }

    /**
	 * mp_pigoxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigoxm 총 갯수
	 * @exception
	 */
    public int selectPO0301MpPigoxmTab2ListTotCnt(MpPigoxmDefaultVO searchVO) {
        return (Integer)select("PO0301MpPigoxmDAO.selectPO0301MpPigoxmTab2ListTotCnt_S", searchVO);
    }
    
    public int updatePO0301Tab2MpPigoxm(EgovMap vo) throws Exception {
        return update("PO0301MpPigoxmDAO.updatePO0301Tab2MpPigoxm", vo);
    }
    /**
     * TAB3 mp_pigdxm 이상육생성.
     * */
    public List<?> selectPO0301Tab3MpPigdxmList_D(MpPigoxmVO searchVO) throws Exception {
        return list("PO0301MpPigoxmDAO.selectPO0301Tab3MpPigdxmList_D", searchVO);
    }
}
