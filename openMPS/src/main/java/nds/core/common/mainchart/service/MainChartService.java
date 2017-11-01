package nds.core.common.mainchart.service;


import java.util.List;

import nds.core.common.common.service.Service;
import nds.core.operation.notice.service.NoticeVO;
import nds.frm.exception.ExceptionHelper;


/**
 * <b>class : MainChartService </b>
 * <b>Class Description</b><br>
 * 메인화면을 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface MainChartService extends Service {
    
    /**
     * widget count
     * 
     * @param mainChartVO
     * @return int
     * @throws Exception
     */
    int selectWidgetsCount(MainChartVO mainChartVO) throws Exception;

    /**
     * widget 정보 조회
     * 
     * @param mainChartVO
     * @return MainVO
     * @throws Exception
     */
    MainChartVO selectWidgetsInfo(MainChartVO mainChartVO) throws Exception;
    
    /**
     * voc 현황 chart 
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart001(MainChartVO mainChartVO) throws Exception;
    
    /**
     * 당월 VOC 처리 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart003(MainChartVO mainChartVO) throws Exception;
    
    /**
     * 니즈별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart005(MainChartVO mainChartVO) throws Exception;
    
    /**
     * 성별 처리현황
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart006(MainChartVO mainChartVO) throws Exception;
    
    /**
     * 연령별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart007(MainChartVO mainChartVO) throws Exception;
    
    /**
     * 지역별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart008(MainChartVO mainChartVO) throws Exception;

    
    /**
     * 채널별처리 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart009(MainChartVO mainChartVO) throws Exception;
    
    /**
     * 월별 처리건수
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart010(MainChartVO mainChartVO) throws Exception;
    
    /**
     * 발생원인별 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart011(MainChartVO mainChartVO) throws Exception;
    
    /**
     * 처리시간별 통계현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart012(MainChartVO mainChartVO) throws Exception;
    

    /**
     * 이용매체별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart013(MainChartVO mainChartVO) throws Exception;
    

    /**
     * 성격별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart014(MainChartVO mainChartVO) throws Exception;
    
    /**
     * 업무유형별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart015(MainChartVO mainChartVO) throws Exception;
    
    /**
     * [제안] 업무유형 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart016(MainChartVO mainChartVO) throws Exception;
    
    /**
     * [제안] 처리부서별 처리결과 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart017(MainChartVO mainChartVO) throws Exception;
    
    /**
     * [제안] 처리부서별 진척도 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    List<MainChartVO> selectMainChart018(MainChartVO mainChartVO) throws Exception;
    
    /**
     * 공지사항 조회
     * 
     * @param noticeVO
     * @return List
     * @throws Exception
     */
    List<NoticeVO> selectMainChart004(NoticeVO noticeVO) throws Exception;
 
}