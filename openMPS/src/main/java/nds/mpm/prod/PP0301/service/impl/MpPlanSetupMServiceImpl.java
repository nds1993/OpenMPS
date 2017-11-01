package nds.mpm.prod.PP0301.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0301.service.MpPlanSetupMDAO;
import nds.mpm.prod.PP0301.service.MpPlanSetupMService;
import nds.mpm.prod.PP0301.vo.MpPlanSetupMDefaultVO;
import nds.mpm.prod.PP0301.vo.MpPlanSetupMVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPlanSetupMServiceImpl.java
 * @Description : MpPlanSetupM Business Implement class
 * @Modification Information
 *
 * @author M
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPlanSetupMService")
public class MpPlanSetupMServiceImpl extends TMMPPBaseService implements
        MpPlanSetupMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPlanSetupMServiceImpl.class);

    @Resource(name="mpPlanSetupMDAO")
    private MpPlanSetupMDAO mpPlanSetupMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPlanSetupMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * setup 기본데이터는 공통코드
	 * insert update 를 insert 쿼리로 같이 처리.
	 * mp_plan_setup_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanSetupMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPlanSetupM(List<EgovMap> vos) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	if(vos == null)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	if(vos.size() == 0)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}

    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("0".equals(reqVo.get("rateType")))
    		{
    			mpPlanSetupMDAO.deleteMpPlanSetupM(reqVo);
    		}
    		else
    		{
    			mpPlanSetupMDAO.insertMpPlanSetupM(reqVo);
        		iCnt++;
    		}
    	}
    	return result;
    }

    /**
	 * mp_plan_setup_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanSetupMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanSetupM(MpPlanSetupMVO vo) throws Exception {
        mpPlanSetupMDAO.updateMpPlanSetupM(vo);
    }

    /**
	 * mp_plan_setup_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanSetupMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPlanSetupM(MpPlanSetupMVO vo) throws Exception {
        //mpPlanSetupMDAO.deleteMpPlanSetupM(vo);
    }

    /**
	 * mp_plan_setup_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanSetupMVO
	 * @return 조회한 mp_plan_setup_m
	 * @exception Exception
	 */
    public MpPlanSetupMVO selectMpPlanSetupM(MpPlanSetupMVO vo) throws Exception {
        MpPlanSetupMVO resultVO = mpPlanSetupMDAO.selectMpPlanSetupM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_plan_setup_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_setup_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanSetupMList(MpPlanSetupMDefaultVO searchVO) throws Exception {
        return mpPlanSetupMDAO.selectMpPlanSetupMList(searchVO);
    }

    /**
	 * mp_plan_setup_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_setup_m 총 갯수
	 * @exception
	 */
    public int selectMpPlanSetupMListTotCnt(MpPlanSetupMDefaultVO searchVO) {
		return mpPlanSetupMDAO.selectMpPlanSetupMListTotCnt(searchVO);
	}
    
}
