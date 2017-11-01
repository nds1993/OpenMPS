package nds.core.common.popup.web;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.core.common.common.service.AttachFileVO;
import nds.core.common.common.service.AttachfileService;
import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.NeedsMstVO;
import nds.core.common.common.service.SkinMstVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.common.common.web.CommonController;
import nds.core.common.message.MessageBean;
import nds.core.common.message.MessageModule;
import nds.core.common.message.MessageModule.MSGTYPE;
import nds.core.common.popup.service.CnslTypeVO;
import nds.core.common.popup.service.PopupAcepnoVO;
import nds.core.common.popup.service.PopupApprovalVO;
import nds.core.common.popup.service.PopupCnslTypeVO;
import nds.core.common.popup.service.PopupCustInfoVO;
import nds.core.common.popup.service.PopupCustMemoVO;
import nds.core.common.popup.service.PopupHelpReqInfoVO;
import nds.core.common.popup.service.PopupPrductVO;
import nds.core.common.popup.service.PopupPropAsgnHistVO;
import nds.core.common.popup.service.PopupPropChngHistVO;
import nds.core.common.popup.service.PopupService;
import nds.core.common.popup.service.PopupVendorVO;
import nds.core.common.popup.service.PopupWidgetSetVO;
import nds.core.logsearch.customerlookuplog.service.CustomerLookupLogService;
import nds.core.logsearch.customerlookuplog.service.CustomerLookupLogVO;
import nds.core.operation.approval.service.ApprovalService;
import nds.core.operation.approval.service.ApprovalVO;
import nds.core.operation.emailTmpl.service.EmailTmplService;
import nds.core.operation.emailTmpl.service.EmailTmplVO;
import nds.core.systemsettings.menu.service.MenuService;
import nds.core.systemsettings.menu.service.MenuVO;
import nds.core.userdep.department.service.DepartMentService;
import nds.core.userdep.department.service.DepartMentVO;
import nds.core.userdep.roleuser.service.RoleUserService;
import nds.core.userdep.roleuser.service.RoleUserVO;
import nds.core.userdep.user.service.UserVO;
import nds.frm.config.StaticConfig;
import nds.frm.listener.UploadProgressListener;
import nds.frm.startup.SYSTEM;
import nds.frm.util.DateTimeUtil;
import nds.frm.util.EncyptShaUtil;
import nds.frm.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: PopupController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Controller
public class PopupController extends BaseController {
	/** PopupService */
    @Resource(name = "popupService")
    private PopupService popupService;
	/** AttachfileService */
    @Resource(name = "attachfileService")
    private AttachfileService attachfileService;
    /** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
    /** CustomerLookupLogService */
    @Resource(name = "customerLookupLogService")
    private CustomerLookupLogService customerLookupLogService;
    /** CustomerLookupLogService */
    @Resource(name = "approvalService")
    private ApprovalService approvalService;
	/** ApprovalService */
    @Resource(name = "departMentService")
    private DepartMentService departMentService;
    /** EmailService */
	@Resource(name = "emailTmplService")
	private EmailTmplService emailTmplService;
	 /** messageModule */
    @Resource(name = "messageModule")
    private MessageModule messageModule;
    /** RoleUserService */
    @Resource(name = "roleUserService")
    private RoleUserService roleUserService;
    @Resource(name = "menuService")
    private MenuService menuService;
    
    /**
     * 메인위젯설정
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_widgets_sch.do")
    public ModelAndView co_widgets_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String crudMode   = StringUtil.getParam(request, "crudMode").toUpperCase(); 
        
        // 로그인 사용자정보 가져오기
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();
        
        ModelAndView mav = new ModelAndView("/common/popup/co_widgets_sch");
        
        if("U".equals(crudMode)){
            String[] chkItems     = request.getParameterValues("chkItem");
            String[] graphBaseCds = request.getParameterValues("graphBaseCd");
            String[] graphRangs   = request.getParameterValues("graphRang");
            String[] sortNos   = request.getParameterValues("sortNo");
            
            if(chkItems != null){
                
            	popupService.deleteMaWidgets(userEmpno);
                
                PopupWidgetSetVO tbvcMaWidgetsSet = null;
                for(int i = 0; i < chkItems.length; i++) {
                    String[] chkItem   = StringUtil.null2void(chkItems[i]).split("[|]");
                    int   chkNo        = Integer.parseInt(chkItem[1]);
                    String graphBaseCd = StringUtil.null2void(graphBaseCds[chkNo]);
                    String graphRang   = StringUtil.null2void(graphRangs[chkNo]);
                    String sortNo = StringUtil.null2void(sortNos[chkNo]);
                     
                    tbvcMaWidgetsSet = new PopupWidgetSetVO();
                    
                    tbvcMaWidgetsSet.setUserId(userEmpno);
                    tbvcMaWidgetsSet.setWidgetsId(chkItem[0]);
                    tbvcMaWidgetsSet.setGraphBaseCd(graphBaseCd);
                    tbvcMaWidgetsSet.setGraphRang(graphRang);
                    tbvcMaWidgetsSet.setRegUser(userEmpno);
                    tbvcMaWidgetsSet.setUpdtUser(userEmpno);
                    tbvcMaWidgetsSet.setSortNo(sortNo);
                    
                    popupService.insertMaWidgets(tbvcMaWidgetsSet);
                }
        		mav = new ModelAndView("jsonView");
        		response.setContentType("application/json; charset=utf-8");
        		response.setHeader("Cache-Control", "no-cache");
            }
        }
        else if("S".equals(crudMode)){
        	mav = new ModelAndView("jsonView");
    		response.setContentType("application/json; charset=utf-8");
    		response.setHeader("Cache-Control", "no-cache");
        }
        
        List<PopupWidgetSetVO> result =  popupService.selectMaWidgetsList(userEmpno); //위젯리스트
        int lastNum = 0;
        for(int i = 0;  i < result.size(); i++){
            int resultSortNo = StringUtil.null2zeroint(result.get(i).getSortNo());
            if(lastNum < resultSortNo){
                lastNum  = resultSortNo;
            }
        }
        //reload 시간 설정
        ResourceBundle rb = ResourceBundle.getBundle("nds.frm.config.vocmain");
        String setMax = rb.getString("widgetMax");

        mav.addObject("widgetMax", setMax);
        mav.addObject("result", result);
        mav.addObject("resultLastNo", lastNum);
    	mav.addObject("rows", result);
        
        return mav;
    }
    
    /**
     * (Ajax)조직정보조회
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/common/popup/co_deptsearch_ajax.html.do")
    public ModelAndView co_deptsearch_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List result = popupService.selectOrgTree(StringUtil.getParam(request, "parentId", "X"));

        ModelAndView mav = new ModelAndView("common/popup/co_deptsearch_ajax.html");
        mav.addObject("result", result);

        return mav;
    }
    
    /**
     * 부서찾기(Ajax)
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_dep_sch_ajax.do")
	public ModelAndView co_dep_sch_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String schDepNm   = StringUtil.getParam(request, "schDepNm"); // 사용자명
		
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		DepartMentVO departMentVO = new DepartMentVO();
		departMentVO.setDepNm(schDepNm);
		
		List<DepartMentVO> result =  popupService.selectDepList(departMentVO);
		mav.addObject("result", result);
		    
		return mav;
	}
	/**
     * 부서찾기
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_dep_sch.do")
      public ModelAndView co_dep_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {
          String schDepNm   = StringUtil.getParam(request, "schDepNm"); // 부서명
          String crudMode 	= StringUtil.getParam(request, "crudMode", "R");
          
          ModelAndView mav = new ModelAndView("common/popup/co_dep_sch");
          
          DepartMentVO departMentVO = new DepartMentVO();
          departMentVO.setDepNm(schDepNm);
			  
			if("R".equals(crudMode)){
				List<DepartMentVO> result =  popupService.selectDepTree(departMentVO);
				
				mav.addObject("result", result);
			}

			return mav;
      }
      
	/**
     * 사용자찾기(Ajax)
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_user_sch_ajax.do")
	public ModelAndView co_user_sch_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String schUsrEmpno   = StringUtil.getParam(request, "schUsrEmpno"); // 사번
		String schUsrNm   = StringUtil.getParam(request, "schUsrNm"); // 사용자명
		String userRole   = StringUtil.getParam(request, "userRole"); // 조회 역할
		
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		UserVO userVO = new UserVO();
		
		userVO.setUserEmpno(schUsrEmpno);
		userVO.setUserNm(schUsrNm);
		userVO.setUserRole(userRole);
		
		List<UserVO> result =  popupService.selectCustList(userVO);
		
		mav.addObject("result", result);
			
		return mav;
	}
	
    /**
     * 사용자찾기
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_user_sch.do")
	public ModelAndView co_user_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String schUsrEmpno   = StringUtil.getParam(request, "schUsrEmpno"); // 사번
		String schUserNm   = StringUtil.getParam(request, "schUserNm"); // 사용자명
		String schDepNm   = StringUtil.getParam(request, "schDepNm"); // 부서명
		String schInDep   = StringUtil.getParam(request, "schInDep"); // 자기의부서만
		String schPstNm  = StringUtil.getParam(request, "schPstNm"); // 직책
		String adviserYn   = StringUtil.getParam(request, "adviserYn", null); // 업무정보
		String crudMode = StringUtil.getParam(request, "crudMode");
		String userRole   = StringUtil.getParam(request, "userRole"); // 조회 역할
		
		ModelAndView mav = new ModelAndView("common/popup/co_user_sch");
		
		UserVO userVO = new UserVO();
		
		// 로그인 사용자정보 가져오기
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String depCd   = StringUtil.getParam(request, "depCd", ""); // 부서코드
		
		
		userVO.setDepCd(depCd);
		userVO.setUserNm(schUserNm);
		userVO.setDepNm(schDepNm);
		userVO.setUserEmpno(schUsrEmpno);
		userVO.setPstNm(schPstNm);
		userVO.setAdviserYn(adviserYn);
		userVO.setAdviserYn(adviserYn);
		userVO.setUserRole(userRole);
		
		int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
        int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "10")); // 페이지당 몇개
		    
		String sidx = StringUtil.getParam(request, "sidx", "");
		String sord = StringUtil.getParam(request, "sord", "");
		
		userVO.setStartNo(((page * rows) - rows) + 1);
		userVO.setEndNo(page * rows);
		
		userVO.setSidx(sidx);
		userVO.setSord(sord);
		
		  
		if("R".equals(crudMode)){
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			List<UserVO> result =  popupService.selectCustList(userVO);
			int intTotalCount = popupService.selectCustListCount(userVO);
			
			mav.addObject("rows", result);
			mav.addObject("total", Math.ceil((double)intTotalCount/rows));  // total page
			mav.addObject("records", intTotalCount);                        // total count
			
		}
		DepartMentVO departMentVO = new DepartMentVO();
		departMentVO.setDepNm(schDepNm);

		List<DepartMentVO> result =  popupService.selectDepTree(departMentVO);

		mav.addObject("result", result);
		

		return mav;
	}
    /**
     * 사용자찾기(Ajax)
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_userlist_sch_ajax.do")
	public ModelAndView co_userlist_sch_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String schUsrEmpno   = StringUtil.getParam(request, "schUsrEmpno", ""); // 사번
		String schUsrNm   = StringUtil.getParam(request, "schUsrNm", ""); // 사용자명
		String userRole   = StringUtil.getParam(request, "userRole", ""); // 조회 역할
		String schDepCd   = StringUtil.getParam(request, "schDepCd", ""); // 조회 역할
		
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		UserVO userVO = new UserVO();
		
		userVO.setUserEmpno(schUsrEmpno);
		userVO.setUserNm(schUsrNm);
		userVO.setUserRole(userRole);
		userVO.setDepCd(schDepCd);
		
		List<UserVO> result =  popupService.selectCustList(userVO);
		
		mav.addObject("result", result);
			
		return mav;
	}
	
    /**
     * 사용자찾기
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_userlist_sch.do")
	public ModelAndView co_userlist_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String schUsrEmpno   = StringUtil.getParam(request, "schUsrEmpno", ""); // 사번
		String schUserNm   = StringUtil.getParam(request, "schUserNm", ""); // 사용자명
		String schWrkInfo   = StringUtil.getParam(request, "schWrkInfo", ""); // 업무정보
		String adviserYn   = StringUtil.getParam(request, "adviserYn", null);
		String crudMode = StringUtil.getParam(request, "crudMode", "");
		String userRole   = StringUtil.getParam(request, "userRole", ""); // 조회 역할
		String schDepCd   = StringUtil.getParam(request, "schDepCd", "");
		
		ModelAndView mav = new ModelAndView("common/popup/co_userlist_sch");
		
		UserVO userVO = new UserVO();
		
		userVO.setDepCd(schDepCd);
		userVO.setUserNm(schUserNm);
		userVO.setUserEmpno(schUsrEmpno);
		userVO.setWrkInfo(schWrkInfo);
		userVO.setAdviserYn(adviserYn);
		userVO.setUserRole(userRole);
		
		int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
        int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "10")); // 페이지당 몇개
		    
		String sidx = StringUtil.getParam(request, "sidx", "");
		String sord = StringUtil.getParam(request, "sord", "");
		
		userVO.setStartNo(((page * rows) - rows) + 1);
		userVO.setEndNo(page * rows);
		
		userVO.setSidx(sidx);
		userVO.setSord(sord);
		
		if("R".equals(crudMode)){
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			List<UserVO> result =  popupService.selectCustList(userVO);
			int intTotalCount = popupService.selectCustListCount(userVO);
			
			mav.addObject("rows", result);
			mav.addObject("total", Math.ceil((double)intTotalCount/rows));  // total page
			mav.addObject("records", intTotalCount);                        // total count
		}

		return mav;
	}
 
    /**
     * VOC유형코드 찾기(AJAX)
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_cnsltype_sch_ajax.do")
    public ModelAndView co_cnsltype_sch_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String schLcls   = StringUtil.getParam(request, "schLcls");
        String schMcls   = StringUtil.getParam(request, "schMcls");
        String schScls   = StringUtil.getParam(request, "schScls");
        String schLvl   = StringUtil.getParam(request, "schLvl");
        String schType   = StringUtil.getParam(request, "schType");   // 1:전체유형  2:내유형
        String schCnslCatNm   = StringUtil.getParam(request, "schCnslCatNm");
        
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
      
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String schUserId = userSession.getLogin().getUserEmpno();
        String schDepCd = userSession.getLogin().getDepCd();              
        
        CnslTypeVO tbvcMyCnslType = new CnslTypeVO();
        
        tbvcMyCnslType.setUserId(schUserId);
        tbvcMyCnslType.setDepCd(schDepCd);
        
        tbvcMyCnslType.setLcls(schLcls);
        tbvcMyCnslType.setMcls(schMcls);
        tbvcMyCnslType.setScls(schScls);
        if("1".equals(schLvl)){
            tbvcMyCnslType.setLclsNm(schCnslCatNm);
        }
        else if("2".equals(schLvl)){
            tbvcMyCnslType.setMclsNm(schCnslCatNm);
        }
        else if("3".equals(schLvl)){
            tbvcMyCnslType.setSclsNm(schCnslCatNm);
        }
        List<CnslTypeVO> result = null;
        if("1".equals(schType)){
            result =  popupService.selectCnslTypeList(tbvcMyCnslType);
        }
        else{
            result =  popupService.selectMyCnslType(tbvcMyCnslType);
        }
        
        mav.addObject("result", result);
        mav.addObject("schlvl", schLvl);
        
        return mav;
    }
    /**
     * VOC유형코드 자동완성(autocomplete)
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_cnsltype_sch_autocomplete.do")
    public ModelAndView co_cnsltype_sch_autocomplete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String term = StringUtil.getParam(request, "term", "");
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
    	ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
    	
		PopupCnslTypeVO popupCnslTypeVO = new PopupCnslTypeVO();
		popupCnslTypeVO.setCnslCatNm(term);

		if("SL".equals(crudMode)){		//대분류 자동완성 조회
			popupCnslTypeVO.setLvl("1");
		}else if("SM".equals(crudMode)){	//중분류 자동완성 조회
			popupCnslTypeVO.setLvl("2");
		}else if("SS".equals(crudMode)){	//소분류 자동완성 조회
			popupCnslTypeVO.setLvl("3");
		}

		List<PopupCnslTypeVO> list = popupService.selectCnslTypeAutoComplete(popupCnslTypeVO);
		JSONArray json = new JSONArray();
		for(int i=0; i<list.size(); i++){
			json.add(list.get(i).getCnslCatNm());
		}
		
		mav.addObject("data", json);
    	
    	return mav;
    }
    
    /**
     * VOC유형코드 찾기
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_cnsltype_sch.do")
    public ModelAndView co_cnsltype_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String schLcls   = StringUtil.getParam(request, "schLcls");
        String schMcls   = StringUtil.getParam(request, "schMcls");
        String schScls   = StringUtil.getParam(request, "schScls");
        String schLvl   = StringUtil.getParam(request, "schLvl");
        String schCnslCatNm   = StringUtil.getParam(request, "schCnslCatNm");
        String schMode   = StringUtil.getParam(request, "schMode"); 
        String crudMode   = StringUtil.getParam(request, "crudMode");
        
        
        ModelAndView mav = new ModelAndView("common/popup/co_cnsltype_sch");
        
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String schUserId = userSession.getLogin().getUserEmpno();
        String schDepCd = userSession.getLogin().getDepCd();              
        
        CnslTypeVO tbvcMyCnslType = new CnslTypeVO();
        
        tbvcMyCnslType.setUserId(schUserId);
        tbvcMyCnslType.setDepCd(schDepCd);
        
        tbvcMyCnslType.setLcls(schLcls);
        tbvcMyCnslType.setMcls(schMcls);
        tbvcMyCnslType.setScls(schScls);
        
        if("ALL".equals(schMode)){
            tbvcMyCnslType.setSchMode(schMode);
            tbvcMyCnslType.setSchCnslCatNm(schCnslCatNm);
        }
        if("1".equals(schLvl)){
            tbvcMyCnslType.setLclsNm(schCnslCatNm);
        }
        else if("2".equals(schLvl)){
            tbvcMyCnslType.setMclsNm(schCnslCatNm);
        }
        else if("3".equals(schLvl)){
            tbvcMyCnslType.setSclsNm(schCnslCatNm);
        }
        
        int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
	    int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "10")); // 페이지당 몇개
	        
	    String sidx = StringUtil.getParam(request, "sidx", "");
	    String sord = StringUtil.getParam(request, "sord", "");

	    tbvcMyCnslType.setStartNo(((page * rows) - rows) + 1);
	    tbvcMyCnslType.setEndNo(page * rows);
		
	    tbvcMyCnslType.setSidx(sidx);
	    tbvcMyCnslType.setSord(sord);
	    
        List<CnslTypeVO> result = null;
        int intTotalCount = 0;
        
		if ("S".equals(crudMode)) {
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
    		
			intTotalCount = popupService.selectCnslTypeListCount(tbvcMyCnslType);
			
			mav.addObject("rows", popupService.selectCnslTypeList(tbvcMyCnslType));
            mav.addObject("total", Math.ceil((double)intTotalCount/rows));  // total page
            mav.addObject("records", intTotalCount);                        // total count
		}
		
        return mav;
    }
    
    /**
     * 고객정보 조회
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_cust_sch.do")
    public ModelAndView co_cust_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String crudMode   = StringUtil.getParam(request, "crudMode");
        String schCstNm   = URLEncoder.encode(StringUtil.getParam(request, "schCstNm", ""), "EUC-KR");        // 고객명
        String cstNm      = StringUtil.getParam(request, "schCstNm", "");        // 고객명
        String schEmail   = StringUtil.getParam(request, "schEmail", "");        // 이메일
        String schHphon   = StringUtil.getParam(request, "schHphon", "");        // 휴대전화
		String pid        = StringUtil.getParam(request, "pid", "co_cust_sch");

		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();
		
        ModelAndView mav = new ModelAndView("common/popup/co_cust_sch");
        
        PopupCustInfoVO tbvcCst = new PopupCustInfoVO();
        
        tbvcCst.setCstNm(schCstNm);
        tbvcCst.setEmail(schEmail);
        tbvcCst.setHphon(schHphon);
        
        int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
	    int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "10")); // 페이지당 몇개
	        
	    String sidx = StringUtil.getParam(request, "sidx", "");
	    String sord = StringUtil.getParam(request, "sord", "");

	    tbvcCst.setStartNo(((page * rows) - rows) + 1);
	    tbvcCst.setEndNo(page * rows);
		
	    tbvcCst.setSidx(sidx);
	    tbvcCst.setSord(sord);

        
        if("R".equals(crudMode) && !"".equals(schCstNm)){
        	CommonController ccr = new CommonController();
            mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");

			String params = "?kor_nm="+ schCstNm +"&email="+ schEmail +"&hp_no="+ schHphon;
			String info = ccr.getCustList("List", params);
			info = info.replaceAll("\n", "");

			List<Object> list = new ArrayList<Object>();
			int resultSize = 0;
        	if(info.indexOf("302 Found") > -1){
        		LogManager.getRootLogger().debug("############## 고객조회 안됨. ###############");
			}
        	else if(info.indexOf("<br><br><br><br><br><br><br>") > -1){
        		LogManager.getRootLogger().debug("############## 고객조회 안됨. ###############");
        	}
			else{
				if(!"".equals(info) && info != null){
					try {
						JSONArray jsonArray = JSONArray.fromObject(info);
						resultSize = jsonArray.size();
						for(int i=0;i < resultSize ;i++){
							list.add(jsonArray.get(i));
						}
						LogManager.getRootLogger().debug("###### list="+list.toString());
					} catch (JSONException e1) {
						e1.printStackTrace();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
			
			mav.addObject("rows", list);
			mav.addObject("records", resultSize);
    		
	        ///// 로그 조회 ///////////
	        if(resultSize > 0){
	    		CustomerLookupLogVO tbvcCstLogInfo = customerLookupLogService.selectMenuNm(pid);
	    		String scrnNm = tbvcCstLogInfo.getScrnNm();
	    		String btnNm = "조회";
	    		String inqObj = "VOC등록을 위한 고객조회";
	    		String inqCond = "";
	    		if (!"".equals(schCstNm)) {
	    			inqCond = inqCond + "고객명";
	    		}
	    		if (!"".equals(schCstNm) && !"".equals(schHphon)) {
	    			inqCond = inqCond + "/휴대전화";
	    		}
	    		if (!"".equals(schCstNm) && !"".equals(schEmail)) {
	    			inqCond = inqCond + "/이메일";
	    		}
	    		
	    		String cnctPath = request.getServletPath().replaceFirst("/WEB-INF/jsp", "");
	    		String ip = request.getRemoteAddr();
	    		String sysId = "VOC";
	    		CustomerLookupLogVO key = new CustomerLookupLogVO();
	    		key.setCstNm(cstNm);
	    		key.setUserEmpNo(userEmpno);
	    		key.setScrnNm(scrnNm);
	    		key.setBtnNm(btnNm);
	    		key.setInqObj(inqObj);
	    		key.setInqCond(inqCond);
	    		key.setCnctPath(cnctPath);
	    		key.setIpAddr(ip);
	    		key.setSysId(sysId);
	    		key.setInqCnt(resultSize+"");
	    		customerLookupLogService.insertCstLogInfo(key);
	        }
        }
        
        mav.addObject("F017",  this.getCommonUpCode("F017"));
        return mav;
    }
    
    /**
     * 고객정보 조회(Ajax)
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_cust_sch_ajax.do")
    public ModelAndView co_cust_sch_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String cstNo     = StringUtil.getParam(request, "cstNo","");        // 고객번호
        String schCstNm  = StringUtil.getParam(request, "schCstNm","");    // 고객명
        String schBirdt  = StringUtil.getParam(request, "schBirdt");        // 생년월일
		String pid       = StringUtil.getParam(request, "pid", "");
		
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
        
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();
		
        List<PopupCustInfoVO> result = null;  
        int resultCnt = 0;
        PopupCustInfoVO tbvcCst = new PopupCustInfoVO();
        String mark = "";
        
        if(!"".equals(cstNo)){
            tbvcCst.setCstNo(cstNo);
            
            result =  popupService.selectCstDetail(tbvcCst);
        }
        else{
            tbvcCst.setCstNm(schCstNm);
            if(!"".equals(schBirdt)){
                tbvcCst.setBirdt(schBirdt);
            }
            else if("".equals(schCstNm)){
                tbvcCst.setCstNm("X");
            }

            result =  popupService.selectCstList(tbvcCst);
            if(result.size() == 1){
                PopupCustInfoVO key = (PopupCustInfoVO)result.get(0);
                result =  popupService.selectCstDetail(key);
            }
        }
        ///// 로그 조회 ///////////
        resultCnt = result.size();
   		if (resultCnt == 1) {
   			if(!"".equals(pid)) {
   				CustomerLookupLogVO tbvcCstLogInfo = customerLookupLogService.selectMenuNm(pid);
				String scrnNm = tbvcCstLogInfo.getScrnNm();
				String btnNm = "조회";
				String inqObj = "VOC등록을 위한 고객조회";
				String inqCond = "";
				if (!"".equals(schCstNm)) {
					inqCond = inqCond + "고객명";
				}
				if (!"".equals(schCstNm) && !"".equals(schBirdt)) {
					inqCond = inqCond + "/";
				}
				if (!"".equals(schBirdt)) {
					inqCond = inqCond + "생년월일";
				}
				
				String cnctPath = request.getServletPath().replaceFirst("/WEB-INF/jsp", "");
				String ip = request.getRemoteAddr();
				String sysId = "VOC";
				CustomerLookupLogVO key = new CustomerLookupLogVO();
				PopupCustInfoVO key2 = (PopupCustInfoVO)result.get(0);
				key.setCstNm(key2.getCstNm());
				key.setUserEmpNo(userEmpno);
				key.setScrnNm(scrnNm);
				key.setBtnNm(btnNm);
				key.setInqObj(inqObj);
				key.setInqCond(inqCond);
				key.setCnctPath(cnctPath);
				key.setIpAddr(ip);
				key.setSysId(sysId);
				key.setInqCnt("1");
				customerLookupLogService.insertCstLogInfo(key);
   			}
		}
        /////////////////////////
        
        mav.addObject("result", result);
        
        return mav;
    }
    
    /**
     * 고객메모 조회(Ajax)
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_custmemo_sch_ajax.do")
    public ModelAndView co_custmemo_sch_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String cstNo   = StringUtil.getParam(request, "cstNo","");        // 고객번호
        
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
        
        List<PopupCustMemoVO> result = null;  
        PopupCustMemoVO tbvcCstMemo = new PopupCustMemoVO();
        tbvcCstMemo.setCstNo(cstNo);
        result =  popupService.selectCstMemoDetail(tbvcCstMemo);
        
        mav.addObject("result", result);
        
        return mav;
    }
    
    /**
     * 고객메모
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_custmemo_sch.do")
    public ModelAndView co_custmemo_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String crudMode   = StringUtil.getParam(request, "crudMode"); 
        String cstNo      = StringUtil.getParam(request, "cstNo");
        String makeUser   = StringUtil.getParam(request, "makeUser");
        
        ModelAndView mav = new ModelAndView("common/popup/co_custmemo_sch");

        PopupCustMemoVO tbvcCstMemo = new PopupCustMemoVO();
        tbvcCstMemo.setCstNo(cstNo);
        
        List<PopupCustMemoVO> list = popupService.selectCstMemo(tbvcCstMemo);
        
        PopupCustMemoVO result = popupService.selectCstMemoLast(tbvcCstMemo);
        
        mav.addObject("resultMark", result);
        mav.addObject("result", list);
        
        return mav;
    }
    
    /**
     * 고객메모 등록
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_custmemo_sav.do")
    public ModelAndView co_custmemo_sav(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String crudMode   = StringUtil.getParam(request, "crudMode"); 
        String cstNo      = StringUtil.getParam(request, "cstNo");
        String makeUser   = StringUtil.getParam(request, "makeUser");
        
        ModelAndView mav = new ModelAndView("common/popup/co_custmemo_sav");

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();

        PopupCustMemoVO tbvcCstMemo = new PopupCustMemoVO();
        tbvcCstMemo.setCstNo(cstNo);
        tbvcCstMemo.setMakeUser(makeUser);
        
        if(crudMode.equals("C")){
            
            bind(request, tbvcCstMemo);
            tbvcCstMemo.setRegUser(userEmpno);
            tbvcCstMemo.setUpdtUser(userEmpno);
            String seqno = popupService.insertCstMemo(tbvcCstMemo);
            tbvcCstMemo.setSeqNo(seqno);
            mav.addObject("msg", "저장이 완료되었습니다.");
        }
        else if(crudMode.equals("U")){
            
            bind(request, tbvcCstMemo);
            tbvcCstMemo.setUpdtUser(userEmpno);
            popupService.updateCstMemo(tbvcCstMemo);
            mav.addObject("msg", "수정이 완료되었습니다.");
        }
        
        PopupCustMemoVO result = popupService.selectCstMemoInfo(tbvcCstMemo);
        
        mav.addObject("result", result);
        
        return mav;
    }

    /**
     * 내 담당업무 정보 관리
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/common/popup/co_work_chg.do")
    public ModelAndView co_work_chg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId      = StringUtil.getParam(request, "userId", "");
        String crudMode      = StringUtil.getParam(request, "crudMode", "");
        ModelAndView mav = new ModelAndView("common/popup/co_work_chg");
        
        UserVO tbvcUsrWrk = new UserVO();
        bind(request, tbvcUsrWrk);
        
        if("S".equals(crudMode)){
            tbvcUsrWrk.setCnfmYn("Y");
            popupService.updateUsrWorkInfo(tbvcUsrWrk);
            
            mav = new ModelAndView("success_script", "scriptBlock", "alertify.alert('저장 되었습니다.');opener.fnOpenMainNoRelogin();window.close();");

        }
        else{
            SkinMstVO skinInfo = commonService.getSkinInfo();
            UserVO tbvcUsr = popupService.selectUsrWorkInfo(userId);
            UserSession uskin = new UserSession(tbvcUsr, skinInfo);
//            WebUtils.setSessionAttribute(request, "userSession", uskin);

            mav.addObject("uskin", uskin);
            mav.addObject("result", tbvcUsr);
        }
        return mav;
    }

	/**
	 * 승인단계 조회(팝업)
	 * 
	 * @param request
	 * @param response
	 */
    @RequestMapping("/common/popup/co_apvmng_pop.do")
    public ModelAndView co_apvmng_pop(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String crudMode = StringUtil.getParam(request, "crudMode");
		String[] apvStag = request.getParameterValues("apvStag");
		String[] mndtYn = request.getParameterValues("mndtYn");
		String[] pstNm = request.getParameterValues("pstNm");
		String[] pstCd = request.getParameterValues("pstCd");
		String[] apvuserEmpno = request.getParameterValues("apvuserEmpno");

		String activeTabId = StringUtil.getParam(request, "activeTabId");

		ModelAndView mav = new ModelAndView("common/popup/co_apvmng_pop");

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
        
        // 승인요청
		if ("C".equals(crudMode)) 
		{
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
				approvalVO.setMndtYn(mndtYn[i]);
				approvalVO.setPstCd(pstCd[i]);
				approvalVO.setPstNm(pstNm[i]);
				apvInsertList.add(approvalVO);
			}

			approvalService.insertApvMg(apvInsertList);
			mav.addObject("openerActiveTabId",activeTabId);
			
			return mav;
		} else if ("AD".equals(crudMode)) {
			approvalService.deleteAllApvMg(userEmpno);
			mav.addObject("openerActiveTabId",activeTabId);
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
			
			approvalVO.setUserEmpno(userEmpno);
			approvalVO.setApvStag(apvStag[0]);
			approvalVO.setApvuserEmpno(apvuserEmpno[0]);
			
			approvalService.deleteApvMg(approvalVO);
			mav.addObject("openerActiveTabId",activeTabId);
			
			return mav;
		} else if ("US".equals(crudMode)) {
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			String depCd = StringUtil.getParam(request, "depCd", "");
			String schUserNm = StringUtil.getParam(request, "schUserNm");
			
	        List<ApprovalVO> result = null;
	        int intTotalCount  = 0;

	        approvalVO.setUserEmpno(userEmpno);
	        approvalVO.setUserId(userId);
	        approvalVO.setDepCd(depCd);
	        approvalVO.setUserNm(schUserNm);
	        
	        result = approvalService.selectUserList(approvalVO);
	        intTotalCount  = approvalService.selectUserListCount(approvalVO);

	        mav.addObject("rows", result);
	        mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
	        
			return mav;
		} else {
			DepartMentVO org = new DepartMentVO();
	        List result = departMentService.selectOrganization(org);
			mav.addObject("result", result);
		}
		
		mav.addObject("openerActiveTabId", activeTabId);
		return mav;
	}

	/**
	 * 승인이력 조회
	 * 
	 * @param request
	 * @param response
	 */
    @RequestMapping("/common/popup/co_apvhist_sch.do")
    public ModelAndView co_apvhist_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String apvId = StringUtil.getParam(request, "apvId", "");
		String crudMode = StringUtil.getParam(request, "crudMode", "");

		ModelAndView mav = new ModelAndView("common/popup/co_apvhist_sch");
		
		if("S".equals(crudMode)){
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
			int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "20")); // 페이지당 몇개
			    
			PopupApprovalVO popupApprovalVO = new PopupApprovalVO();
			
			popupApprovalVO.setApvId(apvId);
			
			popupApprovalVO.setStartNo(((page * rows) - rows) + 1);
			popupApprovalVO.setEndNo(page * rows);
	        
			List resultList = popupService.selectApvHistList(popupApprovalVO);
			
			int intTotalCount = popupService.selectApvHistListCount(popupApprovalVO);
			
			
			 mav.addObject("total", Math.ceil((double)intTotalCount/rows));  // total page
	         mav.addObject("records", intTotalCount);                        // total count
	         mav.addObject("rows", resultList);
		}
		        
		return mav;
	}
    
    
    /**
	 * 배분이력 조회
	 * 
	 * @param request
	 * @param response
	 */
    @RequestMapping("/common/popup/co_dstrhist_sch.do")
    public ModelAndView co_dstrhist_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String vocId = StringUtil.getParam(request, "vocId", "");
		String crudMode = StringUtil.getParam(request, "crudMode", "");

		ModelAndView mav = new ModelAndView("common/popup/co_dstrhist_sch");
		
		if("S".equals(crudMode)){
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
	        int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));
	        
	        String sidx = StringUtil.getParam(request, "sidx", "");
	        String sord = StringUtil.getParam(request, "sord", "");
			
			NeedsMstVO needsMstVO = new NeedsMstVO();
			
			needsMstVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
			needsMstVO.setEndNo(pageNo * pageSize);
	        needsMstVO.setSidx(sidx);
	        needsMstVO.setSord(sord);
			needsMstVO.setVocId(vocId);
			needsMstVO.setSeqno(pageNo+"");
			
			List resultList = popupService.selectDstrHist(needsMstVO);
			
			int intTotalCount = popupService.selectDstrHistCount(needsMstVO);
			
			mav.addObject("rows", resultList);
			mav.addObject("records", intTotalCount);
			mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
			
		}
		        
		mav.addObject("F016",  this.getCommonCode("F016"));
		return mav;
	}
    
    /**
     * 비밀번호 변경 관리
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/common/popup/co_pwd_chg.do")
    public ModelAndView co_pwd_chg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String crudMode  = StringUtil.getParam(request, "crudMode", "");
        String changMode = StringUtil.getParam(request, "changMode", "");
        ModelAndView mav = new ModelAndView("common/popup/co_pwd_chg");
        
        UserVO tbvcUsr = new UserVO();
        bind(request, tbvcUsr);
        String pwd = tbvcUsr.getPwd();
        if("S".equals(crudMode)){
        	if(!"".equals(tbvcUsr.getPwd())) {
        		tbvcUsr.setBefore(EncyptShaUtil.encryptSHA(tbvcUsr.getBefore()));
        		tbvcUsr.setPwd(EncyptShaUtil.encryptSHA(tbvcUsr.getPwd()));
        	}
        	
        	int cnt = popupService.updateUsrWorkInfo(tbvcUsr);

        	if("Y".equals(changMode)){
                mav = new ModelAndView("success_script", "scriptBlock", "alertify.alert('변경 되었습니다.');window.close();");
        	}
        	else{
                mav = new ModelAndView("success_script", "scriptBlock", "alertify.alert('변경 되었습니다.');opener.fnOpenMain('"+ pwd +"');window.close();");
        	}
        	
        	if(cnt == 0){
        		mav = new ModelAndView("success_script", "scriptBlock", "alertify.alert('이전 비밀번호가 틀립니다.');window.close();");
        	}
        }
        else{
            SkinMstVO skinInfo = commonService.getSkinInfo();
            UserVO tbvcUsr2 = popupService.selectUsrWorkInfo(tbvcUsr.getUserId());
            UserSession uskin = new UserSession(tbvcUsr2, skinInfo);
            WebUtils.setSessionAttribute(request, "userSession", uskin);

            mav.addObject("uskin", uskin);
            mav.addObject("result", tbvcUsr2);
        }
        return mav;
    }
    
    @RequestMapping("/common/popup/co_emailsend_pop.do")
    public ModelAndView co_emailsend_pop(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String sendUser = StringUtil.getParam(request, "sendUser", "");
    	String sendEmail = StringUtil.getParam(request, "sendEmail", "");
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
    	String mode = StringUtil.getParam(request, "mode", "2");
    	String receiveUser = StringUtil.getParam(request, "receiveUser", "");
    	String receiveEmail = StringUtil.getParam(request, "receiveEmail", "");
    	String tit = StringUtil.getParam(request, "tit", "");
    	String cntn = StringUtil.getParam(request, "cntn", "");
    	String cstNo = StringUtil.getParam(request, "cstNo", "");
    	
    	String vocId = StringUtil.getParam(request, "vocId", "");
    	
    	UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
    	String userId = userSession.getLogin().getUserId();
    	
    	ModelAndView mav = new ModelAndView("/common/popup/co_emailsend_pop");

		EmailTmplVO tmplResult  = new EmailTmplVO();
    	if("1".equals(mode)){ //게시판 처리완료 답변메세지 셋팅
    		EmailTmplVO emailTmplVO = new EmailTmplVO();
    		tmplResult = emailTmplService.selectTmplInfoVoc(emailTmplVO);
    	}
    	
    	mav.addObject("tmplResult", tmplResult);
    	mav.addObject("userSession", userSession);
    	return mav;
    }
    
    @RequestMapping("/common/popup/co_emailsend_frame.do")
    public ModelAndView co_emailsend_frame(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView mav = new ModelAndView("/common/popup/co_emailsend_frame");
    	EmailTmplVO emailTmplVO = new EmailTmplVO();

		List<EmailTmplVO> result = emailTmplService.selectEmailTmplList(emailTmplVO);
		
		mav.addObject("result", result);
		return mav;
    }
    
    @RequestMapping("/common/popup/co_smssend_pop.do")
    public ModelAndView co_smssend_pop(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
    	String mode = StringUtil.getParam(request, "mode", "2");
    	String callFromHp = StringUtil.getParam(request, "callFromHp", "");
    	String callToHp = StringUtil.getParam(request, "callToHp", "");
    	String cstNo = StringUtil.getParam(request, "cstNo", "");
    	String msg = StringUtil.getParam(request, "msg", "");
    	String cstNm = StringUtil.getParam(request, "cstNm", "");
    	UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
    	String userId = userSession.getLogin().getUserId();
    	
    	ModelAndView mav = new ModelAndView("/common/popup/co_smssend_pop");
    	
		EmailTmplVO tmplResult  = new EmailTmplVO();
    	if("1".equals(mode)){ //게시판 처리완료 답변메세지 셋팅
    		EmailTmplVO emailTmplVO = new EmailTmplVO();
    		tmplResult = emailTmplService.selectTmplInfoVoc(emailTmplVO);
    	}
    	
    	mav.addObject("tmplResult", tmplResult);
    	return mav;
    }
    

    @RequestMapping("/common/popup/co_pushsend_pop.do")
    public ModelAndView co_pushsend_pop(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
    	String mode = StringUtil.getParam(request, "mode", "2");
    	String cstNo = StringUtil.getParam(request, "cstNo", "");
    	String msg = StringUtil.getParam(request, "msg", "");
    	String cstNm = StringUtil.getParam(request, "cstNm", "");
    	UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
    	String userId = userSession.getLogin().getUserId();
    	
    	ModelAndView mav = new ModelAndView("/common/popup/co_pushsend_pop");
    	
		EmailTmplVO tmplResult  = new EmailTmplVO();
    	if("1".equals(mode)){ //게시판 처리완료 답변메세지 셋팅
    		EmailTmplVO emailTmplVO = new EmailTmplVO();
    		tmplResult = emailTmplService.selectTmplInfoVoc(emailTmplVO);
    	}
    	
    	mav.addObject("tmplResult", tmplResult);
    	return mav;
    }
    
	/**
     * 복합VOC 부서찾기
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_cmpxvoc_sch.do")
    public ModelAndView co_cmpxvoc_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String schDepNm   = StringUtil.getParam(request, "schDepNm"); // 부서명
        String crudMode 	= StringUtil.getParam(request, "crudMode", "R");
       
        
        ModelAndView mav = new ModelAndView("common/popup/co_cmpxvoc_sch");
        
        DepartMentVO departMentVO = new DepartMentVO();
        departMentVO.setDepNm(schDepNm);
	  
        if("R".equals(crudMode)){
			List<DepartMentVO> result =  popupService.selectDepTree(departMentVO);
				mav.addObject("result", result);
			}

		return mav;
    }
    
	/**
     * 변경이력
     * @param request
     * @param response
     */
	@RequestMapping("/common/popup/co_chghistory_pop.do")
    public ModelAndView co_chghistory_pop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String crudMode 	= StringUtil.getParam(request, "crudMode", "");
		String propId   = StringUtil.getParam(request, "propId");
		
		ModelAndView mav = new ModelAndView("common/popup/co_chghistory_pop");
		
		PopupPropChngHistVO popupPropChngHistVO = new PopupPropChngHistVO();
		popupPropChngHistVO.setPropId(propId);
		
		List<PopupPropChngHistVO> result = popupService.selectPropChngHistList(popupPropChngHistVO);
		
		mav.addObject("result", result);
		
		return mav;
	}
	

    /**
	 * 제안 배분이력 조회
	 * 
	 * @param request
	 * @param response
	 */
    @RequestMapping("/common/popup/co_propdstrhist_sch.do")
    public ModelAndView co_propdstrhist_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String propId = StringUtil.getParam(request, "propId", "");
		String crudMode = StringUtil.getParam(request, "crudMode", "");

		ModelAndView mav = new ModelAndView("common/popup/co_propdstrhist_sch");
		
		if("S".equals(crudMode)){
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
	        int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));
	        
	        String sidx = StringUtil.getParam(request, "sidx", "");
	        String sord = StringUtil.getParam(request, "sord", "");
			
			PopupPropAsgnHistVO popupPropAsgnHistVO = new PopupPropAsgnHistVO();
			
			popupPropAsgnHistVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
			popupPropAsgnHistVO.setEndNo(pageNo * pageSize);
			popupPropAsgnHistVO.setSidx(sidx);
	        popupPropAsgnHistVO.setSord(sord);
	        popupPropAsgnHistVO.setPropId(propId);
			
			List resultList = popupService.selectPropAsgnHistList(popupPropAsgnHistVO);
			
			int intTotalCount = popupService.selectPropAsgnHistListCount(popupPropAsgnHistVO);
			
			mav.addObject("rows", resultList);
			mav.addObject("records", intTotalCount);
			mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
			
			return mav;
		}
		return mav;
	}
    
    /**
     * 제품검색 팝업 (트리)
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_prducttree_sch.do")
	public ModelAndView co_prducttree_sch(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String crudMode 	= StringUtil.getParam(request, "crudMode", "");
        String schLvl 		= StringUtil.getParam(request, "schLvl", "");
        String schUpTypeCd 	= StringUtil.getParam(request, "schUpTypeCd", "");
        String schTopTypeCd = StringUtil.getParam(request, "schTopTypeCd", "");
        String prductL		= StringUtil.getParam(request, "prductL", "");
        String prductM		= StringUtil.getParam(request, "prductM", "");
        String prductS		= StringUtil.getParam(request, "prductS", "");
        String prductB		= StringUtil.getParam(request, "prductB", "");
        
        ModelAndView mav = new ModelAndView("common/popup/co_prducttree_sch");
        
 
        
        PopupPrductVO popupPrductVO = new PopupPrductVO();
			  
		if("R".equals(crudMode)){
			//제품분류(대,중,소) 검색
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			popupPrductVO.setLvl(schLvl);
			popupPrductVO.setUpTypeCd(schUpTypeCd);
			popupPrductVO.setTopTypeCd(schTopTypeCd);		
						
			
			List<PopupPrductVO> result =  popupService.selectPrductType(popupPrductVO);
		
			mav.addObject("result", result);
		}else if("S".equals(crudMode)){
			//개별 제품 검색
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			popupPrductVO.setLcls(prductL);
			popupPrductVO.setMcls(prductM);
			popupPrductVO.setScls(prductS);
			popupPrductVO.setBossCd(prductB);
			
			List<PopupPrductVO> result =  popupService.selectPrductList(popupPrductVO);
			
			mav.addObject("result", result);
			
		}else if("TS".equals(crudMode)){
			//대표제품 검색
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			popupPrductVO.setLcls(prductL);
			popupPrductVO.setMcls(prductM);
			popupPrductVO.setScls(prductS);
			
			List<PopupPrductVO> result =  popupService.selectPrductBossType(popupPrductVO);
			
			mav.addObject("result", result);
		}

		return mav;
    }
    
    /**
     * 클레임 처리화면(팝업)
     * @param request
     * @param response
     */
    @RequestMapping("/web/slider.do")
    public ModelAndView claiminfo_pop(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView mav = new ModelAndView("web/slider");
    	MenuVO vo = new MenuVO();
    	vo.setViewOrd("asc");
    	List<MenuVO> list = menuService.selectPageList(vo);
    	
    	mav.addObject("result", list);
    	return mav;
    }
    
    /**
     * 클레임 접수번호 조회(ajax)
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_acepno_sch_ajax.do")
	public ModelAndView co_acepno_sch_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String schAcepno = StringUtil.getParam(request, "schAcepno", "").replace("-", "");
    	
    	System.out.println("co_acepno_sch_ajax 호출");
    	
    	ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		PopupAcepnoVO popupAcepnoVO = new PopupAcepnoVO();
		popupAcepnoVO.setAcepno(schAcepno);
		popupAcepnoVO.setSidx("acep_date");
		popupAcepnoVO.setSord("desc");
		List<PopupAcepnoVO> result = popupService.selectClaimList(popupAcepnoVO);
		
		mav.addObject("result", result);
		return mav;
    }
    /**
     * 클레임 접수번호 조회(팝업)
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_acepno_sch.do")
	public ModelAndView co_acepno_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String schStartDd = StringUtil.getParam(request, "schStartDd", "");
		String schEndDd = StringUtil.getParam(request, "schEndDd", "");
    	String schAcepno = StringUtil.getParam(request, "schAcepno", "").replace("-", "");
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
    	String kfda_flag = StringUtil.getParam(request, "kfda_flag","");
		
		ModelAndView mav = new ModelAndView("common/popup/co_acepno_sch");
		
		//조건 일자가 없다면 1주일셋팅
        SYSTEM system   =   SYSTEM.getInstance();
        String today    =   StringUtil.getParam(request, "YYYYMMDD", system.getDate());
        
        if( schStartDd == "" && schEndDd == ""){
            schStartDd = DateTimeUtil.calculateDate(today,-7);
            schEndDd = today;
        }
        
		if("S".equals(crudMode)){
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
			int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));

			String sidx = StringUtil.getParam(request, "sidx", "");
			String sord = StringUtil.getParam(request, "sord", "");
			
			
			
			PopupAcepnoVO popupAcepnoVO = new PopupAcepnoVO();
			popupAcepnoVO.setSchStartDd(schStartDd.replaceAll("-", ""));
			popupAcepnoVO.setSchEndDd(schEndDd.replaceAll("-", ""));
			popupAcepnoVO.setAcepno(schAcepno);
			popupAcepnoVO.setKfda_flag(kfda_flag);
			popupAcepnoVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
			popupAcepnoVO.setEndNo(pageNo * pageSize);
			popupAcepnoVO.setSidx(sidx);
			popupAcepnoVO.setSord(sord);
			
			List<PopupAcepnoVO> result = popupService.selectClaimList(popupAcepnoVO);
			int intTotalCount = popupService.selectClaimListCount(popupAcepnoVO);
			
			mav.addObject("rows", result);
			mav.addObject("records", intTotalCount);
			mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
		}
		
		return mav;
	}
    
    /**
     * 파일관리 팝업
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_filemanage_download_pop.do")
    public ModelAndView co_filemanage_download_pop(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String contsId  = StringUtil.getParam(request, "contsId", "");
    	String docRegNo = StringUtil.getParam(request, "docRegNo", "");
    	ModelAndView mav = new ModelAndView("common/popup/co_filemanage_download_pop");
    	
    	List files = attachfileService.getPotoAttachFileList(docRegNo, contsId);
    	
    	mav.addObject("files", files);
    	
    	
    	return mav;
    }
    
    /**
     * 파일관리 팝업
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_filemanage_pop.do")
	public ModelAndView co_filemanage_pop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String contsId  = StringUtil.getParam(request, "contsId", "");
		String docRegNo = StringUtil.getParam(request, "docRegNo", "");
	
    	ModelAndView mav = new ModelAndView("common/popup/co_filemanage_pop");
    	
    	// 하위 클레임 리스트가져오기.
    	
	    List files = attachfileService.getAttachFileList(docRegNo, contsId);
	    
	    mav.addObject("files", files);
	    
		    
		return mav;
	}
    @RequestMapping("/common/popup/co_filemanage_pop_pis.do")
   	public ModelAndView co_filemanage_pop_pis(HttpServletRequest request, HttpServletResponse response) throws Exception {
   		String keyVal01  = StringUtil.getParam(request, "keyVal01", "");
   		String keyVal02  = StringUtil.getParam(request, "keyVal02", "");
   		String keyVal03  = StringUtil.getParam(request, "keyVal03", "");
   		String keyVal04  = StringUtil.getParam(request, "keyVal04", "");
   		/*
   		   paramap.push('keyVal01='+ key ); //custCode
              paramap.push('keyVal02='+ data2 ); //matrCode
              paramap.push('keyVal03='+ data3 ); //chngNo
              paramap.push('keyVal04='+ data4 ); //gubn
   */		
   		String docRegNo = StringUtil.getParam(request, "docRegNo", "");

       	ModelAndView mav = new ModelAndView("common/popup/co_filemanage_pop_pis");
       	
       	
       	
       	// 하위 클레임 리스트가져오기.
       	
   	    List files = attachfileService.getAttachFileList( keyVal01,keyVal02,keyVal03,keyVal04);
   	    
   	    mav.addObject("files", files);
   	    
   		    
   		return mav;
   	}
    

    /**
     * 파일관리 팝업
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_filemanage_uploadstatus.do")
	public ModelAndView getFileUploadStatus(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		UploadProgressListener progressListener = new UploadProgressListener();
		session = progressListener.getHttpSession();		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("upstatus");	
	
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		

		int percent = 0;	//업로드 percent
		long bytesRead = 0;	//읽어들인 바이트
		long contentLength = 0;	//전체 바이트
		double kbps = 0;
		if(session==null){
			percent = 0;
			bytesRead = 0;
			contentLength = 0;
			kbps = 0;
		}else{
			percent = (Integer)session.getAttribute("percent");
			bytesRead = (Long)session.getAttribute("bytesread");
			contentLength = (Long)session.getAttribute("contentlength");
			kbps = (Double)session.getAttribute("kbps");
		}
		
		StringBuffer retJsonStr = new StringBuffer();
		retJsonStr.append("{percent:"+percent+",bytesread:"+bytesRead+",contentlength:"+contentLength+",kbps:"+kbps+"}");
		System.out.println("upload progress status JSON String : " + retJsonStr.toString());
		
		mav.addObject("result", retJsonStr.toString());
		return mav;
	}

    /**
     * 파일관리 팝업
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_filemanage_delete.do")
	public ModelAndView co_filemanage_delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String contsId  = StringUtil.getParam(request, "contsId", "");
		String docRegNo = StringUtil.getParam(request, "docRegNo", "");
		String atchFileChngName = StringUtil.getParam(request, "atchFileChngName", "");


		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");

		List<AttachFileVO> list = new ArrayList<AttachFileVO>();
		
		AttachFileVO record = new AttachFileVO();
		
		record.setContsId(contsId);
		record.setDocRegNo(docRegNo);
		record.setAtchFileChngName(atchFileChngName);
		
		list.add(record);
		
		System.out.println("co_filemanage_delete 메소드 호출");
		AttachFileVO result = attachfileService.selectByPk(record);
		
		if(null != result){

			// DB첨부파일정보 삭제
			attachfileService.deleteAttachFile(list);
			
			// 물리적 파일 삭제
			File deleteFile = new File(StaticConfig.APP_ROOT_DIR + result.getAtchDataPath() + result.getAtchFileChngName());
			deleteFile.delete();
			mav.addObject("delete", "delete"); 
			

            // 로그인 사용자정보 가져오기
            UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
            String userEmpno = userSession.getLogin().getUserEmpno();
            result.setUpdtUser(userEmpno);
            result.setUpdtUser(userEmpno);
            
            
            popupService.updateClaimFileInfo(result);
            
		}
		
		return mav;
	}
    
    @RequestMapping("/common/popup/co_product_sch_ajax.do")
    public ModelAndView co_prduct_sch_bak_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String schPrductNm   = StringUtil.getParam(request, "schPrductNm"); 
    	String schPrductCd   = StringUtil.getParam(request, "schPrductCd"); 
		String id = StringUtil.getParam(request, "id", "");
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		PopupPrductVO popupPrductVO = new PopupPrductVO();
		
		popupPrductVO.setPrductCd(schPrductCd);
		popupPrductVO.setPrductNm(schPrductNm);
		popupPrductVO.setSaleOrg("1");
		
		
		List<PopupPrductVO> result =  null;
		result = popupService.selectPrductList(popupPrductVO);
		
		mav.addObject("result", result);
		mav.addObject("id", id);
		return mav;
    }
    
    @RequestMapping("/common/popup/co_prduct_sch_ajax.do")
    public ModelAndView co_prduct_sch_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String schPrductNm   = StringUtil.getParam(request, "schPrductNm"); 
    	String schPrductCd   = StringUtil.getParam(request, "schPrductCd"); 
		String id = StringUtil.getParam(request, "id", "");
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		PopupPrductVO popupPrductVO = new PopupPrductVO();
		
		popupPrductVO.setPrductCd(schPrductCd);
		popupPrductVO.setPrductNm(schPrductNm);
		popupPrductVO.setSaleOrg("1");
		
		
		List<PopupPrductVO> result =  null;
		result = popupService.selectPrductList(popupPrductVO);
		
		mav.addObject("result", result);
		mav.addObject("id", id);
		return mav;
    }

    @RequestMapping("/common/popup/co_prduct_sch_ptype_ajax.do")
    public ModelAndView co_prduct_sch_ptype_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String schPrductNm   = StringUtil.getParam(request, "schPrductNm"); 
    	String schPrductCd   = StringUtil.getParam(request, "schPrductCd"); 
		String id = StringUtil.getParam(request, "id", "");
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		PopupPrductVO popupPrductVO = new PopupPrductVO();
		
		popupPrductVO.setPrductCd(schPrductCd);
		popupPrductVO.setPrductNm(schPrductNm);
		popupPrductVO.setSaleOrg("1");
		
		
		List<PopupPrductVO> result =  null;
		result = popupService.selectPrductList(popupPrductVO);
		
		mav.addObject("result", result);
		mav.addObject("id", id);
		return mav;
    }
    
    @RequestMapping("/common/popup/co_prduct_sch.do")
    public ModelAndView co_prduct_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
    	String gubn = StringUtil.getParam(request, "gubn", "");
    	String schLvl = StringUtil.getParam(request, "schLvl", "");
    	String schTopTypeCd = StringUtil.getParam(request, "schTopTypeCd", "");
    	String schUpTypeCd = StringUtil.getParam(request, "schUpTypeCd", "");
    	String schLcls = StringUtil.getParam(request, "schLcls", "");
    	String schMcls = StringUtil.getParam(request, "schMcls", "");
    	String schScls = StringUtil.getParam(request, "schScls", "");
    	String schBossCd = StringUtil.getParam(request, "schBoss", "");
    	String schPrductNm = StringUtil.getParam(request, "schPrductNm", "");
    	String schPrductCd = StringUtil.getParam(request, "schPrductCd", "");
    	String schProdTypeCode = StringUtil.getParam(request, "schProdTypeCode", "");
    	String schSaleOrg = StringUtil.getParam(request, "schSaleOrg", "");
    	
    
    	String applPricCode = StringUtil.getParam(request, "applPricCode", "");
    	
    	String sidx = StringUtil.getParam(request, "sidx", "");
    	String sord = StringUtil.getParam(request, "sord", "");
    	
    	ModelAndView mav = new ModelAndView("common/popup/co_prduct_sch");
    	
    	if("S".equals(crudMode)){
    		mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			String sellDenYn = StringUtil.getParam(request, "sellDvnYn", "");
			
			int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
			int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));

			PopupPrductVO popupPrductVO = new PopupPrductVO();
			popupPrductVO.setLcls(schLcls);
			popupPrductVO.setApplPricCode(applPricCode);
			popupPrductVO.setSellDvn(sellDenYn);
			popupPrductVO.setMcls(schMcls);
			popupPrductVO.setScls(schScls);
			popupPrductVO.setBossCd(schBossCd);
			popupPrductVO.setPrductNm(schPrductNm);
			popupPrductVO.setPrductCd(schPrductCd);
			popupPrductVO.setSaleOrg(schSaleOrg);
			popupPrductVO.setProdTypeCode(schProdTypeCode);
			popupPrductVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
			popupPrductVO.setEndNo(pageNo * pageSize);
			popupPrductVO.setSidx(sidx);
			popupPrductVO.setSord(sord);
			
			List<PopupPrductVO> result = null;
			int intTotalCount;
			
			if("2".equals(gubn)){
				
				result = popupService.selectPrductList_Halb(popupPrductVO);
				intTotalCount = popupService.selectPrductList_HalbCount(popupPrductVO);
			}else{
				
				result = popupService.selectPrductList(popupPrductVO);
				intTotalCount = popupService.selectPrductListCount(popupPrductVO);
			}
			
			 
			
			mav.addObject("rows", result);
			mav.addObject("records", intTotalCount);
			mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
			
    	}else if("B".equals(crudMode)){
    		mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
	        String empNo = userSession.getLogin().getUserEmpno();
	        
			PopupPrductVO popupPrductVO = new PopupPrductVO();
			popupPrductVO.setLcls(schLcls);
			popupPrductVO.setMcls(schMcls);
			popupPrductVO.setScls(schScls);
			popupPrductVO.setBossCd(schBossCd);
			popupPrductVO.setPrductNm(schPrductNm);
			popupPrductVO.setPrductCd(schPrductCd);
			popupPrductVO.setSaleOrg(schSaleOrg);
			popupPrductVO.setProdTypeCode(schProdTypeCode);
			popupPrductVO.setSidx(sidx);
			popupPrductVO.setSord(sord);
			popupPrductVO.setEmpNo(empNo);
			
			List<PopupPrductVO> result = popupService.selectProdBkmkList(popupPrductVO);
			int intTotalCount = 0;
			
			if(result != null){
				intTotalCount = result.size();
			}
			
			mav.addObject("rows", result);
			mav.addObject("records", intTotalCount);
			
    	}else if("TS".equals(crudMode)){
    		mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			PopupPrductVO popupPrductVO = new PopupPrductVO();
			
			popupPrductVO.setLvl(schLvl);
			popupPrductVO.setTopTypeCd(schTopTypeCd);
			popupPrductVO.setUpTypeCd(schUpTypeCd);
			
			List<PopupPrductVO> result = popupService.selectPrductType(popupPrductVO);
			
			mav.addObject("result", result);
    	}else if("BS".equals(crudMode)){
			//대표제품 검색
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			PopupPrductVO popupPrductVO = new PopupPrductVO();
			
			popupPrductVO.setLcls(schLcls);
			popupPrductVO.setMcls(schMcls);
			popupPrductVO.setScls(schScls);
			
			List<PopupPrductVO> result =  popupService.selectPrductBossType(popupPrductVO);
			
			mav.addObject("result", result);
		}
    	
    	mav.addObject("N037", this.getCommonCode("N037"));  
    	mav.addObject("N016", this.getCommonCode("N016"));  
    	
    	return mav;
    }
       
    /**
     * 제품정보 조회(팝업)
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_prduct_sch_bak.do")
    public ModelAndView co_prduct_sch_bak(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
    	String gubn = StringUtil.getParam(request, "gubn", "");
    	String schLvl = StringUtil.getParam(request, "schLvl", "");
    	String schTopTypeCd = StringUtil.getParam(request, "schTopTypeCd", "");
    	String schUpTypeCd = StringUtil.getParam(request, "schUpTypeCd", "");
    	String schLcls = StringUtil.getParam(request, "schLcls", "");
    	String schMcls = StringUtil.getParam(request, "schMcls", "");
    	String schScls = StringUtil.getParam(request, "schScls", "");
    	String schBossCd = StringUtil.getParam(request, "schBoss", "");
    	String schPrductNm = StringUtil.getParam(request, "schPrductNm", "");
    	String schPrductCd = StringUtil.getParam(request, "schPrductCd", "");
    	String schProdTypeCode = StringUtil.getParam(request, "schProdTypeCode", "");
    	String schSaleOrg = StringUtil.getParam(request, "schSaleOrg", "");
    	
    
    	String applPricCode = StringUtil.getParam(request, "applPricCode", "");
    	
    	String sidx = StringUtil.getParam(request, "sidx", "");
    	String sord = StringUtil.getParam(request, "sord", "");
    	
    	ModelAndView mav = new ModelAndView("common/popup/co_prduct_sch_bak");
    	
    	if("S".equals(crudMode)){
    		mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			String sellDenYn = StringUtil.getParam(request, "sellDvnYn", "");
			
			int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
			int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));
			
			PopupPrductVO popupPrductVO = new PopupPrductVO();
			popupPrductVO.setLcls(schLcls);
			popupPrductVO.setApplPricCode(applPricCode);
			popupPrductVO.setSellDvn(sellDenYn);
			popupPrductVO.setMcls(schMcls);
			popupPrductVO.setScls(schScls);
			popupPrductVO.setBossCd(schBossCd);
			popupPrductVO.setPrductNm(schPrductNm);
			popupPrductVO.setPrductCd(schPrductCd);
			popupPrductVO.setSaleOrg(schSaleOrg);
			popupPrductVO.setProdTypeCode(schProdTypeCode);
			popupPrductVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
			popupPrductVO.setEndNo(pageNo * pageSize);
			popupPrductVO.setSidx(sidx);
			popupPrductVO.setSord(sord);
			
			List<PopupPrductVO> result = null;
			int intTotalCount ;
			
			if("2".equals(gubn)){
				
				result = popupService.selectPrductList_Halb(popupPrductVO);
				intTotalCount = popupService.selectPrductList_HalbCount(popupPrductVO);
			}else{
				
				result = popupService.selectPrductList(popupPrductVO);
				intTotalCount = popupService.selectPrductListCount(popupPrductVO);
			}
			
			mav.addObject("rows", result);
			mav.addObject("records", intTotalCount);
			mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
			
    	}else if("B".equals(crudMode)){
    		mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
	        String empNo = userSession.getLogin().getUserEmpno();
	        
			PopupPrductVO popupPrductVO = new PopupPrductVO();
			popupPrductVO.setLcls(schLcls);
			popupPrductVO.setMcls(schMcls);
			popupPrductVO.setScls(schScls);
			popupPrductVO.setBossCd(schBossCd);
			popupPrductVO.setPrductNm(schPrductNm);
			popupPrductVO.setPrductCd(schPrductCd);
			popupPrductVO.setSaleOrg(schSaleOrg);
			popupPrductVO.setProdTypeCode(schProdTypeCode);
			popupPrductVO.setSidx(sidx);
			popupPrductVO.setSord(sord);
			popupPrductVO.setEmpNo(empNo);
			
			List<PopupPrductVO> result = popupService.selectProdBkmkList(popupPrductVO);
			int intTotalCount = 0;
			
			if(result != null){
				intTotalCount = result.size();
			}
			
			mav.addObject("rows", result);
			mav.addObject("records", intTotalCount);
			
    	}else if("TS".equals(crudMode)){
    		mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			PopupPrductVO popupPrductVO = new PopupPrductVO();
			
			popupPrductVO.setLvl(schLvl);
			popupPrductVO.setTopTypeCd(schTopTypeCd);
			popupPrductVO.setUpTypeCd(schUpTypeCd);
			
			List<PopupPrductVO> result = popupService.selectPrductType(popupPrductVO);
			
			mav.addObject("result", result);
    	}else if("BS".equals(crudMode)){
			//대표제품 검색
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			PopupPrductVO popupPrductVO = new PopupPrductVO();
			
			popupPrductVO.setLcls(schLcls);
			popupPrductVO.setMcls(schMcls);
			popupPrductVO.setScls(schScls);
			
			List<PopupPrductVO> result =  popupService.selectPrductBossType(popupPrductVO);
			
			mav.addObject("result", result);
		}
    	
    	mav.addObject("N037", this.getCommonCode("N037"));  
    	mav.addObject("N016", this.getCommonCode("N016"));  
    	
    	return mav;
    }
    
    
    
    @RequestMapping("/common/popup/co_product_sch.do")
    public ModelAndView co_prduct_sch_ptype(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
    	String gubn = StringUtil.getParam(request, "gubn", "");
    	String schLvl = StringUtil.getParam(request, "schLvl", "");
    	String schTopTypeCd = StringUtil.getParam(request, "schTopTypeCd", "");
    	String schUpTypeCd = StringUtil.getParam(request, "schUpTypeCd", "");
    	String schLcls = StringUtil.getParam(request, "schLcls", "");
    	String schMcls = StringUtil.getParam(request, "schMcls", "");
    	String schScls = StringUtil.getParam(request, "schScls", "");
    	String schBossCd = StringUtil.getParam(request, "schBoss", "");
    	String schPrductNm = StringUtil.getParam(request, "schPrductNm", "");
    	String schPrductCd = StringUtil.getParam(request, "schPrductCd", "");
    	String schProdTypeCode = StringUtil.getParam(request, "schProdTypeCode", "");
    	String schSaleOrg = StringUtil.getParam(request, "schSaleOrg", "");
    
    
    	String applPricCode = StringUtil.getParam(request, "applPricCode", "");
    	
    	String sidx = StringUtil.getParam(request, "sidx", "");
    	String sord = StringUtil.getParam(request, "sord", "");
    	
    	ModelAndView mav = new ModelAndView("common/popup/co_product_sch_ptype");
    	
    	if("S".equals(crudMode)){
    		mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			String sellDenYn = StringUtil.getParam(request, "sellDvnYn", "");
			
			int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
			int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));

			
			PopupPrductVO popupPrductVO = new PopupPrductVO();
			popupPrductVO.setLcls(schLcls);
			popupPrductVO.setApplPricCode(applPricCode);
			popupPrductVO.setSellDvn(sellDenYn);
			popupPrductVO.setMcls(schMcls);
			popupPrductVO.setScls(schScls);
			popupPrductVO.setBossCd(schBossCd);
			popupPrductVO.setPrductNm(schPrductNm);
			popupPrductVO.setPrductCd(schPrductCd);
			popupPrductVO.setSaleOrg(schSaleOrg);
			popupPrductVO.setProdTypeCode(schProdTypeCode);
			popupPrductVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
			popupPrductVO.setEndNo(pageNo * pageSize);
			popupPrductVO.setSidx(sidx);
			popupPrductVO.setSord(sord);
			List<PopupPrductVO> result = null;
			
			result = popupService.selectPrductList_pType(popupPrductVO);
			
			int intTotalCount = popupService.selectPrductList_pTypeCount(popupPrductVO);
			
			mav.addObject("rows", result);
			mav.addObject("records", intTotalCount);
			mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
			
    	}else if("B".equals(crudMode)){
    		mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
	        String empNo = userSession.getLogin().getUserEmpno();
	        
			PopupPrductVO popupPrductVO = new PopupPrductVO();
			popupPrductVO.setLcls(schLcls);
			popupPrductVO.setMcls(schMcls);
			popupPrductVO.setScls(schScls);
			popupPrductVO.setBossCd(schBossCd);
			popupPrductVO.setPrductNm(schPrductNm);
			popupPrductVO.setPrductCd(schPrductCd);
			popupPrductVO.setSaleOrg(schSaleOrg);
			popupPrductVO.setProdTypeCode(schProdTypeCode);
			popupPrductVO.setSidx(sidx);
			popupPrductVO.setSord(sord);
			popupPrductVO.setEmpNo(empNo);
			
			List<PopupPrductVO> result = popupService.selectProdBkmkList(popupPrductVO);
			int intTotalCount = 0;
			
			if(result != null){
				intTotalCount = result.size();
			}
			
			mav.addObject("rows", result);
			mav.addObject("records", intTotalCount);
			
    	}else if("TS".equals(crudMode)){
    		mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			PopupPrductVO popupPrductVO = new PopupPrductVO();
			
			popupPrductVO.setLvl(schLvl);
			popupPrductVO.setTopTypeCd(schTopTypeCd);
			popupPrductVO.setUpTypeCd(schUpTypeCd);
			
			List<PopupPrductVO> result = popupService.selectPrductType(popupPrductVO);
			
			mav.addObject("result", result);
    	}else if("BS".equals(crudMode)){
			//대표제품 검색
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			PopupPrductVO popupPrductVO = new PopupPrductVO();
			
			popupPrductVO.setLcls(schLcls);
			popupPrductVO.setMcls(schMcls);
			popupPrductVO.setScls(schScls);
			
			List<PopupPrductVO> result =  popupService.selectPrductBossType(popupPrductVO);
			
			mav.addObject("result", result);
		}
    	
    	mav.addObject("N037", this.getCommonCode("N037"));  
    	mav.addObject("N016", this.getCommonCode("N016"));  
    	
    	return mav;
    }
    /**
     * 부서찾기(Ajax)
     * @param request
     * @param response
     */
    @RequestMapping("/common/popup/co_vendor_sch_ajax.do")
	public ModelAndView co_vendor_sch_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String vendorNm   = StringUtil.getParam(request, "schvendorNm"); // 사용자명
		
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		PopupVendorVO popupVendorVO = new PopupVendorVO();
		popupVendorVO.setVendorNm(vendorNm);
		
		List<PopupVendorVO> result =  popupService.selectVendorList(popupVendorVO);
		mav.addObject("result", result);
		    
		return mav;
	}
    
    @RequestMapping("/common/popup/partner_vendor_sch_ajax.do")
   	public ModelAndView partner_vendor_sch_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
   		String vendorNm   = StringUtil.getParam(request, "schvendorNm"); // 사용자명
   		
   		ModelAndView mav = new ModelAndView("jsonView");
   		response.setContentType("application/json; charset=utf-8");
   		response.setHeader("Cache-Control", "no-cache");
   		
   		PopupVendorVO popupVendorVO = new PopupVendorVO();
   		popupVendorVO.setVendorNm(vendorNm);
   		
   		List<PopupVendorVO> result =  popupService.selectVendorList(popupVendorVO);
   		mav.addObject("result", result);
   		    
   		return mav;
   	}
    
    @RequestMapping("/common/popup/co_vendor_sch.do")
    public ModelAndView co_vendor_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
    	String vendorCd = StringUtil.getParam(request, "vendorCd", "");
    	String vendorNm = StringUtil.getParam(request, "vendorNm", "");
    
    	
    	ModelAndView mav = new ModelAndView("common/popup/co_vendor_sch");
    	
    	if("S".equals(crudMode)){
    		mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			String sellDenYn = StringUtil.getParam(request, "sellDvnYn", "");
			
			int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "10")); // 페이지당 몇개
			int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
			int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));

			
			PopupVendorVO popupVendorVO = new PopupVendorVO();
			
			popupVendorVO.setVendorCd(vendorCd);
			popupVendorVO.setVendorNm(vendorNm);
			popupVendorVO.setStartNo(((page * rows) - rows) + 1);
	    	popupVendorVO.setEndNo(page * rows);
			
			List<PopupVendorVO> result =  popupService.selectVendorList(popupVendorVO);
			
			int intTotalCount =  popupService.selectVendorCount(popupVendorVO);
			
			mav.addObject("rows", result);
			mav.addObject("records", intTotalCount);
			mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
			
    	}
    	mav.addObject("N037", this.getCommonCode("N037"));  
    	mav.addObject("N016", this.getCommonCode("N016"));  
    	
    	return mav;
    }
    
    @RequestMapping("/common/popup/partner_vendor_sch.do")
    public ModelAndView partner_vendor_sch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String crudMode = StringUtil.getParam(request, "crudMode", "");
    	String vendorCd = StringUtil.getParam(request, "vendorCd", "");
    	String vendorNm = StringUtil.getParam(request, "vendorNm", "");
    
    	
    	ModelAndView mav = new ModelAndView("common/popup/partner_vendor_sch");
    	
    	if("S".equals(crudMode)){
    		mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			String sellDenYn = StringUtil.getParam(request, "sellDvnYn", "");
			
			int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "10")); // 페이지당 몇개
			int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
			int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "10"));

			
			PopupVendorVO popupVendorVO = new PopupVendorVO();
			
			popupVendorVO.setVendorCd(vendorCd);
			popupVendorVO.setVendorNm(vendorNm);
			popupVendorVO.setStartNo(((page * rows) - rows) + 1);
	    	popupVendorVO.setEndNo(page * rows);
			
			List<PopupVendorVO> result =  popupService.selectVendorList(popupVendorVO);
			
			int intTotalCount =  popupService.selectVendorCount(popupVendorVO);
			
			mav.addObject("rows", result);
			mav.addObject("records", intTotalCount);
			mav.addObject("total", Math.ceil((double)intTotalCount/pageSize));
			
    	}
    	mav.addObject("N037", this.getCommonCode("N037"));  
    	mav.addObject("N016", this.getCommonCode("N016"));  
    	
    	return mav;
    }

}