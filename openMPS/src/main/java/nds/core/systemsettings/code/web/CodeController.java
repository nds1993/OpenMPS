package nds.core.systemsettings.code.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.systemsettings.code.service.CodeService;
import nds.core.systemsettings.code.service.CodeVO;
import nds.frm.util.StringUtil;


/**
 * <p>Title: CodeController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@NDS.co.kr">김태호</a>
 * @version 1.0
 */
@Controller
public class CodeController extends BaseController {
	/** LoginService */
    @Resource(name = "codeService")
    private CodeService codeService;
    
    
    /**
     * 공통코드관리
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping(value="/systemsettings/code/syset0100.do")
    public ModelAndView syset0100(HttpServletRequest request, HttpServletResponse reponse) throws Exception {

        String crudMode = StringUtil.getParam(request, "crudMode", "S").toUpperCase();

        ModelAndView mav = new ModelAndView("systemsettings/code/syset0100");

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");

        CodeVO codeVO = new CodeVO();
        bind(request, codeVO);

        if("C".equals(crudMode)) {
            codeVO.setRegUser(userSession.getLogin().getUserEmpno());
            codeVO.setUpdtUser(userSession.getLogin().getUserEmpno());
            codeService.insertCode(codeVO);
            crudMode = "S";
            
            if("F003".equals(codeVO.getCdId())) {
            	this.writeXmlCommCd(this.getCommonCodeList("F003"));
            }
        }
        else
            if("U".equals(crudMode)) {
                codeVO.setUseYn(StringUtil.getParam(request, "useYn", "N").toUpperCase());
                codeVO.setUpdtUser(userSession.getLogin().getUserEmpno());
                codeService.updateCode(codeVO);
                crudMode = "S";
                
                if("F003".equals(codeVO.getCdId())) {
                	this.writeXmlCommCd(this.getCommonCodeList("F003"));
                }
            }
            else if("D".equals(crudMode)) {
                CodeVO key = new CodeVO();
                bind(request, key);
                codeService.deleteCodeTree(key);
                crudMode = "S";
                
                if("F003".equals(codeVO.getCdId())) {
                	this.writeXmlCommCd(this.getCommonCodeList("F003"));
                }
            }
            else if("XLS".equals(crudMode)) {
                CodeVO key = new CodeVO();
                String cdKnm = StringUtil.getParam(request, "schcdKnm", "");
                String useYn = StringUtil.getParam(request, "schuseYn", "");
                key.setCdKnm(cdKnm);
                key.setUseYn(useYn);
                mav = new ModelAndView("codes_xlsView", "result", codeService.selectCodeTree(key));
                return mav;
            }
            else if("PDF".equals(crudMode)) {
                CodeVO key = new CodeVO();
                String cdKnm = StringUtil.getParam(request, "schcdKnm", "");
                String useYn = StringUtil.getParam(request, "schuseYn", "");
                key.setCdKnm(cdKnm);
                key.setUseYn(useYn);
                mav = new ModelAndView("codes_pdfView", "result", codeService.selectCodeTree(key));
                return mav;
            }

        if("S".equals(crudMode)) {
            CodeVO key = new CodeVO();
            String cdKnm = StringUtil.getParam(request, "schcdKnm", "");
            String useYn = StringUtil.getParam(request, "schuseYn", "");
            key.setCdKnm(cdKnm);
            key.setUseYn(useYn);
            
            List<CodeVO> list =  codeService.selectCodeTree(key); 

            mav.addObject("result", list);
        }
        mav.addObject("F153", this.getCommonCode("F153"));
        return mav;
    }

    /**
     * (Ajax)기초코드중복체크
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/systemsettings/code/syset0101_ajax.do")
    public ModelAndView syset0101_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");

        CodeVO codeVO = new CodeVO();
        bind(request, codeVO);

        int intTotalCount = codeService.selectCodeCnt(codeVO);

        mav.addObject("intTotalCount", intTotalCount);

        return mav;
    }

}
