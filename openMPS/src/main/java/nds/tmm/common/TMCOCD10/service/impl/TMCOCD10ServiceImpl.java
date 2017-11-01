package nds.tmm.common.TMCOCD10.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOCD10.service.TMCOCD10DAO;
import nds.tmm.common.TMCOCD10.service.TMCOCD10Service;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10DefaultVO;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : TMCOCD10ServiceImpl.java
 * @Description : TMCOCD10 Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOCD10Service")
public class TMCOCD10ServiceImpl extends TMMPPBaseService implements
        TMCOCD10Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOCD10ServiceImpl.class);

    @Resource(name="TMCOCD10DAO")
    private TMCOCD10DAO TMCOCD10DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOCD10IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * BASE_INFO을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOCD10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOCD10(TMCOCD10VO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	TMCOCD10DAO.insertTMCOCD10(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }
    
    /**
	 * BASE_INFO을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOCD10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public int insertTMCOCD10(List<TMCOCD10VO> vos) throws Exception {
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	
    	int iCnt = 0;
    	List<MultiDSTypeVO> mDS = new ArrayList();
    	for(TMCOCD10VO reqVo : vos)
    	{
    		if("C".equals(reqVo.getDsType()) && selectTMCOCD10(reqVo) != null)
    		{
    			return Consts.ResultCode.RC_DUPLICATE.getCode();
    		}
    		
    		MultiDSTypeVO mvo = new MultiDSTypeVO();
    		mvo.setSqlId(resolveSqlId(reqVo.getDsType(), "TMCOCD10"));
    		mvo.setRowData(reqVo);
    		mDS.add(mvo);
    	}
    	
    	iCnt = TMCOCD10DAO.executeBatchMulti(mDS);
    	
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return iCnt;
    }

    /**
	 * BASE_INFO을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOCD10VO
	 * @return void형
	 * @exception Exception
	 */
    public int updateTMCOCD10(List<Object> vos) throws Exception {
        return TMCOCD10DAO.executeBatchUpdate(vos);
    }

    /**
	 * BASE_INFO을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOCD10VO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteTMCOCD10(List<Object> vos) throws Exception {
    	return TMCOCD10DAO.executeBatchDelete(vos);
    }

    /**
	 * BASE_INFO을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOCD10VO
	 * @return 조회한 BASE_INFO
	 * @exception Exception
	 */
    public TMCOCD10VO selectTMCOCD10(Object vo) throws Exception {
        TMCOCD10VO resultVO = TMCOCD10DAO.selectTMCOCD10(vo);
        return resultVO;
    }

    /**
	 * BASE_INFO 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return BASE_INFO 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOCD10List(TMCOCD10DefaultVO searchVO) throws Exception {
        return TMCOCD10DAO.selectTMCOCD10List(searchVO);
    }

    /**
	 * BASE_INFO 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return BASE_INFO 총 갯수
	 * @exception
	 */
    public int selectTMCOCD10ListTotCnt(TMCOCD10DefaultVO searchVO) {
		return TMCOCD10DAO.selectTMCOCD10ListTotCnt(searchVO);
	}
    
}
