package nds.mpm.sales.SD0101.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0101.service.MpDamboInfoDAO;
import nds.mpm.sales.SD0101.service.MpDamboInfoService;
import nds.mpm.sales.SD0101.vo.MpDamboInfoDefaultVO;
import nds.mpm.sales.SD0101.vo.MpDamboInfoVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDamboInfoServiceImpl.java
 * @Description : MpDamboInfo Business Implement class
 * @Modification Information
 *
 * @author b
 * @since b
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpDamboInfoService")
public class MpDamboInfoServiceImpl extends EgovAbstractServiceImpl implements
        MpDamboInfoService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpDamboInfoServiceImpl.class);

    @Resource(name="mpDamboInfoDAO")
    private MpDamboInfoDAO mpDamboInfoDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpDamboInfoIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_dambo_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDamboInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpDamboInfo(EgovMap vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int newDamCode = mpDamboInfoDAO.insertMpDamboInfo(vo);
    	
    	vo.put("damCode",newDamCode);
    	
    	result.setExtraData(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return result;
    }

    /**
	 * mp_dambo_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDamboInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpDamboInfo(EgovMap vo) throws Exception {
        mpDamboInfoDAO.updateMpDamboInfo(vo);
    }

    /**
	 * mp_dambo_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDamboInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpDamboInfo(MpDamboInfoVO vo) throws Exception {
        //mpDamboInfoDAO.deleteMpDamboInfo(vo);
    }

    /**
	 * mp_dambo_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDamboInfoVO
	 * @return 조회한 mp_dambo_info
	 * @exception Exception
	 */
    public MpDamboInfoVO selectMpDamboInfo(MpDamboInfoVO vo) throws Exception {
        MpDamboInfoVO resultVO = mpDamboInfoDAO.selectMpDamboInfo(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_dambo_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_dambo_info 목록
	 * @exception Exception
	 */
    public List<?> selectMpDamboInfoList(MpDamboInfoDefaultVO searchVO) throws Exception {
        return mpDamboInfoDAO.selectMpDamboInfoList(searchVO);
    }

    /**
	 * mp_dambo_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_dambo_info 총 갯수
	 * @exception
	 */
    public int selectMpDamboInfoListTotCnt(MpDamboInfoDefaultVO searchVO) {
		return mpDamboInfoDAO.selectMpDamboInfoListTotCnt(searchVO);
	}
    
}
