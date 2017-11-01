package nds.mpm.prod.PP0307.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : MpBarProMDAO.java
 * @Description : MpBarProM DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PP0307mpBarProMDAO")
public class PP0307MpBarProMDAO extends TMMPPBaseDAO {

    /**
	 * mp_bar_pro_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpBarProMList(MpBarProMDefaultVO searchVO) throws Exception {
        return list("PP0307MpBarProMDAO.selectMpBarProMList_D", searchVO);
    }

    /**
	 * mp_bar_pro_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bar_pro_m 총 갯수
	 * @exception
	 */
    public int selectMpBarProMListTotCnt(MpBarProMDefaultVO searchVO) {
        return (Integer)select("PP0307MpBarProMDAO.selectMpBarProMListTotCnt_S", searchVO);
    }

    public List<?> selectPopMpBarProMList(MpBarProMDefaultVO searchVO) throws Exception {
        return list("PP0307MpBarProMDAO.selectPopMpBarProMList_D", searchVO);
    }
}
