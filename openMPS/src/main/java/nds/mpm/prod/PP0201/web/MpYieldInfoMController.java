package nds.mpm.prod.PP0201.web;

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
import nds.mpm.prod.PP0201.service.MpYieldInfoMService;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMVO;

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
 * @Class Name :  MpYieldInfoMController
 *
 * @author MPM TEAM
 * @since 2017.08.01
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 수율기초등록( PP0201 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.01	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/pp0201/mpyieldinfom")
public class MpYieldInfoMController extends TMMBaseController{
	
	private static final Logger _logger = LoggerFactory.getLogger(MpYieldInfoMController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpYieldInfoMService")
    private MpYieldInfoMService mpYieldInfoMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_yield_info_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpYieldInfoMDefaultVO
	 * @return "/mpYieldInfoM/MpYieldInfoMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpYieldInfoMList(
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
    	
    	String grupPlant = req.getParameter("grupPlant");
    	
    	MpYieldInfoMVO searchVO = new MpYieldInfoMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setGrupPlant(grupPlant);
    	
        List<?> mpYieldInfoMList = mpYieldInfoMService.selectMpYieldInfoMList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpYieldInfoMService.selectMpYieldInfoMListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpYieldInfoMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{strtDate}/{lastDate}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpYieldInfoMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String grupPlant = req.getParameter("grupPlant");
    	
    	MpYieldInfoMVO searchVO = new MpYieldInfoMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setGrupPlant(grupPlant);
    	
        List<EgovMap> mpYieldInfoMList = mpYieldInfoMService.selectMpYieldInfoMList(searchVO);

        String[] columns = PP.PP0201;
        String sheetName = PP.PP0201_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpYieldInfoMList, columns, PP.PP0201_TYPE, sheetName);
		
    	return null;
    } 
    
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpYieldInfoM(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
            @RequestBody List<EgovMap> mpYieldInfoMVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(mpYieldInfoMVOs != null)
    	{
    		for(EgovMap vo : mpYieldInfoMVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("strtDate",StringUtils.remove(strtDate,"-"));
    			vo.put("lastDate",StringUtils.remove(lastDate,"-"));
    			vo.put("workDate",StringUtils.remove((String)vo.get("workDate"),"-"));
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
        result = mpYieldInfoMService.insertMpYieldInfoM(ilist);
        return _filter.makeCORSEntity( result );
    }
    /**
     * 
    @RequestMapping("/mpYieldInfoM/addMpYieldInfoMView.do")
    public String addMpYieldInfoMView(
            @ModelAttribute("searchVO") MpYieldInfoMDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpYieldInfoMVO", new MpYieldInfoMVO());
        return "/mpYieldInfoM/MpYieldInfoMRegister";
    }
    @RequestMapping("/mpYieldInfoM/updateMpYieldInfoM.do")
    public String updateMpYieldInfoM(
            MpYieldInfoMVO mpYieldInfoMVO,
            @ModelAttribute("searchVO") MpYieldInfoMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpYieldInfoMService.updateMpYieldInfoM(mpYieldInfoMVO);
        status.setComplete();
        return "forward:/mpYieldInfoM/MpYieldInfoMList.do";
    }
    
    @RequestMapping("/mpYieldInfoM/deleteMpYieldInfoM.do")
    public String deleteMpYieldInfoM(
            MpYieldInfoMVO mpYieldInfoMVO,
            @ModelAttribute("searchVO") MpYieldInfoMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpYieldInfoMService.deleteMpYieldInfoM(mpYieldInfoMVO);
        status.setComplete();
        return "forward:/mpYieldInfoM/MpYieldInfoMList.do";
    }
	*/
}
