package nds.mpm.buy.PO0102.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.buy.PO0102.service.MpPigdxmService;
import nds.mpm.buy.PO0102.vo.MpPigdxmDefaultVO;
import nds.mpm.buy.PO0102.vo.MpPigdxmVO;
import nds.mpm.buy.PO0102.service.MpPigdxmDAO;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;

/**
 * @Class Name : MpPigdxmServiceImpl.java
 * @Description : MpPigdxm Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPigdxmService")
public class MpPigdxmServiceImpl extends EgovAbstractServiceImpl implements
        MpPigdxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPigdxmServiceImpl.class);

    @Resource(name="mpPigdxmDAO")
    private MpPigdxmDAO mpPigdxmDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPigdxmIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_pigdxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigdxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPigdxm(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	EgovMap checkDelMap = new EgovMap();
    	
    	int delCnt = 0;
    	
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType"))){
    			
    			if(StringUtils.isNotEmpty((String)reqVo.get("dochCode"))) continue;
    			
    			if(StringUtils.isEmpty((String)checkDelMap.get("hisNo")))
    			{
    				checkDelMap.put("hisNo", reqVo.get("hisNo"));
    				mpPigdxmDAO.deleteMpPigdxmForHisNo(reqVo);
    				
    				delCnt++;
    			}
    			
    			/**
    			 * doch_code 생성. sqlmap
    			 * mp_cust_info.fac_kind( 정산구분 )
    			 * 3박피 P
    			 * 1탕박 T
    			 * 도축번호(DOCH_CODE)는 YYYYMMDD(도축일자)+P(박피) 또는 T(탕박)+도체번호로 저장함
    			 * */
    			if("3".equals((String)reqVo.get("facKind"))
    			|| "1".equals((String)reqVo.get("facKind"))){
    				
    			}
    			else{
    				
    				continue;
    			}
    				
    			
    			/**
    			 * 등급코드 셋팅.
    			 * */
    			if("1+".equals((String)reqVo.get("pigMeat")))
    				reqVo.put("pigGrade", "A");
    			else if("1".equals((String)reqVo.get("pigMeat")))
    				reqVo.put("pigGrade", "B");
    			else if("1".equals((String)reqVo.get("pigMeat")))
    				reqVo.put("pigGrade", "C");
    			else if("등외".equals((String)reqVo.get("pigMeat")))
    				reqVo.put("pigGrade", "E");
    			/**
    			 * 성별코드 셋팅.
    			 * */
    			if("암".equals((String)reqVo.get("sexName")))
    				reqVo.put("sexNo", "1");
    			else if("수".equals((String)reqVo.get("sexName")))
    				reqVo.put("sexNo", "2");
    			else if("거세".equals((String)reqVo.get("sexName")))
    				reqVo.put("sexNo", "3");
    			
				mpPigdxmDAO.insertMpPigdxm(reqVo);
				
				/**
				 * other fackind 추가 생성.
				 * 저장 시 정산구분(FAC_KIND)가 박피(3)일 경우는 탕박(1)도 동일하게 생성하여 저장하며, 탕박(1)일 경우도 박피(3)를 동일하게 생성함.
				 * */
				if("3".equals((String)reqVo.get("facKind")))
				{
					reqVo.put("facKind", "1");
					mpPigdxmDAO.insertMpPigdxm(reqVo);
				}
				else
				{
					reqVo.put("facKind", "3");
					mpPigdxmDAO.insertMpPigdxm(reqVo);
				}
				
    			
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    			mpPigdxmDAO.updateMpPigdxm(reqVo);
    		else if("D".equals((String)reqVo.get("dsType")))
    			mpPigdxmDAO.deleteMpPigdxm(reqVo);
    	}
    	
    	LOGGER.debug("delCnt :: " + delCnt);
    	checkDelMap = null;
    	
    	result.setExtraData(vos);
    	
    	return result;
    }

    /**
	 * mp_pigdxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigdxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPigdxm(MpPigdxmVO vo) throws Exception {
        //mpPigdxmDAO.updateMpPigdxm(vo);
    }

    /**
	 * mp_pigdxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigdxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPigdxm(MpPigdxmVO vo) throws Exception {
        //mpPigdxmDAO.deleteMpPigdxm(vo);
    }

    /**
	 * mp_pigdxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigdxmVO
	 * @return 조회한 mp_pigdxm
	 * @exception Exception
	 */
    public MpPigdxmVO selectMpPigdxm(MpPigdxmVO vo) throws Exception {
        MpPigdxmVO resultVO = mpPigdxmDAO.selectMpPigdxm(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_pigdxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigdxmList(MpPigdxmDefaultVO searchVO) throws Exception {
        return mpPigdxmDAO.selectMpPigdxmList(searchVO);
    }

    /**
	 * mp_pigdxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 총 갯수
	 * @exception
	 */
    public int selectMpPigdxmListTotCnt(MpPigdxmDefaultVO searchVO) {
		return mpPigdxmDAO.selectMpPigdxmListTotCnt(searchVO);
	}
    
    public EgovMap selectFacInfo(MpPigdxmVO vo) throws Exception {
    	return mpPigdxmDAO.selectFacInfo(vo);
    }
    
    public EgovMap selectBuyType(MpPigdxmVO vo) throws Exception {
    	return mpPigdxmDAO.selectBuyType(vo);
    }

}
