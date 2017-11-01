package nds.mpm.prod.buy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.buy.service.PighisBuyService;
import nds.mpm.prod.buy.vo.PighisBuyDefaultVO;
import nds.mpm.prod.buy.vo.PighisBuyVO;
import nds.mpm.prod.buy.service.PighisBuyDAO;

/**
 * @Class Name : PighisBuyServiceImpl.java
 * @Description : PighisBuy Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.21
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("pighisBuyService")
public class PighisBuyServiceImpl extends EgovAbstractServiceImpl implements
        PighisBuyService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PighisBuyServiceImpl.class);

    @Resource(name="pighisBuyDAO")
    private PighisBuyDAO pighisBuyDAO;
    
    /** ID Generation */
    //@Resource(name="{egovPighisBuyIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * pighis_buy을 등록한다.
	 * @param vo - 등록할 정보가 담긴 PighisBuyVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertPighisBuy(PighisBuyVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	pighisBuyDAO.insertPighisBuy(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * pighis_buy을 수정한다.
	 * @param vo - 수정할 정보가 담긴 PighisBuyVO
	 * @return void형
	 * @exception Exception
	 */
    public void updatePighisBuy(PighisBuyVO vo) throws Exception {
        pighisBuyDAO.updatePighisBuy(vo);
    }

    /**
	 * pighis_buy을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 PighisBuyVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deletePighisBuy(PighisBuyVO vo) throws Exception {
        pighisBuyDAO.deletePighisBuy(vo);
    }

    /**
	 * pighis_buy을 조회한다.
	 * @param vo - 조회할 정보가 담긴 PighisBuyVO
	 * @return 조회한 pighis_buy
	 * @exception Exception
	 */
    public PighisBuyVO selectPighisBuy(PighisBuyVO vo) throws Exception {
        PighisBuyVO resultVO = pighisBuyDAO.selectPighisBuy(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * pighis_buy 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return pighis_buy 목록
	 * @exception Exception
	 */
    public List<?> selectPighisBuyList(PighisBuyDefaultVO searchVO) throws Exception {
        return pighisBuyDAO.selectPighisBuyList(searchVO);
    }

    /**
	 * pighis_buy 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return pighis_buy 총 갯수
	 * @exception
	 */
    public int selectPighisBuyListTotCnt(PighisBuyDefaultVO searchVO) {
		return pighisBuyDAO.selectPighisBuyListTotCnt(searchVO);
	}
    
}
