package nds.mpm.prod.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.base.service.MpPlanxdService;
import nds.mpm.prod.base.vo.MpPlanxdDefaultVO;
import nds.mpm.prod.base.vo.MpPlanxdVO;
import nds.mpm.prod.base.service.MpPlanxdDAO;

/**
 * @Class Name : MpPlanxdServiceImpl.java
 * @Description : MpPlanxd Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.28
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPlanxdService")
public class MpPlanxdServiceImpl extends EgovAbstractServiceImpl implements
        MpPlanxdService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPlanxdServiceImpl.class);

    @Resource(name="mpPlanxdDAO")
    private MpPlanxdDAO mpPlanxdDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPlanxdIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * MP_PLANXD을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanxdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPlanxd(MpPlanxdVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpPlanxdDAO.insertMpPlanxd(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * MP_PLANXD을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanxdVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanxd(MpPlanxdVO vo) throws Exception {
        mpPlanxdDAO.updateMpPlanxd(vo);
    }

    /**
	 * MP_PLANXD을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanxdVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPlanxd(MpPlanxdVO vo) throws Exception {
        mpPlanxdDAO.deleteMpPlanxd(vo);
    }

    /**
	 * MP_PLANXD을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanxdVO
	 * @return 조회한 MP_PLANXD
	 * @exception Exception
	 */
    public MpPlanxdVO selectMpPlanxd(MpPlanxdVO vo) throws Exception {
        MpPlanxdVO resultVO = mpPlanxdDAO.selectMpPlanxd(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * MP_PLANXD 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_PLANXD 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanxdList(MpPlanxdDefaultVO searchVO) throws Exception {
        return mpPlanxdDAO.selectMpPlanxdList(searchVO);
    }

    /**
	 * MP_PLANXD 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_PLANXD 총 갯수
	 * @exception
	 */
    public int selectMpPlanxdListTotCnt(MpPlanxdDefaultVO searchVO) {
		return mpPlanxdDAO.selectMpPlanxdListTotCnt(searchVO);
	}
    
}
