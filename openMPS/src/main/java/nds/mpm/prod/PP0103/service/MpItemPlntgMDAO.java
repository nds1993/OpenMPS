package nds.mpm.prod.PP0103.service;

import java.util.List;

import nds.mpm.prod.PP0103.vo.MpItemPlntgMDefaultVO;
import nds.mpm.prod.PP0103.vo.MpItemPlntgMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpItemPlntgMDAO.java
 * @Description : MpItemPlntgM DAO Class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpItemPlntgMDAO")
public class MpItemPlntgMDAO extends EgovAbstractDAO {

	/**
	 * mp_item_plntg_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpItemPlntgMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpItemPlntgM(EgovMap vo) throws Exception {
        return (String)insert("mpItemPlntgMDAO.insertMpItemPlntgM", vo);
    }

    /**
	 * mp_item_plntg_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpItemPlntgMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpItemPlntgM(MpItemPlntgMVO vo) throws Exception {
        update("mpItemPlntgMDAO.updateMpItemPlntgM", vo);
    }

    /**
	 * mp_item_plntg_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpItemPlntgMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpItemPlntgM(EgovMap vo) throws Exception {
        delete("mpItemPlntgMDAO.deleteMpItemPlntgM", vo);
    }

    /**
	 * mp_item_plntg_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpItemPlntgMVO
	 * @return 조회한 mp_item_plntg_m
	 * @exception Exception
	 */
    public MpItemPlntgMVO selectMpItemPlntgM(MpItemPlntgMVO vo) throws Exception {
        return (MpItemPlntgMVO) select("mpItemPlntgMDAO.selectMpItemPlntgM_S", vo);
    }

    /**
	 * mp_item_plntg_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_item_plntg_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpItemPlntgMList(MpItemPlntgMDefaultVO searchVO) throws Exception {
        return list("mpItemPlntgMDAO.selectMpItemPlntgMList_D", searchVO);
    }

    /**
	 * mp_item_plntg_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_item_plntg_m 총 갯수
	 * @exception
	 */
    public int selectMpItemPlntgMListTotCnt(MpItemPlntgMDefaultVO searchVO) {
        return (Integer)select("mpItemPlntgMDAO.selectMpItemPlntgMListTotCnt_S", searchVO);
    }

}
