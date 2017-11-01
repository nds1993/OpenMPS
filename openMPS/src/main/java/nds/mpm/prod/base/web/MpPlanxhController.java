package nds.mpm.prod.base.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.prod.base.service.MpPlanxhService;
import nds.mpm.prod.base.vo.MpPlanxhDefaultVO;
import nds.mpm.prod.base.vo.MpPlanxhVO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :  MpPlanxhController
 * @Description : 화면명 : 생산계획서 헤더 
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.28
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 생산계획서 헤더 
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.06.28	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/mpplanxh")
public class MpPlanxhController {
	
	private static final Logger _logger = LoggerFactory.getLogger(MpPlanxhController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpPlanxhService")
    private MpPlanxhService mpPlanxhService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * MP_PLANXH 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPlanxhDefaultVO
	 * @return "/mpPlanxh/MpPlanxhList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPlanxhList(
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpPlanxhVO searchVO = new MpPlanxhVO();
    	searchVO.setPageSize(pageSize);
    	searchVO.setPageIndex(pageIndex);
    	
        List<?> mpGboxxmList = mpPlanxhService.selectMpPlanxhList(searchVO);
        
        int totCnt = mpPlanxhService.selectMpPlanxhListTotCnt(searchVO);
        
        PageSet pageSet = new PageSet();
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpGboxxmList);
        
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPlanxh(
            @RequestBody MpPlanxhVO mpPlanxhVO,
            SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
		mpPlanxhService.insertMpPlanxh(mpPlanxhVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/mpPlanxh/selectMpPlanxh.do")
    public @ModelAttribute("mpPlanxhVO")
    MpPlanxhVO selectMpPlanxh(
            MpPlanxhVO mpPlanxhVO,
            @ModelAttribute("searchVO") MpPlanxhDefaultVO searchVO) throws Exception {
        return mpPlanxhService.selectMpPlanxh(mpPlanxhVO);
    }

    @RequestMapping("/mpPlanxh/updateMpPlanxh.do")
    public String updateMpPlanxh(
            MpPlanxhVO mpPlanxhVO,
            @ModelAttribute("searchVO") MpPlanxhDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPlanxhService.updateMpPlanxh(mpPlanxhVO);
        status.setComplete();
        return "forward:/mpPlanxh/MpPlanxhList.do";
    }
    
    @RequestMapping("/mpPlanxh/deleteMpPlanxh.do")
    public String deleteMpPlanxh(
            MpPlanxhVO mpPlanxhVO,
            @ModelAttribute("searchVO") MpPlanxhDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPlanxhService.deleteMpPlanxh(mpPlanxhVO);
        status.setComplete();
        return "forward:/mpPlanxh/MpPlanxhList.do";
    }

}
