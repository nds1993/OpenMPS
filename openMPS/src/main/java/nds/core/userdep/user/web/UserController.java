package nds.core.userdep.user.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.userdep.user.service.UserService;
import nds.core.userdep.user.service.UserVO;
import nds.frm.util.EncyptShaUtil;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: DepartMentController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */

@Controller
public class UserController extends BaseController {
	/** UserService */
    @Resource(name = "userService")
    private UserService userService;
    /** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService; 
    
    /**
     * 사용자조회
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/ud/usdep0300.do")
    public ModelAndView usdep0300(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        String schUsrNm = StringUtil.getParam(request, "schUsrNm", "");
        String msg = "";
        
        List<UserVO> result = null;
        int intTotalCount  = 0;
        
        ModelAndView mav = new ModelAndView("ud/usdep0300");

        UserVO userVO = new UserVO();
        userVO.setUserNm(schUsrNm);
        
        int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
        int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));
        
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
       
        userVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
        userVO.setEndNo(pageNo * pageSize);
        userVO.setSidx(sidx);
        userVO.setSord(sord);
        
        if("".equals(crudMode)){
            result = userService.selectCustList(userVO);
            intTotalCount  = userService.selectCustListCount(userVO);
        }else if("S".equals(crudMode)){
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
        	reponse.setHeader("Cache-Control", "no-cache");
        	
        	result = userService.selectCustList(userVO);
            intTotalCount  = userService.selectCustListCount(userVO);
        }
        
        // 컬럼 설정 조회
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("usdep0300");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
        mav.addObject("rows", result);
        mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
        mav.addObject("records", intTotalCount);
        
        return mav;
    }
    
    /**
     * 사용자상세조회
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/ud/usdep0310.do")
    public ModelAndView usdep0310(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        String userId = StringUtil.getParam(request, "userId", "");
        
        ModelAndView mav = new ModelAndView("ud/usdep0310");

        UserVO tbvc = new UserVO();
        
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();
        
        if("U".equals(crudMode)) {
            UserVO userVO = new UserVO();
            bind(request, userVO);
            
        	userVO.setUpdtUser(userEmpno);

        	UserVO nowData = userService.selectByPrimaryKey(userId);
        	userVO.setCnfmYn(nowData.getCnfmYn());
        	// 부서 변경시 담당업무정보 확인
        	if(!nowData.getDepCd().equals(userVO.getDepCd())){
        		userVO.setCnfmYn("N");
        	}
        	// 담당업부정보 변경 시 담당업무정보 확인
        	if(!nowData.getWrkInfo().equals(userVO.getWrkInfo())){
        		userVO.setCnfmYn("N"); 
        	}
        	if(!"".equals(userVO.getPwd())) {
        		userVO.setPwd(EncyptShaUtil.encryptSHA(userVO.getPwd()));
        	}
        	
        	userService.updateUsr(userVO);
        }

        tbvc = userService.selectByPrimaryKey(userId);
        
        mav.addObject("result", tbvc);
        
        return mav;
    }
    
    /**
     * 사용자추가
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/ud/usdep0320.do")
    public ModelAndView usdep0320(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        
        ModelAndView mav = new ModelAndView("ud/usdep0320");
        
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();
        
        UserVO userVO = new UserVO();
        bind(request, userVO);
        
        if("C".equals(crudMode)) {
            userVO.setAdviserYn("Y");//분배가능여부
            userVO.setCnfmYn("N");//확인여부
            userVO.setPwd(EncyptShaUtil.encryptSHA(userVO.getPwd()));
            userVO.setUserEmpno(userVO.getUserId());
            userVO.setRegUser(userEmpno);
            userVO.setUpdtUser(userEmpno);
            
        	userService.insertUsr(userVO);
        	mav = new ModelAndView("ud/usdep0300");
        }

        return mav;
    }
    
    /**
     * ID중복체크(Ajax)
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/ud/usdep0321_ajax.do")
    public ModelAndView usdep0321_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = StringUtil.getParam(request, "userId", "");

        UserVO  key = new UserVO();
        key.setUserId(userId);
        
        int intTotalCount = userService.selectUserIdCount(key);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
        mav.addObject("intTotalCount", intTotalCount);

        return mav;
    }
    
    
}
