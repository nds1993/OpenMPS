package nds.mpm.sales.SD0406.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.sales.SD0405.service.MpOrderHService;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :  MpOrderHController
 *
 * @author MPM TEAM
 * @since 2017.08.17
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 유통주문입력( SD0405 ) 
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.17	 			최초생성 이마트엑셀양식기준1차개발
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0406/mporderh")
public class SD0406Controller extends TMMBaseController{
	
	private static final Logger _logger = LoggerFactory.getLogger(SD0406Controller.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpOrderHService")
    private MpOrderHService mpOrderHService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_order_h 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpOrderHDefaultVO
	 * @return "/mpOrderH/MpOrderHList"
	 * @exception Exception
	 */
    @RequestMapping(value="")
    public ResponseEntity<ResultEx> selectMpOrderHList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String teamCode = req.getParameter("teamCode");
    	String salesman = req.getParameter("salesman");
    	String ofceCode = req.getParameter("ofceCode");
    	String strtDate = req.getParameter("ordrDate");
    	
    	MpOrderHVO searchVO = new MpOrderHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	
    	searchVO.setTeamCode(teamCode);
    	searchVO.setSalesman(salesman);
    	searchVO.setOfceCode(ofceCode);
    	
        List<?> mpOrderHList = mpOrderHService.selectSD0406List(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpOrderHService.selectSD0406ListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpOrderHList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    /**
   	 * 출고일자 영업사원 거래처 주문 내역 mp_order_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 MpOrderHDefaultVO
	 * @return "/mpOrderH/MpOrderHList"
	 * @exception Exception
	 */
    @RequestMapping(value="/cust/{delvDate}/{salesman}")
    public ResponseEntity<ResultEx> selectMpOrderHCustList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("delvDate") String delvDate,
    		@PathVariable("salesman") String salesman,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpOrderHVO searchVO = new MpOrderHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setDelvDate(delvDate);
    	searchVO.setSalesman(salesman);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpOrderHList = mpOrderHService.selectSD0406CustList(searchVO);

    	PageSet pageSet = new PageSet();
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	if(mpOrderHList!=null)
    		pageSet.setTotalRecordCount(mpOrderHList.size());
    	pageSet.setResult(mpOrderHList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
   	 * 출고일자 영업사원 거래처 주문 내역 mp_order_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 MpOrderHDefaultVO
	 * @return "/mpOrderH/MpOrderHList"
	 * @exception Exception
	 */
    @RequestMapping(value="/prod/{delvDate}/{salesman}")
    public ResponseEntity<ResultEx> selectMpOrderHOrdrProList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("delvDate") String delvDate,
    		@PathVariable("salesman") String salesman,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpOrderHVO searchVO = new MpOrderHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setDelvDate(delvDate);
    	searchVO.setSalesman(salesman);
    	
        List<?> mpOrderHList = mpOrderHService.selectSD0406OrdrProList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpOrderHList!=null)
    		pageSet.setTotalRecordCount(mpOrderHList.size());
    	pageSet.setResult(mpOrderHList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
}
