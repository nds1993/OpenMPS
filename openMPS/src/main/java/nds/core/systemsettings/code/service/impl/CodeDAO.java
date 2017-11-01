/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-21 13:38:39
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-21 13:38:39
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.systemsettings.code.service.impl;

import java.util.List;

import nds.core.systemsettings.code.service.CodeVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("codeDAO")
public class CodeDAO extends EgovAbstractDAO {

    public CodeDAO() {
        super();
    }
    
    public void insert(CodeVO codeVO) {
    	insert("Code.insert", codeVO);
    }

    public int updateByPrimaryKeySelective(CodeVO codeVO) {
        int rows = update("Code.updateByPrimaryKeySelective", codeVO);
        return rows;
    }

    @SuppressWarnings("unchecked")
    public List selectByCodeList(CodeVO codeVO) {
        return list("Code.selectByCodeList", codeVO);
    }

    public int selectByCodeListCount(CodeVO codeVO){
        int counts = Integer.parseInt(selectByPk("Code.selectByCodeListCount", codeVO).toString());
        return counts;
    }

    public int selectCountByDomain(CodeVO codeVO){
        int counts = Integer.parseInt(selectByPk("Code.selectCountByDomain", codeVO).toString());
        return counts;
    }
    
    @SuppressWarnings("unchecked")
    public List selectTbvcCommCdClsCode(CodeVO codeVO) {
        return list("Code.selectTbvcCommCdClsCode", codeVO);
    }

    @SuppressWarnings("unchecked")
    public List selectTreeByHelper(CodeVO codeVO) {
        return list("Code.selectTreeByHelper", codeVO);
    }    

    public int deleteTree(CodeVO codeVO) {
        int rows = delete("Code.deleteTree", codeVO);
        return rows;
    }

    @SuppressWarnings("unchecked")
    public List selectByRecord(CodeVO codeVO) {
        return list("Code.selectByRecord", codeVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectByRecord_N(CodeVO codeVO) {
        return list("Code.selectByRecord_N", codeVO);
    }
}