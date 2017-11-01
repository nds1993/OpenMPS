package nds.mpm.prod.PP0101.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0101.service.TmPlatxmDAO;
import nds.mpm.prod.PP0101.service.TmPlatxmService;
import nds.mpm.prod.PP0101.vo.TmPlatxmDefaultVO;
import nds.mpm.prod.PP0101.vo.TmPlatxmVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TmPlatxmServiceImpl.java
 * @Description : TmPlatxm Business Implement class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("tmPlatxmService")
public class TmPlatxmServiceImpl extends TMMPPBaseService implements
        TmPlatxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TmPlatxmServiceImpl.class);

    @Resource(name="tmPlatxmDAO")
    private TmPlatxmDAO tmPlatxmDAO;
    
    /** ID Generation */
    //@Resource(name="{egovTmPlatxmIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_platxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TmPlatxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertTmPlatxm(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int iCnt = 0;
    	
    	for(EgovMap reqVo : vos)
    	{
    		reqVo.put("deleYn", "N");
    		if("C".equals((String)reqVo.get("dsType")) && selectTmPlatxm(reqVo) != null)
    		{
    			result = new ResultEx( Consts.ResultCode.RC_DUPLICATE );
    			result.setMsg("이미 사용된 공장코드 또는 공장명칭입니다.");
    			return result;
    		}
    	}
    	
    	for(EgovMap reqVo : vos)
    	{
    		if("D".equals((String)reqVo.get("dsType")))
    		{
    			tmPlatxmDAO.deleteTmPlatxm(reqVo);
    		}
    		else
    		{
    			reqVo.put("deleYn", null);
    			if(selectTmPlatxm(reqVo) != null)
        		{
    				tmPlatxmDAO.updateTmPlatxm(reqVo);
        		}
    			else
    			{
    				tmPlatxmDAO.insertTmPlatxm(reqVo);
    			}
    		}
    		
    	}

    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	
        return result;
    }

    /**
	 * tm_platxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TmPlatxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTmPlatxm(EgovMap vo) throws Exception {
        //tmPlatxmDAO.updateTmPlatxm(vo);
    }

    /**
	 * tm_platxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TmPlatxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTmPlatxm(EgovMap vo) throws Exception {
        //tmPlatxmDAO.deleteTmPlatxm(vo);
    }

    /**
	 * tm_platxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TmPlatxmVO
	 * @return 조회한 tm_platxm
	 * @exception Exception
	 */
    public TmPlatxmVO selectTmPlatxm(EgovMap vo) throws Exception {
        TmPlatxmVO resultVO = tmPlatxmDAO.selectTmPlatxm(vo);
        return resultVO;
    }

    /**
	 * tm_platxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 목록
	 * @exception Exception
	 */
    public List<?> selectTmPlatxmList(TmPlatxmDefaultVO searchVO) throws Exception {
        return tmPlatxmDAO.selectTmPlatxmList(searchVO);
    }

    /**
	 * tm_platxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 총 갯수
	 * @exception
	 */
    public int selectTmPlatxmListTotCnt(TmPlatxmDefaultVO searchVO) {
		return tmPlatxmDAO.selectTmPlatxmListTotCnt(searchVO);
	}
    
    public int checkDupTmPlatxm_S(TmPlatxmDefaultVO searchVO) {
    	return tmPlatxmDAO.checkDupTmPlatxm_S(searchVO);
    }
    
    public List<?> selectGrupTmPlatxmList(TmPlatxmDefaultVO searchVO) throws Exception {
    	return tmPlatxmDAO.selectGrupTmPlatxmList(searchVO);
    }
}
