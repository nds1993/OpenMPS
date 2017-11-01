package nds.core.userdep.roleuser.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.userdep.department.service.impl.DepartMentDAO;
import nds.core.userdep.roleuser.service.RoleUserService;
import nds.core.userdep.roleuser.service.RoleUserVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : RoleUserServiceImpl </b>
 * <b>Class Description</b><br>
 *  사용자 역할 관리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2015.05.12 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Service("roleUserService")
public class RoleUserServiceImpl extends AbstractServiceImpl implements RoleUserService {

	@Resource(name="roleUserDAO")
    private RoleUserDAO roleUserDAO;
	@Resource(name="departMentDAO")
    private DepartMentDAO departMentDAO;
	
    
    /**
     * 사용자 역할 조회
     * @param roleUserVO
     * @return List
     * @throws Exception
     */
    public List selectRoleUserList(RoleUserVO roleUserVO) throws Exception {
    	List list = null;
        try{
            list = roleUserDAO.selectRoleUserList(roleUserVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectRoleUserList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사용자 역할 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
    
    
    /**
     * 사용자 역할 수정
     * @param roleUser
     * @throws Exception
     */
    public void updateRoleUser(RoleUserVO roleUserVO) throws Exception {
        try{
        	if("Y".equals(roleUserVO.getBranch())){
        		roleUserVO.setRoleCd("BRANCH");
        		
        		roleUserDAO.deleteRoleUser(roleUserVO);
            	roleUserDAO.insertRoleUser(roleUserVO); 
        	} else {
        		roleUserVO.setRoleCd("BRANCH");
        		roleUserDAO.deleteRoleUser(roleUserVO);
        	}
        	if("Y".equals(roleUserVO.getGeneraluser())){
        		roleUserVO.setRoleCd("GENERALUSER");
        		
        		roleUserDAO.deleteRoleUser(roleUserVO);
            	roleUserDAO.insertRoleUser(roleUserVO); 
        	} else {
        		roleUserVO.setRoleCd("GENERALUSER");
        		roleUserDAO.deleteRoleUser(roleUserVO);
        	}
        	if("Y".equals(roleUserVO.getDepmanager())){
        		roleUserVO.setRoleCd("DEPMANAGER");
        		roleUserVO.setMstSub(roleUserVO.getDepmanagerMstSub());
        		
        		roleUserDAO.deleteRoleUser(roleUserVO);
            	roleUserDAO.insertRoleUser(roleUserVO); 
        	} else {
        		roleUserVO.setRoleCd("DEPMANAGER");
        		roleUserDAO.deleteRoleUser(roleUserVO);
        	}
        	if("Y".equals(roleUserVO.getSugdstruser())){
        		roleUserVO.setRoleCd("SUGDSTRUSER");
        		roleUserVO.setMstSub(roleUserVO.getSugdstruserMstSub());
        		
        		roleUserDAO.deleteRoleUser(roleUserVO);
            	roleUserDAO.insertRoleUser(roleUserVO); 
        	} else {
        		roleUserVO.setRoleCd("SUGDSTRUSER");
        		roleUserDAO.deleteRoleUser(roleUserVO);
        	}
        	if("Y".equals(roleUserVO.getSugvocuser())){
        		roleUserVO.setRoleCd("SUGVOCUSER");
        		roleUserVO.setMstSub(roleUserVO.getSugvocuserMstSub());
        		
        		roleUserDAO.deleteRoleUser(roleUserVO);
            	roleUserDAO.insertRoleUser(roleUserVO); 
        	} else {
        		roleUserVO.setRoleCd("SUGVOCUSER");
        		roleUserDAO.deleteRoleUser(roleUserVO);
        	}
	        if("Y".equals(roleUserVO.getSugmanager())){
	    		roleUserVO.setRoleCd("SUGMANAGER");
	    		roleUserVO.setMstSub(roleUserVO.getSugmanagerMstSub());
	    		
	    		roleUserDAO.deleteRoleUser(roleUserVO);
	        	roleUserDAO.insertRoleUser(roleUserVO); 
	    	} else {
	    		roleUserVO.setRoleCd("SUGMANAGER");
	    		roleUserDAO.deleteRoleUser(roleUserVO);
	    	}
        	if("Y".equals(roleUserVO.getManager())){
        		roleUserVO.setRoleCd("MANAGER");
        		
        		roleUserDAO.deleteRoleUser(roleUserVO);
            	roleUserDAO.insertRoleUser(roleUserVO); 
        	} else {
        		roleUserVO.setRoleCd("MANAGER");
        		roleUserDAO.deleteRoleUser(roleUserVO);
        	}
        	
        	roleUserDAO.updateUserCnfmYn(roleUserVO);
        	
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateRoleUser() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사용자 역할 조회",ex.getMessage()} 
            );
        }        
    }

    /**
     * 부서 정 건수 조회
     * @param chManagerVO
     * @return int
     * @throws Exception
     */
	public int selectDepMstCheck(RoleUserVO roleUserVO) throws Exception {
		int count = 0;
        try{
        	count = roleUserDAO.selectDepMstCheck(roleUserVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDepMstCheck() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"부서정 건수 조회",ex.getMessage()} 
            );
        }        
        return count;
    }

    /**
     * 역할 정 건수 조회
     * @param chManagerVO
     * @return int
     * @throws Exception
     */
	public int selectRoleMstCheck(RoleUserVO roleUserVO) throws Exception {
		int count = 0;
        try{
        	count = roleUserDAO.selectRoleMstCheck(roleUserVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectRoleMstCheck() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할 정 건수 조회",ex.getMessage()} 
            );
        }        
        return count;
    }
	

    /**
     * 사용자 역할 조회 (역할과 정부구분으로 조회)
     * @param roleUserVO
     * @return List
     * @throws Exception
     */
    public List selectRoleUserInfo(RoleUserVO roleUserVO) throws Exception {
    	List list = null;
        try{
            list = roleUserDAO.selectRoleUserInfo(roleUserVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectRoleUserInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사용자 역할 조회 (역할과 정부구분으로 조회)",ex.getMessage()} 
            );
        }        
        return list;
    }
    
    
    
    /**
     * 사용자 역할 유무 조회
     * @param chManagerVO
     * @return int
     * @throws Exception
     */
	public List selectRoleDepUser(RoleUserVO roleUserVO) throws Exception {
		List list = null;
        try{
        	list = roleUserDAO.selectRoleDepUser(roleUserVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectRoleDepUser() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사용자 역할 유무 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
}