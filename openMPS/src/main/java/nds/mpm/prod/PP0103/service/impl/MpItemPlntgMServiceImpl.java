package nds.mpm.prod.PP0103.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.PP0103.service.MpItemPlntgMService;
import nds.mpm.prod.PP0103.vo.MpItemPlntgMDefaultVO;
import nds.mpm.prod.PP0103.vo.MpItemPlntgMVO;
import nds.mpm.prod.PP0103.service.MpItemPlntgMDAO;

/**
 * @Class Name : MpItemPlntgMServiceImpl.java
 * @Description : MpItemPlntgM Business Implement class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpItemPlntgMService")
public class MpItemPlntgMServiceImpl extends EgovAbstractServiceImpl implements
        MpItemPlntgMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpItemPlntgMServiceImpl.class);

    @Resource(name="mpItemPlntgMDAO")
    private MpItemPlntgMDAO mpItemPlntgMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpItemPlntgMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_item_plntg_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpItemPlntgMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpItemPlntgM(MpItemPlntgMVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
        return null;
    }

    /**
	 * mp_item_plntg_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpItemPlntgMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpItemPlntgM(MpItemPlntgMVO vo) throws Exception {
        //mpItemPlntgMDAO.updateMpItemPlntgM(vo);
    }

    /**
	 * mp_item_plntg_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpItemPlntgMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpItemPlntgM(MpItemPlntgMVO vo) throws Exception {
        //mpItemPlntgMDAO.deleteMpItemPlntgM(vo);
    }

    /**
	 * mp_item_plntg_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpItemPlntgMVO
	 * @return 조회한 mp_item_plntg_m
	 * @exception Exception
	 */
    public MpItemPlntgMVO selectMpItemPlntgM(MpItemPlntgMVO vo) throws Exception {
        MpItemPlntgMVO resultVO = mpItemPlntgMDAO.selectMpItemPlntgM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_item_plntg_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_item_plntg_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpItemPlntgMList(MpItemPlntgMDefaultVO searchVO) throws Exception {
        return mpItemPlntgMDAO.selectMpItemPlntgMList(searchVO);
    }

    /**
	 * mp_item_plntg_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_item_plntg_m 총 갯수
	 * @exception
	 */
    public int selectMpItemPlntgMListTotCnt(MpItemPlntgMDefaultVO searchVO) {
		return mpItemPlntgMDAO.selectMpItemPlntgMListTotCnt(searchVO);
	}
    
}
