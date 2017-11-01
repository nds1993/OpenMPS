package nds.mpm.sales.SD0402.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0402.service.SD0402DAO;
import nds.mpm.sales.SD0402.service.SD0402Service;
import nds.mpm.sales.SD0402.vo.SD0402DefaultVO;
import nds.mpm.sales.SD0402.vo.SD0402VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : SD0402ServiceImpl.java
 * @Description : SD0402 Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD0402Service")
public class SD0402ServiceImpl extends TMMPPBaseService implements
        SD0402Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0402ServiceImpl.class);

    @Resource(name="SD0402DAO")
    private SD0402DAO SD0402DAO;
    
    /** ID Generation */
    //@Resource(name="{egovSD0402IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * SD0402 출고승인요청. 
	 * @param vo - 등록할 정보가 담긴 SD0402VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertSD0402(List<EgovMap> vos) throws Exception {
    	
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
    		SD0402DAO.insertSD0402(reqVo);
    		iCnt++;
    	}
    	return result;
    }

    /**
	 * mp_delv_approval을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SD0402VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateSD0402(SD0402VO vo) throws Exception {
        SD0402DAO.updateSD0402(vo);
    }

    /**
	 * mp_delv_approval을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SD0402VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteSD0402(SD0402VO vo) throws Exception {
        SD0402DAO.deleteSD0402(vo);
    }

    /**
	 * mp_delv_approval을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SD0402VO
	 * @return 조회한 mp_delv_approval
	 * @exception Exception
	 */
    public SD0402VO selectSD0402(SD0402VO vo) throws Exception {
        SD0402VO resultVO = SD0402DAO.selectSD0402(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_delv_approval 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_delv_approval 목록
	 * @exception Exception
	 */
    public List<?> selectSD0402List(SD0402DefaultVO searchVO) throws Exception {
        return SD0402DAO.selectSD0402List(searchVO);
    }

    /**
	 * mp_delv_approval 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_delv_approval 총 갯수
	 * @exception
	 */
    public int selectSD0402ListTotCnt(SD0402DefaultVO searchVO) {
		return SD0402DAO.selectSD0402ListTotCnt(searchVO);
	}
    
    public List<?> selectSD0403List(SD0402DefaultVO searchVO) throws Exception{
        return SD0402DAO.selectSD0403List(searchVO);
    }

    /**
	 * mp_delv_approval 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_delv_approval 총 갯수
	 * @exception
	 */
    public int selectSD0403ListTotCnt(SD0402DefaultVO searchVO){
        return SD0402DAO.selectSD0403ListTotCnt(searchVO);
    }

    /**
	 * 직급별 SD0402 출고승인요청 결재 서비스 
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_delv_approval  총 갯수
	 * @exception
	 */
    public int updateDelvApproConfirm(List<EgovMap> vos) throws Exception{
    	int uCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		SD0402DAO.updateDelvApproConfirm(reqVo);
    		uCnt++;
    	}
    	return uCnt;
    }
    
    public EgovMap selectUserInfo(EgovMap vo) throws Exception {
    	return SD0402DAO.selectUserInfo(vo);
    }
}
