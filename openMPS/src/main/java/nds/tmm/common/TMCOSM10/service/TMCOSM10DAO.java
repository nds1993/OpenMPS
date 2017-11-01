package nds.tmm.common.TMCOSM10.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOSM10.vo.TMCOSM10VO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : TMCOSM10DAO.java
 * @Description : TMCOSM10 DAO Class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOSM10DAO")
public class TMCOSM10DAO extends TMMPPBaseDAO {

	/**
	 * tm_svrqxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOSM10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOSM10(TMCOSM10VO vo) throws Exception {
        return (String)insert("TMCOSM10DAO.insertTMCOSM10", vo);
    }
    
    /**
     * tm_svpcxm을 등록한다.
     * @param vo - 등록할 정보가 담긴 TMCOSM10VO
     * @return 등록 결과
     * @exception Exception
     */
    public String insertTmSvpcmx(TMCOSM10VO vo) throws Exception {
    	return (String)insert("TMCOSM10DAO.insertTmSvpcmx", vo);
    }

    /**
	 * tm_svrqxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOSM10VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOSM10(TMCOSM10VO vo) throws Exception {
        update("TMCOSM10DAO.updateTMCOSM10", vo);
    }

    /**
	 * tm_svrqxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOSM10VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOSM10(TMCOSM10VO vo) throws Exception {
        delete("TMCOSM10DAO.deleteTMCOSM10", vo);
    }
    
    /**
     * tm_svrqxm을 변경한다.
     * @param vo - 변경할 정보가 담긴 TMCOSM10VO
     * @return void형 
     * @exception Exception
     */
    public void updateProcStatusTMCOSM10(TMCOSM10VO vo) throws Exception {
    	delete("TMCOSM10DAO.updateProcStatusTMCOSM10", vo);
    }

    /**
	 * tm_svrqxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOSM10VO
	 * @return 조회한 tm_svrqxm
	 * @exception Exception
	 */
    public TMCOSM10VO selectTMCOSM10(TMCOSM10VO vo) throws Exception {
        return (TMCOSM10VO) select("TMCOSM10DAO.selectTMCOSM10_S", vo);
    }

    /**
	 * tm_svrqxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_svrqxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOSM10List(TMCOSM10VO searchVO) throws Exception {
        return list("TMCOSM10DAO.selectTMCOSM10List_D", searchVO);
    }

    /**
	 * tm_svrqxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_svrqxm 총 갯수
	 * @exception
	 */
    public int selectTMCOSM10ListTotCnt(TMCOSM10VO searchVO) {
        return (Integer)select("TMCOSM10DAO.selectTMCOSM10ListTotCnt_S", searchVO);
    }

}
