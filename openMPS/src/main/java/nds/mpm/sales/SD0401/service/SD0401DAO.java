package nds.mpm.sales.SD0401.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.sales.SD0401.vo.SD0401DefaultVO;
import nds.mpm.sales.SD0401.vo.SD0401VO;
import nds.mpm.sales.SD0402.vo.SD0402DefaultVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : SD0401DAO.java
 * @Description : SD0401 DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("SD0401DAO")
public class SD0401DAO extends TMMPPBaseDAO {

	/**
	 * mp_order_h을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SD0401VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertSD0401(SD0401VO vo) throws Exception {
        return (String)insert("SD0401DAO.insertSD0401_S", vo);
    }

    /**
	 * mp_order_h을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SD0401VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateSD0401(SD0401VO vo) throws Exception {
        update("SD0401DAO.updateSD0401_S", vo);
    }

    /**
	 * mp_order_h을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SD0401VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteSD0401(SD0401VO vo) throws Exception {
        delete("SD0401DAO.deleteSD0401_S", vo);
    }

    /**
	 * mp_order_h을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SD0401VO
	 * @return 조회한 mp_order_h
	 * @exception Exception
	 */
    public SD0401VO selectSD0401(EgovMap vo) throws Exception {
        return (SD0401VO) select("SD0401DAO.selectSD0401_S", vo);
    }

    /**
	 * mp_order_h 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 목록
	 * @exception Exception
	 */
    public List<?> selectSD0401List(SD0401DefaultVO searchVO) throws Exception {
        return list("SD0401DAO.selectSD0401List_D", searchVO);
    }

    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    public int selectSD0401ListTotCnt(SD0401DefaultVO searchVO) {
        return (Integer)select("SD0401DAO.selectSD0401ListTotCnt_S", searchVO);
    }
    
    /**
	 * mp_order_h 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 목록
	 * @exception Exception
	 */
    public List<?> selectSD0402List(SD0402DefaultVO searchVO) throws Exception {
        return list("SD0401DAO.selectSD0402List_D", searchVO);
    }

    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    public int selectSD0402ListTotCnt(SD0402DefaultVO searchVO) {
        return (Integer)select("SD0401DAO.selectSD0402ListTotCnt_S", searchVO);
    }
    
    /**
	 * mp_order_h을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SD0401VO
	 * @return 조회한 mp_order_h
	 * @exception Exception
	 */
    public EgovMap selectSD0401ProJaegoInfo(SD0401VO vo) throws Exception {
        return (EgovMap) select("SD0401DAO.selectSD0401ProJaegoInfo", vo);
    }
    
    /**
     * 여신정보
	 * mp_order_h을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SD0401VO
	 * @return 조회한 mp_order_h
	 * @exception Exception
	 */
    public EgovMap selectSD0401CustLimit(SD0401VO vo) throws Exception {
        return (EgovMap) select("SD0401DAO.selectSD0401CustLimit", vo);
    }
    
    public String selectSD0401DelvCust(SD0401DefaultVO searchVO) {
        return (String)select("SD0401DAO.selectSD0401DelvCust", searchVO);
    }
    
    public List<?> selectTab1MpOrderDList(SD0401VO searchVO) throws Exception {
        return list("SD0401DAO.selectTab1MpOrderDList", searchVO);
    }

    public List<?> selectTab2MpOrderDList(SD0401VO searchVO) throws Exception {
        return list("SD0401DAO.selectTab2MpOrderDList", searchVO);
    }

}
