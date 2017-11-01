package nds.core.operation.ccmtmpl.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.operation.ccmtmpl.service.CcmTmplService;
import nds.core.operation.ccmtmpl.service.CcmTmplVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;


/**
 * <p>Title: CcmController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2015.12.02 초기작성(박민)</pre>
 * @author <a href="mailto:mpark@nds.co.kr">박민</a>
 * @version 1.0
 */
@Controller
public class CcmTmplController extends BaseController {
	
	/** CcmService */
    @Resource(name = "ccmtmplService")
    private CcmTmplService ccmTmplService;
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
    
    /**
     * CCM알림관리
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/operation/ccmtmpl/oprat1600.do")
	public ModelAndView oprat1600(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
     
        String schTmplNm = StringUtil.getParam(request, "schTmplNm", "");
        String schUseYn = StringUtil.getParam(request, "schUseYn", "");
        String schChnlCat = StringUtil.getParam(request, "schChnlCat", "");
        String schProcStat = StringUtil.getParam(request, "schProcStat", "");
        String schInoutDvn = StringUtil.getParam(request, "schInoutDvn", "");
        String schTypeDvn = StringUtil.getParam(request, "schTypeDvn", "C");
        
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        
		ModelAndView mav = new ModelAndView("operation/ccmtmpl/oprat1600");
		
		UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();
		
		CcmTmplVO vo = new CcmTmplVO();

		int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
        int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "20")); // 페이지당 몇개

        vo.setSchTmplNm(schTmplNm);
        vo.setSchChnlCat(schChnlCat);
        vo.setSchProcStat(schProcStat);
        vo.setSchUseYn(schUseYn);
        vo.setSchInoutDvn(schInoutDvn);
		
        vo.setTypeDvn(schTypeDvn);
		
        vo.setStartNo(((page * rows) - rows) + 1);
        vo.setEndNo(page * rows);
		
		// 정렬
        vo.setSidx(sidx);
        vo.setSord(sord);
		
        if("S".equals(crudMode)) {		//목록
        	mav = new ModelAndView("jsonView");
        	response.setContentType("application/json; charset=utf-8");
    		response.setHeader("Cache-Control", "no-cache");
    		
    		int total = ccmTmplService.selectCcmTmplCnt(vo);
    		List<CcmTmplVO> result = ccmTmplService.selectCcmTmplList(vo);
            
    		mav.addObject("total", Math.ceil((double)total/rows));  // total page
            mav.addObject("records", total);                        // total count
            mav.addObject("rows",  result);
        }
		
        // 컬럼 설정 조회
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("oprat1600");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
    	mav.addObject("F153", this.getCommonCode("F153"));
    	mav.addObject("F201", this.getCommonCode("F201"));
    	mav.addObject("F002", this.getCommonCode("F002"));
    	mav.addObject("F016", this.getCommonCode("F016"));
    	mav.addObject("F051", this.getCommonCode("F051"));
    	mav.addObject("F080", this.getCommonCode("F080"));
		return mav; 
	}
    
    
    /**
	 * CCM템플릿 등록/수정/삭제
	 * 
	 * @param request
	 * @param reponse
	 */
    @RequestMapping("/operation/ccmtmpl/oprat1610.do")
	public ModelAndView oprat1610(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
	    String crudMode  = StringUtil.getParam(request, "crudMode", "").toUpperCase();
		String tmplNo = StringUtil.getParam(request, "tmplNo", ""); 
		String schTypeDvn = StringUtil.getParam(request, "schTypeDvn", "V"); 
		
	    ModelAndView mav =  new ModelAndView("operation/ccmtmpl/oprat1610");   

	    UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();
		
		CcmTmplVO vo = new CcmTmplVO();
		bind(request , vo);
		
		vo.setTypeDvn(schTypeDvn);
		vo.setRegUser(userEmpno);
		vo.setUpdtUser(userEmpno);
		
		if ("C".equals(crudMode)) {           // 등록처리
			
			vo.setRegUser(userEmpno);
			vo.setUpdtUser(userEmpno);
			
			String msg = null;
			
			if("Y".equals(vo.getUseYn()))
			{
				int result = Integer.parseInt(ccmTmplService.selectInsertTmpl(vo));
				
				if(result < 1){
					//(채널분류, 진행상태, 내외부구분) 당 사용여부가 사용인 템플릿이 존재하지 않는지 검사한 후 insert
					ccmTmplService.insertCcmTmpl(vo);
				}
				else{
					//(채널분류, 진행상태, 내외부구분) 당 사용여부가 사용인 템플릿이 존재할 경우 에러메세지  
					if("V".equals(schTypeDvn)){
						msg = "해당 [진행상태, 내외부구분]에 사용중인 템플릿이 존재합니다.";
					}
					else{
						msg = "해당 [진행상태]에 사용중인 템플릿이 존재합니다.";
					}
					mav.addObject("useCheck", "Y");
					mav.addObject("result", vo);
					mav.addObject("msg", msg);
					return mav;
				}
			}else{
				ccmTmplService.insertCcmTmpl(vo);
			}
			mav.addObject("result", vo);
			mav.addObject("crudMode", "");
			mav = new ModelAndView("operation/ccmtmpl/oprat1600");
			
		}
		else if ("U".equals(crudMode)){    // 수정처리

			vo.setUpdtUser(userEmpno);
			
			CcmTmplVO result = new CcmTmplVO();
			String changeYn = StringUtil.getParam(request, "changeYn", "");
			
			String msg = "";
			if("true".equals(changeYn)){
				if("Y".equals(vo.getUseYn()))
				{
					int cnt = Integer.parseInt(ccmTmplService.selectInsertTmpl(vo));
					if(cnt < 1){
						try{
							ccmTmplService.updateCcmTmpl(vo);
							result = ccmTmplService.selectByPrimaryKey(vo);
							msg="저장되었습니다.";
						}catch(Exception ex){
							msg="저장에 실패하였습니다.";
						}
					}
					else{
						//(등록채널, 처리상태) 당 사용여부가 사용인 템플릿이 존재할 경우 에러메세지  
						msg = "해당 [처리상태]에 사용중인 템플릿이 존재합니다.";
						result = vo;
						mav.addObject("crudMode", "");
					}
				}else{
					try{
						ccmTmplService.updateCcmTmpl(vo);
						result = ccmTmplService.selectByPrimaryKey(vo);
						msg="저장되었습니다.";
					}catch(Exception e){
						msg="저장이 실패하였습니다.";
					}
					
				}
			}else{
				try{
					ccmTmplService.updateCcmTmpl(vo);
					result = ccmTmplService.selectByPrimaryKey(vo);
					msg="저장되었습니다.";
				}catch(Exception ex){
					msg="저장이 실패하였습니다.";
				}
			}
			mav.addObject("msg", msg);
			mav.addObject("result", result);
		}
		else if ("D".equals(crudMode)){    // 삭제처리
			vo.setTmplNo(tmplNo);
			ccmTmplService.deleteCcmTmpl(vo); 
			
			mav.addObject("crudMode", "");
			mav = new ModelAndView("operation/ccmtmpl/oprat1600");
			
		}
		else if ("V".equals(crudMode)){	//상세내역
			vo.setTmplNo(tmplNo);
			mav.addObject("result", ccmTmplService.selectByPrimaryKey(vo));
		}

	    mav.addObject("F153", this.getCommonCode("F153"));
    	mav.addObject("F002", this.getCommonCode("F002"));
    	mav.addObject("F016", this.getCommonCode("F016"));
    	mav.addObject("F080", this.getCommonCode("F080"));
    	mav.addObject("F201", this.getCommonCode("F201"));
		return mav;
	}
	
}
