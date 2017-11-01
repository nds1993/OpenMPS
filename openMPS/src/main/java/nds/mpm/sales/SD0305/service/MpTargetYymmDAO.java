package nds.mpm.sales.SD0305.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.sales.SD0305.vo.MpTargetYymmDefaultVO;
import nds.mpm.sales.SD0305.vo.MpTargetYymmVO;

/**
 * @Class Name : MpTargetYymmDAO.java
 * @Description : MpTargetYymm DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpTargetYymmDAO")
public class MpTargetYymmDAO extends EgovAbstractDAO {

    /**
	 * mp_target_yymm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_target_yymm 목록
	 * @exception Exception
	 */
    public List<?> selectMpTargetYymmList(MpTargetYymmDefaultVO searchVO) throws Exception {
        return list("SD0305mpTargetYymmDAO.selectMpTargetYymmList_H", searchVO);
    }

    /**
	 * mp_target_yymm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_target_yymm 총 갯수
	 * @exception
	 */
    public int selectMpTargetYymmListTotCnt(MpTargetYymmDefaultVO searchVO) {
        return (Integer)select("SD0305mpTargetYymmDAO.selectMpTargetYymmListTotCnt_S", searchVO);
    }
    
    /**
	 * mp_target_yymm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_target_yymm 목록
	 * @exception Exception
	 */
    public List<?> selectMpTargetYymmListD(MpTargetYymmDefaultVO searchVO) throws Exception {
        return list("SD0305mpTargetYymmDAO.selectMpTargetYymmList_D", searchVO);
    }

}
