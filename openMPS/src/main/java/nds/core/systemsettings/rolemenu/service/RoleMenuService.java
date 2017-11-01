package nds.core.systemsettings.rolemenu.service;



import java.util.List;

import nds.core.common.common.service.Service;
import nds.core.systemsettings.button.service.BtnVO;
import nds.core.systemsettings.menu.service.MenuVO;
import nds.core.systemsettings.role.service.RoleVO;


/**
 * <b>class : RoleMenuService </b>
 * <b>Class Description</b><br>
 * 휴일 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface RoleMenuService extends Service {
    
    /**
     * 역할코드 건수조회
     * @param roleVO
     * @return int
     * @throws Exception
     */
    int selectRoleListCount(RoleVO roleVO) throws Exception;
    
    /**
     * 역할코드 조회
     * @param roleVO
     * @return list
     * @throws Exception
     */
    List selectRoleList(RoleVO roleVO) throws Exception;
    
    /**
     * 역할별컨텐츠 삭제
     * @param roleMenuVO
     * @param roleMenuBtnVO
     * @throws Exception
     */
    void deleteRolePerConts(RoleMenuVO roleMenuVO, RoleMenuBtnVO roleMenuBtnVO) throws Exception;
    
    /**
     * 역할별컨텐츠 조회
     * @param menuVO
     * @return List
     * @throws Exception
     */
    List selectRolePerConts(MenuVO menuVO) throws Exception;
    
    /**
     * 역할별컨텐츠 수정
     * @param roleMenuVO
     * @param roleMenuBtnVO
     * @param buttons
     * @throws Exception
     */
    void updateRolePerConts(RoleMenuVO roleMenuVO, RoleMenuBtnVO roleMenuBtnVO, List buttons) throws Exception;
    
    /**
     * 역할 미사용버튼 목록 조회
     * @param btnVO
     * @return List
     * @throws Exception
     */
    List selectRoleButton(BtnVO btnVO) throws Exception;
    
    /**
     * 역할 사용버튼 목록 조회
     * @param btnVO
     * @return List
     * @throws Exception
     */
    List selectRoleUseButton(BtnVO btnVO) throws Exception;
    
    /**
     * 역할별컨텐츠 등록
     * @param conts
     * @param buttons
     * @throws Exception
     */
    void insertRolePerConts(List conts, List buttons) throws Exception;
    
    /**
     * 역할별 미등록 컨텐츠 조회
     * @param roleMenuVO
     * @return List
     * @throws Exception
     */
    List selectContentsRoleTarget(RoleMenuVO roleMenuVO) throws Exception;
    
    
}
