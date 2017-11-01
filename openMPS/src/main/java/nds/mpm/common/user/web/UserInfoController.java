package nds.mpm.common.user.web;

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
@RequestMapping("/mpm/userinfo")
public class UserInfoController {
	
	private static final Logger _logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "userInfoService")
    private UserInfoService userInfoService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * USER_INFO 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 UserInfoDefaultVO
	 * @return "/userInfo/UserInfoList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectUserInfoList(
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"1"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"10"));
    	
    	UserInfoDefaultVO searchVO = new UserInfoDefaultVO();
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    			
        List<?> userInfoList = userInfoService.selectUserInfoList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = userInfoService.selectUserInfoListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(userInfoList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addUserInfo(
            @RequestBody UserInfoVO userInfoVO,
            SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
        userInfoService.insertUserInfo(userInfoVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{userCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectUserInfo(
            @PathVariable("userCode") String userCode
            ) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	 UserInfoVO userInfoVO = new UserInfoVO();
    	 userInfoVO.setUserCode(userCode);
    	
    	result.setExtraData( userInfoService.selectUserInfo(userInfoVO) );
        return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{userCode}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateUserInfo(
    		@RequestBody UserInfoVO userInfoVO, 
    		SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
        userInfoService.updateUserInfo(userInfoVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{userCode}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteUserInfo(
    		@PathVariable("userCode") String userCode,
    		SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	UserInfoVO userInfoVO = new UserInfoVO();
   	 	userInfoVO.setUserCode(userCode);
        userInfoService.deleteUserInfo(userInfoVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }

}
