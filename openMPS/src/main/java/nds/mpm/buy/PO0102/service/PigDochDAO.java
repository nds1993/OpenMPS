package nds.mpm.buy.PO0102.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.buy.PO0102.vo.PigDochVO;
import nds.mpm.buy.PO0102.vo.PigDochDefaultVO;

/**
 * @Class Name : PigDochDAO.java
 * @Description : PigDoch DAO Class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("pigDochDAO")
public class PigDochDAO extends EgovAbstractDAO {

	/**
	 * pig_doch을 등록한다.
	 * @param vo - 등록할 정보가 담긴 PigDochVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertPigDoch(PigDochVO vo) throws Exception {
        return (String)insert("pigDochDAO.insertPigDoch_S", vo);
    }

    /**
	 * pig_doch을 수정한다.
	 * @param vo - 수정할 정보가 담긴 PigDochVO
	 * @return void형
	 * @exception Exception
	 */
    public void updatePigDoch(PigDochVO vo) throws Exception {
        update("pigDochDAO.updatePigDoch_S", vo);
    }

    /**
	 * pig_doch을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 PigDochVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deletePigDoch(PigDochVO vo) throws Exception {
        delete("pigDochDAO.deletePigDoch_S", vo);
    }

    /**
	 * pig_doch을 조회한다.
	 * @param vo - 조회할 정보가 담긴 PigDochVO
	 * @return 조회한 pig_doch
	 * @exception Exception
	 */
    public PigDochVO selectPigDoch(PigDochVO vo) throws Exception {
        return (PigDochVO) select("pigDochDAO.selectPigDoch_S", vo);
    }

    /**
	 * pig_doch 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return pig_doch 목록
	 * @exception Exception
	 */
    public List<?> selectPigDochList(PigDochDefaultVO searchVO) throws Exception {
        return list("pigDochDAO.selectPigDochList_D", searchVO);
    }

    /**
	 * pig_doch 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return pig_doch 총 갯수
	 * @exception
	 */
    public int selectPigDochListTotCnt(PigDochDefaultVO searchVO) {
        return (Integer)select("pigDochDAO.selectPigDochListTotCnt_S", searchVO);
    }
    
    /**
     * 생돈구매입력 head
	 * 도축일자/농장/차량구분/돈피구분 doch_id sum
	 * @exception
	 */
    public List<?> selectPigDochIdSum(PigDochDefaultVO searchVO) throws Exception {
        return list("pigDochDAO.selectPigDochIdSum", searchVO);
    }

}
