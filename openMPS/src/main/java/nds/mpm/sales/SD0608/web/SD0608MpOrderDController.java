package nds.mpm.sales.SD0608.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.sales.SD0601.vo.MpOrderDVO;
import nds.mpm.sales.SD0608.service.SD0608MpOrderDService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  SD0608MpOrderDController
 *
 * @author MPM TEAM
 * @since 2017. 9. 11.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 거래원장( SD0608 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 9. 11.	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/SD0608/mporderd")
public class SD0608MpOrderDController extends TMMBaseController{

    @Resource(name = "SD0608mpOrderDService")
    private SD0608MpOrderDService mpOrderDService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    @RequestMapping(value="/head/{strtDate}/{lastDate}/{teamCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectHeadMpOrderDList(
	    	@PathVariable("corpCode") String corpCode,
			@PathVariable("strtDate") String strtDate,
			@PathVariable("lastDate") String lastDate,
			@PathVariable("teamCode") String teamCode,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	 	
		EgovMap searchVO = new EgovMap();
		searchVO.put("corpCode",corpCode);
		searchVO.put("strtDate",strtDate);
		searchVO.put("lastDate",lastDate);
		searchVO.put("teamCode",teamCode);
		
        List<?> mpOrderDList = mpOrderDService.selectMpOrderDList(searchVO);
	    
	    PageSet pageSet = new PageSet();
	    
	    if(mpOrderDList != null)
	    	pageSet.setTotalRecordCount(mpOrderDList.size());
		pageSet.setResult(mpOrderDList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );	
    }
    /**
	 * mp_order_d 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpOrderDDefaultVO
	 * @return "/mpOrderD/MpOrderDList"
	 * @exception Exception
	 */
    @RequestMapping(value="/tab1/{strtDate}/{lastDate}/{teamCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTab1MpOrderDList(
	    	@PathVariable("corpCode") String corpCode,
			@PathVariable("strtDate") String strtDate,
			@PathVariable("lastDate") String lastDate,
			@PathVariable("teamCode") String teamCode,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		String salesman = req.getParameter("salesman");
		
		EgovMap searchVO = new EgovMap();
		searchVO.put("corpCode",corpCode);
		searchVO.put("strtDate",strtDate);
		searchVO.put("lastDate",lastDate);
		searchVO.put("teamCode",teamCode);
		searchVO.put("salesman",salesman);
		
		
		
        List<?> mpOrderDList = mpOrderDService.selectMpOrderDTab1List(searchVO);
	    
	    PageSet pageSet = new PageSet();
	    
	    if(mpOrderDList != null)
	    	pageSet.setTotalRecordCount(mpOrderDList.size());
		pageSet.setResult(mpOrderDList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );	
    } 
    
    @RequestMapping(value="/tab2/{strtDate}/{lastDate}/{teamCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTab2MpOrderDList(
	    	@PathVariable("corpCode") String corpCode,
			@PathVariable("strtDate") String strtDate,
			@PathVariable("lastDate") String lastDate,
			@PathVariable("teamCode") String teamCode,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	 	
		String salesman = req.getParameter("salesman");
		
		EgovMap searchVO = new EgovMap();
		searchVO.put("corpCode",corpCode);
		searchVO.put("strtDate",strtDate);
		searchVO.put("lastDate",lastDate);
		searchVO.put("teamCode",teamCode);
		searchVO.put("salesman",salesman);
		
        List<?> mpOrderDList = mpOrderDService.selectMpOrderDTab2List(searchVO);
	    
	    PageSet pageSet = new PageSet();
	    
	    if(mpOrderDList != null)
	    	pageSet.setTotalRecordCount(mpOrderDList.size());
		pageSet.setResult(mpOrderDList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );	
    }
    
    @RequestMapping(value="/tab3/{strtDate}/{lastDate}/{teamCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTab3MpOrderDList(
	    	@PathVariable("corpCode") String corpCode,
			@PathVariable("strtDate") String strtDate,
			@PathVariable("lastDate") String lastDate,
			@PathVariable("teamCode") String teamCode,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	 	
		String salesman = req.getParameter("salesman");
		
		EgovMap searchVO = new EgovMap();
		searchVO.put("corpCode",corpCode);
		searchVO.put("strtDate",strtDate);
		searchVO.put("lastDate",lastDate);
		searchVO.put("teamCode",teamCode);
		searchVO.put("salesman",salesman);
		
        List<?> mpOrderDList = mpOrderDService.selectMpOrderDTab3List(searchVO);
	    
	    PageSet pageSet = new PageSet();
	    
	    if(mpOrderDList != null)
	    	pageSet.setTotalRecordCount(mpOrderDList.size());
		pageSet.setResult(mpOrderDList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );	
    }
}
