package nds.tmm.common.TMCOBD10.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOBD10.service.TMCOBD10DAO;
import nds.tmm.common.TMCOBD10.service.TMCOBD10Service;
import nds.tmm.common.TMCOBD10.vo.TMCOBD10VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOBD10ServiceImpl.java
 * @Description : TMCOBD10 Business Implement class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOBD10Service")
public class TMCOBD10ServiceImpl extends TMMPPBaseService implements
        TMCOBD10Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOBD10ServiceImpl.class);

    @Resource(name="TMCOBD10DAO")
    private TMCOBD10DAO TMCOBD10DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOBD10IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_bdcaxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertTMCOBD10(List<EgovMap> vos) throws Exception {
    	
ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")) && selectTMCOBD10(reqVo) != null)
    		{
    			return new ResultEx( Consts.ResultCode.RC_DUPLICATE );
    		}
    		
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			TMCOBD10DAO.insertTMCOBD10(reqVo);
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			TMCOBD10DAO.updateTMCOBD10(reqVo);
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			TMCOBD10DAO.deleteTMCOBD10(reqVo);
    		}
    		
    	}

    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	
        return result;
    	
    }

    /**
	 * tm_bdcaxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOBD10VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOBD10(EgovMap vo) throws Exception {
        TMCOBD10DAO.updateTMCOBD10(vo);
    }

    /**
	 * tm_bdcaxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOBD10VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOBD10(EgovMap vo) throws Exception {
        TMCOBD10DAO.deleteTMCOBD10(vo);
    }

    /**
	 * tm_bdcaxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOBD10VO
	 * @return 조회한 tm_bdcaxm
	 * @exception Exception
	 */
    public TMCOBD10VO selectTMCOBD10(EgovMap vo) throws Exception {
        TMCOBD10VO resultVO = TMCOBD10DAO.selectTMCOBD10(vo);
        return resultVO;
    }

    /**
	 * tm_bdcaxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_bdcaxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOBD10List(TMCOBD10VO searchVO) throws Exception {
        return TMCOBD10DAO.selectTMCOBD10List(searchVO);
    }

    /**
	 * tm_bdcaxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_bdcaxm 총 갯수
	 * @exception
	 */
    public int selectTMCOBD10ListTotCnt(TMCOBD10VO searchVO) {
		return TMCOBD10DAO.selectTMCOBD10ListTotCnt(searchVO);
	}
    
}
