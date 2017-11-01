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
package nds.core.logsearch.deallog.service.impl;

import java.util.List;

import nds.core.logsearch.deallog.service.DealLogVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("dealLogDAO")
public class DealLogDAO extends EgovAbstractDAO {

    public DealLogDAO() {
        super();
    }

    public void insertExcpLogInfo(DealLogVO dealLogVO) {
    	insert("DealLog.insertExcpLogInfo", dealLogVO);
    }
	
    @SuppressWarnings("unchecked")
	public List selectExcpLogInfoList(DealLogVO dealLogVO) {
        return list("DealLog.selectExcpLogInfoList", dealLogVO);
    }

    public int selectExcpLogInfoListCount(DealLogVO dealLogVO) {
        return (Integer) selectByPk("DealLog.selectExcpLogInfoListCount", dealLogVO);
    }

}