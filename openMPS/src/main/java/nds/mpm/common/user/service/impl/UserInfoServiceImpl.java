package nds.mpm.common.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.common.user.service.UserInfoService;
import nds.mpm.common.user.vo.UserInfoDefaultVO;
import nds.mpm.common.user.vo.UserInfoVO;
import nds.mpm.common.user.service.UserInfoDAO;

/**
 * @Class Name : UserInfoServiceImpl.java
 * @Description : UserInfo Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("userInfoService")
public class UserInfoServiceImpl extends EgovAbstractServiceImpl implements
        UserInfoService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Resource(name="userInfoDAO")
    private UserInfoDAO userInfoDAO;
    
    /** ID Generation */
    //@Resource(name="{egovUserInfoIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * USER_INFO을 등록한다.
	 * @param vo - 등록할 정보가 담긴 UserInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertUserInfo(UserInfoVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	userInfoDAO.insertUserInfo(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * USER_INFO을 수정한다.
	 * @param vo - 수정할 정보가 담긴 UserInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateUserInfo(UserInfoVO vo) throws Exception {
        userInfoDAO.updateUserInfo(vo);
    }

    /**
	 * USER_INFO을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 UserInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteUserInfo(UserInfoVO vo) throws Exception {
        userInfoDAO.deleteUserInfo(vo);
    }

    /**
	 * USER_INFO을 조회한다.
	 * @param vo - 조회할 정보가 담긴 UserInfoVO
	 * @return 조회한 USER_INFO
	 * @exception Exception
	 */
    public UserInfoVO selectUserInfo(UserInfoVO vo) throws Exception {
        UserInfoVO resultVO = userInfoDAO.selectUserInfo(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * USER_INFO 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USER_INFO 목록
	 * @exception Exception
	 */
    public List<?> selectUserInfoList(UserInfoDefaultVO searchVO) throws Exception {
        return userInfoDAO.selectUserInfoList(searchVO);
    }

    /**
	 * USER_INFO 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USER_INFO 총 갯수
	 * @exception
	 */
    public int selectUserInfoListTotCnt(UserInfoDefaultVO searchVO) {
		return userInfoDAO.selectUserInfoListTotCnt(searchVO);
	}
    
}
