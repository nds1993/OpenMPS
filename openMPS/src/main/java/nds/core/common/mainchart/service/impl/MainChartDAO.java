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
package nds.core.common.mainchart.service.impl;



import java.util.List;

import nds.core.common.mainchart.service.MainChartVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mainChartDAO")
public class MainChartDAO extends EgovAbstractDAO {
    
    public int selectWidgetsCount(MainChartVO maWidgetsSetVO) {
        return (Integer)selectByPk("MainChart.selectWidgetsCount", maWidgetsSetVO);
    }
    
    public MainChartVO selectWidgetsInfo(MainChartVO maWidgetsSetVO) {
        return (MainChartVO) selectByPk("MainChart.selectWidgetsInfo", maWidgetsSetVO);
    }

    @SuppressWarnings("unchecked")
    public List selectMainChart001(MainChartVO mainChartVO) {
        return list("MainChart.selectMainChart001", mainChartVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMainChart003(MainChartVO mainChartVO) {
        return list("MainChart.selectMainChart003", mainChartVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMainChart005(MainChartVO mainChartVO) {
        return list("MainChart.selectMainChart005", mainChartVO);
    }

    @SuppressWarnings("unchecked")
    public List selectMainChart006(MainChartVO mainChartVO) {
        return list("MainChart.selectMainChart006", mainChartVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMainChart007(MainChartVO mainChartVO) {
        return list("MainChart.selectMainChart007", mainChartVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMainChart008(MainChartVO mainChartVO) {
        return list("MainChart.selectMainChart008", mainChartVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMainChart009(MainChartVO mainChartVO) {
    	return list("MainChart.selectMainChart009", mainChartVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMainChart010(MainChartVO mainChartVO) {
    	return list("MainChart.selectMainChart010", mainChartVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMainChart011(MainChartVO mainChartVO) {
    	return list("MainChart.selectMainChart011", mainChartVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMainChart012(MainChartVO mainChartVO) {
    	return list("MainChart.selectMainChart012", mainChartVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMainChart013(MainChartVO mainChartVO) {
    	return list("MainChart.selectMainChart013", mainChartVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMainChart014(MainChartVO mainChartVO) {
    	return list("MainChart.selectMainChart014", mainChartVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMainChart015(MainChartVO mainChartVO) {
    	return list("MainChart.selectMainChart015", mainChartVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMainChart016(MainChartVO mainChartVO) {
    	return list("MainChart.selectMainChart016", mainChartVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMainChart017(MainChartVO mainChartVO) {
    	return list("MainChart.selectMainChart017", mainChartVO);
    }

    @SuppressWarnings("unchecked")
    public List selectMainChart018(MainChartVO mainChartVO) {
    	return list("MainChart.selectMainChart018", mainChartVO);
    }
}