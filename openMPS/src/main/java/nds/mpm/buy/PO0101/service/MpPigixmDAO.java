package nds.mpm.buy.PO0101.service;

import java.util.List;

import nds.mpm.buy.PO0101.vo.MpPigixmDefaultVO;
import nds.mpm.buy.PO0101.vo.MpPigixmVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPigixmDAO.java
 * @Description : MpPigixm DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPigixmDAO")
public class MpPigixmDAO extends EgovAbstractDAO {

	/**
	 * mp_pigixm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigixmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpSkinPigixm(EgovMap vo) throws Exception {
        return (String)insert("MpPigixmDAO.insertSkinMpPigixm", vo);
    }
    public String insertMpNonSkinPigixm(EgovMap vo) throws Exception {
        return (String)insert("MpPigixmDAO.insertMpNonSkinPigixm", vo);
    }
    
    /**
	 * mp_pigixm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigixmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpNonSkinPigixm(MpPigixmVO vo) throws Exception {
        return (String)insert("MpPigixmDAO.insertMpNonSkinPigixm", vo);
    }

    /**
	 * mp_pigixm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigixmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPigixm(MpPigixmVO vo) throws Exception {
        update("MpPigixmDAO.updateMpPigixm", vo);
    }

    /**
	 * mp_pigixm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigixmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPigixm(EgovMap vo) throws Exception {
        delete("MpPigixmDAO.deleteMpPigixm", vo);
    }

    /**
	 * mp_pigixm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigixmVO
	 * @return 조회한 mp_pigixm
	 * @exception Exception
	 */
    public MpPigixmVO selectMpPigixm(MpPigixmVO vo) throws Exception {
        return (MpPigixmVO) select("MpPigixmDAO.selectMpPigixm_S", vo);
    }

    /**
	 * mp_pigixm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigixm 목록
	 * @exception Exception
	 */
    public List<EgovMap> selectMpPigixmSkinList(MpPigixmDefaultVO searchVO) throws Exception {
        return (List<EgovMap>)list("MpPigixmDAO.selectMpPigixmSkinList_D", searchVO);
    }
    
    /**
   	 * mp_pigixm 목록을 조회한다.
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return mp_pigixm 목록
   	 * @exception Exception
   	 */
    public List<EgovMap> selectMpPigixmNonSkinList(MpPigixmDefaultVO searchVO) throws Exception {
       return (List<EgovMap>)list("MpPigixmDAO.selectMpPigixmNonSkinList_D", searchVO);
    }

    /**
	 * mp_pigixm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigixm 총 갯수
	 * @exception
	 */
    public int selectMpPigixmListTotCnt(MpPigixmDefaultVO searchVO) {
        return (Integer)select("MpPigixmDAO.selectMpPigixmListTotCnt_S", searchVO);
    }
    
    /**
	 * if_piggxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigixm 목록
	 * @exception Exception
	 */
    public List<?> selectIfPiggxmList(MpPigixmDefaultVO searchVO) throws Exception {
        return list("MpPigixmDAO.selectIfPiggxmList_D", searchVO);
    }
    
    public List<?> selectIfSkinPiggxmList(MpPigixmDefaultVO searchVO) throws Exception {
        return list("MpPigixmDAO.selectIfSkinPiggxmList_D", searchVO);
    }
    
    public List<?> selectIfNonSkinPiggxmList(MpPigixmDefaultVO searchVO) throws Exception {
        return list("MpPigixmDAO.selectIfNonSkinPiggxmList_D", searchVO);
    }

    /**
	 * if_piggxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigixm 총 갯수
	 * @exception
	 */
    public int selectIfPiggxmListTotCnt(MpPigixmDefaultVO searchVO) {
        return (Integer)select("MpPigixmDAO.selectIfPiggxmListTotCnt_S", searchVO);
    }
}
