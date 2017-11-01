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
package nds.core.operation.holiday.service.impl;

import java.util.List;

import nds.core.operation.holiday.service.HolidayVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("holidayDAO")
public class HolidayDAO extends EgovAbstractDAO {

    public HolidayDAO() {
        super();
    }

    public int updateHdayAll(HolidayVO holidayVO) {
        return update("Holiday.updateByPrimaryKey", holidayVO);
    }
    
    public int updateHday(HolidayVO holidayVO) {
        return update("Holiday.updateByPrimaryKeySelective", holidayVO);
    }
    
    public void callSpMakeHday(HolidayVO holidayVO)
    {
        selectByPk("Holiday.callSpMakeHday", holidayVO);
    }
    
    public int selectCountByHelper(HolidayVO holidayVO) {
        return (Integer)selectByPk("Holiday.selectCountByHelper", holidayVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectByHelper(HolidayVO holidayVO) {
        return list("Holiday.selectByHelper", holidayVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectUserWorkHdayResult(HolidayVO holidayVO) {
        return list("Holiday.selectUserWorkHdayResult", holidayVO);
    }
    
    public int selectUserWorkHdayResultCount(HolidayVO holidayVO) {
        return (Integer) selectByPk("Holiday.selectUserWorkHdayResultCount", holidayVO);
    }
    
    public HolidayVO selectHdayTime(HolidayVO holidayVO) {
        return (HolidayVO)selectByPk("Holiday.selectByPrimaryKey", holidayVO);
    }
    
    public int updateHdayResn(HolidayVO holidayVO) {
    	return update("Holiday.updateHdayResn", holidayVO);
    }
    
    public void insertHdayResn(HolidayVO holidayVO) {
    	insert("Holiday.insertHdayResn", holidayVO);
    }
    
    public void deleteHdayResn(HolidayVO holidayVO) {
    	delete("Holiday.deleteHdayResn", holidayVO);
    }
    
}