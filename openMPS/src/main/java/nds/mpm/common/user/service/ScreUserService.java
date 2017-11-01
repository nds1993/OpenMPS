package nds.mpm.common.user.service;

import java.util.List;
import nds.mpm.common.user.vo.ScreUserDefaultVO;
import nds.mpm.common.user.vo.ScreUserVO;

/**
 * @Class Name : ScreUserService.java
 * @Description : ScreUser Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ScreUserService {
	
	/**
	 * scre_user을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ScreUserVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertScreUser(ScreUserVO vo) throws Exception;
    
    /**
	 * scre_user을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ScreUserVO
	 * @return void형
	 * @exception Exception
	 */
    void updateScreUser(ScreUserVO vo) throws Exception;
    
    /**
	 * scre_user을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ScreUserVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteScreUser(ScreUserVO vo) throws Exception;
    
    /**
	 * scre_user을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ScreUserVO
	 * @return 조회한 scre_user
	 * @exception Exception
	 */
    ScreUserVO selectScreUser(ScreUserVO vo) throws Exception;
    
    /**
	 * scre_user 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return scre_user 목록
	 * @exception Exception
	 */
    List selectScreUserList(ScreUserDefaultVO searchVO) throws Exception;
    
    /**
	 * scre_user 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return scre_user 총 갯수
	 * @exception
	 */
    int selectScreUserListTotCnt(ScreUserDefaultVO searchVO);
    
}
