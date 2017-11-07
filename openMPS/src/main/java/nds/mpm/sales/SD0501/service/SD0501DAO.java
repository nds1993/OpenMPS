package nds.mpm.sales.SD0501.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.sales.SD0501.vo.SD0501VO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : SD0501DAO.java
 * @Description : SD0501 DAO Class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("SD0501DAO")
public class SD0501DAO extends TMMPPBaseDAO {

	/**
	 * sd_claimrqxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SD0501VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertSD0501(SD0501VO vo) throws Exception {
        return (String)insert("SD0501DAO.insertSD0501", vo);
    }
    
    /**
     * sd_claimpcxm을 등록한다.
     * @param vo - 등록할 정보가 담긴 SD0501VO
     * @return 등록 결과
     * @exception Exception
     */
    public String insertSdClaimpcxm(SD0501VO vo) throws Exception {
    	return (String)insert("SD0501DAO.insertSdClaimpcxm", vo);
    }

    /**
	 * sd_claimrqxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SD0501VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateSD0501(SD0501VO vo) throws Exception {
        update("SD0501DAO.updateSD0501", vo);
    }

    /**
	 * sd_claimrqxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SD0501VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteSD0501(SD0501VO vo) throws Exception {
        delete("SD0501DAO.deleteSD0501", vo);
    }
    
    /**
     * sd_claimrqxm을 변경한다.
     * @param vo - 변경할 정보가 담긴 SD0501VO
     * @return void형 
     * @exception Exception
     */
    public void updateProcStatusSD0501(SD0501VO vo) throws Exception {
    	delete("SD0501DAO.updateProcStatusSD0501", vo);
    }

    /**
	 * sd_claimrqxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SD0501VO
	 * @return 조회한 sd_claimrqxm
	 * @exception Exception
	 */
    public SD0501VO selectSD0501(SD0501VO vo) throws Exception {
        return (SD0501VO) select("SD0501DAO.selectSD0501_S", vo);
    }

    /**
	 * sd_claimrqxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return sd_claimrqxm 목록
	 * @exception Exception
	 */
    public List<?> selectSD0501List(SD0501VO searchVO) throws Exception {
        return list("SD0501DAO.selectSD0501List_D", searchVO);
    }

    /**
	 * sd_claimrqxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return sd_claimrqxm 총 갯수
	 * @exception
	 */
    public int selectSD0501ListTotCnt(SD0501VO searchVO) {
        return (Integer)select("SD0501DAO.selectSD0501ListTotCnt_S", searchVO);
    }

}
