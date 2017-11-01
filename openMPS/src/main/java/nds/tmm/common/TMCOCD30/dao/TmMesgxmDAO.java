package nds.tmm.common.TMCOCD30.dao;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOCD30.vo.TmMesgxmDefaultVO;
import nds.tmm.common.TMCOCD30.vo.TmMesgxmVO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : TmMesgxmDAO.java
 * @Description : TmMesgxm DAO Class
 * @Modification Information
 *
 * @author OpenMPS Team
 * @since 2017. 7. 18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("tmMesgxmDAO")
public class TmMesgxmDAO extends TMMPPBaseDAO {

	/**
	 * tm_mesgxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TmMesgxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTmMesgxm(TmMesgxmVO vo) throws Exception {
        return (String)insert("tmMesgxmDAO.insertTmMesgxm_S", vo);
    }

    /**
	 * tm_mesgxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TmMesgxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTmMesgxm(TmMesgxmVO vo) throws Exception {
        update("tmMesgxmDAO.updateTmMesgxm_S", vo);
    }

    /**
	 * tm_mesgxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TmMesgxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTmMesgxm(TmMesgxmVO vo) throws Exception {
        delete("tmMesgxmDAO.deleteTmMesgxm_S", vo);
    }

    /**
	 * tm_mesgxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TmMesgxmVO
	 * @return 조회한 tm_mesgxm
	 * @exception Exception
	 */
    public TmMesgxmVO selectTmMesgxm(TmMesgxmVO vo) throws Exception {
        return (TmMesgxmVO) select("tmMesgxmDAO.selectTmMesgxm_S", vo);
    }

    /**
	 * tm_mesgxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_mesgxm 목록
	 * @exception Exception
	 */
    public List<?> selectTmMesgxmList(TmMesgxmDefaultVO searchVO) throws Exception {
        return list("tmMesgxmDAO.selectTmMesgxmList_D", searchVO);
    }

    /**
	 * tm_mesgxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_mesgxm 총 갯수
	 * @exception
	 */
    public int selectTmMesgxmListTotCnt(TmMesgxmDefaultVO searchVO) {
        Object cnt = select("tmMesgxmDAO.selectTmMesgxmListTotCnt_S", searchVO);
        if( cnt == null )
        {
        	return 0;
        }
        return (Integer)cnt;
    }
    
    public List<?> selectTmMesgxmListByKeyword(TmMesgxmDefaultVO searchVO) throws Exception {
        return list("tmMesgxmDAO.selectTmMesgxmKeyword", searchVO);
    }
    public int selectTmMesgxmListByKeywordCnt(TmMesgxmDefaultVO searchVO) {
    	Object cnt = select("tmMesgxmDAO.selectTmMesgxmKeywordCnt", searchVO);
        if( cnt == null )
        {
        	return 0;
        }
        return (Integer)cnt;
    }
}
