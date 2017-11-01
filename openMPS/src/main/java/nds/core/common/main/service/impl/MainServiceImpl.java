package nds.core.common.main.service.impl;




import java.util.List;

import javax.annotation.Resource;

import nds.core.common.main.service.MaNeedsWidgetsVO;
import nds.core.common.main.service.MainService;
import nds.core.common.main.service.MainVO;
import nds.core.common.main.service.NeedsCompByMonVO;
import nds.core.common.main.service.NeedsCountVO;
import nds.core.common.main.service.NeedsStatsVO;
import nds.core.operation.notice.service.NoticeVO;
import nds.core.operation.notice.service.impl.NoticeDAO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : MainServiceImpl </b>
 * <b>Class Description</b><br>
 * 메인화면을 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("mainService")
public class MainServiceImpl extends AbstractServiceImpl implements MainService {

	@Resource(name="mainDAO")
    private MainDAO mainDAO;
	@Resource(name="noticeDAO")
    private NoticeDAO noticeDAO;

    /**
     * 메인위젯 Count
     * 
     * @param needsStatsVO
     * @return Int
     * @throws Exception
     */
    public int selectWidgetsListCount(String needsStatsVO) throws Exception {
        int cnt = 0;
        try{
            cnt = mainDAO.selectWidgetsListCount(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectWidgetsListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"메인위젯 Count 조회중",ex.getMessage()} 
            );
        }         

        return cnt;
    }
    
    

    /**
     * 메인위젯 목록
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<MainVO> selectWidgetsList(String needsStatsVO) throws Exception {
        List<MainVO> list = null;
        try{
            list = mainDAO.selectWidgetsList(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectWidgetsList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"메인위젯 목록 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }

    /**
     * widget count
     * 
     * @param mainVO
     * @return int
     * @throws Exception
     */
    public int selectWidgetsCount(MainVO mainVO) throws Exception {
        int resultCnt = 0;
        try{
            resultCnt = mainDAO.selectWidgetsCount(mainVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectWidgetsCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"위젯 count 조회",ex.getMessage()} 
            );
        }         

        return resultCnt;
    }
    
    /**
     * 위젯 정보
     * 
     * @param mainVO
     * @return MainVO
     * @throws Exception
     */
    public MainVO selectWidgetsInfo(MainVO mainVO) throws Exception {
    	MainVO resutlVO = null;
        try{
            resutlVO = mainDAO.selectWidgetsInfo(mainVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectWidgetsInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"위젯정보 조회",ex.getMessage()} 
            );
        }         

        return resutlVO;
    }
    
    /**
     * voc 현황 chart 부서별
     * 
     * @param needsCountVO
     * @return List
     * @throws Exception
     */
    public List<NeedsCountVO> selectNeedsCountByDep(NeedsCountVO needsCountVO) throws Exception {
        List<NeedsCountVO> list = null;
        try{
            list = mainDAO.selectNeedsCountByDep(needsCountVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsCountByDep() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"voc 현황 chart 부서별조회",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * voc 현황 chart 개인별
     * 
     * @param needsCountVO
     * @return List
     * @throws Exception
     */
    public List<NeedsCountVO> selectNeedsCountByUsr(NeedsCountVO needsCountVO) throws Exception {
        List<NeedsCountVO> list = null;
        try{
            list = mainDAO.selectNeedsCountByUsr(needsCountVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsCountByUsr() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"voc 현황 chart 개인별조회",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 주요VOC 리스트
     * 
     * @param maNeedsWidgetsVO
     * @return List
     * @throws Exception
     */
    public List<MaNeedsWidgetsVO> selectMaWidgetsNeedsList(MaNeedsWidgetsVO maNeedsWidgetsVO) throws Exception {
        List<MaNeedsWidgetsVO> list = null;
        try{
            list = mainDAO.selectMaWidgetsNeedsList(maNeedsWidgetsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMaWidgetsNeedsList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"주요VOC 리스트조회",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 해당년도 월별 처리건수
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsCompByMonVO> selectNeedsCompByNowYear(NeedsCompByMonVO needsCompByMonVO) throws Exception{
        List<NeedsCompByMonVO> list = null;
        try{
            list = mainDAO.selectNeedsCompByNowYear(needsCompByMonVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsCompByNowYear() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"해당년도 월별 처리건수 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 월전체VOC처리건수 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectByCmplCountMonthAll(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{
            list = mainDAO.selectByCmplCountMonthAll(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectByCmplCountMonthAll() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"해당년 월전체VOC처리건수 조회",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 고객니즈별 통계현황 월
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectNeedsByNeedsTypeStatsListYear(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{
            list = mainDAO.selectNeedsByNeedsTypeStatsListYear(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsByNeedsTypeStatsListMonth() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객니즈별 통계현황 년도 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 고객니즈별 통계현황 월
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectNeedsByNeedsTypeStatsListMonth(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{
            list = mainDAO.selectNeedsByNeedsTypeStatsListMonth(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsByNeedsTypeStatsListMonth() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객니즈별 통계현황 년도 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 고객 성별 통계현황 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectNeedsByGenStatsListYear(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{
            list = mainDAO.selectNeedsByGenStatsListYear(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsByGenStatsListYear() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객 성별 통계현황 년도 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    /**
     * 고객 성별 통계현황 월
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectNeedsByGenStatsListMonth(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{
            list = mainDAO.selectNeedsByGenStatsListMonth(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsByGenStatsListMonth() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객 성별 통계현황 월 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 전년대비 기준 Count
     * 
     * @param needsStatsVO
     * @return int
     * @throws Exception
     */
    public int selectGraphRang3Count(NeedsStatsVO needsStatsVO) throws Exception{
        int cnt = 0;
        try{
            cnt = mainDAO.selectGraphRang3Count(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectGraphRang3Count() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"전년대비 기준 Count 조회중",ex.getMessage()} 
            );
        }         

        return cnt;
    }
    
    /**
     * 전월대비 기준 Count
     * 
     * @param needsStatsVO
     * @return int
     * @throws Exception
     */
    public int selectGraphRang4Count(NeedsStatsVO needsStatsVO) throws Exception{
        int cnt = 0;
        try{
            cnt = mainDAO.selectGraphRang4Count(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectGraphRang4Count() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"전월대비 기준 Count 조회중",ex.getMessage()} 
            );
        }         

        return cnt;
    }
    
    /**
     * 발생원인별 통계현황 Count
     * 
     * @param needsStatsVO
     * @return int
     * @throws Exception
     */
    public int selectNeedsByGenCausStatsCountYear(NeedsStatsVO needsStatsVO) throws Exception{
        int cnt = 0;
        try{
            cnt = mainDAO.selectNeedsByGenCausStatsCountYear(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsByGenCausStatsCountYear() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"발생원인별 통계현황 Count조회중",ex.getMessage()} 
            );
        }         

        return cnt;
    }
    
    /**
     * 발생원인별 통계현황 Count
     * 
     * @param needsStatsVO
     * @return int
     * @throws Exception
     */
    public int selectNeedsByGenCausStatsCountMonth(NeedsStatsVO needsStatsVO) throws Exception{
        int cnt = 0;
        try{
            cnt = mainDAO.selectNeedsByGenCausStatsCountMonth(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsByGenCausStatsCountMonth() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"발생원인별 통계현황 Count 조회중",ex.getMessage()} 
            );
        }         

        return cnt;
    }
    
    /**
     * 고객연령별 통계현황 월
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectNeedsByAgeStatsListMonth(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{

            list = mainDAO.selectNeedsByAgeStatsListMonth(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsByAgeStatsListMonth() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객연령별 통계현황 년도 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 고객연령별 통계현황 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectNeedsByAgeStatsListYear(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{

            list = mainDAO.selectNeedsByAgeStatsListYear(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsByAgeStatsListYear() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객연령별 통계현황 년도 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 고객지역별 통계현황 월
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectNeedsByCstCounStatsListMonth(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{
            list = mainDAO.selectNeedsByCstCounStatsListMonth(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsByCstCounStatsListMonth() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객지역별 통계현황 년도 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 고객지역별 통계현황 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectNeedsByCstCounStatsListYear(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{
            list = mainDAO.selectNeedsByCstCounStatsListYear(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsByCstCounStatsListYear() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객지역별 통계현황 년도 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 접수채널별 통계현황 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectNeedsByRegChnlStatsListYear(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{
            list = mainDAO.selectNeedsByRegChnlStatsListYear(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsByRegChnlStatsListYear() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"접수채널별 통계현황 년도 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 발생원인별 통계현황 월
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectNeedsByGenCausStatsListMonth(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{
            list = mainDAO.selectNeedsByGenCausStatsListMonth(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsByGenCausStatsListMonth() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"발생원인별 통계현황 월 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 발생원인별 통계현황 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectNeedsByGenCausStatsListYear(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{
            list = mainDAO.selectNeedsByGenCausStatsListYear(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsByGenCausStatsListYear() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"발생원인별 통계현황 년도 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 처리기간별 통계현황 월
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectDealTmStatsListMonth(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{
            list = mainDAO.selectDealTmStatsListMonth(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDealTmStatsListMonth() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"처리기간별 통계현황 년도 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 처리기간별 통계현황 년도
     * 
     * @param needsStatsVO
     * @return List
     * @throws Exception
     */
    public List<NeedsStatsVO> selectDealTmStatsListYear(NeedsStatsVO needsStatsVO) throws Exception{
        List<NeedsStatsVO> list = null;
        try{
            list = mainDAO.selectDealTmStatsListYear(needsStatsVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDealTmStatsListYear() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"처리기간별 통계현황 년도 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 공지사항 조회
     * 
     * @param noticeVO
     * @return List
     * @throws Exception
     */
    public List<NoticeVO> selectDocuList(NoticeVO noticeVO) throws Exception{
        List<NoticeVO> list = null;
        try{
            list = noticeDAO.selectByHelper(noticeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDocuList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"게시물 조회",ex.getMessage()} 
            );
        }       

        return list;
    }

}
