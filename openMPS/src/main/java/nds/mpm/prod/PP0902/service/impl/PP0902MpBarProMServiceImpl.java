package nds.mpm.prod.PP0902.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;
import nds.mpm.prod.PP0504.vo.MpBarProMVO;
import nds.mpm.prod.PP0902.service.MpDailySumLogMDAO;
import nds.mpm.prod.PP0902.service.MpDailySumMDAO;
import nds.mpm.prod.PP0902.service.PP0902MpBarProMDAO;
import nds.mpm.prod.PP0902.service.PP0902MpBarProMService;
import nds.mpm.prod.PP0902.vo.MpDailySumLogMVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpBarProMServiceImpl.java
 * @Description : MpBarProM Business Implement class
 * @Modification Information
 *
 * @author 3333
 * @since 3333
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PP0902mpBarProMService")
public class PP0902MpBarProMServiceImpl extends EgovAbstractServiceImpl implements
        PP0902MpBarProMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PP0902MpBarProMServiceImpl.class);

    @Resource(name="PP0902mpBarProMDAO")
    private PP0902MpBarProMDAO mpBarProMDAO;
    
    @Resource(name="mpDailySumMDAO")
    private MpDailySumMDAO mpDailySumMDAO;
    
    @Resource(name="mpDailySumLogMDAO")
    private MpDailySumLogMDAO mpDailySumLogMDAO;

    /**
	 * 실적집계 실행.
	 * @exception Exception
	 */
    public ResultEx insertDaliySumMpBarProM(MpBarProMVO searchVO) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	/**
    	 * 집계기간 생산마감에 속하는지 조회.
    	 * */
    	if( mpBarProMDAO.checkProdDataClose(searchVO) > 0 )
    	{
    		result.setMsg("생산일자가 마감되었습니다.");
    		result.setResultCode(Consts.ResultCode.RC_DENINED.getCode());
    		return result;
    	}
    	
    	List<EgovMap> collectList = mpBarProMDAO.selectCollectDailyMpBarProM(searchVO);
    	for(EgovMap reqVo : collectList)
    	{
    		mpDailySumMDAO.insertMpDailySumM(reqVo);
    	}
    	
    	MpDailySumLogMVO sumLogvo = new MpDailySumLogMVO();
    	sumLogvo.setCorpCode(searchVO.getCorpCode());
    	sumLogvo.setCrUser(searchVO.getCrUser());
    	
    	mpDailySumLogMDAO.insertMpDailySumLogM(sumLogvo);

    	return result;
    }

	/**
	 * mp_bar_pro_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBarProMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpBarProM(MpBarProMVO vo) throws Exception {
        return null;
    }

    /**
	 * mp_bar_pro_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBarProMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpBarProM(MpBarProMVO vo) throws Exception {
        mpBarProMDAO.updateMpBarProM(vo);
    }

    /**
	 * mp_bar_pro_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBarProMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpBarProM(MpBarProMVO vo) throws Exception {
        mpBarProMDAO.deleteMpBarProM(vo);
    }

    /**
	 * mp_bar_pro_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBarProMVO
	 * @return 조회한 mp_bar_pro_m
	 * @exception Exception
	 */
    public MpBarProMVO selectMpBarProM(MpBarProMVO vo) throws Exception {
        MpBarProMVO resultVO = mpBarProMDAO.selectMpBarProM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_bar_pro_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpBarProMList(MpBarProMDefaultVO searchVO) throws Exception {
        return mpBarProMDAO.selectMpBarProMList(searchVO);
    }

    /**
	 * mp_bar_pro_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bar_pro_m 총 갯수
	 * @exception
	 */
    public int selectMpBarProMListTotCnt(MpBarProMDefaultVO searchVO) {
		return mpBarProMDAO.selectMpBarProMListTotCnt(searchVO);
	}
    
}
