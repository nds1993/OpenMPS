package nds.core.request.cooperation.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.AttachfileService;
import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.common.message.MessageModule;
import nds.core.request.cooperation.service.CooperationService;
import nds.core.request.cooperation.service.CooperationVO;
import nds.frm.config.StaticConfig;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: CooperationController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2015.07.30 초기작성(김세희)</pre>
 * @author <a href="mailto:shkim@nds.co.kr">김세희</a>
 * @version 1.0
 */
@Controller
public class CooperationController extends BaseController{

	/** AttachfileService */
    @Resource(name = "attachfileService")
    private AttachfileService attachfileService;
	/** CooperationService */
    @Resource(name = "cooperationService")
    private CooperationService cooperationService;
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService; 

    /** messageModule */
    @Resource(name = "messageModule")
    private MessageModule messageModule;
    /**
     * 의견/검토처리 조회
     * @param request
     * @param reponse
     */
    @RequestMapping("/request/cooperation/reqst0100.do")
    public ModelAndView reqst0100(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode 	 = StringUtil.getParam(request, "crudMode", "").toUpperCase();     // crudMode
        // 검색조건
        String schStartDd    = StringUtil.getParam(request, "schStartDd", "").replace("-", "");    // 조건 시작일자
        String schEndDd      = StringUtil.getParam(request, "schEndDd", "").replace("-", "");      // 조건 종료일자
        String schTit        = StringUtil.getParam(request, "schTit", "");        // 제목
        String schChrgDepCd  = StringUtil.getParam(request, "schChrgDepCd");  // 요청부서
        String schChrgUserId = StringUtil.getParam(request, "schChrgUserId"); // 요청자
        String schTretGubun  = StringUtil.getParam(request, "schTretGubun");
        

        String schGubun    = StringUtil.getParam(request, "schGubun");   // 구분
        String schTretCmpltYn   = StringUtil.getParam(request, "schTretCmpltYn");   // 처리상태

        // 로그인 사용자정보 가져오기
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();
        String depCd    = userSession.getLogin().getDepCd();
        
        // 화면
        ModelAndView mav = new ModelAndView("request/cooperation/reqst0100");

	    int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
	    int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "20")); // 페이지당 몇개
	        
	    String sidx = StringUtil.getParam(request, "sidx", "");
	    String sord = StringUtil.getParam(request, "sord", "");
	    
        CooperationVO cooperation = new CooperationVO();

        cooperation.setSchStartDd(schStartDd);
        cooperation.setSchEndDd(schEndDd);
        cooperation.setReqType(schGubun);
        cooperation.setTretCmpltYn(schTretCmpltYn);
        cooperation.setReqDeptCd(schChrgDepCd);
        cooperation.setReqEmpno(schChrgUserId);
        cooperation.setReqText(schTit);
        cooperation.setTretEmpno(userEmpno);
        cooperation.setTretDeptCd(depCd);
        
        cooperation.setSchTretGubun(schTretGubun);
        
        cooperation.setStartNo(((page * rows) - rows) + 1);
        cooperation.setEndNo(page * rows);
   
        cooperation.setSord(sord);
        cooperation.setSidx(sidx);
        
        List<CooperationVO> result = null;
        int intTotalCount         = 0;
        
       if("S".equals(crudMode)){
            mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
    		
    		int total = cooperationService.selectCooListCount(cooperation);
    		result = cooperationService.selectCooList(cooperation);
    		
            mav.addObject("total", Math.ceil((double)total/rows));  // total page
            mav.addObject("records", total);                        // total count
            mav.addObject("rows", result);
            
            return mav;
        }
        
        // 컬럼 설정 조회
        String cstNo = userSession.getLogin().getUserEmpno();		//로그인한 id값
            
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
            
     	myMenuColumnVO.setUserId(cstNo);
     	myMenuColumnVO.setMenuId("reqst0100");
     		
     	MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
            
     	mav.addObject("colResult", colResult);
        mav.addObject("intTotalCount", intTotalCount);
    	
        mav.addObject("F007", this.getCommonCode("F007"));
        return mav;
    }

    /**
     * 의견/검토처리 상세(제안)
     * @param request
     * @param reponse
     */
    @RequestMapping("/request/cooperation/reqst0110.do")
    public ModelAndView reqst0110(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode 	 = StringUtil.getParam(request, "crudMode", "").toUpperCase();     // crudMode
        String reqId         = StringUtil.getParam(request, "reqId", "");     // reqId
        String reqType	     = StringUtil.getParam(request, "reqType", "");
        String tretText      = StringUtil.getParam(request, "tretText", "");
        String tretEmpno     = StringUtil.getParam(request, "tretEmpno", "");
        
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();
        String depCd = userSession.getLogin().getDepCd();
        
        // 화면
        ModelAndView mav = new ModelAndView("request/cooperation/reqst0110");

        CooperationVO cooper = new CooperationVO();
        cooper.setReqId(reqId);
        cooper.setReqType(reqType);
        cooper.setTretText(tretText);
        cooper.setTretEmpno(tretEmpno);
        
        if("S".equals(crudMode)){
        	CooperationVO resultVO = cooperationService.selectReq(cooper);
    		
    		String AttachFileName = resultVO.getReqId();
    		List files = attachfileService.getAttachFileList(AttachFileName, StaticConfig.ATTACH_VOC_CODE);

    		mav.addObject("files", files);
    		mav.addObject("propMst", resultVO);//승인 정보
        }
        else if("RC".equals(crudMode)){
        	cooperationService.updateTemVoc(cooper);
            
        	// 첨부파일 정보가 변경된 경우에만 갱신
            String attcFileYn1 = StringUtil.getParam(request, "attcFileYn1", "");
            
            if(attcFileYn1.equals("Y")) {
                this.updateAttachFile(attachfileService, StaticConfig.ATTACH_HELP_CODE, reqId, request.getParameter("texts1"), request.getParameter("values1"), StaticConfig.ATTACH_HELP_DIR, "FTP"); //내부업로드
                //this.transFTPFiles(attachfileService, StaticConfig.ATTACH_VOC_CODE_ANS, apvId); //외부포털 업로드
            }
        }
        else if("C".equals(crudMode)){
        	cooperationService.updateReqVoc(cooper);
            
        	// 첨부파일 정보가 변경된 경우에만 갱신
            String attcFileYn1 = StringUtil.getParam(request, "attcFileYn1", "");
            
            if(attcFileYn1.equals("Y")) {
                this.updateAttachFile(attachfileService, StaticConfig.ATTACH_HELP_CODE, reqId, request.getParameter("texts1"), request.getParameter("values1"), StaticConfig.ATTACH_HELP_DIR, "FTP"); //내부업로드
                //this.transFTPFiles(attachfileService, StaticConfig.ATTACH_VOC_CODE_ANS, apvId); //외부포털 업로드
            }
            /**
             * 메시지발송.
             */
			//messageModule.sendAutoMessageHelp(reqId, reqType, depCd, userEmpno , request);
        }
		List files = attachfileService.getAttachFileList(reqId, StaticConfig.ATTACH_PRJ_CODE);
        mav.addObject("files", files);
        
        List helpFiles = attachfileService.getAttachFileList(reqId, StaticConfig.ATTACH_HELP_CODE);
        mav.addObject("helpFiles", helpFiles);
        return mav;
    }
    
    /**
     * 의견/검토처리 상세(VOC)
     * @param request
     * @param reponse
     */
    @RequestMapping("/request/cooperation/reqst0120.do")
    public ModelAndView reqst0120(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode    = StringUtil.getParam(request, "crudMode", "").toUpperCase();     // crudMode
        String reqId       = StringUtil.getParam(request, "reqId", "");     // reqId
        String reqType	   = StringUtil.getParam(request, "reqType", "");
        String tretText    = StringUtil.getParam(request, "tretText", "");
        String tretEmpno     = StringUtil.getParam(request, "tretEmpno", "");

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();
        String depCd = userSession.getLogin().getDepCd();
        
        // 화면
        ModelAndView mav = new ModelAndView("request/cooperation/reqst0120");

        CooperationVO cooper = new CooperationVO();
        cooper.setReqId(reqId);
        cooper.setReqType(reqType);
        cooper.setTretText(tretText);
        cooper.setTretEmpno(tretEmpno);
        
        if("S".equals(crudMode)){
        	CooperationVO resultVO;
    		
    		String AttachFileName;
			try {
				resultVO = cooperationService.selectReqVoc(cooper);
				AttachFileName = resultVO.getReqId();
				List files = attachfileService.getAttachFileList(AttachFileName, StaticConfig.ATTACH_VOC_CODE);
	    		mav.addObject("files", files);
	    		mav.addObject("cutNeedsMst", resultVO);//승인 정보

			} catch (Exception e) {
				// TODO: handle exception
			}
    		

        }
        else if("RC".equals(crudMode)){
        	cooperationService.updateTemVoc(cooper);
        	
        	// 첨부파일 정보가 변경된 경우에만 갱신
            String attcFileYn1 = StringUtil.getParam(request, "attcFileYn1", "");
            
            if(attcFileYn1.equals("Y")) {
                this.updateAttachFile(attachfileService, StaticConfig.ATTACH_HELP_CODE, reqId, request.getParameter("texts1"), request.getParameter("values1"), StaticConfig.ATTACH_HELP_DIR, "FTP"); //내부업로드
                //this.transFTPFiles(attachfileService, StaticConfig.ATTACH_VOC_CODE_ANS, apvId); //외부포털 업로드
            }
        }
        else if("C".equals(crudMode)){
        	cooperationService.updateReqVoc(cooper);
        	
        	// 첨부파일 정보가 변경된 경우에만 갱신
            String attcFileYn1 = StringUtil.getParam(request, "attcFileYn1", "");
            
            if(attcFileYn1.equals("Y")) {
                this.updateAttachFile(attachfileService, StaticConfig.ATTACH_HELP_CODE, reqId, request.getParameter("texts1"), request.getParameter("values1"), StaticConfig.ATTACH_HELP_DIR, "FTP"); //내부업로드
                //this.transFTPFiles(attachfileService, StaticConfig.ATTACH_VOC_CODE_ANS, apvId); //외부포털 업로드
            }
            /**
             * 메시지발송.
             */
			//messageModule.sendAutoMessageHelp(reqId, reqType, depCd, userEmpno , request);
        }
		List files = attachfileService.getAttachFileList(reqId, StaticConfig.ATTACH_VOC_CODE);
        mav.addObject("files", files);
        
        List helpFiles = attachfileService.getAttachFileList(reqId, StaticConfig.ATTACH_HELP_CODE);
        mav.addObject("helpFiles", helpFiles);
        return mav;
    }

}
