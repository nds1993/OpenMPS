package nds.core.systemsettings.role.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.systemsettings.role.service.RoleService;
import nds.core.systemsettings.role.service.RoleVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : HolidayServiceImpl </b>
 * <b>Class Description</b><br>
 * 휴일 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2012.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("roleService")
public class RoleServiceImpl extends AbstractServiceImpl implements RoleService {
    
	@Resource(name="roleDAO")
    private RoleDAO roleDAO;
    
    
    /**
     * 역할코드 등록
     * @param roleVO
     * @throws Exception
     */
    public void insertRole(RoleVO roleVO) throws Exception {
        try{
            roleDAO.insert(roleVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertRole() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할코드  등록",ex.getMessage()} 
            );
        }       
    }

    /**
     * 역할코드 수정
     * @param tbcr9300
     * @throws Exception
     */
    public void updateRole(RoleVO roleVO) throws Exception {
        try{
            roleDAO.updateByPrimaryKeySelective(roleVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateRole() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할코드  수정",ex.getMessage()} 
            );
        }       
    }
    
    /**
     * 역할코드 삭제
     * @param tbcr9300
     * @throws Exception
     */
    public void deleteRole(RoleVO roleVO) throws Exception {
        try{
            roleDAO.deleteByPrimaryKey(roleVO.getRoleCd());
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteRole() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할코드  삭제",ex.getMessage()} 
            );
        }       
    }

    /**
     * 역할코드 건수조회
     * @param helper
     * @return
     * @throws Exception
     */
    public int selectRoleCount(RoleVO roleVO) throws Exception {
        int intTotalCount = 0;
        try{
            intTotalCount = roleDAO.selectCountByHelper(roleVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectRoleCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할코드  건수조회",ex.getMessage()} 
            );
        }        
        return intTotalCount;
    }    

    /**
     * 역할코드 조회
     * @param helper
     * @return
     * @throws Exception
     */
    public List selectRole(RoleVO roleVO) throws Exception {
        List list = null;
        try{
            list = roleDAO.selectByHelper(roleVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectRole() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할코드 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
    
    
}