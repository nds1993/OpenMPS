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
package nds.core.operation.surveytmpl.service.impl;



import java.util.List;

import nds.core.operation.surveytmpl.service.SrvyViewVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("srvyViewDAO")
public class SrvyViewDAO extends EgovAbstractDAO {

    public SrvyViewDAO() {
        super();
    }

    public void insertView(SrvyViewVO record) {
        insert("SrvyView.insertView", record);
    }
    
    public Object insertViewsCopy(SrvyViewVO record) {
        Object viewNo = insert("SrvyView.insertViewsCopy", record);
        return viewNo;
    }

    public int updateView(SrvyViewVO record) {
        int rows = update("SrvyView.updateView", record);
        return rows;
    }


    @SuppressWarnings("unchecked")
    public List<SrvyViewVO> selectViewList(SrvyViewVO srvyViewVO) {
        List<SrvyViewVO> list = (List<SrvyViewVO>) list("SrvyView.selectViewList", srvyViewVO);
        return list;
    }
    
    public int selectViewListMaxLevel(SrvyViewVO srvyViewVO) {
        int counts = Integer.parseInt(selectByPk("SrvyView.selectViewListMaxLevel", srvyViewVO).toString());
        return counts;
    }
    
    @SuppressWarnings("unchecked")
    public List<SrvyViewVO> selectVocViewList(SrvyViewVO srvyViewVO) {
        List<SrvyViewVO> list = (List<SrvyViewVO>) list("SrvyView.selectVocViewList", srvyViewVO);
        return list;
    }
    
    public int deleteView(SrvyViewVO srvyViewVO) {
        int rows = delete("SrvyView.deleteView", srvyViewVO);
        return rows;
    }
    
}