package nds.mpm.common.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.common.user.service.ScreUserService;
import nds.mpm.common.user.vo.ScreUserDefaultVO;
import nds.mpm.common.user.vo.ScreUserVO;
import nds.mpm.common.user.service.ScreUserDAO;

/**
 * @Class Name : ScreUserServiceImpl.java
 * @Description : ScreUser Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("screUserService")
public class ScreUserServiceImpl extends EgovAbstractServiceImpl implements
        ScreUserService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(ScreUserServiceImpl.class);

    @Resource(name="screUserDAO")
    private ScreUserDAO screUserDAO;
    
    /** ID Generation */
    //@Resource(name="{egovScreUserIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * scre_user을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ScreUserVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertScreUser(ScreUserVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	screUserDAO.insertScreUser(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * scre_user을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ScreUserVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateScreUser(ScreUserVO vo) throws Exception {
        screUserDAO.updateScreUser(vo);
    }

    /**
	 * scre_user을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ScreUserVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteScreUser(ScreUserVO vo) throws Exception {
        screUserDAO.deleteScreUser(vo);
    }

    /**
	 * scre_user을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ScreUserVO
	 * @return 조회한 scre_user
	 * @exception Exception
	 */
    public ScreUserVO selectScreUser(ScreUserVO vo) throws Exception {
        ScreUserVO resultVO = screUserDAO.selectScreUser(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * scre_user 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return scre_user 목록
	 * @exception Exception
	 */
    public List<?> selectScreUserList(ScreUserDefaultVO searchVO) throws Exception {
        return screUserDAO.selectScreUserList(searchVO);
    }

    /**
	 * scre_user 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return scre_user 총 갯수
	 * @exception
	 */
    public int selectScreUserListTotCnt(ScreUserDefaultVO searchVO) {
		return screUserDAO.selectScreUserListTotCnt(searchVO);
	}
    
}
