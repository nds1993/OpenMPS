package nds.mpm.sales.SD0301.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.sales.SD0301.service.MpTargetYyyyService;
import nds.mpm.sales.SD0301.vo.MpTargetYyyyDefaultVO;
import nds.mpm.sales.SD0301.vo.MpTargetYyyyVO;
import nds.mpm.sales.SD0301.service.MpTargetYyyyDAO;

/**
 * @Class Name : MpTargetYyyyServiceImpl.java
 * @Description : MpTargetYyyy Business Implement class
 * @Modification Information
 *
 * @author 333333
 * @since 333333
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpTargetYyyyService")
public class MpTargetYyyyServiceImpl extends EgovAbstractServiceImpl implements
        MpTargetYyyyService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpTargetYyyyServiceImpl.class);

    @Resource(name="mpTargetYyyyDAO")
    private MpTargetYyyyDAO mpTargetYyyyDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpTargetYyyyIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_target_yyyy을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpTargetYyyyVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpTargetYyyy(MpTargetYyyyVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpTargetYyyyDAO.insertMpTargetYyyy(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_target_yyyy을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpTargetYyyyVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpTargetYyyy(MpTargetYyyyVO vo) throws Exception {
        mpTargetYyyyDAO.updateMpTargetYyyy(vo);
    }

    /**
	 * mp_target_yyyy을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpTargetYyyyVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpTargetYyyy(MpTargetYyyyVO vo) throws Exception {
        mpTargetYyyyDAO.deleteMpTargetYyyy(vo);
    }

    /**
	 * mp_target_yyyy을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpTargetYyyyVO
	 * @return 조회한 mp_target_yyyy
	 * @exception Exception
	 */
    public MpTargetYyyyVO selectMpTargetYyyy(MpTargetYyyyVO vo) throws Exception {
        MpTargetYyyyVO resultVO = mpTargetYyyyDAO.selectMpTargetYyyy(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_target_yyyy 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_target_yyyy 목록
	 * @exception Exception
	 */
    public List<?> selectMpTargetYyyyList(MpTargetYyyyDefaultVO searchVO) throws Exception {
        return mpTargetYyyyDAO.selectMpTargetYyyyList(searchVO);
    }

    /**
	 * mp_target_yyyy 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_target_yyyy 총 갯수
	 * @exception
	 */
    public int selectMpTargetYyyyListTotCnt(MpTargetYyyyDefaultVO searchVO) {
		return mpTargetYyyyDAO.selectMpTargetYyyyListTotCnt(searchVO);
	}
    
}
