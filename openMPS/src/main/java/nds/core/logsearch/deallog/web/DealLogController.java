package nds.core.logsearch.deallog.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.logsearch.deallog.service.DealLogService;
import nds.core.logsearch.deallog.service.DealLogVO;
import nds.frm.startup.SYSTEM;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: DealLogController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */

@Controller
public class DealLogController extends BaseController {
	/** DealLogService */
    @Resource(name = "dealLogService")
    private DealLogService dealLogService;
    /** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService; 

    /**
     * 예외로그 조회
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/logsearch/deallog/logsh0200.do")
	public ModelAndView logsh0200(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
    	String schChrgUserId = StringUtil.getParam(request, "schChrgUserId", "");
    	String schChrgDepCd = StringUtil.getParam(request, "schChrgDepCd", "");
    	String schContsNm = StringUtil.getParam(request, "schContsNm", "");
        String year = SYSTEM.getInstance().getYear();
        String month = SYSTEM.getInstance().getMonth();
        String day = SYSTEM.getInstance().getDay();
        
        String schStartDd = StringUtil.getParam(request, "schStartDd", year + month + day);
        String schEndDd   = StringUtil.getParam(request, "schEndDd", year + month + day);

        schStartDd = StringUtil.delChar(schStartDd);
        schEndDd = StringUtil.delChar(schEndDd);
        
        int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
        int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "15"));

        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
        
        ModelAndView mav = new ModelAndView("logsearch/deallog/logsh0200");

        DealLogVO dealLogVO = new DealLogVO();
        bind(request, dealLogVO);
        
        dealLogVO.setSchStartDd(schStartDd);
        dealLogVO.setSchEndDd(schEndDd);
        dealLogVO.setUserEmpNo(schChrgUserId);
        dealLogVO.setDepNo(schChrgDepCd);
        dealLogVO.setSchContsNm(schContsNm);
        
        dealLogVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
        dealLogVO.setEndNo(pageNo * pageSize);
        dealLogVO.setSidx(sidx);
        dealLogVO.setSord(sord);
        
        if("S".equals(crudMode)) {
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
    		
        }
        
        List<DealLogVO> result = dealLogService.selectExcpLogInfoList(dealLogVO);
        int intTotalCount = dealLogService.selectExcpLogInfoListCount(dealLogVO);
        
        // 컬럼 설정 조회
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("logsh0200");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
        mav.addObject("rows", result);
        mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));  // total page
        mav.addObject("records", intTotalCount);
        
        return mav;
    }
}
