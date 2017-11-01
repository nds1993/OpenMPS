package nds.mpm.sales.SD0805.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0805.service.MpNotifyDAO;
import nds.mpm.sales.SD0805.service.MpNotifyService;
import nds.mpm.sales.SD0805.vo.MpNotifyDefaultVO;
import nds.mpm.sales.SD0805.vo.MpNotifyVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpNotifyServiceImpl.java
 * @Description : MpNotify Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpNotifyService")
public class MpNotifyServiceImpl extends EgovAbstractServiceImpl implements
        MpNotifyService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpNotifyServiceImpl.class);

    @Resource(name="mpNotifyDAO")
    private MpNotifyDAO mpNotifyDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpNotifyIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_notify을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpNotifyVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpNotify(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")) && selectMpNotify(reqVo) != null)
    		{
    			return new ResultEx( Consts.ResultCode.RC_DUPLICATE );
    		}
    		
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			mpNotifyDAO.insertMpNotify(reqVo);
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			mpNotifyDAO.updateMpNotify(reqVo);
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			mpNotifyDAO.deleteMpNotify(reqVo);
    		}
    		
    	}

    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	result.setExtraData(pageSet);
    	
    	return result;
    }

    /**
	 * mp_notify을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpNotifyVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpNotify(MpNotifyVO vo) throws Exception {
        //mpNotifyDAO.updateMpNotify(vo);
    }

    /**
	 * mp_notify을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpNotifyVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpNotify(MpNotifyVO vo) throws Exception {
        //mpNotifyDAO.deleteMpNotify(vo);
    }

    /**
	 * mp_notify을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpNotifyVO
	 * @return 조회한 mp_notify
	 * @exception Exception
	 */
    public MpNotifyVO selectMpNotify(EgovMap vo) throws Exception {
        MpNotifyVO resultVO = mpNotifyDAO.selectMpNotify(vo);
        return resultVO;
    }

    /**
	 * mp_notify 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_notify 목록
	 * @exception Exception
	 */
    public List<?> selectMpNotifyList(MpNotifyDefaultVO searchVO) throws Exception {
        return mpNotifyDAO.selectMpNotifyList(searchVO);
    }

    /**
	 * mp_notify 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_notify 총 갯수
	 * @exception
	 */
    public int selectMpNotifyListTotCnt(MpNotifyDefaultVO searchVO) {
		return mpNotifyDAO.selectMpNotifyListTotCnt(searchVO);
	}
    
}
