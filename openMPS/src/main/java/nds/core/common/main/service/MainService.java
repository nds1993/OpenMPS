package nds.core.common.main.service;


import java.util.List;

import nds.core.common.common.service.Service;
import nds.core.operation.notice.service.NoticeVO;


/**
 * <b>class : MainService </b>
 * <b>Class Description</b><br>
 * 메인화면을 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface MainService extends Service {
    
    /**
     * 메인위젯 목록
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    int selectWidgetsListCount(String needsStatsVO) throws Exception;
    
    /**
     * 메인위젯 목록
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<MainVO> selectWidgetsList(String needsStatsVO) throws Exception;
    
    /**
     * widget count
     * 
     * @param needsStatsVO
     * @return int
     * @throws Exception
     */
    int selectWidgetsCount(MainVO mainVO) throws Exception;

    /**
     * widget 정보 조회
     * 
     * @param needsStatsVO
     * @return MainVO
     * @throws Exception
     */
    MainVO selectWidgetsInfo(MainVO mainVO) throws Exception;
    
    /**
     * voc 현황 chart 개인별
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsCountVO> selectNeedsCountByUsr(NeedsCountVO needsCountVO) throws Exception;

    /**
     * voc 현황 chart 부서별
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsCountVO> selectNeedsCountByDep(NeedsCountVO needsCountVO) throws Exception;
    
    /**
     * 주요VOC 리스트
     * 
     * @param maNeedsWidgetsVO
     * @return List
     * @throws Exception
     */
    List<MaNeedsWidgetsVO> selectMaWidgetsNeedsList(MaNeedsWidgetsVO maNeedsWidgetsVO) throws Exception;
    
    /**
     * 해당년도 월별 처리건수
     * 
     * @param needsCompByMonVO
     * @return List
     * @throws Exception
     */
    List<NeedsCompByMonVO> selectNeedsCompByNowYear(NeedsCompByMonVO needsCompByMonVO) throws Exception;
    
    /**
     * 전년대비 기준 Count
     * 
     * @param needsStatsVO
     * @return int
     * @throws Exception
     */
    int selectGraphRang3Count(NeedsStatsVO needsStatsVO) throws Exception;
    /**
     * 월전체VOC처리건수 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectByCmplCountMonthAll(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 고객니즈별 통계현황 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectNeedsByNeedsTypeStatsListYear(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 고객니즈별 통계현황 월
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectNeedsByNeedsTypeStatsListMonth(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 전월대비 기준 Count
     * 
     * @param needsStatsVO
     * @return int
     * @throws Exception
     */
    int selectGraphRang4Count(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 발생원인별 통계현황 Count
     * 
     * @param needsStatsVO
     * @return int
     * @throws Exception
     */
    int selectNeedsByGenCausStatsCountYear(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 발생원인별 통계현황 Count
     * 
     * @param needsStatsVO
     * @return int
     * @throws Exception
     */
    int selectNeedsByGenCausStatsCountMonth(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 고객 성별 통계현황 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectNeedsByGenStatsListYear(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 고객 성별 통계현황 월
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectNeedsByGenStatsListMonth(NeedsStatsVO needsStatsVO) throws Exception;
    
    
    /**
     * 고객연령별 통계현황 월
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectNeedsByAgeStatsListMonth(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 고객연령별 통계현황 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectNeedsByAgeStatsListYear(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 고객지역별 통계현황 월
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectNeedsByCstCounStatsListMonth(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 고객지역별 통계현황 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectNeedsByCstCounStatsListYear(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 접수채널별 통계현황 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectNeedsByRegChnlStatsListYear(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 발생원인별 통계현황 월
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectNeedsByGenCausStatsListMonth(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 발생원인별 통계현황 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectNeedsByGenCausStatsListYear(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 처리기간별 통계현황 월
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectDealTmStatsListMonth(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 처리기간별 통계현황 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    List<NeedsStatsVO> selectDealTmStatsListYear(NeedsStatsVO needsStatsVO) throws Exception;
    
    /**
     * 공지사항 조회
     * 
     * @param noticeVO
     * @return List
     * @throws Exception
     */
    List<NoticeVO> selectDocuList(NoticeVO noticeVO) throws Exception;
}