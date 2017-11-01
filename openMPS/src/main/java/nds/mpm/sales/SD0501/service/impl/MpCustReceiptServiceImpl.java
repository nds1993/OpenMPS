package nds.mpm.sales.SD0501.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.sales.SD0501.service.MpCustReceiptService;
import nds.mpm.sales.SD0501.vo.MpCustReceiptDefaultVO;
import nds.mpm.sales.SD0501.vo.MpCustReceiptVO;
import nds.mpm.sales.SD0501.service.MpCustReceiptDAO;

/**
 * @Class Name : MpCustReceiptServiceImpl.java
 * @Description : MpCustReceipt Business Implement class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpCustReceiptService")
public class MpCustReceiptServiceImpl extends EgovAbstractServiceImpl implements
        MpCustReceiptService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpCustReceiptServiceImpl.class);

    @Resource(name="mpCustReceiptDAO")
    private MpCustReceiptDAO mpCustReceiptDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpCustReceiptIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_cust_receipt을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustReceiptVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpCustReceipt(MpCustReceiptVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpCustReceiptDAO.insertMpCustReceipt(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_cust_receipt을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustReceiptVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpCustReceipt(MpCustReceiptVO vo) throws Exception {
        mpCustReceiptDAO.updateMpCustReceipt(vo);
    }

    /**
	 * mp_cust_receipt을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustReceiptVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpCustReceipt(MpCustReceiptVO vo) throws Exception {
        mpCustReceiptDAO.deleteMpCustReceipt(vo);
    }

    /**
	 * mp_cust_receipt을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustReceiptVO
	 * @return 조회한 mp_cust_receipt
	 * @exception Exception
	 */
    public MpCustReceiptVO selectMpCustReceipt(MpCustReceiptVO vo) throws Exception {
        MpCustReceiptVO resultVO = mpCustReceiptDAO.selectMpCustReceipt(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_cust_receipt 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_receipt 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustReceiptList(MpCustReceiptDefaultVO searchVO) throws Exception {
        return mpCustReceiptDAO.selectMpCustReceiptList(searchVO);
    }

    /**
	 * mp_cust_receipt 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_receipt 총 갯수
	 * @exception
	 */
    public int selectMpCustReceiptListTotCnt(MpCustReceiptDefaultVO searchVO) {
		return mpCustReceiptDAO.selectMpCustReceiptListTotCnt(searchVO);
	}
    
}
