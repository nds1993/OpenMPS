package nds.mpm.prod.PP0302.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.common.util.ExcelUtil;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.excel.PP;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.prod.PP0302.service.MpPlanCmHService;
import nds.mpm.prod.PP0302.vo.MpPlanCmHDefaultVO;
import nds.mpm.prod.PP0302.vo.MpPlanCmHVO;
import nds.mpm.prod.PP0302.vo.MultiMpPlanCmHVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  MpPlanCmHController
 *
 * @author MPM TEAM
 * @since 2017.08.01
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 생산계획입력 CM ( PP0302 )
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
@RequestMapping("/mpm/{corpCode}/pp0302/mpplancmh")
public class MpPlanCmHController extends TMMBaseController{

    @Resource(name = "mpPlanCmHService")
    private MpPlanCmHService mpPlanCmHService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
     * PP0302 신규버튼클릭시 
     * 이미저장된 계획이 있으면 에러 리턴 -4
	 * mp_plan_cm_h 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPlanCmHDefaultVO
	 * @return "/mpPlanCmH/MpPlanCmHList"
	 * @exception Exception
	 */
    @RequestMapping(value="/new/{plantCode}/{workDate}")
    public ResponseEntity<ResultEx> selectNewMpPlanCmHList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
        
    	MpPlanCmHVO searchVO = new MpPlanCmHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	searchVO.setCrUser(sess.getUser().getId());
    	
    	if(!"Y".equals(mpPlanCmHService.selectMpPlanCmHCanNewCmPlan(searchVO)))
        {
        	return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_DUPLICATE ) );
        }
    	
    	mpPlanCmHService.insertNewMpPlanCmH(searchVO);
    	
        List<?> mpPlanCmHList = mpPlanCmHService.selectMpPlanCmHList(searchVO);
        
        /**
         * 10/8 
         * 신규버튼 조회시 데이터 저장처리(해당일 전체공장 생성) 설계내용 반영으로 
         * 변경. 
         * */
        //List<?> mpPlanCmHList = mpPlanCmHService.selectNewMpPlanCmHList(searchVO);
        
    	PageSet pageSet = new PageSet();
        
    	if(mpPlanCmHList != null)
    		pageSet.setTotalRecordCount(mpPlanCmHList.size());
    	pageSet.setResult(mpPlanCmHList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    /**
     * PP0302 제품추가시 계획정보에 셋팅될 추가 제품정보  
	 * @exception Exception
	 */
    @RequestMapping(value="/newprod/{plantCode}/{workDate}/{proCode}")
    public ResponseEntity<ResultEx> selectNewProdMpPlanCmHList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
    		@PathVariable("proCode") String proCode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
        
    	MpPlanCmHVO searchVO = new MpPlanCmHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	searchVO.setPlantCode(plantCode);
    	searchVO.setProCode(proCode);
    	
        EgovMap newProdMap = mpPlanCmHService.selectNewMpPlanCmHProCode(searchVO);
        
    	result.setExtraData(newProdMap);
    	
    	return _filter.makeCORSEntity( result );
    } 
    /**
     * 생산계획조회
     * 
     * */
    @RequestMapping(value="/search/{plantCode}/{workDate}")
    public ResponseEntity<ResultEx> selectMpPlanCmHList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpPlanCmHVO searchVO = new MpPlanCmHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	searchVO.setPlantCode(plantCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpPlanCmHList = mpPlanCmHService.selectMpPlanCmHList(searchVO);

    	PageSet pageSet = new PageSet();
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	if(mpPlanCmHList!=null)
    	pageSet.setTotalRecordCount(mpPlanCmHList.size());
    	pageSet.setResult(mpPlanCmHList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    /**
     * 엑셀버튼 
     * 
     * */
    @RequestMapping(value="/search/{plantCode}/{workDate}/export")
    public ResponseEntity<ResultEx> selectExMpPlanCmHList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
    	MpPlanCmHVO searchVO = new MpPlanCmHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	searchVO.setPlantCode(plantCode);
    	
        List<EgovMap> mpPlanCmHList = mpPlanCmHService.selectMpPlanCmHList(searchVO);

    	String[] columns = PP.PP03021;
        String sheetName = PP.PP03021_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpPlanCmHList, columns, PP.PP03021_TYPE, sheetName);
		
    	
    	return null;
    }
    /***
     * 생산계획합계탭
     * 공장별 합계 집계수 
     * */
    @RequestMapping(value="/total/{workDate}")
    public ResponseEntity<ResultEx> selectMpPlanCmHTotal(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("workDate") String workDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
    	MpPlanCmHVO searchVO = new MpPlanCmHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	
    	result.setExtraData(mpPlanCmHService.selectNewMpPlanCmHSum(searchVO));
    	
    	return _filter.makeCORSEntity( result );
    } 
    /***
     * 생산계획합계리스
     * */
    @RequestMapping(value="/totlist/{workDate}")
    public ResponseEntity<ResultEx> selectMpPlanCmHTotalList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("workDate") String workDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpPlanCmHVO searchVO = new MpPlanCmHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpPlanCmHList = mpPlanCmHService.selectNewMpPlanCmHSumList(searchVO);

    	PageSet pageSet = new PageSet();
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	if(mpPlanCmHList!=null)
    		pageSet.setTotalRecordCount(mpPlanCmHList.size());
    	pageSet.setResult(mpPlanCmHList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /***
     * 합계탭 확정시간 
     * */
    @RequestMapping(value="/complist/{workDate}")
    public ResponseEntity<ResultEx> selectCompMpPlanCmHTotalList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("workDate") String workDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
    	MpPlanCmHVO searchVO = new MpPlanCmHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	
        EgovMap mpPlanCmHList = mpPlanCmHService.selectCompTimeMpPlanCmH(searchVO);

    	result.setExtraData(mpPlanCmHList);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /***
     * 생산계획합계리스트 엑셀
     * */
    @RequestMapping(value="/totlist/{workDate}/export")
    public ResponseEntity<ResultEx> selectExMpPlanCmHTotalList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("workDate") String workDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
    	MpPlanCmHVO searchVO = new MpPlanCmHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	
        List<EgovMap> mpPlanCmHList = mpPlanCmHService.selectNewMpPlanCmHSumList(searchVO);

    	String[] columns = PP.PP03021;
        String sheetName = PP.PP03021_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpPlanCmHList, columns, PP.PP03021_TYPE, sheetName);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/new/{plantCode}/{workDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addNewMpPlanCmH(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
            @RequestBody MultiMpPlanCmHVO multiMpPlanCmHVO,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	if(multiMpPlanCmHVO != null)
    	{
    		MpPlanCmHVO head = multiMpPlanCmHVO.getHead();
    		head.setCorpCode(corpCode);
    		head.setPlantCode(plantCode);
    		head.setWorkDate(StringUtils.remove(workDate, "-"));
    		head.setCrUser(sess.getUser().getId());
    		
    		List<EgovMap> mpPlanCmHVOs = multiMpPlanCmHVO.getDetail();
    		
    		if( mpPlanCmHVOs != null && mpPlanCmHVOs.size() > 0)
    		{
    			for(EgovMap vo : mpPlanCmHVOs)
            	{
        			vo.put("corpCode",corpCode);
        			vo.put("plantCode",plantCode);
        			vo.put("workDate",StringUtils.remove(workDate, "-"));
        			if(sess != null)
        			{
        				vo.put("crUser",sess.getUser().getId());
        				vo.put("mdUser",sess.getUser().getId());
        			}
            	}
    		}
    		
    		
    		mpPlanCmHService.insertMpPlanCmH(multiMpPlanCmHVO);
    	}
    	
    	
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/search/{plantCode}/{workDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPlanCmH(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
            @RequestBody MultiMpPlanCmHVO multiMpPlanCmHVO,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	return addNewMpPlanCmH(corpCode,plantCode,workDate,multiMpPlanCmHVO,req,res);
    }
    
    @RequestMapping(value="/remove/{plantCode}/{workDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPlanCmH(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	if(sess == null)
    	{
    		return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_DENINED ) );
    	}
    	if(sess.getUser().getId() == null)
    	{
    		return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_DENINED ) );
    	}
    	
    	EgovMap vo = new EgovMap();
    	vo.put("corpCode", corpCode);
    	//vo.put("plantCode", plantCode);
    	vo.put("workDate", StringUtils.remove(workDate,"-"));
    	vo.put("mdUser", sess.getUser().getId());
    	
    	mpPlanCmHService.deleteMpPlanWorkDate(vo);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/header/{plantCode}/{workDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPlanCmH(
    		@PathVariable("corpCode") String corpCode ,
    		@PathVariable("plantCode") String plantCode ,
    		@PathVariable("workDate") String workDate,
            MpPlanCmHVO mpPlanCmHVO,
    		HttpServletResponse res) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpPlanCmHVO searchVO = new MpPlanCmHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	
    	result.setExtraData(mpPlanCmHService.selectMpPlanCmH(searchVO));
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
     * 생산계획 신규저장 status 0
     * 		  -> 계획버튼 status 1
     * 		  -> 확정버튼 status3
     * 		  
     * 
     * status = "plan" or "complete"
     * TM_CODEXD PP003 0:접수 1:계획, 3:확정
     * 
     * */
    @RequestMapping(value="/status/{plantCode}/{workDate}/{status}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> updateMpPlanCmH(
    		@PathVariable("corpCode") String corpCode ,
    		@PathVariable("plantCode") String plantCode ,
    		@PathVariable("workDate") String workDate,
    		@PathVariable("status") String status,
            MpPlanCmHVO mpPlanCmHVO,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpPlanCmHVO searchVO = new MpPlanCmHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	
    	if( mpPlanCmHService.selectMpPlanCmH(searchVO)  == null)
    	{
    		return _filter.makeCORSEntity( new  ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND ));
    	}
    	
    	mpPlanCmHVO.setCorpCode(corpCode);
    	mpPlanCmHVO.setPlantCode(plantCode);
    	mpPlanCmHVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	
    	if("plan".equals(status))
    		mpPlanCmHVO.setStatus("1");
    	else if("complete".equals(status))
    		mpPlanCmHVO.setStatus("3");
    	else
    		return _filter.makeCORSEntity( new  ResultEx( Consts.ResultCode.RC_INVALID_PARAMS ));
    		
        if( mpPlanCmHService.updateMpPlanCmHStatus(mpPlanCmHVO) == 0 ){
        	return _filter.makeCORSEntity(new  ResultEx( Consts.ResultCode.RC_ERROR ));
        }
    	return _filter.makeCORSEntity( result );
    }
    
//    @RequestMapping("/mpPlanCmH/deleteMpPlanCmH.do")
//    public String deleteMpPlanCmH(
//            MpPlanCmHVO mpPlanCmHVO,
//            @ModelAttribute("searchVO") MpPlanCmHDefaultVO searchVO, SessionStatus status)
//            throws Exception {
//        mpPlanCmHService.deleteMpPlanCmH(mpPlanCmHVO);
//        status.setComplete();
//        return "forward:/mpPlanCmH/MpPlanCmHList.do";
//    }

}
