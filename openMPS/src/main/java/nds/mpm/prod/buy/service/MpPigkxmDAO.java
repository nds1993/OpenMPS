package nds.mpm.prod.buy.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.prod.buy.vo.MpPigkxmVO;
import nds.mpm.prod.buy.vo.MpPigkxmDefaultVO;

/**
 * @Class Name : MpPigkxmDAO.java
 * @Description : MpPigkxm DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPigkxmDAO")
public class MpPigkxmDAO extends EgovAbstractDAO {

	/**
	 * mp_pigkxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigkxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPigkxm(MpPigkxmVO vo) throws Exception {
        return (String)insert("mpPigkxmDAO.insertMpPigkxm_S", vo);
    }

    /**
	 * mp_pigkxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigkxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPigkxm(MpPigkxmVO vo) throws Exception {
        update("mpPigkxmDAO.updateMpPigkxm_S", vo);
    }

    /**
	 * mp_pigkxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigkxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPigkxm(MpPigkxmVO vo) throws Exception {
        delete("mpPigkxmDAO.deleteMpPigkxm_S", vo);
    }

    /**
	 * mp_pigkxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigkxmVO
	 * @return 조회한 mp_pigkxm
	 * @exception Exception
	 */
    public MpPigkxmVO selectMpPigkxm(MpPigkxmVO vo) throws Exception {
        return (MpPigkxmVO) select("mpPigkxmDAO.selectMpPigkxm_S", vo);
    }

    /**
	 * mp_pigkxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigkxm 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigkxmList(MpPigkxmDefaultVO searchVO) throws Exception {
        return list("mpPigkxmDAO.selectMpPigkxmList_D", searchVO);
    }

    /**
	 * mp_pigkxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigkxm 총 갯수
	 * @exception
	 */
    public int selectMpPigkxmListTotCnt(MpPigkxmDefaultVO searchVO) {
        return (Integer)select("mpPigkxmDAO.selectMpPigkxmListTotCnt_S", searchVO);
    }

}
