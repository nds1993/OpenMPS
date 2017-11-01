package nds.mpm.prod.buy.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.prod.buy.service.PighisBuyService;
import nds.mpm.prod.buy.vo.PighisBuyVO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : PighisBuyController.java
 * @Description : 화면명 : 매입실적
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.21
 * @version 1.0
 * @see
 * 매입실적
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/pighisbuy")
public class PighisBuyController {
	
	private static final Logger _logger = LoggerFactory.getLogger(PighisBuyController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "pighisBuyService")
    private PighisBuyService pighisBuyService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * pighis_buy 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 PighisBuyDefaultVO
	 * @return "/pighisBuy/PighisBuyList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectPighisBuyList(
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String beginDate = req.getParameter("searchCondition");
    	String buyType = req.getParameter("searchCondition2");
    	
    	PighisBuyVO searchVO = new PighisBuyVO();
    	searchVO.setBuyType(buyType);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
		
        List<?> pighisBuyList = pighisBuyService.selectPighisBuyList(searchVO);
        
    	PageSet pageSet = new PageSet();
        
        int totCnt = pighisBuyService.selectPighisBuyListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(pighisBuyList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
}
