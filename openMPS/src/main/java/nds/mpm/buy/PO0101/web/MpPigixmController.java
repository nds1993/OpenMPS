package nds.mpm.buy.PO0101.web;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.buy.PO0101.service.MpPigixmService;
import nds.mpm.buy.PO0101.vo.MpPigixmDefaultVO;
import nds.mpm.buy.PO0101.vo.MpPigixmVO;
import nds.mpm.common.util.ExcelUtil;
import nds.mpm.common.vo.CommonFileVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.vo.UploadFileVO;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.excel.PO;
import nds.mpm.login.vo.MPUserSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
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
import org.springframework.web.multipart.MultipartFile;

import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
/**
 * @Class Name :  MpPigixmController
 *
 * @author MPM TEAM
 * @since 2017.08.22
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 지육시세등록( PO0101 )
 * 조회 table : mp_pigixm
 * I/F 조회 table : if_piggxm  
 * 돼지도체등급별경락가 excel load
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
@RequestMapping("/mpm/{corpCode}/po0101")
public class MpPigixmController extends TMMBaseController{
	
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPigixmController.class);

    @Resource(name = "mpPigixmService")
    private MpPigixmService mpPigixmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "XSSFexcelService") 
    private EgovExcelService excelService; 
    /**
     * 돼지도체등급별경락가 excel load
     * 데이터 ROW 순서
     * 
     *  aPrice1 박(탕)암1+
	    aDosu1 박(탕)암1+두수
	    bPrice1 박(탕)암1
	    bDosu1 박(탕)암1두수
	    cPrice1 박(탕)암2
	    cDosu1 박(탕)암2두수
	    dPrice1 박(탕)암등외
	    dDosu1 박(탕)암등외두수
	    aPrice2 박(탕)수1+
	    aDosu2 박(탕)수1+두수
	    bPrice2 박(탕)수1
	    bDosu2 박(탕)수1두수
	    cPrice2 박(탕)수2
	    cDosu2 박(탕)수2두수
	    dPrice2 박(탕)수등외
	    dDosu2 박(탕)수등외두수
	    aPrice3 박(탕)거1+
	    aDosu3 박(탕)거1+두수
	    bPrice3 박(탕)거1
	    bDosu3 박(탕)거1두수
	    cPrice3 박(탕)거2
	    cDosu3 박(탕)거2두수
	    dPrice3 박(탕)거등외
	    dDosu3 박(탕)거등외
	    aTotPrice1 전체시세
     * */
    
    //지육시세일자 read row
    private static final int EXCEL_JIYUK_DATE_ROWNUM = 2; 
    // 박피 read excel D cell
    private static final int EXCEL_SKIN_EXPORT_CELLNUM = 3; 
    // 탕박 read excel E cell
    private static final int EXCEL_NSKIN_EXPORT_CELLNUM = 4; 
    
    // 박피 excel row num
    private static final int[] EXCEL_EXPORT_SKINROWNUMS = 
    	{5,6,7,8,9,10,11,12
    	,19,20,21,22,23,24,25,26
    	,33,34,35,36,37,38,39,40
    	,55};
    // 탕박 excel row num
    private static final int[] EXCEL_EXPORT_NSKINCELLNUMS = 
    	{61,62,63,64,65,66,67,68
    	,75,76,77,78,79,80,81,82
    	,89,90,91,92,93,94,95,96
    	,111};
    // 박피,탕박의 모든 excel row num
    private static final int[] EXCEL_EXPORT_ROWNUMS =
    	{
    		// 박피
    		5,6,7,8,9,10,11,12
    	    	,19,20,21,22,23,24,25,26
    	    	,33,34,35,36,37,38,39,40
    	    	,55
    	    	,
    	    	// 탕박
    	    	61,62,63,64,65,66,67,68
    	    	,75,76,77,78,79,80,81,82
    	    	,89,90,91,92,93,94,95,96
    	    	,111
    	};
    
    //
    //
    
    //  박피 export column name
    private static final String[] EXCEL_EXPORT_SKINCOL_NAMES = 
    	{"aPrice1","aDosu1","bPrice1","bDosu1","cPrice1","cDosu1","dPrice1","dDosu1"
    	,"aPrice2","aDosu2","bPrice2","bDosu2","cPrice2","cDosu2","dPrice2","dDosu2"
    	,"aPrice3","aDosu3","bPrice3","bDosu3","cPrice3","cDosu3","dPrice3","dDosu3"
    	,"aTotPrice1"};
    //  탕박 export column name
    private static final String[] EXCEL_EXPORT_NSKINCOL_NAMES = 
    	{"a1Price1","a1Dosu1","b1Price1","b1Dosu1","c1Price1","c1Dosu1","d1Price1","d1Dosu1"
    	,"a1Price2","a1Dosu2","b1Price2","b1Dosu2","c1Price2","c1Dosu2","d1Price2","d1Dosu2"
    	,"a1Price3","a1Dosu3","b1Price3","b1Dosu3","c1Price3","c1Dosu3","d1Price3","d1Dosu3"
    	,"a1TotPrice1"};
    // 박피,탕박 export column name
    private static final String[] EXCEL_EXPORT_COL_NAMES =
    	{
    		// 박피
    		"aPrice1","aDosu1","bPrice1","bDosu1","cPrice1","cDosu1","dPrice1","dDosu1"
    	    	,"aPrice2","aDosu2","bPrice2","bDosu2","cPrice2","cDosu2","dPrice2","dDosu2"
    	    	,"aPrice3","aDosu3","bPrice3","bDosu3","cPrice3","cDosu3","dPrice3","dDosu3"
    	    	,"aTotPrice1"
    	    	,
    	    	// 탕박
    	    	"a1Price1","a1Dosu1","b1Price1","b1Dosu1","c1Price1","c1Dosu1","d1Price1","d1Dosu1"
    	    	,"a1Price2","a1Dosu2","b1Price2","b1Dosu2","c1Price2","c1Dosu2","d1Price2","d1Dosu2"
    	    	,"a1Price3","a1Dosu3","b1Price3","b1Dosu3","c1Price3","c1Dosu3","d1Price3","d1Dosu3"
    	    	,"a1TotPrice1"
    	};
    
    private static final String NONSKIN = "nonskin";
    private static final String SKIN = "skin";
    /**
     * 지육시세 인정 일자 : 전국 12개 경락가격 공판장 중 6개 이상 일 경우 지육시세로 인정하고 6개 미만일 경우는 리스트 및 저장 시 제외한다.
  - I/F 인 경우 : IF_PIGGXM 테이블의 필드 c_0101Amt, c_0320Amt, c_0302Amt,c_1301Amt,c_0323Amt, c_0513Amt, c_1005Amt, c_0202Amt, c_0201AmtM, c_1201Amt, c_0905Amt, c_0714Amt, c_0809Amt, c_1401Amt 필드 중 6개 이상 있을 경우 인정함
  - 엑셀업로드 일 경우 : 수도권, 중부권, 영남권, 호남권 경락가격 중 6개 이상 있을 경우 인정함
  - 박피일 경우 제주지역 포함 6개 이상, 탕박일 경우 제주지역 제외 6개 이상
  - 토요일 일자는 지육시세 등록에서 제외함, 제외된 일자의 가격과 두수는 ‘NULL’로 저장함
     * 
     * */
    private static final int[] EXCEL_EXPORT_SKIN_VALIDCHECKNUMS = 
    	{5,7,8,9,10, 12,13,14,15,16,17 ,19,20,21,22};
    private static final int[] EXCEL_EXPORT_NSKIN_VALIDCHECKNUMS = 
    	{5,7,8,9,10, 12,13,14,15,16,17 ,19,20,21};
    // 박피,탕박
    private static final int[] EXCEL_EXPORT_VALIDCHECKNUMS = 
    	{5,7,8,9,10, 12,13,14,15,16,17 ,19,20,21,22};
 	
    /**
	 * mp_pigixm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPigixmDefaultVO
	 * @return "/mpPigixm/MpPigixmList"
	 * @exception Exception
	 */
    @RequestMapping(value="/mppigixm/{strtDate}/{lastDate}/{skinType}")
    public ResponseEntity<ResultEx> selectMpPigixmList(
        @PathVariable("corpCode") String corpCode,
		@PathVariable("strtDate") String strtDate,
		@PathVariable("lastDate") String lastDate,
		@PathVariable("skinType") String skinType,
		HttpServletRequest req,
		HttpServletResponse res, 
		ModelMap model)
        throws Exception {
    
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	    MPUserSession sess = getSession(req);
		
	    int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
		int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
		
		String salesman = req.getParameter("salesman");
		
		MpPigixmVO searchVO = new MpPigixmVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
		searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
		
		searchVO.setPageIndex(pageIndex);
		searchVO.setPageSize(pageSize);
		
        List<?> mpPigixmList = null;
        
        if("nonskin".equals(skinType))
        {
        		mpPigixmList = mpPigixmService.selectMpPigixmNonSkinList(searchVO);
        }
        else if("skin".equals(skinType))
        {
        		mpPigixmList = mpPigixmService.selectMpPigixmSkinList(searchVO);
        }
	
		PageSet pageSet = new PageSet();
	    
        int totCnt = mpPigixmService.selectMpPigixmListTotCnt(searchVO);
	    pageSet.setPageIndex(pageIndex);
		pageSet.setPageSize(pageSize);
		pageSet.setTotalRecordCount(totCnt);
		pageSet.setResult(mpPigixmList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/mppigixm/{strtDate}/{lastDate}/{skinType}/export")
    public ResponseEntity<ResultEx> selectExMpPigixmList(
        @PathVariable("corpCode") String corpCode,
		@PathVariable("strtDate") String strtDate,
		@PathVariable("lastDate") String lastDate,
		@PathVariable("skinType") String skinType,
		HttpServletRequest req,
		HttpServletResponse res, 
		HttpSession jsession,
		ModelMap model)
        throws Exception {
    
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	    MPUserSession sess = getSession(req);
		
		String salesman = req.getParameter("salesman");
		
		MpPigixmVO searchVO = new MpPigixmVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
		searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
		
        List<EgovMap> mpPigixmList = null;
        
        String[] columns = null;
        String sheetName = null;
        
        if("nonskin".equals(skinType)){
        	mpPigixmList = mpPigixmService.selectMpPigixmNonSkinList(searchVO);
        	columns = PO.PP01011;
        	sheetName = PO.PP01011_NM;
        }
        else if("skin".equals(skinType)){
        	mpPigixmList = mpPigixmService.selectMpPigixmSkinList(searchVO);
        	columns = PO.PP01013;
        	sheetName = PO.PP01013_NM;
        }
    	
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpPigixmList, columns, PO.PP01011_TYPE, sheetName);
		
		return null;
    }
    
    /**
     * I/F 조회
	 * if_piggxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPigixmDefaultVO
	 * @return "/mpPigixm/MpPigixmList"
	 * @exception Exception
	 */
    @RequestMapping(value="/ifpiggxm/{strtDate}/{lastDate}/{skinType}")
    public ResponseEntity<ResultEx> selectIfPiggxmList(
        @PathVariable("corpCode") String corpCode,
		@PathVariable("strtDate") String strtDate,
		@PathVariable("lastDate") String lastDate,
		@PathVariable("skinType") String skinType,
		HttpServletRequest req,
		HttpServletResponse res, 
		ModelMap model)
        throws Exception {
    
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	    MPUserSession sess = getSession(req);
		
	    int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
		int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
		
		String salesman = req.getParameter("salesman");
		String skinyn = "skin".equals(skinType) ? "N" : "Y";

		MpPigixmVO searchVO = new MpPigixmVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
		searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
		searchVO.setSkinyn(skinyn);
		
		searchVO.setPageIndex(pageIndex);
		searchVO.setPageSize(pageSize);
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date today = sdf.parse( searchVO.getStrtDate() );
		Date lastDay = sdf.parse( searchVO.getLastDate() );
		c.setTime( today );

		while( today.getTime() <= lastDay.getTime() )
		{
			String strToday = sdf.format( today );
			result = this.fetchIFpiggxm( strToday );
			if( result.getResultCode() != Consts.ResultCode.RC_OK.getCode() )
			{
				return _filter.makeCORSEntity( result );
			}
			c.add(Calendar.DATE, 1);
			today = c.getTime();
		}
		
        List<?> mpPigixmList = null;
        	/*
        //박피
        if("N".equals(skinyn))
        	mpPigixmList = mpPigixmService.selectIfSkinPiggxmList(searchVO);
        //탕박 
        else if("Y".equals(skinyn))
        	mpPigixmList = mpPigixmService.selectIfNonSkinPiggxmList(searchVO);
        	*/
        mpPigixmList = mpPigixmService.selectIfPiggxmList(searchVO);
	
		PageSet pageSet = new PageSet();
	    
	    pageSet.setPageIndex(pageIndex);
		pageSet.setPageSize(pageSize);
		
		if(mpPigixmList!=null)
			pageSet.setTotalRecordCount(mpPigixmList.size());
		pageSet.setResult(mpPigixmList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    } 
    
    /**
     * 작업일별 지육시세를 가져온다.
     * @param callDate
     * @return
     */
    protected ResultEx fetchIFpiggxm(String callDate)
    {
    	ResultEx result = null;
    	
    	/** API 호출시 날짜 파라메터 추가됨 */
		String apiPath = "/openApi/saveApi.do?strtDate="+callDate+"&lastDate="+callDate;
		Response resp = 
			given().
	       when().
	       	  contentType("application/json").
		      post(apiPath);
		
		LOGGER.debug("IF : "+apiPath);
		LOGGER.debug("response code " + resp.getStatusCode());
		LOGGER.debug("response message " + resp.asString());
		
		try{

			JSONObject json = new JSONObject(resp.asString());
			JSONArray list = (JSONArray)json.get("list");
			
			if(list != null && list.length() > 0)
			{
				JSONObject apiResult = (JSONObject)list.get(0);
				String resultCode = (String)apiResult.get("resultcode");
				String resultMsg = (String)apiResult.get("resultmsg");
				
				LOGGER.debug("OPEN API CALL resultCode :: " + resultCode);
				
				if("00".equals(resultCode))
				{
					//success
					result = new ResultEx( Consts.ResultCode.RC_OK );
				}
				else
				{
					result = new ResultEx( Consts.ResultCode.RC_ERROR );
					result.setExtraData("openapi error :: (" + resultCode + ")" + resultMsg );
				}
			}
		}
		catch(Exception openapiErr)
		{
			openapiErr.printStackTrace();
			
			//
			result = new ResultEx( Consts.ResultCode.RC_EXCEPTION );
			result.setExtraData( openapiErr.getMessage() );
		}
		
		return result;
    }
    
    @RequestMapping("/mpPigixm/addMpPigixmView.do")
    public String addMpPigixmView(
            @ModelAttribute("searchVO") MpPigixmDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpPigixmVO", new MpPigixmVO());
        return "/mpPigixm/MpPigixmRegister";
    }
    
    @RequestMapping(value="/mppigixm/{strtDate}/{lastDate}/{skinType}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPigixm(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("skinType") String skinType,
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
	    			
	    			Iterator keys = vo.keySet().iterator();
	    			String key_name = null;
	    			while (keys.hasNext()) {
	    				key_name = (String) keys.next();
	    				String value = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(vo.get(key_name)),","),"0");
	    	    		
	    				vo.put(key_name,value);
	    			}
	    			
	    			vo.put("corpCode",corpCode);
	    			vo.put("jiyukDate",StringUtils.remove((String)vo.get("jiyukDate"),"-"));
	    			if(sess != null)
	    			{
	    				vo.put("crUser",sess.getUser().getId());
	    				vo.put("mdUser",sess.getUser().getId());
	    			}
	        		ilist.add(vo);
	        	}
	    	}
	    	
	    	if("nonskin".equals(skinType))
	    	{
	    		result = mpPigixmService.insertMpNonSkinPigixm(ilist);
	    	}
	    	else if("skin".equals(skinType))
	    	{
	        	result = mpPigixmService.insertMpSkinPigixm(ilist);
	    	}
	    	
        return _filter.makeCORSEntity( result );
    }
    
    /***
     * 돼지도체등급별경락가 excel load
     * 
     * 
     * */
    @RequestMapping(value="/mppigixm/upload/{strtDate}/{skinType}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> exceladdOrderInfo(
    		@PathVariable("skinType") String skinType,
    		@PathVariable("strtDate") String strtDate,
    		UploadFileVO uploadFile,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	if(sess.getUser() == null)
    		return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_DENINED ) );
    		
    	String userPath = sess.getUser().getId();
    	
    	int skinTypeRows[] = null;
    	int validDataChkCols[] = null;
    	int skinTypeReadCellColNum = 0;
    			
    	/*
    	if(SKIN.equals(skinType))	
    		skinTypeRows = EXCEL_EXPORT_SKINROWNUMS;
    	else if(NONSKIN.equals(skinType))	
    		skinTypeRows = EXCEL_EXPORT_NSKINCELLNUMS;
    	else
    		 return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS ) );
    	*/
    	skinTypeRows = EXCEL_EXPORT_ROWNUMS;
    	
    	/*
    	if(SKIN.equals(skinType))	
    		validDataChkCols = EXCEL_EXPORT_SKIN_VALIDCHECKNUMS;
    	else if(NONSKIN.equals(skinType))	
    		validDataChkCols = EXCEL_EXPORT_NSKIN_VALIDCHECKNUMS;
    	*/
    	validDataChkCols = EXCEL_EXPORT_VALIDCHECKNUMS;
    	
    	if(SKIN.equals(skinType))	
    		skinTypeReadCellColNum = EXCEL_SKIN_EXPORT_CELLNUM;
    	else if(NONSKIN.equals(skinType))	
    		skinTypeReadCellColNum = EXCEL_NSKIN_EXPORT_CELLNUM;
    	
    	/*
    	 * validate request type
    	 */
    	String contextroot = jsession.getServletContext().getRealPath("/");
    	MultipartFile file = uploadFile.getFile();

    	String uploadPath = propertiesService.getString("file.upload.path")  + "/" + userPath;
			
    	CommonFileVO ufile = uploadAndFileInfo(file, contextroot + uploadPath);
    	
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
        
        boolean findData = false;
        EgovMap exportVO = new EgovMap();
        int isValidJiukCellCnt = 0;
        String excelJiyukDate = null;
        
        try{
        	
        	Row jiyukDateRow = sheetT.getRow(EXCEL_JIYUK_DATE_ROWNUM);
        	Cell jiyukDateCell = jiyukDateRow.getCell(0);
        	excelJiyukDate = jiyukDateCell.getStringCellValue();
        	
        	if(StringUtils.isNotEmpty(excelJiyukDate))
        	{
        		excelJiyukDate = StringUtils.trim(excelJiyukDate);
        		if(excelJiyukDate.length() != 8){
        			LOGGER.debug("not read excel jiyuk date!! " + excelJiyukDate);
        			throw new Exception("not read excel jiyuk date!!");
        		}
        	}
        
	        for (int i = 0; i < skinTypeRows.length; i++) {
	        	
	        	//if(i > 0) break;
	        	
	        	findData = false;
	        	int validDataCnt = 0;
	        	
	        	int row = skinTypeRows[i];
	        	Row row1 = sheetT.getRow(row);
	        	
	        	Cell cell1 = row1.getCell(skinTypeReadCellColNum);
	        	
	        	
	        	LOGGER.debug("cell1 :: " + cell1);
	        	LOGGER.debug("cell1.getCellType() :: " + cell1.getCellType());
	        	
	        	for (int c = 0; c < validDataChkCols.length; c++) {
	            	
	        		Cell validchk = row1.getCell(validDataChkCols[c]);
	        		
		        	if( validchk.getCellType() == Cell.CELL_TYPE_NUMERIC )
					{
		        		LOGGER.debug("validchk.getNumericCellValue() :: " + validchk.getNumericCellValue());
		        		LOGGER.debug("validchk is 0? :: " +  validDataChkCols[c] + (validchk.getNumericCellValue() == 0));
		        		
		        		if(validchk.getNumericCellValue() != 0) validDataCnt++;
		        		continue;
					}
					else if( validchk.getCellType() == Cell.CELL_TYPE_STRING )
					{
						LOGGER.debug("validchk.getStringCellValue() :: " + validchk.getStringCellValue());
						LOGGER.debug("validchk is null? :: " +  validDataChkCols[c] + StringUtils.isEmpty(validchk.getStringCellValue()));
						
						if(StringUtils.isNotEmpty(validchk.getStringCellValue())) validDataCnt++;
						
						continue;
					}
					else
					{
						LOGGER.debug("validchk.getStringCellValue() :: " + validchk.getStringCellValue());
						LOGGER.debug("validchk is null? :: " + validDataChkCols[c] + StringUtils.isEmpty(validchk.getStringCellValue()));
						
						if(StringUtils.isNotEmpty(validchk.getStringCellValue())) validDataCnt++;
					}
	        	}
	        	
				if(cell1 == null) break;
				
				String settingVal = null;
				
				if( cell1.getCellType() == Cell.CELL_TYPE_NUMERIC )
				{
					if(cell1.getNumericCellValue() == 0) continue;
					
					settingVal = String.valueOf(cell1.getNumericCellValue());
					findData = true;
					
				}
				else if( cell1.getCellType() == Cell.CELL_TYPE_STRING )
				{
					if( StringUtils.isEmpty(cell1.getStringCellValue())) continue;
					
					String value = StringUtils.trim(cell1.getStringCellValue());
					value = StringUtils.remove(value,"(");
					value = StringUtils.remove(value,")");
					value = StringUtils.remove(value,",");
					
					settingVal = value;
					findData = true;
				}
				else
				{
					if( StringUtils.isEmpty(cell1.getStringCellValue())) continue;
					
					String value = StringUtils.trim(cell1.getStringCellValue());
					value = StringUtils.remove(value,"(");
					value = StringUtils.remove(value,")");
					value = StringUtils.remove(value,",");
					
					settingVal = value;
					findData = true;
				}
				
				LOGGER.debug("validDataCnt :: " + validDataCnt);
				
				if( findData &&  validDataCnt >= 6)
				{
					/*
					if(SKIN.equals(skinType))	
						exportVO.put(EXCEL_EXPORT_SKINCOL_NAMES[i], settingVal);
			        	else if(NONSKIN.equals(skinType))	
			        		exportVO.put(EXCEL_EXPORT_NSKINCOL_NAMES[i], settingVal);
					*/
					// 박피,탕방 동시 처리
					exportVO.put(EXCEL_EXPORT_COL_NAMES[i], settingVal);
					isValidJiukCellCnt++;
				}
				
	        }
	        
	        LOGGER.debug("isNotEmptyCellCnt :: " + isValidJiukCellCnt);
	        
	        if(isValidJiukCellCnt > 0){
	        	exportVO.put("jiyukDate", excelJiyukDate);
	        	exportList.add(exportVO);
	        }
        
        }catch(Exception excelEx)
        {
        	result.setMsg("정해진 엑셀양식이 아니거나 읽을수있는 데이터가 없습니다.");
        	result.setResultCode(Consts.ResultCode.RC_FIND_NOT_FOUND.getCode());
        	return _filter.makeCORSEntity( result );
        }finally
        {
        	File delfile = new File(ufile.getFullpath());
            if(delfile.isFile())
             	delfile.delete();
        }

        log.debug("ReadExcelFile end...." + new Date()); 
        
        PageSet pageSet = new PageSet();
        pageSet.setResult(exportList);
        pageSet.setTotalRecordCount(exportList.size());
		
        if(exportList.size() > 0)
        {
        	result.setExtraData(pageSet);
        }
        else{
        	result.setMsg("기준일자에 해당하는 지육시세가 없습니다.");
        	result.setResultCode(Consts.ResultCode.RC_FIND_NOT_FOUND.getCode());
        	return _filter.makeCORSEntity( result );
        }
        	
        
        return _filter.makeCORSEntity( result );
    }
    /**
    @RequestMapping("/mpPigixm/updateMpPigixmView.do")
    public String updateMpPigixmView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("jiyukDate") java.lang.String jiyukDate ,
            @ModelAttribute("searchVO") MpPigixmDefaultVO searchVO, Model model)
            throws Exception {
        MpPigixmVO mpPigixmVO = new MpPigixmVO();
        mpPigixmVO.setCorpCode(corpCode);
        mpPigixmVO.setJiyukDate(jiyukDate);
        // 변수명은 CoC 에 따라 mpPigixmVO
        model.addAttribute(selectMpPigixm(mpPigixmVO, searchVO));
        return "/mpPigixm/MpPigixmRegister";
    }

    @RequestMapping("/mpPigixm/selectMpPigixm.do")
    public @ModelAttribute("mpPigixmVO")
    MpPigixmVO selectMpPigixm(
            MpPigixmVO mpPigixmVO,
            @ModelAttribute("searchVO") MpPigixmDefaultVO searchVO) throws Exception {
        return mpPigixmService.selectMpPigixm(mpPigixmVO);
    }

    @RequestMapping("/mpPigixm/updateMpPigixm.do")
    public String updateMpPigixm(
            MpPigixmVO mpPigixmVO,
            @ModelAttribute("searchVO") MpPigixmDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPigixmService.updateMpPigixm(mpPigixmVO);
        status.setComplete();
        return "forward:/mpPigixm/MpPigixmList.do";
    }
    
    @RequestMapping("/mpPigixm/deleteMpPigixm.do")
    public String deleteMpPigixm(
            MpPigixmVO mpPigixmVO,
            @ModelAttribute("searchVO") MpPigixmDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPigixmService.deleteMpPigixm(mpPigixmVO);
        status.setComplete();
        return "forward:/mpPigixm/MpPigixmList.do";
    }
	*/
}
