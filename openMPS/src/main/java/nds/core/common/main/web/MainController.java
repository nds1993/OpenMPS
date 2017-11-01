package nds.core.common.main.web;



import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.AttachfileService;
import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.common.main.service.MainService;
import nds.core.common.main.service.MainVO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: MainController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.26 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Controller
public class MainController extends BaseController {
	
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
    /** MainService */
    @Resource(name = "mainService")
    private MainService mainService;
	/** AttachfileService */
    @Resource(name = "attachfileService")
    private AttachfileService attachfileService;
    
    /**
     * vocmain 페이지
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/vocmain.do")
    public ModelAndView vocmain(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //Session info
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userId = userSession.getLogin().getUserEmpno();

        UserSession uskin = new UserSession(userSession.getLogin(), commonService.getSkinInfo(userSession.getSkinNo()));
        ModelAndView mav = new ModelAndView(uskin.getSkinViewFolder() + "/vocmain");
        mav.addObject("uskin", uskin);
        
        //reload 시간 설정
        ResourceBundle rb = ResourceBundle.getBundle("nds.frm.config.vocmain");
        String setTime = rb.getString("mainsettime");
        String setMax = rb.getString("widgetMax");
        
        int resultCount = mainService.selectWidgetsListCount(userId);
        List<MainVO> list = mainService.selectWidgetsList(userId);

        mav.addObject("widgetMax", setMax);
        mav.addObject("reloadTime", setTime);
        mav.addObject("resultCount", resultCount);
        mav.addObject("resultWidgets", list);
        return mav; 
        
    }
    
    /**
     * rmsmain 페이지
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/skin/default/rmsmain.do")
    public ModelAndView rmsmain(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //Session info
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userId = userSession.getLogin().getUserEmpno();
        System.out.println("main PAGE 호출");

        UserSession uskin = new UserSession(userSession.getLogin(), commonService.getSkinInfo(userSession.getSkinNo()));
        ModelAndView mav = new ModelAndView("skin/default/rmsmain");
        mav.addObject("uskin", uskin);
        
        return mav; 
        
    }

}
