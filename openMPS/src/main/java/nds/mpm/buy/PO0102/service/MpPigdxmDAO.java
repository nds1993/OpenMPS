package nds.mpm.buy.PO0102.service;

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
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPigdxmDAO")
public class MpPigdxmDAO extends EgovAbstractDAO {

	/**
	 * mp_pigdxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigdxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPigdxm(EgovMap vo) throws Exception {
        return (String)insert("mpPigdxmDAO.insertMpPigdxm", vo);
    }

    /**
	 * mp_pigdxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigdxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPigdxm(EgovMap vo) throws Exception {
        update("mpPigdxmDAO.updateMpPigdxm", vo);
    }

    /**
	 * mp_pigdxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigdxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpPigdxm(EgovMap vo) throws Exception {
        return delete("mpPigdxmDAO.deleteMpPigdxm", vo);
    }
    
    public void deleteMpPigdxmForHisNo(EgovMap vo) throws Exception {
        delete("mpPigdxmDAO.deleteMpPigdxmForHisNo", vo);
    }

    /**
	 * mp_pigdxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigdxmVO
	 * @return 조회한 mp_pigdxm
	 * @exception Exception
	 */
    public MpPigdxmVO selectMpPigdxm(MpPigdxmVO vo) throws Exception {
        return (MpPigdxmVO) select("mpPigdxmDAO.selectMpPigdxm_S", vo);
    }

    /**
	 * mp_pigdxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigdxm 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigdxmList(MpPigdxmDefaultVO searchVO) throws Exception {
        return list("mpPigdxmDAO.selectMpPigdxmList_D", searchVO);
    }

    /**
	 * mp_pigdxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigdxm 총 갯수
	 * @exception
	 */
    public int selectMpPigdxmListTotCnt(MpPigdxmDefaultVO searchVO) {
        return (Integer)select("mpPigdxmDAO.selectMpPigdxmListTotCnt_S", searchVO);
    }

    public EgovMap selectFacInfo(MpPigdxmVO vo) throws Exception {
        return (EgovMap) select("mpPigdxmDAO.selectFacInfo", vo);
    }

    public EgovMap selectBuyType(MpPigdxmVO vo) throws Exception {
        return (EgovMap) select("mpPigdxmDAO.selectBuyType", vo);
    }
}
