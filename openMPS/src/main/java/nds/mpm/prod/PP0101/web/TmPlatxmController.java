package nds.mpm.prod.PP0101.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.common.util.ExcelUtil;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.excel.PP;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.prod.PP0101.service.TmPlatxmService;
import nds.mpm.prod.PP0101.vo.TmPlatxmDefaultVO;
import nds.mpm.prod.PP0101.vo.TmPlatxmVO;

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
import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
 * @Class Name :  TmPlatxmController
 *
 * @author MPM TEAM
 * @since 2017.07.31
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 공장등록
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.07.31	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/pp0101/tmplatxm")
public class TmPlatxmController extends TMMBaseController{
	
	private static final Logger _logger = LoggerFactory.getLogger(TmPlatxmController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "tmPlatxmService")
    private TmPlatxmService tmPlatxmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_platxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TmPlatxmDefaultVO
	 * @return "/tmPlatxm/TmPlatxmList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTmPlatxmList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String grupPlant = req.getParameter("grupPlant");
    	String plantCode = req.getParameter("plantCode");
    	String prdtType = req.getParameter("prdtType");
    	String searchKeyword = req.getParameter("searchKeyword");
    	String useYn = req.getParameter("useYn");
    	String searchCondition = req.getParameter("searchCondition");
    	
    	TmPlatxmVO searchVO = new TmPlatxmVO();
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setGrupPlant(grupPlant);
    	searchVO.setPrdtType(prdtType);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setSearchKeyword(searchKeyword);
    	searchVO.setUseYn(useYn);
    	searchVO.setSearchCondition(searchCondition);
    	
    	 List<?> tmPlatxmList = null;
    	 
    	 if("grup".equals(searchCondition))
    		 tmPlatxmList = tmPlatxmService.selectGrupTmPlatxmList(searchVO);
    	 else
    		 tmPlatxmList = tmPlatxmService.selectTmPlatxmList(searchVO);
        
    	PageSet pageSet = new PageSet();
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	
    	if(tmPlatxmList != null)
    		pageSet.setTotalRecordCount(tmPlatxmList.size());
    	pageSet.setResult(tmPlatxmList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExTmPlatxmList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	String grupPlant = req.getParameter("grupPlant");
    	String plantCode = req.getParameter("plantCode");
    	String prdtType = req.getParameter("prdtType");
    	String searchKeyword = req.getParameter("searchKeyword");
    	
    	TmPlatxmVO searchVO = new TmPlatxmVO();
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setGrupPlant(grupPlant);
    	searchVO.setPrdtType(prdtType);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setSearchKeyword(searchKeyword);
    	
    	List<EgovMap> tmPlatxmList = tmPlatxmService.selectTmPlatxmList(searchVO);
    	String[] columns = PP.PP0101;
        String sheetName = PP.PP0101_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, tmPlatxmList, columns, PP.PP0101_TYPE, sheetName);
    	
    	return null;
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTmPlatxm(
    		@PathVariable("corpCode") String corpCode,
    		@RequestBody List<EgovMap> tmPlatxmVOs,
    		HttpServletRequest req,
    		HttpServletResponse res
    		)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(tmPlatxmVOs != null)
    	{
    		for(EgovMap vo : tmPlatxmVOs)
        	{
    			vo.put("corpCode",corpCode);
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	result = tmPlatxmService.insertTmPlatxm(ilist);
        return _filter.makeCORSEntity( result );
        
    }
    
    @RequestMapping(value="/dup",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> checkDupTmPlatxm(
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String plantCode = req.getParameter("plantCode");
    	String plantName = req.getParameter("plantName");
        
    	TmPlatxmVO searchVO = new TmPlatxmVO();
    	searchVO.setPlantCode(plantCode);
    	searchVO.setPlantCode(plantName);
    	
    	result.setExtraData((tmPlatxmService.checkDupTmPlatxm_S(searchVO) > 0 ? "Y":"N"));
    	
    	return _filter.makeCORSEntity( result );
    }
    /**
    @RequestMapping("/tmPlatxm/updateTmPlatxm.do")
    public String updateTmPlatxm(
            TmPlatxmVO tmPlatxmVO,
            @ModelAttribute("searchVO") TmPlatxmDefaultVO searchVO, SessionStatus status)
            throws Exception {
        //tmPlatxmService.updateTmPlatxm(tmPlatxmVO);
        status.setComplete();
        return "forward:/tmPlatxm/TmPlatxmList.do";
    }
    
    @RequestMapping("/tmPlatxm/deleteTmPlatxm.do")
    public String deleteTmPlatxm(
            TmPlatxmVO tmPlatxmVO,
            @ModelAttribute("searchVO") TmPlatxmDefaultVO searchVO, SessionStatus status)
            throws Exception {
        //tmPlatxmService.deleteTmPlatxm(tmPlatxmVO);
        status.setComplete();
        return "forward:/tmPlatxm/TmPlatxmList.do";
    }
	**/
}
