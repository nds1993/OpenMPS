package nds.mpm.common.web;

import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.user.service.UserInfoService;
import nds.mpm.common.user.vo.UserInfoDefaultVO;
import nds.mpm.common.user.vo.UserInfoVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : UserInfoController.java
 * @Description : UserInfo Controller class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/common")
public class UbiController extends TMMBaseController{
	
	private static final Logger _logger = LoggerFactory.getLogger(UbiController.class);
	
	@Autowired
	protected CorsFilter		_filter;
    
	 /**
     * 
      * @Method Name : commonNotAuthUbiPrint
      * @작성일 : 2017. 10. 11.
      * @작성자 : MS
      * @변경이력 : 
      * @Method 설명 : 유비아이 공통(NOT세션)
      * @param request
      * @param model
      * @return
      * @throws Exception
     */
    @RequestMapping(value = "commonAuthUbiPrint.do")
    public String commonNotAuthUbiPrint(HttpServletRequest request,ModelMap model) throws Exception {

    	String queryString = "";
    	Enumeration<?> params = request.getParameterNames();
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    queryString +=  name+"#"+request.getParameter(name)+"#";
		   
		}
		_logger.debug("queryString=====>" + queryString);
		model.addAttribute("jrfDir",request.getParameter("jrfDir"));
		model.addAttribute("paramArray",queryString);
		
		return "common/ubi/commonUbi";

    }

    /**
     * 
      * @Method Name : commonAuthUbiPrint
      * @작성일 : 2017. 4. 20.
      * @작성자 : MS
      * @변경이력 : 
      * @Method 설명 :   유비아이 공통(세션)
      * @param request
      * @param response
      * @param model
      * @param session
      * @return
      * @throws Exception
     */
   /* @RequestMapping(value = "commonAuthUbiPrint.do")
    public String commonAuthUbiPrint(HttpServletRequest request,HttpServletResponse response,ModelMap model,HttpSession session) throws Exception {
    	SessionHelper popSessionChk = new SessionHelper();
    	popSessionChk.popSessonChk(session, response);
    	String queryString = "";
    	Enumeration<?> params = request.getParameterNames();
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    queryString +=  name+"#"+request.getParameter(name)+"#";
		   
		}
		UserSession uSession = (UserSession)session.getAttribute("uSession");
		queryString +=  "scCustKey#"+uSession.getCustKey()+"#";
		_logger.debug("queryString=====>" + queryString);
		model.addAttribute("jrfDir",request.getParameter("jrfDir"));
		model.addAttribute("paramArray",queryString);
    	return "homeP/common/ubi/commonUbi";
  
    }*/

}