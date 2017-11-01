package nds.mpm.sales.SD0407.web;

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
import nds.mpm.sales.SD0405.service.MpOrderUploadService;
import nds.mpm.sales.SD0405.vo.MpOrderUploadVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

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
@RequestMapping("/mpm/{corpCode}/sd0407/mporderupload")
public class SD0407MpOrderUploadController extends TMMBaseController{
	
	private static final Logger _logger = LoggerFactory.getLogger(SD0407MpOrderUploadController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpOrderUploadService")
    private MpOrderUploadService mpOrderUploadService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @RequestMapping(value="")
    public ResponseEntity<ResultEx> selectMpOrderHList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String custCode = req.getParameter("custCode");
    	
    	MpOrderUploadVO searchVO = new MpOrderUploadVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setCustCode(custCode);
    	
        List<?> mpOrderHList = mpOrderUploadService.selectMpOrderUploadList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpOrderUploadService.selectMpOrderUploadListTotCnt(searchVO);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpOrderHList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/{ordrCust}")
    public ResponseEntity<ResultEx> selectMpOrderUpload(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("ordrCust") String ordrCust,
            Model model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        EgovMap searchVO = new EgovMap();
        
        searchVO.put("corpCode",corpCode);
        searchVO.put("ordrCust",ordrCust);
        
    	result.setExtraData(mpOrderUploadService.selectMpOrderUpload(searchVO));
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{ordrCust}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpOrderUpload(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("ordrCust") String ordrCust,
    		@RequestBody EgovMap mpOrderUploadVO,
    		HttpServletRequest req,
    		HttpServletResponse res
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	mpOrderUploadVO.put("corpCode", corpCode);
    	mpOrderUploadVO.put("ordrCust", ordrCust);
    	mpOrderUploadVO.put("crUser", ordrCust);
    	mpOrderUploadVO.put("mdUser", ordrCust);
    	
    	result = mpOrderUploadService.insertMpOrderUpload(mpOrderUploadVO);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{ordrCust}",method=RequestMethod.PUT)
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
}
