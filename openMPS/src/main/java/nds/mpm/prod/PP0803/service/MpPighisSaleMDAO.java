package nds.mpm.prod.PP0803.service;

import java.util.List;

import nds.mpm.prod.PP0803.vo.MpPighisSaleMDefaultVO;
import nds.mpm.prod.PP0803.vo.MpPighisSaleMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPighisSaleMDAO.java
 * @Description : MpPighisSaleM DAO Class
 * @Modification Information
 *
 * @author 거래내역실적신고(이력제)
 * @since 거래내역실적신고(이력제)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPighisSaleMDAO")
public class MpPighisSaleMDAO extends EgovAbstractDAO {

	/**
	 * mp_pighis_sale_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPighisSaleMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPighisSaleM(EgovMap vo) throws Exception {
        return (String)insert("mpPighisSaleMDAO.insertMpPighisSaleM", vo);
    }

    /**
	 * mp_pighis_sale_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPighisSaleMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPighisSaleM(MpPighisSaleMVO vo) throws Exception {
        update("mpPighisSaleMDAO.updateMpPighisSaleM", vo);
    }

    /**
	 * mp_pighis_sale_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPighisSaleMVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpPighisSaleM(MpPighisSaleMVO vo) throws Exception {
        return delete("mpPighisSaleMDAO.deleteMpPighisSaleM", vo);
    }

    /**
	 * mp_pighis_sale_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPighisSaleMVO
	 * @return 조회한 mp_pighis_sale_m
	 * @exception Exception
	 */
    public MpPighisSaleMVO selectMpPighisSaleM(MpPighisSaleMVO vo) throws Exception {
        return (MpPighisSaleMVO) select("mpPighisSaleMDAO.selectMpPighisSaleM_S", vo);
    }

    /**
	 * mp_pighis_sale_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pighis_sale_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpPighisSaleMList(MpPighisSaleMDefaultVO searchVO) throws Exception {
        return list("mpPighisSaleMDAO.selectMpPighisSaleMList_D", searchVO);
    }

    /**
	 * mp_pighis_sale_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pighis_sale_m 총 갯수
	 * @exception
	 */
    public int selectMpPighisSaleMListTotCnt(MpPighisSaleMDefaultVO searchVO) {
        return (Integer)select("mpPighisSaleMDAO.selectMpPighisSaleMListTotCnt_S", searchVO);
    }

    /**
	 * mp_pighis_sale_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pighis_sale_m 목록
	 * @exception Exception
	 */
    public List<EgovMap> selectMpPighisSaleMSendFormatList(MpPighisSaleMDefaultVO searchVO) throws Exception {
        return (List<EgovMap>)list("mpPighisSaleMDAO.selectMpPighisSaleMSendFormatList", searchVO);
    }
    
    public int updateApiTimeMpPighisSaleM(EgovMap vo) throws Exception {
        return update("mpPighisSaleMDAO.updateApiTimeMpPighisSaleM", vo);
    }

    /**
     * 매출장조회 
     * */
    public List<?> selectMpPighisSaleMProcodeList(MpPighisSaleMDefaultVO searchVO) throws Exception {
        return list("mpPighisSaleMDAO.selectMpPighisSaleMProcodeList", searchVO);
    }
    
    public List<?> selectOdOderhdList(MpPighisSaleMDefaultVO searchVO) throws Exception {
        return list("mpPighisSaleMDAO.selectOdOderhdList_D", searchVO);
    }
    
    public int checkDupSaleDate(MpPighisSaleMDefaultVO searchVO) {
        return (Integer)select("mpPighisSaleMDAO.checkDupSaleDate", searchVO);
    }
    
    public int selectMpPighisSaleSum(MpPighisSaleMDefaultVO searchVO) {
        return (Integer)select("mpPighisSaleMDAO.selectMpPighisSaleSum", searchVO);
    }
    

}
