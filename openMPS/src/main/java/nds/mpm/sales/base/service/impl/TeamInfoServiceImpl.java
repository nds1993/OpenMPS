package nds.mpm.sales.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.sales.base.service.TeamInfoService;
import nds.mpm.sales.base.vo.TeamInfoDefaultVO;
import nds.mpm.sales.base.vo.TeamInfoVO;
import nds.mpm.sales.base.service.TeamInfoDAO;

/**
 * @Class Name : TeamInfoServiceImpl.java
 * @Description : TeamInfo Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("teamInfoService")
public class TeamInfoServiceImpl extends EgovAbstractServiceImpl implements
        TeamInfoService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamInfoServiceImpl.class);

    @Resource(name="teamInfoDAO")
    private TeamInfoDAO teamInfoDAO;
    
    /** ID Generation */
    //@Resource(name="{egovTeamInfoIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * team_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TeamInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTeamInfo(TeamInfoVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	teamInfoDAO.insertTeamInfo(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * team_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TeamInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTeamInfo(TeamInfoVO vo) throws Exception {
        teamInfoDAO.updateTeamInfo(vo);
    }

    /**
	 * team_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TeamInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTeamInfo(TeamInfoVO vo) throws Exception {
        teamInfoDAO.deleteTeamInfo(vo);
    }

    /**
	 * team_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TeamInfoVO
	 * @return 조회한 team_info
	 * @exception Exception
	 */
    public TeamInfoVO selectTeamInfo(TeamInfoVO vo) throws Exception {
        TeamInfoVO resultVO = teamInfoDAO.selectTeamInfo(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * team_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return team_info 목록
	 * @exception Exception
	 */
    public List<?> selectTeamInfoList(TeamInfoDefaultVO searchVO) throws Exception {
        return teamInfoDAO.selectTeamInfoList(searchVO);
    }

    /**
	 * team_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return team_info 총 갯수
	 * @exception
	 */
    public int selectTeamInfoListTotCnt(TeamInfoDefaultVO searchVO) {
		return teamInfoDAO.selectTeamInfoListTotCnt(searchVO);
	}
    
}
