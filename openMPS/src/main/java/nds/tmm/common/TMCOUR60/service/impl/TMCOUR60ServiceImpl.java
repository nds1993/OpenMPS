package nds.tmm.common.TMCOUR60.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOUR30.vo.TMCOUR30VO;
import nds.tmm.common.TMCOUR60.service.TMCOUR60DAO;
import nds.tmm.common.TMCOUR60.service.TMCOUR60Service;
import nds.tmm.common.TMCOUR60.vo.TMCOUR60DefaultVO;
import nds.tmm.common.TMCOUR60.vo.TMCOUR60VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : TMCOUR60ServiceImpl.java
 * @Description : TMCOUR60 Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOUR60Service")
public class TMCOUR60ServiceImpl extends TMMPPBaseService implements
        TMCOUR60Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOUR60ServiceImpl.class);

    @Resource(name="TMCOUR60DAO")
    private TMCOUR60DAO TMCOUR60DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOUR60IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_grusrx을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR60VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public int insertTMCOUR60(List<TMCOUR60VO> vos) throws Exception {
    	LOGGER.debug(vos.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	
    	List<MultiDSTypeVO> mDS = new ArrayList();
    	
    	for(TMCOUR60VO reqVo : vos)
    	{
    		if("C".equals(reqVo.getDsType()) && selectTMCOUR60(reqVo) != null)
    		{
    			return Consts.ResultCode.RC_DUPLICATE.getCode();
    		}
    		
    		MultiDSTypeVO mvo = new MultiDSTypeVO();
    		mvo.setSqlId(resolveSqlId(reqVo.getDsType(), "TMCOUR60"));
    		mvo.setRowData(reqVo);
    		mDS.add(mvo);
    	}
    	
    	//TMCOUR60DAO.insertTMCOUR60(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return TMCOUR60DAO.executeBatchMulti(mDS);
    }

    /**
	 * tm_grusrx을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR60VO
	 * @return void형
	 * @exception Exception
	 */
    public int updateTMCOUR60(List<Object> vos) throws Exception {
        return TMCOUR60DAO.executeBatchUpdate(vos);
    }

    /**
	 * tm_grusrx을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR60VO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteTMCOUR60(List<Object> vos) throws Exception {
        return TMCOUR60DAO.executeBatchDelete(vos);
    }

    /**
	 * tm_grusrx을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR60VO
	 * @return 조회한 tm_grusrx
	 * @exception Exception
	 */
    public TMCOUR60VO selectTMCOUR60(TMCOUR60VO vo) throws Exception {
        TMCOUR60VO resultVO = TMCOUR60DAO.selectTMCOUR60(vo);
        return resultVO;
    }

    /**
	 * tm_grusrx 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_grusrx 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOUR60List(TMCOUR60DefaultVO searchVO) throws Exception {
        return TMCOUR60DAO.selectTMCOUR60List(searchVO);
    }

    /**
	 * tm_grusrx 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_grusrx 총 갯수
	 * @exception
	 */
    public int selectTMCOUR60ListTotCnt(TMCOUR60DefaultVO searchVO) {
		return TMCOUR60DAO.selectTMCOUR60ListTotCnt(searchVO);
	}
    
    public List<?> selectTMCOUR60UserList_D(TMCOUR60DefaultVO searchVO) throws Exception {
    	return TMCOUR60DAO.selectTMCOUR60UserList_D(searchVO);
    }
}
