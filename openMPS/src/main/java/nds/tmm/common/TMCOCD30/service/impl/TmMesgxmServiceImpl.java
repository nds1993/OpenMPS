package nds.tmm.common.TMCOCD30.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOCD30.dao.TmMesgxmDAO;
import nds.tmm.common.TMCOCD30.service.TmMesgxmService;
import nds.tmm.common.TMCOCD30.vo.TmMesgxmDefaultVO;
import nds.tmm.common.TMCOCD30.vo.TmMesgxmVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : TmMesgxmServiceImpl.java
 * @Description : TmMesgxm Business Implement class
 * @Modification Information
 *
 * @author OpenMPS Team
 * @since 2017. 7. 18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("tmMesgxmService")
public class TmMesgxmServiceImpl extends TMMPPBaseService implements
        TmMesgxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TmMesgxmServiceImpl.class);

    @Resource(name="tmMesgxmDAO")
    private TmMesgxmDAO tmMesgxmDAO;
    
    /** ID Generation */
    //@Resource(name="{egovTmMesgxmIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_mesgxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TmMesgxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public int insertTmMesgxm(List<TmMesgxmVO> vos) throws Exception {
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vos.toString());
    	
    	//tmMesgxmDAO.insertTmMesgxm(vo);
    	List<MultiDSTypeVO> mDS = new ArrayList();
    	for(TmMesgxmVO reqVo : vos)
    	{
    		if("C".equals(reqVo.getDsType()) && selectTmMesgxm(reqVo) != null)
    		{
    			return Consts.ResultCode.RC_DUPLICATE.getCode();
    		}
    		
    		MultiDSTypeVO mvo = new MultiDSTypeVO();
    		mvo.setSqlId(resolveSqlId(reqVo.getDsType(), "TmMesgxm"));
    		mvo.setRowData(reqVo);
    		mDS.add(mvo);
    	}
    	int iCnt = tmMesgxmDAO.executeBatchMulti(mDS);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return iCnt;
    }

    /**
	 * tm_mesgxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TmMesgxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTmMesgxm(TmMesgxmVO vo) throws Exception {
        tmMesgxmDAO.updateTmMesgxm(vo);
    }

    /**
	 * tm_mesgxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TmMesgxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTmMesgxm(TmMesgxmVO vo) throws Exception {
        tmMesgxmDAO.deleteTmMesgxm(vo);
    }

    /**
	 * tm_mesgxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TmMesgxmVO
	 * @return 조회한 tm_mesgxm
	 * @exception Exception
	 */
    public TmMesgxmVO selectTmMesgxm(TmMesgxmVO vo) throws Exception {
        TmMesgxmVO resultVO = tmMesgxmDAO.selectTmMesgxm(vo);
        return resultVO;
    }

    /**
	 * tm_mesgxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_mesgxm 목록
	 * @exception Exception
	 */
    public List<?> selectTmMesgxmList(TmMesgxmDefaultVO searchVO) throws Exception {
    	
    	if( searchVO.getSearchKeyword() != null )
    	{
    		return tmMesgxmDAO.selectTmMesgxmListByKeyword(searchVO);
    	}
    	else
    	{
    		return tmMesgxmDAO.selectTmMesgxmList(searchVO);
    	}
    }

    /**
	 * tm_mesgxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_mesgxm 총 갯수
	 * @exception
	 */
    public int selectTmMesgxmListTotCnt(TmMesgxmDefaultVO searchVO) {
    	
    	if( searchVO.getSearchKeyword() != null )
    	{
    		return tmMesgxmDAO.selectTmMesgxmListByKeywordCnt(searchVO);
    	}
    	else
    	{
    		return tmMesgxmDAO.selectTmMesgxmListTotCnt(searchVO);
    	}
	}
    
}
