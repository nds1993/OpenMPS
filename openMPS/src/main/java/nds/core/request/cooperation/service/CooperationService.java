package nds.core.request.cooperation.service;

import java.util.List;

import nds.core.common.common.service.Service;

/**
 * <p>Title: CooperationService</p>
 * <p>Description: Service Implements Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2015.07.30 초기작성(김세희)</pre>
 * @author <a href="mailto:shkim@nds.co.kr">김세희</a>
 * @version 1.0
 */
public interface CooperationService extends Service{


	/**
     * 의견/검토 카운트 조회
     * 
     * @param cooperationVO
     * @return int
     * @throws Exception
     */
    int selectCooListCount(CooperationVO cooperationVO) throws Exception;
    
    /**
     *의견/검토 조회
     * 
     * @param cooperationVO
     * @return List
     * @throws Exception
     */
    List<CooperationVO> selectCooList(CooperationVO cooperationVO) throws Exception;
    
    /**
     *의견/검토 VOC 상세조회
     * 
     * @param cooperationVO
     * @return CooperationVO
     * @throws Exception
     */
    CooperationVO selectReqVoc(CooperationVO cooperationVO) throws Exception;
    /**
     *의견/검토 상세조회
     * 
     * @param cooperationVO
     * @return CooperationVO
     * @throws Exception
     */
    CooperationVO selectReq(CooperationVO cooperationVO) throws Exception;
    /**
     *의견/검토 임시저장
     * 
     * @param cooperationVO
     * @return void
     * @throws Exception
     */
    void updateTemVoc(CooperationVO cooperationVO) throws Exception;
    /**
     *의견/검토 검토저장
     * 
     * @param cooperationVO
     * @return void
     * @throws Exception
     */
    void updateReqVoc(CooperationVO cooperationVO) throws Exception;
      
}
