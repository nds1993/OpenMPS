package nds.mpm.prod.PP0307.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.prod.PP0303.vo.MpPlanPlatDVO;
import nds.mpm.prod.PP0307.service.PP0307MpBarProMService;
import nds.mpm.prod.PP0504.vo.MpBarProMVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :  PP0307MpPlanPlatDController
 *
 * @author MPM TEAM
 * @since 2017.08.29
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 생산계획 대비 실적현황 조회( PP0307 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.29	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0307/mpplanplatd")
public class PP0307MpPlanPlatDController extends TMMBaseController{

	@Resource(name = "PP0307mpBarProMService")
	private  PP0307MpBarProMService mpPlanCmHService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    @RequestMapping(value="/{plantCode}/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPlanPlatDList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpBarProMVO searchVO = new MpBarProMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setLastDate(lastDate);
    	searchVO.setPlantCode(plantCode);
    	
        List<?> mpPlanSetupMList = mpPlanCmHService.selectMpBarProMList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpPlanCmHService.selectMpBarProMListTotCnt(searchVO);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpPlanSetupMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/pop/{plantCode}/{proCode}/{proDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectPopMpPlanPlatDList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("proCode") String proCode,
    		@PathVariable("proDate") String proDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        MpBarProMVO searchVO = new MpBarProMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setProCode(proCode);
    	searchVO.setProDate(StringUtils.replace(proDate, "-", ""));
    	
        List<?> mpPlanSetupMList = mpPlanCmHService.selectPopMpBarProMList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpPlanSetupMList!=null)
    		pageSet.setTotalRecordCount(mpPlanSetupMList.size());
    	pageSet.setResult(mpPlanSetupMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }

}
