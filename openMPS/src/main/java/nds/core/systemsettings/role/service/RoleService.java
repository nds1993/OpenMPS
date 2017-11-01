package nds.core.systemsettings.role.service;



import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : RoleService </b>
 * <b>Class Description</b><br>
 * 휴일 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface RoleService extends Service {
    
    /**
     * 역할코드 등록
     * @param roleVO
     * @throws Exception
     */
    void insertRole(RoleVO roleVO) throws Exception;
    
    /**
     * 역할코드 수정
     * @param roleVO
     * @throws Exception
     */
    void updateRole(RoleVO roleVO) throws Exception;
    
    /**
     * 역할코드 삭제
     * @param roleVO
     * @throws Exception
     */
    void deleteRole(RoleVO roleVO) throws Exception;
    
    /**
     * 역할코드 건수조회
     * @param roleVO
     * @return int
     * @throws Exception
     */
    int selectRoleCount(RoleVO roleVO) throws Exception;
    
    /**
     * 역할코드 조회
     * @param roleVO
     * @return List
     * @throws Exception
     */
    List selectRole(RoleVO roleVO) throws Exception;
    
}
