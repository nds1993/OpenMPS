package nds.mpm.prod.PP0402.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.common.util.ExcelUtil;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.excel.PP;
import nds.mpm.prod.PP0401.vo.MpReqMtrlMVO;
import nds.mpm.prod.PP0402.service.PP0402MpReqMtrlMService;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  MpBarProMController
 *
 * @author MPM TEAM
 * @since 2017.08.24
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 자재소요량  조회 ( PP0402 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.28	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0402/mpreqmtrlm")
public class PP0402MpReqMtrlMController extends TMMBaseController{

    @Resource(name = "PP0402MpReqMtrlMService")
    private PP0402MpReqMtrlMService MpReqMtrlMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * 자재 소요량 조회 - 제품+자재별
	 * @exception Exception
	 */
    @RequestMapping(value="/tab1/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpReqMtrlMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        /*String plantCode = req.getParameter("plantCode");*/
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String plantCode = req.getParameter("plantCode");
    	
    	MpReqMtrlMVO searchVO = new MpReqMtrlMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setPlantCode(plantCode);
    	
        List<?> mpReqMtrlMList = MpReqMtrlMService.selectProcodeMtrlCodeMpReqMtrlMList_D(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = MpReqMtrlMService.selectProcodeMtrlCodeMpReqMtrlMListTotCnt_S(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpReqMtrlMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    @RequestMapping(value="/tab1/{strtDate}/{lastDate}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpReqMtrlMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	String plantCode = req.getParameter("plantCode");    	
    	
    	MpReqMtrlMVO searchVO = new MpReqMtrlMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setPlantCode(plantCode);
    	
        List<EgovMap> mpReqMtrlMList = MpReqMtrlMService.selectProcodeMtrlCodeMpReqMtrlMList_D(searchVO);

        String[] columns = PP.PP04021;
        String sheetName = PP.PP04021_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpReqMtrlMList, columns, PP.PP04021_TYPE, sheetName);
    	    	
    	return null;
    } 
    
    /**
     * 자재 소요량 조회 - 자재별
   	 * mp_req_mtrl_m 자제별 목록을 조회한다. (pageing)
   	 * @exception Exception
   	 */
    @RequestMapping(value="/tab2/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTab2MpReqMtrlMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        /*String plantCode = req.getParameter("plantCode");*/
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String plantCode = req.getParameter("plantCode");
    	
    	MpReqMtrlMVO searchVO = new MpReqMtrlMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setPlantCode(plantCode);
    	
        List<?> mpReqMtrlMList = MpReqMtrlMService.selectMtrlCodeMpReqMtrlMList_D(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = MpReqMtrlMService.selectMtrlCodeMpReqMtrlMListTotCnt_S(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpReqMtrlMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    @RequestMapping(value="/tab2/{strtDate}/{lastDate}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExTab2MpReqMtrlMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String plantCode = req.getParameter("plantCode");
        
    	MpReqMtrlMVO searchVO = new MpReqMtrlMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setPlantCode(plantCode);
    	
        List<EgovMap> mpReqMtrlMList = MpReqMtrlMService.selectMtrlCodeMpReqMtrlMList_D(searchVO);

        String[] columns = PP.PP04022;
        String sheetName = PP.PP04022_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpReqMtrlMList, columns, PP.PP04022_TYPE, sheetName);
    	    	
    	return null;
    }

}
