package nds.mpm.sales.SD0403;

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
import nds.mpm.login.vo.RoleConsts;
import nds.mpm.sales.SD0402.service.SD0402Service;
import nds.mpm.sales.SD0402.vo.SD0402VO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Class Name :  SD0402Controller
 *
 * @author MPM TEAM
 * @since 2017.08.17
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 출고승인( SD0403 )
 * 서비스는 출고요청서비스 SD0402를 사용한다.
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
@RequestMapping("/mpm/{corpCode}/sd0403/mpdelvappro")
public class SD0403Controller extends TMMBaseController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0403Controller.class);

    @Resource(name = "SD0402Service")
    private SD0402Service SD0402Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_delv_approval 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SD0402DefaultVO
	 * @return "/SD0402/SD0402List"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}")
    public ResponseEntity<ResultEx> selectSD0403List(
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
    	
    	String salesman = req.getParameter("salesman");
    	String approYn = req.getParameter("approYn");
    	
    	SD0402VO searchVO = new SD0402VO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setApproYn(approYn);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> SD0402List = SD0402Service.selectSD0403List(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = SD0402Service.selectSD0403ListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(SD0402List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 

    /**
	 * 출고승인 
	 * @param
	 * session user ofce_code not null
	 * delv_date + ordr_no 키
	 * @return 
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}/approyn",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> approSD0403(
    		@PathVariable("corpCode") String corpCode,
            @RequestBody List<EgovMap> mpBomHVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
        
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	/**
    	 * 
    	 * */

    	LOGGER.debug("user ofce :: " + sess.getUser().getOfceCode());
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(mpBomHVOs != null)
    	{
    		for(EgovMap vo : mpBomHVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("approYn", "N");
    			if(sess != null)
    			{
    				vo.put("mdUser",sess.getUser().getId());
    				
    		    	if(RoleConsts.OFCE_PART_CODE.equals(sess.getUser().getOfceCode()))
    		    	{
    		    		vo.put("partAppro", "Y");
    		    	}
    		    	else if(RoleConsts.OFCE_HEAD_CODE.equals(sess.getUser().getOfceCode()))
    		    	{
    		    		vo.put("headAppro", "Y");
    		    	}
    		    	else if(RoleConsts.OFCE_CEO_CODE.equals(sess.getUser().getOfceCode()))
    		    	{
    		    		vo.put("ceoAppro", "Y");
    		    	}
    		    	else
    		    	{
    		    		return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_DENINED ) );
    		    	}
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	SD0402Service.updateDelvApproConfirm(ilist);
        return _filter.makeCORSEntity( result );
    }
    
    /**
	 * 출고승인 반려
	 * @param 
	 * session user ofce_code not null
	 * delv_date + ordr_no 키
	 * @return 
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}/reject",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> rejectSD0403(
    		@PathVariable("corpCode") String corpCode,
            @RequestBody List<EgovMap> mpBomHVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
        
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	LOGGER.debug("user ofce :: " + sess.getUser().getOfceCode());
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(mpBomHVOs != null)
    	{
    		for(EgovMap vo : mpBomHVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("approYn", "N");
    			if(sess != null)
    			{
    				vo.put("mdUser",sess.getUser().getId());
    				
    				if(RoleConsts.OFCE_PART_CODE.equals(sess.getUser().getOfceCode()))
    		    	{
    		    		vo.put("partAppro", "X");
    		    	}
    		    	else if(RoleConsts.OFCE_HEAD_CODE.equals(sess.getUser().getOfceCode()))
    		    	{
    		    		vo.put("headAppro", "X");
    		    	}
    		    	else if(RoleConsts.OFCE_CEO_CODE.equals(sess.getUser().getOfceCode()))
    		    	{
    		    		vo.put("ceoAppro", "X");
    		    	}
    		    	else
    		    	{
    		    		return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_DENINED ) );
    		    	}
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	SD0402Service.updateDelvApproConfirm(ilist);
        return _filter.makeCORSEntity( result );
    }
}
