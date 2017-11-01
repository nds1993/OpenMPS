package nds.mpm.prod.PP0304.web;

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
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.excel.PP;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.prod.PP0304.service.MpAcceptOrdrPmMService;
import nds.mpm.prod.PP0304.vo.MpAcceptOrdrPmMVO;

import org.apache.commons.lang.StringUtils;
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
 * @Class Name :  MpAcceptOrdrPmMController
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 생산의뢰접수(PM주문)
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.06.07	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/pp0304/mpacceptordrpmm")
public class MpAcceptOrdrPmMController extends TMMBaseController{

    @Resource(name = "mpAcceptOrdrPmMService")
    private MpAcceptOrdrPmMService mpAcceptOrdrPmMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * PM 접수내역을 조회
	 * status = "naccept" 미접수내역, "accept" 접수내역, 
	 * @param searchVO - 조회할 정보가 담긴 MpAcceptOrdrPmMDefaultVO
	 * @return "/mpAcceptOrdrPmM/MpAcceptOrdrPmMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{plantCode}/{delvDate}/{status}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpAcceptOrdrPmMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("delvDate") String delvDate,
    		@PathVariable("status") String status,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String strtDate = req.getParameter("strtDate");
    	String lastDate = req.getParameter("lastDate");
    	
    	MpAcceptOrdrPmMVO searchVO = new MpAcceptOrdrPmMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	
        List<?> mpLpcInfoMList = null;
        int totCnt = 0;
        
        if("accept".equals(status))
        {
        	mpLpcInfoMList = mpAcceptOrdrPmMService.selectMpAcceptOrdrPmMList(searchVO);
        	totCnt = mpAcceptOrdrPmMService.selectMpAcceptOrdrPmMListTotCnt(searchVO);
        }
        else if("naccept".equals(status))
        {
        	mpLpcInfoMList = mpAcceptOrdrPmMService.selectMpOrderDList(searchVO);
        	totCnt = mpAcceptOrdrPmMService.selectMpOrderDListTotCnt(searchVO);
        }
        else{
        	return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS ) );
        }
        
    	PageSet pageSet = new PageSet();
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpLpcInfoMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{plantCode}/{delvDate}/{status}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpAcceptOrdrPmMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("delvDate") String delvDate,
    		@PathVariable("status") String status,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	MpAcceptOrdrPmMVO searchVO = new MpAcceptOrdrPmMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setDelvDate(StringUtils.remove(delvDate,"-"));
    	
        List<EgovMap> mpLpcInfoMList = null;
        int totCnt = 0;
        
        if("accept".equals(status))
        {
        	mpLpcInfoMList = mpAcceptOrdrPmMService.selectMpAcceptOrdrPmMList(searchVO);
        }
        else if("naccept".equals(status))
        {
        	mpLpcInfoMList = mpAcceptOrdrPmMService.selectMpOrderDList(searchVO);
        }
        else{
        	return null;
        }
        String[] columns = PP.PP0304;
        String sheetName = PP.PP0304_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpLpcInfoMList, columns, PP.PP0304_TYPE, sheetName);
    	return null;
    }
    @RequestMapping(value="/{plantCode}/{delvDate}/{status}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpAcceptOrdrPmM(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("delvDate") String delvDate,
    		@PathVariable("status") String status,
    		@RequestBody List<EgovMap> MpAcceptOrdrPmMVOs,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(MpAcceptOrdrPmMVOs != null)
    	{
    		for(EgovMap vo : MpAcceptOrdrPmMVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("delvDate",StringUtils.remove((String)vo.get("delvDate"),"-"));
    			vo.put("plantCode",plantCode);
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    		
    		if("naccept".equals(status))
            {
    			result = mpAcceptOrdrPmMService.updateMpCancel(MpAcceptOrdrPmMVOs);
            }
            else if("accept".equals(status))
            {
            	result = mpAcceptOrdrPmMService.insertMpAcceptOrdrPmM(MpAcceptOrdrPmMVOs);
            }
            else{
            	return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS ) );
            }
    		
    	}
    	
        return _filter.makeCORSEntity( result );
    }

    /**
    @RequestMapping("/mpAcceptOrdrPmM/addMpAcceptOrdrPmMView.do")
    public String addMpAcceptOrdrPmMView(
            @ModelAttribute("searchVO") MpAcceptOrdrPmMDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpAcceptOrdrPmMVO", new MpAcceptOrdrPmMVO());
        return "/mpAcceptOrdrPmM/MpAcceptOrdrPmMRegister";
    }
    @RequestMapping("/mpAcceptOrdrPmM/selectMpAcceptOrdrPmM.do")
    public @ModelAttribute("mpAcceptOrdrPmMVO")
    MpAcceptOrdrPmMVO selectMpAcceptOrdrPmM(
            MpAcceptOrdrPmMVO mpAcceptOrdrPmMVO,
            @ModelAttribute("searchVO") MpAcceptOrdrPmMDefaultVO searchVO) throws Exception {
        return mpAcceptOrdrPmMService.selectMpAcceptOrdrPmM(mpAcceptOrdrPmMVO);
    }
	*/
}
