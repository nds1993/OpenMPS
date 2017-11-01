package nds.mpm.sales.SD0404.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.common.vo.CommonFileVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.vo.UploadFileVO;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0401.service.SD0401Service;
import nds.mpm.sales.SD0404.service.SD0404Service;
import nds.mpm.sales.SD0405.service.impl.OnlineUploadMpOrderHServiceImpl;
import nds.mpm.sales.SD0405.vo.MpOrderDVO;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import nds.mpm.sales.SD0405.vo.OrderListComparator;

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
 * @Class Name :  SD0401Controller
 *
 * @author MPM TEAM
 * @since 2017.08.25
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : PM주문입력 ( SD0404 ) 
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
@RequestMapping("/mpm/{corpCode}/sd0404")
public class SD0404Controller extends TMMBaseController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0404Controller.class);

    @Resource(name = "SD0404Service")
    private SD0404Service SD0404Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "XSSFexcelService") 
    private EgovExcelService excelService;
    
    private static final int EXCEL_READ_STARTROW = 2;
    private static final int[] EXCEL_EXPORT_CELLNUMS = 
    	{0,1,2,3,4
    	,5,6,7};
    private static final String[] EXCEL_EXPORT_COL_NAMES = 
    	{"delvDate","ordrCust","proCode","ordrBox","ordrWeight"
    	,"unitPrice","ordrAmt","ordrVat"};
	
    /**
	 * mp_order_h 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SD0401DefaultVO
	 * @return "/SD0401/SD0401List"
	 * @exception Exception
	 */
    @RequestMapping(value="/mporderh/{strtDate}/{ordrCust}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSD0401List(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("ordrCust") String ordrCust,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
        
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
        
        String delvDc = req.getParameter("delvDc");
    	
    	MpOrderHVO searchVO = new MpOrderHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setOrdrCust(ordrCust);
    	searchVO.setDelvDc(delvDc);
    	
        List<?> SD04014List = SD0404Service.selectSD0404List(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = SD0404Service.selectSD0404ListTotCnt(searchVO);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(SD04014List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /***
     * excel load
     * 
     * 
     * */
    @RequestMapping(value="/excel/upload",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> exceladdOrderInfo(
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
        
        List<EgovMap> exportList = new ArrayList();
        
        boolean findData = false;
        for (int row = EXCEL_READ_STARTROW; row <= sheetT.getLastRowNum(); row++) {
        	
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
    		if(findData)
    			exportList.add(exportVO);
        }
        
       //File delfile = new File(ufile.getFullpath());
       //if(delfile.isFile())
       // 	delfile.delete();
        
        //mpPighisBuyMService.insertMpPighisBuyM(exportList);
        
        log.debug("ReadExcelFile end...." + new Date()); 
        
        PageSet pageSet = new PageSet();
        pageSet.setResult(exportList);
        pageSet.setTotalRecordCount(exportList.size());
		
        result.setExtraData(pageSet);
        
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/mporderh/{strtDate}/{ordrCust}/{delvDc}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addSD0404(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("ordrCust") String ordrCust,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("delvDc") String delvDc,
    		MpOrderHVO mpOrderHVO,
    		MpOrderDVO mpOrderDVO,
    		@RequestBody List<EgovMap> sD0404VO,
    		HttpServletRequest req,
    		HttpServletResponse res
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	/**
    	mpOrderHVO.setCorpCode(corpCode);
    	mpOrderHVO.setDelvDate(strtDate);
    	mpOrderHVO.setDelvDc(delvDc);
    	mpOrderHVO.setCrUser(sess.getUser().getId());
    	
    	mpOrderDVO.setCorpCode(corpCode);
    	mpOrderHVO.setDelvDate(strtDate);
    	mpOrderDVO.setCrUser(sess.getUser().getId());
    	
    	result = SD0404Service.insertMpOrderH(sD0404VO, mpOrderHVO, mpOrderDVO);
    	*/
    	
    	List ilist = new ArrayList();
    	//중복검색
    	if(sD0404VO != null)
    	{
    		for(EgovMap vo : sD0404VO)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("ordrType","10");
    			vo.put("delvType","2");
    			vo.put("approYn", "Y"); //결재상태고정.
    			vo.put("amtDisplay", "Y"); //금액표시고정
    			vo.put("limitYn", "N"); //경과일수 제외고정 
    			vo.put("delvDc", delvDc);
    			
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser", sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	/**
         * 거래처 기준 주문번호 생성하고 제품은 상세테이블에 거래처주문번호의 시퀀스를 생성
         * 거래처,제품으로 정렬시킴.
         * */
        Collections.sort(ilist, new OrderListComparator("ordrCust", "proCode"));
    	
    	result = SD0404Service.insertMpOrderHExcel(ilist, mpOrderDVO);
    	
    	return _filter.makeCORSEntity( result );
    }
}
