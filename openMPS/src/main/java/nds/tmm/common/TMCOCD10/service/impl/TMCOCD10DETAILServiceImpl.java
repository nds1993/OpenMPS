package nds.tmm.common.TMCOCD10.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOCD10.service.TMCOCD10DETAILDAO;
import nds.tmm.common.TMCOCD10.service.TMCOCD10DETAILService;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10DETAILDefaultVO;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10DETAILVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : TMCOCD10DETAILServiceImpl.java
 * @Description : TMCOCD10DETAIL Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOCD10DETAILService")
public class TMCOCD10DETAILServiceImpl extends TMMPPBaseService implements
        TMCOCD10DETAILService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOCD10DETAILServiceImpl.class);

    @Resource(name="TMCOCD10DETAILDAO")
    private TMCOCD10DETAILDAO TMCOCD10DETAILDAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOCD10DETAILIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_codexd을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOCD10DETAILVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public int insertTMCOCD10DETAIL(List<TMCOCD10DETAILVO> vos) throws Exception {
    	LOGGER.debug(vos.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	List<MultiDSTypeVO> mDS = new ArrayList();
    	for(TMCOCD10DETAILVO reqVo : vos)
    	{
    		if("C".equals(reqVo.getDsType()) && selectTMCOCD10DETAIL(reqVo) != null)
    		{
    			return Consts.ResultCode.RC_DUPLICATE.getCode();
    		}
    		
    		MultiDSTypeVO mvo = new MultiDSTypeVO();
    		mvo.setSqlId(resolveSqlId(reqVo.getDsType(), "TMCOCD10DETAIL"));
    		mvo.setRowData(reqVo);
    		mDS.add(mvo);
    	}
    	int iCnt = TMCOCD10DETAILDAO.executeBatchMulti(mDS);
    	
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return iCnt;
    }

    /**
	 * tm_codexd을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOCD10DETAILVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateTMCOCD10DETAIL(List<Object> vos) throws Exception {
    	return TMCOCD10DETAILDAO.executeBatchUpdate(vos);
    }

    /**
	 * tm_codexd을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOCD10DETAILVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteTMCOCD10DETAIL(List<Object> vos) throws Exception {
    	return TMCOCD10DETAILDAO.executeBatchDelete(vos);
    }

    /**
	 * tm_codexd을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOCD10DETAILVO
	 * @return 조회한 tm_codexd
	 * @exception Exception
	 */
    public TMCOCD10DETAILVO selectTMCOCD10DETAIL(Object vo) throws Exception {
        TMCOCD10DETAILVO resultVO = TMCOCD10DETAILDAO.selectTMCOCD10DETAIL(vo);
        return resultVO;
    }

    /**
	 * tm_codexd 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_codexd 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOCD10DETAILList(TMCOCD10DETAILDefaultVO searchVO) throws Exception {
        return TMCOCD10DETAILDAO.selectTMCOCD10DETAILList(searchVO);
    }

    /**
	 * tm_codexd 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_codexd 총 갯수
	 * @exception
	 */
    public int selectTMCOCD10DETAILListTotCnt(TMCOCD10DETAILDefaultVO searchVO) {
		return TMCOCD10DETAILDAO.selectTMCOCD10DETAILListTotCnt(searchVO);
	}
    public List<?> selectTMCOCD10DETAILSDDCList_D(TMCOCD10DETAILDefaultVO searchVO) throws Exception {
		return TMCOCD10DETAILDAO.selectTMCOCD10DETAILSDDCList_D(searchVO);
    }
    public List<?> selectTMCOCD10DETAILCodeList_D(TMCOCD10DETAILDefaultVO searchVO) throws Exception {
    	return TMCOCD10DETAILDAO.selectTMCOCD10DETAILCodeList_D(searchVO);
    }

}
