package nds.mpm.sales.SD9011.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.vo.SearchCommonVO;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD9011.service.SD9011MpStndPriceService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :  SD9011MpStndPriceController
 *
 * @author MPM TEAM
 * @since 2017. 9. 14.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 단가조회( SD0911 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 9. 14.	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd9011/mpstndprice")
public class SD9011MpStndPriceController extends TMMBaseController{

    @Resource(name = "SD9011mpStndPriceService")
    private SD9011MpStndPriceService mpStndPriceService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * 거래처 
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpStndPriceList(
	    	@PathVariable("corpCode") String corpCode,
			HttpServletRequest req,
			HttpServletResponse res, 
			ModelMap model)
	        throws Exception {
	    
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	    MPUserSession sess = getSession(req);
		
	    SearchCommonVO searchVO = new SearchCommonVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setSalesman(sess.getUser().getId());
		
        List<?> mpStndPriceList = mpStndPriceService.selectMpStndPriceList(searchVO);
	
		PageSet pageSet = new PageSet();
	    
		if(mpStndPriceList != null)
			pageSet.setTotalRecordCount(mpStndPriceList.size());
		pageSet.setResult(mpStndPriceList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    } 
    
    /**
     * 제품
     * */
    @RequestMapping(value="/{strtDate}/{custCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectProdMpStndPriceList(
	    	@PathVariable("corpCode") String corpCode,
			@PathVariable("strtDate") String strtDate,
			@PathVariable("custCode") String custCode,
			HttpServletRequest req,
			HttpServletResponse res, 
			ModelMap model)
	        throws Exception {
	    
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	    MPUserSession sess = getSession(req);
		
	    SearchCommonVO searchVO = new SearchCommonVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(strtDate);
		searchVO.setCustCode(custCode);
		searchVO.setSalesman(sess.getUser().getId());
		
        List<?> mpStndPriceList = mpStndPriceService.selectProdMpStndPriceList(searchVO);
	
		PageSet pageSet = new PageSet();
	    
		if(mpStndPriceList != null)
			pageSet.setTotalRecordCount(mpStndPriceList.size());
		pageSet.setResult(mpStndPriceList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    }
}