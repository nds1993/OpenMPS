package nds.mpm.sales.SD0101.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.sales.SD0101.service.MpCustProLimitService;
import nds.mpm.sales.SD0101.vo.MpCustInfoVO;
import nds.mpm.sales.SD0101.vo.MpCustProLimitDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustProLimitVO;
import nds.mpm.sales.SD0101.vo.MpDamboInfoVO;

/**
 * @Class Name : MpCustProLimitController.java
 * @Description : MpCustProLimit Controller class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/sd0101/mpcustprolimit")
public class MpCustProLimitController extends TMMBaseController{

    @Resource(name = "mpCustProLimitService")
    private MpCustProLimitService mpCustProLimitService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_cust_pro_limit 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpCustProLimitDefaultVO
	 * @return "/mpCustProLimit/MpCustProLimitList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{custCode}")
    public ResponseEntity<ResultEx> selectMpCustProLimitList(
	    	@PathVariable("corpCode") String corpCode,
	    	@PathVariable("custCode") String custCode,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpCustProLimitVO searchVO = new MpCustProLimitVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setCustCode(custCode);
    	
        List<?> mpCustProLimitList = mpCustProLimitService.selectMpCustProLimitList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpCustProLimitList!=null)
    		pageSet.setTotalRecordCount(mpCustProLimitList.size());
    	pageSet.setResult(mpCustProLimitList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 

}
