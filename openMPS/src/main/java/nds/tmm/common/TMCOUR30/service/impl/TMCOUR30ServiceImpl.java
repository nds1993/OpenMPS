package nds.tmm.common.TMCOUR30.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOUR30.service.TMCOUR30DAO;
import nds.tmm.common.TMCOUR30.service.TMCOUR30Service;
import nds.tmm.common.TMCOUR30.vo.TMCOUR30DefaultVO;
import nds.tmm.common.TMCOUR30.vo.TMCOUR30VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : TMCOUR30ServiceImpl.java
 * @Description : TMCOUR30 Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOUR30Service")
public class TMCOUR30ServiceImpl extends TMMPPBaseService implements
        TMCOUR30Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOUR30ServiceImpl.class);

    @Resource(name="TMCOUR30DAO")
    private TMCOUR30DAO TMCOUR30DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOUR30IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_menuxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR30VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public int insertTMCOUR30(List<TMCOUR30VO> vos) throws Exception {
    	
    	LOGGER.debug("insertTMCOUR30 :: " + vos.toString());
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	//중복검색
    	
    	List<MultiDSTypeVO> mDS = new ArrayList();
    	
    	for(TMCOUR30VO reqVo : vos)
    	{
    		if("C".equals(reqVo.getDsType()) && selectTMCOUR30(reqVo) != null)
    		{
    			return Consts.ResultCode.RC_DUPLICATE.getCode();
    		}
    		
    		MultiDSTypeVO mvo = new MultiDSTypeVO();
    		mvo.setSqlId(resolveSqlId(reqVo.getDsType(), "TMCOUR30"));
    		mvo.setRowData(reqVo);
    		mDS.add(mvo);
    	}
    	return TMCOUR30DAO.executeBatchMulti(mDS);
    }

    /**
	 * tm_menuxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR30VO
	 * @return void형
	 * @exception Exception
	 */
    public int updateTMCOUR30(List<Object> vos) throws Exception {
        return TMCOUR30DAO.executeBatchUpdate(vos);
    }

    /**
	 * tm_menuxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR30VO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteTMCOUR30(List<Object> vos) throws Exception {
        return TMCOUR30DAO.executeBatchDelete(vos);
    }

    /**
	 * tm_menuxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR30VO
	 * @return 조회한 tm_menuxm
	 * @exception Exception
	 */
    public TMCOUR30VO selectTMCOUR30(Object vo) throws Exception {
        TMCOUR30VO resultVO = TMCOUR30DAO.selectTMCOUR30(vo);
        return resultVO;
    }

    /**
	 * tm_menuxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_menuxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOUR30List(TMCOUR30DefaultVO searchVO) throws Exception {
        return TMCOUR30DAO.selectTMCOUR30List(searchVO);
    }

    /**
	 * tm_menuxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_menuxm 총 갯수
	 * @exception
	 */
    public int selectTMCOUR30ListTotCnt(TMCOUR30DefaultVO searchVO) {
		return TMCOUR30DAO.selectTMCOUR30ListTotCnt(searchVO);
	}
    
    public List<?> selectTMCOUR30TreeList(TMCOUR30DefaultVO searchVO) throws Exception{
    	return TMCOUR30DAO.selectTMCOUR30TreeList(searchVO);
    }
}
