package nds.mpm.sales.SD0606.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.sales.SD0606.vo.MpCustRecordVO;
import nds.mpm.sales.SD0606.service.SD0606MpCustRecordService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :  SD0606MpCustRecordController
 *
 * @author MPM TEAM
 * @since 2017. 9. 11.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 판매추이 - 누계 (  )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		  수정내용
 *  -------    	    --------    ---------------------------
 *  2017. 9. 11.	 			  최초생성
 *  2017. 9. 19    JAR         수정
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0606/mpcustrecord")
public class SD0606MpCustRecordController extends TMMBaseController{

    @Resource(name = "SD0606mpCustRecordService")
    private SD0606MpCustRecordService mpCustRecordService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_cust_record 판매추이 누계 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpCustRecordDefaultVO
	 * @return "/mpCustRecord/MpCustRecordList"
	 * @exception Exception
	 */
    @RequestMapping(value="/list/{strtYyyy}/{teamCode}/{salesman}/{custCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectHeadMpCustRecordList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtYyyy") String strtYyyy, 
    		@PathVariable("teamCode") String teamCode,
    		@PathVariable("salesman") String salesman,
    		@PathVariable("custCode") String custCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
    	int preYear = Integer.parseInt(strtYyyy) -1;
    	
    	MpCustRecordVO searchVO = new MpCustRecordVO();
    	searchVO.setCorpCode(corpCode); //회사코드
    	searchVO.setStrtYyyy(strtYyyy); //판매년도
    	searchVO.setStrtPreYyyy(String.valueOf(preYear)); //전년도
    	searchVO.setTeamCode(teamCode); //영업파트
    	searchVO.setSalesman(salesman); //영업사원
    	searchVO.setCustCode(custCode); //거래처
     	
        List<?> mpCustRecordList = mpCustRecordService.selectMpCustRecordList(searchVO);
        
        
        PageSet pageSet = new PageSet();
        
        if(mpCustRecordList != null)
        	pageSet.setTotalRecordCount(mpCustRecordList.size());
    	pageSet.setResult(mpCustRecordList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 

    /**
	 * mp_cust_record 판매추이 누계 그래프용 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpCustRecordDefaultVO
	 * @return "/mpCustRecord/MpCustRecordList"
	 * @exception Exception
	 */
    @RequestMapping(value="/chart/{strtYyyy}/{teamCode}/{salesman}/{custCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectDetailTab1MpCustRecordList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtYyyy") String strtYyyy,
    		@PathVariable("teamCode") String teamCode,
    		@PathVariable("salesman") String salesman,
    		@PathVariable("custCode") String custCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
    	int preYear = Integer.parseInt(strtYyyy) -1;
    	
    	MpCustRecordVO searchVO = new MpCustRecordVO();
    	searchVO.setCorpCode(corpCode); //회사코드
    	searchVO.setStrtYyyy(strtYyyy); //판매년도
    	searchVO.setStrtPreYyyy(String.valueOf(preYear)); //전년도
    	searchVO.setTeamCode(teamCode); //영업파트
    	searchVO.setSalesman(salesman); //영업사원
    	searchVO.setCustCode(custCode); //거래처
     	
        List<?> mpCustRecordList = mpCustRecordService.selectChartMpCustRecordList(searchVO);
        
    	result.setExtraData(mpCustRecordList);
    	
    	return _filter.makeCORSEntity( result );
    }
}
