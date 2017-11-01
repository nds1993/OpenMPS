package nds.mpm.buy.PO0102.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.buy.PO0102.service.PigDochService;
import nds.mpm.buy.PO0102.vo.PigDochDefaultVO;
import nds.mpm.buy.PO0102.vo.PigDochVO;
import nds.mpm.buy.PO0102.service.PigDochDAO;

/**
 * @Class Name : PigDochServiceImpl.java
 * @Description : PigDoch Business Implement class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("pigDochService")
public class PigDochServiceImpl extends EgovAbstractServiceImpl implements
        PigDochService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PigDochServiceImpl.class);

    @Resource(name="pigDochDAO")
    private PigDochDAO pigDochDAO;
    
    /** ID Generation */
    //@Resource(name="{egovPigDochIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * pig_doch을 등록한다.
	 * @param vo - 등록할 정보가 담긴 PigDochVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertPigDoch(PigDochVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	pigDochDAO.insertPigDoch(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * pig_doch을 수정한다.
	 * @param vo - 수정할 정보가 담긴 PigDochVO
	 * @return void형
	 * @exception Exception
	 */
    public void updatePigDoch(PigDochVO vo) throws Exception {
        pigDochDAO.updatePigDoch(vo);
    }

    /**
	 * pig_doch을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 PigDochVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deletePigDoch(PigDochVO vo) throws Exception {
        pigDochDAO.deletePigDoch(vo);
    }

    /**
	 * pig_doch을 조회한다.
	 * @param vo - 조회할 정보가 담긴 PigDochVO
	 * @return 조회한 pig_doch
	 * @exception Exception
	 */
    public PigDochVO selectPigDoch(PigDochVO vo) throws Exception {
        PigDochVO resultVO = pigDochDAO.selectPigDoch(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * pig_doch 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return pig_doch 목록
	 * @exception Exception
	 */
    public List<?> selectPigDochList(PigDochDefaultVO searchVO) throws Exception {
        return pigDochDAO.selectPigDochList(searchVO);
    }

    /**
	 * pig_doch 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return pig_doch 총 갯수
	 * @exception
	 */
    public int selectPigDochListTotCnt(PigDochDefaultVO searchVO) {
		return pigDochDAO.selectPigDochListTotCnt(searchVO);
	}
    
    public List<?> selectPigDochIdSum(PigDochDefaultVO searchVO) throws Exception {
    	return pigDochDAO.selectPigDochIdSum(searchVO);
    }
    
}
