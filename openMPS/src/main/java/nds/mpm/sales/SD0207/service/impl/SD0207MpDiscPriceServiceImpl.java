package nds.mpm.sales.SD0207.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0205.vo.MpDiscPriceDefaultVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceTitleVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceVO;
import nds.mpm.sales.SD0207.service.SD0207MpDiscPriceDAO;
import nds.mpm.sales.SD0207.service.SD0207MpDiscPriceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDiscPriceServiceImpl.java
 * @Description : MpDiscPrice Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD0207MpDiscPriceService")
public class SD0207MpDiscPriceServiceImpl extends EgovAbstractServiceImpl implements
        SD0207MpDiscPriceService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0207MpDiscPriceServiceImpl.class);

    @Resource(name="SD0207MpDiscPriceDAO")
    private SD0207MpDiscPriceDAO mpDiscPriceDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpDiscPriceIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_disc_price을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDiscPriceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpDiscPrice(MpDiscPriceVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpDiscPriceDAO.insertMpDiscPrice(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_disc_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDiscPriceVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpDiscPrice(MpDiscPriceVO vo) throws Exception {
        mpDiscPriceDAO.updateMpDiscPrice(vo);
    }

    /**
	 * mp_disc_price을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDiscPriceVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpDiscPrice(MpDiscPriceVO vo) throws Exception {
        mpDiscPriceDAO.deleteMpDiscPrice(vo);
    }

    /**
	 * mp_disc_price을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDiscPriceVO
	 * @return 조회한 mp_disc_price
	 * @exception Exception
	 */
    public MpDiscPriceVO selectMpDiscPrice(MpDiscPriceVO vo) throws Exception {
        MpDiscPriceVO resultVO = mpDiscPriceDAO.selectMpDiscPrice(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_disc_price 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price 목록
	 * @exception Exception
	 */
    public List<?> selectMpDiscPriceTitleList(MpDiscPriceTitleVO searchVO) throws Exception {
        return mpDiscPriceDAO.selectMpDiscPriceTitleList(searchVO);
    }

    /**
	 * mp_disc_price 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price 총 갯수
	 * @exception
	 */
    public int selectMpDiscPriceListTotCnt(MpDiscPriceDefaultVO searchVO) {
		return mpDiscPriceDAO.selectMpDiscPriceListTotCnt(searchVO);
	}
    
    public ResultEx updateMpDiscPriceConfirm(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	for(EgovMap vo : vos)
    	{
        	if(mpDiscPriceDAO.updateMpDiscPriceConfirm(vo) == 0)
        		throw new Exception("승인이 실패하였습니다.");
    	}
    	
    	return result;
    }
    
    public List<?> selectMpDiscPriceDetailList(MpDiscPriceTitleVO searchVO) throws Exception {
		return mpDiscPriceDAO.selectMpDiscPriceDetailList(searchVO);
    }
}
