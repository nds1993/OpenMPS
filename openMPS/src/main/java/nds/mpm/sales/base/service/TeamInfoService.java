package nds.mpm.sales.base.service;

import java.util.List;
import nds.mpm.sales.base.vo.TeamInfoDefaultVO;
import nds.mpm.sales.base.vo.TeamInfoVO;

/**
 * @Class Name : TeamInfoService.java
 * @Description : TeamInfo Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TeamInfoService {
	
	/**
	 * team_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TeamInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertTeamInfo(TeamInfoVO vo) throws Exception;
    
    /**
	 * team_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TeamInfoVO
	 * @return void형
	 * @exception Exception
	 */
    void updateTeamInfo(TeamInfoVO vo) throws Exception;
    
    /**
	 * team_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TeamInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTeamInfo(TeamInfoVO vo) throws Exception;
    
    /**
	 * team_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TeamInfoVO
	 * @return 조회한 team_info
	 * @exception Exception
	 */
    TeamInfoVO selectTeamInfo(TeamInfoVO vo) throws Exception;
    
    /**
	 * team_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return team_info 목록
	 * @exception Exception
	 */
    List selectTeamInfoList(TeamInfoDefaultVO searchVO) throws Exception;
    
    /**
	 * team_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return team_info 총 갯수
	 * @exception
	 */
    int selectTeamInfoListTotCnt(TeamInfoDefaultVO searchVO);
    
}
