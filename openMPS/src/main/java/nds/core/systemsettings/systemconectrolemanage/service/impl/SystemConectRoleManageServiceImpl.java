package nds.core.systemsettings.systemconectrolemanage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.systemsettings.sitemanage.service.SiteManageVO;
import nds.core.systemsettings.systemconectrolemanage.service.SystemConectRoleManageService;
import nds.core.systemsettings.systemconectrolemanage.service.SystemConectRoleManageVO;
import nds.core.userdep.department.service.DepartMentVO;
import nds.core.userdep.department.service.impl.DepartMentDAO;
import nds.core.userdep.user.service.UserVO;
import nds.core.userdep.user.service.impl.UserDAO;
import nds.frm.exception.ExceptionHelper;
import nds.frm.exception.MainException;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("systemConectRoleManageService")
public class SystemConectRoleManageServiceImpl extends AbstractServiceImpl implements SystemConectRoleManageService{
	
	@Resource(name="systemConectRoleManageDAO")
    private SystemConectRoleManageDAO systemConectRoleManageDAO;
	
	@Resource(name="departMentDAO")
    private DepartMentDAO departMentDAO;
	
	@Resource(name="userDAO")
    private UserDAO userDAO;
	
	/**
     * 사이트 권한 수정
     * @param siteManageVO
     * @throws MainException
     */
	public void updateSiteRole(SystemConectRoleManageVO systemConectRoleManageVO) throws MainException {
		try{
			systemConectRoleManageDAO.updateSiteRole(systemConectRoleManageVO);
    	}catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateSiteRole() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사이트 권한 수정 중",ex.getMessage()} 
            );
        }       
	}
	
	/**
     * 권한 상세 조회
     * @param systemConectRoleManageVO
     * @throws MainException
     */
    public SystemConectRoleManageVO selectRoleDetail(SystemConectRoleManageVO systemConectRoleManageVO) throws MainException{
    	SystemConectRoleManageVO result = null;
    	try{
    		result = systemConectRoleManageDAO.selectRoleDetail(systemConectRoleManageVO);
    	}catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectRoleDetail() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"권한 상세 조회 중",ex.getMessage()} 
            );
        }     
    	return result;
    }
    
	 /**
     * 부서 트리 조회
     * @param siteManageVO
     * @throws MainException
     */
	@SuppressWarnings("unchecked")
	public List<DepartMentVO> selectDepTree(DepartMentVO departMentVO) throws MainException {
		List<DepartMentVO> list = null;
    	try{
    		list = departMentDAO.selectOrganization(departMentVO);
    	}catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDepTree() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"부서 트리 조회 중",ex.getMessage()} 
            );
        }       
    	return list;
	}

	public List<UserVO> selectCustList(UserVO userVO) throws MainException {
		List list = null;
		try {
			list = userDAO.selectCustList(userVO);
		} catch (Exception ex) {
			throw ExceptionHelper.getException(ex
					, this.getClass().getName() + " : " + "selectCustList() 에러 발생"
					,"SYS001"
					,new Object[] {"사용자 조회",ex.getMessage()} 
			);
		}
		return list;
	}

	public int selectCustListCount(UserVO userVO) throws MainException {
		int intTotalCount = 0;
    	try{
    		intTotalCount = userDAO.selectCustListCount(userVO);
		}catch(Exception ex){
           throw ExceptionHelper.getException(ex
                   , this.getClass().getName() + " : " + "selectCustListCount() 에러 발생"
                   ,"SYS001"
                   ,new Object[] {"사용자  건수조회",ex.getMessage()} 
           );
		}        

       return intTotalCount;
	}

	public List<SystemConectRoleManageVO> selectSiteList(SystemConectRoleManageVO systemConectRoleManageVO)	throws MainException {
		List<SystemConectRoleManageVO> list = null;
		try {
			list = systemConectRoleManageDAO.selectSiteList(systemConectRoleManageVO);
		} catch (Exception ex) {
			throw ExceptionHelper.getException(ex
					, this.getClass().getName() + " : " + "selectSiteList() 에러 발생"
					,"SYS001"
					,new Object[] {"사이트 목록 조회",ex.getMessage()} 
			);
		}
		return list;
	}

	public List<SystemConectRoleManageVO> selectAssignList(SystemConectRoleManageVO systemConectRoleManageVO) throws MainException {
		List<SystemConectRoleManageVO> list = null;
		try {
			list = systemConectRoleManageDAO.selectAssignList(systemConectRoleManageVO);
		} catch (Exception ex) {
			throw ExceptionHelper.getException(ex
					, this.getClass().getName() + " : " + "selectAssignList() 에러 발생"
					,"SYS001"
					,new Object[] {"사이트 허가 목록 조회",ex.getMessage()} 
			);
		}
		return list;
	}

}
