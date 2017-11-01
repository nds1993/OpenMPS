package nds.tmm.common.TMCOBD20.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOBD20.vo.TMCOBD20VO;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOBD20DAO.java
 * @Description : TMCOBD20 DAO Class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("TMCOBD20DAO")
public class TMCOBD20DAO extends TMMPPBaseDAO {

	/**
	 * tm_bdctxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD20VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTMCOBD20(TMCOBD20VO vo) throws Exception {
        return (String)insert("TMCOBD20DAO.insertTMCOBD20", vo);
    }
    
    /**
	 * tm_bdctxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOBD20VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOBD20(TMCOBD20VO vo) throws Exception {
        update("TMCOBD20DAO.updateTMCOBD20", vo);
    }
    
    /**
     * tm_bdctxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void updateReadCntTMCOBD20(TMCOBD20VO vo) throws Exception {
    	update("TMCOBD20DAO.updateReadCntTMCOBD20", vo);
    }
    
    /**
     * tm_bdredh을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void insertBdredhTMCOBD20(TMCOBD20VO vo) throws Exception {
    	update("TMCOBD20DAO.insertBdredhTMCOBD20", vo);
    }
    
    /**
     * tm_bdctxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void updateReplySortOrderTMCOBD20(TMCOBD20VO vo) throws Exception {
    	update("TMCOBD20DAO.updateReplySortOrderTMCOBD20", vo);
    }
    
    /**
	 * tm_bdredh을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOBD20VO
	 * @return 조회한 tm_bdredh
	 * @exception Exception
	 */
    public TMCOBD20VO selectBdredhTMCOBD20(TMCOBD20VO vo) throws Exception {
        return (TMCOBD20VO) select("TMCOBD20DAO.selectBdredhTMCOBD20_S", vo);
    }
    
    /**
	 * tm_bdctxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOBD20VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOBD20(TMCOBD20VO vo) throws Exception {
        delete("TMCOBD20DAO.deleteTMCOBD20", vo);
    }
    
    /**
	 * tm_bdctxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOBD20VO
	 * @return 조회한 tm_bdctxm
	 * @exception Exception
	 */
    public TMCOBD20VO selectTMCOBD20(EgovMap vo) throws Exception {
        return (TMCOBD20VO) select("TMCOBD20DAO.selectTMCOBD20_S", vo);
    }
    
    /**
	 * tm_bdctxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_bdctxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOBD20List(TMCOBD20VO searchVO) throws Exception {
        return list("TMCOBD20DAO.selectTMCOBD20List_D", searchVO);
    }

    /**
	 * tm_bdctxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_bdctxm 총 갯수
	 * @exception
	 */
    public int selectTMCOBD20ListTotCnt(TMCOBD20VO searchVO) {
        return (Integer)select("TMCOBD20DAO.selectTMCOBD20ListTotCnt_S", searchVO);
    }
    
    /**
     * tm_bdcmxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void insertComment(TMCOBD20VO vo) throws Exception {
    	update("TMCOBD20DAO.insertComment", vo);
    }

    /**
     * tm_bdcmxm 목록을 조회한다.
     * @param searchMap - 조회할 정보가 담긴 Map
     * @return tm_bdctxm 목록
     * @exception Exception
     */
    public List<?> selectComment(TMCOBD20VO searchVO) throws Exception {
    	return list("TMCOBD20DAO.selectComment", searchVO);
    }
    
    /**
     * tm_bdcmxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void updateComment(TMCOBD20VO vo) throws Exception {
    	update("TMCOBD20DAO.updateComment", vo);
    }
    
    /**
     * tm_bdcmxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void deleteComment(TMCOBD20VO vo) throws Exception {
    	update("TMCOBD20DAO.deleteComment", vo);
    }
  
    /**
     * tm_atflxm 목록을 조회한다.
     * @param searchMap - 조회할 정보가 담긴 Map
     * @return tm_atflxm 목록
     * @exception Exception
     */
    public String selectNextFileId(TMCOBD20VO searchVO) throws Exception {
    	return (String) select("TMCOBD20DAO.selectNextFileId", searchVO);
    }    
    
    /**
     * tm_atflxm을 등록한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void insertFileMaster(TMCOBD20VO vo) throws Exception {
    	update("TMCOBD20DAO.insertFileMaster", vo);
    }
    
    /**
     * tm_atflxd을 등록한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void insertFileDetail(TMCOBD20VO vo) throws Exception {
    	update("TMCOBD20DAO.insertFileDetail", vo);
    }
    
    /**
     * tm_atflxd을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void deleteFile(TMCOBD20VO vo) throws Exception {
    	update("TMCOBD20DAO.deleteFile", vo);
    }
    
    /**
     * tm_atflxd 목록을 조회한다.
     * @param searchMap - 조회할 정보가 담긴 Map
     * @return tm_atflxd 목록
     * @exception Exception
     */
    public List<?> selectFile(TMCOBD20VO searchVO) throws Exception {
    	return list("TMCOBD20DAO.selectFile", searchVO);
    }    
    
}
