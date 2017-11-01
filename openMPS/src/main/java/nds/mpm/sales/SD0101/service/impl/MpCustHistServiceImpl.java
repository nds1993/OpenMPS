package nds.mpm.sales.SD0101.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0101.service.MpCustHistDAO;
import nds.mpm.sales.SD0101.service.MpCustHistService;
import nds.mpm.sales.SD0101.service.MpSalesmanCustDAO;
import nds.mpm.sales.SD0101.vo.MpCustHistDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustHistVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustHistServiceImpl.java
 * @Description : MpCustHist Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpCustHistService")
public class MpCustHistServiceImpl extends EgovAbstractServiceImpl implements
        MpCustHistService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpCustHistServiceImpl.class);

    @Resource(name="mpCustHistDAO")
    private MpCustHistDAO mpCustHistDAO;
    
    @Resource(name="mpSalesmanCustDAO")
    private MpSalesmanCustDAO mpSalesmanCustDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpCustHistIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_cust_hist을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustHistVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpCustHist(EgovMap vo) throws Exception {
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int newSeq = mpCustHistDAO.insertMpCustHist(vo);
    	vo.put("seqNo", newSeq);
    	
    	vo.put("salesmanCust", vo.get("custCode"));
    	
    	mpSalesmanCustDAO.deleteMpSalesmanCust(vo);
    	mpSalesmanCustDAO.insertMpSalesmanCust(vo);
    	//TODO 해당 테이블 정보에 맞게 수정
    	
    	result.setExtraData(vo);
        return result;
    }

    /**
	 * mp_cust_hist을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustHistVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpCustHist(MpCustHistVO vo) throws Exception {
        ///mpCustHistDAO.updateMpCustHist(vo);
    }

    /**
	 * mp_cust_hist을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustHistVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpCustHist(MpCustHistVO vo) throws Exception {
        //mpCustHistDAO.deleteMpCustHist(vo);
    }

    /**
	 * mp_cust_hist을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustHistVO
	 * @return 조회한 mp_cust_hist
	 * @exception Exception
	 */
    public MpCustHistVO selectMpCustHist(MpCustHistVO vo) throws Exception {
        MpCustHistVO resultVO = mpCustHistDAO.selectMpCustHist(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_cust_hist 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_hist 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustHistList(MpCustHistDefaultVO searchVO) throws Exception {
        return mpCustHistDAO.selectMpCustHistList(searchVO);
    }

    /**
	 * mp_cust_hist 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_hist 총 갯수
	 * @exception
	 */
    public int selectMpCustHistListTotCnt(MpCustHistDefaultVO searchVO) {
		return mpCustHistDAO.selectMpCustHistListTotCnt(searchVO);
	}
    
}
