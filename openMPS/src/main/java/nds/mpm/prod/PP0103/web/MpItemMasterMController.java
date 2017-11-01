package nds.mpm.prod.PP0103.web;

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
import nds.mpm.prod.PP0103.service.MpItemMasterMService;
import nds.mpm.prod.PP0103.vo.MpItemMasterMVO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpItemMasterMController.java
 *
 * @author MPM TEAM
 * @since 2017.06.08
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 제품등록( PP0103 ).

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
@RequestMapping("/mpm/{corpCode}/pp0103/mpitemmasterm")
public class MpItemMasterMController extends TMMBaseController {
	
	private static final Logger _logger = LoggerFactory.getLogger(MpItemMasterMController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpItemMasterMService")
    private MpItemMasterMService mpItemMasterMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * product_info 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpItemMasterMDefaultVO
	 * @return "/MpItemMasterM/MpItemMasterMList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpItemMasterMList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String searchCondition = req.getParameter("searchCondition");
    	String salesman = req.getParameter("salesman");
    	String searchKeyword = req.getParameter("searchKeyword");
    	String setYn = req.getParameter("setYn");
    	String proCode = req.getParameter("proCode");
    	
    	String animalKind = req.getParameter("animalKind");
    	String proKind = req.getParameter("proKind");
    	String prdtType = req.getParameter("prdtType");
    	
    	MpItemMasterMVO searchVO = new MpItemMasterMVO();
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchKeyword(searchKeyword);	
    	searchVO.setSetYn(setYn);
    	searchVO.setProCode(proCode);
    	
    	searchVO.setAnimalKind(animalKind);
    	searchVO.setProKind(proKind);
    	searchVO.setPrdtType(prdtType);
    	
    	searchVO.setPageSize(pageSize);
    	searchVO.setPageIndex(pageIndex);
    	
        List<?> userInfoList = mpItemMasterMService.selectMpItemMasterMList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpItemMasterMService.selectMpItemMasterMListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(userInfoList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpItemMasterMList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String searchCondition = req.getParameter("searchCondition");
    	String salesman = req.getParameter("salesman");
    	String searchKeyword = req.getParameter("searchKeyword");
    	String setYn = req.getParameter("setYn");
    	String proCode = req.getParameter("proCode");
    	
    	MpItemMasterMVO searchVO = new MpItemMasterMVO();
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchKeyword(searchKeyword);	
    	searchVO.setSetYn(setYn);
    	searchVO.setProCode(proCode);
    	
    	searchVO.setPageSize(pageSize);
    	searchVO.setPageIndex(pageIndex);
    	
        List<EgovMap> userInfoList = mpItemMasterMService.selectMpItemMasterMList(searchVO);

        String[] columns = PP.PP0103;
        String sheetName = PP.PP0103_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, userInfoList, columns, PP.PP0103_TYPE, sheetName);
    	
    	return null;
    } 
    
    @RequestMapping(value="/{salesman}/code",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpItemMasterMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("salesman") String salesman,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String searchKeyword = req.getParameter("searchKeyword");
    	String animalKind = req.getParameter("animalKind");
    	String prdtType = req.getParameter("prdtType");
    	String setYn = req.getParameter("setYn");
    	
    	MpItemMasterMVO searchVO = new MpItemMasterMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setAnimalKind(animalKind);
    	searchVO.setPrdtType(prdtType);
    	searchVO.setSetYn(setYn);
    	searchVO.setSearchKeyword(searchKeyword);
    	
    	searchVO.setSalesmanLevel(getSalesmanLevel(req));
    	
        List<?> userInfoList = mpItemMasterMService.selectMpItemMasterMCodeList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	pageSet.setResult(userInfoList);
    	
    	if(userInfoList != null)
    		pageSet.setTotalRecordCount(userInfoList.size());
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpItemMasterM(
    		@PathVariable("corpCode") String corpCode,
    		@RequestBody List<EgovMap> mpItemMasterMVOs,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(mpItemMasterMVOs != null)
    	{
    		for(EgovMap vo : mpItemMasterMVOs)
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
    	
    	result = mpItemMasterMService.insertMpItemMasterM(ilist);
        return _filter.makeCORSEntity( result );
    }
}
