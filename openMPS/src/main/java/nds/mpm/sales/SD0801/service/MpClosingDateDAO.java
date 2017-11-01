package nds.mpm.sales.SD0801.service;

import java.util.List;

import nds.mpm.sales.SD0801.vo.MpClosingDateDefaultVO;
import nds.mpm.sales.SD0801.vo.MpClosingDateVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpClosingDateDAO.java
 * @Description : MpClosingDate DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpClosingDateDAO")
public class MpClosingDateDAO extends EgovAbstractDAO {

	
	/**
	 * 생산마감 체크 
	 * 현재 시스템 날짜가 prod_close의 날짜보다 같거나 
               작은 경우에만 Transaction을 허용
               
       현재일마감여부 return Y=마감, N=마감전          
	 * @exception Exception
	 */
    public String checkProdClosing(MpClosingDateVO vo) throws Exception {
        return (String) select("mpClosingDateDAO.checkProdClosing", vo);
    }
	
	/**
	 * mp_closing_date을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpClosingDateVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpClosingDate(EgovMap vo) throws Exception {
        return (String)insert("mpClosingDateDAO.insertMpClosingDate", vo);
    }

    /**
	 * mp_closing_date을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpClosingDateVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpClosingDate(EgovMap vo) throws Exception {
        return update("mpClosingDateDAO.updateMpClosingDate", vo);
    }

    /**
	 * mp_closing_date을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpClosingDateVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpClosingDate(EgovMap vo) throws Exception {
    	return delete("mpClosingDateDAO.deleteMpClosingDate", vo);
    }

    /**
	 * mp_closing_date을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpClosingDateVO
	 * @return 조회한 mp_closing_date
	 * @exception Exception
	 */
    public MpClosingDateVO selectMpClosingDate(EgovMap vo) throws Exception {
        return (MpClosingDateVO) select("mpClosingDateDAO.selectMpClosingDate_S", vo);
    }

    /**
	 * mp_closing_date 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_closing_date 목록
	 * @exception Exception
	 */
    public List<?> selectMpClosingDateList(MpClosingDateDefaultVO searchVO) throws Exception {
        return list("mpClosingDateDAO.selectMpClosingDateList_D", searchVO);
    }

    /**
	 * mp_closing_date 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_closing_date 총 갯수
	 * @exception
	 */
    public int selectMpClosingDateListTotCnt(MpClosingDateDefaultVO searchVO) {
        return (Integer)select("mpClosingDateDAO.selectMpClosingDateListTotCnt_S", searchVO);
    }

}
