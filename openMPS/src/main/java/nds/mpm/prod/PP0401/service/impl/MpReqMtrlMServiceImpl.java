package nds.mpm.prod.PP0401.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0401.service.MpReqMtrlMDAO;
import nds.mpm.prod.PP0401.service.MpReqMtrlMService;
import nds.mpm.prod.PP0401.vo.MpReqMtrlMDefaultVO;
import nds.mpm.prod.PP0401.vo.MpReqMtrlMVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpReqMtrlMServiceImpl.java
 * @Description : MpReqMtrlM Business Implement class
 * @Modification Information
 *
 * @author 자재 소요량 산출
 * @since 자재 소요량 산출
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpReqMtrlMService")
public class MpReqMtrlMServiceImpl extends EgovAbstractServiceImpl implements
        MpReqMtrlMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpReqMtrlMServiceImpl.class);

    @Resource(name="mpReqMtrlMDAO")
    private MpReqMtrlMDAO mpReqMtrlMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpReqMtrlMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_req_mtrl_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpReqMtrlMVO
	 * @return 등록 결과
	 * @exception Exception
	 * 
	 * isDelete = true 해당일 데이터 삭제후 저장.
	 */
    public ResultEx insertMpReqMtrlM(MpReqMtrlMVO searchVO, List<EgovMap> vos, boolean isDelete) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	if(isDelete)
    	{
    		mpReqMtrlMDAO.deleteCalcWorkDateMpReqMtrlM(searchVO);
    	}
    	
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			mpReqMtrlMDAO.insertMpReqMtrlM(reqVo);
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			mpReqMtrlMDAO.updateMpReqMtrlM(reqVo);
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			mpReqMtrlMDAO.deleteMpReqMtrlM(reqVo);
    		}
    		
    	}

    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	
        return result;
    }

    /**
	 * mp_req_mtrl_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpReqMtrlMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpReqMtrlM(MpReqMtrlMVO vo) throws Exception {
        //mpReqMtrlMDAO.updateMpReqMtrlM(vo);
    }

    /**
	 * mp_req_mtrl_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpReqMtrlMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpReqMtrlM(MpReqMtrlMVO vo) throws Exception {
        //mpReqMtrlMDAO.deleteMpReqMtrlM(vo);
    }

    /**
	 * mp_req_mtrl_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpReqMtrlMVO
	 * @return 조회한 mp_req_mtrl_m
	 * @exception Exception
	 */
    public MpReqMtrlMVO selectMpReqMtrlM(MpReqMtrlMVO vo) throws Exception {
        MpReqMtrlMVO resultVO = mpReqMtrlMDAO.selectMpReqMtrlM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_req_mtrl_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_mtrl_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpReqMtrlMList(MpReqMtrlMDefaultVO searchVO) throws Exception {
        return mpReqMtrlMDAO.selectMpReqMtrlMList(searchVO);
    }

    /**
	 * mp_req_mtrl_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_mtrl_m 총 갯수
	 * @exception
	 */
    public int selectMpReqMtrlMListTotCnt(MpReqMtrlMDefaultVO searchVO) {
		return mpReqMtrlMDAO.selectMpReqMtrlMListTotCnt(searchVO);
	}
    
    public int checkMpReqMtrlMCnt(MpReqMtrlMDefaultVO searchVO) {
    	return mpReqMtrlMDAO.checkMpReqMtrlMCnt(searchVO);
    }
    
    public int deleteCalcWorkDateMpReqMtrlM(MpReqMtrlMDefaultVO searchVO) {
		return mpReqMtrlMDAO.deleteCalcWorkDateMpReqMtrlM(searchVO);
    }
    
    public List<EgovMap> selectCalculateMpReqMtrlMList(MpReqMtrlMDefaultVO searchVO) throws Exception {
		return mpReqMtrlMDAO.selectCalculateMpReqMtrlMList(searchVO);
    }
}
