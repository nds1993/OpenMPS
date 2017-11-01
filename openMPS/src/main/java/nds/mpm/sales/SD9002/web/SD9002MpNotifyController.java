package nds.mpm.sales.SD9002.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.sales.SD0805.service.MpNotifyService;
import nds.mpm.sales.SD0805.vo.MpNotifyVO;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;


/**
 * @Class Name :  MpNotifyController
 *
 * @author MPM TEAM
 * @since 2017. 9. 14.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 공지사항 ( 모바일 조회 )
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
@RequestMapping("/mpm/{corpCode}/sd9002/mpnotify")
public class SD9002MpNotifyController extends TMMBaseController{

    @Resource(name = "mpNotifyService")
    private MpNotifyService mpNotifyService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * 공지사항
	 */
    @RequestMapping(value="/{openGubn}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpNotifyList(
	    	@PathVariable("corpCode") String corpCode,
	    	@PathVariable("openGubn") String openGubn,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	 	
		String strtDate = req.getParameter("strtDate");
		String lastDate = req.getParameter("lastDate");
		
		MpNotifyVO searchVO = new MpNotifyVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setOpenGubn(openGubn);
		searchVO.setStrtDate(strtDate);
		searchVO.setLastDate(lastDate);
	 	
        List<?> mpNotifyList = mpNotifyService.selectMpNotifyList(searchVO);
	    
        int totCnt = mpNotifyService.selectMpNotifyListTotCnt(searchVO);

	    PageSet pageSet = new PageSet();
	    
	    pageSet.setTotalRecordCount(totCnt);
		pageSet.setResult(mpNotifyList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    }
}