package nds.mpm.prod.PP0503.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.prod.PP0504.vo.MpBarProMVO;
import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;

/**
 * @Class Name : MpBarProMDAO.java
 * @Description : MpBarProM DAO Class
 * @Modification Information
 *
 * @author 생산 vs 입고현황 조회
 * @since 생산 vs 입고현황 조회
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PP0503mpBarProMDAO")
public class PP0503MpBarProMDAO extends EgovAbstractDAO {

    /**
	 * mp_bar_pro_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpBarProMList(MpBarProMDefaultVO searchVO) throws Exception {
        return list("PP0503mpBarProMDAO.selectMpBarProMList_D", searchVO);
    }

    /**
	 * mp_bar_pro_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bar_pro_m 총 갯수
	 * @exception
	 */
    public int selectMpBarProMListTotCnt(MpBarProMDefaultVO searchVO) {
        return (Integer)select("PP0503mpBarProMDAO.selectMpBarProMListTotCnt_S", searchVO);
    }

    public List<?> selectPopMpBarProMList(MpBarProMDefaultVO searchVO) throws Exception {
        return list("PP0503mpBarProMDAO.selectPopMpBarProMList_D", searchVO);
    }

}
