package nds.mpm.prod.PP0602.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;
import nds.mpm.prod.PP0504.vo.MpBarProMVO;
import nds.mpm.prod.PP0602.service.PP0602MpBarProMService;
import nds.mpm.prod.PP0602.service.PP0602MpBarProMDAO;

/**
 * @Class Name : MpBarProMServiceImpl.java
 * @Description : MpBarProM Business Implement class
 * @Modification Information
 *
 * @author 공장별 생산현황 조회(집계)
 * @since 공장별 생산현황 조회(집계)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PP0602mpBarProMService")
public class PP0602MpBarProMServiceImpl extends EgovAbstractServiceImpl implements
        PP0602MpBarProMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PP0602MpBarProMServiceImpl.class);

    @Resource(name="PP0602mpBarProMDAO")
    private PP0602MpBarProMDAO mpBarProMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpBarProMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_bar_pro_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBarProMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpBarProM(MpBarProMVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpBarProMDAO.insertMpBarProM(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
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
	 * mp_bar_pro_m 목록을 조회한다.(품목별)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpBarProMListTab1(MpBarProMVO searchVO) throws Exception {
        return mpBarProMDAO.selectMpBarProMListTab1(searchVO);
    }
    
    /**
	 * mp_bar_pro_m 목록을 조회한다.(제품별)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpBarProMListTab2(MpBarProMVO searchVO) throws Exception {
        return mpBarProMDAO.selectMpBarProMListTab2(searchVO);
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
