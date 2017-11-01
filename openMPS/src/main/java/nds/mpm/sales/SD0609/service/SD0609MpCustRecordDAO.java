package nds.mpm.sales.SD0609.service;

import java.util.List;

import nds.mpm.sales.SD0604.vo.MpCustRecordDefaultVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : MpCustRecordDAO.java
 * @Description : MpCustRecord DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("SD0609mpCustRecordDAO")
public class SD0609MpCustRecordDAO extends EgovAbstractDAO {

    /**
	 * mp_cust_record 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_record 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustRecordList(MpCustRecordDefaultVO searchVO) throws Exception {
        return list("SD0609mpCustRecordDAO.selectMpCustRecordList_D", searchVO);
    }

    /**
	 * mp_cust_record 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_record 총 갯수
	 * @exception
	 */
    public int selectMpCustRecordListTotCnt(MpCustRecordDefaultVO searchVO) {
        return (Integer)select("SD0609mpCustRecordDAO.selectMpCustRecordListTotCnt_S", searchVO);
    }
    
    /**
	 * mp_cust_record 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_record 목록
	 * @exception Exception
	 */
    public List<?> selectDetailTab1MpCustRecordList(MpCustRecordDefaultVO searchVO) throws Exception {
        return list("SD0609mpCustRecordDAO.selectDetailTab1MpCustRecordList_D", searchVO);
    }

    public List<?> selectDetailTab2MpCustRecordList(MpCustRecordDefaultVO searchVO) throws Exception {
        return list("SD0609mpCustRecordDAO.selectDetailTab2MpCustRecordList_D", searchVO);
    }
}
