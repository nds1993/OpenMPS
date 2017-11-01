package nds.mpm.buy.PO0204.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.buy.PO0102.vo.MpPigdxmVO;
import nds.mpm.buy.PO0204.service.PO0204MpPigdxmService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;

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
 * @Class Name :  MpPigdxmController
 *
 * @author MPM TEAM
 * @since 2017.08.31
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 생돈지급율 조회( PO0204 )
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
@RequestMapping("/mpm/{corpCode}/po0204/mppigdxm")
public class PO0204MpPigdxmController extends TMMBaseController {

    @Resource(name = "PO0204mpPigdxmService")
    private PO0204MpPigdxmService mpPigdxmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_pigdxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPigdxmDefaultVO
	 * @return "/mpPigdxm/MpPigdxmList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{custCode}/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPigdxmList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("custCode") String custCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String facKind = req.getParameter("facKind");
    	String buyType = req.getParameter("buyType");
    	String brandCode = req.getParameter("brandCode");
    	
    	MpPigdxmVO searchVO = new MpPigdxmVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setFacKind(facKind);
    	searchVO.setBuyType(buyType);
    	searchVO.setBrandCode(brandCode);
    	searchVO.setCustCode(custCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setLastDate(lastDate);
    	
    	
        List<?> mpPigdxmList = mpPigdxmService.selectMpPigdxmList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpPigdxmService.selectMpPigdxmListTotCnt(searchVO);
    	
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpPigdxmList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{custCode}/{strtDate}/{lastDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPigixm(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("custCode") String custCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
            @RequestBody List<EgovMap> mpPigixmVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(mpPigixmVOs != null)
    	{
    		for(EgovMap vo : mpPigixmVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("custCode",custCode);
    			if(sess != null)
    			{
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
        result = mpPigdxmService.deleteMpPigdxm(ilist);
        return _filter.makeCORSEntity( result );
    }
}
