package nds.core.systemsettings.roleuser.service;

import java.util.List;

import nds.core.userdep.department.service.DepartMentVO;
import nds.frm.exception.MainException;



/**
 * <b>class : RoleUserManagerService </b>
 * <b>Class Description</b><br>
 * <b>History</b><br>
 * <pre>      : 2015.12.03 초기작성(박민)</pre>
 * @author <a href="mailto:mpark@nds.co.kr">박민</a>
 * @version 1.0
 */
public interface RoleUserManageService {

	/**
	 * 역할사용자 조회
	 * @param roleUserVO
	 * @return
	 * @throws MainException
	 */
	List<RoleUserVO> selectRoleUserList(RoleUserVO roleUserVO) throws MainException;

	/**
	 * 역할 사용자 조회
	 * @param roleUserVO
	 * @return
	 */
	List<RoleUserVO> selectCustList(RoleUserVO roleUserVO) throws MainException;

	/**
	 * 역할 사용자 건수 조회
	 * @param roleUserVO
	 * @return
	 */
	int selectCustListCount(RoleUserVO roleUserVO) throws MainException;

	/**
	 * 역할 사용자 저장
	 * @param vo
	 */
	void insertRoleUser(RoleUserVO vo) throws MainException;

	/**
	 * 역할 사용자 삭제
	 * @param vo
	 */
	void deleteRoleUser(RoleUserVO vo) throws MainException;

	/**
	 * 부서조회
	 * @param org
	 * @return
	 */
	List selectOrganization(DepartMentVO org) throws MainException;
    
}
