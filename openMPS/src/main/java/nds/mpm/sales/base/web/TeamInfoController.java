package nds.mpm.sales.base.web;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.sales.base.service.TeamInfoService;
import nds.mpm.sales.base.vo.TeamInfoDefaultVO;
import nds.mpm.sales.base.vo.TeamInfoVO;

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
 * @Class Name :  TeamInfoController
 * @Description : 화면명 : 팀관리
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 팀관리.

 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.06.07	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{headCode}/teaminfo")
public class TeamInfoController {
	
	private static final Logger _logger = LoggerFactory.getLogger(TeamInfoController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "teamInfoService")
    private TeamInfoService teamInfoService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * team_info 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TeamInfoDefaultVO
	 * @return "/teamInfo/TeamInfoList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTeamInfoList(
    		@PathVariable("headCode") String headCode,
    		ModelMap model)
            throws Exception {
    	
    	ResponseEntity<ResultEx> resData = null;
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	TeamInfoVO searchVO = new TeamInfoVO();
    	searchVO.setHeadCode(headCode);
    	
        List<?> teamInfoList = teamInfoService.selectTeamInfoList(searchVO);
        PageSet pageSet = new PageSet();
        pageSet.setResult(teamInfoList);
        result.setExtraData(pageSet);
        
        return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTeamInfo(
    		@PathVariable("headCode") String headCode,
            @RequestBody TeamInfoVO teamInfoVO,
            SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
        teamInfoService.insertTeamInfo(teamInfoVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{teamCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTeamInfo(
    		@PathVariable("headCode") String headCode,
    		@PathVariable("teamCode") String teamCode
         ) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	TeamInfoVO  teamInfoVO = new TeamInfoVO();
    	teamInfoVO.setHeadCode(headCode);
    	teamInfoVO.setTeamCode(teamCode);
		
    	result.setExtraData(teamInfoService.selectTeamInfo(teamInfoVO));
        return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{teamCode}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateTeamInfo(
    		@PathVariable("headCode") String headCode,
    		@PathVariable("teamCode") String teamCode,
            @RequestBody TeamInfoVO teamInfoVO, 
            SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
        teamInfoService.updateTeamInfo(teamInfoVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{teamCode}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteTeamInfo(
    		@PathVariable("headCode") String headCode,
    		@PathVariable("teamCode") String teamCode, 
    		SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	TeamInfoVO  teamInfoVO = new TeamInfoVO();
    	teamInfoVO.setHeadCode(headCode);
    	teamInfoVO.setTeamCode(teamCode);
		
        teamInfoService.deleteTeamInfo(teamInfoVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }

}
