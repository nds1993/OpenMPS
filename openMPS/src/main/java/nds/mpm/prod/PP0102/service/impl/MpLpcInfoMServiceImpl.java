package nds.mpm.prod.PP0102.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0102.service.MpLpcInfoMDAO;
import nds.mpm.prod.PP0102.service.MpLpcInfoMService;
import nds.mpm.prod.PP0102.vo.MpLpcInfoMDefaultVO;
import nds.mpm.prod.PP0102.vo.MpLpcInfoMVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : MpLpcInfoMServiceImpl.java
 * @Description : MpLpcInfoM Business Implement class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpLpcInfoMService")
public class MpLpcInfoMServiceImpl extends TMMPPBaseService implements
        MpLpcInfoMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpLpcInfoMServiceImpl.class);

    @Resource(name="mpLpcInfoMDAO")
    private MpLpcInfoMDAO mpLpcInfoMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpLpcInfoMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_lpc_info_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpLpcInfoMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public int insertMpLpcInfoM(List<MpLpcInfoMVO> vos) throws Exception {
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	int iCnt = 0;
    	List<MultiDSTypeVO> mDS = new ArrayList();
    	for(MpLpcInfoMVO reqVo : vos)
    	{
    		if("C".equals(reqVo.getDsType()) && selectMpLpcInfoM(reqVo) != null)
    		{
    			return Consts.ResultCode.RC_DUPLICATE.getCode();
    		}
    		
    		MultiDSTypeVO mvo = new MultiDSTypeVO();
    		mvo.setSqlId(resolveSqlId(reqVo.getDsType(), "TmPlatxm"));
    		mvo.setRowData(reqVo);
    		mDS.add(mvo);
    	}
    	
    	iCnt = mpLpcInfoMDAO.executeBatchMulti(mDS);
    	//mpLpcInfoMDAO.insertMpLpcInfoM(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return iCnt;
    }

    /**
	 * mp_lpc_info_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpLpcInfoMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpLpcInfoM(MpLpcInfoMVO vo) throws Exception {
        mpLpcInfoMDAO.updateMpLpcInfoM(vo);
    }

    /**
	 * mp_lpc_info_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpLpcInfoMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpLpcInfoM(MpLpcInfoMVO vo) throws Exception {
        mpLpcInfoMDAO.deleteMpLpcInfoM(vo);
    }

    /**
	 * mp_lpc_info_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpLpcInfoMVO
	 * @return 조회한 mp_lpc_info_m
	 * @exception Exception
	 */
    public MpLpcInfoMVO selectMpLpcInfoM(MpLpcInfoMVO vo) throws Exception {
        MpLpcInfoMVO resultVO = mpLpcInfoMDAO.selectMpLpcInfoM(vo);
        return resultVO;
    }

    /**
	 * mp_lpc_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_lpc_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpLpcInfoMList(MpLpcInfoMDefaultVO searchVO) throws Exception {
        return mpLpcInfoMDAO.selectMpLpcInfoMList(searchVO);
    }

    /**
	 * mp_lpc_info_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_lpc_info_m 총 갯수
	 * @exception
	 */
    public int selectMpLpcInfoMListTotCnt(MpLpcInfoMDefaultVO searchVO) {
		return mpLpcInfoMDAO.selectMpLpcInfoMListTotCnt(searchVO);
	}
    
}
