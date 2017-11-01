package nds.mpm.sales.SD0405.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.sales.SD0405.service.MpOrderDDAO;
import nds.mpm.sales.SD0405.service.MpOrderDService;
import nds.mpm.sales.SD0405.vo.MpOrderDDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderDVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderDServiceImpl.java
 * @Description : MpOrderD Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpOrderDService")
public class MpOrderDServiceImpl extends EgovAbstractServiceImpl implements
        MpOrderDService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpOrderDServiceImpl.class);

    @Resource(name="mpOrderDDAO")
    private MpOrderDDAO mpOrderDDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpOrderDIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_order_d을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpOrderDVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpOrderD(EgovMap vo) throws Exception {
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	
    	//mpOrderDDAO.insertMpOrderD(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_order_d을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpOrderDVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpOrderD(EgovMap vo) throws Exception {
        mpOrderDDAO.updateMpOrderD(vo);
    }

    /**
	 * mp_order_d을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpOrderDVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpOrderD(EgovMap vo) throws Exception {
        mpOrderDDAO.deleteMpOrderD(vo);
    }

    /**
	 * mp_order_d을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpOrderDVO
	 * @return 조회한 mp_order_d
	 * @exception Exception
	 */
    public EgovMap selectMpOrderD(MpOrderDVO vo) throws Exception {
    	EgovMap resultVO = mpOrderDDAO.selectMpOrderD(vo);
        return resultVO;
    }

    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDList(MpOrderDDefaultVO searchVO) throws Exception {
        return mpOrderDDAO.selectMpOrderDList(searchVO);
    }

    /**
	 * mp_order_d 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 총 갯수
	 * @exception
	 */
    public int selectMpOrderDListTotCnt(MpOrderDDefaultVO searchVO) {
		return mpOrderDDAO.selectMpOrderDListTotCnt(searchVO);
	}
    
    
    
}
