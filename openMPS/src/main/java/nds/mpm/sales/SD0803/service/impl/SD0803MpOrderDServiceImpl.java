package nds.mpm.sales.SD0803.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0405.service.MpOrderHDAO;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import nds.mpm.sales.SD0803.service.SD0803MpOrderDDAO;
import nds.mpm.sales.SD0803.service.SD0803MpOrderDService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
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

@Service("SD0803MpOrderDService")
public class SD0803MpOrderDServiceImpl extends EgovAbstractServiceImpl implements
SD0803MpOrderDService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0803MpOrderDServiceImpl.class);

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
    public ResultEx insertMpOrderH(MpOrderHVO mpOrderHVO) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	/**
    	 * 단가변경 여부 체크 
    	 * 
    	 * */
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
    
    public void recreateOrder(MpOrderHVO mpOrderHVO) throws Exception{
    	
    	String ordrCust = mpOrderHVO.getOrdrCust();
    	String ordrType = mpOrderHVO.getOrdrType();
    	
    	/**
    	 * 판매주문
    	 * 	회송주문생성.
    	 * 	매출주문생성.
    	 * 
    	 * */
    	if("10".equals(ordrType))
    	{
    		EgovMap newOrder = new EgovMap();
        	newOrder.put("corpCode", mpOrderHVO.getCorpCode());
        	newOrder.put("delvDate", mpOrderHVO.getDelvDate());
        	
        	String newOrderNo = mpOrderHDAO.selectMpOrderNo(newOrder);
        	if(newOrderNo == null)
        	{
        		throw new Exception("return order key fail!!");
        	}
    		
        	mpOrderHVO.setReturnDelvDate(mpOrderHVO.getDelvDate());
        	mpOrderHVO.setReturnOrdrNo(newOrderNo);
        	mpOrderHVO.setMemo(mpOrderHVO.getOrdrNo());
        	
        	if(mpOrderDDAO.insertReturnMpOrderH(mpOrderHVO) == null)
        	{
        		throw new Exception("return orderH fail!!");
        	}
        	if(mpOrderDDAO.insertReturnMpOrderD(mpOrderHVO) == null)
        	{
        		throw new Exception("return orderD fail!!");
        	}
        	
        	String newOrderNo2 = mpOrderHDAO.selectMpOrderNo(newOrder);
        	if(newOrderNo2 == null)
        	{
        		throw new Exception("new order key fail!!");
        	}
        	
        	mpOrderHVO.setNewDelvDate(mpOrderHVO.getDelvDate());
        	mpOrderHVO.setNewOrdrNo(newOrderNo2);
        	if(mpOrderDDAO.insertReCreateMpOrderD(mpOrderHVO) == null)
        	{
        		throw new Exception("new orderD fail!!");
        	}
        	if(mpOrderDDAO.insertReCreateMpOrderH(mpOrderHVO) == null)
        	{
        		throw new Exception("new orderH fail!!");
        	}
    	}
    	/**
    	 * 판매주문
    	 * 	회송주문생성.
    	 * 	매출주문생성.
    	 * 
    	 * */
    	else if("50".equals(ordrType))
    	{
    		EgovMap newOrder = new EgovMap();
        	newOrder.put("corpCode", mpOrderHVO.getCorpCode());
        	newOrder.put("delvDate", mpOrderHVO.getDelvDate());
        	
        	String newOrderNo = mpOrderHDAO.selectMpOrderNo(newOrder);
        	if(newOrderNo == null)
        	{
        		throw new Exception("return order key fail!!");
        	}
    		
        	mpOrderHVO.setNewDelvDate(mpOrderHVO.getDelvDate());
        	mpOrderHVO.setNewOrdrNo(newOrderNo);
        	if(mpOrderDDAO.insertReturnReCreateMpOrderD(mpOrderHVO) == null)
        	{
        		throw new Exception("new orderD fail!!");
        	}
        	if(mpOrderDDAO.insertReCreateMpOrderH(mpOrderHVO) == null)
        	{
        		throw new Exception("new orderH fail!!");
        	}
        	
        	String newOrderNo2 = mpOrderHDAO.selectMpOrderNo(newOrder);
        	
        	/**
        	 * 신규생성된 회송매출의 주문번호셋팅.
        	 * */
        	mpOrderHVO.setOrdrNo(newOrderNo);
        	mpOrderHVO.setReturnDelvDate(mpOrderHVO.getDelvDate());
        	mpOrderHVO.setReturnOrdrNo(newOrderNo2);
        	mpOrderHVO.setMemo(newOrderNo);
        	
        	if(mpOrderDDAO.insertReturnMpOrderH(mpOrderHVO) == null)
        	{
        		throw new Exception("return orderH fail!!");
        	}
        	if(mpOrderDDAO.insertReturnMpOrderD(mpOrderHVO) == null)
        	{
        		throw new Exception("return orderD fail!!");
        	}
    	}
    	
    }

    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDList(MpOrderHVO searchVO) throws Exception {
        return mpOrderDDAO.selectMpOrderDList(searchVO);
    }

    /**
	 * mp_order_d 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 총 갯수
	 * @exception
	 */
    public int selectMpOrderDListTotCnt(MpOrderHVO searchVO) {
		return mpOrderDDAO.selectMpOrderDListTotCnt(searchVO);
	}
    
    
    
}
