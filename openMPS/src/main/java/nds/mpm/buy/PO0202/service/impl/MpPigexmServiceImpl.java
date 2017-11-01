package nds.mpm.buy.PO0202.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.buy.PO0102.service.MpPigdxmDAO;
import nds.mpm.buy.PO0102.vo.MpPigdxmVO;
import nds.mpm.buy.PO0202.service.MpPigexmDAO;
import nds.mpm.buy.PO0202.service.MpPigexmService;
import nds.mpm.buy.PO0202.vo.MpPigexmDefaultVO;
import nds.mpm.buy.PO0202.vo.MpPigexmVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPigexmServiceImpl.java
 * @Description : MpPigexm Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPigexmService")
public class MpPigexmServiceImpl extends EgovAbstractServiceImpl implements
        MpPigexmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPigexmServiceImpl.class);

    @Resource(name="mpPigexmDAO")
    private MpPigexmDAO mpPigexmDAO;
    
	/**
	 * 신규저장
	 * 
	 * 1. 조회조건의 정산구분+정산방법에 해당하는 최근이력을 조회
	 * 2. 최근데이터가 존재하면 선택된 도축일자 시작일 전일로 종료일을 업데이트. 이력생성
	 * 3. 신규데이터의 시작일은 선택한 도축일자 시작일로 종료일은 99991231로 업데이트하여 신규정산방법생성.
	 * 
	 * @param vo - 등록할 정보가 담긴 MpPigexmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPigexm(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	/**
    	 * 저장전 중복체크 
    	 * */
    	for(EgovMap reqVo : vos)
    	{
    		if(StringUtils.isEmpty((String)reqVo.get("dsType")))
    		{
    			return new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS );
    		}
    		
    		reqVo.put("facCode", ""+reqVo.get("facCode"));
    		if(StringUtils.isEmpty((String)reqVo.get("facCode")))
    		{
    			result.setMsg("fac code는 필수입력입니다.");
    			result.setResultCode(Consts.ResultCode.RC_INVALID_PARAMS.getCode());
    			return result;
    		}
    		
    	}
    	
    	String newKey = null;
    	int iCnt = 0;
    	
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType"))
    		|| "U".equals((String)reqVo.get("dsType")))
    		{
    			List<?> items = mpPigexmDAO.selectMpPigexm(reqVo);
    			if(items.isEmpty())
    			{
    				newKey = mpPigexmDAO.insertMpPigexm(reqVo);
        			if(newKey == null)
        				throw new Exception("inert key fail!!");
    			}
    			else
    			{
    				iCnt = mpPigexmDAO.updateMpPigexm(reqVo);
        			if(iCnt == 0)
        				throw new Exception("update key fail!!");
    			}
    			
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			iCnt = mpPigexmDAO.deleteMpPigexm(reqVo);
    			
    			if(iCnt == 0)
    				throw new Exception("update key fail!!");
    		}
    		
    	}
    	
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	
        return result;
    }

    /**
	 * mp_pigexm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigexmVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpPigexm(EgovMap vo) throws Exception {
        return mpPigexmDAO.updateMpPigexm(vo);
    }

    /**
	 * mp_pigexm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigexmVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpPigexm(EgovMap vo) throws Exception {
        return mpPigexmDAO.deleteMpPigexm(vo);
    }

    /**
	 * mp_pigexm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigexmVO
	 * @return 조회한 mp_pigexm
	 * @exception Exception
	 */
    public MpPigexmVO selectMpPigexm(EgovMap vo) throws Exception {
        MpPigexmVO resultVO = (MpPigexmVO) mpPigexmDAO.selectMpPigexm(vo);
        return resultVO;
    }

    /**
	 * mp_pigexm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigexm 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigexmList(MpPigexmDefaultVO searchVO) throws Exception {
        return mpPigexmDAO.selectMpPigexmList(searchVO);
    }

    /**
	 * mp_pigexm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigexm 총 갯수
	 * @exception
	 */
    public int selectMpPigexmListTotCnt(MpPigexmDefaultVO searchVO) {
		return mpPigexmDAO.selectMpPigexmListTotCnt(searchVO);
	}
    
    public List<?> selectMpPigexmCodeList(MpPigexmVO searchVO) throws Exception {
        return mpPigexmDAO.selectMpPigexmCodeList(searchVO);
    }
    
    public List<?> selectMpPigexmRecentList(MpPigexmVO searchVO) throws Exception {
        return mpPigexmDAO.selectMpPigexmRecentList(searchVO);
    }
    
    public List<?> selectMpPigexmFacCodeList(MpPigexmVO searchVO) throws Exception {
        return mpPigexmDAO.selectMpPigexmFacCodeList(searchVO);
    }
    
    public ResultEx selectMpPigexmJiyukAvg(MpPigexmVO searchVO) {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String startDate = searchVO.getStartDate();
    	String endDate = searchVO.getEndDate();
    	
    	if(StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate))
    	{
    		result.setExtraData(mpPigexmDAO.selectMpPigexmJiyukAvg(searchVO));
    		return result;
    	}
    	else 
    		return new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS );
    }
    
    /**
     * 생돈정산 처리.
     * 
     * */
    public ResultEx updateAdjustFnPO0202Call(MpPigexmVO searchVO) throws Exception{
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	if(mpPigexmDAO.checkMpPigdxmDochCnt(searchVO) < 1)
    	{
    		result.setMsg("도축일자 기간에 해당하는 내역이 없습니다.");
    		result.setResultCode(Consts.ResultCode.RC_FIND_NOT_FOUND.getCode());
    		return result;
    	}
    	
    	EgovMap retunMap = mpPigexmDAO.updateAdjustFnPO0202Call(searchVO);
    	
    	LOGGER.debug("retunMap :: " + retunMap);
    	
    	String resultCode = (String)retunMap.get("oResultCode");
    	
    	if("0".equals(resultCode))
    	{
    		result.setResultCode(Consts.ResultCode.RC_OK.getCode());
    	}
    	else
    	{
    		result.setResultCode(Consts.ResultCode.RC_ERROR.getCode());
    	}
    	
    	result.setExtraData(retunMap);
    	
    	return result;
    }
    
    public EgovMap selectJiyukPeriod(MpPigexmDefaultVO searchVO) {
    	return mpPigexmDAO.selectJiyukPeriod(searchVO);
    }
}
