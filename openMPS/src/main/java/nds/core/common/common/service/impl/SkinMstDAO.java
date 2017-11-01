/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2009-8-26 16:39:16
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2009-8-26 16:39:16
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.common.common.service.impl;


import nds.core.common.common.service.SkinMstVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("skinMstDAO")
public class SkinMstDAO extends EgovAbstractDAO {

    public SkinMstDAO() {
        super();
    }

//    public Object insert(SkinMstVO skinMstVO) {
//        return (String)insert("SkinMst.insert", skinMstVO);
//    }
//
//    public int updateByPrimaryKeySelective(SkinMstVO skinMstVO) {
//        return (Integer)update("SkinMst.updateByPrimaryKeySelective", skinMstVO);
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<SkinMstVO> selectByHelper(SkinMstVO skinMstVO) {
//        return list("SkinMst.selectByHelper", skinMstVO);
//    }
//
//    public int selectCountByHelper(SkinMstVO skinMstVO) {
//        return (Integer)selectByPk("SkinMst.selectCountByHelper", skinMstVO);
//    }
//
    public SkinMstVO selectByPrimaryKey(String skinNo) {
        SkinMstVO key = new SkinMstVO();
        key.setSkinNo(skinNo);
        return (SkinMstVO) selectByPk("SkinMst.selectByPrimaryKey", key);
    }

//    public int deleteByPrimaryKey(String skinNo) {
//        SkinMstVO key = new SkinMstVO();
//        key.setSkinNo(skinNo);
//        return (Integer)delete("SkinMst.deleteByPrimaryKey", key);
//    }
    
    public SkinMstVO selectBaseSkinInfo() {
        return (SkinMstVO) selectByPk("SkinMst.selectBaseSkinInfo", null);
    }
}