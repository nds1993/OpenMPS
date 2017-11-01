package nds.mpm.prod.PP0505.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0505.service.MpAutoTotalMDAO;
import nds.mpm.prod.PP0505.service.MpAutoTotalMService;
import nds.mpm.prod.PP0505.vo.MpAutoTotalMDefaultVO;
import nds.mpm.prod.PP0505.vo.MpAutoTotalMVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpAutoTotalMServiceImpl.java
 * @Description : MpAutoTotalM Business Implement class
 * @Modification Information
 *
 * @author PM라벨실적 입고 요청(이시다)
 * @since PM라벨실적 입고 요청(이시다)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpAutoTotalMService")
public class MpAutoTotalMServiceImpl extends EgovAbstractServiceImpl implements
        MpAutoTotalMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpAutoTotalMServiceImpl.class);

    @Resource(name="mpAutoTotalMDAO")
    private MpAutoTotalMDAO mpAutoTotalMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpAutoTotalMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_auto_total_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpAutoTotalMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpAutoTotalM(MpAutoTotalMVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	mpAutoTotalMDAO.insertMpAutoTotalM(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_auto_total_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpAutoTotalMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpAutoTotalM(MpAutoTotalMVO vo) throws Exception {
        mpAutoTotalMDAO.updateMpAutoTotalM(vo);
    }

    /**
	 * mp_auto_total_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpAutoTotalMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpAutoTotalM(MpAutoTotalMVO vo) throws Exception {
        mpAutoTotalMDAO.deleteMpAutoTotalM(vo);
    }

    /**
	 * mp_auto_total_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpAutoTotalMVO
	 * @return 조회한 mp_auto_total_m
	 * @exception Exception
	 */
    public MpAutoTotalMVO selectMpAutoTotalM(MpAutoTotalMVO vo) throws Exception {
        MpAutoTotalMVO resultVO = mpAutoTotalMDAO.selectMpAutoTotalM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_auto_total_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_auto_total_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpAutoTotalMList(MpAutoTotalMDefaultVO searchVO) throws Exception {
        return mpAutoTotalMDAO.selectMpAutoTotalMList(searchVO);
    }

    /**
	 * mp_auto_total_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_auto_total_m 총 갯수
	 * @exception
	 */
    public int selectMpAutoTotalMListTotCnt(MpAutoTotalMDefaultVO searchVO) {
		return mpAutoTotalMDAO.selectMpAutoTotalMListTotCnt(searchVO);
	}
    
    public List<?> selectMpAutoTotalMDetail(MpAutoTotalMDefaultVO searchVO) throws Exception {
    	return mpAutoTotalMDAO.selectMpAutoTotalMDetail(searchVO);
    }
    
    /**
     * 생산일자가 마감된 자료는 삭제 불가
     * 선택된 레코드는 Table의 WMS입고상태 (IN_STATUS) 컬럼 값을 '0'으로 Update
     * 입고요청 서비스(OPEN API)호출
     * API 결과를 메시지 박스로 표시
     * 재 조회 (처리 결과 확인)
     * */
    public ResultEx checkIpgoClose(MpAutoTotalMDefaultVO searchVO) throws Exception {
    
    	ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);
    	
    	/***
    	 * 입고요청 전 생산 마감체크.
    	 * 
    	 * */
    	if(mpAutoTotalMDAO.checkIpgoClosing(searchVO) > 0)
    	{
    		//시스템날짜가 prod_close 날짜보다 큼. 마감.
    		return new ResultEx(Consts.ResultCode.RC_DENINED);
    	}
    	return result;
    }
    
    /**
     * 생산마감 체크 mp_closing_date
     * 
     * */
    public int checkMpClosingDate(MpAutoTotalMDefaultVO searchVO) {
		return mpAutoTotalMDAO.selectMpAutoTotalMListTotCnt(searchVO);
	}
}
