package nds.mpm.sales.SD0201.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.sales.SD0201.vo.MpStndProdDefaultVO;
import nds.mpm.sales.SD0201.vo.MpStndProdVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpStndProdDAO.java
 * @Description : MpStndProd DAO Class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpStndProdDAO")
public class MpStndProdDAO extends TMMPPBaseDAO {

	/**
	 * mptnd_prod을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpStndProdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpStndProd(EgovMap vo) throws Exception {
        return (String)insert("MpStndProdDAO.insertMpStndProd", vo);
    }

    /**
	 * mptnd_prod을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpStndProdVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpStndProd(MpStndProdVO vo) throws Exception {
        update("MpStndProdDAO.updateMpStndProd", vo);
    }

    /**
	 * mptnd_prod을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpStndProdVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpStndProd(EgovMap vo) throws Exception {
        delete("MpStndProdDAO.deleteMpStndProd", vo);
    }

    /**
	 * mptnd_prod을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpStndProdVO
	 * @return 조회한 mptnd_prod
	 * @exception Exception
	 */
    public MpStndProdVO selectMpStndProd(EgovMap vo) throws Exception {
        return (MpStndProdVO) select("MpStndProdDAO.selectMpStndProd", vo);
    }

    /**
	 * mptnd_prod 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mptnd_prod 목록
	 * @exception Exception
	 */
    public List<?> selectMpStndProdList(MpStndProdDefaultVO searchVO) throws Exception {
        return list("MpStndProdDAO.selectMpStndProdList_D", searchVO);
    }

    /**
	 * mptnd_prod 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mptnd_prod 총 갯수
	 * @exception
	 */
    public int selectMpStndProdListTotCnt(MpStndProdDefaultVO searchVO) {
        return (Integer)select("MpStndProdDAO.selectMpStndProdListTotCnt_S", searchVO);
    }

}
