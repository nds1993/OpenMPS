package nds.mpm.prod.PP0102.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.prod.PP0102.service.MpLpcInfoMService;
import nds.mpm.prod.PP0102.vo.MpLpcInfoMDefaultVO;
import nds.mpm.prod.PP0102.vo.MpLpcInfoMVO;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :  MpLpcInfoMController
 *
 * @author MPM TEAM
 * @since 2017.07.31
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 농장등록( PP0102 )
 * 
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
@RequestMapping("/mpm/{corpCode}/pp0102/mplpcinfom")
public class MpLpcInfoMController extends TMMBaseController {
	
	private static final Logger _logger = LoggerFactory.getLogger(MpLpcInfoMController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpLpcInfoMService")
    private MpLpcInfoMService mpLpcInfoMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_lpc_info_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpLpcInfoMDefaultVO
	 * @return "/mpLpcInfoM/MpLpcInfoMList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpLpcInfoMList(
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String beginDate = req.getParameter("beginDate");
    	String endDate = req.getParameter("endDate");
    	
    	MpLpcInfoMVO searchVO = new MpLpcInfoMVO();
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpLpcInfoMList = mpLpcInfoMService.selectMpLpcInfoMList(searchVO);
        
    	PageSet pageSet = new PageSet();
        
        int totCnt = mpLpcInfoMService.selectMpLpcInfoMListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpLpcInfoMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    /**
    @RequestMapping(value="",method=RequestMethod.POST)
    public String addMpLpcInfoM(
    		@PathVariable("corpCode") String corpCode,
    		@RequestBody List<MpLpcInfoMVO> mpLpcInfoMVOs,
    		HttpServletRequest req,
    		HttpServletResponse res
            )
            throws Exception {
    	
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(mpLpcInfoMVOs != null)
    	{
    		for(MpLpcInfoMVO vo : mpLpcInfoMVOs)
        	{
    			vo.setCorpCode(corpCode);
    			if(sess != null)
    			{
    				vo.setCrUser(sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
        mpLpcInfoMService.insertMpLpcInfoM(ilist);
        return "forward:/mpLpcInfoM/MpLpcInfoMList.do";
    }
    
    @RequestMapping("/mpLpcInfoM/selectMpLpcInfoM.do")
    public @ModelAttribute("mpLpcInfoMVO")
    MpLpcInfoMVO selectMpLpcInfoM(
            MpLpcInfoMVO mpLpcInfoMVO,
            @ModelAttribute("searchVO") MpLpcInfoMDefaultVO searchVO) throws Exception {
        return mpLpcInfoMService.selectMpLpcInfoM(mpLpcInfoMVO);
    }

    @RequestMapping("/mpLpcInfoM/updateMpLpcInfoM.do")
    public String updateMpLpcInfoM(
            MpLpcInfoMVO mpLpcInfoMVO,
            @ModelAttribute("searchVO") MpLpcInfoMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpLpcInfoMService.updateMpLpcInfoM(mpLpcInfoMVO);
        status.setComplete();
        return "forward:/mpLpcInfoM/MpLpcInfoMList.do";
    }
    
    @RequestMapping("/mpLpcInfoM/deleteMpLpcInfoM.do")
    public String deleteMpLpcInfoM(
            MpLpcInfoMVO mpLpcInfoMVO,
            @ModelAttribute("searchVO") MpLpcInfoMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpLpcInfoMService.deleteMpLpcInfoM(mpLpcInfoMVO);
        status.setComplete();
        return "forward:/mpLpcInfoM/MpLpcInfoMList.do";
    }
	**/
}
