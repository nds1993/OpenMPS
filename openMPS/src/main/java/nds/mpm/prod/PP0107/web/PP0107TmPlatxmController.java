package nds.mpm.prod.PP0107.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.prod.PP0101.vo.TmPlatxmVO;
import nds.mpm.prod.PP0101.web.TmPlatxmController;
import nds.mpm.prod.PP0107.service.PP0107TmPlatxmService;


/**
 * @Class Name :  tmplatxm
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 공장 창고 매핑 조회
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
@RequestMapping("/mpm/{corpCode}/pp0107")
public class PP0107TmPlatxmController extends TMMBaseController{

	private static final Logger _logger = LoggerFactory.getLogger(TmPlatxmController.class);
	
	@Autowired
	protected CorsFilter		_filter;
	
    @Resource(name = "PP0107TmPlatxmService")
    private PP0107TmPlatxmService PP0107TmPlatxmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
     * left grid
     * 
     * */
    @RequestMapping(value="/tmplatxm",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTmPlatxmList(
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	String plantCode = req.getParameter("plantCode");
    	String useYn = req.getParameter("useYn");
    	
    	TmPlatxmVO searchVO = new TmPlatxmVO();
    	searchVO.setPlantCode(plantCode);
    	searchVO.setUseYn(useYn);
    	
    	 List<?> tmPlatxmList = PP0107TmPlatxmService.selectTmPlatxmLeftList(searchVO);
        
    	PageSet pageSet = new PageSet();
        
    	if(tmPlatxmList != null)
    		pageSet.setTotalRecordCount(tmPlatxmList.size());
    	pageSet.setResult(tmPlatxmList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    /**
	 * tm_platxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TmPlatxmDefaultVO
	 * @return "/tmPlatxm/TmPlatxmList"
	 * @exception Exception
	 */
    @RequestMapping(value="/tmplatwarhxm/{plantCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> PP0107selectTmPlatxmList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	
    	TmPlatxmVO searchVO = new TmPlatxmVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	
        List<?> tmPlatxmList = PP0107TmPlatxmService.selectTmPlatxmList(searchVO);
        
        PageSet pageSet = new PageSet();
        
        int totCnt = PP0107TmPlatxmService.selectTmPlatxmListTotCnt(searchVO);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(tmPlatxmList);
    	
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/tmplatwarhxm/{plantCode}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTmPlatxm(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
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
    			vo.put("plantCode",plantCode);

    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	result = PP0107TmPlatxmService.insertTmPlatWarhxm(ilist);
        return _filter.makeCORSEntity( result );
        
    }
    
   /* @RequestMapping("/tmPlatxm/addTmPlatxmView.do")
    public String addTmPlatxmView(
            @ModelAttribute("searchVO") TmPlatxmDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("tmPlatxmVO", new TmPlatxmVO());
        return "/tmPlatxm/TmPlatxmRegister";
    }
    
    @RequestMapping("/tmPlatxm/addTmPlatxm.do")
    public String addTmPlatxm(
            TmPlatxmVO tmPlatxmVO,
            @ModelAttribute("searchVO") TmPlatxmDefaultVO searchVO, SessionStatus status)
            throws Exception {
        tmPlatxmService.insertTmPlatxm(tmPlatxmVO);
        status.setComplete();
        return "forward:/tmPlatxm/TmPlatxmList.do";
    }
    
    @RequestMapping("/tmPlatxm/updateTmPlatxmView.do")
    public String updateTmPlatxmView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("plantCode") java.lang.String plantCode ,
            @ModelAttribute("searchVO") TmPlatxmDefaultVO searchVO, Model model)
            throws Exception {
        TmPlatxmVO tmPlatxmVO = new TmPlatxmVO();
        tmPlatxmVO.setCorpCode(corpCode);
        tmPlatxmVO.setPlantCode(plantCode);
        // 변수명은 CoC 에 따라 tmPlatxmVO
        model.addAttribute(selectTmPlatxm(tmPlatxmVO, searchVO));
        return "/tmPlatxm/TmPlatxmRegister";
    }

    @RequestMapping("/tmPlatxm/selectTmPlatxm.do")
    public @ModelAttribute("tmPlatxmVO")
    TmPlatxmVO selectTmPlatxm(
            TmPlatxmVO tmPlatxmVO,
            @ModelAttribute("searchVO") TmPlatxmDefaultVO searchVO) throws Exception {
        return tmPlatxmService.selectTmPlatxm(tmPlatxmVO);
    }

    @RequestMapping("/tmPlatxm/updateTmPlatxm.do")
    public String updateTmPlatxm(
            TmPlatxmVO tmPlatxmVO,
            @ModelAttribute("searchVO") TmPlatxmDefaultVO searchVO, SessionStatus status)
            throws Exception {
        tmPlatxmService.updateTmPlatxm(tmPlatxmVO);
        status.setComplete();
        return "forward:/tmPlatxm/TmPlatxmList.do";
    }
    
    @RequestMapping("/tmPlatxm/deleteTmPlatxm.do")
    public String deleteTmPlatxm(
            TmPlatxmVO tmPlatxmVO,
            @ModelAttribute("searchVO") TmPlatxmDefaultVO searchVO, SessionStatus status)
            throws Exception {
        tmPlatxmService.deleteTmPlatxm(tmPlatxmVO);
        status.setComplete();
        return "forward:/tmPlatxm/TmPlatxmList.do";
    }*/

}
