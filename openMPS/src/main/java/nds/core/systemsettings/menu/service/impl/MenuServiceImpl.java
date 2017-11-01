package nds.core.systemsettings.menu.service.impl;


import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import nds.core.systemsettings.button.service.BtnVO;
import nds.core.systemsettings.menu.service.MenuBtnVO;
import nds.core.systemsettings.menu.service.MenuService;
import nds.core.systemsettings.menu.service.MenuVO;
import nds.core.systemsettings.rolemenu.service.RoleMenuBtnVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : MenuServiceImpl </b>
 * <b>Class Description</b><br>
 * 메뉴관리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2012.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("menuService")
public class MenuServiceImpl extends AbstractServiceImpl implements MenuService {
    
	@Resource(name="menuDAO")
    private MenuDAO menuDAO;
    

    /**
     * 컨텐츠 조회
     * @return List
     * @throws Exception
     */
    public List selectContentsAll() throws Exception{
		List list = null;
		try {
			System.out.println("Impl 컨텐츠 조회");
			list = menuDAO.select();
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectContentsAll() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"컨텐츠트리 조회",ex.getMessage()} 
            );
		}
		return list;
    }
    
    /**
     * 컨텐츠 건수조회
     * @param menuVO
     * @return int
     * @throws Exception
     */
    public int selectContentsCount(MenuVO menuVO) throws Exception{
        int intTotalCount = 0;
        try{
        	intTotalCount = menuDAO.selectCountByHelper(menuVO);
		}catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectContentsCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"컨텐츠  건수조회",ex.getMessage()} 
            );
		}        

        return intTotalCount;
    }
    
    /**
     * 컨텐츠 등록
     * @param menuVO
     * @throws Exception
     */
    public void insertContents(MenuVO menuVO) throws Exception{
		try {
			menuDAO.insert(menuVO);
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertContents() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"컨텐츠 등록",ex.getMessage()} 
            );
		}
    }
    
    /**
     * 컨텐츠 수정
     * @param menuVO
     * @throws Exception
     */
    public void updateContents(MenuVO menuVO) throws Exception{
		try {
			menuDAO.updateByPrimaryKey(menuVO);
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateContents() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"컨텐츠 수정",ex.getMessage()} 
            );
		}
    }
    
    /**
     * 컨텐츠 삭제
     * @param contsId
     * @throws Exception
     */
    public void deleteContents(String contsId) throws Exception{
		try {
        	// 하위 컨텐츠 있는지 확인
			MenuBtnVO menuBtn = new MenuBtnVO();
        	menuBtn.setContsId(contsId);
			List<MenuBtnVO> downContsId = menuDAO.selectDownContsId(menuBtn);
            Iterator<MenuBtnVO> iter = downContsId.iterator();
            MenuBtnVO vo = null;
            
            while(iter.hasNext()) {
            	MenuBtnVO menuBtn2 = new MenuBtnVO();
            	vo = (MenuBtnVO) iter.next();
            	
            	menuBtn2.setContsId(vo.getContsId());
            	List<MenuBtnVO> downContsId2 = menuDAO.selectDownContsId(menuBtn2);
            	Iterator<MenuBtnVO> iter2 = downContsId2.iterator();
            	MenuBtnVO vo2 = null;
            	while(iter2.hasNext()) {
            		vo2 = (MenuBtnVO) iter2.next();
            		menuDAO.deleteByPrimaryKey(vo2.getContsId());
            	}
            	menuDAO.deleteByPrimaryKey(vo.getContsId());
            
            }			
			menuDAO.deleteByPrimaryKey(contsId);
			
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteContents() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"컨텐츠 삭제",ex.getMessage()} 
            );
		}
    }
    
    /**
     * 컨텐츠사용버튼 등록
     * @param menuBtnVO
     * @throws Exception
     */
    public void insertContentsButton(MenuBtnVO menuBtnVO) throws Exception{
		try {
			menuDAO.insertBtn(menuBtnVO);
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertContentsButton() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"컨텐츠 사용버튼 등록",ex.getMessage()} 
            );
		}
    }
    
    /**
     * 컨텐츠사용버튼 삭제
     * @param menuBtnVO
     * @throws Exception
     */
    public void deleteContentsButton(MenuBtnVO menuBtnVO) throws Exception{
		try {
			
	        
        	// 하위 컨텐츠 있는지 확인
			List<MenuBtnVO> downContsId = menuDAO.selectDownContsId(menuBtnVO);
        	
            Iterator<MenuBtnVO> iter = downContsId.iterator();
            MenuBtnVO vo = null;
            while(iter.hasNext()) {
            	MenuBtnVO menuBtn = new MenuBtnVO();
            	vo = (MenuBtnVO) iter.next();
            	menuBtn.setContsId(vo.getContsId());
            	
    			List<MenuBtnVO> downContsId2 = menuDAO.selectDownContsId(menuBtn);
                Iterator<MenuBtnVO> iter2 = downContsId2.iterator();
                MenuBtnVO vo2 = null;
                while(iter2.hasNext()) {
                	MenuBtnVO menuBtn2 = new MenuBtnVO();
                	vo2 = (MenuBtnVO) iter2.next();
                	menuBtn2.setContsId(vo2.getContsId());
                	menuDAO.deleteBtn(menuBtn2);
                }
                menuDAO.deleteBtn(menuBtn);
            }
            menuDAO.deleteBtn(menuBtnVO);
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteContentsButton() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"컨텐츠 사용버튼 삭제",ex.getMessage()} 
            );
		}
    }
    
    /**
     * 컨텐츠 미사용 버튼 조회
     * @param key
     * @return List
     * @throws Exception
     */
    public List selectContsButton(BtnVO btnVO) throws Exception {
        List list = null;
        try{
            list = menuDAO.selectContsButton(btnVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectContsButton() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"컨텐츠 미사용버튼 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
    
    /**
     * 컨텐츠사용버튼 조회
     * @param key
     * @return List
     * @throws Exception
     */
    public List selectContsUseButton(BtnVO btnVO) throws Exception {
		List list = null;
		try {
			list = menuDAO.selectContsUseButton(btnVO);
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectContsUseButton() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"컨텐츠 사용버튼 조회",ex.getMessage()} 
            );
		}
		return list;
	}
    
    /**
     * 뷰 페이지 목록 조회
     * @return
     * @throws Exception
     */
    public List<MenuVO> selectPageList(MenuVO menuVO) throws Exception{
    	List<MenuVO> list = null;
    	try {
			list = menuDAO.selectPageList(menuVO);
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPageList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"뷰 페이지 목록 조회",ex.getMessage()} 
            );
		}
    	return list;
    }
    
    public List<MenuVO> selectPageGubn(MenuVO menuVO) throws Exception{
    	List<MenuVO> list = null;
    	try {
			list = menuDAO.selectPageGubn(menuVO);
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPageGubn() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"뷰 페이지 목록 조회",ex.getMessage()} 
            );
		}
    	return list;
    }
    
    /* 뷰 페이지 목록 조회
    * @return
    * @throws Exception
    */
   public List<MenuVO> getAgentWaitCount(MenuVO menuVO) throws Exception{
   	List<MenuVO> list = null;
   	try {
			list = menuDAO.getAgentWaitCount(menuVO);
		} catch (Exception ex) {
           throw ExceptionHelper.getException(ex
                   , this.getClass().getName() + " : " + "getAgentWaitCount() 에러 발생"
                   ,"SYS001"
                   ,new Object[] {"뷰 페이지 목록 조회",ex.getMessage()} 
           );
		}
   	return list;
   }
    
}