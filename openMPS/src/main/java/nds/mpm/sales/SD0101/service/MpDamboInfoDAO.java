package nds.mpm.sales.SD0101.service;

import java.util.List;

import nds.mpm.sales.SD0101.vo.MpDamboInfoDefaultVO;
import nds.mpm.sales.SD0101.vo.MpDamboInfoVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDamboInfoDAO.java
 * @Description : MpDamboInfo DAO Class
 * @Modification Information
 *
 * @author b
 * @since b
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpDamboInfoDAO")
public class MpDamboInfoDAO extends EgovAbstractDAO {

	/**
	 * mp_dambo_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDamboInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public int insertMpDamboInfo(EgovMap vo) throws Exception {
        return (Integer)insert("mpDamboInfoDAO.insertMpDamboInfo", vo);
    }

    /**
	 * mp_dambo_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDamboInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpDamboInfo(EgovMap vo) throws Exception {
        return update("mpDamboInfoDAO.updateMpDamboInfo", vo);
    }

    /**
	 * mp_dambo_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDamboInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpDamboInfo(EgovMap vo) throws Exception {
    	return delete("mpDamboInfoDAO.deleteMpDamboInfo", vo);
    }

    /**
	 * mp_dambo_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDamboInfoVO
	 * @return 조회한 mp_dambo_info
	 * @exception Exception
	 */
    public MpDamboInfoVO selectMpDamboInfo(MpDamboInfoVO vo) throws Exception {
        return (MpDamboInfoVO) select("mpDamboInfoDAO.selectMpDamboInfo_S", vo);
    }

    /**
	 * mp_dambo_info 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_dambo_info 목록
	 * @exception Exception
	 */
    public List<?> selectMpDamboInfoList(MpDamboInfoDefaultVO searchVO) throws Exception {
        return list("mpDamboInfoDAO.selectMpDamboInfoList_D", searchVO);
    }

    /**
	 * mp_dambo_info 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_dambo_info 총 갯수
	 * @exception
	 */
    public int selectMpDamboInfoListTotCnt(MpDamboInfoDefaultVO searchVO) {
        return (Integer)select("mpDamboInfoDAO.selectMpDamboInfoListTotCnt_S", searchVO);
    }

}
