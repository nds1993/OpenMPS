package nds.mpm.buy.PO0102.service;

import java.util.List;

import nds.mpm.buy.PO0102.vo.PigDochDefaultVO;
import nds.mpm.buy.PO0102.vo.PigDochVO;

/**
 * @Class Name : PigDochService.java
 * @Description : PigDoch Business class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PigDochService {
	
	/**
	 * pig_doch을 등록한다.
	 * @param vo - 등록할 정보가 담긴 PigDochVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertPigDoch(PigDochVO vo) throws Exception;
    
    /**
	 * pig_doch을 수정한다.
	 * @param vo - 수정할 정보가 담긴 PigDochVO
	 * @return void형
	 * @exception Exception
	 */
    void updatePigDoch(PigDochVO vo) throws Exception;
    
    /**
	 * pig_doch을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 PigDochVO
	 * @return void형 
	 * @exception Exception
	 */
    void deletePigDoch(PigDochVO vo) throws Exception;
    
    /**
	 * pig_doch을 조회한다.
	 * @param vo - 조회할 정보가 담긴 PigDochVO
	 * @return 조회한 pig_doch
	 * @exception Exception
	 */
    PigDochVO selectPigDoch(PigDochVO vo) throws Exception;
    
    /**
	 * pig_doch 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return pig_doch 목록
	 * @exception Exception
	 */
    List selectPigDochList(PigDochDefaultVO searchVO) throws Exception;
    
    /**
	 * pig_doch 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return pig_doch 총 갯수
	 * @exception
	 */
    int selectPigDochListTotCnt(PigDochDefaultVO searchVO);
    
    public List<?> selectPigDochIdSum(PigDochDefaultVO searchVO) throws Exception;

}
