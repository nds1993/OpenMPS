package nds.mpm.prod.PP0105.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.prod.PP0105.vo.MpBomDDefaultVO;
import nds.mpm.prod.PP0105.vo.MpBomDVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpBomDDAO.java
 * @Description : MpBomD DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpBomDDAO")
public class MpBomDDAO extends TMMPPBaseDAO {

	/**
	 * mp_bom_d을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBomDVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpBomD(EgovMap vo) throws Exception {
        return (String)insert("MpBomDDAO.insertMpBomD", vo);
    }

    /**
	 * mp_bom_d을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBomDVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpBomD(EgovMap vo) throws Exception {
        update("MpBomDDAO.updateMpBomD", vo);
    }

    /**
	 * mp_bom_d을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBomDVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpBomD(EgovMap vo) throws Exception {
        delete("MpBomDDAO.deleteMpBomD", vo);
    }
    
    public int deleteOldVerMpBomD(EgovMap vo) throws Exception {
        return delete("MpBomDDAO.deleteOldVerMpBomD", vo);
    }
    
    /**
	 * mp_bom_d을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBomDVO
	 * @return 조회한 mp_bom_d
	 * @exception Exception
	 */
    public MpBomDVO selectMpBomD(EgovMap vo) throws Exception {
        return (MpBomDVO) select("MpBomDDAO.selectMpBomD_S", vo);
    }

    /**
	 * mp_bom_d 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bom_d 목록
	 * @exception Exception
	 */
    public List<EgovMap> selectMpBomDList(MpBomDDefaultVO searchVO) throws Exception {
        return (List<EgovMap>)list("MpBomDDAO.selectMpBomDList_D", searchVO);
    }

    /**
	 * mp_bom_d 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bom_d 총 갯수
	 * @exception
	 */
    public int selectMpBomDListTotCnt(MpBomDDefaultVO searchVO) {
        return (Integer)select("MpBomDDAO.selectMpBomDListTotCnt_S", searchVO);
    }

}
