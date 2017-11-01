package nds.core.operation.approval.web;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.operation.approval.service.ApprovalService;
import nds.core.operation.approval.service.ApprovalVO;
import nds.core.userdep.department.service.DepartMentService;
import nds.core.userdep.department.service.DepartMentVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: ApprovalController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.26 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Controller
public class ApprovalController extends BaseController {
	
	/** ApprovalService */
    @Resource(name = "approvalService")
    private ApprovalService approvalService;
	/** ApprovalService */
    @Resource(name = "departMentService")
    private DepartMentService departMentService;
    
    /**
     * 승인단계 관리
     * @param request
     * @param reponse
     */
    @RequestMapping("/operation/approval/oprat0600.do")
    public ModelAndView oprat0600(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String crudMode = StringUtil.getParam(request, "crudMode");
		String[] apvStag = request.getParameterValues("apvStagCd");
		String[] mndtYn = request.getParameterValues("mndtYnCd");
		String[] pstNm = request.getParameterValues("pstNm");
		String[] pstCd = request.getParameterValues("pstCd");
		String[] apvuserEmpno = request.getParameterValues("apvuserEmpno");

		ModelAndView mav = new ModelAndView("operation/approval/oprat0600");

		// 로그인 사용자정보 가져오기
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();
		String userId = userSession.getLogin().getUserId();

		int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
        int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));
        
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
       
        ApprovalVO approvalVO = new ApprovalVO();
        
        approvalVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
        approvalVO.setEndNo(pageNo * pageSize);
        approvalVO.setSidx(sidx);
        approvalVO.setSord(sord);
        
		if ("C".equals(crudMode)) {
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			List<ApprovalVO> apvInsertList = new ArrayList<ApprovalVO>();
			for(int i=0;i<apvStag.length;i++){
				approvalVO = new ApprovalVO();
				approvalVO.setUserEmpno(userEmpno);
				approvalVO.setRegUser(userEmpno);
				approvalVO.setUpdtUser(userEmpno);
				approvalVO.setApvStag(apvStag[i]);
				approvalVO.setApvuserEmpno(apvuserEmpno[i]);
				approvalVO.setMndtYn(mndtYn[Integer.parseInt(apvStag[i])-1]);
				approvalVO.setPstCd(pstCd[i]);
				approvalVO.setPstNm(pstNm[i]);
				apvInsertList.add(approvalVO);
			}

			approvalService.insertApvMg(apvInsertList);
			
			return mav;
		} else if ("AD".equals(crudMode)) {
			approvalService.deleteAllApvMg(userEmpno);
			return mav;
			//return new ModelAndView("success", "forwardAction", "op/oprat0600.voc?crudMode=S");
		} else if ("S".equals(crudMode)) {
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			List<ApprovalVO> result = new ArrayList<ApprovalVO>();
			int intTotalCount = 0;
			
			result = approvalService.selectApvMgList(userEmpno);
			intTotalCount  = approvalService.selectApvMgListCount(userEmpno);
			
	        mav.addObject("rows", result);
	        mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
	        
			return mav;
		} else if ("D".equals(crudMode)) {
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			if (null!=apvStag) {
				approvalVO.setUserEmpno(userEmpno);
				approvalVO.setApvStag(apvStag[0]);
				approvalVO.setApvuserEmpno(apvuserEmpno[0]);
				
				approvalService.deleteApvMg(approvalVO);
			}
			
			
			return mav;
		} else if ("US".equals(crudMode)) {
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			String depCd = StringUtil.getParam(request, "depCd", "");
			
	        List<ApprovalVO> result = null;
	        int intTotalCount  = 0;

	        approvalVO.setUserEmpno(userEmpno);
	        approvalVO.setUserId(userId);
	        approvalVO.setDepCd(depCd);
	        
	        result = approvalService.selectUserList(approvalVO);
	        intTotalCount  = approvalService.selectUserListCount(approvalVO);

	        mav.addObject("rows", result);
	        mav.addObject("records", intTotalCount);
	        mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
	        
			return mav;
		} else if ("AS".equals(crudMode)) {
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			List<ApprovalVO> result = new ArrayList<ApprovalVO>();
			int intTotalCount = 0;
			
			result = approvalService.selectApvStagList(userEmpno);
			intTotalCount  = approvalService.selectApvStagListCount(userEmpno);
			
	        mav.addObject("rows", result);
	        mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
		} else {
			DepartMentVO org = new DepartMentVO();
	        List result = departMentService.selectOrganization(org);
			
			mav.addObject("result", result);
		}
		
        
		return mav;
    }

}
