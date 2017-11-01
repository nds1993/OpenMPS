package nds.mpm.sales.SD0505.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.sales.SD0505.service.SD0505MpOrderHService;
import nds.mpm.sales.SD0505.vo.MpOrderHVO;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :  SD0505MpOrderHController
 *
 * @author MPM TEAM
 * @since 2017. 9. 12.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 미수현황 - 거래처( SD0505 )
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
@RequestMapping("/mpm/{corpCode}/SD0505/mporderh")
public class SD0505MpOrderHController extends TMMBaseController{

    @Resource(name = "SD0505mpOrderHService")
    private SD0505MpOrderHService mpOrderHService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_order_h 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpOrderHDefaultVO
	 * @return "/mpOrderH/MpOrderHList"
	 * @exception Exception
	 */
    @RequestMapping(value="/head/{strtDate}/{lastDate}/{teamCode}/{corpCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpOrderHList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("teamCode") String teamCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
		
    	MpOrderHVO searchVO = new MpOrderHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setLastDate(lastDate);
    	searchVO.setTeamCode(teamCode);
        List<?> mpOrderHList = mpOrderHService.selectMpOrderHList(searchVO);
        
        PageSet pageSet = new PageSet();
        
        if(mpOrderHList != null)
        	pageSet.setTotalRecordCount(mpOrderHList.size());
    	pageSet.setResult(mpOrderHList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/detail/{strtDate}/{lastDate}/{teamCode}/{corpCode}/{custCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selecDetailtMpOrderHList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("teamCode") String teamCode,
    		@PathVariable("custCode") String custCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
		
    	MpOrderHVO searchVO = new MpOrderHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setLastDate(lastDate);
    	searchVO.setTeamCode(teamCode);
    	searchVO.setCustCode(custCode);
        List<?> mpOrderHList = mpOrderHService.selectDetailMpOrderHList(searchVO);
        
        PageSet pageSet = new PageSet();
        
        if(mpOrderHList != null)
        	pageSet.setTotalRecordCount(mpOrderHList.size());
    	pageSet.setResult(mpOrderHList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
}
