package nds.mpm.sales.SD0203.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0202.vo.MpStndPriceVO;
import nds.mpm.sales.SD0203.service.MpSalePriceService;
import nds.mpm.sales.SD0203.vo.MpSalePriceDefaultVO;
import nds.mpm.sales.SD0203.vo.MpSalePriceVO;

/**
 * @Class Name :  MpSalePriceController
 *
 * @author MPM TEAM
 * @since 2017. 10. 2.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 판매단가
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 10. 2.	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0203/mpsaleprice")
public class MpSalePriceController extends TMMBaseController{

    @Resource(name = "mpSalePriceService")
    private MpSalePriceService mpSalePriceService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_sale_price 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpSalePriceDefaultVO
	 * @return "/mpSalePrice/MpSalePriceList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}/{salesman}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpSalePriceList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("salesman") String salesman,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String custCode = req.getParameter("custCode");
    	String searchCondition = req.getParameter("searchCondition");
    	String searchCondition2 = req.getParameter("searchCondition2");
    	
    	MpSalePriceVO searchVO = new MpSalePriceVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
    	strtDate = StringUtils.remove(strtDate, "-");
    	lastDate = StringUtils.remove(lastDate, "-");
    	
    	searchVO.setCustCode(custCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setLastDate(lastDate);
    	searchVO.setSalesman(salesman);
    	searchVO.setSearchCondition(searchCondition);
    	
        List<?> mpSalePriceList = null;
        
      //신규 가격 입력시
        if("new".equals(searchCondition))
        {
        	if(StringUtils.isNotEmpty(searchCondition2))
        	{
        		searchVO.setStrtDate(StringUtils.remove(searchCondition2, "-"));
        	}
        	mpSalePriceList = mpSalePriceService.selectNewMpSalePriceList(searchVO);
        }
        else
        	mpSalePriceList = mpSalePriceService.selectMpSalePriceList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpSalePriceList!= null)
    		pageSet.setTotalRecordCount(mpSalePriceList.size());
    	pageSet.setResult(mpSalePriceList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{strtDate}/{lastDate}/{salesman}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpSalePrice(
            @PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("salesman") String salesman,
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
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    			}
    			vo.put("corpCode", corpCode);
    			vo.put("strtDate", StringUtils.remove(strtDate,"-"));
    			vo.put("lastDate", StringUtils.remove(lastDate,"-"));
    			vo.put("salesman", salesman);
        		ilist.add(vo);
        	}
    	}
    	
        
        result = mpSalePriceService.insertMpSalePrice(ilist);
        return _filter.makeCORSEntity( result );
    }
    
    /**
     * 승인요청
     * */
    @RequestMapping(value="/{strtDate}/{lastDate}/{salesman}/reqappro",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> reqApproMpSalePrice(
            @PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("salesman") String salesman,
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
    			if(sess != null)
    			{
    				vo.put("mdUser", sess.getUser().getId());
    			}
    			vo.put("corpCode", corpCode);
    			vo.put("strtDate", StringUtils.remove((String)vo.get("strtDate"),"-"));
    			vo.put("lastDate", StringUtils.remove((String)vo.get("lastDate"),"-"));
    			vo.put("salesman", sess.getUser().getId());
        		ilist.add(vo);
        	}
    	}
        
        result = mpSalePriceService.updateReqApproMpSalePrice(ilist);
        return _filter.makeCORSEntity( result );
    }

    /***
    @RequestMapping("/mpSalePrice/addMpSalePriceView.do")
    public String addMpSalePriceView(
            @ModelAttribute("searchVO") MpSalePriceDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpSalePriceVO", new MpSalePriceVO());
        return "/mpSalePrice/MpSalePriceRegister";
    }
    @RequestMapping("/mpSalePrice/updateMpSalePrice.do")
    public String updateMpSalePrice(
            MpSalePriceVO mpSalePriceVO,
            @ModelAttribute("searchVO") MpSalePriceDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpSalePriceService.updateMpSalePrice(mpSalePriceVO);
        status.setComplete();
        return "forward:/mpSalePrice/MpSalePriceList.do";
    }
    
    @RequestMapping("/mpSalePrice/deleteMpSalePrice.do")
    public String deleteMpSalePrice(
            MpSalePriceVO mpSalePriceVO,
            @ModelAttribute("searchVO") MpSalePriceDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpSalePriceService.deleteMpSalePrice(mpSalePriceVO);
        status.setComplete();
        return "forward:/mpSalePrice/MpSalePriceList.do";
    }
**/
}
