package nds.tmm.common.TMCOMT60.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOMT60.dao.UdsMsgDAO;
import nds.tmm.common.TMCOMT60.service.UdsMsgService;
import nds.tmm.common.TMCOMT60.vo.UdsMsgDefaultVO;
import nds.tmm.common.TMCOMT60.vo.UdsMsgVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : UdsMsgServiceImpl.java
 * @Description : UdsMsg Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.08.14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("udsMsgService")
public class UdsMsgServiceImpl extends TMMPPBaseService implements
        UdsMsgService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(UdsMsgServiceImpl.class);

    @Resource(name="udsMsgDAO")
    private UdsMsgDAO udsMsgDAO;
    
    /** ID Generation */
    //@Resource(name="{egovIfLogtxmIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

    /**
	 * Uds_Msg을 등록한다.
	 * @param vo - 등록할 정보가 담긴 UdsMsgVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertUdsMsg(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")) && selectUdsMsg(reqVo) != null)
    		{
    			return new ResultEx( Consts.ResultCode.RC_DUPLICATE );
    		}
    		
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			udsMsgDAO.insertUdsMsg(reqVo);
    		}
    		
    	}
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	
        return result;
    }

    /**
	 * uds_msg을 조회한다.
	 * @param vo - 조회할 정보가 담긴 UdsMsgVO
	 * @return 조회한 uds_msg
	 * @exception Exception
	 */
    public UdsMsgVO selectUdsMsg(EgovMap vo) throws Exception {
    	UdsMsgVO resultVO = udsMsgDAO.selectUdsMsg(vo);
        return resultVO;
    }
    
    /**
	 * uds_msg 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return uds_msg 목록
	 * @exception Exception
	 */
    public List<?> selectUdsMsgList(UdsMsgDefaultVO searchVO) throws Exception {
    		return udsMsgDAO.selectUdsMsgList(searchVO);
    }

    /**
	 * uds_msg 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return uds_msg 총 갯수
	 * @exception
	 */
    public int selectUdsMsgListTotCnt(UdsMsgDefaultVO searchVO) {
    		return udsMsgDAO.selectUdsMsgListTotCnt(searchVO);
	}
    
}
