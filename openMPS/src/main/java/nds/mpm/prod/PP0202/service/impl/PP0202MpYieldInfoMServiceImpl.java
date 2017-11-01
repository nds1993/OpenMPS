package nds.mpm.prod.PP0202.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.PP0202.service.PP0202MpYieldInfoMService;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMDefaultVO;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMVO;
import nds.mpm.prod.PP0202.service.PP0202MpYieldInfoMDAO;

/**
 * @Class Name : MpYieldInfoMServiceImpl.java
 * @Description : MpYieldInfoM Business Implement class
 * @Modification Information
 *
 * @author 22222
 * @since 22222
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PP0202mpYieldInfoMService")
public class PP0202MpYieldInfoMServiceImpl extends EgovAbstractServiceImpl implements
        PP0202MpYieldInfoMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PP0202MpYieldInfoMServiceImpl.class);

    @Resource(name="PP0202mpYieldInfoMDAO")
    private PP0202MpYieldInfoMDAO mpYieldInfoMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpYieldInfoMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_yield_info_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpYieldInfoMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpYieldInfoM(MpYieldInfoMVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpYieldInfoMDAO.insertMpYieldInfoM(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_yield_info_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpYieldInfoMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpYieldInfoM(MpYieldInfoMVO vo) throws Exception {
        mpYieldInfoMDAO.updateMpYieldInfoM(vo);
    }

    /**
	 * mp_yield_info_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpYieldInfoMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpYieldInfoM(MpYieldInfoMVO vo) throws Exception {
        mpYieldInfoMDAO.deleteMpYieldInfoM(vo);
    }

    /**
	 * mp_yield_info_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpYieldInfoMVO
	 * @return 조회한 mp_yield_info_m
	 * @exception Exception
	 */
    public MpYieldInfoMVO selectMpYieldInfoM(MpYieldInfoMVO vo) throws Exception {
        MpYieldInfoMVO resultVO = mpYieldInfoMDAO.selectMpYieldInfoM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpYieldInfoMCmTopList(MpYieldInfoMDefaultVO searchVO) throws Exception {
        return mpYieldInfoMDAO.selectMpYieldInfoMCmTopList(searchVO);
    }
    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpYieldInfoMCmLDetailList(MpYieldInfoMDefaultVO searchVO) throws Exception {
        return mpYieldInfoMDAO.selectMpYieldInfoMCmLDetailList(searchVO);
    }
    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpYieldInfoMCmSDetailList(MpYieldInfoMDefaultVO searchVO) throws Exception {
        return mpYieldInfoMDAO.selectMpYieldInfoMCmSDetailList(searchVO);                               
    }
    
    /**
	 * mp_yield_info_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 총 갯수
	 * @exception
	 */
    public int selectMpYieldInfoMListTotCnt(MpYieldInfoMDefaultVO searchVO) {
		return mpYieldInfoMDAO.selectMpYieldInfoMListTotCnt(searchVO);
	}
    
    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpYieldInfoMPmList(MpYieldInfoMDefaultVO searchVO) throws Exception {
        return mpYieldInfoMDAO.selectMpYieldInfoMPmList(searchVO);
    }

    /**
	 * mp_yield_info_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 총 갯수
	 * @exception
	 */
    public int selectMpYieldInfoMPmListTotCnt(MpYieldInfoMDefaultVO searchVO) {
		return mpYieldInfoMDAO.selectMpYieldInfoMPmListTotCnt(searchVO);
	}
   
}
