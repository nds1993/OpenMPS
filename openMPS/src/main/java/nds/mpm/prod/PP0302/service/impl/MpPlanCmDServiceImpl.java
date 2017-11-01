package nds.mpm.prod.PP0302.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.prod.PP0302.service.MpPlanCmDDAO;
import nds.mpm.prod.PP0302.service.MpPlanCmDService;
import nds.mpm.prod.PP0302.vo.MpPlanCmDDefaultVO;
import nds.mpm.prod.PP0302.vo.MpPlanCmDVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPlanCmDServiceImpl.java
 * @Description : MpPlanCmD Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPlanCmDService")
public class MpPlanCmDServiceImpl extends EgovAbstractServiceImpl implements
        MpPlanCmDService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPlanCmDServiceImpl.class);

    @Resource(name="mpPlanCmDDAO")
    private MpPlanCmDDAO mpPlanCmDDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPlanCmDIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_plan_cm_d을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanCmDVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPlanCmD(EgovMap vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpPlanCmDDAO.insertMpPlanCmD(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_plan_cm_d을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanCmDVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanCmD(MpPlanCmDVO vo) throws Exception {
        mpPlanCmDDAO.updateMpPlanCmD(vo);
    }

    /**
	 * mp_plan_cm_d을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanCmDVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPlanCmD(MpPlanCmDVO vo) throws Exception {
       // mpPlanCmDDAO.deleteMpPlanCmD(vo);
    }

    /**
	 * mp_plan_cm_d을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanCmDVO
	 * @return 조회한 mp_plan_cm_d
	 * @exception Exception
	 */
    public MpPlanCmDVO selectMpPlanCmD(MpPlanCmDVO vo) throws Exception {
        MpPlanCmDVO resultVO = mpPlanCmDDAO.selectMpPlanCmD(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_plan_cm_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_cm_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanCmDList(MpPlanCmDDefaultVO searchVO) throws Exception {
        return mpPlanCmDDAO.selectMpPlanCmDList(searchVO);
    }

    /**
	 * mp_plan_cm_d 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_cm_d 총 갯수
	 * @exception
	 */
    public int selectMpPlanCmDListTotCnt(MpPlanCmDDefaultVO searchVO) {
		return mpPlanCmDDAO.selectMpPlanCmDListTotCnt(searchVO);
	}
    
}
