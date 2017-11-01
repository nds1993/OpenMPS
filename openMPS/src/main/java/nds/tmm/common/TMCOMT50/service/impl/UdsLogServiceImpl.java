package nds.tmm.common.TMCOMT50.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.tmm.common.TMCOMT50.dao.UdsLogDAO;
import nds.tmm.common.TMCOMT50.service.UdsLogService;
import nds.tmm.common.TMCOMT50.vo.UdsLogDefaultVO;
import nds.tmm.common.TMCOMT50.vo.UdsLogVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : UdsLogServiceImpl.java
 * @Description : UdsLog Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.08.18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("udsLogService")
public class UdsLogServiceImpl extends TMMPPBaseService implements
        UdsLogService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(UdsLogServiceImpl.class);

    @Resource(name="udsLogDAO")
    private UdsLogDAO udsLogDAO;
    
    /** ID Generation */
    //@Resource(name="{egovUdsLogidGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

    /**
	 * uds_log을 조회한다.
	 * @param vo - 조회할 정보가 담긴 UdsLogVO
	 * @return 조회한 uds_log
	 * @exception Exception
	 */
    public UdsLogVO selectUdsLog(UdsLogVO vo) throws Exception {
        UdsLogVO resultVO = udsLogDAO.selectUdsLog(vo);
        return resultVO;
    }

    /**
	 * uds_log 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return uds_log 목록
	 * @exception Exception
	 */
    public List<?> selectUdsLogList(UdsLogDefaultVO searchVO) throws Exception {
		return udsLogDAO.selectUdsLogList(searchVO);
    }

    /**
	 * uds_log 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return uds_log 총 갯수
	 * @exception
	 */
    public int selectUdsLogListTotCnt(UdsLogDefaultVO searchVO) {
		return udsLogDAO.selectUdsLogListTotCnt(searchVO);
	}
    
}
