package nds.mpm.sales.base.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.common.exception.TMMControllerExcepHndlr;
import nds.mpm.common.util.ExcelUtil;
import nds.mpm.common.util.FileUtil;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.sales.base.service.MpLpcixmService;
import nds.mpm.sales.base.vo.MpLpcixmVO;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  MpLpcixmController
 *
 * @author MPM TEAM
 * @since 2017.06.27
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : LPC 코드관리.

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
@RequestMapping("/mpm/mplpcixm")
public class MpLpcixmController extends TMMControllerExcepHndlr {
	
	private static final Logger _logger = LoggerFactory.getLogger(MpLpcixmController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpLpcixmService")
    private MpLpcixmService mpLpcixmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * MP_LPCIXM 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpLpcixmDefaultVO
	 * @return "/mpLpcixm/MpLpcixmList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpLpcixmList(
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model,
    		HttpSession jsession)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"10"));
    	int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"1"));
    	
    	MpLpcixmVO searchVO = new MpLpcixmVO();
    	searchVO.setPageSize(pageSize);
    	searchVO.setPageIndex(pageIndex);
    	
        List<EgovMap> mpLpcixmList = mpLpcixmService.selectMpLpcixmList(searchVO);
        
        PageSet pageSet = new PageSet();
        pageSet.setTotalRecordCount(mpLpcixmList.size());
    	pageSet.setResult(mpLpcixmList);
    	
    	String contextroot = jsession.getServletContext().getRealPath("/");
    	
    	String[] column = {"corpCode","lpcCode","lpcName"};
    	String[] columnType = {"C","C","C"};
    	
    	String fileName = "mplpcinfo.xlsx";
    	String filePath = contextroot + "excel/";
    	
    	//String mkFileName = ExcelUtil.createExcel(column, columnType, exportList, filePath + fileName, "mplpcinfo");
    	//FileUtil.downloadFile(filePath, fileName, res);
    	
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpLpcixmList, column,columnType, "mplpcinfo");
    	
    	
    	log.debug("mkFileName :: " + filePath + fileName);
        
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    	//return null;
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpLpcixm(
            @RequestBody MpLpcixmVO mpLpcixmVO,
            SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        mpLpcixmService.insertMpLpcixm(mpLpcixmVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{lpcKind}/{lpcCode}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateMpLpcixmView(
            @RequestBody MpLpcixmVO mpLpcixmVO ,
            Model model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        mpLpcixmService.updateMpLpcixm(mpLpcixmVO);
        
        return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{lpcKind}/{lpcCode}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteMpLpcixm(
    		@PathVariable("lpcKind") String lpcKind, 
    		@PathVariable("lpcCode") String lpcCode,
    		SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpLpcixmVO mpLpcixmVO = new MpLpcixmVO();
    	mpLpcixmVO.setLpcKind(lpcKind);
    	mpLpcixmVO.setLpcCode(lpcCode);
    	
        mpLpcixmService.deleteMpLpcixm(mpLpcixmVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }

}
