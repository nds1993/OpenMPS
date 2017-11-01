package nds.core.logsearch.dwloadlog.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.logsearch.dwloadlog.service.DwloadLogService;
import nds.core.logsearch.dwloadlog.service.DwloadLogVO;
import nds.frm.startup.SYSTEM;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: DwloadLogController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2015.05.20 초기작성(김세희)</pre>
 * @author <a href="mailto:shkim@nds.co.kr">김세희</a>
 * @version 1.0
 */

@Controller
public class DwloadLogController extends BaseController{
    /** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService; 
    /** DwloadLogService */
    @Resource(name = "dwloadLogService")
    private DwloadLogService dwloadLogService;
    
    /**
     * 다운로드로그조회
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/logsearch/dwloadlog/logsh0400.do")
    public ModelAndView logsh0400(HttpServletRequest request, HttpServletResponse reponse) throws Exception{
		String year = SYSTEM.getInstance().getYear();
		String month = SYSTEM.getInstance().getMonth();
		String day = SYSTEM.getInstance().getDay();
		String crudMode = StringUtil.getParam(request, "crudMode", "");
		
	    String schStartDd = StringUtil.getParam(request, "schStartDd", year + month + day);
	    String schEndDd   = StringUtil.getParam(request, "schEndDd", year + month + day);
	    String schUsrEmpNo= StringUtil.getParam(request, "schUsrEmpNo", "");
	   	
	    schStartDd = StringUtil.delChar(schStartDd);
	    schEndDd = StringUtil.delChar(schEndDd);
	    
	    String sidx = StringUtil.getParam(request, "sidx", "");
	    String sord = StringUtil.getParam(request, "sord", "");
	    
	    int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
	    int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));
	
	    ModelAndView mav = new ModelAndView("logsearch/dwloadlog/logsh0400");

    	DwloadLogVO dwloadLogVO = new DwloadLogVO();
    	bind(request, dwloadLogVO);
    	
        dwloadLogVO.setSchStartDd(schStartDd);
        dwloadLogVO.setSchEndDd(schEndDd);
        dwloadLogVO.setRegUser(schUsrEmpNo);
        
        dwloadLogVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
        dwloadLogVO.setEndNo(pageNo * pageSize);
        dwloadLogVO.setSidx(sidx);
        dwloadLogVO.setSord(sord);
        
        if("S".equals(crudMode)){
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
        	reponse.setHeader("Cache-Control", "no-cache");
        	
        	List<DwloadLogVO> result = dwloadLogService.selectDwInfoLogList(dwloadLogVO);
            int intTotalCount = dwloadLogService.selectDwInfoLogListCnt(dwloadLogVO);
            
            mav.addObject("rows", result);
            mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
            mav.addObject("records", intTotalCount);
        }
        
        // 컬럼 설정 조회
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("logsh0400");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
    	return mav;
    	
    }

}
