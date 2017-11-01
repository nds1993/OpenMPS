package nds.mpm.sales.SD0201.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0201.service.MpStndProdService;
import nds.mpm.sales.SD0201.vo.MpStndProdDefaultVO;
import nds.mpm.sales.SD0201.vo.MpStndProdVO;

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
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpStndProdController.java
 * @Description : MpStndProd Controller class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/sd0201/mpstndprod")
public class MpStndProdController extends TMMBaseController{

    @Resource(name = "mpStndProdService")
    private MpStndProdService mpStndProdService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_stnd_prod 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpStndProdDefaultVO
	 * @return "/mpStndProd/MpStndProdList"
	 * @exception Exception
	 */
    @RequestMapping(value="")
    public ResponseEntity<ResultEx> selectMpStndProdList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpStndProdVO searchVO = new MpStndProdVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpStndProdList = mpStndProdService.selectMpStndProdList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpStndProdService.selectMpStndProdListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpStndProdList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    	
    } 
    
    @RequestMapping("/mpStndProd/addMpStndProdView.do")
    public String addMpStndProdView(
            @ModelAttribute("searchVO") MpStndProdDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpStndProdVO", new MpStndProdVO());
        return "/mpStndProd/MpStndProdRegister";
    }
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpStndProd(
            @PathVariable("corpCode") String corpCode,
    		@RequestBody List<EgovMap> mpStndProdVO,
    		HttpServletRequest req)
            throws Exception {
        
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	//중복검색
    	if(mpStndProdVO != null)
    	{
    		for(EgovMap vo : mpStndProdVO)
        	{
    			vo.put("corpCode",corpCode);
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser", sess.getUser().getId());
    			}
        	}
    	}
    	result = mpStndProdService.insertMpStndProd(mpStndProdVO);
        return _filter.makeCORSEntity( result );
    }
    /**
    @RequestMapping("/mpStndProd/updateMpStndProd.do")
    public String updateMpStndProd(
            MpStndProdVO mpStndProdVO,
            @ModelAttribute("searchVO") MpStndProdDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpStndProdService.updateMpStndProd(mpStndProdVO);
        status.setComplete();
        return "forward:/mpStndProd/MpStndProdList.do";
    }
    
    @RequestMapping("/mpStndProd/deleteMpStndProd.do")
    public String deleteMpStndProd(
            MpStndProdVO mpStndProdVO,
            @ModelAttribute("searchVO") MpStndProdDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpStndProdService.deleteMpStndProd(mpStndProdVO);
        status.setComplete();
        return "forward:/mpStndProd/MpStndProdList.do";
    }
	**/
}
