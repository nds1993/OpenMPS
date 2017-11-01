package nds.mpm.sales.SD9008.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import nds.mpm.sales.SD9008.service.SD9008MpOrderDService;

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
 * @Class Name :  SD0401Controller
 *
 * @author MPM TEAM
 * @since 2017.08.31
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 모바일 주문조회,입력(영업사원) ( SD9008 ) 
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
@RequestMapping("/mpm/{corpCode}/SD9008/mporderd")
public class SD9008MpOrderDController extends TMMBaseController {

    @Resource(name = "SD9008MpOrderDService")
    private SD9008MpOrderDService mpOrderDService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    @RequestMapping(value="/{delvDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectOrderHList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("delvDate") String delvDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
        
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
    	String salesman = req.getParameter("salesman");
    	
    	EgovMap searchVO = new EgovMap();
    	searchVO.put("corpCode",corpCode);
    	searchVO.put("delvDate",delvDate);
    	searchVO.put("salesman",salesman);
    	
        List<?> SD0401List = mpOrderDService.selectMpOrderHList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(SD0401List != null)
    		pageSet.setTotalRecordCount(SD0401List.size());
    	pageSet.setResult(SD0401List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
	 * 주문 제품목록조회
	 * @param searchVO - 조회할 정보가 담긴 SD0401DefaultVO
	 * @return 
	 * @exception Exception
	 */
    @RequestMapping(value="/{delvDate}/{ordrCust}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSD0401List(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("delvDate") String delvDate,
    		@PathVariable("ordrCust") String ordrCust,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
        
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
        
        String displayUnit = req.getParameter("displayUnit");
        String ordrNo = req.getParameter("ordrNo");
    	
        EgovMap searchVO = new EgovMap();
    	searchVO.put("corpCode",corpCode);
    	searchVO.put("delvDate",delvDate);
    	searchVO.put("ordrCust",ordrCust);
    	searchVO.put("displayUnit",displayUnit);
    	searchVO.put("ordrNo",ordrNo);
    	             
        List<?> SD0401List = mpOrderDService.selectMpOrderDList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(SD0401List != null)
    		pageSet.setTotalRecordCount(SD0401List.size());
    	pageSet.setResult(SD0401List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }

}
