package nds.tmm.common.TMCOCD10.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.tmm.common.TMCOCD10.service.TMCOCD10DETAILService;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10DETAILVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :  TMCOCD10DETAILController
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 공통코드관리 상세( TMCOCD10 )
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
@RequestMapping("/tmm/{corpCode}/tmcocd10")
public class TMCOCD10DETAILController extends TMMBaseController {

	@Autowired
	protected CorsFilter		_filter;
	
    @Resource(name = "TMCOCD10DETAILService")
    private TMCOCD10DETAILService TMCOCD10DETAILService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * BASE_ITEM 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOCD10DETAILDefaultVO
	 * @return "/TMCOCD10DETAIL/TMCOCD10DETAILList"
	 * @exception Exception
	*/ 
    @RequestMapping(value="/{groupCode}/tmcodexd",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOCD10DETAILList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("groupCode") String groupCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String searchKeyword = req.getParameter("searchKeyword");
    	String etc02 = req.getParameter("etc02");
		
    	TMCOCD10DETAILVO searchVO = new TMCOCD10DETAILVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setGroupCode(groupCode);
    	searchVO.setSearchKeyword(searchKeyword);
    	searchVO.setEtc02(etc02);
    	
    	
    	PageSet pageSet = new PageSet();
    	
    	List<?> baseInfoList = TMCOCD10DETAILService.selectTMCOCD10DETAILList(searchVO);
    	
    	int totalRecordCount = TMCOCD10DETAILService.selectTMCOCD10DETAILListTotCnt(searchVO);
    	
    	pageSet.setTotalRecordCount(totalRecordCount);
    	pageSet.setResult(baseInfoList);
		
    	result.setExtraData(pageSet);
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{groupCode}/tmcodexd/code",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOCD10DETAILList2(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("groupCode") String groupCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	String searchKeyword = req.getParameter("searchKeyword");
    	String etc02 = req.getParameter("etc02");
    	
    	TMCOCD10DETAILVO searchVO = new TMCOCD10DETAILVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setGroupCode(groupCode);
    	searchVO.setSearchKeyword(searchKeyword);
    	searchVO.setEtc02(etc02);
    	
    	PageSet pageSet = new PageSet();
    	
    	List<?> baseInfoList = null;
    	
    	if("SDDC".equals(groupCode))
    	{
    		baseInfoList = TMCOCD10DETAILService.selectTMCOCD10DETAILSDDCList_D(searchVO);
    	}
    	else
    		baseInfoList = TMCOCD10DETAILService.selectTMCOCD10DETAILCodeList_D(searchVO);
    	
    	
    	if(baseInfoList != null)
    		pageSet.setTotalRecordCount(baseInfoList.size());
    	pageSet.setResult(baseInfoList);
		
    	result.setExtraData(pageSet);
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{groupCode}/tmcodexd",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTMCOCD10DETAIL(
    		@PathVariable("corpCode") String corpCode,
    		@RequestBody List<TMCOCD10DETAILVO> tMCOCD10DETAILVOs,
    		HttpServletRequest req)
            throws Exception {
    	
    	ResponseEntity<ResultEx> resData = null;
    	MPUserSession sess = getSession(req);
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	List ilist = new ArrayList();
    	//중복검색
    	if(tMCOCD10DETAILVOs != null)
    	{
    		for(TMCOCD10DETAILVO vo : tMCOCD10DETAILVOs)
        	{
        		vo.setCorpCode(corpCode);
        		if(sess != null)
    			{
    				vo.setCrUser(sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
        int nRet = 0;
        if((nRet = TMCOCD10DETAILService.insertTMCOCD10DETAIL(ilist)) < 0)
        {
        	result = new ResultEx( Consts.ResultCode.RC_ERROR );
        	result.setResultCode(nRet);
        }
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/updateTMCOCD10DETAIL.do",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateTMCOCD10DETAIL(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("groupCode") String groupCode,
    		@RequestBody List<Object> tMCOCD10DETAILVO,
            SessionStatus status)
            throws Exception {
    	
    	ResponseEntity<ResultEx> resData = null;
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        TMCOCD10DETAILService.updateTMCOCD10DETAIL(tMCOCD10DETAILVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/deleteTMCOCD10DETAIL.do",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteTMCOCD10DETAIL(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("groupCode") String groupCode,
    		@RequestBody List<Object> tMCOCD10DETAILVOs,
            SessionStatus status)
            throws Exception {
    	
    	ResponseEntity<ResultEx> resData = null;
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	
        TMCOCD10DETAILService.deleteTMCOCD10DETAIL(tMCOCD10DETAILVOs);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }

}
