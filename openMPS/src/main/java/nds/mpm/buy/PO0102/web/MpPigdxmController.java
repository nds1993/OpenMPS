package nds.mpm.buy.PO0102.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.buy.PO0102.service.MpPigdxmService;
import nds.mpm.buy.PO0102.vo.MpPigdxmVO;
import nds.mpm.buy.PO0102.vo.OrderByComparator;
import nds.mpm.common.vo.CommonFileVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.vo.UploadFileVO;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;

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

import edu.emory.mathcs.backport.java.util.Collections;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  MpPigdxmController
 *
 * @author MPM TEAM
 * @since 2017. 10. 5.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 등판자료입력( PO0102 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 10. 5.	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/po0102")
public class MpPigdxmController extends TMMBaseController{
	
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPigdxmController.class);

    @Resource(name = "mpPigdxmService")
    private MpPigdxmService mpPigdxmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "XSSFexcelService") 
    private EgovExcelService excelService;
    
    private static final int EXCEL_READ_STARTROW = 0;
    
    //양식1(전체)
    private static final int[] EXCEL_TOTAL_EXPORT_CELLNUMS = 
    	{0,1,3,4,5,6,7,8,9,10, 13,26,27,28};
    //양식2(개별)
    private static final int[] EXCEL_EXPORT_CELLNUMS = 
    	{0,1,4,5,6,7,10,11,12,13,17, 36,38,39};
    
    private static final String[] EXCEL_EXPORT_COL_NAMES = 
    	{"no","buyTypeName","crDatemonth","crDateday","dochId","pigGu1"
    	,"pigGu2","sexName","pigWeig2","pigWeig3","gradeGubun1"
    	,"pigMeat","custName","hisNo"};
 	
    /**
	 * mp_pigdxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPigdxmDefaultVO
	 * @return "/mpPigdxm/MpPigdxmList"
	 * @exception Exception
	 */
    @RequestMapping(value="/mppigdxm/{dochDate}")
    public ResponseEntity<ResultEx> selectMpPigdxmList(
    	@PathVariable("corpCode") String corpCode,
		@PathVariable("dochDate") String dochDate,
		HttpServletRequest req,
		HttpServletResponse res, 
		ModelMap model)
        throws Exception {
    
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	    MPUserSession sess = getSession(req);
		
	    int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
		int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
		
		String hisNo = req.getParameter("hisNo");
		
		MpPigdxmVO searchVO = new MpPigdxmVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setDochDate(StringUtils.remove(dochDate,"-"));
		searchVO.setHisNo(hisNo);
		
		searchVO.setPageIndex(pageIndex);
		searchVO.setPageSize(pageSize);
		
        List<?> mpPigdxmList = mpPigdxmService.selectMpPigdxmList(searchVO);
	
		PageSet pageSet = new PageSet();
	    
        int totCnt = mpPigdxmService.selectMpPigdxmListTotCnt(searchVO);
	    pageSet.setPageIndex(pageIndex);
		pageSet.setPageSize(pageSize);
		pageSet.setTotalRecordCount(totCnt);
		pageSet.setResult(mpPigdxmList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/mppigdxm/{dochDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPigdxmView(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("dochDate") String dochDate,
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
    			vo.put("dochDate",StringUtils.remove(dochDate,"-"));
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	result = mpPigdxmService.insertMpPigdxm(ilist);
        return _filter.makeCORSEntity( result );
    }
    
    /***
     * 등급판정 excel load
     * 
     * 
     * */
    @RequestMapping(value="/excel/upload",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> exceladdOrderInfo(
    		@PathVariable("corpCode") String corpCode,
    		UploadFileVO uploadFile,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String excelGubun = req.getParameter("excelGubun");
    	int[] readCellNums = null;
    	
    	if(StringUtils.isEmpty(excelGubun))
    	{
    		result = new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS );
    		return _filter.makeCORSEntity( result );
    	}
    	
    	if("all".equals(excelGubun))
    	{
    		readCellNums = EXCEL_TOTAL_EXPORT_CELLNUMS;
    	}
    	else if("per".equals(excelGubun))
    	{
    		readCellNums = EXCEL_EXPORT_CELLNUMS;
    	}	
    	
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
	    		if("1".equals(checkString))
	    		{
	    			excelReadStartRow = row;
	    			break;
	    		}
	    	}
    	
        }
        
        LOGGER.debug("startRowChkCell excelReadStartRow :: " + excelReadStartRow);

        List<EgovMap> exportList = new ArrayList();
        
        try{
        
	        if(excelReadStartRow == 0)
	        {
	        	/**
	        	 * excel format 오류 
	        	 * 읽을수있는 파일이 아니거나 정해진 포맷이 아님.
	        	 * */
	        	result.setResultCode(Consts.ResultCode.RC_DENINED.getCode());
	   			result.setMsg("정해진 엑셀양식이 아니거나 읽을수있는 데이터가 없습니다.");
	   			return _filter.makeCORSEntity( result );
	        }
	        
	        String dochId = null;
	        
	        boolean findData = false;
	        for (int row = excelReadStartRow; row <= sheetT.getLastRowNum(); row++) {
	        	
	        	//LOGGER.debug("row :: " + row);
	        	
	        	findData = false;
	        	
	        	EgovMap exportVO = new EgovMap();
	        	Row row1 = sheetT.getRow(row);
	         
	        	
	    		for(int c=0; c < readCellNums.length; c++)
	    		{
	    			Cell cell1 = row1.getCell(readCellNums[c]);
	    			
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
	    			
	    			if(StringUtils.isEmpty((String)exportVO.get("dochId"))) break;
	    			
	    			String buyTypeName = (String)exportVO.get("buyTypeName");

	    			/**
	    			 * 추가정보 셋팅 
	    			 * 이력번호 hisNo 앞 6자리 농장번호. = mp_cust_info.fac_cust
	    			 * 업로드 시 이력번호 앞 6자리로 거래처정보(MP_CUST_INFO_M)의 농장번호(FAC_CUST)와 동일한 
	    			 * 출하농장 행의 농장(CUST_CODE), 농장명(CUST_NAME), 정산구분(FAC_KIND), FAC_CODE(정산방법코드), 브랜드(BRAND_CODE), 대표농장을 조회
	    			 * 하여 아래 리스트의 정상구분(FAC_KIND), 정산방법(FAC_CODE), 농장(CUST_CODE), 농장명(CUST_NAME)
	    			 * , 브랜드코드(BRAND_CODE), 대표농장코드, 대표농장명을 리스트에 표시함
	    			 * 
	    			 * */
	    			MpPigdxmVO fvo = new MpPigdxmVO();
	    			String hisNo = (String)exportVO.get("hisNo");
	    			LOGGER.debug("hisNo :: " + hisNo);
	    			
	    			if(StringUtils.isNotEmpty(hisNo))
	    			{
	    				fvo.setCorpCode(corpCode);
	    				fvo.setHisNo(hisNo);
	    				fvo.setBuyTypeName(buyTypeName);
	    				
	    				EgovMap findBuyType = mpPigdxmService.selectBuyType(fvo);
	    				if( findBuyType != null )
	    				{
	    					exportVO.put("buyType", StringUtils.defaultIfEmpty((String)findBuyType.get("buyType"),""));
	    				}
	    				
	    				EgovMap findFac = mpPigdxmService.selectFacInfo(fvo);
	    				if(findFac != null)
	    				{
	    					exportVO.put("facKind", findFac.get("facKind"));
	    					exportVO.put("facKindName", findFac.get("facKindName"));
	    					exportVO.put("facCode", findFac.get("facCode"));
	    					exportVO.put("facCodeName", findFac.get("facCodeName"));
	    					exportVO.put("custCode", StringUtils.defaultIfEmpty((String)findFac.get("custCode"),""));
	    					exportVO.put("custName", findFac.get("custName"));
	    					exportVO.put("brandCode", StringUtils.defaultIfEmpty((String)findFac.get("brandCode"),""));
	    					exportVO.put("brandCodeName", findFac.get("brandCodeName"));
	    					exportVO.put("repCust", StringUtils.defaultIfEmpty((String)findFac.get("receCombCust"),""));
	    					exportVO.put("repCustName", findFac.get("receCombCustName"));
	    					
	    					if(StringUtils.isEmpty((String)exportVO.get("buyType")))
	    					{
	    						exportVO.put("orderby1", "1");
	    					}
	    					else
	    						exportVO.put("orderby1", "2");
	    					
	    					if(StringUtils.isEmpty((String)findFac.get("custCode")))
	    					{
	    						exportVO.put("orderby2", "1");
	    					}
	    					else
	    						exportVO.put("orderby2", "2");
	    					if(StringUtils.isEmpty((String)findFac.get("brandCode")))
	    					{
	    						exportVO.put("orderby3", "1");
	    					}
	    					else
	    						exportVO.put("orderby3", "2");
	    					if(StringUtils.isEmpty((String)findFac.get("repCust")))
	    					{
	    						exportVO.put("orderby4", "1");
	    					}
	    					else
	    						exportVO.put("orderby4", "2");
	    				}
	    				else
		    			{
		    				exportVO.put("orderby1", "1");
		    				exportVO.put("orderby2", "1");
		    				exportVO.put("orderby3", "1");
		    				exportVO.put("orderby4", "1");
		    			}
	    			}
	    			else
	    			{
	    				exportVO.put("orderby1", "1");
	    				exportVO.put("orderby2", "1");
	    				exportVO.put("orderby3", "1");
	    				exportVO.put("orderby4", "1");
	    			}
	    			
	    			fvo = null;
	    		}
	    		else
    			{
    				exportVO.put("orderby1", "1");
    				exportVO.put("orderby2", "1");
    				exportVO.put("orderby3", "1");
    				exportVO.put("orderby4", "1");
    			}
	    		
	    		if(StringUtils.isNotEmpty((String)exportVO.get("buyTypeName")))
	    			exportList.add(exportVO);
	    			
	        }
        
        }catch(Exception excelEx)
        {
        	excelEx.printStackTrace();
        	result.setMsg("정해진 엑셀양식이 아니거나 읽을수있는 데이터가 없습니다.");
        	result.setResultCode(Consts.ResultCode.RC_FIND_NOT_FOUND.getCode());
        	return _filter.makeCORSEntity( result );
        }finally
        {
        	File delfile = new File(ufile.getFullpath());
            if(delfile.isFile())
             	delfile.delete();
        }
        /***
         * TEST
         * 
         *
        for(EgovMap ts: exportList)
        {
        	ts.put("dsType", "C");
        	ts.put("corpCode", "1001");
        	ts.put("facKind", "1");
        	ts.put("dochDate", "20170801");
        }
       	mpPigdxmService.insertMpPigdxm(exportList);
       	 */
        /**
         *  * 작업장코드, 농장코드, 브랜드코드, 대표농장이 없을 경우 
						NULL 로 표시하고, 리스트의 최상위로 순서대로 나열함
         * */
        Collections.sort(exportList, new OrderByComparator("orderby1", "orderby2", "orderby3", "orderby4"));
        
        log.debug("ReadExcelFile end...." + new Date()); 
        
        log.debug("ReadExcel list size...." + exportList.size()); 
        
        PageSet pageSet = new PageSet();
        pageSet.setResult(exportList);
        pageSet.setTotalRecordCount(exportList.size());
		
        result.setExtraData(pageSet);
        
        return _filter.makeCORSEntity( result );
    }

}
