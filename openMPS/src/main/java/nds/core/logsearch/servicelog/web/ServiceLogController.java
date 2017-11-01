package nds.core.logsearch.servicelog.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.logsearch.servicelog.service.ServiceLogService;
import nds.core.logsearch.servicelog.service.ServiceLogVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

@Controller
public class ServiceLogController extends BaseController{
	/** ServiceLogService */
    @Resource(name = "serviceLogService")
    private ServiceLogService serviceLogService;
    /** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService; 
   
    @RequestMapping("/logsearch/servicelog/logsh0500.do")
    public ModelAndView logsh0500(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
    	
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
    	String schStartDd = StringUtil.getParam(request, "schStartDd", "");
    	String schEndDd = StringUtil.getParam(request, "schEndDd", "");
    	String userEmpNo = StringUtil.getParam(request, "schChrgUserId", "");
    	String depNo = StringUtil.getParam(request, "schChrgDepCd", "");
    	String schProcNm = StringUtil.getParam(request, "schProcNm", "");
    	
    	schStartDd = StringUtil.delChar(schStartDd);
        schEndDd = StringUtil.delChar(schEndDd);
        
    	ModelAndView mav = new ModelAndView("logsearch/servicelog/logsh0500");
    	
    	if("S".equals(crudMode)){
    		mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
        	reponse.setHeader("Cache-Control", "no-cache");
        	
        	 int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
             int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));

             String sidx = StringUtil.getParam(request, "sidx", "");
             String sord = StringUtil.getParam(request, "sord", "");
             
             ServiceLogVO serviceLogVO = new ServiceLogVO();
             serviceLogVO.setSchEndDd(schEndDd);
             serviceLogVO.setSchStartDd(schStartDd);
             serviceLogVO.setSchProcNm(schProcNm);
             serviceLogVO.setUserEmpNo(userEmpNo);
             serviceLogVO.setDepNo(depNo);
             serviceLogVO.setSidx(sidx);
             serviceLogVO.setSord(sord);
             serviceLogVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
             serviceLogVO.setEndNo(pageNo * pageSize);
             
             List<ServiceLogVO> result = serviceLogService.selectServiceLogList(serviceLogVO);
             int totalCount = serviceLogService.selectServiceLogCount(serviceLogVO);
             
             mav.addObject("rows", result);
             mav.addObject("records", totalCount);
             mav.addObject("total", Math.ceil((double)totalCount/pageSize));
    	}
    	// 컬럼 설정 조회
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        String cstNo = userSession.getLogin().getUserEmpno();

        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();

        myMenuColumnVO.setUserId(cstNo);
        myMenuColumnVO.setMenuId("logsh0500");

        MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);

        mav.addObject("colResult", colResult);
    	return mav;
    }
}
