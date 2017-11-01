package nds.mpm.sales.SD0402.web;

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
import nds.mpm.sales.SD0401.service.SD0401Service;
import nds.mpm.sales.SD0402.service.SD0402Service;
import nds.mpm.sales.SD0402.vo.SD0402DefaultVO;
import nds.mpm.sales.SD0402.vo.SD0402VO;

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
 * @Class Name :  SD0402Controller
 *
 * @author MPM TEAM
 * @since 2017.08.17
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 출고승인요청( SD0402 )
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
@RequestMapping("/mpm/{corpCode}/sd0402/mpdelvappro")
public class SD0402Controller extends TMMBaseController {

    @Resource(name = "SD0402Service")
    private SD0402Service SD0402Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_delv_approval 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SD0402DefaultVO
	 * @return "/SD0402/SD0402List"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}")
    public ResponseEntity<ResultEx> selectSD0402List(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
        
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String salesman = req.getParameter("salesman");
    	
    	SD0402VO searchVO = new SD0402VO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> SD0402List = SD0402Service.selectSD0402List(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = SD0402Service.selectSD0402ListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(SD0402List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/SD0402/addSD0402View.do")
    public String addSD0402View(
            @ModelAttribute("searchVO") SD0402DefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("SD0402VO", new SD0402VO());
        return "/SD0402/SD0402Register";
    }
    
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addSD0402(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
            @RequestBody List<EgovMap> mpBomHVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
        
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(mpBomHVOs != null)
    	{
    		for(EgovMap vo : mpBomHVOs)
        	{
    			String creditPrice = StringUtils.defaultIfEmpty((String)vo.get("creditPrice"),"0");
    			String ordrAmt = StringUtils.defaultIfEmpty((String)vo.get("ordrAmt"),"0");
    			String creditOver = StringUtils.defaultIfEmpty((String)vo.get("creditOver"),"0");
    			
    			vo.put("corpCode",corpCode);
    			vo.put("creditPrice",StringUtils.remove(creditPrice,","));
    			vo.put("ordrAmt",StringUtils.remove(ordrAmt,","));
    			vo.put("creditOver",StringUtils.remove(creditOver,","));
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	SD0402Service.insertSD0402(ilist);
    	
    	
    	String salesman = req.getParameter("salesman");
    	
    	SD0402VO searchVO = new SD0402VO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	
        List<?> SD0402List = SD0402Service.selectSD0402List(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = SD0402Service.selectSD0402ListTotCnt(searchVO);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(SD0402List);
		
    	result.setExtraData(pageSet);
    	
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/SD0402/updateSD0402View.do")
    public String updateSD0402View(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("delvDate") java.lang.String delvDate ,
            @RequestParam("oedrNo") java.lang.String oedrNo ,
            @ModelAttribute("searchVO") SD0402DefaultVO searchVO, Model model)
            throws Exception {
        SD0402VO SD0402VO = new SD0402VO();
        SD0402VO.setCorpCode(corpCode);
        SD0402VO.setDelvDate(delvDate);
        SD0402VO.setOrdrNo(oedrNo);
        // 변수명은 CoC 에 따라 SD0402VO
        model.addAttribute(selectSD0402(SD0402VO, searchVO));
        return "/SD0402/SD0402Register";
    }

    @RequestMapping("/SD0402/selectSD0402.do")
    public @ModelAttribute("SD0402VO")
    SD0402VO selectSD0402(
            SD0402VO SD0402VO,
            @ModelAttribute("searchVO") SD0402DefaultVO searchVO) throws Exception {
        return SD0402Service.selectSD0402(SD0402VO);
    }

    @RequestMapping("/SD0402/updateSD0402.do")
    public String updateSD0402(
            SD0402VO SD0402VO,
            @ModelAttribute("searchVO") SD0402DefaultVO searchVO, SessionStatus status)
            throws Exception {
        SD0402Service.updateSD0402(SD0402VO);
        status.setComplete();
        return "forward:/SD0402/SD0402List.do";
    }
    
    @RequestMapping("/SD0402/deleteSD0402.do")
    public String deleteSD0402(
            SD0402VO SD0402VO,
            @ModelAttribute("searchVO") SD0402DefaultVO searchVO, SessionStatus status)
            throws Exception {
        SD0402Service.deleteSD0402(SD0402VO);
        status.setComplete();
        return "forward:/SD0402/SD0402List.do";
    }

}
