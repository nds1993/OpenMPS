package nds.mpm.buy.PO0302.service;

import java.util.List;

import nds.mpm.buy.PO0302.vo.MpPigoxmVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : PO0302MpPigoxmDAO.java
 * @Description : PO0302MpPigoxmDAO DAO Class
 * @Modification Information
 *
 * @author 123
 * @since 123
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PO0302MpPigoxmDAO")
public class PO0302MpPigoxmDAO extends EgovAbstractDAO {

	 /**
	 * mp_pigoxm 목록을 조회한다.tab1
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigoxm 목록
	 * @exception Exception
	 */
    public List<?> selectPO0302MpPigoxmList_tab1(MpPigoxmVO searchVO) throws Exception {
        return list("PO0302MpPigoxmDAO.selectMpPigoxmList_tab1", searchVO);
    }

    /**
	 * mp_pigoxm 총 갯수를 조회한다.tab1
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_req_mtrl_m 총 갯수
	 * @exception
	 */
    public int selectPO0302MpPigoxmListTotCnt_tab1(MpPigoxmVO searchVO) {
        return (Integer)select("PO0302MpPigoxmDAO.selectMpPigoxmListTotCnt_tab1", searchVO);
    }

	 /**
	 * mp_pigoxm 목록을 조회한다.tab2
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigoxm 목록
	 * @exception Exception
	 */
   public List<?> selectPO0302MpPigoxmList_tab2(MpPigoxmVO searchVO) throws Exception {
       return list("PO0302MpPigoxmDAO.selectMpPigoxmList_tab2", searchVO);
   }

   /**
	 * mp_pigoxm 총 갯수를 조회한다.tab2
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_req_mtrl_m 총 갯수
	 * @exception
	 */
   public int selectPO0302MpPigoxmListTotCnt_tab2(MpPigoxmVO searchVO) {
       return (Integer)select("PO0302MpPigoxmDAO.selectMpPigoxmListTotCnt_tab2", searchVO);
   }

}
