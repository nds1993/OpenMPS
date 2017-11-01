package nds.mpm.sales.SD0802.web;

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
import nds.mpm.sales.SD0802.service.MpDelvLimitService;
import nds.mpm.sales.SD0802.vo.MpDelvLimitVO;

import org.apache.commons.lang.StringUtils;
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
 * @Class Name :  MpDelvLimitController
 *
 * @author MPM TEAM
 * @since 2017. 10. 5.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 출고제한 ( SD0802 )
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 10. 5.	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/sd0802/mpdelvlimit")
public class MpDelvLimitController extends TMMBaseController{

    @Resource(name = "mpDelvLimitService")
    private MpDelvLimitService mpDelvLimitService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_delv_limit 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpDelvLimitDefaultVO
	 * @return "/mpDelvLimit/MpDelvLimitList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{startDate}/{lastDate}")
    public ResponseEntity<ResultEx> selectMpDelvLimitList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("startDate") String startDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpDelvLimitVO searchVO = new MpDelvLimitVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setStartDate(StringUtils.remove(startDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpDelvLimitList = mpDelvLimitService.selectMpDelvLimitList(searchVO);

    	PageSet pageSet = new PageSet();
    	
    	if(mpDelvLimitList!= null)
    		pageSet.setTotalRecordCount(mpDelvLimitList.size());
    	pageSet.setResult(mpDelvLimitList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{startDate}/{lastDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx>  addMpDelvLimit(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("startDate") String startDate,
    		@PathVariable("lastDate") String lastDate,
    		@RequestBody List<EgovMap> mpDelvLimitVOs,
    		HttpServletRequest req)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	int nRet = 0;
    	
    	List ilist = new ArrayList();
    	//중복검색
    	if(mpDelvLimitVOs != null)
    	{
    		for(EgovMap vo : mpDelvLimitVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("startDate",StringUtils.remove((String)vo.get("startDate"),"-"));
    			vo.put("lastDate",StringUtils.remove((String)vo.get("lastDate"),"-"));
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser", sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	result = mpDelvLimitService.insertMpDelvLimit(ilist);
        return _filter.makeCORSEntity( result );
    }
    
    /**
    @RequestMapping("/mpDelvLimit/updateMpDelvLimitView.do")
    public String updateMpDelvLimitView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("startDate") java.lang.String startDate ,
            @RequestParam("lastDate") java.lang.String lastDate ,
            @RequestParam("dcCode") java.lang.String dcCode ,
            @RequestParam("proCode") java.lang.String proCode ,
            @ModelAttribute("searchVO") MpDelvLimitDefaultVO searchVO, Model model)
            throws Exception {
        MpDelvLimitVO mpDelvLimitVO = new MpDelvLimitVO();
        mpDelvLimitVO.setCorpCode(corpCode);
        mpDelvLimitVO.setStartDate(startDate);
        mpDelvLimitVO.setLastDate(lastDate);
        mpDelvLimitVO.setDcCode(dcCode);
        mpDelvLimitVO.setProCode(proCode);
        // 변수명은 CoC 에 따라 mpDelvLimitVO
        model.addAttribute(selectMpDelvLimit(mpDelvLimitVO, searchVO));
        return "/mpDelvLimit/MpDelvLimitRegister";
    }

    @RequestMapping("/mpDelvLimit/selectMpDelvLimit.do")
    public @ModelAttribute("mpDelvLimitVO")
    MpDelvLimitVO selectMpDelvLimit(
            MpDelvLimitVO mpDelvLimitVO,
            @ModelAttribute("searchVO") MpDelvLimitDefaultVO searchVO) throws Exception {
        return mpDelvLimitService.selectMpDelvLimit(mpDelvLimitVO);
    }

    @RequestMapping("/mpDelvLimit/updateMpDelvLimit.do")
    public String updateMpDelvLimit(
            MpDelvLimitVO mpDelvLimitVO,
            @ModelAttribute("searchVO") MpDelvLimitDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpDelvLimitService.updateMpDelvLimit(mpDelvLimitVO);
        status.setComplete();
        return "forward:/mpDelvLimit/MpDelvLimitList.do";
    }
    
    @RequestMapping("/mpDelvLimit/deleteMpDelvLimit.do")
    public String deleteMpDelvLimit(
            MpDelvLimitVO mpDelvLimitVO,
            @ModelAttribute("searchVO") MpDelvLimitDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpDelvLimitService.deleteMpDelvLimit(mpDelvLimitVO);
        status.setComplete();
        return "forward:/mpDelvLimit/MpDelvLimitList.do";
    }
	*/
}
