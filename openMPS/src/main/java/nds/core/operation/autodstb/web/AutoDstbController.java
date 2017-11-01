package nds.core.operation.autodstb.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.operation.autonotice.service.AutoNoticeService;
import nds.core.operation.autonotice.service.AutoNoticeVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: AutoDstbController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2015.04.22 초기작성(김세희)</pre>
 * @author <a href="mailto:shkim@nds.co.kr">김세희</a>
 * @version 1.0
 */

@Controller
public class AutoDstbController extends BaseController{
	  /** AutoNoticeService */
    @Resource(name = "autoNoticeService")
    private AutoNoticeService autoNoticeService; 

    /**
     * 자동알림설정 조회
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/operation/autodstb/oprat1500.do")
    public ModelAndView imprj0900(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
   	    String delConfNo = StringUtil.getParam(request, "confNo", "");
   	    String schTypeDvn = StringUtil.getParam(request, "schTypeDvn", "V");
   	    
   		String[] procStat = request.getParameterValues("procStat");
		String[] inoutDvn = request.getParameterValues("inoutDvn");
		String[] tgtUser = request.getParameterValues("tgtUser");
		String[] msgrUseYn = request.getParameterValues("msgrUseYn");
		String[] confNo = request.getParameterValues("confNo");
		
		String[] procStatCd = request.getParameterValues("procStatCd");
		String[] inoutDvnCd = request.getParameterValues("inoutDvnCd");
		String[] tgtUserCd = request.getParameterValues("tgtUserCd");
		String[] smsUseYnCd = request.getParameterValues("smsUseYnCd");
		String[] emailUseYnCd = request.getParameterValues("emailUseYnCd");
		String[] msgrUseYnCd = request.getParameterValues("msgrUseYnCd");
		
   	    
   	    // 화면
   		ModelAndView mav = new ModelAndView("operation/autodstb/oprat1500");

   		// 로그인 사용자정보 가져오기
   		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
   		String userEmpno = userSession.getLogin().getUserEmpno();

   		int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
   		int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "10")); // 페이지당 몇개
   		
   		String sidx = StringUtil.getParam(request, "sidx", "");
   		String sord = StringUtil.getParam(request, "sord", "");
   		
   		AutoNoticeVO autoNoticeVO = new AutoNoticeVO();
   		bind(request , autoNoticeVO);
   		
   		autoNoticeVO.setTypeDvn(schTypeDvn);
   		autoNoticeVO.setStartNo(((page * rows) - rows) + 1);
   		autoNoticeVO.setEndNo(page * rows);
   		
   		autoNoticeVO.setSidx(sidx);
   		autoNoticeVO.setSord(sord);

   		
 		if ("S".equals(crudMode)) {
   			mav = new ModelAndView("jsonView");
           	reponse.setContentType("application/json; charset=utf-8");
       		reponse.setHeader("Cache-Control", "no-cache");
       		
       		int total = autoNoticeService.selectAutoListCount(autoNoticeVO);
       		List<AutoNoticeVO> result = autoNoticeService.selectAutoList(autoNoticeVO);
       		
       		mav.addObject("total", Math.ceil((double)total/rows));  // total page
            mav.addObject("records", total);                        // total count
            mav.addObject("rows", result);
            
   		}
 		
 		else if ("C".equals(crudMode)) {
 			
 			List<AutoNoticeVO> autoInsertList = new ArrayList<AutoNoticeVO>();
			for(int i=0;i<procStatCd.length;i++){
				autoNoticeVO = new AutoNoticeVO();
				autoNoticeVO.setConfNo(confNo[i]);
				autoNoticeVO.setRegUser(userEmpno);
				autoNoticeVO.setUpdtUser(userEmpno);
				autoNoticeVO.setTypeDvn(schTypeDvn);
				autoNoticeVO.setProcStat(procStatCd[i]);
		   		autoNoticeVO.setTgtUser(tgtUserCd[i]);
		   		autoNoticeVO.setSmsUseYn(smsUseYnCd[i]);
		   		autoNoticeVO.setEmailUseYn(emailUseYnCd[i]);
		   		
		   		if(("V").equals(schTypeDvn)){
		   			autoNoticeVO.setInoutDvn(inoutDvnCd[i]);
//		   			if(inoutDvnCd[i].equals("I")){
		   				autoNoticeVO.setMsgrUseYn(msgrUseYnCd[i]);
//		   			}
//		   			else {
//		   				autoNoticeVO.setMsgrUseYn("Y");
//		   			}
		   		}
		   		else{
		   			autoNoticeVO.setMsgrUseYn(msgrUseYnCd[i]);
		   			autoNoticeVO.setInoutDvn("I");
		   		}
		   		autoInsertList.add(autoNoticeVO);
			}

			autoNoticeService.insertAutoNotice(autoInsertList);
			mav.addObject("crudMode", "");
 		}
 		
 		else if ("D".equals(crudMode)) {
 			autoNoticeVO.setUpdtUser(userEmpno);
			autoNoticeVO.setConfNo(delConfNo);
			autoNoticeVO.setUseYn("N");
			
			autoNoticeService.deleteAuto(autoNoticeVO);
 		}
 		
 		mav.addObject("F016", this.getCommonCode("F016"));
 		mav.addObject("F051", this.getCommonCode("F051"));
 		mav.addObject("F080", this.getCommonCode("F080"));
 		mav.addObject("F201", this.getCommonCode("F201"));
 		mav.addObject("F154", this.getCommonCodeList("F154"));
 		mav.addObject("F156", this.getCommonCodeList("F156"));
 		mav.addObject("F157", this.getCommonCodeList("F157"));
 		return mav;

    }

}
