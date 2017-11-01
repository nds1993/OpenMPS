package nds.core.userdep.chmanager.service;



import java.util.List;

import nds.core.common.common.service.Service;
import nds.core.userdep.roleuser.service.RoleUserVO;


/**
 * <b>class : DepartMentService </b>
 * <b>Class Description</b><br>
 * 채널관리자 관리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2015.05.12 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
public interface ChManagerService extends Service {
    
    /**
     * 채널관리자 등록
     * @param chManagerVO
     * @throws Exception
     */
    void insertChnlManager(ChManagerVO chManagerVO) throws Exception;
    
    /**
     * 채널관리자 삭제
     * @param chManagerVO
     * @throws Exception
     */
    void deleteChnlManager(ChManagerVO chManagerVO) throws Exception;
	
    /**
     * 채널 조회
     * @param chManagerVO
     * @return List
     * @throws Exception
     */
    List selectChnlList(ChManagerVO chManagerVO) throws Exception;

    /**
     * 채널관리자 조회
     * @param chManagerVO
     * @return List
     * @throws Exception
     */
    List selectChnlManagerList(ChManagerVO chManagerVO) throws Exception;

    /**
     * 채널 정부 건수 조회
     * @param chManagerVO
     * @return int
     * @throws Exception
     */
    int selectChnlMstCount(ChManagerVO chManagerVO) throws Exception;

    /**
     * 채널 사용자 건수 조회
     * @param chManagerVO
     * @return int
     * @throws Exception
     */
    int selectChnlUserCount(ChManagerVO chManagerVO) throws Exception;
}
