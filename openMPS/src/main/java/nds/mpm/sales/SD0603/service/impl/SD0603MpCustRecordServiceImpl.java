package nds.mpm.sales.SD0603.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.sales.SD0603.service.SD0603MpCustRecordDAO;
import nds.mpm.sales.SD0603.service.SD0603MpCustRecordService;
import nds.mpm.sales.SD0604.vo.MpCustRecordDefaultVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

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

@Service("SD0603mpCustRecordService")
public class SD0603MpCustRecordServiceImpl extends EgovAbstractServiceImpl implements
        SD0603MpCustRecordService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0603MpCustRecordServiceImpl.class);

    @Resource(name="SD0603mpCustRecordDAO")
    private SD0603MpCustRecordDAO mpCustRecordDAO;

    /**
	 * mp_cust_record 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_record 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustRecordList(MpCustRecordDefaultVO searchVO) throws Exception {
        return mpCustRecordDAO.selectMpCustRecordList(searchVO);
    }

    /**
	 * mp_cust_record 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_record 총 갯수
	 * @exception
	 */
    public int selectMpCustRecordListTotCnt(MpCustRecordDefaultVO searchVO) {
		return mpCustRecordDAO.selectMpCustRecordListTotCnt(searchVO);
	}
    
    public List<?> selectDetailTab1MpCustRecordList(MpCustRecordDefaultVO searchVO) throws Exception {
        return mpCustRecordDAO.selectDetailTab1MpCustRecordList(searchVO);
    }

    public List<?> selectDetailTab2MpCustRecordList(MpCustRecordDefaultVO searchVO) throws Exception {
        return mpCustRecordDAO.selectDetailTab2MpCustRecordList(searchVO);
    }
}
