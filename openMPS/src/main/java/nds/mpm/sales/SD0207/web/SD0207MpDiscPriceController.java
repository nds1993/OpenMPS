package nds.mpm.sales.SD0207.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.login.vo.RoleConsts;
import nds.mpm.sales.SD0205.service.MpDiscPriceTitleService;
import nds.mpm.sales.SD0205.vo.MpDiscPriceDefaultVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceTitleVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceVO;
import nds.mpm.sales.SD0207.service.SD0207MpDiscPriceService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDiscPriceController.java
 * @Description : MpDiscPrice Controller class
 * @Modification Information
 *
 * @author n
 * @since n
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/sd0207/mpdiscprice")
public class SD0207MpDiscPriceController extends TMMBaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(SD0207MpDiscPriceController.class);

	/**
     * 헤더 조회할 서비스
     * 
     * */
	 @Resource(name = "mpDiscPriceTitleService")
	 private MpDiscPriceTitleService mpDiscPriceTitleService;
    
    /**
     * 승인처리 서비스
     * 
     * */
    @Resource(name = "SD0207MpDiscPriceService")
    private SD0207MpDiscPriceService sD0207mpDiscPriceService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_disc_price 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpDiscPriceDefaultVO
	 * @return "/mpDiscPrice/MpDiscPriceList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpDiscPriceList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
    	String approYn = req.getParameter("approYn");
    	
    	MpDiscPriceTitleVO searchVO = new MpDiscPriceTitleVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setApproYn(approYn);
    	
    	searchVO.setHeadCode(sess.getUser().getHeadCode());
    	searchVO.setPartCode(sess.getUser().getDeptCode());
    	searchVO.setTeamCode(sess.getUser().getTeamCode());
    	searchVO.setUserCode(sess.getUser().getId());
    	searchVO.setSalesmanLevel(getSalesmanLevel(req));
    	
    	LOGGER.debug("saleslevel :: " + searchVO.getSalesmanLevel());
    	
        List<?> mpDiscPriceList = sD0207mpDiscPriceService.selectMpDiscPriceTitleList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpDiscPriceList!=null)
    		pageSet.setTotalRecordCount(mpDiscPriceList.size());
    	pageSet.setResult(mpDiscPriceList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
	 * mp_disc_price 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpDiscPriceDefaultVO
	 * @return "/mpDiscPrice/MpDiscPriceList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}/{custCode}/detail",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpDiscPriceDetailList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("custCode") String custCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
        MpDiscPriceTitleVO searchVO = new MpDiscPriceTitleVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setCustCode(custCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setLastDate(lastDate);
    	
    	searchVO.setSalesman(sess.getUser().getId());
    	
        List<?> mpDiscPriceList = sD0207mpDiscPriceService.selectMpDiscPriceDetailList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpDiscPriceList!=null)
    		pageSet.setTotalRecordCount(mpDiscPriceList.size());
    	pageSet.setResult(mpDiscPriceList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/mpDiscPrice/addMpDiscPriceView.do")
    public String addMpDiscPriceView(
            @ModelAttribute("searchVO") MpDiscPriceDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpDiscPriceVO", new MpDiscPriceVO());
        return "/mpDiscPrice/MpDiscPriceRegister";
    }
    
    @RequestMapping(value="/{strtDate}/{lastDate}/appro/{status}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpDiscPrice(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("status") String status,
            @RequestBody List<EgovMap> mpSalePriceVO,
            HttpServletRequest req)
            throws Exception {
            	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        MPUserSession sess = getSession(req);
    	int nRet = 0;
    	
    	//중복검색
    	if(mpSalePriceVO != null)
    	{
    		for(EgovMap vo : mpSalePriceVO)
        	{
    			vo.put("corpCode",corpCode);
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    				
    				setAppro(req, status, vo);
    			}
        	}
    	}
    	result = sD0207mpDiscPriceService.updateMpDiscPriceConfirm(mpSalePriceVO);
    	return _filter.makeCORSEntity( result );
    }
    
    public void setAppro(HttpServletRequest req, String appro, EgovMap vo) throws Exception{
    	
    	int salesmanLev = getSalesmanLevel(req);
    	
    	if("yappro".equals(appro))
    	{
    		if(RoleConsts.OFCE_TEAM_LEVEL == salesmanLev
    		|| RoleConsts.OFCE_PART_LEVEL == salesmanLev)
	    	{
	    		vo.put("partAppro", "Y");
	    	}
	    	else if(RoleConsts.OFCE_HEAD_LEVEL == salesmanLev)
	    	{
	    		vo.put("headAppro", "Y");
	    	}
	    	else if(RoleConsts.OFCE_CEO_LEVEL == salesmanLev)
	    	{
	    		vo.put("ceoAppro", "Y");
	    	}
    	}
    	else if("nappro".equals(appro))
    	{
    		if(RoleConsts.OFCE_TEAM_LEVEL == salesmanLev
    	    || RoleConsts.OFCE_PART_LEVEL == salesmanLev)
	    	{
	    		vo.put("partAppro", "X");
	    	}
	    	else if(RoleConsts.OFCE_HEAD_LEVEL == salesmanLev)
	    	{
	    		vo.put("headAppro", "X");
	    	}
	    	else if(RoleConsts.OFCE_CEO_LEVEL == salesmanLev)
	    	{
	    		vo.put("ceoAppro", "X");
	    	}
    	}
    	
    }
    

}
