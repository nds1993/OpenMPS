package nds.mpm.buy.PO0301.service;

import java.util.List;

import nds.mpm.buy.PO0301.vo.MpPigxxdDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigxxdVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : PO0301MpPigxxdDAO.java
 * @Description : PO0301MpPigxxd DAO Class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PO0301MpPigxxdDAO")
public class PO0301MpPigxxdDAO extends EgovAbstractDAO {

	/**
	 * mp_pigxxd을 등록한다.
	 * @param vo - 등록할 정보가 담긴 PO0301MpPigxxdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPigxxd(EgovMap vo) throws Exception {
        return (String)insert("PO0301MpPigxxdDAO.insertMpPigxxd", vo);
    }

    /**
	 * mp_pigxxd을 수정한다.
	 * @param vo - 수정할 정보가 담긴 PO0301MpPigxxdVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpPigxxd(EgovMap vo) throws Exception {
        return update("PO0301MpPigxxdDAO.updateMpPigxxd", vo);
    }

    /**
	 * mp_pigxxd을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 PO0301MpPigxxdVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpPigxxd(EgovMap vo) throws Exception {
        return delete("PO0301MpPigxxdDAO.deleteMpPigxxd", vo);
    }

    /**
	 * mp_pigxxd을 조회한다.
	 * @param vo - 조회할 정보가 담긴 PO0301MpPigxxdVO
	 * @return 조회한 mp_pigxxd
	 * @exception Exception
	 */
    public MpPigxxdVO selectMpPigxxd(MpPigxxdVO vo) throws Exception {
        return (MpPigxxdVO) select("PO0301MpPigxxdDAO.selectMpPigxxd_S", vo);
    }

    /**
	 * mp_pigxxd 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigxxd 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigxxdList(MpPigxxdDefaultVO searchVO) throws Exception {
        return list("PO0301MpPigxxdDAO.selectMpPigxxdList_D", searchVO);
    }

    /**
	 * mp_pigxxd 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigxxd 총 갯수
	 * @exception
	 */
    public int selectMpPigxxdListTotCnt(MpPigxxdDefaultVO searchVO) {
        return (Integer)select("PO0301MpPigxxdDAO.selectMpPigxxdListTotCnt_S", searchVO);
    }

}
