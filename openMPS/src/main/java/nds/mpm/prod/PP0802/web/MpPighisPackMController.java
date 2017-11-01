package nds.mpm.prod.PP0802.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
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
import nds.mpm.prod.PP0802.service.MpPighisPackMService;
import nds.mpm.prod.PP0802.vo.MpPighisPackMVO;
import nds.mpm.prod.PP0802.vo.MpPighisPackVO;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Class Name :  MpPighisPackMController
 *
 * @author MPM TEAM
 * @since 2017.08.25
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 포장처리실적신고( PP0802 )
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
@RequestMapping("/mpm/{corpCode}/pp0802/mppigpackm")
public class MpPighisPackMController extends TMMBaseController{
	
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPighisPackMController.class);

    @Resource(name = "mpPighisPackMService")
    private MpPighisPackMService mpPighisPackMService;
    
    @Resource(name = "openapiPigService")
	private OpenapiPighisService openapiPigService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    private final String FILENAME_PREFIX = "PTS_PROC_";

    /**
   	 * 신고결과조회 mp_pig_his_pack_m
   	 * @exception
   	 */
       /**
   	 * 신고대상 조회 mp_bar_pro_m
   	 * @exception
   	 */
    @RequestMapping(value="/{packDate}/{sendType}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPighisPackMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("packDate") String packDate,
    		@PathVariable("sendType") String sendType,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpPighisPackMVO searchVO = new MpPighisPackMVO();
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPackDate(StringUtils.remove(packDate,"-"));
    	searchVO.setSearchCondition(sendType);
    	
        List<?> mpPighisBuyMList = null;
        
        if("nsend".equals(sendType))
        {
        	//신고대상조회
        	mpPighisBuyMList = mpPighisPackMService.selectMpBarProMList(searchVO);
        }
        else
        {
        	mpPighisBuyMList = mpPighisPackMService.selectMpPighisPackMList(searchVO);
        }
        
        

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpPighisPackMService.selectMpPighisPackMListTotCnt(searchVO);
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpPighisBuyMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{packDate}/{sendType}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpPighisPackMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("packDate") String packDate,
    		@PathVariable("sendType") String sendType,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpPighisPackMVO searchVO = new MpPighisPackMVO();
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPackDate(StringUtils.remove(packDate,"-"));
    	searchVO.setSearchCondition(sendType);
    	
        List<EgovMap> mpPighisBuyMList = null;
        
        if("nsend".equals(sendType))
        {
        	//신고대상조회
        	mpPighisBuyMList = mpPighisPackMService.selectMpBarProMList(searchVO);
        }
        else
        {
        	mpPighisBuyMList = mpPighisPackMService.selectMpPighisPackMList(searchVO);
        }
        
        String[] columns = PP.PP0802;
        String sheetName = PP.PP0802_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpPighisBuyMList, columns, PP.PP0802_TYPE, sheetName);
    	
    	return null;
    }
    
    /**
     * data save
     * @param 	packDate 
	 * 			
     * */
    @RequestMapping(value="/{packDate}/{sendType}/save",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPighisBuyM(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("packDate") String packDate,
            @RequestBody List<EgovMap> reqmpPlanCmHVOs,
            MpPighisPackMVO mpPighisPackMVO,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	String forceD = req.getParameter("forceD");
    	
    	mpPighisPackMVO.setPackDate(StringUtils.remove(packDate,"-"));
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
    			if(mpPighisPackMService.deleteMpPighisPackM(mpPighisPackMVO) == 0)
    			{
    				if( mpPighisPackMService.checkDupPackDateMpPighisPackM(mpPighisPackMVO) > 0)
    	    		{
    	    			result.setResultCode(Consts.ResultCode.RC_ERROR.getCode());
    	    			result.setMsg("duplicated data delete fail!!");
    	    			return _filter.makeCORSEntity( result );
    	    		}
    			}
    		}
    		else
    		{
    			if( mpPighisPackMService.checkDupPackDateMpPighisPackM(mpPighisPackMVO) > 0)
	    		{
	    			result.setResultCode(Consts.ResultCode.RC_DUPLICATE.getCode());
	    			return _filter.makeCORSEntity( result );
	    		}
    		}
    		
    		for(EgovMap vo : reqmpPlanCmHVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("packDate",StringUtils.remove(packDate,"-"));
    			if(sess != null)
    			{
    				vo.put("mdUser",sess.getUser().getId());
    				vo.put("crUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	result = mpPighisPackMService.insertMpPighisPackM(ilist);
    	
        return _filter.makeCORSEntity( result );
    }
    
    /**
     * 신고파일생성.
     * @param 	packDate 포장처리일자 
	 * 			sendType nsend신고대상, send신고결과 
     * 
     * */
    @RequestMapping(value="/{packDate}/{sendType}/download",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> downloadMpPighisBuyM(
    		@PathVariable("packDate") String packDate,
    		@PathVariable("sendType") String sendType,
    		MpPighisPackMVO searchVO,
            HttpServletRequest req,
            HttpServletResponse res,
            HttpSession jsession
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	String userPath = sess.getUser().getId();
    	
    	String contextroot = jsession.getServletContext().getRealPath("/");
    	String uploadPath = propertiesService.getString("file.upload.path") + "/" + userPath;
    	/*
    	 * validate request type
    	 */
    	String searchCondition = req.getParameter("searchCondition");
        /**
        // BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
        // 파일안에 문자열 쓰기
         * 신고 API 파일전송 포맷 .
         * 
        //T1|포장일자|묶음번호|표준부위코드|표준부위명|무게
		title
		,pack_date
		,bunch_no
		,base_part_code
		,base_part_name
		,pack_weig
         **/
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setPackDate(StringUtils.remove(packDate,"-"));
    	//신고하지않은 건만
    	searchVO.setSearchCondition("nsend");
    	
    	List<EgovMap> mpPlanCmHVOs = mpPighisPackMService.selectMpPighisPackMSendFormatList(searchVO);
    	
 		StringBuffer creatFileCont = new StringBuffer();
 		
 		if(mpPlanCmHVOs != null && mpPlanCmHVOs.size() > 0)
 		{
 			for(EgovMap vo : mpPlanCmHVOs)
 	 		{
 	 			if("T1".equals((String)vo.get("title")))
 	 			{
 	 				creatFileCont.append( "T1"+"|" + StringUtils.remove(packDate,"-"));
 	 	 			creatFileCont.append( "|" + vo.get("bunchNo") );
 	 	 			creatFileCont.append( "|||||416001" );
 	 	 			creatFileCont.append( "\r\n" );
 	 			}
 	 			else if("T2".equals((String)vo.get("title")))
 	 			{
 	 				creatFileCont.append( "T2"+ "|" + vo.get("packDate") );
 	 				creatFileCont.append( "|" + vo.get("bunchNo") );
 	 	 			creatFileCont.append( "|" + vo.get("basePartCode") );
 	 	 			creatFileCont.append( "|" + vo.get("basePartName") );
 	 	 			creatFileCont.append( "|" + vo.get("packWeig") );
 	 				creatFileCont.append( "\r\n" );
 	 			}
 	 			else if("T3".equals((String)vo.get("title")))
 	 			{
 	 				creatFileCont.append( "T3"+ "|" + vo.get("packDate") );
 	 				creatFileCont.append( "|" + vo.get("bunchNo") );
 	 	 			creatFileCont.append( "|"  );
 	 				creatFileCont.append( "\r\n" );
 	 			}
 	 			
 	 		}
 	 		
 			String filePath = contextroot + uploadPath ;
 	    	String filename = FILENAME_PREFIX + StringUtils.remove(packDate, "-") + ".txt";
 	    	String originalfilename = filename;
 	 		String txtFileFull = filePath + "/" + filename;
 	 		
 	 		LOGGER.debug("file create userPath :: " + userPath);
 	 		LOGGER.debug("file create start :: " + txtFileFull);
 	 		
 	 		File checkDir = new File(filePath);
 			if(!checkDir.exists())
 				checkDir.mkdirs();
 	 		
 			//축산물이력제 신고 사이트, 파일신고시 EUC_KR 인코딩파일이 아니면 한글깨짐.
 			BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFileFull),"EUC_KR"));  
 
 	 		fw.write(creatFileCont.toString());
 	 		fw.flush();
 	         
 	    	FileUtil.downloadFile(filePath, filename, originalfilename, res);
 		}
 		else
 		{
 			result = new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
 		}
    	
 		return null;
    }
    
    /***
     * 
     * 포장처리실적 OPEN API 신고 호출 
     * 
     * */
    @RequestMapping(value="/openapi/send/{packDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> sendMpPighisBuyMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("packDate") String packDate,
    		MpPighisPackMVO mpPighisPackMVO,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	
    	mpPighisPackMVO.setPackDate(StringUtils.remove(packDate,"-"));
    	//신고하지않은 건만
    	mpPighisPackMVO.setSearchCondition("nsend");
    	/**TODO
    	 * TEST
    	 * */
    	//mpPighisPackMVO.setBasePartCode("430421");
    	
    	
    	List<EgovMap> mpPighisBuyVOs = mpPighisPackMService.selectMpPighisPackMList(mpPighisPackMVO);
    	List<MpPighisPackVO> sendFormVOs = new ArrayList();
    	
    	for(EgovMap vo : mpPighisBuyVOs)
    	{
    		MpPighisPackVO svo = new MpPighisPackVO();
    		
    		svo.setPack_date((String)vo.get("packDate"));
    		svo.setBunch_no((String)vo.get("bunchNo"));
    		svo.setBase_part_code((String)vo.get("basePartCode"));
    		svo.setBase_part_name((String)vo.get("basePartName"));
    		svo.setPack_weig(Double.parseDouble(StringUtils.defaultIfEmpty(vo.get("packWeig")+"","0")));
    		
    		sendFormVOs.add(svo);
    	}
    	
    	//신고할 건 없음.
    	if(sendFormVOs.size() == 0)
    	{
    		return _filter.makeCORSEntity(new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND ) );
    	}
    	
    	LOGGER.debug("opapi send packData check :::: "  );
    	for(MpPighisPackVO vo : sendFormVOs)
    	{
    		
    		LOGGER.debug("packDate :: " + vo.getPack_date() + "-bunchNo :::: " + vo.getBunch_no()+ "-basePartCode :::: " + vo.getBase_part_code() );
    		
    		
    	}
    	LOGGER.debug("opapi send packData end :::: "  );
    	
    	List<OpenapiPighisResultVO> resultVo = openapiPigService.declarePighisPack(sendFormVOs);
    	
    	if(resultVo != null && resultVo.size() > 0)
    	{
    		OpenapiPighisResultVO firstRow = resultVo.get(0);
    		
    		LOGGER.debug("OpenapiPighisResultVO :: " + new ObjectMapper().writeValueAsString(firstRow) );
    		
    		if("Y".equals(firstRow.getCheckYn()))
			{
    			for(EgovMap vo : mpPighisBuyVOs)
    	    	{
    				vo.put("mdUser", sess.getUser().getId());
    				vo.put("memo", "success");
    				vo.put("searchCondition", "success");
    				mpPighisPackMService.updateApiTimeMpPighisPackM(vo);
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
					if(memo != null && memo.length() > 100)
					memo = memo.substring(0, 100);
				}
    			
    			for(EgovMap vo : mpPighisBuyVOs)
    	    	{
    				vo.put("memo", memo);
    				vo.put("searchCondition", "error");
    				mpPighisPackMService.updateApiTimeMpPighisPackM(vo);
    	    	}
    			
    			result.setMsg(memo);
    			result.setExtraData(resultVo);
    			result.setResultCode(Consts.ResultCode.RC_ERROR.getCode());
    		}
    	}
    	else
    	{
    		String memo = "포장처리실적신고openapi 응답메시지없음. 오류";
    		for(EgovMap vo : mpPighisBuyVOs)
	    	{
				vo.put("memo", memo);
				vo.put("searchCondition", "error");
				mpPighisPackMService.updateApiTimeMpPighisPackM(vo);
	    	}
    		
    		result.setExtraData(memo);
			result.setResultCode(Consts.ResultCode.RC_ERROR.getCode());
    	}
    	
    	result.setExtraData(resultVo);
    	
    	return _filter.makeCORSEntity( result );
    }
    /***
     * 
     * 포장처리실적 OPEN API 신고 호출 테스트 
     * 
     * */
    @RequestMapping(value="/openbuy/test",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPighisBuyMList(
    		MpPighisPackMVO mpPighisPackMVO,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	mpPighisPackMVO.setCorpCode("1001");
    	mpPighisPackMVO.setPackDate("20170801");
    	
    	mpPighisPackMVO.setSearchCondition2("test");
    	
    	List<EgovMap> mpPighisBuyVOs = mpPighisPackMService.selectMpPighisPackMList(mpPighisPackMVO);
    	List<MpPighisPackVO> sendFormVOs = new ArrayList();
    	
    	for(EgovMap vo : mpPighisBuyVOs)
    	{
    		MpPighisPackVO svo = new MpPighisPackVO();
    		
    		svo.setPack_date((String)vo.get("packDate"));
    		svo.setBunch_no((String)vo.get("bunchNo"));
    		svo.setBase_part_code((String)vo.get("basePartCode"));
    		svo.setBase_part_name((String)vo.get("basePartName"));
    		svo.setPack_weig(Double.parseDouble(StringUtils.defaultIfEmpty(vo.get("packWeig")+"","0")));
    		
    		sendFormVOs.add(svo);
    	}
    	/**
    	 * ONLY TEST
    	 * 
		List<MpPighisBuyVO> list = (ArrayList<MpPighisBuyVO>)openapiPigService.getPighisBuy();*/
		/**
		 * */
    	System.out.println(  openapiPigService.declarePighisPack(sendFormVOs) );
    	return null;
    }
}
