package nds.tmm.common.TMCOMT60.dao;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOMT60.vo.UdsMsgDefaultVO;
import nds.tmm.common.TMCOMT60.vo.UdsMsgVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : UdsMsgDAO.java
 * @Description : UdsMsg DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.08.14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("udsMsgDAO")
public class UdsMsgDAO extends TMMPPBaseDAO {

	/**
	 * Uds_Msg을 등록한다.
	 * @param vo - 등록할 정보가 담긴 Uds_MsgVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertUdsMsg(EgovMap vo) throws Exception {
    	return (String)insert("udsMsgDAO.insertUdsMsg", vo);
    }
	
    /**
	 * uds_msg을 조회한다.
	 * @param vo - 조회할 정보가 담긴 UdsMsgVO
	 * @return 조회한 uds_msg
	 * @exception Exception
	 */
    public UdsMsgVO selectUdsMsg(EgovMap vo) throws Exception {
        return (UdsMsgVO) select("udsMsgDAO.selectUdsMsg_S", vo);
    }

    /**
	 * uds_msg 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return uds_msg 목록
	 * @exception Exception
	 */
    public List<?> selectUdsMsgList(UdsMsgDefaultVO searchVO) throws Exception {
        return list("udsMsgDAO.selectUdsMsgList_D", searchVO);
    }

    /**
	 * uds_msg 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return uds_msg 총 갯수
	 * @exception
	 */
    public int selectUdsMsgListTotCnt(UdsMsgDefaultVO searchVO) {
        return (Integer)select("udsMsgDAO.selectUdsMsgListTotCnt_S", searchVO);
    }
    
}
