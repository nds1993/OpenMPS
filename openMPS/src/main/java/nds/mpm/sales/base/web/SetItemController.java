package nds.mpm.sales.base.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.sales.base.service.SetItemService;
import nds.mpm.sales.base.vo.SetItemDefaultVO;
import nds.mpm.sales.base.vo.SetItemVO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : SetItemController.java
 *
 * @author MPM TEAM
 * @since 2017.06.09
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 세트제품관리.

 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.06.27	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/setitem")
public class SetItemController {
	
	private static final Logger _logger = LoggerFactory.getLogger(SetItemController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "setItemService")
    private SetItemService setItemService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * set_item 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SetItemDefaultVO
	 * @return "/setItem/SetItemList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSetItemList(
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"1"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"10"));
    	
    	SetItemVO searchVO = new SetItemVO();
    	
        List<?> setItemList = setItemService.selectSetItemList(searchVO);
        
        PageSet pageSet = new PageSet();
        
        int totCnt = setItemService.selectSetItemListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(setItemList);
        
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addSetItem(
            @RequestBody SetItemVO setItemVO,
            @ModelAttribute("searchVO") SetItemDefaultVO searchVO, SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        setItemService.insertSetItem(setItemVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{setCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSetItem(
    		@PathVariable("setCode") String setCode
            ) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	SetItemVO setItemVO = new SetItemVO();
    	setItemVO.setSetCode(setCode);
    	
    	result.setExtraData(setItemService.selectSetItem(setItemVO));
    	return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{setCode}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateSetItem(
    		@PathVariable("setCode") String setCode,
    		@RequestBody SetItemVO setItemVO,
            SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        setItemService.updateSetItem(setItemVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{setCode}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteSetItem(
    		@PathVariable("setCode") String setCode,
    		SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	SetItemVO setItemVO = new SetItemVO();
    	setItemVO.setSetCode(setCode);
    	
        setItemService.deleteSetItem(setItemVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }

}
