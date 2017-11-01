package nds.core.userdep.roleuser.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.userdep.department.service.DepartMentService;
import nds.core.userdep.department.service.DepartMentVO;
import nds.core.userdep.roleuser.service.RoleUserService;
import nds.core.userdep.roleuser.service.RoleUserVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: RoleUserController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2015.05.12 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */

@Controller
public class RoleUserController extends BaseController {
	/** RoleMenuService */
    @Resource(name = "roleUserService")
    private RoleUserService roleUserService;
	/** DepartMentService */
    @Resource(name = "departMentService")
    private DepartMentService departMentService;

    /**
     * 역할별메뉴권한등록
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/ud/usdep0400.do")
    public ModelAndView usdep0400(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        String depCd = StringUtil.getParam(request, "depCd", "");

        ModelAndView mav = new ModelAndView("ud/usdep0400");
        
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String empno = userSession.getLogin().getUserEmpno();
        String depCdSession = userSession.getLogin().getDepCd();

        if("S".equals(crudMode)) {
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
    		
            String sidx = StringUtil.getParam(request, "sidx", "");
            String sord = StringUtil.getParam(request, "sord", "");
    		
    		RoleUserVO roleUserVO = new RoleUserVO();
    		
    		if("".equals(depCd)) {
    			depCd = depCdSession;
    		}
    		roleUserVO.setDepCd(depCd);
    		
    		roleUserVO.setSidx(sidx);
    		roleUserVO.setSord(sord);
    		
    		// 권한에 따른 조회의 구분을 위함
    		if(("Y").equals(userSession.getLogin().getDeveloper()) || ("Y").equals(userSession.getLogin().getManager())){
    			roleUserVO.setManager("Y");
            }
    		
    		mav.addObject("rows", roleUserService.selectRoleUserList(roleUserVO));
    		
    		return mav;
        } else if("U".equals(crudMode)) {
        	String userId           = StringUtil.getParam(request, "userId", "");
        	String branch           = StringUtil.getParam(request, "branch", "");
        	String generaluser      = StringUtil.getParam(request, "generaluser", "");
            String depmanager       = StringUtil.getParam(request, "depmanager", "");
            String depmanagerMstSub = StringUtil.getParam(request, "depmanagerMstSub", "");
            String sugdstruser      = StringUtil.getParam(request, "sugdstruser", "");
            String sugdstruserMstSub= StringUtil.getParam(request, "sugdstruserMstSub", "");
            String sugvocuser       = StringUtil.getParam(request, "sugvocuser", "");
            String sugvocuserMstSub = StringUtil.getParam(request, "sugvocuserMstSub", "");
            String sugmanager       = StringUtil.getParam(request, "sugmanager", "");
            String sugmanagerMstSub = StringUtil.getParam(request, "sugmanagerMstSub", "");
            String manager          = StringUtil.getParam(request, "manager", "");
    		
    		RoleUserVO roleUserVO = new RoleUserVO();
    		roleUserVO.setUserId(userId);
    		roleUserVO.setBranch(branch);
    		roleUserVO.setGeneraluser(generaluser);
    		roleUserVO.setDepmanager(depmanager);
    		roleUserVO.setDepmanagerMstSub(depmanagerMstSub);
    		roleUserVO.setSugdstruser(sugdstruser);
    		roleUserVO.setSugdstruserMstSub(sugdstruserMstSub);
    		roleUserVO.setSugvocuser(sugvocuser);
    		roleUserVO.setSugvocuserMstSub(sugvocuserMstSub);
    		roleUserVO.setSugmanager(sugmanager);
    		roleUserVO.setSugmanagerMstSub(sugmanagerMstSub);
    		roleUserVO.setManager(manager);
    		
    		roleUserVO.setRegUser(empno);
    		roleUserVO.setUpdtUser(empno);
    		
    		roleUserService.updateRoleUser(roleUserVO);
    		
    		return mav;
        }
        
        // 부서 트리 조회
        DepartMentVO org = new DepartMentVO();
        
        // 권한에 따른 조회의 구분을 위함
        if(!("Y").equals(userSession.getLogin().getDeveloper()) && !("Y").equals(userSession.getLogin().getManager())){
        	org.setDepCd(depCdSession);
        }
        
        List result = departMentService.selectOrganization(org);
        mav.addObject("result", result);
        
        return mav;
    }

    /**
     * 부서 정부 중복체크(Ajax)
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/ud/usdep0401_ajax.do")
    public ModelAndView usdep0401_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String userId = StringUtil.getParam(request, "userId", "");
    	String roleCd = StringUtil.getParam(request, "roleCd", "");

    	RoleUserVO roleUserVO = new RoleUserVO();
		roleUserVO.setUserId(userId);
		roleUserVO.setRoleCd(roleCd);
		
		int intTotalCount = roleUserService.selectDepMstCheck(roleUserVO);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
        mav.addObject("intTotalCount", intTotalCount);

        return mav;
    }

    /**
     * 역할 정부 중복체크(Ajax)
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/ud/usdep0402_ajax.do")
    public ModelAndView usdep0402_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String userId = StringUtil.getParam(request, "userId", "");
    	String roleCd = StringUtil.getParam(request, "roleCd", "");

    	RoleUserVO roleUserVO = new RoleUserVO();
    	roleUserVO.setUserId(userId);
		roleUserVO.setRoleCd(roleCd);
		
		int intTotalCount = roleUserService.selectRoleMstCheck(roleUserVO);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
        mav.addObject("intTotalCount", intTotalCount);

        return mav;
    }
}
