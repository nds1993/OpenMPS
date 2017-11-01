package nds.mpm.sales.SD0103.web;

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
import nds.mpm.sales.SD0103.service.MpSalesmanProService;
import nds.mpm.sales.SD0103.vo.MpSalesmanProDefaultVO;
import nds.mpm.sales.SD0103.vo.MpSalesmanProVO;

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
 * @Class Name : MpSalesmanProController.java
 * @Description : MpSalesmanPro Controller class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/sd0103/mpsalesmanpro")
public class MpSalesmanProController extends TMMBaseController{
	
	private static final Logger _logger = LoggerFactory.getLogger(MpSalesmanProController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpSalesmanProService")
    private MpSalesmanProService mpSalesmanProService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_salesman_pro 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpSalesmanProDefaultVO
	 * @return "/mpSalesmanPro/MpSalesmanProList"
	 * @exception Exception
	 */
    @RequestMapping(value="")
    public ResponseEntity<ResultEx> selectMpSalesmanProList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
        
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String salesman = req.getParameter("salesman");
    	
    	MpSalesmanProVO searchVO = new MpSalesmanProVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSalesman(salesman);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpSalesmanProList = mpSalesmanProService.selectMpSalesmanProList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpSalesmanProService.selectMpSalesmanProListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpSalesmanProList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/mpSalesmanPro/addMpSalesmanProView.do")
    public String addMpSalesmanProView(
            @ModelAttribute("searchVO") MpSalesmanProDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpSalesmanProVO", new MpSalesmanProVO());
        return "/mpSalesmanPro/MpSalesmanProRegister";
    }
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpSalesmanPro(
    		@PathVariable("corpCode") String corpCode,
            @RequestBody List<EgovMap> mpSalesmanProVO,
            HttpServletRequest req
            )
            throws Exception {
            	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	int nRet = 0;
    	
    	List ilist = new ArrayList();
    	//중복검색
    	if(mpSalesmanProVO != null)
    	{
    		for(EgovMap vo : mpSalesmanProVO)
        	{
    			vo.put("corpCode",corpCode);
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser", sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}    	
    	result = mpSalesmanProService.insertMpSalesmanPro(ilist);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/mpSalesmanPro/updateMpSalesmanPro.do")
    public String updateMpSalesmanPro(
            MpSalesmanProVO mpSalesmanProVO,
            @ModelAttribute("searchVO") MpSalesmanProDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpSalesmanProService.updateMpSalesmanPro(mpSalesmanProVO);
        status.setComplete();
        return "forward:/mpSalesmanPro/MpSalesmanProList.do";
    }
    
    @RequestMapping("/mpSalesmanPro/deleteMpSalesmanPro.do")
    public String deleteMpSalesmanPro(
            MpSalesmanProVO mpSalesmanProVO,
            @ModelAttribute("searchVO") MpSalesmanProDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpSalesmanProService.deleteMpSalesmanPro(mpSalesmanProVO);
        status.setComplete();
        return "forward:/mpSalesmanPro/MpSalesmanProList.do";
    }

}
