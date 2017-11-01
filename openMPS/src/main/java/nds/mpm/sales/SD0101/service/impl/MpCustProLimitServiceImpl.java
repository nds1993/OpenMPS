package nds.mpm.sales.SD0101.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.sales.SD0101.service.MpCustProLimitService;
import nds.mpm.sales.SD0101.vo.MpCustProLimitDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustProLimitVO;
import nds.mpm.sales.SD0101.service.MpCustProLimitDAO;

/**
 * @Class Name : MpCustProLimitServiceImpl.java
 * @Description : MpCustProLimit Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpCustProLimitService")
public class MpCustProLimitServiceImpl extends EgovAbstractServiceImpl implements
        MpCustProLimitService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpCustProLimitServiceImpl.class);

    @Resource(name="mpCustProLimitDAO")
    private MpCustProLimitDAO mpCustProLimitDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpCustProLimitIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_cust_pro_limit을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustProLimitVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpCustProLimit(MpCustProLimitVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpCustProLimitDAO.insertMpCustProLimit(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_cust_pro_limit을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustProLimitVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpCustProLimit(MpCustProLimitVO vo) throws Exception {
        mpCustProLimitDAO.updateMpCustProLimit(vo);
    }

    /**
	 * mp_cust_pro_limit을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustProLimitVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpCustProLimit(MpCustProLimitVO vo) throws Exception {
        mpCustProLimitDAO.deleteMpCustProLimit(vo);
    }

    /**
	 * mp_cust_pro_limit을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustProLimitVO
	 * @return 조회한 mp_cust_pro_limit
	 * @exception Exception
	 */
    public MpCustProLimitVO selectMpCustProLimit(MpCustProLimitVO vo) throws Exception {
        MpCustProLimitVO resultVO = mpCustProLimitDAO.selectMpCustProLimit(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_cust_pro_limit 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_pro_limit 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustProLimitList(MpCustProLimitDefaultVO searchVO) throws Exception {
        return mpCustProLimitDAO.selectMpCustProLimitList(searchVO);
    }

    /**
	 * mp_cust_pro_limit 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_pro_limit 총 갯수
	 * @exception
	 */
    public int selectMpCustProLimitListTotCnt(MpCustProLimitDefaultVO searchVO) {
		return mpCustProLimitDAO.selectMpCustProLimitListTotCnt(searchVO);
	}
    
}
