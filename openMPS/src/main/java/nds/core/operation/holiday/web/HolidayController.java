package nds.core.operation.holiday.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.operation.holiday.service.HolidayService;
import nds.core.operation.holiday.service.HolidayVO;
import nds.frm.startup.SYSTEM;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: HolidayController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */

@Controller
public class HolidayController extends BaseController {
	/** HolidayService */
    @Resource(name = "holidayService")
    private HolidayService holidayService;
    
    /**
     * 휴일조회
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/operation/holiday/oprat0800.do")
    public ModelAndView oprat0800(HttpServletRequest request, HttpServletResponse reponse) throws Exception {

        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        String hDayYmd = StringUtil.getParam(request, "hDayYmd", "");
       	
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();
        
        ModelAndView mav = new ModelAndView("operation/holiday/oprat0800");

        if(crudMode.equals("U")) {

            String schYyyy = StringUtil.getParam(request, "hdayYyyy", "");
            String schMm = StringUtil.getParam(request, "hdayMm", "");
            String CmpgMed = StringUtil.getParam(request, "schCmpgMed", "");

            String[] ymd = request.getParameterValues("yymmdd");

            if(ymd != null) {
                HolidayVO holidayVO = new HolidayVO();
                holidayVO.setUpdateDvn("ALL");
                holidayVO.setYymmdd(schYyyy + schMm + "%");
                holidayVO.setMedDvn(CmpgMed);
                holidayVO.setHdayYn("N");
                holidayVO.setUpdtUser(userEmpno);
                holidayVO.setUserId(userEmpno);

                holidayService.updateHdayAll(holidayVO);
            }
            for(int i = 0; i < ymd.length; i++) {
                HolidayVO holidayVO = new HolidayVO();
                holidayVO.setUpdateDvn("DAY");
                holidayVO.setYymmdd(ymd[i]);
                holidayVO.setMedDvn(CmpgMed);
                holidayVO.setHdayYn("Y");
                holidayVO.setUpdtUser(userEmpno);
                holidayVO.setUserId(userEmpno);
                
                holidayService.updateHday(holidayVO);
            }
            
        }
        // 생성
		else if (crudMode.equals("C")) {
			String schYyyy = StringUtil.getParam(request, "hdayYyyy", "");
			String schMm = StringUtil.getParam(request, "hdayMm", "");

			HolidayVO key = new HolidayVO();
			key.setYymmdd(schYyyy);
			key.setRegUser(userEmpno);
			holidayService.callSpMakeHday(key);

		} else if(crudMode.equals("HD")) {
			
	       	 HolidayVO holidayVO = new HolidayVO();
	       	 holidayVO.setHdayResn("");
	       	 holidayVO.setHdayYn("N");
	       	 holidayVO.setYymmdd(hDayYmd);
	       	 holidayVO.setUpdtUser(userEmpno);
	       	 holidayVO.setUserId(userEmpno);
	       	
	       	 // holidayService.updateHdayResn(holidayVO);
	       	holidayService.deleteHdayResn(holidayVO);
        }
        SYSTEM system = SYSTEM.getInstance();
        String schYyyy = StringUtil.getParam(request, "schYyyy", system.getYear());
        String schMm = StringUtil.getParam(request, "schMm", system.getMonth());

        HolidayVO key = new HolidayVO();
        key.setYymmdd(schYyyy + "%");
        key.setUserId(userEmpno);
        
        mav.addObject("schYyyy", schYyyy);
        mav.addObject("schMm", schMm);
        mav.addObject("intTotalCount", holidayService.selectHdayCount(key));

        key = new HolidayVO();
        key.setYymmdd(schYyyy + schMm + "%");
        key.setUserId(userEmpno);
        ArrayList<HolidayVO> result = (ArrayList<HolidayVO>) holidayService.selectHdayList(key);

        ArrayList<HolidayVO[]> rtn = new ArrayList<HolidayVO[]>();

        if(result != null) {
            HolidayVO[] week = null;
            for(int i = 0; i < result.size(); i++) {
                HolidayVO holidayVO = (HolidayVO) result.get(i);

                if(i == 0)
                    week = new HolidayVO[7];

                if(holidayVO.getDowCd().equals("1")) {
                    if(i != 0)
                        week = new HolidayVO[7];
                    week[0] = holidayVO;
                }
                else {
                    week[Integer.parseInt(holidayVO.getDowCd()) - 1] = holidayVO;
                }

                if(holidayVO.getDowCd().equals("7") || i == result.size() - 1) {
                    rtn.add(week);
                }
            }
        }

        mav.addObject("result", rtn);

        return mav;
    }

    /**
     * 휴일관리 시간등록
     * 
     * @param request
     * @param reponse
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping("/operation/holiday/oprat0801_popup.do")
    public ModelAndView oprat0801_popup(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        
        String CmpgMed = StringUtil.getParam(request, "CmpgMed", "");
        String hDayYmd = StringUtil.getParam(request, "hDayYmd", "");
        String strtTm = StringUtil.getParam(request, "strtTm", "");
        String wrkEndTm = StringUtil.getParam(request, "endTm", "");

        // 로그인 사용자정보 가져오기
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();
        
        ModelAndView mav = null;

        // 저장
        if(crudMode.equals("C")) {

            HolidayVO holidayVO = new HolidayVO();
            holidayVO.setYymmdd(hDayYmd);
            holidayVO.setWrkStrtTm(strtTm);
            holidayVO.setWrkEndTm(wrkEndTm);
            holidayVO.setRegUser(userEmpno);
            holidayVO.setUpdtUser(userEmpno);
            holidayVO.setUserId(userEmpno);
            
            holidayService.updateHday(holidayVO);
            mav = new ModelAndView("success_close", "scriptBlock", "opener.fnButtonClick('검색','');");
        }
        else {
            mav = new ModelAndView("operation/holiday/oprat0801_popup");

            HolidayVO keyVO = new HolidayVO();
            keyVO.setMedDvn(CmpgMed);
            keyVO.setYymmdd(hDayYmd);
            keyVO.setUserId(userEmpno);
            
            mav.addObject("result", holidayService.selectHdayTime(keyVO));
        }
        return mav;
    }
    

    /**
     * 휴일사유팝업
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/operation/holiday/oprat0802_popup.do")
    public ModelAndView oprat0802_popup(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
    	
    	String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
    	String hDayYmd = StringUtil.getParam(request, "hDayYmd", "");
    	String hdayYn = StringUtil.getParam(request, "hdayYn", "");
    	String hdayResn = StringUtil.getParam(request, "hdayResn", "");
    	
    	String strtTm = StringUtil.getParam(request, "strtTm", "");
    	String endTm = StringUtil.getParam(request, "endTm", "");
    	
    	ModelAndView mav = new ModelAndView("operation/holiday/oprat0802_popup");
    	
         if(crudMode.equals("C")) {

             UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
             String userEmpno = userSession.getLogin().getUserEmpno();

             HolidayVO holidayVO = new HolidayVO();
             holidayVO.setHdayResn(hdayResn);
             holidayVO.setMedDvn("S");
             holidayVO.setHdayYn(hdayYn);
             holidayVO.setYymmdd(hDayYmd);
             holidayVO.setRegUser(userEmpno);
             holidayVO.setUpdtUser(userEmpno);
             holidayVO.setUserId(userEmpno);
             holidayVO.setWrkStrtTm(strtTm);
             holidayVO.setWrkEndTm(endTm);
             // holidayService.updateHdayResn(holidayVO);
             
             holidayService.insertHdayResn(holidayVO);
             return mav;
         }
    	
    	return mav;
    }
    
    /**
     * 사용자 휴일 조회
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/operation/holiday/holidayUser.do")
    public ModelAndView holidayUser(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
    	
    	ModelAndView mav = new ModelAndView("jsonView");
    	reponse.setContentType("application/json; charset=utf-8");
    	reponse.setHeader("Cache-Control", "no-cache");
		
		String yymmdd = StringUtil.getParam(request, "yymmdd", "");
		
		HolidayVO holidayVO = new HolidayVO();
        holidayVO.setYymmdd(yymmdd);
        
        int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
	    int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "5")); // 페이지당 몇개
        
		String sidx = StringUtil.getParam(request, "sidx", "");
		String sord = StringUtil.getParam(request, "sord", "");
		
		holidayVO.setStartNo(((page * rows) - rows) + 1);
		holidayVO.setEndNo(page * rows);

		holidayVO.setSord(sord);
		holidayVO.setSidx(sidx);
        
        int total = holidayService.selectUserWorkHdayResultCount(holidayVO);
		List<HolidayVO> result = holidayService.selectUserWorkHdayResult(holidayVO);
		
		mav.addObject("total", Math.ceil((double)total/rows));  // total page
        mav.addObject("records", total);                        // total count
        mav.addObject("rows", result);
		return mav;
    }
    
    
}
