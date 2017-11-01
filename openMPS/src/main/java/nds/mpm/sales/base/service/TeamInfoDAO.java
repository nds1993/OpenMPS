package nds.mpm.sales.base.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.sales.base.vo.TeamInfoVO;
import nds.mpm.sales.base.vo.TeamInfoDefaultVO;

/**
 * @Class Name : TeamInfoDAO.java
 * @Description : TeamInfo DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("teamInfoDAO")
public class TeamInfoDAO extends EgovAbstractDAO {

	/**
	 * team_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TeamInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTeamInfo(TeamInfoVO vo) throws Exception {
        return (String)insert("teamInfoDAO.insertTeamInfo_S", vo);
    }

    /**
	 * team_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TeamInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTeamInfo(TeamInfoVO vo) throws Exception {
        update("teamInfoDAO.updateTeamInfo_S", vo);
    }

    /**
	 * team_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TeamInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTeamInfo(TeamInfoVO vo) throws Exception {
        delete("teamInfoDAO.deleteTeamInfo_S", vo);
    }

    /**
	 * team_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TeamInfoVO
	 * @return 조회한 team_info
	 * @exception Exception
	 */
    public TeamInfoVO selectTeamInfo(TeamInfoVO vo) throws Exception {
        return (TeamInfoVO) select("teamInfoDAO.selectTeamInfo_S", vo);
    }

    /**
	 * team_info 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return team_info 목록
	 * @exception Exception
	 */
    public List<?> selectTeamInfoList(TeamInfoDefaultVO searchVO) throws Exception {
        return list("teamInfoDAO.selectTeamInfoList_D", searchVO);
    }

    /**
	 * team_info 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return team_info 총 갯수
	 * @exception
	 */
    public int selectTeamInfoListTotCnt(TeamInfoDefaultVO searchVO) {
        return (Integer)select("teamInfoDAO.selectTeamInfoListTotCnt_S", searchVO);
    }

}
