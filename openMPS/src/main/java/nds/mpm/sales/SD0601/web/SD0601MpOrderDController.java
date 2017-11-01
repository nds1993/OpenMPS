package nds.mpm.sales.SD0601.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.sales.SD0601.service.SD0601MpOrderDService;
import nds.mpm.sales.SD0601.vo.MpOrderDVO;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :  SD0601MpOrderDController
 *
 * @author MPM TEAM
 * @since 2017. 9. 11.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 거래원장( SD0601 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 9. 11.	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0601/mporderd")
public class SD0601MpOrderDController extends TMMBaseController{

    @Resource(name = "SD0601mpOrderDService")
    private SD0601MpOrderDService mpOrderDService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_order_d 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpOrderDDefaultVO
	 * @return "/mpOrderD/MpOrderDList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{sellDate}/{custCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpOrderDList(
	    	@PathVariable("corpCode") String corpCode,
			@PathVariable("sellDate") String sellDate,
			@PathVariable("custCode") String custCode,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	 	
		MpOrderDVO searchVO = new MpOrderDVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setSellDate(sellDate);
		searchVO.setCustCode(custCode);
		
        List<?> mpOrderDList = mpOrderDService.selectMpOrderDList(searchVO);
	    
	    PageSet pageSet = new PageSet();
	    
        int totCnt = mpOrderDService.selectMpOrderDListTotCnt(searchVO);

		pageSet.setTotalRecordCount(totCnt);
		pageSet.setResult(mpOrderDList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );	
    } 

}
