/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-25 17:57:59
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-25 17:57:59
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.userdep.department.service.impl;

import java.util.List;

import nds.core.userdep.department.service.DepartMentVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("departMentDAO")
public class DepartMentDAO extends EgovAbstractDAO {

    public DepartMentDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List selectByHelper(DepartMentVO record) {
        return list("DepartMent.selectByHelper", record);
    }
    
    public int selectCountByHelper(DepartMentVO record) {
        return (Integer) selectByPk("DepartMent.selectCountByHelper", record);
    }

    /**
     * 조직트리 조회
     * @param tbhrOrganization
     * @return
     */
    public List selectOrganization(DepartMentVO record) {
        return list("DepartMent.selectOrganization", record);
    }

    /**
     * 조직트리 조회
     * @param tbhrOrganization
     * @return
     */
    public List selectOrgTree(String depCd) {
        DepartMentVO key = new DepartMentVO();
        key.setDepCd(depCd);
        return list("DepartMent.selectOrgTree", key);
    }
    
   

    public List selectDeptInfo(DepartMentVO record) {
        return list("DepartMent.selectDeptInfo", record);
    }    
    /**
     * 부서정보
     */
    public DepartMentVO getDeptInfo(String deptCd) {
        DepartMentVO key = new DepartMentVO();
        key.setDepCd(deptCd);
        return (DepartMentVO) selectByPk("DepartMent.getDeptInfo", key);
    }    
    
    public Object insert(DepartMentVO record) {
        Object deptCd = null;
        return (Object) insert("DepartMent.insert", record);
    }

    public int updateDep(DepartMentVO record) {
        return (Integer) update("DepartMent.updateDep", record);
    }    
    
    public int deleteDep(DepartMentVO record) {
        return (Integer) delete("DepartMent.deleteDep", record);
    }
    
    public int getDeptUsingCount(String deptCd) {
        return (Integer) selectByPk("DepartMent.getDeptUsingCount", deptCd);
    }
    
    public int selectDepCount(DepartMentVO tbvcDep) {
        return (Integer) selectByPk("DepartMent.selectDepCount", tbvcDep);
    }
}