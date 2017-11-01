package nds.mpm.sales.SD0606.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.sales.SD0606.vo.MpCustRecordVO;
import nds.mpm.sales.SD0606.service.SD0606MpCustRecordDAO;
import nds.mpm.sales.SD0606.service.SD0606MpCustRecordService;

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
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 9. 20.  JAR        수정
 *  Copyright (C)  All right reserved.
 */

@Service("SD0606mpCustRecordService")
public class SD0606MpCustRecordServiceImpl extends EgovAbstractServiceImpl implements
        SD0606MpCustRecordService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0606MpCustRecordServiceImpl.class);

    @Resource(name="SD0606mpCustRecordDAO")
    private SD0606MpCustRecordDAO mpCustRecordDAO;

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
