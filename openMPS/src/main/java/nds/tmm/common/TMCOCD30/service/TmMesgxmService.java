package nds.tmm.common.TMCOCD30.service;

import java.util.List;
import nds.tmm.common.TMCOCD30.vo.TmMesgxmDefaultVO;
import nds.tmm.common.TMCOCD30.vo.TmMesgxmVO;

/**
 * @Class Name : TmMesgxmService.java
 * @Description : TmMesgxm Business class
 * @Modification Information
 *
 * @author OpenMPS Team
 * @since 2017. 7. 18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TmMesgxmService {
	
	/**
	 * tm_mesgxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TmMesgxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	int insertTmMesgxm(List<TmMesgxmVO> vo) throws Exception;
    
    /**
	 * tm_mesgxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TmMesgxmVO
	 * @return void형
	 * @exception Exception
	 */
    void updateTmMesgxm(TmMesgxmVO vo) throws Exception;
    
    /**
	 * tm_mesgxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TmMesgxmVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTmMesgxm(TmMesgxmVO vo) throws Exception;
    
    /**
	 * tm_mesgxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TmMesgxmVO
	 * @return 조회한 tm_mesgxm
	 * @exception Exception
	 */
    TmMesgxmVO selectTmMesgxm(TmMesgxmVO vo) throws Exception;
    
    /**
	 * tm_mesgxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_mesgxm 목록
	 * @exception Exception
	 */
    List selectTmMesgxmList(TmMesgxmDefaultVO searchVO) throws Exception;
    
    /**
	 * tm_mesgxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_mesgxm 총 갯수
	 * @exception
	 */
    int selectTmMesgxmListTotCnt(TmMesgxmDefaultVO searchVO);
    
}
