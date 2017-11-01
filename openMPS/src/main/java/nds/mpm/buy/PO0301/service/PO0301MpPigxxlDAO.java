package nds.mpm.buy.PO0301.service;

import java.util.List;

import nds.mpm.buy.PO0301.vo.MpPigxxlDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigxxlVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPigxxlDAO.java
 * @Description : MpPigxxl DAO Class
 * @Modification Information
 *
 * @author ㅌ
 * @since ㅌ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PO0301mpPigxxlDAO")
public class PO0301MpPigxxlDAO extends EgovAbstractDAO {

	/**
	 * mp_pigxxl을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigxxlVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPigxxl(EgovMap vo) throws Exception {
        return (String)insert("PO0301mpPigxxlDAO.insertMpPigxxl_S", vo);
    }

    /**
	 * mp_pigxxl을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigxxlVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpPigxxl(EgovMap vo) throws Exception {
        return update("PO0301mpPigxxlDAO.updateMpPigxxl_S", vo);
    }

    /**
	 * mp_pigxxl을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigxxlVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpPigxxl(EgovMap vo) throws Exception {
        return delete("PO0301mpPigxxlDAO.deleteMpPigxxl_S", vo);
    }

    /**
	 * mp_pigxxl을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigxxlVO
	 * @return 조회한 mp_pigxxl
	 * @exception Exception
	 */
    public MpPigxxlVO selectMpPigxxl(MpPigxxlVO vo) throws Exception {
        return (MpPigxxlVO) select("PO0301mpPigxxlDAO.selectMpPigxxl_S", vo);
    }

    /**
	 * mp_pigxxl 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigxxl 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigxxlList(MpPigxxlDefaultVO searchVO) throws Exception {
        return list("PO0301mpPigxxlDAO.selectMpPigxxlList_D", searchVO);
    }

    /**
	 * mp_pigxxl 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigxxl 총 갯수
	 * @exception
	 */
    public int selectMpPigxxlListTotCnt(MpPigxxlDefaultVO searchVO) {
        return (Integer)select("PO0301mpPigxxlDAO.selectMpPigxxlListTotCnt_S", searchVO);
    }

}
