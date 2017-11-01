package nds.core.systemsettings.sitemanage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.systemsettings.sitemanage.service.SiteManageService;
import nds.core.systemsettings.sitemanage.service.SiteManageVO;
import nds.frm.exception.ExceptionHelper;
import nds.frm.exception.MainException;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("siteManageService")
public class SiteManageServiceImpl extends AbstractServiceImpl implements SiteManageService{

	@Resource(name="siteManageDAO")
    private SiteManageDAO siteManagaeDAO;
	
	 /**
     * 사이트 등록
     * @param siteManageVO
     * @throws MainException
     */
    public void insertSite(SiteManageVO siteManageVO) throws MainException{
    	try{
    		siteManagaeDAO.insertSite(siteManageVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertSite() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사이트 등록",ex.getMessage()} 
            );
        }       
    }
   
    /**
     * 사이트 수정
     * @param siteManageVO
     * @throws MainException
     */
    public void updateSite(SiteManageVO siteManageVO) throws MainException{
    	try{
    		siteManagaeDAO.updateSite(siteManageVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateSite() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사이트 수정",ex.getMessage()} 
            );
        }       
    }
    
    /**
     * 사이트 삭제
     * @param siteManageVO
     * @throws MainException
     */
    public void deleteSite(SiteManageVO siteManageVO) throws MainException{
    	try{
    		siteManagaeDAO.deleteSite(siteManageVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteSite() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사이트 삭제",ex.getMessage()} 
            );
        }  
    }
    
    /**
     * 사이트 리스트 조회
     * @param siteManageVO
     * @throws MainException
     */
    public List<SiteManageVO> selectSite(SiteManageVO siteManageVO) throws MainException{
    	List<SiteManageVO> list = null;
    	try{
    		list = siteManagaeDAO.selectSite(siteManageVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectSite() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사이트 리스트 조회",ex.getMessage()} 
            );
        }  
    	return list;
    }
   
    /**
     * 사이트 리스트 건수 조회
     * @param siteManageVO
     * @throws MainException
     */
    public int selectSiteCount(SiteManageVO siteManageVO) throws MainException{
    	int count = 0;
    	try{
    		count = siteManagaeDAO.selectSiteCount(siteManageVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectSiteCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사이트 리스트 건수 조회",ex.getMessage()} 
            );
        }  
    	return count;
    }

}
