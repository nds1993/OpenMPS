package nds.mpm.buy.PO0203.service;

import java.util.List;

import nds.mpm.buy.PO0102.vo.MpPigdxmDefaultVO;
import nds.mpm.buy.PO0102.vo.MpPigdxmVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : MpPigdxmDAO.java
 * @Description : MpPigdxm DAO Class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PO0203MpPigdxmDAO")
public class PO0203MpPigdxmDAO extends EgovAbstractDAO {

    /**
	 * mp_pigdxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigdxm 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigdxmList(MpPigdxmDefaultVO searchVO) throws Exception {
        return list("PO0203MpPigdxmDAO.selectMpPigdxmListGroupNongjang", searchVO);
    }
    
    public List<?> selectMpPigdxmListGroupDoch(MpPigdxmDefaultVO searchVO) throws Exception {
        return list("PO0203MpPigdxmDAO.selectMpPigdxmListGroupDoch", searchVO);
    }

    /**
	 * mp_pigdxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigdxm 총 갯수
	 * @exception
	 */
    public int selectMpPigdxmListTotCnt(MpPigdxmDefaultVO searchVO) {
        return (Integer)select("PO0203MpPigdxmDAO.selectMpPigdxmListTotCnt_S", searchVO);
    }

}
