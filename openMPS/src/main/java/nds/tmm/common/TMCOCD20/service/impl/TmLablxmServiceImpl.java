package nds.tmm.common.TMCOCD20.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.tmm.common.TMCOCD10.service.TMCOCD10DETAILDAO;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10DETAILVO;
import nds.tmm.common.TMCOCD20.dao.TmLablxmDAO;
import nds.tmm.common.TMCOCD20.service.TmLablxmService;
import nds.tmm.common.TMCOCD20.vo.TmLablxmDefaultVO;
import nds.tmm.common.TMCOCD20.vo.TmLablxmVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : TmLablxmServiceImpl.java
 * @Description : TmLablxm Business Implement class
 * @Modification Information
 *
 * @author OpenMPS Team
 * @since 2017. 7. 18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("tmLablxmService")
public class TmLablxmServiceImpl extends TMMPPBaseService implements
        TmLablxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TmLablxmServiceImpl.class);

    @Resource(name="tmLablxmDAO")
    private TmLablxmDAO tmLablxmDAO;
    
    /** ID Generation */
    //@Resource(name="{egovTmLablxmIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_lablxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TmLablxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public int insertTmLablxm(List<TmLablxmVO> vos) throws Exception {
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vos.toString());
    	
    	//tmLablxmDAO.insertTmLablxm(vo);
    	List<MultiDSTypeVO> mDS = new ArrayList();
    	for(TmLablxmVO reqVo : vos)
    	{
    		MultiDSTypeVO mvo = new MultiDSTypeVO();
    		mvo.setSqlId(resolveSqlId(reqVo.getDsType(), "TmLablxm"));
    		mvo.setRowData(reqVo);
    		mDS.add(mvo);
    	}
    	int iCnt = tmLablxmDAO.executeBatchMulti(mDS);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return iCnt;
    }

    /**
	 * tm_lablxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TmLablxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTmLablxm(TmLablxmVO vo) throws Exception {
        tmLablxmDAO.updateTmLablxm(vo);
    }

    /**
	 * tm_lablxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TmLablxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTmLablxm(TmLablxmVO vo) throws Exception {
        tmLablxmDAO.deleteTmLablxm(vo);
    }

    /**
	 * tm_lablxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_lablxm 목록
	 * @exception Exception
	 */
    public List<?> selectTmLablxmList(TmLablxmDefaultVO searchVO) throws Exception {
        return tmLablxmDAO.selectTmLablxmList(searchVO);
    }

    /**
	 * tm_lablxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_lablxm 총 갯수
	 * @exception
	 */
    public int selectTmLablxmListTotCnt(TmLablxmDefaultVO searchVO) {
		return tmLablxmDAO.selectTmLablxmListTotCnt(searchVO);
	}
    
}
