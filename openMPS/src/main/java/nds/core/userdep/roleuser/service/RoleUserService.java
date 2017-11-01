package nds.core.userdep.roleuser.service;



import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : RoleMenuService </b>
 * <b>Class Description</b><br>
 *  사용자 역할 관리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2015.05.12 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
public interface RoleUserService extends Service {
    
    /**
     * 사용자 역할 조회
     * @param roleUserVO
     * @return List
     * @throws Exception
     */
    List selectRoleUserList(RoleUserVO roleUserVO) throws Exception;
    
    /**
     * 사용자 역할 수정
     * @param roleUser
     * @throws Exception
     */
    void updateRoleUser(RoleUserVO roleUserVO) throws Exception;

    /**
     * 부서 정 건수 조회
     * @param roleUserVO
     * @return int
     * @throws Exception
     */
    int selectDepMstCheck(RoleUserVO roleUserVO) throws Exception;
    
    /**
     * 역할 정 건수 조회
     * @param roleUserVO
     * @return int
     * @throws Exception
     */
    int selectRoleMstCheck(RoleUserVO roleUserVO) throws Exception;

    /**
     * 사용자 역할 조회 (역할과 정부구분으로 조회)
     * @param roleUserVO
     * @return List
     * @throws Exception
     */
    List selectRoleUserInfo(RoleUserVO roleUserVO) throws Exception;
    
    /**
     * 사용자 역할 유무 조회
     * @param roleUserVO
     * @return List
     * @throws Exception
     */
    List selectRoleDepUser(RoleUserVO roleUserVO) throws Exception;
}
