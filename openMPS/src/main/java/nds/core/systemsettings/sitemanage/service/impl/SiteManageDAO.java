package nds.core.systemsettings.sitemanage.service.impl;

import java.util.List;

import nds.core.systemsettings.sitemanage.service.SiteManageVO;
import nds.frm.exception.MainException;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("siteManageDAO")
public class SiteManageDAO extends EgovAbstractDAO {

	public SiteManageDAO() {
		super();
	}
	
	/**
     * 사이트 등록
     * @param siteManageVO
     * @throws MainException
     */
    public void insertSite(SiteManageVO siteManageVO){
    	insert("SiteManage.insertSite", siteManageVO);
    }
    /**
     * 사이트 수정
     * @param siteManageVO
     * @throws MainException
     */
    public void updateSite(SiteManageVO siteManageVO){
    	update("SiteManage.updateSite", siteManageVO);
    }
    
    /**
     * 사이트 삭제
     * @param siteManageVO
     * @throws MainException
     */
    public void deleteSite(SiteManageVO siteManageVO){
    	delete("SiteManage.deleteSite", siteManageVO);
    }
    
    /**
     * 사이트 리스트 조회
     * @param siteManageVO
     * @throws MainException
     */
    @SuppressWarnings("unchecked")
	public List selectSite(SiteManageVO siteManageVO){
    	return list("SiteManage.selectSite", siteManageVO);
    }
   
    /**
     * 사이트 리스트 건수 조회
     * @param siteManageVO
     * @throws MainException
     */
    public int selectSiteCount(SiteManageVO siteManageVO){
    	return (Integer)selectByPk("SiteManage.selectSiteCount", siteManageVO);
    }
}
