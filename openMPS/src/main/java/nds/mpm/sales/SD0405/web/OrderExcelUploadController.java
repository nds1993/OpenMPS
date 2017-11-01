package nds.mpm.sales.SD0405.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.common.vo.CommonFileVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.vo.UploadFileVO;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.sales.SD0405.service.MpOrderUploadService;
import nds.mpm.sales.SD0405.vo.MpOrderUploadVO;
import nds.mpm.sales.SD0405.vo.OrderListComparator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import edu.emory.mathcs.backport.java.util.Collections;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : OrderExcelUploadController.java
 *
 * @author MPM TEAM
 * @since 2017.06.05
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 온라인주문업로드( SD0405 ) - 엑셀데이터 화면으로 불러오기.
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.06.05	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/sd0405/excel")
public class OrderExcelUploadController extends TMMBaseController
{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderExcelUploadController.class);
	
	@Autowired
	protected CorsFilter		_filter;
	
	String LOTTE_COL_NMS[] = { "COL1","COL2","COL3","COL4","COL5"
			,"COL6","COL7","COL8","COL9","COL10"
			,"COL11","COL12","COL13","COL14","COL15"
			,"COL16"};
    /**
	 * 롯데 헤더 ROW Read Cell Number
	 * */
    int LOTTE_HEAD_EXPORT_CELLNUMS[] = { 3, 4, 5, 6, 7, 8, 9 };
    /**
	 * 롯데 디테일 ROW Read Cell Number
	 * */
    int LOTTE_DETAIL_EXPORT_CELLNUMS[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };


	@Resource(name = "mpOrderUploadService")
    private MpOrderUploadService mpOrderUploadService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "XSSFexcelService") 
    private EgovExcelService excelService; 
	
    @RequestMapping(value="/upload",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> exceladdOrderInfo(
    		UploadFileVO uploadFile,
    		HttpServletRequest request,
    		HttpServletResponse res,
    		HttpSession jsession)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	/*
    	 * validate request type
    	 */
    	String contextroot = jsession.getServletContext().getRealPath("/");
    	MultipartFile file = uploadFile.getFile();

    	String uploadPath = propertiesService.getString("file.upload.path");
			
    	CommonFileVO ufile = uploadAndFileInfo(file, contextroot + uploadPath);
    	
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(ufile.getFullpath());
		System.out.println(ufile.getMimeType());
		System.out.println(ufile.getNewName());
		System.out.println(ufile.getOrginalName());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		log.debug("ReadExcelFile start...." + new Date()); 
		 
        List excelList = null;
        
        excelList = loadDefaultExcelFile(ufile);
        //excelList = loadLotteExcelFile(ufile);
        
        log.debug("ReadExcelFile end...." + new Date()); 
        
        PageSet pageSet = new PageSet();
        pageSet.setResult(excelList);
        pageSet.setTotalRecordCount(excelList.size());
		
        result.setExtraData(pageSet);
        
        return _filter.makeCORSEntity( result );
    }
    
    /**
     * 롯데외 온라인주문 엑셀 양식.
     * */
    public List loadDefaultExcelFile(
    		CommonFileVO ufile)
            throws Exception {
    	
    	List excelList = new ArrayList();
    	
    	LOGGER.debug("ReadExcelFile start...." + new Date()); 
    	
    	Workbook wbT = null;
        if(ufile.getOrginalName() != null && ufile.getOrginalName().indexOf(".xlsx") > 0)
        	wbT = excelService.loadWorkbook(ufile.getFullpath(), new XSSFWorkbook());
        else if(ufile.getOrginalName() != null && ufile.getOrginalName().indexOf(".xls") > 0)
            wbT = excelService.loadWorkbook(ufile.getFullpath());
               
        Sheet sheetT = wbT.getSheetAt(0);
        
        for (int i = 1; i <= sheetT.getLastRowNum(); i++) {
        	
        	Map rowMap = new HashMap();
        	Row row1 = sheetT.getRow(i);
         
        	for (int j = 0; j < row1.getLastCellNum(); j++) {
        		Cell cell1 = row1.getCell(j);
        		
        		try{
        			if(cell1 != null){
                		rowMap.put("COL"+(j+1), cell1.getStringCellValue());
        			}
        		}catch(Exception e)
        		{
        			if(cell1 != null){
                		rowMap.put("COL"+(j+1), cell1.getNumericCellValue());
        			}
        		}
        		
        	}
        	
        	excelList.add(rowMap);
        }
        
        File delfile = new File(ufile.getFullpath());
        //if(delfile.isFile()) delfile.delete();
        
        LOGGER.debug("ReadExcelFile end...." + new Date()); 
        
        return excelList;
    }
    
    /**
     * 롯데 온라인주문 엑셀 양식, 타거래처와 양식다름으로 추가 구현.
     * */
    public List loadLotteExcelFile(
    		CommonFileVO ufile)
            throws Exception {
    	
    	int LOTTE_HEAD_GUBUN_CELL_NUM = 1;
    	int LOTTE_ITEM_COUNT_CELL_NUM = 7;
    	
    	List excelList = new ArrayList();
    	
    	LOGGER.debug("ReadExcelFile start...." + new Date()); 
    	
    	Workbook wbT = null;
        if(ufile.getOrginalName() != null && ufile.getOrginalName().indexOf(".xlsx") > 0)
        	wbT = excelService.loadWorkbook(ufile.getFullpath(), new XSSFWorkbook());
        else if(ufile.getOrginalName() != null && ufile.getOrginalName().indexOf(".xls") > 0)
            wbT = excelService.loadWorkbook(ufile.getFullpath());
               
        Sheet sheetT = wbT.getSheetAt(0);
        
        int colNumCnt = 0;
        int itemCnt = 0;
        
        Map rowMap = null;
        double beforeRowGubun=0;
        
        String custCode = null;
        String delvDate1 = null;
        String delvDate2 = null;
        
        for (int rowNum = 0; rowNum <= sheetT.getLastRowNum(); rowNum++) {
        //for (int rowNum = 0; rowNum <= 600; rowNum++) {
        	
        	itemCnt = 0;
        	colNumCnt = 0;
        	beforeRowGubun=0;
        	
        	Row row1 = sheetT.getRow(rowNum);
        	
        	Cell cell1 = row1.getCell(LOTTE_HEAD_GUBUN_CELL_NUM);
        	String headerChecker = cell1.getStringCellValue();
        	
        	LOGGER.debug("headerChecker :: " + headerChecker);
        	
        	//header
        	if("ORDERS".equals(headerChecker))
        	{
        		//item 갯수
        		cell1 = row1.getCell(LOTTE_ITEM_COUNT_CELL_NUM);
        		String itemCntStr = cell1.getStringCellValue();
        		
        		if(StringUtils.isNumeric(itemCntStr))
        		{
        			itemCnt = Integer.parseInt(itemCntStr);
        		}
        		else continue;
        		
        		//header item setting
        		for (int h = 0; h < LOTTE_HEAD_EXPORT_CELLNUMS.length; h++) {
        			Cell hcell = row1.getCell(LOTTE_HEAD_EXPORT_CELLNUMS[h]);
        			
        			LOGGER.debug("headRow :: " + rowNum + "|colNumCnt:: " + h + "::" + hcell.getStringCellValue());
        			
        			if("COL4".equals(LOTTE_COL_NMS[h]))
        				custCode = hcell.getStringCellValue();
        			else if("COL6".equals(LOTTE_COL_NMS[h]))
        				delvDate1 = 	hcell.getStringCellValue();
        			else if("COL7".equals(LOTTE_COL_NMS[h]))
        				delvDate2 = 	hcell.getStringCellValue();
        			
        		}
        		
        		//detail의 title row skip
        		rowNum += 2;
        		
        		for(int detailRow=0; detailRow < itemCnt; detailRow++)
        		{
        			
        			row1 = sheetT.getRow(rowNum++);
        			
        			rowMap = new HashMap();
        			rowMap.put("COL4", custCode);
        			rowMap.put("COL6", delvDate1);
        			rowMap.put("COL7", delvDate2);
        			
        			for (int d = 0; d < LOTTE_DETAIL_EXPORT_CELLNUMS.length; d++) {
            			Cell dcell = row1.getCell(LOTTE_DETAIL_EXPORT_CELLNUMS[d]);
            			
            			LOGGER.debug("detailRow :: " + rowNum + "|colNumCnt:: " + colNumCnt + "::" + dcell.getStringCellValue());
            			
            			rowMap.put(LOTTE_COL_NMS[d + LOTTE_HEAD_EXPORT_CELLNUMS.length], dcell.getStringCellValue());
            			colNumCnt++;
            		}
        			
        			excelList.add(rowMap);
        		}
        	}
        }
        
        File delfile = new File(ufile.getFullpath());
        //if(delfile.isFile()) delfile.delete();
        
        LOGGER.debug("ReadExcelFile end...." + new Date()); 
        
        return excelList;
    }
    
    @RequestMapping(value="/upload/test",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> exceladdOrderInfoTest(
    		UploadFileVO uploadFile,
    		HttpServletRequest request,
    		HttpServletResponse res,
    		HttpSession jsession)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	/*
    	 * validate request type
    	 */
    	String contextroot = jsession.getServletContext().getRealPath("/");
    	MultipartFile file = uploadFile.getFile();

    	String uploadPath = propertiesService.getString("file.upload.path");
			
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
        
        List excelList = new ArrayList();
         
        for (int i = 0; i < sheetT.getLastRowNum(); i++) {
        	
        	Map rowMap = new HashMap();
        	Row row1 = sheetT.getRow(i);
         
        	for (int j = 0; j < row1.getLastCellNum(); j++) {
        		Cell cell1 = row1.getCell(j);
        		
        		try{
        			if(cell1 != null){
                		System.out.println("row " + i + ", cell " + j + ">" + cell1.getStringCellValue());
                		rowMap.put("COL"+(j+1), cell1.getStringCellValue());
        			}
        		}catch(Exception e)
        		{
        			if(cell1 != null){
                		System.out.println("row " + i + ", cell " + j + ">" + cell1.getNumericCellValue());
                		rowMap.put("COL"+(j+1), cell1.getNumericCellValue());
        			}
        		}
        		
        	}
        	
        	excelList.add(rowMap);
        }
        
        File delfile = new File(ufile.getFullpath());
        if(delfile.isFile())
        	delfile.delete();
        
        log.debug("ReadExcelFile end...." + new Date()); 
        
        MpOrderUploadVO searchVO = new MpOrderUploadVO();
        searchVO.setCorpCode("1001");
        searchVO.setOrdrCust("nds");
        Map exportColMap = mpOrderUploadService.selectMpOrderUploadExcelCol(searchVO);
        
        List exportList = new ArrayList();
        
        boolean isValueFind = false;
        for(int i=0; i < excelList.size(); i++)
        {
        	Map exportResult = new HashMap();
        	isValueFind = false;
        	Map sourceMap = (Map)excelList.get(i);
        	
	        Iterator keys = exportColMap.keySet().iterator();
			String key_name = null;
			while (keys.hasNext()) {
				key_name = (String) keys.next();
				if (exportColMap.get(key_name) != null) {
					//System.out.println("keys name :" + key_name);
					//System.out.println("keys value :" + exportColMap.get(key_name));
					
					//System.out.println("find value :" + sourceMap.get(StringUtils.upperCase(key_name)));
					
					exportResult.put(exportColMap.get(key_name), sourceMap.get(StringUtils.upperCase(key_name)));
					isValueFind = true;
				}
			}
			
			if(isValueFind)
			{
				exportList.add(exportResult);
			}
        }
        
        PageSet pageSet = new PageSet();
        pageSet.setResult(exportList);
        pageSet.setTotalRecordCount(exportList.size());
		
        result.setExtraData(pageSet);
        
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/upload/test/sort",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> exceladdOrderInfoTestSort(
    		UploadFileVO uploadFile,
    		HttpServletRequest request,
    		HttpServletResponse res,
    		HttpSession jsession)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	/*
    	 * validate request type
    	 */
    	String contextroot = jsession.getServletContext().getRealPath("/");
    	MultipartFile file = uploadFile.getFile();

    	String uploadPath = propertiesService.getString("file.upload.path");
			
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
        
        List<EgovMap> excelList = new ArrayList();
         
        for (int i = 0; i <= sheetT.getLastRowNum(); i++) {
        	
        	EgovMap rowMap = new EgovMap();
        	Row row1 = sheetT.getRow(i);
         
        	for (int j = 0; j < row1.getLastCellNum(); j++) {
        		Cell cell1 = row1.getCell(j);
        		
        		try{
        			if(cell1 != null){
                		//System.out.println("row " + i + ", cell " + j + ">" + cell1.getStringCellValue());
                		rowMap.put("COL"+(j+1), cell1.getStringCellValue());
        			}
        		}catch(Exception e)
        		{
        			if(cell1 != null){
                		//System.out.println("row " + i + ", cell " + j + ">" + cell1.getNumericCellValue());
                		rowMap.put("COL"+(j+1), cell1.getNumericCellValue());
        			}
        		}
        		
        	}
        	
        	excelList.add(rowMap);
        }
        
        File delfile = new File(ufile.getFullpath());
        if(delfile.isFile())
        	delfile.delete();
        
        log.debug("ReadExcelFile end...." + new Date()); 
        
        MpOrderUploadVO searchVO = new MpOrderUploadVO();
        searchVO.setCorpCode("1001");
        searchVO.setOrdrCust("13206-003");
        Map exportColMap = mpOrderUploadService.selectMpOrderUploadExcelCol(searchVO);

        String sort_key1 =  (String)exportColMap.get("SORTKEY1");
        String sort_key2 = (String)exportColMap.get("SORTKEY2");
		
        System.out.println("sort_key1 " + sort_key1);
		System.out.println("sort_key2 " + sort_key2);
		
        Collections.sort(excelList, new OrderListComparator(StringUtils.upperCase(sort_key1), StringUtils.upperCase(sort_key2)));
        
        List<EgovMap> exportList = new ArrayList();
        
        boolean isValueFind = false;
        for(int i=0; i < excelList.size(); i++)
        {
        	Map exportResult = new HashMap();
        	isValueFind = false;
        	EgovMap sourceMap = (EgovMap)excelList.get(i);
        	
	        Iterator keys = exportColMap.keySet().iterator();
			String key_name = null;
			while (keys.hasNext()) {
				key_name = (String) keys.next();
				if (exportColMap.get(key_name) != null) {
					//System.out.println("keys name :" + key_name);
					//System.out.println("keys value :" + exportColMap.get(key_name));
					
					//System.out.println("find value :" + sourceMap.get(StringUtils.upperCase(key_name)));
					
					sourceMap.put(exportColMap.get(key_name), sourceMap.get(StringUtils.upperCase(key_name)));
					isValueFind = true;
				}
			}
			
			if(isValueFind)
			{
				exportList.add(sourceMap);
			}
        }
        
        for(Map vo : exportList)
        {
        	System.out.println("ex vo " + vo);
        }
        
        //result = emartMpOrderHService.insertMpOrderHExcel(exportList, new MpOrderDVO());
        
        PageSet pageSet = new PageSet();
        pageSet.setTotalRecordCount(exportList.size());
		
        result.setExtraData(pageSet);
        
        return _filter.makeCORSEntity( result );
    }
}
