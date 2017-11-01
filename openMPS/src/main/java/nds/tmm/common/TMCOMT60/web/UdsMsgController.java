package nds.tmm.common.TMCOMT60.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.tmm.common.TMCOMT60.service.UdsMsgService;
import nds.tmm.common.TMCOMT60.vo.UdsMsgDefaultVO;
import nds.tmm.common.TMCOMT60.vo.UdsMsgVO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : UdsMsgController.java
 * @Description : UdsMsg Controller class
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
@RequestMapping("/tmm/{corpCode}/tmcomt60")
public class UdsMsgController extends TMMBaseController
{
	private static final Logger _logger = LoggerFactory.getLogger(UdsMsgController.class);
	
	@Autowired
	protected CorsFilter		_filter;
	
    @Resource(name = "udsMsgService")
    private UdsMsgService udsMsgService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * uds_msg 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 udsMsgDefaultVO
	 * @return "/udsMsg/udsMsgList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectUdsMsgList(
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	UdsMsgVO searchVO = new UdsMsgVO();
    	
    	String frstDate = req.getParameter("frstDate");
    	String lastDate = req.getParameter("lastDate");
    	
    	searchVO.setFrstDate(StringUtils.remove(frstDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));

    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
		List<?> udsMsgList = udsMsgService.selectUdsMsgList(searchVO);

    	PageSet pageSet = new PageSet();
        
		int totalRecordCount = udsMsgService.selectUdsMsgListTotCnt(searchVO);
		
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totalRecordCount);
    	pageSet.setResult(udsMsgList);
    	
		result.setExtraData(pageSet);
        
        return _filter.makeCORSEntity( result );
    } 

    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addUdsMsg(
    		@RequestBody List<EgovMap> UdsMsgVO,
    		HttpServletRequest req,
    		HttpServletResponse res
    		)
            throws Exception {

    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();

    	if(UdsMsgVO != null)
    	{
    		for(EgovMap vo : UdsMsgVO)
        	{
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	udsMsgService.insertUdsMsg(ilist);
        
    	return _filter.makeCORSEntity( result );
        
    }
    
}
