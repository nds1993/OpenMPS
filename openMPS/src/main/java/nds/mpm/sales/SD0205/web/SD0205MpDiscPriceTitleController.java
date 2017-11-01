package nds.mpm.sales.SD0205.web;

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
import nds.mpm.sales.SD0205.service.MpDiscPriceTitleService;
import nds.mpm.sales.SD0205.vo.MpDiscPriceTitleVO;

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
 * @Class Name :  SD0205MpDiscPriceTitleController
 *
 * @author MPM TEAM
 * @since 2017.09.11
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 할인단가 헤더 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.09.11	 			최초생성, 할인단가 설계 타이틀항목 추가, 테이블 결재컬럼변경. 
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/sd0205/mpdiscpricetitle")
public class SD0205MpDiscPriceTitleController extends TMMBaseController{

    @Resource(name = "mpDiscPriceTitleService")
    private MpDiscPriceTitleService mpDiscPriceTitleService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 할인단가 헤더 내역
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpDiscPriceList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
        
        String salesman = req.getParameter("salesman");
        String custCode = req.getParameter("custCode");
        String approYn = req.getParameter("approYn");
    	
    	MpDiscPriceTitleVO searchVO = new MpDiscPriceTitleVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setSalesman(salesman);
    	searchVO.setCustCode(custCode);
    	
        List<?> mpDiscPriceList = mpDiscPriceTitleService.selectMpDiscPriceTitleList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpDiscPriceList != null)
    		pageSet.setTotalRecordCount(mpDiscPriceList.size());
    	pageSet.setResult(mpDiscPriceList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /***
     * 목적, 타이틀 등록.
     * 
     * */
    @RequestMapping(value="/{strtDate}/{lastDate}/{custCode}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpDiscPrice(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("custCode") String custCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
            @RequestBody List<EgovMap> mpSalePriceVO,
            HttpServletRequest req)
            throws Exception {
            	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        MPUserSession sess = getSession(req);
    	int nRet = 0;
    	
    	List ilist = new ArrayList();
    	//중복검색
    	if(mpSalePriceVO != null)
    	{
    		for(EgovMap vo : mpSalePriceVO)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("custCode",custCode);
    			vo.put("strtDate",StringUtils.remove(strtDate,"-"));
    			vo.put("lastDate",StringUtils.remove(lastDate,"-"));
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser", sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	result = mpDiscPriceTitleService.insertMpDiscPriceTitle(ilist);
    	return _filter.makeCORSEntity( result );
    }

}
