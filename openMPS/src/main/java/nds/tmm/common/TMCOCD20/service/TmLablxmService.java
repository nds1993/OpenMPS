package nds.tmm.common.TMCOCD20.service;

import java.util.List;
import nds.tmm.common.TMCOCD20.vo.TmLablxmDefaultVO;
import nds.tmm.common.TMCOCD20.vo.TmLablxmVO;

/**
 * @Class Name : TmLablxmService.java
 * @Description : TmLablxm Business class
 * @Modification Information
 *
 * @author OpenMPS Team
 * @since 2017. 7. 18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface TmLablxmService {
	
	/**
	 * tm_lablxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TmLablxmVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    int insertTmLablxm(List<TmLablxmVO> vos) throws Exception;
    
    /**
	 * tm_lablxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TmLablxmVO
	 * @return void형
	 * @exception Exception
	 */
    void updateTmLablxm(TmLablxmVO vo) throws Exception;
    
    /**
	 * tm_lablxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TmLablxmVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTmLablxm(TmLablxmVO vo) throws Exception;
    
    /**
	 * tm_lablxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_lablxm 목록
	 * @exception Exception
	 */
    List selectTmLablxmList(TmLablxmDefaultVO searchVO) throws Exception;
    
    /**
	 * tm_lablxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_lablxm 총 갯수
	 * @exception
	 */
    int selectTmLablxmListTotCnt(TmLablxmDefaultVO searchVO);
    
}
