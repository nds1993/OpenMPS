package nds.mpm.sales.SD0803.service;

import java.util.List;

import nds.mpm.sales.SD0401.vo.SD0401VO;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

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

@Repository("SD0803MpOrderDDAO")
public class SD0803MpOrderDDAO extends EgovAbstractDAO {

	/**
	 * 회송주문헤더 생성
	 * mp_order_h
	 * 
	 * delv_date
	 * ordr_no 
	 * 
	 * */
	public String insertReturnMpOrderH(MpOrderHVO vo) throws Exception {
        return (String)insert("SD0803MpOrderDDAO.insertReturnMpOrderH", vo);
    }
	/**
	 * 회송주문상세 생성
	 * mp_order_d
	 * 
	 * delv_date
	 * ordr_no 
	 * 
	 * */
	public String insertReturnMpOrderD(MpOrderHVO vo) throws Exception {
        return (String)insert("SD0803MpOrderDDAO.insertReturnMpOrderD", vo);
    }
	/**
	 * 신규주문헤더 생성
	 * mp_order_h
	 * 
	 * delv_date
	 * ordr_no 
	 * 
	 * */
	public String insertReCreateMpOrderH(MpOrderHVO vo) throws Exception {
        return (String)insert("SD0803MpOrderDDAO.insertReCreateMpOrderH", vo);
    }
	/**
	 * 신규주문상세 생성
	 * mp_order_d
	 * 
	 * delv_date
	 * ordr_no 
	 * 
	 * */
	public String insertReCreateMpOrderD(MpOrderHVO vo) throws Exception {
        return (String)insert("SD0803MpOrderDDAO.insertReCreateMpOrderD", vo);
    }
	
	/**
	 * 회송주문의 매출주문상세 생성
	 * mp_order_d
	 * 
	 * delv_date
	 * ordr_no 
	 * 
	 * */
	public String insertReturnReCreateMpOrderD(MpOrderHVO vo) throws Exception {
        return (String)insert("SD0803MpOrderDDAO.insertReturnReCreateMpOrderD", vo);
    }
    
    public List<?> selectMpOrderDList(MpOrderHVO searchVO) throws Exception {
        return list("SD0803MpOrderDDAO.selectMpOrderDList_D", searchVO);
    }
    /**
	 * mp_order_d 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_d 총 갯수
	 * @exception
	 */
    public int selectMpOrderDListTotCnt(MpOrderHVO searchVO) {
        return (Integer)select("SD0803MpOrderDDAO.selectMpOrderDListTotCnt_S", searchVO);
    }

}
