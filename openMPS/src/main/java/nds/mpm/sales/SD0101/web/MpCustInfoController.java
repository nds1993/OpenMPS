package nds.mpm.sales.SD0101.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.frm.component.excel.ExcelUtil;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0101.service.MpCustInfoService;
import nds.mpm.sales.SD0101.vo.MpCustInfoDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustInfoMultiVO;
import nds.mpm.sales.SD0101.vo.MpCustInfoVO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  MpCustInfoController
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 거래처정보( SD0101 )
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
@RequestMapping("/mpm/{corpCode}/sd0101/mpcustinfo")
public class MpCustInfoController extends TMMBaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MpCustInfoController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpCustInfoService")
    private MpCustInfoService mpCustInfoService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_cust_info 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpCustInfoDefaultVO
	 * @return "/mpCustInfo/MpCustInfoList"
	 * @exception Exception
	 */
    @RequestMapping(value="")
    public ResponseEntity<ResultEx> selectMpCustInfoList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String closeYn = req.getParameter("closeYn");
    	String teamCode = req.getParameter("teamCode");
    	String salesman = req.getParameter("salesmanName");
    	String custCode = req.getParameter("custCode");
    	String custType = req.getParameter("custType");
    	
    	MpCustInfoVO searchVO = new MpCustInfoVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setCloseYn(closeYn);
    	searchVO.setTeamCode(teamCode);
    	searchVO.setSalesman(salesman);
    	searchVO.setCustCode(custCode);
    	searchVO.setCustType(custType);
    	
    	searchVO.setPageSize(pageSize);
    	searchVO.setPageIndex(pageIndex);
    	
    	LOGGER.debug("startOrder :: " + searchVO.getStartOrder());
    	
        List<?> mpCustInfoList = mpCustInfoService.selectMpCustInfoList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpCustInfoService.selectMpCustInfoListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpCustInfoList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    /**
     * 거래처 코드
     * 
     * return code, label format
     * 
     * **/
    @RequestMapping(value="/{salesman}/code")
    public ResponseEntity<ResultEx> selectMpCustInfoCodeList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("salesman") String salesman,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String searchCondition = req.getParameter("searchCondition");
    	String searchKeyword = req.getParameter("searchKeyword");
    	String custCode = req.getParameter("custCode");
    	String custType = req.getParameter("custType");
    	
    	MpCustInfoVO searchVO = new MpCustInfoVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSalesman(salesman);
    	searchVO.setCustCode(custCode);
    	searchVO.setSearchKeyword(searchKeyword);
    	searchVO.setCustType(custType);
    	
        List<?> mpCustInfoList = mpCustInfoService.selectMpCustInfoCodeList(searchVO);

        if("upload".equals(searchCondition))
        {
        	mpCustInfoList = mpCustInfoService.selectOnlineUploadMpCustInfoCodeList(searchVO);
        }
        else
        {
        	mpCustInfoList = mpCustInfoService.selectMpCustInfoCodeList(searchVO);
        }
        
    	PageSet pageSet = new PageSet();
        
    	if(mpCustInfoList != null)
    		pageSet.setTotalRecordCount(mpCustInfoList.size());
    	pageSet.setResult(mpCustInfoList);
		
    	result.setExtraData(pageSet);
    	
    	/**
    	String contextroot = jsession.getServletContext().getRealPath("/");
    	String uploadPath = propertiesService.getString("file.upload.path")  + "/test";
    	
    	File dir = new File(contextroot + uploadPath);
    	if(!dir.exists())
    		dir.mkdir();
    	
    	String fullpath = contextroot + uploadPath + "/test.xls";
    	String[] column = {"code","label"};
    	String[] columnType = {"S","S"};

    	ExcelUtil.createExcel(column, columnType, (ArrayList)mpCustInfoList, fullpath, "test");
    	**/
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    /**
     * 거래처 생성
     * 
     * **/
    @RequestMapping(value="/{custCode}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpCustInfo(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("custCode") String custCode,
    		@RequestBody MpCustInfoMultiVO mpCustInfoMultiVO,
    		HttpServletRequest req
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	EgovMap custinfo = mpCustInfoMultiVO.getCustinfo();
    	custinfo.put("corpCode", corpCode);
    	custinfo.put("custCode", custCode);
    	
        result = mpCustInfoService.insertMpCustInfo(mpCustInfoMultiVO, sess);
        return _filter.makeCORSEntity( result );
    }
    
    /**
     * 거래처 중복체크 .
     * resultCode == -4 중복
     * 
     * **/
    @RequestMapping(value="/dup/{custCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpCustInfo(
    		@RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("custCode") java.lang.String custCode ,
            MpCustInfoVO mpCustInfoVO
            ) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	mpCustInfoVO.setCorpCode(corpCode);
    	mpCustInfoVO.setCustCode(custCode);
    	
    	if( mpCustInfoService.selectMpCustInfo(mpCustInfoVO) != null){
    		return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_DUPLICATE ) );
    	}
        return _filter.makeCORSEntity( result );
    }
    
    /**
     * 거래처 계좌정보
     * 
     * 영업본부
     * 영업파트
     * 영업자
     * 은행코드
     * 은행명
     * 입금자
     * 여신한도
     * 당월입금액
     * 미수
     * 잔액 
     * 
     * **/
    @RequestMapping(value="/account/{custCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpCustAccountInfo(
    		@PathVariable("corpCode") java.lang.String corpCode ,
    		@PathVariable("custCode") java.lang.String custCode ,
            MpCustInfoVO mpCustInfoVO
            ) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	mpCustInfoVO.setCorpCode(corpCode);
    	mpCustInfoVO.setCustCode(custCode);
    	
    	EgovMap resultMap = mpCustInfoService.selectMpCustAccountInfo(mpCustInfoVO);
    	
    	if(resultMap == null)
    	{
    		result.setResultCode(Consts.ResultCode.RC_FIND_NOT_FOUND.getCode());
    		result.setMsg(Consts.ResultCode.RC_FIND_NOT_FOUND.getDesc());
    	}
    	else
    	{
        	result.setExtraData(resultMap);
    	}
        return _filter.makeCORSEntity( result );
    }

    /**
     * 거래처 정보수정. 아래정보 제외. 화면기준.
     * 
     brand_code character varying(10), -- 브랜드 - SD013
  	 fac_kind character varying(10), -- 정산구분 - SD014
  	 fac_code character varying(10), -- 정산방법 - 생돈정산 Table 참조
     * 
     * **/
    @RequestMapping(value="",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateMpCustInfo(
            @RequestBody EgovMap mpCustInfoVO,
            HttpServletRequest req
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        mpCustInfoService.updateMpCustInfo(mpCustInfoVO);
        return _filter.makeCORSEntity( result );
    }
    
    /**
     * 거래처 정보수정.
     * 
     brand_code character varying(10), -- 브랜드 - SD013
  	 fac_kind character varying(10), -- 정산구분 - SD014
  	 fac_code character varying(10), -- 정산방법 - 생돈정산 Table 참조
     * 
     * **/
    @RequestMapping(value="/fac",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateMpCustInfoFac(
            @RequestBody MpCustInfoVO mpCustInfoVO,
            HttpServletRequest req
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        mpCustInfoService.updateMpCustInfoFac(mpCustInfoVO);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteMpCustInfo(
            MpCustInfoVO mpCustInfoVO
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        mpCustInfoService.deleteMpCustInfo(mpCustInfoVO);
        return _filter.makeCORSEntity( result );
    }

}
