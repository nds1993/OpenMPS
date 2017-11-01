package nds.core.systemsettings.systemconectrolemanage.service;

import java.util.List;

import nds.core.systemsettings.sitemanage.service.SiteManageVO;
import nds.core.userdep.department.service.DepartMentVO;
import nds.core.userdep.user.service.UserVO;
import nds.frm.exception.MainException;

public interface SystemConectRoleManageService {
	 /**
     * 사이트 권한 수정
     * @param systemConectRoleManageVO
     * @throws MainException
     */
    public void updateSiteRole(SystemConectRoleManageVO systemConectRoleManageVO) throws MainException;
   
    /**
     * 권한 상세 조회
     * @param systemConectRoleManageVO
     * @throws MainException
     */
    public SystemConectRoleManageVO selectRoleDetail(SystemConectRoleManageVO systemConectRoleManageVO) throws MainException;
    
    /**
     * 부서 트리 조회
     * @param departMentVO
     * @throws MainException
     */
    public List<DepartMentVO> selectDepTree(DepartMentVO departMentVO) throws MainException;
    
    /**
     * 사용자 목록 조회
     * @param userVO
     * @throws MainException
     */
    public List<UserVO> selectCustList(UserVO userVO) throws MainException;
    
    /**
     * 사용자 목록 건수 조회
     * @param userVO
     * @throws MainException
     */
    public int selectCustListCount(UserVO userVO) throws MainException;
    
    /**
     * 사이트 목록 조회
     * @param SystemConectRoleManageVO
     * @throws MainException
     */
    public List<SystemConectRoleManageVO> selectSiteList(SystemConectRoleManageVO systemConectRoleManageVO) throws MainException;
   
    /**
     * 허가 사이트 목록 조회
     * @param SystemConectRoleManageVO
     * @throws MainException
     */
    public List<SystemConectRoleManageVO> selectAssignList(SystemConectRoleManageVO systemConectRoleManageVO) throws MainException;
    
}
