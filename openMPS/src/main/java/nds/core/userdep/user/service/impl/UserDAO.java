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
package nds.core.userdep.user.service.impl;

import java.util.List;

import nds.core.userdep.user.service.UserVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("userDAO")
public class UserDAO extends EgovAbstractDAO {

    public UserDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List selectByUserInfo(UserVO userVO) {
        return list("User.selectByUserInfo", userVO);
    }

    @SuppressWarnings("unchecked")
    public List selectCustList(UserVO userVO) {
		return list("User.selectCustList", userVO);
    }
    public int selectCustListCount(UserVO userVO) {
        return (Integer) selectByPk("User.selectCustListCount", userVO);
    }
    
    public UserVO selectByPrimaryKey(String userId) {
    	UserVO key = new UserVO();
        key.setUserId(userId);
        return (UserVO) selectByPk("User.selectByPrimaryKey", key);
    }
    
    public void insertUsr(UserVO userVO) {
        insert("User.insertUsr", userVO);
    }
    
    public int updateByPrimaryKey(UserVO userVO) {
        return (Integer) update("User.updateByPrimaryKey", userVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectByUserInfoByRole(UserVO userVO) {
        return list("User.selectByUserInfoByRole", userVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectByUserInfoByDepMngRole(UserVO userVO) {
    	return list("User.selectByUserInfoByDepMngRole", userVO);
    }
    
    
}