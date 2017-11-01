package nds.core.common.mainchart.service.impl;




import java.util.List;

import javax.annotation.Resource;

import nds.core.common.mainchart.service.MainChartService;
import nds.core.common.mainchart.service.MainChartVO;
import nds.core.operation.notice.service.NoticeVO;
import nds.core.operation.notice.service.impl.NoticeDAO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : MainChartServiceImpl </b>
 * <b>Class Description</b><br>
 * 메인화면을 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("mainChartService")
public class MainChartServiceImpl extends AbstractServiceImpl implements MainChartService {

	@Resource(name="mainChartDAO")
    private MainChartDAO mainChartDAO;
	@Resource(name="noticeDAO")
    private NoticeDAO noticeDAO;

    /**
     * widget count
     * 
     * @param mainChartVO
     * @return int
     * @throws Exception
     */
    public int selectWidgetsCount(MainChartVO mainChartVO) throws Exception {
        int resultCnt = 0;
        try{
            resultCnt = mainChartDAO.selectWidgetsCount(mainChartVO);
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
     * @param mainChartVO
     * @return MainChartVO
     * @throws Exception
     */
    public MainChartVO selectWidgetsInfo(MainChartVO mainChartVO) throws Exception {
    	MainChartVO resutlVO = null;
        try{
            resutlVO = mainChartDAO.selectWidgetsInfo(mainChartVO);
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
     * voc 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart001(MainChartVO mainChartVO) throws Exception {
        List<MainChartVO> list = null;
        try{
            list = mainChartDAO.selectMainChart001(mainChartVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart001() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"voc 현황",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 당월 VOC 처리 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart003(MainChartVO mainChartVO) throws Exception {
        List<MainChartVO> list = null;
        try{
            list = mainChartDAO.selectMainChart003(mainChartVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart003() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"당월 VOC 처리건수",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 니즈별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart005(MainChartVO mainChartVO) throws Exception {
        List<MainChartVO> list = null;
        try{
            list = mainChartDAO.selectMainChart005(mainChartVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart005() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"니즈별 처리현황",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 성별 처리현황
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart006(MainChartVO mainChartVO) throws Exception{
        List<MainChartVO> list = null;
        try{
            list = mainChartDAO.selectMainChart006(mainChartVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart006() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"성별 처리현황",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 연령별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart007(MainChartVO mainChartVO) throws Exception{
        List<MainChartVO> list = null;
        try{
            list = mainChartDAO.selectMainChart007(mainChartVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart007() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"연령별 처리현황",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 지역별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart008(MainChartVO mainChartVO) throws Exception{
        List<MainChartVO> list = null;
        try{
            list = mainChartDAO.selectMainChart008(mainChartVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart008() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"지역별 처리현황",ex.getMessage()} 
            );
        }         

        return list;
    }
  
    
    /**
     * 채널별처리 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart009(MainChartVO mainChartVO) throws Exception {
        List<MainChartVO> list = null;
        try {
            list = mainChartDAO.selectMainChart009(mainChartVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart009() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"채널별처리 현황",ex.getMessage()} 
            );
        }
        return list;
    }
    
    /**
     * 월별 처리건수
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart010(MainChartVO mainChartVO) throws Exception {
        List<MainChartVO> list = null;
        try {
            list = mainChartDAO.selectMainChart010(mainChartVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart010() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"월별 처리건수",ex.getMessage()} 
            );
        }
        return list;
    }
    
    /**
     * 발생원인별 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart011(MainChartVO mainChartVO) throws Exception {
        List<MainChartVO> list = null;
        try {
            list = mainChartDAO.selectMainChart011(mainChartVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart011() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"발생원인별 현황",ex.getMessage()} 
            );
        }
        return list;
    }
    
    /**
     * 처리시간별 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart012(MainChartVO mainChartVO) throws Exception {
        List<MainChartVO> list = null;
        try {
            list = mainChartDAO.selectMainChart012(mainChartVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart012() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"처리시간별 현황",ex.getMessage()} 
            );
        }
        return list;
    }
    
    /**
     * 이용매체별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart013(MainChartVO mainChartVO) throws Exception {
        List<MainChartVO> list = null;
        try {
            list = mainChartDAO.selectMainChart013(mainChartVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart013() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"이용매체별 처리현황",ex.getMessage()} 
            );
        }
        return list;
    }
    

    /**
     * 성격별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart014(MainChartVO mainChartVO) throws Exception {
        List<MainChartVO> list = null;
        try {
            list = mainChartDAO.selectMainChart014(mainChartVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart014() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"성격별 처리현황",ex.getMessage()} 
            );
        }
        return list;
    }

    /**
     * 업무유형별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart015(MainChartVO mainChartVO) throws Exception {
        List<MainChartVO> list = null;
        try {
            list = mainChartDAO.selectMainChart015(mainChartVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart015() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"업무유형별 처리현황",ex.getMessage()} 
            );
        }
        return list;
    }
    
    /**
     * [제안] 업무유형 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart016(MainChartVO mainChartVO) throws Exception {
        List<MainChartVO> list = null;
        try {
            list = mainChartDAO.selectMainChart016(mainChartVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart016() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"[제안] 업무유형 현황",ex.getMessage()} 
            );
        }
        return list;
    }
    
    /**
     * [제안] 처리부서별 처리결과 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart017(MainChartVO mainChartVO) throws Exception {
        List<MainChartVO> list = null;
        try {
            list = mainChartDAO.selectMainChart017(mainChartVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart017() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"[제안] 처리부서별 처리결과 현황",ex.getMessage()} 
            );
        }
        return list;
    }

    /**
     * [제안] 처리부서별 진척도 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    public List<MainChartVO> selectMainChart018(MainChartVO mainChartVO) throws Exception {
        List<MainChartVO> list = null;
        try {
            list = mainChartDAO.selectMainChart018(mainChartVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMainChart018() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"[제안] 처리부서별 진척도 현황",ex.getMessage()} 
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
	public List<NoticeVO> selectMainChart004(NoticeVO noticeVO) throws Exception{
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
