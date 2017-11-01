package nds.mpm.prod.PP0604.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.PP0604.service.MpBarinProMService;
import nds.mpm.prod.PP0604.vo.MpBarinProMDefaultVO;
import nds.mpm.prod.PP0604.vo.MpBarinProMVO;
import nds.mpm.prod.PP0604.service.MpBarinProMDAO;

/**
 * @Class Name : MpBarinProMServiceImpl.java
 * @Description : MpBarinProM Business Implement class
 * @Modification Information
 *
 * @author 속라벨 발행현황 조회
 * @since 속라벨 발행현황 조회
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpBarinProMService")
public class MpBarinProMServiceImpl extends EgovAbstractServiceImpl implements
        MpBarinProMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpBarinProMServiceImpl.class);

    @Resource(name="mpBarinProMDAO")
    private MpBarinProMDAO mpBarinProMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpBarinProMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_barin_pro_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBarinProMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpBarinProM(MpBarinProMVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpBarinProMDAO.insertMpBarinProM(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_barin_pro_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBarinProMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpBarinProM(MpBarinProMVO vo) throws Exception {
        mpBarinProMDAO.updateMpBarinProM(vo);
    }

    /**
	 * mp_barin_pro_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBarinProMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpBarinProM(MpBarinProMVO vo) throws Exception {
        mpBarinProMDAO.deleteMpBarinProM(vo);
    }

    /**
	 * mp_barin_pro_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBarinProMVO
	 * @return 조회한 mp_barin_pro_m
	 * @exception Exception
	 */
    public MpBarinProMVO selectMpBarinProM(MpBarinProMVO vo) throws Exception {
        MpBarinProMVO resultVO = mpBarinProMDAO.selectMpBarinProM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_barin_pro_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_barin_pro_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpBarinProMList(MpBarinProMDefaultVO searchVO) throws Exception {
        return mpBarinProMDAO.selectMpBarinProMList(searchVO);
    }

    /**
	 * mp_barin_pro_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_barin_pro_m 총 갯수
	 * @exception
	 */
    public int selectMpBarinProMListTotCnt(MpBarinProMDefaultVO searchVO) {
		return mpBarinProMDAO.selectMpBarinProMListTotCnt(searchVO);
	}
    
}
