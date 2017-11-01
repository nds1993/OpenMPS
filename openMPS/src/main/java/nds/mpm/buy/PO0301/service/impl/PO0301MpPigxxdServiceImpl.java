package nds.mpm.buy.PO0301.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.buy.PO0301.service.PO0301MpPigxxdDAO;
import nds.mpm.buy.PO0301.service.PO0301MpPigxxdService;
import nds.mpm.buy.PO0301.vo.MpPigxxdDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigxxdVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPigxxdServiceImpl.java
 * @Description : MpPigxxd Business Implement class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PO0301MpPigxxdService")
public class PO0301MpPigxxdServiceImpl extends EgovAbstractServiceImpl implements
        PO0301MpPigxxdService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PO0301MpPigxxdServiceImpl.class);

    @Resource(name="PO0301MpPigxxdDAO")
    private PO0301MpPigxxdDAO mpPigxxdDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPigxxdIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_pigxxd을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigxxdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPigxxd(List<EgovMap> vos) throws Exception {
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
    			newKey = mpPigxxdDAO.insertMpPigxxd(reqVo);
    			
    			LOGGER.debug("newKey or upKey :: " + newKey);
    			
    			if(newKey == null)
    				throw new Exception("insert key fail!!");
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			iCnt = mpPigxxdDAO.updateMpPigxxd(reqVo);
    			
    			LOGGER.debug("newKey or upKey :: " + newKey);
    			
    			if(iCnt == 0)
    				throw new Exception("update key fail!!");
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			iCnt = mpPigxxdDAO.deleteMpPigxxd(reqVo);
    			if(iCnt == 0)
    				throw new Exception("update key fail!!");
    		}
    		
    		iCnt++;
    	}
    	
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	
    	return result;
    }

    /**
	 * mp_pigxxd을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigxxdVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPigxxd(MpPigxxdVO vo) throws Exception {
        //mpPigxxdDAO.updateMpPigxxd(vo);
    }

    /**
	 * mp_pigxxd을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigxxdVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPigxxd(MpPigxxdVO vo) throws Exception {
        //mpPigxxdDAO.deleteMpPigxxd(vo);
    }

    /**
	 * mp_pigxxd을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigxxdVO
	 * @return 조회한 mp_pigxxd
	 * @exception Exception
	 */
    public MpPigxxdVO selectMpPigxxd(MpPigxxdVO vo) throws Exception {
        MpPigxxdVO resultVO = mpPigxxdDAO.selectMpPigxxd(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_pigxxd 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigxxd 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigxxdList(MpPigxxdDefaultVO searchVO) throws Exception {
        return mpPigxxdDAO.selectMpPigxxdList(searchVO);
    }

    /**
	 * mp_pigxxd 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigxxd 총 갯수
	 * @exception
	 */
    public int selectMpPigxxdListTotCnt(MpPigxxdDefaultVO searchVO) {
		return mpPigxxdDAO.selectMpPigxxdListTotCnt(searchVO);
	}
    
}
