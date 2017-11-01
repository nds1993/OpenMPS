package nds.mpm.sales.SD0102.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0102.service.MpOppoProCodeDAO;
import nds.mpm.sales.SD0102.service.MpOppoProCodeService;
import nds.mpm.sales.SD0102.vo.MpOppoProCodeDefaultVO;
import nds.mpm.sales.SD0102.vo.MpOppoProCodeVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOppoProCodeServiceImpl.java
 * @Description : MpOppoProCode Business Implement class
 * @Modification Information
 *
 * @author dd
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpOppoProCodeService")
public class MpOppoProCodeServiceImpl extends TMMPPBaseService implements
        MpOppoProCodeService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpOppoProCodeServiceImpl.class);

    @Resource(name="mpOppoProCodeDAO")
    private MpOppoProCodeDAO mpOppoProCodeDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpOppoProCodeIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_oppo_pro_code을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpOppoProCodeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpOppoProCode(List<EgovMap> vos) throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        for(EgovMap reqVo : vos)
    	{
    		if("null".equals(String.valueOf(reqVo.get("proCode"))) 
    				|| StringUtils.isEmpty(String.valueOf(reqVo.get("proCode"))))
    		{
    			result.setMsg("제품코드가 누락되었습니다.");
    			result.setResultCode( Consts.ResultCode.RC_FIND_NOT_FOUND.getCode() );
    			return result;
    		}
    		
    		if("null".equals(String.valueOf(reqVo.get("custCode"))) 
    				|| StringUtils.isEmpty(String.valueOf(reqVo.get("custCode"))))
    		{
    			result.setMsg("거래처코드가 누락되었습니다.");
    			result.setResultCode( Consts.ResultCode.RC_FIND_NOT_FOUND.getCode() );
    			return result;
    		}
    		if("null".equals(String.valueOf(reqVo.get("seqNo"))) 
    				|| StringUtils.isEmpty(String.valueOf(reqVo.get("seqNo"))))
    		{
    			result.setMsg("우선순위가 누락되었습니다.");
    			result.setResultCode( Consts.ResultCode.RC_FIND_NOT_FOUND.getCode() );
    			return result;
    		}
    		
    	}
    	
    	List<MultiDSTypeVO> mDS = new ArrayList();
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")) && selectMpOppoProCode(reqVo) != null)
    		{
    			return new ResultEx( Consts.ResultCode.RC_DUPLICATE.getCode() );
    		}
    		
    		MultiDSTypeVO mvo = new MultiDSTypeVO();
    		mvo.setSqlId(resolveSqlId((String)reqVo.get("dsType"), "MpOppoProCode"));
    		mvo.setRowData(reqVo);
    		mDS.add(mvo);
    	}
    	mpOppoProCodeDAO.executeBatchMulti(mDS);
    	result.setExtraData(vos);
        return result;
    }

    /**
	 * mp_oppo_pro_code을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpOppoProCodeVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpOppoProCode(MpOppoProCodeVO vo) throws Exception {
        mpOppoProCodeDAO.updateMpOppoProCode(vo);
    }

    /**
	 * mp_oppo_pro_code을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpOppoProCodeVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpOppoProCode(MpOppoProCodeVO vo) throws Exception {
        mpOppoProCodeDAO.deleteMpOppoProCode(vo);
    }

    /**
	 * mp_oppo_pro_code을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpOppoProCodeVO
	 * @return 조회한 mp_oppo_pro_code
	 * @exception Exception
	 */
    public MpOppoProCodeVO selectMpOppoProCode(EgovMap vo) throws Exception {
        MpOppoProCodeVO resultVO = mpOppoProCodeDAO.selectMpOppoProCode(vo);
        return resultVO;
    }

    /**
	 * mp_oppo_pro_code 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_oppo_pro_code 목록
	 * @exception Exception
	 */
    public List<?> selectMpOppoProCodeList(MpOppoProCodeDefaultVO searchVO) throws Exception {
        return mpOppoProCodeDAO.selectMpOppoProCodeList(searchVO);
    }

    /**
	 * mp_oppo_pro_code 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_oppo_pro_code 총 갯수
	 * @exception
	 */
    public int selectMpOppoProCodeListTotCnt(MpOppoProCodeDefaultVO searchVO) {
		return mpOppoProCodeDAO.selectMpOppoProCodeListTotCnt(searchVO);
	}
    
}
