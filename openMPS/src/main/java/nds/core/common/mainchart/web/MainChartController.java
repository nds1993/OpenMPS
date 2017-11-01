package nds.core.common.mainchart.web;

import java.io.BufferedOutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.AttachfileService;
import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.common.mainchart.service.MainChartService;
import nds.core.common.mainchart.service.MainChartVO;
import nds.core.operation.notice.service.NoticeVO;
import nds.frm.config.StaticConfig;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import sun.misc.BASE64Decoder;

/**
 * <p>Title: MainChartController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.26 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Controller
public class MainChartController extends BaseController {
	
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
    /** MainService */
    @Resource(name = "mainChartService")
    private MainChartService mainChartService;
	/** AttachfileService */
    @Resource(name = "attachfileService")
    private AttachfileService attachfileService;
    
    /**
     * VOC 현황
     * @param request
     * @param reponse
     */
    @RequestMapping("/common/mainchart/mainchart001.do")
    public ModelAndView mainchart001(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userEmpno = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
          
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart001");

	    MainChartVO mainChartVO = new MainChartVO();
	    mainChartVO.setUserId(userEmpno);
	    mainChartVO.setWidgetsId("001");
	
	    MainChartVO resultVO = new MainChartVO();
	          
	    int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
          
	    if(widgetCnt != 0){
	    	resultVO = mainChartService.selectWidgetsInfo(mainChartVO);
	    }
	    else { 
		    //위젯 정보가 없을경우 디폴트 셋팅 : 해당년기준, 부서
		    resultVO.setGraphBaseCd("02");
		    resultVO.setGraphRang("1");
		    resultVO.setWidgetsId("001");
	    }
	    resultVO.setWidgetsId("001");
	          
	    mav.addObject("resultTitle", resultVO);
	          
	    MainChartVO chartInfo = new MainChartVO();
	    
	    if(resultVO.getGraphBaseCd().equals("02")){ 
	    	//부서별
	    	chartInfo.setDepCd(userDepCd);
	    }
	    else if(resultVO.getGraphBaseCd().equals("03")){ 
	    	//개인별
	    	chartInfo.setUserId(userEmpno);
	    }
	          
	    List<MainChartVO> list = null;

	    list = mainChartService.selectMainChart001(chartInfo);
	    
	    int size = 0;
		for(int i = 0; i < list.size();i++){
			if(!list.get(i).getNeedsTotCnt().equals("0")) size++;
		}
  
		String chartResult = "";
		//추가
    	if(size != 0){
	      	for(int i = 0; i < list.size(); i++){
	          	if(i == list.size() - 1){
	          		chartResult += "['" + list.get(i).getNeedsTypeNm() + "', " + list.get(i).getNeedsTotCnt() + ", "+ list.get(i).getNeedsCnt() + ", " + list.get(i).getNeedsNotCnt()+"]";
	          	}
	          	else{
	          		chartResult += "['" + list.get(i).getNeedsTypeNm() + "', " + list.get(i).getNeedsTotCnt() + ", "+ list.get(i).getNeedsCnt() + ", " + list.get(i).getNeedsNotCnt()+"], ";
	      		}
	      	}
		}
    	else{
    		list = new ArrayList<MainChartVO>();
    		chartResult = "['없음', 100]";
	  	}
  
    	mav.addObject("F205", this.getCommonCode("F205"));
  		mav.addObject("result", list);
  		mav.addObject("chartResult", chartResult);
  		return mav;
	}
      
    /**
     * 이슈 VOC
     * @param request
     * @param reponse
     */
    @RequestMapping("/common/mainchart/mainchart002.do")
    public ModelAndView mainchart002(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		ModelAndView mav = new ModelAndView("/common/mainchart/mainchart002");
		
 
		return mav;
    }
    
    /**
     * 당월 VOC 처리 현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    @RequestMapping("/common/mainchart/mainchart003.do")
    public ModelAndView mainchart003(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        //현재날짜
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String today = formatter.format(new java.util.Date());
        
        int year = Integer.parseInt(today.substring(0, 4));
        int beforYear = year-1;
        int month = Integer.parseInt(today.substring(4,6));
        int beforMonth = month-1;
        if(beforMonth == 0){
            beforMonth = 12;
        }
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userId = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
        
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart003");
        
        MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("003");

        MainChartVO resultVO = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultVO = mainChartService.selectWidgetsInfo(mainChartVO);
        }
        else { 
            //위젯 정보가 없을경우 디폴트 셋팅 : 전월대비, 부서
            resultVO.setGraphBaseCd("02");
            resultVO.setGraphRang("4");
            resultVO.setWidgetsId("003");
        }
//        mav.addObject("resultTitle", resultVO);

        
        MainChartVO chartInfo = new MainChartVO();
        
        if(resultVO.getGraphBaseCd().equals("02")){ 
            //부서별
        	chartInfo.setDepCd(userDepCd);
        }else if(resultVO.getGraphBaseCd().equals("03")){ 
            //개인별
        	chartInfo.setUserId(userId);
        }
        
        chartInfo.setGraphRang(resultVO.getGraphRang());
        
        List<MainChartVO> list = null;
        list = mainChartService.selectMainChart003(chartInfo);
        
        String chartResult1 = "";
        String chartResult2 = "";
        String chartResultNm = "";
        if(list.size() > 0) {
            for(int j = 0; j < list.size(); j++){
            	chartResult1 = chartResult1+list.get(j).getData1();   
            	chartResult2 = chartResult2+list.get(j).getData2();   
            	chartResultNm = chartResultNm+"'"+list.get(j).getValueNm()+"'";
            	if(j != list.size()-1) {
            		chartResult1 += ",";
            		chartResult2 += ",";
            		chartResultNm += ",";
    			}
            }
        }
        else{
            chartResult1 = "0";
            chartResult2 = "0";
        }
        
        if("3".equals(chartInfo.getGraphRang())) {
        	mav.addObject("legendNm", "'" + beforYear + "년"+ month + "월','" + year + "년" + month + "월'");
        } else if("4".equals(chartInfo.getGraphRang())) {
        	mav.addObject("legendNm", "'" + beforMonth + "월','" + month + "월'");
        }
        
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("resultTitle", resultVO);
        mav.addObject("chartResult1", chartResult1);
        mav.addObject("chartResult2", chartResult2);
        mav.addObject("chartResultNm", chartResultNm);
        
        return mav;
    }
    
    /**
     * 공지사항
     * @param request
     * @param reponse
     */
    @RequestMapping("/common/mainchart/mainchart004.do")
    public ModelAndView mainchart004(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        ModelAndView mav = new ModelAndView("common/mainchart/mainchart004");

        NoticeVO tbvcblbdkey = new NoticeVO();

        tbvcblbdkey.setDbrdCd(StaticConfig.VOC_BOARD_NOTICE);
        tbvcblbdkey.setStartNo(1);
        tbvcblbdkey.setEndNo(8);
        tbvcblbdkey.setDocNo("");
        tbvcblbdkey.setTit("");
        tbvcblbdkey.setSchStartDd("00000000000000");
        tbvcblbdkey.setSchEndDd("99999999999999");

        mav.addObject("result", mainChartService.selectMainChart004(tbvcblbdkey));
        return mav;
    }
    
    /**
     * 니즈별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    @RequestMapping("/common/mainchart/mainchart005.do")
    public ModelAndView mainchart005(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
    	//현재날짜
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    String today = formatter.format(new java.util.Date());
	    
	    int year = Integer.parseInt(today.substring(0, 4));
	    int beforYear = year-1;
	    int month = Integer.parseInt(today.substring(4,6));
	    int beforMonth = month-1;
	    if(beforMonth == 0){
	        beforMonth = 12;
	    }
	
	    UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
	    // 로그인 사용자정보 가져오기
	    String userId = userSession.getLogin().getUserEmpno();
	    String userDepCd = userSession.getLogin().getDepCd();
	
	    ModelAndView mav = new ModelAndView("/common/mainchart/mainchart005");
	    
	    MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("005");

        MainChartVO resultVO = new MainChartVO();	
	
	    int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
	
	    if(widgetCnt != 0){
	    	resultVO = mainChartService.selectWidgetsInfo(mainChartVO);
	    }else { 
	        //위젯 정보가 없을경우 디폴트 셋팅 : 전월대비, 부서
	    	resultVO.setGraphBaseCd("02");
	    	resultVO.setGraphRang("4");
	    	resultVO.setWidgetsId("005");
	    }
	    
	    MainChartVO chartInfo = new MainChartVO();
	    
	    if(resultVO.getGraphBaseCd().equals("02")){ 
	        //부서별
	    	chartInfo.setDepCd(userDepCd);
	    }else if(resultVO.getGraphBaseCd().equals("03")){
	        //개인별
	    	chartInfo.setUserId(userId);
	    }
	    resultVO.setGraphRang("4");
	    chartInfo.setGraphRang(resultVO.getGraphRang());
	
	    List<MainChartVO> list = null;
        list = mainChartService.selectMainChart005(chartInfo);
        
        String chartResult1 = "";
        String chartResult2 = "";
        String chartResultNm = "";
        
        if(list.size() > 0) {
            for(int j = 0; j < list.size(); j++){
            	chartResult1 = chartResult1+list.get(j).getData1();
            	chartResult2 = chartResult2+list.get(j).getData2();
            	chartResultNm = chartResultNm+"'"+list.get(j).getValueNm()+"'";
            	if(j != list.size()-1) {
            		chartResult1 += ",";
            		chartResult2 += ",";
            		chartResultNm += ",";
    			}
            }
        }
        else{
            chartResult1 = "0";
            chartResult2 = "0";
        }
        
        if("3".equals(resultVO.getGraphRang())) {
        	mav.addObject("legendNm", "'" + beforYear + "년"+ month + "월','" + year + "년" + month + "월'");
        } else if("4".equals(resultVO.getGraphRang())) {
        	mav.addObject("legendNm", "'" + beforMonth + "월','" + month + "월'");
        }
        
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("resultTitle", resultVO);
        mav.addObject("chartResult1", chartResult1);
        mav.addObject("chartResult2", chartResult2);
        mav.addObject("chartResultNm", chartResultNm);
        
        return mav;
    }
    
    /**
     * 성별 처리현황
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    @RequestMapping("/common/mainchart/mainchart006.do")
    public ModelAndView mainchart006(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
    	//현재날짜
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String today = formatter.format(new java.util.Date());
        
        int year = Integer.parseInt(today.substring(0, 4));
        int beforYear = year-1;
        int month = Integer.parseInt(today.substring(4,6));
        int beforMonth = month-1;
        if(beforMonth == 0){
            beforMonth = 12;
        }
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userId = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
        
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart006");

        MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("006");

        MainChartVO resultVO = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultVO = mainChartService.selectWidgetsInfo(mainChartVO);
        }
        else { 
            //위젯 정보가 없을경우 디폴트 셋팅 : 전월대비, 부서
            resultVO.setGraphBaseCd("02");
            resultVO.setGraphRang("4");
            resultVO.setWidgetsId("006");
        }

        MainChartVO chartInfo = new MainChartVO();
        
        if(resultVO.getGraphBaseCd().equals("02")){ 
            //부서별
        	chartInfo.setDepCd(userDepCd);
        }else if(resultVO.getGraphBaseCd().equals("03")){ 
            //개인별
        	chartInfo.setUserId(userId);
        }
        chartInfo.setGraphRang(resultVO.getGraphRang());
        

        List<MainChartVO> list = null;
        list = mainChartService.selectMainChart006(chartInfo);
        
        String chartResult1 = "";
        String chartResult2 = "";
        String chartResultNm = "";
        String chartResultCd = "";
        
        for(int i=0; i<list.size(); i++ ) {
        	MainChartVO result = list.get(i);
        	
        	chartResult1 = chartResult1 + result.getData1();
        	chartResult2 = chartResult2 + result.getData2();
        	chartResultNm = chartResultNm + "'" + result.getValueNm() + "'";
        	chartResultCd = chartResultCd + "'" + result.getValueCd() + "'";
        	
        	if(i != list.size()-1) {
        		chartResult1 += ",";
        		chartResult2 += ",";
        		chartResultNm += ",";
        		chartResultCd += ",";
			}
        }
        
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("chartResult1",chartResult1);
        mav.addObject("chartResult2",chartResult2);
        mav.addObject("chartResultNm",chartResultNm);
        mav.addObject("chartResultCd",chartResultCd);
        
        mav.addObject("resultTitle", resultVO);
        
        if("3".equals(resultVO.getGraphRang())) {
        	mav.addObject("legendNm", "'" + beforYear + "년','" + year + "년'");
        } else if("4".equals(resultVO.getGraphRang())) {
        	mav.addObject("legendNm", "'" + year + "년 " + beforMonth + "월','" + year + "년 " + month + "월'");
        }
	    
	    return mav;
    }
    
    /**
     * 연령별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    @RequestMapping("/common/mainchart/mainchart007.do")
    public ModelAndView mainchart007(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
    	//현재날짜
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String today = formatter.format(new java.util.Date());
        
        int year = Integer.parseInt(today.substring(0, 4));
        int beforYear = year-1;
        int month = Integer.parseInt(today.substring(4,6));
        int beforMonth = month-1;
        if(beforMonth == 0){
            beforMonth = 12;
        }
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userId = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
        
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart007");

        MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("007");

        MainChartVO resultVO = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultVO = mainChartService.selectWidgetsInfo(mainChartVO);
        }
        else { 
            //위젯 정보가 없을경우 디폴트 셋팅 : 전월대비, 부서
            resultVO.setGraphBaseCd("02");
            resultVO.setGraphRang("4");
            resultVO.setWidgetsId("007");
        }

        MainChartVO chartInfo = new MainChartVO();
        
        if(resultVO.getGraphBaseCd().equals("02")){ 
            //부서별
        	chartInfo.setDepCd(userDepCd);
        }else if(resultVO.getGraphBaseCd().equals("03")){ 
            //개인별
        	chartInfo.setUserId(userId);
        }
        chartInfo.setGraphRang(resultVO.getGraphRang());
        

        List<MainChartVO> list = null;
        list = mainChartService.selectMainChart007(chartInfo);
        
        String chartResult1 = "";
        String chartResult2 = "";
        String chartResultNm = "";
        
        for(int i=0; i<list.size(); i++ ) {
        	MainChartVO result = list.get(i);
        	
        	chartResult1 = chartResult1 + result.getData1();
        	chartResult2 = chartResult2 + result.getData2();
        	chartResultNm = chartResultNm + "'" + result.getValueNm() + "'";
        	
        	if(i != list.size()-1) {
        		chartResult1 += ",";
        		chartResult2 += ",";
        		chartResultNm += ",";
			}
        }
        
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("chartResult1",chartResult1);
        mav.addObject("chartResult2",chartResult2);
        mav.addObject("chartResultNm",chartResultNm);
        
        mav.addObject("resultTitle", resultVO);
        
        if("3".equals(resultVO.getGraphRang())) {
        	mav.addObject("legendNm", "'" + beforYear + "년','" + year + "년'");
        } else if("4".equals(resultVO.getGraphRang())) {
        	mav.addObject("legendNm", "'" + year + "년 " + beforMonth + "월','" + year + "년 " + month + "월'");
        }
	    
	    return mav;
    }
    
    /**
     * 지역별 처리현황
     * 
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    @RequestMapping("/common/mainchart/mainchart008.do")
    public ModelAndView mainchart008(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
    	//현재날짜
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String today = formatter.format(new java.util.Date());
        
        int year = Integer.parseInt(today.substring(0, 4));
        int beforYear = year-1;
        int month = Integer.parseInt(today.substring(4,6));
        int beforMonth = month-1;
        if(beforMonth == 0){
            beforMonth = 12;
        }
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userId = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
        
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart008");

        MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("008");

        MainChartVO resultVO = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultVO = mainChartService.selectWidgetsInfo(mainChartVO);
        }
        else { 
            //위젯 정보가 없을경우 디폴트 셋팅 : 전월대비, 부서
            resultVO.setGraphBaseCd("02");
            resultVO.setGraphRang("4");
            resultVO.setWidgetsId("008");
        }

        MainChartVO chartInfo = new MainChartVO();
        
        if(resultVO.getGraphBaseCd().equals("02")){ 
            //부서별
        	chartInfo.setDepCd(userDepCd);
        }else if(resultVO.getGraphBaseCd().equals("03")){ 
            //개인별
        	chartInfo.setUserId(userId);
        }
        chartInfo.setGraphRang(resultVO.getGraphRang());
        

        List<MainChartVO> list = null;
        list = mainChartService.selectMainChart008(chartInfo);
        
        String chartResult1 = "";
        String chartResult2 = "";
        String chartResultNm = "";
        
        for(int i=0; i<list.size(); i++ ) {
        	MainChartVO result = list.get(i);
        	
        	chartResult1 = chartResult1 + result.getData1();
        	chartResult2 = chartResult2 + result.getData2();
        	chartResultNm = chartResultNm + "'" + result.getValueNm() + "'";
        	
        	if(i != list.size()-1) {
        		chartResult1 += ",";
        		chartResult2 += ",";
        		chartResultNm += ",";
			}
        }
        
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("chartResult1",chartResult1);
        mav.addObject("chartResult2",chartResult2);
        mav.addObject("chartResultNm",chartResultNm);
        
        mav.addObject("resultTitle", resultVO);
        
        if("3".equals(resultVO.getGraphRang())) {
        	mav.addObject("legendNm", "'" + beforYear + "년','" + year + "년'");
        } else if("4".equals(resultVO.getGraphRang())) {
        	mav.addObject("legendNm", "'" + year + "년 " + beforMonth + "월','" + year + "년 " + month + "월'");
        }
	    
	    return mav;
    }
    
    /**
     * 채널별 처리현황
     * @param request
     * @param reponse
     */
    @RequestMapping("/common/mainchart/mainchart009.do")
    public ModelAndView mainchart009(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userId = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
        
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart009");

        MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("009");

        MainChartVO resultMWS = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultMWS = mainChartService.selectWidgetsInfo(mainChartVO);
        } else { 
            // 위젯 정보가 없을경우 디폴트 셋팅 : 01-전체 02-부서 03-개인
            resultMWS.setGraphBaseCd("02");
            // 위젯 정보가 없을경우 디폴트 셋팅 : 1-당해년 2-당월 3-전년대비 4-전월대비
            resultMWS.setGraphRang("1");
            resultMWS.setWidgetsId("009");
        }

        MainChartVO mainChart = new MainChartVO();
        
        if(resultMWS.getGraphBaseCd().equals("02")){ 
            //부서별
        	mainChart.setDepCd(userDepCd);
        }else if(resultMWS.getGraphBaseCd().equals("03")){ 
            //개인별
        	mainChart.setUserId(userId);
        }
        
        mainChart.setDvnYM(resultMWS.getGraphRang());
        
        List<MainChartVO> list = mainChartService.selectMainChart009(mainChart);
        
        String chartResult1 = "";
        String chartResultNm = "";
        
        for(int i=0; i<list.size(); i++ ) {
        	MainChartVO result = list.get(i);
        	
        	chartResult1 = chartResult1 + result.getData1();
        	chartResultNm = chartResultNm + "'" + result.getValueNm() + "'";
        	
        	if(i != list.size()-1) {
        		chartResult1 += ",";
        		chartResultNm += ",";
			}
        }
        
        if(list.size() == 0) {
        	chartResult1 = "0"; 
        	chartResultNm = "'없음'";
        }
        
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("chartResult1",chartResult1);
        mav.addObject("chartResultNm",chartResultNm);
        
        mav.addObject("resultTitle", resultMWS);
        return mav; 
    }
    
    /**
    * 월별 처리건수
    * @param request
    * @param reponse
    */
    @RequestMapping("/common/mainchart/mainchart010.do")
    public ModelAndView mainchart010(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userId = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
        
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart010");
        
        MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("010");

        MainChartVO resultMWS = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultMWS = mainChartService.selectWidgetsInfo(mainChartVO);
        } else { 
            // 위젯 정보가 없을경우 디폴트 셋팅 : 01-전체 02-부서 03-개인
            resultMWS.setGraphBaseCd("02");
            // 위젯 정보가 없을경우 디폴트 셋팅 : 1-당해년 2-당월 3-전년대비 4-전월대비
            resultMWS.setGraphRang("1");
            resultMWS.setWidgetsId("010");
        }

        MainChartVO mainChart = new MainChartVO();
        
        if(resultMWS.getGraphBaseCd().equals("02")){ 
            //부서별
        	mainChart.setDepCd(userDepCd);
        }else if(resultMWS.getGraphBaseCd().equals("03")){ 
            //개인별
        	mainChart.setUserId(userId);
        }
        
        mainChart.setDvnYM(resultMWS.getGraphRang());
        
        List<MainChartVO> list = mainChartService.selectMainChart010(mainChart);
        
        String chartResult1 = "";
        String chartResult2 = "";
        String chartResult3 = "";
        String chartResult4 = "";
        String chartResultNm = "";
        
        for(int i=0; i<list.size(); i++ ) {
        	MainChartVO result = list.get(i);
        	
        	chartResult1 = chartResult1 + result.getNeeds01();
        	chartResult2 = chartResult2 + result.getNeeds02();
        	chartResult3 = chartResult3 + result.getNeeds03();
        	chartResult4 = chartResult4 + result.getNeeds04();
        	chartResultNm = chartResultNm + "'" + result.getMonth() + "'";
        	
        	if(i != list.size()-1) {
        		chartResult1 += ",";
        		chartResult2 += ",";
        		chartResult3 += ",";
        		chartResult4 += ",";
        		chartResultNm += ",";
			}
        }
        
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("chartResult1",chartResult1);
        mav.addObject("chartResult2",chartResult2);
        mav.addObject("chartResult3",chartResult3);
        mav.addObject("chartResult4",chartResult4);
        mav.addObject("chartResultNm",chartResultNm);
        
        mav.addObject("legendNm", "'고객문의', '고객제안', '친절칭찬', '불편불만'");
        
        mav.addObject("resultTitle", resultMWS);
        
        return mav;
    }
    
    /**
	 * 발생원인별 현황
	 * @param request
	 * @param reponse
	 */
    @RequestMapping("/common/mainchart/mainchart011.do")
    public ModelAndView mainchart011(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
	    //현재날짜
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String today = formatter.format(new java.util.Date());
		int year = Integer.parseInt(today.substring(0, 4));
		int beforYear = year-1;
		int month = Integer.parseInt(today.substring(4,6));
		int beforMonth = month-1;
		if(beforMonth == 0){
		    beforMonth = 12;
		}
		
		UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
		// 로그인 사용자정보 가져오기
		String userId = userSession.getLogin().getUserEmpno();
		String userDepCd = userSession.getLogin().getDepCd();
		
		ModelAndView mav = new ModelAndView("/common/mainchart/mainchart011");
		
		MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("011");

        MainChartVO resultMWS = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultMWS = mainChartService.selectWidgetsInfo(mainChartVO);
        } else { 
            // 위젯 정보가 없을경우 디폴트 셋팅 : 01-전체 02-부서 03-개인
            resultMWS.setGraphBaseCd("02");
            // 위젯 정보가 없을경우 디폴트 셋팅 : 1-당해년 2-당월 3-전년대비 4-전월대비
            resultMWS.setGraphRang("3");
            resultMWS.setWidgetsId("011");
        }

        MainChartVO mainChart = new MainChartVO();
        
        if(resultMWS.getGraphBaseCd().equals("02")){ 
            //부서별
        	mainChart.setDepCd(userDepCd);
        }else if(resultMWS.getGraphBaseCd().equals("03")){ 
            //개인별
        	mainChart.setUserId(userId);
        }
        
        mainChart.setDvnYM(resultMWS.getGraphRang());
        
        List<MainChartVO> list = mainChartService.selectMainChart011(mainChart);
        
        String chartResult1 = "";
        String chartResult2 = "";
        String chartResultNm = "";
        
        for(int i=0; i<list.size(); i++ ) {
        	MainChartVO result = list.get(i);
        	
        	chartResult1 = chartResult1 + result.getData1();
        	chartResult2 = chartResult2 + result.getData2();
        	chartResultNm = chartResultNm + "'" + result.getValueNm() + "'";
        	
        	if(i != list.size()-1) {
        		chartResult1 += ",";
        		chartResult2 += ",";
        		chartResultNm += ",";
			}
        }
        
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("chartResult1",chartResult1);
        mav.addObject("chartResult2",chartResult2);
        mav.addObject("chartResultNm",chartResultNm);
        
        mav.addObject("resultTitle", resultMWS);
        
        if("3".equals(resultMWS.getGraphRang())) {
        	mav.addObject("legendNm", "'" + beforYear + "년','" + year + "년'");
        } else if("4".equals(resultMWS.getGraphRang())) {
        	mav.addObject("legendNm", "'" + year + "년 " + beforMonth + "월','" + year + "년 " + month + "월'");
        }
	    
	    return mav;
	}
    
    /**
     * 처리시간별 현황
     * @param request
     * @param reponse
     */
    @RequestMapping("/common/mainchart/mainchart012")
    public ModelAndView mainchart012(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        //현재날짜
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String today = formatter.format(new java.util.Date());
        
        int year = Integer.parseInt(today.substring(0, 4));
        int beforYear = year-1;
        int month = Integer.parseInt(today.substring(4,6));
        int beforMonth = month-1;

        if(beforMonth == 0){
            beforMonth = 12;
        }
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userId = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
        
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart012");
        
        MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("012");

        MainChartVO resultMWS = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultMWS = mainChartService.selectWidgetsInfo(mainChartVO);
        } else { 
            // 위젯 정보가 없을경우 디폴트 셋팅 : 01-전체 02-부서 03-개인
            resultMWS.setGraphBaseCd("02");
            // 위젯 정보가 없을경우 디폴트 셋팅 : 1-당해년 2-당월 3-전년대비 4-전월대비
            resultMWS.setGraphRang("3");
            resultMWS.setWidgetsId("012");
        }

        MainChartVO mainChart = new MainChartVO();
        
        if(resultMWS.getGraphBaseCd().equals("02")){ 
            //부서별
        	mainChart.setDepCd(userDepCd);
        }else if(resultMWS.getGraphBaseCd().equals("03")){ 
            //개인별
        	mainChart.setUserId(userId);
        }
        
        mainChart.setDvnYM(resultMWS.getGraphRang());
        
        List<MainChartVO> list = mainChartService.selectMainChart012(mainChart);
        
        String chartResult1 = "";
        String chartResult2 = "";
        String chartResultNm = "";
        
        for(int i=0; i<list.size(); i++ ) {
        	MainChartVO result = list.get(i);
        	
        	chartResult1 = chartResult1 + result.getData1();
        	chartResult2 = chartResult2 + result.getData2();
        	chartResultNm = chartResultNm + "'" + result.getValueNm() + "'";
        	
        	if(i != list.size()-1) {
        		chartResult1 += ",";
        		chartResult2 += ",";
        		chartResultNm += ",";
			}
        }
        
        mav.addObject("chartResult1",chartResult1);
        mav.addObject("chartResult2",chartResult2);
        mav.addObject("chartResultNm",chartResultNm);
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("resultTitle", resultMWS);
        
        if("3".equals(resultMWS.getGraphRang())) {
        	mav.addObject("legendNm", "'" + beforYear + "년','" + year + "년'");
        } else if("4".equals(resultMWS.getGraphRang())) {
        	mav.addObject("legendNm", "'" + year + "년 " + beforMonth + "월','" + year + "년 " + month + "월'");
        }
        
        return mav;
    }
    

    /**
     * 이용매체별 처리현황
     * @param request
     * @param reponse
     */
    @RequestMapping("/common/mainchart/mainchart013")
    public ModelAndView mainchart013(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        //현재날짜
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String today = formatter.format(new java.util.Date());
        
        int year = Integer.parseInt(today.substring(0, 4));
        int beforYear = year-1;
        int month = Integer.parseInt(today.substring(4,6));
        int beforMonth = month-1;

        if(beforMonth == 0){
            beforMonth = 12;
        }
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userId = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
        
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart013");
        
        MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("013");

        MainChartVO resultMWS = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultMWS = mainChartService.selectWidgetsInfo(mainChartVO);
        } else { 
            // 위젯 정보가 없을경우 디폴트 셋팅 : 01-전체 02-부서 03-개인
            resultMWS.setGraphBaseCd("02");
            // 위젯 정보가 없을경우 디폴트 셋팅 : 1-당해년 2-당월 3-전년대비 4-전월대비
            resultMWS.setGraphRang("3");
            resultMWS.setWidgetsId("013");
        }

        MainChartVO mainChart = new MainChartVO();
        
        if(resultMWS.getGraphBaseCd().equals("02")){ 
            //부서별
        	mainChart.setDepCd(userDepCd);
        }else if(resultMWS.getGraphBaseCd().equals("03")){ 
            //개인별
        	mainChart.setUserId(userId);
        }
        
        mainChart.setGraphRang(resultMWS.getGraphRang());
        
        List<MainChartVO> list = mainChartService.selectMainChart013(mainChart);
        
        String chartResult1 = "";
        String chartResult2 = "";
        String chartResultNm = "";
        
        for(int i=0; i<list.size(); i++ ) {
        	MainChartVO result = list.get(i);
        	
        	chartResult1 = chartResult1 + result.getData1();
        	chartResult2 = chartResult2 + result.getData2();
        	chartResultNm = chartResultNm + "'" + result.getValueNm() + "'";
        	
        	if(i != list.size()-1) {
        		chartResult1 += ",";
        		chartResult2 += ",";
        		chartResultNm += ",";
			}
        }
        
        mav.addObject("chartResult1",chartResult1);
        mav.addObject("chartResult2",chartResult2);
        mav.addObject("chartResultNm",chartResultNm);
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("resultTitle", resultMWS);
        
        if("3".equals(resultMWS.getGraphRang())) {
        	mav.addObject("legendNm", "'" + beforYear + "년','" + year + "년'");
        } else if("4".equals(resultMWS.getGraphRang())) {
        	mav.addObject("legendNm", "'" + year + "년 " + beforMonth + "월','" + year + "년 " + month + "월'");
        }
        
        return mav;
    }

    /**
     * 성격별 처리현황
     * @param request
     * @param reponse
     */
    @RequestMapping("/common/mainchart/mainchart014")
    public ModelAndView mainchart014(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        //현재날짜
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String today = formatter.format(new java.util.Date());
        
        int year = Integer.parseInt(today.substring(0, 4));
        int beforYear = year-1;
        int month = Integer.parseInt(today.substring(4,6));
        int beforMonth = month-1;

        if(beforMonth == 0){
            beforMonth = 12;
        }
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userId = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
        
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart014");
        
        MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("014");

        MainChartVO resultMWS = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultMWS = mainChartService.selectWidgetsInfo(mainChartVO);
        } else { 
            // 위젯 정보가 없을경우 디폴트 셋팅 : 01-전체 02-부서 03-개인
            resultMWS.setGraphBaseCd("02");
            // 위젯 정보가 없을경우 디폴트 셋팅 : 1-당해년 2-당월 3-전년대비 4-전월대비
            resultMWS.setGraphRang("3");
            resultMWS.setWidgetsId("014");
        }

        MainChartVO mainChart = new MainChartVO();
        
        if(resultMWS.getGraphBaseCd().equals("02")){ 
            //부서별
        	mainChart.setDepCd(userDepCd);
        }else if(resultMWS.getGraphBaseCd().equals("03")){ 
            //개인별
        	mainChart.setUserId(userId);
        }
        
        mainChart.setGraphRang(resultMWS.getGraphRang());
        
        List<MainChartVO> list = mainChartService.selectMainChart014(mainChart);
        
        String chartResult1 = "";
        String chartResult2 = "";
        String chartResultNm = "";
        
        for(int i=0; i<list.size(); i++ ) {
        	MainChartVO result = list.get(i);
        	
        	chartResult1 = chartResult1 + result.getData1();
        	chartResult2 = chartResult2 + result.getData2();
        	chartResultNm = chartResultNm + "'" + result.getValueNm() + "'";
        	
        	if(i != list.size()-1) {
        		chartResult1 += ",";
        		chartResult2 += ",";
        		chartResultNm += ",";
			}
        }
        
        mav.addObject("chartResult1",chartResult1);
        mav.addObject("chartResult2",chartResult2);
        mav.addObject("chartResultNm",chartResultNm);
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("resultTitle", resultMWS);
        
        if("3".equals(resultMWS.getGraphRang())) {
        	mav.addObject("legendNm", "'" + beforYear + "년','" + year + "년'");
        } else if("4".equals(resultMWS.getGraphRang())) {
        	mav.addObject("legendNm", "'" + year + "년 " + beforMonth + "월','" + year + "년 " + month + "월'");
        }
        
        return mav;
    }
    
    /**
     * 업무유형별 처리현황
     * @param request
     * @param reponse
     */
    @RequestMapping("/common/mainchart/mainchart015")
    public ModelAndView mainchart015(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        //현재날짜
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String today = formatter.format(new java.util.Date());
        
        int year = Integer.parseInt(today.substring(0, 4));
        int beforYear = year-1;
        int month = Integer.parseInt(today.substring(4,6));
        int beforMonth = month-1;

        if(beforMonth == 0){
            beforMonth = 12;
        }
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userId = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
        
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart015");
        
        MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("015");

        MainChartVO resultMWS = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultMWS = mainChartService.selectWidgetsInfo(mainChartVO);
        } else { 
            // 위젯 정보가 없을경우 디폴트 셋팅 : 01-전체 02-부서 03-개인
            resultMWS.setGraphBaseCd("02");
            // 위젯 정보가 없을경우 디폴트 셋팅 : 1-당해년 2-당월 3-전년대비 4-전월대비
            resultMWS.setGraphRang("3");
            resultMWS.setWidgetsId("015");
        }

        MainChartVO mainChart = new MainChartVO();
        
        if(resultMWS.getGraphBaseCd().equals("02")){ 
            //부서별
        	mainChart.setDepCd(userDepCd);
        }else if(resultMWS.getGraphBaseCd().equals("03")){ 
            //개인별
        	mainChart.setUserId(userId);
        }
        
        mainChart.setGraphRang(resultMWS.getGraphRang());
        
        List<MainChartVO> list = mainChartService.selectMainChart015(mainChart);
        
        String chartResult1 = "";
        String chartResult2 = "";
        String chartResultNm = "";
        
        for(int i=0; i<list.size(); i++ ) {
        	MainChartVO result = list.get(i);
        	
        	chartResult1 = chartResult1 + result.getData1();
        	chartResult2 = chartResult2 + result.getData2();
        	chartResultNm = chartResultNm + "'" + result.getValueNm() + "'";
        	
        	if(i != list.size()-1) {
        		chartResult1 += ",";
        		chartResult2 += ",";
        		chartResultNm += ",";
			}
        }
        
        mav.addObject("chartResult1",chartResult1);
        mav.addObject("chartResult2",chartResult2);
        mav.addObject("chartResultNm",chartResultNm);
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("resultTitle", resultMWS);
        
        if("3".equals(resultMWS.getGraphRang())) {
        	mav.addObject("legendNm", "'" + beforYear + "년','" + year + "년'");
        } else if("4".equals(resultMWS.getGraphRang())) {
        	mav.addObject("legendNm", "'" + year + "년 " + beforMonth + "월','" + year + "년 " + month + "월'");
        }
        
        return mav;
    }
    
    /**
     * 업무유형 처리현황
     * @param request
     * @param reponse
     */
    @RequestMapping("/common/mainchart/mainchart016.do")
    public ModelAndView mainchart016(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userId = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
        
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart016");

        MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("016");

        MainChartVO resultMWS = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultMWS = mainChartService.selectWidgetsInfo(mainChartVO);
        } else { 
            // 위젯 정보가 없을경우 디폴트 셋팅 : 01-전체 02-부서 03-개인
            resultMWS.setGraphBaseCd("02");
            // 위젯 정보가 없을경우 디폴트 셋팅 : 1-당해년 2-당월 3-전년대비 4-전월대비
            resultMWS.setGraphRang("1");
            resultMWS.setWidgetsId("016");
        }

        MainChartVO mainChart = new MainChartVO();
        
        if(resultMWS.getGraphBaseCd().equals("02")){ 
            //부서별
        	mainChart.setDepCd(userDepCd);
        }else if(resultMWS.getGraphBaseCd().equals("03")){ 
            //개인별
        	mainChart.setUserId(userId);
        }
        
        mainChart.setDvnYM(resultMWS.getGraphRang());
        
        List<MainChartVO> list = mainChartService.selectMainChart016(mainChart);
        
		String chartResult = "";
		

		String chartResult1 = "";
		String chartResult2 = "";
		
    	if(list.size() != 0){
	      	for(int i = 0; i < list.size(); i++){
	      		if(list.get(i).getValueCd() != null) {
	      			if(i == list.size() - 1){
		          		chartResult1 += "['" + list.get(i).getValueNm() + "', " + list.get(i).getData1() + "]";
		          	}
		          	else{
		          		chartResult1 += "['" + list.get(i).getValueNm() + "', " + list.get(i).getData1() + "], ";
		      		}
	        	}
	      	}
		}
    	else{
    		chartResult1 = "['없음', 100]";
	  	}
    	
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("chartResult1",chartResult1);
        
        mav.addObject("resultTitle", resultMWS);
        return mav; 
    }

    /**
     * 업무유형별 처리현황
     * @param request
     * @param reponse
     */
    @RequestMapping("/common/mainchart/mainchart017")
    public ModelAndView mainchart017(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        //현재날짜
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String today = formatter.format(new java.util.Date());
        
        int year = Integer.parseInt(today.substring(0, 4));
        int beforYear = year-1;
        int month = Integer.parseInt(today.substring(4,6));
        int beforMonth = month-1;

        if(beforMonth == 0){
            beforMonth = 12;
        }
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userId = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
        
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart017");
        
        MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("017");

        MainChartVO resultMWS = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultMWS = mainChartService.selectWidgetsInfo(mainChartVO);
        } else { 
            // 위젯 정보가 없을경우 디폴트 셋팅 : 01-전체 02-부서 03-개인
            resultMWS.setGraphBaseCd("02");
            // 위젯 정보가 없을경우 디폴트 셋팅 : 1-당해년 2-당월 3-전년대비 4-전월대비
            resultMWS.setGraphRang("1");
            resultMWS.setWidgetsId("017");
        }

        MainChartVO mainChart = new MainChartVO();
        mainChart.setDvnYM(resultMWS.getGraphRang());
        List<MainChartVO> list = mainChartService.selectMainChart017(mainChart);
        
        String chartResult1 = "";
        String chartResult2 = "";
        String chartResult3 = "";
        String chartResultNm = "";
        
        for(int i=0; i<list.size(); i++ ) {
        	MainChartVO result = list.get(i);
        	
        	chartResult1 = chartResult1 + result.getData1();
        	chartResult2 = chartResult2 + result.getData2();
        	chartResult3 = chartResult3 + result.getData3();
        	chartResultNm = chartResultNm + "'" + result.getValueNm() + "'";
        	
        	if(i != list.size()-1) {
        		chartResult1 += ",";
        		chartResult2 += ",";
        		chartResult3 += ",";
        		chartResultNm += ",";
			}
        }
        
        mav.addObject("chartResult1",chartResult1);
        mav.addObject("chartResult2",chartResult2);
        mav.addObject("chartResult3",chartResult3);
        mav.addObject("chartResultNm",chartResultNm);
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("resultTitle", resultMWS);
        
        return mav;
    }
    

    /**
     * 업무유형별 처리현황
     * @param request
     * @param reponse
     */
    @RequestMapping("/common/mainchart/mainchart018")
    public ModelAndView mainchart018(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        //현재날짜
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String today = formatter.format(new java.util.Date());
        
        int year = Integer.parseInt(today.substring(0, 4));
        int beforYear = year-1;
        int month = Integer.parseInt(today.substring(4,6));
        int beforMonth = month-1;

        if(beforMonth == 0){
            beforMonth = 12;
        }
        
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        // 로그인 사용자정보 가져오기
        String userId = userSession.getLogin().getUserEmpno();
        String userDepCd = userSession.getLogin().getDepCd();
        
        ModelAndView mav = new ModelAndView("/common/mainchart/mainchart018");
        
        MainChartVO mainChartVO = new MainChartVO();
        mainChartVO.setUserId(userId);
        mainChartVO.setWidgetsId("018");

        MainChartVO resultMWS = new MainChartVO();
        
        int widgetCnt = mainChartService.selectWidgetsCount(mainChartVO);
        
        if(widgetCnt != 0){
            resultMWS = mainChartService.selectWidgetsInfo(mainChartVO);
        } else { 
            // 위젯 정보가 없을경우 디폴트 셋팅 : 01-전체 02-부서 03-개인
            resultMWS.setGraphBaseCd("02");
            // 위젯 정보가 없을경우 디폴트 셋팅 : 1-당해년 2-당월 3-전년대비 4-전월대비
            resultMWS.setGraphRang("1");
            resultMWS.setWidgetsId("018");
        }

        MainChartVO mainChart = new MainChartVO();
        mainChart.setDvnYM(resultMWS.getGraphRang());
		
        List<MainChartVO> list = mainChartService.selectMainChart018(mainChart);
        
        String chartResult1 = "";
        String chartResult2 = "";
        String chartResult3 = "";
        String chartResult4 = "";
        String chartResult5 = "";
        String chartResultNm = "";
        
        for(int i=0; i<list.size(); i++ ) {
        	MainChartVO result = list.get(i);
        	
        	chartResult1 = chartResult1 + result.getData1();
        	chartResult2 = chartResult2 + result.getData2();
        	chartResult3 = chartResult3 + result.getData3();
        	chartResult4 = chartResult4 + result.getData4();
        	chartResult5 = chartResult5 + result.getData5();
        	chartResultNm = chartResultNm + "'" + result.getValueNm() + "'";
        	
        	if(i != list.size()-1) {
        		chartResult1 += ",";
        		chartResult2 += ",";
        		chartResult3 += ",";
        		chartResult4 += ",";
        		chartResult5 += ",";
        		chartResultNm += ",";
			}
        }
        
        mav.addObject("chartResult1",chartResult1);
        mav.addObject("chartResult2",chartResult2);
        mav.addObject("chartResult3",chartResult3);
        mav.addObject("chartResult4",chartResult4);
        mav.addObject("chartResult5",chartResult5);
        mav.addObject("chartResultNm",chartResultNm);
        mav.addObject("F204", this.getCommonCode("F204"));
        mav.addObject("F205", this.getCommonCode("F205"));
        mav.addObject("resultTitle", resultMWS);
        
        return mav;
    }
    
    /**
     * 차트 이미지 다운로드
     * @param mainChartVO
     * @return List
     * @throws Exception
     */
    @RequestMapping("/common/mainchart/chartImgdown.do")
    public ModelAndView chartImgdown(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
    	Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
    	
    	
    	String fileName = StringUtil.getParam(request, "filename");
	    String data = StringUtil.getParam(request, "imageData");
	    data = data.split(",")[1];
	    
	    fileName = URLEncoder.encode(fileName+"_"+today, "utf-8");
	    
	    reponse.setContentType("application/octet-stream");
    	reponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".png\";");
	    
	    BASE64Decoder base64Decoder = new BASE64Decoder();
	    byte[] result = base64Decoder.decodeBuffer(data);
	    BufferedOutputStream bos = null;
	    try{
	    	bos = new BufferedOutputStream(reponse.getOutputStream());
	        bos.write(result);	
	    }
	    catch(Exception e) {
	    	
	    }
	    finally{
	    	bos.close();
	    }
	    
	    return null;
    }

}
