package nds.mpm.prod.PP0902.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.PP0902.service.MpDailySumLogMService;
import nds.mpm.prod.PP0902.vo.MpDailySumLogMDefaultVO;
import nds.mpm.prod.PP0902.vo.MpDailySumLogMVO;
import nds.mpm.prod.PP0902.service.MpDailySumLogMDAO;

/**
 * @Class Name : MpDailySumLogMServiceImpl.java
 * @Description : MpDailySumLogM Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpDailySumLogMService")
public class MpDailySumLogMServiceImpl extends EgovAbstractServiceImpl implements
        MpDailySumLogMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpDailySumLogMServiceImpl.class);

    @Resource(name="mpDailySumLogMDAO")
    private MpDailySumLogMDAO mpDailySumLogMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpDailySumLogMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_daily_sum_log_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDailySumLogMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpDailySumLogM(MpDailySumLogMVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpDailySumLogMDAO.insertMpDailySumLogM(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_daily_sum_log_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDailySumLogMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpDailySumLogM(MpDailySumLogMVO vo) throws Exception {
        mpDailySumLogMDAO.updateMpDailySumLogM(vo);
    }

    /**
	 * mp_daily_sum_log_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDailySumLogMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpDailySumLogM(MpDailySumLogMVO vo) throws Exception {
        mpDailySumLogMDAO.deleteMpDailySumLogM(vo);
    }

    /**
	 * mp_daily_sum_log_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDailySumLogMVO
	 * @return 조회한 mp_daily_sum_log_m
	 * @exception Exception
	 */
    public MpDailySumLogMVO selectMpDailySumLogM(MpDailySumLogMVO vo) throws Exception {
        MpDailySumLogMVO resultVO = mpDailySumLogMDAO.selectMpDailySumLogM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_daily_sum_log_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_daily_sum_log_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpDailySumLogMList(MpDailySumLogMDefaultVO searchVO) throws Exception {
        return mpDailySumLogMDAO.selectMpDailySumLogMList(searchVO);
    }

    /**
	 * mp_daily_sum_log_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_daily_sum_log_m 총 갯수
	 * @exception
	 */
    public int selectMpDailySumLogMListTotCnt(MpDailySumLogMDefaultVO searchVO) {
		return mpDailySumLogMDAO.selectMpDailySumLogMListTotCnt(searchVO);
	}
    
}
