package nds.mpm.sales.SD0405.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0405.service.MpOrderHService;
import nds.mpm.sales.SD0405.service.MpOrderUploadService;
import nds.mpm.sales.SD0405.service.OnlineUploadMpOrderHService;
import nds.mpm.sales.SD0405.vo.MpOrderDVO;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import nds.mpm.sales.SD0405.vo.MpOrderUploadVO;
import nds.mpm.sales.SD0405.vo.OrderListComparator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.emory.mathcs.backport.java.util.Collections;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  MpOrderHController
 *
 * @author MPM TEAM
 * @since 2017.08.17
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 온라인 엑셀주문입력( SD0405 ) - 거래처별 주문 생성.
 * 
 * 1. 이마트 하루 2번 2차주문건은 1차주문건을 갱신.( 기존주문과비교 동일한제품은 업데이트, 신규제품은 추가 )
 * 2. 이마트외 중복주문시 해당일 동일제품은 제거 동일날짜. 동일제품은 최종주문만 유효.
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.17	 			최초생성 이마트엑셀양식기준1차개발
 *  2017.10.03	 				롯데 개별양식 엑셀 읽기 추가.
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0405/mporderh")
public class MpOrderHController extends TMMBaseController{
	
    private static final Logger LOGGER = LoggerFactory.getLogger(MpOrderHController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "onlineUploadMpOrderHService")
    private OnlineUploadMpOrderHService onlineUploadMpOrderHService;
    
    @Resource(name = "mpOrderHService")
    private MpOrderHService mpOrderHService;

	@Resource(name = "mpOrderUploadService")
    private MpOrderUploadService mpOrderUploadService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_order_h 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpOrderHDefaultVO
	 * @return "/mpOrderH/MpOrderHList"
	 * @exception Exception
	 */
    @RequestMapping(value="/excel/{custCode}/{proUkind}")
    public ResponseEntity<ResultEx> selectMpOrderHList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("custCode") String custCode,
    		@PathVariable("proUkind") String proUkind,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpOrderHVO searchVO = new MpOrderHVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpOrderHList = mpOrderHService.selectMpOrderHList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpOrderHService.selectMpOrderHListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpOrderHList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/excel/{custCode}/{proUkind}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpOrderH(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("custCode") String custCode,
    		@PathVariable("proUkind") String proUkind,
            @RequestBody List<Map> excelList,
            MpOrderDVO mpOrderDVO,
            HttpServletRequest req
            )
            throws Exception {
    	
        if(excelList == null)
        {
        	return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS ) );
        }
        if(excelList.size() == 0)
        {
        	return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS ) );
        }
        
        ResultEx result = null;
        
        mpOrderDVO.setCorpCode(corpCode);
        //화면 선택거래처 수금취합거래처.
        //mpOrderDVO.setCustCode("13200-000");
        mpOrderDVO.setCustCode("14103-101");
        mpOrderDVO.setProUkind(proUkind);
        
        try{
        	result = insertExcelPost(excelList, mpOrderDVO, req);
        }catch(Exception e)
        {
        	e.printStackTrace();
        	LOGGER.debug("ERROR :: " + e.getMessage());
        }
        
        
        return _filter.makeCORSEntity( result );
    }
    
    public ResultEx insertExcelPost(
    		@RequestBody List<Map> excelList,
            MpOrderDVO mpOrderDVO,
            HttpServletRequest req
    		) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	String delvDate = req.getParameter("delvDate");
        
        MpOrderUploadVO searchVO = new MpOrderUploadVO();
        searchVO.setCorpCode(mpOrderDVO.getCorpCode());
        searchVO.setOrdrCust(mpOrderDVO.getCustCode());
        
        /**
         * cust_code 의 엑셀양식 export 컬럼정보 및 소팅할 컬럼조회.
         * 
         * */
        Map exportColMap = mpOrderUploadService.selectMpOrderUploadExcelCol(searchVO);
        //System.out.println("exportColMap " + exportColMap.toString());
        
        if(exportColMap == null)
        {
        	return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
        }

        List<EgovMap> exportList = new ArrayList();
        
        boolean isValueFind = false;
        
        /**
         * 화면에서 전달된 엑셀데이터의 column 은 COL1,COL2,,,,유형.
         * 화면선택거래처 엑셀양식관리 테이블 mp_order_upload에 설정된 
         * COL1,COL2,,,,매핑 컬럼을 추출하여 주문데이터형성에 사용할 데이터를 수집한다.
         * 
         * */
        for(int i=0; i < excelList.size(); i++)
        {
        	isValueFind = false;
        	EgovMap matchColMap = new EgovMap();
        	matchColMap.put("corpCode", mpOrderDVO.getCorpCode());
        	matchColMap.put("crUser", sess.getUser().getId());
        	matchColMap.put("mdUser", sess.getUser().getId());
        	
        	Map sourceMap = (Map)excelList.get(i);
        	
	        Iterator keys = exportColMap.keySet().iterator();
			String key_name = null;
			
			while (keys.hasNext()) {
				key_name = (String) keys.next();
				if (
					StringUtils.isNotEmpty((String)exportColMap.get(key_name))
					&& key_name.indexOf("HD") == -1
					&& key_name.indexOf("NAME") == -1) {
					
					/**
					System.out.println("find key_name :" + key_name);
					System.out.println("find exportColMap.get(key_name) :" + exportColMap.get(key_name));
					System.out.println("find value :" + sourceMap.get(StringUtils.upperCase(key_name)));
					*/
					if( StringUtils.isNotEmpty((String)sourceMap.get(StringUtils.upperCase(key_name))))
					{
						matchColMap.put(exportColMap.get(key_name), sourceMap.get(StringUtils.upperCase(key_name)));
						isValueFind = true;
					}
						
					//System.out.println("matchColMap :: " + matchColMap);
				}
			}
			
			if(isValueFind)
			{
				exportList.add(matchColMap);
			}
        }
        
        if(exportList == null)
    	{
    		//저장할 데이터 없음.
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	if(exportList.size() == 0)
    	{
    		//저장할 데이터 없음.
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    
    	/**
         * 거래처 기준 주문번호 생성하고 제품은 상세테이블에 거래처주문번호의 시퀀스를 생성
         * 거래처,제품으로 정렬시킴.
         * */
        Collections.sort(exportList, new OrderListComparator("ordrCust", "proCode"));
        
        LOGGER.debug(exportList.toString());
        
        result = onlineUploadMpOrderHService.insertMpOrderHExcel(exportList, mpOrderDVO);
        
        return result ;
    }
   
}
