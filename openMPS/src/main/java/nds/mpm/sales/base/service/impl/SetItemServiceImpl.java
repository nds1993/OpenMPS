package nds.mpm.sales.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.sales.base.service.SetItemService;
import nds.mpm.sales.base.vo.SetItemDefaultVO;
import nds.mpm.sales.base.vo.SetItemVO;
import nds.mpm.sales.base.service.SetItemDAO;

/**
 * @Class Name : SetItemServiceImpl.java
 * @Description : SetItem Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.09
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("setItemService")
public class SetItemServiceImpl extends EgovAbstractServiceImpl implements
        SetItemService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SetItemServiceImpl.class);

    @Resource(name="setItemDAO")
    private SetItemDAO setItemDAO;
    
    /** ID Generation */
    //@Resource(name="{egovSetItemIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * set_item을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SetItemVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertSetItem(SetItemVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	setItemDAO.insertSetItem(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * set_item을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SetItemVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateSetItem(SetItemVO vo) throws Exception {
        setItemDAO.updateSetItem(vo);
    }

    /**
	 * set_item을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SetItemVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteSetItem(SetItemVO vo) throws Exception {
        setItemDAO.deleteSetItem(vo);
    }

    /**
	 * set_item을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SetItemVO
	 * @return 조회한 set_item
	 * @exception Exception
	 */
    public SetItemVO selectSetItem(SetItemVO vo) throws Exception {
        SetItemVO resultVO = setItemDAO.selectSetItem(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * set_item 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return set_item 목록
	 * @exception Exception
	 */
    public List<?> selectSetItemList(SetItemDefaultVO searchVO) throws Exception {
        return setItemDAO.selectSetItemList(searchVO);
    }

    /**
	 * set_item 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return set_item 총 갯수
	 * @exception
	 */
    public int selectSetItemListTotCnt(SetItemDefaultVO searchVO) {
		return setItemDAO.selectSetItemListTotCnt(searchVO);
	}
    
}
