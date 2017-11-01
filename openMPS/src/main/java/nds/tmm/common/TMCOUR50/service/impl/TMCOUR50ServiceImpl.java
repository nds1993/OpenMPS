package nds.tmm.common.TMCOUR50.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.tmm.common.TMCOUR50.service.TMCOUR50DAO;
import nds.tmm.common.TMCOUR50.service.TMCOUR50Service;
import nds.tmm.common.TMCOUR50.vo.TMCOUR50DefaultVO;
import nds.tmm.common.TMCOUR50.vo.TMCOUR50VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : TMCOUR50ServiceImpl.java
 * @Description : TMCOUR50 Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOUR50Service")
public class TMCOUR50ServiceImpl extends TMMPPBaseService implements
        TMCOUR50Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOUR50ServiceImpl.class);

    @Resource(name="TMCOUR50DAO")
    private TMCOUR50DAO TMCOUR50DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOUR50IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_authxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR50VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public int insertTMCOUR50(List<TMCOUR50VO> vos) throws Exception {
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vos.toString());
    	
    	List<MultiDSTypeVO> mDS = new ArrayList();
    	//기존데이터가 존재하면 업데이트.
    	for(TMCOUR50VO reqVo : vos)
    	{
    		MultiDSTypeVO mvo = new MultiDSTypeVO();
    		
    		if(TMCOUR50DAO.selectTMCOUR50(reqVo) != null)
    		{
    			mvo.setSqlId(resolveSqlId("U", "TMCOUR50"));
    		}
    		else
    		{
    			mvo.setSqlId(resolveSqlId("C", "TMCOUR50"));
    		}
    		
    		mvo.setRowData(reqVo);
    		mDS.add(mvo);
    	}
    	
    	//TMCOUR50DAO.insertTMCOUR50(vos);
    	//TODO 해당 테이블 정보에 맞게 수정    	
    	TMCOUR50DAO.executeBatchMulti(mDS);
    	
        return 0;
    }

    /**
	 * tm_authxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR50VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOUR50(TMCOUR50VO vo) throws Exception {
        TMCOUR50DAO.updateTMCOUR50(vo);
    }

    /**
	 * tm_authxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR50VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOUR50(TMCOUR50VO vo) throws Exception {
        TMCOUR50DAO.deleteTMCOUR50(vo);
    }

    /**
	 * tm_authxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR50VO
	 * @return 조회한 tm_authxm
	 * @exception Exception
	 */
    public TMCOUR50VO selectTMCOUR50(TMCOUR50VO vo) throws Exception {
        TMCOUR50VO resultVO = TMCOUR50DAO.selectTMCOUR50(vo);
        return resultVO;
    }

    /**
	 * tm_authxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_authxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOUR50List(TMCOUR50DefaultVO searchVO) throws Exception {
        return TMCOUR50DAO.selectTMCOUR50List(searchVO);
    }

    /**
	 * tm_authxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_authxm 총 갯수
	 * @exception
	 */
    public int selectTMCOUR50ListTotCnt(TMCOUR50DefaultVO searchVO) {
		return TMCOUR50DAO.selectTMCOUR50ListTotCnt(searchVO);
	}
    
}
