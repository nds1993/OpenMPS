package nds.mpm.sales.SD0103.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0103.service.MpCustProDAO;
import nds.mpm.sales.SD0103.service.MpCustProService;
import nds.mpm.sales.SD0103.vo.MpCustProDefaultVO;
import nds.mpm.sales.SD0103.vo.MpCustProVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustProServiceImpl.java
 * @Description : MpCustPro Business Implement class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpCustProService")
public class MpCustProServiceImpl extends TMMPPBaseService implements
        MpCustProService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpCustProServiceImpl.class);

    @Resource(name="mpCustProDAO")
    private MpCustProDAO mpCustProDAO;
    
	/**
	 * mp_cust_pro을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustProVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpCustPro(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	List<MultiDSTypeVO> mDS = new ArrayList();
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")) && selectMpCustPro(reqVo) != null)
    		{
    			return new ResultEx(  Consts.ResultCode.RC_DUPLICATE );
    		}
    		
    		MultiDSTypeVO mvo = new MultiDSTypeVO();
    		mvo.setSqlId(resolveSqlId((String)reqVo.get("dsType"), "MpCustPro"));
    		mvo.setRowData(reqVo);
    		mDS.add(mvo);
    	}
    	int iCnt = mpCustProDAO.executeBatchMulti(mDS);
    	result.setExtraData(vos);
        return result;
    }

    /**
	 * mp_cust_pro을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustProVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpCustPro(MpCustProVO vo) throws Exception {
        mpCustProDAO.updateMpCustPro(vo);
    }

    /**
	 * mp_cust_pro을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustProVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpCustPro(MpCustProVO vo) throws Exception {
        mpCustProDAO.deleteMpCustPro(vo);
    }

    /**
	 * mp_cust_pro을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustProVO
	 * @return 조회한 mp_cust_pro
	 * @exception Exception
	 */
    public MpCustProVO selectMpCustPro(EgovMap vo) throws Exception {
        MpCustProVO resultVO = mpCustProDAO.selectMpCustPro(vo);
        return resultVO;
    }

    /**
	 * mp_cust_pro 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_pro 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustProList(MpCustProDefaultVO searchVO) throws Exception {
        return mpCustProDAO.selectMpCustProList(searchVO);
    }

    /**
	 * mp_cust_pro 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_pro 총 갯수
	 * @exception
	 */
    public int selectMpCustProListTotCnt(MpCustProDefaultVO searchVO) {
		return mpCustProDAO.selectMpCustProListTotCnt(searchVO);
	}
    
}
