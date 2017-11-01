package nds.mpm.buy.PO0302.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.buy.PO0302.service.PO0302MpPigoxmService;
import nds.mpm.buy.PO0302.vo.MpPigoxmVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :  PigOtherController
 *
 * @author MPM TEAM
 * @since 2017.08.31
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 이상육 발생현황 조회( PO0302 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.31	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/po0302/mppigoxm")
public class PO0302MpPigoxmController extends TMMBaseController {

    @Resource(name = "PO0302MpPigoxmService")
    private PO0302MpPigoxmService PO0302MpPigoxmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
 	 * 이상육/세류사돈/도축사고/정산차액 조회
 	 * @exception Exception
 	 */
    @RequestMapping(value="/tab1/{brandCode}/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectPO0302MpPigoxmList_Tabl(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("brandCode") String brandCode,
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
    	String othPart = req.getParameter("othPart");
    	
    	MpPigoxmVO searchVO = new MpPigoxmVO();
    	searchVO.setCorpCode(corpCode);
    	
    	/*searchVO.setBrandCode(brandCode);*/
    	searchVO.setOthPart(othPart);
    	searchVO.setCustCode(custCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setLastDate(lastDate);
    	
    	if("0".equals(brandCode)){
       		searchVO.setBrandCode("");
       	}else{
       		searchVO.setBrandCode(brandCode);
       	}
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpPigoxmList = PO0302MpPigoxmService.selectPO0302MpPigoxmList_tab1(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = PO0302MpPigoxmService.selectPO0302MpPigoxmListTotCnt_tabl(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpPigoxmList);
		
    	result.setExtraData(pageSet);
    
    	return _filter.makeCORSEntity( result );
    } 
    
    
    /**
 	 * 이상육 발생현황 조회
 	 * @exception Exception
 	 */
    @RequestMapping(value="/tab2/{brandCode}/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectPO0302MpPigoxmList_Tab2(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("brandCode") String brandCode,
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
    	String othPart = req.getParameter("othPart");
    	
    	MpPigoxmVO searchVO = new MpPigoxmVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setBrandCode(brandCode);
    	searchVO.setCustCode(custCode);
    	searchVO.setOthPart(othPart);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setLastDate(lastDate);
    	
    	if("0".equals(brandCode)){
       		searchVO.setBrandCode("");
       	}else{
       		searchVO.setBrandCode(brandCode);
       	}
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpPigoxmList = PO0302MpPigoxmService.selectPO0302MpPigoxmList_tab2(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = PO0302MpPigoxmService.selectPO0302MpPigoxmListTotCnt_tab2(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpPigoxmList);
		
    	result.setExtraData(pageSet);
    
    	return _filter.makeCORSEntity( result );
    } 

}
