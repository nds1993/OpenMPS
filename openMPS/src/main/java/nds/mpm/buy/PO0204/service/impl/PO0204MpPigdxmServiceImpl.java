package nds.mpm.buy.PO0204.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.buy.PO0102.vo.MpPigdxmDefaultVO;
import nds.mpm.buy.PO0102.vo.MpPigdxmVO;
import nds.mpm.buy.PO0204.service.PO0204MpPigdxmService;
import nds.mpm.buy.PO0204.service.PO0204MpPigdxmDAO;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;

/**
 * @Class Name : MpPigdxmServiceImpl.java
 * @Description : MpPigdxm Business Implement class
 * @Modification Information
 *
 * @author 생돈지급율조회
 * @since 생돈지급율조회
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PO0204mpPigdxmService")
public class PO0204MpPigdxmServiceImpl extends EgovAbstractServiceImpl implements
        PO0204MpPigdxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PO0204MpPigdxmServiceImpl.class);

    @Resource(name="PO0204mpPigdxmDAO")
    private PO0204MpPigdxmDAO mpPigdxmDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPigdxmIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_pigdxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigdxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPigdxm(MpPigdxmVO vo) throws Exception {
    	
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_pigdxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigdxmVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPigdxm(MpPigdxmVO vo) throws Exception {
        mpPigdxmDAO.updateMpPigdxm(vo);
    }

    /**
	 * mp_pigdxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigdxmVO
	 * @return void형 
	 * @exception Exception
	 */
    public ResultEx deleteMpPigdxm(List<EgovMap> vos) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int iCnt = 0;
    	int tiCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("D".equals((String)reqVo.get("dsType")))
    		{
    			iCnt = mpPigdxmDAO.deleteMpPigdxm(reqVo);
    			if(iCnt == 0)
    			{
    				throw new Exception("delete fail!!");
    			}
    			tiCnt++;
    		}
    			
    	}
    	result.setExtraData(tiCnt);
    	return result;
    }

    /**
	 * mp_pigdxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigdxmVO
	 * @return 조회한 mp_pigdxm
	 * @exception Exception
	 */
    public MpPigdxmVO selectMpPigdxm(MpPigdxmVO vo) throws Exception {
        MpPigdxmVO resultVO = mpPigdxmDAO.selectMpPigdxm(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_pigdxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigdxmList(MpPigdxmDefaultVO searchVO) throws Exception {
        return mpPigdxmDAO.selectMpPigdxmList(searchVO);
    }

    /**
	 * mp_pigdxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 총 갯수
	 * @exception
	 */
    public int selectMpPigdxmListTotCnt(MpPigdxmDefaultVO searchVO) {
		return mpPigdxmDAO.selectMpPigdxmListTotCnt(searchVO);
	}
    
}
