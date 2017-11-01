package nds.mpm.buy.PO0301.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.buy.PO0301.service.PO0301MpPigoxmService;
import nds.mpm.buy.PO0301.vo.MpPigoxmDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigoxmVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;

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
 * @Class Name :  PigOtherController
 *
 * @author MPM TEAM
 * @since 2017.08.31
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 이상육 발생현황 관리( PO0301 )-TAB1 이상육,계류사돈,도축사고,정산차액
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.31	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/po0301/mppigoxm")
public class PO0301Tab1MpPigoxmController extends TMMBaseController {

    @Resource(name = "PO0301MpPigoxmService")
    private PO0301MpPigoxmService PO0301MpPigoxmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
     * TAB1 이상육 조회
	 * mp_pidoxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 PO0301MpPigoxmDefaultVO
	 * @exception Exception
	 */
    @RequestMapping(value="/tab1/{dochDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectPO0301MpPigoxmList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("dochDate") String dochDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));

    	String custCode = req.getParameter("custCode");
    	String brandCode = req.getParameter("brandCode");
    	
    	MpPigoxmVO searchVO = new MpPigoxmVO();
    	searchVO.setCorpCode(corpCode);
    	
    	/*searchVO.setBrandCode(brandCode);*/
    	searchVO.setCustCode(custCode);
    	searchVO.setDochDate(StringUtils.remove(dochDate,"-"));
    	searchVO.setBrandCode(brandCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<EgovMap> PO0301MpPigoxmList = PO0301MpPigoxmService.selectPO0301MpPigoxmTab1List(searchVO);

        StringBuffer othCause = new StringBuffer();
        for(EgovMap vo: PO0301MpPigoxmList)
        {
        	othCause  = new StringBuffer();
        	
        	if(!"0".equals((String)vo.get("othCause1")))
        		othCause.append((String)vo.get("othCause1"));
        	if(!"0".equals((String)vo.get("othCause2")))
        		othCause.append(",").append((String)vo.get("othCause2"));
        	if(!"0".equals((String)vo.get("othCause3")))
        		othCause.append(",").append((String)vo.get("othCause3"));
        	if(!"0".equals((String)vo.get("othCause4")))
        		othCause.append(",").append((String)vo.get("othCause4"));
        	if(!"0".equals((String)vo.get("othCause5")))
        		othCause.append(",").append((String)vo.get("othCause5"));
        	if(!"0".equals((String)vo.get("othCause6")))
        		othCause.append(",").append((String)vo.get("othCause6"));
        	if(!"0".equals((String)vo.get("othCause7")))
        		othCause.append(",").append((String)vo.get("othCause7"));
        	if(!"0".equals((String)vo.get("othCause8")))
        		othCause.append(",").append((String)vo.get("othCause8"));
        	if(!"0".equals((String)vo.get("othCause9")))
        		othCause.append(",").append((String)vo.get("othCause9"));
        	
        	vo.put("othCause", StringUtils.stripStart(othCause.toString(), ","));
        }
        
    	PageSet pageSet = new PageSet();
        
        int totCnt = PO0301MpPigoxmService.selectPO0301MpPigoxmTab1ListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(PO0301MpPigoxmList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    /**
     * TAB1 이상육 조회 이상육일괄생성 버튼 
     * 돼지구매 및 정산내역 도축일자, 농장번호, 도체번호 합계 조회. 
	 * mp_pigdxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 PO0301MpPigoxmDefaultVO
	 * @exception Exception
	 */
    @RequestMapping(value="/tab1/mppigdxm/{dochDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectPO0301MpPigdxmList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("dochDate") String dochDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpPigoxmVO searchVO = new MpPigoxmVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setDochDate(dochDate);
    	
        List<?> PO0301MpPigoxmList = PO0301MpPigoxmService.selectPO0301Tab1MpPigdxmList_D(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(PO0301MpPigoxmList!= null)
    		pageSet.setTotalRecordCount(PO0301MpPigoxmList.size());
    	pageSet.setResult(PO0301MpPigoxmList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    /**
     * TAB1 이상육 저장
	 * @param searchVO - 조회할 정보가 담긴 PO0301MpPigoxmDefaultVO
	 * @exception Exception
	 */
    @RequestMapping(value="/tab1/{dochDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addPO0301MpPigoxm(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("dochDate") String dochDate,
            @RequestBody List<EgovMap> PO0301MpPigoxmVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
            	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(PO0301MpPigoxmVOs != null)
    	{
    		for(EgovMap vo : PO0301MpPigoxmVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("dochDate",StringUtils.remove(dochDate,"-"));
    			//구데이터 동일테이블 사용으로 인한 고정값
    			vo.put("othCause",0);
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	result.setExtraData(PO0301MpPigoxmService.insertPO0301MpPigoxm(ilist));
    	
        return _filter.makeCORSEntity( result );
    }
    
}
