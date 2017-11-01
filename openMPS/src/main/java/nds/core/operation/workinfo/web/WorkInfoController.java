package nds.core.operation.workinfo.web;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.operation.workinfo.service.WorkInfoService;
import nds.core.operation.workinfo.service.WorkInfoVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: WorkInfoController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.26 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Controller
public class WorkInfoController extends BaseController {

	/** MyMenuService */
	@Resource(name = "workInfoService")
	private WorkInfoService workInfoService;
	 /** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService; 

	/**
	 * 담당업무정보
	 * @param request
	 * @param reponse
	 */
	@RequestMapping("/ba/bainf0400.do")
	public ModelAndView bainf0400(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String crudMode      = StringUtil.getParam(request, "crudMode", "");
		String schDepCd      = StringUtil.getParam(request, "schDepCd", "");
		String schUserId     = StringUtil.getParam(request, "schUserId", "");
		String schAdviserYn  = StringUtil.getParam(request, "schAdviserYn", "");
		String schCnfmYn     = StringUtil.getParam(request, "schCnfmYn", "");

		ModelAndView mav = new ModelAndView("ba/bainf0400");

		WorkInfoVO tbvcUsr = new WorkInfoVO();
		tbvcUsr.setDepCd(schDepCd);
		tbvcUsr.setUserId(schUserId);
		tbvcUsr.setAdviserYn(schAdviserYn);
		tbvcUsr.setCnfmYn(schCnfmYn);

		if("".equals(crudMode)){
			UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
			schDepCd = userSession.getLogin().getDepCd();
			tbvcUsr.setDepCd(schDepCd);
		}else if("S".equals(crudMode)){
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
        }
		int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
		int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "15"));
		tbvcUsr.setStartNo(((pageNo * pageSize) - pageSize) + 1);
		tbvcUsr.setEndNo(pageNo * pageSize);


		List<WorkInfoVO> result = workInfoService.selectUsrWrkInfo(tbvcUsr);
		int intTotalCount = workInfoService.selectUsrWrkInfoCount(tbvcUsr);
		
		 // 컬럼 설정 조회
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("bainf0200");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
		mav.addObject("F042", this.getCommonCode("F042"));
		mav.addObject("F095", this.getCommonCode("F095"));
		mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
		mav.addObject("rows", result);
		mav.addObject("records", intTotalCount);

		return mav;
	}

	/**
	 * 담당업무정보 상세내역
	 * @param request
	 * @param reponse
	 */
	@RequestMapping("/ba/bainf0410.do")
	public ModelAndView bainf0410(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String userId      = StringUtil.getParam(request, "userId", "");
		String crudMode      = StringUtil.getParam(request, "crudMode", "");
		String schDepCd      = StringUtil.getParam(request, "schDepCd", "");
		String schDepNm      = StringUtil.getParam(request, "schDepNm", "");
		String schUserId     = StringUtil.getParam(request, "schUserId", "");
		String srchName      = StringUtil.getParam(request, "srchName", "");
		String schCnfmYn     = StringUtil.getParam(request, "schCnfmYn", "");

		ModelAndView mav = new ModelAndView("ba/bainf0410");

		WorkInfoVO tbvcUsr = new WorkInfoVO(); 
		bind(request, tbvcUsr);
		tbvcUsr.setUserId(userId);

		if("U".equals(crudMode)){
			WorkInfoVO nowData = workInfoService.selectUsrWrkInfoDetail(tbvcUsr);

			tbvcUsr.setCnfmYn(nowData.getCnfmYn());
			if(!nowData.getWrkInfo().equals(tbvcUsr.getWrkInfo())){
				tbvcUsr.setCnfmYn("N");
			}
			workInfoService.updateUsrWrkInfo(tbvcUsr);
			/*mav = new ModelAndView("success_script", "scriptBlock", "alert('저장 되었습니다.');location.href='/ba/bainf0410.voc?userId="
                    +schUserId+"&schDepCd="+schDepCd+"&schCnfmYn="+schCnfmYn+"&schDepNm="+schDepNm+"&schUserId="+schUserId+"&srchName="+srchName+"';" );*/
			
			return mav;
		}

		WorkInfoVO result = workInfoService.selectUsrWrkInfoDetail(tbvcUsr);
		mav.addObject("result", result);

		return mav;
	}
}
