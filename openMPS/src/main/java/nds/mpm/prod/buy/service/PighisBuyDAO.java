package nds.mpm.prod.buy.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.prod.buy.vo.PighisBuyVO;
import nds.mpm.prod.buy.vo.PighisBuyDefaultVO;

/**
 * @Class Name : PighisBuyDAO.java
 * @Description : PighisBuy DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.21
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("pighisBuyDAO")
public class PighisBuyDAO extends EgovAbstractDAO {

	/**
	 * pighis_buy을 등록한다.
	 * @param vo - 등록할 정보가 담긴 PighisBuyVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertPighisBuy(PighisBuyVO vo) throws Exception {
        return (String)insert("pighisBuyDAO.insertPighisBuy_S", vo);
    }

    /**
	 * pighis_buy을 수정한다.
	 * @param vo - 수정할 정보가 담긴 PighisBuyVO
	 * @return void형
	 * @exception Exception
	 */
    public void updatePighisBuy(PighisBuyVO vo) throws Exception {
        update("pighisBuyDAO.updatePighisBuy_S", vo);
    }

    /**
	 * pighis_buy을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 PighisBuyVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deletePighisBuy(PighisBuyVO vo) throws Exception {
        delete("pighisBuyDAO.deletePighisBuy_S", vo);
    }

    /**
	 * pighis_buy을 조회한다.
	 * @param vo - 조회할 정보가 담긴 PighisBuyVO
	 * @return 조회한 pighis_buy
	 * @exception Exception
	 */
    public PighisBuyVO selectPighisBuy(PighisBuyVO vo) throws Exception {
        return (PighisBuyVO) select("pighisBuyDAO.selectPighisBuy_S", vo);
    }

    /**
	 * pighis_buy 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return pighis_buy 목록
	 * @exception Exception
	 */
    public List<?> selectPighisBuyList(PighisBuyDefaultVO searchVO) throws Exception {
        return list("pighisBuyDAO.selectPighisBuyList_D", searchVO);
    }

    /**
	 * pighis_buy 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return pighis_buy 총 갯수
	 * @exception
	 */
    public int selectPighisBuyListTotCnt(PighisBuyDefaultVO searchVO) {
        return (Integer)select("pighisBuyDAO.selectPighisBuyListTotCnt_S", searchVO);
    }

}
