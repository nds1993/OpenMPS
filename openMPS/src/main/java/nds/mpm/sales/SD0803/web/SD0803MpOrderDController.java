package nds.mpm.sales.SD0803.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0401.service.SD0401Service;
import nds.mpm.sales.SD0401.vo.MultiSD0401VO;
import nds.mpm.sales.SD0401.vo.SD0401DefaultVO;
import nds.mpm.sales.SD0401.vo.SD0401VO;
import nds.mpm.sales.SD0405.service.MpOrderHService;
import nds.mpm.sales.SD0405.vo.MpOrderDDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderDVO;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import nds.mpm.sales.SD0803.service.SD0803MpOrderDService;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * @Class Name :  SD0401Controller
 *
 * @author MPM TEAM
 * @since 2017.08.31
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 단가수정 ( SD0804 ) 
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.31	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0803/mporderh")
public class SD0803MpOrderDController extends TMMBaseController {

    @Resource(name = "SD0803MpOrderDService")
    private SD0803MpOrderDService mpOrderDService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * 주문 제품목록조회
	 * @param searchVO - 조회할 정보가 담긴 SD0401DefaultVO
	 * @return 
	 * @exception Exception
	 */
    @RequestMapping(value="/{delvDate}/{ordrNo}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSD0401List(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("delvDate") String delvDate,
    		@PathVariable("ordrNo") String ordrNo,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
        
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        MPUserSession sess = getSession(req);
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpOrderHVO searchVO = new MpOrderHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setDelvDate(delvDate);
    	searchVO.setOrdrNo(ordrNo);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> SD0401List = mpOrderDService.selectMpOrderDList(searchVO);

    	PageSet pageSet = new PageSet();
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	
    	if(SD0401List!=null)
    	pageSet.setTotalRecordCount(SD0401List.size());
    	pageSet.setResult(SD0401List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
   
    /**
     * 출고일자, 주문번호기준 
     * 회송주문, 매출주문생성
     * 해당주문건의 모든 상세제품을 조회 및 재생성.
     * 
     * */
    @RequestMapping(value="/{delvDate}/{ordrNo}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addSD0401(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("delvDate") String delvDate,
    		@PathVariable("ordrNo") String ordrNo,
    		MpOrderHVO mpOrderHVO,
    		HttpServletRequest req,
    		HttpServletResponse res
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	mpOrderHVO.setDelvDate(StringUtils.remove(delvDate,"-"));
    	mpOrderHVO.setOrdrNo(ordrNo);
    	
    	mpOrderDService.insertMpOrderH(mpOrderHVO);

    	return _filter.makeCORSEntity( result );
    }

}
