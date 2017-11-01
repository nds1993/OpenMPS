package nds.mpm.sales.base.service;

import java.util.List;
import nds.mpm.sales.base.vo.SetItemDefaultVO;
import nds.mpm.sales.base.vo.SetItemVO;

/**
 * @Class Name : SetItemService.java
 * @Description : SetItem Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.09
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SetItemService {
	
	/**
	 * set_item을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SetItemVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertSetItem(SetItemVO vo) throws Exception;
    
    /**
	 * set_item을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SetItemVO
	 * @return void형
	 * @exception Exception
	 */
    void updateSetItem(SetItemVO vo) throws Exception;
    
    /**
	 * set_item을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SetItemVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteSetItem(SetItemVO vo) throws Exception;
    
    /**
	 * set_item을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SetItemVO
	 * @return 조회한 set_item
	 * @exception Exception
	 */
    SetItemVO selectSetItem(SetItemVO vo) throws Exception;
    
    /**
	 * set_item 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return set_item 목록
	 * @exception Exception
	 */
    List selectSetItemList(SetItemDefaultVO searchVO) throws Exception;
    
    /**
	 * set_item 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return set_item 총 갯수
	 * @exception
	 */
    int selectSetItemListTotCnt(SetItemDefaultVO searchVO);
    
}
