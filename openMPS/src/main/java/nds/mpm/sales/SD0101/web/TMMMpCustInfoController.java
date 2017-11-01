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
@RequestMapping("/tmm/{corpCode}/sd0101/mpcustinfo")
public class TMMMpCustInfoController extends TMMBaseController{
	
	private static final Logger _logger = LoggerFactory.getLogger(TMMMpCustInfoController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpCustInfoService")
    private MpCustInfoService mpCustInfoService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /**
     * 콤보리스트 데이터 호출 api 추가, sd0101과 동일 거래처 코드
     * 
     * 화면 PP0803 사용 
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
    	
    	String searchKeyword = req.getParameter("searchKeyword");
    	String custCode = req.getParameter("custCode");
    	
    	MpCustInfoVO searchVO = new MpCustInfoVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSalesman(salesman);
    	searchVO.setCustCode(custCode);
    	searchVO.setSearchKeyword(searchKeyword);
    	
        List<?> mpCustInfoList = mpCustInfoService.selectMpCustInfoCodeList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	pageSet.setResult(mpCustInfoList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
}
