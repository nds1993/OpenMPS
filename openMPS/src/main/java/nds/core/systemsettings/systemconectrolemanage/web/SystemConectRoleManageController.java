package nds.core.systemsettings.systemconectrolemanage.web;

import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.systemsettings.systemconectrolemanage.service.SystemConectRoleManageService;
import nds.core.systemsettings.systemconectrolemanage.service.SystemConectRoleManageVO;
import nds.core.userdep.department.service.DepartMentVO;
import nds.core.userdep.user.service.UserVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/systemsettings/systemconectrolemanage")
public class SystemConectRoleManageController extends BaseController {

	/** SystemConectRoleManageService */
    @Resource(name = "systemConectRoleManageService")
    private SystemConectRoleManageService systemConectRoleManageService;
	
	@RequestMapping("/systemconectrolemanage0100.do")
	 public ModelAndView systemconectrolemanage0100(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String crudMode = StringUtil.getParam(request, "crudMode", "");
		String empNo = StringUtil.getParam(request, "userId", "");
		
		ModelAndView mav = new ModelAndView("systemsettings/systemconectrolemanage/systemconectrolemanage0100");
		DepartMentVO departMentVO = new DepartMentVO();
		
		if("S".equals(crudMode)){
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			String depCd = StringUtil.getParam(request, "depCd", "");
			
			UserVO userVO = new UserVO();
			
			userVO.setDepCd(depCd);
			
			int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
	        int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "10")); // 페이지당 몇개
			    
			String sidx = StringUtil.getParam(request, "sidx", "");
			String sord = StringUtil.getParam(request, "sord", "");
			
			userVO.setStartNo(((page * rows) - rows) + 1);
			userVO.setEndNo(page * rows);
			
			userVO.setSidx(sidx);
			userVO.setSord(sord);
			
			List<UserVO> result =  systemConectRoleManageService.selectCustList(userVO);
			int intTotalCount = systemConectRoleManageService.selectCustListCount(userVO);
			
			mav.addObject("rows", result);
			mav.addObject("total", Math.ceil((double)intTotalCount/rows));  // total page
			mav.addObject("records", intTotalCount);                        // total count
		}else if("SS".equals(crudMode)){
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			SystemConectRoleManageVO systemConectRoleManageVO = new SystemConectRoleManageVO();
			systemConectRoleManageVO.setEmpNo(empNo);
			
			List<SystemConectRoleManageVO> result =  systemConectRoleManageService.selectSiteList(systemConectRoleManageVO);
			
			mav.addObject("siteRows", result);
		}else if("AS".equals(crudMode)){
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			SystemConectRoleManageVO systemConectRoleManageVO = new SystemConectRoleManageVO();
			systemConectRoleManageVO.setEmpNo(empNo);
			
			List<SystemConectRoleManageVO> result =  systemConectRoleManageService.selectAssignList(systemConectRoleManageVO);
			
			mav.addObject("assignRows", result);
		}else if("DS".equals(crudMode)){
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			SystemConectRoleManageVO systemConectRoleManageVO = new SystemConectRoleManageVO();
			systemConectRoleManageVO.setEmpNo(empNo);
			
			SystemConectRoleManageVO result = systemConectRoleManageService.selectRoleDetail(systemConectRoleManageVO);
			
			mav.addObject("data", result);
		}else if("U".equals(crudMode)){
			String arrSiteCd = StringUtil.getParam(request, "arrSiteCd", "");
			String userList =  StringUtil.getParam(request, "userList", "");
			String temp01 = StringUtil.getParam(request, "temp01", "");
			String temp02 = StringUtil.getParam(request, "temp02", "");
			String temp03 = StringUtil.getParam(request, "temp03", "");
			String temp04 = StringUtil.getParam(request, "temp04", "");
			String temp05 = StringUtil.getParam(request, "temp05", "");
			String temp06 = StringUtil.getParam(request, "temp06", "");
			String temp07 = StringUtil.getParam(request, "temp07", "");
			String temp08 = StringUtil.getParam(request, "temp08", "");
			String temp09 = StringUtil.getParam(request, "temp09", "");
			String temp10 = StringUtil.getParam(request, "temp10", "");
			
			UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
	        String user = userSession.getLogin().getUserEmpno();
	        
			SystemConectRoleManageVO systemConectRoleManageVO = new SystemConectRoleManageVO();
			systemConectRoleManageVO.setRoleList(arrSiteCd);
			systemConectRoleManageVO.setRegUser(user);
			systemConectRoleManageVO.setUpdtUser(user);
			systemConectRoleManageVO.setTemp01(temp01);
			systemConectRoleManageVO.setTemp02(temp02);
			systemConectRoleManageVO.setTemp03(temp03);
			systemConectRoleManageVO.setTemp04(temp04);
			systemConectRoleManageVO.setTemp05(temp05);
			systemConectRoleManageVO.setTemp06(temp06);
			systemConectRoleManageVO.setTemp07(temp07);
			systemConectRoleManageVO.setTemp08(temp08);
			systemConectRoleManageVO.setTemp09(temp09);
			systemConectRoleManageVO.setTemp10(temp10);
			
			StringTokenizer userId = new StringTokenizer(userList, "|");
			while (userId.hasMoreTokens()) {
				systemConectRoleManageVO.setEmpNo(userId.nextToken());
				systemConectRoleManageService.updateSiteRole(systemConectRoleManageVO);
			}
		}
			
		
		List<DepartMentVO> result =  systemConectRoleManageService.selectDepTree(departMentVO);

		mav.addObject("result", result);
		
		return mav;
	}
}
