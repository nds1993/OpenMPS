package nds.core.logsearch.loginlog.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.logsearch.loginlog.service.LoginLogService;
import nds.core.logsearch.loginlog.service.LoginLogVO;
import nds.frm.startup.SYSTEM;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: LoginLogController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */

@Controller
public class LoginLogController extends BaseController {
	/** LoginLogService */
    @Resource(name = "loginLogService")
    private LoginLogService loginLogService;
    /** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService; 

    /**
     * 로그인로그조회
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/logsearch/loginlog/logsh0100.do")
    public ModelAndView logsh0100(HttpServletRequest request, HttpServletResponse reponse) throws Exception {

        String year = SYSTEM.getInstance().getYear();
        String month = SYSTEM.getInstance().getMonth();
        String day = SYSTEM.getInstance().getDay();
        String crudMode = StringUtil.getParam(request, "crudMode", "");
        String schStartDd = StringUtil.getParam(request, "schStartDd", year + month + day);
        String schEndDd   = StringUtil.getParam(request, "schEndDd", year + month + day);
        String schUserId= StringUtil.getParam(request, "schUsrEmpNo", "");
        String cnctYn= StringUtil.getParam(request, "schCnctYn", "");
        
        schStartDd = StringUtil.delChar(schStartDd);
        schEndDd = StringUtil.delChar(schEndDd);
        
        int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
        int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));

        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
        
        ModelAndView mav = new ModelAndView("logsearch/loginlog/logsh0100");

        LoginLogVO loginLogVO = new LoginLogVO();
        bind(request, loginLogVO);
        
        loginLogVO.setSchStartDd(schStartDd+"000000");
        loginLogVO.setSchEndDd(schEndDd+"235959");
        loginLogVO.setUserId(schUserId);
        loginLogVO.setCnctYn(cnctYn);
        if("S".equals(crudMode)){
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
        	reponse.setHeader("Cache-Control", "no-cache");
        }
        loginLogVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
        loginLogVO.setEndNo(pageNo * pageSize);
        loginLogVO.setSidx(sidx);
        loginLogVO.setSord(sord);
        
        List<LoginLogVO> result = loginLogService.selectLoginLogList(loginLogVO);
        int intTotalCount = loginLogService.selectLoginLogListCount(loginLogVO);
        int connectUserCnt = loginLogService.selectLoginConnectCount(loginLogVO);

        // 컬럼 설정 조회
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("logsh0100");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
		mav.addObject("F151", this.getCommonCode("F151"));
		mav.addObject("F152", this.getCommonCode("F152"));
        mav.addObject("colResult", colResult);
        mav.addObject("rows", result);
        mav.addObject("records", intTotalCount);
        mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
        mav.addObject("connectUserCnt", connectUserCnt);
        
        return mav;
    }
}
