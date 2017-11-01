package nds.mpm.prod.buy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.buy.service.MpPigkxmService;
import nds.mpm.prod.buy.vo.MpPigkxmDefaultVO;
import nds.mpm.prod.buy.vo.MpPigkxmVO;
import nds.mpm.prod.buy.service.MpPigkxmDAO;

/**
 * @Class Name : MpPigkxmServiceImpl.java
 * @Description : MpPigkxm Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPigkxmService")
public class MpPigkxmServiceImpl extends EgovAbstractServiceImpl implements
        MpPigkxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPigkxmServiceImpl.class);

    @Resource(name="mpPigkxmDAO")
    private MpPigkxmDAO mpPigkxmDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPigkxmIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_pigkxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigkxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPigkxm(MpPigkxmVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpPigkxmDAO.insertMpPigkxm(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_pigkxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigkxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPigkxm(MpPigkxmVO vo) throws Exception {
        mpPigkxmDAO.updateMpPigkxm(vo);
    }

    /**
	 * mp_pigkxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigkxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPigkxm(MpPigkxmVO vo) throws Exception {
        mpPigkxmDAO.deleteMpPigkxm(vo);
    }

    /**
	 * mp_pigkxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigkxmVO
	 * @return 조회한 mp_pigkxm
	 * @exception Exception
	 */
    public MpPigkxmVO selectMpPigkxm(MpPigkxmVO vo) throws Exception {
        MpPigkxmVO resultVO = mpPigkxmDAO.selectMpPigkxm(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_pigkxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigkxm 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigkxmList(MpPigkxmDefaultVO searchVO) throws Exception {
        return mpPigkxmDAO.selectMpPigkxmList(searchVO);
    }

    /**
	 * mp_pigkxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigkxm 총 갯수
	 * @exception
	 */
    public int selectMpPigkxmListTotCnt(MpPigkxmDefaultVO searchVO) {
		return mpPigkxmDAO.selectMpPigkxmListTotCnt(searchVO);
	}
    
}
