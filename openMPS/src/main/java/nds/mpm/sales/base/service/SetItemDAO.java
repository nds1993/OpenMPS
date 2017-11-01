package nds.mpm.sales.base.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.sales.base.vo.SetItemVO;
import nds.mpm.sales.base.vo.SetItemDefaultVO;

/**
 * @Class Name : SetItemDAO.java
 * @Description : SetItem DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.09
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("setItemDAO")
public class SetItemDAO extends EgovAbstractDAO {

	/**
	 * set_item을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SetItemVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertSetItem(SetItemVO vo) throws Exception {
        return (String)insert("setItemDAO.insertSetItem_S", vo);
    }

    /**
	 * set_item을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SetItemVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateSetItem(SetItemVO vo) throws Exception {
        update("setItemDAO.updateSetItem_S", vo);
    }

    /**
	 * set_item을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SetItemVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteSetItem(SetItemVO vo) throws Exception {
        delete("setItemDAO.deleteSetItem_S", vo);
    }

    /**
	 * set_item을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SetItemVO
	 * @return 조회한 set_item
	 * @exception Exception
	 */
    public SetItemVO selectSetItem(SetItemVO vo) throws Exception {
        return (SetItemVO) select("setItemDAO.selectSetItem_S", vo);
    }
    
    /**
	 * set_item 전체 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return set_item 목록
	 * @exception Exception
	 */
    public List<?> selectSetItemListAll(SetItemDefaultVO searchVO) throws Exception {
        return list("setItemDAO.selectSetItemList_A", searchVO);
    }

    /**
	 * set_item 상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return set_item 목록
	 * @exception Exception
	 */
    public List<?> selectSetItemList(SetItemDefaultVO searchVO) throws Exception {
        return list("setItemDAO.selectSetItemList_D", searchVO);
    }

    /**
	 * set_item 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return set_item 총 갯수
	 * @exception
	 */
    public int selectSetItemListTotCnt(SetItemDefaultVO searchVO) {
        return (Integer)select("setItemDAO.selectSetItemListTotCnt_S", searchVO);
    }

}
