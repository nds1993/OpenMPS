package nds.mpm.prod.PP0803.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0803.service.MpPighisSaleMDAO;
import nds.mpm.prod.PP0803.service.MpPighisSaleMService;
import nds.mpm.prod.PP0803.vo.MpPighisSaleMDefaultVO;
import nds.mpm.prod.PP0803.vo.MpPighisSaleMVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPighisSaleMServiceImpl.java
 * @Description : MpPighisSaleM Business Implement class
 * @Modification Information
 *
 * @author 거래내역실적신고(이력제)
 * @since 거래내역실적신고(이력제)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPighisSaleMService")
public class MpPighisSaleMServiceImpl extends TMMPPBaseService implements
        MpPighisSaleMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPighisSaleMServiceImpl.class);

    @Resource(name="mpPighisSaleMDAO")
    private MpPighisSaleMDAO mpPighisSaleMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPighisSaleMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_pighis_sale_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPighisSaleMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPighisSaleM(List<EgovMap> vos) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	if(vos == null)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	if(vos.size() == 0)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}

    	String newKey = null;
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			newKey = mpPighisSaleMDAO.insertMpPighisSaleM(reqVo);
    			if(newKey == null)
    			{
    				throw new Exception("insert fall!!");
    			}
    		}
    	}
    	
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return result;
    }

    /**
	 * mp_pighis_sale_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPighisSaleMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPighisSaleM(MpPighisSaleMVO vo) throws Exception {
        mpPighisSaleMDAO.updateMpPighisSaleM(vo);
    }

    /**
	 * mp_pighis_sale_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPighisSaleMVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpPighisSaleM(MpPighisSaleMVO vo) throws Exception {
        return mpPighisSaleMDAO.deleteMpPighisSaleM(vo);
    }

    /**
	 * mp_pighis_sale_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPighisSaleMVO
	 * @return 조회한 mp_pighis_sale_m
	 * @exception Exception
	 */
    public MpPighisSaleMVO selectMpPighisSaleM(MpPighisSaleMVO vo) throws Exception {
        MpPighisSaleMVO resultVO = mpPighisSaleMDAO.selectMpPighisSaleM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_pighis_sale_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pighis_sale_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpPighisSaleMList(MpPighisSaleMDefaultVO searchVO) throws Exception {
        return mpPighisSaleMDAO.selectMpPighisSaleMList(searchVO);
    }

    /**
	 * mp_pighis_sale_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pighis_sale_m 총 갯수
	 * @exception
	 */
    public int selectMpPighisSaleMListTotCnt(MpPighisSaleMDefaultVO searchVO) {
		return mpPighisSaleMDAO.selectMpPighisSaleMListTotCnt(searchVO);
	}
    
    /**
     * 신고파일생성 포맷리턴 
	 * mp_pighis_sale_m 총 갯수를 조회한다.
	 * @exception
	 */
    public List<EgovMap> selectMpPighisSaleMSendFormatList(MpPighisSaleMDefaultVO searchVO) throws Exception{
        return mpPighisSaleMDAO.selectMpPighisSaleMSendFormatList(searchVO);
    }

    public int updateApiTimeMpPighisSaleM(EgovMap vo) throws Exception {
    	return mpPighisSaleMDAO.updateApiTimeMpPighisSaleM(vo);
    }
    
    public List<?> selectMpPighisSaleMProcodeList(MpPighisSaleMDefaultVO searchVO) throws Exception {
    	return mpPighisSaleMDAO.selectMpPighisSaleMProcodeList(searchVO);
    }

    public List<?> selectOdOderhdList(MpPighisSaleMDefaultVO searchVO) throws Exception {
    	return mpPighisSaleMDAO.selectOdOderhdList(searchVO);
    }
    
    public int checkDupSaleDate(MpPighisSaleMDefaultVO searchVO) {
    	return mpPighisSaleMDAO.checkDupSaleDate(searchVO);
    }
    
    public int selectMpPighisSaleSum(MpPighisSaleMDefaultVO searchVO) {
    	return mpPighisSaleMDAO.selectMpPighisSaleSum(searchVO);
    }
}
