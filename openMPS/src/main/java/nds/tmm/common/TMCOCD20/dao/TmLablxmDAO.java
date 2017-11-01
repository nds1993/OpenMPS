package nds.tmm.common.TMCOCD20.dao;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOCD20.vo.TmLablxmDefaultVO;
import nds.tmm.common.TMCOCD20.vo.TmLablxmVO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : TmLablxmDAO.java
 * @Description : TmLablxm DAO Class
 * @Modification Information
 *
 * @author OpenMPS Team
 * @since 2017. 7. 18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("tmLablxmDAO")
public class TmLablxmDAO extends TMMPPBaseDAO {

	/**
	 * tm_lablxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TmLablxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTmLablxm(TmLablxmVO vo) throws Exception {
        return (String)insert("tmLablxmDAO.insertTmLablxm_S", vo);
    }

    /**
	 * tm_lablxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TmLablxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTmLablxm(TmLablxmVO vo) throws Exception {
        update("tmLablxmDAO.updateTmLablxm_S", vo);
    }

    /**
	 * tm_lablxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TmLablxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTmLablxm(TmLablxmVO vo) throws Exception {
        delete("tmLablxmDAO.deleteTmLablxm_S", vo);
    }

    /**
	 * tm_lablxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_lablxm 목록
	 * @exception Exception
	 */
    public List<?> selectTmLablxmList(TmLablxmDefaultVO searchVO) throws Exception {
        return list("tmLablxmDAO.selectTmLablxmList_D", searchVO);
    }

    /**
	 * tm_lablxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_lablxm 총 갯수
	 * @exception
	 */
    public int selectTmLablxmListTotCnt(TmLablxmDefaultVO searchVO) {
        return (Integer)select("tmLablxmDAO.selectTmLablxmListTotCnt_S", searchVO);
    }

}
