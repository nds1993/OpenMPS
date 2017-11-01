package nds.mpm.sales.SD0103.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.sales.SD0103.vo.MpSalesmanProDefaultVO;
import nds.mpm.sales.SD0103.vo.MpSalesmanProVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpSalesmanProDAO.java
 * @Description : MpSalesmanPro DAO Class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpSalesmanProDAO")
public class MpSalesmanProDAO extends TMMPPBaseDAO {

	/**
	 * mp_salesman_pro을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpSalesmanProVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpSalesmanPro(EgovMap vo) throws Exception {
        return (String)insert("MpSalesmanProDAO.insertMpSalesmanPro", vo);
    }

    /**
	 * mp_salesman_pro을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSalesmanProVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpSalesmanPro(EgovMap vo) throws Exception {
        update("MpSalesmanProDAO.updateMpSalesmanPro", vo);
    }

    /**
	 * mp_salesman_pro을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpSalesmanProVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpSalesmanPro(EgovMap vo) throws Exception {
        delete("MpSalesmanProDAO.deleteMpSalesmanPro", vo);
    }

    /**
	 * mp_salesman_pro을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpSalesmanProVO
	 * @return 조회한 mp_salesman_pro
	 * @exception Exception
	 */
    public MpSalesmanProVO selectMpSalesmanPro(EgovMap vo) throws Exception {
        return (MpSalesmanProVO) select("MpSalesmanProDAO.selectMpSalesmanPro_S", vo);
    }

    /**
	 * mp_salesman_pro 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_salesman_pro 목록
	 * @exception Exception
	 */
    public List<?> selectMpSalesmanProList(MpSalesmanProDefaultVO searchVO) throws Exception {
        return list("MpSalesmanProDAO.selectMpSalesmanProList_D", searchVO);
    }

    /**
	 * mp_salesman_pro 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_salesman_pro 총 갯수
	 * @exception
	 */
    public int selectMpSalesmanProListTotCnt(MpSalesmanProDefaultVO searchVO) {
        return (Integer)select("MpSalesmanProDAO.selectMpSalesmanProListTotCnt_S", searchVO);
    }

}
