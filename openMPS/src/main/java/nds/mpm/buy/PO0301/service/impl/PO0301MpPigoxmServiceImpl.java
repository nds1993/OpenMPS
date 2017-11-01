package nds.mpm.buy.PO0301.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.buy.PO0301.service.PO0301MpPigoxmDAO;
import nds.mpm.buy.PO0301.service.PO0301MpPigoxmService;
import nds.mpm.buy.PO0301.vo.MpPigoxmDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigoxmVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : PO0301MpPigoxmServiceImpl.java
 * @Description : PO0301MpPigoxm Business Implement class
 * @Modification Information
 *
 * @author PO0302
 * @since PO0302
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PO0301MpPigoxmService")
public class PO0301MpPigoxmServiceImpl extends EgovAbstractServiceImpl implements
        PO0301MpPigoxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PO0301MpPigoxmServiceImpl.class);

    @Resource(name="PO0301MpPigoxmDAO")
    private PO0301MpPigoxmDAO PO0301MpPigoxmDAO;
    
    /** ID Generation */
    //@Resource(name="{egovPO0301MpPigoxmIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_pigoxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 PO0301MpPigoxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertPO0301MpPigoxm(List<EgovMap> vos) throws Exception {
    	
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
    			newKey = PO0301MpPigoxmDAO.insertPO0301MpPigoxm(reqVo);
    			
    			LOGGER.debug("newKey or upKey :: " + newKey);
    			
    			if(newKey == null)
    				throw new Exception("insert key fail!!");
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			iCnt = PO0301MpPigoxmDAO.updatePO0301MpPigoxm(reqVo);
    			
    			LOGGER.debug("newKey or upKey :: " + newKey);
    			
    			if(iCnt == 0)
    				throw new Exception("update key fail!!");
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			iCnt = PO0301MpPigoxmDAO.deletePO0301MpPigoxm(reqVo);
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
    
    public ResultEx insertPO030Tab2MpPigoxm(List<EgovMap> vos) throws Exception {
    	
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
    			newKey = PO0301MpPigoxmDAO.insertPO0301MpPigoxm(reqVo);
    			
    			LOGGER.debug("newKey or upKey :: " + newKey);
    			
    			if(newKey == null)
    				throw new Exception("insert key fail!!");
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			iCnt = PO0301MpPigoxmDAO.updatePO0301Tab2MpPigoxm(reqVo);
    			
    			LOGGER.debug("newKey or upKey :: " + newKey);
    			
    			if(iCnt == 0)
    				throw new Exception("update key fail!!");
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			iCnt = PO0301MpPigoxmDAO.deletePO0301MpPigoxm(reqVo);
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
	 * mp_pigoxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 PO0301MpPigoxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updatePO0301MpPigoxm(EgovMap vo) throws Exception {
        PO0301MpPigoxmDAO.updatePO0301MpPigoxm(vo);
    }

    /**
	 * mp_pigoxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 PO0301MpPigoxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deletePO0301MpPigoxm(EgovMap vo) throws Exception {
        PO0301MpPigoxmDAO.deletePO0301MpPigoxm(vo);
    }

    /**
	 * mp_pigoxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 PO0301MpPigoxmVO
	 * @return 조회한 mp_pigoxm
	 * @exception Exception
	 */
    public MpPigoxmVO selectPO0301MpPigoxm(MpPigoxmVO vo) throws Exception {
        MpPigoxmVO resultVO = PO0301MpPigoxmDAO.selectPO0301MpPigoxm(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_pigoxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigoxm 목록
	 * @exception Exception
	 */
    public List<?> selectPO0301MpPigoxmTab1List(MpPigoxmDefaultVO searchVO) throws Exception {
        return PO0301MpPigoxmDAO.selectPO0301MpPigoxmTab1List(searchVO);
    }

    /**
	 * mp_pigoxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigoxm 총 갯수
	 * @exception
	 */
    public int selectPO0301MpPigoxmTab1ListTotCnt(MpPigoxmDefaultVO searchVO) {
		return PO0301MpPigoxmDAO.selectPO0301MpPigoxmTab1ListTotCnt(searchVO);
	}
    
    public List<?> selectPO0301Tab1MpPigdxmList_D(MpPigoxmVO searchVO) throws Exception {
    	 return PO0301MpPigoxmDAO.selectPO0301Tab1MpPigdxmList_D(searchVO);
    }
    
    
    public List<?> selectPO0301MpPigoxmTab2List(MpPigoxmDefaultVO searchVO) throws Exception {
        return PO0301MpPigoxmDAO.selectPO0301MpPigoxmTab2List(searchVO);
    }

    public int selectPO0301MpPigoxmTab2ListTotCnt(MpPigoxmDefaultVO searchVO) {
		return PO0301MpPigoxmDAO.selectPO0301MpPigoxmTab2ListTotCnt(searchVO);
	}
    
    public List<?> selectPO0301Tab3MpPigdxmList_D(MpPigoxmVO searchVO) throws Exception {
    	return PO0301MpPigoxmDAO.selectPO0301Tab3MpPigdxmList_D(searchVO);
    }

}
