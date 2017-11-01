/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-20 10:22:58
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-20 10:22:58
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.logsearch.customerlookuplog.service.impl;

import java.util.List;

import nds.core.logsearch.customerlookuplog.service.CustomerLookupLogVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("customerLookupLogDAO")
public class CustomerLookupLogDAO extends EgovAbstractDAO {

    public CustomerLookupLogDAO() {
        super();
    }

    public void insertCstLogInfo(CustomerLookupLogVO customerLookupLogVO) {
        insert("CustomerLookupLog.insertCstLogInfo", customerLookupLogVO);
    }
    
    public CustomerLookupLogVO selectMenuNm(String key) {
    	CustomerLookupLogVO customerLookupLogVO = new CustomerLookupLogVO();
    	customerLookupLogVO.setPid(key);
    	return (CustomerLookupLogVO) selectByPk("CustomerLookupLog.selectMenuNm", customerLookupLogVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectCstInfoLogList(CustomerLookupLogVO customerLookupLogVO) {
        return list("CustomerLookupLog.selectCstInfoLogList", customerLookupLogVO);
    }

    public int selectCstInfoLogListCnt(CustomerLookupLogVO customerLookupLogVO) {
        return (Integer) selectByPk("CustomerLookupLog.selectCstInfoLogListCount", customerLookupLogVO);
    }
}