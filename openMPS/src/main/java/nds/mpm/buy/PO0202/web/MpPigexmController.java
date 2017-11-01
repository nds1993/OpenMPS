package nds.mpm.buy.PO0202.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.buy.PO0201.web.PO0201MppigdxmController;
import nds.mpm.buy.PO0202.service.MpPigexmService;
import nds.mpm.buy.PO0202.vo.MpPigexmDefaultVO;
import nds.mpm.buy.PO0202.vo.MpPigexmVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  MpPigexmController
 *
 * @author MPM TEAM
 * @since 2017.08.31
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 생돈정산( PO0202 )
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
@RequestMapping("/mpm/{corpCode}/po0202/mppigexm")
public class MpPigexmController extends TMMBaseController {
	
	private static final Logger _logger = LoggerFactory.getLogger(PO0201MppigdxmController.class);

    @Resource(name = "mpPigexmService")
    private MpPigexmService mpPigexmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_pigexm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPigexmDefaultVO
	 * @return "/mpPigexm/MpPigexmList"
	 * @exception Exception
	 */
    @RequestMapping(value="/search/{startDate}/{endDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPigexmList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("startDate") String startDate,
    		@PathVariable("endDate") String endDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String facKind = req.getParameter("facKind");
    	String facCode = req.getParameter("facCode");
    	
    	MpPigexmVO searchVO = new MpPigexmVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStartDate(StringUtils.remove(startDate,"-"));
    	searchVO.setEndDate(StringUtils.remove(endDate,"-"));
    	searchVO.setFacKind(facKind);
    	searchVO.setFacCode(facCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> pigDochList = mpPigexmService.selectMpPigexmList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpPigexmService.selectMpPigexmListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(pigDochList);
		
    	result.setExtraData(pageSet);
    	return _filter.makeCORSEntity( result );
    } 
    /**
     * 정산구분 선택시 정산방법 조회
     * 
     * */
    @RequestMapping(value="/code/{facKind}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPigexmCodeList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("facKind") String facKind,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpPigexmVO searchVO = new MpPigexmVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setFacKind(facKind);
    	
        List<?> pigDochList = mpPigexmService.selectMpPigexmCodeList(searchVO);

    	PageSet pageSet = new PageSet();
    	
        if(pigDochList != null)
        	pageSet.setTotalRecordCount(pigDochList.size());
    	pageSet.setResult(pigDochList);
		
    	result.setExtraData(pageSet);
    	return _filter.makeCORSEntity( result );
    } 
    
    /**
     * 최근일자 조회 
     * 
     * */
    @RequestMapping(value="/recent/{facKind}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPigexmRecentList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("facKind") String facKind,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String facCode = req.getParameter("facCode");
    	
    	MpPigexmVO searchVO = new MpPigexmVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setFacKind(facKind);
    	searchVO.setFacCode(facCode);
    	
        List<?> pigDochList = mpPigexmService.selectMpPigexmRecentList(searchVO);

    	PageSet pageSet = new PageSet();
    	
        if(pigDochList != null)
        	pageSet.setTotalRecordCount(pigDochList.size());
    	pageSet.setResult(pigDochList);
		
    	result.setExtraData(pageSet);
    	return _filter.makeCORSEntity( result );
    } 
    
    /**
     * 도축일자 시작일 월, 수요일 선택시 지육시세 평균시세기준일 유효일자조회.
     * 
	 * 지육시세 기준일자 기준 
		
		출하일 기준 적용시세
		월,화 (출하일전주) 수/목/금 (출하일주) 월/화 지육시세 산술평균 
		수목금 ( 출하일주) 월 ~ 금 
     * 
     * */
    @RequestMapping(value="/jiyuk/{startDate}/{endDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPigexmJij(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("startDate") String startDate,
    		@PathVariable("endDate") String endDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpPigexmVO searchVO = new MpPigexmVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStartDate(StringUtils.remove(startDate,"-"));
    	searchVO.setEndDate(StringUtils.remove(endDate,"-"));
    	
    	result.setExtraData(mpPigexmService.selectJiyukPeriod(searchVO));
    	
    	return _filter.makeCORSEntity( result );
    }
    
    
    /**
     * 평균 지육시세조회
     * 
     * */
    @RequestMapping(value="/jiyuk/{facKind}/{startDate}/{endDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPigexmJijuk(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("facKind") String facKind,
    		@PathVariable("startDate") String startDate,
    		@PathVariable("endDate") String endDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpPigexmVO searchVO = new MpPigexmVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setFacKind(facKind);
    	searchVO.setStartDate(StringUtils.remove(startDate,"-"));
    	searchVO.setEndDate(StringUtils.remove(endDate,"-"));
    	
    	ResultEx resultMap = mpPigexmService.selectMpPigexmJiyukAvg(searchVO);
    	
    	return _filter.makeCORSEntity(resultMap);
    }
    
    /**
     * 생돈정산
     * 
     * */
    @RequestMapping(value="/adjust/{facKind}/{facCode}/{startDate}/{endDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> selectAdjustMpPigexm(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("facKind") String facKind,
    		@PathVariable("facCode") String facCode,
    		@PathVariable("startDate") String startDate,
    		@PathVariable("endDate") String endDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	MpPigexmVO searchVO = new MpPigexmVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setFacKind(facKind);
    	searchVO.setFacCode(facCode);
    	searchVO.setStartDate(StringUtils.remove(startDate,"-"));
    	searchVO.setEndDate(StringUtils.remove(endDate,"-"));
    	searchVO.setMdUser(sess.getUser().getId());
    	
    	result = mpPigexmService.updateAdjustFnPO0202Call(searchVO);
		
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/search/{startDate}/{endDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPigexm(
            @PathVariable("corpCode") String corpCode,
    		@PathVariable("startDate") String startDate,
    		@PathVariable("endDate") String endDate,
    		@RequestBody List<EgovMap> tmPlatxmVOs,
    		HttpServletRequest req,
    		HttpServletResponse res
    		)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//price value null convert
    	if(tmPlatxmVOs != null)
    	{
    		for(EgovMap vo : tmPlatxmVOs)
            {
    	        Iterator keys = vo.keySet().iterator();
    			String key_name = null;
    			while (keys.hasNext()) {
    				key_name = (String) keys.next();
    				if (key_name.indexOf("pig") > -1 
    					|| key_name.indexOf("gu") > -1
    					|| key_name.indexOf("add") > -1
    					|| key_name.indexOf("inc") > -1) {
    					
    					vo.put(key_name, StringUtils.defaultIfEmpty(String.valueOf(vo.get(key_name)),"0"));
    				}
    			}
            }
    		
    		
    		for(EgovMap vo : tmPlatxmVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("startDate",StringUtils.remove((String)vo.get("startDate"),"-"));
    			vo.put("endDate",StringUtils.remove((String)vo.get("endDate"),"-"));
    			vo.put("fromDate",StringUtils.remove((String)vo.get("fromDate"),"-"));
    			vo.put("toDate",StringUtils.remove((String)vo.get("toDate"),"-"));
    			
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	result = mpPigexmService.insertMpPigexm(ilist);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/mpPigexm/updateMpPigexmView.do")
    public String updateMpPigexmView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("facKind") java.lang.String facKind ,
            @RequestParam("facCode") java.lang.String facCode ,
            @RequestParam("startDate") java.lang.String startDate ,
            @ModelAttribute("searchVO") MpPigexmDefaultVO searchVO, Model model)
            throws Exception {
        MpPigexmVO mpPigexmVO = new MpPigexmVO();
        mpPigexmVO.setCorpCode(corpCode);
        mpPigexmVO.setFacKind(facKind);
        mpPigexmVO.setFacCode(facCode);
        mpPigexmVO.setStartDate(StringUtils.remove(startDate,"-"));
        // 변수명은 CoC 에 따라 mpPigexmVO
        model.addAttribute(selectMpPigexm(mpPigexmVO, searchVO));
        return "/mpPigexm/MpPigexmRegister";
    }

    @RequestMapping("/mpPigexm/selectMpPigexm.do")
    public @ModelAttribute("mpPigexmVO")
    MpPigexmVO selectMpPigexm(
            MpPigexmVO mpPigexmVO,
            @ModelAttribute("searchVO") MpPigexmDefaultVO searchVO) throws Exception {
        //return mpPigexmService.selectMpPigexm(mpPigexmVO);
    	return null;
    }
    
    @RequestMapping(value="/search/{startDate}/{endDate}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateMpPigexm(
            @RequestBody MpPigexmVO mpPigexmVO,
            @PathVariable("corpCode") String corpCode,
    		@PathVariable("startDate") String startDate,
    		@PathVariable("endDate") String endDate)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	//result.setExtraData(mpPigexmService.updateMpPigexm(mpPigexmVO));
        return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/search/{startDate}/{endDate}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteMpPigexm(
    		@RequestBody MpPigexmVO mpPigexmVO,
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("startDate") String startDate,
    		@PathVariable("endDate") String endDate)
    	throws Exception {
        
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	//result.setExtraData(mpPigexmService.deleteMpPigexm(mpPigexmVO));
        return _filter.makeCORSEntity( result );
    }

}
