package nds.core.systemsettings.service.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.systemsettings.service.service.VocServiceService;
import nds.core.systemsettings.service.service.VocServiceVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: VocServiceController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2015.04.27 초기작성(임기범)</pre>
 * @author <a href="mailto:kblim@nds.co.kr">임기범</a>
 * @version 1.0
 */

@Controller
public class VocServiceController extends BaseController {

	/** VocServiceService */
    @Resource(name = "vocServiceService")
    private VocServiceService vocServiceService;
    
    /** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
	
	
	 @RequestMapping("/systemsettings/service/syset0700.do")
	 public ModelAndView syset0700(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		 
		String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();

        ModelAndView mav = new ModelAndView("systemsettings/service/syset0700");
        
        if("S".equals(crudMode)) {
        	
        }
        
        // 컬럼 설정 조회
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("syset0700");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
        
        return mav;
		 
	 }
	 
	    /**
	     * (Ajax)VOC서비스
	     * 
	     * @param request
	     * @param reponse
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping("/systemsettings/service/syset0701_ajax.do")  
	    public ModelAndView syset0701_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {

			ModelAndView mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");

			
			String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();

			VocServiceVO vocServiceVO = new VocServiceVO();
	        // request정보를 담는다.
	        bind(request, vocServiceVO);

	        // 로그인 사용자정보 가져오기
	        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
	        String userId = userSession.getLogin().getUserEmpno();

	        if(crudMode.equals("U")) {
	        	vocServiceVO.setUpdtUser(userId);
	            vocServiceService.updateService(vocServiceVO);
	        }
	        else if (crudMode.equals("S"))
	        {
	        	VocServiceVO key = new VocServiceVO();
		        key.setOrderByClause("A.SERV_NM DESC, A.SERV_NO");
		        
		        List result = vocServiceService.selectService(key);
		        int intTotalCount1 = vocServiceService.selectServiceCount(key);
		        
		        mav.addObject("total", ((intTotalCount1-10)/10)+1);
		        mav.addObject("records", intTotalCount1);
		        
		        
		        VocServiceVO key2 = new VocServiceVO();
		        key2.setServStop("N");
		        int intTotalCount2 = vocServiceService.selectServiceCount(key2);
		        mav.addObject("intExecCount", intTotalCount2);

		        VocServiceVO key3 = new VocServiceVO();
		        key3.setSrvrStat("1");
		        int intTotalCount3 = vocServiceService.selectServiceCount(key3);
		        
		        if(intTotalCount3 > 0) {
		        	
		        	for(int x=0; x<result.size();x++  ) {
		        		((VocServiceVO)result.get(x)).setStatCd("00");
		        	}
		        }
		        else {
		        	
		        	for(int x=0; x<result.size();x++  ) {
		        		((VocServiceVO)result.get(x)).setStatCd("99");
		        	}
		        }
		        
		        mav.addObject("rows", result);
		        
	        }
	        
	        
	        return mav;
	    }
	 
}
