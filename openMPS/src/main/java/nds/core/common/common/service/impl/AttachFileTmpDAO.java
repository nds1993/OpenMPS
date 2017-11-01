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

import nds.core.common.common.service.AttachFileTmpVO;
import nds.core.common.common.service.AttachFileVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("attachFileTmpDAO")
public class AttachFileTmpDAO extends EgovAbstractDAO {

    public AttachFileTmpDAO() {
        super();
    }

    public void insert(AttachFileTmpVO attachFileTmpVO) {
        insert("AttachFileTmp.insert", attachFileTmpVO);
    }

    public int deleteByPrimaryKey(String atchFileChngName) {
        AttachFileTmpVO key = new AttachFileTmpVO();
        key.setAtchFileChngName(atchFileChngName);
        return (Integer)delete("AttachFileTmp.deleteByPrimaryKey", key);
    }
    
    public List selectFileExt() {
        return list("AttachFileTmp.selectFileExt", null);
    }
}