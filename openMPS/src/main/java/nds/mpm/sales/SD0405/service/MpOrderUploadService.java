package nds.mpm.sales.SD0405.service;

import java.util.List;
import java.util.Map;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0405.vo.MpOrderUploadDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderUploadVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderUploadService.java
 * @Description : MpOrderUpload Business class
 * @Modification Information
 *
 * @author ㅇ
 * @since ㅇ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpOrderUploadService {
	
	/**
	 * mp_order_upload을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpOrderUploadVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public ResultEx insertMpOrderUpload(EgovMap vo) throws Exception ;
    
    /**
	 * mp_order_upload을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpOrderUploadVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpOrderUpload(EgovMap vo) throws Exception;
    
    /**
	 * mp_order_upload을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpOrderUploadVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpOrderUpload(EgovMap vo) throws Exception;
    
    /**
	 * mp_order_upload을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpOrderUploadVO
	 * @return 조회한 mp_order_upload
	 * @exception Exception
	 */
    EgovMap selectMpOrderUpload(EgovMap vo) throws Exception;
    
    /**
	 * mp_order_upload 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_upload 목록
	 * @exception Exception
	 */
    List selectMpOrderUploadList(MpOrderUploadDefaultVO searchVO) throws Exception;
    public Map selectMpOrderUploadExcelCol(MpOrderUploadDefaultVO searchVO) throws Exception ;

    /**
	 * mp_order_upload 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_upload 총 갯수
	 * @exception
	 */
    int selectMpOrderUploadListTotCnt(MpOrderUploadDefaultVO searchVO);
    
}
