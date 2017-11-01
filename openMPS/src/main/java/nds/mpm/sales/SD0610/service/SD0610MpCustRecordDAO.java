package nds.mpm.sales.SD0610.service;

import java.util.List;

import nds.mpm.sales.SD0610.vo.MpCustRecordDefaultVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

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

@Repository("SD0610mpCustRecordDAO")
public class SD0610MpCustRecordDAO extends EgovAbstractDAO {

    /**
	 * mp_cust_record 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_record 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustRecordList(EgovMap searchVO) throws Exception {
        return list("SD0610mpCustRecordDAO.selectMpCustRecordList_H", searchVO);
    }
    
    /**
	 * mp_cust_record 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_record 목록
	 * @exception Exception
	 */
    public List<?> selectDetailMpCustRecordList(EgovMap searchVO) throws Exception {
        return list("SD0610mpCustRecordDAO.selectMpCustRecordList_D", searchVO);
    }

    /**
	 * mp_cust_record 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_record 총 갯수
	 * @exception
	 */
    public int selectMpCustRecordListTotCnt(EgovMap searchVO) {
        return (Integer)select("SD0610mpCustRecordDAO.selectMpCustRecordListTotCnt_S", searchVO);
    }
}
