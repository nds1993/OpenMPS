package nds.mpm.sales.SD0103.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0103.service.MpSalesmanProDAO;
import nds.mpm.sales.SD0103.service.MpSalesmanProService;
import nds.mpm.sales.SD0103.vo.MpSalesmanProDefaultVO;
import nds.mpm.sales.SD0103.vo.MpSalesmanProVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpSalesmanProServiceImpl.java
 * @Description : MpSalesmanPro Business Implement class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpSalesmanProService")
public class MpSalesmanProServiceImpl extends TMMPPBaseService implements
        MpSalesmanProService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpSalesmanProServiceImpl.class);

    @Resource(name="mpSalesmanProDAO")
    private MpSalesmanProDAO mpSalesmanProDAO;
	/**
	 * mp_salesman_pro을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpSalesmanProVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpSalesmanPro(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			if(mpSalesmanProDAO.selectMpSalesmanPro(reqVo) != null)
    			{
    				result.setMsg("중복된 제품입니다.");
    				result.setResultCode(Consts.ResultCode.RC_DUPLICATE.getCode());
    				return result;
    			}
    		}
    	}
    	
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			mpSalesmanProDAO.insertMpSalesmanPro(reqVo);
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			mpSalesmanProDAO.updateMpSalesmanPro(reqVo);
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			mpSalesmanProDAO.deleteMpSalesmanPro(reqVo);
    		}
    	}

    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos); 
    	
    	result.setExtraData(pageSet);
        return result;
    }

    /**
	 * mp_salesman_pro을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSalesmanProVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpSalesmanPro(MpSalesmanProVO vo) throws Exception {
        //mpSalesmanProDAO.updateMpSalesmanPro(vo);
    }

    /**
	 * mp_salesman_pro을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpSalesmanProVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpSalesmanPro(MpSalesmanProVO vo) throws Exception {
        //mpSalesmanProDAO.deleteMpSalesmanPro(vo);
    }

    /**
	 * mp_salesman_pro을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpSalesmanProVO
	 * @return 조회한 mp_salesman_pro
	 * @exception Exception
	 */
    public MpSalesmanProVO selectMpSalesmanPro(EgovMap vo) throws Exception {
        MpSalesmanProVO resultVO = mpSalesmanProDAO.selectMpSalesmanPro(vo);
        return resultVO;
    }

    /**
	 * mp_salesman_pro 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_salesman_pro 목록
	 * @exception Exception
	 */
    public List<?> selectMpSalesmanProList(MpSalesmanProDefaultVO searchVO) throws Exception {
        return mpSalesmanProDAO.selectMpSalesmanProList(searchVO);
    }

    /**
	 * mp_salesman_pro 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_salesman_pro 총 갯수
	 * @exception
	 */
    public int selectMpSalesmanProListTotCnt(MpSalesmanProDefaultVO searchVO) {
		return mpSalesmanProDAO.selectMpSalesmanProListTotCnt(searchVO);
	}
    
}
