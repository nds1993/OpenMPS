/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2009-6-11 10:23:05
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* CHANGE REVISION
* ------------------------------
* AUTHOR   : NDS
* DATE     : 2009-6-11 10:23:05
* REVISION : 1.0   First release.
* -------------------------------
* CLASS DESCRIPTION
* -------------------------------
*
*/
package nds.core.common.common.service.impl;



import java.util.List;

import nds.core.common.common.service.GwApprovalVO;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.NotiUserInfoVO;
import nds.core.common.common.service.PropTypeVO;

import org.springframework.stereotype.Repository;

//import nds.clm.claimmanageinfo.tyregist.service.TyRegistVO;


//import nds.core.common.common.service.VocCommonCodeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("commonDAO")
public class CommonDAO extends EgovAbstractDAO {

    public CommonDAO() {
        super();
    }

    public void insertMenuColumn(MyMenuColumnVO myMenuColumnVO) {
        update("Common.insertMenuColumn", myMenuColumnVO);
    }
    
    public MyMenuColumnVO selectMenuColumn(MyMenuColumnVO myMenuColumnVO){
        return (MyMenuColumnVO) selectByPk("Common.selectMenuColumn", myMenuColumnVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectPropTypeMcls(PropTypeVO propTypeVO) {
    	return list("Common.selectPropTypeMcls", propTypeVO);
    }  

    @SuppressWarnings("unchecked")
    public List selectPropResultTypeMcls(PropTypeVO propTypeVO) {
    	return list("Common.selectPropResultTypeMcls", propTypeVO);
    }

	public void updateApprovalState100(GwApprovalVO record) {
		update("Common.updateApprovalState100", record);
	}  

	public void updateApprovalState200(GwApprovalVO record) {
		update("Common.updateApprovalState200", record);
	}  


    @SuppressWarnings("unchecked")
	public List selectNotiUserList(NotiUserInfoVO key) {
		return list("Common.selectNotiUserList", key);
	}
	
}