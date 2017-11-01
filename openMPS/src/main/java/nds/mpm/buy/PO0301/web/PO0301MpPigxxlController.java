package nds.mpm.buy.PO0301.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.buy.PO0301.service.PO0301MpPigxxlService;
import nds.mpm.buy.PO0301.vo.MpPigxxlDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigxxlVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;

import org.apache.commons.lang.StringUtils;
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
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  PO0301MpPigxxlController
 *
 * @author MPM TEAM
 * @since 2017.09.08
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 이상육발생현황 관리( PO0301 ) 
 * TAB4 이상육 삼겹 - 환산계수 관리 
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
@RequestMapping("/mpm/{corpCode}/po0301/mppigxxl")
public class PO0301MpPigxxlController  extends TMMBaseController{

    @Resource(name = "PO0301mpPigxxlService")
    private PO0301MpPigxxlService mpPigxxlService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_pigxxl 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPigxxlDefaultVO
	 * @return "/mpPigxxl/MpPigxxlList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{procDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx>  selectMpPigxxlList(
	    	@PathVariable("corpCode") String corpCode,
			@PathVariable("procDate") String procDate,
			HttpServletRequest req,
			HttpServletResponse res, 
			ModelMap model)
	        throws Exception {
		
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		MpPigxxlVO searchVO = new MpPigxxlVO();
		searchVO.setCorpCode(corpCode);
		
		searchVO.setStartDate(procDate);
		
	    List<EgovMap> PO0301MpPigoxmList = mpPigxxlService.selectMpPigxxlList(searchVO);
	
		PageSet pageSet = new PageSet();
	    
		if(PO0301MpPigoxmList != null)
			pageSet.setTotalRecordCount(PO0301MpPigoxmList.size());
		pageSet.setResult(PO0301MpPigoxmList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    } 
    
    public String addMpPigxxlView(
            @ModelAttribute("searchVO") MpPigxxlDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpPigxxlVO", new MpPigxxlVO());
        return "/mpPigxxl/MpPigxxlRegister";
    }
    
    @RequestMapping(value="/{procDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPigxxl(
            @PathVariable("corpCode") String corpCode,
    		@PathVariable("procDate") String procDate,
            @RequestBody List<EgovMap> PO0301MpPigoxmVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
            	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	if(PO0301MpPigoxmVOs != null)
    	{
    		for(EgovMap vo : PO0301MpPigoxmVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("procDate",StringUtils.remove(procDate,"-"));
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	result.setExtraData(mpPigxxlService.insertMpPigxxl(ilist));
    	
        return _filter.makeCORSEntity( result );
    }

    public String updateMpPigxxl(
            MpPigxxlVO mpPigxxlVO,
            @ModelAttribute("searchVO") MpPigxxlDefaultVO searchVO, SessionStatus status)
            throws Exception {
        //mpPigxxlService.updateMpPigxxl(mpPigxxlVO);
        status.setComplete();
        return "forward:/mpPigxxl/MpPigxxlList.do";
    }
    
    public String deleteMpPigxxl(
            MpPigxxlVO mpPigxxlVO,
            @ModelAttribute("searchVO") MpPigxxlDefaultVO searchVO, SessionStatus status)
            throws Exception {
        //mpPigxxlService.deleteMpPigxxl(mpPigxxlVO);
        status.setComplete();
        return "forward:/mpPigxxl/MpPigxxlList.do";
    }

}
