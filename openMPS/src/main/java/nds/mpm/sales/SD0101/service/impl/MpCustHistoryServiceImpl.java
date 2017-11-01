package nds.mpm.sales.SD0101.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.sales.SD0101.service.MpCustHistoryService;
import nds.mpm.sales.SD0101.vo.MpCustHistoryDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustHistoryVO;
import nds.mpm.sales.SD0101.service.MpCustHistoryDAO;

/**
 * @Class Name : MpCustHistoryServiceImpl.java
 * @Description : MpCustHistory Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpCustHistoryService")
public class MpCustHistoryServiceImpl extends EgovAbstractServiceImpl implements
        MpCustHistoryService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpCustHistoryServiceImpl.class);

    @Resource(name="mpCustHistoryDAO")
    private MpCustHistoryDAO mpCustHistoryDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpCustHistoryIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_cust_history을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustHistoryVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpCustHistory(MpCustHistoryVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	//mpCustHistoryDAO.insertMpCustHistory(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_cust_history을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustHistoryVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpCustHistory(MpCustHistoryVO vo) throws Exception {
        //mpCustHistoryDAO.updateMpCustHistory(vo);
    }

    /**
	 * mp_cust_history을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustHistoryVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpCustHistory(MpCustHistoryVO vo) throws Exception {
        //mpCustHistoryDAO.deleteMpCustHistory(vo);
    }

    /**
	 * mp_cust_history을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustHistoryVO
	 * @return 조회한 mp_cust_history
	 * @exception Exception
	 */
    public MpCustHistoryVO selectMpCustHistory(MpCustHistoryVO vo) throws Exception {
        MpCustHistoryVO resultVO = mpCustHistoryDAO.selectMpCustHistory(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_cust_history 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_history 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustHistoryList(MpCustHistoryDefaultVO searchVO) throws Exception {
        return mpCustHistoryDAO.selectMpCustHistoryList(searchVO);
    }

    /**
	 * mp_cust_history 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_history 총 갯수
	 * @exception
	 */
    public int selectMpCustHistoryListTotCnt(MpCustHistoryDefaultVO searchVO) {
		return mpCustHistoryDAO.selectMpCustHistoryListTotCnt(searchVO);
	}
    
}
