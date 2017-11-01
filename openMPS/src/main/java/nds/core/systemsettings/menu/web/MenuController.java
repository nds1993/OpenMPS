package nds.core.systemsettings.menu.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.systemsettings.button.service.BtnVO;
import nds.core.systemsettings.menu.service.MenuBtnVO;
import nds.core.systemsettings.menu.service.MenuService;
import nds.core.systemsettings.menu.service.MenuVO;
import nds.core.systemsettings.rolemenu.service.RoleMenuBtnVO;
import nds.core.systemsettings.rolemenu.service.RoleMenuService;
import nds.core.systemsettings.rolemenu.service.RoleMenuVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: MenuController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */

@Controller
public class MenuController extends BaseController {
	/** MenuService */
    @Resource(name = "menuService")
    private MenuService menuService;
	/** RoleMenuService */
    @Resource(name = "roleMenuService")
    private RoleMenuService roleMenuService;
    
    
    /**
     * 뷰 페이지 관리
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/systemsettings/menu/pagemanage.do")
    public ModelAndView pagemanage(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();

        ModelAndView mav = new ModelAndView("systemsettings/menu/pagemanage");

        if("S".equals(crudMode)){
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
        	reponse.setHeader("Cache-Control", "no-cache");
        	MenuVO vo = new MenuVO();
        	List<MenuVO> list = menuService.selectPageList(vo);
        	
        	int total = 0;
        	if(list.size() > 0){
        		total = list.size();
        	}
        	
        	mav.addObject("rows", list);
        	mav.addObject("records", total);
        }else if("U".equals(crudMode)){
        	String [] contsId = request.getParameterValues("arrContsId");
        	String [] contsNm = request.getParameterValues("arrContsNm");
        	String [] urlPath = request.getParameterValues("arrUrlPath");
        	String [] inOutDvn = request.getParameterValues("arrInOutDvn");
        	String [] viewMin = request.getParameterValues("arrViewMin");
        	String [] viewSec = request.getParameterValues("arrViewSec");
        	String [] openYn = request.getParameterValues("arrOpenYn");
        	String [] pastGubn = request.getParameterValues("arrPastGubn");
        	String [] viewOrd = request.getParameterValues("arrViewOrd");
        	
        	MenuVO menuVO = new MenuVO();
        	for(int i = 0; i < contsId.length; i++){
        		menuVO.setContsId(contsId[i]);
        		menuVO.setContsNm(contsNm[i]);
        		menuVO.setUrlPath(urlPath[i]);
        		menuVO.setInOutDvn(inOutDvn[i]);
        		menuVO.setViewMin(viewMin[i]);
        		menuVO.setViewSec(viewSec[i]);
        		menuVO.setOpenYn(openYn[i]);
        		menuVO.setPastGubn(pastGubn[i]);
        		menuVO.setViewOrd(viewOrd[i]);
        		
        		menuService.updateContents(menuVO);
        	}
        	
        }else if("D".equals(crudMode)){
        	String [] contsId = request.getParameterValues("arrContsId");
        	
        	for(int i = 0; i < contsId.length; i++){
        		menuService.deleteContents(contsId[i]);
        	}
        }
        
        mav.addObject("F201", this.getCommonCode("F201"));
        mav.addObject("F025", this.getCommonCode("F025"));
        
        return mav;
    }
    
    
    /**
     * 메뉴관리
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/systemsettings/menu/syset0400.do")
    public ModelAndView syset0400(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();

        MenuVO menuVO = new MenuVO();
        MenuBtnVO menuBtnVO = new MenuBtnVO();
        
        // request정보를 담는다.
        bind(request, menuVO);
        menuBtnVO.setContsId(menuVO.getContsId());

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();

        ModelAndView mav = new ModelAndView("systemsettings/menu/syset0400");

        if(crudMode.equals("C")) {
            menuVO.setRegUser(userEmpno);
            menuVO.setUpdtUser(userEmpno);
            menuService.insertContents(menuVO);

            mav.addObject("crudMode", "");
        }
        else if(crudMode.equals("U")) {
                menuVO.setUpdtUser(userEmpno);
                menuService.updateContents(menuVO);

                mav.addObject("crudMode", "");
            }
            else
                if(crudMode.equals("D")) {
                    RoleMenuVO roleMenuVO = new RoleMenuVO();
                    RoleMenuBtnVO roleMenuBtnVO = new RoleMenuBtnVO();
                    roleMenuVO.setContsId(menuVO.getContsId());
                    roleMenuBtnVO.setContsId(menuVO.getContsId());
                    
                    menuService.deleteContents(menuVO.getContsId());

                    mav.addObject("crudMode", "");
                }
        System.out.println("Controller1111");
        List result = menuService.selectContentsAll();
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 메뉴중복체크(Ajax)
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/systemsettings/menu/syset0401_ajax.do")
    public ModelAndView syset0401_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String contsId = StringUtil.getParam(request, "contsId", "");

        MenuVO  menuVO = new MenuVO();
        menuVO.setContsId(contsId);
        
        int intTotalCount = menuService.selectContentsCount(menuVO);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
        mav.addObject("intTotalCount", intTotalCount);

        return mav;
    }

    /**
     * 버튼목록(Ajax)
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/systemsettings/menu/syset0402_ajax.do")
    public ModelAndView syset0402_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String contsId = StringUtil.getParam(request, "contsId", "");
        String filter = StringUtil.getParam(request, "filter", "");

        BtnVO key = new BtnVO();
        key.setContsId(contsId);
        key.setBtnNm(filter);

        List result = menuService.selectContsButton(key);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
        mav.addObject("result", result);

        return mav;
    }

    /**
     * 사용버튼목록(Ajax)
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/systemsettings/menu/syset0403_ajax.do")
    public ModelAndView syset0403_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String contsId = StringUtil.getParam(request, "contsId", "");

        BtnVO key = new BtnVO();
        key.setContsId(contsId);

        List result = menuService.selectContsUseButton(key);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
        mav.addObject("result", result);

        return mav;
    }


	/**
	 * 대기건수가져오기(Ajax)
	 * 
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/systemsettings/menu/getagentwaitcount_ajax.do")
	public ModelAndView getagentwaitcount_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
		MenuVO menuVO = new MenuVO();
		 List result = menuService.getAgentWaitCount(menuVO);
	
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
	    mav.addObject("result", result);
	
	    return mav;
	}
}
