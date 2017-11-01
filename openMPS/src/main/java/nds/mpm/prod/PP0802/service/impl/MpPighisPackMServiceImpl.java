package nds.mpm.prod.PP0802.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0802.service.MpPighisPackMDAO;
import nds.mpm.prod.PP0802.service.MpPighisPackMService;
import nds.mpm.prod.PP0802.vo.MpPighisPackMDefaultVO;
import nds.mpm.prod.PP0802.vo.MpPighisPackMVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPighisPackMServiceImpl.java
 * @Description : MpPighisPackM Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPighisPackMService")
public class MpPighisPackMServiceImpl extends EgovAbstractServiceImpl implements
        MpPighisPackMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPighisPackMServiceImpl.class);

    @Resource(name="mpPighisPackMDAO")
    private MpPighisPackMDAO mpPighisPackMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPighisPackMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_pighis_pack_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPighisPackMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPighisPackM(List<EgovMap> vos) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	if(vos == null)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	if(vos.size() == 0)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}

    	String newKey = null;
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			newKey = mpPighisPackMDAO.insertMpPighisPackM(reqVo);
    			if(newKey == null)
    			{
    				throw new Exception("insert fall!!");
    			}
    		}
    	}
    	return result;
    }

    /**
	 * mp_pighis_pack_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPighisPackMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPighisPackM(MpPighisPackMVO vo) throws Exception {
        //mpPighisPackMDAO.updateMpPighisPackM(vo);
    }

    /**
	 * mp_pighis_pack_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPighisPackMVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpPighisPackM(MpPighisPackMVO vo) throws Exception {
        return mpPighisPackMDAO.deleteMpPighisPackM(vo);
    }

    /**
	 * mp_pighis_pack_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPighisPackMVO
	 * @return 조회한 mp_pighis_pack_m
	 * @exception Exception
	 */
    public MpPighisPackMVO selectMpPighisPackM(MpPighisPackMVO vo) throws Exception {
        MpPighisPackMVO resultVO = mpPighisPackMDAO.selectMpPighisPackM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * 신고결과조회 mp_pig_his_pack_m
	 * @exception
	 */
    public List<?> selectMpPighisPackMList(MpPighisPackMDefaultVO searchVO) throws Exception {
        return mpPighisPackMDAO.selectMpPighisPackMList(searchVO);
    }
    public int selectMpPighisPackMListTotCnt(MpPighisPackMDefaultVO searchVO) {
		return mpPighisPackMDAO.selectMpPighisPackMListTotCnt(searchVO);
	}
    
    public List<EgovMap> selectMpPighisPackMSendFormatList(MpPighisPackMVO searchVO) throws Exception {
        return mpPighisPackMDAO.selectMpPighisPackMSendFormatList(searchVO);

    }
    
    public int updateApiTimeMpPighisPackM(EgovMap vo) throws Exception {
    	return mpPighisPackMDAO.updateApiTimeMpPighisPackM(vo);
    }
    /**
	 * 신고대상 조회 mp_bar_pro_m
	 * @exception
	 */
    public List<?> selectMpBarProMList(MpPighisPackMDefaultVO searchVO) throws Exception {
        return mpPighisPackMDAO.selectMpBarProMList(searchVO);
    }
    public int selectMpBarProMListTotCnt(MpPighisPackMDefaultVO searchVO) {
		return mpPighisPackMDAO.selectMpBarProMListTotCnt(searchVO);
	}
    public int checkDupPackDateMpPighisPackM(MpPighisPackMDefaultVO searchVO) {
		return mpPighisPackMDAO.checkDupPackDateMpPighisPackM(searchVO);
	}
    
}
