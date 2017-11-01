package nds.mpm.sales.SD0802.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0802.service.MpDelvLimitService;
import nds.mpm.sales.SD0802.vo.MpDelvLimitDefaultVO;
import nds.mpm.sales.SD0802.vo.MpDelvLimitVO;
import nds.mpm.sales.SD0802.service.MpDelvLimitDAO;

/**
 * @Class Name : MpDelvLimitServiceImpl.java
 * @Description : MpDelvLimit Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpDelvLimitService")
public class MpDelvLimitServiceImpl extends EgovAbstractServiceImpl implements
        MpDelvLimitService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpDelvLimitServiceImpl.class);

    @Resource(name="mpDelvLimitDAO")
    private MpDelvLimitDAO mpDelvLimitDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpDelvLimitIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_delv_limit을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDelvLimitVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpDelvLimit(List<EgovMap> vos) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	for(EgovMap reqVo : vos)
    	{
    		
    		if("C".equals((String)reqVo.get("dsType")) && mpDelvLimitDAO.selectMpDelvLimit(reqVo) != null)
    		{
    			return new ResultEx(  Consts.ResultCode.RC_DUPLICATE.getCode() );
    		}
    	}
    	
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
			{
    			mpDelvLimitDAO.insertMpDelvLimit(reqVo);
			}
    		else if("U".equals((String)reqVo.get("dsType")))
			{
        		mpDelvLimitDAO.updateMpDelvLimit(reqVo);
			}
    		else if("D".equals((String)reqVo.get("dsType")))
			{
    			mpDelvLimitDAO.deleteMpDelvLimit(reqVo);
			}
    	}
    	
    	result.setExtraData(vos);
        return result;		
    }

    /**
	 * mp_delv_limit을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDelvLimitVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpDelvLimit(MpDelvLimitVO vo) throws Exception {
        //mpDelvLimitDAO.updateMpDelvLimit(vo);
    }

    /**
	 * mp_delv_limit을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDelvLimitVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpDelvLimit(MpDelvLimitVO vo) throws Exception {
        //mpDelvLimitDAO.deleteMpDelvLimit(vo);
    }

    /**
	 * mp_delv_limit을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDelvLimitVO
	 * @return 조회한 mp_delv_limit
	 * @exception Exception
	 */
    public MpDelvLimitVO selectMpDelvLimit(MpDelvLimitVO vo) throws Exception {
        //MpDelvLimitVO resultVO = mpDelvLimitDAO.selectMpDelvLimit(vo);
        //if (resultVO == null)
        //    throw processException("info.nodata.msg");
        return null;
    }

    /**
	 * mp_delv_limit 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_delv_limit 목록
	 * @exception Exception
	 */
    public List<?> selectMpDelvLimitList(MpDelvLimitDefaultVO searchVO) throws Exception {
        return mpDelvLimitDAO.selectMpDelvLimitList(searchVO);
    }

    /**
	 * mp_delv_limit 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_delv_limit 총 갯수
	 * @exception
	 */
    public int selectMpDelvLimitListTotCnt(MpDelvLimitDefaultVO searchVO) {
		return mpDelvLimitDAO.selectMpDelvLimitListTotCnt(searchVO);
	}
    
}
