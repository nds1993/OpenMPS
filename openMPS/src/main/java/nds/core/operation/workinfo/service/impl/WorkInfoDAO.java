/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-7-20 1:7:47
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-7-20 1:7:47
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.operation.workinfo.service.impl;

import java.util.List;

import nds.core.operation.workinfo.service.WorkInfoVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("workInfoDAO")
public class WorkInfoDAO extends EgovAbstractDAO {

    public WorkInfoDAO() {
        super();
    }
    
    @SuppressWarnings("unchecked")
    public List selectUsrWrkInfo(WorkInfoVO workInfoVO){
        return list("WorkInfo.selectUsrWrkInfo", workInfoVO);
    }
    
    public int selectUsrWrkInfoCount(WorkInfoVO workInfoVO) {
        return (Integer)selectByPk("WorkInfo.selectUsrWrkInfoCount", workInfoVO);
    }
    
    public WorkInfoVO selectUsrWrkInfoDetail(String userId) {
        WorkInfoVO key = new WorkInfoVO();
        key.setUserId(userId);
        return (WorkInfoVO) selectByPk("WorkInfo.selectUsrWrkInfoDetail", key);
    }
    
    public int updateUsrWrkInfo(WorkInfoVO workInfoVO) {
        return (Integer)update("WorkInfo.updateUsrWrkInfo", workInfoVO);
    }

}