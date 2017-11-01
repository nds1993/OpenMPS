package nds.mpm.buy.PO0202.service;

import java.util.List;

import nds.mpm.buy.PO0202.vo.MpPigexmDefaultVO;
import nds.mpm.buy.PO0202.vo.MpPigexmVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPigexmDAO.java
 * @Description : MpPigexm DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPigexmDAO")
public class MpPigexmDAO extends EgovAbstractDAO {

	/**
	 * mp_pigexm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigexmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPigexm(EgovMap vo) throws Exception {
        return (String)insert("mpPigexmDAO.insertMpPigexm", vo);
    }

    /**
	 * mp_pigexm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigexmVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpPigexm(EgovMap vo) throws Exception {
        return update("mpPigexmDAO.updateMpPigexm", vo);
    }

    /**
	 * mp_pigexm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigexmVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpPigexm(EgovMap vo) throws Exception {
        return delete("mpPigexmDAO.deleteMpPigexm", vo);
    }

    /**
	 * mp_pigexm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigexmVO
	 * @return 조회한 mp_pigexm
	 * @exception Exception
	 */
    public List<?> selectMpPigexm(EgovMap vo) throws Exception {
        return list("mpPigexmDAO.selectMpPigexm_S", vo);
    }

    /**
	 * mp_pigexm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigexm 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigexmList(MpPigexmDefaultVO searchVO) throws Exception {
        return list("mpPigexmDAO.selectMpPigexmList_D", searchVO);
    }

    /**
	 * mp_pigexm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigexm 총 갯수
	 * @exception
	 */
    public int selectMpPigexmListTotCnt(MpPigexmDefaultVO searchVO) {
        return (Integer)select("mpPigexmDAO.selectMpPigexmListTotCnt_S", searchVO);
    }
    
    public List<?> selectMpPigexmCodeList(MpPigexmVO searchVO) throws Exception {
        return list("mpPigexmDAO.selectMpPigexmCodeList", searchVO);
    }
    
    /**
     * 이력중 가장최근 정산구분+정산방법에 해당하는 정상방법조회
     * 
     * */
    public List selectMpPigexmRecentList(MpPigexmVO vo) throws Exception {
        return list("mpPigexmDAO.selectMpPigexmRecentList", vo);
    }
    
    /**
     * 기존 정산방법 코드 체크 
     * */
    public List<?> selectMpPigexmFacCodeList(MpPigexmDefaultVO searchVO) {
        return list("mpPigexmDAO.selectMpPigexmFacCodeList", searchVO);
    }
    
    public EgovMap selectMpPigexmJiyukAvg(MpPigexmDefaultVO searchVO) {
        return (EgovMap)select("mpPigexmDAO.selectMpPigexmJiyukAvg", searchVO);
    }
    
    public EgovMap updateAdjustFnPO0202Call(MpPigexmDefaultVO searchVO) {
        return (EgovMap)select("mpPigexmDAO.callAdjustFnPO0202Call", searchVO);
    }
    
    public EgovMap selectJiyukPeriod(MpPigexmDefaultVO searchVO) {
        return (EgovMap)select("mpPigexmDAO.selectJiyukPeriod", searchVO);
    }
    
    /**
	 * fac code duplicate check
	 * @exception
	 */
    public int checkMpPigexmFacCode(EgovMap searchVO) {
        return (Integer)select("mpPigexmDAO.checkMpPigexmFacCode", searchVO);
    }
    
    /**
     * 정산대상건 조회.
     * */
    public int checkMpPigdxmDochCnt(MpPigexmDefaultVO searchVO) {
        return (Integer)select("mpPigexmDAO.checkMpPigdxmDochCnt", searchVO);
    }
    
    /**
     * 신규생성시 이력저장, 최근일자 종료일 업데이트 
     * */
    public int updateRecentMpPigexmHist(EgovMap vo) throws Exception {
        return update("mpPigexmDAO.updateRecentMpPigexmHist", vo);
    }
    
}
