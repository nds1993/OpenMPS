package nds.mpm.sales.SD0302.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.sales.SD0301.vo.MpTargetYyyyVO;
import nds.mpm.sales.SD0301.vo.MpTargetYyyyDefaultVO;

/**
 * @Class Name : MpTargetYyyyDAO.java
 * @Description : MpTargetYyyy DAO Class
 * @Modification Information
 *
 * @author 3333333
 * @since 333333
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("SD0302mpTargetYyyyDAO")
public class SD0302MpTargetYyyyDAO extends EgovAbstractDAO {

	/**
	 * mp_target_yyyy을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpTargetYyyyVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpTargetYyyy(MpTargetYyyyVO vo) throws Exception {
        return (String)insert("SD0302mpTargetYyyyDAO.insertMpTargetYyyy_S", vo);
    }

    /**
	 * mp_target_yyyy을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpTargetYyyyVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpTargetYyyy(MpTargetYyyyVO vo) throws Exception {
        update("SD0302mpTargetYyyyDAO.updateMpTargetYyyy_S", vo);
    }

    /**
	 * mp_target_yyyy을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpTargetYyyyVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpTargetYyyy(MpTargetYyyyVO vo) throws Exception {
        delete("SD0302mpTargetYyyyDAO.deleteMpTargetYyyy_S", vo);
    }

    /**
	 * mp_target_yyyy을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpTargetYyyyVO
	 * @return 조회한 mp_target_yyyy
	 * @exception Exception
	 */
    public MpTargetYyyyVO selectMpTargetYyyy(MpTargetYyyyVO vo) throws Exception {
        return (MpTargetYyyyVO) select("SD0302mpTargetYyyyDAO.selectMpTargetYyyy_S", vo);
    }

    /**
	 * mp_target_yyyy 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_target_yyyy 목록
	 * @exception Exception
	 */
    public List<?> selectMpTargetYyyyList(MpTargetYyyyDefaultVO searchVO) throws Exception {
        return list("SD0302mpTargetYyyyDAO.selectMpTargetYyyyList_D", searchVO);
    }

    /**
	 * mp_target_yyyy 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_target_yyyy 총 갯수
	 * @exception
	 */
    public int selectMpTargetYyyyListTotCnt(MpTargetYyyyDefaultVO searchVO) {
        return (Integer)select("SD0302mpTargetYyyyDAO.selectMpTargetYyyyListTotCnt_S", searchVO);
    }

}
