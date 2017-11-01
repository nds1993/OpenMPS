package nds.mpm.prod.PP0803.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.common.util.ExcelUtil;
import nds.mpm.common.util.FileUtil;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.excel.PP;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.prod.PP0801.service.OpenapiPighisService;
import nds.mpm.prod.PP0801.vo.OpenapiPighisResultVO;
import nds.mpm.prod.PP0802.vo.MpPighisPackMVO;
import nds.mpm.prod.PP0803.service.MpPighisSaleMService;
import nds.mpm.prod.PP0803.vo.MpPighisSaleMDefaultVO;
import nds.mpm.prod.PP0803.vo.MpPighisSaleMVO;
import nds.mpm.prod.PP0803.vo.MpPighisSaleVO;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  MpBarProMController
 *
 * @author MPM TEAM
 * @since 2017.08.24
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 거래내역실적신고 ( PP0803 )
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
@RequestMapping("/mpm/{corpCode}/pp0803/mppighissalem")
public class MpPighisSaleMController  extends TMMBaseController{
	
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPighisSaleMController.class);

    @Resource(name = "mpPighisSaleMService")
    private MpPighisSaleMService mpPighisSaleMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "openapiPigService")
	private OpenapiPighisService openapiPigService;
    
    private final String FILENAME_PREFIX = "PTS_IN_";
	
    /**
	 * mp_pighis_sale_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPighisSaleMDefaultVO
	 * @return "/mpPighisSaleM/MpPighisSaleMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{saleDate}/{sendType}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> mpPighisSaleMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("saleDate") String saleDate,
    		@PathVariable("sendType") String sendType,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpPighisSaleMVO searchVO = new MpPighisSaleMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSaleDate(StringUtils.remove(saleDate,"-"));
    	
        List<?> mpPighisSaleMList = mpPighisSaleMService.selectMpPighisSaleMList(searchVO);
        
        if("nsend".equals(sendType))
        {
        	//신고대상조회
        	mpPighisSaleMList = mpPighisSaleMService.selectOdOderhdList(searchVO);
        }
        else
        {
        	mpPighisSaleMList = mpPighisSaleMService.selectMpPighisSaleMList(searchVO);
        }

    	PageSet pageSet = new PageSet();
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	if(mpPighisSaleMList != null)
    	pageSet.setTotalRecordCount(mpPighisSaleMList.size());
    	pageSet.setResult(mpPighisSaleMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{saleDate}/{sendType}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> mpExPighisSaleMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("saleDate") String saleDate,
    		@PathVariable("sendType") String sendType,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpPighisSaleMVO searchVO = new MpPighisSaleMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSaleDate(StringUtils.remove(saleDate,"-"));
    	
        List<EgovMap> mpPighisSaleMList = mpPighisSaleMService.selectMpPighisSaleMList(searchVO);
        
        if("nsend".equals(sendType))
        {
        	//신고대상조회
        	mpPighisSaleMList = mpPighisSaleMService.selectOdOderhdList(searchVO);
        }
        else
        {
        	mpPighisSaleMList = mpPighisSaleMService.selectMpPighisSaleMList(searchVO);
        }

        String[] columns = PP.PP0803;
        String sheetName = PP.PP0803_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpPighisSaleMList, columns, PP.PP0803_TYPE, sheetName);
    	
    	return null;
    }
    
    @RequestMapping(value="/sales/{saleDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> mpPighisSaleMListProd(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("saleDate") String saleDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpPighisSaleMVO searchVO = new MpPighisSaleMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSaleDate(StringUtils.remove(saleDate,"-"));
    	
        List<?> mpPighisSaleMList = mpPighisSaleMService.selectMpPighisSaleMProcodeList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpPighisSaleMList != null)
    		pageSet.setTotalRecordCount(mpPighisSaleMList.size());
    	pageSet.setResult(mpPighisSaleMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/salestot/{saleDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPighisSaleSum(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("saleDate") String saleDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpPighisSaleMVO searchVO = new MpPighisSaleMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSaleDate(StringUtils.remove(saleDate,"-"));
    	
        int mpPighisSaleSum = mpPighisSaleMService.selectMpPighisSaleSum(searchVO);

    	result.setExtraData(mpPighisSaleSum);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
     * data save
     * @param 	packDate 
	 * 			
     * */
    @RequestMapping(value="/{saleDate}/{sendType}/save",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPighisBuyM(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("saleDate") String saleDate,
            @RequestBody List<EgovMap> reqmpPlanCmHVOs,
            MpPighisSaleMVO mpPighisSaleMVO,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	String forceD = req.getParameter("forceD");
    	
    	mpPighisSaleMVO.setCorpCode(corpCode);
    	mpPighisSaleMVO.setSaleDate(StringUtils.remove(saleDate,"-"));
    	List ilist = new ArrayList();
    	if(reqmpPlanCmHVOs != null)    		 
    	{	
    		
    		/**
    		 * 매입일자+도축장코드 중복 존재하면 -4 반환
    		 * 화면에서 forceD=Y 파라메터 전달시 buy_date+buy_type data 강제삭제.
    		 * forceD != Y -> 중복체크
    		 * 
    		 * */
    		if("Y".equals(forceD))
    		{
    			if(mpPighisSaleMService.deleteMpPighisSaleM(mpPighisSaleMVO) == 0)
    			{
    				result.setResultCode(Consts.ResultCode.RC_ERROR.getCode());
	    			result.setMsg("duplicated data delete fail!!");
	    			return _filter.makeCORSEntity( result );
    			}
    		}
    		else
    		{
    			if( mpPighisSaleMService.checkDupSaleDate(mpPighisSaleMVO) > 0)
	    		{
	    			result.setResultCode(Consts.ResultCode.RC_DUPLICATE.getCode());
	    			return _filter.makeCORSEntity( result );
	    		}
    		}
    		
    		for(EgovMap vo : reqmpPlanCmHVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("saleDate",StringUtils.remove(saleDate,"-"));
    			if(sess != null)
    			{
    				vo.put("mdUser",sess.getUser().getId());
    				vo.put("crUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	result = mpPighisSaleMService.insertMpPighisSaleM(ilist);
    	
        return _filter.makeCORSEntity( result );
    }
    
    /**
	 * mp_pighis_sale_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPighisSaleMDefaultVO
	 * @return "/mpPighisSaleM/MpPighisSaleMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{saleDate}/{sendType}/download",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPighisSaleMSendFormatList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("saleDate") String saleDate,
    		@PathVariable("sendType") String sendType,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpPighisSaleMVO searchVO = new MpPighisSaleMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSaleDate(StringUtils.remove(saleDate,"-"));
    	
        List<EgovMap> mpPighisSaleMList = mpPighisSaleMService.selectMpPighisSaleMSendFormatList(searchVO);

        MPUserSession sess = getSession(req);
    	
    	String userPath = sess.getUser().getId();
    	String filename = FILENAME_PREFIX + saleDate + ".txt";
    	
    	String contextroot = jsession.getServletContext().getRealPath("/");
    	String uploadPath = propertiesService.getString("file.upload.path")  + "/" + userPath;

    	//if(sess.getUser() == null)
    	//	return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_DENINED ) );

    	//String userPath = sess.getUser().getId();
    	
    	/*
    	 * validate request type
    	 */
    	String filePath = uploadPath;
    	
        /**
        // BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
        // 파일안에 문자열 쓰기
         * 신고 API 파일전송 포맷 
         * Header(T1) + Detail(T2)
         * 
        P1|20170802|묶음||L11707253338001||1358528825|롯데쇼핑(주)롯데슈퍼이천|이철우||()   |판매장|416001|73.9
		P2|20170802|묶음||L11707253338001||430468|목심|73.9|
		P3|20170802|묶음||L11707253338001
		P1|20170802|묶음||L11707263338011||1358528825|롯데쇼핑(주)롯데슈퍼이천|이철우||()   |판매장|416001|93.8
         
        title , sale_date, his_bunch_no, cust_regno, cust_name, cust_owner, cust_phone
		, cust_addr, sale_weig
		, base_part_code, base_part_name
         **/
    	
 		StringBuffer creatFileCont = new StringBuffer();
 		
 		if(mpPighisSaleMList != null && mpPighisSaleMList.size() > 0)
 		{
 			for(EgovMap vo : mpPighisSaleMList)
 	 		{
 				if("P1".equals((String)vo.get("title")))
 	 			{
 	 				creatFileCont.append( "P1"+"|" + vo.get("saleDate"));
 	 	 			creatFileCont.append( "|묶음||" + vo.get("hisBunchNo") );
 	 	 			creatFileCont.append( "||" + vo.get("custRegno") );
 	 	 			creatFileCont.append( "|" + vo.get("custName") );
 	 	 			creatFileCont.append( "|" + vo.get("custOwner") );
 	 	 			creatFileCont.append( "|" + vo.get("custPhone") );
 	 	 			creatFileCont.append( "|" + vo.get("custAddr") );
 	 	 			creatFileCont.append( "|" + "판매장" );
 	 	 			creatFileCont.append( "|" + "416001" );
 	 	 			creatFileCont.append( "|" + vo.get("saleWeig") );
 	 	 			
 	 	 			creatFileCont.append( "\n" );
 	 			}
 	 			else if("P2".equals((String)vo.get("title")))
 	 			{
 	 				creatFileCont.append( "P2"+"|" + vo.get("saleDate"));
 	 	 			creatFileCont.append( "|묶음||" + vo.get("hisBunchNo") );
 	 	 			creatFileCont.append( "||" + vo.get("basePartCode") );
 	 	 			creatFileCont.append( "|" + vo.get("basePartName") );
 	 	 			creatFileCont.append( "|" + vo.get("saleWeig") );
 	 	 			
 	 	 			creatFileCont.append( "\n" );
 	 			}
 	 			else if("P3".equals((String)vo.get("title")))
 	 			{
 	 				creatFileCont.append( "P3"+"|" + vo.get("saleDate"));
 	 	 			creatFileCont.append( "|묶음||" + vo.get("hisBunchNo") );
 	 	 			
 	 	 			creatFileCont.append( "\n" );
 	 			}
 	 		}
 	 		
 	 		String txtFileFull = contextroot + uploadPath + "/" + filename ;
 	 		
 	 		BufferedWriter fw = new BufferedWriter(new FileWriter(new File(txtFileFull), false));
 	 		  
 	 		fw.write(creatFileCont.toString());
 	 		fw.flush();
 	 		
 	 		LOGGER.debug("file create start :: " + filename);
 	         
 	    	FileUtil.downloadFile(contextroot + uploadPath, filename, res);
 		}
 		else
 		{
 			result = new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
 		}
 		return _filter.makeCORSEntity( result );
    }
    
    /***
     * 
     * 거래실적 OPEN API 신고 호출 
     * 
     * */
    @RequestMapping(value="/openapi/send/{saleDate}/{sendType}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> sendMpPighisBuyMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("saleDate") String saleDate,
    		@PathVariable("sendType") String sendType,
    		MpPighisSaleMVO mpPighisSaleMVO,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	//신고하지않은 건만
    	mpPighisSaleMVO.setCorpCode(corpCode);
    	mpPighisSaleMVO.setSearchCondition("nsend");
    	mpPighisSaleMVO.setSaleDate(StringUtils.remove(saleDate,"-"));
    	
    	List<EgovMap> mpPighisBuyVOs = mpPighisSaleMService.selectMpPighisSaleMList(mpPighisSaleMVO);
    	List<MpPighisSaleVO> sendFormVOs = new ArrayList();
    	
    	for(EgovMap vo : mpPighisBuyVOs)
    	{
    		MpPighisSaleVO svo = new MpPighisSaleVO();
    		
    		svo.setSale_date((String)vo.get("saleDate"));
    		svo.setHis_bunch_no((String)vo.get("hisBunchNo"));
    		svo.setGagong_no((String)vo.get("gagongNo"));
    		svo.setBase_part_code((String)vo.get("basePartCode"));
    		svo.setBase_part_name((String)vo.get("basePartName"));
    		svo.setSale_weig(Double.parseDouble(StringUtils.defaultIfEmpty(vo.get("saleWeig")+"","0")));
    		svo.setCust_name((String)vo.get("custName"));
    		svo.setCust_regno((String)vo.get("custRegno"));
    		svo.setCust_owner((String)vo.get("custOwner"));
    		svo.setCust_phone((String)vo.get("custPhone"));
    		
    		sendFormVOs.add(svo);
    	}
    	
    	//신고할 건 없음.
    	if(sendFormVOs.size() == 0)
    	{
    		return _filter.makeCORSEntity(new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND ) );
    	}
    	
    	LOGGER.debug("opapi data :: " + new ObjectMapper().writeValueAsString(sendFormVOs));

    	//List<OpenapiPighisResultVO> resultVo = openapiPigService.declarePighisSale(sendFormVOs);
    	List<OpenapiPighisResultVO> resultVo = null;
    	
    	if(resultVo != null && resultVo.size() > 0)
    	{
    		OpenapiPighisResultVO firstRow = resultVo.get(0);
    		if("Y".equals(firstRow.getCheckYn()))
			{
    			
    			for(EgovMap vo : mpPighisBuyVOs)
    	    	{
    				vo.put("mdUser", sess.getUser().getId());
    				vo.put("memo", "success");
    				vo.put("searchCondition", "success");
    				mpPighisSaleMService.updateApiTimeMpPighisSaleM(vo);
    	    	}
    			
    			result.setMsg(firstRow.getCheckMsg());
    			return _filter.makeCORSEntity( result );
			}
    		else
    		{
    			String memo = firstRow.getCheckMsg();
				
				if(memo != null)
				{
					//db column length limit
					memo = memo.substring(0, 100);
				}
    			for(EgovMap vo : mpPighisBuyVOs)
    	    	{
    				vo.put("memo", memo);
    				mpPighisSaleMService.updateApiTimeMpPighisSaleM(vo);
    	    	}
    			
    			result.setMsg(memo);
    			result.setExtraData(resultVo);
    			result.setResultCode(Consts.ResultCode.RC_ERROR.getCode());
    		}
    	}
    	else
    	{
    		
    		String memo = "거래내역실적신고openapi 응답메시지없음. 오류";
    		for(EgovMap vo : mpPighisBuyVOs)
	    	{
				vo.put("memo", memo);
				mpPighisSaleMService.updateApiTimeMpPighisSaleM(vo);
	    	}
    		
    		result.setExtraData(memo);
			result.setResultCode(Consts.ResultCode.RC_ERROR.getCode());
    	}
    	
    	result.setExtraData(resultVo);
    	
    	return _filter.makeCORSEntity( result );
    }
    /***
     * 
     * 거래실적 OPEN API 신고 호출 테스트 
     * 
     * */
    @RequestMapping(value="/test/{saleDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPighisBuyMList(
    		MpPighisSaleMVO mpPighisSaleMVO,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	mpPighisSaleMVO.setCorpCode("1001");
    	mpPighisSaleMVO.setSearchCondition("nsend");
    	mpPighisSaleMVO.setSaleDate("20170810");
    	mpPighisSaleMVO.setCustCode("17759-000");
    	mpPighisSaleMVO.setSearchCondition2("test");
    	
    	List<EgovMap> mpPighisBuyVOs = mpPighisSaleMService.selectMpPighisSaleMList(mpPighisSaleMVO);
    	List<MpPighisSaleVO> sendFormVOs = new ArrayList();
    	
    	for(EgovMap vo : mpPighisBuyVOs)
    	{
    		MpPighisSaleVO svo = new MpPighisSaleVO();
    		
    		svo.setSale_date((String)vo.get("saleDate"));
    		svo.setHis_bunch_no((String)vo.get("hisBunchNo"));
    		svo.setGagong_no((String)vo.get("gagongNo"));
    		svo.setBase_part_code((String)vo.get("basePartCode"));
    		svo.setBase_part_name((String)vo.get("basePartName"));
    		svo.setSale_weig(Double.parseDouble(StringUtils.defaultIfEmpty(vo.get("saleWeig")+"","0")));
    		svo.setCust_name((String)vo.get("custName"));
    		svo.setCust_regno((String)vo.get("custRegno"));
    		svo.setCust_owner((String)vo.get("custOwner"));
    		svo.setCust_phone((String)vo.get("custPhone"));
    		sendFormVOs.add(svo);
    	}
    	/**
    	 * ONLY TEST
    	 * 
		List<MpPighisBuyVO> list = (ArrayList<MpPighisBuyVO>)openapiPigService.getPighisBuy();*/
		/**
		 * */
    	openapiPigService.declarePighisSale(sendFormVOs);
    	return null;
    }
    
    /***
    @RequestMapping("/mpPighisSaleM/addMpPighisSaleMView.do")
    public String addMpPighisSaleMView(
            @ModelAttribute("searchVO") MpPighisSaleMDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpPighisSaleMVO", new MpPighisSaleMVO());
        return "/mpPighisSaleM/MpPighisSaleMRegister";
    }
    
    @RequestMapping("/mpPighisSaleM/addMpPighisSaleM.do")
    public String addMpPighisSaleM(
            MpPighisSaleMVO mpPighisSaleMVO,
            @ModelAttribute("searchVO") MpPighisSaleMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPighisSaleMService.insertMpPighisSaleM(mpPighisSaleMVO);
        status.setComplete();
        return "forward:/mpPighisSaleM/MpPighisSaleMList.do";
    }
    
    @RequestMapping("/mpPighisSaleM/updateMpPighisSaleMView.do")
    public String updateMpPighisSaleMView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @ModelAttribute("searchVO") MpPighisSaleMDefaultVO searchVO, Model model)
            throws Exception {
        MpPighisSaleMVO mpPighisSaleMVO = new MpPighisSaleMVO();
        mpPighisSaleMVO.setCorpCode(corpCode);        
        // 변수명은 CoC 에 따라 mpPighisSaleMVO
        model.addAttribute(selectMpPighisSaleM(mpPighisSaleMVO, searchVO));
        return "/mpPighisSaleM/MpPighisSaleMRegister";
    }

    @RequestMapping("/mpPighisSaleM/selectMpPighisSaleM.do")
    public @ModelAttribute("mpPighisSaleMVO")
    MpPighisSaleMVO selectMpPighisSaleM(
            MpPighisSaleMVO mpPighisSaleMVO,
            @ModelAttribute("searchVO") MpPighisSaleMDefaultVO searchVO) throws Exception {
        return mpPighisSaleMService.selectMpPighisSaleM(mpPighisSaleMVO);
    }

    @RequestMapping("/mpPighisSaleM/updateMpPighisSaleM.do")
    public String updateMpPighisSaleM(
            MpPighisSaleMVO mpPighisSaleMVO,
            @ModelAttribute("searchVO") MpPighisSaleMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPighisSaleMService.updateMpPighisSaleM(mpPighisSaleMVO);
        status.setComplete();
        return "forward:/mpPighisSaleM/MpPighisSaleMList.do";
    }
    
    @RequestMapping("/mpPighisSaleM/deleteMpPighisSaleM.do")
    public String deleteMpPighisSaleM(
            MpPighisSaleMVO mpPighisSaleMVO,
            @ModelAttribute("searchVO") MpPighisSaleMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPighisSaleMService.deleteMpPighisSaleM(mpPighisSaleMVO);
        status.setComplete();
        return "forward:/mpPighisSaleM/MpPighisSaleMList.do";
    }
	*/
}
