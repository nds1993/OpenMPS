package nds.mpm.sales.SD0801.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0801.service.MpClosingDateService;
import nds.mpm.sales.SD0801.vo.MpClosingDateDefaultVO;
import nds.mpm.sales.SD0801.vo.MpClosingDateVO;
import nds.mpm.sales.SD0801.service.MpClosingDateDAO;

/**
 * @Class Name : MpClosingDateServiceImpl.java
 * @Description : MpClosingDate Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpClosingDateService")
public class MpClosingDateServiceImpl extends EgovAbstractServiceImpl implements
        MpClosingDateService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpClosingDateServiceImpl.class);

    @Resource(name="mpClosingDateDAO")
    private MpClosingDateDAO mpClosingDateDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpClosingDateIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_closing_date을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpClosingDateVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpClosingDate(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	for(EgovMap reqVo : vos)
    	{
    		
    		if("C".equals((String)reqVo.get("dsType")) && mpClosingDateDAO.selectMpClosingDate(reqVo) != null)
    		{
    			return new ResultEx(  Consts.ResultCode.RC_DUPLICATE.getCode() );
    		}
    	}
    	
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
			{
    			mpClosingDateDAO.insertMpClosingDate(reqVo);
			}
    		else if("U".equals((String)reqVo.get("dsType")))
			{
    			mpClosingDateDAO.updateMpClosingDate(reqVo);
			}
    		else if("D".equals((String)reqVo.get("dsType")))
			{
    			mpClosingDateDAO.deleteMpClosingDate(reqVo);
			}
    	}
    	
    	result.setExtraData(vos);
        return result;	
    }

    /**
	 * mp_closing_date을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpClosingDateVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpClosingDate(MpClosingDateVO vo) throws Exception {
        //mpClosingDateDAO.updateMpClosingDate(vo);
    }

    /**
	 * mp_closing_date을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpClosingDateVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpClosingDate(MpClosingDateVO vo) throws Exception {
        //mpClosingDateDAO.deleteMpClosingDate(vo);
    }

    /**
	 * mp_closing_date을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpClosingDateVO
	 * @return 조회한 mp_closing_date
	 * @exception Exception
	 */
    public MpClosingDateVO selectMpClosingDate(MpClosingDateVO vo) throws Exception {
        //MpClosingDateVO resultVO = mpClosingDateDAO.selectMpClosingDate(vo);
        //if (resultVO == null)
        //    throw processException("info.nodata.msg");
        return null;
    }

    /**
	 * mp_closing_date 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_closing_date 목록
	 * @exception Exception
	 */
    public List<?> selectMpClosingDateList(MpClosingDateDefaultVO searchVO) throws Exception {
        return mpClosingDateDAO.selectMpClosingDateList(searchVO);
    }

    /**
	 * mp_closing_date 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_closing_date 총 갯수
	 * @exception
	 */
    public int selectMpClosingDateListTotCnt(MpClosingDateDefaultVO searchVO) {
		return mpClosingDateDAO.selectMpClosingDateListTotCnt(searchVO);
	}
    
}
