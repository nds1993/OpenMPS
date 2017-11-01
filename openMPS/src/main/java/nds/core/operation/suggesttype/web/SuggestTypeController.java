package nds.core.operation.suggesttype.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.operation.suggesttype.service.SuggestTypeService;
import nds.core.operation.suggesttype.service.SuggestTypeVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: SuggestTypeController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2015.08.20 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */

@Controller
public class SuggestTypeController extends BaseController {
	/** SuggestTypeService */
    @Resource(name = "suggestTypeService")
    private SuggestTypeService suggestTypeService;
    
    /**
     * 제안유형관리
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/operation/suggesttype/oprat1200.do")
    public ModelAndView oprat1200(HttpServletRequest request, HttpServletResponse reponse) throws Exception {

        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        String code = StringUtil.getParam(request, "code", "").toUpperCase();
        String type = StringUtil.getParam(request, "type", "").toUpperCase();

        ModelAndView mav = new ModelAndView("operation/suggesttype/oprat1200");
        
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");

        SuggestTypeVO suggestTypeVO = new SuggestTypeVO();
        suggestTypeVO.setCode(code);
        suggestTypeVO.setType(type);

        if("CM".equals(crudMode)) {
        	String mcls = StringUtil.getParam(request, "mcls", "");
        	String mclsNm = StringUtil.getParam(request, "mclsNm", "");
        	String useYnMcls = StringUtil.getParam(request, "useYnMcls", "");
        	String ordMcls = StringUtil.getParam(request, "ordMcls", "");
        	
        	suggestTypeVO.setLcls(mcls);
        	suggestTypeVO.setMcls("00");
        	suggestTypeVO.setScls("00");
        	suggestTypeVO.setLvl("1");
        	suggestTypeVO.setTypeCatNm(mclsNm);
        	suggestTypeVO.setUseYn(useYnMcls);
        	suggestTypeVO.setOrd(ordMcls);
            suggestTypeVO.setRegUser(userSession.getLogin().getUserEmpno());
            suggestTypeVO.setUpdtUser(userSession.getLogin().getUserEmpno());
            suggestTypeService.insertSuggestType(suggestTypeVO);
        } else if("CS".equals(crudMode)) {
        	String sclsMcls = StringUtil.getParam(request, "sclsMcls", "");
        	String scls = StringUtil.getParam(request, "scls", "");
        	String sclsNm = StringUtil.getParam(request, "sclsNm", "");
        	String useYnScls = StringUtil.getParam(request, "useYnScls", "");
        	String ordScls = StringUtil.getParam(request, "ordScls", "");
        	
        	suggestTypeVO.setLcls(sclsMcls);
        	suggestTypeVO.setMcls(scls);
        	suggestTypeVO.setScls("00");
        	suggestTypeVO.setLvl("2");
        	suggestTypeVO.setTypeCatNm(sclsNm);
        	suggestTypeVO.setUseYn(useYnScls);
        	suggestTypeVO.setOrd(ordScls);
            suggestTypeVO.setRegUser(userSession.getLogin().getUserEmpno());
            suggestTypeVO.setUpdtUser(userSession.getLogin().getUserEmpno());
            suggestTypeService.insertSuggestType(suggestTypeVO);
        } else if("UM".equals(crudMode)) {
        	String mcls = StringUtil.getParam(request, "mcls", "");
        	String mclsNm = StringUtil.getParam(request, "mclsNm", "");
        	String useYnMcls = StringUtil.getParam(request, "useYnMcls", "");
        	String ordMcls = StringUtil.getParam(request, "ordMcls", "");
        	
        	suggestTypeVO.setLcls(mcls);
        	suggestTypeVO.setMcls("00");
        	suggestTypeVO.setScls("00");
        	
        	suggestTypeVO.setTypeCatNm(mclsNm);
        	suggestTypeVO.setUseYn(useYnMcls);
        	suggestTypeVO.setOrd(ordMcls);
            suggestTypeVO.setUpdtUser(userSession.getLogin().getUserEmpno());
            suggestTypeService.updateSuggestType(suggestTypeVO);
        } else if("US".equals(crudMode)) {
        	String sclsMcls = StringUtil.getParam(request, "sclsMcls", "");
        	String scls = StringUtil.getParam(request, "scls", "");
        	String sclsNm = StringUtil.getParam(request, "sclsNm", "");
        	String useYnScls = StringUtil.getParam(request, "useYnScls", "");
        	String ordScls = StringUtil.getParam(request, "ordScls", "");
        	
        	suggestTypeVO.setLcls(sclsMcls);
        	suggestTypeVO.setMcls(scls);
        	suggestTypeVO.setScls("00");
        	
        	suggestTypeVO.setTypeCatNm(sclsNm);
        	suggestTypeVO.setUseYn(useYnScls);
        	suggestTypeVO.setOrd(ordScls);
            suggestTypeVO.setUpdtUser(userSession.getLogin().getUserEmpno());
            suggestTypeService.updateSuggestType(suggestTypeVO);
        } else if("DM".equals(crudMode)) {
        	String mcls = StringUtil.getParam(request, "mcls", "");
        	
        	suggestTypeVO.setLcls(mcls);
        	suggestTypeVO.setMcls("00");
        	suggestTypeVO.setScls("00");
            suggestTypeService.deleteSuggestType(suggestTypeVO);
        } else if("DS".equals(crudMode)) {
        	String sclsMcls = StringUtil.getParam(request, "sclsMcls", "");
        	String scls = StringUtil.getParam(request, "scls", "");
        	
        	suggestTypeVO.setLcls(sclsMcls);
        	suggestTypeVO.setMcls(scls);
        	suggestTypeVO.setScls("00");
            suggestTypeService.deleteSuggestType(suggestTypeVO);
        } else if("SM".equals(crudMode)) {
        	mav = new ModelAndView("jsonView");
			reponse.setContentType("application/json; charset=utf-8");
			reponse.setHeader("Cache-Control", "no-cache");
			
            String schMclsNm = StringUtil.getParam(request, "schMclsNm", "");
            
            suggestTypeVO.setLcls("");
            suggestTypeVO.setMcls("");
            suggestTypeVO.setScls("");
            suggestTypeVO.setTypeCatNm(schMclsNm);
            suggestTypeVO.setLvl("1");
            
            mav.addObject("rows", suggestTypeService.selectPropTypeList(suggestTypeVO));
            return mav;
        } else if("SS".equals(crudMode)) {
        	mav = new ModelAndView("jsonView");
			reponse.setContentType("application/json; charset=utf-8");
			reponse.setHeader("Cache-Control", "no-cache");
			
            String schSclsNm = StringUtil.getParam(request, "schSclsNm", "");
            String selectedMcls = StringUtil.getParam(request, "selectedMcls", "00");
            
            suggestTypeVO.setLcls(selectedMcls);
            suggestTypeVO.setMcls("");
            suggestTypeVO.setScls("00");
            suggestTypeVO.setTypeCatNm(schSclsNm);
            suggestTypeVO.setLvl("2");
            
            mav.addObject("rows", suggestTypeService.selectPropTypeList(suggestTypeVO));
            return mav;
        }
        return mav;
    }
    
    /**
     * (Ajax)제안유형관리코드중복체크
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/operation/suggesttype/oprat1201_ajax.do")
    public ModelAndView oprat1201_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
 
        SuggestTypeVO suggestTypeVO = new SuggestTypeVO();
        bind(request, suggestTypeVO);

        int intTotalCount = suggestTypeService.selectCnslCodeCnt(suggestTypeVO);

        mav.addObject("intTotalCount", intTotalCount);

        return mav;
    }
}
