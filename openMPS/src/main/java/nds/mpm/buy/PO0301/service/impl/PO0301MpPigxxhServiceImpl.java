package nds.mpm.buy.PO0301.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.buy.PO0301.service.PO0301MpPigxxhDAO;
import nds.mpm.buy.PO0301.service.PO0301MpPigxxhService;
import nds.mpm.buy.PO0301.vo.MpPigxxhDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigxxhVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : MpPigxxhServiceImpl.java
 * @Description : MpPigxxh Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PO0301MpPigxxhService")
public class PO0301MpPigxxhServiceImpl extends EgovAbstractServiceImpl implements
        PO0301MpPigxxhService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PO0301MpPigxxhServiceImpl.class);

    @Resource(name="PO0301MpPigxxhDAO")
    private PO0301MpPigxxhDAO mpPigxxhDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPigxxhIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_pigxxh을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigxxhVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public MpPigxxhVO insertMpPigxxh(MpPigxxhVO vo) throws Exception {
    	
    	mpPigxxhDAO.insertMpPigxxh(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return vo;
    }

    /**
	 * mp_pigxxh을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigxxhVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPigxxh(MpPigxxhVO vo) throws Exception {
        mpPigxxhDAO.updateMpPigxxh(vo);
    }

    /**
	 * mp_pigxxh을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigxxhVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPigxxh(MpPigxxhVO vo) throws Exception {
        mpPigxxhDAO.deleteMpPigxxh(vo);
    }

    /**
	 * mp_pigxxh을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigxxhVO
	 * @return 조회한 mp_pigxxh
	 * @exception Exception
	 */
    public MpPigxxhVO selectMpPigxxh(MpPigxxhVO vo) throws Exception {
        MpPigxxhVO resultVO = mpPigxxhDAO.selectMpPigxxh(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_pigxxh 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigxxh 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigxxhList(MpPigxxhDefaultVO searchVO) throws Exception {
        return mpPigxxhDAO.selectMpPigxxhList(searchVO);
    }

    /**
	 * mp_pigxxh 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigxxh 총 갯수
	 * @exception
	 */
    public int selectMpPigxxhListTotCnt(MpPigxxhDefaultVO searchVO) {
		return mpPigxxhDAO.selectMpPigxxhListTotCnt(searchVO);
	}
    
}
