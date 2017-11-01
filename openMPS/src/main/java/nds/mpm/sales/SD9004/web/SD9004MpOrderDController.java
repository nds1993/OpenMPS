package nds.mpm.sales.SD9004.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0401.service.SD0401Service;
import nds.mpm.sales.SD0401.vo.MultiSD0401VO;
import nds.mpm.sales.SD0401.vo.SD0401DefaultVO;
import nds.mpm.sales.SD0401.vo.SD0401VO;
import nds.mpm.sales.SD0405.service.MpOrderHService;
import nds.mpm.sales.SD0405.vo.MpOrderDDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderDVO;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import nds.mpm.sales.SD9004.service.SD9004MpOrderDService;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

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
 * 화면명 : 모바일 주문조회,입력(영업사원) ( SD9004 ) 
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
@RequestMapping("/mpm/{corpCode}/sd9004/mporderh")
public class SD9004MpOrderDController extends TMMBaseController {

    @Resource(name = "SD0401Service")
    private SD0401Service SD0401Service;
    
    @Resource(name = "mpOrderHService")
    private MpOrderHService mpOrderHService;
    
    @Resource(name = "SD9004MpOrderDService")
    private SD9004MpOrderDService mpOrderDService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
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
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String delvCust = req.getParameter("delvCust");
    	String limitYn = req.getParameter("limitYn");
    	
    	MpOrderHVO searchVO = new MpOrderHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setDelvDate(delvDate);
    	searchVO.setDelvCust(delvCust);
    	searchVO.setOrdrCust(ordrCust);
    	searchVO.setLimitYn(limitYn);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> SD0401List = mpOrderDService.selectMpOrderDList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpOrderDService.selectMpOrderDListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(SD0401List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
   
    /**
     * 모바일 주문입력  
     * MOBILE 추가,수정.삭제
     * dsType (C|U|D)
     * 
     * */
    @RequestMapping(value="/{delvDate}/{ordrCust}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addSD0401(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("delvDate") String delvDate,
    		@PathVariable("ordrCust") String ordrCust,
    		SD0401VO mpOrderDVO,
    		@RequestBody MultiSD0401VO sD0401VO,    
    		HttpServletRequest req,
    		HttpServletResponse res
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	List ilist = new ArrayList();
    	//중복검색
    	if(sD0401VO != null)
    	{
    		mpOrderDVO.setCorpCode(corpCode);
    		mpOrderDVO.setDelvDate(StringUtils.remove(delvDate,"-"));
    		mpOrderDVO.setOrdrCust(ordrCust);
    		
    		result = SD0401Service.insertMpOrderH(mpOrderDVO, sD0401VO);
    	}
    	
    	
    	return _filter.makeCORSEntity( result );
    }

}
