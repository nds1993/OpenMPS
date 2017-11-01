package nds.mpm.sales.SD0610.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.sales.SD0610.vo.MpCustRecordDefaultVO;
import nds.mpm.sales.SD0610.service.SD0610MpCustRecordDAO;
import nds.mpm.sales.SD0610.service.SD0610MpCustRecordService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustRecordServiceImpl.java
 * @Description : MpCustRecord Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD0610mpCustRecordService")
public class SD0610MpCustRecordServiceImpl extends EgovAbstractServiceImpl implements
        SD0610MpCustRecordService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0610MpCustRecordServiceImpl.class);

    @Resource(name="SD0610mpCustRecordDAO")
    private SD0610MpCustRecordDAO mpCustRecordDAO;

    /**
	 * mp_cust_record 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_record 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustRecordList(EgovMap searchVO) throws Exception {
        return mpCustRecordDAO.selectMpCustRecordList(searchVO);
    }
    
    /**
	 * mp_cust_record 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_record 목록
	 * @exception
	 */
    public List<?> selectDetailMpCustRecordList(EgovMap searchVO) throws Exception {
        return mpCustRecordDAO.selectDetailMpCustRecordList(searchVO);
    }

    /**
	 * mp_cust_record 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_record 총 갯수
	 * @exception
	 */
    public int selectMpCustRecordListTotCnt(EgovMap searchVO) {
		return mpCustRecordDAO.selectMpCustRecordListTotCnt(searchVO);
	}
}
