package nds.mpm.sales.SD1001.web;

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
import nds.mpm.sales.SD0401.vo.SD0401VO;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import nds.mpm.sales.SD1001.service.SD1001MpDiscPriceService;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :  SD0401Controller
 *
 * @author MPM TEAM
 * @since 2017.08.31
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 고객포털 - 주문입력 ( SD1001 ) 
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
@RequestMapping("/mpm/{corpCode}/sd1001/mporderh")
public class SD1001MpOrderDController extends TMMBaseController {

    @Resource(name = "SD1001MpOrderHService")
    private SD1001MpDiscPriceService mpDiscPriceService;
    
    @Resource(name = "SD0401Service")
    private SD0401Service SD0401Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * 주문조회
	 * @param searchVO - 조회할 정보가 담긴 SD0401DefaultVO
	 * @return 
	 * @exception Exception
	 */
    @RequestMapping(value="/{ordrCust}/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSD0401List(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("ordrCust") String ordrCust,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
        
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpOrderHVO searchVO = new MpOrderHVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpDiscPriceList = mpDiscPriceService.selectMpDiscPriceList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpDiscPriceService.selectMpDiscPriceListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpDiscPriceList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
     * 상세 제품목록 
     * */
    @RequestMapping(value="/{ordrCust}/{strtDate}/{lastDate}/{delvDate}/{ordrNo}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectDetailList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("ordrCust") String ordrCust,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("delvDate") String delvDate,
    		@PathVariable("ordrNo") String ordrNo,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
        
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpOrderHVO searchVO = new MpOrderHVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpDiscPriceList = mpDiscPriceService.selectMpOrderDetailList(searchVO);

    	PageSet pageSet = new PageSet();
    	if(mpDiscPriceList != null)
    		pageSet.setTotalRecordCount(mpDiscPriceList.size());
    	pageSet.setResult(mpDiscPriceList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
     * 주문입력
     * 
     * */
    @RequestMapping(value="/{ordrCust}/{strtDate}/{lastDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addSD0401(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("ordrCust") String ordrCust,
    		SD0401VO searchVO,
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
    		searchVO.setCorpCode(corpCode);
    		searchVO.setOrdrCust(ordrCust);
    		
    		result = SD0401Service.insertMpOrderH(searchVO, sD0401VO);
    	}
    	return _filter.makeCORSEntity( result );
    }


}
