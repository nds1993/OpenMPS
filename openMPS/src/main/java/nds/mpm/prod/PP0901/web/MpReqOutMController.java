package nds.mpm.prod.PP0901.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.prod.PP0901.service.MpReqOutMService;
import nds.mpm.prod.PP0901.vo.MpReqOutMDefaultVO;
import nds.mpm.prod.PP0901.vo.MpReqOutMVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :  MpBarProMController
 *
 * @author MPM TEAM
 * @since 2017.08.24
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 부자재출고처리 Batch ( PP0901 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.25	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0901/mpreqoutm")
public class MpReqOutMController extends TMMBaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(MpReqOutMController.class);

    @Resource(name = "PP0901mpReqOutMService")
    private MpReqOutMService mpReqOutMService;
    
    /**
	 * 부자재 출고처리 리스트
	 */
    @RequestMapping(value="/{workDate}")
    public ResponseEntity<ResultEx> selectMpReqOutMList(		
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("workDate") String workDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	

       	String searchCondition = req.getParameter("searchCondition");
       	
       	searchCondition = "test";
    	
    	MpReqOutMVO searchVO = new MpReqOutMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setWorkDate(workDate);
    	searchVO.setSearchCondition(searchCondition);
       	

        List<?> mpPighisSaleMList = mpReqOutMService.selectBomCurDayWorkList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(mpPighisSaleMList.size());
    	pageSet.setResult(mpPighisSaleMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
   	 * 부자재 출고처리 배치처리 테스트 
   	 */
   @RequestMapping(value="/test/batch/{workDate}",method=RequestMethod.POST)
   public ResponseEntity<ResultEx> selectTESTBatchMpReqOutMList(		
   		@PathVariable("corpCode") String corpCode,
   		@PathVariable("workDate") String workDate,
   		HttpServletRequest req,
   		HttpServletResponse res,
   		ModelMap model)
           throws Exception {
   	
   	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
   	
   	MpReqOutMVO searchVO = new MpReqOutMVO();
   	searchVO.setCorpCode(corpCode);
   	
   	searchVO.setWorkDate(workDate);
   	searchVO.setSearchCondition("test");

   	result = mpReqOutMService.insertBatchJobMpReqOutM(searchVO);

   	return _filter.makeCORSEntity( result );
   }

}
