package nds.core.systemsettings.sitemanage.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.systemsettings.sitemanage.service.SiteManageService;
import nds.core.systemsettings.sitemanage.service.SiteManageVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/systemsettings/sitemanage1")
public class SiteManageController extends BaseController{
	
	/** SiteManageService */
    @Resource(name = "siteManageService")
    private SiteManageService siteManageService;
	
	@RequestMapping("/sitemanage01001.do")
	 public ModelAndView sitemanage0100(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String crudMode = StringUtil.getParam(request, "crudMode", "");
		String siteCd   = StringUtil.getParam(request, "siteCd", "");
		String siteNm   = StringUtil.getParam(request, "siteNm", "");
		String url   = StringUtil.getParam(request, "url", "");
		String useYn   = StringUtil.getParam(request, "useYn", "");
		String temp01   = StringUtil.getParam(request, "temp01", "");
		String temp02   = StringUtil.getParam(request, "temp02", "");
		String temp03   = StringUtil.getParam(request, "temp03", "");
		
		// 로그인 사용자정보 가져오기
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String empNo = userSession.getLogin().getUserEmpno();
        
		ModelAndView mav = new ModelAndView("systemsettings/sitemanage/sitemanage0100");
		
		SiteManageVO siteManageVO = new SiteManageVO();
		siteManageVO.setSiteCd(siteCd);
		siteManageVO.setSiteNm(siteNm);
		siteManageVO.setUrl(url);
		siteManageVO.setUseYn(useYn);
		siteManageVO.setTemp01(temp01);
		siteManageVO.setTemp02(temp02);
		siteManageVO.setTemp03(temp03);
		siteManageVO.setRegUser(empNo);
		siteManageVO.setUpdtUser(empNo);
		
		if("S".equals(crudMode)){
			mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
    		
    		String schSiteNm = StringUtil.getParam(request, "schSiteNm", "");
    		String schUseYn = StringUtil.getParam(request, "schUseYn", "");		
    		
    		int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
            int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "10")); // 페이지당 몇개
    		    
    		String sidx = StringUtil.getParam(request, "sidx", "");
    		String sord = StringUtil.getParam(request, "sord", "");
    		
    		siteManageVO.setSiteNm(schSiteNm);
    		siteManageVO.setUseYn(schUseYn);
    		
    		siteManageVO.setStartNo(((page * rows) - rows) + 1);
    		siteManageVO.setEndNo(page * rows);
    		
    		siteManageVO.setSidx(sidx);
    		siteManageVO.setSord(sord);
    		
    		List<SiteManageVO> result = siteManageService.selectSite(siteManageVO);
    		int intTotalCount = siteManageService.selectSiteCount(siteManageVO);
    		
    		mav.addObject("rows", result);
			mav.addObject("total", Math.ceil((double)intTotalCount/rows));  // total page
			mav.addObject("records", intTotalCount);                        // total count
    		
		}else if("C".equals(crudMode)){
			siteManageService.insertSite(siteManageVO);
			
			mav.addObject("crudMode", "");
		}else if("U".equals(crudMode)){
			siteManageService.updateSite(siteManageVO);
			
			mav.addObject("crudMode", "");
		}else if("D".equals(crudMode)){
			siteManageService.deleteSite(siteManageVO);
			
			mav.addObject("crudMode", "");
		}
		
		mav.addObject("F153", this.getCommonCode("F153"));
		
		return mav;
	}
}
