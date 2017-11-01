package nds.core.userdep.chmanager.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.userdep.chmanager.service.ChManagerService;
import nds.core.userdep.chmanager.service.ChManagerVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: ChManagerController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2015.05.12 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */

@Controller
public class ChManagerController extends BaseController {
	/** ChManagerService */
    @Resource(name = "chManagerService")
    private ChManagerService chManagerService;
    
    /**
     * 부서조회
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/ud/usdep0100.do")
    public ModelAndView usdep0100(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
        
        ChManagerVO chManagerVO = new ChManagerVO();

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();

        ModelAndView mav = new ModelAndView("ud/usdep0100");

        if("C".equals(crudMode)) {
        	bind(request, chManagerVO);
        	
        	chManagerVO.setRegUser(userEmpno);
        	chManagerVO.setUpdtUser(userEmpno);
        	
        	chManagerService.insertChnlManager(chManagerVO);
        }
        else if("D".equals(crudMode)) {
        	bind(request, chManagerVO);
        	
        	chManagerService.deleteChnlManager(chManagerVO);
        }
        
        if("S1".equals(crudMode)) {
    		mav = new ModelAndView("jsonView");
    		reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
    		
            chManagerVO.setSidx(sidx);
    		chManagerVO.setSord(sord);
    		
    		mav.addObject("rows", chManagerService.selectChnlList(chManagerVO));
        }
        if("S2".equals(crudMode)) {
        	mav = new ModelAndView("jsonView");
    		reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
    		
    		String chnlCd = StringUtil.getParam(request, "chnlCd", "");
    		
    		ChManagerVO key = new ChManagerVO();
    		key.setChnlCd(chnlCd);
    		key.setSidx(sidx);
    		key.setSord(sord);
    		
    		mav.addObject("rows", chManagerService.selectChnlManagerList(key));
        }
        return mav;
    }
    
    /**
     * 정부 중복체크(Ajax)
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/ud/usdep0101_ajax.do")
    public ModelAndView usdep0101_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String chnlCd = StringUtil.getParam(request, "chnlCd", "");

        ChManagerVO  key = new ChManagerVO();
        key.setMstSub("MA");
        key.setChnlCd(chnlCd);
        
        int intTotalCount = chManagerService.selectChnlMstCount(key);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
        mav.addObject("intTotalCount", intTotalCount);

        return mav;
    }
    
    /**
     * 사용자 중복체크(Ajax)
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/ud/usdep0102_ajax.do")
    public ModelAndView usdep0102_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String userId = StringUtil.getParam(request, "userId", "");
    	String chnlCd = StringUtil.getParam(request, "chnlCd", "");
        
        ChManagerVO  key = new ChManagerVO();
        key.setUserId(userId);
        key.setChnlCd(chnlCd);
        
        int intTotalCount = chManagerService.selectChnlUserCount(key);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
        mav.addObject("intTotalCount", intTotalCount);

        return mav;
    }
}
