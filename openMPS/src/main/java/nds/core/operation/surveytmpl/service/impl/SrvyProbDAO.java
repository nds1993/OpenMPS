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

import nds.core.operation.surveytmpl.service.SrvyProbVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("srvyProbDAO")
public class SrvyProbDAO extends EgovAbstractDAO {

    public SrvyProbDAO() {
        super();
    }
    
    public void insert(SrvyProbVO record) {
        insert("SrvyProb.insertQues", record);
    }
    
    public void insertQues(SrvyProbVO record) {
        insert("SrvyProb.insertQues", record);
    }
    
    public Object insertQuesCopy(SrvyProbVO record) {
        Object probNo = insert("SrvyProb.insertQuesCopy", record);
        return probNo;
    }

    public int updateQues(SrvyProbVO record) {
        int rows = update("SrvyProb.updateQues", record);
        return rows;
    }
    
    public String selectProbNo(SrvyProbVO key) {
        String probno = (String) selectByPk("SrvyProb.selectProbNo", key);
        return probno;
    }

    @SuppressWarnings("unchecked")
    public List<SrvyProbVO> selectQuesList(SrvyProbVO srvyProbVO) {
        List<SrvyProbVO> list = (List<SrvyProbVO>) list("SrvyProb.selectQuesList", srvyProbVO);
        return list;
    }
    
    public SrvyProbVO selectQues(SrvyProbVO srvyProbVO) {
        srvyProbVO = (SrvyProbVO)selectByPk("SrvyProb.selectQues", srvyProbVO);
        return srvyProbVO;
    }
    
    public int selectOrdCount(SrvyProbVO srvyProbVO) {
        int counts = Integer.parseInt(selectByPk("SrvyProb.selectOrdCount", srvyProbVO).toString());
        return counts;
    }
    
    @SuppressWarnings("unchecked")
    public List<SrvyProbVO> selectByConnect(SrvyProbVO key) {
        List<SrvyProbVO> list = (List<SrvyProbVO>) list("SrvyProb.selectByConnect", key);
        return list;
    }

    public int deleteQues(SrvyProbVO srvyProbVO) {
        int rows = delete("SrvyProb.deleteQues", srvyProbVO);
        return rows;
    }
}