package nds.tmm.common.TMCOMT50.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.tmm.common.TMCOMT50.service.UdsLogService;
import nds.tmm.common.TMCOMT50.vo.UdsLogDefaultVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : TmMesgxmController.java
 * @Description : TmMesgxm Controller class
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
@RequestMapping("/tmm/{corpCode}/tmcomt50")
public class UdsLogController extends TMMBaseController
{
	@Autowired
	protected CorsFilter _filter;
	
    @Resource(name = "udsLogService")
    private UdsLogService udsLogService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * uds_log 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 UdsLogDefaultVO
	 * @return "/udsLog/UdsLogList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectUdsLogList(HttpServletRequest req)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	int msgType = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("msgType"),"0"));
    	
    	UdsLogDefaultVO searchVO = new UdsLogDefaultVO();

    	String frstDate = req.getParameter("frstDate");
    	String lastDate = req.getParameter("lastDate");
    	
    	/** EgovPropertyService.sample */
    	searchVO.setFrstDate(StringUtils.remove(frstDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setMsgType(msgType);

    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
		
        List<?> udsLogList = udsLogService.selectUdsLogList(searchVO);

        PageSet pageSet = new PageSet();

        int totCnt = udsLogService.selectUdsLogListTotCnt(searchVO);
        
        pageSet.setTotalRecordCount( totCnt );
		pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount( totCnt );
    	pageSet.setResult(udsLogList);
    	
    	result.setExtraData(pageSet);
        
        return _filter.makeCORSEntity( result );
    } 
}
