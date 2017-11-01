package nds.core.operation.voccnsltype.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.operation.voccnsltype.service.ScrCdService;
import nds.core.operation.voccnsltype.service.ScrCdVO;
import nds.core.operation.voccnsltype.service.VocCnslTypeService;
import nds.core.operation.voccnsltype.service.VocCnslTypeVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: VocCnslTypeController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */

@Controller
public class VocCnslTypeController extends BaseController {
	/** VocCnslTypeService */
    @Resource(name = "vocCnslTypeService")
    private VocCnslTypeService vocCnslTypeService;
    
	/** ScrCdService */
    @Resource(name = "scrCdService")
    private ScrCdService scrCdService;
    
    /**
     * VOC유형관리
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/operation/voccnsltype/oprat1100.do")
    public ModelAndView oprat1100(HttpServletRequest request, HttpServletResponse reponse) throws Exception {

        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();

        ModelAndView mav = new ModelAndView("operation/voccnsltype/oprat1100");
        
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");

        VocCnslTypeVO vocCnslTypeVO = new VocCnslTypeVO();
        
        String sidx = StringUtil.getParam(request, "sidx", "");
		String sord = StringUtil.getParam(request, "sord", "");
		
		vocCnslTypeVO.setSord(sord);
		vocCnslTypeVO.setSidx(sidx);

        if("CL".equals(crudMode)) {
        	String lcls = StringUtil.getParam(request, "lcls", "");
        	String lclsNm = StringUtil.getParam(request, "lclsNm", "");
        	String useYnLcls = StringUtil.getParam(request, "useYnLcls", "");
        	String ordLcls = StringUtil.getParam(request, "ordLcls", "");
        	
        	vocCnslTypeVO.setLcls(lcls);
        	vocCnslTypeVO.setMcls("00");
        	vocCnslTypeVO.setScls("00");
        	vocCnslTypeVO.setLvl("1");
        	vocCnslTypeVO.setCnslCatNm(lclsNm);
        	vocCnslTypeVO.setUseYn(useYnLcls);
        	vocCnslTypeVO.setOrd(ordLcls);
            vocCnslTypeVO.setRegUser(userSession.getLogin().getUserEmpno());
            vocCnslTypeVO.setUpdtUser(userSession.getLogin().getUserEmpno());
            vocCnslTypeService.insertCnslCode(vocCnslTypeVO);
        } else if("CM".equals(crudMode)) {
        	String mcls = StringUtil.getParam(request, "mcls", "");
        	String mclsNm = StringUtil.getParam(request, "mclsNm", "");
        	String useYnMcls = StringUtil.getParam(request, "useYnMcls", "");
        	String ordMcls = StringUtil.getParam(request, "ordMcls", "");
        	
        	vocCnslTypeVO.setLcls("00");
        	vocCnslTypeVO.setMcls(mcls);
        	vocCnslTypeVO.setScls("00");
        	vocCnslTypeVO.setLvl("2");
        	vocCnslTypeVO.setLclsYn("Y");
        	vocCnslTypeVO.setCnslCatNm(mclsNm);
        	vocCnslTypeVO.setUseYn(useYnMcls);
        	vocCnslTypeVO.setOrd(ordMcls);
            vocCnslTypeVO.setRegUser(userSession.getLogin().getUserEmpno());
            vocCnslTypeVO.setUpdtUser(userSession.getLogin().getUserEmpno());
            vocCnslTypeService.insertCnslCode(vocCnslTypeVO);
        } else if("CS".equals(crudMode)) {
        	String scrId = StringUtil.getParam(request, "scrId", "");
        	String scrKnm = StringUtil.getParam(request, "scrKnm", "");
        	String useYnScr = StringUtil.getParam(request, "useYnScr", "");
        	
    		ScrCdVO scrCdVO = new ScrCdVO();
        	
    		scrCdVO.setScrId(scrId);
    		scrCdVO.setScrKnm(scrKnm);
    		scrCdVO.setUseYn(useYnScr);
    		scrCdVO.setRegUser(userSession.getLogin().getUserEmpno());
    		scrCdVO.setUpdtUser(userSession.getLogin().getUserEmpno());
            
    		scrCdService.insertScrCd(scrCdVO);
        } else if("UL".equals(crudMode)) {
        	String lcls = StringUtil.getParam(request, "lcls", "");
        	String lclsNm = StringUtil.getParam(request, "lclsNm", "");
        	String useYnLcls = StringUtil.getParam(request, "useYnLcls", "");
        	String ordLcls = StringUtil.getParam(request, "ordLcls", "");
        	
        	vocCnslTypeVO.setLcls(lcls);
        	vocCnslTypeVO.setMcls("00");
        	vocCnslTypeVO.setScls("00");
        	
        	vocCnslTypeVO.setCnslCatNm(lclsNm);
        	vocCnslTypeVO.setUseYn(useYnLcls);
        	vocCnslTypeVO.setOrd(ordLcls);
            vocCnslTypeVO.setUpdtUser(userSession.getLogin().getUserEmpno());
            vocCnslTypeService.updateCnslTypeCode(vocCnslTypeVO);
        } else if("UM".equals(crudMode)) {
        	String mcls = StringUtil.getParam(request, "mcls", "");
        	String mclsNm = StringUtil.getParam(request, "mclsNm", "");
        	String useYnMcls = StringUtil.getParam(request, "useYnMcls", "");
        	String ordMcls = StringUtil.getParam(request, "ordMcls", "");
        	
        	vocCnslTypeVO.setLcls("00");
        	vocCnslTypeVO.setMcls(mcls);
        	vocCnslTypeVO.setScls("00");
        	
        	vocCnslTypeVO.setCnslCatNm(mclsNm);
        	vocCnslTypeVO.setUseYn(useYnMcls);
        	vocCnslTypeVO.setOrd(ordMcls);
            vocCnslTypeVO.setUpdtUser(userSession.getLogin().getUserEmpno());
            vocCnslTypeService.updateCnslTypeCode(vocCnslTypeVO);
        } else if("US".equals(crudMode)) {
        	String scrId = StringUtil.getParam(request, "scrId", "");
        	String scrKnm = StringUtil.getParam(request, "scrKnm", "");
        	String useYnScr = StringUtil.getParam(request, "useYnScr", "");
        	
    		ScrCdVO scrCdVO = new ScrCdVO();
    		
    		scrCdVO.setScrId(scrId);
    		scrCdVO.setScrKnm(scrKnm);
    		scrCdVO.setUseYn(useYnScr);
            vocCnslTypeVO.setUpdtUser(userSession.getLogin().getUserEmpno());
            
            scrCdService.updateScrCdCode(scrCdVO);
        } else if("DL".equals(crudMode)) {
        	String lcls = StringUtil.getParam(request, "lcls", "");
        	
        	vocCnslTypeVO.setLcls(lcls);
        	vocCnslTypeVO.setMcls("00");
        	vocCnslTypeVO.setScls("00");
            vocCnslTypeService.deleteCnslTypeTree(vocCnslTypeVO);
        } else if("DM".equals(crudMode)) {
        	String mcls = StringUtil.getParam(request, "mcls", "");
        	
        	vocCnslTypeVO.setLcls("00");
        	vocCnslTypeVO.setMcls(mcls);
        	vocCnslTypeVO.setScls("00");
            vocCnslTypeService.deleteCnslTypeTree(vocCnslTypeVO);
        } else if("DS".equals(crudMode)) {
        	String scrId = StringUtil.getParam(request, "scrId", "");
        	
    		ScrCdVO scrCdVO = new ScrCdVO();
    		
    		scrCdVO.setScrId(scrId);
            
        	scrCdService.deleteScrCdTree(scrCdVO);
        } else if("SL".equals(crudMode)) {
        	mav = new ModelAndView("jsonView");
			reponse.setContentType("application/json; charset=utf-8");
			reponse.setHeader("Cache-Control", "no-cache");
			
            String schLclsNm = StringUtil.getParam(request, "schLclsNm", "");
            
            vocCnslTypeVO.setLcls("");
            vocCnslTypeVO.setMcls("00");
            vocCnslTypeVO.setScls("00");
            vocCnslTypeVO.setCnslCatNm(schLclsNm);
            vocCnslTypeVO.setLvl("1");
            
            mav.addObject("rows", vocCnslTypeService.selectCnslTypeList(vocCnslTypeVO));
            return mav;
        } else if("SM".equals(crudMode)) {
        	mav = new ModelAndView("jsonView");
			reponse.setContentType("application/json; charset=utf-8");
			reponse.setHeader("Cache-Control", "no-cache");
			
            String schMclsNm = StringUtil.getParam(request, "schMclsNm", "");
            
            vocCnslTypeVO.setLcls("00");
            vocCnslTypeVO.setMcls("");
            vocCnslTypeVO.setScls("00");
            vocCnslTypeVO.setCnslCatNm(schMclsNm);
            vocCnslTypeVO.setLvl("2");
            
            mav.addObject("rows", vocCnslTypeService.selectCnslTypeList(vocCnslTypeVO));
            return mav;
        } else if("SS".equals(crudMode)) {
        	mav = new ModelAndView("jsonView");
			reponse.setContentType("application/json; charset=utf-8");
			reponse.setHeader("Cache-Control", "no-cache");
			
            String schSclsNm = StringUtil.getParam(request, "schSclsNm", "");
            
            ScrCdVO scrCdVO = new ScrCdVO();
    		
    		scrCdVO.setSord(sord);
    		scrCdVO.setSidx(sidx);
    		
    		scrCdVO.setScrKnm(schSclsNm);
            
            mav.addObject("rows", scrCdService.selectScrCdList(scrCdVO));
            return mav;
        }
        if("CM".equals(crudMode) || "UM".equals(crudMode) || "DM".equals(crudMode)) {
        	vocCnslTypeVO.setLcls("00");
            vocCnslTypeVO.setMcls("");
            vocCnslTypeVO.setScls("00");
            vocCnslTypeVO.setCnslCatNm("");
            vocCnslTypeVO.setLvl("2");
            
        	this.writeXmlVocType(vocCnslTypeService.selectCnslTypeList(vocCnslTypeVO));
        }
        
        return mav;
    }
    
    /**
     * (Ajax)VOC유형관리코드중복체크
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/operation/voccnsltype/oprat1101_ajax.do")
    public ModelAndView oprat1101_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");

        VocCnslTypeVO vocCnslTypeVO = new VocCnslTypeVO();
        bind(request, vocCnslTypeVO);

        int intTotalCount = vocCnslTypeService.selectCnslCodeCnt(vocCnslTypeVO);

        mav.addObject("intTotalCount", intTotalCount);

        return mav;
    }
    
    
    
}
