package nds.mpm.buy.PO0201.service;

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

@Repository("PO0201MpPigdxmDAO")
public class PO0201MpPigdxmDAO extends EgovAbstractDAO {

    /**
	 * mp_pigdxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigdxm 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigdxmList(MpPigdxmDefaultVO searchVO) throws Exception {
        return list("PO0201MpPigdxmDAO.selectMpPigdxmHeadList_D", searchVO);
    }

    /**
	 * mp_pigdxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigdxm 총 갯수
	 * @exception
	 */
    public int selectMpPigdxmListTotCnt(MpPigdxmDefaultVO searchVO) {
        return (Integer)select("PO0201MpPigdxmDAO.selectMpPigdxmListTotCnt_S", searchVO);
    }
    
    /**
	 * mp_pigdxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigdxmVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpPigdxm(EgovMap vo) throws Exception {
        return update("PO0201MpPigdxmDAO.updateMpPigdxm", vo);
    }
    
    
    public List<?> selectMpPigdxmDetailList(MpPigdxmDefaultVO searchVO) throws Exception {
        return list("PO0201MpPigdxmDAO.selectMpPigdxmDetailList_D", searchVO);
    }
    
    public int updateMpPigdxmRepCust(EgovMap vo) throws Exception {
        return update("PO0201MpPigdxmDAO.updateMpPigdxmRepCust", vo);
    }
}
