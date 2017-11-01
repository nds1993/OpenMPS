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
package nds.core.common.login.service.impl;

import java.util.List;

import nds.core.userdep.user.service.UserVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("loginDAO")
public class LoginDAO extends EgovAbstractDAO {

    public LoginDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List selectByUserInfo(UserVO record) {
        return list("Login.selectByUserInfo", record);
    }
}