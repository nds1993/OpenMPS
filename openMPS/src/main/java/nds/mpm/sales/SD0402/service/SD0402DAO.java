package nds.mpm.sales.SD0402.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.sales.SD0402.vo.SD0402DefaultVO;
import nds.mpm.sales.SD0402.vo.SD0402VO;
import nds.mpm.sales.SD0402.vo.SD0402DefaultVO;
import nds.mpm.sales.SD0402.vo.SD0402VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : SD0402DAO.java
 * @Description : SD0402 DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("SD0402DAO")
public class SD0402DAO extends TMMPPBaseDAO {

	/**
	 * mp_delv_approval을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SD0402VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertSD0402(EgovMap vo) throws Exception {
        return (String)insert("SD0402DAO.insertSD0402", vo);
    }

    /**
	 * mp_delv_approval을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SD0402VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateSD0402(SD0402VO vo) throws Exception {
        update("SD0402DAO.updateSD0402Request", vo);
    }

    /**
	 * mp_delv_approval을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SD0402VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteSD0402(SD0402VO vo) throws Exception {
        delete("SD0402DAO.deleteSD0402", vo);
    }

    /**
	 * mp_delv_approval을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SD0402VO
	 * @return 조회한 mp_delv_approval
	 * @exception Exception
	 */
    public SD0402VO selectSD0402(SD0402VO vo) throws Exception {
        return (SD0402VO) select("SD0402DAO.selectSD0402_S", vo);
    }

    /**
	 * mp_delv_approval 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_delv_approval 목록
	 * @exception Exception
	 */
    public List<?> selectSD0402List(SD0402DefaultVO searchVO) throws Exception {
        return list("SD0402DAO.selectSD0402List_D", searchVO);
    }

    /**
	 * mp_delv_approval 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_delv_approval 총 갯수
	 * @exception
	 */
    public int selectSD0402ListTotCnt(SD0402DefaultVO searchVO) {
        return (Integer)select("SD0402DAO.selectSD0402ListTotCnt_S", searchVO);
    }
    
    /**
	 * mp_delv_approval 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_delv_approval 목록
	 * @exception Exception
	 */
    public List<?> selectSD0403List(SD0402DefaultVO searchVO) throws Exception {
        return list("SD0402DAO.selectSD0403List_D", searchVO);
    }

    /**
	 * mp_delv_approval 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_delv_approval 총 갯수
	 * @exception
	 */
    public int selectSD0403ListTotCnt(SD0402DefaultVO searchVO) {
        return (Integer)select("SD0402DAO.selectSD0403ListTotCnt_S", searchVO);
    }

    public int updateDelvApproConfirm(EgovMap vo) throws Exception {
        return update("SD0402DAO.updateDelvApproConfirm", vo);
    }
    
    /**
	 * user ofce_code search role check
	 * @exception Exception
	 */
    public EgovMap selectUserInfo(EgovMap vo) throws Exception {
        return (EgovMap) select("SD0402DAO.selectUserInfo", vo);
    }
}
