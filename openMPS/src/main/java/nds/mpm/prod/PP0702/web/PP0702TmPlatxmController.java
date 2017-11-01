package nds.mpm.prod.PP0702.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.prod.PP0101.vo.TmPlatxmVO;
import nds.mpm.prod.PP0702.service.PP0702TmPlatxmService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;


/**
 * @Class Name :  TmPlatxmController
 *
 * @author MPM TEAM
 * @since 2017.07.31
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 공장등록
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.07.31	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/pp0702/tmplatxm")
public class PP0702TmPlatxmController extends TMMBaseController{
	
	private static final Logger _logger = LoggerFactory.getLogger(PP0702TmPlatxmController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "PP0702tmPlatxmService")
    private PP0702TmPlatxmService tmPlatxmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_platxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TmPlatxmDefaultVO
	 * @return "/tmPlatxm/TmPlatxmList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{grupPlant}/{proCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTmPlatxmList(
    		@PathVariable("grupPlant") String grupPlant,
    		@PathVariable("proCode") String proCode,    		
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
//    	String grupPlant = req.getParameter("grupPlant");
//    	String proCode = req.getParameter("proCode");
    	
    	TmPlatxmVO searchVO = new TmPlatxmVO();
    	searchVO.setGrupPlant(grupPlant);
    	searchVO.setProCode(proCode);
    	searchVO.setCorpCode(corpCode);
    	
    	if("0".equals(grupPlant)){
	       		searchVO.setGrupPlant("");
       	}else{
       		searchVO.setGrupPlant(grupPlant);
       	}
    	
    	 List<?> tmPlatxmList = tmPlatxmService.selectTmPlatxmList(searchVO);
        
    	PageSet pageSet = new PageSet();
        
    	int totCnt = tmPlatxmService.selectTmPlatxmListTotCnt(searchVO);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(tmPlatxmList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 

}
