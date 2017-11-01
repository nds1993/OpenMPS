package nds.mpm.sales.SD0405.service;

import java.util.List;

import nds.mpm.sales.SD0405.vo.MpOrderUploadDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderUploadVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderUploadDAO.java
 * @Description : MpOrderUpload DAO Class
 * @Modification Information
 *
 * @author ㅇ
 * @since ㅇ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpOrderUploadDAO")
public class MpOrderUploadDAO extends EgovAbstractDAO {

	/**
	 * mp_order_upload을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpOrderUploadVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpOrderUpload(EgovMap vo) throws Exception {
        return (String)insert("mpOrderUploadDAO.insertMpOrderUpload_S", vo);
    }

    /**
	 * mp_order_upload을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpOrderUploadVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpOrderUpload(EgovMap vo) throws Exception {
        update("mpOrderUploadDAO.updateMpOrderUpload_S", vo);
    }

    /**
	 * mp_order_upload을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpOrderUploadVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpOrderUpload(EgovMap vo) throws Exception {
        delete("mpOrderUploadDAO.deleteMpOrderUpload_S", vo);
    }

    /**
	 * mp_order_upload을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpOrderUploadVO
	 * @return 조회한 mp_order_upload
	 * @exception Exception
	 */
    public EgovMap selectMpOrderUpload(EgovMap vo) throws Exception {
        return (EgovMap) select("mpOrderUploadDAO.selectMpOrderUpload_S", vo);
    }

    /**
	 * mp_order_upload 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_upload 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderUploadList(MpOrderUploadDefaultVO searchVO) throws Exception {
        return list("mpOrderUploadDAO.selectMpOrderUploadList_D", searchVO);
    }

    /**
	 * mp_order_upload 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_upload 총 갯수
	 * @exception
	 */
    public int selectMpOrderUploadListTotCnt(MpOrderUploadDefaultVO searchVO) {
        return (Integer)select("mpOrderUploadDAO.selectMpOrderUploadListTotCnt_S", searchVO);
    }

}
