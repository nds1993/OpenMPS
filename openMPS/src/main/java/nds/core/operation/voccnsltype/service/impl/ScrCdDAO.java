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
package nds.core.operation.voccnsltype.service.impl;

import java.util.List;

import nds.core.operation.voccnsltype.service.ScrCdVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("scrCdDAO")
public class ScrCdDAO extends EgovAbstractDAO {

    public ScrCdDAO() {
        super();
    }

    public void insertScrCd(ScrCdVO scrCdVO) {
        insert("ScrCd.insertScrCd", scrCdVO);
    }
    
    public int updateByPrimaryKeySelective(ScrCdVO scrCdVO) {
        return (Integer)update("ScrCd.updateByPrimaryKeySelective", scrCdVO);
    }
    
    public int deleteByPrimaryKey(ScrCdVO scrCdVO) {
        return (Integer)delete("ScrCd.deleteByPrimaryKey", scrCdVO);
    }
    
    public int selectByPrimaryKeyCount(ScrCdVO scrCdVO) {
        return (Integer)selectByPk("ScrCd.selectByPrimaryKeyCount", scrCdVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectCnslTypeList(ScrCdVO scrCdVO){
        return list("ScrCd.selectCnslTypeList", scrCdVO);
    }
    
}