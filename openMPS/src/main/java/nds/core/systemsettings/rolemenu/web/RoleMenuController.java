package nds.core.systemsettings.rolemenu.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.systemsettings.button.service.BtnVO;
import nds.core.systemsettings.menu.service.MenuVO;
import nds.core.systemsettings.role.service.RoleVO;
import nds.core.systemsettings.rolemenu.service.RoleMenuBtnVO;
import nds.core.systemsettings.rolemenu.service.RoleMenuService;
import nds.core.systemsettings.rolemenu.service.RoleMenuVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: RoleController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */

@Controller
public class RoleMenuController extends BaseController {
	/** RoleMenuService */
    @Resource(name = "roleMenuService")
    private RoleMenuService roleMenuService;
    /** CommonService */
	@Resource(name = "commonService")
	private CommonService commonService; 
    /**
     * 역할별메뉴권한관리
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/systemsettings/rolemenu/syset0600.do")
    public ModelAndView syset0600(HttpServletRequest request, HttpServletResponse reponse) throws Exception {

        ModelAndView mav = new ModelAndView("systemsettings/rolemenu/syset0600");
        
        String crudMode = StringUtil.getParam(request, "crudMode", "");
		
        String[][] SCHKEY = new String[][] { { "schNAME", "이름" }, { "schID", "ID" } };
        mav.addObject("SCHKEY", SCHKEY);

        RoleVO key = new RoleVO();
        if("S".equals(crudMode)){
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
        }

        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
        
        key.setSidx(sidx);
        key.setSord(sord);

        List result = roleMenuService.selectRoleList(key);
        int intTotalCount1 = roleMenuService.selectRoleListCount(key);

        // 컬럼 설정 조회
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("syset0600");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
        mav.addObject("rows", result);
        mav.addObject("records", intTotalCount1);
        return mav;
    }

    /**
     * 역할별메뉴(권한메뉴트리)
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/systemsettings/rolemenu/syset0601_frame.do")
    public ModelAndView syset0601_frame(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();

        String roleCd = StringUtil.getParam(request, "roleCd", "").toUpperCase();
        String roleNm = StringUtil.getParam(request, "roleNm", "").toUpperCase();

        ModelAndView mav = new ModelAndView("systemsettings/rolemenu/syset0601_frame");

        if("D".equals(crudMode)) {
            RoleMenuVO roleMenuVO = new RoleMenuVO();
            // request정보를 담는다.
            bind(request, roleMenuVO);

            RoleMenuBtnVO roleMenuBtnVO = new RoleMenuBtnVO();

            roleMenuBtnVO.setRoleCd(roleMenuVO.getRoleCd());
            roleMenuBtnVO.setContsId(roleMenuVO.getContsId());

            roleMenuService.deleteRolePerConts(roleMenuVO, roleMenuBtnVO);
        }

        if(!"".equals(roleCd)) {
            request.getSession(false).setAttribute("roleCode", roleCd);
            request.getSession(false).setAttribute("roleName", roleNm);

            MenuVO menuVO = new MenuVO();

            String schKey = StringUtil.getParam(request, "schKey", "").trim();
            String filter = StringUtil.getParam(request, "filter", "").trim();

            menuVO.setRoleCd(roleCd);
            if("schID".equals(schKey)) {
            	menuVO.setContsId("%" + filter.toUpperCase() + "%");
            }
            else
                if("schNAME".equals(schKey)) {
                	menuVO.setContsNm("%" + filter.toUpperCase() + "%");
                }

            List result = roleMenuService.selectRolePerConts(menuVO);

            mav.addObject("result", result);
        }
        return mav;
    }

    /**
     * 역할별메뉴권한저장
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/systemsettings/rolemenu/syset0602_frame.do")
    public ModelAndView syset0602_frame(HttpServletRequest request, HttpServletResponse reponse) throws Exception {

        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();

        RoleMenuVO roleMenuVO = new RoleMenuVO();

        // request정보를 담는다.
        bind(request, roleMenuVO);

        ModelAndView mav = new ModelAndView("systemsettings/rolemenu/syset0602_frame");

        // 로그인 사용자정보 가져오기
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");

        String userEmpno = userSession.getLogin().getUserEmpno();

        if(crudMode.equals("U")) {
        	roleMenuVO.setUpdtUser(userEmpno);

            RoleMenuBtnVO roleMenuBtnKey = new RoleMenuBtnVO();
            roleMenuBtnKey.setRoleCd(roleMenuVO.getRoleCd());
            roleMenuBtnKey.setContsId(roleMenuVO.getContsId());

            List<RoleMenuBtnVO> roleMenuBtnListVO = new ArrayList<RoleMenuBtnVO>();
            String[] assignButtonList = request.getParameterValues("assignButtonList");
            String[] element = null;

            if(null != assignButtonList) {
                for(int i = 0; i < assignButtonList.length; i++) {
                    element = assignButtonList[i].split("[,]");

                    RoleMenuBtnVO roleMenuBtnVO = new RoleMenuBtnVO();

                    roleMenuBtnVO.setRoleCd(roleMenuVO.getRoleCd());
                    roleMenuBtnVO.setContsId(roleMenuVO.getContsId());
                    roleMenuBtnVO.setBtnId(element[0]);
                    roleMenuBtnVO.setRegUser(userEmpno);
                    roleMenuBtnVO.setUpdtUser(userEmpno);
                    roleMenuBtnListVO.add(roleMenuBtnVO);
                }
            }

            roleMenuService.updateRolePerConts(roleMenuVO, roleMenuBtnKey, roleMenuBtnListVO);
            mav.addObject("crudMode", "");
        }
        return mav;
    }

    /**
     * 역할미사용버튼목록(Ajax)
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/systemsettings/rolemenu/syset0603_ajax.do")
    public ModelAndView syset0603_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleCd = StringUtil.getParam(request, "roleCd", "");
        String contsId = StringUtil.getParam(request, "contsId", "");

        BtnVO key = new BtnVO();
        key.setRoleCd(roleCd.toUpperCase());
        key.setContsId(contsId);

        List result = roleMenuService.selectRoleButton(key);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
        mav.addObject("result", result);

        return mav;
    }

    /**
     * 역할사용버튼목록(Ajax)
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/systemsettings/rolemenu/syset0604_ajax.do")
    public ModelAndView syset0604_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleCd = StringUtil.getParam(request, "roleCd", "");
        String contsId = StringUtil.getParam(request, "contsId", "");

        BtnVO key = new BtnVO();
        key.setContsId(contsId);
        key.setRoleCd(roleCd.toUpperCase());

        List result = roleMenuService.selectRoleUseButton(key);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
        mav.addObject("result", result);

        return mav;
    }
    
    /**
     * 역할별메뉴권한등록(팝업)
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/systemsettings/rolemenu/syset0610.do")
    public ModelAndView syset0610(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();

        ModelAndView mav = new ModelAndView("systemsettings/rolemenu/syset0610");

        if(crudMode.equals("C")) {
            RoleMenuVO roleMenuVO = new RoleMenuVO();
            bind(request, roleMenuVO);

            // 로그인 사용자정보 가져오기
            UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
            String userEmpno = userSession.getLogin().getUserEmpno();

            List<RoleMenuVO> roleMenuListVO = new ArrayList<RoleMenuVO>();
            List<RoleMenuBtnVO> roleMenuBtnListVO = new ArrayList<RoleMenuBtnVO>();
            String[] contsList = request.getParameterValues("contsList");
            String[] element = null;
            if(contsList != null) {
                for(int i = 0; i < contsList.length; i++) {
                    element = contsList[i].split("[,]");

                    roleMenuVO = new RoleMenuVO();
                    roleMenuVO.setRoleCd(element[0]);
                    roleMenuVO.setContsId(element[1]);
                    roleMenuVO.setRegUser(userEmpno);
                    roleMenuVO.setUpdtUser(userEmpno);
                    roleMenuListVO.add(roleMenuVO);

                    for(int j = 2; j < element.length; j++) {
                        RoleMenuBtnVO roleMenuBtnVO = new RoleMenuBtnVO();
                        roleMenuBtnVO.setRoleCd(roleMenuVO.getRoleCd());
                        roleMenuBtnVO.setContsId(roleMenuVO.getContsId());
                        roleMenuBtnVO.setRegUser(userEmpno);
                        roleMenuBtnVO.setUpdtUser(userEmpno);
                        roleMenuBtnVO.setBtnId(element[j]);
                        roleMenuBtnListVO.add(roleMenuBtnVO);
                    }
                }
            }
            roleMenuService.insertRolePerConts(roleMenuListVO, roleMenuBtnListVO);
            
            String roleCd = StringUtil.getParam(request, "roleCd", "");
            String roleNm = StringUtil.getParam(request, "roleNm", "");
            String slidx = StringUtil.getParam(request, "slidx", "");
            mav = new ModelAndView("success_script", "scriptBlock","window.opener.fnRefresh();window.close();");
            return mav;
        }

        String roleCd = StringUtil.getParam(request, "roleCd", "");

        RoleMenuVO roleMenuVOKey = new RoleMenuVO();
        roleMenuVOKey.setRoleCd(roleCd.toUpperCase());

        List result = roleMenuService.selectContentsRoleTarget(roleMenuVOKey);

        mav.addObject("result", result);
        return mav;
    }
    
    
    
}
