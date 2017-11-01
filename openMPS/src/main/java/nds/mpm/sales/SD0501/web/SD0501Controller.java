package nds.mpm.sales.SD0501.web;

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
import nds.mpm.sales.SD0501.service.SD0501Service;
import nds.mpm.sales.SD0501.vo.SD0501DefaultVO;
import nds.mpm.sales.SD0501.vo.SD0501VO;

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
 * @Class Name :  SD0501Controller
 *
 * @author MPM TEAM
 * @since 2017.08.11
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 수금입력, SD0501,SD0502
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.11	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0501/mpreceinfo")
public class SD0501Controller extends TMMBaseController {

    @Resource(name = "SD0501Service")
    private SD0501Service SD0501Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_rece_info 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SD0501DefaultVO
	 * @return "/SD0501/SD0501List"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}")
    public ResponseEntity<ResultEx> selectSD0501List(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String custCode = req.getParameter("custCode");
    	
    	SD0501VO searchVO = new SD0501VO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setCustCode(custCode);
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> SD0501List = SD0501Service.selectSD0501List(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = SD0501Service.selectSD0501ListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(SD0501List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/SD0501/addSD0501View.do")
    public String addSD0501View(
            @ModelAttribute("searchVO") SD0501DefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("SD0501VO", new SD0501VO());
        return "/SD0501/SD0501Register";
    }
    
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addSD0501(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
            @RequestBody List<EgovMap> mpSalePriceVO,
            HttpServletRequest req)
            throws Exception {
            	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        MPUserSession sess = getSession(req);
    	int nRet = 0;
    	
    	List ilist = new ArrayList();
    	//중복검색
    	if(mpSalePriceVO != null)
    	{
    		for(EgovMap vo : mpSalePriceVO)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("strtDate",StringUtils.remove(strtDate,"-"));
    			vo.put("lastDate",StringUtils.remove(lastDate,"-"));
    			vo.put("receDate",StringUtils.remove((String)vo.get("receDate"),"-"));
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser", sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	result = SD0501Service.insertSD0501(ilist);
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/SD0501/updateSD0501View.do")
    public String updateSD0501View(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("receDate") java.lang.String receDate ,
            @RequestParam("receCode") java.lang.String receCode ,
            @ModelAttribute("searchVO") SD0501DefaultVO searchVO, Model model)
            throws Exception {
        SD0501VO SD0501VO = new SD0501VO();
        SD0501VO.setCorpCode(corpCode);
        SD0501VO.setReceDate(receDate);
        SD0501VO.setReceCode(receCode);
        // 변수명은 CoC 에 따라 SD0501VO
        model.addAttribute(selectSD0501(SD0501VO, searchVO));
        return "/SD0501/SD0501Register";
    }

    @RequestMapping("/SD0501/selectSD0501.do")
    public @ModelAttribute("SD0501VO")
    SD0501VO selectSD0501(
            SD0501VO SD0501VO,
            @ModelAttribute("searchVO") SD0501DefaultVO searchVO) throws Exception {
        return SD0501Service.selectSD0501(SD0501VO);
    }

    @RequestMapping("/SD0501/updateSD0501.do")
    public String updateSD0501(
            SD0501VO SD0501VO,
            @ModelAttribute("searchVO") SD0501DefaultVO searchVO, SessionStatus status)
            throws Exception {
        SD0501Service.updateSD0501(SD0501VO);
        status.setComplete();
        return "forward:/SD0501/SD0501List.do";
    }
    
    @RequestMapping("/SD0501/deleteSD0501.do")
    public String deleteSD0501(
            SD0501VO SD0501VO,
            @ModelAttribute("searchVO") SD0501DefaultVO searchVO, SessionStatus status)
            throws Exception {
        SD0501Service.deleteSD0501(SD0501VO);
        status.setComplete();
        return "forward:/SD0501/SD0501List.do";
    }

}
