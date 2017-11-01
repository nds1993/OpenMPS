package nds.core.operation.surveytmpl.web;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.operation.surveytmpl.service.SrvyProbVO;
import nds.core.operation.surveytmpl.service.SrvyViewVO;
import nds.core.operation.surveytmpl.service.SurveyTemplateService;
import nds.core.operation.surveytmpl.service.SurveyTemplateVO;
import nds.core.operation.surveytmpl.service.SurveyVocVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;


 
/**
 * <p>Title: SurveyTemplateController</p>
 * <p>Description: 설문 템플릿 Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2015.01.05 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Controller
public class SurveyTemplateController extends BaseController {

	/** SurveyTemplateService */
    @Resource(name = "surveyTemplateService")
    private SurveyTemplateService surveyTemplateService;
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
	/**
	 * 설문지템플릿관리
	 * 
	 * @param request
	 * @param reponse
	 */
    @RequestMapping(value="/op/oprat0200.do")
	public ModelAndView oprat0200(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
    	String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        
		SurveyTemplateVO surveyTemplateVO = new SurveyTemplateVO();
		bind(request, surveyTemplateVO);      
		
		surveyTemplateVO.setSchTmplDvn("Q");
		
		int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
        int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "20")); // 페이지당 몇개
        
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
        
        surveyTemplateVO.setStartNo(((page * rows) - rows) + 1);
        surveyTemplateVO.setEndNo(page * rows);
        
		// 정렬
        surveyTemplateVO.setSidx(sidx);
        surveyTemplateVO.setSord(sord);
		
		ModelAndView mav = new ModelAndView("op/oprat0200");
		
        if("S".equals(crudMode)) {
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
    		
    		int total = surveyTemplateService.selectTmplCount(surveyTemplateVO);
    		
            mav.addObject("total", Math.ceil((double)total/rows));  // total page
            mav.addObject("records", total);                        // total count
            mav.addObject("rows", surveyTemplateService.selectTmplList(surveyTemplateVO));
        }
        
        // 컬럼 설정 조회
    	UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("oprat0200");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
        
        mav.addObject("F153", this.getCommonCode("F153"));
		return mav;
	}

	/**
	 * 설문지템플릿 등록/수정/삭제
	 * 
	 * @param request
	 * @param reponse
	 */
    @RequestMapping(value="/op/oprat0210.do")
	public ModelAndView oprat0210(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String crudMode  = StringUtil.getParam(request, "crudMode", "").toUpperCase();
		
		SurveyTemplateVO surveyTemplateVO = new SurveyTemplateVO();
		bind(request, surveyTemplateVO);  
		
        ModelAndView mav =  null;
        
        //템플릿등록
        if (crudMode.equals("C"))
        {
            // 로그인 사용자정보 가져오기
	        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
	        String cstNo = userSession.getLogin().getUserId();
	        
	        surveyTemplateVO.setInoutDvn("O");
	        surveyTemplateVO.setRegUser(cstNo);
	        surveyTemplateVO.setUpdtUser(cstNo);
	        
	        if("Y".equals(surveyTemplateVO.getUseYn())) {
	        	surveyTemplateService.updateUseN();
	        }
	    
	        String tmplNo = surveyTemplateService.insertTmpl(surveyTemplateVO);
	        
	        surveyTemplateService.callSpSrvyInit(surveyTemplateVO);
	        
	        mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
    		
    		mav.addObject("tmplNo", tmplNo);
        }
        //템플릿삭제
        else if (crudMode.equals("D"))
        {
        	surveyTemplateService.deleteTmpl(surveyTemplateVO);
        	mav = new ModelAndView("op/oprat0200");
        }        
        else
        	mav = new ModelAndView("op/oprat0210");
             
        return mav;        
	}
    
    /**
     * 설문지템플릿 상세정보관리
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping(value="/op/oprat0220.do")
    public ModelAndView oprat0220(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        
        //템플릿
        SurveyTemplateVO surveyTemplateVO = new SurveyTemplateVO();
        bind(request, surveyTemplateVO);      

        ModelAndView mav = new ModelAndView("op/oprat0220");    
        
        // 로그인 사용자정보 가져오기
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String cstNo = userSession.getLogin().getUserId();
        String depCode = userSession.getLogin().getDepCd();
        
        //템플릿수정
        if (crudMode.equals("U"))
        {
            
            surveyTemplateVO.setUpdtUser(cstNo);
            
            if("Y".equals(surveyTemplateVO.getUseYn())) {
	        	surveyTemplateService.updateUseN();
	        }
            
            surveyTemplateService.updateTmpl(surveyTemplateVO);

        }   
        
        mav.addObject("tmpl", surveyTemplateService.selectTmplInfo(surveyTemplateVO));
        
        SurveyVocVO surveyVocVO = new SurveyVocVO();
        
        surveyVocVO.setTmplNo(surveyTemplateVO.getTmplNo());
        // 만족도 조사 이력조회
        if("S".equals(crudMode)) {
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
    		
    		int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
            int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "5")); // 페이지당 몇개
            
            String sidx = StringUtil.getParam(request, "sidx", "");
            String sord = StringUtil.getParam(request, "sord", "");
            
            surveyVocVO.setStartNo(((page * rows) - rows) + 1);
            surveyVocVO.setEndNo(page * rows);
            
    		// 정렬
            surveyVocVO.setSidx(sidx);
            surveyVocVO.setSord(sord);
    		
    		int total = surveyTemplateService.selectVocSatisListCount(surveyVocVO);
    		
            mav.addObject("total", Math.ceil((double)total/rows));  // total page
            mav.addObject("records", total);                        // total count
            mav.addObject("rows", surveyTemplateService.selectVocSatisList(surveyVocVO));
        }
        
        mav.addObject("tmplSatiList", surveyTemplateService.selectVocSatisList(surveyVocVO));
        mav.addObject("sendCnt", surveyTemplateService.selectVocSatisListCount(surveyVocVO));
        
        return mav;        
    }
    

    /**
     * 설문템플릿 미리보기
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
	@RequestMapping(value="/op/oprat0221_popup.do")
    public ModelAndView oprat0221_popup(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ModelAndView mav = new ModelAndView("op/oprat0221_popup"); 

        String c_id    =   StringUtil.getParam(request, "c_id", "");
        
        String[] data   =   c_id.split(":");
        
        String vocId = StringUtil.null2void(data[0]);
        String tmplNo = StringUtil.null2void(data[1]);
        String cstNo    =  StringUtil.null2void(data[2]);
        
        SurveyTemplateVO key = new SurveyTemplateVO();
        key.setTmplNo(tmplNo);
        SurveyTemplateVO result = surveyTemplateService.selectTmplInfo(key);
        
        if(result != null) {
            mav.addObject("tmpl", surveyTemplateService.srvyTmpl(vocId, tmplNo, cstNo));
            mav.addObject("result", result);
            mav.addObject("F025", this.getCommonCode("F025"));
        }
        else {
            mav = new ModelAndView("success_script", "scriptBlock","alertify.alert('해당 설문의 템플릿이 존재하지 않습니다.');window.close();");
        }
        return mav;
    }
    
    /**
     *설문지템플릿 상세정보관리
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping(value="/op/oprat0230.do")
    public ModelAndView oprat0230(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        //템플릿
        SurveyTemplateVO surveyTemplateVO = new SurveyTemplateVO();
        bind(request, surveyTemplateVO);      
        //문제
        SrvyProbVO srvyProbVO = new SrvyProbVO();
        bind(request, srvyProbVO);   
        //보기
        SrvyViewVO srvyViewVO = new SrvyViewVO();
        bind(request, srvyViewVO);        
        
        ModelAndView mav =  null;        
        
        mav = new ModelAndView("op/oprat0230");
        mav.addObject("tmpl", surveyTemplateService.selectTmplInfo(surveyTemplateVO));
        
        ArrayList<SrvyProbVO> quesList =  (ArrayList<SrvyProbVO>)surveyTemplateService.selectQuesList(srvyProbVO);
        ArrayList<SrvyViewVO> viewList =  (ArrayList<SrvyViewVO>)surveyTemplateService.selectViewList(srvyViewVO);
        
        ArrayList<SrvyProbVO> quesSubList = new ArrayList<SrvyProbVO>();
        ArrayList<ArrayList<Object>> rtnList = new ArrayList<ArrayList<Object>>();        
        
        for (int i =0; i < quesList.size(); i++)
        {
            SrvyProbVO t8110 = (SrvyProbVO)quesList.get(i);
            if(!(t8110.getLineNo()).equals("0"))
                quesSubList.add(t8110);
        }
        
        for (int i =0; i < quesList.size(); i++)
        {
            ArrayList<Object> rtnQuesList = new ArrayList<Object>();
           
            SrvyProbVO t8110 = (SrvyProbVO)quesList.get(i);
            
            if((t8110.getLineNo()).equals("0")){
                rtnQuesList.add(t8110);
            
                ArrayList<SrvyViewVO> rtnViewList = new ArrayList<SrvyViewVO>();
                
                for (int j =0; j < viewList.size(); j++)
                {
                    SrvyViewVO t8111 = (SrvyViewVO)viewList.get(j);
                    
                    if ( (t8110.getTmplNo().equals(t8111.getTmplNo())) && (t8110.getProbNo().equals(t8111.getProbNo())) )
                        rtnViewList.add(t8111);                 
                } 
                rtnQuesList.add(rtnViewList);
                
                ArrayList<SrvyProbVO> rtnQuesSubList = new ArrayList<SrvyProbVO>();
                
                for (int j =0; j < quesSubList.size(); j++)
                {
                    SrvyProbVO s8110 = (SrvyProbVO)quesSubList.get(j);
                    
                    if ( (t8110.getTmplNo().equals(s8110.getTmplNo())) && (t8110.getProbNo().equals(s8110.getProbNo())) )
                        rtnQuesSubList.add(s8110);                 
                } 
                rtnQuesList.add(rtnQuesSubList);
                
                rtnList.add(rtnQuesList);
            }
        }
           
        mav.addObject("tmplList", rtnList);
        mav.addObject("F100", this.getCommonCode("F100"));
        mav.addObject("F037", this.getCommonCode("F037"));
        mav.addObject("F101", this.getCommonCode("F101"));
        
        return mav;        
    }
    
    /**
     * 설문지템플릿 문항관리
     * @param request
     * @param reponse
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value="/op/oprat0231_popup.do")
    public ModelAndView oprat0231_popup(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        
        SrvyProbVO srvyProbVO = new SrvyProbVO();
        bind(request, srvyProbVO);
        
        SrvyViewVO srvyViewVO = new SrvyViewVO();
        bind(request, srvyViewVO);        
        
        ModelAndView mav = new ModelAndView("op/oprat0231_popup");                

        if (!crudMode.equals(""))
        {
            // 로그인 사용자정보 가져오기
            UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
            String cstNo = userSession.getLogin().getUserId();
            
            if((StringUtil.null2void(srvyProbVO.getProbNo())).equals("")){
                String i = surveyTemplateService.selectProbNo(srvyProbVO);
                srvyProbVO.setProbNo(i);
            }
            
            if (crudMode.equals("C")){
                
                // 질문등록
                List<SrvyProbVO> paramProbList = new ArrayList<SrvyProbVO>();
                srvyProbVO.setLineNo("0");
                srvyProbVO.setRegUser(cstNo);
                srvyProbVO.setUpdtUser(cstNo);
                        
                paramProbList.add(srvyProbVO);
                surveyTemplateService.insertQues(paramProbList);
                
                if("05".equals(srvyProbVO.getViewType())){
                    //보기전체삭제
                    SrvyViewVO delsrvyViewVO = new SrvyViewVO();
                    delsrvyViewVO.setTmplNo(srvyProbVO.getTmplNo());
                    delsrvyViewVO.setProbNo(srvyProbVO.getProbNo());
                    surveyTemplateService.deleteView(delsrvyViewVO);
                    
                    //보기등록
                    List<SrvyViewVO> paramList = new ArrayList<SrvyViewVO>();
                    SrvyViewVO t8111 = new SrvyViewVO();
                    t8111.setCrudGbn("C");
                    t8111.setTmplNo(srvyProbVO.getTmplNo());
                    t8111.setProbNo(srvyProbVO.getProbNo());
                    t8111.setLineNo("0");
                    t8111.setOrd("1");
                    t8111.setRegUser(cstNo);
                    t8111.setUpdtUser(cstNo);
                    
                    paramList.add(t8111);
                    
                    surveyTemplateService.insertView(paramList);
                }
                
                mav.addObject("save", "Y");
            }
            else if(crudMode.equals("D")){
                SrvyViewVO del8111= new SrvyViewVO();
                del8111.setTmplNo(srvyProbVO.getTmplNo());
                del8111.setProbNo(srvyProbVO.getProbNo());
                
                SrvyProbVO del8110= new SrvyProbVO();
                del8110.setTmplNo(srvyProbVO.getTmplNo());
                del8110.setProbNo(srvyProbVO.getProbNo());
                
                surveyTemplateService.delete(del8110, del8111);
                
                mav.addObject("delete", "Y");
            }
            
            srvyProbVO.setOrd("");
            srvyProbVO.setLineNo("0");
            mav.addObject("result", surveyTemplateService.selectQues(srvyProbVO));  
        }
        mav.addObject("F037", this.getCommonCode("F037"));
        mav.addObject("F101", this.getCommonCode("F101"));
        mav.addObject("F100", this.getCommonCode("F100"));
        return mav;
    }
    
    
    /**
     * 설문지템플릿 문항관리
     * @param request
     * @param reponse
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value="/op/oprat0232_popup.do")
    public ModelAndView oprat0232_popup(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        
        SrvyProbVO srvyProbVO = new SrvyProbVO();
        bind(request, srvyProbVO);
        
        SrvyViewVO srvyViewVO = new SrvyViewVO();
        bind(request, srvyViewVO);        
        
        ModelAndView mav = new ModelAndView("op/oprat0232_popup");                

        if (!crudMode.equals(""))
        {
            // 로그인 사용자정보 가져오기
            UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
            String cstNo = userSession.getLogin().getUserId();
            
            if((StringUtil.null2void(srvyProbVO.getProbNo())).equals("")){
                String i = surveyTemplateService.selectProbNo(srvyProbVO);
                srvyProbVO.setProbNo(i);
            }
            
            if (crudMode.equals("C")){
                
                //보기전체삭제
                SrvyViewVO delsrvyViewVO = new SrvyViewVO();
                delsrvyViewVO.setTmplNo(srvyProbVO.getTmplNo());
                delsrvyViewVO.setProbNo(srvyProbVO.getProbNo());
                surveyTemplateService.deleteView(delsrvyViewVO);
               
                //보기등록
                String[] ord = request.getParameterValues("ord");
                String[] viewNm = request.getParameterValues("viewNm");                     
                String[] viewNo = request.getParameterValues("viewNo");
                @SuppressWarnings("unused")
                String[] scr = request.getParameterValues("scr");
                
                List<SrvyViewVO> paramList = new ArrayList<SrvyViewVO>();
                
                if(ord != null){
                    for (int i =0; i < ord.length; i++)
                    {
                        SrvyViewVO t8111 = new SrvyViewVO();
                        
                        t8111.setCrudGbn("C");
                        t8111.setTmplNo(srvyProbVO.getTmplNo());
                        t8111.setProbNo(srvyProbVO.getProbNo());
                        t8111.setLineNo("0");
                        t8111.setOrd(StringUtil.null2void(ord[i]));
                        t8111.setViewNm(StringUtil.null2void(viewNm[i]));
                        t8111.setViewNo(StringUtil.null2void(viewNo[i]));
                        t8111.setScr(StringUtil.null2String(scr[i],""));
                        t8111.setRegUser(cstNo);
                        t8111.setUpdtUser(cstNo);
                        
                        paramList.add(t8111);
                    }
                    surveyTemplateService.insertView(paramList);
                }
                

                srvyViewVO.setPreOrd("");
                srvyViewVO.setViewNo("");
                srvyViewVO.setOrd("");
                mav.addObject("resultView", surveyTemplateService.selectViewList(srvyViewVO));
                
                mav.addObject("save", "Y");
            }
            else if(crudMode.equals("D")){
                SrvyViewVO del8111= new SrvyViewVO();
                del8111.setTmplNo(srvyProbVO.getTmplNo());
                del8111.setProbNo(srvyProbVO.getProbNo());
                
                SrvyProbVO del8110= new SrvyProbVO();
                del8110.setTmplNo(srvyProbVO.getTmplNo());
                del8110.setProbNo(srvyProbVO.getProbNo());
                
                surveyTemplateService.delete(del8110, del8111);
                
                mav.addObject("save", "Y");
            }
            else {
                srvyProbVO.setLineNo("");
                srvyProbVO.setStartNo(1);
                mav.addObject("resultProb", surveyTemplateService.selectByConnect(srvyProbVO));
                
                srvyViewVO.setPreOrd("");
                srvyViewVO.setViewNo("");
                srvyViewVO.setOrd("");
                mav.addObject("resultView", surveyTemplateService.selectViewList(srvyViewVO));
            }
        }
        return mav;
    }
    
    /**
     * 설문지템플릿 가져오기
     * @param request
     * @param reponse
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value="/op/oprat0233_popup.do")
    public ModelAndView oprat0233_popup(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        
        ModelAndView mav =  new ModelAndView("op/oprat0233_popup");
        
        SurveyTemplateVO surveyTemplateVO = new SurveyTemplateVO();
        
        SrvyProbVO srvyProbVO = new SrvyProbVO();
        bind(request, srvyProbVO);
        
        SrvyViewVO srvyViewVO = new SrvyViewVO();
        bind(request, srvyViewVO);
        
        surveyTemplateVO.setSchTmplDvn("Q");
        surveyTemplateVO.setUseYn("Y");
        
        if(crudMode.equals("R")){
            
            String schTmplNo = StringUtil.getParam(request, "schTmplNo", "");
            srvyProbVO.setTmplNo(schTmplNo);
            srvyViewVO.setTmplNo(schTmplNo);
            
            List<SrvyProbVO> quesList = surveyTemplateService.selectQuesList(srvyProbVO);
            List<SrvyViewVO> viewList  = surveyTemplateService.selectViewList(srvyViewVO);
            
            List<Object> quesSubList = new ArrayList<Object>();
            List<Object> rtnList = new ArrayList<Object>();        
            
            for (int i =0; i < quesList.size(); i++)
            {
                SrvyProbVO t8110 = (SrvyProbVO)quesList.get(i);
                if(!(t8110.getLineNo()).equals("0"))
                    quesSubList.add(t8110);
            }
            
            for (int i =0; i < quesList.size(); i++)
            {
                List<Object> rtnQuesList = new ArrayList<Object>();
               
                SrvyProbVO t8110 = (SrvyProbVO)quesList.get(i);
                
                if((t8110.getLineNo()).equals("0")){
                    rtnQuesList.add(t8110);
                    List<SrvyViewVO> rtnViewList = new ArrayList<SrvyViewVO>();
                    for (int j =0; j < viewList.size(); j++) {
                        SrvyViewVO t8111 = (SrvyViewVO)viewList.get(j);
                        if ( (t8110.getTmplNo().equals(t8111.getTmplNo())) && (t8110.getProbNo().equals(t8111.getProbNo())) )
                            rtnViewList.add(t8111);                 
                    } 
                    rtnQuesList.add(rtnViewList);
                    
                    List<SrvyProbVO> rtnQuesSubList = new ArrayList<SrvyProbVO>();
                    for (int j =0; j < quesSubList.size(); j++) {
                        SrvyProbVO s8110 = (SrvyProbVO)quesSubList.get(j);
                        
                        if ( (t8110.getTmplNo().equals(s8110.getTmplNo())) && (t8110.getProbNo().equals(s8110.getProbNo())) )
                            rtnQuesSubList.add(s8110);                 
                    } 
                    rtnQuesList.add(rtnQuesSubList);
                    
                    rtnList.add(rtnQuesList);
                }
            }
               
            mav.addObject("rtnList", rtnList);
        }
        else if(crudMode.equals("C")){
            String schTmplNo = StringUtil.getParam(request, "schTmplNo", "");
            String tmplNo = StringUtil.getParam(request, "tmplNo", "");
            
            UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
            String cstNo = userSession.getLogin().getUserId();
            
            String[] chkItem = request.getParameterValues("chkItem");
            if (chkItem != null) {
                for (int i = 0; i < chkItem.length; i++) {
                    SrvyProbVO copy8110 = new SrvyProbVO();
                    copy8110.setSchTmplNo(schTmplNo);
                    copy8110.setTmplNo(tmplNo);
                    copy8110.setSchProbNo(chkItem[i]);
                    copy8110.setRegUser(cstNo);
                    copy8110.setUpdtUser(cstNo);
                    
                    String probNo = surveyTemplateService.insertQuesCopy(copy8110);
                    
                    SrvyViewVO copy8111 = new SrvyViewVO();
                    copy8111.setSchTmplNo(schTmplNo);
                    copy8111.setSchProbNo(chkItem[i]);
                    copy8111.setTmplNo(tmplNo);
                    copy8111.setProbNo(probNo);
                    copy8111.setRegUser(cstNo);
                    copy8111.setUpdtUser(cstNo);
                    
                    surveyTemplateService.insertViewsCopy(copy8111);
                }
                
            }
            mav.addObject("save", "Y");
            mav.addObject("F101", this.getCommonCode("F101"));
            return mav;
        }
        
        int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
        int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "20")); // 페이지당 몇개
        
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
        
        surveyTemplateVO.setStartNo(((page * rows) - rows) + 1);
        surveyTemplateVO.setEndNo(page * rows);
        
		// 정렬
        surveyTemplateVO.setSidx(sidx);
        surveyTemplateVO.setSord(sord);
		
        if("S".equals(crudMode)) {
        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
    		
    		int total = surveyTemplateService.selectTmplListCount(surveyTemplateVO);
    		
            mav.addObject("total", Math.ceil((double)total/rows));  // total page
            mav.addObject("records", total);                        // total count
            mav.addObject("rows", surveyTemplateService.selectTmplList(surveyTemplateVO));
        }
        
        mav.addObject("F101", this.getCommonCode("F101"));
        return mav;
    } 
}
