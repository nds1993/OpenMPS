package nds.mpm.prod.base.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.prod.base.service.MpPlanxdService;
import nds.mpm.prod.base.vo.MpPlanxdDefaultVO;
import nds.mpm.prod.base.vo.MpPlanxdVO;

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
 * @Class Name :  MpPlanxdController
 * @Description : 화면명 : 생산계획서 상세
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.28
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 생산계획서 상세
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
@RequestMapping("/mpm/mpplanxd")
public class MpPlanxdController {
	
	private static final Logger _logger = LoggerFactory.getLogger(MpPlanxhController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpPlanxdService")
    private MpPlanxdService mpPlanxdService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * MP_PLANXD 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPlanxdDefaultVO
	 * @return "/mpPlanxd/MpPlanxdList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPlanxdList(
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
        
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpPlanxdVO searchVO = new MpPlanxdVO();
    	searchVO.setPageSize(pageSize);
    	searchVO.setPageIndex(pageIndex);
    	
        List<?> mpGboxxmList = mpPlanxdService.selectMpPlanxdList(searchVO);
        
        int totCnt = mpPlanxdService.selectMpPlanxdListTotCnt(searchVO);
        
        PageSet pageSet = new PageSet();
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpGboxxmList);
        
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPlanxd(
            @RequestBody MpPlanxdVO mpPlanxdVO,
            SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        mpPlanxdService.insertMpPlanxd(mpPlanxdVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/mpPlanxd/selectMpPlanxd.do")
    public @ModelAttribute("mpPlanxdVO")
    MpPlanxdVO selectMpPlanxd(
            MpPlanxdVO mpPlanxdVO,
            @ModelAttribute("searchVO") MpPlanxdDefaultVO searchVO) throws Exception {
        return mpPlanxdService.selectMpPlanxd(mpPlanxdVO);
    }

    @RequestMapping("/mpPlanxd/updateMpPlanxd.do")
    public String updateMpPlanxd(
            MpPlanxdVO mpPlanxdVO,
            @ModelAttribute("searchVO") MpPlanxdDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPlanxdService.updateMpPlanxd(mpPlanxdVO);
        status.setComplete();
        return "forward:/mpPlanxd/MpPlanxdList.do";
    }
    
    @RequestMapping("/mpPlanxd/deleteMpPlanxd.do")
    public String deleteMpPlanxd(
            MpPlanxdVO mpPlanxdVO,
            @ModelAttribute("searchVO") MpPlanxdDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPlanxdService.deleteMpPlanxd(mpPlanxdVO);
        status.setComplete();
        return "forward:/mpPlanxd/MpPlanxdList.do";
    }

}
