package nds.tmm.common.TMCOMT40.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.tmm.common.TMCOCD30.vo.TmMesgxmDefaultVO;
import nds.tmm.common.TMCOCD30.vo.TmMesgxmVO;
import nds.tmm.common.TMCOMT40.dao.IfLogtxmDAO;
import nds.tmm.common.TMCOMT40.service.IfLogtxmService;
import nds.tmm.common.TMCOMT40.vo.IfLogtxmDefaultVO;
import nds.tmm.common.TMCOMT40.vo.IfLogtxmVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : IfLogtxmServiceImpl.java
 * @Description : IfLogtxm Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.08.14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("ifLogtxmService")
public class IfLogtxmServiceImpl extends TMMPPBaseService implements
        IfLogtxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(IfLogtxmServiceImpl.class);

    @Resource(name="ifLogtxmDAO")
    private IfLogtxmDAO ifLogtxmDAO;
    
    /** ID Generation */
    //@Resource(name="{egovIfLogtxmIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

    /**
	 * if_logtxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 IfLogtxmVO
	 * @return 조회한 if_logtxm
	 * @exception Exception
	 */
    public IfLogtxmVO selectIfLogtxm(IfLogtxmVO vo) throws Exception {
        IfLogtxmVO resultVO = ifLogtxmDAO.selectIfLogtxm(vo);
        return resultVO;
    }

    /**
	 * if_logtxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return if_logtxm 목록
	 * @exception Exception
	 */
    public List<?> selectIfLogtxmList(IfLogtxmDefaultVO searchVO) throws Exception {
    		return ifLogtxmDAO.selectIfLogtxmList(searchVO);
    }

    /**
	 * if_logtxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return if_logtxm 총 갯수
	 * @exception
	 */
    public int selectIfLogtxmListTotCnt(IfLogtxmDefaultVO searchVO) {
    		return ifLogtxmDAO.selectIfLogtxmListTotCnt(searchVO);
	}
    
}
