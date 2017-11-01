package nds.core.systemsettings.role.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.systemsettings.role.service.RoleService;
import nds.core.systemsettings.role.service.RoleVO;
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
public class RoleController extends BaseController {
	/** RoleService */
	@Resource(name = "roleService")
	private RoleService roleService;
	/** CommonService */
	@Resource(name = "commonService")
	private CommonService commonService; 

	/**
	 * 역할관리
	 * 
	 * @param request
	 * @param reponse
	 */
	@RequestMapping("/systemsettings/role/syset0500.do")
	public ModelAndView syset0500(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();

		RoleVO roleVO = new RoleVO();
		// request정보를 담는다.
		bind(request, roleVO);

		// 로그인 사용자정보 가져오기
		UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();

		ModelAndView mav = new ModelAndView("systemsettings/role/syset0500");

		if(crudMode.equals("C")) {
			roleVO.setRegUser(userEmpno);
			roleVO.setUpdtUser(userEmpno);
			roleService.insertRole(roleVO);
			mav.addObject("crudMode", "");
		}
		else if(crudMode.equals("U")) {
			roleVO.setUpdtUser(userEmpno);
			roleService.updateRole(roleVO);
			mav.addObject("crudMode", "");
		}
		else if(crudMode.equals("D")) {
			roleService.deleteRole(roleVO);
			mav.addObject("crudMode", "");
		}else if("S".equals(crudMode)) {
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
		}

		RoleVO key = new RoleVO();
		// helper.setOrderByClause("A.ROLE_CD");
		// Tbcr9300Helper.Criteria c1 = helper.createCriteria();
		// c1.andRoleNmLike("%" + StringUtil.getParam(request, "schroleNm",
		// "").toUpperCase() + "%");
		key.setRoleNm(StringUtil.getParam(request, "schroleNm", "").toUpperCase());

		int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
		int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "5"));
		
		String sidx = StringUtil.getParam(request, "sidx", "");
		String sord = StringUtil.getParam(request, "sord", "");

		key.setStartNo(((pageNo * pageSize) - pageSize) + 1);
		key.setEndNo(pageNo * pageSize);
		key.setSidx(sidx);
		key.setSord(sord);

		List result = roleService.selectRole(key);
		int intTotalCount = roleService.selectRoleCount(key);
		
		// 컬럼 설정 조회
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("syset0500");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
        mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
		mav.addObject("rows", result);
		mav.addObject("records", intTotalCount);

		return mav;
	}

	/**
	 * 역할코드중복체크(Ajax)
	 * 
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/systemsettings/role/syset0501_ajax.do")
	public ModelAndView syset0501_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String roleCd = StringUtil.getParam(request, "roleCd", "");

		RoleVO key = new RoleVO();
		key.setRoleCd(roleCd.toUpperCase());
		int intTotalCount = roleService.selectRoleCount(key);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");

		mav.addObject("intTotalCount", intTotalCount);

		return mav;
	}



}
