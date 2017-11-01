package nds.mpm.sales.SD0101.service;

import java.util.List;

import nds.mpm.sales.SD0101.vo.MpCustInfoDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustInfoVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustInfoDAO.java
 * @Description : MpCustInfo DAO Class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpCustInfoDAO")
public class MpCustInfoDAO extends EgovAbstractDAO {

	/**
	 * mp_cust_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpCustInfo(EgovMap vo) throws Exception {
        return (String)insert("mpCustInfoDAO.insertMpCustInfo", vo);
    }

    /**
	 * mp_cust_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpCustInfo(EgovMap vo) throws Exception {
    	return update("mpCustInfoDAO.updateMpCustInfo", vo);
    }
    
    /**
	 * mp_cust_info 농장정보를 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpCustInfoFac(MpCustInfoVO vo) throws Exception {
        update("mpCustInfoDAO.updateMpCustInfoFac", vo);
    }

    /**
	 * mp_cust_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpCustInfo(EgovMap vo) throws Exception {
        return delete("mpCustInfoDAO.deleteMpCustInfo", vo);
    }

    /**
	 * mp_cust_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustInfoVO
	 * @return 조회한 mp_cust_info
	 * @exception Exception
	 */
    public MpCustInfoVO selectMpCustInfo(MpCustInfoVO vo) throws Exception {
        return (MpCustInfoVO) select("mpCustInfoDAO.selectMpCustInfo_S", vo);
    }

    /**
	 * mp_cust_info 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_info 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustInfoList(MpCustInfoDefaultVO searchVO) throws Exception {
        return list("mpCustInfoDAO.selectMpCustInfoList_D", searchVO);
    }

    /**
	 * mp_cust_info 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_info 총 갯수
	 * @exception
	 */
    public int selectMpCustInfoListTotCnt(MpCustInfoDefaultVO searchVO) {
        return (Integer)select("mpCustInfoDAO.selectMpCustInfoListTotCnt_S", searchVO);
    }
    
    /**
	 * mp_cust_info 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_info 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustInfoCodeList(MpCustInfoDefaultVO searchVO) throws Exception {
        return list("mpCustInfoDAO.selectMpCustInfoCodeList_D", searchVO);
    }
    
    /**
	 * 거래처 영업정보, 계좌정보, 잔액, 당월입금액, 여신한도.
	 * @exception Exception
	 */
    public EgovMap selectMpCustAccountInfo(MpCustInfoVO vo) throws Exception {
        return (EgovMap) select("mpCustInfoDAO.selectMpCustAccountInfo", vo);
    }
    
    public int checkCustCodeCnt(EgovMap searchVO) {
        return (Integer)select("mpCustInfoDAO.checkCustCodeCnt", searchVO);
    }
    
    public List<?> selectOnlineUploadMpCustInfoCodeList(MpCustInfoDefaultVO searchVO) throws Exception {
        return list("mpCustInfoDAO.selectOnlineUploadMpCustInfoCodeList", searchVO);
    }
    
    
}
