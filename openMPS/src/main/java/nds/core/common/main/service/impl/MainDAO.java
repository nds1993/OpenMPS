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
package nds.core.common.main.service.impl;



import java.util.List;

import nds.core.common.main.service.MaNeedsWidgetsVO;
import nds.core.common.main.service.MainVO;
import nds.core.common.main.service.NeedsCompByMonVO;
import nds.core.common.main.service.NeedsCountVO;
import nds.core.common.main.service.NeedsStatsVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mainDAO")
public class MainDAO extends EgovAbstractDAO {

    @SuppressWarnings("unchecked")
    public List selectWidgetsList(String needsStatsVO) {
        return list("Main.selectWidgetsList", needsStatsVO);
    }
    public int selectWidgetsListCount(String needsStatsVO) {
        return (Integer)selectByPk("Main.selectWidgetsListCount", needsStatsVO);
    }
    
    public int selectWidgetsCount(MainVO maWidgetsSetVO) {
        return (Integer)selectByPk("Main.selectWidgetsCount", maWidgetsSetVO);
    }
    
    public MainVO selectWidgetsInfo(MainVO maWidgetsSetVO) {
        return (MainVO) selectByPk("Main.selectWidgetsInfo", maWidgetsSetVO);
    }

    @SuppressWarnings("unchecked")
    public List selectNeedsCountByDep(NeedsCountVO needsCountVO) {
        return list("Main.selectNeedsCountByDep", needsCountVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectNeedsCountByUsr(NeedsCountVO needsCountVO) {
        return list("Main.selectNeedsCountByUsr", needsCountVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMaWidgetsNeedsList(MaNeedsWidgetsVO maNeedsWidgetsVO) {
        return list("Main.selectMaWidgetsNeedsList", maNeedsWidgetsVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectNeedsCompByNowYear(NeedsCompByMonVO needsCompByMonVO) {
        return list("Main.selectNeedsCompByNowYear", needsCompByMonVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectByCmplCountMonthAll(NeedsStatsVO needsStatsVO) {
        return list("Main.selectByCmplCountMonthAll", needsStatsVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectNeedsByNeedsTypeStatsListYear(NeedsStatsVO needsStatsVO) {
        return list("Main.selectNeedsByNeedsTypeStatsListYear", needsStatsVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectNeedsByNeedsTypeStatsListMonth(NeedsStatsVO needsStatsVO) {
        return list("Main.selectNeedsByNeedsTypeStatsListMonth", needsStatsVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectNeedsByGenStatsListYear(NeedsStatsVO needsStatsVO) {
        return list("Main.selectNeedsByGenStatsListYear", needsStatsVO);
    }

    @SuppressWarnings("unchecked")
    public List selectNeedsByGenStatsListMonth(NeedsStatsVO needsStatsVO) {
        return list("Main.selectNeedsByGenStatsListMonth", needsStatsVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectNeedsByAgeStatsListMonth(NeedsStatsVO needsStatsVO) {
    	return list("Main.selectNeedsByAgeStatsListMonth", needsStatsVO);
    }

    @SuppressWarnings("unchecked")
    public List selectNeedsByAgeStatsListYear(NeedsStatsVO needsStatsVO) {
    	return list("Main.selectNeedsByAgeStatsListMonth", needsStatsVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectNeedsByCstCounStatsListMonth(NeedsStatsVO needsStatsVO) {
    	return list("Main.selectNeedsByCstCounStatsListMonth", needsStatsVO);
    }

    @SuppressWarnings("unchecked")
    public List selectNeedsByCstCounStatsListYear(NeedsStatsVO needsStatsVO) {
    	return list("Main.selectNeedsByCstCounStatsListYear", needsStatsVO);
    }

    @SuppressWarnings("unchecked")
    public List selectNeedsByRegChnlStatsListYear(NeedsStatsVO needsStatsVO) {
    	return list("Main.selectNeedsByRegChnlStatsListYear", needsStatsVO);
    }

    @SuppressWarnings("unchecked")
    public List selectNeedsByGenCausStatsListMonth(NeedsStatsVO needsStatsVO) {
    	return list("Main.selectNeedsByGenCausStatsListMonth", needsStatsVO);
    }

    @SuppressWarnings("unchecked")
    public List selectNeedsByGenCausStatsListYear(NeedsStatsVO needsStatsVO) {
    	return list("Main.selectNeedsByGenCausStatsListYear", needsStatsVO);
    }

    @SuppressWarnings("unchecked")
    public List selectDealTmStatsListMonth(NeedsStatsVO needsStatsVO) {
    	return list("Main.selectDealTmStatsListMonth", needsStatsVO);
    }

    @SuppressWarnings("unchecked")
    public List selectDealTmStatsListYear(NeedsStatsVO needsStatsVO) {
    	return list("Main.selectDealTmStatsListYear", needsStatsVO);
    }
    
    public int selectGraphRang3Count(NeedsStatsVO needsStatsVO) {
        return (Integer)selectByPk("Main.selectGraphRang3Count", needsStatsVO);
    }
    
    public int selectGraphRang4Count(NeedsStatsVO needsStatsVO) {
        return (Integer)selectByPk("Main.selectGraphRang4Count", needsStatsVO);
    }
    
    public int selectNeedsByGenCausStatsCountYear(NeedsStatsVO needsStatsVO) {
        return (Integer)selectByPk("Main.selectNeedsByGenCausStatsCountYear", needsStatsVO);
    }

    public int selectNeedsByGenCausStatsCountMonth(NeedsStatsVO needsStatsVO) {
        return (Integer)selectByPk("Main.selectNeedsByGenCausStatsCountMonth", needsStatsVO);
    }
    
}