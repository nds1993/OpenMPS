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

import nds.core.common.common.service.AttachFileVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("attachFileDAO")
public class AttachFileDAO extends EgovAbstractDAO {

    public AttachFileDAO() {
        super();
    }

    public void insert(AttachFileVO attachFileVO) {
        insert("AttachFile.insert", attachFileVO);
    }

    public void insertHomepage(AttachFileVO attachFileVO) {
        insert("AttachFile.insertHomepage", attachFileVO);
    }
    
    public List selectByHelper(AttachFileVO attachFileVO) {
        return list("AttachFile.selectByHelper", attachFileVO);
    }
    public List selectPisAttachedFile(AttachFileVO attachFileVO) {
        return list("AttachFile.selectPisAttachedFile", attachFileVO);
    }

    public AttachFileVO selectByPk(AttachFileVO attachFileVO) {
        return (AttachFileVO)selectByPk("AttachFile.selectByPk", attachFileVO);
    }


    public int deleteByPrimaryKey(AttachFileVO attachFileVO) {
        return (Integer)delete("AttachFile.deleteByPrimaryKey", attachFileVO);
    }
    public int deleteByDocRegNo(AttachFileVO attachFileVO) {
        return (Integer)delete("AttachFile.deleteByDocRegNo", attachFileVO);
    }
    
    public List selectFileList(AttachFileVO attachFileVO) {
        return list("AttachFile.selectFileList", attachFileVO);
    }
    
    public List selectNsTestFileList(AttachFileVO attachFileVO) {
        return list("AttachFile.selectNsTestFileList", attachFileVO);
    }
    
    public List selectByRegNo(AttachFileVO attachFileVO) {
        return list("AttachFile.selectByRegNo", attachFileVO);
    }

	public List getPotoAttachFileList(AttachFileVO attachFileVO) {
		return list("AttachFile.getPotoAttachFileList", attachFileVO);
	}
    
}