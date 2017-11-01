package nds.mpm.prod.PP0302.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0302.service.MpPlanCmDDAO;
import nds.mpm.prod.PP0302.service.MpPlanCmHDAO;
import nds.mpm.prod.PP0302.service.MpPlanCmHService;
import nds.mpm.prod.PP0302.vo.MpPlanCmDVO;
import nds.mpm.prod.PP0302.vo.MpPlanCmHDefaultVO;
import nds.mpm.prod.PP0302.vo.MpPlanCmHVO;
import nds.mpm.prod.PP0302.vo.MultiMpPlanCmHVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPlanCmHServiceImpl.java
 * @Description : MpPlanCmH Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPlanCmHService")
public class MpPlanCmHServiceImpl extends EgovAbstractServiceImpl implements
        MpPlanCmHService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPlanCmHServiceImpl.class);

    @Resource(name="mpPlanCmHDAO")
    private MpPlanCmHDAO mpPlanCmHDAO;
    
    @Resource(name="mpPlanCmDDAO")
    private MpPlanCmDDAO mpPlanCmDDAO;
    
    public String selectMpPlanCmHCanNewCmPlan(MpPlanCmHDefaultVO searchVO) {
    	return mpPlanCmHDAO.selectMpPlanCmHCanNewCmPlan(searchVO);
    }
    
    /**
	 * 작업일 신규 생성.
	 */
    public ResultEx insertNewMpPlanCmH(MpPlanCmHVO searchVO) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	searchVO.setPlantCode(null);
    	List<EgovMap> mpPlanCmHList = mpPlanCmHDAO.selectNewMpPlanCmHList(searchVO);
        
    	for(EgovMap inMap : mpPlanCmHList)
    	{
    		inMap.put("corpCode", searchVO.getCorpCode());
    		inMap.put("workDate", searchVO.getWorkDate());
    		inMap.put("crUser", searchVO.getCrUser());
    		mpPlanCmDDAO.insertMpPlanCmD(inMap);
    	}
    	
    	mpPlanCmHDAO.insertNewMpPlanCmHeader(searchVO);
    	
    	return result;
    }
	
    
	/**
	 * 수정 저장
	 */
    public ResultEx insertMpPlanCmH(MultiMpPlanCmHVO multiMpPlanCmHVO) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpPlanCmHVO head = multiMpPlanCmHVO.getHead();
    	List<EgovMap> vos = multiMpPlanCmHVO.getDetail();
    	
    	if(head == null)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	
    	if( vos != null)
    	{
    		for(int i = 0; i<vos.size(); i++)
	    	{
	    		EgovMap inMap = vos.get(i);

	    		//합계 skip
    			if("TOT".equals((String)inMap.get("rowGubun")))
    			{
    				continue;
    			}
	    		
	    		if("C".equals((String)inMap.get("dsType"))
	    			&& mpPlanCmDDAO.checkDupProCodeMpPlanCmD(inMap) > 0)
	    		{
	    			result.setMsg("이미 존재하는 제품입니다.");
	    			result.setResultCode(Consts.ResultCode.RC_DUPLICATE.getCode());
	    			return result;
	    		}
	    	}
    	
	    	for(int i = 0; i<vos.size(); i++)
	    	{
	    		EgovMap inMap = vos.get(i);
	    	
	    		//합계 skip
    			if("TOT".equals((String)inMap.get("rowGubun")))
    			{
    				continue;
    			}
	    		
	    		String unitKg = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("unitKg")),"null"),"0");
	    		String jaegoQty = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("jaegoQty")),"null"),"0");
	    		String jaegoWeig = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("jaegoWeig")),"null"),"0");
	    		String orderQty = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("orderQty")),"null"),"0");
	    		String orderWeig = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("orderWeig")),"null"),"0");
	    		String ordWeekQty = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("ordWeekQty")),"null"),"0");
	    		String ordWeekWeig = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("ordWeekWeig")),"null"),"0");
	    		String woutQty = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("woutQty")),"null"),"0");
	    		String woutWeig = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("woutWeig")),"null"),"0");
	    		String ajaegoWeig = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("ajaegoWeig")),"null"),"0");
	    		String bjaegoWeig = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("bjaegoWeig")),"null"),"0");
	    		String sanQty = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("sanQty")),"null"),"0");
	    		String sanWeig = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("sanWeig")),"null"),"0");
	    		String sanDosu = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("sanDosu")),"null"),"0");
	    		String workCnt = StringUtils.defaultIfEmpty(StringUtils.remove(String.valueOf(inMap.get("workCnt")),"null"),"0");
	    		
	    		double dUnitKg = 0;
	    		double dSanQty = 0;
	    		double dSanWeig = 0;
	    		
	    		if("0".equals(unitKg))
	    			dUnitKg = 1;
	    		else
	    			dUnitKg = Double.parseDouble(unitKg);
	    		
	    		dSanQty = Double.parseDouble(sanQty);
	    		
	    		dSanWeig = (dUnitKg * dSanQty) ;
	    		
	    		inMap.put("unitKg", unitKg );
	    		inMap.put("jaegoQty", jaegoQty);
	    		inMap.put("jaegoWeig", jaegoWeig);
	    		inMap.put("orderQty", orderQty);
	    		inMap.put("orderWeig", orderWeig);
	    		inMap.put("ordWeekQty", ordWeekQty);
	    		inMap.put("ordWeekWeig", ordWeekWeig);
	    		inMap.put("woutQty", woutQty);
	    		inMap.put("woutWeig", woutWeig);
	    		inMap.put("ajaegoWeig", ajaegoWeig);
	    		inMap.put("bjaegoWeig", bjaegoWeig);
	    		inMap.put("sanQty", sanQty);
	    		inMap.put("sanWeig", dSanWeig);
	    		inMap.put("sanDosu", sanDosu);
	    		inMap.put("workCnt", workCnt);
	    		
	    		if(inMap.get("proName") != null && ((String)inMap.get("proName")).indexOf("무박") > -1)
	    			inMap.put("fRateSort", 0);
	    		else
	    			inMap.put("fRateSort", 5);
	    		
	    		mpPlanCmDDAO.insertMpPlanCmD(inMap);
	    	}
    	
    	}
    	
    	
    	double doosuSum = 0;
    	double doosu1 = head.getDoosu1();
    	double doosu2 = head.getDoosu2();
    	double doosu3 = head.getDoosu3();
    	double doosu4 = head.getDoosu4();
    	
    	head.setDoosu5((doosu1+doosu2+doosu3+doosu4));
    	
        mpPlanCmHDAO.insertMpPlanCmH(head);
    	
    	result.setExtraData(vos);
    	
        return result;
    }

    /**
	 * mp_plan_cm_h을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanCmHVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanCmH(MpPlanCmHVO vo) throws Exception {
        mpPlanCmHDAO.updateMpPlanCmH(vo);
    }
    
	public int updateMpPlanCmHStatus(MpPlanCmHVO vo) throws Exception{
		return mpPlanCmHDAO.updateMpPlanCmHStatus(vo);
	}

    /**
	 * mp_plan_cm_h을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanCmHVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPlanCmH(MpPlanCmHVO vo) throws Exception {
        //mpPlanCmHDAO.deleteMpPlanCmH(vo);
    }
    
    public ResultEx deleteMpPlanWorkDate(EgovMap vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int nCnt = 0;
    	
    	nCnt = mpPlanCmDDAO.deleteMpPlanCmD(vo);
    	if(nCnt == 0)
    	{
    		throw new Exception("delete detail fail!!");
    	}
    	nCnt = mpPlanCmHDAO.deleteMpPlanCmH(vo);
    	if(nCnt == 0)
    	{
    		throw new Exception("head fail!!");
    	}
    	
    	return result;
    }

    /**
	 * mp_plan_cm_h을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanCmHVO
	 * @return 조회한 mp_plan_cm_h
	 * @exception Exception
	 */
    public MpPlanCmHVO selectMpPlanCmH(MpPlanCmHVO vo) throws Exception {
        MpPlanCmHVO resultVO = mpPlanCmHDAO.selectMpPlanCmH(vo);
        return resultVO;
    }

    /**
	 * mp_plan_cm_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_cm_h 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanCmHList(MpPlanCmHDefaultVO searchVO) throws Exception {
        return mpPlanCmHDAO.selectMpPlanCmHList(searchVO);
    }

    /**
	 * mp_plan_cm_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_cm_h 총 갯수
	 * @exception
	 */
    public int selectMpPlanCmHListTotCnt(MpPlanCmHDefaultVO searchVO) {
		return mpPlanCmHDAO.selectMpPlanCmHListTotCnt(searchVO);
	}
    
    public List<?> selectNewMpPlanCmHList(MpPlanCmHDefaultVO searchVO) throws Exception{
    	return mpPlanCmHDAO.selectNewMpPlanCmHList(searchVO);
    }
    
    public List<?> selectNewMpPlanCmHProduct(MpPlanCmHDefaultVO searchVO) throws Exception{
    	return mpPlanCmHDAO.selectNewMpPlanCmHList(searchVO);
    }
    
    public EgovMap selectNewMpPlanCmHProCode(MpPlanCmHDefaultVO searchVO) throws Exception {
    	return mpPlanCmHDAO.selectNewMpPlanCmHProCode(searchVO);
    }

    public EgovMap selectNewMpPlanCmHSum(MpPlanCmHDefaultVO searchVO) throws Exception{
    	return mpPlanCmHDAO.selectNewMpPlanCmHSum(searchVO);
    }
    public List<EgovMap> selectNewMpPlanCmHSumList(MpPlanCmHDefaultVO searchVO) throws Exception{
    	return mpPlanCmHDAO.selectNewMpPlanCmHSumList(searchVO);
    }
    public EgovMap selectMpPlanCmHDoosuSum(MpPlanCmHDefaultVO searchVO) throws Exception {
    	return mpPlanCmHDAO.selectMpPlanCmHDoosuSum(searchVO);
    }
    public EgovMap selectCompTimeMpPlanCmH(MpPlanCmHDefaultVO vo) throws Exception {
    	return mpPlanCmHDAO.selectCompTimeMpPlanCmH(vo);
    }

}
