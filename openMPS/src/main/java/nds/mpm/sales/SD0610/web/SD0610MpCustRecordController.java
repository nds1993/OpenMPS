package nds.mpm.sales.SD0610.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.sales.SD0610.vo.MpCustRecordVO;
import nds.mpm.sales.SD0610.service.SD0610MpCustRecordService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  SD0610MpCustRecordController
 *
 * @author MPM TEAM
 * @since 2017. 9. 11.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 제품매출 - 영업파트( SD0610 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 9. 11.	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/SD0610/mpcustrecord")
public class SD0610MpCustRecordController extends TMMBaseController{

    @Resource(name = "SD0610mpCustRecordService")
    private SD0610MpCustRecordService mpCustRecordService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_cust_record 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpCustRecordDefaultVO
	 * @return "/mpCustRecord/MpCustRecordList"
	 * @exception Exception
	 */
    @RequestMapping(value="/head/{strtDate}/{lastDate}/{teamCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectHeadMpCustRecordList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("teamCode") String teamCode,
    		//@PathVariable("searchCondition") String searchCondition,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
    	String salesman = req.getParameter("salesman");
    	String interest = req.getParameter("interest");
    	
    	EgovMap searchVO = new EgovMap();
		searchVO.put("corpCode",corpCode);
		searchVO.put("strtDate",strtDate);
		searchVO.put("lastDate",lastDate);
		searchVO.put("teamCode",teamCode);
		searchVO.put("salesman",salesman);
		searchVO.put("interest",interest);
		
    	/*MpCustRecordVO searchVO = new MpCustRecordVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setLastDate(lastDate);
    	searchVO.setTeamCode(teamCode);
    	searchVO.setTeamCode(searchCondition);*/
     	
        List<?> mpCustRecordList = mpCustRecordService.selectMpCustRecordList(searchVO);
        
        PageSet pageSet = new PageSet();
        
        int totCnt = 0;
        //int totCnt = mpCustRecordService.selectMpCustRecordListTotCnt(searchVO);
        if( mpCustRecordList != null )
        {
        	totCnt = mpCustRecordList.size();
        }
        
    	
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpCustRecordList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 

    @RequestMapping(value="/detail/{strtDate}/{lastDate}/{teamCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectDetailMpCustRecordList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("teamCode") String teamCode,
    		//@PathVariable("searchCondition") String searchCondition,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
    	String proCode = req.getParameter("proCode");
    	
    	EgovMap searchVO = new EgovMap();
		searchVO.put("corpCode",corpCode);
		searchVO.put("strtDate",strtDate);
		searchVO.put("lastDate",lastDate);
		searchVO.put("teamCode",teamCode);
		searchVO.put("proCode",proCode);
		
    	/*MpCustRecordVO searchVO = new MpCustRecordVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setLastDate(lastDate);
    	searchVO.setTeamCode(teamCode);
    	searchVO.setTeamCode(searchCondition);*/
     	
        List<?> mpCustRecordList = mpCustRecordService.selectDetailMpCustRecordList(searchVO);
        
        PageSet pageSet = new PageSet();
        
        int totCnt = 0;
        //int totCnt = mpCustRecordService.selectMpCustRecordListTotCnt(searchVO);
        if( mpCustRecordList != null )
        {
        	totCnt = mpCustRecordList.size();
        }
        
        pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpCustRecordList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
}
