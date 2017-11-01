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
package nds.mpm.api.openApi.service.impl;

import nds.mpm.api.openApi.service.OpenapiVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("openapiDAO")
public class OpenapiDAO extends EgovAbstractDAO {

    public OpenapiDAO() {
        super();
    }

    public void insert(OpenapiVO openapiVO) {

    	insert("Openapi.insert", openapiVO);
    }

    public void insertLog(OpenapiVO openapiVO) {

    	insert("Openapi.insertLog", openapiVO);
    }

    
    public void insertTrace(OpenapiVO openapiVO) {

    	insert("Openapi.insertTrace", openapiVO);
    }
    
    
    public void insertConfirm(OpenapiVO openapiVO) {

    	insert("Openapi.insertConfirm", openapiVO);
    }
    
    
    public void insertConfirmDetail(OpenapiVO openapiVO) {

    	insert("Openapi.insertConfirmDetail", openapiVO);
    }

    public void insertConfirmNo(OpenapiVO openapiVO) {

    	insert("Openapi.insertConfirmNo", openapiVO);
    }
    
    public void insertGrade(OpenapiVO openapiVO) {

    	insert("Openapi.insertGrade", openapiVO);
    }

    public void insertGradeDetail(OpenapiVO openapiVO) {

    	insert("Openapi.insertGradeDetail", openapiVO);
    }
    
    public void insertApperence(OpenapiVO openapiVO) {

    	insert("Openapi.insertApperence", openapiVO);
    }
}