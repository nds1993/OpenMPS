package nds.mpm.prod.PP0604.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.prod.PP0604.vo.MpBarinProMVO;
import nds.mpm.prod.PP0604.vo.MpBarinProMDefaultVO;

/**
 * @Class Name : MpBarinProMDAO.java
 * @Description : MpBarinProM DAO Class
 * @Modification Information
 *
 * @author 속라벨 발행현황 조회
 * @since 속라벨 발행현황 조회
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpBarinProMDAO")
public class MpBarinProMDAO extends EgovAbstractDAO {

	/**
	 * mp_barin_pro_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBarinProMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpBarinProM(MpBarinProMVO vo) throws Exception {
        return (String)insert("mpBarinProMDAO.insertMpBarinProM_S", vo);
    }

    /**
	 * mp_barin_pro_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBarinProMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpBarinProM(MpBarinProMVO vo) throws Exception {
        update("mpBarinProMDAO.updateMpBarinProM_S", vo);
    }

    /**
	 * mp_barin_pro_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBarinProMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpBarinProM(MpBarinProMVO vo) throws Exception {
        delete("mpBarinProMDAO.deleteMpBarinProM_S", vo);
    }

    /**
	 * mp_barin_pro_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBarinProMVO
	 * @return 조회한 mp_barin_pro_m
	 * @exception Exception
	 */
    public MpBarinProMVO selectMpBarinProM(MpBarinProMVO vo) throws Exception {
        return (MpBarinProMVO) select("mpBarinProMDAO.selectMpBarinProM_S", vo);
    }

    /**
	 * mp_barin_pro_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_barin_pro_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpBarinProMList(MpBarinProMDefaultVO searchVO) throws Exception {
        return list("mpBarinProMDAO.selectMpBarinProMList_D", searchVO);
    }

    /**
	 * mp_barin_pro_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_barin_pro_m 총 갯수
	 * @exception
	 */
    public int selectMpBarinProMListTotCnt(MpBarinProMDefaultVO searchVO) {
        return (Integer)select("mpBarinProMDAO.selectMpBarinProMListTotCnt_S", searchVO);
    }

}
