package nds.tmm.common.TMCOMT40.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.tmm.common.TMCOCD10.web.TMCOCD10Controller;
import nds.tmm.common.TMCOMT40.service.IfLogtxmService;
import nds.tmm.common.TMCOMT40.vo.IfLogtxmDefaultVO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : IfLogtxmController.java
 * @Description : IfLogtxm Controller class
 * @Modification Information
 *
 * @author OpenMPS Team
 * @since 2017. 7. 18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/tmm/{corpCode}/tmcomt40")
public class IfLogtxmController extends TMMBaseController
{
	private static final Logger _logger = LoggerFactory.getLogger(TMCOCD10Controller.class);
	
	@Autowired
	protected CorsFilter		_filter;
	
    @Resource(name = "ifLogtxmService")
    private IfLogtxmService ifLogtxmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * if_logtxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 IfLogtxmDefaultVO
	 * @return "/ifLogtxm/IfLogtxmList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectIfLogtxmList(
       		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	IfLogtxmDefaultVO searchVO = new IfLogtxmDefaultVO();
    	
    	String frstDate = req.getParameter("frstDate");
    	String lastDate = req.getParameter("lastDate");

        searchVO.setCorpCode(corpCode);
    	searchVO.setIntrName( req.getParameter("intrName") );
    	searchVO.setFrstDate(StringUtils.remove(frstDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));

    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
        
		List<?> ifLogtxmList = ifLogtxmService.selectIfLogtxmList(searchVO);

    	PageSet pageSet = new PageSet();
        
		int totalRecordCount = ifLogtxmService.selectIfLogtxmListTotCnt(searchVO);
		pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totalRecordCount);
    	pageSet.setResult(ifLogtxmList);
    	
		result.setExtraData(pageSet);
        
        return _filter.makeCORSEntity( result );
    } 
   
}
