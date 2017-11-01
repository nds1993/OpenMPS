package nds.mpm.prod.PP0204.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.prod.PP0204.service.PP0204MpYieldInfoMService;
import nds.mpm.prod.PP0204.vo.MpYieldInfoMVO;

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
 * @Class Name :  PP0204MpYieldInfoMController
 *
 * @author MPM TEAM
 * @since 2017. 9. 12.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 품목별생산량및수율조회( PP0204 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 9. 12.	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0204/mpyieldinfom")
public class PP0204MpYieldInfoMController extends TMMBaseController{

    @Resource(name = "PP0204mpYieldInfoMService")
    private PP0204MpYieldInfoMService mpYieldInfoMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /***
     * 헤더 집계 테이블 조회 
     * return row 1건
     * 리스트가 아닌 단일건 객체 반환. 
     * */
    @RequestMapping(value="/head/{strtDate}/{grupPlant}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectHeadMpYieldInfoMList(
    	@PathVariable("corpCode") String corpCode,
   		@PathVariable("strtDate") String strtDate,
   		@PathVariable("grupPlant") String grupPlant,
   		HttpServletRequest req,
   		HttpServletResponse res,
   		ModelMap model)
       throws Exception {
	
       ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
   	
       MpYieldInfoMVO searchVO = new MpYieldInfoMVO();
       searchVO.setCorpCode(corpCode);
       searchVO.setGrupPlant(grupPlant);
       searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));

       List<?> mpYieldInfoMList = mpYieldInfoMService.selectTotalMpYieldInfoM_S(searchVO);

	   PageSet pageSet = new PageSet();
	    
	   if(mpYieldInfoMList != null)
		   pageSet.setTotalRecordCount(mpYieldInfoMList.size());
       pageSet.setResult(mpYieldInfoMList);
   		
       result.setExtraData(pageSet);
       	
       return _filter.makeCORSEntity( result );
    } 
    /**
	 * mp_yield_info_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpYieldInfoMDefaultVO
	 * @return "/mpYieldInfoM/MpYieldInfoMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/detail/{strtDate}/{grupPlant}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpYieldInfoMList(
    	@PathVariable("corpCode") String corpCode,
   		@PathVariable("strtDate") String strtDate,
   		@PathVariable("grupPlant") String grupPlant,
   		HttpServletRequest req,
   		HttpServletResponse res,
   		ModelMap model)
       throws Exception {
	
       ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
   	
       MpYieldInfoMVO searchVO = new MpYieldInfoMVO();
       searchVO.setCorpCode(corpCode);
       searchVO.setGrupPlant(grupPlant);
       searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
	
       List<?> mpYieldInfoMList = mpYieldInfoMService.selectMpYieldInfoMList(searchVO);

	   PageSet pageSet = new PageSet();
    
	   if(mpYieldInfoMList != null)
		   pageSet.setTotalRecordCount(mpYieldInfoMList.size());
       pageSet.setResult(mpYieldInfoMList);
   		
       result.setExtraData(pageSet);
       	
       return _filter.makeCORSEntity( result );
    } 

}
