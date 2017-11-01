package nds.core.systemsettings.roleuser.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.systemsettings.roleuser.service.RoleUserManageService;
import nds.core.systemsettings.roleuser.service.RoleUserVO;
import nds.core.userdep.department.service.DepartMentVO;
import nds.frm.exception.ExceptionHelper;
import nds.frm.exception.MainException;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * <b>class : RoleUserManagerServiceImpl </b> <b>Class Description</b><br>
 * <b>History</b><br>
 * 
 * <pre>
 * : 2015.12.03 초기작성(박민)
 * </pre>
 * 
 * @author <a href="mailto:mpark@nds.co.kr">박민</a>
 * @version 1.0
 */
@Service("roleUserManageService")
public class RoleUserManageServiceImpl extends AbstractServiceImpl implements RoleUserManageService {

	@Resource(name = "roleUserManageDAO")
	private RoleUserManageDAO roleUserManageDAO;

	/**
     * 역할 사용자 조회
     * @param vo
     * @return List
     * @throws Exception
     */
    public List<RoleUserVO> selectRoleUserList(RoleUserVO vo) throws MainException {
		List<RoleUserVO> list = null;
		try {
            list = roleUserManageDAO.selectRoleUserList(vo);
            return list;
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectRoleUserList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할사용자 조회 중",ex.getMessage()} 
            );
        }
	}

	/**
	 * 역할 사용자 조회
	 * @param roleUserVO
	 * @return
	 */
	public List<RoleUserVO> selectCustList(RoleUserVO roleUserVO) throws MainException {
		List<RoleUserVO> list = null;
		try {
			list = roleUserManageDAO.selectCustList(roleUserVO);
		} catch (Exception ex) {
			throw ExceptionHelper.getException(ex
					, this.getClass().getName() + " : " + "selectCustList() 에러 발생"
					,"SYS001"
					,new Object[] {"역할 사용자 조회",ex.getMessage()} 
			);
		}
		return list;
	}
	
	/**
	 * 역할 사용자 건수 조회
	 * @param roleUserVO
	 * @return
	 */
	public int selectCustListCount(RoleUserVO roleUserVO) throws MainException {
    	int intTotalCount = 0;
    	try{
    		intTotalCount = roleUserManageDAO.selectCustListCount(roleUserVO);
		}catch(Exception ex){
           throw ExceptionHelper.getException(ex
                   , this.getClass().getName() + " : " + "selectCustListCount() 에러 발생"
                   ,"SYS001"
                   ,new Object[] {"역할 사용자 조회 건수조회",ex.getMessage()} 
           );
		}        

       return intTotalCount;
	}

	/**
	 * 역할 사용자 저장
	 * @param vo
	 */
	public void insertRoleUser(RoleUserVO vo) throws MainException {
    	try{
    		roleUserManageDAO.insertRoleUser(vo);
		}catch(Exception ex){

			throw ExceptionHelper.getException(ex
                   , this.getClass().getName() + " : " + "insertRoleUser() 에러 발생"
                   ,"SYS001"
                   ,new Object[] {"역할 사용자 조회 저장",ex.getMessage()} 
           );
		}   
	}

	/**
	 * 역할 사용자 삭제
	 * @param vo
	 */
	public void deleteRoleUser(RoleUserVO vo) throws MainException {
    	try{
    		roleUserManageDAO.deleteRoleUser(vo);
		}catch(Exception ex){

			throw ExceptionHelper.getException(ex
                   , this.getClass().getName() + " : " + "deleteRoleUser() 에러 발생"
                   ,"SYS001"
                   ,new Object[] {"역할 사용자 조회 삭제",ex.getMessage()} 
           );
		}   		
	}

	/**
	 * 부서트리 조회
	 */
	@Override
	public List selectOrganization(DepartMentVO org) throws MainException {
		List list = null;
		try {
			list = roleUserManageDAO.selectOrganization(org);
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDepAll() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"컨텐츠트리 조회",ex.getMessage()} 
            );
		}
		return list;
	}
	
	
    
    
    
    
    


}