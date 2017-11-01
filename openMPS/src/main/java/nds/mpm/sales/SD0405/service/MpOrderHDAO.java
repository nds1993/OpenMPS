package nds.mpm.sales.SD0405.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.sales.SD0101.vo.MpCustInfoVO;
import nds.mpm.sales.SD0405.vo.MpOrderHDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderHDAO.java
 * @Description : MpOrderH DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpOrderHDAO")
public class MpOrderHDAO extends TMMPPBaseDAO {

	/**
	 * mp_order_h을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpOrderHVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpOrderH(EgovMap vo) throws Exception {
        return (String)insert("MpOrderHDAO.insertMpOrderH", vo);
    }

    /**
	 * mp_order_h을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpOrderHVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpOrderH(EgovMap vo) throws Exception {
        update("MpOrderHDAO.updateMpOrderH", vo);
    }
    
    /**
	 * mp_order_h을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpOrderHVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpOrderHAmt(EgovMap vo) throws Exception {
        update("MpOrderHDAO.updateMpOrderHAmt", vo);
    }

    /**
	 * mp_order_h을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpOrderHVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpOrderH(EgovMap vo) throws Exception {
        return delete("MpOrderHDAO.deleteMpOrderH", vo);
    }

    /**
	 * mp_order_h을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpOrderHVO
	 * @return 조회한 mp_order_h
	 * @exception Exception
	 */
    public MpOrderHVO selectMpOrderH(MpOrderHVO vo) throws Exception {
        return (MpOrderHVO) select("MpOrderHDAO.selectMpOrderH_S", vo);
    }

    /**
	 * mp_order_h 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderHList(MpOrderHDefaultVO searchVO) throws Exception {
        return list("MpOrderHDAO.selectMpOrderHList_D", searchVO);
    }

    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    public int selectMpOrderHListTotCnt(MpOrderHDefaultVO searchVO) {
        return (Integer)select("MpOrderHDAO.selectMpOrderHListTotCnt_S", searchVO);
    }

    public String selectMpOrderNo(EgovMap searchVO) throws Exception {
        return (String)select("MpOrderHDAO.selectMpOrderNo", searchVO);
    }
    
    public int selectMpOrderPrice(EgovMap searchVO) throws Exception {
        return (Integer)select("MpOrderHDAO.selectMpOrderPrice", searchVO);
    }
    
    /**
	 * mp_order_h 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 목록
	 * @exception Exception
	 */
    public List<?> selectSD0406List(MpOrderHDefaultVO searchVO) throws Exception {
        return list("MpOrderHDAO.selectSD0406List", searchVO);
    }

    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    public int selectSD0406ListTotCnt(MpOrderHDefaultVO searchVO) {
        return (Integer)select("MpOrderHDAO.selectSD0406ListTotCnt_S", searchVO);
    }
    
    /**
   	 * 출고일자 영업사원 거래처 주문 내역 mp_order_h 목록을 조회한다.
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return mp_order_h 목록
   	 * @exception Exception
   	 */
    public List<?> selectSD0406CustList(MpOrderHDefaultVO searchVO) throws Exception {
       return list("MpOrderHDAO.selectSD0406CustList", searchVO);
    }
    
    /**
   	 * 출고일자 영업사원 거래처 주문 제품 내역 mp_order_d 목록을 조회한다.
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return mp_order_h 목록
   	 * @exception Exception
   	 */
    public List<?> selectSD0406OrdrProList(MpOrderHDefaultVO searchVO) throws Exception {
       return list("MpOrderHDAO.selectSD0406OrdrProList", searchVO);
    }
    
    public int deleteClearDevDateMpOrderH(EgovMap vo) throws Exception {
        return delete("MpOrderHDAO.deleteClearDevDateMpOrderH", vo);
    }
    
    public int deleteClearDevDateMpOrderD(EgovMap vo) throws Exception {
        return delete("MpOrderHDAO.deleteClearDevDateMpOrderD", vo);
    }
    
    public EgovMap selectSD0405DelvCust(MpCustInfoVO searchVO) throws Exception {
        return (EgovMap)select("MpOrderHDAO.selectSD0405DelvCust", searchVO);
    }
    
    /**
     * 엑셀업로드 출고일 주문 번호조회
     * 
     * */
    public EgovMap selectUploadCustMpOrderNo(EgovMap searchVO) {
        return (EgovMap)select("MpOrderHDAO.selectUploadCustMpOrderNo", searchVO);
    }
    /**
     * 엑셀업로드 출고일 주문 갱신
     * 
     * */
    public int updateUploadMpOrderHCustAmt(EgovMap vo) throws Exception {
        return update("MpOrderHDAO.updateUploadMpOrderHCustAmt", vo);
    }
    /**
     * 엑셀업로드 출고일 거래처 주문 삭제
     * 
     * */
    public int deleteUploadClearCustMpOrderH(EgovMap vo) throws Exception {
        return delete("MpOrderHDAO.deleteUploadClearCustMpOrderH", vo);
    }
    public int deleteUploadClearCustMpOrderD(EgovMap vo) throws Exception {
        return delete("MpOrderHDAO.deleteUploadClearCustMpOrderD", vo);
    }
}
