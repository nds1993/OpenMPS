package nds.mpm.prod.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.base.service.MpIbarxmService;
import nds.mpm.prod.base.vo.MpIbarxmDefaultVO;
import nds.mpm.prod.base.vo.MpIbarxmVO;
import nds.mpm.prod.base.service.MpIbarxmDAO;

/**
 * @Class Name : MpIbarxmServiceImpl.java
 * @Description : MpIbarxm Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpIbarxmService")
public class MpIbarxmServiceImpl extends EgovAbstractServiceImpl implements
        MpIbarxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpIbarxmServiceImpl.class);

    @Resource(name="mpIbarxmDAO")
    private MpIbarxmDAO mpIbarxmDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpIbarxmIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * MP_IBARXM을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpIbarxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpIbarxm(MpIbarxmVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpIbarxmDAO.insertMpIbarxm(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * MP_IBARXM을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpIbarxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpIbarxm(MpIbarxmVO vo) throws Exception {
        mpIbarxmDAO.updateMpIbarxm(vo);
    }

    /**
	 * MP_IBARXM을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpIbarxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpIbarxm(MpIbarxmVO vo) throws Exception {
        mpIbarxmDAO.deleteMpIbarxm(vo);
    }

    /**
	 * MP_IBARXM을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpIbarxmVO
	 * @return 조회한 MP_IBARXM
	 * @exception Exception
	 */
    public MpIbarxmVO selectMpIbarxm(MpIbarxmVO vo) throws Exception {
        MpIbarxmVO resultVO = mpIbarxmDAO.selectMpIbarxm(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * MP_IBARXM 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_IBARXM 목록
	 * @exception Exception
	 */
    public List<?> selectMpIbarxmList(MpIbarxmDefaultVO searchVO) throws Exception {
        return mpIbarxmDAO.selectMpIbarxmList(searchVO);
    }

    /**
	 * MP_IBARXM 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_IBARXM 총 갯수
	 * @exception
	 */
    public int selectMpIbarxmListTotCnt(MpIbarxmDefaultVO searchVO) {
		return mpIbarxmDAO.selectMpIbarxmListTotCnt(searchVO);
	}
    
}
