package nds.mpm.sales.SD0405.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.sales.SD0405.service.MpOrderUploadService;
import nds.mpm.sales.SD0405.vo.MpOrderUploadDefaultVO;
import nds.mpm.sales.SD0407.web.SD0407MpOrderUploadController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : MpOrderUploadController.java
 * @Description : MpOrderUpload Controller class
 * @Modification Information
 *
 * @author ㅇ
 * @since ㅇ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/sd0405/mporderupload")
public class MpOrderUploadController {
	
	private static final Logger _logger = LoggerFactory.getLogger(MpOrderUploadController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpOrderUploadService")
    private MpOrderUploadService mpOrderUploadService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_order_upload 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpOrderUploadDefaultVO
	 * @return "/mpOrderUpload/MpOrderUploadList"
	 * @exception Exception
	 */
    @RequestMapping(value="")
    public String selectMpOrderUploadList(@ModelAttribute("searchVO") MpOrderUploadDefaultVO searchVO, 
    		ModelMap model)
            throws Exception {
    	
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List<?> mpOrderUploadList = mpOrderUploadService.selectMpOrderUploadList(searchVO);
        model.addAttribute("resultList", mpOrderUploadList);
        
        int totCnt = mpOrderUploadService.selectMpOrderUploadListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/mpOrderUpload/MpOrderUploadList";
    } 
    
    /**not use SD0407 moved
   // @RequestMapping("/{ordrCust}")
    public ResponseEntity<ResultEx> addMpOrderUploadView(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("ordrCust") String ordrCust,
            Model model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MpOrderUploadVO searchVO = new MpOrderUploadVO();
        
        searchVO.setCorpCode(corpCode);
        searchVO.setOrdrCust(ordrCust);
        
    	result.setExtraData(mpOrderUploadService.selectMpOrderUpload(searchVO));
    	return _filter.makeCORSEntity( result );
    }
    
    
    //@RequestMapping(value="/{ordrCust}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpOrderUpload(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("ordrCust") String ordrCust,
    		@RequestBody EgovMap mpOrderUploadVO,
    		HttpServletRequest req,
    		HttpServletResponse res
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	mpOrderUploadVO.put("corpCode", corpCode);
    	mpOrderUploadVO.put("ordrCust", ordrCust);
    	
    	result = mpOrderUploadService.insertMpOrderUpload(mpOrderUploadVO);
        return _filter.makeCORSEntity( result );
    }
    
    
    @RequestMapping(value="",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateMpOrderUpload(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("ordrCust") String ordrCust,
            @RequestBody EgovMap mpOrderUploadVO,
            HttpServletRequest req
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	mpOrderUploadVO.put("corpCode", corpCode);
    	mpOrderUploadVO.put("ordrCust", ordrCust);
    	
        mpOrderUploadService.updateMpOrderUpload(mpOrderUploadVO);
        result.setExtraData(mpOrderUploadVO);
        return _filter.makeCORSEntity( result );
    }
    **/
    @RequestMapping(value="/{ordrCust}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteMpOrderUpload(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("ordrCust") String ordrCust,
    		@RequestBody EgovMap mpOrderUploadVO
    		)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	mpOrderUploadVO.put("corpCode", corpCode);
    	mpOrderUploadVO.put("ordrCust", ordrCust);
    	
        mpOrderUploadService.deleteMpOrderUpload(mpOrderUploadVO);
        return _filter.makeCORSEntity( result );
    }

    @RequestMapping("/test/post")
    public ResponseEntity<ResultEx> testaddMpOrderUpload(
    		@PathVariable("corpCode") String corpCode,
            Model model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        List<EgovMap> convList = new ArrayList();
        
        
        
    	result.setExtraData(convList);
    	return _filter.makeCORSEntity( result );
    }
}
