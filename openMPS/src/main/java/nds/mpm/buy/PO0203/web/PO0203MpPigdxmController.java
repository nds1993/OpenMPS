package nds.mpm.buy.PO0203.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.buy.PO0102.vo.MpPigdxmVO;
import nds.mpm.buy.PO0203.service.PO0203MpPigdxmService;
import nds.mpm.common.util.ExcelUtil;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.excel.PO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  PO0203MpPigdxmController
 *
 * @author MPM TEAM
 * @since 2017.09.06
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 출하정산집계표 ( PO0203 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.09.06	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/po0203/mppigdxm")
public class PO0203MpPigdxmController extends TMMBaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PO0203MpPigdxmController.class);

    @Resource(name = "PO0203MpPigdxmService")
    private PO0203MpPigdxmService mpPigdxmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_pigdxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPigdxmDefaultVO
	 * @return "/mpPigdxm/MpPigdxmList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPigdxmList(
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
		
		String facKind = req.getParameter("facKind");
		String custCode = req.getParameter("custCode");
    	String buyType = req.getParameter("buyType");
    	String brandCode = req.getParameter("brandCode");
    	String searchCondition = req.getParameter("gubun_1");
    	String searchCondition2 = StringUtils.defaultIfEmpty(req.getParameter("gubun_2"),"1");
    	String searchCondition3 = StringUtils.defaultIfEmpty(req.getParameter("gubun_3"),"1");
    	String searchCondition4 = StringUtils.defaultIfEmpty(req.getParameter("gubun_4"),"1");
    	
    	MpPigdxmVO searchVO = new MpPigdxmVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setFacKind(facKind);
    	searchVO.setBuyType(buyType);
    	searchVO.setBrandCode(brandCode);
    	searchVO.setCustCode(custCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchCondition2(searchCondition2);
    	searchVO.setSearchCondition3(searchCondition3);
    	searchVO.setSearchCondition4(searchCondition4);
		
        List<?> mpPigdxmList = null;
        
        if("0".equals(searchCondition2))
        {
        	//농장별합계
        	mpPigdxmList = mpPigdxmService.selectMpPigdxmList(searchVO);
        }
        else
        {
        	//개체별합계
        	mpPigdxmList = mpPigdxmService.selectMpPigdxmListGroupDoch(searchVO);
        }
        
        
        
        
        LOGGER.debug("searchCondition :: " + searchCondition);
    	
	
		PageSet pageSet = new PageSet();
	    
	    pageSet.setPageIndex(pageIndex);
		pageSet.setPageSize(pageSize);
		
		pageSet.setTotalRecordCount(mpPigdxmList.size());
		pageSet.setResult(mpPigdxmList);
		
		result.setExtraData(pageSet);
		return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{strtDate}/{lastDate}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpPigdxmList(
        @PathVariable("corpCode") String corpCode,
		@PathVariable("strtDate") String strtDate,
		@PathVariable("lastDate") String lastDate,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession jsession,
		ModelMap model)
        throws Exception {
	
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		String facKind = req.getParameter("facKind");
		String custCode = req.getParameter("custCode");
    	String buyType = req.getParameter("buyType");
    	String brandCode = req.getParameter("brandCode");
    	String searchCondition = StringUtils.defaultIfEmpty(req.getParameter("gubun_1"),"0");
    	String searchCondition2 = StringUtils.defaultIfEmpty(req.getParameter("gubun_2"),"1");
    	String searchCondition3 = StringUtils.defaultIfEmpty(req.getParameter("gubun_3"),"1");
    	String searchCondition4 = StringUtils.defaultIfEmpty(req.getParameter("gubun_4"),"1");
    	
    	MpPigdxmVO searchVO = new MpPigdxmVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setFacKind(facKind);
    	searchVO.setBuyType(buyType);
    	searchVO.setBrandCode(brandCode);
    	searchVO.setCustCode(custCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchCondition2(searchCondition2);
    	searchVO.setSearchCondition3(searchCondition3);
    	searchVO.setSearchCondition4(searchCondition4);
		
        List<EgovMap> mpPigdxmList = null;
        String colNames[] = null; 
        String colTypes[] = null;
        String sheetName = null;
        
        
        if("0".equals(searchCondition2))
        {
        	//농장별합계
        	mpPigdxmList = mpPigdxmService.selectMpPigdxmList(searchVO);
        	colNames = PO.PP02031; 
            colTypes = PO.PP02031_TYPE;
            sheetName = PO.PP02031_NM;
        }
        else
        {
        	//개체별합계
        	mpPigdxmList = mpPigdxmService.selectMpPigdxmListGroupDoch(searchVO);
        	colNames = PO.PP02032; 
            colTypes = PO.PP02032_TYPE;
            sheetName = PO.PP02032_NM;
        }
        
        LOGGER.debug("searchCondition :: " + searchCondition);
    	
        ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpPigdxmList, colNames, colTypes, sheetName);
        
		return null;
		
    }

}
