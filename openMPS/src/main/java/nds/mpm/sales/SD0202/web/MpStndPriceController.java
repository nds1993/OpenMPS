package nds.mpm.sales.SD0202.web;

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
import nds.mpm.sales.SD0202.service.MpStndPriceService;
import nds.mpm.sales.SD0202.vo.MpStndPriceDefaultVO;
import nds.mpm.sales.SD0202.vo.MpStndPriceVO;

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
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  MpStndPriceController
 *
 * @author MPM TEAM
 * @since 2017. 9. 17.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 표준가격( 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 9. 17.	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0202/mpstndprice")
public class MpStndPriceController extends TMMBaseController{

    @Resource(name = "mpStndPriceService")
    private MpStndPriceService mpStndPriceService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /***
     * 
     * left grid
     * 표준단가 날짜 조회.
     * 
     * */
    @RequestMapping(value="/period/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpStndPricePeriodList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
    	MpStndPriceVO searchVO = new MpStndPriceVO();
    	searchVO.setCorpCode(corpCode);
    	
    	strtDate = StringUtils.remove(strtDate, "-");
    	lastDate = StringUtils.remove(lastDate, "-");
    	
    	searchVO.setStrtDate(strtDate);
    	searchVO.setLastDate(lastDate);
    	
        List<?> mpStndPriceList = mpStndPriceService.selectMpStndPricePeriodList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpStndPriceList != null)
    		pageSet.setTotalRecordCount(mpStndPriceList.size());
    	pageSet.setResult(mpStndPriceList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );

    } 
    /***
     * 
     * right grid
     * 신규 또는 등록된 표준단가 제품 조회.
     * 
     * */
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpStndPriceList(
    		@PathVariable("corpCode") String corpCode,
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
    	
    	MpStndPriceVO searchVO = new MpStndPriceVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
    	strtDate = StringUtils.remove(strtDate, "-");
    	lastDate = StringUtils.remove(lastDate, "-");
    	
    	searchVO.setStrtDate(strtDate);
    	searchVO.setLastDate(lastDate);
    	
        List<?> mpStndPriceList = mpStndPriceService.selectMpStndPriceList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpStndPriceService.selectMpStndPriceListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpStndPriceList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );

    } 
    
    @RequestMapping("/mpStndPrice/addMpStndPriceView.do")
    public String addMpStndPriceView(
            @ModelAttribute("searchVO") MpStndPriceDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpStndPriceVO", new MpStndPriceVO());
        return "/mpStndPrice/MpStndPriceRegister";
    }
    
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpStndPrice(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@RequestBody List<EgovMap> mpStndPriceVO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	int nRet = 0;
    	
    	List ilist = new ArrayList();
    	//중복검색
    	if(mpStndPriceVO != null)
    	{
    		for(EgovMap vo : mpStndPriceVO)
        	{
    			vo.put("corpCode",corpCode);
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    			}
    			vo.put("corpCode", corpCode);
    			vo.put("strtDate", StringUtils.remove(strtDate,"-"));
    			vo.put("lastDate", StringUtils.remove(lastDate,"-"));
        		ilist.add(vo);
        	}
    	}
    	result = mpStndPriceService.insertMpStndPrice(ilist);
        
        return _filter.makeCORSEntity( result );
    }
    /**
    @RequestMapping("/mpStndPrice/updateMpStndPrice.do")
    public String updateMpStndPrice(
            MpStndPriceVO mpStndPriceVO,
            @ModelAttribute("searchVO") MpStndPriceDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpStndPriceService.updateMpStndPrice(mpStndPriceVO);
        status.setComplete();
        return "forward:/mpStndPrice/MpStndPriceList.do";
    }
    
    @RequestMapping("/mpStndPrice/deleteMpStndPrice.do")
    public String deleteMpStndPrice(
            MpStndPriceVO mpStndPriceVO,
            @ModelAttribute("searchVO") MpStndPriceDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpStndPriceService.deleteMpStndPrice(mpStndPriceVO);
        status.setComplete();
        return "forward:/mpStndPrice/MpStndPriceList.do";
    }
	*/
}
