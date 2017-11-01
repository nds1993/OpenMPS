package nds.core.logsearch.customerlookuplog.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.logsearch.customerlookuplog.service.CustomerLookupLogService;
import nds.core.logsearch.customerlookuplog.service.CustomerLookupLogVO;
import nds.frm.startup.SYSTEM;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: CustomerLookupLogController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */

@Controller
public class CustomerLookupLogController extends BaseController {
	/** CustomerLookupLogService */
    @Resource(name = "customerLookupLogService")
    private CustomerLookupLogService customerLookupLogService;
    /** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService; 
    /**
     * 고객정보조회로그조회
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/logsearch/customerlookuplog/logsh0300.do")
    public ModelAndView logsh0300(HttpServletRequest request, HttpServletResponse reponse) throws Exception {

        String year = SYSTEM.getInstance().getYear();
        String month = SYSTEM.getInstance().getMonth();
        String day = SYSTEM.getInstance().getDay();
        String crudMode = StringUtil.getParam(request, "crudMode","");
        
        String schStartDd = StringUtil.getParam(request, "schStartDd", year + month + day);
        String schEndDd   = StringUtil.getParam(request, "schEndDd", year + month + day);
        String schUsrEmpNo= StringUtil.getParam(request, "schUsrEmpNo", "");
       	
        schStartDd = StringUtil.delChar(schStartDd);
        schEndDd = StringUtil.delChar(schEndDd);
        
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
        
        int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
        int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));

        ModelAndView mav = new ModelAndView("logsearch/customerlookuplog/logsh0300");

        CustomerLookupLogVO customerLookupLogVO = new CustomerLookupLogVO();
        bind(request, customerLookupLogVO);
        
        customerLookupLogVO.setSchStartDd(schStartDd);
        customerLookupLogVO.setSchEndDd(schEndDd);
        customerLookupLogVO.setUserEmpNo(schUsrEmpNo);
        
        customerLookupLogVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
        customerLookupLogVO.setEndNo(pageNo * pageSize);
        customerLookupLogVO.setSidx(sidx);
        customerLookupLogVO.setSord(sord);
        
        if("S".equals(crudMode)){
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
        	reponse.setHeader("Cache-Control", "no-cache");
        }
        
        List<CustomerLookupLogVO> result = customerLookupLogService.selectCstInfoLogList(customerLookupLogVO);
        int intTotalCount = customerLookupLogService.selectCstInfoLogListCnt(customerLookupLogVO);
        
        // 컬럼 설정 조회
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("logsh0300");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
        mav.addObject("rows", result);
        mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
        mav.addObject("records", intTotalCount);

        return mav;
    }
    
}
