package nds.mpm.prod.PP0902.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.PP0902.service.MpDailySumLogMDAO;
import nds.mpm.prod.PP0902.service.MpDailySumMService;
import nds.mpm.prod.PP0902.vo.MpDailySumMDefaultVO;
import nds.mpm.prod.PP0902.vo.MpDailySumMVO;
import nds.mpm.prod.PP0902.service.MpDailySumMDAO;

/**
 * @Class Name : MpDailySumMServiceImpl.java
 * @Description : MpDailySumM Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpDailySumMService")
public class MpDailySumMServiceImpl extends EgovAbstractServiceImpl implements
        MpDailySumMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpDailySumMServiceImpl.class);

    @Resource(name="mpDailySumMDAO")
    private MpDailySumMDAO mpDailySumMDAO;
    
	/**
	 * mp_daily_sum_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDailySumMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpDailySumM(MpDailySumMVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpDailySumMDAO.insertMpDailySumM(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_daily_sum_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDailySumMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpDailySumM(MpDailySumMVO vo) throws Exception {
        mpDailySumMDAO.updateMpDailySumM(vo);
    }

    /**
	 * mp_daily_sum_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDailySumMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpDailySumM(MpDailySumMVO vo) throws Exception {
        mpDailySumMDAO.deleteMpDailySumM(vo);
    }

    /**
	 * mp_daily_sum_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDailySumMVO
	 * @return 조회한 mp_daily_sum_m
	 * @exception Exception
	 */
    public MpDailySumMVO selectMpDailySumM(MpDailySumMVO vo) throws Exception {
        MpDailySumMVO resultVO = mpDailySumMDAO.selectMpDailySumM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_daily_sum_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_daily_sum_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpDailySumMList(MpDailySumMDefaultVO searchVO) throws Exception {
        return mpDailySumMDAO.selectMpDailySumMList(searchVO);
    }

    /**
	 * mp_daily_sum_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_daily_sum_m 총 갯수
	 * @exception
	 */
    public int selectMpDailySumMListTotCnt(MpDailySumMDefaultVO searchVO) {
		return mpDailySumMDAO.selectMpDailySumMListTotCnt(searchVO);
	}
    
}
