package nds.mpm.sales.SD0602.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.sales.SD0602.vo.SD0602MpOrderDVO;
import nds.mpm.sales.SD0602.service.SD0602MpOrderDService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :  SD0602MpOrderDController
 *
 * @author MPM TEAM
 * @since 2017. 9. 25.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 매출장( SD0602 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일			수정자			수정내용
 *  -------    	--------    ---------------------------
 *  2017.9.25	이강욱 		최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0602/mporderd")
public class SD0602MpOrderDController extends TMMBaseController{

    @Resource(name = "SD0602mpOrderDService")
    private SD0602MpOrderDService SD0602mpOrderDService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * 매출장 - 일자별 합계 목록을 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 SD0602MpOrderDVO    
	 * @param	chgoGubn 출고구분 코드 배열
	 * @param	partGubn 영업파트 코드 배열
	 * @param	manGubn  영업사원 코드 배열
	 * @param	prodGubn 제품코드  배열
	 * @param	custGubn 거래처코드 배열
	 * @return "/mpSD0602OrderDList"
	 * @TEST URL : http://localhost:8080/rest/mpm/1001/sd0602/mporderd/tab1/20170801/20170823/10/5021,5029/iskim,taehyun910/10225,10123,10262,10153,10172,10168/17982-000,17996-000,17600-000/
	 * @exception Exception
	 */
    @RequestMapping(value="/tab1/{strtDate}/{lastDate}/{chgoGubn}/{partGubn}/{manGubn}/{prodGubn}/{custGubn}/",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTab1MpOrderDList(
    		
	    	@PathVariable("corpCode") String corpCode,			//회사코드 1001
			@PathVariable("strtDate") String strtDate,			//시작일
			@PathVariable("lastDate") String lastDate,			//종료일			
			@PathVariable("chgoGubn") String chgoGubn,			//출고구분 배열 
			@PathVariable("partGubn") String partGubn,			//영업파트 조회조건	
			@PathVariable("manGubn")  String manGubn,			//영업사원 조회조건	
			@PathVariable("prodGubn") String prodGubn,			//제품코드 조회조건	
			@PathVariable("custGubn") String custGubn,			//거래처코드 조회조건	
	
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );	 	
	
		String[] chgoGubnList	= ("".equals(chgoGubn))?null:chgoGubn.split(",");	// 화면에서 받은 배열을 , 로 분리한 후 List에 담아 넘긴다.		
		String[] partGubnList	= ("".equals(chgoGubn))?null:partGubn.split(",");
		String[] manGubnList	= ("".equals(chgoGubn))?null:manGubn.split(",");
		String[] prodGubnList	= ("".equals(chgoGubn))?null:prodGubn.split(",");
		String[] custGubnList	= ("".equals(chgoGubn))?null:custGubn.split(",");
	
		SD0602MpOrderDVO searchVO = new SD0602MpOrderDVO();
		
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(strtDate);
		searchVO.setLastDate(lastDate);
		searchVO.setChgoGubnList(chgoGubnList);
		searchVO.setPartGubnList(partGubnList);
		searchVO.setManGubnList(manGubnList);
		searchVO.setProdGubnList(prodGubnList);
		searchVO.setCustGubnList(custGubnList);
		
        List<SD0602MpOrderDVO> mpSD0602OrderDList = SD0602mpOrderDService.selectSD0602MpOrderList_1(searchVO);
     
	    PageSet pageSet = new PageSet();
	    
	    if(mpSD0602OrderDList != null)
	    pageSet.setTotalRecordCount(mpSD0602OrderDList.size());
		pageSet.setResult(mpSD0602OrderDList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );	
    } 
    
    @RequestMapping(value="/tab2/{strtDate}/{lastDate}/{chgoGubn}/{partGubn}/{manGubn}/{prodGubn}/{custGubn}/",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTab2MpOrderDList(
    		
    		@PathVariable("corpCode") String corpCode,			//회사코드 1001
			@PathVariable("strtDate") String strtDate,			//시작일
			@PathVariable("lastDate") String lastDate,			//종료일			
			@PathVariable("chgoGubn") String chgoGubn,			//출고구분 배열 
			@PathVariable("partGubn") String partGubn,			//영업파트 조회조건	
			@PathVariable("manGubn")  String manGubn,			//영업사원 조회조건	
			@PathVariable("prodGubn") String prodGubn,			//제품코드 조회조건	
			@PathVariable("custGubn") String custGubn,			//거래처코드 조회조건
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		String[] chgoGubnList	= ("".equals(chgoGubn))?null:chgoGubn.split(",");	// 화면에서 받은 배열을 , 로 분리한 후 List에 담아 넘긴다.		
		String[] partGubnList	= ("".equals(chgoGubn))?null:partGubn.split(",");
		String[] manGubnList	= ("".equals(chgoGubn))?null:manGubn.split(",");
		String[] prodGubnList	= ("".equals(chgoGubn))?null:prodGubn.split(",");
		String[] custGubnList	= ("".equals(chgoGubn))?null:custGubn.split(",");
	 	
		SD0602MpOrderDVO searchVO = new SD0602MpOrderDVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(strtDate);
		searchVO.setLastDate(lastDate);
		searchVO.setChgoGubnList(chgoGubnList);
		searchVO.setPartGubnList(partGubnList);
		searchVO.setManGubnList(manGubnList);
		searchVO.setProdGubnList(prodGubnList);
		searchVO.setCustGubnList(custGubnList);
		
        List<SD0602MpOrderDVO> mpSD0602OrderDList = SD0602mpOrderDService.selectSD0602MpOrderList_2(searchVO);
	    
	    PageSet pageSet = new PageSet();
	    
	    if(mpSD0602OrderDList != null)
	    pageSet.setTotalRecordCount(mpSD0602OrderDList.size());
		pageSet.setResult(mpSD0602OrderDList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );	
    }
    
    @RequestMapping(value="/tab3/{strtDate}/{lastDate}/{chgoGubn}/{partGubn}/{manGubn}/{prodGubn}/{custGubn}/",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTab3MpOrderDList(
    		
    		@PathVariable("corpCode") String corpCode,			//회사코드 1001
			@PathVariable("strtDate") String strtDate,			//시작일
			@PathVariable("lastDate") String lastDate,			//종료일			
			@PathVariable("chgoGubn") String chgoGubn,			//출고구분 배열 
			@PathVariable("partGubn") String partGubn,			//영업파트 조회조건	
			@PathVariable("manGubn")  String manGubn,			//영업사원 조회조건	
			@PathVariable("prodGubn") String prodGubn,			//제품코드 조회조건	
			@PathVariable("custGubn") String custGubn,			//거래처코드 조회조건
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		String[] chgoGubnList	= ("".equals(chgoGubn))?null:chgoGubn.split(",");	// 화면에서 받은 배열을 , 로 분리한 후 List에 담아 넘긴다.		
		String[] partGubnList	= ("".equals(chgoGubn))?null:partGubn.split(",");
		String[] manGubnList	= ("".equals(chgoGubn))?null:manGubn.split(",");
		String[] prodGubnList	= ("".equals(chgoGubn))?null:prodGubn.split(",");
		String[] custGubnList	= ("".equals(chgoGubn))?null:custGubn.split(",");
	 	
		SD0602MpOrderDVO searchVO = new SD0602MpOrderDVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(strtDate);
		searchVO.setLastDate(lastDate);
		searchVO.setChgoGubnList(chgoGubnList);
		searchVO.setPartGubnList(partGubnList);
		searchVO.setManGubnList(manGubnList);
		searchVO.setProdGubnList(prodGubnList);
		searchVO.setCustGubnList(custGubnList);
		
        List<SD0602MpOrderDVO> mpSD0602OrderDList = SD0602mpOrderDService.selectSD0602MpOrderList_3(searchVO);
	    
	    PageSet pageSet = new PageSet();
	    
	    if(mpSD0602OrderDList != null)
	    pageSet.setTotalRecordCount(mpSD0602OrderDList.size());
		pageSet.setResult(mpSD0602OrderDList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );	
    }
    
    @RequestMapping(value="/tab4/{strtDate}/{lastDate}/{chgoGubn}/{partGubn}/{manGubn}/{prodGubn}/{custGubn}/",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTab4MpOrderDList(
    		
    		@PathVariable("corpCode") String corpCode,			//회사코드 1001
			@PathVariable("strtDate") String strtDate,			//시작일
			@PathVariable("lastDate") String lastDate,			//종료일			
			@PathVariable("chgoGubn") String chgoGubn,			//출고구분 배열 
			@PathVariable("partGubn") String partGubn,			//영업파트 조회조건	
			@PathVariable("manGubn")  String manGubn,			//영업사원 조회조건	
			@PathVariable("prodGubn") String prodGubn,			//제품코드 조회조건	
			@PathVariable("custGubn") String custGubn,			//거래처코드 조회조건
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		String[] chgoGubnList	= ("".equals(chgoGubn))?null:chgoGubn.split(",");	// 화면에서 받은 배열을 , 로 분리한 후 List에 담아 넘긴다.		
		String[] partGubnList	= ("".equals(chgoGubn))?null:partGubn.split(",");
		String[] manGubnList	= ("".equals(chgoGubn))?null:manGubn.split(",");
		String[] prodGubnList	= ("".equals(chgoGubn))?null:prodGubn.split(",");
		String[] custGubnList	= ("".equals(chgoGubn))?null:custGubn.split(",");
	 	
		SD0602MpOrderDVO searchVO = new SD0602MpOrderDVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(strtDate);
		searchVO.setLastDate(lastDate);
		searchVO.setChgoGubnList(chgoGubnList);
		searchVO.setPartGubnList(partGubnList);
		searchVO.setManGubnList(manGubnList);
		searchVO.setProdGubnList(prodGubnList);
		searchVO.setCustGubnList(custGubnList);
		
        List<SD0602MpOrderDVO> mpSD0602OrderDList = SD0602mpOrderDService.selectSD0602MpOrderList_4(searchVO);
	    
	    PageSet pageSet = new PageSet();
	    
	    if(mpSD0602OrderDList != null)
	    pageSet.setTotalRecordCount(mpSD0602OrderDList.size());
		pageSet.setResult(mpSD0602OrderDList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );	
    }
    
    @RequestMapping(value="/tab5/{strtDate}/{lastDate}/{chgoGubn}/{partGubn}/{manGubn}/{prodGubn}/{custGubn}/",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTab5MpOrderDList(
    		
    		@PathVariable("corpCode") String corpCode,			//회사코드 1001
			@PathVariable("strtDate") String strtDate,			//시작일
			@PathVariable("lastDate") String lastDate,			//종료일			
			@PathVariable("chgoGubn") String chgoGubn,			//출고구분 배열 
			@PathVariable("partGubn") String partGubn,			//영업파트 조회조건	
			@PathVariable("manGubn")  String manGubn,			//영업사원 조회조건	
			@PathVariable("prodGubn") String prodGubn,			//제품코드 조회조건	
			@PathVariable("custGubn") String custGubn,			//거래처코드 조회조건
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		String[] chgoGubnList	= ("".equals(chgoGubn))?null:chgoGubn.split(",");	// 화면에서 받은 배열을 , 로 분리한 후 List에 담아 넘긴다.		
		String[] partGubnList	= ("".equals(chgoGubn))?null:partGubn.split(",");
		String[] manGubnList	= ("".equals(chgoGubn))?null:manGubn.split(",");
		String[] prodGubnList	= ("".equals(chgoGubn))?null:prodGubn.split(",");
		String[] custGubnList	= ("".equals(chgoGubn))?null:custGubn.split(",");
	 	
		SD0602MpOrderDVO searchVO = new SD0602MpOrderDVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(strtDate);
		searchVO.setLastDate(lastDate);
		searchVO.setChgoGubnList(chgoGubnList);
		searchVO.setPartGubnList(partGubnList);
		searchVO.setManGubnList(manGubnList);
		searchVO.setProdGubnList(prodGubnList);
		searchVO.setCustGubnList(custGubnList);
		
        List<SD0602MpOrderDVO> mpSD0602OrderDList = SD0602mpOrderDService.selectSD0602MpOrderList_5(searchVO);
	    
	    PageSet pageSet = new PageSet();
	    
	    if(mpSD0602OrderDList != null)
	    pageSet.setTotalRecordCount(mpSD0602OrderDList.size());
		pageSet.setResult(mpSD0602OrderDList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );	
    }

}
