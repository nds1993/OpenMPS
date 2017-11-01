package nds.mpm.buy.PO0201.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.buy.PO0102.service.MpPigdxmService;
import nds.mpm.buy.PO0102.vo.MpPigdxmVO;
import nds.mpm.buy.PO0201.service.PO0201MpPigdxmService;
import nds.mpm.common.util.ExcelUtil;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.excel.PO;
import nds.mpm.login.vo.MPUserSession;

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
 * @Class Name :  PigDochController
 *
 * @author MPM TEAM
 * @since 2017.08.31
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 생돈구매세부입력( PO0201 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.31	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/po0201/mppigdxm")
public class PO0201MppigdxmController extends TMMBaseController {
	
	private static final Logger _logger = LoggerFactory.getLogger(PO0201MppigdxmController.class);
	
	@Autowired
	protected CorsFilter		_filter;

	@Resource(name = "PO0201MpPigdxmService")
    private PO0201MpPigdxmService pO0201MpPigdxmService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * pig_doch 목록을 조회한다. (pageing)
	 * 
	 * do_su 이력번호의 상세 도체번호 수
	 * 화면에서 생체중을 입력하면 생체중/두수의 값을 해당하는 상세생체중 값으로 일괄적용.
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}")
    public ResponseEntity<ResultEx> selectPigDochList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String searchCondition = req.getParameter("searchCondition");
    	String facKind = req.getParameter("facKind");
    	String custCode = req.getParameter("custCode");
    	
    	MpPigdxmVO searchVO = new MpPigdxmVO();
    	searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
		searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
		searchVO.setSearchCondition(searchCondition);
		
		searchVO.setFacKind(facKind);
		searchVO.setCustCode(custCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> pigDochList = pO0201MpPigdxmService.selectMpPigdxmList(searchVO);

    	PageSet pageSet = new PageSet();
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	
    	if(pigDochList != null)
    		pageSet.setTotalRecordCount(pigDochList.size());
    	pageSet.setResult(pigDochList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{strtDate}/{lastDate}/{hisNo}")
    public ResponseEntity<ResultEx> selectMpPigdxmList(
    	@PathVariable("corpCode") String corpCode,
    	@PathVariable("strtDate") String strtDate,
		@PathVariable("lastDate") String lastDate,
		@PathVariable("hisNo") String hisNo,
		HttpServletRequest req,
		HttpServletResponse res, 
		ModelMap model)
        throws Exception {
    
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	    MPUserSession sess = getSession(req);
		
	    int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
		int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
		
		String searchCondition = req.getParameter("searchCondition");
		
		MpPigdxmVO searchVO = new MpPigdxmVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
		searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
		searchVO.setSearchCondition(searchCondition);
    	
		searchVO.setHisNo(hisNo);
		
		searchVO.setPageIndex(pageIndex);
		searchVO.setPageSize(pageSize);
		
        List<?> mpPigdxmList = pO0201MpPigdxmService.selectMpPigdxmDetailList(searchVO);
	
		PageSet pageSet = new PageSet();
	    
	    pageSet.setPageIndex(pageIndex);
		pageSet.setPageSize(pageSize);
		
		if(mpPigdxmList!=null)
		pageSet.setTotalRecordCount(mpPigdxmList.size());
		pageSet.setResult(mpPigdxmList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{strtDate}/{lastDate}/{hisNo}/export")
    public ResponseEntity<ResultEx> selectExMpPigdxmList(
    	@PathVariable("corpCode") String corpCode,
    	@PathVariable("strtDate") String strtDate,
		@PathVariable("lastDate") String lastDate,
		@PathVariable("hisNo") String hisNo,
		HttpServletRequest req,
		HttpServletResponse res, 
		HttpSession jsession,
		ModelMap model)
        throws Exception {
    
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	    MPUserSession sess = getSession(req);
		
		String searchCondition = req.getParameter("searchCondition");
		
		MpPigdxmVO searchVO = new MpPigdxmVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
		searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
		searchVO.setSearchCondition(searchCondition);
    	
		searchVO.setHisNo(hisNo);
		
		List<EgovMap> pigDochList = pO0201MpPigdxmService.selectMpPigdxmList(searchVO);
		
        List<EgovMap> mpPigdxmList = pO0201MpPigdxmService.selectMpPigdxmDetailList(searchVO);
		
        ExcelUtil.createExcelDownloadEgovMapList(res, jsession, pigDochList, mpPigdxmList, PO.PP02011, PO.PP02012, PO.PP02011_TYPE, PO.PP02012_TYPE, PO.PP02011_NM);
        
		return null;
    }
    
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPigdxmView(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
            @RequestBody List<EgovMap> mpPigixmVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(mpPigixmVOs != null)
    	{
    		for(EgovMap vo : mpPigixmVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("strtDate",strtDate);
    			vo.put("lastDate",lastDate);
    			vo.put("dochDate",StringUtils.remove((String)vo.get("dochDate"), "-"));
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	result = pO0201MpPigdxmService.insertMpPigdxm(ilist);
        return _filter.makeCORSEntity( result );
    }
}
