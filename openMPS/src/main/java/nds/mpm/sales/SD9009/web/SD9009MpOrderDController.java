package nds.mpm.sales.SD9009.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD9009.service.SD9009MpOrderDService;

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
 * 화면명 : 모바일 주문조회,입력(대리점) ( SD9005 ) 
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
@RequestMapping("/mpm/{corpCode}/sd9009/sddc")
public class SD9009MpOrderDController extends TMMBaseController {

    @Resource(name = "SD9009MpOrderDService")
    private SD9009MpOrderDService mpOrderDService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * 주문 제품목록조회
	 * @param searchVO - 조회할 정보가 담긴 SD0401DefaultVO
	 * @return 
	 * @exception Exception
	 */
    @RequestMapping(value="/{whcode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSD0401List(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("whcode") String whcode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
        
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
        
        String proGubun = req.getParameter("proGubun");
        String whName = req.getParameter("whName");
    	String proType = req.getParameter("proType");
    	String proLcode = req.getParameter("proLcode");
    	
    	EgovMap searchVO = new EgovMap();
    	searchVO.put("corpCode",corpCode);
    	searchVO.put("whcode",whcode);
    	searchVO.put("proGubun",proGubun);
    	searchVO.put("whName",whName);
    	searchVO.put("proType",proType);
    	searchVO.put("proLcode",proLcode);
    	
        List<?> SD0401List = mpOrderDService.selectMpOrderDList(searchVO);

    	PageSet pageSet = new PageSet();
        if(SD0401List != null)
        	pageSet.setTotalRecordCount(SD0401List.size());
    	pageSet.setResult(SD0401List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{whcode}/{proCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectProcodeSD0401List(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("whcode") String whcode,
    		@PathVariable("proCode") String proCode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
        
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
    	String proGubun = req.getParameter("proGubun");
    	
    	EgovMap searchVO = new EgovMap();
    	searchVO.put("corpCode",corpCode);
    	searchVO.put("whcode",whcode);
    	searchVO.put("proCode",proCode);
    	searchVO.put("proGubun",proGubun);
    	
        List<?> SD0401List = mpOrderDService.selectProcodeMpOrderDList(searchVO);

    	PageSet pageSet = new PageSet();
        if(SD0401List != null)
        	pageSet.setTotalRecordCount(SD0401List.size());
    	pageSet.setResult(SD0401List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }

}
