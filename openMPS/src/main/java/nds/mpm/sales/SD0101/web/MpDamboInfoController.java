package nds.mpm.sales.SD0101.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.sales.SD0101.service.MpDamboInfoService;
import nds.mpm.sales.SD0101.vo.MpDamboInfoDefaultVO;
import nds.mpm.sales.SD0101.vo.MpDamboInfoVO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDamboInfoController.java
 * @Description : MpDamboInfo Controller class
 * @Modification Information
 *
 * @author b
 * @since b
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/sd0101/mpdamboinfo")
public class MpDamboInfoController {
	
	private static final Logger _logger = LoggerFactory.getLogger(MpDamboInfoController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpDamboInfoService")
    private MpDamboInfoService mpDamboInfoService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_dambo_info 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpDamboInfoDefaultVO
	 * @return "/mpDamboInfo/MpDamboInfoList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{custCode}")
    public ResponseEntity<ResultEx> selectMpDamboInfoList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("custCode") String custCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpDamboInfoVO searchVO = new MpDamboInfoVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setCustCode(custCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpDamboInfoList = mpDamboInfoService.selectMpDamboInfoList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpDamboInfoService.selectMpDamboInfoListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpDamboInfoList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/mpDamboInfo/addMpDamboInfoView.do")
    public String addMpDamboInfoView(
            @ModelAttribute("searchVO") MpDamboInfoDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpDamboInfoVO", new MpDamboInfoVO());
        return "/mpDamboInfo/MpDamboInfoRegister";
    }
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpDamboInfo(
            @RequestBody EgovMap mpDamboInfoVO,
            HttpServletRequest req
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	result = mpDamboInfoService.insertMpDamboInfo(mpDamboInfoVO);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/mpDamboInfo/selectMpDamboInfo.do")
    public @ModelAttribute("mpDamboInfoVO")
    MpDamboInfoVO selectMpDamboInfo(
            MpDamboInfoVO mpDamboInfoVO,
            @ModelAttribute("searchVO") MpDamboInfoDefaultVO searchVO) throws Exception {
        return mpDamboInfoService.selectMpDamboInfo(mpDamboInfoVO);
    }

    @RequestMapping(value="",method=RequestMethod.PUT)
    public String updateMpDamboInfo(
    		@RequestBody EgovMap mpDamboInfoVO,
            @ModelAttribute("searchVO") MpDamboInfoDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpDamboInfoService.updateMpDamboInfo(mpDamboInfoVO);
        status.setComplete();
        return "forward:/mpDamboInfo/MpDamboInfoList.do";
    }
    
    @RequestMapping(value="",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteMpDamboInfo(
            MpDamboInfoVO mpDamboInfoVO,
            HttpServletRequest req
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        mpDamboInfoService.deleteMpDamboInfo(mpDamboInfoVO);
        return _filter.makeCORSEntity( result );
    }

}
