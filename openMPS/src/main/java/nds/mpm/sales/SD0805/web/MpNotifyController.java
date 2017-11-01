package nds.mpm.sales.SD0805.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0604.vo.MpCustRecordVO;
import nds.mpm.sales.SD0805.service.MpNotifyService;
import nds.mpm.sales.SD0805.vo.MpNotifyDefaultVO;
import nds.mpm.sales.SD0805.vo.MpNotifyVO;

/**
 * @Class Name :  MpNotifyController
 *
 * @author MPM TEAM
 * @since 2017. 9. 14.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 공지사항 ( SD0905 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 9. 14.	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0805/mpnotify")
public class MpNotifyController extends TMMBaseController{

    @Resource(name = "mpNotifyService")
    private MpNotifyService mpNotifyService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_notify 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpNotifyDefaultVO
	 * @return "/mpNotify/MpNotifyList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpNotifyList(
	    	@PathVariable("corpCode") String corpCode,
			@PathVariable("strtDate") String strtDate,
			@PathVariable("lastDate") String lastDate,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	 	
		
		MpNotifyVO searchVO = new MpNotifyVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(strtDate);
		searchVO.setLastDate(lastDate);
	 	
        List<?> mpNotifyList = mpNotifyService.selectMpNotifyList(searchVO);
	    
        int totCnt = mpNotifyService.selectMpNotifyListTotCnt(searchVO);

	    PageSet pageSet = new PageSet();
	    
	    pageSet.setTotalRecordCount(totCnt);
		pageSet.setResult(mpNotifyList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpNotify(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@RequestBody List<EgovMap> tmPlatxmVOs,
    		HttpServletRequest req,
    		HttpServletResponse res
    		)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(tmPlatxmVOs != null)
    	{
    		for(EgovMap vo : tmPlatxmVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("strtDate",StringUtils.remove(strtDate,"-"));
    			vo.put("lastDate",StringUtils.remove(lastDate,"-"));
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	result = mpNotifyService.insertMpNotify(ilist);
        return _filter.makeCORSEntity( result );
        
    }
    /*
    public String addMpNotifyView(
            @ModelAttribute("searchVO") MpNotifyDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpNotifyVO", new MpNotifyVO());
        return "/mpNotify/MpNotifyRegister";
    }
    
    public String addMpNotify(
            MpNotifyVO mpNotifyVO,
            @ModelAttribute("searchVO") MpNotifyDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpNotifyService.insertMpNotify(mpNotifyVO);
        status.setComplete();
        return "forward:/mpNotify/MpNotifyList.do";
    }
    
    public String updateMpNotifyView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("startDate") java.lang.String startDate ,
            @RequestParam("lastDate") java.lang.String lastDate ,
            @RequestParam("seqNo") java.lang.String seqNo ,
            @ModelAttribute("searchVO") MpNotifyDefaultVO searchVO, Model model)
            throws Exception {
        MpNotifyVO mpNotifyVO = new MpNotifyVO();
        mpNotifyVO.setCorpCode(corpCode);
        mpNotifyVO.setStartDate(startDate);
        mpNotifyVO.setLastDate(lastDate);
        mpNotifyVO.setSeqNo(seqNo);
        // 변수명은 CoC 에 따라 mpNotifyVO
        model.addAttribute(selectMpNotify(mpNotifyVO, searchVO));
        return "/mpNotify/MpNotifyRegister";
    }

    public @ModelAttribute("mpNotifyVO")
    MpNotifyVO selectMpNotify(
            MpNotifyVO mpNotifyVO,
            @ModelAttribute("searchVO") MpNotifyDefaultVO searchVO) throws Exception {
        return mpNotifyService.selectMpNotify(mpNotifyVO);
    }

    public String updateMpNotify(
            MpNotifyVO mpNotifyVO,
            @ModelAttribute("searchVO") MpNotifyDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpNotifyService.updateMpNotify(mpNotifyVO);
        status.setComplete();
        return "forward:/mpNotify/MpNotifyList.do";
    }
    
    public String deleteMpNotify(
            MpNotifyVO mpNotifyVO,
            @ModelAttribute("searchVO") MpNotifyDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpNotifyService.deleteMpNotify(mpNotifyVO);
        status.setComplete();
        return "forward:/mpNotify/MpNotifyList.do";
    }
	*/
}
