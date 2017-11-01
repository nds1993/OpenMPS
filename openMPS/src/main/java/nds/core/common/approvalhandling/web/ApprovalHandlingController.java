package nds.core.common.approvalhandling.web;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.approvalhandling.service.ApprovalHandlingService;
import nds.core.common.common.service.AttachfileService;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.common.message.MessageModule;
import nds.core.operation.approval.service.ApprovalVO;
import nds.frm.config.StaticConfig;
import nds.frm.startup.SYSTEM;
import nds.frm.util.DateTimeUtil;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: MainController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.26 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Controller
public class ApprovalHandlingController extends BaseController {
	
    /** MainService */
    @Resource(name = "approvalHandlingService")
    private ApprovalHandlingService approvalHandlingService;
	/** AttachfileService */
    @Resource(name = "attachfileService")
    private AttachfileService attachfileService;
    
    /** messageModule */
    @Resource(name = "messageModule")
    private MessageModule messageModule;

    /**
	 * 승인대기건 조회
	 * 
	 * @param request
	 * @param reponse
	 */
    @RequestMapping("/common/approvalhandling/myvocapvrecv.do")
	public ModelAndView myvocapvrecv(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String crudMode	 	= StringUtil.getParam(request, "crudMode", "S");
    	String apvId 		= StringUtil.getParam(request, "apvId", "");
    	String apvType 		= StringUtil.getParam(request, "apvType", "");
    	String schApvType 	= StringUtil.getParam(request, "schApvType", "");
    	String schStartDd     = StringUtil.getParam(request, "schStartDd", "").replace("-", "");
    	String schEndDd       = StringUtil.getParam(request, "schEndDd", "").replace("-", "");
    	String schCmplYn 	= StringUtil.getParam(request, "schCmplYn", "");
    	String schApvStat 	= StringUtil.getParam(request, "schApvStat", "");
    	String rqstuserEmpno = StringUtil.getParam(request, "rqstuserEmpno", "");
    	
    	// 로그인 사용자정보 가져오기
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();

		//조건 일자가 없다면 1주일셋팅
        SYSTEM system   =   SYSTEM.getInstance();
        String today    =   StringUtil.getParam(request, "YYYYMMDD", system.getDate());
        
        if( schStartDd == "" && schEndDd == ""){
            schStartDd = DateTimeUtil.calculateDate(today,-30);
            schEndDd = today;
        }
		
        ModelAndView mav = new ModelAndView("common/approvalhandling/myvocapvrecv");

		int intTotalCount = 0;
		List result = null;
		
		ApprovalVO approvalVO = new ApprovalVO();
		bind(request, approvalVO);
		approvalVO.setApvId(apvId);
		approvalVO.setApvuserEmpno(userEmpno);
		approvalVO.setSchStartDd(schStartDd);
		approvalVO.setSchEndDd(schEndDd);
		approvalVO.setApvType(schApvType);
		approvalVO.setApvStat(schApvStat);
		approvalVO.setCmplYn(schCmplYn);

		approvalVO.setRqstuserEmpno(rqstuserEmpno);
		
		int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
        int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "20"));
        
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
        
        approvalVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
        approvalVO.setEndNo(pageNo * pageSize);
        approvalVO.setSidx(sidx);
        approvalVO.setSord(sord);
		
		if("SV".equals(crudMode)){ //승인수신 목록 조회
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			result = approvalHandlingService.selectChargeVocList(approvalVO);
			intTotalCount = approvalHandlingService.selectChargeVocCount(approvalVO);
			
			mav.addObject("rows", result);
			mav.addObject("records", intTotalCount);
			mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
		}else if("AP".equals(crudMode)) { // 승인
	
			approvalVO.setApvStat("03");
			approvalVO.setApvType(apvType);
			approvalHandlingService.callApvInfoProc(approvalVO);
			
		}else if("RT".equals(crudMode)) { // 반려
	
			approvalVO.setApvStat("04");
			approvalVO.setApvType(apvType);
			approvalHandlingService.callApvInfoProc(approvalVO); // 반려처리
		}
		return mav;
	}
    
    /**
	 * 승인대기건 상세조회
	 * 
	 * @param request
	 * @param reponse
	 */
    @RequestMapping("/common/approvalhandling/myvocapvrecvinfo.do")
	public ModelAndView myvocapvrecvinfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String crudMode		 = StringUtil.getParam(request, "crudMode", "S");
    	String conapvEmpno	 = StringUtil.getParam(request, "conapvEmpno", "");
    	String cmplYn	 = StringUtil.getParam(request, "cmplYn", "");
    	
		// 로그인 사용자정보 가져오기
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();

		ModelAndView mav = new ModelAndView("common/approvalhandling/myvocapvrecvinfo");

		int intTotalCount = 0;
		List result = null;
		
		ApprovalVO approvalVO = new ApprovalVO();
		bind(request, approvalVO);
		
		int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
        int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "5"));
        
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
        
        approvalVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
        approvalVO.setEndNo(pageNo * pageSize);
        approvalVO.setSidx(sidx);
        approvalVO.setSord(sord);
		
		if("SA".equals(crudMode)){ //승인정보 조회
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			result = approvalHandlingService.selectApvStag(approvalVO);
			
			if(result.size()>0){
				intTotalCount = result.size();
			}
			
			mav.addObject("rows", result);
			mav.addObject("records", intTotalCount);
			mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
			
			return mav;
		}else if("SV".equals(crudMode)){
			approvalVO.setConapvEmpno(conapvEmpno);
			
			ApprovalVO resultVO = approvalHandlingService.selectChargeVoc(approvalVO);

			String AttachFileName = approvalVO.getApvId();
			List files = attachfileService.getAttachFileList(AttachFileName, StaticConfig.ATTACH_VOC_CODE);
			
			mav.addObject("files", files);
			mav.addObject("result", resultVO);//승인 정보
			return mav;
		}
        
		result = approvalHandlingService.selectApvStag(approvalVO);
		ApprovalVO result0 = (ApprovalVO) result.get(0);
		
		approvalVO.setConapvEmpno(result0.getApvuserEmpno());
		approvalVO.setApvStag(result0.getApvStag());
		
		ApprovalVO resultVO = approvalHandlingService.selectChargeVoc(approvalVO);
		String AttachFileName = approvalVO.getApvId();
		
		List files = attachfileService.getAttachFileList(AttachFileName, StaticConfig.ATTACH_VOC_CODE);
		resultVO.setCmplYn(cmplYn);
		
		mav.addObject("files", files);
		mav.addObject("result", resultVO);//승인 정보
		
		return mav;
	}

    /**
	 * 승인대기건 제안상세조회
	 * 
	 * @param request
	 * @param reponse
	 */
    @RequestMapping("/common/approvalhandling/myvocapvrecvreqinfo.do")
	public ModelAndView myvocapvrecvreqinfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String apvId = StringUtil.getParam(request, "apvId", "");
    	String apvuserOpi = StringUtil.getParam(request, "apvuserOpi", "");
    	String rqstuserEmpno = StringUtil.getParam(request, "rqstuserEmpno", ""); 
    	
    	// 로그인 사용자정보 가져오기
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();

		ModelAndView mav = new ModelAndView("common/approvalhandling/myvocapvrecvreqinfo");
		
		ApprovalVO approvalVO = new ApprovalVO();
		bind(request, approvalVO);
		
		
		ApprovalVO resultVO = approvalHandlingService.selectChargeSug(approvalVO);
		
		String AttachFileName = approvalVO.getApvId();
		List files = attachfileService.getAttachFileList(AttachFileName, StaticConfig.ATTACH_VOC_CODE);

		mav.addObject("files", files);
		mav.addObject("result", resultVO);//승인 정보
			
        
		return mav;
	}
    
	/**
	 * 승인 발신 진행건 조회
	 * 
	 * @param request
	 * @param reponse
	 */
    @RequestMapping("/common/approvalhandling/myvocapvdspt.do")
    
	public ModelAndView myvocapvdspt(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String crudMode 	= StringUtil.getParam(request, "crudMode", "S");
    	String apvId 		= StringUtil.getParam(request, "apvId", "");
    	String apvType 		= StringUtil.getParam(request, "apvType", "");
    	
    	String schApvType 	= StringUtil.getParam(request, "schApvType", "");
    	String schStartDd   = StringUtil.getParam(request, "schStartDd", "").replace("-", "");
    	String schEndDd     = StringUtil.getParam(request, "schEndDd", "").replace("-", "");
    	String seqno 		= StringUtil.getParam(request, "seqno", "");
    	String schCmplYn 	= StringUtil.getParam(request, "schCmplYn", "");
    	String schApvStat 	= StringUtil.getParam(request, "schApvStat", "");
    	
    	//조건 일자가 없다면 1주일셋팅
        SYSTEM system   =   SYSTEM.getInstance();
        String today    =   StringUtil.getParam(request, "YYYYMMDD", system.getDate());
        
        if( schStartDd == "" && schEndDd == ""){
            schStartDd = DateTimeUtil.calculateDate(today,-30);
            schEndDd = today;
        }
        
		// 로그인 사용자정보 가져오기
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();

		ModelAndView mav = new ModelAndView("common/approvalhandling/myvocapvdspt");

		ApprovalVO tbvcApvInfo = new ApprovalVO();
		
		tbvcApvInfo.setRqstuserEmpno(userEmpno);
		tbvcApvInfo.setSchStartDd(schStartDd);
		tbvcApvInfo.setSchEndDd(schEndDd);
		tbvcApvInfo.setSeqno(seqno);
		tbvcApvInfo.setApvType(apvType);
		
		int intTotalCount = 0;
		List result = null;

		int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
		int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "5"));

        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
        
		tbvcApvInfo.setStartNo(((pageNo * pageSize) - pageSize) + 1);
		tbvcApvInfo.setEndNo(pageNo * pageSize);
		tbvcApvInfo.setSidx(sidx);
		tbvcApvInfo.setSord(sord);
		
		if("SV".equals(crudMode)){ //승인발신 목록 조회
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			tbvcApvInfo.setCmplYn(schCmplYn);
			tbvcApvInfo.setApvStat(schApvStat);
			tbvcApvInfo.setApvType(schApvType);
			
			result = approvalHandlingService.selectChargeVocList(tbvcApvInfo);
			intTotalCount = approvalHandlingService.selectChargeVocCount(tbvcApvInfo);
			
			mav.addObject("rows", result);
			mav.addObject("records", intTotalCount);
			mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
		
		}
		
		return mav;
	}

    /**
	 * 승인진행건 조회
	 * 
	 * @param request
	 * @param reponse
	 */
    @RequestMapping("/common/approvalhandling/myvocapvdsptinfo.do")
	public ModelAndView myvocapvdsptinfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String crudMode 	 = StringUtil.getParam(request, "crudMode", "S");
    	String apvId		 = StringUtil.getParam(request, "apvId", "");
    	String seqno 		 = StringUtil.getParam(request, "seqno", "");
    	String apvStag 		 = StringUtil.getParam(request, "apvStag", "");
    	String conapvEmpno	 = StringUtil.getParam(request, "conapvEmpno", "");
    	
		// 로그인 사용자정보 가져오기
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();

		ModelAndView mav = new ModelAndView("common/approvalhandling/myvocapvdsptinfo");

		ApprovalVO tbvcApvInfo = new ApprovalVO();
		tbvcApvInfo.setRqstuserEmpno(userEmpno);
		tbvcApvInfo.setSeqno(seqno);
		tbvcApvInfo.setApvStag(apvStag);
		tbvcApvInfo.setApvId(apvId);
		
		int intTotalCount = 0;
		List result = null;

		int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
		int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "5"));

        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
        
		tbvcApvInfo.setStartNo(((pageNo * pageSize) - pageSize) + 1);
		tbvcApvInfo.setEndNo(pageNo * pageSize);
		tbvcApvInfo.setSidx(sidx);
		tbvcApvInfo.setSord(sord);
		
		if("SA".equals(crudMode)){ //승인정보 조회
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			result = approvalHandlingService.selectApvStag(tbvcApvInfo);
			
			if(result.size()>0){
				intTotalCount = result.size();
			}
			
			mav.addObject("rows", result);
			mav.addObject("records", intTotalCount);
			mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
			return mav;
		}else if("SV".equals(crudMode)){
			tbvcApvInfo.setConapvEmpno(conapvEmpno);
			
			ApprovalVO resultVO = approvalHandlingService.selectChargeVoc(tbvcApvInfo);
			String AttachFileName = tbvcApvInfo.getApvId();
			
			List files = attachfileService.getAttachFileList(AttachFileName, StaticConfig.ATTACH_VOC_CODE);
			
			mav.addObject("files", files);
			mav.addObject("result", resultVO);//승인 정보
			return mav;
		}
		
		result = approvalHandlingService.selectApvStag(tbvcApvInfo);
		ApprovalVO result0 = (ApprovalVO) result.get(0);
		
		tbvcApvInfo.setConapvEmpno(result0.getApvuserEmpno());
		tbvcApvInfo.setApvStag(result0.getApvStag());
		
		ApprovalVO resultVO = approvalHandlingService.selectChargeVoc(tbvcApvInfo);
		String AttachFileName = tbvcApvInfo.getApvId();
		
		List files = attachfileService.getAttachFileList(AttachFileName, StaticConfig.ATTACH_VOC_CODE);
		
		mav.addObject("files", files);
		mav.addObject("result", resultVO);//승인 정보
		
		return mav;
	}


    /**
	 * 승인진행건 조회
	 * 
	 * @param request
	 * @param reponse
	 */
    @RequestMapping("/common/approvalhandling/myvocapvdsptreqinfo.do")
	public ModelAndView myvocapvdsptreqinfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String apvId 	= StringUtil.getParam(request, "apvId", "");
    	
		// 로그인 사용자정보 가져오기
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();

		ModelAndView mav = new ModelAndView("common/approvalhandling/myvocapvdsptreqinfo");

		int intTotalCount = 0;
		List result = null;
		
		ApprovalVO approvalVO = new ApprovalVO();
		approvalVO.setApvId(apvId);
		approvalVO.setRqstuserEmpno(userEmpno);
		
		ApprovalVO resultVO = approvalHandlingService.selectChargeSug(approvalVO);
		
		String AttachFileName = approvalVO.getApvId();
		List files = attachfileService.getAttachFileList(AttachFileName, StaticConfig.ATTACH_VOC_CODE);

		mav.addObject("files", files);
		mav.addObject("result", resultVO);//승인 정보
			
		return mav;
	}
}
