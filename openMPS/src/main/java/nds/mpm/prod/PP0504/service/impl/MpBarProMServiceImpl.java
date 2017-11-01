package nds.mpm.prod.PP0504.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0504.service.MpBarProMDAO;
import nds.mpm.prod.PP0504.service.MpBarProMService;
import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;
import nds.mpm.prod.PP0504.vo.MpBarProMVO;
import nds.mpm.sales.SD0801.service.MpClosingDateDAO;
import nds.mpm.sales.SD0801.vo.MpClosingDateVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpBarProMServiceImpl.java
 * @Description : MpBarProM Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpBarProMService")
public class MpBarProMServiceImpl extends TMMPPBaseService implements
        MpBarProMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpBarProMServiceImpl.class);

    @Resource(name="mpBarProMDAO")
    private MpBarProMDAO mpBarProMDAO;
    
    /**
     * 생산마감체크 SD0801
     * 
     * */
    @Resource(name="mpClosingDateDAO")
    private MpClosingDateDAO mpClosingDateDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpBarProMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_bar_pro_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBarProMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpBarProM(MpBarProMVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpBarProMDAO.insertMpBarProM(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }
    public ResultEx insertMpBarProM(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	if(vos == null)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	if(vos.size() == 0)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	/**
    	 * pro_date > now
    	 * count
    	 * */
    	MpClosingDateVO checkvo = new MpClosingDateVO();
    	checkvo.setCorpCode((String)vos.get(0).get("corpCode"));
    	
    	//
    	if(!"N".equals( mpClosingDateDAO.checkProdClosing(checkvo) ))
    	{
    		result.setMsg("생산일자가 마감되었습니다.");
    		result.setResultCode(Consts.ResultCode.RC_DENINED.getCode());
    		return result;
    	}
    	
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("D".equals((String)reqVo.get("dsType"))){
    			iCnt = mpBarProMDAO.deleteMpBarProM(reqVo);
    			if(iCnt == 0)
    			{
    				throw new Exception("delete fail!!");
    			}
    		}
    		else
    		{
    			reqVo.put("deleYn", "N");
    			iCnt = mpBarProMDAO.updateMpBarProM(reqVo);
    			if(iCnt == 0)
    			{
    				throw new Exception("restore fail!!");
    			}
    		}
    	}
    	
    	PageSet pageSet = new PageSet();
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	result.setExtraData(pageSet);
    	
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return result;
    }

    /**
	 * mp_bar_pro_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBarProMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpBarProM(MpBarProMVO vo) throws Exception {
        //mpBarProMDAO.updateMpBarProM(vo);
    }

    /**
	 * mp_bar_pro_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBarProMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpBarProM(MpBarProMVO vo) throws Exception {
        //mpBarProMDAO.deleteMpBarProM(vo);
    }

    /**
	 * mp_bar_pro_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBarProMVO
	 * @return 조회한 mp_bar_pro_m
	 * @exception Exception
	 */
    public MpBarProMVO selectMpBarProM(MpBarProMVO vo) throws Exception {
        MpBarProMVO resultVO = mpBarProMDAO.selectMpBarProM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_bar_pro_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpBarProMList(MpBarProMDefaultVO searchVO) throws Exception {
        return mpBarProMDAO.selectMpBarProMList(searchVO);
    }

    /**
	 * mp_bar_pro_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bar_pro_m 총 갯수
	 * @exception
	 */
    public int selectMpBarProMListTotCnt(MpBarProMDefaultVO searchVO) {
		return mpBarProMDAO.selectMpBarProMListTotCnt(searchVO);
	}
    
}
