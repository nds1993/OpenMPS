package nds.mpm.buy.PO0204.service;

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
 * @author 생돈지급율조회
 * @since 생돈지급율조회
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PO0204mpPigdxmDAO")
public class PO0204MpPigdxmDAO extends EgovAbstractDAO {

	/**
	 * mp_pigdxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigdxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPigdxm(MpPigdxmVO vo) throws Exception {
        return (String)insert("PO0204mpPigdxmDAO.insertMpPigdxm_S", vo);
    }

    /**
	 * mp_pigdxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigdxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPigdxm(MpPigdxmVO vo) throws Exception {
        update("PO0204mpPigdxmDAO.updateMpPigdxm_S", vo);
    }

    /**
	 * mp_pigdxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigdxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpPigdxm(EgovMap vo) throws Exception {
        return update("PO0204mpPigdxmDAO.updateMpPigdxm", vo);
    }

    /**
	 * mp_pigdxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigdxmVO
	 * @return 조회한 mp_pigdxm
	 * @exception Exception
	 */
    public MpPigdxmVO selectMpPigdxm(MpPigdxmVO vo) throws Exception {
        return (MpPigdxmVO) select("PO0204mpPigdxmDAO.selectMpPigdxm_S", vo);
    }

    /**
	 * mp_pigdxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigdxm 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigdxmList(MpPigdxmDefaultVO searchVO) throws Exception {
        return list("PO0204mpPigdxmDAO.selectMpPigdxmList_D", searchVO);
    }

    /**
	 * mp_pigdxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigdxm 총 갯수
	 * @exception
	 */
    public int selectMpPigdxmListTotCnt(MpPigdxmDefaultVO searchVO) {
        return (Integer)select("PO0204mpPigdxmDAO.selectMpPigdxmListTotCnt_S", searchVO);
    }

}
