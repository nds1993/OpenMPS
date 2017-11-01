package nds.mpm.sales.SD0102.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.sales.SD0102.vo.MpOppoProCodeDefaultVO;
import nds.mpm.sales.SD0102.vo.MpOppoProCodeVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOppoProCodeDAO.java
 * @Description : MpOppoProCode DAO Class
 * @Modification Information
 *
 * @author dd
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpOppoProCodeDAO")
public class MpOppoProCodeDAO extends TMMPPBaseDAO {

	/**
	 * mp_oppo_pro_code을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpOppoProCodeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpOppoProCode(MpOppoProCodeVO vo) throws Exception {
        return (String)insert("MpOppoProCodeDAO.insertMpOppoProCode", vo);
    }

    /**
	 * mp_oppo_pro_code을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpOppoProCodeVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpOppoProCode(MpOppoProCodeVO vo) throws Exception {
        update("MpOppoProCodeDAO.updateMpOppoProCode", vo);
    }

    /**
	 * mp_oppo_pro_code을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpOppoProCodeVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpOppoProCode(MpOppoProCodeVO vo) throws Exception {
        delete("MpOppoProCodeDAO.deleteMpOppoProCode", vo);
    }

    /**
	 * mp_oppo_pro_code을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpOppoProCodeVO
	 * @return 조회한 mp_oppo_pro_code
	 * @exception Exception
	 */
    public MpOppoProCodeVO selectMpOppoProCode(EgovMap vo) throws Exception {
        return (MpOppoProCodeVO) select("MpOppoProCodeDAO.selectMpOppoProCode_S", vo);
    }

    /**
	 * mp_oppo_pro_code 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_oppo_pro_code 목록
	 * @exception Exception
	 */
    public List<?> selectMpOppoProCodeList(MpOppoProCodeDefaultVO searchVO) throws Exception {
        return list("MpOppoProCodeDAO.selectMpOppoProCodeList_D", searchVO);
    }

    /**
	 * mp_oppo_pro_code 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_oppo_pro_code 총 갯수
	 * @exception
	 */
    public int selectMpOppoProCodeListTotCnt(MpOppoProCodeDefaultVO searchVO) {
        return (Integer)select("MpOppoProCodeDAO.selectMpOppoProCodeListTotCnt_S", searchVO);
    }
    
}
