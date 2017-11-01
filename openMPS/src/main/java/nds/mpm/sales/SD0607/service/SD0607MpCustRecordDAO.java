package nds.mpm.sales.SD0607.service;

import java.util.List;

import nds.mpm.sales.SD0607.vo.MpCustRecordVO;

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
* << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 9. 20.  JAR        수정
 *  Copyright (C)  All right reserved.
 */

@Repository("SD0607mpCustRecordDAO")
public class SD0607MpCustRecordDAO extends EgovAbstractDAO {

    /**
	 * mp_cust_record 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_record 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustRecordList(MpCustRecordVO searchVO) throws Exception {
        return list("SD0607mpCustRecordDAO.selectMpCustRecordList_D", searchVO);
    }
    /**
	 * mp_cust_record 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_record 목록
	 * @exception Exception
	 */
    public List<?> selectChartMpCustRecordList(MpCustRecordVO searchVO) throws Exception {
        return list("SD0607mpCustRecordDAO.selectChartMpCustRecordList", searchVO);
    }
}
