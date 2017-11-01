package nds.mpm.sales.SD0401.web;

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
import nds.mpm.sales.SD0405.service.MpOrderDService;
import nds.mpm.sales.SD0405.service.MpOrderHService;
import nds.mpm.sales.SD0405.vo.MpOrderDDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderDVO;

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

/**
 * @Class Name :  SD0401Controller
 *
 * @author MPM TEAM
 * @since 2017.08.17
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 주문조회,입력 ( SD0401 ) 
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
@RequestMapping("/mpm/{corpCode}/sd0401/mporderh")
public class SD0401Controller extends TMMBaseController {

    @Resource(name = "SD0401Service")
    private SD0401Service SD0401Service;
    
    @Resource(name = "mpOrderHService")
    private MpOrderHService mpOrderHService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_order_h 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SD0401DefaultVO
	 * @return "/SD0401/SD0401List"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSD0401List(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
        
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String strtDate = req.getParameter("strtDate");
    	String lastDate = req.getParameter("lastDate");
    	
    	String salesman = req.getParameter("salesman");
    	String ordrType = req.getParameter("ordrType");
    	String status = req.getParameter("status");
    	
    	
    	SD0401VO searchVO = new SD0401VO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSalesman(salesman);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setOrdrType(ordrType);
    	searchVO.setStatus(status);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> SD0401List = SD0401Service.selectSD0401List(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = SD0401Service.selectSD0401ListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(SD0401List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
	 * mp_order_d 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpOrderDDefaultVO
	 * @return "/mpOrderD/MpOrderDList"
	 * @exception Exception
	 */
    @RequestMapping(value="/tab1/{delvDate}/{ordrNo}")
    public ResponseEntity<ResultEx> selectMpOrderDList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("delvDate") String delvDate,
    		@PathVariable("ordrNo") String ordrNo,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	SD0401VO searchVO = new SD0401VO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setDelvDate(delvDate);
    	searchVO.setOrdrNo(ordrNo);
    	
        List<?> mpOrderDList = SD0401Service.selectTab1MpOrderDList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpOrderDList!=null )
    		pageSet.setTotalRecordCount(mpOrderDList.size());
    	pageSet.setResult(mpOrderDList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    @RequestMapping(value="/tab2/{delvDate}/{ordrNo}")
    public ResponseEntity<ResultEx> selectTab2MpOrderDList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("delvDate") String delvDate,
    		@PathVariable("ordrNo") String ordrNo,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	SD0401VO searchVO = new SD0401VO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setDelvDate(delvDate);
    	searchVO.setOrdrNo(ordrNo);
    	
        List<?> mpOrderDList = SD0401Service.selectTab2MpOrderDList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpOrderDList!=null )
    		pageSet.setTotalRecordCount(mpOrderDList.size());
    	pageSet.setResult(mpOrderDList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    /**
     * 주문입력시 거래처의 배송처 조회.
     * 
     * */
    @RequestMapping(value="/delv/{ordrCust}")
    public ResponseEntity<ResultEx> selectMpOrderDelvCust(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("ordrCust") String ordrCust,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	SD0401VO searchVO = new SD0401VO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setOrdrCust(ordrCust);
		
    	result.setExtraData(SD0401Service.selectSD0401DelvCust(searchVO));
    	
    	return _filter.makeCORSEntity( result );
    } 
    /**
    @RequestMapping("/SD0401/addSD0401View.do")
    public String addSD0401View(
            @ModelAttribute("searchVO") SD0401DefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("SD0401VO", new SD0401VO());
        return "/SD0401/SD0401Register";
    }
    **/
   
    /**
     * 웹 모바일 주문입력 SD0401 
     * WEB, MOBILE order save 
     * 
     * */
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addSD0401(
    		@PathVariable("corpCode") String corpCode,
    		SD0401VO searchVO,
    		@RequestBody MultiSD0401VO sD0401VO,
    		HttpServletRequest req,
    		HttpServletResponse res
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);

    	searchVO.setCorpCode(corpCode);
    	searchVO.setCrUser(sess.getUser().getId());
    	searchVO.setMdUser(sess.getUser().getId());
    	
    	if(sD0401VO != null)
    	{
    		result = SD0401Service.insertMpOrderH(searchVO, sD0401VO);
    	}
    	return _filter.makeCORSEntity( result );
    }
    /**
    @RequestMapping("/SD0401/updateSD0401.do")
    public String updateSD0401(
            SD0401VO SD0401VO,
            @ModelAttribute("searchVO") SD0401DefaultVO searchVO, SessionStatus status)
            throws Exception {
        status.setComplete();
        return "forward:/SD0401/SD0401List.do";
    }
    
    @RequestMapping("/SD0401/deleteSD0401.do")
    public String deleteSD0401(
            SD0401VO SD0401VO,
            @ModelAttribute("searchVO") SD0401DefaultVO searchVO, SessionStatus status)
            throws Exception {
        status.setComplete();
        return "forward:/SD0401/SD0401List.do";
    }
    **/
    /**
     * 신규주문.
     * WMS view table 연계, 
     * 제품추가시 제품단가, 제품재고조회.
     * 
     * **/
    @RequestMapping("/wms/{ordrCust}/{delvDate}/{delvDc}/{proCode}")
    public ResponseEntity<ResultEx> selectMpOrderD(
    		@PathVariable("corpCode") java.lang.String corpCode ,
    		@PathVariable("ordrCust") java.lang.String ordrCust ,
    		@PathVariable("delvDate") java.lang.String delvDate ,
    		@PathVariable("delvDc") java.lang.String delvDc ,
    		@PathVariable("proCode") java.lang.String proCode ,
            SD0401VO SD0401VO
            ) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	SD0401VO.setCorpCode(corpCode);
    	SD0401VO.setCustCode(ordrCust);
    	SD0401VO.setDelvDate(StringUtils.remove(delvDate,"-"));
    	SD0401VO.setDelvDc(delvDc);
    	SD0401VO.setProCode(proCode);
    	result.setExtraData(SD0401Service.selectSD0401ProJaegoInfo(SD0401VO));
        
        return _filter.makeCORSEntity( result );
    }
    
    /**
     * 신규주문 
     * 거래처,제품 추가시 여신조회.
     * 
     * */
    @RequestMapping("/credit/{ordrCust}/{strtDate}/{ordrAmt}")
    public ResponseEntity<ResultEx> selectSD0401CustLimit(
    		@PathVariable("corpCode") java.lang.String corpCode ,
    		@PathVariable("ordrCust") java.lang.String ordrCust ,
    		@PathVariable("strtDate") java.lang.String strtDate ,
    		@PathVariable("ordrAmt") BigDecimal ordrAmt ,
            SD0401VO SD0401VO,
            @ModelAttribute("searchVO") MpOrderDDefaultVO searchVO) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		SD0401VO.setCorpCode(corpCode);
		SD0401VO.setCustCode(ordrCust);
		SD0401VO.setStrtDate(StringUtils.remove(strtDate,"-"));
		SD0401VO.setOrdrAmt(ordrAmt);
		result.setExtraData(SD0401Service.selectSD0401CustLimit(SD0401VO));
	    
	    return _filter.makeCORSEntity( result );
    }
}
