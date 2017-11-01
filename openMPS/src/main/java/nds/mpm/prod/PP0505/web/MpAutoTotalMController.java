package nds.mpm.prod.PP0505.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.prod.PP0505.service.MpAutoTotalMService;
import nds.mpm.prod.PP0505.vo.MpAutoTotalMVO;

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
 * @Class Name :  MpAutoTotalMController
 *
 * @author MPM TEAM
 * @since 2017.08.24
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : PM라벨실적 입고 요청(이시다) ( PP0505 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.24	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0505/mpautototalm")
public class MpAutoTotalMController extends TMMBaseController{

    @Resource(name = "mpAutoTotalMService")
    private MpAutoTotalMService mpAutoTotalMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_auto_total_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpAutoTotalMDefaultVO
	 * @return "/mpAutoTotalM/MpAutoTotalMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{plantCode}/{strtDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpAutoTotalMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("strtDate") String strtDate,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpAutoTotalMVO searchVO = new MpAutoTotalMVO();
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	
    	List<?> mpBarProMList = mpAutoTotalMService.selectMpAutoTotalMList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpAutoTotalMService.selectMpAutoTotalMListTotCnt(searchVO);
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpBarProMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{plantCode}/{strtDate}/detail/{proCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpAutoTotalMDetail(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("proCode") String proCode,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	MpAutoTotalMVO searchVO = new MpAutoTotalMVO();
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setProCode(proCode);
    	
    	List<?> mpBarProMList = mpAutoTotalMService.selectMpAutoTotalMDetail(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpBarProMList != null)
    		pageSet.setTotalRecordCount(mpBarProMList.size());
    	pageSet.setResult(mpBarProMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    /**
     * 
     * 생산일자가 마감된 자료는 삭제 불가
     * 선택된 레코드는 Table의 WMS입고상태 (IN_STATUS) 컬럼 값을 '0'으로 Update
     * 입고요청 서비스(OPEN API)호출
     * API 결과를 메시지 박스로 표시
     * 재 조회 (처리 결과 확인)
     * 
     * */
    @RequestMapping(value="/{plantCode}/{strtDate}/ipgo/check",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpAutoTotalMIpgo(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("strtDate") String strtDate,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	MpAutoTotalMVO searchVO = new MpAutoTotalMVO();
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	
    	result = mpAutoTotalMService.checkIpgoClose(searchVO);

    	return _filter.makeCORSEntity( result );
    } 
}
