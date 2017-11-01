package nds.core.systemsettings.button.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.systemsettings.button.service.BtnVO;
import nds.core.systemsettings.button.service.ButtonService;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: ButtonController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */

@Controller
public class ButtonController extends BaseController {
	/** ButtonService */
    @Resource(name = "buttonService")
    private ButtonService buttonService;
    /** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService; 
    
    /**
     * 버튼관리
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/systemsettings/button/syset0300.do")
    public ModelAndView syset0300(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        
        BtnVO btnVO = new BtnVO();
        bind(request, btnVO);
        
        if(!"".equals(btnVO.getImgeCd())) {
        	btnVO.setImgeCd(StringUtil.textToHtml(btnVO.getImgeCd(), true));
        }
        
        // 로그인 사용자정보 가져오기
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();

        ModelAndView mav = new ModelAndView("systemsettings/button/syset0300");

        if(crudMode.equals("C")) {
            btnVO.setRegUser(userEmpno);
            btnVO.setUpdtUser(userEmpno);
            buttonService.insertButton(btnVO);
            mav.addObject("crudMode", "");
        }else if(crudMode.equals("S")){
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
        }
        else if(crudMode.equals("U")) {
            btnVO.setUpdtUser(userEmpno);
            buttonService.updateButton(btnVO);
            mav.addObject("crudMode", "");
        }
        else if(crudMode.equals("D")) {
        	buttonService.deleteButton(btnVO);
            mav.addObject("crudMode", "");
        }
               

        BtnVO key = new BtnVO();

        key.setBtnNm("%" + StringUtil.getParam(request, "schbtnNm", "").toUpperCase() + "%");
        int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
        int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "5"));
        
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");

        key.setStartNo(((pageNo * pageSize) - pageSize) + 1);
        key.setEndNo(pageNo * pageSize);
        key.setSidx(sidx);
        key.setSord(sord);

        List result = buttonService.selectButton(key);
        int intTotalCount = buttonService.selectButtonCount(key);
        
        // 컬럼 설정 조회
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("syset0300");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
		mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
        mav.addObject("colResult", colResult);
        mav.addObject("rows", result);
        mav.addObject("records", intTotalCount);
        
        return mav;
    }

    /**
     * 버튼코드중복체크(Ajax)
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/systemsettings/button/syset0301_ajax.do")
    public ModelAndView syset0301_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String btnId = StringUtil.getParam(request, "btnId", "");

        BtnVO btnVO = new BtnVO();
        btnVO.setBtnId(btnId.toUpperCase());
        int intTotalCount = buttonService.selectButtonIdCount(btnVO);

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
        mav.addObject("intTotalCount", intTotalCount);

        return mav;
    }
    
    @RequestMapping("/systemsettings/button/syset0310_pop.do")
    public ModelAndView syset0310_pop(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView mav = new ModelAndView("systemsettings/button/syset0310_pop");
    	
    	return mav;
    }
}
