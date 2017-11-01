package nds.tmm.common.TMCOUR40.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOUR40.service.TMCOUR40DAO;
import nds.tmm.common.TMCOUR40.service.TMCOUR40Service;
import nds.tmm.common.TMCOUR40.vo.TMCOUR40DefaultVO;
import nds.tmm.common.TMCOUR40.vo.TMCOUR40VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : TMCOUR40ServiceImpl.java
 * @Description : TMCOUR40 Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOUR40Service")
public class TMCOUR40ServiceImpl extends TMMPPBaseService implements
        TMCOUR40Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOUR40ServiceImpl.class);

    @Resource(name="TMCOUR40DAO")
    private TMCOUR40DAO TMCOUR40DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOUR40IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_grupxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR40VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public int insertTMCOUR40(List<TMCOUR40VO> vos) throws Exception {
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vos.toString());
    	List<MultiDSTypeVO> mDS = new ArrayList();
    	
    	for(TMCOUR40VO reqVo : vos)
    	{
    		if("C".equals(reqVo.getDsType()) && selectTMCOUR40(reqVo) != null)
    		{
    			return Consts.ResultCode.RC_DUPLICATE.getCode();
    		}
    		
    		MultiDSTypeVO mvo = new MultiDSTypeVO();
    		mvo.setSqlId(resolveSqlId(reqVo.getDsType(), "TMCOUR40"));
    		mvo.setRowData(reqVo);
    		mDS.add(mvo);
    	}
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return TMCOUR40DAO.executeBatchMulti(mDS);
    }

    /**
	 * tm_grupxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR40VO
	 * @return void형
	 * @exception Exception
	 */
    public int updateTMCOUR40(List<Object> vos) throws Exception {
        return TMCOUR40DAO.executeBatchUpdate(vos);
    }

    /**
	 * tm_grupxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR40VO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteTMCOUR40(List<Object> vos) throws Exception {
    	return TMCOUR40DAO.executeBatchDelete(vos);
    }

    /**
	 * tm_grupxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR40VO
	 * @return 조회한 tm_grupxm
	 * @exception Exception
	 */
    public TMCOUR40VO selectTMCOUR40(TMCOUR40VO vo) throws Exception {
        TMCOUR40VO resultVO = TMCOUR40DAO.selectTMCOUR40(vo);
        return resultVO;
    }

    /**
	 * tm_grupxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_grupxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOUR40List(TMCOUR40DefaultVO searchVO) throws Exception {
        return TMCOUR40DAO.selectTMCOUR40List(searchVO);
    }

    /**
	 * tm_grupxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_grupxm 총 갯수
	 * @exception
	 */
    public int selectTMCOUR40ListTotCnt(TMCOUR40DefaultVO searchVO) {
		return TMCOUR40DAO.selectTMCOUR40ListTotCnt(searchVO);
	}
    
}
