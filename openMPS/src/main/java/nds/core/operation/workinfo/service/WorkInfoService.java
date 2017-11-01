package nds.core.operation.workinfo.service;



import java.util.List;

import nds.core.common.common.service.Service;



/**
 * <p>Title: WorkInfoService</p>
 * <p>Description: Service Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.26 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
public interface WorkInfoService extends Service {

    /**
     * 담당업무정보 조회
     * @param workInfoVO
     * @return List
     * @throws Exception
     */
    List<WorkInfoVO> selectUsrWrkInfo(WorkInfoVO workInfoVO) throws Exception;

    /**
     * 담당업무정보 조회 총건수
     * @param workInfoVO
     * @return int
     * @throws Exception
     */
    int selectUsrWrkInfoCount(WorkInfoVO workInfoVO) throws Exception;

    /**
     * 담당업무정보 상세조회
     * @param workInfoVO
     * @return WorkInfoVO
     * @throws Exception
     */
    WorkInfoVO selectUsrWrkInfoDetail(WorkInfoVO workInfoVO) throws Exception;
    
    /**
     * 담당업무정보 업무정보 등록
     * @param workInfoVO
     * @return void
     * @throws Exception
     */
    void updateUsrWrkInfo(WorkInfoVO workInfoVO) throws Exception;
}
