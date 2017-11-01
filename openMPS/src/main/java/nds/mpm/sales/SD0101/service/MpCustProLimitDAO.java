package nds.mpm.sales.SD0101.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.sales.SD0101.vo.MpCustProLimitVO;
import nds.mpm.sales.SD0101.vo.MpCustProLimitDefaultVO;

/**
 * @Class Name : MpCustProLimitDAO.java
 * @Description : MpCustProLimit DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpCustProLimitDAO")
public class MpCustProLimitDAO extends EgovAbstractDAO {

	/**
	 * mp_cust_pro_limit을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustProLimitVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpCustProLimit(MpCustProLimitVO vo) throws Exception {
        return (String)insert("mpCustProLimitDAO.insertMpCustProLimit", vo);
    }

    /**
	 * mp_cust_pro_limit을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustProLimitVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpCustProLimit(MpCustProLimitVO vo) throws Exception {
        update("mpCustProLimitDAO.updateMpCustProLimit", vo);
    }

    /**
	 * mp_cust_pro_limit을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustProLimitVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpCustProLimit(MpCustProLimitVO vo) throws Exception {
        delete("mpCustProLimitDAO.deleteMpCustProLimit", vo);
    }

    /**
	 * mp_cust_pro_limit을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustProLimitVO
	 * @return 조회한 mp_cust_pro_limit
	 * @exception Exception
	 */
    public MpCustProLimitVO selectMpCustProLimit(MpCustProLimitVO vo) throws Exception {
        return (MpCustProLimitVO) select("mpCustProLimitDAO.selectMpCustProLimit_S", vo);
    }

    /**
	 * mp_cust_pro_limit 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_pro_limit 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustProLimitList(MpCustProLimitDefaultVO searchVO) throws Exception {
        return list("mpCustProLimitDAO.selectMpCustProLimitList_D", searchVO);
    }

    /**
	 * mp_cust_pro_limit 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_pro_limit 총 갯수
	 * @exception
	 */
    public int selectMpCustProLimitListTotCnt(MpCustProLimitDefaultVO searchVO) {
        return (Integer)select("mpCustProLimitDAO.selectMpCustProLimitListTotCnt_S", searchVO);
    }

}
