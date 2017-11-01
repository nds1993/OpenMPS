package nds.mpm.prod.PP0104.web;

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
import nds.mpm.prod.PP0103.service.MpItemMasterMService;
import nds.mpm.prod.PP0103.vo.MpItemMasterMVO;
import nds.mpm.prod.PP0104.service.MpSetItemMService;
import nds.mpm.prod.PP0104.vo.MpSetItemMVO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  MpSetItemMController
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 세트등록( PP0104 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.09.05	 이수락			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/pp0104/mpsetitemm")
public class MpSetItemMController extends TMMBaseController {
	
	private static final Logger _logger = LoggerFactory.getLogger(MpSetItemMController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpSetItemMService")
    private MpSetItemMService mpSetItemMService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
     * left grid
     * set product 조회
     * 
     * */
    @RequestMapping(value="/mpitemmasterm/{setYn}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSetItemMasterMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("setYn") String setYn,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String searchKeyword = req.getParameter("searchKeyword");
    	//세트상품은 전체조회(생산)
    	
    	MpItemMasterMVO searchVO = new MpItemMasterMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	searchVO.setSetYn(setYn);
    	
    	searchVO.setSearchKeyword(searchKeyword);
    	
        List<?> userInfoList = mpSetItemMService.selectMpItemMasterMList(searchVO);

    	PageSet pageSet = new PageSet();
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	if(userInfoList != null)
    		pageSet.setTotalRecordCount(userInfoList.size());
    	pageSet.setResult(userInfoList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }

    /**
     * right grid set item product
	 * mp_set_item_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpSetItemMDefaultVO
	 * @return "/mpSetItemM/MpSetItemMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{setCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpSetItemMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("setCode") String setCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String searchCondition = req.getParameter("searchCondition");
    	String searchKeyword = req.getParameter("searchKeyword");
    	
    	MpSetItemMVO searchVO = new MpSetItemMVO();
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchKeyword(searchKeyword);
    	searchVO.setSetCode(setCode);
    	
        List<?> mpSetItemMList = mpSetItemMService.selectMpSetItemMList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpSetItemMService.selectMpSetItemMListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpSetItemMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{setCode}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpSetItemM(
            @PathVariable("corpCode") String corpCode,
            @PathVariable("setCode") String setCode,
    		@RequestBody List<EgovMap> mpSetItemMVOs,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(mpSetItemMVOs != null)
    	{
    		for(EgovMap vo : mpSetItemMVOs)
        	{
    			vo.put("corpCode", corpCode);
    			vo.put("setCode", setCode);
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser", sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	result = mpSetItemMService.insertMpSetItemM(ilist);
        return _filter.makeCORSEntity( result );
    }

}
