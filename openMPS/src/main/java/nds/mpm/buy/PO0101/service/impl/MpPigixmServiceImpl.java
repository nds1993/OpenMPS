package nds.mpm.buy.PO0101.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.buy.PO0101.service.MpPigixmService;
import nds.mpm.buy.PO0101.vo.MpPigixmDefaultVO;
import nds.mpm.buy.PO0101.vo.MpPigixmVO;
import nds.mpm.buy.PO0101.service.MpPigixmDAO;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;

/**
 * @Class Name : MpPigixmServiceImpl.java
 * @Description : MpPigixm Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPigixmService")
public class MpPigixmServiceImpl extends EgovAbstractServiceImpl implements
        MpPigixmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPigixmServiceImpl.class);

    @Resource(name="mpPigixmDAO")
    private MpPigixmDAO mpPigixmDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPigixmIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_pigixm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigixmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpSkinPigixm(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		
    		LOGGER.debug("dsType :: " + (String)reqVo.get("dsType"));
    		
    		if(!"D".equals((String)reqVo.get("dsType")))
    				mpPigixmDAO.insertMpSkinPigixm(reqVo);
    		if("D".equals((String)reqVo.get("dsType")))
    				mpPigixmDAO.deleteMpPigixm(reqVo);
    	}
    	
    	result.setExtraData(vos);
    	
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return result;
    }
    public ResultEx insertMpNonSkinPigixm(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	int iCnt = 0;
    	List<MultiDSTypeVO> mDS = new ArrayList();
    	for(EgovMap reqVo : vos)
    	{
    		if("D".equals((String)reqVo.get("dsType")))
    		{
			mpPigixmDAO.deleteMpPigixm(reqVo);
    		}
    		else
    		{
    			mpPigixmDAO.insertMpNonSkinPigixm(reqVo);
    		}
    	}
    	
    	result.setExtraData(vos);
    	
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return result;
    }

    /**
	 * mp_pigixm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigixmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPigixm(MpPigixmVO vo) throws Exception {
        mpPigixmDAO.updateMpPigixm(vo);
    }

    /**
	 * mp_pigixm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigixmVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPigixm(MpPigixmVO vo) throws Exception {
        //mpPigixmDAO.deleteMpPigixm(vo);
    }

    /**
	 * mp_pigixm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigixmVO
	 * @return 조회한 mp_pigixm
	 * @exception Exception
	 */
    public MpPigixmVO selectMpPigixm(MpPigixmVO vo) throws Exception {
        MpPigixmVO resultVO = mpPigixmDAO.selectMpPigixm(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_pigixm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigixm 목록
	 * @exception Exception
	 */
    public List<EgovMap> selectMpPigixmSkinList(MpPigixmDefaultVO searchVO) throws Exception {
        return mpPigixmDAO.selectMpPigixmSkinList(searchVO);
    }
    
    /**
   	 * mp_pigixm 목록을 조회한다.
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return mp_pigixm 목록
   	 * @exception Exception
   	 */
    public List<EgovMap> selectMpPigixmNonSkinList(MpPigixmDefaultVO searchVO) throws Exception {
    	return mpPigixmDAO.selectMpPigixmNonSkinList(searchVO);
    }
    /**
	 * mp_pigixm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigixm 총 갯수
	 * @exception
	 */
    public int selectMpPigixmListTotCnt(MpPigixmDefaultVO searchVO) {
		return mpPigixmDAO.selectMpPigixmListTotCnt(searchVO);
	}
    
    public List<?> selectIfPiggxmList(MpPigixmDefaultVO searchVO) throws Exception {
        return mpPigixmDAO.selectIfPiggxmList(searchVO);
    }
    public List<?> selectIfSkinPiggxmList(MpPigixmDefaultVO searchVO) throws Exception {
        return mpPigixmDAO.selectIfSkinPiggxmList(searchVO);
    }
    public List<?> selectIfNonSkinPiggxmList(MpPigixmDefaultVO searchVO) throws Exception {
        return mpPigixmDAO.selectIfNonSkinPiggxmList(searchVO);
    }

    /**
	 * if_piggxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pigixm 총 갯수
	 * @exception
	 */
    public int selectIfPiggxmListTotCnt(MpPigixmDefaultVO searchVO) {
		return mpPigixmDAO.selectIfPiggxmListTotCnt(searchVO);
	}
}
