package nds.mpm.sales.SD0103.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.sales.SD0103.vo.MpCustProDefaultVO;
import nds.mpm.sales.SD0103.vo.MpCustProVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustProDAO.java
 * @Description : MpCustPro DAO Class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpCustProDAO")
public class MpCustProDAO extends TMMPPBaseDAO {

	/**
	 * mp_cust_pro을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustProVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpCustPro(MpCustProVO vo) throws Exception {
        return (String)insert("MpCustProDAO.insertMpCustPro", vo);
    }

    /**
	 * mp_cust_pro을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustProVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpCustPro(MpCustProVO vo) throws Exception {
        update("MpCustProDAO.updateMpCustPro", vo);
    }

    /**
	 * mp_cust_pro을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustProVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpCustPro(MpCustProVO vo) throws Exception {
        delete("MpCustProDAO.deleteMpCustPro", vo);
    }

    /**
	 * mp_cust_pro을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustProVO
	 * @return 조회한 mp_cust_pro
	 * @exception Exception
	 */
    public MpCustProVO selectMpCustPro(EgovMap vo) throws Exception {
        return (MpCustProVO) select("MpCustProDAO.selectMpCustPro_S", vo);
    }

    /**
	 * mp_cust_pro 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_pro 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustProList(MpCustProDefaultVO searchVO) throws Exception {
        return list("MpCustProDAO.selectMpCustProList_D", searchVO);
    }

    /**
	 * mp_cust_pro 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_pro 총 갯수
	 * @exception
	 */
    public int selectMpCustProListTotCnt(MpCustProDefaultVO searchVO) {
        return (Integer)select("MpCustProDAO.selectMpCustProListTotCnt_S", searchVO);
    }

    public int deleteAllMpCustPro(EgovMap vo) throws Exception {
        return delete("MpCustProDAO.deleteAllMpCustPro", vo);
    }
    
}
