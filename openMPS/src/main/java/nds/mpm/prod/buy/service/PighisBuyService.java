package nds.mpm.prod.buy.service;

import java.util.List;
import nds.mpm.prod.buy.vo.PighisBuyDefaultVO;
import nds.mpm.prod.buy.vo.PighisBuyVO;

/**
 * @Class Name : PighisBuyService.java
 * @Description : PighisBuy Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.21
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PighisBuyService {
	
	/**
	 * pighis_buy을 등록한다.
	 * @param vo - 등록할 정보가 담긴 PighisBuyVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertPighisBuy(PighisBuyVO vo) throws Exception;
    
    /**
	 * pighis_buy을 수정한다.
	 * @param vo - 수정할 정보가 담긴 PighisBuyVO
	 * @return void형
	 * @exception Exception
	 */
    void updatePighisBuy(PighisBuyVO vo) throws Exception;
    
    /**
	 * pighis_buy을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 PighisBuyVO
	 * @return void형 
	 * @exception Exception
	 */
    void deletePighisBuy(PighisBuyVO vo) throws Exception;
    
    /**
	 * pighis_buy을 조회한다.
	 * @param vo - 조회할 정보가 담긴 PighisBuyVO
	 * @return 조회한 pighis_buy
	 * @exception Exception
	 */
    PighisBuyVO selectPighisBuy(PighisBuyVO vo) throws Exception;
    
    /**
	 * pighis_buy 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return pighis_buy 목록
	 * @exception Exception
	 */
    List selectPighisBuyList(PighisBuyDefaultVO searchVO) throws Exception;
    
    /**
	 * pighis_buy 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return pighis_buy 총 갯수
	 * @exception
	 */
    int selectPighisBuyListTotCnt(PighisBuyDefaultVO searchVO);
    
}
