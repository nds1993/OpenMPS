package nds.core.systemsettings.rolemenu.service.impl;


import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import nds.core.systemsettings.button.service.BtnVO;
import nds.core.systemsettings.menu.service.MenuVO;
import nds.core.systemsettings.role.service.RoleVO;
import nds.core.systemsettings.rolemenu.service.RoleMenuBtnVO;
import nds.core.systemsettings.rolemenu.service.RoleMenuService;
import nds.core.systemsettings.rolemenu.service.RoleMenuVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : RoleMenuServiceImpl </b>
 * <b>Class Description</b><br>
 *  역할별메뉴권한관리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2012.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends AbstractServiceImpl implements RoleMenuService {

	@Resource(name="roleMenuDAO")
    private RoleMenuDAO roleMenuDAO;
	@Resource(name="roleMenuBtnDAO")
    private RoleMenuBtnDAO roleMenuBtnDAO;
	
	
    /**
     * 역할코드 건수조회
     * @param roleVO
     * @return int
     * @throws Exception
     */
    public int selectRoleListCount(RoleVO roleVO) throws Exception{
        int intTotalCount = 0;
        try{
            intTotalCount = roleMenuDAO.selectRoleListCount(roleVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectRoleListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할코드  건수조회",ex.getMessage()} 
            );
        }        
        return intTotalCount;
    }
    
    /**
     * 역할코드 조회
     * @param roleVO
     * @return list
     * @throws Exception
     */
    public List selectRoleList(RoleVO roleVO) throws Exception{
        List list = null;
        try{
            list = roleMenuDAO.selectRoleList(roleVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectRoleList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할코드 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
    
    /**
     * 역할별컨텐츠 삭제
     * @param roleMenuVO
     * @param roleMenuBtnVO
     * @throws Exception
     */
    public void deleteRolePerConts(RoleMenuVO roleMenuVO, RoleMenuBtnVO roleMenuBtnVO) throws Exception{
        try {
        
        	// 하위 컨텐츠 있는지 확인
        	@SuppressWarnings("unchecked")
			List<RoleMenuBtnVO> downContsId = roleMenuBtnDAO.selectDownContsId(roleMenuBtnVO);
        	
            Iterator<RoleMenuBtnVO> iter = downContsId.iterator();
            RoleMenuBtnVO vo = null;
            while(iter.hasNext()) {
            	RoleMenuBtnVO roleBtn = new RoleMenuBtnVO();
            	vo = (RoleMenuBtnVO) iter.next();
            	roleBtn.setContsId(vo.getContsId());
            	
            	@SuppressWarnings("unchecked")
    			List<RoleMenuBtnVO> downContsId2 = roleMenuBtnDAO.selectDownContsId(roleBtn);
            	Iterator<RoleMenuBtnVO> iter2 = downContsId2.iterator();
            	RoleMenuBtnVO vo2 = null;
            	while(iter2.hasNext()) {
            		RoleMenuBtnVO roleBtn2 = new RoleMenuBtnVO();
            		vo2 = (RoleMenuBtnVO) iter2.next();	
            		roleBtn2.setContsId(vo2.getContsId());
            		roleMenuBtnDAO.deleteRoleMenuBtn(roleBtn2);
            	}
            	roleMenuBtnDAO.deleteRoleMenuBtn(roleBtn);
            	
            }
        	
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteRolePerConts() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할별컨텐츠 삭제",ex.getMessage()} 
            );
        }
        
        try{
        	// 하위 컨텐츠 있는지 확인
        	@SuppressWarnings("unchecked")
			List<RoleMenuBtnVO> downContsId = roleMenuBtnDAO.selectDownContsId(roleMenuBtnVO);
        	
            Iterator<RoleMenuBtnVO> iter = downContsId.iterator();
            RoleMenuBtnVO vo = null;
            while(iter.hasNext()) {
            	RoleMenuBtnVO roleMenu = new RoleMenuBtnVO();
            	vo = (RoleMenuBtnVO) iter.next();
            	roleMenu.setContsId(vo.getContsId());
            	
            	@SuppressWarnings("unchecked")
    			List<RoleMenuBtnVO> downContsId2 = roleMenuBtnDAO.selectDownContsId(roleMenu);
            	Iterator<RoleMenuBtnVO> iter2 = downContsId2.iterator();
            	RoleMenuBtnVO vo2 = null;
            	while(iter2.hasNext()) {
            		RoleMenuVO roleMenu2 = new RoleMenuVO();
            		vo2 = (RoleMenuBtnVO) iter2.next();	
            		roleMenu2.setContsId(vo2.getContsId());
            		roleMenuDAO.deleteByPrimaryKey(roleMenu2);
            	}
            	RoleMenuVO roleMenu3 = new RoleMenuVO();
            	roleMenu3.setContsId(vo.getContsId());
            	roleMenuDAO.deleteByPrimaryKey(roleMenu3);
            }
            
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteRolePerConts() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할별컨텐츠 버튼권한 삭제",ex.getMessage()} 
            );
        }
    }
    
    /**
     * 역할별컨텐츠 조회
     * @param menuVO
     * @return List
     * @throws Exception
     */
    public List selectRolePerConts(MenuVO menuVO) throws Exception{
        List list = null;
        try{
            list = roleMenuDAO.selectRoleMenuTree(menuVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectRolePerConts() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할별컨텐츠 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
    
    /**
     * 역할별컨텐츠 수정
     * @param roleMenuVO
     * @param roleMenuBtnVO
     * @param buttons
     * @throws Exception
     */
    public void updateRolePerConts(RoleMenuVO roleMenuVO, RoleMenuBtnVO roleMenuBtnVO, List buttons) throws Exception{
        try{
        	roleMenuDAO.updateByPrimaryKeySelective(roleMenuVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateRolePerConts() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할별컨텐츠 수정",ex.getMessage()} 
            );
        }       

        try {
        	roleMenuBtnDAO.deleteRoleMenuBtn(roleMenuBtnVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateRolePerConts() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할별컨텐츠 버튼권한 수정",ex.getMessage()} 
            );
        }
        
        try {
            Iterator iter = buttons.iterator();
            while(iter.hasNext()){
                RoleMenuBtnVO roleMenuBtnVOSet = (RoleMenuBtnVO) iter.next();
                roleMenuBtnDAO.insert(roleMenuBtnVOSet);
            }
        
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateRolePerConts() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할별컨텐츠 버튼권한 수정",ex.getMessage()} 
            );
        }
    }
    
    /**
     * 역할 미사용버튼 목록 조회
     * @param btnVO
     * @return List
     * @throws Exception
     */
    public List selectRoleButton(BtnVO btnVO) throws Exception{
        List list = null;
        try{
            list = roleMenuDAO.selectRoleButton(btnVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectRoleButton() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할별 미사용버튼 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
    
    /**
     * 역할 사용버튼 목록 조회
     * @param btnVO
     * @return List
     * @throws Exception
     */
    public List selectRoleUseButton(BtnVO btnVO) throws Exception{
        List list = null;
        try{
            list = roleMenuBtnDAO.selectRoleUseButton(btnVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectRoleUseButton() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할별 사용가능버튼 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
    
    /**
     * 역할별컨텐츠 등록
     * @param conts
     * @param buttons
     * @throws Exception
     */
    public void insertRolePerConts(List conts, List buttons) throws Exception{
        try{
            Iterator iter = conts.iterator();
            while(iter.hasNext()){
                RoleMenuVO roleMenuVO = (RoleMenuVO) iter.next();
                roleMenuDAO.insert(roleMenuVO);
            }
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertRolePerConts() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할별컨텐츠 등록",ex.getMessage()} 
            );
        }   
        
        try {
            Iterator iter = buttons.iterator();
            while(iter.hasNext()){
                RoleMenuBtnVO roleMenuBtnVO = (RoleMenuBtnVO) iter.next();
                roleMenuBtnDAO.insert(roleMenuBtnVO);
            }
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertRolePerConts() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할별컨텐츠 버튼권한 등록",ex.getMessage()} 
            );
        }
    }
    
    /**
     * 역할별 미등록 컨텐츠 조회
     * @param roleMenuVO
     * @return List
     * @throws Exception
     */
    public List selectContentsRoleTarget(RoleMenuVO roleMenuVO) throws Exception{
        List list = null;
        try {
            list = roleMenuDAO.selectRoleTarget(roleMenuVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectContentsRoleTarget() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"역할별 미등록 컨텐츠 조회",ex.getMessage()} 
            );
        }
        return list;
    }
   
    
}