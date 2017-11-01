package nds.core.systemsettings.sitemanage.service;

import java.util.List;

import nds.frm.exception.MainException;

public interface SiteManageService {
	 /**
     * 사이트 등록
     * @param siteManageVO
     * @throws MainException
     */
    public void insertSite(SiteManageVO siteManageVO) throws MainException;
   
    /**
     * 사이트 수정
     * @param siteManageVO
     * @throws MainException
     */
    public void updateSite(SiteManageVO siteManageVO) throws MainException;
    
    /**
     * 사이트 삭제
     * @param siteManageVO
     * @throws MainException
     */
    public void deleteSite(SiteManageVO siteManageVO) throws MainException;
    
    /**
     * 사이트 리스트 조회
     * @param siteManageVO
     * @throws MainException
     */
    public List<SiteManageVO> selectSite(SiteManageVO siteManageVO) throws MainException;
   
    /**
     * 사이트 리스트 건수 조회
     * @param siteManageVO
     * @throws MainException
     */
    public int selectSiteCount(SiteManageVO siteManageVO) throws MainException;
}
