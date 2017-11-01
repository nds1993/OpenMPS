package nds.mpm.sales.SD0405.service;

import java.util.List;
import java.util.Map;

import nds.mpm.sales.SD0405.vo.MpOrderDDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderDVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderDDAO.java
 * @Description : MpOrderD DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpOrderDDAO")
public class MpOrderDDAO extends EgovAbstractDAO {

	/**
	 * mp_order_d을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpOrderDVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpOrderD(EgovMap vo) throws Exception {
        return (String)insert("mpOrderDDAO.insertMpOrderD", vo);
    }

    /**
	 * mp_order_d을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpOrderDVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpOrderD(EgovMap vo) throws Exception {
        update("mpOrderDDAO.updateMpOrderD", vo);
    }
    
    /**
   	 * mp_order_d weight, amt 금액을 수정한다.
   	 * @param vo - 수정할 정보가 담긴 MpOrderDVO
   	 * @return void형
   	 * @exception Exception
   	 */
     public void updateMpOrderDAmt(EgovMap vo) throws Exception {
        update("mpOrderDDAO.updateMpOrderDAmt", vo);
     }
       
    /**
	 * mp_order_d을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpOrderDVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpOrderD(EgovMap vo) throws Exception {
        return delete("mpOrderDDAO.deleteMpOrderD", vo);
    }

    /**
	 * mp_order_d을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpOrderDVO
	 * @return 조회한 mp_order_d
	 * @exception Exception
	 */
    public EgovMap selectMpOrderD(MpOrderDVO vo) throws Exception {
        return (EgovMap) select("mpOrderDDAO.selectMpOrderD_S", vo);
    }

    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDList(MpOrderDDefaultVO searchVO) throws Exception {
        return list("mpOrderDDAO.selectMpOrderDList_D", searchVO);
    }

    /**
	 * mp_order_d 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_d 총 갯수
	 * @exception
	 */
    public int selectMpOrderDListTotCnt(MpOrderDDefaultVO searchVO) {
        return (Integer)select("mpOrderDDAO.selectMpOrderDListTotCnt_S", searchVO);
    }

}
