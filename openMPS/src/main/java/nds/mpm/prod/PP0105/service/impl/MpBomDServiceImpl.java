package nds.mpm.prod.PP0105.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.prod.PP0105.service.MpBomDDAO;
import nds.mpm.prod.PP0105.service.MpBomDService;
import nds.mpm.prod.PP0105.vo.MpBomDDefaultVO;
import nds.mpm.prod.PP0105.vo.MpBomDVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpBomDServiceImpl.java
 * @Description : MpBomD Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpBomDService")
public class MpBomDServiceImpl extends EgovAbstractServiceImpl implements
        MpBomDService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpBomDServiceImpl.class);

    @Resource(name="mpBomDDAO")
    private MpBomDDAO mpBomDDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpBomDIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_bom_d을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBomDVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpBomD(EgovMap vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpBomDDAO.insertMpBomD(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_bom_d을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBomDVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpBomD(EgovMap vo) throws Exception {
        mpBomDDAO.updateMpBomD(vo);
    }

    /**
	 * mp_bom_d을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBomDVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpBomD(EgovMap vo) throws Exception {
        mpBomDDAO.deleteMpBomD(vo);
    }

    /**
	 * mp_bom_d을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBomDVO
	 * @return 조회한 mp_bom_d
	 * @exception Exception
	 */
    public MpBomDVO selectMpBomD(EgovMap vo) throws Exception {
        MpBomDVO resultVO = mpBomDDAO.selectMpBomD(vo);
        return resultVO;
    }

    /**
	 * mp_bom_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bom_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpBomDList(MpBomDDefaultVO searchVO) throws Exception {
        return mpBomDDAO.selectMpBomDList(searchVO);
    }

    /**
	 * mp_bom_d 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bom_d 총 갯수
	 * @exception
	 */
    public int selectMpBomDListTotCnt(MpBomDDefaultVO searchVO) {
		return mpBomDDAO.selectMpBomDListTotCnt(searchVO);
	}
    
}
