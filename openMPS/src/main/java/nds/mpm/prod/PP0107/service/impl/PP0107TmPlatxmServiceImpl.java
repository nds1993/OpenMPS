package nds.mpm.prod.PP0107.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0101.vo.TmPlatxmDefaultVO;
import nds.mpm.prod.PP0101.vo.TmPlatxmVO;
import nds.mpm.prod.PP0107.service.PP0107TmPlatxmDAO;
import nds.mpm.prod.PP0107.service.PP0107TmPlatxmService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TmPlatxmServiceImpl.java
 * @Description : TmPlatxm Business Implement class
 * @Modification Information
 *
 * @author 11
 * @since 11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PP0107TmPlatxmService")
public class PP0107TmPlatxmServiceImpl extends EgovAbstractServiceImpl implements
        PP0107TmPlatxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PP0107TmPlatxmServiceImpl.class);

    @Resource(name="PP0107TmPlatxmDAO")
    private PP0107TmPlatxmDAO PP0107TmPlatxmDAO;
    
    /** ID Generation */
    //@Resource(name="{egovTmPlatxmIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_platxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TmPlatxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertTmPlatWarhxm(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int iCnt = 0;
    	
    	for(EgovMap reqVo : vos)
    	{
			if("C".equals((String)reqVo.get("dsType")) && PP0107TmPlatxmDAO.checkTmPlatxmWhcode(reqVo) > 0)
			{
				result.setMsg("이미 존재하는 창고코드 입니다.");
				result.setResultCode(Consts.ResultCode.RC_DUPLICATE.getCode());
				return result;
			}
    	}
    	
    	for(EgovMap reqVo : vos)
    	{
    		if(StringUtils.isEmpty((String)reqVo.get("whCode"))) continue;
    		
    		if("D".equals((String)reqVo.get("dsType")))
    		{
    			PP0107TmPlatxmDAO.deleteTmPlatxm(reqVo);
    		}
    		else {
    			
    			if(PP0107TmPlatxmDAO.checkTmPlatxmWhcode(reqVo) > 0)
    			{
    				PP0107TmPlatxmDAO.updateTmPlatxm(reqVo);
    			}
    			else
    			{
        			PP0107TmPlatxmDAO.insertTmPlatWarhxm(reqVo);
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
    	//PP0107TmPlatxmDAO.updateTmPlatxm(vo);
    }

    /**
	 * tm_platxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TmPlatxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTmPlatxm(EgovMap vo) throws Exception {
    	//PP0107TmPlatxmDAO.deleteTmPlatxm(vo);
    }

    /**
	 * tm_platxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TmPlatxmVO
	 * @return 조회한 tm_platxm
	 * @exception Exception
	 */
    public TmPlatxmVO selectTmPlatxm(TmPlatxmVO vo) throws Exception {
        TmPlatxmVO resultVO = PP0107TmPlatxmDAO.selectTmPlatxm(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * tm_platxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 목록
	 * @exception Exception
	 */
    public List<?> selectTmPlatxmList(TmPlatxmDefaultVO searchVO) throws Exception {
        return PP0107TmPlatxmDAO.selectTmPlatxmList(searchVO);
    }

    /**
	 * tm_platxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 총 갯수
	 * @exception
	 */
    public int selectTmPlatxmListTotCnt(TmPlatxmDefaultVO searchVO) {
		return PP0107TmPlatxmDAO.selectTmPlatxmListTotCnt(searchVO);
	}
    
    /**
	 * tm_platxm left 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 목록
	 * @exception Exception
	 */
    public List<?> selectTmPlatxmLeftList(TmPlatxmDefaultVO searchVO) throws Exception {
        return PP0107TmPlatxmDAO.selectTmPlatxmLeftList(searchVO);
    }

    /**
	 * tm_platxm left 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 총 갯수
	 * @exception
	 */
    public int selectTmPlatxmLeftListTotCnt(TmPlatxmDefaultVO searchVO) {
		return PP0107TmPlatxmDAO.selectTmPlatxmLeftListTotCnt(searchVO);
	}
    
}
