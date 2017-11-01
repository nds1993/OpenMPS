package nds.mpm.common.user.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.user.service.ScreUserService;
import nds.mpm.common.user.vo.ScreUserDefaultVO;
import nds.mpm.common.user.vo.ScreUserVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;

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
 * @Class Name : ScreUserController.java
 * @Description : ScreUser Controller class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/screuser")
public class ScreUserController {
	
	private static final Logger _logger = LoggerFactory.getLogger(ScreUserController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "screUserService")
    private ScreUserService screUserService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * scre_user 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 ScreUserDefaultVO
	 * @return "/screUser/ScreUserList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{userCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectScreUserList(
    		@PathVariable("userCode") String userCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	ScreUserVO searchVO = new ScreUserVO();
    	searchVO.setUserCode(userCode);
        List<?> screUserList = screUserService.selectScreUserList(searchVO);
        
        PageSet pageSet = new PageSet();
    	pageSet.setResult(screUserList);
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
   
    @RequestMapping(value="/{userCode}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addScreUser(
    		@PathVariable("userCode") String userCode,
    		@RequestBody ScreUserVO screUserVO, 
    		SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
        screUserService.insertScreUser(screUserVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{userCode}/{screNo}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectScreUser(
    		@PathVariable("userCode") String userCode,
    		@PathVariable("screNo") int screNo
            ) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	ScreUserVO screUserVO = new ScreUserVO();
    	
    	screUserVO.setUserCode(userCode);
    	screUserVO.setScreNo(screNo);
    	
    	result.setExtraData(screUserService.selectScreUser(screUserVO));
    	return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{userCode}/{screNo}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateScreUser(
    		@PathVariable("userCode") String userCode,
    		@PathVariable("screNo") int screNo,
    		@RequestBody ScreUserVO screUserVO,
            SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
        screUserService.updateScreUser(screUserVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{userCode}/{screNo}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteScreUser(
    		@PathVariable("userCode") String userCode,
    		@PathVariable("screNo") int screNo, 
    		SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	ScreUserVO screUserVO = new ScreUserVO();
    	screUserVO.setUserCode(userCode);
    	screUserVO.setScreNo(screNo);
    	
        screUserService.deleteScreUser(screUserVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }

}
