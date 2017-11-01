package nds.core.systemsettings.extention.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.systemsettings.extention.service.ExtentionService;
import nds.core.systemsettings.extention.service.ExtentionVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

@Controller
public class ExtentionController extends BaseController{
	/** ExtentionService */
    @Resource(name = "extentionService")
    private ExtentionService extentionService;
    /** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService; 
    
    /**
     * 파일확장자관리
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/systemsettings/extention/syset0800.do")
    public ModelAndView syset0800(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        String enFileExt = StringUtil.getParam(request, "enFileExt", "");
        
        ExtentionVO extentionVO = new ExtentionVO();
        bind(request, extentionVO);
        
        // 로그인 사용자정보 가져오기
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();

        ModelAndView mav = new ModelAndView("systemsettings/extention/syset0800");

        if(crudMode.equals("C")) {
        	extentionVO.setRegUser(userEmpno);
        	extentionVO.setUpdtUser(userEmpno);
            extentionService.insertExtention(extentionVO);
            mav.addObject("crudMode", "");
        }else if(crudMode.equals("S")){
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
        }
        else if(crudMode.equals("U")) {
        	extentionVO.setUpdtUser(userEmpno);
            extentionService.updateExtention(extentionVO);
            mav.addObject("crudMode", "");
        }
        else if(crudMode.equals("D")) {
        	extentionService.deleteExtention(extentionVO);
            mav.addObject("crudMode", "");
        }
               

        ExtentionVO key = new ExtentionVO();

//        key.setBtnNm("%" + StringUtil.getParam(request, "schbtnNm", "").toUpperCase() + "%");
        
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");

        key.setSidx(sidx);
        key.setSord(sord);

        List result = extentionService.selectExtention(key);
        int intTotalCount = extentionService.selectExtentionCount(key);
        
        // 컬럼 설정 조회
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("syset0800");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        mav.addObject("colResult", colResult);
        mav.addObject("rows", result);
        mav.addObject("records", intTotalCount);
        
        return mav;
    }

    /**
     * 파일 확장자 중복체크(Ajax)
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/systemsettings/extention/syset0801_ajax.do")
    public ModelAndView syset0801_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileExt = StringUtil.getParam(request, "fileExt", "");

        ExtentionVO extentionVO = new ExtentionVO();
        extentionVO.setFileExt(fileExt);
        int intTotalCount = extentionService.selectExtentionIdCount(extentionVO);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
        mav.addObject("intTotalCount", intTotalCount);

        return mav;
    }
}
