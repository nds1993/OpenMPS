package nds.mpm.sales.SD0101.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0101.service.MpCreditLimitDAO;
import nds.mpm.sales.SD0101.service.MpCreditLimitService;
import nds.mpm.sales.SD0101.vo.MpCreditLimitDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCreditLimitVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCreditLimitServiceImpl.java
 * @Description : MpCreditLimit Business Implement class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpCreditLimitService")
public class MpCreditLimitServiceImpl extends EgovAbstractServiceImpl implements
        MpCreditLimitService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpCreditLimitServiceImpl.class);

    @Resource(name="mpCreditLimitDAO")
    private MpCreditLimitDAO mpCreditLimitDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpCreditLimitIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_credit_limit을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCreditLimitVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpCreditLimit(EgovMap vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String newCreditSeq = mpCreditLimitDAO.insertMpCreditLimit(vo);
    	vo.put("seqNo", newCreditSeq);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return result;
    }

    /**
	 * mp_credit_limit을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCreditLimitVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpCreditLimit(MpCreditLimitVO vo) throws Exception {
        //mpCreditLimitDAO.updateMpCreditLimit(vo);
    }

    /**
	 * mp_credit_limit을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCreditLimitVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpCreditLimit(MpCreditLimitVO vo) throws Exception {
        //mpCreditLimitDAO.deleteMpCreditLimit(vo);
    }

    /**
	 * mp_credit_limit을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCreditLimitVO
	 * @return 조회한 mp_credit_limit
	 * @exception Exception
	 */
    public MpCreditLimitVO selectMpCreditLimit(MpCreditLimitVO vo) throws Exception {
        MpCreditLimitVO resultVO = mpCreditLimitDAO.selectMpCreditLimit(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_credit_limit 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_credit_limit 목록
	 * @exception Exception
	 */
    public List<?> selectMpCreditLimitList(MpCreditLimitDefaultVO searchVO) throws Exception {
        return mpCreditLimitDAO.selectMpCreditLimitList(searchVO);
    }

    /**
	 * mp_credit_limit 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_credit_limit 총 갯수
	 * @exception
	 */
    public int selectMpCreditLimitListTotCnt(MpCreditLimitDefaultVO searchVO) {
		return mpCreditLimitDAO.selectMpCreditLimitListTotCnt(searchVO);
	}
    
    public ResultEx updateApproMpCreditLimit(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int nCnt = 0;
    	for(EgovMap vo : vos)
		{
    		nCnt = mpCreditLimitDAO.updateApproMpCreditLimit(vo);
    		if(nCnt == 0)
    		{
    			throw new Exception("승인처리가 실패하였습니다.");
    		}
		}
    	
    	result.setExtraData(vos);
    	return result;
    }
}
