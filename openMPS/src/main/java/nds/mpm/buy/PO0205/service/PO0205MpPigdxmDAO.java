package nds.mpm.buy.PO0205.service;

import java.util.List;

import nds.mpm.buy.PO0102.vo.MpPigdxmDefaultVO;
import nds.mpm.buy.PO0102.vo.MpPigdxmVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPigdxmDAO.java
 * @Description : MpPigdxm DAO Class
 * @Modification Information
 *
 * @author 더느림지급액조회
 * @since 더느림지급액조회
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PO0205mpPigdxmDAO")
public class PO0205MpPigdxmDAO extends EgovAbstractDAO {

	/**
	 * mp_pigdxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigdxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPigdxm(MpPigdxmVO vo) throws Exception {
        return (String)insert("PO0205mpPigdxmDAO.insertMpPigdxm_S", vo);
    }

    /**
	 * mp_pigdxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigdxmVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpPigdxm(EgovMap vo) throws Exception {
        return update("PO0205mpPigdxmDAO.updateMpPigdxm_S", vo);
    }

    /**
	 * mp_pigdxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigdxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPigdxm(MpPigdxmVO vo) throws Exception {
        delete("PO0205mpPigdxmDAO.deleteMpPigdxm_S", vo);
    }

    /**
	 * mp_pigdxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigdxmVO
	 * @return 조회한 mp_pigdxm
	 * @exception Exception
	 */
    public MpPigdxmVO selectMpPigdxm(MpPigdxmVO vo) throws Exception {
        return (MpPigdxmVO) select("PO0205mpPigdxmDAO.selectMpPigdxm_S", vo);
    }

    /**
	 * mp_pigdxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigdxm 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigdxmList(MpPigdxmDefaultVO searchVO) throws Exception {
        return list("PO0205mpPigdxmDAO.selectMpPigdxmList_D", searchVO);
    }

    /**
	 * mp_pigdxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigdxm 총 갯수
	 * @exception
	 */
    public int selectMpPigdxmListTotCnt(MpPigdxmDefaultVO searchVO) {
        return (Integer)select("PO0205mpPigdxmDAO.selectMpPigdxmListTotCnt_S", searchVO);
    }

}
