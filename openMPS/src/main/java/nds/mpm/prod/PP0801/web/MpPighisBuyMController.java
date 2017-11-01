package nds.mpm.prod.PP0801.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.common.util.ExcelUtil;
import nds.mpm.common.util.FileUtil;
import nds.mpm.common.vo.CommonFileVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.vo.UploadFileVO;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.excel.PP;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.prod.PP0801.service.MpPighisBuyMService;
import nds.mpm.prod.PP0801.service.OpenapiPighisService;
import nds.mpm.prod.PP0801.vo.MpPighisBuyMVO;
import nds.mpm.prod.PP0801.vo.MpPighisBuyVO;
import nds.mpm.prod.PP0801.vo.OpenapiPighisResultVO;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  MpPighisBuyMController
 *
 * @author MPM TEAM
 * @since 2017.08.25
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 매입실적신고( PP0801 )
 * excel 축산물평가원 돼지도체등급판정결과
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
@RequestMapping("/mpm/{corpCode}/pp0801/mppighisbuym")
public class MpPighisBuyMController extends TMMBaseController{
	
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPighisBuyMController.class);

    @Resource(name = "mpPighisBuyMService")
    private MpPighisBuyMService mpPighisBuyMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "XSSFexcelService") 
    private EgovExcelService excelService;
    
    @Resource(name = "openapiPigService")
	private OpenapiPighisService openapiPigService;
    
    private final String FILENAME_PREFIX = "PTS_IN_";
    
    /***
     * 작업장 1
     * 월 2
     * 일 3
     * 도체번호 4
     * 판정방법 5
     * 도체형태 6 
     * 성별 7
     * 도체중량 8
     * 등지방두께 9
     * 1차등급 12
     * 최종등급 25
     * 출하농가 26
     * 이력번호 27
     */
    private static final int EXCEL_READ_STARTROW = 3;
    private static final int[] EXCEL_EXPORT_CELLNUMS = {1,2,3,4,5,6,7,8,9,12,24,25,26};
    private static final String[] EXCEL_EXPORT_COL_NAMES = {"buyTypename","buyDateM","buyDateD","dochNo","gitaPan","gitaType","gitaSex","gitaWeig","gitaFatThick","gradeGubun1","gradeGubun2","custName","hisNo"};
 	
    /**
     * 
	 * mp_pighis_buy_m 목록을 조회한다. (pageing)
	 * @param 	buyDate 매입일자
	 * 			buyType 도축장코드
	 * @return "/mpPighisBuyM/MpPighisBuyMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{buyDate}/{buyType}")
    public ResponseEntity<ResultEx> selectMpPighisBuyMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("buyDate") String buyDate,
    		@PathVariable("buyType") String buyType,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpPighisBuyMVO searchVO = new MpPighisBuyMVO();
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setBuyDate(StringUtils.remove(buyDate,"-"));
    	searchVO.setBuyType(buyType);
    	
        List<?> mpPighisBuyMList = mpPighisBuyMService.selectMpPighisBuyMList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpPighisBuyMService.selectMpPighisBuyMListTotCnt(searchVO);
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpPighisBuyMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{buyDate}/{buyType}/export")
    public ResponseEntity<ResultEx> selectExMpPighisBuyMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("buyDate") String buyDate,
    		@PathVariable("buyType") String buyType,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpPighisBuyMVO searchVO = new MpPighisBuyMVO();
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setBuyDate(StringUtils.remove(buyDate,"-"));
    	searchVO.setBuyType(buyType);
    	
        List<EgovMap> mpPighisBuyMList = mpPighisBuyMService.selectMpPighisBuyMList(searchVO);

        String[] columns = PP.PP0801;
        String sheetName = PP.PP0801_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpPighisBuyMList, columns, PP.PP0801_TYPE, sheetName);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /***
     * 등급판정 excel load, not save
	 *	
     * */
    @RequestMapping(value="/{buyDate}/{buyType}/upload",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> exceladdOrderInfo(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("buyDate") String buyDate,
    		@PathVariable("buyType") String buyType,
    		UploadFileVO uploadFile,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	/**/
    	MPUserSession sess = getSession(req);
    	
    	if(sess.getUser() == null)
    		return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_DENINED ) );

    	String userPath = sess.getUser().getId();
    	
    	//String userPath = "handon";
    	/*
    	 * validate request type
    	 */
    	String contextroot = jsession.getServletContext().getRealPath("/");
    	MultipartFile file = uploadFile.getFile();

    	String uploadPath = propertiesService.getString("file.upload.path")  + "/" + userPath;
			
    	CommonFileVO ufile = uploadAndFileInfo(file, contextroot + uploadPath);
    	
    	if(ufile.getFullpath() == null)
    	{
    		result.setResultCode(-201);
    		return _filter.makeCORSEntity( result );
    	}
    	
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(ufile.getFullpath());
		System.out.println(ufile.getMimeType());
		System.out.println(ufile.getNewName());
		System.out.println(ufile.getOrginalName());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		log.debug("ReadExcelFile start...." + new Date()); 
		 
        Workbook wbT = null;
        if(ufile.getOrginalName() != null && ufile.getOrginalName().indexOf(".xlsx") > 0)
        	wbT = excelService.loadWorkbook(ufile.getFullpath(), new XSSFWorkbook());
        else if(ufile.getOrginalName() != null && ufile.getOrginalName().indexOf(".xls") > 0)
            wbT = excelService.loadWorkbook(ufile.getFullpath());
               
        Sheet sheetT = wbT.getSheetAt(0);
        
        List<EgovMap> exportList = new ArrayList();
        
        int excelReadStartRow = 0;
        /**
         * 데이터가 시작되는 행을 검색.
         * 파일마다 시작 행이 다를수 있음.
         * 
         * */
        for (int row = EXCEL_READ_STARTROW; row <= sheetT.getLastRowNum(); row++) {
        
        	Row row1 = sheetT.getRow(row);
        	Cell firstCell = row1.getCell(0);
	    	if(firstCell != null && firstCell.getCellType() == Cell.CELL_TYPE_STRING)
	    	{
	    		String checkString = firstCell.getStringCellValue();
	    		LOGGER.debug("startRowChkCell STRING :: " + checkString);
	    		
	    		if("1".equals(checkString))
	    		{
	    			excelReadStartRow = row;
	    			break;
	    		}
	    	}
    	
        }
        
        if(excelReadStartRow == 0)
        {
        	/**
        	 * excel format 오류 
        	 * 읽을수있는 파일이 아니거나 정해진 포맷이 아님.
        	 * */
        	result.setResultCode(Consts.ResultCode.RC_DENINED.getCode());
   			result.setMsg("invalid format excel file");
   			return _filter.makeCORSEntity( result );
        }
        
        boolean findData = false;
        for (int row = excelReadStartRow; row <= sheetT.getLastRowNum(); row++) {
        	
        	//LOGGER.debug("row :: " + row);
        	
        	findData = false;
        	
        	EgovMap exportVO = new EgovMap();
        	Row row1 = sheetT.getRow(row);
         
    		for(int c=0; c < EXCEL_EXPORT_CELLNUMS.length; c++)
    		{
    			Cell cell1 = row1.getCell(EXCEL_EXPORT_CELLNUMS[c]);
    			
    			if(cell1 == null) break;
    			
    			if( cell1.getCellType() == Cell.CELL_TYPE_NUMERIC )
    			{
    				exportVO.put(EXCEL_EXPORT_COL_NAMES[c], cell1.getNumericCellValue());
    				findData = true;
    				
    			}
    			else if( cell1.getCellType() == Cell.CELL_TYPE_STRING )
    			{
    				if( StringUtils.isEmpty(cell1.getStringCellValue())) continue;
    				exportVO.put(EXCEL_EXPORT_COL_NAMES[c], StringUtils.trim(cell1.getStringCellValue()));
    				findData = true;
    			}
    			else
    			{
    				if( StringUtils.isEmpty(cell1.getStringCellValue())) continue;
    				exportVO.put(EXCEL_EXPORT_COL_NAMES[c], StringUtils.trim(cell1.getStringCellValue()));
    				findData = true;
    			}
    		}
    		if(findData){
    			exportVO.put("apiTime", null);
    			exportList.add(exportVO);
    		}
    			
        }
        
       File delfile = new File(ufile.getFullpath());
       if(delfile.isFile())
        	delfile.delete();
       
       /**
		 * 
		 * buyTypename = tm_codexd(MP006) etc01 = mp_cust_info.cust_name
		 * 매입처정보 체크 
		 * */
       MpPighisBuyMVO chkVO = new MpPighisBuyMVO();
       
       for(EgovMap vo : exportList)
		{
    	   chkVO.setBuyTypename((String)vo.get("buyTypename"));
    	   String chkbuyType = mpPighisBuyMService.selectBuyTypeMpPighisBuyM_S(chkVO);
       		if(chkbuyType != null && buyType.equals(chkbuyType))
       		{}
       		else
       		{
       			result.setResultCode(Consts.ResultCode.RC_DENINED.getCode());
       			result.setMsg("선택한 도축장과 같은 업로드 파일을 선택하십시오.");
       			return _filter.makeCORSEntity( result );
       		}
		}
        
        log.debug("ReadExcelFile end...." + new Date()); 
        
        PageSet pageSet = new PageSet();
        pageSet.setResult(exportList);
        pageSet.setTotalRecordCount(exportList.size());
		
        result.setExtraData(pageSet);
        
        return _filter.makeCORSEntity( result );
    }
    
    /***
     * 신고파일생성
     * @param 	buyDate 매입일자
	 * 			buyType 도축장코드
     * */
    @RequestMapping(value="/{buyDate}/{buyType}/download",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> downloadMpPighisBuyM(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("buyDate") String buyDate,
    		@PathVariable("buyType") String buyType,
    		MpPighisBuyMVO mpPighisBuyMVO,
            HttpServletRequest req,
            HttpServletResponse res,
            HttpSession jsession
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	buyDate = StringUtils.remove(buyDate, "-");
    	
    	mpPighisBuyMVO.setBuyDate(buyDate);
    	mpPighisBuyMVO.setBuyType(buyType);
    	
    	MPUserSession sess = getSession(req);
    	/***/
    	
    	String userPath = sess.getUser().getId();
    	
    	//String userPath = "test";
    	
    	String contextroot = jsession.getServletContext().getRealPath("/");
    	String uploadPath = propertiesService.getString("file.upload.path")  + "/" + userPath;

    	//if(sess.getUser() == null)
    	//	return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_DENINED ) );
    	/*
    	 * validate request type
    	 */
    	String filePath = uploadPath;
    	
        /**
        // BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
        // 파일안에 문자열 쓰기
         * excel 축산물평가원 돼지도체등급판정결과
         * 매입처정보 없음. 매입처정보 조회로직 필요.
         * 신고 API 파일전송 포맷.
         * 
        //T1|20170712|이력||110035800063||1258117003|openMPS엘피씨공사|김경환|031-8056-3300|경기도 안성시 일죽면 일생로 183-11|가공장|지육|자체매입분|5458( 묶음중량 )
		  T2|20170712|이력||110035800063|1102|430410|지육|85|2

         **/
    	
    	/**
         * Header(T1) + Detail(T2)
         * 
         * title
			, buy_typename
			, corp_code
			, buy_date 
			, his_no
			, doch_no
			, comp_ssno
			, comp_name
			, comp_owner
			, comp_tel
			, comp_addr
			, gita_weig
			, grade_gubun2
			, base_part_code
			, base_part_name
         * 
         * 가공장|지육|자체매입분 fix
         * */
    	mpPighisBuyMVO.setBuyDate(StringUtils.remove(buyDate,"-"));
    	mpPighisBuyMVO.setBuyType(buyType);
    	List<EgovMap> mpPlanCmHVOs = mpPighisBuyMService.selectMpPighisBuyMSendFormatList(mpPighisBuyMVO);
    	
    	String buyTypename = "";
    	
 		StringBuffer creatFileCont = new StringBuffer();
 		if(mpPlanCmHVOs != null && mpPlanCmHVOs.size() > 0)
 		{
 			for(int i =0 ; i < mpPlanCmHVOs.size() ; i++)
 	 		{
 				EgovMap vo = mpPlanCmHVOs.get(i);
 				
 				if(i == 0) buyTypename = (String)vo.get("compName");
 				
 	 			if("T1".equals((String)vo.get("title")))
 	 			{
 	 				creatFileCont.append( "T1"+ "|" + vo.get("buyDate") + "|이력" );
 	 				creatFileCont.append( "||" + vo.get("hisNo") );
 	 				creatFileCont.append( "||" + vo.get("compSsno") );
 	 				creatFileCont.append( "|" + vo.get("compName") );
 	 				creatFileCont.append( "|" + vo.get("compOwner") );
 	 				creatFileCont.append( "|" + vo.get("compTel") );
 	 				creatFileCont.append( "|" + vo.get("compAddr") );
 	 				creatFileCont.append( "|" + "가공장" );
 	 				creatFileCont.append( "|" + "지육" );
 	 				creatFileCont.append( "|" + "자체매입분" );
 	 				creatFileCont.append( "|" + vo.get("gitaWeig") );
 	 				creatFileCont.append( "\r\n" );
 	 			}
 	 			else if("T2".equals((String)vo.get("title")))
 	 			{
 	 				creatFileCont.append( "T2"+ "|" + vo.get("buyDate") + "|이력" );
 	 				creatFileCont.append( "||" + vo.get("hisNo") );
 	 				creatFileCont.append( "|" + vo.get("dochNo") );
 	 				creatFileCont.append( "|" + "430410" );
 	 				creatFileCont.append( "|" + "지육" );
 	 				creatFileCont.append( "|" + vo.get("gitaWeig") );
 	 				creatFileCont.append( "|" + vo.get("gradeGubun2Cd") );
 	 				creatFileCont.append( "\r\n" );
 	 			}
 	 		}
 			String filename = FILENAME_PREFIX + buyDate + ".txt";
 			String originalfilename = FILENAME_PREFIX + buyTypename + buyDate + ".txt";
 	 		String txtFileFull = contextroot + uploadPath + "/" + filename;
 	 		
 	 		File checkDir = new File(filePath);
 			if(!checkDir.exists())
 				checkDir.mkdirs();
 	 		
 			//축산물이력제 신고 사이트, 파일신고시 EUC_KR 인코딩파일이 아니면 한글깨짐.
 			BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFileFull),"EUC_KR"));  
 			
 	 		fw.write(creatFileCont.toString());
 	 		fw.flush();
 	 		
 	 		
 	 		//LOGGER.debug("creatFileCont :: " + creatFileCont.toString());
 	 		
 	 		LOGGER.debug("file create start :: " + txtFileFull);
 	         
 	    	FileUtil.downloadFile(contextroot + uploadPath, filename, originalfilename, res);
 	    	
 	    	LOGGER.debug("file create end :: " + txtFileFull);
 		}
 		else
 		{
 			result = new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
 		}
    	
 		return _filter.makeCORSEntity( result );
    }
    
    /**
     * excel data save
     * @param 	buyDate 매입일자
	 * 			buyType 도축장코드
     * */
    @RequestMapping(value="/{buyDate}/{buyType}/save",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPighisBuyM(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("buyDate") String buyDate,
    		@PathVariable("buyType") String buyType,
            @RequestBody List<EgovMap> reqmpPlanCmHVOs,
            MpPighisBuyMVO mpPighisBuyMVO,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	String forceD = req.getParameter("forceD");
    	
    	buyDate = StringUtils.remove(buyDate, "-");
    	
    	mpPighisBuyMVO.setBuyDate(StringUtils.remove(buyDate,"-"));
    	mpPighisBuyMVO.setBuyType(buyType);
    	List ilist = new ArrayList();
    	try
    	{
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
	    			mpPighisBuyMService.deleteDupMpPighisBuyM(mpPighisBuyMVO);
	    		}
	    		else
	    		{
	    			if( mpPighisBuyMService.checkDupBuyDateMpPighisBuyM(mpPighisBuyMVO) > 0)
		    		{
		    			result.setResultCode(Consts.ResultCode.RC_DUPLICATE.getCode());
		    			return _filter.makeCORSEntity( result );
		    		}
	    		}
	    		
	    		for(EgovMap vo : reqmpPlanCmHVOs)
	        	{
	    			vo.put("corpCode",corpCode);
	    			vo.put("buyType",buyType);
	    			vo.put("buyDate",StringUtils.remove(buyDate,"-"));
	    			if(sess != null)
	    			{
	    				vo.put("mdUser",sess.getUser().getId());
	    				vo.put("crUser",sess.getUser().getId());
	    			}
	        		ilist.add(vo);
	        	}
	        	
	    	}
	    	result = mpPighisBuyMService.insertMpPighisBuyM(ilist);
    	
    	}
    	catch(Exception e)
    	{
    		ilist = null;
    		
    		result.setResultCode(Consts.ResultCode.RC_ERROR.getCode());
    			
    		result.setMsg(e.getMessage());
    		
    		return _filter.makeCORSEntity( result );
    	}
        return _filter.makeCORSEntity( result );
    }
    
    /***
     * 
     * 매입실적 OPEN API 신고 호출 
     * 
     * */
    @RequestMapping(value="/openapi/send/{buyDate}/{buyType}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> sendMpPighisBuyMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("buyDate") String buyDate,
    		@PathVariable("buyType") String buyType,
    		MpPighisBuyMVO mpPighisBuyMVO,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	mpPighisBuyMVO.setCorpCode(corpCode);
    	mpPighisBuyMVO.setBuyDate(StringUtils.remove(buyDate,"-"));
    	mpPighisBuyMVO.setBuyType(buyType);
    	mpPighisBuyMVO.setSearchCondition("nsend");
    	/**TODO
    	 * TEST
    	 * mpPighisBuyMVO.setSearchCondition2("test");
    	 * mpPighisBuyMVO.setDochNo("9001");
    	 * */
    	List<EgovMap> mpPighisBuyVOs = mpPighisBuyMService.selectMpPighisBuyMList(mpPighisBuyMVO);
    	List<MpPighisBuyVO> sendFormVOs = new ArrayList();
    	
    	for(EgovMap vo : mpPighisBuyVOs)
    	{
    		MpPighisBuyVO svo = new MpPighisBuyVO();
    		
    		svo.setBuy_date((String)vo.get("buyDate"));
    		svo.setHis_no((String)vo.get("hisNo"));
    		svo.setDoch_no((String)vo.get("dochNo"));
    		svo.setBase_part_code("430410");
    		svo.setBase_part_name("지육");
    		svo.setGita_weig(Double.parseDouble(StringUtils.defaultIfEmpty(vo.get("gitaWeig")+"","0")));
    		svo.setGrade_gubun2((String)vo.get("gradeGubun2Cd"));
    		svo.setComp_ssno((String)vo.get("compSsno"));
    		svo.setComp_name((String)vo.get("compName"));
    		svo.setComp_owner((String)vo.get("compOwner"));
    		svo.setComp_tel((String)vo.get("compTel"));
    		svo.setComp_addr((String)vo.get("compAddr"));
    		
    		sendFormVOs.add(svo);
    	}
    	
    	//신고할 건 없음.
    	if(sendFormVOs.size() == 0)
    	{
    		result.setMsg("신고할 내역이 없습니다.");
    		result.setResultCode(Consts.ResultCode.RC_FIND_NOT_FOUND.getCode());
    		return _filter.makeCORSEntity( result );
    	}

    	List<OpenapiPighisResultVO> resultVo = openapiPigService.declarePighisBuy(sendFormVOs);
    	
    	if(resultVo != null && resultVo.size()>0)
    	{
    		OpenapiPighisResultVO firstRow = resultVo.get(0);
    		if("Y".equals(firstRow.getCheckYn()))
			{
    			for(EgovMap vo : mpPighisBuyVOs)
    	    	{
    				vo.put("mdUser", sess.getUser().getId());
    				vo.put("memo", "success");
    				vo.put("searchCondition", "success");
    				mpPighisBuyMService.updateApiTimeMpPighisBuyM(vo);
    	    	}
    			
    			result.setMsg(firstRow.getCheckMsg());
    			result.setExtraData(sendFormVOs.size());
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
					//db column length limit
					vo.put("memo", memo);
    				mpPighisBuyMService.updateApiTimeMpPighisBuyM(vo);
    	    	}
    			
    			result.setMsg(memo);
    			result.setExtraData(resultVo);
    			result.setResultCode(Consts.ResultCode.RC_ERROR.getCode());
    		}
    	}
    	else
    	{
    		String memo = "매입실적신고openapi 응답메시지없음. 오류";
    		for(EgovMap vo : mpPighisBuyVOs)
	    	{
				vo.put("memo", memo);
				mpPighisBuyMService.updateApiTimeMpPighisBuyM(vo);
	    	}
    		
    		result.setExtraData(memo);
			result.setResultCode(Consts.ResultCode.RC_ERROR.getCode());
    	}
    	
    	return _filter.makeCORSEntity( result );
    }
    /***
     * 
     * 매입실적 OPEN API 신고 호출 테스트 
     * 
     * */
    @RequestMapping(value="/openbuy/test",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPighisBuyMList(
    		MpPighisBuyMVO mpPighisBuyMVO,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	mpPighisBuyMVO.setCorpCode("1001");
    	mpPighisBuyMVO.setBuyDate("20170801");
    	mpPighisBuyMVO.setBuyType("0");
    	
    	mpPighisBuyMVO.setSearchCondition2("test");
    	
    	List<EgovMap> mpPighisBuyVOs = mpPighisBuyMService.selectMpPighisBuyMList(mpPighisBuyMVO);
    	List<MpPighisBuyVO> sendFormVOs = new ArrayList();
    	
    	for(EgovMap vo : mpPighisBuyVOs)
    	{
    		MpPighisBuyVO svo = new MpPighisBuyVO();
    		
    		svo.setBuy_date((String)vo.get("buyDate"));
    		svo.setHis_no((String)vo.get("hisNo"));
    		svo.setDoch_no((String)vo.get("dochNo"));
    		svo.setBase_part_code((String)vo.get("basePartCode"));
    		svo.setBase_part_name((String)vo.get("basePartName"));
    		svo.setGita_weig(Double.parseDouble(StringUtils.defaultIfEmpty(vo.get("gitaWeig")+"","0")));
    		svo.setGrade_gubun2((String)vo.get("gradeGubun2"));
    		svo.setComp_ssno((String)vo.get("compSsno"));
    		svo.setComp_name((String)vo.get("compName"));
    		svo.setComp_owner((String)vo.get("compOwner"));
    		svo.setComp_tel((String)vo.get("compTel"));
    		svo.setComp_addr((String)vo.get("compAddr"));
    		
    		sendFormVOs.add(svo);
    	}
    	/**
    	 * ONLY TEST
    	 * 
		List<MpPighisBuyVO> list = (ArrayList<MpPighisBuyVO>)openapiPigService.getPighisBuy();*/
		/**
		 * */
    	System.out.println( openapiPigService.declarePighisBuy(sendFormVOs) );
    	return null;
    }
    
}
