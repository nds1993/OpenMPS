package nds.core.logsearch.systemlog.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.logsearch.systemlog.service.SystemLogService;
import nds.core.logsearch.systemlog.service.SystemLogVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

@Controller
public class SystemLogController {
	/** ServiceLogService */
    @Resource(name = "systemLogService")
    private SystemLogService systemLogService;
    /** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService; 
    
    @RequestMapping("/logsearch/systemlog/logsh0600.do")
    public ModelAndView logsh0600(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
    	
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
    	String schStartDd = StringUtil.getParam(request, "schStartDd", "");
    	String schEndDd = StringUtil.getParam(request, "schEndDd", "");
    	String userEmpNo = StringUtil.getParam(request, "schChrgUserId", "");
    	String depNo = StringUtil.getParam(request, "schChrgDepCd", "");
    	String schContsId = StringUtil.getParam(request, "schContsId", "");
    	
    	schStartDd = StringUtil.delChar(schStartDd);
        schEndDd = StringUtil.delChar(schEndDd);
        
    	ModelAndView mav = new ModelAndView("logsearch/systemlog/logsh0600");
    	
    	if("S".equals(crudMode)){
    		mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
        	reponse.setHeader("Cache-Control", "no-cache");
        	
        	 int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
             int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));

             String sidx = StringUtil.getParam(request, "sidx", "");
             String sord = StringUtil.getParam(request, "sord", "");
             
             SystemLogVO systemLogVO = new SystemLogVO();
             systemLogVO.setSchEndDd(schEndDd);
             systemLogVO.setSchStartDd(schStartDd);
             systemLogVO.setSchContsId(schContsId);
             systemLogVO.setUserEmpNo(userEmpNo);
             systemLogVO.setDepNo(depNo);
             systemLogVO.setSidx(sidx);
             systemLogVO.setSord(sord);
             systemLogVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
             systemLogVO.setEndNo(pageNo * pageSize);
             
             List<SystemLogVO> result = systemLogService.selectSystemLogList(systemLogVO);
             int totalCount = systemLogService.selectSystemLogCount(systemLogVO);
             
          
             mav.addObject("rows", result);
             mav.addObject("records", totalCount);
             mav.addObject("total", Math.ceil((double)totalCount/pageSize));
    	}
    	// 컬럼 설정 조회
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        String cstNo = userSession.getLogin().getUserEmpno();

        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();

        myMenuColumnVO.setUserId(cstNo);
        myMenuColumnVO.setMenuId("logsh0600");

        MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);

        mav.addObject("colResult", colResult);
        
    	return mav;
    }
}
