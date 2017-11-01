package nds.tmm.common.TMCOBD30.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOBD30.vo.TMCOBD30VO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : TMCOBD30DAO.java
 * @Description : TMCOBD30 DAO Class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOBD30DAO")
public class TMCOBD30DAO extends TMMPPBaseDAO {

	/**
	 * tm_faqxxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD30VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOBD30(TMCOBD30VO vo) throws Exception {
        return (String)insert("TMCOBD30DAO.insertTMCOBD30", vo);
    }
    
    /**
	 * tm_faqxxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOBD30VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOBD30(TMCOBD30VO vo) throws Exception {
        update("TMCOBD30DAO.updateTMCOBD30", vo);
    }
    
    /**
     * tm_faqxxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD30VO
     * @return void형
     * @exception Exception
     */
    public void updateReadCntTMCOBD30(TMCOBD30VO vo) throws Exception {
    	update("TMCOBD30DAO.updateReadCntTMCOBD30", vo);
    }

    /**
	 * tm_faqxxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOBD30VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOBD30(TMCOBD30VO vo) throws Exception {
        delete("TMCOBD30DAO.deleteTMCOBD30", vo);
    }
    
    /**
	 * tm_faqxxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOBD30VO
	 * @return 조회한 tm_faqxxm
	 * @exception Exception
	 */
    public TMCOBD30VO selectTMCOBD30(TMCOBD30VO vo) throws Exception {
        return (TMCOBD30VO) select("TMCOBD30DAO.selectTMCOBD30_S", vo);
    }
    
    /**
	 * tm_bdredh을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD30VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertBdredhTMCOBD30(TMCOBD30VO vo) throws Exception {
        return (String)insert("TMCOBD30DAO.insertBdredhTMCOBD30", vo);
    }
    
    /**
	 * tm_bdredh을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOBD30VO
	 * @return 조회한 tm_bdredh
	 * @exception Exception
	 */
    public TMCOBD30VO selectBdredhTMCOBD30(TMCOBD30VO vo) throws Exception {
        return (TMCOBD30VO) select("TMCOBD30DAO.selectBdredhTMCOBD30_S", vo);
    }

    /**
	 * tm_faqxxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_faqxxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOBD30List(TMCOBD30VO searchVO) throws Exception {
        return list("TMCOBD30DAO.selectTMCOBD30List_D", searchVO);
    }

    /**
	 * tm_faqxxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_faqxxm 총 갯수
	 * @exception
	 */
    public int selectTMCOBD30ListTotCnt(TMCOBD30VO searchVO) {
        return (Integer)select("TMCOBD30DAO.selectTMCOBD30ListTotCnt_S", searchVO);
    }

}
