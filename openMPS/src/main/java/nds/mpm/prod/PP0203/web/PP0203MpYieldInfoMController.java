package nds.mpm.prod.PP0203.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.prod.PP0203.service.PP0203MpYieldInfoMService;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMVO;

/**
 * @Class Name : MpYieldInfoMController.java
 * @Description : MpYieldInfoM Controller class
 * @Modification Information
 *
 * @author 3333333
 * @since 3333333
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0203/mpyieldinfom")
public class PP0203MpYieldInfoMController extends TMMBaseController{

    @Resource(name = "PP0203mpYieldInfoMService")
    private PP0203MpYieldInfoMService mpYieldInfoMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_yield_info_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpYieldInfoMDefaultVO
	 * @return "/mpYieldInfoM/MpYieldInfoMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/top/{junStrtDate}/{junLastDateBe}/{dangStrtDateNo}/{dangLastDateNo}/{grupPlant}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpYieldInfoMTopList(
       		@PathVariable("corpCode") String corpCode,
       		@PathVariable("junStrtDate") String junStrtDate,
       		@PathVariable("junLastDateBe") String junLastDateBe,
       		@PathVariable("dangStrtDateNo") String dangStrtDateNo,
       		@PathVariable("dangLastDateNo") String dangLastDateNo,
       		@PathVariable("grupPlant") String grupPlant,
       		HttpServletRequest req,
       		HttpServletResponse res,
       		ModelMap model)
           throws Exception {
    	
           ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
       	
           int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
	       int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
	       	
	       MpYieldInfoMVO searchVO = new MpYieldInfoMVO();
	       searchVO.setCorpCode(corpCode);
	       	
	       searchVO.setPageIndex(pageIndex);
	       searchVO.setPageSize(pageSize);
	       	
	       searchVO.setJunStrtDate(StringUtils.remove(junStrtDate,"-"));
	       searchVO.setJunLastDateBe(StringUtils.remove(junLastDateBe,"-"));
	       searchVO.setDangStrtDateNo(StringUtils.remove(dangStrtDateNo,"-"));
	       searchVO.setDangLastDateNo(StringUtils.remove(dangLastDateNo,"-"));
	       searchVO.setGrupPlant(grupPlant);
    	
           List<?> mpYieldInfoMList = mpYieldInfoMService.selectMpYieldInfoMTopList(searchVO);

    	   PageSet pageSet = new PageSet();
        
           int totCnt = mpYieldInfoMService.selectMpYieldInfoMListTotCnt(searchVO);
           pageSet.setPageIndex(pageIndex);
	       pageSet.setPageSize(pageSize);
	       pageSet.setTotalRecordCount(totCnt);
	       pageSet.setResult(mpYieldInfoMList);
	   		
	       result.setExtraData(pageSet);
	       	
	       return _filter.makeCORSEntity( result );
    } 
    
    /**
	 * mp_yield_info_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpYieldInfoMDefaultVO
	 * @return "/mpYieldInfoM/MpYieldInfoMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/ldetail/{junStrtDate}/{junLastDateBe}/{dangStrtDateNo}/{dangLastDateNo}/{grupPlant}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpYieldInfoMLDetailList(
       		@PathVariable("corpCode") String corpCode,
       		@PathVariable("junStrtDate") String junStrtDate,
       		@PathVariable("junLastDateBe") String junLastDateBe,
       		@PathVariable("dangStrtDateNo") String dangStrtDateNo,
       		@PathVariable("dangLastDateNo") String dangLastDateNo,
       		@PathVariable("grupPlant") String grupPlant,
       		HttpServletRequest req,
       		HttpServletResponse res,
       		ModelMap model)
           throws Exception {
    	
           ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
       	
           int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
	       int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
	       	
	       MpYieldInfoMVO searchVO = new MpYieldInfoMVO();
	       searchVO.setCorpCode(corpCode);
	       	
	       searchVO.setPageIndex(pageIndex);
	       searchVO.setPageSize(pageSize);
	       	
	       searchVO.setJunStrtDate(StringUtils.remove(junStrtDate,"-"));
	       searchVO.setJunLastDateBe(StringUtils.remove(junLastDateBe,"-"));
	       searchVO.setDangStrtDateNo(StringUtils.remove(dangStrtDateNo,"-"));
	       searchVO.setDangLastDateNo(StringUtils.remove(dangLastDateNo,"-"));
	       
	       searchVO.setGrupPlant(grupPlant);
    	
           List<?> mpYieldInfoMList = mpYieldInfoMService.selectMpYieldInfoMLDetailList(searchVO);

    	   PageSet pageSet = new PageSet();
        
           int totCnt = mpYieldInfoMService.selectMpYieldInfoMListTotCnt(searchVO);
           pageSet.setPageIndex(pageIndex);
	       pageSet.setPageSize(pageSize);
	       pageSet.setTotalRecordCount(totCnt);
	       pageSet.setResult(mpYieldInfoMList);
	   		
	       result.setExtraData(pageSet);
	       	
	       return _filter.makeCORSEntity( result );
    } 

    /**
	 * mp_yield_info_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpYieldInfoMDefaultVO
	 * @return "/mpYieldInfoM/MpYieldInfoMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/sdetail/{junStrtDate}/{junLastDateBe}/{dangStrtDateNo}/{dangLastDateNo}/{grupPlant}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpYieldInfoMSDetailList(
       		@PathVariable("corpCode") String corpCode,
       		@PathVariable("junStrtDate") String junStrtDate,
       		@PathVariable("junLastDateBe") String junLastDateBe,
       		@PathVariable("dangStrtDateNo") String dangStrtDateNo,
       		@PathVariable("dangLastDateNo") String dangLastDateNo,
       		@PathVariable("grupPlant") String grupPlant,
       		HttpServletRequest req,
       		HttpServletResponse res,
       		ModelMap model)
           throws Exception {
    	
           ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
       	
           int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
	       int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
	       	
	       MpYieldInfoMVO searchVO = new MpYieldInfoMVO();
	       searchVO.setCorpCode(corpCode);
	       	
	       searchVO.setPageIndex(pageIndex);
	       searchVO.setPageSize(pageSize);
	       	
	       searchVO.setJunStrtDate(StringUtils.remove(junStrtDate,"-"));
	       searchVO.setJunLastDateBe(StringUtils.remove(junLastDateBe,"-"));
	       searchVO.setDangStrtDateNo(StringUtils.remove(dangStrtDateNo,"-"));
	       searchVO.setDangLastDateNo(StringUtils.remove(dangLastDateNo,"-"));
	       searchVO.setGrupPlant(grupPlant);
    	
           List<?> mpYieldInfoMList = mpYieldInfoMService.selectMpYieldInfoMSDetailList(searchVO);

    	   PageSet pageSet = new PageSet();
        
           int totCnt = mpYieldInfoMService.selectMpYieldInfoMListTotCnt(searchVO);
           pageSet.setPageIndex(pageIndex);
	       pageSet.setPageSize(pageSize);
	       pageSet.setTotalRecordCount(totCnt);
	       pageSet.setResult(mpYieldInfoMList);
	   		
	       result.setExtraData(pageSet);
	       	
	       return _filter.makeCORSEntity( result );
    } 
    /*@RequestMapping("/mpYieldInfoM/addMpYieldInfoMView.do")
    public String addMpYieldInfoMView(
            @ModelAttribute("searchVO") MpYieldInfoMDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpYieldInfoMVO", new MpYieldInfoMVO());
        return "/mpYieldInfoM/MpYieldInfoMRegister";
    }
    
    @RequestMapping("/mpYieldInfoM/addMpYieldInfoM.do")
    public String addMpYieldInfoM(
            MpYieldInfoMVO mpYieldInfoMVO,
            @ModelAttribute("searchVO") MpYieldInfoMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpYieldInfoMService.insertMpYieldInfoM(mpYieldInfoMVO);
        status.setComplete();
        return "forward:/mpYieldInfoM/MpYieldInfoMList.do";
    }
    
    @RequestMapping("/mpYieldInfoM/updateMpYieldInfoMView.do")
    public String updateMpYieldInfoMView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("grupPlant") java.lang.String grupPlant ,
            @RequestParam("workDate") java.lang.String workDate ,
            @RequestParam("seq") java.math.BigDecimal seq ,
            @ModelAttribute("searchVO") MpYieldInfoMDefaultVO searchVO, Model model)
            throws Exception {
        MpYieldInfoMVO mpYieldInfoMVO = new MpYieldInfoMVO();
        mpYieldInfoMVO.setCorpCode(corpCode);
        mpYieldInfoMVO.setGrupPlant(grupPlant);
        mpYieldInfoMVO.setWorkDate(workDate);
        mpYieldInfoMVO.setSeq(seq);
        // 변수명은 CoC 에 따라 mpYieldInfoMVO
        model.addAttribute(selectMpYieldInfoM(mpYieldInfoMVO, searchVO));
        return "/mpYieldInfoM/MpYieldInfoMRegister";
    }

    @RequestMapping("/mpYieldInfoM/selectMpYieldInfoM.do")
    public @ModelAttribute("mpYieldInfoMVO")
    MpYieldInfoMVO selectMpYieldInfoM(
            MpYieldInfoMVO mpYieldInfoMVO,
            @ModelAttribute("searchVO") MpYieldInfoMDefaultVO searchVO) throws Exception {
        return mpYieldInfoMService.selectMpYieldInfoM(mpYieldInfoMVO);
    }

    @RequestMapping("/mpYieldInfoM/updateMpYieldInfoM.do")
    public String updateMpYieldInfoM(
            MpYieldInfoMVO mpYieldInfoMVO,
            @ModelAttribute("searchVO") MpYieldInfoMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpYieldInfoMService.updateMpYieldInfoM(mpYieldInfoMVO);
        status.setComplete();
        return "forward:/mpYieldInfoM/MpYieldInfoMList.do";
    }
    
    @RequestMapping("/mpYieldInfoM/deleteMpYieldInfoM.do")
    public String deleteMpYieldInfoM(
            MpYieldInfoMVO mpYieldInfoMVO,
            @ModelAttribute("searchVO") MpYieldInfoMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpYieldInfoMService.deleteMpYieldInfoM(mpYieldInfoMVO);
        status.setComplete();
        return "forward:/mpYieldInfoM/MpYieldInfoMList.do";
    }
*/
}
