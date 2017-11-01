package nds.mpm.buy.PO0301.service;

import java.util.List;

import nds.mpm.buy.PO0301.vo.MpPigoxmDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigoxmVO;
import nds.mpm.common.vo.ResultEx;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : PO0301MpPigoxmService.java
 * @Description : PO0301MpPigoxm Business class
 * @Modification Information
 *
 * @author PO0302
 * @since PO0302
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PO0301MpPigoxmService {
	
	/**
	 * mp_pigoxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 PO0301MpPigoxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertPO0301MpPigoxm(List<EgovMap> vos) throws Exception ;
    
    public ResultEx insertPO030Tab2MpPigoxm(List<EgovMap> vos) throws Exception ;

    
    /**
	 * mp_pigoxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 PO0301MpPigoxmVO
	 * @return void형
	 * @exception Exception
	 */
    void updatePO0301MpPigoxm(EgovMap vo) throws Exception;
    
    /**
	 * mp_pigoxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 PO0301MpPigoxmVO
	 * @return void형 
	 * @exception Exception
	 */
    void deletePO0301MpPigoxm(EgovMap vo) throws Exception;
    
    /**
	 * mp_pigoxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 PO0301MpPigoxmVO
	 * @return 조회한 mp_pigoxm
	 * @exception Exception
	 */
    MpPigoxmVO selectPO0301MpPigoxm(MpPigoxmVO vo) throws Exception;
    
    /**
	 * mp_pigoxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigoxm 목록
	 * @exception Exception
	 */
    List selectPO0301MpPigoxmTab1List(MpPigoxmDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_pigoxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigoxm 총 갯수
	 * @exception
	 */
    int selectPO0301MpPigoxmTab1ListTotCnt(MpPigoxmDefaultVO searchVO);
    
    public List<?> selectPO0301Tab1MpPigdxmList_D(MpPigoxmVO searchVO) throws Exception ;

    public List<?> selectPO0301MpPigoxmTab2List(MpPigoxmDefaultVO searchVO) throws Exception ;

    public int selectPO0301MpPigoxmTab2ListTotCnt(MpPigoxmDefaultVO searchVO) ;
    
    public List<?> selectPO0301Tab3MpPigdxmList_D(MpPigoxmVO searchVO) throws Exception ;

}
