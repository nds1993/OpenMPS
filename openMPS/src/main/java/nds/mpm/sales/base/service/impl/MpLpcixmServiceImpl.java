package nds.mpm.sales.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.sales.base.service.MpLpcixmService;
import nds.mpm.sales.base.vo.MpLpcixmDefaultVO;
import nds.mpm.sales.base.vo.MpLpcixmVO;
import nds.mpm.sales.base.service.MpLpcixmDAO;

/**
 * @Class Name : MpLpcixmServiceImpl.java
 * @Description : MpLpcixm Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.27
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpLpcixmService")
public class MpLpcixmServiceImpl extends EgovAbstractServiceImpl implements
        MpLpcixmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpLpcixmServiceImpl.class);

    @Resource(name="mpLpcixmDAO")
    private MpLpcixmDAO mpLpcixmDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpLpcixmIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * MP_LPCIXM을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpLpcixmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpLpcixm(MpLpcixmVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpLpcixmDAO.insertMpLpcixm(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * MP_LPCIXM을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpLpcixmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpLpcixm(MpLpcixmVO vo) throws Exception {
        mpLpcixmDAO.updateMpLpcixm(vo);
    }

    /**
	 * MP_LPCIXM을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpLpcixmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpLpcixm(MpLpcixmVO vo) throws Exception {
        mpLpcixmDAO.deleteMpLpcixm(vo);
    }

    /**
	 * MP_LPCIXM을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpLpcixmVO
	 * @return 조회한 MP_LPCIXM
	 * @exception Exception
	 */
    public MpLpcixmVO selectMpLpcixm(MpLpcixmVO vo) throws Exception {
        MpLpcixmVO resultVO = mpLpcixmDAO.selectMpLpcixm(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * MP_LPCIXM 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_LPCIXM 목록
	 * @exception Exception
	 */
    public List<?> selectMpLpcixmList(MpLpcixmDefaultVO searchVO) throws Exception {
        return mpLpcixmDAO.selectMpLpcixmList(searchVO);
    }

    /**
	 * MP_LPCIXM 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return MP_LPCIXM 총 갯수
	 * @exception
	 */
    public int selectMpLpcixmListTotCnt(MpLpcixmDefaultVO searchVO) {
		return mpLpcixmDAO.selectMpLpcixmListTotCnt(searchVO);
	}
    
}
