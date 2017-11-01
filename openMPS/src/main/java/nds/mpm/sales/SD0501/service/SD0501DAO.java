package nds.mpm.sales.SD0501.service;

import java.util.List;

import nds.mpm.sales.SD0501.vo.MpCustReceiptDefaultVO;
import nds.mpm.sales.SD0501.vo.SD0501DefaultVO;
import nds.mpm.sales.SD0501.vo.SD0501VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : SD0501DAO.java
 * @Description : SD0501 DAO Class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("SD0501DAO")
public class SD0501DAO extends EgovAbstractDAO {

	/**
	 * mp_rece_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SD0501VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertSD0501(EgovMap vo) throws Exception {
        return (String)insert("SD0501DAO.insertSD0501", vo);
    }

    /**
	 * mp_rece_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SD0501VO
	 * @return void형
	 * @exception Exception
	 */
    public int updateSD0501(EgovMap vo) throws Exception {
       return update("SD0501DAO.updateSD0501", vo);
    }

    /**
	 * mp_rece_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SD0501VO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteSD0501(EgovMap vo) throws Exception {
    	return delete("SD0501DAO.deleteSD0501", vo);
    }

    /**
	 * mp_rece_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SD0501VO
	 * @return 조회한 mp_rece_info
	 * @exception Exception
	 */
    public SD0501VO selectSD0501(SD0501VO vo) throws Exception {
        return (SD0501VO) select("SD0501DAO.selectSD0501_S", vo);
    }

    /**
	 * mp_rece_info 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_rece_info 목록
	 * @exception Exception
	 */
    public List<?> selectSD0501List(SD0501DefaultVO searchVO) throws Exception {
        return list("SD0501DAO.selectSD0501List_D", searchVO);
    }

    /**
	 * mp_rece_info 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_rece_info 총 갯수
	 * @exception
	 */
    public int selectSD0501ListTotCnt(SD0501DefaultVO searchVO) {
        return (Integer)select("SD0501DAO.selectSD0501ListTotCnt_S", searchVO);
    }

    public int checkReceClosing(EgovMap searchVO) {
        return (Integer)select("SD0501DAO.checkReceClosing", searchVO);
    }
}
