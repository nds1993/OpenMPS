package nds.mpm.sales.SD0802.service;

import java.util.List;

import nds.mpm.sales.SD0802.vo.MpDelvLimitDefaultVO;
import nds.mpm.sales.SD0802.vo.MpDelvLimitVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDelvLimitDAO.java
 * @Description : MpDelvLimit DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpDelvLimitDAO")
public class MpDelvLimitDAO extends EgovAbstractDAO {

	/**
	 * mp_delv_limit을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDelvLimitVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpDelvLimit(EgovMap vo) throws Exception {
        return (String)insert("mpDelvLimitDAO.insertMpDelvLimit", vo);
    }

    /**
	 * mp_delv_limit을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDelvLimitVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpDelvLimit(EgovMap vo) throws Exception {
        return update("mpDelvLimitDAO.updateMpDelvLimit", vo);
    }

    /**
	 * mp_delv_limit을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDelvLimitVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpDelvLimit(EgovMap vo) throws Exception {
    	return delete("mpDelvLimitDAO.deleteMpDelvLimit", vo);
    }

    /**
	 * mp_delv_limit을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDelvLimitVO
	 * @return 조회한 mp_delv_limit
	 * @exception Exception
	 */
    public MpDelvLimitVO selectMpDelvLimit(EgovMap vo) throws Exception {
        return (MpDelvLimitVO) select("mpDelvLimitDAO.selectMpDelvLimit_S", vo);
    }

    /**
	 * mp_delv_limit 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_delv_limit 목록
	 * @exception Exception
	 */
    public List<?> selectMpDelvLimitList(MpDelvLimitDefaultVO searchVO) throws Exception {
        return list("mpDelvLimitDAO.selectMpDelvLimitList_D", searchVO);
    }

    /**
	 * mp_delv_limit 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_delv_limit 총 갯수
	 * @exception
	 */
    public int selectMpDelvLimitListTotCnt(MpDelvLimitDefaultVO searchVO) {
        return (Integer)select("mpDelvLimitDAO.selectMpDelvLimitListTotCnt_S", searchVO);
    }

}
