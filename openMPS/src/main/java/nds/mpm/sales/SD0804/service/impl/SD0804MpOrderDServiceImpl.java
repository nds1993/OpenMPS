package nds.mpm.sales.SD0804.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0405.service.MpOrderHDAO;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import nds.mpm.sales.SD0803.service.SD0803MpOrderDDAO;
import nds.mpm.sales.SD0803.service.impl.SD0803MpOrderDServiceImpl;
import nds.mpm.sales.SD0804.service.SD0804MpOrderDService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderDServiceImpl.java
 * @Description : MpOrderD Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD0804MpOrderDService")
public class SD0804MpOrderDServiceImpl extends SD0803MpOrderDServiceImpl implements
SD0804MpOrderDService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0804MpOrderDServiceImpl.class);

    @Resource(name="mpOrderHDAO")
    private MpOrderHDAO mpOrderHDAO;
    
    @Resource(name="SD0803MpOrderDDAO")
    private SD0803MpOrderDDAO mpOrderDDAO;
    
    /**
     * 사전조건 생성할 주문의 제품단가와 현재일의 단가가 차이가 있어야한다. 
     * 
     * 1. 기존주문에대한 회송주문 생성. minus 매출
     * 2. 기존주문에대한 신규주문 생성. 변경된 단가적용 매출.
     * 
     * */
    public ResultEx insertMpOrderH(MpOrderHVO mpOrderHVO, List<EgovMap> mpOrderDVOs) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	/**
    	 * 단가변경 여부 체크 
    	 * 
    	 * */
    	// 조건에 셋팅될 제품코드 변수생성.
    	List<String> proCodes = new ArrayList();
    	
    	for(EgovMap vo : mpOrderDVOs)
    	{
    		if(StringUtils.isEmpty((String)vo.get("proCode")))
    		{
    			result.setMsg("procode empty!!");
    			result.setResultCode(Consts.ResultCode.RC_INVALID_PARAMS.getCode());
    			return result;
    		}
    		
    		proCodes.add((String)vo.get("proCode"));
    	}
    	
    	if(proCodes.size() > 0)
    		mpOrderHVO.setProCodes(proCodes);
    	else
    	{
    		result.setMsg("procode empty!!");
    		result.setResultCode(Consts.ResultCode.RC_INVALID_PARAMS.getCode());
			return result;
    	}
    		
    	
    	List<EgovMap> checks = (List<EgovMap>)mpOrderDDAO.selectMpOrderDList(mpOrderHVO);
    	
    	if(checks == null){
    		result.setResultCode(Consts.ResultCode.RC_FIND_NOT_FOUND.getCode());
    		return result;
    	}
    	if(checks.size() == 0){
    		result.setResultCode(Consts.ResultCode.RC_FIND_NOT_FOUND.getCode());
    		return result;
    	}
    	
    	for(EgovMap vo : checks)
    	{
    		if(!"Y".equals( (String)vo.get("priceChgYn") ))
			{
    			//-101 current day price not changed
    			result.setResultCode(Consts.ResultCode.RC_DENINED.getCode());
    			result.setMsg("price not changed");
    			break;
			}
    		
    	}
    	
    	EgovMap checkMap = checks.get(0);
    	String ordrCust = (String)checkMap.get("ordrCust");
    	String ordrType = (String)checkMap.get("ordrType");
    	
    	mpOrderHVO.setOrdrCust(ordrCust);
    	mpOrderHVO.setOrdrType(ordrType);
    	
    	/**
    	 * 회송, 매출 주문 생성호출.
    	 * if error Exception throws
    	 * 
    	 * */
    	recreateOrder(mpOrderHVO);
    	
    	PageSet pageSet = new PageSet();
    	/**
    	 * 최종 주문리스트 재조회.
    	 * 
    	 * */
    	List<?> results = mpOrderDDAO.selectMpOrderDList(mpOrderHVO);
    	
    	if(results != null)
    		pageSet.setTotalRecordCount(results.size());
    	pageSet.setResult(results);
    	result.setExtraData(pageSet);  	
    	
    	return result;
    }
    
}
