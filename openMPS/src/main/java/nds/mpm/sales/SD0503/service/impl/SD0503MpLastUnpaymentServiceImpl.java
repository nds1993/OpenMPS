package nds.mpm.sales.SD0503.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.sales.SD0503.service.SD0503MpLastUnpaymentService;
import nds.mpm.sales.SD0501.vo.MpLastUnpaymentDefaultVO;
import nds.mpm.sales.SD0503.vo.MpLastUnpaymentVO;
import nds.mpm.sales.SD0503.service.SD0503MpLastUnpaymentDAO;

/**
 * @Class Name : MpLastUnpaymentServiceImpl.java
 * @Description : MpLastUnpayment Business Implement class
 * @Modification Information
 *
 * @author 33333
 * @since 33333
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD0503mpLastUnpaymentService")
public class SD0503MpLastUnpaymentServiceImpl extends EgovAbstractServiceImpl implements
        SD0503MpLastUnpaymentService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0503MpLastUnpaymentServiceImpl.class);

    @Resource(name="SD0503mpLastUnpaymentDAO")
    private SD0503MpLastUnpaymentDAO mpLastUnpaymentDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpLastUnpaymentIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_last_unpayment을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpLastUnpaymentVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpLastUnpayment(MpLastUnpaymentVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpLastUnpaymentDAO.insertMpLastUnpayment(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_last_unpayment을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpLastUnpaymentVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpLastUnpayment(MpLastUnpaymentVO vo) throws Exception {
        mpLastUnpaymentDAO.updateMpLastUnpayment(vo);
    }

    /**
	 * mp_last_unpayment을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpLastUnpaymentVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpLastUnpayment(MpLastUnpaymentVO vo) throws Exception {
        mpLastUnpaymentDAO.deleteMpLastUnpayment(vo);
    }

    /**
	 * mp_last_unpayment을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpLastUnpaymentVO
	 * @return 조회한 mp_last_unpayment
	 * @exception Exception
	 */
    public MpLastUnpaymentVO selectMpLastUnpayment(MpLastUnpaymentVO vo) throws Exception {
        MpLastUnpaymentVO resultVO = mpLastUnpaymentDAO.selectMpLastUnpayment(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_last_unpayment 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_last_unpayment 목록
	 * @exception Exception
	 */
    public List<?> selectMpLastUnpaymentList(MpLastUnpaymentDefaultVO searchVO) throws Exception {
        return mpLastUnpaymentDAO.selectMpLastUnpaymentList(searchVO);
    }

    /**
	 * mp_last_unpayment 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_last_unpayment 총 갯수
	 * @exception
	 */
    public int selectMpLastUnpaymentListTotCnt(MpLastUnpaymentDefaultVO searchVO) {
		return mpLastUnpaymentDAO.selectMpLastUnpaymentListTotCnt(searchVO);
	}
    
}
