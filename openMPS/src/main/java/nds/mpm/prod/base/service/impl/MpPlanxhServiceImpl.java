package nds.mpm.prod.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.base.service.MpPlanxhService;
import nds.mpm.prod.base.vo.MpPlanxhDefaultVO;
import nds.mpm.prod.base.vo.MpPlanxhVO;
import nds.mpm.prod.base.service.MpPlanxhDAO;

/**
 * @Class Name : MpPlanxhServiceImpl.java
 * @Description : MpPlanxh Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.28
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPlanxhService")
public class MpPlanxhServiceImpl extends EgovAbstractServiceImpl implements
        MpPlanxhService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPlanxhServiceImpl.class);

    @Resource(name="mpPlanxhDAO")
    private MpPlanxhDAO mpPlanxhDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPlanxhIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * MP_PLANXH을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanxhVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPlanxh(MpPlanxhVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpPlanxhDAO.insertMpPlanxh(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * MP_PLANXH을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanxhVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanxh(MpPlanxhVO vo) throws Exception {
        mpPlanxhDAO.updateMpPlanxh(vo);
    }

    /**
	 * MP_PLANXH을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanxhVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPlanxh(MpPlanxhVO vo) throws Exception {
        mpPlanxhDAO.deleteMpPlanxh(vo);
    }

    /**
	 * MP_PLANXH을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanxhVO
	 * @return 조회한 MP_PLANXH
	 * @exception Exception
	 */
    public MpPlanxhVO selectMpPlanxh(MpPlanxhVO vo) throws Exception {
        MpPlanxhVO resultVO = mpPlanxhDAO.selectMpPlanxh(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * MP_PLANXH 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_PLANXH 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanxhList(MpPlanxhDefaultVO searchVO) throws Exception {
        return mpPlanxhDAO.selectMpPlanxhList(searchVO);
    }

    /**
	 * MP_PLANXH 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_PLANXH 총 갯수
	 * @exception
	 */
    public int selectMpPlanxhListTotCnt(MpPlanxhDefaultVO searchVO) {
		return mpPlanxhDAO.selectMpPlanxhListTotCnt(searchVO);
	}
    
}
