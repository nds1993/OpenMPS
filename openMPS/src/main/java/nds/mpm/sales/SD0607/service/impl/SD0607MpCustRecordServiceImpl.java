package nds.mpm.sales.SD0607.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.sales.SD0607.vo.MpCustRecordVO;
import nds.mpm.sales.SD0607.service.SD0607MpCustRecordDAO;
import nds.mpm.sales.SD0607.service.SD0607MpCustRecordService;

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
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 9. 20.  JAR        수정
 *  Copyright (C)  All right reserved.
 */

@Service("SD0607mpCustRecordService")
public class SD0607MpCustRecordServiceImpl extends EgovAbstractServiceImpl implements
        SD0607MpCustRecordService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0607MpCustRecordServiceImpl.class);

    @Resource(name="SD0607mpCustRecordDAO")
    private SD0607MpCustRecordDAO mpCustRecordDAO;

    /**
	 * mp_cust_record 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_record 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustRecordList(MpCustRecordVO searchVO) throws Exception {
        return mpCustRecordDAO.selectMpCustRecordList(searchVO);
    }

    public List<?> selectChartMpCustRecordList(MpCustRecordVO searchVO) throws Exception {
        return mpCustRecordDAO.selectChartMpCustRecordList(searchVO);
    }
}
