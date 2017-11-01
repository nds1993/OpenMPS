package nds.mpm.prod.buy.service;

import java.util.List;
import nds.mpm.prod.buy.vo.MpPigkxmDefaultVO;
import nds.mpm.prod.buy.vo.MpPigkxmVO;

/**
 * @Class Name : MpPigkxmService.java
 * @Description : MpPigkxm Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpPigkxmService {
	
	/**
	 * mp_pigkxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigkxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpPigkxm(MpPigkxmVO vo) throws Exception;
    
    /**
	 * mp_pigkxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigkxmVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpPigkxm(MpPigkxmVO vo) throws Exception;
    
    /**
	 * mp_pigkxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigkxmVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpPigkxm(MpPigkxmVO vo) throws Exception;
    
    /**
	 * mp_pigkxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigkxmVO
	 * @return 조회한 mp_pigkxm
	 * @exception Exception
	 */
    MpPigkxmVO selectMpPigkxm(MpPigkxmVO vo) throws Exception;
    
    /**
	 * mp_pigkxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigkxm 목록
	 * @exception Exception
	 */
    List selectMpPigkxmList(MpPigkxmDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_pigkxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigkxm 총 갯수
	 * @exception
	 */
    int selectMpPigkxmListTotCnt(MpPigkxmDefaultVO searchVO);
    
}
