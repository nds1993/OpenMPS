package nds.core.userdep.department.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.userdep.department.service.DepartMentService;
import nds.core.userdep.department.service.DepartMentVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: DepartMentController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */

@Controller
public class DepartMentController extends BaseController {
	/** DepartMentService */
    @Resource(name = "departMentService")
    private DepartMentService departMentService;
    
    /**
     * 부서조회
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/ud/usdep0200.do")
    public ModelAndView usdep0200(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();

        DepartMentVO departMentVO = new DepartMentVO();

        // request정보를 담는다.
        bind(request, departMentVO);

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();

        ModelAndView mav = new ModelAndView("ud/usdep0200");

        if(crudMode.equals("C")) {
        	departMentVO.setRegUser(userEmpno);
        	departMentVO.setUpdtUser(userEmpno);
            departMentService.insertDep(departMentVO);

            mav.addObject("crudMode", "");
        }
        else if(crudMode.equals("U")) {
    		departMentVO.setUpdtUser(userEmpno);
            departMentService.updateDep(departMentVO);

            mav.addObject("crudMode", "");
        }
        else if(crudMode.equals("D")) {

            departMentService.deleteDep(departMentVO);

            mav.addObject("crudMode", "");
        }
        DepartMentVO org = new DepartMentVO();
        List result = departMentService.selectOrganization(org);
        mav.addObject("result", result);
        
        return mav;
    }
    
    /**
     * 부서코드중복체크(Ajax)
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/ud/usdep0201_ajax.do")
    public ModelAndView usdep0201_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String depCd = StringUtil.getParam(request, "depCd", "");

        DepartMentVO  key = new DepartMentVO();
        key.setDepCd(depCd);
        
        int intTotalCount = departMentService.selectDepCount(key);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
        mav.addObject("intTotalCount", intTotalCount);

        return mav;
    }
    
    
}
