package nds.mpm.buy.PO0301.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.buy.PO0301.vo.MpPigxxhVO;
import nds.mpm.buy.PO0301.vo.MpPigxxhDefaultVO;

/**
 * @Class Name : MpPigxxhDAO.java
 * @Description : MpPigxxh DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PO0301MpPigxxhDAO")
public class PO0301MpPigxxhDAO extends EgovAbstractDAO {

	/**
	 * mp_pigxxh을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigxxhVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPigxxh(MpPigxxhVO vo) throws Exception {
        return (String)insert("PO0301MpPigxxhDAO.insertMpPigxxh_S", vo);
    }

    /**
	 * mp_pigxxh을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigxxhVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPigxxh(MpPigxxhVO vo) throws Exception {
        update("PO0301MpPigxxhDAO.updateMpPigxxh_S", vo);
    }

    /**
	 * mp_pigxxh을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigxxhVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPigxxh(MpPigxxhVO vo) throws Exception {
        delete("PO0301MpPigxxhDAO.deleteMpPigxxh_S", vo);
    }

    /**
	 * mp_pigxxh을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigxxhVO
	 * @return 조회한 mp_pigxxh
	 * @exception Exception
	 */
    public MpPigxxhVO selectMpPigxxh(MpPigxxhVO vo) throws Exception {
        return (MpPigxxhVO) select("PO0301MpPigxxhDAO.selectMpPigxxh_S", vo);
    }

    /**
	 * mp_pigxxh 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigxxh 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigxxhList(MpPigxxhDefaultVO searchVO) throws Exception {
        return list("PO0301MpPigxxhDAO.selectMpPigxxhList_D", searchVO);
    }

    /**
	 * mp_pigxxh 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigxxh 총 갯수
	 * @exception
	 */
    public int selectMpPigxxhListTotCnt(MpPigxxhDefaultVO searchVO) {
        return (Integer)select("PO0301MpPigxxhDAO.selectMpPigxxhListTotCnt_S", searchVO);
    }

}
