package nds.mpm.sales.SD0206.web;

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
import nds.mpm.sales.SD0203.service.MpSalePriceService;
import nds.mpm.sales.SD0203.vo.MpSalePriceVO;

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
 * @Class Name :  SD0206MpSalePriceController
 *
 * @author MPM TEAM
 * @since 2017.08.28
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 판매단가 승인( SD0206 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.06.07	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0206/mpsaleprice")
public class SD0206MpSalePriceController extends TMMBaseController{

    @Resource(name = "mpSalePriceService")
    private MpSalePriceService mpSalePriceService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * SD0203 mp_sale_price 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpSalePriceDefaultVO
	 * @return "/mpSalePrice/MpSalePriceList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}/{salesman}/{status}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpSalePriceList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("salesman") String salesman,
    		@PathVariable("status") String status,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpSalePriceVO searchVO = new MpSalePriceVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
    	strtDate = StringUtils.remove(strtDate, "-");
    	lastDate = StringUtils.remove(lastDate, "-");
    	
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setSalesman(salesman);
    	
    	if(!"ALL".equals(status))
    		searchVO.setStatus(status);
    	
        List<?> mpSalePriceList = mpSalePriceService.selectSD0206MpSalePriceList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpSalePriceService.selectSD0206MpSalePriceListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpSalePriceList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    /**
     * 판매단가승인처리 SD0203 service call
	 * @param 
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}/{salesman}/{appro}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpStndProd(
            @PathVariable("corpCode") String corpCode,
            @PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("salesman") String salesman,
    		@PathVariable("appro") String appro,
    		@RequestBody List<EgovMap> mpStndProdVO,
    		HttpServletRequest req)
            throws Exception {
        
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	String approYn = "N";
    	if("confirm".equals(appro))
    	{
    		approYn = "Y";
    	}
    	else if("reject".equals(appro))
    	{
    		approYn = "N";
    	}	
    	
    	if(mpStndProdVO != null)
    	{
    		for(EgovMap vo : mpStndProdVO)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("approYn",approYn);
    			vo.put("strtDate",StringUtils.remove((String)vo.get("strtDate"), "-"));
    			vo.put("lastDate",StringUtils.remove((String)vo.get("lastDate"), "-"));
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser", sess.getUser().getId());
    			}
        	}
    		
    		//가격변경 저장처리
    		if("upsave".equals(appro))
        	{
    			result = mpSalePriceService.insertMpSalePrice(mpStndProdVO);
        	}
    		else if("reject".equals(appro))
        	{
    			result = mpSalePriceService.updateSD0206ApproRejectMpSalePrice(mpStndProdVO);
        	}
    		//승인처리
        	else
        	{
    	    	
    	    	result = mpSalePriceService.updateSD0206MpSalePrice(mpStndProdVO);

        	}
        	
    	}
    	
        return _filter.makeCORSEntity( result );
    }
    
}
