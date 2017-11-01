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
import nds.core.operation.surveytmpl.service.SurveyTemplateVO;
import nds.core.operation.surveytmpl.service.SurveyVocVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("surveyTemplateDAO")
public class SurveyTemplateDAO extends EgovAbstractDAO {

    public SurveyTemplateDAO() {
        super();
    }

    public Object insertTmpl(SurveyTemplateVO record) {
    	Object tmplNo = insert("SurveyTemplate.insertTmpl", record);
    	return tmplNo;
    }    

    public int updateTmpl(SurveyTemplateVO record) {
        int rows = update("SurveyTemplate.updateTmpl", record);
        return rows;
    }
    
    public void updateUseN() {
        update("SurveyTemplate.updateUseN", null);
    }

    public int selectTmplCount(SurveyTemplateVO surveyTemplateVO) {
        int counts = Integer.parseInt(selectByPk("SurveyTemplate.selectTmplCount", surveyTemplateVO).toString());
        return counts;
    }

    @SuppressWarnings("unchecked")
    public List<SurveyTemplateVO> selectTmplList(SurveyTemplateVO surveyTemplateVO) {
        List<SurveyTemplateVO> list = (List<SurveyTemplateVO>) list("SurveyTemplate.selectTmplList", surveyTemplateVO);
        return list;
    }
    
    public int selectTmplListCount(SurveyTemplateVO surveyTemplateVO) {
        return Integer.parseInt(selectByPk("SurveyTemplate.selectTmplListCount", surveyTemplateVO).toString());
    }
    
    public SurveyTemplateVO selectTmplInfo(SurveyTemplateVO surveyTemplateVO) {
        SurveyTemplateVO record = (SurveyTemplateVO) selectByPk("SurveyTemplate.selectTmplInfo", surveyTemplateVO);
        return record;
    }
    
    public int deleteTmpl(SurveyTemplateVO surveyTemplateVO) {
        int rows = delete("SurveyTemplate.deleteTmpl", surveyTemplateVO);
        return rows;
    }

//    @SuppressWarnings("unchecked")
//	public List<SurveyTemplateVO> selectTmplImage(SurveyTemplateVO record) {
//        List<SurveyTemplateVO> list = list("SurveyTemplate.selectFromCoTemplateFrame", record);
//        return list;
//    }
    
    public int selectTmplCntnCount(SurveyTemplateVO surveyTemplateVO) {
        int counts = Integer.parseInt(selectByPk("SurveyTemplate.selectTmplCntnCount", surveyTemplateVO).toString());
        return counts;
    }

    @SuppressWarnings("unchecked")
    public List<SurveyTemplateVO> selectTmplCntnList(SurveyTemplateVO surveyTemplateVO) {
        List<SurveyTemplateVO> list = (List<SurveyTemplateVO>) list("SurveyTemplate.selectTmplCntnList", surveyTemplateVO);
        return list;
    }    
    
    @SuppressWarnings("unchecked")
    public List<SurveyVocVO> selectVocSatisList(SurveyVocVO surveyVocVO) {
        List<SurveyVocVO> list = (List<SurveyVocVO>) list("SurveyTemplate.selectVocSatisList", surveyVocVO);
        return list;
    }    
    
    public int selectVocSatisListCount(SurveyVocVO surveyVocVO) {
        int counts = Integer.parseInt(selectByPk("SurveyTemplate.selectVocSatisListCount", surveyVocVO).toString());
        return counts;
    }

    public SurveyTemplateVO selectByTmplUseY(SurveyTemplateVO surveyTemplateVO) {
        return (SurveyTemplateVO) selectByPk("SurveyTemplate.selectByTmplUseY", surveyTemplateVO);
    }
    
    public void callSpSrvyInit(SurveyTemplateVO surveyTemplateVO) {
        selectByPk("SurveyTemplate.callSpSrvyInit", surveyTemplateVO);
    }
    
    public void insertSrvyResult(SrvyViewVO srvyViewVO) {
        insert("SurveyTemplate.insertSrvyResult", srvyViewVO);
    }
    
    public void updateNeedsMstSatiScr(SrvyViewVO srvyViewVO) {
        update("SurveyTemplate.updateNeedsMstSatiScr", srvyViewVO);
    }
    
    
}