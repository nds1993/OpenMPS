package nds.mpm.sales.SD0201.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0201.service.MpStndProdDAO;
import nds.mpm.sales.SD0201.service.MpStndProdService;
import nds.mpm.sales.SD0201.vo.MpStndProdDefaultVO;
import nds.mpm.sales.SD0201.vo.MpStndProdVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpStndProdServiceImpl.java
 * @Description : MpStndProd Business Implement class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpStndProdService")
public class MpStndProdServiceImpl extends TMMPPBaseService implements
        MpStndProdService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpStndProdServiceImpl.class);

    @Resource(name="mpStndProdDAO")
    private MpStndProdDAO mpStndProdDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpStndProdIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_stnd_prod을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpStndProdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpStndProd(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")) && mpStndProdDAO.selectMpStndProd(reqVo) != null)
    		{
    			result.setResultCode(Consts.ResultCode.RC_DUPLICATE.getCode());
    			return result;
    		}
    	}
    	
    	for(EgovMap reqVo : vos)
    	{
    		
    		if("C".equals((String)reqVo.get("dsType")))
			{
        		mpStndProdDAO.insertMpStndProd(reqVo);

			}
    		else if("U".equals((String)reqVo.get("dsType")))
			{
    			EgovMap delVo = new EgovMap();
    			delVo.put("corpCode", reqVo.get("corpCode"));
        		delVo.put("proCode", reqVo.get("oldProCode"));
        		
        		mpStndProdDAO.deleteMpStndProd(delVo);
        		
        		mpStndProdDAO.insertMpStndProd(reqVo);
			}
    		else if("D".equals((String)reqVo.get("dsType")))
			{
    			mpStndProdDAO.deleteMpStndProd(reqVo);
			}
    	}
    	
    	result.setExtraData(vos);
        return result;
    }

    /**
	 * mp_stnd_prod을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpStndProdVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpStndProd(MpStndProdVO vo) throws Exception {
        mpStndProdDAO.updateMpStndProd(vo);
    }

    /**
	 * mp_stnd_prod을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpStndProdVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpStndProd(MpStndProdVO vo) throws Exception {
        //mpStndProdDAO.deleteMpStndProd(vo);
    }

    /**
	 * mp_stnd_prod을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpStndProdVO
	 * @return 조회한 mp_stnd_prod
	 * @exception Exception
	 */
    public MpStndProdVO selectMpStndProd(EgovMap vo) throws Exception {
        MpStndProdVO resultVO = mpStndProdDAO.selectMpStndProd(vo);
        return resultVO;
    }

    /**
	 * mp_stnd_prod 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_stnd_prod 목록
	 * @exception Exception
	 */
    public List<?> selectMpStndProdList(MpStndProdDefaultVO searchVO) throws Exception {
        return mpStndProdDAO.selectMpStndProdList(searchVO);
    }

    /**
	 * mp_stnd_prod 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_stnd_prod 총 갯수
	 * @exception
	 */
    public int selectMpStndProdListTotCnt(MpStndProdDefaultVO searchVO) {
		return mpStndProdDAO.selectMpStndProdListTotCnt(searchVO);
	}
    
}
