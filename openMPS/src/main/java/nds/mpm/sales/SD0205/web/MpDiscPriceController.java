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
import nds.mpm.sales.SD0205.service.MpDiscPriceService;
import nds.mpm.sales.SD0205.vo.MpDiscPriceDefaultVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceTitleVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceVO;
import nds.mpm.sales.SD0205.vo.MultiMpDiscPriceVO;

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
 * @Class Name :  MpDiscPriceController
 *
 * @author MPM TEAM
 * @since 2017.09.11
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 할인단가상세 ( SD0205 )
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.16	 			최초생성
 *	2017.09.11				9/8 할인단가입력설계 변경, 테이블변경. 수정개발.
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/sd0205/mpdiscprice")
public class MpDiscPriceController extends TMMBaseController{

    @Resource(name = "mpDiscPriceService")
    private MpDiscPriceService mpDiscPriceService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    //@RequestMapping(value="/{strtDate}/{lastDate}/{salesman}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpDiscPriceList(
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
    	
    	String salesmanCust = req.getParameter("salesmanCust");
    	
    	MpDiscPriceVO searchVO = new MpDiscPriceVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setLastDate(lastDate);
    	searchVO.setSalesman(salesman);
    	
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
	 * 할인단가 상세 내역 
	 * 
	 * 거래처의 제품목록을 기준으로 등록 정보와 병합하여 출력되어야 한다
		즉, 할인단가가 등록되어 있지 않은 경우 거래처의 제품목록이 출력되어야 하며 (MP_CUST_PRO:거래처별 제품목록 정보) 할인단가가 등록되어 있는 경우 거래처의 제품목록과 할인단가 등록 정보가 병합되어(중복 출력 배제) 출력되어야 한다
		이 때 3,2,1주전 판매량을 조회하여 준다
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}/{custCode}/{salesman}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpDiscPriceDetailList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("custCode") String custCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("salesman") String salesman,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
    	MpDiscPriceVO searchVO = new MpDiscPriceVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setCustCode(custCode);
    	searchVO.setSalesman(salesman);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	
    	//기존 할인단가내역조회
    	searchVO.setSearchCondition("saved");
        List<?> mpDiscPriceList = mpDiscPriceService.selectMpDiscPriceDetailList(searchVO);
        
        if(mpDiscPriceList != null && mpDiscPriceList.size() == 0)
        {
        	//거래처 등록 제품 조회
        	searchVO.setSearchCondition(null);
        	mpDiscPriceList = mpDiscPriceService.selectMpDiscPriceDetailList(searchVO);
        }

    	PageSet pageSet = new PageSet();
        
    	if(mpDiscPriceList != null)
    		pageSet.setTotalRecordCount(mpDiscPriceList.size());
    	pageSet.setResult(mpDiscPriceList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    //@RequestMapping("/mpDiscPrice/addMpDiscPriceView.do")
    public String addMpDiscPriceView(
            @ModelAttribute("searchVO") MpDiscPriceDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpDiscPriceVO", new MpDiscPriceVO());
        return "/mpDiscPrice/MpDiscPriceRegister";
    }
    
    /**
     * 거래처의 제품별 할인단가 등록
		- 거래처별로 시작일자 종료일자 입력
		- 할인단가의 시작일과 종료일 기간은 중복되어서는 안된다
		- 거래처의 제품별 할인단가는 영업사원 등록 후 숭인요청 버튼을 통하여 파트장, 본부장, 대표이사의 승인을 요청한다
		- 각 단계별 승인 시 MP_DISC_PRICE_TITLE의 PART_DATE, HEAD_DATE, CEO_DATE에 등록일자 기록 (할인단가 승인에서 설명)
     * 
     * 화면상에 등록되는 정보 이외에 할인단가 제목 테이블에서 관리되고 있는 항목은 프로그램 내부에서 산출 또는 추출하여 아래와 같은
		정보 기록 (현재월 기준)
		. 3개월 전 판매량 및 수금/미수액/회전일 기록 (MON_AGO_3, MON_SALE_3, MON_RECE_3, MON_MISU_3, MON_TURNOVER_3) . 2개월 전 판매량 및 수금/미수액/회전일 기록 (MON_AGO_2, MON_SALE_2, MON_RECE_2, MON_MISU_2, MON_TURNOVER_2) . 1개월 전 판매량 및 수금/미수액/회전일 기록 (MON_AGO_1, MON_SALE_1, MON_RECE_1, MON_MISU_2, MON_TURNOVER_1)

     * 매출채권 회전율 = 매출액/당월매출채권 매출채권 회전일 = 30 / 매출채권회전율
     * */
    @RequestMapping(value="/{strtDate}/{lastDate}/{custCode}/{salesman}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpDiscPrice(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("custCode") String custCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("salesman") String salesman,
            @RequestBody MultiMpDiscPriceVO multiMpDiscPriceVO,
            HttpServletRequest req)
            throws Exception {
            	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        MPUserSession sess = getSession(req);
    	int nRet = 0;
    	
    	List ilist = new ArrayList();
    	
    	List<EgovMap> head = multiMpDiscPriceVO.getHead();
    	if(head != null)
    	{
    		for(EgovMap vo : head)
        	{
    			vo.put("corpCode", corpCode);
    			vo.put("strtDate", StringUtils.remove((String)vo.get("strtDate"), "-"));
    			vo.put("lastDate", StringUtils.remove((String)vo.get("lastDate"), "-"));
    			vo.put("salesman", salesman);
    			vo.put("mdUser", sess.getUser().getId());
        	}
    	}
    	
    	List<EgovMap> mpSalePriceVO = multiMpDiscPriceVO.getDetail();  	
    	//중복검색
    	if(mpSalePriceVO != null)
    	{
    		for(EgovMap vo : mpSalePriceVO)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("strtDate",StringUtils.remove((String)vo.get("strtDate"),"-"));
    			vo.put("lastDate",StringUtils.remove((String)vo.get("lastDate"),"-"));
    			vo.put("salesman",salesman);
    			vo.put("custCode",custCode);
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser", sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	result = mpDiscPriceService.insertMpDiscPrice(multiMpDiscPriceVO);
    	return _filter.makeCORSEntity( result );
    }
    
    /**
     * 승인요청 버튼
     * 
     * */
    @RequestMapping(value="/reqappro/{strtDate}/{lastDate}/{salesman}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> requestApproval(
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
    			vo.put("strtDate",StringUtils.remove((String)vo.get("strtDate"),"-"));
    			vo.put("lastDate",StringUtils.remove((String)vo.get("lastDate"),"-"));
    			vo.put("salesman",salesman);
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser", sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	result = mpDiscPriceService.updateMpDiscPriceApproRequest(ilist);
    	return _filter.makeCORSEntity( result );
    }

    //@RequestMapping("/mpDiscPrice/updateMpDiscPrice.do")
    public String updateMpDiscPrice(
            MpDiscPriceVO mpDiscPriceVO,
            @ModelAttribute("searchVO") MpDiscPriceDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpDiscPriceService.updateMpDiscPrice(mpDiscPriceVO);
        status.setComplete();
        return "forward:/mpDiscPrice/MpDiscPriceList.do";
    }
    
    //@RequestMapping("/mpDiscPrice/deleteMpDiscPrice.do")
    public String deleteMpDiscPrice(
            MpDiscPriceVO mpDiscPriceVO,
            @ModelAttribute("searchVO") MpDiscPriceDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpDiscPriceService.deleteMpDiscPrice(mpDiscPriceVO);
        status.setComplete();
        return "forward:/mpDiscPrice/MpDiscPriceList.do";
    }

}
