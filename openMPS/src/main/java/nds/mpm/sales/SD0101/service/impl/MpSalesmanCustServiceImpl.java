package nds.mpm.sales.SD0101.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0101.service.MpSalesmanCustDAO;
import nds.mpm.sales.SD0101.service.MpSalesmanCustService;
import nds.mpm.sales.SD0101.vo.MpSalesmanCustDefaultVO;
import nds.mpm.sales.SD0101.vo.MpSalesmanCustVO;
import nds.mpm.sales.SD0103.service.MpCustProDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpSalesmanCustServiceImpl.java
 * @Description : MpSalesmanCust Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpSalesmanCustService")
public class MpSalesmanCustServiceImpl extends EgovAbstractServiceImpl implements
        MpSalesmanCustService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpSalesmanCustServiceImpl.class);

    @Resource(name="mpSalesmanCustDAO")
    private MpSalesmanCustDAO mpSalesmanCustDAO;
    
    @Resource(name="mpCustProDAO")
    private MpCustProDAO mpCustProDAO;

	/**
	 * mp_salesman_cust을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpSalesmanCustVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpSalesmanCust(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	List<MultiDSTypeVO> mDS = new ArrayList();
    	int nCnt=0;
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			mpSalesmanCustDAO.insertMpSalesmanCust(reqVo);
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			mpSalesmanCustDAO.updateMpSalesmanCust(reqVo);
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			mpSalesmanCustDAO.deleteMpSalesmanCust(reqVo);
    			
    			nCnt = mpCustProDAO.deleteAllMpCustPro(reqVo);
    			if(nCnt == 0)
    				throw new Exception("삭제중 오류가 발생하였습니다.");
    		}
    	}
    	
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos); 
    	
    	result.setExtraData(pageSet);
        return result;
    }

    /**
	 * mp_salesman_cust을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSalesmanCustVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpSalesmanCust(MpSalesmanCustVO vo) throws Exception {
        ///mpSalesmanCustDAO.updateMpSalesmanCust(vo);
    }

    /**
	 * mp_salesman_cust을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpSalesmanCustVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpSalesmanCust(EgovMap vo) throws Exception {
        mpSalesmanCustDAO.deleteMpSalesmanCust(vo);
    }

    /**
	 * mp_salesman_cust을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpSalesmanCustVO
	 * @return 조회한 mp_salesman_cust
	 * @exception Exception
	 */
    public MpSalesmanCustVO selectMpSalesmanCust(MpSalesmanCustVO vo) throws Exception {
        MpSalesmanCustVO resultVO = mpSalesmanCustDAO.selectMpSalesmanCust(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_salesman_cust 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_salesman_cust 목록
	 * @exception Exception
	 */
    public List<?> selectMpSalesmanCustList(MpSalesmanCustDefaultVO searchVO) throws Exception {
        return mpSalesmanCustDAO.selectMpSalesmanCustList(searchVO);
    }

    /**
	 * mp_salesman_cust 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_salesman_cust 총 갯수
	 * @exception
	 */
    public int selectMpSalesmanCustListTotCnt(MpSalesmanCustDefaultVO searchVO) {
		return mpSalesmanCustDAO.selectMpSalesmanCustListTotCnt(searchVO);
	}
    
}
