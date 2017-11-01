package nds.mpm.prod.PP0303.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.PP0303.service.MpPlanPlatDDAO;
import nds.mpm.prod.PP0303.service.MpPlanPlatDService;
import nds.mpm.prod.PP0303.vo.MpPlanPlatDDefaultVO;
import nds.mpm.prod.PP0303.vo.MpPlanPlatDVO;

/**
 * @Class Name : MpPlanPlatDServiceImpl.java
 * @Description : MpPlanPlatD Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPlanPlatDService")
public class MpPlanPlatDServiceImpl extends EgovAbstractServiceImpl implements
        MpPlanPlatDService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPlanPlatDServiceImpl.class);

    @Resource(name="mpPlanPlatDDAO")
    private MpPlanPlatDDAO mpPlanPlatDDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPlanPlatDIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_plan_plat_d을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanPlatDVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPlanPlatD(MpPlanPlatDVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpPlanPlatDDAO.insertMpPlanPlatD(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_plan_plat_d을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanPlatDVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanPlatD(MpPlanPlatDVO vo) throws Exception {
        mpPlanPlatDDAO.updateMpPlanPlatD(vo);
    }

    /**
	 * mp_plan_plat_d을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanPlatDVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPlanPlatD(MpPlanPlatDVO vo) throws Exception {
        mpPlanPlatDDAO.deleteMpPlanPlatD(vo);
    }

    /**
	 * mp_plan_plat_d을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanPlatDVO
	 * @return 조회한 mp_plan_plat_d
	 * @exception Exception
	 */
    public MpPlanPlatDVO selectMpPlanPlatD(MpPlanPlatDVO vo) throws Exception {
        MpPlanPlatDVO resultVO = mpPlanPlatDDAO.selectMpPlanPlatD(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_plan_plat_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_plat_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanPlatDList(MpPlanPlatDDefaultVO searchVO) throws Exception {
        return mpPlanPlatDDAO.selectMpPlanPlatDList(searchVO);
    }

    /**
	 * mp_plan_plat_d 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_plat_d 총 갯수
	 * @exception
	 */
    public int selectMpPlanPlatDListTotCnt(MpPlanPlatDDefaultVO searchVO) {
		return mpPlanPlatDDAO.selectMpPlanPlatDListTotCnt(searchVO);
	}
    
}
